package interfazUsuario;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.List;

import Core.Casillero;
import Core.Domino;
import Core.Jugador;
import Core.PosicionDomino;
import Core.Tablero;
import Core.Terreno;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class ColocarDominoStage extends Stage implements SeleccionListener {

	private Jugador jugador;
	private PosicionDomino resultado;
	Tablero tablero;
	TableroUI grid;
	StackPane root;

	public ColocarDominoStage(Jugador jugador) {
		this.jugador = jugador;
		this.tablero = jugador.getRey().getTablero();
		inicializar();
	}

	private void inicializar() {
		setTitle("Turno de Colocar Domino: " + jugador.getNombre());
		root = new StackPane();

		grid = new TableroUI(tablero, jugador.getDominoEnMano(), this);
		grid.setAlignment(Pos.CENTER);
		grid.setStyle("-fx-background-color: cornsilk;");
		root.getChildren().add(grid);

		Scene scene = new Scene(root, 500, 500);
		scene.setOnKeyReleased(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent event) {
				switch (event.getCode()) {
				case Q:
					grid.rotarSeleccionadoIzquierda();
					break;
				case E:
					grid.rotarSeleccionadoDerecha();
					break;
				default:
					break;
				}
			}
		});
		setScene(scene);
	}



	public PosicionDomino showAndReturn() {
		super.showAndWait();

		return resultado;
	}

	@Override
	public void publicarSeleccion(Domino domino, PosicionDomino pos) {
		jugador.setDominoEnMano(domino);
		resultado = pos;
		close();
	}

}
