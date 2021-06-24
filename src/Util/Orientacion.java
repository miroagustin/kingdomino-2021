package Util;

import Core.PosicionDomino;

public abstract class Orientacion {
	int numeroCambios = 0;
	abstract public void rotarIzquierda(PosicionDomino posicion);

	abstract public void rotarDerecha(PosicionDomino posicion);
	public void verificarPosicion(PosicionDomino posicion, int cambios) {
		numeroCambios = cambios;
		if(numeroCambios > 4) {
			posicion.setPosValida(false);
			return;
		}
		if(posicion.estaFueraRango())
		{
			numeroCambios++;
			rotarIzquierda(posicion);
		}
	}
}
