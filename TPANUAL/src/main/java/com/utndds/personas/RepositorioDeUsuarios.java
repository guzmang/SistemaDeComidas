package com.utndds.personas;

import java.util.stream.Collectors;
import java.util.List;
import java.util.ArrayList;

import com.utndds.excepciones.NoEncontroUsuarioException;

public class RepositorioDeUsuarios implements RepoUsuarios {

	private List<Usuario> usuariosPendientes = new ArrayList<Usuario>();
	private List<Usuario> usuariosAceptados = new ArrayList<Usuario>();

	private List<Usuario> usuariosRechazados = new ArrayList<Usuario>();
	private List<String> motivosRechazados = new ArrayList<String>();

	public RepositorioDeUsuarios() {
		super();
	}

	public void addPendiente(Usuario pendiente) {
		usuariosPendientes.add(pendiente);
	}

	public void add(Usuario usuario) {
		this.usuariosAceptados.add(usuario);
		this.usuariosPendientes.remove(usuario);
	}

	public void remove(Usuario usuario) {
		String motivo = "Rechazado";
		this.usuariosRechazados.add(usuario);
		this.motivosRechazados.add(motivo);
		this.usuariosPendientes.remove(usuario);
	}

	public void update(Usuario usuario) {
		this.usuariosAceptados.remove(usuario);
		this.usuariosAceptados.add(usuario);
	}

	public Usuario get(Usuario usuario) throws NoEncontroUsuarioException {
		if (usuariosAceptados.stream().noneMatch(
				user -> user.seLlamaIgual(usuario))) {
			throw new NoEncontroUsuarioException();
		}
		return this.getUsuariosAceptadosQueMatchean(usuario).get(0);
	}

	public List<Usuario> list(Usuario usuario) {
		if (usuario.tieneCondicion()) {
			return this.getUsuariosAceptadosQueMatchean(usuario).stream()
					.filter(user -> user.contieneTodasLasCondiciones(usuario))
					.collect(Collectors.toList());
		}
		return this.getUsuariosAceptadosQueMatchean(usuario);
	}

	// Getters

	public List<Usuario> getUsuariosPendientes() {
		return usuariosPendientes;

	}

	public List<Usuario> getUsuariosAceptados() {
		return usuariosAceptados;
	}

	public List<Usuario> getUsuariosRechazados() {
		return usuariosRechazados;
	}

	public List<String> getMotivosRechazados() {
		return motivosRechazados;
	}

	public List<Usuario> getUsuariosAceptadosQueMatchean(Usuario usuario) {
		return usuariosAceptados.stream()
				.filter(user -> user.seLlamaIgual(usuario))
				.collect(Collectors.toList());
	}

}
