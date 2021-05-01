package Core;

import java.util.ArrayList;
import java.util.List;

import Util.CoreUtils;

public class Mazo {
	private final int CANT_DOMINOS_BARAJADOS = 4;
	private List<Domino> dominos;
	List<Terreno> terrenosDisponibles;

	Mazo() throws Exception {
		terrenosDisponibles = ManagerEntrada.obtenerTerrenosDisponibles();
		this.dominos = crearMazoRandom();

	}

	private ArrayList<Domino> crearMazoRandom() throws Exception {
		ArrayList<Domino> resultado = new ArrayList<Domino>();
		int maximoDominos = terrenosDisponibles.size() / 2;

		for (int i = 0; i < maximoDominos; i++) {
			Terreno parteUno = getNextTerreno();
			Terreno parteDos = getNextTerreno();
			resultado.add(new Domino(parteUno, parteDos, i+1));
		}

		return resultado;
	}

	private Terreno getNextTerreno() {
		int next = CoreUtils.randInt(0, terrenosDisponibles.size() - 1);
		return terrenosDisponibles.remove(next);
	}

	public boolean tieneDominos() {
		return this.dominos.size() != 0;
	}

	public ArrayList<Domino> barajarDomino() {
		ArrayList<Domino> dominosBarajados = new ArrayList<Domino>();
		for (int i = 0; i < CANT_DOMINOS_BARAJADOS; i++) {
			int next = CoreUtils.randInt(0, dominos.size() - 1);
			dominosBarajados.add(dominos.remove(next));
		}
		return dominosBarajados;
	}

	public List<Domino> getDominos() {
		return this.dominos;
	}
}
