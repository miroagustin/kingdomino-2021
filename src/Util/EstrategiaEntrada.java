package Util;

import Core.Jugador;
import Core.PosicionDomino;
import Core.SectorBarajado;

public interface EstrategiaEntrada {
	public int obtenerSeleccionDomino(SectorBarajado sb, Jugador jugador);
	public PosicionDomino obtenerPosicionDomino(Jugador jugador);
}
