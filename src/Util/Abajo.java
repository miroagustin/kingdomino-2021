package Util;

import Core.Casillero;
import Core.PosicionDomino;

public class Abajo extends Orientacion {
	@Override
	public String toString() {
		return "Abajo [getClass()=" + getClass();
	}

	public Abajo(PosicionDomino posicion) {
		Casillero casillero = new Casillero(posicion.getCasilleroUno().getX(), posicion.getCasilleroUno().getY() + 1);
		posicion.setCasilleroDos(casillero);
	}

	@Override
	public void rotarIzquierda(PosicionDomino posicion) {
		posicion.setOrientacion(new Derecha(posicion));

	}

	@Override
	public void rotarDerecha(PosicionDomino posicion) {
		posicion.setOrientacion(new Izquierda(posicion));

	}

}
