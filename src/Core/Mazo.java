package Core;

import java.util.ArrayList;

import Util.CoreUtils;

public class Mazo {
	private ArrayList<Domino> dominos;
	ArrayList<Terreno> terrenosDisponibles;

	Mazo() throws Exception {
		terrenosDisponibles = Terreno.obtenerTerrenosDisponibles();
		this.dominos = crearMazoRandom();
		
	}

	private ArrayList<Domino> crearMazoRandom() throws Exception {
		ArrayList<Domino> resultado = new ArrayList<Domino>();
		int maximoDominos = terrenosDisponibles.size() / 2;

		for (int i = 0; i < maximoDominos; i++) {
			Terreno parteUno = getNextTerreno();
			Terreno parteDos = getNextTerreno();
			resultado.add(new Domino(parteUno, parteDos));
		}

		return resultado;
	}

	private Terreno getNextTerreno() {
		int next = CoreUtils.randInt(0, terrenosDisponibles.size() - 1);
		return terrenosDisponibles.remove(next);
	}

	public ArrayList<Domino> getDominos() {
		return this.dominos;
	}
}
