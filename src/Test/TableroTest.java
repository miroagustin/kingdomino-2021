package Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.Before;
import org.junit.jupiter.api.Test;

import Core.Casillero;
import Core.Domino;
import Core.Mazo;
import Core.Tablero;

class TableroTest {
	Tablero tablero;

	@Before
	public void setUp() {
		tablero = new Tablero();
		//Funcion solo usable en testing
		tablero.completarTerrenosRandom();
		System.out.println(tablero.toString());

	}

	@Test
	public void colocarDomino() throws Exception {
		Tablero tablero = new Tablero();
		Mazo mazo = new Mazo();
		Casillero casillero1 = tablero.getCasillero(3, 4);
		Casillero casillero2 = tablero.getCasillero(2, 4);

		List<Domino> baraja = mazo.barajarDomino();
		
		assertTrue(tablero.colocarDomino(baraja.get(0), casillero1, casillero2));
		System.out.println(tablero.toString());
		tablero.calcularPuntaje();
	}

	@Test
	public void calcularPuntajeTest() {
		setUp();
		// Todavia no anda
		assertEquals(tablero.calcularPuntaje(), 18);

	}

}
