package com.utndds.recetas;

import java.util.Collection;
import java.util.HashSet;
import java.util.stream.Collectors;

import javax.persistence.*;

import com.utndds.condiciones.Condicion;
import com.utndds.consultas.Filtro;
import com.utndds.personas.GrupoDeUsuarios;
import com.utndds.personas.Usuario;

@Entity
@DiscriminatorValue("Simple")
public class RecetaSimple extends ComponenteReceta {

	public RecetaSimple(String nombre, Usuario autor,
			HashSet<Producto> ingredientes, HashSet<Producto> condimentos,
			String[] explicacionPasos, int calorias,
			String dificultadPreparacion, String temporada,
			HashSet<Receta> subrecetas, HashSet<Condicion> condiciones) {
		super();
		this.setNombre(nombre);
		this.autor = autor;
		this.ingredientes = ingredientes;
		this.condimentos = condimentos;
		this.explicacionPasos = explicacionPasos;
		this.calorias = calorias;
		this.dificultadPreparacion = dificultadPreparacion;
		this.temporada = temporada;
		this.condiciones = condiciones;
	}

	public RecetaSimple() {
		super();
	}

	public void designarAutor(Usuario usuario) {
		this.autor = usuario;
	}

	public boolean esValida() {
		return ((!(ingredientes.isEmpty()))
				&& (10 < calorias && calorias < 5000) && this.esAutorValido());
	}

	public boolean esAutorValido() {
		return autor != null || publica;
	}

	public void modificarIngredientes(HashSet<Producto> ingredientes) {
		if (ingredientes != null) {
			this.ingredientes.clear();
			this.ingredientes = ingredientes;
		}
	}

	public void modificarCondimentos(HashSet<Producto> condimentos) {
		if (condimentos != null) {
			this.condimentos.clear();
			this.condimentos = condimentos;
		}
	}

	public int getCalorias() {
		return calorias;
	}

	public void agregarIngrediente(Producto ingrediente) {
		ingredientes.add(ingrediente);
	}

	public Collection<Producto> getIngredientes() {
		return ingredientes;
	}

	public Collection<Producto> getCondimentos() {
		return condimentos;
	}

	public boolean esInadecuadaPara(Condicion condicion) {
		return condicion.noPuedeComer(this);
	}

	public Collection<Condicion> getInadecuados() {
		return (HashSet<Condicion>) condiciones.stream()
				.filter(condicion -> condicion.noPuedeComer(this))
				.collect(Collectors.toSet());
	}

	public void modificarReceta(String nombre, Collection<Producto> ingredientes,
								Collection<Producto> condimentos, String[] explicacionPasos,
								int calorias, String dificultadPreparacion, String temporada,
								Collection<ComponenteReceta> subrecetas) {
		this.setNombre(nombre);
	//	this.modificarIngredientes(ingredientes); TODO
	//	this.modificarCondimentos(condimentos); TODO

	}

	public boolean isPublica() {
		return publica;
	}

	public void setPublica(boolean publica) {
		this.publica = publica;
	}

	public void agregarCondimento(Producto condimento) {
		condimentos.add(condimento);
	}

	public boolean noContiene(String clave) {
		return ingredientes.stream().allMatch(
				ingrediente -> !ingrediente.getNombre().equals(clave));
	}

	public boolean puedeSerVistaPor(Usuario usuario) {
		return (this.isPublica() || this.esElMismoAutor(usuario) || this
				.compartenElGrupoDondeEstaLaReceta(autor, usuario));

	}

	public boolean esElMismoAutor(Usuario otroUsuario) {
		return autor.getNombre().equalsIgnoreCase(otroUsuario.getNombre());
	}

	public boolean compartenElGrupoDondeEstaLaReceta(Usuario creador,
			Usuario usuario) {
		if (usuario.getGrupos().isEmpty())
			return false;
		Collection<GrupoDeUsuarios> compartidos = autor.getGrupos().stream()
				.filter(grupo -> usuario.getGrupos().contains(grupo))
				.collect(Collectors.toList());
		return compartidos.stream().anyMatch(
				compartido -> compartido.contieneReceta(this));
	}

	public boolean contienePreferencia(Producto preferencia) {
		return this.getProductos().contains(preferencia);
	}

	public Collection<ComponenteReceta> getSubrecetas() {
		Collection<ComponenteReceta> recetas = new HashSet<ComponenteReceta>();
		recetas.add(this);
		return recetas;
	}

	public Collection<Producto> getProductos() {
		HashSet<Producto> productos = new HashSet<Producto>();
		productos.addAll(ingredientes);
		productos.addAll(condimentos);
		return productos;
	}

	public Boolean contains(String producto) {
		return this.getProductos().stream()
				.anyMatch(p -> p.getNombre().equalsIgnoreCase(producto));
	}

	public HashSet<Producto> getProductosQueMatchean(String producto) {
		return (HashSet<Producto>) this.getProductos().stream()
				.filter(p -> p.getNombre().equalsIgnoreCase(producto))
				.collect(Collectors.toSet());

	}

	public Double getCantidadDeUnProducto(String producto) {
		return this.getProductosQueMatchean(producto).stream()
				.mapToDouble(p -> p.getCantidad()).sum();
	}

	public boolean cumpleLosFiltros(HashSet<Filtro> filtros) {
		return filtros.stream().allMatch(filtro -> filtro.cumpleFiltro(this));
	}


}
