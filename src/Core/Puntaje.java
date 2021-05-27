package Core;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Puntaje {

	private Map<Integer, Grupo> grupos = new HashMap<Integer, Grupo>();
	private Integer idGrupoIncremental = 1;
	private Tablero tablero;

	public Puntaje(Tablero tablero) {
		this.tablero = tablero;
	}

	public void agregar(Domino domino, PosicionDomino posicionDomino) {
		Casillero casilleroUno = posicionDomino.getCasilleroUno();
		Casillero casilleroDos = posicionDomino.getCasilleroDos();
		actualizarGrupos(casilleroUno);
		actualizarGrupos(casilleroDos);
	}

	private void actualizarGrupos(Casillero casillero) {
		List<Casillero> casillerosAdyacentes = new CasillerosAdyacentes(casillero, tablero).obtenerAdyacentesValidos();
		Set<Integer> idGrupos = new HashSet<Integer>();
		for (Casillero adyacente : casillerosAdyacentes) {
			if (!adyacente.esComodin())
				idGrupos.add(adyacente.getGrupo());
		}
		casillero.setGrupo(idGrupoIncremental);
		Grupo grupoNuevo = new Grupo(idGrupoIncremental, casillero);
		grupos.put(idGrupoIncremental, grupoNuevo);
		for (int idGrupo : idGrupos) {
			grupoNuevo.mergear(grupos.get(idGrupo));
			grupos.remove(idGrupo);
		}
		idGrupoIncremental++;
	}

	public int calcularPuntaje() {
		int total = 0;
		for (Grupo grupo : grupos.values()) {
			total += grupo.getPuntaje();
		}
		return total;
	}

}
