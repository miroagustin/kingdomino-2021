package interfazUsuario;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import Core.Casillero;
import Core.Jugador;
import Core.PosicionDomino;
import Core.Tablero;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class ColocarDominoStage extends Stage {

	private Jugador jugador;
	private PosicionDomino resultado;
	Tablero tablero;
	GridPane root;

	public ColocarDominoStage(Jugador jugador) {
		this.jugador = jugador;
		inicializar();
	}

	private void inicializar() {
		setTitle("Turno de Colocar Domino: " + jugador.getNombre());
		root = new GridPane();
		inicializarTablero();
		root.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent t) {
				close();
			}
		});
		Scene scene = new Scene(root, 450, 250);
		setScene(scene);
	}

	private void inicializarTablero() {
		tablero = jugador.getRey().getTablero();
		root.setHgap(1);
		root.setVgap(1);
		for (int x = tablero.getXmin(); x <= tablero.getXmax(); x++) {
			for (int y = tablero.getYmin(); y <= tablero.getYmax(); y++) {
				root.add(new Rectangle(40, 40, Color.CORNFLOWERBLUE), x, y);
				Casillero casillero = tablero.getCasillero(x, y);
				if (casillero != null && !casillero.estaVacio()) {
					Image image1 = null;
					try {
						image1 = new Image(new FileInputStream("imagenes\\Terreno"
								+ tablero.getCasillero(x, y).getTerreno().getTipoTerreno() + ".jpg"));
					} catch (FileNotFoundException e) {
						e.printStackTrace();
					}
					ImageView vistaImagen1 = new ImageView(image1);

					vistaImagen1.setFitWidth(40.0f);
					vistaImagen1.setFitHeight(40.0f);
					root.add(vistaImagen1, x, y);
				}
			}
		}
	}

	public PosicionDomino showAndReturn() {
		super.showAndWait();
		Casillero c = tablero.getCasillerosPosibles(jugador.getDominoEnMano()).get(0);
		return new PosicionDomino(c);
	}

}
