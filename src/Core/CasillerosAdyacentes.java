package Core;

import java.util.LinkedList;
import java.util.List;

public class CasillerosAdyacentes {

	private List<Casillero> adyacentes = new LinkedList<Casillero>();

	public CasillerosAdyacentes(Casillero casillero, Tablero tablero) {
		if (!tablero.casilleroFueraDeRango(casillero.getX(), casillero.getY() - 1)) {
			adyacentes.add(tablero.getOcrearCasilleroVacio(casillero.getX(), casillero.getY() - 1));
		}
		if (!tablero.casilleroFueraDeRango(casillero.getX() - 1, casillero.getY())) {
			adyacentes.add(tablero.getOcrearCasilleroVacio(casillero.getX() - 1, casillero.getY()));
		}
		if (!tablero.casilleroFueraDeRango(casillero.getX() + 1, casillero.getY())) {
			adyacentes.add(tablero.getOcrearCasilleroVacio(casillero.getX() + 1, casillero.getY()));
		}
		if (!tablero.casilleroFueraDeRango(casillero.getX(), casillero.getY() + 1)) {
			adyacentes.add(tablero.getOcrearCasilleroVacio(casillero.getX(), casillero.getY() + 1));
		}
	}

	public List<Casillero> obtenerAdyacentesValidos(Casillero casilleroReferencia) {
		List<Casillero> adyacentesValidos = new LinkedList<Casillero>();
		for (Casillero casillero : adyacentes) {
			if (casilleroReferencia.equalTerreno(casillero))
				adyacentesValidos.add(casillero);
		}
		return adyacentesValidos;
	}

	public List<Casillero> obtenerAdyacentesVacios() {
		List<Casillero> adyacentesVacios = new LinkedList<Casillero>();
		
		for (Casillero casillero : adyacentes) {
			if(casillero != null && casillero.estaVacio())
				adyacentesVacios.add(casillero);			
		}
		return adyacentesVacios;
	}

}
