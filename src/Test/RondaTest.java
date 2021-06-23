package Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.LinkedList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Core.Domino;
import Core.Jugador;
import Core.Mazo;
import Core.PosicionDomino;
import Core.SectorBarajado;
import Util.EstrategiaAutomatica;
import Util.ManagerEntrada;
import Core.Rey.Colores;

public class RondaTest {

	private List<Jugador> jugadores;
	private SectorBarajado sectorBarajado;
	private Mazo mazo;
	private List<Domino> baraja;

	@BeforeEach
	public void setUp() throws Exception {
		Jugador jugador1 = new Jugador("Pepe");
		Jugador jugador2 = new Jugador("Moni");
		Jugador jugador3 = new Jugador("Fatiga");
		Jugador jugador4 = new Jugador("Coqui");
		jugadores = new LinkedList<Jugador>();
		jugadores.add(jugador1);
		jugadores.add(jugador2);
		jugadores.add(jugador3);
		jugadores.add(jugador4);
		jugador1.elegirRey(Colores.azul);
		jugador2.elegirRey(Colores.negro);
		jugador3.elegirRey(Colores.rosa);
		jugador4.elegirRey(Colores.verde);
		ManagerEntrada.getInstancia().setEstrategia(new EstrategiaAutomatica("Opciones.in", "Posiciones.in"));
	}

	@Test
	void rondasTest() throws Exception {
		mazo = new Mazo();
		int turnos = 0;
		while (mazo.tieneDominos() && turnos < 6) {
			baraja = mazo.barajarDomino();
			sectorBarajado = new SectorBarajado(baraja);
			iniciarRondaElegirDominoTest();
			inciarRondaColocarDominoTest();
			turnos++;
		}
		for (Jugador jugador : jugadores) {
			System.out.println("Tablero de " + jugador.getNombre());
			System.out.println(jugador.getRey().getTablero());
		}

	}

	public void iniciarRondaElegirDominoTest() throws Exception {
		int i = 0;
		for (Jugador jugador : jugadores) {
			List<Integer> opciones = sectorBarajado.mostrarOpciones();
			int opcionElejida = ManagerEntrada.getInstancia().obtenerSeleccionDomino(sectorBarajado,jugador);
			Domino dominoElegido = sectorBarajado.elegirDomino(opcionElejida, jugador.getRey());
			jugador.setDominoEnMano(dominoElegido);
			assertEquals(this.jugadores.get(i).getDominoEnMano(), baraja.get(i));
		}
		this.jugadores = sectorBarajado.ordenarJugadores();
	}

	public void inciarRondaColocarDominoTest() throws Exception {
		for (int i = 0; i < 4; i++) {
			PosicionDomino posicion = ManagerEntrada.getInstancia().obtenerPosicionDomino(jugadores.get(i));
			jugadores.get(i).colocarDomino(posicion);
			assertEquals(jugadores.get(i).getRey().getTablero()
					.getCasillero(posicion.getCasilleroUno().getX(), posicion.getCasilleroUno().getY()).getTerreno(),
					jugadores.get(i).getDominoEnMano().getTerrenoUno());
			assertEquals(jugadores.get(i).getRey().getTablero()
					.getCasillero(posicion.getCasilleroDos().getX(), posicion.getCasilleroDos().getY()).getTerreno(),
					jugadores.get(i).getDominoEnMano().getTerrenoDos());
		}
	}

}
