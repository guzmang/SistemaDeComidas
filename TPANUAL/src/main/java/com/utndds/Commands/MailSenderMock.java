package com.utndds.Commands;

import java.util.Collection;

import com.utndds.consultas.Consulta;
import com.utndds.consultas.Filtro;
import com.utndds.personas.Usuario;

public class MailSenderMock implements MailSender{
	
	private Usuario usuario;

	public void enviarMail(Collection<Filtro> parametrosConsulta,
			int cantidadResultados) {

	}

	public void execute(Consulta consulta) {
		this.enviarMail(consulta.getFiltros(), consulta
				.filtrar(usuario.getRecetas()).size());
	}

}
