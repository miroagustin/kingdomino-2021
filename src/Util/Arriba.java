package Util;

import Core.Casillero;
import Core.PosicionDomino;

public class Arriba implements Orientacion {

	@Override
	public void rotarIzquierda(PosicionDomino posicion) {
		posicion.setOrientacion(new Izquierda());
		Casillero casillero = new Casillero(posicion.getCasilleroUno().getX(), posicion.getCasilleroUno().getY() - 1);
		posicion.setCasilleroDos(casillero);
	}

	@Override
	public void rotarDerecha(PosicionDomino posicion) {
		posicion.setOrientacion(new Derecha());
		Casillero casillero = new Casillero(posicion.getCasilleroUno().getX(), posicion.getCasilleroUno().getY() + 1);
		posicion.setCasilleroDos(casillero);
	}

}
