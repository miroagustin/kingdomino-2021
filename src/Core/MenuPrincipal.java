package Core;

import java.util.ArrayList;

public class MenuPrincipal {

	private ArrayList<Sala> salas;

	public MenuPrincipal() {
		this.setSalas(new ArrayList<Sala>());
	}

	public ArrayList<Sala> getSalas() {
		return salas;
	}

	public void setSalas(ArrayList<Sala> salas) {
		this.salas = salas;
	}

	public void setSala(Sala sala) {
		this.salas.add(sala);
	}

}
