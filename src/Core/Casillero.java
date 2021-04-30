package Core;

public class Casillero {
	
	private Terreno terreno;
	
	public boolean estaVacio() {
		if(terreno.equals(null))
			return false;
		return true;
	}

}
