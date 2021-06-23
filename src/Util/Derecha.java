package Util;

import Core.Casillero;
import Core.PosicionDomino;

public class Derecha implements Orientacion {
	public Derecha(PosicionDomino posicion) {
		Casillero casillero = new Casillero(posicion.getCasilleroUno().getX(), posicion.getCasilleroUno().getY() + 1);
		posicion.setCasilleroDos(casillero);
	}
	@Override
	public void rotarIzquierda(PosicionDomino posicion) {
		posicion.setOrientacion(new Arriba(posicion));
	}

	@Override
	public void rotarDerecha(PosicionDomino posicion) {
		posicion.setOrientacion(new Abajo(posicion));
	}

}
