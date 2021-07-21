package juego;

import java.awt.Image;
import entorno.Entorno;
import entorno.Herramientas;

public class Lobo {

	// Variables de Instancia:

	private int x;
	private int y;
	private Image lobo;

	/** Constructor del Objeto Lobo: */

	public Lobo(int x, int y) {
		this.x = x;
		this.y = y;
		this.lobo = Herramientas.cargarImagen("lobo.gif");
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

	/** Mueve al lobo hacia la derecha, disminuyendo la x e incrementando la y */

	private void moverse() {
		this.x -= 0.3;
		this.y += 1;
	}

	/** Dibuja en Pantalla la imagen del lobo */

	void imprimir(Entorno entorno) {
		entorno.dibujarImagen(lobo, x, y, 0, 0.7);
		this.moverse();
	}

	/** Si el lobo llega al final de la pantalla, lo ubica en el principio */

	void reaparecerLobo(Calle calle) {
		if (this.x < 0 || this.y > 600) {
			this.x = 900;
			this.y = calle.getY() - 470;
		}
	}

} // Cierre total de la Clase Lobo
