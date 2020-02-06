package mundo;

import java.awt.Color;
import java.io.Serializable;

/**
 * Clase que representa un disparo e implementa las interfaces movimiento y Serializable
 *
 */
public class Disparo implements Movimiento, Serializable{
	
	//HACER PRUEBAS
	
	/**
	 * Constante con los pixeles que se moveran los disparos en cada movimiento.
	 */
	public final static int MOVIMIENTO_DISPARO = 1;
	
	/**
	 * Constante que representa que el disparo proviene de un enemigo.
	 */
	public final static char DISPARO_ENEMIGO = 'e';
	
	/**
	 * Constante que representa que el disparo proviene de la Nave1.
	 */
	public final static char DISPARO_ALIADO = 'a';
	
	/**
	 * Constante que representa el alto que tiene cada disparo en pixeles
	 */
	public final static int ALTO_MISILES = 30;
	
	/**
	 * Constante que representa el ancho de cada misil en pixeles.
	 */
	public final static int ANCHO_MISILES = 10;
	
	
	/**
	 * 
	 * Atributo con la pocision x donde comienza a dibujarse el misil.
	 */
	private int posX;
	
	/**
	 * Atributo con la pocision y donde comienza a dibujarse el misil.
	 */
	private int posY;
	
	/**
	 * Atributo que indica de que tipo es el disparo. Es una de las constantes definidas en esta clase.
	 */
	private char tipoDisparo;
	
	/**
	 * Atributo que indica el color con el que sera dibujado el misil.
	 */
	private Color color;
	
	/**
	 * Crea una nueva instancia de la clase Disparo. Si el disparo es de tipo ALIADO, su color debe de ser verde. En
	 * caso contrario, es rojo.
	 * @param x La pocision x donde se empieza a dibujar el misil. x >= 0
	 * @param y La pocision y donde se comienza a dibujar el misil. y >= 0
	 * @param tipoDisparo El tipo de disparo (aliado o enemigo). Debe de ser una de las constantes de esta clase.
	 */
	public Disparo (int x, int y, char tipoDisparo){
		posX = x;
		posY = y;
		this.tipoDisparo = tipoDisparo;
		if (tipoDisparo == DISPARO_ALIADO){
			color = Color.GREEN;
		} else {
			color = Color.RED;
		}
	}

	/**
	 * Retorna la pocision x donde se comienza a dibujar el misil.
	 * @return posX
	 */
	public int getPosX() {
		return posX;
	}

	/**
	 * Modifica la pocision x donde se comienza a dibujar el misil.
	 * @param posX La nueva pocision x del misil. posX >= 0.
	 */
	public void setPosX(int posX) {
		this.posX = posX;
	}

	/**
	 * Retorna la pocision y donde se comienza a dibujar el misil.
	 * @return posy
	 */
	public int getPosY() {
		return posY;
	}

	/**
	 * Modifica la pocision y donde se comienza a dibujar el misil.
	 * @param posX La nueva pocision y del misil. posY >= 0.
	 */
	public void setPosY(int posY) {
		this.posY = posY;
	}

	/**
	 * Retorna el tipo de disparo que es el Disparo actual.
	 * @return tipoDisparo, es una de las constantes definidas en esta clase.
	 */
	public char getTipoDisparo() {
		return tipoDisparo;
	}

	/**
	 * Modifica el tipo de disparo actual.
	 * @param tipoDisparo El nuevo tipo de disparo. Debe ser una de las constantes definidas en esta clase.
	 */
	public void setTipoDisparo(char tipoDisparo) {
		this.tipoDisparo = tipoDisparo;
	}

	
	@Override
	/**
	 * Descripcion: metodo que se encarga de mover el disparo, segun si es aliado o enemigo. Si el disparo es aliado,
	 * lo mueve en y el valor pasado por parametro "y", sino, lo mueve en el valor opuesto.
	 * @param x El movimiento en x, en este caso no es necesario.
	 * @param y El movimiento en y. y > 0
	 * @param altoPanel El alto del panel, en este caso no es necesario.
	 * @param anchoPanel el ancho del panel, en este caso no es necesario.
	 */
	public void mover(int x, int y, int altoPanel, int anchoPanel){
		if (tipoDisparo == DISPARO_ALIADO){
			posY -= y;
		} else {
			posY += y;
		}
	}

	/**
	 * Retorna el color del Disparo actual
	 * @return color
	 */
	public Color getColor() {
		return color;
	}
	
	/**
	 * Modifica el color del Disparo actual. 
	 * @param color el nuevo color del Disparo
	 */
	public void setColor(Color color) {
		this.color = color;
	}
	
	
}
