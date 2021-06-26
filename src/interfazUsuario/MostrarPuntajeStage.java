package interfazUsuario;

import java.util.List;

import Core.Jugador;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class MostrarPuntajeStage extends Stage {
	private List<Jugador> tablaPuntaje;

	public MostrarPuntajeStage(List<Jugador> tablaPuntaje) {
		this.tablaPuntaje = tablaPuntaje;
		inicializar();
	}

	private void inicializar() {
		Scene scene = new Scene(new Group());
		TablaJugadores table = new TablaJugadores(tablaPuntaje);
		setTitle("Tabla de puntuacion");
		setWidth(600);
		setHeight(250);
		Jugador ganador = tablaPuntaje.get(0);
		table.setTranslateY(50);
		table.setTranslateX((600-250)/2);
		

		final Label label = new Label(
				"El ganador es " + ganador.getNombre() + " con " + ganador.getPuntaje() + " puntos");
		label.setFont(new Font("Arial", 33));
		label.setAlignment(Pos.CENTER);

		((Group) scene.getRoot()).getChildren().addAll(table, label);
		setScene(scene);
	}
}
