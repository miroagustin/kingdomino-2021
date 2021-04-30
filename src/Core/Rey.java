package Core;

public class Rey {

	private String color;
	private Tablero tablero;

	public Rey(String color) {
		this.color = color;
	}

	public Tablero getTablero() {
		return tablero;
	}

	public void setTablero(Tablero tablero) {
		this.tablero = tablero;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getColor() {
		return this.color;
	}

}
