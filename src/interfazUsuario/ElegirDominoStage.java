package interfazUsuario;

import java.util.List;

import Core.Domino;
import Core.Jugador;
import Core.SectorBarajado;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class ElegirDominoStage extends Stage {
	private SectorBarajado sector;
	private Jugador jugador;
	TableroUI tablero;
	private int resultado;
	private int turno;

	public ElegirDominoStage(SectorBarajado sb, Jugador jugador, int turno) {
		this.sector = sb;
		this.jugador = jugador;
		this.turno = turno;
		inicializar();
	}

	private void inicializar() {
		List<Integer> opcionesDomino = sector.mostrarOpciones();
		setTitle("Turno " + turno + " de elegir domino: " + jugador.getNombre());
		StackPane root = new StackPane();
		root.setPadding(new Insets(10, 10, 10, 10));
		GridPane grid = new GridPane();
		tablero = new TableroUI(jugador.getRey().getTablero(), null, null);
		tablero.setAlignment(Pos.BASELINE_CENTER);
		tablero.setStyle("-fx-background-color: cornsilk;");

		grid.setAlignment(Pos.BOTTOM_CENTER);
		grid.setHgap(5);
		grid.setVgap(25);
		root.getChildren().add(tablero);
		root.getChildren().add(grid);
		for (int i = 0; i < 4; i++) {
			if (opcionesDomino.contains(i)) {
				final int nroEspacioDomino = i;
				Domino dominoEspacio = sector.getDomino(nroEspacioDomino);
				GridPane domino = new GridPane();
				TerrenoUI terrenoUno = new TerrenoUI(100, 100, dominoEspacio.getTerrenoUno());
				TerrenoUI terrenoDos = new TerrenoUI(100, 100, dominoEspacio.getTerrenoDos());
				domino.add(terrenoUno, 0, 0);
				domino.add(terrenoDos, 1, 0);
				domino.setHgap(0); 
				domino.setVgap(0);

				domino.setOnMouseClicked(new EventHandler<MouseEvent>() {
					@Override
					public void handle(MouseEvent t) {
						resultado = nroEspacioDomino;
						close();
					}
				});
				domino.setOnMouseEntered(new EventHandler<MouseEvent>() {
					@Override
					public void handle(MouseEvent t) {

						tablero.setDominoSeleccionado(dominoEspacio);
					}
				});
				domino.setOnMouseExited(new EventHandler<MouseEvent>() {
					@Override
					public void handle(MouseEvent t) {
						tablero.setDominoSeleccionado(null);
					}
				});

				grid.add(domino, i, 0);
			}
		}

		setScene(new Scene(root, 1280, 720));
	}

	public int showAndReturn() {
		super.showAndWait();
		return resultado;
	}
}
