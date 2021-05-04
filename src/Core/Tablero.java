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
	private List<Casillero> casillerosCalculados;
	private Casillero comodin;
	private int Xmin;
	private int Xmax;
	private int Ymin;
	private int Ymax;

	public Tablero() {
		this.casilleros = generarCasilleros();
		this.casillerosCalculados = new LinkedList<Casillero>();
	}

	@Override
	public String toString() {
		String resultado = "";
		int i = 0;
		for (Casillero[] fila : casilleros) {
			for (int j = 0; j < fila.length; j++) {
				resultado += String.format("%d" + " %d" + "%8s|", i, j, "");
			}
			resultado += "\n";
			for (Casillero casillero : fila) {
				if (!casillero.estaVacio()) {
					resultado += String.format("%9s", casillero.getTerreno().getTipoTerreno());
					resultado += String.format("%2d|", casillero.getTerreno().getCoronas());
				} else
					resultado += String.format("%11s|", "");
			}
			resultado += "\n___________|___________|___________|___________|___________|___________|___________|___________|___________|\n";
			i++;
		}
		return resultado;
	}

	public Casillero getCasillero(int x, int y) {
		return this.casilleros[x][y];
	}

	private void setLimites(Casillero casillero) {
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
		setLimites(comodin);
		comodin.setTerreno(new Terreno(TipoTerreno.comodin, 0));
		resultado[4][4] = comodin;
		return resultado;
	}

	public int calcularPuntaje() {
		int total = 0;
		for (Casillero[] fila : casilleros) {
			for (Casillero casillero : fila) {
				if (!casillero.estaVacio() && !casillerosCalculados.contains(casillero) && !casillero.esComodin())
					total += calcularCasillero(casillero).getTotal();
			}
		}
		return total;
	}

	private ResultadoCasillero calcularCasillero(Casillero casillero) {
		int totalCasillero = 1;
		int totalCoronas = casillero.getTerreno().getCoronas();
		System.out.println(casillero);
		casillerosCalculados.add(casillero);
		List<Casillero> casillerosAdyacentes = new CasillerosAdyacentes(casillero, casilleros)
				.obtenerAdyacentesValidos();
		for (Casillero casilleroAdyacente : casillerosAdyacentes) {
			if (!casillerosCalculados.contains(casilleroAdyacente) && !casilleroAdyacente.esComodin()) {
				ResultadoCasillero resultadoParcial = calcularCasillero(casilleroAdyacente);
				totalCasillero += resultadoParcial.getTotalCasillero();
				totalCoronas += resultadoParcial.getTotalCoronas();
			}
		}
		return new ResultadoCasillero(totalCasillero, totalCoronas);
	}

	List<Casillero> obtenerAdyacentesValidos(Casillero casillero, Terreno terreno) {
		Casillero casilleroConTerreno = new Casillero(casillero);
		casilleroConTerreno.setTerreno(terreno);

		CasillerosAdyacentes adyacentes = new CasillerosAdyacentes(casilleroConTerreno, casilleros);
		return adyacentes.obtenerAdyacentesValidos();
	}

	public boolean colocarDomino(Domino domino, Casillero casilleroUno, Casillero casilleroDos) {
		if (!sePuedeColocarDomino(domino, casilleroUno, casilleroDos)) {
			return false;
		}
		setLimites(casilleroUno);
		setLimites(casilleroDos);
		casilleroUno.setTerreno(domino.getTerrenoUno());
		casilleroDos.setTerreno(domino.getTerrenoDos());
		return true;
	}

	private boolean sePuedeColocarDomino(Domino domino, Casillero casilleroUno, Casillero casilleroDos) {
		if (casilleroFueraDeRango(casilleroUno) || casilleroFueraDeRango(casilleroUno))
			return false;
		if (!casilleroUno.estaVacio() || !casilleroDos.estaVacio())
			return false;
		if (obtenerAdyacentesValidos(casilleroUno, domino.getTerrenoUno()).size() == 0
				&& obtenerAdyacentesValidos(casilleroDos, domino.getTerrenoDos()).size() == 0) {
			return false;
		}
		return true;
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
	public void completarTerrenosRandom() {
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
			}
		}
		casilleros[4][4].setTerreno(new Terreno(TipoTerreno.comodin, 0));

	}

}
