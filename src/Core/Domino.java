package Core;

public class Domino {

	private Terreno parteDos;
	private Terreno parteUno;
	private int nroDomino;

	public Domino(Terreno parteUno, Terreno parteDos, int nroDomino) {
		this.parteUno = parteUno;
		this.parteDos = parteDos;
		this.nroDomino = nroDomino;
	}

	public void moverDomino() {

	}

	@Override
	public String toString() {
		return "Domino\n [parteDos=" + parteDos + ", parteUno=" + parteUno + "]\n";
	}

	public Terreno getTerrenoUno() {
		
		return this.parteUno;
	}
	public Terreno getTerrenoDos() {
		// TODO Auto-generated method stub
		return this.parteDos;
	}

}
