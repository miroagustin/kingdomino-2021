package Core;

import Util.Arriba;
import Util.Orientacion;

public class PosicionDomino {
	Casillero casilleroUno;
	Casillero casilleroDos;
	private Orientacion orientacion = new Arriba();

	public PosicionDomino(Casillero casilleroUno) {
		this.casilleroUno = casilleroUno;
	}

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

	public void setOrientacion(Orientacion orientacion) {
		this.orientacion = orientacion;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;

		if (getClass() != obj.getClass())
			return false;

		PosicionDomino other = (PosicionDomino) obj;

		if (casilleroUno.equals(other.casilleroUno))
			return false;

		if (casilleroDos.equals(other.casilleroDos))
			return false;

		return true;

	}

}
