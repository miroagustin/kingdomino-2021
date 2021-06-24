package interfazUsuario;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import Core.Jugador;
import Core.SectorBarajado;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class ElegirDominoStage extends Stage {
	private SectorBarajado sector;
	private Jugador jugador;
	private int resultado;

	public ElegirDominoStage(SectorBarajado sb, Jugador jugador) throws FileNotFoundException {
		this.sector = sb;
		this.jugador = jugador;
		inicializar();
	}

	private void inicializar() throws FileNotFoundException {
		setTitle("Turno de elegir domino: " + jugador.getNombre());
		GridPane root = new GridPane();
		Image image1 = new Image(new FileInputStream("imagenes\\" + sector.getNumeroDomino(0) + ".jpg"));
		ImageView vistaImagen1 = new ImageView(image1);
		vistaImagen1.setX(150.0f);
		vistaImagen1.setY(75.0f);
		vistaImagen1.setFitWidth(150.0f);
		vistaImagen1.setFitHeight(100.0f);
		vistaImagen1.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent t) {
				resultado = 0;
				close();
			}
		});
		Image image2 = new Image(new FileInputStream("imagenes\\" + sector.getNumeroDomino(1) + ".jpg"));
		ImageView vistaImagen2 = new ImageView(image2);
		vistaImagen2.setX(150.0f);
		vistaImagen2.setY(75.0f);
		vistaImagen2.setFitWidth(150.0f);
		vistaImagen2.setFitHeight(100.0f);
		vistaImagen2.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent t) {
				resultado = 1;
				close();
			}
		});
		Image image3 = new Image(new FileInputStream("imagenes\\" + sector.getNumeroDomino(2) + ".jpg"));
		ImageView vistaImagen3 = new ImageView(image3);
		vistaImagen3.setX(150.0f);
		vistaImagen3.setY(75.0f);
		vistaImagen3.setFitWidth(150.0f);
		vistaImagen3.setFitHeight(100.0f);
		vistaImagen3.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent t) {
				resultado = 2;
				close();
			}
		});
		Image image4 = new Image(new FileInputStream("imagenes\\" + sector.getNumeroDomino(3) + ".jpg"));
		ImageView vistaImagen4 = new ImageView(image4);
		vistaImagen4.setX(150.0f);
		vistaImagen4.setY(75.0f);
		vistaImagen4.setFitWidth(150.0f);
		vistaImagen4.setFitHeight(100.0f);
		vistaImagen4.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent t) {
				resultado = 3;
				close();
			}
		});
		root.setHgap(10);
		root.setVgap(25);
		if (sector.mostrarOpciones().contains(0))
			root.add(vistaImagen1, 1, 2);
		if (sector.mostrarOpciones().contains(1))
			root.add(vistaImagen2, 2, 2);
		if (sector.mostrarOpciones().contains(2))
			root.add(vistaImagen3, 3, 2);
		if (sector.mostrarOpciones().contains(3))
			root.add(vistaImagen4, 4, 2);
		setScene(new Scene(root, 450, 250));
	}

	public int showAndReturn() {
		super.showAndWait();
		return resultado;
	}
}
