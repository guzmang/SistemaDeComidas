package com.utndds.tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.utndds.personas.Usuario;

public class TestIMC {

	Usuario bianca;

	@Test
	public void IMCEsCorrecto() {
		assertEquals(19.03, bianca.calcularIMC(), 0.02);
	}

	@Test
	public void IMCEsCorrectoCuandoNoSeEspecificaElPeso() {
		bianca = new Usuario(1.70,0);
		assertEquals(0, bianca.calcularIMC(), 0.02);
	}

	@Test
	public void IMCEsCorrectoCuandoNoSeEspecificaLaAltura() {
		bianca = new Usuario(0,55);
		assertEquals(0, bianca.calcularIMC(), 0.02);
	}
	
	@Test
	public void IMCEsCorrectoCuandoNoSeInicializanDatos() {
		bianca = new Usuario();
		assertEquals(0, bianca.calcularIMC(), 0.02);
	}
	
	@Before
	public void setup() {
		bianca = new Usuario(1.70, 55);
	}
}
