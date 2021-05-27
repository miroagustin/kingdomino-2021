package Core;

import java.util.LinkedList;
import java.util.List;

public class Grupo {
	private int id;
	private List<Casillero> casilleros = new LinkedList<Casillero>();
	private int puntaje;
	private int coronas;

	public Grupo(List<Casillero> floodFill) {
		casilleros = floodFill;
	}

	public Grupo(int idGrupoIncremental, Casillero casilleroConTerreno) {
		agregar(casilleroConTerreno);
		this.id = idGrupoIncremental;
		
	}

	public int getId() {
		return this.id;
	}

	public void agregar(Casillero casillero) {
		casilleros.add(casillero);
		puntaje = (coronas += casillero.getTerreno().getCoronas()) * casilleros.size();
	}

	public void mergear(Grupo otro) {
		for (Casillero casillero : otro.casilleros) {
			this.agregar(casillero);
			casillero.setGrupo(id);
		}
	}

	public int getPuntaje() {
		return puntaje;
	}

}
