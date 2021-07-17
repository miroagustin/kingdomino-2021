package Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Core.Casillero;
import Core.Domino;
import Core.PosicionDomino;
import Core.Tablero;
import Core.Terreno;
import Core.Terreno.TipoTerreno;

class TableroTest {
	Tablero tablero;

	@BeforeEach
	public void setUp() {
		tablero = new Tablero();
	}

	@Test
	public void colocarDomino() throws Exception {
		Domino domino1 = new Domino(new Terreno(TipoTerreno.agua, 1), new Terreno(TipoTerreno.pantano, 0), 1);
		Domino domino2 = new Domino(new Terreno(TipoTerreno.agua, 1), new Terreno(TipoTerreno.bosque, 1), 2);
		Domino domino3 = new Domino(new Terreno(TipoTerreno.agua, 0), new Terreno(TipoTerreno.bosque, 0), 3);
		Domino domino4 = new Domino(new Terreno(TipoTerreno.agua, 1), new Terreno(TipoTerreno.bosque, 0), 4);
		Domino domino5 = new Domino(new Terreno(TipoTerreno.agua, 1), new Terreno(TipoTerreno.bosque, 0), 4);

		assertTrue(tablero.colocarDomino(domino1,
				new PosicionDomino(tablero.getOcrearCasilleroVacio(3, 4), tablero.getOcrearCasilleroVacio(2, 4))));
		assertTrue(tablero.colocarDomino(domino2,
				new PosicionDomino(tablero.getOcrearCasilleroVacio(3, 5), tablero.getOcrearCasilleroVacio(3, 6))));
		assertTrue(tablero.colocarDomino(domino3,
				new PosicionDomino(tablero.getOcrearCasilleroVacio(5, 6), tablero.getOcrearCasilleroVacio(4, 6))));
		assertTrue(tablero.colocarDomino(domino4,
				new PosicionDomino(tablero.getOcrearCasilleroVacio(5, 7), tablero.getOcrearCasilleroVacio(4, 7))));
		assertTrue(tablero.colocarDomino(domino5,
				new PosicionDomino(tablero.getOcrearCasilleroVacio(4, 5), tablero.getOcrearCasilleroVacio(5, 5))));
		assertTrue(tablero.colocarDomino(domino5,
				new PosicionDomino(tablero.getOcrearCasilleroVacio(2, 5), tablero.getOcrearCasilleroVacio(2, 6))));
	}

	@Test
	public void colocarDominoPosicionNoVacia() throws Exception {
		Domino domino1 = new Domino(new Terreno(TipoTerreno.agua, 1), new Terreno(TipoTerreno.pantano, 0), 1);
		Domino domino2 = new Domino(new Terreno(TipoTerreno.agua, 1), new Terreno(TipoTerreno.bosque, 1), 2);

		assertTrue(tablero.colocarDomino(domino1,
				new PosicionDomino(tablero.getOcrearCasilleroVacio(3, 4), tablero.getOcrearCasilleroVacio(2, 4))));
		assertFalse(tablero.colocarDomino(domino2,
				new PosicionDomino(tablero.getOcrearCasilleroVacio(3, 4), tablero.getOcrearCasilleroVacio(2, 4))));
	}

	@Test
	public void colocarDominoPosicionSinAdyacentesValidos() throws Exception {
		Domino domino1 = new Domino(new Terreno(TipoTerreno.agua, 1), new Terreno(TipoTerreno.pantano, 0), 1);
		Domino domino2 = new Domino(new Terreno(TipoTerreno.agua, 1), new Terreno(TipoTerreno.bosque, 1), 2);

		assertTrue(tablero.colocarDomino(domino1,
				new PosicionDomino(tablero.getOcrearCasilleroVacio(3, 4), tablero.getOcrearCasilleroVacio(2, 4))));
		assertFalse(tablero.colocarDomino(domino2,
				new PosicionDomino(tablero.getOcrearCasilleroVacio(3, 6), tablero.getOcrearCasilleroVacio(3, 5))));
	}

	@Test
	public void colocarDominoPosicionFueraDeRango() throws Exception {
		Domino domino1 = new Domino(new Terreno(TipoTerreno.agua, 1), new Terreno(TipoTerreno.agua, 0), 1);
		Domino domino2 = new Domino(new Terreno(TipoTerreno.agua, 1), new Terreno(TipoTerreno.agua, 1), 2);
		Domino domino3 = new Domino(new Terreno(TipoTerreno.agua, 0), new Terreno(TipoTerreno.agua, 0), 3);

		assertTrue(tablero.colocarDomino(domino1,
				new PosicionDomino(tablero.getOcrearCasilleroVacio(3, 4), tablero.getOcrearCasilleroVacio(2, 4))));
		assertTrue(tablero.colocarDomino(domino2,
				new PosicionDomino(tablero.getOcrearCasilleroVacio(1, 4), tablero.getOcrearCasilleroVacio(0, 4))));
		assertFalse(tablero.colocarDomino(domino3,
				new PosicionDomino(tablero.getOcrearCasilleroVacio(5, 4), tablero.getOcrearCasilleroVacio(6, 4))));
	}

	@Test
	public void posicionesValidasComodin() throws Exception {
		Domino domino1 = new Domino(new Terreno(TipoTerreno.bosque, 1), new Terreno(TipoTerreno.agua, 0), 1);

		List<Casillero> posibles = tablero.getCasillerosPosibles(domino1);
		assertTrue(posibles.remove(tablero.getCasillero(3, 4)));
		assertTrue(posibles.remove(tablero.getCasillero(5, 4)));
		assertTrue(posibles.remove(tablero.getCasillero(4, 3)));
		assertTrue(posibles.remove(tablero.getCasillero(4, 5)));
		assertEquals(0, posibles.size());
	}

	@Test
	public void posicionesValidasTerrenosVarios() throws Exception {
		Domino domino1 = new Domino(new Terreno(TipoTerreno.bosque, 1), new Terreno(TipoTerreno.agua, 0), 1);
		Domino domino2 = new Domino(new Terreno(TipoTerreno.pantano, 1), new Terreno(TipoTerreno.agua, 1), 2);

		assertTrue(tablero.colocarDomino(domino1,
				new PosicionDomino(tablero.getOcrearCasilleroVacio(3, 4), tablero.getOcrearCasilleroVacio(2, 4))));

		List<Casillero> posibles = tablero.getCasillerosPosibles(domino2);
		assertTrue(posibles.remove(tablero.getCasillero(5, 4)));
		assertTrue(posibles.remove(tablero.getCasillero(4, 3)));
		assertTrue(posibles.remove(tablero.getCasillero(4, 5)));
		assertTrue(posibles.remove(tablero.getCasillero(1, 4)));
		assertTrue(posibles.remove(tablero.getCasillero(2, 3)));
		assertTrue(posibles.remove(tablero.getCasillero(2, 5)));
		assertEquals(0, posibles.size());
	}

}
