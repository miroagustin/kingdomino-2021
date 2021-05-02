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

	public void crearSala(Jugador dueño) {
		Sala sala = new Sala(dueño);
		this.salas.add(sala);
	}

}
