package interfazUsuario;

import java.util.List;

import Core.Domino;
import Core.Jugador;
import Core.SectorBarajado;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class ElegirDominoStage extends Stage {
	private SectorBarajado sector;
	private Jugador jugador;
	TableroUI tablero;
	private int resultado;
	private int turno;
	private List<Jugador> puntajes;

	public ElegirDominoStage(SectorBarajado sb, Jugador jugador, int turno, List<Jugador> puntajes) {
		this.sector = sb;
		this.jugador = jugador;
		this.turno = turno;
		this.puntajes = puntajes;
		inicializar();
	}

	private void inicializar() {
		List<Integer> opcionesDomino = sector.mostrarOpciones();
		setTitle("Turno " + turno + " de elegir domino: " + jugador.getNombre());
		StackPane root = new StackPane();
		root.setPadding(new Insets(10, 10, 10, 10));
		Label labelRonda = new Label("Turno nro: " + turno + "\nJugador: " + jugador.getNombre() + "\nPuntaje: " + jugador.getPuntaje());
		labelRonda.setFont(new Font("Arial", 22));
		labelRonda.setTranslateX(-500);
		labelRonda.setTranslateY(-300);
		GridPane sectorBarajado = new GridPane();
		tablero = new TableroUI(jugador.getRey().getTablero(), null, null);
		tablero.setAlignment(Pos.BASELINE_CENTER);
		tablero.setStyle("-fx-background-color: cornsilk;");
		TablaJugadores tablaPuntajes = new TablaJugadores(puntajes);
		tablaPuntajes.setMaxSize(200, 130);
		tablaPuntajes.setTranslateX(-500);
		tablaPuntajes.setTranslateY(-100);

		sectorBarajado.setAlignment(Pos.BOTTOM_CENTER);
		sectorBarajado.setHgap(5);
		sectorBarajado.setVgap(25);
		root.getChildren().addAll(tablero, sectorBarajado, labelRonda,tablaPuntajes);
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

				sectorBarajado.add(domino, i, 0);
			}
		}
		setScene(new Scene(root, 1280, 720));
	}

	public int showAndReturn() {
		super.showAndWait();
		return resultado;
	}
}
