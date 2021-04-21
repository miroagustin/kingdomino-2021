package Core;

import java.util.ArrayList;

import Util.CoreUtils;

public class Mazo {
	private final int MaximoDominos = 48;
	private ArrayList<Domino> dominos;
	ArrayList<Terreno> terrenosDisponibles;

	Mazo() throws Exception {
		terrenosDisponibles = Terreno.obtenerTerrenosDisponibles();
		this.dominos = crearMazoRandom();
		
	}

	private ArrayList<Domino> crearMazoRandom() throws Exception {
		ArrayList<Domino> resultado = new ArrayList<Domino>();

		for (int i = 0; i < MaximoDominos; i++) {
			Terreno parteUno = getNextTerreno();
			Terreno parteDos = getNextTerreno();
			resultado.add(new Domino(parteUno, parteDos));
		}

		return resultado;
	}

	private Terreno getNextTerreno() {
		int next = CoreUtils.randInt(0, terrenosDisponibles.size() - 1);
		Terreno nextTerreno = terrenosDisponibles.get(next);
		nextTerreno.extraerTerreno();
		if (!nextTerreno.tieneTerrenos()) {
			terrenosDisponibles.remove(next);
		}
		return nextTerreno;
	}

	public ArrayList<Domino> getDominos() {
		return this.dominos;
	}
}
