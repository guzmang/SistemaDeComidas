package com.utndds.Commands;

import java.util.Collection;

import com.utndds.consultas.Consulta;
import com.utndds.consultas.Filtro;

public interface MailSender {

	public void enviarMail(Collection<Filtro> parametrosConsulta,
			int cantidadResultados);

	public void execute(Consulta consulta);
}
