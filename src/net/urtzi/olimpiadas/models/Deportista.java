package net.urtzi.olimpiadas.models;


/**
 * Modelo de deportista.
 */
public class Deportista {
	private String nombre;
	private int id,peso,altura;
	private char sexo;
	public Deportista(int id, String nombre, char sexo, int peso, int altura) {
		super();
		this.nombre = nombre;
		this.id = id;
		this.peso = peso;
		this.altura = altura;
		this.sexo = sexo;
	}
	
	public Deportista(String nombre, char sexo, int peso, int altura) {
		super();
		this.id = -1;
		this.nombre = nombre;
		this.peso = peso;
		this.altura = altura;
		this.sexo = sexo;
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
	public int getPeso() {
		return peso;
	}
	public void setPeso(int peso) {
		this.peso = peso;
	}
	public int getAltura() {
		return altura;
	}
	public void setAltura(int altura) {
		this.altura = altura;
	}
	public char getSexo() {
		return sexo;
	}
	public void setSexo(char sexo) {
		this.sexo = sexo;
	}
	@Override
		public String toString() {
			return nombre;
		}
	
}
