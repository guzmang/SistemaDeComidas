package com.utndds.Commands;

import com.utndds.personas.Usuario;
import com.utndds.consultas.Consulta;

public class MarcadorFav implements AdministradorFunciones {

	private Usuario usuario;
	private Consulta consulta;

	public void execute(Consulta consulta) {
		this.consulta.filtrar(usuario.getRecetas()).stream()
				.forEach(r -> r.marcarComoFavorita());
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;

	}

	public void setBusquedaRealizada(Consulta consulta) {
		this.consulta = consulta;

	}
}
