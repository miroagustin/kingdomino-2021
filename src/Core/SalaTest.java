package Core;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class SalaTest {

	@Test
	void crearSalaTest() {
		Jugador dueño = new Jugador("Pepe");
		Sala sala = new Sala(dueño);
		assertEquals(dueño, sala.getDueño());
		assertEquals("azul", dueño.getRey().getColor());
	}

	@Test
	void ingresarJugadorTest() {
		Jugador dueño = new Jugador("Pepe");
		Jugador jugador1 = new Jugador("Pepe");
		Jugador jugador2 = new Jugador("Pepe");
		Jugador jugador3 = new Jugador("Pepe");

		Sala sala = new Sala(dueño);
		assertEquals("azul", dueño.getRey().getColor());
		sala.ingresarJugador(jugador1);
		assertEquals("verde", jugador1.getRey().getColor());

		sala.ingresarJugador(jugador2);
		assertEquals(dueño, sala.getDueño());
		assertEquals("azul", dueño.getRey().getColor());

		assertEquals("rosa", jugador2.getRey().getColor());
		sala.asignarColor("negro", jugador2);
		assertEquals("negro", jugador2.getRey().getColor());

		sala.ingresarJugador(jugador3);
		assertEquals("rosa", jugador3.getRey().getColor());
	}

	@Test
	void iniciarPartidaTest() throws Exception {
		Jugador dueño = new Jugador("Pepe");
		Jugador jugador1 = new Jugador("Pepe");
		Jugador jugador2 = new Jugador("Pepe");
		Jugador jugador3 = new Jugador("Pepe");

		Sala sala = new Sala(dueño);
		sala.ingresarJugador(jugador1);
		sala.ingresarJugador(jugador2);
		sala.ingresarJugador(jugador3);

		sala.iniciarPartida();
		assertTrue(sala.getPartidaActual().mostrarEstado());

	}

}
