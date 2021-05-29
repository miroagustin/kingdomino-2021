package Util;

import java.util.List;
import java.util.Queue;

import Core.PosicionDomino;

public class EstrategiaAutomatica implements EstrategiaEntrada {
	
	private Queue<Integer> colaSeleccion;
	private Queue<PosicionDomino> colaPosicion;

	@SuppressWarnings("unchecked")
	public EstrategiaAutomatica(String nombreArchivoSeleccion,String nombreArchivoPosicion) throws Exception {
		this.colaSeleccion = (Queue<Integer>) ManagerEntrada.getInstancia().obtenerColaSeleccion(nombreArchivoSeleccion);
		this.colaPosicion = (Queue<PosicionDomino>) ManagerEntrada.getInstancia().obtenerColaPosicion(nombreArchivoPosicion);
	}

	@Override
	public int obtenerSeleccionDomino(List<Integer> opciones) {
		return colaSeleccion.poll();
	}

	@Override
	public PosicionDomino obtenerPosicionDomino() {
		// TODO validar posiciones
		return colaPosicion.poll();
	}
}
