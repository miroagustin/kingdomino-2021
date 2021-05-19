package Util;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;

import Core.Casillero;
import Core.PosicionDomino;
import Core.Terreno;
import Core.Terreno.TipoTerreno;

public class ManagerEntrada {
	private final static String nombreArchivoTerrenos = "terrenosDisponibles.csv";
	private Scanner scannerInput;
	private Queue<PosicionDomino> colaPosicionDomino = new LinkedList<PosicionDomino>();
	private static ManagerEntrada instancia;
	private EstrategiaEntrada estrategia;

	public void setEstrategia(EstrategiaEntrada estrategia) {
		this.estrategia = estrategia;
	}

	public static ManagerEntrada getInstancia() throws Exception {
		if(instancia == null)
			instancia = new ManagerEntrada();
		return instancia;
	}

	private boolean validarLineaTerreno(String[] linea) {
		if (linea.length != 3) {
			return false;
		}
		if (!linea[1].trim().matches("^[0-9]+$")) {
			return false;
		}
		if (!linea[2].trim().matches("^[0-9]+$")) {
			return false;
		}
		if(!existeTipoTerreno(linea[0]))
			return false;
		return true;
	}

	private boolean existeTipoTerreno(String linea) {
		for (TipoTerreno tipoTerreno : TipoTerreno.values()) {
			if(tipoTerreno.toString().equals(linea))
				return true;
		}
		return false;
	}

	public List<Terreno> obtenerTerrenosDisponibles() throws Exception {
		Scanner refarch = new Scanner(new File(nombreArchivoTerrenos));
		List<Terreno> resultado = new LinkedList<Terreno>();
		int contadorLinea = 0;
		// Me salteo la cabecera
		refarch.nextLine();
		while (refarch.hasNextLine()) {
			contadorLinea++;
			String linea = refarch.nextLine();
			String[] campos = linea.split(";");
			if (!validarLineaTerreno(campos)) {
				throw new Exception("Linea " + contadorLinea + " mal formada, revise. linea: " + linea);
			}
			for (int i = 0; i < Integer.parseInt(campos[2]); i++) {

				resultado.add(new Terreno(Enum.valueOf(TipoTerreno.class, campos[0]), Integer.parseInt(campos[1])));
			}

		}
		refarch.close();
		return resultado;
	}
// Para seleccionar domino, TODO: OTRA PARA SELECCIONAR LA POSICION DEL DOMINO
	public int obtenerSeleccionDomino(List<Integer> opciones) {
		/*
		String regexValidaOpcion = "^[0-3]$";
		
		String linea = scannerInput.nextLine();

		if(!linea.matches(regexValidaOpcion) || linea.matches(regexValidaOpcion) && !opciones.contains(Integer.parseInt(linea))) {
			System.out.println("Por favor ingrese una opcion valida." + opciones);
			return obtenerSeleccionDomino(opciones);
		}
		return Integer.parseInt(linea);*/
		return estrategia.obtenerSeleccionDomino(opciones);
	}
	public PosicionDomino obtenerPosicionDomino(List<PosicionDomino> opciones) {
		return estrategia.obtenerPosicionDomino(opciones);
		//return colaPosicionDomino.poll();
	}

	public void openInput() {
		scannerInput = new Scanner(System.in);
	}

	public void closeInput() {
		scannerInput.close();
	}

	public List<Integer> obtenerColaSeleccion(String nombreArchivoSeleccion) throws Exception {
		// TODO Auto-generated method stub
		Scanner refarch = new Scanner(new File(nombreArchivoSeleccion));
		List<Integer> resultado = new LinkedList<Integer>();
		int contadorLinea = 0;
		while (refarch.hasNextInt()) {
			contadorLinea++;
			int opcionJugador = refarch.nextInt();
			if (opcionJugador < 0 || opcionJugador > 3) {
				throw new Exception("Nro " + contadorLinea + " mal formada, revise. linea: " + opcionJugador);
			}
		}
		refarch.close();
		return resultado;
	}

	public List<PosicionDomino> obtenerColaPosicion(String nombreArchivoPosicion) throws Exception {
		Scanner refarch = new Scanner(new File(nombreArchivoPosicion));
		List<PosicionDomino> resultado = new LinkedList<PosicionDomino>();
		int contadorLinea = 0;
		while (refarch.hasNextInt()) {
			contadorLinea++;
			
			int posicionUnoX = refarch.nextInt();
			int posicionUnoY = refarch.nextInt();
			
			int posicionDosX = refarch.nextInt();
			int posicionDosY = refarch.nextInt();
			PosicionDomino posicionDomino = new PosicionDomino(new Casillero(posicionUnoX, posicionUnoY) , new Casillero(posicionDosX, posicionDosY));
			
			if (!posicionDomino.esValida()) {
				throw new Exception("Nro " + contadorLinea + " mal formada, revise. linea: " + posicionDomino);
			}
		}
		refarch.close();
		return resultado;
	}

}
