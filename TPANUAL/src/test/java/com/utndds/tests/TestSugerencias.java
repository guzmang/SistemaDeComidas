package com.utndds.tests;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

import java.time.LocalDate;
import java.util.HashSet;

import org.junit.Before;
import org.junit.Test;

import com.utndds.personas.GrupoDeUsuarios;
import com.utndds.personas.Usuario;
import com.utndds.recetas.Producto;
import com.utndds.recetas.RecetaSimple;
import com.utndds.condiciones.*;
import com.utndds.creadores.CreadorReceta;
import com.utndds.creadores.CreadorUsuario;

public class TestSugerencias {

	public GrupoDeUsuarios grupo;
	public HashSet<Usuario> users;

	public Vegano veganismo = new Vegano();
	public Celiaco celiaquia = new Celiaco();
	public Diabetico diabetes = new Diabetico();
	public Hipertenso hipertensionismo = new Hipertenso();

	public HashSet<Condicion> condiciones1 = new HashSet<Condicion>();
	public HashSet<Condicion> condiciones2 = new HashSet<Condicion>();

	public Usuario usuario1;
	public Usuario usuario2;

	public RecetaSimple recetaConCarne;
	public RecetaSimple recetaConSal;

	public RecetaSimple recetaParaTodos;

	public Usuario sebastian;

	@Before
	public void setUp() {

		sebastian = new CreadorUsuario().setNombre("sebastian")
				.setAsDiabetico().build();
		sebastian.agregarDisgusto("Leche");

		condiciones1.add(veganismo);
		condiciones1.add(celiaquia);

		condiciones2.add(diabetes);
		condiciones2.add(hipertensionismo);

		recetaConCarne = new CreadorReceta().addIngrediente(
				new Producto("Carne", 2.0, true, "Kilos")).build();
		recetaConSal = new CreadorReceta().addCondimento(
				new Producto("Sal", 120.0, false, "Gramos")).build();
		recetaParaTodos = new CreadorReceta().addIngrediente(
				new Producto("Lechuga", 2.0, true, "Planta")).build();

		Usuario usuario1 = new CreadorUsuario().setNombre("Yo").setAsHombre()
				.setNacimiento(LocalDate.of(2000, 02, 02))
				.setCondiciones(condiciones1).build();
		Usuario usuario2 = new CreadorUsuario().setNombre("Tu").setAsHombre()
				.setNacimiento(LocalDate.of(2000, 02, 01))
				.setCondiciones(condiciones2).build();

		users = new HashSet<Usuario>();
		users.add(usuario1);
		users.add(usuario2);

		grupo = new GrupoDeUsuarios("Vamo' lo' pibe'", users);
		grupo.agregarPreferencia(new Producto("Lechuga", 2.0, true, "Planta"));

	}

	@Test
	public void cuandoSeLeSugiereUnaRecetaNoAptaYConIngredientesQueLeGustanDevuelveFalse() {
		RecetaSimple recetaConAzucar = new CreadorReceta()
				.addCondimento(new Producto("azucar", 120.0, false, "Kilos"))
				.addIngrediente(new Producto("Ron", 2.0, true, "Litros"))
				.build();
		assertFalse(sebastian.seLePuedeSugerir(recetaConAzucar));
	}

	@Test
	public void cuandoSeLeSugiereUnaRecetaAptaYConIngredientesQueNoLeGustanDevuelveFalse() {
		RecetaSimple recetaConLeche = new CreadorReceta().addIngrediente(
				new Producto("Leche", 2.0, true, "Litros")).build();
		assertFalse(sebastian.seLePuedeSugerir(recetaConLeche));
	}

	@Test
	public void cuandoSeLeSugiereUnaRecetaNoAptaYConUnIngredienteQueNoLeGustaDevuelveFalse() {
		RecetaSimple recetaConLecheYAzucar = new CreadorReceta()
				.addIngrediente(new Producto("Leche", 2.0, true, "Litros"))
				.addCondimento(new Producto("Azucar", 120.0, false, "Kilos"))
				.build();
		assertFalse(sebastian.seLePuedeSugerir(recetaConLecheYAzucar));
	}

	@Test
	public void cuandoSeLeSugiereUnaRecetaSinIngredientesDevuelveTrue() {
		RecetaSimple receta = new CreadorReceta().build();
		assertTrue(sebastian.seLePuedeSugerir(receta));
	}
}
