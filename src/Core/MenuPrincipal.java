package Core;

import java.util.LinkedList;
import java.util.List;

public class MenuPrincipal {

	private List<Sala> salas;

	public MenuPrincipal() {
		this.salas = new LinkedList<Sala>();
	}

	public List<Sala> getSalas() {
		return salas;
	}

	public void crearSala(Jugador due�o) {
		Sala sala = new Sala(due�o);
		this.salas.add(sala);
	}

}
