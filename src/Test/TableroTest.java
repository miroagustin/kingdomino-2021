package Test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.Before;
import org.junit.jupiter.api.Test;

import Core.Domino;
import Core.PosicionDomino;
import Core.Tablero;
import Core.Terreno;
import Core.Terreno.TipoTerreno;

class TableroTest {
	Tablero tablero;

	@Before
	public void setUp() {
		tablero = new Tablero();
		// Funcion solo usable en testing
		tablero.completarTerrenos();
		System.out.println(tablero);
	}

	@Test
	public void colocarDomino() throws Exception {
		Tablero tablero = new Tablero();
		Domino domino1 = new Domino(new Terreno(TipoTerreno.agua, 1), new Terreno(TipoTerreno.pantano, 0), 1);
		Domino domino2 = new Domino(new Terreno(TipoTerreno.agua, 1), new Terreno(TipoTerreno.bosque, 1), 2);
		Domino domino3 = new Domino(new Terreno(TipoTerreno.agua, 0), new Terreno(TipoTerreno.bosque, 0), 3);
		Domino domino4 = new Domino(new Terreno(TipoTerreno.agua, 1), new Terreno(TipoTerreno.bosque, 0), 4);
		Domino domino5 = new Domino(new Terreno(TipoTerreno.agua, 1), new Terreno(TipoTerreno.bosque, 0), 4);

		assertTrue(tablero.colocarDomino(domino1,
				new PosicionDomino(tablero.getOcrearCasilleroVacio(3, 4), tablero.getOcrearCasilleroVacio(2, 4))));
		assertEquals(1, tablero.calcularPuntaje());
		assertTrue(tablero.colocarDomino(domino2,
				new PosicionDomino(tablero.getOcrearCasilleroVacio(3, 5), tablero.getOcrearCasilleroVacio(3, 6))));
		assertEquals(5, tablero.calcularPuntaje());
		assertTrue(tablero.colocarDomino(domino3,
				new PosicionDomino(tablero.getOcrearCasilleroVacio(5, 6), tablero.getOcrearCasilleroVacio(4, 6))));
		assertEquals(6, tablero.calcularPuntaje());
		assertTrue(tablero.colocarDomino(domino4,
				new PosicionDomino(tablero.getOcrearCasilleroVacio(5, 7), tablero.getOcrearCasilleroVacio(4, 7))));
		assertEquals(9, tablero.calcularPuntaje());
		assertTrue(tablero.colocarDomino(domino5,
				new PosicionDomino(tablero.getOcrearCasilleroVacio(4, 5), tablero.getOcrearCasilleroVacio(5, 5))));
		assertEquals(14, tablero.calcularPuntaje());
		assertTrue(tablero.colocarDomino(domino5,
				new PosicionDomino(tablero.getOcrearCasilleroVacio(2, 5), tablero.getOcrearCasilleroVacio(2, 6))));
		System.out.println(tablero);
		assertEquals(22, tablero.calcularPuntaje());
		
	}

	@Test
	public void calcularPuntajeTest() {
		setUp();
		//assertEquals(18, tablero.calcularPuntaje());
	}

}
