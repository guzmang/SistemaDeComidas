package com.utndds.Monitores;

import com.utndds.excepciones.ConsultaNoValidaException;
import com.utndds.excepciones.DificultadIncorrectaException;
import com.utndds.personas.Usuario;
import com.utndds.recetas.Receta;

public interface Observer {

	public void actualizarBusquedas(Receta receta, Usuario usuario) throws DificultadIncorrectaException, ConsultaNoValidaException;
}
