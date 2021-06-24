package Util;

import Core.Casillero;
import Core.PosicionDomino;

public class Arriba extends Orientacion {
	public Arriba(PosicionDomino posicion) {
		Casillero casillero = new Casillero(posicion.getCasilleroUno().getX(), posicion.getCasilleroUno().getY() - 1);
		posicion.setCasilleroDos(casillero);
	}

	@Override
	public String toString() {
		return "Arriba [getClass()=" + getClass();
	}
	@Override
	public void rotarIzquierda(PosicionDomino posicion) {
		posicion.setOrientacion(new Izquierda(posicion));
		verificarPosicion(posicion,numeroCambios);
	}

	@Override
	public void rotarDerecha(PosicionDomino posicion) {
		posicion.setOrientacion(new Derecha(posicion));
		verificarPosicion(posicion,numeroCambios);
	}

}
