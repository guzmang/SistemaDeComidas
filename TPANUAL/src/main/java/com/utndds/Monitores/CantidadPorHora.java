package com.utndds.Monitores;

import com.utndds.excepciones.ConsultaNoValidaException;
import com.utndds.excepciones.DificultadIncorrectaException;
import com.utndds.personas.Usuario;
import com.utndds.recetas.Receta;

import java.util.Calendar;

import javax.persistence.*;

@Entity
public class CantidadPorHora extends ObserverBusqueda {
	@Id
	@GeneratedValue
	long obs_por_hora_id;
	private int cantidadBusquedasPorHora[] = new int[100];

	@Override
	public void actualizarBusquedas(Receta receta, Usuario usuario)
			throws DificultadIncorrectaException, ConsultaNoValidaException {
		Calendar calendario = Calendar.getInstance();
		int hora = calendario.get(Calendar.HOUR_OF_DAY);
		cantidadBusquedasPorHora[hora]++;
	}

	public int obtenerCantidadConsulta(int hora) {
		return cantidadBusquedasPorHora[hora];
	}

}
