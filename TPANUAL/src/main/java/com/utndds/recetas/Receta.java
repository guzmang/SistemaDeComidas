package com.utndds.recetas;

import java.util.Collection;
import java.util.HashSet;

import com.utndds.condiciones.Condicion;
import com.utndds.consultas.Filtro;
import com.utndds.personas.Usuario;

public interface Receta {

	public boolean esDificil();

	public boolean esValida();
	
	public boolean esInadecuadaPara(Condicion condicion);

	public Collection<Condicion> getInadecuados();

	public int getCalorias();

	public Collection<ComponenteReceta> getSubrecetas();

	public Boolean contains(String producto);

	public String getNombre();

	public String getDificultad();

	public Collection<Producto> getIngredientes();

	public Collection<Producto> getCondimentos();
	
	public Collection<Producto> getProductos();

	public void marcarComoFavorita();

	public boolean contienePreferencia(Producto preferencia);

	public void modificarReceta(String nombre,
								Collection<Producto> ingredientes,
								Collection<Producto> condimentos, String[] explicacionPasos,
								int calorias, String dificultadPreparacion, String temporada,
								Collection<ComponenteReceta> subrecetas);

	public boolean puedeSerVistaPor(Usuario usuario);

	public void modificarIngredientes(HashSet<Producto> ingredientes);

	void modificarCondimentos(HashSet<Producto> condimentos);

	public boolean isPublica();

	public Double getCantidadDeUnProducto(String producto);
	
	public boolean cumpleLosFiltros(HashSet<Filtro> filtros);
}
