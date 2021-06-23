package Util;

import Core.Casillero;
import Core.PosicionDomino;

public class Abajo implements Orientacion {
	public Abajo(PosicionDomino posicion) {
		Casillero casillero = new Casillero(posicion.getCasilleroUno().getX() + 1, posicion.getCasilleroUno().getY());
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
