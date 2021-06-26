package Core;

import java.util.List;

import Util.ManagerEntrada;

public class Ronda {

	private List<Jugador> jugadores;
	private SectorBarajado sectorBarajado;

	public Ronda(List<Domino> dominos, List<Jugador> jugadores) {
		this.jugadores = jugadores;
		this.sectorBarajado = new SectorBarajado(dominos.subList(0, jugadores.size()));
		iniciarRondaElegirDomino();
		inciarRondaColocarDomino();
		this.jugadores = sectorBarajado.ordenarJugadores();
	}

	private void inciarRondaColocarDomino() {
		for (Jugador jugador : jugadores) {
			PosicionDomino posicion = ManagerEntrada.getInstancia().obtenerPosicionDomino(jugador);
			if(posicion != null) {
				jugador.colocarDomino(posicion);
			}
		}
	}

	private void iniciarRondaElegirDomino() {
		for (Jugador jugador : jugadores) {
			int opcionElejida = ManagerEntrada.getInstancia().obtenerSeleccionDomino(sectorBarajado, jugador);
			Domino dominoElegido = sectorBarajado.elegirDomino(opcionElejida, jugador.getRey());
			jugador.setDominoEnMano(dominoElegido);
		}
	}

	public List<Jugador> getJugadoresOrdenados() {
		return this.jugadores;
	}

}
