package com.utndds.creadores;

import com.utndds.recetas.Producto;

public class CreadorProducto {
	private String nombre = "Producto Generico";
	private double cantidad = 10;
	private String medida = "Unidades";
	private boolean isIngrediente = true;

	public Producto build() {
		return new Producto(nombre, cantidad, isIngrediente, medida);
	}

	public CreadorProducto setNombre(String nombre) {
		this.nombre = nombre;
		return this;
	}

	public CreadorProducto setCantidad(double cantidad) {
		this.cantidad = cantidad;
		return this;
	}

	public CreadorProducto setAsIngrediente() {
		this.isIngrediente = true;
		return this;
	}

	public CreadorProducto setAsCondimento() {
		this.isIngrediente = false;
		return this;
	}

	public CreadorProducto setMedida(String medida) {
		this.medida = medida;
		return this;
	}
}
