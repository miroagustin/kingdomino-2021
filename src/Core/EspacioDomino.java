package Core;

public class EspacioDomino {
	
	@Override
	public String toString() {
		return "EspacioDomino [domino=" + domino + ", rey=" + rey  + "]";
	}
	private Domino domino;
	private Rey rey;

	public EspacioDomino(Domino domino) {
		this.domino = domino;
	}
	public boolean estaVacio() {
		return rey == null;
	}
	public Rey getRey() {
		return rey;
	}
	public void colocarRey(Rey rey) {
		this.rey = rey;
	}
	public Domino getDomino() {
		return this.domino;
	}
	public Jugador getJugador() {
		if(rey == null) 
			return null;
		return rey.getJugador();
	}

}
