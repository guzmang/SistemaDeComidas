package com.utndds.tests;

import static org.junit.Assert.*;

import java.util.Collection;
import java.util.HashSet;

import org.junit.Before;
import org.junit.Test;

import com.utndds.consultas.Busqueda;
import com.utndds.consultas.filtrarLeche;
import com.utndds.recetas.ComponenteReceta;
import com.utndds.recetas.Producto;
import com.utndds.recetas.RecetaSimple;

public class TestFiltros {

	RecetaSimple recetaConLeche = new RecetaSimple();
	RecetaSimple recetaConAzucar = new RecetaSimple();
	Collection<ComponenteReceta> recetas = new HashSet<ComponenteReceta>();
	filtrarLeche filtro = new filtrarLeche();

	@Before
	public void setUp() {
		recetaConLeche.agregarIngrediente(new Producto("Leche", 2.0, true,
				"Litros"));
		recetaConAzucar.agregarIngrediente(new Producto("Azucar", 120.0, false,
				"Kilos"));
		recetas.add(recetaConAzucar);
		recetas.add(recetaConLeche);
	}

	@Test
	public void FiltrarRecetasQueContienenLecheStrategy() {
		Busqueda consulta = new Busqueda();
		consulta.agregarFiltro(filtro);
		assertTrue(consulta.filtrar(recetas).stream()
				.allMatch(r -> r.contains("Leche")));
	}

	@Test
	public void AsegurarseQueNoFiltreTodasStrategy() {
		Busqueda consulta = new Busqueda();
		consulta.agregarFiltro(filtro);
		assertFalse(consulta.filtrar(recetas).isEmpty());
	}

	@Test
	public void CuandoTodasSonFiltradasStrategy() {
		Busqueda consulta = new Busqueda();
		consulta.agregarFiltro(filtro);
		recetas.remove(recetaConAzucar);
		recetas.remove(recetaConLeche);
		assertTrue(consulta.filtrar(recetas).isEmpty());
	}
	
	@Test
	public void CuandoSeFiltraEnUnaConsultaQueNoTieneFiltrosNoSeFiltraNinguna() {
		Busqueda consulta = new Busqueda();
		assertEquals(consulta.filtrar(recetas).size(),2);
	}
	
	@Test
	public void CuandoSeFiltraUnaColeccionVacia() {
		Busqueda consulta = new Busqueda();
		consulta.agregarFiltro(filtro);
		recetas.remove(recetaConAzucar);
		recetas.remove(recetaConLeche);
		assertTrue(consulta.filtrar(recetas).isEmpty());
	}
}