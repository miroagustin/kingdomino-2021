package Util;

import Core.Casillero;
import Core.PosicionDomino;

public class Izquierda extends Orientacion {
	public Izquierda(PosicionDomino posicion) {
		Casillero casillero = new Casillero(posicion.getCasilleroUno().getX() - 1, posicion.getCasilleroUno().getY());
		posicion.setCasilleroDos(casillero);

	}

	@Override
	public String toString() {
		return "Izquierda [getClass()=" + getClass();
	}

	@Override
	public void rotarIzquierda(PosicionDomino posicion) {
		posicion.setOrientacion(new Abajo(posicion));

	}

	@Override
	public void rotarDerecha(PosicionDomino posicion) {
		posicion.setOrientacion(new Arriba(posicion));

	}

}
