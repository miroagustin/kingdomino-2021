package Core;

import java.util.List;

import Util.ManagerEntrada;

public class Partida {

	private List<Jugador> jugadores;
	public enum EstadosPartida { 
		iniciada, enEspera, finalizada
	}
	

	private EstadosPartida estadoPartida;
	private Mazo mazo;

	public Partida(List<Jugador> jugadores) throws Exception {
		this.jugadores = jugadores;
		this.estadoPartida = EstadosPartida.enEspera;
		this.mazo = new Mazo();
	}
	
	public void iniciar() {
		this.estadoPartida = EstadosPartida.iniciada;
		ManagerEntrada.openInput();
		
		empezarPartida();

		ManagerEntrada.closeInput();
		this.estadoPartida = EstadosPartida.finalizada;
	}

	private void empezarPartida() {
		// TODO ORDENAR LOS JUGADORES ALEATORIAMENTE ANTES DE BARAJAR
		while(mazo.tieneDominos()) {
			Ronda ronda = new Ronda(mazo.barajarDomino(), jugadores);
			this.jugadores = ronda.getJugadoresOrdenados();
		}
		
	}

	public boolean eliminarJugador(Jugador jugadorParaSacar) {
		return this.jugadores.remove(jugadorParaSacar);
	}

	public EstadosPartida mostrarEstado() { //partidaEnCurso() seria mejor
		return estadoPartida;
	}

	public void obtenerTablaPuntaje() {

	}
	
	public List<Jugador> getJugadores() {
		return jugadores;
	}

}
