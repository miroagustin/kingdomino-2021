package Util;

import java.io.File;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import Core.Casillero;
import Core.Domino;
import Core.PosicionDomino;
import Core.Terreno;
import Core.Terreno.TipoTerreno;

public class ManagerEntrada {
	private final static String nombreArchivoTerrenos = "terrenosDisponibles.csv";
	private Scanner scannerInput;
	private static ManagerEntrada instancia;
	private EstrategiaEntrada estrategia;

	public void setEstrategia(EstrategiaEntrada estrategia) {
		this.estrategia = estrategia;
	}

	public static ManagerEntrada getInstancia() throws Exception {
		if (instancia == null)
			instancia = new ManagerEntrada();
		return instancia;
	}

	private boolean validarLineaTerreno(String[] linea) {
		if (linea.length != 3) {
			return false;
		}
		if (!linea[1].trim().matches("^[0-3]$")) {
			return false;
		}
		if (!linea[2].trim().matches("^[0-9]+$")) {
			return false;
		}
		if (!existeTipoTerreno(linea[0]))
			return false;
		return true;
	}

	private boolean existeTipoTerreno(String linea) {
		for (TipoTerreno tipoTerreno : TipoTerreno.values()) {
			if (tipoTerreno.toString().equals(linea))
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

	private boolean validarLineaDomino(String[] campos) {
		if (campos.length != 4) {
			return false;
		}
		if (!existeTipoTerreno(campos[0]))
			return false;
		if (!campos[1].trim().matches("^[0-3]$")) {
			return false;
		}
		if (!existeTipoTerreno(campos[2]))
			return false;
		if (!campos[3].trim().matches("^[0-3]$")) {
			return false;
		}
		return true;
	}

	public int obtenerSeleccionDomino(List<Integer> opciones) {
		/*
		 * String regexValidaOpcion = "^[0-3]$";
		 * 
		 * String linea = scannerInput.nextLine();
		 * 
		 * if(!linea.matches(regexValidaOpcion) || linea.matches(regexValidaOpcion) &&
		 * !opciones.contains(Integer.parseInt(linea))) {
		 * System.out.println("Por favor ingrese una opcion valida." + opciones); return
		 * obtenerSeleccionDomino(opciones); } return Integer.parseInt(linea);
		 */
		return estrategia.obtenerSeleccionDomino(opciones);
	}

	public PosicionDomino obtenerPosicionDomino() {
		return estrategia.obtenerPosicionDomino();
	}

	public void openInput() {
		scannerInput = new Scanner(System.in);
	}

	public void closeInput() {
		scannerInput.close();
	}

	public List<Integer> obtenerColaSeleccion(String nombreArchivoSeleccion) throws Exception {
		Scanner refarch = new Scanner(new File(nombreArchivoSeleccion));
		List<Integer> resultado = new LinkedList<Integer>();
		int contadorLinea = 0;
		while (refarch.hasNextInt()) {
			contadorLinea++;
			int opcionJugador = refarch.nextInt();
			if (opcionJugador < 0 || opcionJugador > 3) {
				throw new Exception("Nro " + contadorLinea + " mal formada, revise. linea: " + opcionJugador);
			} else
				resultado.add(opcionJugador);
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
			PosicionDomino posicionDomino = new PosicionDomino(new Casillero(posicionUnoX, posicionUnoY),
					new Casillero(posicionDosX, posicionDosY));

			if (!posicionDomino.esValida()) {
				throw new Exception("Nro " + contadorLinea + " mal formada, revise. linea: " + posicionDomino);
			} else
				resultado.add(posicionDomino);
		}
		refarch.close();
		return resultado;
	}

	public List<Domino> obtenerMazoOriginal() throws Exception {
		Scanner refarch = new Scanner(new File("MazoOriginal.in"));
		List<Domino> resultado = new LinkedList<Domino>();
		int contadorLinea = 0;
		// Me salteo la cabecera
		refarch.nextLine();
		while (refarch.hasNextLine()) {
			contadorLinea++;
			String linea = refarch.nextLine();
			String[] campos = linea.split(";");
			if (!validarLineaDomino(campos)) {
				throw new Exception("Linea " + contadorLinea + " mal formada, revise. linea: " + linea);
			}
			Terreno parteUno = new Terreno(Enum.valueOf(TipoTerreno.class, campos[0]), Integer.parseInt(campos[1]));
			Terreno parteDos = new Terreno(Enum.valueOf(TipoTerreno.class, campos[2]), Integer.parseInt(campos[3]));
			resultado.add(new Domino(parteUno, parteDos, contadorLinea));

		}
		refarch.close();
		return resultado;
	}

}
