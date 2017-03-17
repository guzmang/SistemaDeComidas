package com.utndds.tests;

import java.time.LocalDate;
import java.util.HashSet;

import org.junit.Before;
import org.junit.Test;

import com.utndds.condiciones.Condicion;
import com.utndds.condiciones.Diabetico;
import com.utndds.creadores.CreadorReceta;
import com.utndds.creadores.CreadorUsuario;
import com.utndds.personas.Usuario;
import com.utndds.recetas.Producto;
import com.utndds.recetas.RecetaSimple;

import static org.junit.Assert.*;

public class TestDiabetico {

	private Usuario diabetico;
	private HashSet<Condicion> condiciones;
	private HashSet<String> preferencias;

	private RecetaSimple recetaNoAzucarada;
	private RecetaSimple recetaNeutra;
	private RecetaSimple recetaMuyAzucarada;

	@Before
	public void SetUp() {

		condiciones = new HashSet<Condicion>();
		condiciones.add(new Diabetico());
		preferencias = new HashSet<String>();
		preferencias.add("Carne");
		preferencias.add("Chivito");

		diabetico = new CreadorUsuario().setNombre("Demian").setAsHombre()
				.setNacimiento(LocalDate.of(2000, 11, 9)).setAltura(168)
				.setPeso(75).setRutina("Activo").setCondiciones(condiciones)
				.setPreferencias(preferencias).build();

		recetaNoAzucarada = new CreadorReceta().addCondimento(new Producto("Cacao", 90, false,
				"Gramos")).addIngrediente(new Producto("Leche", 3, false,
				"Litros")).build();

		recetaMuyAzucarada = new CreadorReceta().addCondimento(new Producto("azucar", 101, false,
				"Gramos")).build();

		recetaNeutra = new CreadorReceta().addCondimento(new Producto("Azucar", 100, false,
				"Gramos")).build();

	}

	@Test
	public void verificarSiElDiabeticoEsValido() {
		assertTrue(diabetico.esUsuarioValido());
	}

	@Test
	public void elDiabeticocumpleCondicionPreexistente() {
		assertTrue(diabetico.subsanaCondicionesPreexistentes());
	}

	@Test
	public void elDiabeticoPuedeComerConMenosDe100GramosDeAzucar() {
		assertFalse(diabetico.noPuedeComer(recetaNoAzucarada));
	}

	@Test
	public void elDiabeticoNoPuedeComerCon100GramosDeAzucarExactamente() {
		assertTrue(diabetico.noPuedeComer(recetaNeutra));
	}

	@Test
	public void elDiabeticoNoPuedeComerConMasDe100GramosDeAzucar() {
		assertTrue(diabetico.noPuedeComer(recetaMuyAzucarada));
	}

}