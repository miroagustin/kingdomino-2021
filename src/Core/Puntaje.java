package Core;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Puntaje implements Comparable<Puntaje> {

	private Map<Integer, Grupo> grupos = new HashMap<Integer, Grupo>();
	private Integer idGrupoIncremental = 1;
	private int puntaje;

	public void agregar(Domino domino, Casillero casilleroUno, Casillero casilleroDos) {
		actualizarGrupos(casilleroUno);
		actualizarGrupos(casilleroDos);
		calcularPuntaje();
	}

	private void actualizarGrupos(Casillero casillero) {
		Set<Integer> idGrupos = new HashSet<Integer>();
		for (Casillero adyacente : casillero.obtenerAdyacentesValidos()) {
			if (!adyacente.esComodin())
				idGrupos.add(adyacente.getGrupo());
		}
		casillero.setGrupo(idGrupoIncremental);
		Grupo grupoNuevo = new Grupo(idGrupoIncremental, casillero);
		grupos.put(idGrupoIncremental, grupoNuevo);
		for (int idGrupo : idGrupos) {
			if (idGrupo != -1) {
				grupoNuevo.mergear(grupos.get(idGrupo));
				grupos.remove(idGrupo);
			}
		}
		idGrupoIncremental++;
	}

	private void calcularPuntaje() {
		puntaje = 0;
		for (Grupo grupo : grupos.values()) {
			puntaje += grupo.getPuntaje();
		}
	}

	public int getPuntaje() {
		return puntaje;
	}

	@Override
	public int compareTo(Puntaje other) {
		return Integer.compare(puntaje, other.puntaje);
	}

}
