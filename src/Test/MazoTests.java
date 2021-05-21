package Test;

import java.util.List;

import org.junit.Assert;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import Core.Domino;
import Core.Mazo;
@DisplayName("Mazo")
class MazoTests {

	@DisplayName("Generar Mazo")
	@Test
	void generarMazoTest() throws Exception {
		Mazo mazo = new Mazo();
		System.out.println(mazo.getDominos());
		Assert.assertEquals(48, mazo.getDominos().size());
	}
	@DisplayName("Barajar Domino")
	@Test
	void barajarDominoTest() throws Exception {
		Mazo mazo = new Mazo();
		List<Domino> baraja = mazo.barajarDomino();
		Assert.assertEquals(4, baraja.size());
	}
	
	@DisplayName("Baraja Ordenada de menor a mayor")
	@Test
	void barajaOrdenadaTest() throws Exception {
		Mazo mazo = new Mazo();
		List<Domino> baraja = mazo.barajarDomino();
		for (int i = 0; i < baraja.size()-1; i++) {
			Assert.assertTrue(baraja.get(i).compareTo(baraja.get(i+1))<0);
		}
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
