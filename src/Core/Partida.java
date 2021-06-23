package Core;

import java.util.Collections;
import java.util.List;
import Util.ManagerEntrada;

public class Partida {

	private List<Jugador> jugadores;

	public enum EstadosPartida {
		iniciada, enEspera, finalizada
	}

	private EstadosPartida estadoPartida;
	private Mazo mazo;

	public Partida(List<Jugador> jugadores) {
		this.jugadores = jugadores;
		this.estadoPartida = EstadosPartida.enEspera;
		this.mazo = new Mazo();
	}

	public void iniciar() {
		this.estadoPartida = EstadosPartida.iniciada;
		ManagerEntrada.getInstancia().openInput();

		empezarPartida();

		ManagerEntrada.getInstancia().closeInput();
		this.estadoPartida = EstadosPartida.finalizada;
	}

	private void empezarPartida() {
		Collections.shuffle(jugadores);
		while (mazo.tieneDominos()) {
			Ronda ronda = new Ronda(mazo.barajarDomino(), jugadores);
			this.jugadores = ronda.getJugadoresOrdenados();
		}
	}

	public boolean eliminarJugador(Jugador jugadorParaSacar) {
		return this.jugadores.remove(jugadorParaSacar);
	}

	public EstadosPartida mostrarEstado() {
		return estadoPartida;
	}

	public List<Jugador> obtenerTablaPuntaje() {
		jugadores.sort(null);
		return jugadores;
	}

	public List<Jugador> getJugadores() {
		return jugadores;
	}

}
