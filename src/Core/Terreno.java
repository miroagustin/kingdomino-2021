package Core;

public class Terreno {
	@Override
	public String toString() {
		return "Terreno [coronas=" + coronas + ", tipoTerreno=" + tipoTerreno + ", cantTerreno=" + getCantTerreno()
				+ "]\n";
	}

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

	public Terreno() {
		this.cantTerreno = 0;
		this.coronas = 0;
		this.tipoTerreno = "vacio";
	}

	public int getCantTerreno() {
		return cantTerreno;
	}

	public boolean equals(Terreno other) {
		if (other == null)
			return false;
		if (this.tipoTerreno == "comodin")
			return true;
		if (other.tipoTerreno == "comodin")
			return true;
		return this.tipoTerreno == other.tipoTerreno && this.coronas == other.coronas;
	}

	public String getTipo() {
		return this.tipoTerreno;
	}

}
