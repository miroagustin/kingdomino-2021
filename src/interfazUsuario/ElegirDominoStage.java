package interfazUsuario;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;

import Core.Jugador;
import Core.SectorBarajado;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class ElegirDominoStage extends Stage {
	private SectorBarajado sector;
	private Jugador jugador;
	TableroUI tablero;
	private int resultado;

	public ElegirDominoStage(SectorBarajado sb, Jugador jugador) {
		this.sector = sb;
		this.jugador = jugador;
		inicializar();
	}

	private void inicializar() {
		List<Integer> opcionesDomino = sector.mostrarOpciones();
		try {
			setTitle("Turno de elegir domino: " + jugador.getNombre());
			StackPane root = new StackPane();
			GridPane grid = new GridPane();
			tablero = new TableroUI(jugador.getRey().getTablero(), null, null);
			tablero.setAlignment(Pos.BASELINE_CENTER);
			grid.setAlignment(Pos.BOTTOM_CENTER);
			grid.setHgap(5);
			grid.setVgap(25);
			root.getChildren().add(tablero);
			root.getChildren().add(grid);
			for (int i = 0; i < 4; i++) {
				if (opcionesDomino.contains(i)) {
					final int nroEspacioDomino = i;
					Image imagenDomino = new Image(
							new FileInputStream("imagenes\\" + sector.getNumeroDomino(i) + ".jpg"));
					ImageView vistaImagen = new ImageView(imagenDomino);
					vistaImagen.setFitWidth(150.0f);
					vistaImagen.setFitHeight(100.0f);
					vistaImagen.setOnMouseClicked(new EventHandler<MouseEvent>() {
						@Override
						public void handle(MouseEvent t) {
							resultado = nroEspacioDomino;
							close();
						}
					});
					vistaImagen.setOnMouseEntered(new EventHandler<MouseEvent>() {
						@Override
						public void handle(MouseEvent t) {
							tablero.setDominoSeleccionado(sector.getDomino(nroEspacioDomino));
						}
					});
					vistaImagen.setOnMouseExited(new EventHandler<MouseEvent>() {
						@Override
						public void handle(MouseEvent t) {
							tablero.setDominoSeleccionado(null);
						}
					});
					grid.add(vistaImagen, i, 0);
				}
			}

			setScene(new Scene(root, 600, 600));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	public int showAndReturn() {
		super.showAndWait();
		return resultado;
	}
}
