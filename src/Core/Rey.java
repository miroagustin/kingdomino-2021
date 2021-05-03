package Core;

public class Rey {
	public enum colores {
		azul, verde, rosa, negro
	}

	private colores color;
	private Tablero tablero;

	public Rey(colores color) {
		this.color = color;
	}

	public Tablero getTablero() {
		return tablero;
	}

	public void setTablero(Tablero tablero) {
		this.tablero = tablero;
	}

	public void setColor(colores color) {
		this.color = color;
	}

	public colores getColor() {
		return this.color;
	}

}
