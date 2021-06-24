package interfazUsuario;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;

import Core.Casillero;
import Core.Jugador;
import Core.PosicionDomino;
import Core.Tablero;
import Core.Terreno;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class ColocarDominoStage extends Stage {

	private Jugador jugador;
	private PosicionDomino resultado;
	Tablero tablero;
	GridPane root;
	private List<Casillero> casillerosPosibles;

	public ColocarDominoStage(Jugador jugador) {
		this.jugador = jugador;
		inicializar();
	}

	private void inicializar() {
		setTitle("Turno de Colocar Domino: " + jugador.getNombre());
		root = new GridPane();
		inicializarTablero();

		Scene scene = new Scene(root, 450, 250);
		setScene(scene);
	}

	private void inicializarTablero() {
		tablero = jugador.getRey().getTablero();
		casillerosPosibles = tablero.getCasillerosPosibles(jugador.getDominoEnMano());
		root.setHgap(1);
		root.setVgap(1);
		for (int x = tablero.getXmin(); x <= tablero.getXmax(); x++) {
			for (int y = tablero.getYmin(); y <= tablero.getYmax(); y++) {
				Casillero casillero = tablero.getCasillero(x, y);
				if (casillero != null && !casillero.estaVacio() ) {

					ImageView vistaImagen = getImagenTerreno(casillero.getTerreno());
					root.add(vistaImagen, x, y);
					
				} else {
					Rectangle rect = new Rectangle(40, 40);
					final Casillero casilleroSeleccionado = new Casillero(x, y);
					PosicionDomino pos = new PosicionDomino(casilleroSeleccionado, tablero);

					if (pos.esValida() && casillerosPosibles.stream().anyMatch(r -> r.getX() == casilleroSeleccionado.getX() && r.getY() == casilleroSeleccionado.getY())) {
						ImageView vistaImagen ;
						if(!tablero.tieneAdyacentesDelTerreno(casilleroSeleccionado, jugador.getDominoEnMano().getTerrenoUno())) {
							pos.invertir();
							jugador.getDominoEnMano().invertir();
						}
						vistaImagen = getImagenTerreno(jugador.getDominoEnMano().getTerrenoUno());
						
						
						
						rect.setFill(Color.GREEN);
						rect.setOnMouseClicked(new EventHandler<MouseEvent>() {
							@Override
							public void handle(MouseEvent t) {
								close();
							}
						});
						rect.setOnMouseEntered(new EventHandler<MouseEvent>() {
							@Override
							public void handle(MouseEvent t) {
								resultado = pos;
								rect.setFill(new ImagePattern(vistaImagen.getImage()));
								System.out.println(pos);
							}
						});
						rect.setOnMouseExited(new EventHandler<MouseEvent>() {
							@Override
							public void handle(MouseEvent t) {
								rect.setFill(Color.GREEN);
							}
						});
					} else {
						rect.setFill(Color.GREY);
					}
					root.add(rect, x, y);
				}
			}
		}
	}

	private ImageView getImagenTerreno(Terreno terreno) {
		Image image1 = null;
		try {
			image1 = new Image(
					new FileInputStream("imagenes\\Terreno" + terreno.getTipoTerreno() + ".jpg"));
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
