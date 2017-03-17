package com.utndds.creadores;

import java.util.Collections;
import java.util.HashSet;

import com.utndds.condiciones.*;
import com.utndds.personas.Usuario;
import com.utndds.recetas.Producto;
import com.utndds.recetas.RecetaSimple;

public class CreadorReceta {
	private String nombre = "Receta Generica";
	private Usuario autor = new Usuario();
	private String dificultad = "Facil";
	private String temporada = "Verano";
	private HashSet<Producto> ingredientes = new HashSet<Producto>();
	private HashSet<Producto> condimentos = new HashSet<Producto>();
	private int calorias = 500;
	private HashSet<Condicion> condiciones = new HashSet<Condicion>();
	
	public CreadorReceta setNombre(String nombre) {
		this.nombre = nombre;
		return this;
	}
	
	public CreadorReceta setAutor(Usuario autor){
		this.autor = autor;
		return this;
	}
	public CreadorReceta setDificultad(String dificultad) {
		this.dificultad = dificultad;
		return this;
	}
	
	public CreadorReceta setCalorias(int calorias){
		this.calorias = calorias;
		return this;
	}
	
	public CreadorReceta setTemporada(String temporada) {
		this.temporada = temporada;
		return this;
	}

	public CreadorReceta setIngredientes(HashSet<Producto> ingredientes) {
		this.ingredientes = ingredientes;
		return this;
	}

	public CreadorReceta setCondimentos(HashSet<Producto> condimentos) {
		this.condimentos = condimentos;
		return this;
	}
	
	public CreadorReceta setCondiciones(){
		Vegano vegano = new Vegano(); 
		Celiaco celiaco = new Celiaco();
		Diabetico diabetico = new Diabetico();
		Hipertenso hipertenso = new Hipertenso();
		Collections.addAll(condiciones, vegano, celiaco, diabetico, hipertenso);
		return this;
	}

	public CreadorReceta addIngrediente(Producto ingrediente) {
		this.ingredientes.add(ingrediente);
		return this;
	}

	public CreadorReceta addCondimento(Producto condimento) {
		this.condimentos.add(condimento);
		return this;
	}


	public RecetaSimple build() {
		return new RecetaSimple(nombre, autor, ingredientes, condimentos, null,
				calorias, dificultad, temporada, null, condiciones);
	}

}
