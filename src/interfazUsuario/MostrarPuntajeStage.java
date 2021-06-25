package interfazUsuario;

import java.util.List;

import Core.Jugador;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
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
		// horrible
		Scene scene = new Scene(new Group());
		TableView<Jugador> table = new TableView<Jugador>();
		setTitle("Tabla de puntuacion");
		setWidth(300);
		setHeight(500);
		Jugador ganador = tablaPuntaje.get(0);

		final Label label = new Label(
				"El ganador es " + ganador.getNombre() + " con " + ganador.getPuntaje() + " puntos");
		label.setFont(new Font("Arial", 20));

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
		((Group) scene.getRoot()).getChildren().addAll(label, table);
		setScene(scene);
	}
}
