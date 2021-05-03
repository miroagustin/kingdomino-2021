package Test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import Core.Mazo;
import Core.SectorBarajado;
import junit.framework.Assert;

class SectorBarajadoTest {

	@Test
	void obtenerEspaciosDisponibles() throws Exception {
		Mazo mazo = new Mazo();
		SectorBarajado sb = new SectorBarajado(mazo.barajarDomino());
		assertEquals(sb.mostrarOpciones().size(), 4);
		
	}

}
