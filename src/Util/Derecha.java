package Util;

import Core.Casillero;
import Core.PosicionDomino;

public class Derecha extends Orientacion {
	public Derecha(PosicionDomino posicion) {
		Casillero casillero = new Casillero(posicion.getCasilleroUno().getX() + 1, posicion.getCasilleroUno().getY());
		posicion.setCasilleroDos(casillero);
	}
	@Override
	public String toString() {
		return "Derecha [getClass()=" + getClass();
	}
	@Override
	public void rotarIzquierda(PosicionDomino posicion) {
		posicion.setOrientacion(new Arriba(posicion));
		verificarPosicion(posicion, numeroCambios);
	}

	@Override
	public void rotarDerecha(PosicionDomino posicion) {
		posicion.setOrientacion(new Abajo(posicion));
		verificarPosicion(posicion,numeroCambios);
	}

}
