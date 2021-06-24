package Test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import Core.*;

class DominoTest {

	@Test
	@DisplayName("Construccion del domino")
	void partesCorrectas() {
		Domino domino = new Domino (new Terreno(Terreno.TipoTerreno.trigo, 0), new Terreno(Terreno.TipoTerreno.agua,0));
		
		assertTrue(domino.getTerrenoUno().equalsTipoTerreno(new Terreno(Terreno.TipoTerreno.trigo,0)));
		
		assertTrue(domino.getTerrenoDos().equalsTipoTerreno(new Terreno(Terreno.TipoTerreno.agua,0)));
	}
	
	@Test
	@DisplayName("Ubicacion del domino")
	void ubicacion() {
		Domino domino = new Domino (new Terreno(Terreno.TipoTerreno.trigo, 0), new Terreno(Terreno.TipoTerreno.agua,0));
		
		domino.setPosicion(new PosicionDomino (new Casillero(5,5), new Casillero(5,6)));
		
		assertTrue(domino.getPosicion().equals(new PosicionDomino (new Casillero(5,5), new Casillero(5,6))));
		
		
	}

	@Test
	@DisplayName("Rotar Domino")
	void rotar() {
		
	}
}
