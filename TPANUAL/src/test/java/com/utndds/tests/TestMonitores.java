package com.utndds.tests;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.util.HashSet;

import org.junit.Before;
import org.junit.Test;

import com.utndds.Monitores.*;
import com.utndds.personas.Usuario;
import com.utndds.recetas.RecetaSimple;
import com.utndds.condiciones.Condicion;
import com.utndds.condiciones.Vegano;
import com.utndds.consultas.Busqueda;
import com.utndds.creadores.CreadorReceta;
import com.utndds.creadores.CreadorUsuario;

public class TestMonitores {

	public Usuario usuaria;
	public Usuario usuarioVegano;
	public Vegano vegano;
	public RecetaSimple receta;
	public RecetaSimple receta2;
	public RecetaSimple receta3;
	public RecetaSimple receta4;
	public Busqueda busqueda;
	public HashSet<Condicion> condiciones = new HashSet<Condicion>();
	public HashSet<ObserverBusqueda> observers = new HashSet<ObserverBusqueda>();
	public CantidadPorHora cantPorHora = new CantidadPorHora();
	public MasBuscada masBuscada = new MasBuscada();
	public PorSexo porSexo = new PorSexo();
	public VeganosQueBuscanDificiles veganosDificiles = new VeganosQueBuscanDificiles();

	@Before
	public void setup() {
		vegano = new Vegano();
		condiciones.add(vegano);
		usuaria = new CreadorUsuario().setNombre("usuaria666").setAsMujer().setNacimiento(LocalDate.of(1995,8,22)).setAltura(1.70).setPeso(57.0).build();
		usuarioVegano = new CreadorUsuario().setNombre("usuario666").setAsHombre().setNacimiento(LocalDate.of(1995,8,23)).setAltura(1.70).setPeso(57.0).setCondiciones(condiciones).build();

		
		receta = new CreadorReceta().setNombre("Pollo al verdeo").setDificultad("Mediana").build();
		receta2 = new CreadorReceta().setNombre("Asado").setDificultad("Dificil").build();
		receta3 = new CreadorReceta().setNombre("Polenta").setDificultad("Facil").build();
		receta4 = new CreadorReceta().setNombre("Shawarma").setDificultad("Dificil").build();
		
		busqueda = new Busqueda();
		busqueda.agregarObserver(masBuscada);
		busqueda.agregarObserver(porSexo);
		busqueda.agregarObserver(veganosDificiles);
		busqueda.agregarObserver(cantPorHora);
	}
	
	@Test
	public void EsCorrectoElNumeroDeVeganosQueBuscaronRecetasDificiles(){
		busqueda.setUsuario(usuarioVegano);
		busqueda.buscar(receta2);
		assertEquals(veganosDificiles.getVeganosQueBuscaronDificiles(), 1);
	}
		
	}
	
	