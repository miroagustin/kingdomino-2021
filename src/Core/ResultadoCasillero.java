package Core;

public class ResultadoCasillero {

	private int totalCasillero;
	private int totalCoronas;

	public ResultadoCasillero(int totalCasillero, int totalCoronas) {
		this.totalCasillero = totalCasillero;
		this.totalCoronas = totalCoronas;
	}

	public int getTotalCasillero() {
		return totalCasillero;
	}

	public int getTotalCoronas() {
		return totalCoronas;
	}
	public int getTotal() {
		return totalCoronas * totalCasillero;
	}

}
