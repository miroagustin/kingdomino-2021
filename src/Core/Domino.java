package Core;

public class Domino {

	private Terreno parteDos;
	private Terreno parteUno;

	public Domino(Terreno parteUno, Terreno parteDos) {
		this.parteUno = parteUno;
		this.parteDos = parteDos;
	}

	public void moverDomino() {

	}

	@Override
	public String toString() {
		return "Domino\n [parteDos=" + parteDos + ", parteUno=" + parteUno + "]\n";
	}

}
