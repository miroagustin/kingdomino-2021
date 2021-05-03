package Core;

import java.util.Arrays;

import Core.Terreno.TipoTerreno;

public class Tablero {
	// 9x9 es el maximo tamaño que puede tener el tablero
	private Casillero[][] casilleros;

	public Tablero() {
		this.casilleros = generarCasilleros();
	}

	@Override
	public String toString() {
		return "Tablero [casilleros=" + Arrays.toString(casilleros) + "]";
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
		comodin.setTerreno(new Terreno(TipoTerreno.comodin,0));
		resultado[4][4] = comodin;
		return resultado;
	}

	public int calcularPuntaje() {
		// TODO Auto-generated method stub
		return null;
	}

	boolean verificarAdyacenciaTerreno(Casillero casillero, Terreno terreno) {
		CasillerosAdyacentes adyacentes = obtenerAdyacentes(casillero);
		if (casillero.equalTerreno(adyacentes.getDerecha()))
			return true;
		if (casillero.equalTerreno(adyacentes.getIzquierda()))
			return true;
		if (casillero.equalTerreno(adyacentes.getArriba()))
			return true;
		if (casillero.equalTerreno(adyacentes.getAbajo()))
			return true;

		return false;
	}

	CasillerosAdyacentes obtenerAdyacentes(Casillero casillero) {
		// TODO: FIJARSE SI CONVIENE PASAR ESTA LOGICA AL CONSTRUCTOR DE CASILLEROS
		// ADYACENTES
		CasillerosAdyacentes adyacentes = new CasillerosAdyacentes();
		if (casillero.getY() - 1 >= 0) {
			adyacentes.setArriba(this.casilleros[casillero.getX()][casillero.getY() - 1]);
		}
		if (casillero.getX() - 1 >= 0) {
			adyacentes.setIzq(this.casilleros[casillero.getX() - 1][casillero.getY()]);
		}
		if (casillero.getX() + 1 < 9) {
			adyacentes.setDer(this.casilleros[casillero.getX() + 1][casillero.getY()]);
		}
		if (casillero.getY() + 1 < 9) {
			adyacentes.setAbajo(this.casilleros[casillero.getX()][casillero.getY() + 1]);
		}
		return adyacentes;
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

		if (!verificarAdyacenciaTerreno(casilleroUno, domino.getTerrenoUno())
				&& !verificarAdyacenciaTerreno(casilleroDos, domino.getTerrenoDos())) {
			return false;
		}
		return true;
	}

}
