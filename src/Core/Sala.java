package Core;

import java.util.LinkedList;
import java.util.List;

public class Sala {

	private List<Jugador> jugadores;
	private Partida partidaActual;
	private Jugador dueño;
	private List<String> colorDisponible;

	public Sala(Jugador dueño) {
		this.dueño = dueño;
		this.jugadores = new LinkedList<Jugador>();
		this.jugadores.add(dueño);
		this.colorDisponible = new LinkedList<String>();
		this.setColoresDisponibles();
		this.dueño.elegirRey("azul");
	}

	private void setColoresDisponibles() {
		// this.colorDisponible.add("azul");
		this.colorDisponible.add("verde");
		this.colorDisponible.add("rosa");
		this.colorDisponible.add("negro");
	}

	public boolean ingresarJugador(Jugador jugadorNuevo) {
		this.asignarColorRandom(colorDisponible, jugadorNuevo);
		return jugadores.add(jugadorNuevo);
	}

	private void asignarColorRandom(List<String> colorDisponible, Jugador jugadorNuevo) {
//		int i = 0;
//		while (!this.asignarColor(colorDisponible.get(i), jugadorNuevo))
//			i++;
		jugadorNuevo.elegirRey(colorDisponible.get(0));
		colorDisponible.remove(0);

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

	public boolean asignarColor(String color, Jugador jugador) {
//		if (this.colorDisponible(color)) {
//			jugador.elegirRey(color);
//			return true;
//		}
//		return false;
		if (this.colorDisponible.remove(color)) {
			this.colorDisponible.add(jugador.getRey().getColor());
			jugador.elegirRey(color);
		}
		return false;
	}

//	private boolean colorDisponible(String color) {
//		for (Jugador jugador : jugadores) {
//			if (jugador.getRey().getColor() == color)
//				return false;
//		}
//		return true;
//	}

	public Jugador getDueño() {
		return dueño;
	}

	public Partida getPartidaActual() {
		return partidaActual;
	}

}
