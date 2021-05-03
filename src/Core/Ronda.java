package Core;

import java.util.List;

import Util.ManagerEntrada;

public class Ronda {

	private List<Jugador> jugadores;
	private SectorBarajado sectorBarajado;

	public Ronda(List<Domino> dominos, List<Jugador> jugadores) {
		this.jugadores = jugadores;
		this.sectorBarajado = new SectorBarajado(dominos);
		iniciarRondaElegirDomino();
		inciarRondaColocarDomino();
	}


	private void inciarRondaColocarDomino() {
		// TODO Auto-generated method stub
		
	}


	private void iniciarRondaElegirDomino() {
		for (Jugador jugador : jugadores) {
			List<Integer> opciones = sectorBarajado.mostrarOpciones();
			System.out.println("Turno de elegir domino - Jugador " + jugador.getNombre());
			int opcionJugador = ManagerEntrada.obtenerOpcionJugador(opciones);
			Domino dominoElegido = sectorBarajado.elegirDomino(opcionJugador, jugador.getRey());
			// TODO: DOMINO EN MANO PARA DESPUES COLOCAR EN TABLERO
			jugador.setDominoEnMano(dominoElegido);
		}
		this.jugadores = sectorBarajado.ordenarJugadores();
	}


	public List<Jugador> getJugadoresOrdenados() {
		return this.jugadores;
	}

}
