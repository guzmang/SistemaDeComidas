package com.utndds.consultas;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.stream.Collectors;

import com.utndds.recetas.ComponenteReceta;


public class Consulta {
	private Collection<Filtro> filtros = new ArrayList<Filtro>();
	
	public Consulta(Collection<Filtro> filtros) {
		this.filtros = filtros;
	}

	public Consulta() {
	}

	public void agregarFiltro(Filtro filtro) {
		filtros.add(filtro);
	}

	public Collection<ComponenteReceta> filtrar(Collection<ComponenteReceta> recetas) {
		if (filtros.isEmpty())
			return recetas;

		for (Iterator<Filtro> i = filtros.iterator(); i.hasNext();) {
			Filtro filtro = i.next();
			recetas = recetas.stream()
					.filter(receta -> filtro.cumpleFiltro(receta))
					.collect(Collectors.toList());
		}

		return recetas;
	}

	
	// Devuelve el resultado de una consulta
	public int cantidadResultadosConsulta(Collection<ComponenteReceta> recetas) {
		return this.filtrar(recetas).size();
	}

	// getter para el command que necesita los par�metros de busqueda
	public Collection<Filtro> getFiltros() {
		return filtros;
	}
}
