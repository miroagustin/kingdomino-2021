package Core;

import Util.Orientacion;

public class PosicionDomino {
	Casillero casilleroUno;
	Casillero casilleroDos;
	private Orientacion orientacion;

	public PosicionDomino(Casillero casilleroUno, Casillero casilleroDos) {
		this.casilleroUno = casilleroUno;
		this.casilleroDos = casilleroDos;
	}

	public Casillero getCasilleroUno() {
		return casilleroUno;
	}

	public Casillero getCasilleroDos() {
		return casilleroDos;
	}

	public boolean esValida() {
		return casilleroUno.esAdyacente(casilleroDos);
	}

	public void rotarIzquierda() {
		orientacion.rotarIzquierda(this);
	}

	public void rotarDerecha() {
		orientacion.rotarDerecha(this);
	}

	public void actualizarCasillerosValidos(Casillero casilleroUno, Casillero casilleroDos) {
		this.casilleroUno = casilleroUno;
		this.casilleroDos = casilleroDos;
	}

	public void setCasilleroDos(Casillero casillero) {
		this.casilleroDos = new Casillero(casillero);
	}

}
