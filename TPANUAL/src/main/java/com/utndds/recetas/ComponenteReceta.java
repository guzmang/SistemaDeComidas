package com.utndds.recetas;

import java.util.Collection;
import java.util.HashSet;

import javax.persistence.*;

import com.utndds.condiciones.Condicion;
import com.utndds.personas.Usuario;

@Entity
@Table(name="Recetas")
@Inheritance
@DiscriminatorColumn(name="TIPO_RECETA")
public abstract class ComponenteReceta implements Receta {

	@Id
	@GeneratedValue
	long receta_id;
	@OneToMany(cascade = {CascadeType.ALL})
	protected Collection<Condicion> condiciones = new HashSet<Condicion>();
	protected String nombre;
	@ManyToOne(cascade = {CascadeType.ALL})
	protected Usuario autor;
	protected boolean publica = false;
	protected boolean favorita = false;
	@ElementCollection
	protected Collection<Producto> ingredientes = new HashSet<Producto>();
	@ElementCollection
	protected Collection<Producto> condimentos = new HashSet<Producto>();
	protected String[] explicacionPasos = new String[10];
	protected int calorias;
	protected String dificultadPreparacion;
	protected String temporada;

	public void setDificultadPreparacion(String dificultadPreparacion) {
		this.dificultadPreparacion = dificultadPreparacion;
	}

	public Usuario getAutor() {
		return autor;
	}

	public String getDificultad() {
		return dificultadPreparacion;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getTemporada() {
		return temporada;
	}


	public void marcarComoFavorita() {
		favorita = true;
	}

	public boolean esDificil() {
		return dificultadPreparacion.equals("Dificil");
	}
}
