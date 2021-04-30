package Core;

import java.util.ArrayList;

public class Sala {

	private ArrayList<Jugador> jugadores;
	private Partida partidaActual;
	private Jugador dueño;

	public Sala(Jugador dueño) {
		this.dueño = dueño;
	}

	public boolean ingresarJugador(Jugador jugadorNuevo) {
		return jugadores.add(jugadorNuevo);
	}

	public boolean quitarJugador(Jugador jugadorParaSacar) {
		if (jugadores.remove(jugadorParaSacar))
			if (partidaActual.eliminarJugador(jugadorParaSacar))
				return true;
		return false;
	}

	public void iniciarPartida() throws Exception {
		this.partidaActual = new Partida(this.jugadores);
	}

	public boolean elegirColor(String color, Jugador jugador) {

		if (this.colorDisponible(color)) {
			jugador.elegirRey(color);
			return true;
		}
		return false;
	}

	private boolean colorDisponible(String color) {
		for (Jugador jugador : jugadores) {
			if (jugador.getRey().getColor() != color)
				return true;
		}
		return false;
	}

}
