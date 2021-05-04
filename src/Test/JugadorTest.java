package Test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import Core.Jugador;
import Core.Rey.Colores;

class JugadorTest {

	@Test
	void generarJugadorTest() {
		Jugador jugador = new Jugador("Pepe");
		assertEquals("Pepe",jugador.getNombre());
	}
	
	@Test
	void elegirReyTest() {
		Jugador jugador = new Jugador("Pepe");
		jugador.elegirRey(Colores.azul);
		assertEquals(Colores.azul,jugador.getRey().getColor());
	}

}