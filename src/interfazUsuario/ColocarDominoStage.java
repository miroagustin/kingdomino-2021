package interfazUsuario;

import Core.Domino;
import Core.Jugador;
import Core.PosicionDomino;
import Core.Tablero;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class ColocarDominoStage extends Stage implements SeleccionListener {

	private Jugador jugador;
	private PosicionDomino resultado;
	Tablero tablero;
	TableroUI grid;
	StackPane root;
	private int turno;

	public ColocarDominoStage(Jugador jugador, int turno) {
		this.jugador = jugador;
		this.tablero = jugador.getRey().getTablero();
		this.turno = turno;
		inicializar();
	}

	private void inicializar() {
		setTitle("Turno " + turno + " de Colocar Domino: " + jugador.getNombre());
		root = new StackPane();
		Label labelRonda = new Label("Turno nro: " + turno + "\nJugador: " + jugador.getNombre() + "\nPuntaje: " + jugador.getPuntaje());
		labelRonda.setFont(new Font("Arial", 22));
		labelRonda.setTranslateX(-500);
		labelRonda.setTranslateY(-300);

		grid = new TableroUI(tablero, jugador.getDominoEnMano(), this);
		grid.setAlignment(Pos.CENTER);
		grid.setStyle("-fx-background-color: cornsilk;");
		root.getChildren().addAll(grid,labelRonda);

		Scene scene = new Scene(root, 1280, 720);
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
				case W:
					grid.invertirSeleccionado();
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
