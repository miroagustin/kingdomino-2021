package Core;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class SectorBarajado {
	List<EspacioDomino> espacioDominos;

	public SectorBarajado(List<Domino> dominos) {
		llenarEspacioDomino(dominos);
	}

	private void llenarEspacioDomino(List<Domino> dominos) {
		this.espacioDominos = new ArrayList<EspacioDomino>(4);
		for (int i = 0; i < dominos.size(); i++) {
			espacioDominos.add(new EspacioDomino(dominos.get(i)));
		}
	}

	public List<Integer> mostrarOpciones() {
		int i = 0;
		List<Integer> opciones = new LinkedList<Integer>();
		for (EspacioDomino espacioDomino : espacioDominos) {
			if (espacioDomino.estaVacio()) {
				opciones.add(i);
			}
			i++;
		}
		return opciones;

	}

	public Domino elegirDomino(int opcionJugador, Rey rey) {
		EspacioDomino espacioVacio = espacioDominos.get(opcionJugador);
		espacioVacio.colocarRey(rey);
		return espacioVacio.getDomino();
	}

	public List<Jugador> ordenarJugadores() {
		List<Jugador> jugadoresOrdenados = new LinkedList<Jugador>();
		for (EspacioDomino espacioDomino : espacioDominos) {
			jugadoresOrdenados.add(espacioDomino.getJugador());
		}
		return jugadoresOrdenados;
	}

}
