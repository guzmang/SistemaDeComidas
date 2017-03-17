package com.utndds.condiciones;

import java.util.Collections;
import java.util.HashSet;

import com.utndds.personas.Usuario;
import com.utndds.recetas.Receta;

import javax.persistence.*;

@DiscriminatorValue("Vegano")
public class Vegano extends Condicion {

	private HashSet<String> comidasCarnivoras = new HashSet<String>();
	private HashSet<String> frutas = new HashSet<String>();

	public Vegano() {
		Collections.addAll(comidasCarnivoras, "Pollo", "Carne", "Cuadril",
				"Chivito", "Chori");
		Collections.addAll(frutas, "Banana", "Manzana", "Naranja", "Kiwi");

	}

	public boolean esValido(Usuario usuario) {
		return !(usuario.leGusta(comidasCarnivoras));
	}

	public boolean cumpleCondicionPreexistente(Usuario usuario) {
		return usuario.leGusta(frutas);
	}

	public boolean noPuedeComer(Receta receta) {
		return comidasCarnivoras.stream().anyMatch(
				comida -> receta.contains(comida));
	}

	public String getName() {
		return "Vegano";

	}

}
