package Util;

import Core.PosicionDomino;

public interface Orientacion {

	abstract public void rotarIzquierda(PosicionDomino posicion);

	abstract public void rotarDerecha(PosicionDomino posicion);

}
