package Core;

import Core.Rey.Colores;

public class Jugador {

	private String nombre;
	private Rey rey;
	private Domino dominoEnMano;

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

	public void elegirRey(Colores color) {
		this.rey = new Rey(color, this);
	}

	public void setDominoEnMano(Domino domino) {
		this.dominoEnMano = domino;
	}

	public Domino getDominoEnMano() {
		return this.dominoEnMano;
	}

	public boolean colocarDomino(PosicionDomino posicionDomino) {
		return rey.getTablero().colocarDomino(dominoEnMano, posicionDomino);
	}

}
