package juego;

import java.awt.Image;
import entorno.Entorno;
import entorno.Herramientas;

public class Conejo {

	// Variables de Instancia:
	
	private int x;
	private int y;
	private Image conejo;

	/** Constructor del Objeto Conejo: */

	Conejo(int x, int y) {
		this.x = x;
		this.y = y;
		this.conejo = Herramientas.cargarImagen("conejoStop.png");
	}

	/** Permite obtener el valor de x */

	public double getX() {
		return x;
	}	

	/** Permite obtener el valor de y */
	public double getY() {
		return y;
	}
	
	/** Mueve al conejo hacia arriba, disminuyendo la y */

	void salto() {
		this.conejo = Herramientas.cargarImagen("conejoJump.png");
		this.y -= 40;
	}
	
	/** Mueve al conejo hacia la derecha, disminuyendo la x */
	
	void saltarDerecha() {
		this.conejo = Herramientas.cargarImagen("conejoJump.png");
		this.x -= 40;
	}
	
	/** Mueve al conejo hacia la izquierda, incrementando la x */
	
	void saltarIzquierda() {
		this.conejo = Herramientas.cargarImagen("conejoJump.png");
		this.x += 40;
	}

	/** Decrece la posición del conejo constantemente */

	void caer() {
		this.conejo = Herramientas.cargarImagen("conejoStop.png");
		this.y += 1;
	}
	
	/** Disminuye la posicion del conejo cuando choca con un arbusto */
	
	void chocar() {
		this.conejo = Herramientas.cargarImagen("conejoJump.png");
		this.y += 10;
	}

	/** Dibuja en Pantalla la imagen del conejo */

	void imprimir(Entorno entorno) {
		entorno.dibujarImagen(conejo, x, y, 0, 0.35);
	}

} // Cierre total de la Clase Conejo
