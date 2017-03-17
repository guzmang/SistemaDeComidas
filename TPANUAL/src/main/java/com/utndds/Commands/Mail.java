package com.utndds.Commands;

import com.utndds.consultas.Consulta;
import com.utndds.personas.Usuario;
import com.utndds.excepciones.UsuarioNoEspecialException;

import java.util.ArrayList;
import java.util.Collection;

public class Mail implements AdministradorFunciones {

	private MailSender mailSender;

	private Collection<Usuario> usuariosEspeciales = new ArrayList<Usuario>();
	// lista de usuarios que hay que mandar mail con la consulta
	
	private Usuario usuarioConsultante = new Usuario();
	// usuario que hace la consulta
	
	public void execute(Consulta consulta) {
		
		if(usuarioConsultanteEsEspecial()){
		mailSender.execute(consulta);
		}
		else throw new UsuarioNoEspecialException();
	}

	private boolean usuarioConsultanteEsEspecial() {
		
		return usuariosEspeciales.contains(usuarioConsultante);
	}

	public void setBusquedaRealizada(Consulta consulta) {
	}
	
	public void setUsuarioConsultante(Usuario usuario){
		this.usuarioConsultante = usuario;
	}
	
	public void setMailSender(MailSender mailSender){
		this.mailSender = mailSender;
	}
	public void agregarUsuarioEspecial(Usuario nuevoUsuario){
		this.usuariosEspeciales.add(nuevoUsuario);
		
	}
}