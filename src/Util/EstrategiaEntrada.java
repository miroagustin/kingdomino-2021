package Util;

import java.util.List;

import Core.PosicionDomino;

public interface EstrategiaEntrada {
	public int obtenerSeleccionDomino(List<Integer> opciones);
	public PosicionDomino obtenerPosicionDomino(List<PosicionDomino> opciones);
}
