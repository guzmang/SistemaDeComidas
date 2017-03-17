package com.utndds.recetas;

import java.util.Collection;

import com.utndds.consultas.QueComemosAdapter;
import com.utndds.excepciones.ConsultaNoValidaException;
import com.utndds.excepciones.DificultadIncorrectaException;

public class RepositorioDeRecetasMock implements RepositorioDeRecetas {

	QueComemosAdapter adapter; 
	Collection<Receta> recetas;

	public void agregar(Receta receta) {
		recetas.add(receta);
	};

	public void quitar(Receta receta) {
		recetas.remove(receta);
	};

	public Collection<Receta> listarTodas() {
		return recetas;
	};

	public Collection<Receta> buscar(Receta parametros)
			throws DificultadIncorrectaException, ConsultaNoValidaException {
		return adapter.buscar(parametros);
	}

	{

	}
}
