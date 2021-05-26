package Core;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

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

	public Tablero() {
		this.casilleros = generarCasilleros();
	}

	@Override
	public String toString() {
		String resultado = "";
		int i = Xmin;
		for (int fila = Xmin, columna = Ymin; fila <= Xmax; fila++) {
			for (int j = Ymin; j <= Ymax; j++) {
				resultado += String.format("%d" + " %d" + "%8s|", i, j, "");
			}
			resultado += "\n";
			for (columna = Ymin; columna <= Ymax; columna++) {
				if (!casilleros[fila][columna].estaVacio()) {
					resultado += String.format("%9s", casilleros[fila][columna].getTerreno().getTipoTerreno());
					resultado += String.format("%2d|", casilleros[fila][columna].getTerreno().getCoronas());
				} else
					resultado += String.format("%11s|", "");
			}
			resultado += "\n";
			for (int j = Ymin; j <= Ymax; j++) {
				resultado += "___________|";
			}
			resultado += "\n";
			i++;
		}
		return resultado;
	}

	public Casillero getCasillero(int x, int y) {
		return this.casilleros[x][y];
	}

	private void actualizarLimites(Casillero casillero) {
		if (casillero.getX() > Xmax)
			Xmax = casillero.getX();
		if (casillero.getX() < Xmin)
			Xmin = casillero.getX();
		if (casillero.getY() > Ymax)
			Ymax = casillero.getY();
		if (casillero.getY() < Ymin)
			Ymin = casillero.getY();
	}

	private Casillero[][] generarCasilleros() {
		Casillero[][] resultado = new Casillero[9][9];
		for (int i = 0; i < resultado.length; i++) {
			for (int j = 0; j < resultado[i].length; j++) {
				resultado[i][j] = new Casillero(i, j);
			}
		}
		// Coloco el comodin/castillo en el centro del tablero
		comodin = new Casillero(4, 4);
		actualizarLimites(comodin);
		comodin.setTerreno(new Terreno(TipoTerreno.comodin, 0));
		comodin.visitado();
		resultado[4][4] = comodin;
		return resultado;
	}

	public int calcularPuntaje() {
		int total = 0;
		total += calcularPuntajeConFloodFill();
		for (int i = Xmin; i <= Xmax; i++) {
			for (int j = Ymin; j <= Ymax; j++) {
				if (i != 4 || j != 4)
					casilleros[i][j].visitar();
			}
		}
		return total;
	}

	public int calcularPuntajeConFloodFill() {
		List<Casillero> grupo = new LinkedList<Casillero>();
		int subTotal = 0;
		for (int i = Xmin; i <= Xmax; i++) {
			for (int j = Ymin; j <= Ymax; j++) {
				if (casilleros[i][j].sinVisitar()) {
					grupo = floodFill(i, j);
					int cantCoronas = 0;
					int cantFichas = 0;
					for (Casillero casillero : grupo) {
						cantCoronas += casillero.getTerreno().getCoronas();
						cantFichas++;
					}
					subTotal += cantFichas * cantCoronas;
				}
			}
		}
		return subTotal;
	}

	private List<Casillero> floodFill(int fila, int columna) {
		Queue<Casillero> casillerosParaVisitar = new LinkedList<Casillero>();
		List<Casillero> region = new LinkedList<Casillero>();
		casillerosParaVisitar.add(casilleros[fila][columna]);
		casilleros[fila][columna].visitado();
		Casillero casillero;
		while ((casillero = casillerosParaVisitar.poll()) != null) {
			region.add(casillero);
			List<Casillero> casillerosAdyacentes = new CasillerosAdyacentes(casillero, casilleros)
					.obtenerAdyacentesValidos();
			for (Casillero casillero2 : casillerosAdyacentes) {
				if (casilleros[casillero2.getX()][casillero2.getY()].sinVisitar()) {
					casillerosParaVisitar.add(casillero2);
					casilleros[casillero2.getX()][casillero2.getY()].visitado();
				}
			}
		}
		return region;
	}

	public boolean colocarDomino(Domino domino, PosicionDomino posicionDomino) {
		Casillero casilleroUno = posicionDomino.getCasilleroUno();
		Casillero casilleroDos = posicionDomino.getCasilleroDos();

		if (!sePuedeColocarDomino(domino, casilleroUno, casilleroDos)) {
			return false;
		}
		actualizarLimites(casilleroUno);
		actualizarLimites(casilleroDos);
		casilleroUno.setTerreno(domino.getTerrenoUno());
		casilleroDos.setTerreno(domino.getTerrenoDos());
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
		if (obtenerAdyacentesValidos(casilleroUno, domino.getTerrenoUno()).size() == 0
				&& obtenerAdyacentesValidos(casilleroDos, domino.getTerrenoDos()).size() == 0) {
			return false;
		}
		// Verificamos que los casilleros sean adyacentes
		if (!casilleroUno.esAdyacente(casilleroDos))
			return false;

		return true;
	}

	List<Casillero> obtenerAdyacentesValidos(Casillero casillero, Terreno terreno) {
		Casillero casilleroConTerreno = new Casillero(casillero);
		casilleroConTerreno.setTerreno(terreno);
		CasillerosAdyacentes adyacentes = new CasillerosAdyacentes(casilleroConTerreno, casilleros);
		return adyacentes.obtenerAdyacentesValidos();
	}

	private boolean casilleroFueraDeRango(Casillero casillero) {
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

	// FUNCION PARA TESTEAR EL PUNTAJE
	public void completarTerrenos() {
		List<TipoTerreno> tipos = new ArrayList<TipoTerreno>(Arrays.asList(TipoTerreno.values()));
		tipos.remove(TipoTerreno.comodin);
		tipos.remove(TipoTerreno.vacio);
		// para que quede en el medio
		for (int i = 2; i < 7; i++) {
			for (int j = 2; j < 7; j++) {
				if (j < 4 && i < 4) {
					casilleros[i][j].setTerreno(new Terreno(TipoTerreno.agua, (j + i) % 2));
				} else {
					casilleros[i][j]
							.setTerreno(new Terreno(tipos.get((CoreUtils.randInt(0, 5) + j + i) % 6), (j + i) % 2));
				}
				actualizarLimites(casilleros[i][j]);
			}
		}
		casilleros[4][4].setTerreno(new Terreno(TipoTerreno.comodin, 0));

	}

}
