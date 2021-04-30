package Core;

import java.util.ArrayList;

public class Partida {

	private ArrayList<Jugador> jugadores;
	

	private boolean partidaIniciada;
	private Mazo mazo;

	public Partida(ArrayList<Jugador> jugadores) throws Exception {
		this.jugadores = jugadores;
		this.partidaIniciada = true;
		this.mazo = new Mazo();
	}

	public boolean eliminarJugador(Jugador jugadorParaSacar) {
		return this.jugadores.remove(jugadorParaSacar);
	}

	public boolean mostrarEstado() { //partidaEnCurso() seria mejor
		return partidaIniciada;
	}

	public void obtenerTablaPuntaje() {

	}
	
	public ArrayList<Jugador> getJugadores() {
		return jugadores;
	}

}
