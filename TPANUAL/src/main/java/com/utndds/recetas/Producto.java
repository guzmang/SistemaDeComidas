package com.utndds.recetas;

import javax.persistence.*;

@Embeddable
public class Producto {
	private String nombre;
	private double cantidad;
	private boolean ingrediente;
	private String medida;

	public Producto(){}
	public Producto(String nombre, double cantidad, boolean ingrediente,
			String medida) {
		super();
		this.nombre = nombre;
		this.cantidad = cantidad;
		this.ingrediente = ingrediente;
		this.medida = medida;
	}

	public Producto(String nombre) {
		super();
		this.nombre = nombre;
	}

	public boolean sosIngrediente() {
		return ingrediente;
	}

	public String getNombre() {
		return nombre;
	}

	public double getCantidad() {
		return cantidad;
	}

	public String getMedida() {
		return medida;
	}

}
