package com.utndds.recetas;

import java.util.Collection;

import com.utndds.excepciones.ConsultaNoValidaException;
import com.utndds.excepciones.DificultadIncorrectaException;

public interface RepositorioDeRecetas {

	Collection<Receta> buscar(Receta parametros)
			throws DificultadIncorrectaException, ConsultaNoValidaException;

	void quitar(Receta receta);

	void agregar(Receta receta);

	public Collection<Receta> listarTodas();

}
