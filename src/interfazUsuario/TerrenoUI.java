package interfazUsuario;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import Core.Terreno;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class TerrenoUI extends Canvas {
	private Terreno terreno;

	public TerrenoUI(double x, double y, Terreno terreno) {
		super(x, y);
		this.terreno = terreno;
		inicializar();
	}

	private void inicializar() {
		Image imageTerreno = null;
		Image imageCoronas = null;
		GraphicsContext gc = getGraphicsContext2D();
		try {
			imageTerreno = new Image(new FileInputStream("imagenes\\Terreno" + terreno.getTipoTerreno() + ".jpg"),
					getWidth(), getHeight(), false, false);
			imageCoronas = new Image(new FileInputStream("imagenes\\corona-" + terreno.getCoronas() + ".png"),
					getWidth(), getHeight(), false, false);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		gc.drawImage(imageTerreno, 0, 0);
		gc.drawImage(imageCoronas, 0, 0);
	}
}
