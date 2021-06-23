package interfazUsuario;

import Core.Jugador;
import Core.PosicionDomino;
import Core.SectorBarajado;

public interface AccionesDominoListener {
	int elegirDomino(final SectorBarajado sb, final Jugador jugador);
	PosicionDomino elegirPosicionDomino(final Jugador jugador);
}
