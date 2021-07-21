package juego;

import java.awt.Image;
import entorno.Entorno;

public class Calle {

	// Variables de Instancia:
	
	private int x;
	private int y;
	private Image calle;

	/** Constructor del Objeto Calle: */

	public Calle(int x, int y) {
		this.x = x;
		this.y = y;
		this.calle = entorno.Herramientas.cargarImagen("calle.png");
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
		this.y = y-1;
	}

	/** Decrece la posición de la calle constantemente */

	private void bajarCalle() {
		this.y = y + 1;
	}

	/** Dibuja en Pantalla la imagen de la calle */

	void imprimir(Entorno entorno) {
		entorno.dibujarImagen(calle, x + 5, y, 0, 1.0);
		this.bajarCalle();
	}
	
	/** Si la calle llega al final de la pantalla, ubica la calle en el principio */

	void reaparecerCalle() {
		if (this.y > 750) {
			this.y=-150;
		}
	}

} // Cierre total de la Clase Calle
