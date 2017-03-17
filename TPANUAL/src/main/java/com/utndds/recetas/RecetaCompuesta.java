package com.utndds.recetas;

import java.util.Collection;
import java.util.HashSet;
import java.util.stream.Collectors;

import javax.persistence.*;

import com.utndds.condiciones.Condicion;
import com.utndds.consultas.Filtro;
import com.utndds.personas.Usuario;

@Entity
@DiscriminatorValue("Compuesta")
public class RecetaCompuesta extends ComponenteReceta {

	@ManyToMany(cascade = {CascadeType.ALL})
	private Collection<ComponenteReceta> subrecetas;

	public RecetaCompuesta(String nombre, Usuario autor,
						   Collection<Producto> ingredientes, Collection<Producto> condimentos,
						   String[] explicacionPasos, int calorias,
						   String dificultadPreparacion, String temporada,
						   Collection<ComponenteReceta> subrecetas, Collection<Condicion> condiciones) {
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
		this.subrecetas = subrecetas;
	}

	public RecetaCompuesta() {
	}

	public RecetaCompuesta(String nombre) {
		this.nombre = nombre;
	}
	

	public void agregarSubreceta(ComponenteReceta receta) {
		if (receta.esValida())
			subrecetas.add(receta);
	}

	public void quitarSubreceta(Receta receta) {
		subrecetas.remove(receta);
	}

	public boolean esDificil() {
		return subrecetas.stream().anyMatch(receta -> receta.esDificil());
	}

	public boolean esValida() {
		return subrecetas.stream().allMatch(receta -> receta.esValida());
	}

	@SuppressWarnings("unchecked")
	public Collection<Condicion> getInadecuados() {
		// return subrecetas.stream().map(r -> r.getInadecuados()).collect(Collectors.toCollection());
		return new HashSet<>();
	}

	public int getCalorias() {
		return subrecetas.stream().mapToInt(receta -> receta.getCalorias()).sum();
	}

	public Collection<ComponenteReceta> getSubrecetas() {
		return subrecetas;
	}

	public Boolean contains(String producto) {
		return subrecetas.stream()
				.anyMatch(receta -> receta.contains(producto));
	}

	public Collection<Producto> getIngredientes() {
		subrecetas.forEach(subreceta -> ingredientes.addAll(subreceta
				.getIngredientes()));
		return ingredientes;
	}

	public Collection<Producto> getCondimentos() {
		subrecetas.forEach(subreceta -> condimentos.addAll(subreceta
				.getCondimentos()));
		return condimentos;
	}

	public void marcarComoFavorita() {
		favorita = true;

	}

	public boolean contienePreferencia(Producto preferencia) {
		return subrecetas.stream().anyMatch(
				receta -> receta.contienePreferencia(preferencia));
	}

	public void modificarReceta(String nombre, Collection<Producto> ingredientes,
								Collection<Producto> condimentos, String[] explicacionPasos,
								int calorias, String dificultadPreparacion, String temporada,
								Collection<ComponenteReceta> subrecetas) {

	}

	public boolean puedeSerVistaPor(Usuario usuario) {
		return subrecetas.stream().allMatch(
				subreceta -> subreceta.puedeSerVistaPor(usuario));
	}

	public void modificarIngredientes(HashSet<Producto> ingredientesNuevos) {
		ingredientes.clear();
		ingredientes.addAll(ingredientesNuevos);
	}

	public void modificarCondimentos(HashSet<Producto> condimentosNuevos) {
		condimentos.clear();
		condimentos.addAll(condimentosNuevos);
	}

	public boolean isPublica() {
		return publica;
	}

	public Double getCantidadDeUnProducto(String producto) {
		return subrecetas.stream()
				.mapToDouble(subreceta -> subreceta.getCantidadDeUnProducto(producto))
				.sum();
	}

	public Collection<Producto> getProductos() {
		HashSet<Producto> productos = new HashSet<Producto>();
		subrecetas.forEach(subreceta -> productos.addAll(subreceta
				.getProductos()));
		return productos;
	}

	@Override
	public boolean esInadecuadaPara(Condicion condicion) {
		return subrecetas.stream().anyMatch(
				subreceta -> subreceta.esInadecuadaPara(condicion));
	}

	@Override
	public boolean cumpleLosFiltros(HashSet<Filtro> filtros) {
		return subrecetas.stream().allMatch(receta -> receta.cumpleLosFiltros(filtros));
	}
}
