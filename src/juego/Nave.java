package juego;

import java.awt.Image;
import entorno.Entorno;
import entorno.Herramientas;

public class Nave {

	// Variables de Instancia:

	private int x;
	private int y;
	private Image nave;

	/** Constructor del Objeto Nave: */

	public Nave(int x, int y) {
		this.x = x;
		this.y = y;
		this.nave = Herramientas.cargarImagen("nave.png");
	}

	/** Permite obtener el valor de x */

	public int getX() {
		return x;
	}

	/** Permite modificar el valor de x */

	public void setX(int x) {
		this.x = x;
	}

	/** Permite obtener el valor de y */

	public int getY() {
		return y;
	}

	/** Permite modificar el valor de y */

	public void setY(int y) {
		this.y = y;
	}

	/** Decrece la posición de la nave constantemente */

	private void bajarNave() {
		this.y = y + 1;
	}

	/** Imprime en pantalla la imagen de una nave */

	void imprimir(Entorno entorno) {
		entorno.dibujarImagen(nave, this.x, this.y, 0, 1);
		this.bajarNave();
	}

} // Cierre total de la Clase Nave
