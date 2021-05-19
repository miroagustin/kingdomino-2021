package Core;

import Core.Terreno.TipoTerreno;

public class Casillero {

	@Override
	public String toString() {
		return "[t=" + terreno.toShortString() + ", x=" + x + ", y=" + y + "]";
	}

	private Terreno terreno;
	private int x;
	private int y;

	public Casillero(int x, int y) {
		this.x = x;
		this.y = y;
		this.terreno = new Terreno();
	}

	public Casillero(Casillero other) {
		this.terreno = other.terreno;
		this.x = other.x;
		this.y = other.y;
	}

	public boolean estaVacio() {
		if (terreno.getTipoTerreno() == TipoTerreno.vacio)
			return true;
		return false;
	}

	public Boolean equalTerreno(Casillero other) {
		if(other == null) {
			return false;
		}
		return this.terreno.equalsTipoTerreno(other.terreno);

	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((terreno == null) ? 0 : terreno.hashCode());
		result = prime * result + x;
		result = prime * result + y;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;

		if (getClass() != obj.getClass())
			return false;
		Casillero other = (Casillero) obj;
		if (terreno == null) {
			if (other.terreno != null)
				return false;
		} else if (!terreno.equalsTipoTerreno(other.terreno))
			return false;
		if (x != other.x)
			return false;
		if (y != other.y)
			return false;
		return true;
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

	public boolean esComodin() {
		return this.getTerreno().esComodin();
	}
	public boolean esAdyacente(Casillero other) {
		return (Math.abs(this.x - other.x) == 1 &&  Math.abs(this.y - other.y) == 0)
				|| (Math.abs(this.x - other.x) == 0 &&  Math.abs(this.y - other.y) == 1);
	}
}
