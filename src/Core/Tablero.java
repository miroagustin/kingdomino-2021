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

	public Tablero() {
		this.casilleros = generarCasilleros();
		this.casillerosCalculados = new LinkedList<Casillero>();
	}

	@Override
	public String toString() {
		String resultado = "";
		for (Casillero[] fila : casilleros) {
			for (Casillero casillero : fila) {
				resultado += casillero.toString() + "\t";
			}
			resultado += "\n";
		}
		return resultado;
	}

	private Casillero[][] generarCasilleros() {
		Casillero[][] resultado = new Casillero[9][9];
		for (int i = 0; i < resultado.length; i++) {
			for (int j = 0; j < resultado[i].length; j++) {
				resultado[i][j] = new Casillero(i, j);
			}
		}
		// Coloco el comodin/castillo en el centro del tablero
		Casillero comodin = new Casillero(4, 4);
		comodin.setTerreno(new Terreno(TipoTerreno.comodin, 0));
		resultado[4][4] = comodin;
		return resultado;
	}

	public int calcularPuntaje() {
		int total = 0;
		for (Casillero[] fila : casilleros) {
			for (Casillero casillero : fila) {
				if (!casillerosCalculados.contains(casillero))
					total += calcularCasillero(casillero).getTotal();
			}
		}
		return total;
	}

	private ResultadoCasillero calcularCasillero(Casillero casillero) {
		int totalCasillero = 0;
		int totalCoronas = 0;
		casillerosCalculados.add(casillero);
		List<Casillero> casillerosAdyacentes = obtenerAdyacentesValidos(casillero, casillero.getTerreno());
		for (Casillero casilleroAdyacente : casillerosAdyacentes) {
			ResultadoCasillero resultadoParcial = calcularCasillero(casilleroAdyacente);
			totalCasillero += resultadoParcial.getTotalCasillero();
			totalCoronas += resultadoParcial.getTotalCoronas();
		}
		return new ResultadoCasillero(totalCasillero, totalCoronas);
	}

	List<Casillero> obtenerAdyacentesValidos(Casillero casillero, Terreno terreno) {
		CasillerosAdyacentes adyacentes = new CasillerosAdyacentes(casillero,casilleros);
		return adyacentes.obtenerAdyacentesValidos();
	}

	public boolean colocarDomino(Domino domino, Casillero casilleroUno, Casillero casilleroDos) {
		if (!sePuedeColocarDomino(domino, casilleroUno, casilleroDos)) {
			return false;
		}
		casilleroUno.setTerreno(domino.getTerrenoUno());
		casilleroDos.setTerreno(domino.getTerrenoDos());
		return true;
	}

	private boolean sePuedeColocarDomino(Domino domino, Casillero casilleroUno, Casillero casilleroDos) {
		if (!casilleroUno.estaVacio() || !casilleroDos.estaVacio())
			return false;
		if (obtenerAdyacentesValidos(casilleroUno, domino.getTerrenoUno()).size() == 0
				&& obtenerAdyacentesValidos(casilleroDos, domino.getTerrenoDos()).size() == 0) {
			return false;
		}
		return true;
	}
	// FUNCION PARA TESTEAR EL PUNTAJE
	public void completarTerrenosRandom() {
		List<TipoTerreno> tipos = new ArrayList<TipoTerreno>(Arrays.asList(TipoTerreno.values()));
		tipos.remove(TipoTerreno.comodin);
		tipos.remove(TipoTerreno.vacio);
		for (Casillero[] fila : casilleros) {
			for (Casillero casillero : fila) {
				casillero.setTerreno(new Terreno(tipos.get(CoreUtils.randInt(0, 5)), CoreUtils.randInt(0, 1)));
			}
		}

	}

}
