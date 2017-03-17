package com.utndds.creadores;

import java.time.LocalDate;
import java.util.HashSet;

import com.utndds.condiciones.Celiaco;
import com.utndds.condiciones.Condicion;
import com.utndds.condiciones.Diabetico;
import com.utndds.condiciones.Hipertenso;
import com.utndds.condiciones.Vegano;
import com.utndds.personas.Usuario;
import com.utndds.personas.Usuario.Sexo;

public class CreadorUsuario {
	private String nombre = "Usuario Generico";
	private Sexo sexo = Sexo.MASCULINO;
	private LocalDate nacimiento = LocalDate.of(1995, 03, 05);
	private double altura = 1.80;
	private double peso = 64;
	private String rutina = "Rutina Generica";
	private HashSet<Condicion> condiciones = new HashSet<Condicion>();
	private HashSet<String> preferencias = new HashSet<String>();

	public CreadorUsuario setNombre(String nombre) {
		this.nombre = nombre;
		return this;
	}

	public CreadorUsuario setAsHombre() {
		this.sexo = Sexo.MASCULINO;
		return this;
	}
	
	public CreadorUsuario setAsMujer() {
		this.sexo = Sexo.FEMENINO;
		return this;
	}
	
	public CreadorUsuario setNacimiento(LocalDate nacimiento) {
		this.nacimiento = nacimiento;
		return this;
	}
	
	public CreadorUsuario setAltura(double altura) {
		this.altura = altura;
		return this;
	}
	
	public CreadorUsuario setRutina(String rutina) {
		this.rutina = rutina;
		return this;
	}
	
	public CreadorUsuario setPeso(double peso) {
		this.peso = peso;
		return this;
	}
	
	public CreadorUsuario setPreferencias(HashSet<String> preferencias) {
		this.preferencias.addAll(preferencias);
		return this;
	}

	public CreadorUsuario addPreferencia(String preferencia) {
		this.preferencias.add(preferencia);
		return this;
	}

	public CreadorUsuario setCondiciones(HashSet<Condicion> condiciones){
		this.condiciones = condiciones;
		return this;
	}

	public CreadorUsuario setAsVegano() {
		this.condiciones.add(new Vegano());
		return this;
	}

	public CreadorUsuario setAsCeliaco() {
		this.condiciones.add(new Celiaco());
		return this;
	}

	public CreadorUsuario setAsDiabetico() {
		this.condiciones.add(new Diabetico());
		return this;
	}

	public CreadorUsuario setAsHipertenso() {
		this.condiciones.add(new Hipertenso());
		return this;
	}

	public Usuario build() {
		return new Usuario(nombre, sexo, nacimiento, altura, peso, rutina, condiciones, preferencias);
	}
}
