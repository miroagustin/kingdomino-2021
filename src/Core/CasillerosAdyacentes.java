package Core;

import java.util.LinkedList;
import java.util.List;

public class CasillerosAdyacentes {

	private Casillero arriba;
	private Casillero abajo;
	private Casillero izquierda;
	private Casillero derecha;

	public CasillerosAdyacentes(Casillero casillero, Tablero tablero) {
		if (!tablero.casilleroFueraDeRango(new Casillero(casillero.getX(), casillero.getY() - 1))) {
			setIzq(tablero.getOcrearCasilleroVacio(casillero.getX(), casillero.getY() - 1));
		}
		if (!tablero.casilleroFueraDeRango(new Casillero(casillero.getX() - 1, casillero.getY()))) {
			setArriba(tablero.getOcrearCasilleroVacio(casillero.getX() - 1, casillero.getY()));
		}
		if (!tablero.casilleroFueraDeRango(new Casillero(casillero.getX() + 1, casillero.getY()))) {
			setAbajo(tablero.getOcrearCasilleroVacio(casillero.getX() + 1, casillero.getY()));
		}
		if (!tablero.casilleroFueraDeRango(new Casillero(casillero.getX(), casillero.getY() + 1))) {
			setDer(tablero.getOcrearCasilleroVacio(casillero.getX(), casillero.getY() + 1));
		}
	}

	public List<Casillero> obtenerAdyacentesValidos(Casillero casilleroReferencia) {
		List<Casillero> adyacentesValidos = new LinkedList<Casillero>();
		if (casilleroReferencia.equalTerreno(getDerecha()))
			adyacentesValidos.add(getDerecha());

		if (casilleroReferencia.equalTerreno(getIzquierda()))
			adyacentesValidos.add(getIzquierda());

		if (casilleroReferencia.equalTerreno(getArriba()))
			adyacentesValidos.add(getArriba());

		if (casilleroReferencia.equalTerreno(getAbajo()))
			adyacentesValidos.add(getAbajo());

		return adyacentesValidos;
	}

	public List<Casillero> obtenerAdyacentesVacios() {
		List<Casillero> adyacentesValidos = new LinkedList<Casillero>();
		if (getDerecha().estaVacio())
			adyacentesValidos.add(getDerecha());

		if (getIzquierda().estaVacio())
			adyacentesValidos.add(getIzquierda());

		if (getArriba().estaVacio())
			adyacentesValidos.add(getArriba());

		if (getAbajo().estaVacio())
			adyacentesValidos.add(getAbajo());

		return adyacentesValidos;
	}

	public Casillero getArriba() {
		return arriba;
	}

	public Casillero getAbajo() {
		return abajo;
	}

	public Casillero getIzquierda() {
		return izquierda;
	}

	public Casillero getDerecha() {
		return derecha;
	}

	private void setArriba(Casillero casillero) {
		this.arriba = casillero;
	}

	private void setAbajo(Casillero casillero) {
		this.abajo = casillero;
	}

	private void setIzq(Casillero casillero) {
		this.izquierda = casillero;
	}

	private void setDer(Casillero casillero) {
		this.derecha = casillero;
	}

}
