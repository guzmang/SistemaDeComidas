package com.utndds.condiciones;
import javax.persistence.*;

import com.utndds.personas.Usuario;
import com.utndds.recetas.Receta;

import java.util.Collections;
import java.util.HashSet;

@DiscriminatorValue("Hipertenso")
public class Hipertenso extends Condicion {

	private HashSet<String> alimentosPeligrosos = new HashSet<String>();
	
	public Hipertenso(){
		Collections.addAll(alimentosPeligrosos, "sal", "caldo");
	}
	
	public boolean esValido(Usuario usuario) {
		return usuario.tienePreferencia();
	}

	public boolean cumpleCondicionPreexistente(Usuario usuario) {
		return usuario.getRutina().equalsIgnoreCase("Intensivo");
	}

	public boolean noPuedeComer(Receta receta) {
		return alimentosPeligrosos.stream().anyMatch(alimento -> receta.contains(alimento));
	}

	public String getName() {
		return "Hipertenso";

	}
}
