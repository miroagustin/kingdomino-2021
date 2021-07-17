package Util;

import java.util.List;
import java.util.Queue;

import Core.Jugador;
import Core.PosicionDomino;
import Core.SectorBarajado;

public class EstrategiaAutomatica implements EstrategiaEntrada {

	private Queue<Integer> colaSeleccion;
	private Queue<PosicionDomino> colaPosicion;

	@SuppressWarnings("unchecked")
	public EstrategiaAutomatica(String nombreArchivoSeleccion, String nombreArchivoPosicion) throws Exception {
		this.colaSeleccion = (Queue<Integer>) ManagerEntrada.getInstancia()
				.obtenerColaSeleccion(nombreArchivoSeleccion);
		this.colaPosicion = (Queue<PosicionDomino>) ManagerEntrada.getInstancia()
				.obtenerColaPosicion(nombreArchivoPosicion);
	}

	@Override
	public PosicionDomino obtenerPosicionDomino(Jugador jugador) {
		return colaPosicion.poll();
	}

	@Override
	public int obtenerSeleccionDomino(SectorBarajado sb, Jugador jugador) {
		return colaSeleccion.poll();
	}

	@Override
	public void mostrarPuntaje(List<Jugador> p) {
	}

}
