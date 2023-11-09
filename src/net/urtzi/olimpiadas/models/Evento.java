package net.urtzi.olimpiadas.models;

public class Evento {
	private int id;
	private String nombre;
	private Olimpiada olimpiada;
	private Deporte deporte;
	public Evento(int id, String nombre, Olimpiada olimpiada, Deporte deporte) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.olimpiada = olimpiada;
		this.deporte = deporte;
	}
	public Evento(String nombre, Olimpiada olimpiada, Deporte deporte) {
		super();
		this.nombre = nombre;
		this.olimpiada = olimpiada;
		this.deporte = deporte;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public Olimpiada getOlimpiada() {
		return olimpiada;
	}
	public void setOlimpiada(Olimpiada olimpiada) {
		this.olimpiada = olimpiada;
	}
	public Deporte getDeporte() {
		return deporte;
	}
	public void setDeporte(Deporte deporte) {
		this.deporte = deporte;
	}
	@Override
	public String toString() {
		return nombre + " en " + olimpiada;
	}
	
}
