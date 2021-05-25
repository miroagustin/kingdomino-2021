package Core;

import java.util.LinkedList;
import java.util.List;

import Core.Terreno.TipoTerreno;

public class CasillerosAdyacentes {

	private Casillero arriba;
	private Casillero abajo;
	private Casillero izquierda;
	private Casillero derecha;
	private Casillero casilleroReferencia;

	public CasillerosAdyacentes(Casillero casillero, Casillero[][] casilleros) {
		this.casilleroReferencia = casillero;
		if (casillero.getY() - 1 >= 0) {
			setArriba(casilleros[casillero.getX()][casillero.getY() - 1]);
		}
		if (casillero.getX() - 1 >= 0) {
			setIzq(casilleros[casillero.getX() - 1][casillero.getY()]);
		}
		if (casillero.getX() + 1 < 9) {
			setDer(casilleros[casillero.getX() + 1][casillero.getY()]);
		}
		if (casillero.getY() + 1 < 9) {
			setAbajo(casilleros[casillero.getX()][casillero.getY() + 1]);
		}
	}

	public List<Casillero> obtenerAdyacentesValidos() {
		List<Casillero> adyacentesValidos = new LinkedList<Casillero>();
		if (casilleroReferencia.equalTerreno(getDerecha().getTerreno().getTipoTerreno()))
			adyacentesValidos.add(getDerecha());

		if (casilleroReferencia.equalTerreno(getIzquierda().getTerreno().getTipoTerreno()))
			adyacentesValidos.add(getIzquierda());

		if (casilleroReferencia.equalTerreno(getArriba().getTerreno().getTipoTerreno()))
			adyacentesValidos.add(getArriba());

		if (casilleroReferencia.equalTerreno(getAbajo().getTerreno().getTipoTerreno()))
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
