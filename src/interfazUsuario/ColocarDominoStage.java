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

public class ColocarDominoStage extends Stage {

	private Jugador jugador;
	private PosicionDomino resultado;
	Tablero tablero;
	GridPane grid;
	StackPane root;
	private List<Casillero> casillerosPosibles = new LinkedList<Casillero>();

	public ColocarDominoStage(Jugador jugador) {
		this.jugador = jugador;
		inicializar();
	}

	private void inicializar() {
		setTitle("Turno de Colocar Domino: " + jugador.getNombre());
		root = new StackPane();

		grid = new GridPane();
		grid.setAlignment(Pos.CENTER);
		grid.setStyle("-fx-background-color: cornsilk;");
		root.getChildren().add(grid);
		inicializarTablero();

		Scene scene = new Scene(root, 500, 500);
		scene.setOnKeyReleased(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent event) {
				if (resultado == null)
					return;
				switch (event.getCode()) {
				case Q:
					resultado.rotarIzquierda();
					break;
				case E:
					resultado.rotarDerecha();
					break;
				default:
					break;
				}
			}
		});
		setScene(scene);
	}

	private void inicializarTablero() {
		tablero = jugador.getRey().getTablero();
		if (jugador.getDominoEnMano() != null)
			casillerosPosibles = tablero.getCasillerosPosibles(jugador.getDominoEnMano());
		
		grid.setHgap(1);
		grid.setVgap(1);
		int minimoX = Math.max(0, tablero.getXmin() - 1);
		int minimoY = Math.max(0, tablero.getYmin() - 1);
		for (int x = minimoX; x <= tablero.getXmax() + 1; x++) {
			for (int y = minimoY; y <= tablero.getYmax() + 1; y++) {
				Casillero casillero = tablero.getCasillero(x, y);
				if (casillero != null && !casillero.estaVacio()) {
					// Posicion Ocupada
					ImageView vistaImagen = getImagenTerreno(casillero.getTerreno());
					grid.add(vistaImagen, x, y);
				} else {
					// Posicion Posible
					Rectangle rect = new Rectangle(40, 40);
					final Casillero casilleroSeleccionado = new Casillero(x, y);
					PosicionDomino pos = new PosicionDomino(casilleroSeleccionado, tablero);

					if (pos.esValida()
							&& casillerosPosibles.stream().anyMatch(r -> r.getX() == casilleroSeleccionado.getX()
									&& r.getY() == casilleroSeleccionado.getY())) {
						ImageView vistaImagenTerrenoUno;
						ImageView vistaImagenTerrenoDos;
						Domino dominoCasillero = new Domino(jugador.getDominoEnMano());
						if (!tablero.tieneAdyacentesDelTerreno(casilleroSeleccionado,
								dominoCasillero.getTerrenoUno())) {
							dominoCasillero.invertir();
						}
						vistaImagenTerrenoUno = getImagenTerreno(dominoCasillero.getTerrenoUno());
						vistaImagenTerrenoDos = getImagenTerreno(dominoCasillero.getTerrenoDos());
						Rectangle rectanguloDos = new Rectangle(40, 40,
								new ImagePattern(vistaImagenTerrenoDos.getImage()));
						rect.setFill(Color.GREEN);
						rect.setOnMouseClicked(new EventHandler<MouseEvent>() {
							@Override
							public void handle(MouseEvent t) {
								jugador.setDominoEnMano(dominoCasillero);
								close();
							}
						});
						rect.setOnMouseEntered(new EventHandler<MouseEvent>() {
							@Override
							public void handle(MouseEvent t) {
								resultado = pos;
								rect.setFill(new ImagePattern(vistaImagenTerrenoUno.getImage()));
								grid.add(rectanguloDos, pos.getCasilleroDos().getX(), pos.getCasilleroDos().getY());
							}
						});
						rect.setOnMouseExited(new EventHandler<MouseEvent>() {
							@Override
							public void handle(MouseEvent t) {
								rect.setFill(Color.GREEN);
								grid.getChildren().remove(rectanguloDos);
							}
						});

					} else {
						rect.setFill(Color.GREY);
					}
					grid.add(rect, x, y);
				}
			}
		}
	}

	private ImageView getImagenTerreno(Terreno terreno) {
		Image image1 = null;
		try {
			image1 = new Image(new FileInputStream("imagenes\\Terreno" + terreno.getTipoTerreno() + ".jpg"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		ImageView vistaImagen = new ImageView(image1);
		vistaImagen.setFitWidth(40.0f);
		vistaImagen.setFitHeight(40.0f);

		return vistaImagen;
	}

	public PosicionDomino showAndReturn() {
		super.showAndWait();

		return resultado;
	}

}
