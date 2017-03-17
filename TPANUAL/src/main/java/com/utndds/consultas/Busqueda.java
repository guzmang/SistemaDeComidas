package com.utndds.consultas;

import java.util.Collection;
import java.util.HashSet;
import java.util.stream.Collectors;



import com.utndds.Monitores.ObserverBusqueda;
import com.utndds.excepciones.ConsultaNoValidaException;
import com.utndds.excepciones.DificultadIncorrectaException;
import com.utndds.personas.Usuario;
import com.utndds.recetas.ComponenteReceta;
import com.utndds.recetas.RecetaSimple;


public class Busqueda {

	private Usuario usuario = new Usuario();
	private HashSet<Filtro> filtros = new HashSet<Filtro>();
	private HashSet<ObserverBusqueda> observers = new HashSet<ObserverBusqueda>();

	public Busqueda(HashSet<Filtro> filtros) {
		this.filtros = filtros;
	}

	public Busqueda() {
	}

	public void agregarFiltro(Filtro filtro) {
		filtros.add(filtro);
	}

	public Collection<ComponenteReceta> filtrar(Collection<ComponenteReceta> collection) {
		if (filtros.isEmpty())
			return collection;
		return collection.stream()
				.filter(receta -> receta.cumpleLosFiltros(filtros))
				.collect(Collectors.toSet());
	}

	public void agregarObserver(ObserverBusqueda observer) {
		observers.add(observer);
	}

	public void quitarObserver(ObserverBusqueda observer) {
		observers.remove(observer);
	}

	public Collection<ComponenteReceta> buscar(RecetaSimple parametros)
			throws DificultadIncorrectaException, ConsultaNoValidaException {
		observers.forEach(observer -> observer.actualizarBusquedas(parametros,
				usuario));
		return this.filtrar(usuario.getRecetas());
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
}
