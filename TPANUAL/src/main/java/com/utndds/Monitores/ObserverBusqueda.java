package com.utndds.Monitores;

import java.util.Map;

import javax.persistence.*;

import com.utndds.excepciones.ConsultaNoValidaException;
import com.utndds.excepciones.DificultadIncorrectaException;
import com.utndds.personas.Usuario;
import com.utndds.recetas.Receta;


@Inheritance(strategy=InheritanceType.TABLE_PER_CLASS)

public abstract class ObserverBusqueda implements Observer {
	
	public void actualizarBusquedas(Receta receta, Usuario usuario)
			throws DificultadIncorrectaException, ConsultaNoValidaException {
			}

	

	public void aumentarEnUnoLaReceta(Receta receta,
			Map<Receta, Integer> dic) {
		if (dic.containsKey(receta)) {
			int val = dic.get(receta);
			dic.put(receta, val++);
		} else {
			dic.put(receta, 0);
		}
	}


}