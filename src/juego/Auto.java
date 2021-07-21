package juego;

import java.awt.Image;
import java.util.Random;
import entorno.Entorno;
import entorno.Herramientas;

public class Auto {

	// Variables de Instancia:
	
	private int x;
	private int y;
	private int carril;
	private String nombreAuto;
	private Image auto;

	/** Constructor del Objeto Auto: */
	
	public Auto(int x, int y, int carril, String nombreAuto) {
		this.x = x;
		this.y = y;
		this.carril = carril;
		this.auto = Herramientas.cargarImagen(this.cambiarAuto());
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
	
	/** Desplaza la imagen del auto dependiendo su carril */

	void moverse() {
		if(this.carril==1 || this.carril==3) {
			this.x -=3;
			this.y +=1;
		} else {
			this.x += 2;
			this.y += 1;
		}
	}
	
	/** En el nivel 2: desplaza la imagen del auto dependiendo su carril */
	
	void moverse2() {
		if(this.carril==1 || this.carril==3) {
			this.y -= 1;
		} else {
			this.x -= 1;
			this.y -= 1;
		}
	}

	/** Imprime en pantalla la imagen de un auto */

	void imprimir(Entorno entorno) {
		entorno.dibujarImagen(auto, this.x, this.y, 0, 0.8);
		this.moverse();
	}
	
	
	/** Elije el nombre de la imagen de un auto aleatoriamente */
	
	String cambiarAuto() {
		if(this.carril==1 || this.carril==3) {
		Random random = new Random();
		String[] traficoDerecha = { "autoIzq1.png", "autoIzq2.png", "autoIzq3.png", "autoIzq4.png" };
		int autoAleatorio = random.nextInt(4);
		this.nombreAuto = traficoDerecha[autoAleatorio];
		return nombreAuto;
		} else {
			Random random = new Random();
			String[] traficoDerecha = { "autoDer1.png", "autoDer2.png", "autoDer3.png", "autoDer4.png" };
			int autoAleatorio = random.nextInt(4);
			this.nombreAuto = traficoDerecha[autoAleatorio];
			return nombreAuto;
		}
	}
	
	/** Dependiendo su carril, hace reaparecer al auto en el mismo carril */
	
	void reaparecerAuto(Calle calle) {
		if(this.carril==1) {
			if (this.x < 0 || this.auto==null) {
				this.auto = Herramientas.cargarImagen(this.cambiarAuto());
				this.x = 1000;
				this.y =calle.getY()-105;
			}
		} else if (this.carril==2){
			if (this.x > 900 || this.auto==null) {
				this.auto = Herramientas.cargarImagen(this.cambiarAuto());
				this.x = 0;
				this.y =calle.getY()-45;
			}
		} else if (this.carril==3){
			if (this.x < 0 || this.auto==null) {
				this.auto = Herramientas.cargarImagen(this.cambiarAuto());
				this.x = 900;
				this.y =calle.getY()+25;
			}
		} else if (this.carril==4){
			if (this.x > 900 || this.auto==null) {
				this.auto = Herramientas.cargarImagen(this.cambiarAuto());
				this.x = -100;
				this.y =calle.getY()+80;
			}
		}
	}
	
} // Cierre total de la Clase Auto
