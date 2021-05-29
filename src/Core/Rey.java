package Core;

public class Rey {

	private Colores color;
	private Tablero tablero;
	private Jugador jugador;

	public Rey(Colores color, Jugador jugador) {
		this.color = color;
		this.jugador = jugador;
		tablero = new Tablero();
	}

	public Tablero getTablero() {
		return tablero;
	}

	public void setTablero(Tablero tablero) {
		this.tablero = tablero;
	}

	public void setColor(Colores color) {
		this.color = color;
	}

	public Colores getColor() {
		return this.color;
	}

	public Jugador getJugador() {
		return this.jugador;
	}

	@Override
	public String toString() {
		return "Rey [color=" + color + ", tablero=" + tablero + "]";
	}

	public enum Colores {
		azul, verde, rosa, negro
	}

}
