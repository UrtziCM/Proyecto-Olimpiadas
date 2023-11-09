package net.urtzi.olimpiadas.models;

public class Equipo {
	private String nombre,abreviatura;
	private int id;
	public Equipo(int id, String nombre, String abreviatura) {
		super();
		this.nombre = nombre;
		this.abreviatura = abreviatura;
		this.id = id;
	}
	public Equipo(String nombre, String abreviatura) {
		super();
		this.nombre = nombre;
		this.abreviatura = abreviatura;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getAbreviatura() {
		return abreviatura;
	}
	public void setAbreviatura(String abreviatura) {
		this.abreviatura = abreviatura;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	@Override
	public String toString() {
		return nombre + "(" + abreviatura + ")";
	}
	
}
