package interfazUsuario;


import java.util.LinkedList;
import java.util.List;
import Core.Jugador;
import Core.Partida;
import Core.PosicionDomino;
import Core.Rey.Colores;
import Core.SectorBarajado;
import Util.EstrategiaUI;
import Util.ManagerEntrada;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class KingDominoUI extends Application implements AccionesDominoListener {
	public static void main(String[] args) {
		launch(args);
	}

	private List<Jugador> jugadores;
	private Partida partida;


	public void setUp() {
		Jugador jugador1 = new Jugador("Pepe");
		Jugador jugador2 = new Jugador("Moni");
		Jugador jugador3 = new Jugador("Fatiga");
		Jugador jugador4 = new Jugador("Coqui");
		jugadores = new LinkedList<Jugador>();
		jugadores.add(jugador1);
		jugadores.add(jugador2);
		jugadores.add(jugador3);
		jugadores.add(jugador4);
		jugador1.elegirRey(Colores.azul);
		jugador2.elegirRey(Colores.negro);
		jugador3.elegirRey(Colores.rosa);
		jugador4.elegirRey(Colores.verde);
		partida = new Partida(this.jugadores);
		EstrategiaUI estrategia = new EstrategiaUI();
		estrategia.addListener(this);
		ManagerEntrada.getInstancia().setEstrategia(estrategia);
	}

	@Override
	public void start(Stage primaryStage) {
		setUp();
		primaryStage.setTitle("Bienvenido a Kingdomino 2021!");
		Button btn = new Button();
		btn.setText("Empezar partida");
		btn.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				partida.iniciar();
			}
		});
		primaryStage.setOnCloseRequest(
                event -> System.exit(0)
        );

		StackPane root = new StackPane();
		root.getChildren().add(btn);
		primaryStage.setScene(new Scene(root, 500, 250));
		primaryStage.show();

	}

	@Override
	public int elegirDomino(SectorBarajado sb, Jugador jugador) {
		ElegirDominoStage stage = new ElegirDominoStage(sb, jugador, partida.getTurno(), partida.getJugadores());
		stage.initStyle(StageStyle.TRANSPARENT);
		int decision = stage.showAndReturn();

		return decision;
	}

	@Override
	public PosicionDomino elegirPosicionDomino(Jugador jugador) {
		ColocarDominoStage stage = new ColocarDominoStage(jugador, partida.getTurno());
		stage.initStyle(StageStyle.TRANSPARENT);
		return stage.showAndReturn();
	}

	@Override
	public void mostrarPuntaje(List<Jugador> tablaPuntaje) {
		MostrarPuntajeStage stage = new MostrarPuntajeStage(tablaPuntaje);
		stage.showAndWait();
		Platform.exit();
		System.exit(0);
	}
}
