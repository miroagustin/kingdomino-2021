package interfazUsuario;

import Core.Jugador;
import Core.SectorBarajado;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class ElegirDominoStage extends Stage {
	private SectorBarajado sector;
	private Jugador jugador;
	private int resultado;

	public ElegirDominoStage(SectorBarajado sb, Jugador jugador) {
		this.sector = sb;
		this.jugador = jugador;
		inicializar();
	}

	private void inicializar() {
		setTitle("Turno de: " + jugador.getNombre());
		GridPane root = new GridPane();
		Rectangle rectangle1 = new Rectangle();
		rectangle1.setX(150.0f);
		rectangle1.setY(75.0f);
		rectangle1.setWidth(100.0f);
		rectangle1.setHeight(150.0f);
		rectangle1.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent t) {
				rectangle1.setFill(Color.RED);
				resultado = 0;
				close();
			}
		});
		Rectangle rectangle2 = new Rectangle();
		rectangle2.setX(100.0f);
		rectangle2.setY(75.0f);
		rectangle2.setWidth(100.0f);
		rectangle2.setHeight(150.0f);
		rectangle2.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent t) {
				rectangle2.setFill(Color.RED);
				resultado = 1;
				close();
			}
		});
		Rectangle rectangle3 = new Rectangle();
		rectangle3.setX(200.0f);
		rectangle3.setY(75.0f);
		rectangle3.setWidth(100.0f);
		rectangle3.setHeight(150.0f);
		rectangle3.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent t) {
				rectangle3.setFill(Color.RED);
				resultado = 2;
				close();
			}
		});
		Rectangle rectangle4 = new Rectangle();
		rectangle4.setX(50.0f);
		rectangle4.setY(75.0f);
		rectangle4.setWidth(100.0f);
		rectangle4.setHeight(150.0f);
		rectangle4.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent t) {
				rectangle4.setFill(Color.RED);
				resultado = 3;
				close();
			}
		});
		root.setHgap(10);
		root.setVgap(25);
		if (sector.mostrarOpciones().contains(0))
			root.add(rectangle1, 1, 2);
		if (sector.mostrarOpciones().contains(1))
			root.add(rectangle2, 2, 2);
		if (sector.mostrarOpciones().contains(2))
			root.add(rectangle3, 3, 2);
		if (sector.mostrarOpciones().contains(3))
			root.add(rectangle4, 4, 2);
		setScene(new Scene(root, 450, 250));
	}

	public int showAndReturn() {
		super.showAndWait();
		return resultado;
	}
}
