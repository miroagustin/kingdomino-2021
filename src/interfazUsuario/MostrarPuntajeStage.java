package interfazUsuario;

import java.util.List;

import Core.Jugador;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.VBox;
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
		TableView<Jugador> table = new TableView<Jugador>();
		setTitle("Tabla de puntuacion");
		setWidth(300);
		setHeight(500);
		Jugador ganador = tablaPuntaje.get(0);

		final Label label = new Label(
				"El ganador es " + ganador.getNombre() + "con " + ganador.getPuntaje() + "puntos");
		label.setFont(new Font("Arial", 20));

		table.setEditable(true);

		TableColumn nombre = new TableColumn("Nombre");
		TableColumn color = new TableColumn("Color");
		TableColumn puntaje = new TableColumn("Puntaje");

		table.getColumns().addAll(nombre, color, puntaje);
		table.getItems().addAll(tablaPuntaje);

		final VBox vbox = new VBox();
		vbox.setSpacing(5);
		vbox.setPadding(new Insets(10, 0, 0, 10));
		vbox.getChildren().addAll(label, table);
		((Group) scene.getRoot()).getChildren().addAll(vbox);
		setScene(scene);
	}
}
