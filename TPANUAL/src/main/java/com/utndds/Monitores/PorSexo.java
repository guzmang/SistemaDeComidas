package com.utndds.Monitores;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.*;

import com.utndds.excepciones.ConsultaNoValidaException;
import com.utndds.excepciones.DificultadIncorrectaException;
import com.utndds.personas.Usuario;
import com.utndds.personas.Usuario.Sexo;
import com.utndds.recetas.Receta;

@Entity
public class PorSexo extends ObserverBusqueda {
	@Id
	@GeneratedValue
	long obs_por_sexo_id;
	@Transient
	private Map<Receta, Integer> busqueda = new HashMap<Receta, Integer>();
	@Lob
	private Map<Receta, Integer> busquedasDeHombres = new HashMap<Receta, Integer>();
	@Lob
	private Map<Receta, Integer> busquedasDeMujeres = new HashMap<Receta, Integer>();

	@Override
	public void actualizarBusquedas(Receta receta, Usuario usuario)
			throws DificultadIncorrectaException, ConsultaNoValidaException {
		if (usuario.getSexo().equals(Sexo.MASCULINO)) {
			this.aumentarEnUnoLaReceta(receta, busquedasDeHombres);
		} else {
			this.aumentarEnUnoLaReceta(receta, busquedasDeMujeres);
		}
	}

	public Receta getRecetaMasBuscada(Sexo sexo) {
		if (sexo.equals(Sexo.MASCULINO))
			busqueda = busquedasDeHombres;
		else
			busqueda = busquedasDeMujeres;

		Comparator<Receta> compararValor = new Comparator<Receta>() {
			@Override
			public int compare(Receta a, Receta b) {
				return Integer.compare(busqueda.get(a), busqueda.get(b));
			}
		};

		return Collections.max(busqueda.keySet(), compararValor);
	}
}