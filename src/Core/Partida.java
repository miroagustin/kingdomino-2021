package Core;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import Util.ManagerEntrada;

public class Partida {

	private List<Jugador> jugadores;

	public enum EstadosPartida {
		iniciada, enEspera, finalizada
	}

	private EstadosPartida estadoPartida;
	private Mazo mazo;
	private int turno;

	public Partida(List<Jugador> jugadores) {
		this.jugadores = jugadores;
		this.estadoPartida = EstadosPartida.enEspera;
		this.mazo = new Mazo();
	}

	public void iniciar() {
		this.estadoPartida = EstadosPartida.iniciada;

		empezarPartida();

		this.estadoPartida = EstadosPartida.finalizada;
	}

	private void empezarPartida() {
		Collections.shuffle(jugadores);
		Collections.shuffle(mazo.getDominos());
		while (mazo.tieneDominos()) {
			turno++;
			Ronda ronda = new Ronda(mazo.barajarDomino(), jugadores);
			this.jugadores = ronda.getJugadoresOrdenados();
		}
		ManagerEntrada.getInstancia().mostrarPuntaje(obtenerTablaPuntaje());
	}

	public boolean eliminarJugador(Jugador jugadorParaSacar) {
		return this.jugadores.remove(jugadorParaSacar);
	}

	public EstadosPartida mostrarEstado() {
		return estadoPartida;
	}

	public List<Jugador> obtenerTablaPuntaje() {
		List<Jugador> resultado = new LinkedList<Jugador>(jugadores);
		resultado.sort(null);
		
		return resultado;
	}

	public List<Jugador> getJugadores() {
		return jugadores;
	}

	public int getTurno() {
		return turno;
	}

}
