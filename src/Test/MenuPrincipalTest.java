package Test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import Core.Jugador;
import Core.MenuPrincipal;

class MenuPrincipalTest {

	@Test
	@DisplayName("AgregarSala")
	void agregarSala() {
		MenuPrincipal menu = new MenuPrincipal();

		assertEquals(0, menu.getSalas().size());

		menu.crearSala(new Jugador("Pepe"));

		assertEquals(1, menu.getSalas().size());

	}

	@Test
	@DisplayName("MultiplesSalas")
	void multiplesSalas() {
		MenuPrincipal menu = new MenuPrincipal();

		assertEquals(0, menu.getSalas().size());

		menu.crearSala(new Jugador("Pepe"));
		menu.crearSala(new Jugador("Pepe2"));
		menu.crearSala(new Jugador("Pepe3"));

		assertEquals(3, menu.getSalas().size());

	}

}
