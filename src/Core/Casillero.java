package Core;

import Core.Terreno.TipoTerreno;

public class Casillero {

	@Override
	public String toString() {
		return "[t=" + terreno + ", x=" + x + ", y=" + y + "]";
	}

	private Terreno terreno;
	private int x;
	private int y;

	public Casillero(int x, int y) {
		this.x = x;
		this.y = y;
		this.terreno = new Terreno();
	}

	public boolean estaVacio() {
		if (terreno.getTipoTerreno() == TipoTerreno.vacio)
			return true;
		return false;
	}

	public Boolean equalTerreno(Casillero other) {
		return this.terreno.equals(other.terreno);

	}

	public void setTerreno(Terreno terreno) {
		this.terreno = terreno;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public Terreno getTerreno() {
		return terreno;
	}

}
