package Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.LinkedList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Core.Jugador;
import Core.Partida;
import Core.Partida.EstadosPartida;
import Core.Rey.Colores;

class PartidaTest {

	private List<Jugador> jugadores;

	@BeforeEach
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
		jugador1.elegirRey(Colores.azul);
		jugador2.elegirRey(Colores.negro);
		jugador3.elegirRey(Colores.rosa);
		jugador4.elegirRey(Colores.verde);

	}

	@Test
	void generarPartidaTest() throws Exception {
		Partida partida = new Partida(this.jugadores);
		assertNotNull(partida);
		assertEquals(partida.mostrarEstado(), EstadosPartida.enEspera);
	}

	@Test
	void eliminarJugadorTest() throws Exception {
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
