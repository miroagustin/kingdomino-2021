package interfazUsuario;

import java.util.List;

import Core.Domino;
import Core.Jugador;
import Core.SectorBarajado;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
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
	private TableroUI otroTablero;
	private Label labelTableroContrincante;

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
		root.setStyle("-fx-background-color: cornsilk;");
		root.setPadding(new Insets(10, 10, 10, 10));
		Label labelRonda = new Label(
				"Turno nro: " + turno + "\nJugador: " + jugador.getNombre() + "\nPuntaje: " + jugador.getPuntaje());
		labelRonda.setFont(new Font("Arial", 22));
		labelRonda.setTranslateX(-500);
		labelRonda.setTranslateY(-300);
		GridPane sectorBarajado = new GridPane();
		tablero = new TableroUI(jugador.getRey(), null, null);
		tablero.setAlignment(Pos.BASELINE_CENTER);

		TablaJugadores tablaPuntajes = new TablaJugadores(puntajes);
		tablaPuntajes.setMaxSize(200, 130);
		tablaPuntajes.setTranslateX(-500);
		tablaPuntajes.setTranslateY(-100);

		sectorBarajado.setAlignment(Pos.BOTTOM_CENTER);
		sectorBarajado.setHgap(5);
		sectorBarajado.setVgap(25);
		Button boton1 = new Button("Ver otro tablero");
		boton1.setTranslateX(500);
		boton1.setTranslateY(300);
		boton1.setOnAction(new EventHandler<ActionEvent>() {
			int index = puntajes.indexOf(jugador);

			@Override
			public void handle(ActionEvent event) {
				root.getChildren().removeAll(otroTablero, tablero, sectorBarajado, labelRonda, tablaPuntajes, boton1,
						labelTableroContrincante);
				index++;
				if (index == 4)
					index = 0;
				if(index == puntajes.indexOf(jugador)) {
					index++;
					if(index == 4) {
						index = 0;
					}
				}
					

				otroTablero = new TableroUI(puntajes.get(index).getRey(), null, null);

				otroTablero.setScaleX(0.4);
				otroTablero.setScaleY(0.4);
				otroTablero.setTranslateX(600);
				otroTablero.setTranslateY(-130);
				labelTableroContrincante = new Label("Estas viendo el tablero de: " + puntajes.get(index).getNombre()
						+ "\nPuntaje: " + puntajes.get(index).getPuntaje());
				labelTableroContrincante.setFont(new Font("Arial", 18));
				labelTableroContrincante.setTranslateX(500);
				labelTableroContrincante.setTranslateY(200);

				root.getChildren().addAll(otroTablero, tablero, sectorBarajado, labelRonda, tablaPuntajes, boton1,
						labelTableroContrincante);
				
			}
		});

		root.getChildren().addAll(tablero, sectorBarajado, labelRonda, tablaPuntajes, boton1);
		for (int i = 0; i < 4; i++) {
			if (opcionesDomino.contains(i)) {
				final int nroEspacioDomino = i;
				Domino dominoEspacio = sector.getDomino(nroEspacioDomino);
				GridPane domino = new GridPane();
				TerrenoUI terrenoUno = new TerrenoUI(100, 100, dominoEspacio.getTerrenoUno(),jugador.getRey());
				TerrenoUI terrenoDos = new TerrenoUI(100, 100, dominoEspacio.getTerrenoDos(),jugador.getRey());
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
