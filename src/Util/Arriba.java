package Util;

import Core.Casillero;
import Core.PosicionDomino;

public class Arriba implements Orientacion {
	public Arriba(PosicionDomino posicion) {
		Casillero casillero = new Casillero(posicion.getCasilleroUno().getX() - 1, posicion.getCasilleroUno().getY());
		posicion.setCasilleroDos(casillero);
	}
	@Override
	public void rotarIzquierda(PosicionDomino posicion) {
		posicion.setOrientacion(new Izquierda(posicion));
	}

	@Override
	public void rotarDerecha(PosicionDomino posicion) {
		posicion.setOrientacion(new Derecha(posicion));
	}

}
