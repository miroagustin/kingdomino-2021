package Core;

import java.util.List;

import Core.Terreno.TipoTerreno;

public class Casillero {

	@Override
	public String toString() {
		return "[t=" + terreno.toShortString() + ", x=" + x + ", y=" + y + "]";
	}

	private Terreno terreno;
	private int x;
	private int y;
	private int grupo;
	private CasillerosAdyacentes adyacentes;

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

	public Boolean equalTerreno(Casillero casillero) {
		if (casillero == null) {
			return false;
		}
		return this.terreno.equalsTipoTerreno(casillero.getTerreno());
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
		return (Math.abs(this.x - other.x) == 1 && Math.abs(this.y - other.y) == 0)
				|| (Math.abs(this.x - other.x) == 0 && Math.abs(this.y - other.y) == 1);
	}

	public int getGrupo() {
		return this.grupo;
	}

	public void setGrupo(int id) {
		this.grupo = id;
	}

	public void setAdyacentes(CasillerosAdyacentes casillerosAdyacentes) {
		this.adyacentes = casillerosAdyacentes;
	}

	public List<Casillero> obtenerAdyacentesValidos() {
		return this.adyacentes.obtenerAdyacentesValidos(this);
	}
}
