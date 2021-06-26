package interfazUsuario;

import java.util.List;

import Core.Jugador;
import Core.Puntaje;
import Core.Rey;
import javafx.beans.binding.Bindings;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class TablaJugadores extends TableView<Jugador> {

	public TablaJugadores(List<Jugador> tablaPuntaje) {

		this.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		this.setFixedCellSize(25);
		this.prefHeightProperty().bind(this.fixedCellSizeProperty().multiply(Bindings.size(this.getItems()).add(1.01)));
		this.minHeightProperty().bind(this.prefHeightProperty());
		this.maxHeightProperty().bind(this.prefHeightProperty());

		this.setEditable(true);

		TableColumn<Jugador, String> nombre = new TableColumn<Jugador, String>("Nombre");
		TableColumn<Jugador, Rey> color = new TableColumn<Jugador, Rey>("Color");
		TableColumn<Jugador, Puntaje> puntaje = new TableColumn<Jugador, Puntaje>("Puntaje");

		this.getColumns().addAll(nombre, color, puntaje);

		nombre.setCellValueFactory(new PropertyValueFactory<Jugador, String>("nombre"));
		color.setCellValueFactory(new PropertyValueFactory<Jugador, Rey>("rey"));
		puntaje.setCellValueFactory(new PropertyValueFactory<Jugador, Puntaje>("puntaje"));

		this.getItems().addAll(tablaPuntaje);
		this.getStylesheets().add("estilotabla.css");
	}

}
