package com.utndds.Commands;

import com.utndds.consultas.Consulta;
import org.apache.logging.log4j.*;

public class LogMasDe100Resultados implements AdministradorFunciones {

	private Logger logger = LogManager
			.getLogger("consultasQueDevuelvenMasDe100Resultados");

	public void execute(Consulta consulta) {
		logger.info(consulta);
		
	}


}
	
