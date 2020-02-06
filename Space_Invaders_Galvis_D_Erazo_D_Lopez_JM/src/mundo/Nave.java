package mundo;

import java.io.Serializable;

/**
 * Clase abstracta que representa una nave e implementa las interfaces movimiento y Serializable
 *
 */
public abstract class Nave implements Movimiento, Serializable{
	
	/**
	 * Constante que indica que la nave se esta desplazando a la derecha.
	 */
	public final static char DERECHA = 'D';
	
	/**
	 * Constante que representa que la nave se esta desplazando a la izquierda.
	 */
	public final static char IZQUIERDA = 'I';
	
	
	/**
	 * Atributo que representa la pocision x donde se comienza a dibujar la nave. 
	 */
	private int posX;
	
	/**
	 * Atributo que representa la pocision y donde se comienza a dibujar la nave.
	 */
	private int posY;
	
	/**
	 * Atributo que representa la pocision x donde se dibujo la nave originalmente al comenzar la partida.
	 */
	private int xOriginal;
	
	/**
	 * Atributo que representa la pocision y donde se dibujo la nave originalmente al comenzar la partida.
	 */
	private int yOriginal;
	
	/**
	 * Atributo que representa el largo en pixeles de la nave actual.
	 */
	private int largo;
	
	/**
	 * Atributo que representa el alto en pixeles de la nave actual.
	 */
	private int alto;
	
	/**
	 * Atributo con la ruta donde se encuentra la imagen de la nave actual.
	 */
	private String rutaImagen;
	
	
	
	/**
	 * Crea una nueva instancia de la clase Nave. xOriginal y yOriginal toman los mismos valores de posX y posY respectivamente.
	 * @param posX la pocision x donde se comenzara a dibujar la nave originalmente. posX >= 0.
	 * @param posY la pocision y donde se comenzara a dibujar la nave originalmente. posY >= 0.
	 * @param rutaImagen La ruta de la imagen correspondiente a la nave. rutaImagen != null. rutaImagen != "".
	 * @param largo El largo en pixeles de la nave actual. largo > 0.
	 * @param alto El alto en pixeles de la nave actual. alto > 0.
	 */
	public Nave (int posX, int posY, String rutaImagen, int largo, int alto)  {
		this.posX = posX;
		this.posY = posY;
		xOriginal = posX;
		yOriginal = posY;
		this.rutaImagen = rutaImagen;
		this.largo = largo;
		this.alto = alto;
	}
	
	/**
	 * Retorna la pocision x original de la nave.
	 * @return xOriginal
	 */
	public int getxOriginal() {
		return xOriginal;
	}
	
	/**
	 * Modifica la pocision x original de la nave.
	 * @param xOriginal La nueva pocision x original de la nave. xOriginal >= 0.
	 */
	public void setxOriginal(int xOriginal) {
		this.xOriginal = xOriginal;
	}
	

	/**
	 * Retorna la pocision y original de la nave.
	 * @return yOriginal
	 */
	public int getyOriginal() {
		return yOriginal;
	}
	
	/**
	 * Modifica la pocision y original de la nave.
	 * @param xOriginal La nueva pocision y original de la nave. yOriginal >= 0.
	 */
	public void setyOriginal(int yOriginal) {
		this.yOriginal = yOriginal;
	}
	
	/**
	 * Retorna el largo en pixeles de la nave actual.
	 * @return largo.
	 */
	public int getLargo() {
		return largo;
	}
	
	/**
	 * Modifica el largo en pixeles de la nave actual.
	 * @param largo el nuevo largo de la nave. largo > 0
	 */
	public void setLargo(int largo) {
		this.largo = largo;
	}
	
	/**
	 * Retorna el alto en pixeles de la nave actual.
	 * @return alto.
	 */
	public int getAlto() {
		return alto;
	}
	
	/**
	 * Modifica el alto en pixeles de la nave actual.
	 * @param alto el nuevo alto de la nave. alto > 0
	 */
	public void setAlto(int alto) {
		this.alto = alto;
	}

	/**
	 * Retorna la pocision x actual de la nave.
	 * @return posX
	 */
	public int getPosX() {
		return posX;
	}
	
	/**
	 * Modifica la pocision x actual de la nave.
	 * @param posX la nueva pocision x de la nave. posX > 0
	 */
	public void setPosX(int posX) {
		this.posX = posX;
	}
	
	/**
	 * Retorna la pocision y actual de la nave.
	 * @return posY
	 */
	public int getPosY() {
		return posY;
	}
	
	/**
	 * Modifica la pocision y actual de la nave.
	 * @param posX la nueva pocision y de la nave. posY > 0
	 */
	public void setPosY(int posY) {
		this.posY = posY;
	}

	/**
	 * Retorna la ruta correspondiente a la imagen de la nave actual.
	 * @return rutaImagen.
	 */
	public String getRutaImagen() {
		return rutaImagen;
	}

	/**
	 * Modifica la ruta de la imagen correspondiente a la nave actual.
	 * @param rutaImagen la nueva ruta de la imagen correspondiente a la nave. rutaImagen != null. rutaImagen != "".
	 */
	public void setRutaImagen(String rutaImagen) {
		this.rutaImagen = rutaImagen;
	}
	
	/**
	 * Descripcion: Indica si la nave actual recibio un disparo con pocision indicada. Se considera que la nave
	 * recibio el disparo  si: el extremo superior o inferior del disparo esta entre el extremo superior e inferior de la nave y
	 * el extremo izquierdo o derecho del disparo esta entre el extremo izquierdo y derecho de la nave.
	 * @param x1 El extremo izquierdo del disparo. x1 >= 0
	 * @param x2 El extremo derecho del disparo. x2 >= 0.
	 * @param y1 El extremo inferior del disparo. y1 >= 0.
	 * @param y2 El extremo superior del disparo. y2 >= 0.
	 * @return true si recibio el disparo, false en caso contrario.
	 */
	public boolean recibioDisparo(int x1, int x2, int y1, int y2){
		boolean recibio = false;
		recibio = ((y1 <= posY + largo && y1 >= posY) || (y2 <= posY + largo && y2 >= posY)) &&
				((x1 >= posX && x1 <= posX + largo) || (x2 >= posX && x2 <= posX + largo));
		
		return recibio;
	}
	
	@Override
	public void mover (int x, int y, int altoPanel, int anchoPanel){
		posX += x;
		posY += y;
		//TODO contrato
	}
		
	
}
