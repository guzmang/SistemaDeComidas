package com.utndds.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.time.LocalDate;
import java.util.HashSet;

import org.junit.Before;
import org.junit.Test;

import com.utndds.condiciones.Condicion;
import com.utndds.condiciones.Vegano;
import com.utndds.creadores.CreadorProducto;
import com.utndds.creadores.CreadorReceta;
import com.utndds.creadores.CreadorUsuario;
import com.utndds.personas.Usuario;
import com.utndds.recetas.Producto;
import com.utndds.recetas.RecetaSimple;

public class TestCreadores {

	private CreadorProducto creadorProducto;
	private CreadorReceta creadorReceta;
	private CreadorUsuario creadorUsuario;

	@Before
	public void SetUp() {
		creadorProducto = new CreadorProducto();
		creadorReceta = new CreadorReceta();
		creadorUsuario = new CreadorUsuario();
	}

	@Test
	public void CreadorProductoSeteaCorrectamenteElNombre() {
		Producto producto = creadorProducto.setNombre("producto1").build();
		assertEquals(producto.getNombre(), "producto1");
	}

	@Test
	public void CreadorProductoSeteaCorrectamenteLaMedida() {
		Producto producto = creadorProducto.setMedida("Toneladas").build();
		assertEquals(producto.getMedida(), "Toneladas");
	}

	@Test
	public void CreadorProductoSeteaCorrectamenteLaCantidad() {
		Producto producto = creadorProducto.setCantidad(666).build();
		assertTrue(producto.getCantidad() == 666);
	}

	@Test
	public void CreadorProductoSeteaCorrectamenteElTipoIngrediente() {
		Producto producto = creadorProducto.setAsIngrediente().build();
		assertTrue(producto.sosIngrediente());
	}

	@Test
	public void CreadorProductoSeteaCorrectamenteElTipoCondimento() {
		Producto producto = creadorProducto.setAsCondimento().build();
		assertFalse(producto.sosIngrediente());
	}
	

	@Test
	public void CreadorUsuarioSeteaCorrectamenteElNombre() {
		Usuario usuario = creadorUsuario.setNombre("Stevie").build();
		assertEquals(usuario.getNombre(), "Stevie");
	}
	
	@Test
	public void CreadorUsuarioSeteaCorrectamenteElSexoMasculino() {
		Usuario usuario = creadorUsuario.setAsHombre().build();
		assertTrue(usuario.esHombre());
	}
	
	@Test
	public void CreadorUsuarioSeteaCorrectamenteElSexoFemenino() {
		Usuario usuario = creadorUsuario.setAsMujer().build();
		assertFalse(usuario.esHombre());
	}
	
	@Test
	public void CreadorUsuarioSeteaCorrectamenteElNacimiento() {
		Usuario usuario = creadorUsuario.setNacimiento(LocalDate.of(1964,9,6)).build();
		assertEquals(usuario.getFechaNacimiento(),LocalDate.of(1964,9,6));
	}
	
	@Test
	public void CreadorUsuarioSeteaCorrectamenteLaAltura() {
		Usuario usuario = creadorUsuario.setAltura(1.80).build();
		assertTrue(usuario.getAltura() == 1.80);
	}
	
	@Test
	public void CreadorUsuarioSeteaCorrectamenteElPeso() {
		Usuario usuario = creadorUsuario.setPeso(60.50).build();
		assertTrue(usuario.getPeso() == 60.50);
	}
	
	@Test
	public void CreadorUsuarioSeteaCorrectamenteLaRutina() {
		Usuario usuario = creadorUsuario.setRutina("Rutina1").build();
		assertEquals(usuario.getRutina(),"Rutina1");
	}
	
	@Test
	public void CreadorUsuarioSeteaCorrectamenteLasCondiciones() {
		Usuario usuario = creadorUsuario.setAsVegano().build();
		assertEquals(usuario.getCondiciones().size(),1);
	}
	
	@Test
	public void CreadorUsuarioSeteaCorrectamenteLasPreferencias() {
		Usuario usuario = creadorUsuario.addPreferencia("Preferencia1").build();
		assertEquals(usuario.getPreferencias().size(),1);
	}
	
	@Test
	public void CreadorUsuarioCreaUnUsuarioValido() {
		HashSet<String> preferenciasUsuario = new HashSet<String>();
		HashSet<Condicion> condiciones = new HashSet<Condicion>();
		
		preferenciasUsuario.add("Preferencia1");
		condiciones.add(new Vegano());
		
		Usuario usuario = new CreadorUsuario().setCondiciones(condiciones).setPreferencias(preferenciasUsuario).build();
		
		assertTrue(usuario.esUsuarioValido());
	}

	
	@Test
	public void CreadorRecetaSeteaCorrectamenteElNombre() {
		RecetaSimple receta = creadorReceta.setNombre("Receta1").build();
		assertEquals(receta.getNombre(), "Receta1");
	}
	
	@Test
	public void CreadorRecetaSeteaCorrectamenteLaDificultad() {
		RecetaSimple receta = creadorReceta.setDificultad("ReDificil").build();
		assertEquals(receta.getDificultad(), "ReDificil");
	}
	
	@Test
	public void CreadorRecetaSeteaCorrectamenteLaTemporada() {
		RecetaSimple receta = creadorReceta.setTemporada("Oto�o").build();
		assertEquals(receta.getTemporada(), "Oto�o");
	}
	
	@Test
	public void CreadorRecetaSeteaCorrectamenteLasCalorias() {
		RecetaSimple receta = creadorReceta.setCalorias(666).build();
		assertEquals(receta.getCalorias(), 666);
	}
	
	@Test
	public void CreadorRecetaSeteaCorrectamenteLosIngredientes() {
		RecetaSimple receta = creadorReceta.addIngrediente(new Producto("ingrediente1")).build();
		assertEquals(receta.getIngredientes().size(), 1);
	}
	
	@Test
	public void CreadorRecetaSeteaCorrectamenteLosCondimentos() {
		RecetaSimple receta = creadorReceta.addCondimento(new Producto("condimento1")).build();
		assertEquals(receta.getCondimentos().size(), 1);
	}

	@Test
	public void CreadorRecetaCreaUnaRecetaValida() {
		RecetaSimple receta = creadorReceta.addIngrediente(new Producto("ingrediente1")).setCalorias(100).build();
		assertTrue(receta.esValida());
	}
}
