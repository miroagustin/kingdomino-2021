package Core;

import Util.Arriba;
import Util.Orientacion;

public class PosicionDomino {
	@Override
	public String toString() {
		return "PosicionDomino [\ncasilleroUno=" + casilleroUno + ", \ncasilleroDos=" + casilleroDos
				+ ",\n orientacion=" + orientacion + "]";
	}

	Casillero casilleroUno;
	Casillero casilleroDos;
	private Orientacion orientacion;
	private Tablero tablero;
	private boolean invertido = false;
	private boolean posValida = true;
	int numeroCambios = 0;

	public PosicionDomino(Casillero casilleroUno, Tablero t) {
		this.casilleroUno = casilleroUno;
		this.tablero = t;
		orientacion = new Arriba(this);
		verificarPosicion(true);

	}

	public PosicionDomino(Casillero casilleroUno, Casillero casilleroDos) {
		this.casilleroUno = casilleroUno;
		this.casilleroDos = casilleroDos;
	}

	public void verificarPosicion(boolean derecha) {
		numeroCambios = 0;
		while (posicionInValida() && numeroCambios < 4) {
			numeroCambios++;
			if (derecha)
				orientacion.rotarDerecha(this);
			else
				orientacion.rotarIzquierda(this);
		}
		if (numeroCambios == 4)
			posValida = false;
	}

	public Casillero getCasilleroUno() {
		return casilleroUno;
	}

	public Casillero getCasilleroDos() {
		return casilleroDos;
	}

	public boolean esValida() {
		return casilleroUno.esAdyacente(casilleroDos) && posValida;
	}

	public void rotarIzquierda() {
		orientacion.rotarIzquierda(this);
		verificarPosicion(false);
	}

	public void rotarDerecha() {
		orientacion.rotarDerecha(this);
		verificarPosicion(true);
	}

	public void invertir() {
		invertido = !invertido;
		Casillero aux = casilleroUno;
		casilleroUno = casilleroDos;
		casilleroDos = aux;
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

	public boolean posicionInValida() {
		Casillero casilleroEnTablero = tablero.getCasillero(casilleroDos.getX(), casilleroDos.getY());
		boolean res = tablero.casilleroFueraDeRango(casilleroDos)
				|| (casilleroEnTablero != null && (!casilleroEnTablero.estaVacio()));
		return res;
	}

	public void setPosValida(boolean posValida) {
		this.posValida = posValida;
	}

	public boolean getPosValida() {
		return posValida;
	}

}
