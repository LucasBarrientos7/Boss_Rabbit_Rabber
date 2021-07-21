package juego;

import java.awt.Image;

import entorno.Entorno;
import entorno.Herramientas;

public class Disparo {

	// Variables de Instancia:
	
	private double x;
	private double y;
	private Image disparo;

	/** Constructor del Objeto Alimento: */

	Disparo(double x, double y) {
		this.x = x;
		this.y = y;
		this.disparo = Herramientas.cargarImagen("kamehameha.png");
	}

	/** Permite obtener el valor de x */

	public double getX() {
		return x;
	}

	/** Permite modificar el valor de x */
	
	public void setX(double x) {
		this.x = x;
	}

	/** Permite obtener el valor de y */

	public double getY() {
		return y;
	}
	
	/** Permite modificar el valor de y */
	
	public void setY(double y) {
		this.y = y;
	}

	/** Mueve el disparo hacia arriba, disminuyendo la y */

	private void moverse() {
		this.y -= 10;
	}
	
	/** Imprime en pantalla la imagen de un disparo */

	void disparar(Entorno entorno) {
		entorno.dibujarImagen(disparo, x, y-60, 0, 0.06);
		moverse();
	}

} // Cierre total de la Clase Disparo
