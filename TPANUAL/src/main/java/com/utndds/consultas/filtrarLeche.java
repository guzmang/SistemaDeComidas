package com.utndds.consultas;

import com.utndds.recetas.Receta;

public class filtrarLeche implements Filtro {

	public boolean cumpleFiltro(Receta receta) {
		return receta.contains("leche");				
	}
}
