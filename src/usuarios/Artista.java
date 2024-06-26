package usuarios;

import java.util.ArrayList;
import java.util.*; 
import fabrica.*;
import piezas.*;
import modelo.*;
import exceptions.*;

public class Artista extends Usuario{
	
	private static final String TIPO = "Artista";

	private ArrayList<Pieza> piezasCreadas;
	
	public Artista(String login, String password, String nombre, String telefono, ArrayList<Pieza> piezasCreadas) {
		super(login, password, nombre, telefono);
		this.piezasCreadas = piezasCreadas;
	}

	@Override
	public String getTipo() {
		return TIPO;
	}

	public ArrayList<Pieza> getPiezasCreadas()
	{
		return this.piezasCreadas;
	}
	
	public void añadirPieza(Pieza pieza)
	{
		this.piezasCreadas.add(pieza);
	}
}

