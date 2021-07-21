package juego;

import java.awt.Image;
import entorno.Entorno;
import entorno.Herramientas;

public class Arbusto {
	
	// Variables de Instancia:
	
	private int x;
	private int y;
	private Image arbusto;
	private int fila;

	/** Constructor del Objeto Arbusto */
	
	  public Arbusto (int x, int y, int fila) {
	        this.x = x;
	        this.y = y;
	        this.fila=0;
	        this.arbusto=Herramientas.cargarImagen("arbustos.png");
	    }
	  
	 /** Permite obtener el valor de Fila */
	  
	   public int getFila() {
	        return fila;
	   }

	 /** Permite modificar el valor de Fila */
	   
	   public void setFila(int fila) {
	        this.fila = fila;
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
	
	/** Decrece la imagen del arbusto */
	
	void bajarArbusto() {
	        this.y = y + 1;
	    }

	/** Invoca al entorno y dibuja la imagen arbusto */
	
	void imprimir(Entorno entorno) {
		entorno.dibujarImagen(arbusto, x, y, 0, 0.35);
	        this.bajarArbusto();
	    }
	
} // Cierre total de la Clase Arbusto
