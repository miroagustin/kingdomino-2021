package interfazUsuario;

import java.util.LinkedList;
import java.util.List;
import Core.Casillero;
import Core.Domino;
import Core.PosicionDomino;
import Core.Tablero;
import javafx.event.EventHandler;
import javafx.scene.canvas.Canvas;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class TableroUI extends GridPane {

	private Tablero tablero;
	private Domino dominoEnMano;
	private List<Casillero> casillerosPosibles = new LinkedList<Casillero>();
	protected PosicionDomino seleccionado;
	private SeleccionListener listener;
	private final double medidaCasillero = 65.0f;
	protected Domino dominoSeleccionado;
	private StackPane casilleroPaneSeleccionado;
	protected DominoUI dominoUISeleccionado;

	public boolean puedeColocar() {
		return casillerosPosibles.size() > 0;
	}

	public void rotarSeleccionadoDerecha() {
		if (seleccionado != null && dominoUISeleccionado != null) {
			getChildren().remove(dominoUISeleccionado.getParteDos());
			seleccionado.rotarDerecha();
			add(dominoUISeleccionado.getParteDos(), seleccionado.getCasilleroDos().getX(),
					seleccionado.getCasilleroDos().getY());
		}
	}

	public void rotarSeleccionadoIzquierda() {
		if (seleccionado != null && dominoUISeleccionado != null) {
			getChildren().remove(dominoUISeleccionado.getParteDos());
			seleccionado.rotarIzquierda();
			add(dominoUISeleccionado.getParteDos(), seleccionado.getCasilleroDos().getX(),
					seleccionado.getCasilleroDos().getY());
		}
	}

	public void invertirSeleccionado() {
		if (seleccionado != null && dominoUISeleccionado != null && seleccionado.sePuedeInvertir(dominoSeleccionado)) {
			getChildren().remove(dominoUISeleccionado.getParteDos());
			casilleroPaneSeleccionado.getChildren().remove(dominoUISeleccionado.getParteUno());
			dominoSeleccionado.invertir();
			dominoUISeleccionado.invertir();
			casilleroPaneSeleccionado.getChildren().add(dominoUISeleccionado.getParteUno());
			add(dominoUISeleccionado.getParteDos(), seleccionado.getCasilleroDos().getX(),
					seleccionado.getCasilleroDos().getY());
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
					Canvas vistaImagen = new TerrenoUI(medidaCasillero, medidaCasillero, casillero.getTerreno());
					add(vistaImagen, x, y);
				} else {
					// Posicion Posible

					StackPane casilleroPane = new StackPane();
					Rectangle rect = new Rectangle(medidaCasillero, medidaCasillero);
					casilleroPane.getChildren().add(rect);
					final Casillero casilleroSeleccionado = new Casillero(x, y);
					PosicionDomino pos = new PosicionDomino(casilleroSeleccionado, tablero);
					
					if (dominoEnMano != null
							&& casillerosPosibles.stream().anyMatch(r -> r.getX() == casilleroSeleccionado.getX()
									&& r.getY() == casilleroSeleccionado.getY())) {
						
						Canvas vistaImagenTerrenoDos;
						Canvas vistaImagenTerrenoUno;
						Domino dominoCasillero = new Domino(dominoEnMano);
						if (!tablero.tieneAdyacentesDelTerreno(casilleroSeleccionado,
								dominoCasillero.getTerrenoUno())) {
							dominoCasillero.invertir();
						}
						vistaImagenTerrenoUno = new TerrenoUI(medidaCasillero, medidaCasillero,
								dominoCasillero.getTerrenoUno());
						vistaImagenTerrenoDos = new TerrenoUI(medidaCasillero, medidaCasillero,
								dominoCasillero.getTerrenoDos());
						DominoUI dominoUI = new DominoUI(vistaImagenTerrenoUno, vistaImagenTerrenoDos);

						rect.setFill(Color.GREEN);

						casilleroPane.setOnMouseClicked(new EventHandler<MouseEvent>() {
							@Override
							public void handle(MouseEvent t) {
								listener.publicarSeleccion(dominoCasillero, seleccionado);
							}
						});
						casilleroPane.setOnMouseEntered(new EventHandler<MouseEvent>() {

							@Override
							public void handle(MouseEvent t) {
								seleccionado = pos;
								casilleroPane.getChildren().remove(rect);
								casilleroPane.getChildren().add(dominoUI.getParteUno());
								add(dominoUI.getParteDos(), pos.getCasilleroDos().getX(), pos.getCasilleroDos().getY());
								dominoUISeleccionado = dominoUI;
								dominoSeleccionado = dominoCasillero;
								casilleroPaneSeleccionado = casilleroPane;
							}
						});
						casilleroPane.setOnMouseExited(new EventHandler<MouseEvent>() {
							@Override
							public void handle(MouseEvent t) {
								getChildren().remove(dominoUI.getParteDos());
								casilleroPane.getChildren().remove(dominoUI.getParteUno());
								casilleroPane.getChildren().add(rect);
								dominoUISeleccionado = null;
							}
						});
					} else {
						rect.setFill(Color.GREY);
					}
					add(casilleroPane, x, y);
				}
			}
		}

	}

	protected void invertirCanvas(Canvas vistaImagenTerrenoUno, Canvas vistaImagenTerrenoDos) {
		Canvas aux = vistaImagenTerrenoUno;
		vistaImagenTerrenoUno = vistaImagenTerrenoDos;
		vistaImagenTerrenoDos = aux;
	}

}
