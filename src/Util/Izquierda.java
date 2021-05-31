package Util;

import Core.Casillero;
import Core.PosicionDomino;

public class Izquierda implements Orientacion {

	@Override
	public void rotarIzquierda(PosicionDomino posicion) {
		posicion.setOrientacion(new Abajo());
		Casillero casillero = new Casillero(posicion.getCasilleroUno().getX() + 1, posicion.getCasilleroUno().getY());
		posicion.setCasilleroDos(casillero);
	}

	@Override
	public void rotarDerecha(PosicionDomino posicion) {
		posicion.setOrientacion(new Arriba());
		Casillero casillero = new Casillero(posicion.getCasilleroUno().getX() - 1, posicion.getCasilleroUno().getY());
		posicion.setCasilleroDos(casillero);
	}

}
