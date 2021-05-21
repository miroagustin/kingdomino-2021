package Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.Before;
import org.junit.jupiter.api.Test;

import Core.Casillero;
import Core.Domino;
import Core.Mazo;
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
		tablero.completarTerrenosRandom();

	}

	@Test
	public void colocarDomino() throws Exception {
		Tablero tablero = new Tablero();
		Domino domino1 = new Domino(new Terreno(TipoTerreno.agua, 1), new Terreno(TipoTerreno.pantano, 0), 1);
		Domino domino2 = new Domino(new Terreno(TipoTerreno.agua, 1), new Terreno(TipoTerreno.bosque, 1), 2);

		assertTrue(tablero.colocarDomino(domino1,
				new PosicionDomino(tablero.getCasillero(3, 4), tablero.getCasillero(2, 4))));
		assertTrue(tablero.colocarDomino(domino2,
				new PosicionDomino(tablero.getCasillero(3, 5), tablero.getCasillero(3, 6))));
	}

	@Test
	public void calcularPuntajeTest() {
		setUp();
		// Todavia no anda
		assertEquals(tablero.calcularPuntaje(), 18);
	}

}
