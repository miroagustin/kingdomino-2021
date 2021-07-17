package interfazUsuario;

import java.util.List;

import Core.Jugador;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class MostrarPuntajeStage extends Stage {
	private List<Jugador> tablaPuntaje;
	private TableroUI otroTablero;
	private Label labelTableroContrincante;

	public MostrarPuntajeStage(List<Jugador> tablaPuntaje) {
		this.tablaPuntaje = tablaPuntaje;
		inicializar();
	}

	private void inicializar() {
		StackPane root = new StackPane();
		root.setStyle("-fx-background-color: cornsilk;");
		root.setPadding(new Insets(10, 10, 10, 10));
		TablaJugadores table = new TablaJugadores(tablaPuntaje);
		setTitle("Tabla de puntuacion");
		Jugador ganador = tablaPuntaje.get(0);
		table.setMaxSize(200, 130);
		table.setTranslateX(0);
		table.setTranslateY(-200);

		final Label label = new Label(
				"El ganador es " + ganador.getNombre() + " con " + ganador.getPuntaje() + " puntos");
		label.setFont(new Font("Arial", 33));
		label.setTranslateX(0);
		label.setTranslateY(-300);

		Button boton1 = new Button("Ver otro tablero");

		boton1.setTranslateX(0);
		boton1.setTranslateY(310);

		boton1.setOnAction(new EventHandler<ActionEvent>() {
			int index = 0;

			@Override
			public void handle(ActionEvent event) {
				root.getChildren().removeAll(table, label, otroTablero, boton1, labelTableroContrincante);
				index++;
				if (index == 4)
					index = 0;

				otroTablero = new TableroUI(tablaPuntaje.get(index).getRey(), null, null);

				otroTablero.setScaleX(0.7);
				otroTablero.setScaleY(0.7);
				otroTablero.setTranslateX(800);
				otroTablero.setTranslateY(-90);
				labelTableroContrincante = new Label("Estas viendo el tablero de: "
						+ tablaPuntaje.get(index).getNombre() + "\nPuntaje: " + tablaPuntaje.get(index).getPuntaje());
				labelTableroContrincante.setFont(new Font("Arial", 18));
				labelTableroContrincante.setTranslateX(500);
				labelTableroContrincante.setTranslateY(200);

				root.getChildren().addAll(table, label, otroTablero, boton1, labelTableroContrincante);

			}
		});

		root.getChildren().addAll(table, label, boton1);
		setScene(new Scene(root, 1280, 720));
	}
}
