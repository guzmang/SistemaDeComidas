package com.utndds.tests;

import static org.junit.Assert.*;

import java.util.HashSet;

import com.utndds.condiciones.*;
import com.utndds.personas.Usuario;
import com.utndds.recetas.Producto;
import com.utndds.recetas.Receta;
import com.utndds.recetas.RecetaCompuesta;
import com.utndds.recetas.RecetaSimple;

import org.junit.Before;
import org.junit.Test;

public class TestCondiciones {

	private Receta receta;

	private String nombre;

	private Usuario autor;

	private Producto producto1;
	private Producto producto2;
	private Producto producto3;
	private Producto producto4;
	private Producto producto5;
	private Producto producto6;
	private Producto producto7;
	private Producto producto8;
	private Producto producto9;
	private Producto producto10;
	private Producto producto11;
	private Producto producto12;
	private Producto producto13;
	private Producto producto14;
	private Producto producto15;
	private Producto producto16;
	private Producto producto17;
	private Producto producto18;
	private Producto producto19;
	private Producto producto20;
	private String[] explicacionPasos;
	private int calorias;
	private String dificultadPreparacion;
	private String temporada;

	private HashSet<Producto> ingredientes;

	private HashSet<Producto> condimentos;

	private Celiaco celiaco;
	private Diabetico diabetico;
	private Hipertenso hipertenso;
	private Vegano vegano;

	private HashSet<Condicion> condiciones;

	@Before
	public void SetUp() {

		celiaco = new Celiaco();
		diabetico = new Diabetico();
		hipertenso = new Hipertenso();
		vegano = new Vegano();

		condiciones = new HashSet<Condicion>();

		condiciones.add(celiaco);
		condiciones.add(diabetico);
		condiciones.add(hipertenso);
		condiciones.add(vegano);

		nombre = "Bifes a la criolla con papas y arvejas";

		explicacionPasos = new String[10];
		explicacionPasos[0] = "Limpiar el morr�n. Cortar en tiras a lo "
				+ "largo y reservar.";
		explicacionPasos[1] = "Pelar y cortar las papas en rodajas "
				+ "gruesitas, cortar la cebolla en juliana.";
		explicacionPasos[2] = "Rociar levemente con aceite de oliva a "
				+ "la paellera.";
		explicacionPasos[3] = "Pasar la carne por harina (para que se dore "
				+ "mejor) luego disponerla sobre la paella "
				+ "(previamente aceitada). Retirar la carne"
				+ " una vez sellada vuelta y vuelta.";
		explicacionPasos[4] = "En la misma paellera colocar una capa de "
				+ "cebolla cortada en juliana, pimientos en"
				+ " juliana, luego una capa de rodajas de "
				+ "papas, rodajas de tomates. Agregar la "
				+ "carne, condimentar por encima.";
		explicacionPasos[5] = "Una vez dispuestos todos los ingredientes en"
				+ " el disco con la sal, la pimienta, el "
				+ "or�gano, el laurel y el aj� molido, "
				+ "incorporar pur� de tomate y caldo de"
				+ " verdura hasta cubrir la totalidad de" + " la preparaci�n.";
		explicacionPasos[6] = "Cuando las papas tomen color, indicar� que"
				+ " la preparaci�n est� lista (la cocci�n"
				+ " total luego de agregar las papas lleva"
				+ " 20 minutos aproximadamente).";
		explicacionPasos[7] = "Agregar los huevos, chasc�ndolos previamente"
				+ " por separado y luego volcarlos encima de"
				+ " los bifes. Sumar las arvejas y espolvorear"
				+ " con perejil picado toda la preparaci�n.";
		calorias = 785;
		dificultadPreparacion = "Media";
		temporada = "TodoElAnio";

		// Debemos tener al menos un ingrediente

		producto1 = new Producto("Cuadril", 3, true, "Kilogramos");
		producto2 = new Producto("Papas", 1.5, true, "Kilogramos");
		producto3 = new Producto("Tomates triturados", 1.5, true, "Kilogramos");
		producto4 = new Producto("Morron Rojo", 4, true, "Unidades");
		producto5 = new Producto("Morron Amarillo", 4, true, "Unidades");
		producto6 = new Producto("Morron Verde", 4, true, "Unidades");
		producto7 = new Producto("Cebollas", 1, true, "Kilogramos");
		producto8 = new Producto("Aceite de oliva", 2, true, "Cucharadas");
		producto9 = new Producto("Huevos", 20, true, "Unidades");
		producto10 = new Producto("Tomate perita", 1, true, "Latas");
		producto11 = new Producto("Pure de tomate", 0.5, true, "Litros");
		producto12 = new Producto("Tomate perita", 0.5, true, "Kilogramos");

		// Debemos tener al menos un condimento, sino falla

		producto13 = new Producto("Aji molido", 0.0, false, "C/N");
		producto14 = new Producto("Ajo picado", 1, false, "Cabeza");
		producto15 = new Producto("Perejil picado", 0.0, false, "C/N");
		producto16 = new Producto("Oregano", 0.0, false, "C/N");
		producto17 = new Producto("Sal", 101.0, false, "C/N");
		producto18 = new Producto("Pimienta", 0.0, false, "C/N");
		producto19 = new Producto("Hojas de laurel", 0.0, false, "C/N");
		producto20 = new Producto("Caldo de verdura o carne", 0.0, false, "C/N");

		ingredientes = new HashSet<Producto>();

		ingredientes.add(producto1);
		ingredientes.add(producto2);
		ingredientes.add(producto3);
		ingredientes.add(producto4);
		ingredientes.add(producto5);
		ingredientes.add(producto6);
		ingredientes.add(producto7);
		ingredientes.add(producto8);
		ingredientes.add(producto9);
		ingredientes.add(producto10);
		ingredientes.add(producto11);
		ingredientes.add(producto12);

		condimentos = new HashSet<Producto>();

		condimentos.add(producto13);
		condimentos.add(producto14);
		condimentos.add(producto15);
		condimentos.add(producto16);
		condimentos.add(producto17);
		condimentos.add(producto18);
		condimentos.add(producto19);
		condimentos.add(producto20);

		receta = new RecetaSimple(nombre, autor, ingredientes, condimentos,
				explicacionPasos, calorias, dificultadPreparacion, temporada,
				null, condiciones);
	}

	@Test
	public void tieneLasCaloriasCorrectas() {
		assertEquals(785, receta.getCalorias());
	}

	@Test
	public void cuandoSeAgregaEnDosRecetasLaMismaSubrecetaQueEstaSeReutiliza() {
		/* Chequea que se reutilice la misma subreceta */

		RecetaSimple salsaTomate = new RecetaSimple();
		RecetaCompuesta milangaNapolitana = new RecetaCompuesta();
		RecetaCompuesta tallarines = new RecetaCompuesta();

		milangaNapolitana.agregarSubreceta(salsaTomate);
		tallarines.agregarSubreceta(salsaTomate);

		assertEquals(milangaNapolitana.getSubrecetas(),
				tallarines.getSubrecetas());
	}

	@Test
	public void esInadecuadaParaQuienCorresponde() {
		assertTrue(receta.esInadecuadaPara(vegano));
		assertTrue(receta.getInadecuados().contains(hipertenso));
	}

}
