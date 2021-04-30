package Core;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

public class Terreno {
	@Override
	public String toString() {
		return "Terreno [coronas=" + coronas + ", tipoTerreno=" + tipoTerreno + ", cantTerreno=" + cantTerreno + "]\n";
	}

	private final static String nombreArchivoTerrenos = "terrenosDisponibles.csv";
	private int coronas;
	private String tipoTerreno;
	private int cantTerreno;

	Terreno(String[] linea) {
		this.tipoTerreno = linea[0].trim();
		this.coronas = Integer.parseInt(linea[1].trim());
		this.cantTerreno = Integer.parseInt(linea[2].trim());
	}

	Terreno(Terreno fuente) {
		this.tipoTerreno = fuente.tipoTerreno;
		this.coronas = fuente.coronas;
		this.cantTerreno = 1;
	}

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
		return true;
	}

	public static ArrayList<Terreno> obtenerTerrenosDisponibles() throws Exception {
		Scanner refarch = new Scanner(new File(nombreArchivoTerrenos));
		ArrayList<Terreno> resultado = new ArrayList<Terreno>();
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
			Terreno terrenoBase = new Terreno(campos);
			for (int i = 0; i < terrenoBase.cantTerreno; i++) {
				resultado.add(new Terreno(terrenoBase));
			}

		}
		refarch.close();
		return resultado;
	}
}
