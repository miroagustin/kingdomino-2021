package Util;

import java.util.List;

import Core.Jugador;
import Core.PosicionDomino;
import Core.Puntaje;
import Core.SectorBarajado;

public interface EstrategiaEntrada {
	public int obtenerSeleccionDomino(SectorBarajado sb, Jugador jugador);
	public PosicionDomino obtenerPosicionDomino(Jugador jugador);
	public void mostrarPuntaje(List<Jugador> tablaPuntaje);
}
