package Core;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import Util.ManagerEntrada;

public class Mazo {
	private final int CANT_DOMINOS_BARAJADOS = 4;
	private List<Domino> dominos;

	public Mazo() throws Exception {
		this.dominos = ManagerEntrada.getInstancia().obtenerMazoOriginal();
	}

	public boolean tieneDominos() {
		return !this.dominos.isEmpty();
	}

	public List<Domino> barajarDomino() {
		List<Domino> dominosBarajados = new LinkedList<Domino>();
		for (int i = 0; i < CANT_DOMINOS_BARAJADOS; i++) {
			dominosBarajados.add(dominos.remove(0));
		}
		dominosBarajados.sort(null);
		return dominosBarajados;
	}

	public void mezclarMazo() {
		Collections.shuffle(dominos);
	}

	public List<Domino> getDominos() {
		return this.dominos;
	}
}
