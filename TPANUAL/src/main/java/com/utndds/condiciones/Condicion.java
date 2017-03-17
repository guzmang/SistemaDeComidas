package com.utndds.condiciones;

import javax.persistence.*;

import com.utndds.personas.Usuario;
import com.utndds.recetas.Receta;

@Entity
@Inheritance
@DiscriminatorColumn(name = "NOMBRE_CONDICION")
public abstract class Condicion {

	@Id
	@GeneratedValue
	long condicion_id;

	public boolean esValido(Usuario usuario) {
		return true;
	}

	public boolean cumpleCondicionPreexistente(Usuario usuario) {
		return true;
	}

	public boolean noPuedeComer(Receta receta) {
		return false;
	}

}
