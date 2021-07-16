package Util;

import java.util.List;

import Core.Jugador;
import Core.PosicionDomino;
import Core.SectorBarajado;
import interfazUsuario.AccionesDominoListener;

public class EstrategiaUI implements EstrategiaEntrada {
	private AccionesDominoListener listener;

	public final void addListener(final AccionesDominoListener listener) {
		this.listener = listener;
	}

	public final void removeListener(final AccionesDominoListener listener) {
		this.listener = null;
	}

	@Override
	public int obtenerSeleccionDomino(SectorBarajado sb, Jugador jugador) {
		return listener.elegirDomino(sb, jugador);
	}

	@Override
	public PosicionDomino obtenerPosicionDomino(Jugador jugador) {
		return listener.elegirPosicionDomino(jugador);
	}

	@Override
	public void mostrarPuntaje(List<Jugador> p) {
		listener.mostrarPuntaje(p);
	}

}
