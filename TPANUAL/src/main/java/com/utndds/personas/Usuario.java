package com.utndds.personas;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.stream.Collectors;

import javax.persistence.*;

import com.utndds.condiciones.Condicion;
import com.utndds.recetas.*;

@Entity
public class Usuario {

	@Id
	@GeneratedValue
	long usuario_id;

	@Column(name = "nombre")
	private String nombre;
	@Enumerated(EnumType.ORDINAL)
	private Sexo sexo;
	private LocalDate nacimiento;
	private double altura;
	private double peso;

    @ElementCollection
	private Collection<String> preferencias = new HashSet<String>();
    @ElementCollection
	private Collection<String> disgustos = new HashSet<String>();
	@OneToMany(cascade = {CascadeType.ALL})
	private Collection<Condicion> condiciones = new HashSet<Condicion>();
	@ManyToMany(cascade = {CascadeType.ALL})
	private Collection<GrupoDeUsuarios> grupos = new HashSet<GrupoDeUsuarios>();
	@ManyToMany(cascade = {CascadeType.ALL})
	private Collection<ComponenteReceta> recetas = new HashSet<ComponenteReceta>();
	private String rutina;

	public Usuario() {
	}

	public Usuario(String nombre, Sexo sexo, LocalDate nacimiento,
			double altura, double peso, String rutina,
			HashSet<Condicion> condiciones, HashSet<String> preferencias) {
		super();
		this.nombre = nombre;
		this.sexo = sexo;
		this.nacimiento = nacimiento;
		this.altura = altura;
		this.peso = peso;
		this.rutina = rutina;
		this.condiciones = condiciones;
		this.preferencias = preferencias;
	}

	public Usuario(double altura, double peso) {
		super();
		this.altura = altura;
		this.peso = peso;
	}

	public Usuario(String nombre) {
		this.nombre = nombre;
	}

	public Usuario(String nombre, HashSet<Condicion> condiciones,
			HashSet<String> preferencias) {
		this.nombre = nombre;
		this.condiciones = condiciones;
		this.preferencias = preferencias;
	}

	public void modificarCondiciones(HashSet<Condicion> condicionesARemover,
			HashSet<Condicion> condicionesAAgregar) {
		this.condiciones.removeAll(condicionesARemover);
		this.condiciones.addAll(condicionesAAgregar);
	}

	public void modificarReceta(Receta receta, HashSet<Producto> ingredientes,
			HashSet<Producto> condimentos) {
		receta.modificarIngredientes(ingredientes);
		receta.modificarCondimentos(condimentos);
	}

	public HashSet<ComponenteReceta> listarTodas() {
		return (HashSet<ComponenteReceta>) recetas.stream()
				.filter(receta -> receta.puedeSerVistaPor(this))
				.collect(Collectors.toSet());
	}

	public boolean puedeModificarReceta(Receta receta) {
		return (receta.isPublica() || recetas.contains(receta));
	}

	public boolean tieneCamposObligatorios() {
		return ((this.nombre != null) && (this.peso != 0.0)
				&& (this.nacimiento != null) && (this.altura != 0.0));
	}

	public boolean tienePreferencia() {
		return !preferencias.isEmpty();
	}

	public boolean leGusta(HashSet<String> comidas) {
		return !(Collections.disjoint(comidas, preferencias));
	}

	public boolean esNacimientoValido() {
		return nacimiento.isBefore(LocalDate.now());
	}

	public LocalDate getFechaNacimiento() {
		return nacimiento;
	}

	public boolean esUsuarioValido() {
		return (this.tieneCamposObligatorios() && this.condicionesValidas()
				&& this.nombreEsMayorACuatro() && this.esNacimientoValido());
	}

	public boolean sigueRutinaSaludable() {
		return ((valorEnRango(this.calcularIMC(), 18, 30)
				&& (condiciones.isEmpty()) || this
					.subsanaCondicionesPreexistentes()));
	}

	public boolean valorEnRango(double valor, int min, int max) {
		return ((valor > min) && (valor < max));
	}

	public boolean nombreEsMayorACuatro() {
		return nombre.length() >= 4;
	}

	public boolean subsanaCondicionesPreexistentes() {
		return condiciones.stream().anyMatch(
				condicion -> condicion.cumpleCondicionPreexistente(this));
	}

	public boolean condicionesValidas() {
		return (condiciones.stream().allMatch(condicion -> condicion
				.esValido(this)));
	}

	public double calcularIMC() {
		return altura != 0 ? getPeso() / (altura * altura) : 0;
	}

	public void modificarRecetaPublica(ComponenteReceta receta, String nombre,
			HashSet<Producto> ingredientes, HashSet<Producto> condimentos,
			String[] explicacionPasos, int calorias,
			String dificultadPreparacion, String temporada,
			HashSet<ComponenteReceta> subrecetas) {
		this.agregar(receta);
		receta.modificarReceta(nombre, ingredientes, condimentos,
				explicacionPasos, calorias, dificultadPreparacion, temporada,
				subrecetas);
	}

	public boolean seLePuedeSugerir(Receta receta) {
		return receta.getProductos().size() == 0
				|| (disgustos.stream().allMatch(
						disgusto -> !receta.contains(disgusto)) && this
						.cumpleCondiciones(receta));
	}

	public boolean cumpleCondiciones(Receta receta) {
		return condiciones.stream().allMatch(c -> (!c.noPuedeComer(receta)));
	}

	public boolean noPuedeComer(Receta receta) {
		return condiciones.stream().anyMatch(c -> c.noPuedeComer(receta));
	}

	public void agregar(ComponenteReceta receta) {
		if (receta.esValida())
			recetas.add(receta);
	}

	public void quitar(Receta receta) {
		recetas.remove(receta);
	}

	public void agregarCondicion(Condicion condicion) {
		condiciones.add(condicion);
	}

	public void agregarDisgusto(String disgusto) {
		disgustos.add(disgusto);
	}

	public String getNombre() {
		return nombre;
	}

	public Collection<String> getDisgustos() {
		return disgustos;
	}

	public void setDisgustos(HashSet<String> disgustos) {
		this.disgustos = disgustos;
	}

	public String getRutina() {
		return rutina;
	}

	public double getAltura() {
		return altura;
	}

	public double getPeso() {
		return peso;
	}

	public void setPeso(double peso) {
		this.peso = peso;
	}

	public Collection<String> getPreferencias() {
		return preferencias;
	}

	public Collection<Condicion> getCondiciones() {
		return condiciones;
	}

	public void setPreferencias(HashSet<String> preferencias) {
		this.preferencias = preferencias;
	}

	public Collection<ComponenteReceta> getRecetas() {
		return recetas;
	}

	public void setGrupo(GrupoDeUsuarios grupo) {
		grupos.add(grupo);
	}

	public Collection<GrupoDeUsuarios> getGrupos() {
		return grupos;
	}

	public Sexo getSexo() {
		return sexo;
	}

	public void setSexo(Sexo sexo) {
		this.sexo = sexo;
	}

	public boolean esHombre() {
		return sexo.equals(Sexo.MASCULINO);
	}

	public enum Sexo {
		MASCULINO, FEMENINO
	}

	public boolean seLlamaIgual(Usuario otroUsuario) {
		return nombre.equalsIgnoreCase(otroUsuario.getNombre());
	}

	public boolean tieneCondicion() {
		return !condiciones.isEmpty();
	}

	public boolean contieneTodasLasCondiciones(Usuario otroUsuario) {
		return condiciones.stream().allMatch(
				condicion -> otroUsuario.getCondiciones().contains(condicion));
	}
}
