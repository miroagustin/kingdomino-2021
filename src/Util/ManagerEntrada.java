package Util;

import java.io.File;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import Core.Terreno;
import Core.Terreno.TipoTerreno;

public class ManagerEntrada {
	private final static String nombreArchivoTerrenos = "terrenosDisponibles.csv";
	private static Scanner scannerInput;

	private static boolean validarLineaTerreno(String[] linea) {
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

	private static boolean existeTipoTerreno(String linea) {
		for (TipoTerreno tipoTerreno : TipoTerreno.values()) {
			if(tipoTerreno.toString().equals(linea))
				return true;
		}
		return false;
	}

	public static List<Terreno> obtenerTerrenosDisponibles() throws Exception {
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

	public static int obtenerOpcionJugador(List<Integer> opciones) {
		String regexValidaOpcion = "^[0-3]$";
		
		String linea = scannerInput.nextLine();

		if(!linea.matches(regexValidaOpcion) || linea.matches(regexValidaOpcion) && !opciones.contains(Integer.parseInt(linea))) {
			System.out.println("Por favor ingrese una opcion valida." + opciones);
			return obtenerOpcionJugador(opciones);
		}
		return Integer.parseInt(linea);
	}

	public static void openInput() {
		scannerInput = new Scanner(System.in);
		
	}

	public static void closeInput() {
		scannerInput.close();
		
	}

}
