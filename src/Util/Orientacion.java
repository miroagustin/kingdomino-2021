package Util;

import Core.PosicionDomino;

public abstract class Orientacion {
	int numeroCambios = 0;
	abstract public void rotarIzquierda(PosicionDomino posicion);

	abstract public void rotarDerecha(PosicionDomino posicion);
}
