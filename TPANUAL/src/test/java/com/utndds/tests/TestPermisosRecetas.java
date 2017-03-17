package com.utndds.tests;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

import java.time.LocalDate;
import java.util.HashSet;

import org.junit.Before;
import org.junit.Test;

import com.utndds.creadores.CreadorUsuario;
import com.utndds.personas.Usuario;
import com.utndds.recetas.*;

public class TestPermisosRecetas {

	public Usuario vegano;
	public Usuario diabetico;

	public Producto comida1;
	public Producto comida2;
	public Producto comida3;

	public HashSet<Producto> ingredientes;

	public Producto comida4;
	public Producto comida5;
	public Producto comida6;

	public HashSet<Producto> ingredientes2;

	public RecetaSimple receta;
	public RecetaSimple receta2;

	public Producto comidaPublica;
	public HashSet<Producto> ingredientesPublicos;
	public RecetaSimple recetaPublica;

	public SistemaDeComidas sistemaDeComidas;

	@Before
	public void SetUp() {

		/*
		 * Finalmente, las recetas pueden ser vistas o modificadas por un
		 * usuario si: - Este usuario es quien la cre� - O bien es una receta
		 * p�blica: una receta que se sube en forma masiva por �sistema� (no las
		 * sube ning�n usuario en particular) Sin embargo, hay que tener
		 * especial cuidado con las recetas p�blicas al momento de modificarlas:
		 * si bien cualquier usuario puede hacer modificaciones o
		 * actualizaciones a una receta p�blica, dichos cambios s�lo ser�n
		 * visibles por �l.
		 */
		

		vegano = new CreadorUsuario().setNombre("carlos").setNacimiento(LocalDate.of(2000, 02, 02)).setAsVegano().build();

		comida1 = new Producto("Manzana", 1, true, null);
		comida2 = new Producto("Naranja", 1, true, null);
		comida3 = new Producto("Banana", 1, true, null);

		ingredientes = new HashSet<Producto>();
		ingredientes.add(comida1);
		ingredientes.add(comida2);
		ingredientes.add(comida3);

		receta = new RecetaSimple("Ensalada de frutas", null,
				ingredientes,
				null, null,
				200, null,
				null, null, null);
		
		receta.designarAutor(vegano);
		vegano.agregar(receta);
		
		diabetico = new CreadorUsuario().setNombre("carla").setAsMujer().setAsDiabetico().setNacimiento(LocalDate.of(2000, 02, 02)).build();


		comida4 = new Producto("Carne", 1, true, null);
		comida5 = new Producto("Lechuga", 1, true, null);
		comida6 = new Producto("Tomate", 1, true, null);

		ingredientes2 = new HashSet<Producto>();
		ingredientes2.add(comida4);
		ingredientes2.add(comida5);
		ingredientes2.add(comida6);

		receta2 = new RecetaSimple("Bife con ensalada", null,
				ingredientes2,
				null, null,
				500, null,
				null, null, null);
		receta2.designarAutor(diabetico);
		diabetico.agregar(receta);

		comidaPublica = new Producto("Papa", 3, true, null);
		ingredientesPublicos = new HashSet<Producto>();
		ingredientesPublicos.add(comidaPublica);

		recetaPublica = new RecetaSimple("Pure de papas", null,
				ingredientesPublicos,
				null, null,
				300, null,
				null, null, null);
		
		recetaPublica.setPublica(true);
		recetaPublica.designarAutor(null);

		sistemaDeComidas = new SistemaDeComidas();
		sistemaDeComidas.agregar(receta);
		sistemaDeComidas.agregar(receta2);
		sistemaDeComidas.agregar(recetaPublica);

	}

	@Test
	public void usuarioPuedeVerRecetaPublica() {
		assertTrue(recetaPublica.puedeSerVistaPor(vegano));
	}

	@Test
	public void usuarioPuedeVerRecetaPropia() {
		assertTrue(receta.puedeSerVistaPor(vegano));
	}

	@Test
	public void usuarioNoPuedeVerRecetaAjena() {
		assertFalse(receta2.puedeSerVistaPor(vegano));
	}

	@Test
	public void usuarioPuedeModificarRecetaPublica() {
		assertTrue(vegano.puedeModificarReceta(recetaPublica));
	}

	@Test
	public void usuarioPuedeModificarRecetaPropia() {
		assertTrue(vegano.puedeModificarReceta(receta));
	}

	@Test
	public void usuarioNoPuedeModificarRecetaAjena() {
		assertFalse(vegano.puedeModificarReceta(receta2));
	}

}