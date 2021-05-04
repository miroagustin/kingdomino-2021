package Core;

public class Terreno {
	@Override
	public String toString() {
		return "Terreno [coronas=" + coronas + ", tipoTerreno=" + tipoTerreno + "]\n";
	}
	public String toShortString() {
		// TODO Auto-generated method stub
		return tipoTerreno.toString().substring(0, 2).toUpperCase() + coronas;
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

	public boolean equalsTipoTerreno(Terreno other) {
		if (other == null)
			return false;
		if(this.tipoTerreno == TipoTerreno.vacio || other.tipoTerreno == TipoTerreno.vacio)
			return false;
		if (this.tipoTerreno == TipoTerreno.comodin || other.tipoTerreno == TipoTerreno.comodin)
			return true;
		return this.tipoTerreno == other.tipoTerreno;
	}

	public TipoTerreno getTipoTerreno() {
		return this.tipoTerreno;
	}

	public int getCoronas() {
		// TODO Auto-generated method stub
		return this.coronas;
	}


}
