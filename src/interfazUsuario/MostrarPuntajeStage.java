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
		// horrible

		Scene scene = new Scene(new Group());
		TablaJugadores table = new TablaJugadores(tablaPuntaje);
		setTitle("Tabla de puntuacion");
		setWidth(600);
		setHeight(200);
		Jugador ganador = tablaPuntaje.get(0);

		final Label label = new Label(
				"El ganador es " + ganador.getNombre() + " con " + ganador.getPuntaje() + " puntos");
		label.setFont(new Font("Arial", 33));
		label.setAlignment(Pos.CENTER);

		table.setEditable(true);

		TableColumn<Jugador,String> nombre = new TableColumn<Jugador,String>("Nombre");
		TableColumn<Jugador,String> color = new TableColumn<Jugador,String>("Color");
		TableColumn<Jugador,String> puntaje = new TableColumn<Jugador,String>("Puntaje");

		table.getColumns().addAll(nombre, color, puntaje);
		
		
		nombre.setCellValueFactory(
			    new PropertyValueFactory<Jugador,String>("nombre")
			);
		color.setCellValueFactory(
			    new PropertyValueFactory<Jugador,String>("rey")
			);
		puntaje.setCellValueFactory(
			    new PropertyValueFactory<Jugador,String>("puntaje")
			);
		//table.setItems((ObservableList<Jugador>) tablaPuntaje);
		//((Group) scene.getRoot()).getChildren().addAll(table);
		((Group) scene.getRoot()).getChildren().addAll(label);
		setScene(scene);
	}
}
