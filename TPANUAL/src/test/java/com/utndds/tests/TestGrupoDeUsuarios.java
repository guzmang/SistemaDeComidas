package com.utndds.tests;

import static org.junit.Assert.*;

import java.util.HashSet;

import org.junit.Before;
import org.junit.Test;

import com.utndds.personas.GrupoDeUsuarios;
import com.utndds.personas.Usuario;
import com.utndds.recetas.RecetaSimple;
import com.utndds.condiciones.Condicion;
import com.utndds.condiciones.Diabetico;
import com.utndds.creadores.CreadorUsuario;

public class TestGrupoDeUsuarios {

	public GrupoDeUsuarios grupo;
	public HashSet<Usuario> users;
	public Usuario usuario1;
	public Usuario usuario2;
	public HashSet<RecetaSimple> recetas;
	public HashSet<Condicion> condiciones;
	public RecetaSimple receta1 = new RecetaSimple();
	public RecetaSimple receta2 = new RecetaSimple();

	public GrupoDeUsuarios grupo2;
	public HashSet<Usuario> users2;
	public Usuario usuarioA;
	public Usuario usuarioB;

	@Before
	public void setUp() {

		recetas = new HashSet<RecetaSimple>();
		recetas.add(receta1);
		recetas.add(receta2);
		users = new HashSet<Usuario>();
		users.add(usuario1);
		users.add(usuario2);
		grupo = new GrupoDeUsuarios("Las pepas", users);

		condiciones = new HashSet<Condicion>();
		condiciones.add(new Diabetico());

		HashSet<String> preferencias = new HashSet<String>();
		preferencias.add("Una preferencia");

		usuarioA = new CreadorUsuario().setNombre("Lilolu").setCondiciones(condiciones).setPreferencias(preferencias).build();
		usuarioB = new CreadorUsuario().setNombre("Lulu").setCondiciones(condiciones).setPreferencias(preferencias).build();

		users2 = new HashSet<Usuario>();
		users2.add(usuarioA);
		users2.add(usuarioB);
		grupo2 = new GrupoDeUsuarios("One Direction", users2);

	}

	@Test
	public void verificarSiSeAgregaRecetaNueva() {
		RecetaSimple recetaNueva = new RecetaSimple();
		grupo.agregar(recetaNueva);
		assertTrue(grupo.contieneReceta(recetaNueva));
	}

	@Test
	public void verificarSiSeBorraRecetaExistente() {
		grupo.quitar(receta1);
		assertFalse(grupo.contieneReceta(receta1));
	}

	@Test
	public void verificarQueNoSeRompeAlIntentarBorrarRecetaQueNoExiste() {
		RecetaSimple recetaInexistente = new RecetaSimple();
		grupo.quitar(recetaInexistente);
		assertFalse(grupo.contieneReceta(recetaInexistente));
	}

	@Test
	public void verificarSiSeAgregaUsuarioNuevo() {
		// Usuario nuevoUsuario = usuarioA;
		grupo.agregarUsuario(usuarioA);
		assertTrue(grupo.contains(usuarioA));
	}

	@Test
	public void verificarSiSeBorraUsuarioExistente() {
		grupo2.quitarUsuario(usuarioA);
		assertFalse(grupo2.contains(usuarioA));
	}

	@Test
	public void verificarQueNoSeAgregaUsuarioExistente() {
		boolean sePudoAgregar = true;
		try {
			grupo.agregarUsuario(usuario2);
		} catch (RuntimeException e) {
			sePudoAgregar = false;
		}
		assertFalse(sePudoAgregar);
	}

	@Test
	public void verificarQueNoSeRompeAlIntentarBorrarUsuarioQueNoExiste() {
		Usuario usuarioInexistente = new Usuario();
		grupo.quitarUsuario(usuarioInexistente);
		assertEquals(grupo.getUsuarios().size(), 1);
	}
}
