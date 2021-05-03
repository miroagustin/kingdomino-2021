package Core;

import java.util.LinkedList;
import java.util.List;

import Core.Rey.colores;

public class Sala {

	private List<Jugador> jugadores;
	private Partida partidaActual;
	private Jugador due�o;
	private List<colores> colorDisponible;

	public Sala(Jugador due�o) {
		this.due�o = due�o;
		this.jugadores = new LinkedList<Jugador>();
		this.jugadores.add(due�o);
		this.colorDisponible = new LinkedList<colores>();
		
		this.setColoresDisponibles();
		this.asignarColorRandom(due�o);
	}

	private void setColoresDisponibles() {
		this.colorDisponible.add(colores.azul);
		this.colorDisponible.add(colores.verde);
		this.colorDisponible.add(colores.rosa);
		this.colorDisponible.add(colores.negro);
	}

	public boolean ingresarJugador(Jugador jugadorNuevo) {
		this.asignarColorRandom(jugadorNuevo);
		return jugadores.add(jugadorNuevo);
	}

	private void asignarColorRandom(Jugador jugadorNuevo) {
		jugadorNuevo.elegirRey(colorDisponible.remove(0));
	}

	public boolean quitarJugador(Jugador jugadorParaSacar) {
		if (jugadores.remove(jugadorParaSacar)) {
			partidaActual.eliminarJugador(jugadorParaSacar);
			return true;
		}
		return false;
	}

	public void iniciarPartida() throws Exception {
		this.partidaActual = new Partida(this.jugadores);
	}

	public boolean asignarColor(colores color, Jugador jugador) {
		if (this.colorDisponible.remove(color)) {
			this.colorDisponible.add(jugador.getRey().getColor());
			jugador.elegirRey(color);
		}
		return false;
	}

	public Jugador getDue�o() {
		return due�o;
	}

	public Partida getPartidaActual() {
		return partidaActual;
	}

}
