package com.example.demo;

import java.time.LocalDate;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class PersonasFormulario {

	@NotNull
	@Size(min=2, max=30)
	private String nombre;
	
	@NotNull
	@Min(18)
	private Integer edad;

	@NotNull
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate fechaNacimiento;

	@NotNull(message = "Debes seleccionar una consola")
	private String Consola;
	
	@NotEmpty(message = "Debes seleccionar un juego")
	private List<String>Juegos;
	
	
	// Nombre
	public String getNombre(){
		
		return this.nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	// Edad
	
	public Integer getEdad() {
		return this.edad;
	}
	
	public void setEdad(Integer edad) {
		this.edad = edad;
	}
	
	// Fecha de Nacimiento 
	public LocalDate getFechaNacimiento() {
		return this.fechaNacimiento;
		}
	
	public void setFechaNacimiento(LocalDate fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}
	
	// Consola
	
	public String getConsola() {
		return this.Consola;
	}
	
	public void setConsola(String consola) {
		this.Consola = consola;
	}
	
	// JuegooSS
	
	public List<String> getJuegos(){
		return this.Juegos;
	}
	
	public void setJuegos(List<String> juegos) {
		this.Juegos = juegos;
	}
}
