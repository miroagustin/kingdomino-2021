package Test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import Core.Jugador;
import Core.Mazo;
import Core.Rey;
import Core.SectorBarajado;
import Core.Rey.Colores;

class SectorBarajadoTest {

	@Test
	void obtenerEspaciosDisponibles() throws Exception {
		Mazo mazo = new Mazo();
		SectorBarajado sb = new SectorBarajado(mazo.barajarDomino());
		assertEquals(4, sb.mostrarOpciones().size());
	}
	
	@Test
	void colocarRey() throws Exception {
		Mazo mazo = new Mazo();
		SectorBarajado sb = new SectorBarajado(mazo.barajarDomino());		
		sb.elegirDomino(0, new Rey(Colores.azul,new Jugador("Miguel")));
		assertEquals(3,sb.mostrarOpciones().size());
	}

}
