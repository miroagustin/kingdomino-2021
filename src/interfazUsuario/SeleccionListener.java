package interfazUsuario;

import Core.Domino;
import Core.PosicionDomino;

public interface SeleccionListener {

	void publicarSeleccion(Domino dominoCasillero, PosicionDomino seleccionado);
}
