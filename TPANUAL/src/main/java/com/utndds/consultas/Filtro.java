package com.utndds.consultas;

import com.utndds.recetas.Receta;

public interface Filtro {
	public boolean cumpleFiltro(Receta receta);
}
