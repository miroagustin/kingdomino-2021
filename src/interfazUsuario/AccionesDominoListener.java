package interfazUsuario;

import java.util.List;

import Core.Jugador;
import Core.PosicionDomino;
import Core.Puntaje;
import Core.SectorBarajado;

public interface AccionesDominoListener {
	int elegirDomino(final SectorBarajado sb, final Jugador jugador);
	PosicionDomino elegirPosicionDomino(final Jugador jugador);
	void mostrarPuntaje(final List<Jugador> tablaPuntaje);
}
