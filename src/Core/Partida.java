package Core;

import java.util.List;

public class Partida {

	private List<Jugador> jugadores;
	

	private boolean partidaIniciada;
	private Mazo mazo;

	public Partida(List<Jugador> jugadores) throws Exception {
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
	
	public List<Jugador> getJugadores() {
		return jugadores;
	}

}
