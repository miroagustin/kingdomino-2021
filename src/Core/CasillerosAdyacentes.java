package Core;

public class CasillerosAdyacentes {

	private Casillero arriba;
	private Casillero abajo;
	private Casillero izquierda;
	private Casillero derecha;

	public void setArriba(Casillero casillero) {
		this.arriba = casillero;
	}
	public Casillero getArriba() {
		return arriba;
	}
	public void setAbajo(Casillero casillero) {
		this.abajo = casillero;
	}
	public Casillero getAbajo() {
		return abajo;
	}
	public void setIzq(Casillero casillero) {
		this.izquierda = casillero;
	}
	public Casillero getIzquierda() {
		return izquierda;
	}
	public void setDer(Casillero casillero) {
		this.derecha = casillero;
	}
	public Casillero getDerecha() {
		return derecha;
	}




}
