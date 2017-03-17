package com.utndds.Monitores;

import com.utndds.Commands.LogMasDe100Resultados;
import com.utndds.consultas.Consulta;
import com.utndds.excepciones.ConsultaNoValidaException;
import com.utndds.excepciones.DificultadIncorrectaException;
import com.utndds.personas.Usuario;
import com.utndds.recetas.Receta;


public class LoggerMayor100 implements Observer {

	private Consulta consulta;

	private Usuario usuario;
	private LogMasDe100Resultados logger;

	@Override
	public void actualizarBusquedas(Receta receta, Usuario usuario)
			throws DificultadIncorrectaException, ConsultaNoValidaException {

		if (this.resultadosConsultaMayorA100())
			logger.execute(consulta);
	}

	public boolean resultadosConsultaMayorA100() {
		return consulta.filtrar(usuario.getRecetas()).size() > 100;
	}

}
