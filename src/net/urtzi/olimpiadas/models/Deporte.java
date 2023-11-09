package net.urtzi.olimpiadas.models;

/**
 * Modelo de los deportes practicados en las olimpiadas.
 * 
 */
public class Deporte {
	private String nombre;
	private int id;
	
	
	public Deporte(int id, String nombre) {
		this.id = id;
		this.nombre = nombre;
	}
	
	public Deporte(String nombre) {
		this.id = -1;
		this.nombre = nombre;
	}
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	@Override
	public String toString() {
		return this.nombre;
	}
	
	
}
