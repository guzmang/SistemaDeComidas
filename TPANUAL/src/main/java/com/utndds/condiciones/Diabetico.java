package com.utndds.condiciones;

import javax.persistence.*;

import com.utndds.personas.Usuario;
import com.utndds.recetas.Receta;

@DiscriminatorValue("Diabetico")
public class Diabetico extends Condicion {

	public boolean cumpleCondicionPreexistente(Usuario usuario) {
		return (this.tieneRutinaActiva(usuario) || usuario.getPeso() <= 70);

	}

	public boolean esValido(Usuario usuario){
		return usuario.tienePreferencia() && usuario.getSexo() != null;
	}
	
	public boolean tieneRutinaActiva(Usuario usuario) {
		return usuario.getRutina().equalsIgnoreCase("Activo");
	}

	public boolean noPuedeComer(Receta receta) {
			return receta.getCantidadDeUnProducto("azucar") >= 100;
	}

	public String getName() {
		return "Diabetico";
	}
}
