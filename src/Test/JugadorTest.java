package Test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import Core.Jugador;
import Core.Rey.Colores;

@DisplayName("Jugador")
class JugadorTest {

	@Test
	@DisplayName("Generar Jugador")
	void generarJugadorTest() {
		Jugador jugador = new Jugador("Pepe");
		assertEquals("Pepe",jugador.getNombre());
	}
	
	@Test
	@DisplayName("Elegir Rey")
	void elegirReyTest() {
		Jugador jugador = new Jugador("Pepe");
		jugador.elegirRey(Colores.azul);
		assertEquals(Colores.azul,jugador.getRey().getColor());
	}

}
