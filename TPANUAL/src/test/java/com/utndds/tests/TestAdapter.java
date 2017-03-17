package com.utndds.tests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.utndds.consultas.QueComemosAdapter;
import com.utndds.creadores.CreadorProducto;
import com.utndds.creadores.CreadorReceta;
import com.utndds.creadores.CreadorUsuario;
import com.utndds.excepciones.ConsultaNoValidaException;
import com.utndds.excepciones.DificultadIncorrectaException;
import com.utndds.personas.Usuario;
import com.utndds.recetas.Producto;
import com.utndds.recetas.Receta;
import com.utndds.recetas.RecetaSimple;

public class TestAdapter {

	QueComemosAdapter adapter = new QueComemosAdapter();
	List<String> claves = new ArrayList<String>();
	HashSet <Producto> ingredientes = new HashSet<Producto>();
	Producto ingrediente;
	Receta parametros;
	Usuario usuario;
	
	@Before
	public void setUp(){
		
		usuario = new CreadorUsuario().setNombre("Cesar Po").build();
		ingrediente = new CreadorProducto().setNombre("Lechuga").setCantidad(2.0).setMedida("Litros").build();
		ingredientes.add(ingrediente);
		parametros = new CreadorReceta().setNombre("ensalada caesar").setIngredientes(ingredientes).build();
	}
	
	@Test
	public void CuandoTodosLosDatosCorrespondenAUnaRecetaDeLaRepoDevuelveEsaReceta() {
		try {
			parametros.modificarIngredientes(new HashSet<Producto>());
			HashSet<Receta> resultado = adapter.buscar(parametros);
			if(resultado.size() == 0) fail();
			Receta receta = resultado.iterator().next();
			RecetaSimple recetaSimple = (RecetaSimple) receta;
			assertEquals(recetaSimple.getAutor().getNombre(),"Cesar Po");
		} catch (DificultadIncorrectaException | ConsultaNoValidaException e2) {
			fail();
		}
	}
	
	@Test
	public void CuandoNingunDatoCorrespondeAUnaRecetaDeLaRepoNoDevuelveNinguna() {
		
		// se usa una receta con nombre "root"
		parametros = new RecetaSimple("root", usuario,ingredientes,
										null, null,
										1, "Facil",
										null, null, null);
		
		try {
			HashSet<Receta> resultado = adapter.buscar(parametros);
			assertTrue(resultado.isEmpty());
		} catch (DificultadIncorrectaException | ConsultaNoValidaException e2) {
			fail();
		}
	}
	
	@Test
	public void CuandoSePoneUnaDificultadNoValidaTiraExcepcion() {

		parametros = new RecetaSimple("ensalada caesar", usuario,
				ingredientes,
				null, null,
				1, "asdasd",
				null, null, null);
		
		try {
			adapter.buscar(parametros);
			fail();
		} 
		catch (DificultadIncorrectaException e) {} 
		catch (ConsultaNoValidaException e) {
			fail();
		}
	}
	
	@Test
	public void CuandoSeEnviaUnaConsultaAbarcativaDevuelveVariasRecetas() {
		parametros = new RecetaSimple("", usuario,
				new HashSet<Producto>(),
				null, null,
				1, "Facil",
				null, null, null);
		
		try {
			HashSet<Receta> resultado = adapter.buscar(parametros);
			assertTrue(resultado.size() > 1);
		} catch (DificultadIncorrectaException | ConsultaNoValidaException e2) {
			fail();
		}
	}
	
	@Test
	public void CuandoSeEnviaUnaConsultaSinNingunValorTiraExcepcion() {
		
		// receta vacia
		parametros = new RecetaSimple(null, null,
										null,
										null, null,
										1, null,
										null, null, null);
		try {
			HashSet<Receta> resultado = adapter.buscar(parametros);
			assertTrue(resultado.size() > 1);
		} catch (DificultadIncorrectaException e){
			fail();
		} catch (ConsultaNoValidaException e) {}
	}
	
	@Test
	public void CuandoSeEnviaUnaConsultaSinAlgunValorTiraExcepcion() {
		
		// sin nombre
		parametros = new RecetaSimple(null, usuario,
										ingredientes,
										null, null,
										1, "Facil",
										null, null, null);
		try {
			HashSet<Receta> resultado = adapter.buscar(parametros);
			assertTrue(resultado.size() > 1);
		} catch (DificultadIncorrectaException e){
			fail();
		} catch (ConsultaNoValidaException e) {}
	}
}
