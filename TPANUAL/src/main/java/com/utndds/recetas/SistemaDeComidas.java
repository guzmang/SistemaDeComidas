package com.utndds.recetas;

import java.util.Collection;
import java.util.HashSet;

import javax.persistence.*;

import com.utndds.excepciones.ConsultaNoValidaException;
import com.utndds.excepciones.DificultadIncorrectaException;

public class SistemaDeComidas implements RepositorioDeRecetas {

	@OneToMany
	public Collection<Receta> recetasPublicas = new HashSet<Receta>();

	public SistemaDeComidas(HashSet<Receta> recetasPublicas) {
		this.recetasPublicas = recetasPublicas;
	}

	public SistemaDeComidas() {
		super();
	}

	public void agregar(Receta recetaPublica) {
		recetasPublicas.add(recetaPublica);
	}

	public void quitar(Receta recetaPublica) {
		recetasPublicas.remove(recetaPublica);
	}

	public Collection<Receta> listarTodas() {
		return recetasPublicas;
	}

	@Override
	public Collection<Receta> buscar(Receta parametros)
			throws DificultadIncorrectaException, ConsultaNoValidaException {
		return null;
	}
}
