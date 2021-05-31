package Core;

import Core.Rey.Colores;

public class Jugador implements Comparable<Jugador> {

	private String nombre;
	private Rey rey;
	private Domino dominoEnMano;
	private Puntaje puntaje;

	public Jugador(String nombre) {
		this.nombre = nombre;
		this.puntaje = new Puntaje();
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
		if (rey.getTablero().colocarDomino(dominoEnMano, posicionDomino)) {
			puntaje.agregar(dominoEnMano, posicionDomino);
			return true;
		}
		return false;
	}

	@Override
	public int compareTo(Jugador o) {
		return this.puntaje.compareTo(o.puntaje);
	}

	public int getPuntaje() {
		return puntaje.getPuntaje();
	}

}
