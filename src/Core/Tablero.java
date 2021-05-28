package Core;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import Core.Terreno.TipoTerreno;
import Util.CoreUtils;

public class Tablero {
	// 9x9 es el maximo tamaño que puede tener el tablero
	private Casillero[][] casilleros;
	private Casillero comodin;
	private List<Casillero> casillerosVacios = new LinkedList<Casillero>();

	private int Xmin = 9;
	private int Xmax;
	private int Ymin = 9;
	private int Ymax;
	private Puntaje puntaje = new Puntaje();

	public Tablero() {
		generarCasilleros();
	}

	public List<Casillero> getCasillerosPosibles(Domino domino) {
		List<Casillero> casillerosPosibles = new LinkedList<Casillero>();
		for (Casillero casillero : casillerosVacios) {
			if (!casilleroFueraDeRango(casillero) && (casillero.getTerreno().equalsTipoTerreno(domino.getTerrenoUno())
					|| casillero.getTerreno().equalsTipoTerreno(domino.getTerrenoDos()))) {
				casillerosPosibles.add(casillero);
			}
		}
		return casillerosPosibles;
	}

	private void generarCasilleros() {
		casilleros = new Casillero[9][9];
		comodin = new Casillero(4, 4);
		casilleros[4][4] = comodin;
		actualizarLimites(4, 4);
		comodin.setTerreno(new Terreno(TipoTerreno.comodin, 0));
		generarCasillerosAdyacentes(comodin);
	}

	public Casillero getOcrearCasilleroVacio(int x, int y) {
		if (getCasillero(x, y) == null) {
			Casillero casilleroVacio = new Casillero(x, y);
			setCasillero(casilleroVacio);
			casillerosVacios.add(casilleroVacio);
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
		colocarTerreno(casilleroUno, domino.getTerrenoUno());
		colocarTerreno(casilleroDos, domino.getTerrenoDos());
		puntaje.agregar(domino, posicionDomino);
		return true;
	}

	public void colocarTerreno(Casillero casillero, Terreno terreno) {
		casillero.setTerreno(terreno);
		casillerosVacios.remove(casillero);
		actualizarLimites(casillero.getX(), casillero.getY());
		generarCasillerosAdyacentes(casillero);
	}

	private boolean sePuedeColocarDomino(Domino domino, Casillero casilleroUno, Casillero casilleroDos) {
		// Nos fijamos que el casillero este dentro del tablero
		if (casilleroFueraDeRango(casilleroUno) || casilleroFueraDeRango(casilleroUno))
			return false;
		// Nos fijamos que no esten vacios
		if (!casilleroUno.estaVacio() || !casilleroDos.estaVacio())
			return false;
		// Verificamos que los casilleros sean adyacentes
		if (!casilleroUno.esAdyacente(casilleroDos))
			return false;

		// Verificamos que pueda colocarse por adyacencia
		if (!tieneAdyacentesDe(casilleroUno, domino.getTerrenoUno())
				&& !tieneAdyacentesDe(casilleroDos, domino.getTerrenoDos())) {
			return false;
		}
		return true;
	}

	private boolean tieneAdyacentesDe(Casillero casillero, Terreno terreno) {
		return adyacenteValido(getCasillero(casillero.getX(), casillero.getY() - 1), terreno)
				|| adyacenteValido(getCasillero(casillero.getX() - 1, casillero.getY()), terreno)
				|| adyacenteValido(getCasillero(casillero.getX() + 1, casillero.getY()), terreno)
				|| adyacenteValido(getCasillero(casillero.getX(), casillero.getY() + 1), terreno);
	}

	private boolean adyacenteValido(Casillero casillero, Terreno terreno) {
		return casillero != null && !casilleroFueraDeRango(casillero)
				&& casillero.getTerreno().equalsTipoTerreno(terreno);
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
		String resultado = "\n";
		int i = 0;
		for (int fila = Xmin - 1, columna = 0; fila <= Xmax+1; fila++) {
			for (int j = Ymin - 1; j <= Ymax+1; j++) {
				resultado += String.format("%d" + " %d" + "%8s|", fila, j, "");
			}
			resultado += "\n";
			for (columna = Ymin -1; columna <= Ymax +1; columna++) {
				if (casilleros[fila][columna] != null) {
					if (!casilleros[fila][columna].estaVacio() || (!casilleroFueraDeRango(casilleros[fila][columna])
							&& casillerosVacios.contains(casilleros[fila][columna]))) {
						resultado += String.format("%9s", casilleros[fila][columna].getTerreno().getTipoTerreno());
						resultado += String.format("%2d|", casilleros[fila][columna].getTerreno().getCoronas());
					}
				} else
					resultado += String.format("%11s|", "");
			}
			resultado += "\n";
			for (int j = Ymin -1; j <= Ymax+1; j++) {
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
