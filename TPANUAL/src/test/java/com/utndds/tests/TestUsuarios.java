package com.utndds.tests;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertEquals;

import java.util.Collections;
import java.util.HashSet;

import org.junit.Before;
import org.junit.Test;

import com.utndds.condiciones.Condicion;
import com.utndds.condiciones.Vegano;
import com.utndds.creadores.CreadorProducto;
import com.utndds.creadores.CreadorReceta;
import com.utndds.creadores.CreadorUsuario;
import com.utndds.personas.GrupoDeUsuarios;
import com.utndds.personas.Usuario;
import com.utndds.recetas.*;

public class TestUsuarios {

	private Usuario vegano;
	private Condicion veganismo;
	private HashSet<String> preferenciasVegano;
	private HashSet<String> preferenciasUsuario;
	private HashSet<String> preferenciasCreador;
	private HashSet<String> preferenciasOtroCreador;

	private Producto comida1;
	private Producto comida2;
	private Producto comida3;

	private HashSet<Producto> ingredientes;

	private Producto comida4;
	private Producto comida5;
	private Producto comida6;

	private HashSet<Producto> ingredientes2;
	private HashSet<Producto> condimentos2;
	private RecetaSimple receta;
	private RecetaSimple receta2;

	private SistemaDeComidas sistemaDeComidas;

	private Usuario usuario;
	private RecetaSimple recetaA;
	private Usuario creador;
	private GrupoDeUsuarios grupo;
	private GrupoDeUsuarios grupo2;
	private Usuario otroCreador;
	private Producto ingrediente;
	private Producto condimento;
	private Producto condimento2;
	private HashSet<Producto> condimentos;
	private HashSet<Producto> ingredientesA;
	private HashSet<Producto> condimentosB;
	private HashSet<Condicion> condiciones = new HashSet<Condicion>();

	private HashSet<Usuario> grupoUsuarios1 = new HashSet<Usuario>();
	private HashSet<Usuario> grupoUsuarios2 = new HashSet<Usuario>();
	private HashSet<Usuario> grupoUsuarios3 = new HashSet<Usuario>();

	@Before
	public void SetUp() {

		preferenciasVegano = new HashSet<String>();
		preferenciasUsuario = new HashSet<String>();
		preferenciasCreador = new HashSet<String>();
		preferenciasOtroCreador = new HashSet<String>();
		veganismo = new Vegano();
		condiciones.add(veganismo);

		Collections.addAll(preferenciasVegano, "frutilla");

		vegano = new CreadorUsuario().setNombre("Carlos")
				.setCondiciones(condiciones)
				.setPreferencias(preferenciasVegano).build();

		comida1 = new Producto("Manzana", 30, true, "Gramos");
		comida2 = new Producto("Naranja", 30, true, "Gramos");
		comida3 = new Producto("Banana", 30, true, "Gramos");

		ingredientes = new HashSet<Producto>();
		condimentos = new HashSet<Producto>();
		ingredientes.add(comida1);
		ingredientes.add(comida2);
		ingredientes.add(comida3);

		receta = new RecetaSimple("Ensalada de frutas", vegano, ingredientes,
				condimentos, null, 230, "facil", null, null, null);
		vegano.agregar(receta);

		comida4 = new Producto("Carne", 30, true, "Gramos");
		comida5 = new Producto("Lechuga", 30, true, "Gramos");
		comida6 = new Producto("Tomate", 30, true, "Gramos");

		condimentos2 = new HashSet<Producto>();
		ingredientes2 = new HashSet<Producto>();
		ingredientes2.add(comida4);
		ingredientes2.add(comida5);
		ingredientes2.add(comida6);

		condiciones.add(veganismo);

		receta2 = new CreadorReceta().setNombre("Bife con ensalada")
				.setAutor(vegano).setIngredientes(ingredientes2)
				.setCondimentos(condimentos2).setCalorias(500).build();
		vegano.agregar(receta2);
		sistemaDeComidas = new SistemaDeComidas();
		sistemaDeComidas.agregar(receta2);

		Collections.addAll(preferenciasUsuario, "caqui", "arroz");
		Collections.addAll(preferenciasCreador, "arroz", "manzana", "lechuga");
		Collections.addAll(preferenciasOtroCreador, "arroz", "manzana",
				"lechuga");

		usuario = new CreadorUsuario().setNombre("vegana666")
				.setCondiciones(condiciones)
				.setPreferencias(preferenciasUsuario).build();
		creador = new CreadorUsuario().setNombre("master")
				.setCondiciones(condiciones)
				.setPreferencias(preferenciasCreador).build();
		otroCreador = new CreadorUsuario().setNombre("Master of lechugas")
				.setCondiciones(condiciones)
				.setPreferencias(preferenciasOtroCreador).build();

		grupoUsuarios1.add(usuario);
		grupoUsuarios1.add(creador);

		grupoUsuarios2.add(creador);

		grupoUsuarios3.add(usuario);

		grupo = new GrupoDeUsuarios("grupoVegano", grupoUsuarios1);
		grupo2 = new GrupoDeUsuarios("grupoDeMasters", grupoUsuarios2);
		new GrupoDeUsuarios("grupoSinPrivilegios", grupoUsuarios3);

		creador.setGrupo(grupo);
		usuario.setGrupo(grupo);
		otroCreador.setGrupo(grupo2);

		ingrediente = new CreadorProducto().setNombre("Arroz").build();
		condimento = new CreadorProducto().setNombre("Sal").setAsCondimento()
				.build();
		condimento2 = new CreadorProducto().setNombre("Dulce")
				.setAsCondimento().build();

		ingredientesA = new HashSet<Producto>();

		condimentosB = new HashSet<Producto>();

		ingredientesA.add(ingrediente);
		condimentos.add(condimento);
		condimentosB.add(condimento2);

		recetaA = new CreadorReceta().setNombre("arrozSalado")
				.setAutor(creador).setIngredientes(ingredientesA)
				.setCondimentos(condimentos).setDificultad("facil").build();
		new CreadorReceta().setNombre("arrozDulce").setAutor(otroCreador)
				.setIngredientes(ingredientesA).setCondimentos(condimentosB)
				.setDificultad("facil").build();

		grupo.agregar(recetaA);

	}

	@Test
	public void verificarSiLaRecetaFueAgregadaCorrectamenteAlSistema() {
		assertEquals(1, sistemaDeComidas.listarTodas().size());
	}

	@Test
	public void elUsuarioPuedeAgregarRecetas() {
		assertEquals(2, vegano.getRecetas().size(), 0);
	}

	@Test
	public void elUsuarioPuedeComerUnaRecetaQueDeberiaPoder() {
		assertFalse(vegano.noPuedeComer(receta));
	}

	@Test
	public void elUsuarioNoPuedeComerUnaRecetaQueNoDeberia() {
		assertTrue(vegano.noPuedeComer(receta2));
	}

	/*
	 * @Test public void
	 * elUsuarioPuedeVerLaRecetaDeUnAutorQuePerteneceAUnGrupoEnComun() {
	 * assertTrue(receta.puedeSerVistaPor(usuario)); }
	 */

	@Test
	public void elUsuarioPuedeVerUnaRecetaQueElMismoCreo() {
		assertTrue(receta.puedeSerVistaPor(vegano));
	}

	@Test
	public void elUsuarioNoPuedeVerUnaRecetaDeUnCreadorConElQueNoComparteGrupo() {
		assertFalse(receta.puedeSerVistaPor(otroCreador));
	}

	@Test
	public void elUsuarioComparteElGrupoConElCreadorDeUnaReceta() {
		assertTrue(recetaA.compartenElGrupoDondeEstaLaReceta(usuario, creador));
	}

	@Test
	public void elUsuarioEsValido() {
		assertTrue(usuario.esUsuarioValido());
	}

	@Test
	public void elNombreDeLaComidaEstaBien() {
		assertEquals("Carne", comida4.getNombre());
	}

	@Test
	public void laReceta2ContieneCarne() {
		assertTrue(receta2.contains("carne"));
	}
}