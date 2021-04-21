package Core;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import junit.framework.Assert;

class MazoTests {

	@Test
	void generarMazoTest() throws Exception {
		Mazo mazo = new Mazo();
		assertEquals(48, mazo.getDominos().size());
	}

}
