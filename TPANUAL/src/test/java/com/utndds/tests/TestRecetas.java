package com.utndds.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import com.utndds.creadores.CreadorReceta;
import com.utndds.recetas.Producto;
import com.utndds.recetas.RecetaSimple;

public class TestRecetas {
	private RecetaSimple recetaNeutra;
	private RecetaSimple recetaNoAzucarada;
	private RecetaSimple recetaMuyAzucarada;
	private CreadorReceta creadorReceta;
	
	@Before
	public void setup(){

		recetaNeutra = new RecetaSimple();
		recetaNoAzucarada = new RecetaSimple();
		recetaMuyAzucarada = new RecetaSimple();
		creadorReceta = new CreadorReceta();

		recetaNoAzucarada = creadorReceta.build();
		recetaNoAzucarada.agregarCondimento(new Producto("Cacao", 90, false, "Gramos"));
		recetaNoAzucarada.agregarIngrediente(new Producto("Leche", 3, false, "Litros"));
		
		
		recetaMuyAzucarada = new RecetaSimple();
		// FIXME no funciona con creadorReceta.buildGenerica();
		recetaMuyAzucarada.agregarCondimento(new Producto("Azucar", 101, false, "Gramos"));

		recetaNeutra = creadorReceta.build();
		recetaNeutra.agregarCondimento(new Producto("Azucar", 100, false, "Gramos"));
	}

	@Test
	public void recetaContieneLaCantidadExactaDeLoQueDebeContener(){
		assertEquals(101, recetaMuyAzucarada.getCantidadDeUnProducto("azucar"), 0);
	}
	
	@Test
	public void recetaContieneLoQueDebeContener(){
		assertTrue(recetaMuyAzucarada.contains("azucar"));
	}

	
	@Test
	public void laRecetaNoContieneAlgoQueNoTiene(){
		assertFalse(recetaMuyAzucarada.contains("leche"));
		assertFalse(recetaMuyAzucarada.contains("cacao"));
		assertTrue(recetaMuyAzucarada.contains("Azucar"));
	}

}
