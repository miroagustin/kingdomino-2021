package Test;

import static org.junit.Assert.*;

import org.junit.jupiter.api.Test;

import Core.Terreno;
import Core.Terreno.TipoTerreno;
import Util.ManagerEntrada;

class TerrenoTests {

	@Test
	void traeTerrenosDisponibles() throws Exception {
		assertEquals(96, ManagerEntrada.getInstancia().obtenerTerrenosDisponibles().size());
	}

	@Test
	void equalsVacioTest() {
		Terreno terrenoUno = new Terreno();
		Terreno terrenoDos = new Terreno();
		assertFalse(terrenoUno.equalsTipoTerreno(terrenoDos));
	}

	@Test
	void equalsComodinTest() {
		Terreno terrenoUno = new Terreno(TipoTerreno.trigo, 0);
		Terreno terrenoDos = new Terreno(TipoTerreno.comodin, 0);
		assertTrue(terrenoUno.equalsTipoTerreno(terrenoDos));
	}

	@Test
	void equalsTipoTerrenoTest() {
		Terreno terrenoUno = new Terreno(TipoTerreno.trigo, 0);
		Terreno terrenoDos = new Terreno(TipoTerreno.trigo, 0);
		assertTrue(terrenoUno.equalsTipoTerreno(terrenoDos));
	}

	@Test
	void notEqualsTipoTerrenoTest() {
		Terreno terrenoUno = new Terreno(TipoTerreno.trigo, 0);
		Terreno terrenoDos = new Terreno(TipoTerreno.agua, 0);
		assertFalse(terrenoUno.equalsTipoTerreno(terrenoDos));
	}

}
