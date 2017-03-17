package com.utndds.Monitores;

import java.util.Collection;
import java.util.HashSet;

import javax.persistence.*;

import com.utndds.excepciones.ConsultaNoValidaException;
import com.utndds.excepciones.DificultadIncorrectaException;
import com.utndds.personas.Usuario;
import com.utndds.recetas.Receta;

@Entity
public class VeganosQueBuscanDificiles extends ObserverBusqueda {
	@Id
	@GeneratedValue
	long obs_veganos_dificiles_id;
	@OneToMany(cascade = {CascadeType.ALL})
	private Collection<Usuario> veganosQueBuscaronRecetasDificiles = new HashSet<Usuario>();

	@Override
	public void actualizarBusquedas(Receta receta, Usuario usuario)
			throws DificultadIncorrectaException, ConsultaNoValidaException {
		if (receta.esDificil())
			veganosQueBuscaronRecetasDificiles.add(usuario);
	}

	public int getVeganosQueBuscaronDificiles() {
		return veganosQueBuscaronRecetasDificiles.size();
	}
}
