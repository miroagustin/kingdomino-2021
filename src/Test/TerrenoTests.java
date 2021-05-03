package Test;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

import Util.ManagerEntrada;

class TerrenoTests {

	@Test
	void traeTerrenosDisponibles() throws Exception {
		Assert.assertEquals(96, ManagerEntrada.obtenerTerrenosDisponibles().size());
	}

}
