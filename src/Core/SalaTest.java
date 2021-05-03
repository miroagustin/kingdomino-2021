package Core;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import Core.Rey.colores;

class SalaTest {

	@Test
	void crearSalaTest() {
		Jugador due�o = new Jugador("Pepe");
		Sala sala = new Sala(due�o);
		assertEquals(due�o, sala.getDue�o());
		assertEquals(colores.azul, due�o.getRey().getColor());
	}

	@Test
	void ingresarJugadorTest() {
		Jugador due�o = new Jugador("Pepe");
		Jugador jugador1 = new Jugador("Pepe");
		Jugador jugador2 = new Jugador("Pepe");
		Jugador jugador3 = new Jugador("Pepe");

		Sala sala = new Sala(due�o);
		assertEquals(colores.azul, due�o.getRey().getColor());
		sala.ingresarJugador(jugador1);
		assertEquals(colores.verde, jugador1.getRey().getColor());

		sala.ingresarJugador(jugador2);
		assertEquals(due�o, sala.getDue�o());
		assertEquals(colores.azul, due�o.getRey().getColor());

		assertEquals(colores.rosa, jugador2.getRey().getColor());
		sala.asignarColor(colores.negro, jugador2);
		assertEquals(colores.negro, jugador2.getRey().getColor());

		sala.ingresarJugador(jugador3);
		assertEquals(colores.rosa, jugador3.getRey().getColor());
	}

	@Test
	void iniciarPartidaTest() throws Exception {
		Jugador due�o = new Jugador("Pepe");
		Jugador jugador1 = new Jugador("Pepe");
		Jugador jugador2 = new Jugador("Pepe");
		Jugador jugador3 = new Jugador("Pepe");

		Sala sala = new Sala(due�o);
		sala.ingresarJugador(jugador1);
		sala.ingresarJugador(jugador2);
		sala.ingresarJugador(jugador3);

		sala.iniciarPartida();
		assertTrue(sala.getPartidaActual().mostrarEstado());

	}

}
