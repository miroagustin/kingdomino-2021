package interfazUsuario;

import javafx.scene.canvas.Canvas;

public class DominoUI {
	Canvas parteUno;
	Canvas parteDos;

	public DominoUI(Canvas parteUno, Canvas parteDos) {
		this.parteUno = parteUno;
		this.parteDos = parteDos;
	}

	public Canvas getParteUno() {
		return parteUno;
	}

	public Canvas getParteDos() {
		return parteDos;
	}

	public void invertir() {
		Canvas aux = parteUno;
		parteUno = parteDos;
		parteDos = aux;
	}
}
