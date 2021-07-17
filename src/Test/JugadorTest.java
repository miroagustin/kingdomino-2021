package Test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import Core.Casillero;
import Core.Domino;
import Core.Jugador;
import Core.PosicionDomino;
import Core.Terreno;
import Core.Rey.Colores;
import Core.Terreno.TipoTerreno;

@DisplayName("Jugador")
class JugadorTest {

	@Test
	@DisplayName("Generar Jugador")
	void generarJugadorTest() {
		Jugador jugador = new Jugador("Pepe");
		assertEquals("Pepe", jugador.getNombre());
	}

	@Test
	@DisplayName("Elegir Rey")
	void elegirReyTest() {
		Jugador jugador = new Jugador("Pepe");
		jugador.elegirRey(Colores.azul);
		assertEquals(Colores.azul, jugador.getRey().getColor());
	}

	@Test
	@DisplayName("Calcular puntaje")
	void calcularPuntajeTest() {
		Domino domino = new Domino(new Terreno(TipoTerreno.agua, 1), new Terreno(TipoTerreno.agua, 0), 4);
		Jugador jugador = new Jugador("Pepe");
		jugador.elegirRey(Colores.azul);
		jugador.setDominoEnMano(domino);
		jugador.colocarDomino(new PosicionDomino(new Casillero(3, 4), new Casillero(2, 4)));
		assertEquals(2, jugador.getPuntaje());
	}

}
