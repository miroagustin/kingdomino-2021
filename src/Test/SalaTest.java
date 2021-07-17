package Test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import Core.Jugador;
import Core.Sala;
import Core.Partida.EstadosPartida;
import Core.Rey.Colores;

class SalaTest {

	@Test
	void crearSalaTest() {
		Jugador due�o = new Jugador("Pepe");
		Sala sala = new Sala(due�o);
		assertEquals(due�o, sala.getDue�o());
		assertEquals(Colores.azul, due�o.getRey().getColor());
	}

	@Test
	void ingresarJugadorTest() {
		Jugador due�o = new Jugador("Pepe");
		Jugador jugador1 = new Jugador("Pepe");
		Jugador jugador2 = new Jugador("Pepe");
		Jugador jugador3 = new Jugador("Pepe");

		Sala sala = new Sala(due�o);
		assertEquals(Colores.azul, due�o.getRey().getColor());
		sala.ingresarJugador(jugador1);
		assertEquals(Colores.verde, jugador1.getRey().getColor());

		sala.ingresarJugador(jugador2);
		assertEquals(due�o, sala.getDue�o());
		assertEquals(Colores.azul, due�o.getRey().getColor());

		assertEquals(Colores.rojo, jugador2.getRey().getColor());
		sala.asignarColor(Colores.amarillo, jugador2);
		assertEquals(Colores.amarillo, jugador2.getRey().getColor());

		sala.ingresarJugador(jugador3);
		assertEquals(Colores.rojo, jugador3.getRey().getColor());
	}

	@Test
	void iniciarPartidaTest() throws Exception {
		Jugador due�o = new Jugador("Pepe");
		Jugador jugador1 = new Jugador("Popo");
		Jugador jugador2 = new Jugador("Pipi");
		Jugador jugador3 = new Jugador("Papa");

		Sala sala = new Sala(due�o);
		sala.ingresarJugador(jugador1);
		sala.ingresarJugador(jugador2);
		sala.ingresarJugador(jugador3);

		sala.iniciarPartida();
		assertEquals(EstadosPartida.enEspera, sala.getPartidaActual().mostrarEstado());

	}

}
