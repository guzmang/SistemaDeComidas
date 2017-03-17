package com.utndds.consultas;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;
import com.utndds.excepciones.ConsultaNoValidaException;
import com.utndds.excepciones.DificultadIncorrectaException;
import com.utndds.personas.Usuario;
import com.utndds.recetas.Producto;
import com.utndds.recetas.Receta;
import com.utndds.recetas.RecetaSimple;
import com.utndds.recetas.RepositorioDeRecetas;

import queComemos.entrega3.dominio.Dificultad;
import queComemos.entrega3.repositorio.BusquedaRecetas;
import queComemos.entrega3.repositorio.RepoRecetas;

public class QueComemosAdapter implements RepositorioDeRecetas  {
	public static RepoRecetas repo = new RepoRecetas();
	
	public QueComemosAdapter() {
	}


	public HashSet<Receta> buscar(Receta parametros)
			throws DificultadIncorrectaException, ConsultaNoValidaException {
		if (parametros.getNombre() == null
				|| parametros.getDificultad() == null
				|| parametros.getIngredientes() == null)
			throw new ConsultaNoValidaException();

		BusquedaRecetas busqueda = new BusquedaRecetas();
		busqueda.setNombre(parametros.getNombre());
		parametros
				.getIngredientes()
				.stream()
				.forEach(
						ingrediente -> busqueda.agregarPalabraClave(ingrediente
								.getNombre()));

		if (parametros.getDificultad().equalsIgnoreCase("Facil"))
			busqueda.setDificultad(Dificultad.FACIL);
		else if (parametros.getDificultad().equalsIgnoreCase("Mediana"))
			busqueda.setDificultad(Dificultad.MEDIANA);
		else if (parametros.getDificultad().equalsIgnoreCase("Dificil"))
			busqueda.setDificultad(Dificultad.DIFICIL);
		else
			throw new DificultadIncorrectaException();

		String json = repo.getRecetas(busqueda);
		return parsearJson(json);
	}

	private HashSet<Receta> parsearJson(String json) {
		JsonParser parser = new JsonParser();
		Gson gson = new Gson();
		JsonArray array = parser.parse(json).getAsJsonArray();

		HashSet<queComemos.entrega3.dominio.Receta> recetasServicio = new HashSet<queComemos.entrega3.dominio.Receta>();
		array.forEach(token -> recetasServicio.add(gson.fromJson(token,
				queComemos.entrega3.dominio.Receta.class)));

		HashSet<Receta> recetas = new HashSet<Receta>();
		recetasServicio.forEach(r -> recetas.add(new RecetaSimple(r.getNombre(),
				new Usuario(r.getAutor()), parsearIngredientes(r
						.getIngredientes()), null, null, r
						.getTotalCalorias(), parsearDificultad(r
						.getDificultadReceta()), null, null, null)));

		return recetas;
	}

	private HashSet<Producto> parsearIngredientes(
			List<String> ingredientesExternos) {
		HashSet<Producto> ingredientes = new HashSet<Producto>();
		ingredientesExternos.forEach(r -> ingredientes.add(new Producto(r)));
		return ingredientes;
	}
	
	private String parsearDificultad(Dificultad dificultadExterna) {
		if (dificultadExterna == Dificultad.FACIL)
			return "Facil";
		else if (dificultadExterna == Dificultad.MEDIANA)
			return "Mediana";
		else if (dificultadExterna == Dificultad.DIFICIL)
			return "Dificil";
		return "";
	}


	@Override
	public void quitar(Receta receta) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void agregar(Receta receta) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public Collection<Receta> listarTodas() {
		// TODO Auto-generated method stub
		return null;
	}

}
