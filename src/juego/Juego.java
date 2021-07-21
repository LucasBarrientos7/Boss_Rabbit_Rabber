package juego;

import java.awt.Color;
import java.awt.Image;
import java.util.Random;

import javax.sound.sampled.Clip;

import entorno.Entorno;
import entorno.Herramientas;
import entorno.InterfaceJuego;

public class Juego extends InterfaceJuego {

	private Entorno entorno;

	// Variables:

	private int time;
	private int score;
	private int cantSaltos;

	private Conejo conejo;
	
	private Disparo disparo;
	
	private Calle calle;

	private Auto[] autos;

	private Arbusto[] arbustos;

	private Lobo lobo;

	private Nave nave;

	private Image fondo;
	private Image espacio;
	private Image gameOver;
	
	private Clip musica;
	private Clip detonacion;
	private Clip ladrido;
	private Clip salto;
	private Clip victoria;
	private Clip derrota;

	Juego() {
		// Inicializa el objeto entorno
		this.entorno = new Entorno(this, "Boss Rabbit Rabber Game", 800, 600);

		// Inicializar lo que haga falta para el juego
		this.inicializarTodo();

		// Inicia el juego!
		this.entorno.iniciar();
	}

	public void tick() {

		if (colisionBordes() || colisionLobo(lobo)|| colisionAutos(autos)) {

			this.gameOver();
			
			this.restartGame();

		} else if (colisionNave(nave)) {
			
			this.imprimirVictoria();
			
		} else {

			this.recorridoDelJuego();
			
		}
	}

	// Metodos:
	
	/** Inicializa todas las variables */

	void inicializarTodo() {

		this.time = 0;
		this.score = 0;
		this.cantSaltos = 0;

		this.musica = Herramientas.cargarSonido("musica.wav");
		this.salto = Herramientas.cargarSonido("salto.wav");
		this.ladrido = Herramientas.cargarSonido("ladrido.wav");
		this.detonacion = Herramientas.cargarSonido("detonacion.wav");
		this.victoria = Herramientas.cargarSonido("victoria.wav");
		this.derrota = Herramientas.cargarSonido("derrota.wav");

		this.fondo = Herramientas.cargarImagen("fondo.png");
		this.espacio = Herramientas.cargarImagen("espacio.png");
		this.gameOver = Herramientas.cargarImagen("gameOver.png");

		this.conejo = new Conejo(400, 430);

		this.calle = new Calle(400, -150);

		this.lobo = new Lobo(900, -150);

		this.nave = new Nave(400, this.calle.getY()-150);	
		
		this.autos = new Auto[4];
		for (int i = 0; i < this.autos.length; i++) {
			if (i % 2 != 0) {
				this.autos[i] = new Auto(900, -75 * (i + 1), i + 1, "autoIzq" + (i + 1) + ".png");
			} else {
				this.autos[i] = new Auto(-100, 75 * (i + 1), i + 1, "autoDer" + (i + 1) + ".png");
			}
		}
		
		this.arbustos = new Arbusto[4];
		for (int i = 0; i < this.arbustos.length; i++) {
			this.arbustos[i] = new Arbusto(this.posicionArbustoX(), this.calle.getY() - 600 - (i * 150), i + 1);
		}
	}
	
	/** Recorrido comun del juego */
	
	void recorridoDelJuego() {
		this.time++;
		this.derrota.stop();
		this.musica.loop(1);
		this.entorno.dibujarImagen(fondo, 450, 300, 0);
		
		this.calle.imprimir(entorno);
		this.calle.reaparecerCalle();
		
		this.conejo.imprimir(entorno);
		
		this.imprimirAutos(autos);
		this.reaparecerAutos(autos);
		this.disparoDelConejo();

		this.saltoConejo();
		this.sonidoSalto();

		this.imprimirPuntaje();
		this.imprimirSaltos();

		this.level2();
		this.imprimirNave();
	}
	
	/** Imprime en pantalla  la derrota del Usuario */
	
	void gameOver() {
		this.disparo = null;
		this.derrota.start();
		this.musica.close();
		this.entorno.dibujarImagen(gameOver, 450, 300, 0);
		this.entorno.cambiarFont("Aharoni", 50, Color.WHITE);
		this.entorno.escribirTexto("GAME OVER", 250, 250);
		this.entorno.cambiarFont("Aharoni", 30, Color.RED);
		this.entorno.escribirTexto("PRESS ENTER FOR RESTART THE GAME", 140, 300);
	}
	
	/** Reinicia el juego */

	void restartGame() {
		if (this.entorno.sePresiono(this.entorno.TECLA_ENTER)) {
			this.inicializarTodo();
		}
	}
	
	/** Ejecuta el nivel 2 del juego */
	
	void level2() {
		if (time > 1900 && time < 2200) {
			this.entorno.cambiarFont("Showcard Gothic", 50, Color.RED);
			this.entorno.escribirTexto("LEVEL 2", 300, 500);
			
		}if (time>1800) {
			this.imprimirAutos2(autos);
		}
		if (time > 2300) {
			this.reaparecerArbusto(arbustos, calle);
		}
		if (time > 2300 && time < 5000) {
			this.ladrido.loop(1);
			this.lobo.imprimir(entorno);
			if (time > 2400 && time < 2650) {
				this.entorno.cambiarFont("Showcard Gothic", 45, Color.RED);
				this.entorno.escribirTexto("BEWARE OF THE WOLF!", 130, 500);
			}
			this.lobo.reaparecerLobo(calle);
			this.imprimirSaltos();
			this.imprimirPuntaje();
		}
	}
	
	/** Imprime en pantalla la imagen de la Nave */
	
	void imprimirNave() {
		if (time >= 4900) {
			this.ladrido.close();
			this.nave.imprimir(entorno);
		}
	}
	
	/** Imprime la pantalla de victoria */

	void imprimirVictoria() {
		this.musica.close();
		this.victoria.start();
		this.entorno.dibujarImagen(espacio, 450, 300, 0);
		this.entorno.cambiarFont("Showcard Gothic", 70, Color.WHITE);
		this.entorno.escribirTexto("YOU WIN!", 230, 150);
		this.entorno.cambiarFont("Showcard Gothic", 40, Color.YELLOW);
		this.entorno.escribirTexto("JUMPS: " + cantSaltos + "      " + "SCORE: " + score, 190, 200);
		this.entorno.cambiarFont("Aharoni", 30, Color.RED);
		this.entorno.escribirTexto("PRESS ENTER FOR RESTART THE GAME", 140, 520);
		this.restartGame();
	}
	
	/** Otorga una posicion aleatoria en x al objeto Arbusto */

	int posicionArbustoX() {
		Random r = new Random();
		int[] aleatorio = { 140, 300, 500, 660 };
		int posicion = r.nextInt(4);
		int numero = aleatorio[posicion];
		return numero;
	}

	/** Imprime en pantalla el arreglo de autos */

	void imprimirAutos(Auto[] autos) {
		
		for (int i = 0; i < this.autos.length; i++) {
			if(this.autos[i]!=null) {
				this.autos[i].imprimir(entorno);
			} else {
				if (i % 2 != 0) {
					this.autos[i] = new Auto(900, -75 * (i + 1), i + 1, "autoIzq" + (i + 1) + ".png");
					this.autos[i].imprimir(entorno);
				} else {
					this.autos[i] = new Auto(-100, 75 * (i + 1), i + 1, "autoDer" + (i + 1) + ".png");
					this.autos[i].imprimir(entorno);
				}
			}	
		}
	}
	
	/** Imprime los autos en el nuivel 2 y eleva su velocidad */
	
	void imprimirAutos2(Auto[] autos) {
		
		for (int i = 0; i < this.autos.length; i++) {
			if(this.autos[i]!=null) {
				this.autos[i].imprimir(entorno);
				this.autos[i].moverse2();
			} else {
				if (i % 2 != 0) {
					this.autos[i] = new Auto(900, -75 * (i + 1), i + 1, "autoIzq" + (i + 1) + ".png");
					this.autos[i].imprimir(entorno);
					this.autos[i].moverse2();
				} else {
					this.autos[i] = new Auto(-100, 75 * (i + 1), i + 1, "autoDer" + (i + 1) + ".png");
					this.autos[i].imprimir(entorno);
					this.autos[i].moverse2();
				}
			}	
		}
	}

	/** Verifica si el conejo colisiono con un Objeto Auto */
	
	boolean colisionAutos(Auto[] autos) {
		for (int i = 0; i < this.autos.length; i++) {
			if (this.autos[i]!=null) {
				if (this.conejo.getX() + 15 >= this.autos[i].getX() - 70
					&& this.conejo.getX() - 15 <= this.autos[i].getX() + 70
					&& this.conejo.getY() + 15 >= this.autos[i].getY() - 10
					&& this.conejo.getY() - 15 <= this.autos[i].getY() + 22) {
				return true;
				}
			}
		}
		return false;
	}
	
	/** Verifica si el conejo ingresó a la nave */

	boolean colisionNave(Nave nave) {
		if (this.conejo.getX() + 30 >= this.nave.getX() - 800 && this.conejo.getX() - 30 <= this.nave.getX() + 800
				&& this.conejo.getY() + 25 >= this.nave.getY() - 50
				&& this.conejo.getY() - 35 <= this.nave.getY() + 50) {
			return true;
		}
		return false;
	}
	
	/** Verifica si el conejo colisiono con un Objeto Lobo */

	boolean colisionLobo(Lobo lobo) {
		if (this.conejo.getX() + 30 >= this.lobo.getX() - 70 && this.conejo.getX() - 30 <= this.lobo.getX() + 70
				&& this.conejo.getY() + 25 >= this.lobo.getY() - 10
				&& this.conejo.getY() - 35 <= this.lobo.getY() + 22) {
			return true;
		}
		return false;
	}
	
	/** Verifica si el conejo colisiono con un Objeto Arbusto */

	void colisionArbusto() {
		for (int i = 0; i < this.arbustos.length; i++) {
			if (this.conejo.getX() + 30 >= this.arbustos[i].getX() - 120
					&& this.conejo.getX() - 30 <= this.arbustos[i].getX() + 120
					&& this.conejo.getY() + 25 >= this.arbustos[i].getY() - 10
					&& this.conejo.getY() - 35 <= this.arbustos[i].getY() + 22) {
			this.conejo.chocar();
			}
		}
	}
	
	/** Verifica si el disparo colisiono con un Objeto Auto */
	
	void colisionDisparo() {
		for (int i = 0; i < this.autos.length; i++) {
			if(this.disparo!=null) {
				if (this.disparo.getX() + 15 >= this.autos[i].getX() - 55
					&& this.disparo.getX() - 15 <= this.autos[i].getX() + 55
					&& this.disparo.getY() + 50 >= this.autos[i].getY() - 40
					&& this.disparo.getY() - 20 <= this.autos[i].getY() + 40) {
					this.score +=5;
					this.autos[i]=null;
					this.disparo=null;
				}		
			}
		}
	}
	
	/** Verifica si el conejo colisiono con los bordes de la pantalla */

	boolean colisionBordes() {
		if (this.conejo.getX() > 790 || this.conejo.getX() < 10 || this.conejo.getY() > 590
				|| this.conejo.getY() < 10) {
			return true;
		}
		return false;
	}
	
	/** Reaparece los autos cuando salen por los bordes laterales */

	void reaparecerAutos(Auto[] autos) {
		for (int i = 0; i < this.autos.length; i++) {
			this.autos[i].reaparecerAuto(calle);
		}
	}
	
	/** Reaparece el arreglo de Arbustos entre calle y calle */
	
	void reaparecerArbusto(Arbusto[] arbustos, Calle calle) {
		for (int i = 0; i < this.arbustos.length; i++) {
			this.arbustos[i].imprimir(entorno);
		}
		for (int i = 0; i < this.arbustos.length; i++) {
			if (this.arbustos[i].getY() > 810) {
				this.arbustos[i].setX(posicionArbustoX());
				this.arbustos[i].setY(this.calle.getY() - 180 * (i + 1));
			}
		}
	}

	/** Imprime en pantalla el puntaje del Usuario */

	void imprimirPuntaje() {
		this.entorno.cambiarFont("Showcard Gothic", 30, Color.WHITE);
		this.entorno.escribirTexto("SCORE:" + this.score, 600, 50);
	}
	
	/** Imprime en pantalla los saltos que realiza el conejo */

	void imprimirSaltos() {
		this.entorno.cambiarFont("Showcard Gothic", 30, Color.yellow);
		this.entorno.escribirTexto("JUMPS: " + cantSaltos, 50, 50);
	}
	
	/** Ejecuta el sonido que hace el conejo al saltar */

	void sonidoSalto() {
		if (this.entorno.estaPresionada(this.entorno.TECLA_ARRIBA)
				|| this.entorno.estaPresionada(this.entorno.TECLA_DERECHA)
				|| this.entorno.estaPresionada(this.entorno.TECLA_IZQUIERDA)) {
			this.salto.loop(1);
		}
	}

	/** Determina a que direccion salta el conejo utilizando las flechas */
	
	void saltoConejo() {
		if (this.entorno.sePresiono(this.entorno.TECLA_ARRIBA)) {
			this.conejo.salto();
			cantSaltos++;
		} else if (this.entorno.sePresiono(this.entorno.TECLA_DERECHA)) {
			this.conejo.saltarIzquierda();
		} else if (this.entorno.sePresiono(this.entorno.TECLA_IZQUIERDA)) {
			this.conejo.saltarDerecha();
		} else {
			this.conejo.caer();
		}
		this.colisionArbusto();
	}
	
	/** Ejecuta todo lo relacionado con el disparo */

	void disparoDelConejo() {
		if (this.entorno.sePresiono(this.entorno.TECLA_ESPACIO)) {
			if (this.disparo == null) {
				this.detonacion.loop(1);
				this.disparo = new Disparo(this.conejo.getX(), this.conejo.getY());
			} 
		}
		if (this.disparo != null) {
			this.disparo.disparar(entorno);
			this.colisionDisparo();
		}
		if (this.disparo != null) {
			if (this.disparo.getY() < 0) {
				this.disparo = null;
			}
		}
	}

	@SuppressWarnings("unused")
	public static void main(String[] args) {
		Juego juego = new Juego();

	} // Cierre del Main

} // Cierre total del Programa
