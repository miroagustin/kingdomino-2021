package Core;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class JugadorTest {

	@Test
	void generarJugadorTest() {
		Jugador jugador = new Jugador("Pepe");
		assertEquals("Pepe",jugador.getNombre());
	}
	
	@Test
	void elegirReyTest() {
		Jugador jugador = new Jugador("Pepe");
		jugador.elegirRey("azul");
		assertEquals("azul",jugador.getRey().getColor());
	}

}
