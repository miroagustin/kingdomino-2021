package Core;

import static org.junit.jupiter.api.Assertions.*;

import java.util.LinkedList;
import java.util.List;

import org.junit.Before;
import org.junit.jupiter.api.Test;

class PartidaTest {

	private List<Jugador> jugadores;

	/*
	 * El método anotado como @Before nos permitirá ejecutar código antes de la
	 * ejecución de cada prueba
	 */
	@Before
	public void setUp() throws Exception {
		Jugador jugador1 = new Jugador("Pepe");
		Jugador jugador2 = new Jugador("Moni");
		Jugador jugador3 = new Jugador("Fatiga");
		Jugador jugador4 = new Jugador("Coqui");
		jugadores = new LinkedList<Jugador>();
		jugadores.add(jugador1);
		jugadores.add(jugador2);
		jugadores.add(jugador3);
		jugadores.add(jugador4);

	}

	@Test
	void generarPartidaTest() throws Exception {
		Jugador jugador1 = new Jugador("Pepe");
		Jugador jugador2 = new Jugador("Moni");
		Jugador jugador3 = new Jugador("Fatiga");
		Jugador jugador4 = new Jugador("Coqui");
		List<Jugador> jugadores = new LinkedList<Jugador>();
		jugadores.add(jugador1);
		jugadores.add(jugador2);
		jugadores.add(jugador3);
		jugadores.add(jugador4);

		Partida partida = new Partida(jugadores);
		assertNotNull(partida);
		assertTrue(partida.mostrarEstado());
		assertEquals("Pepe", partida.getJugadores().get(0).getNombre());
	}

	@Test
	void eliminarJugadorTest() throws Exception {

		Jugador jugador1 = new Jugador("Pepe");
		Jugador jugador2 = new Jugador("Moni");
		Jugador jugador3 = new Jugador("Fatiga");
		Jugador jugador4 = new Jugador("Coqui");
		List<Jugador> jugadores = new LinkedList<Jugador>();
		jugadores.add(jugador1);
		jugadores.add(jugador2);
		jugadores.add(jugador3);
		jugadores.add(jugador4);

		Partida partida = new Partida(jugadores);
		for (Jugador jugador : partida.getJugadores()) {
			System.out.print("Nombre: " + jugador.getNombre() + "\t");
		}

		assertEquals(4, partida.getJugadores().size());

		partida.eliminarJugador(partida.getJugadores().get(2));
		System.out.println("\nEliminamos el jugador 3");

		for (Jugador jugador : partida.getJugadores()) {
			System.out.print("Nombre: " + jugador.getNombre() + "\t");
		}

		assertEquals(3, partida.getJugadores().size());
	}

}
