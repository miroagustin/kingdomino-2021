package Core;

public class Rey {
	@Override
	public String toString() {
		return "Rey [color=" + color + ", tablero=" + tablero + "]";
	}

	public enum Colores {
		azul, verde, rosa, negro
	}

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
		// TODO Auto-generated method stub
		return this.jugador;
	}

}
