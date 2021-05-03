package Core;

public class Terreno {
	@Override
	public String toString() {
		return "Terreno [coronas=" + coronas + ", tipoTerreno=" + tipoTerreno + "]\n";
	}

	private int coronas;
	private TipoTerreno tipoTerreno;
	public enum TipoTerreno{
		comodin,vacio,desierto,llanura,agua,pantano,bosque,mina
	}

	Terreno(Terreno fuente) {
		this.tipoTerreno = fuente.tipoTerreno;
		this.coronas = fuente.coronas;
	}

	public Terreno() {
		this.coronas = 0;
		this.tipoTerreno = TipoTerreno.vacio;
	}

	public Terreno(TipoTerreno tipo, int coronas) {
		this.tipoTerreno = tipo;
		this.coronas = coronas;
	}

	public boolean equals(Terreno other) {
		if (other == null)
			return false;
		if (this.tipoTerreno == TipoTerreno.comodin || other.tipoTerreno == TipoTerreno.comodin)
			return true;
		return this.tipoTerreno == other.tipoTerreno && this.coronas == other.coronas;
	}

	public TipoTerreno getTipoTerreno() {
		return this.tipoTerreno;
	}

}
