package Core;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

class TerrenoTests {

	@Test
	void traeTerrenosDisponibles() throws Exception {
		Assert.assertEquals(16,Terreno.obtenerTerrenosDisponibles().size());
	}

}
