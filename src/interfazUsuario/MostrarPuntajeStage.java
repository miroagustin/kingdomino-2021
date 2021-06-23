package interfazUsuario;

import java.util.List;

import Core.Jugador;
import Core.Puntaje;
import javafx.stage.Stage;

public class MostrarPuntajeStage extends Stage{
	private List<Jugador> tablaPuntaje;

	public MostrarPuntajeStage(List<Jugador> tablaPuntaje) {
			this.tablaPuntaje = tablaPuntaje;
			inicializar();
		
	}

	private void inicializar() {
		// TODO Auto-generated method stub
	}
}
