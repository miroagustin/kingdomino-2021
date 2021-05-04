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

	public Tablero() {
		this.casilleros = generarCasilleros();
		this.casillerosCalculados = new LinkedList<Casillero>();
	}

	@Override
	public String toString() {
		String resultado = "";
		for (Casillero[] fila : casilleros) {
			for (Casillero casillero : fila) {
				if (!casillero.estaVacio())
					resultado += casillero.toString() + "\n";
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
		comodin = new Casillero(4, 4);
		comodin.setTerreno(new Terreno(TipoTerreno.comodin, 0));
		resultado[4][4] = comodin;
		return resultado;
	}

	public int calcularPuntaje() {
		int total = 0;
		for (Casillero[] fila : casilleros) {
			for (Casillero casillero : fila) {
				if (!casillero.estaVacio() && !casillerosCalculados.contains(casillero))
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
		List<Casillero> casillerosAdyacentes = obtenerAdyacentesValidos(casillero, casillero.getTerreno());
		for (Casillero casilleroAdyacente : casillerosAdyacentes) {
			if(casillerosCalculados.contains(casilleroAdyacente))
				continue;
			
			ResultadoCasillero resultadoParcial = calcularCasillero(casilleroAdyacente);
			totalCasillero += resultadoParcial.getTotalCasillero();
			totalCoronas += resultadoParcial.getTotalCoronas();
		}
		return new ResultadoCasillero(totalCasillero, totalCoronas);
	}

	List<Casillero> obtenerAdyacentesValidos(Casillero casillero, Terreno terreno) {
		CasillerosAdyacentes adyacentes = new CasillerosAdyacentes(casillero, casilleros);
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
		if(casilleroFueraDeRango(casilleroUno) || casilleroFueraDeRango(casilleroUno))
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
				}
				else {
					casilleros[i][j]
							.setTerreno(new Terreno(tipos.get((CoreUtils.randInt(0, 5) + j + i) % 6), (j + i) % 2));
				}

			}
		}

	}

}
