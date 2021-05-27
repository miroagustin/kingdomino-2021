package Core;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import Core.Terreno.TipoTerreno;
import Util.CoreUtils;

public class Tablero {
	// 9x9 es el maximo tamaño que puede tener el tablero
	private Casillero[][] casilleros;
	private Casillero comodin;
	private int Xmin = 9;
	private int Xmax;
	private int Ymin = 9;
	private int Ymax;
	private Puntaje puntaje = new Puntaje();

	public Tablero() {
		generarCasilleros();
	}

	private void generarCasilleros() {
		casilleros = new Casillero[9][9];
		comodin = getOcrearCasilleroVacio(4, 4);
		comodin.setTerreno(new Terreno(TipoTerreno.comodin, 0));
		generarCasillerosAdyacentes(comodin);
	}

	public Casillero getOcrearCasilleroVacio(int x, int y) {
		if (getCasillero(x, y) == null) {
			setCasillero(new Casillero(x, y));
		}
		return getCasillero(x, y);
	}

	private void actualizarLimites(int x, int y) {
		if (x > Xmax)
			Xmax = x;
		if (x < Xmin)
			Xmin = x;
		if (y > Ymax)
			Ymax = y;
		if (y < Ymin)
			Ymin = y;
	}

	private Casillero getCasillero(int x, int y) {
		return casilleros[x][y];
	}

	private void setCasillero(Casillero casillero) {
		this.casilleros[casillero.getX()][casillero.getY()] = casillero;
	}

	private void generarCasillerosAdyacentes(Casillero casillero) {
		casillero.setAdyacentes(new CasillerosAdyacentes(casillero, this));
	}

	public int calcularPuntaje() {
		return puntaje.calcularPuntaje();
	}

	public boolean colocarDomino(Domino domino, PosicionDomino posicionDomino) {
		Casillero casilleroUno = posicionDomino.getCasilleroUno();
		Casillero casilleroDos = posicionDomino.getCasilleroDos();
		if (!sePuedeColocarDomino(domino, casilleroUno, casilleroDos)) {
			return false;
		}
		casilleroUno.setTerreno(domino.getTerrenoUno());
		casilleroDos.setTerreno(domino.getTerrenoDos());
		actualizarLimites(casilleroUno.getX(), casilleroUno.getY());
		actualizarLimites(casilleroDos.getX(), casilleroDos.getY());
		generarCasillerosAdyacentes(casilleroUno);
		generarCasillerosAdyacentes(casilleroDos);
		puntaje.agregar(domino, posicionDomino);
		return true;
	}

	private boolean sePuedeColocarDomino(Domino domino, Casillero casilleroUno, Casillero casilleroDos) {
		// Nos fijamos que el casillero este dentro del tablero
		if (casilleroFueraDeRango(casilleroUno) || casilleroFueraDeRango(casilleroUno))
			return false;
		// Nos fijamos que no esten vacios
		if (!casilleroUno.estaVacio() || !casilleroDos.estaVacio())
			return false;
		// Verificamos que pueda colocarse por adyacencia
		if (!tieneAdyacentesDe(casilleroUno, domino.getTerrenoUno())
				&& !tieneAdyacentesDe(casilleroDos, domino.getTerrenoDos())) {
			return false;
		}
		// Verificamos que los casilleros sean adyacentes
		if (!casilleroUno.esAdyacente(casilleroDos))
			return false;
		return true;
	}

	private boolean tieneAdyacentesDe(Casillero casillero, Terreno terreno) {
		return adyacenteValido(getCasillero(casillero.getX(), casillero.getY() - 1), terreno)
				|| adyacenteValido(getCasillero(casillero.getX() - 1, casillero.getY()), terreno)
				|| adyacenteValido(getCasillero(casillero.getX() + 1, casillero.getY()), terreno)
				|| adyacenteValido(getCasillero(casillero.getX(), casillero.getY() + 1), terreno);
	}

	private boolean adyacenteValido(Casillero casillero, Terreno terreno) {
		return casillero != null && casillero.getY() >= 0 && casillero.getTerreno().equalsTipoTerreno(terreno);
	}

	public boolean casilleroFueraDeRango(Casillero casillero) {
		if (Xmax - casillero.getX() > 4)
			return true;
		if (casillero.getX() - Xmin > 4)
			return true;
		if (Ymax - casillero.getY() > 4)
			return true;
		if (casillero.getY() - Ymin > 4)
			return true;
		return false;
	}

	@Override
	public String toString() {
		String resultado = "";
		int i = 0;
		for (int fila = 0, columna = 0; fila <= 8; fila++) {
			for (int j = 0; j <= 8; j++) {
				resultado += String.format("%d" + " %d" + "%8s|", i, j, "");
			}
			resultado += "\n";
			for (columna = 0; columna <= 8; columna++) {
				if (casilleros[fila][columna] != null) {
					// if (!casilleros[fila][columna].estaVacio()) {
					resultado += String.format("%9s", casilleros[fila][columna].getTerreno().getTipoTerreno());
					resultado += String.format("%2d|", casilleros[fila][columna].getTerreno().getCoronas());
//					} else
//						resultado += String.format("%11s|", "");
				} else
					resultado += String.format("%11s|", "");
			}
			resultado += "\n";
			for (int j = 0; j <= 8; j++) {
				resultado += "___________|";
			}
			resultado += "\n";
			i++;
		}
		return resultado;
	}

	// FUNCION PARA TESTEAR EL PUNTAJE
	public void completarTerrenos() {
		List<TipoTerreno> tipos = new ArrayList<TipoTerreno>(Arrays.asList(TipoTerreno.values()));
		tipos.remove(TipoTerreno.comodin);
		tipos.remove(TipoTerreno.vacio);
		// para que quede en el medio
		for (int i = 2; i < 7; i++) {
			for (int j = 2; j < 7; j++) {
				if (j < 4 && i < 4) {
					getOcrearCasilleroVacio(i, j).setTerreno(new Terreno(TipoTerreno.agua, (j + i) % 2));
				} else {
					getOcrearCasilleroVacio(i, j)
							.setTerreno(new Terreno(tipos.get((CoreUtils.randInt(0, 5) + j + i) % 6), (j + i) % 2));
				}
				getOcrearCasilleroVacio(i, j);
			}
		}
		getOcrearCasilleroVacio(4, 4).setTerreno(new Terreno(TipoTerreno.comodin, 0));
	}

}
