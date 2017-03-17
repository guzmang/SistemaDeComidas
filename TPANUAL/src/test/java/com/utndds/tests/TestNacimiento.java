package com.utndds.tests;

import java.time.LocalDate;

import org.junit.Test;

import com.utndds.creadores.CreadorUsuario;
import com.utndds.personas.Usuario;

import static org.junit.Assert.*;

public class TestNacimiento {
	@Test
	public void VerificarNacimientoValido() {
		Usuario usuario = new CreadorUsuario().setNacimiento(LocalDate.of(2000, 02, 02)).build();
		assertTrue(usuario.esNacimientoValido());
		
	}

	@Test
	public void VerificarNacimientoInvalido() {
		Usuario usuario = new CreadorUsuario().setNacimiento(LocalDate.of(2020, 02, 02)).build();
		assertFalse(usuario.esNacimientoValido());

	}
}
