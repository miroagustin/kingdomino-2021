package Core;

import java.util.LinkedList;
import java.util.List;
import Core.Terreno.TipoTerreno;

public class Tablero {
	// 9x9 es el maximo tama?o que puede tener el tablero
	private List<Casillero> casilleros = new LinkedList<Casillero>();
	private Casillero comodin = new Casillero(4, 4);
	private List<Casillero> casillerosVacios = new LinkedList<Casillero>();

	private int Xmin = 9;
	private int Xmax;
	private int Ymin = 9;
	private int Ymax;

	public Tablero() {
		generarCasilleros();
	}

	public List<Casillero> getCasillerosPosibles(Domino domino) {
		List<Casillero> casillerosPosibles = new LinkedList<Casillero>();
		for (Casillero casillero : casillerosVacios) {
			if (tieneAlgunVacioAdyacente(casillero) && !casilleroFueraDeRango(casillero)
					&& (tieneAdyacentesDelTerreno(casillero, domino.getTerrenoUno())
							|| tieneAdyacentesDelTerreno(casillero, domino.getTerrenoDos()))) {
				casillerosPosibles.add(casillero);
			}
		}
		return casillerosPosibles;
	}

	private void generarCasilleros() {
		comodin = new Casillero(4, 4);
		setCasillero(comodin);
		actualizarLimites(comodin.getX(), comodin.getY());
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

	private void sacarVaciosFueraDeRango() {
		if (Xmax - Xmin == 4)
			for (int i = Ymin; i <= Ymax; i++) {
				Casillero casillero;
				if ((casillero = getCasillero(Xmin - 1, i)) != null) {
					casilleros.remove(casillero);
					casillerosVacios.remove(casillero);
				}
				if ((casillero = getCasillero(Xmax + 1, i)) != null) {
					casilleros.remove(casillero);
					casillerosVacios.remove(casillero);
				}
			}
		if (Ymax - Ymin == 4)
			for (int i = Xmin; i <= Xmax; i++) {
				Casillero casillero;
				if ((casillero = getCasillero(i, Ymin - 1)) != null) {
					casilleros.remove(casillero);
					casillerosVacios.remove(casillero);
				}
				if ((casillero = getCasillero(i, Ymax + 1)) != null) {
					casilleros.remove(casillero);
					casillerosVacios.remove(casillero);
				}
			}
	}

	public Casillero getCasillero(int x, int y) {
		for (Casillero casillero : casilleros) {
			if (casillero.getX() == x && casillero.getY() == y)
				return casillero;
		}
		return null;
	}

	public Casillero getCasillero(Casillero c) {
		return getCasillero(c.getX(), c.getY());
	}

	private void setCasillero(Casillero casillero) {
		this.casilleros.add(casillero);
	}

	private void generarCasillerosAdyacentes(Casillero casillero) {
		casillero.setAdyacentes(new CasillerosAdyacentes(casillero, this));
	}

	public boolean colocarDomino(Domino domino, PosicionDomino posicionDomino) {
		Casillero casilleroUno = getOcrearCasilleroVacio(posicionDomino.getCasilleroUno().getX(),
				posicionDomino.getCasilleroUno().getY());
		Casillero casilleroDos = getOcrearCasilleroVacio(posicionDomino.getCasilleroDos().getX(),
				posicionDomino.getCasilleroDos().getY());
		if (!sePuedeColocarDomino(domino, casilleroUno, casilleroDos)) {
			System.err.println("No se pudo colocar el domino " + domino + "En la posicion " + posicionDomino);
			return false;
		}
		posicionDomino.actualizarCasillerosValidos(casilleroUno, casilleroDos);
		domino.setPosicion(posicionDomino);
		colocarTerreno(casilleroUno, domino.getTerrenoUno());
		colocarTerreno(casilleroDos, domino.getTerrenoDos());
		sacarVaciosFueraDeRango();
		return true;
	}

	public void colocarTerreno(Casillero casillero, Terreno terreno) {
		casillero.setTerreno(terreno);
		casillerosVacios.remove(casillero);
		actualizarLimites(casillero.getX(), casillero.getY());
		generarCasillerosAdyacentes(casillero);
	}

	public boolean sePuedeColocarDomino(Domino domino, Casillero casilleroUno, Casillero casilleroDos) {
		// Nos fijamos que el casillero este dentro del tablero
		if (casilleroFueraDeRango(casilleroUno) || casilleroFueraDeRango(casilleroUno)) {
			System.err.println("Algun casillero fuera de rango");
			return false;
		}

		// Nos fijamos que no esten vacios
		if (!casilleroUno.estaVacio() || !casilleroDos.estaVacio()) {
			System.err.println("Algun casillero no esta vacio");
			return false;
		}

		// Verificamos que los casilleros sean adyacentes
		if (!casilleroUno.esAdyacente(casilleroDos)) {
			System.err.println("casilleroUno no es adyacente a casilleroDos");
			return false;
		}

		// Verificamos que pueda colocarse por adyacencia
		if (!tieneAdyacentesDelTerreno(casilleroUno, domino.getTerrenoUno())
				&& !tieneAdyacentesDelTerreno(casilleroDos, domino.getTerrenoDos())) {
			System.err.println("Ninguno de los casilleros tiene adyacentes validos");
			return false;
		}
		return true;
	}

	public boolean tieneAdyacentesDelTerreno(Casillero casillero, Terreno terreno) {
		return adyacenteValido(getCasillero(casillero.getX(), casillero.getY() - 1), terreno)
				|| adyacenteValido(getCasillero(casillero.getX() - 1, casillero.getY()), terreno)
				|| adyacenteValido(getCasillero(casillero.getX() + 1, casillero.getY()), terreno)
				|| adyacenteValido(getCasillero(casillero.getX(), casillero.getY() + 1), terreno);
	}

	private boolean tieneAlgunVacioAdyacente(Casillero casillero) {
		Casillero arriba = new Casillero(casillero.getX(), casillero.getY() - 1);
		Casillero izquierda = new Casillero(casillero.getX() - 1, casillero.getY());
		Casillero derecha = new Casillero(casillero.getX() + 1, casillero.getY());
		Casillero abajo = new Casillero(casillero.getX(), casillero.getY() + 1);

		if ((getCasillero(arriba) == null || getCasillero(arriba).estaVacio()) && !casilleroFueraDeRango(arriba)) {
			return true;
		}
		if ((getCasillero(izquierda) == null || getCasillero(izquierda).estaVacio())
				&& !casilleroFueraDeRango(izquierda)) {
			return true;
		}
		if ((getCasillero(derecha) == null || getCasillero(derecha).estaVacio()) && !casilleroFueraDeRango(derecha)) {
			return true;
		}
		if ((getCasillero(abajo) == null || getCasillero(abajo).estaVacio()) && !casilleroFueraDeRango(abajo)) {
			return true;
		}

		return false;
	}

	private boolean adyacenteValido(Casillero casillero, Terreno terreno) {
		return casillero != null && !casilleroFueraDeRango(casillero)
				&& casillero.getTerreno().equalsTipoTerreno(terreno);
	}

	public boolean casilleroFueraDeRango(Casillero casillero) {
		return casilleroFueraDeRango(casillero.getX(), casillero.getY());
	}

	public boolean casilleroFueraDeRango(int x, int y) {
		if (Xmax - x > 4)
			return true;
		if (x - Xmin > 4)
			return true;
		if (Ymax - y > 4)
			return true;
		if (y - Ymin > 4)
			return true;
		return false;
	}

	@Override
	public String toString() {
		String resultado = "\n";
		for (int fila = Math.max(Xmin - 1, 0), columna = 0; fila <= Math.min(Xmax + 1, 9); fila++) {
			for (int j = Ymin - 1; j <= Ymax + 1; j++) {
				resultado += String.format("%d" + " %d" + "%8s|", fila, j, "");
			}
			resultado += "\n";
			for (columna = Math.max(Ymin - 1, 0); columna <= Math.min(Ymax + 1, 9); columna++) {
				if (getCasillero(fila, columna) != null) {
					resultado += String.format("%9s", getCasillero(fila, columna).getTerreno().getTipoTerreno());
					resultado += String.format("%2d|", getCasillero(fila, columna).getTerreno().getCoronas());
				} else
					resultado += String.format("%11s|", "");
			}
			resultado += "\n";
			for (int j = Math.max(Ymin - 1, 0); j <= Math.min(Ymax + 1, 9); j++) {
				resultado += "___________|";
			}
			resultado += "\n";
		}
		return resultado;
	}

	public int getXmin() {
		int indiceDibujo = Xmin - 1 > 0 ? Xmin - 2 : Xmin;
		return Xmax - Xmin == 4 ? Xmin : indiceDibujo;
		// return Xmin;
	}

	public int getXmax() {
		int indiceDibujo = Xmax + 1 < 9 ? Xmax + 2 : Xmax;
		return Xmax - Xmin == 4 ? Xmax : indiceDibujo;
		// return Xmax;
	}

	public int getYmin() {
		int indiceDibujo = Ymin - 1 > 0 ? Ymin - 2 : Ymin;
		return Ymax - Ymin == 4 ? Ymin : indiceDibujo;
		// return Ymin;
	}

	public int getYmax() {
		int indiceDibujo = Ymax + 1 < 9 ? Ymax + 2 : Ymax;
		return Ymax - Ymin == 4 ? Ymax : indiceDibujo;
		// return Ymax;
	}
}
