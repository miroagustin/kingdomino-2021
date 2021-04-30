package Core;

public class Jugador {

	private String nombre;
	private Rey rey;

	public Jugador(String nombre) {
		this.nombre = nombre;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Rey getRey() {
		return rey;
	}

	public void elegirRey(String color) {
		this.rey = new Rey(color);
	}

	public void rendirse() {

	}

}
