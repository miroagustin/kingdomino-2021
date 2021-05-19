package Core;

public class PosicionDomino {
	Casillero casilleroUno;
	Casillero casilleroDos;
	public PosicionDomino(Casillero casilleroUno, Casillero casilleroDos) {
		this.casilleroUno = casilleroUno;
		this.casilleroDos = casilleroDos;
	}
	public Casillero getCasilleroUno() {
		// TODO Auto-generated method stub
		return casilleroUno;
	}
	public Casillero getCasilleroDos() {
		// TODO Auto-generated method stub
		return casilleroDos;
	}
	public boolean esValida() {
		// TODO Auto-generated method stub
		return casilleroUno.esAdyacente(casilleroDos);
	}

}
