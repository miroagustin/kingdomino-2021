package interfazUsuario;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Stream;

import Core.Casillero;
import Core.Domino;
import Core.PosicionDomino;
import Core.Tablero;
import Core.Terreno;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

public class TableroUI extends GridPane {

	private Tablero tablero;
	private Domino dominoEnMano;
	private List<Casillero> casillerosPosibles = new LinkedList<Casillero>();
	protected PosicionDomino seleccionado;
	private SeleccionListener listener;
	private Rectangle rectSeleccionado;

	public void rotarSeleccionadoDerecha() {
		if (seleccionado != null && rectSeleccionado != null) {
			getChildren().remove(rectSeleccionado);
			seleccionado.rotarDerecha();
			add(rectSeleccionado, seleccionado.getCasilleroDos().getX(), seleccionado.getCasilleroDos().getY());
		}	
	}

	public void rotarSeleccionadoIzquierda() {
		if (seleccionado != null && rectSeleccionado != null) {
			getChildren().remove(rectSeleccionado);
			seleccionado.rotarIzquierda();
			add(rectSeleccionado, seleccionado.getCasilleroDos().getX(), seleccionado.getCasilleroDos().getY());
		}
	}

	public TableroUI(Tablero tablero, Domino domino, SeleccionListener listener) {
		this.tablero = tablero;
		this.dominoEnMano = domino;
		this.listener = listener;
		inicializar();
	}
	public void setDominoSeleccionado(Domino d) {
		dominoEnMano = d;
		actualizar();
	}

	private void actualizar() {
		getChildren().clear();
		inicializar();
	}

	private void inicializar() {
		if (dominoEnMano != null)
			casillerosPosibles = tablero.getCasillerosPosibles(dominoEnMano);

		setHgap(1);
		setVgap(1);
		int minimoX = Math.max(0, tablero.getXmin() - 1);
		int minimoY = Math.max(0, tablero.getYmin() - 1);
		int maximoX = Math.min(9, tablero.getXmax() + 1);
		int maximoY = Math.min(9, tablero.getYmax() + 1);
		for (int x = minimoX; x <= maximoX; x++) {
			for (int y = minimoY; y <= maximoY; y++) {
				Casillero casillero = tablero.getCasillero(x, y);
				if (casillero != null && !casillero.estaVacio()) {
					// Posicion Ocupada
					ImageView vistaImagen = getImagenTerreno(casillero.getTerreno());
					add(vistaImagen, x, y);
				} else {
					// Posicion Posible
					Rectangle rect = new Rectangle(40, 40);
					final Casillero casilleroSeleccionado = new Casillero(x, y);
					PosicionDomino pos = new PosicionDomino(casilleroSeleccionado, tablero);

					if (pos.esValida() && dominoEnMano != null
							&& casillerosPosibles.stream().anyMatch(r -> r.getX() == casilleroSeleccionado.getX()
									&& r.getY() == casilleroSeleccionado.getY())) {
						ImageView vistaImagenTerrenoUno;
						ImageView vistaImagenTerrenoDos;
						Domino dominoCasillero = new Domino(dominoEnMano);
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
								listener.publicarSeleccion(dominoCasillero, seleccionado);
							}
						});
						rect.setOnMouseEntered(new EventHandler<MouseEvent>() {
							

							@Override
							public void handle(MouseEvent t) {
								seleccionado = pos;
								rect.setFill(new ImagePattern(vistaImagenTerrenoUno.getImage()));
								add(rectanguloDos, pos.getCasilleroDos().getX(), pos.getCasilleroDos().getY());
								rectSeleccionado = rectanguloDos;
							}
						});
						rect.setOnMouseExited(new EventHandler<MouseEvent>() {
							@Override
							public void handle(MouseEvent t) {
								rect.setFill(Color.GREEN);
								getChildren().remove(rectanguloDos);
								rectSeleccionado = null;
							}
						});

					} else {
						rect.setFill(Color.GREY);
					}
					add(rect, x, y);
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

}
