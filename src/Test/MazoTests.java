package Test;

import java.util.List;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

import Core.Domino;
import Core.Mazo;

class MazoTests {

	@Test
	void generarMazoTest() throws Exception {
		Mazo mazo = new Mazo();
		System.out.println(mazo.getDominos());
		Assert.assertEquals(48, mazo.getDominos().size());
	}

	@Test
	void barajarDominoTest() throws Exception {
		Mazo mazo = new Mazo();
		List<Domino> baraja = mazo.barajarDomino();
		Assert.assertEquals(4, baraja.size());

	}

	@Test
	void tieneDominosTest() throws Exception {
		Mazo mazo = new Mazo();
		while (mazo.tieneDominos()) {
			mazo.barajarDomino();
		}
		Assert.assertEquals(0, mazo.getDominos().size());
	}

}
