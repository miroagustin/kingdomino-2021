package Core;

public class Domino implements Comparable<Domino>{

	private Terreno parteDos;
	private Terreno parteUno;
	private int nroDomino;
	private PosicionDomino posicion;

	public Domino(Terreno parteUno, Terreno parteDos, int nroDomino) {
		this.parteUno = parteUno;
		this.parteDos = parteDos;
		this.nroDomino = nroDomino;
	}

	public Domino(Terreno parteUno2, Terreno parteDos2) {
		this.parteUno = parteUno2;
		this.parteDos = parteDos2;
		int valor = (parteUno2.getCoronas()+parteDos2.getCoronas());
		if(parteUno2.equals(parteDos2))
			valor *= 2;
		this.nroDomino = valor;
	}

	@Override
	public String toString() {
		return "Domino\n [parteDos=" + parteDos + ", parteUno=" + parteUno + "]\n";
	}

	public Terreno getTerrenoUno() {
		return this.parteUno;
	}
	public Terreno getTerrenoDos() {
		return this.parteDos;
	}

	@Override
	public int compareTo(Domino o) {
		return Integer.compare(this.nroDomino, o.nroDomino);
	}

	public void setNumeroDomino(int nroDomino) {
		this.nroDomino = nroDomino;
	}
	

}
