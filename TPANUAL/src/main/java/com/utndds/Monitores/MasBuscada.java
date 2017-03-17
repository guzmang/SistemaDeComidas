package com.utndds.Monitores;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.*;

import com.utndds.excepciones.ConsultaNoValidaException;
import com.utndds.excepciones.DificultadIncorrectaException;
import com.utndds.personas.Usuario;
import com.utndds.recetas.Receta;

@Entity
public class MasBuscada extends ObserverBusqueda {

	@Id
	@GeneratedValue
	long obs_mas_buscada_id;
	@Lob
	private Map<Receta, Integer> recetasBuscadas = new HashMap<Receta, Integer>();

	@Override
	public void actualizarBusquedas(Receta receta, Usuario usuario)
			throws DificultadIncorrectaException, ConsultaNoValidaException {
		this.aumentarEnUnoLaReceta(receta, recetasBuscadas);
	}

	public Receta getRecetaMasBuscada() {
		return Collections.max(recetasBuscadas.keySet(), compararValor);
	}

	@Transient
	Comparator<Receta> compararValor = new Comparator<Receta>() {
		@Override
		public int compare(Receta a, Receta b) {
			return Integer.compare(recetasBuscadas.get(a),
					recetasBuscadas.get(b));
		}
	};

}
