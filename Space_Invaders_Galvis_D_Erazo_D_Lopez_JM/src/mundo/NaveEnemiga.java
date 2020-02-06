package mundo;

/**
 * Clase abstracta que representa una nave enemiga y extiende de Nave.
 *
 */
public abstract class NaveEnemiga extends Nave{
	
	/**
	 * Constante que representa los pixeles que se movera la nave por cada movimiento.
	 */
	public final static int MOVIMIENTO = 50;
	
	
	/**
	 * Atributo que indica si la nave actual se encuentra eliminada o no.
	 */
	private boolean eliminada;
	
	/**
	 * Atributo que indica la direccion en la que se esta moviendo la nave actual. Es una de las constantes definidas
	 * en la clase Nave.
	 */
	private char direccion;
	
	/**
	 * La siguiente nave enemiga dentro de la lista enlazada de naves.
	 */
	private NaveEnemiga siguiente;
	
	
	/**
	 * Crea una nueva instancia de NaveEnemiga
	 * @param posX La pocision x donde se comenzara a dibujar la nave. posX  >= 0.
	 * @param posY La pocision y donde se comenzara a dibujar la nave. posY >= 0.
	 * @param rutaImagen La ruta donde se encuentra la imagen de la nave actual. rutaImagen != null. rutaImagen != "".
	 * @param largo El largo en pixeles de la nave actual. largo > 0.
	 * @param alto El alto actual en pixeles de la nave actual. alto > 0.
	 * @param eliminada Indica si la nave actual se encuentra eliminada o no.
	 * @param direccion La direccion en la que comenzara a moverse la nave actual. Es una de las constantes definidas en la clase Nave.
	 */
	public NaveEnemiga(int posX, int posY, String rutaImagen, int largo, int alto, boolean eliminada, char direccion) {
		super(posX, posY, rutaImagen, largo, alto);
		this.eliminada = eliminada;
		this.direccion = direccion;
	}

	/**
	 * Retorna la direccion en la que se encuentra moviendo la nave actual.
	 * @return direccion
	 */
	public char getDireccion() {
		return direccion;
	}

	/**
	 * Modifica la direccion en la que se encuentra moviendo la nave actual.
	 * @param direccion la nueva direccion en la que se esta moviendo la nave.
	 */
	public void setDireccion(char direccion) {
		this.direccion = direccion;
	}

	/**
	 * Retorna si la nave acutal esta eliminada o no.
	 * @return true si esta eliminada, false en caso contrario.
	 */
	public boolean isEliminada() {
		return eliminada;
	}

	/**
	 * Modifica el atributo indicando si la nave actual se encuentra eliminada.
	 * @param eliminada El nuevo valor del atributo eliminada.
	 */
	public void setEliminada(boolean eliminada) {
		this.eliminada = eliminada;
	}
	
	
	@Override
	/**
	 * Descripcion: Si la direccion es derecha, lo desplaza en el valor pasado en x si puede realizar el movimiento. Sino,
	 * lo deja en la misma x, le aumenta su valor a y y cambia su direccion. Si la direccion es izquierda, se usa la misma logica pero le quita a 
	 * su x el valor pasado por parametro.
	 * @param x Los pixeles para los que se quiere mover el objeto en el eje x. x > 0
	 * @param y No tiene importancia en esta implementacion.
	 * @param altoPanel El alto del panel en el que se quiere mover el objeto
	 * @param anchoPanel El ancho del panel en el que se quiere mover el objeto
	 */
	public void mover (int x, int y, int altoPanel, int anchoPanel){
		switch (direccion){
		case DERECHA:
			super.mover(x, 0, 0, 0);
			break;
		case IZQUIERDA:
			super.mover(-x, 0, 0, 0);
			break;
		}
		validarMovimiento(altoPanel, anchoPanel);
		//TODO contrato
	}
	
	/**
	 * Descripcion: Valida el movimiento de la nave actual, garantizando que la totalidad
	 * de la nave este dentro de las dimensiones del panel. Para lograrlo, si la nave se sale de las dimensiones del panel,
	 * cambia su direccion, se desplaza en x hacia su pocision anterior al movimiento y se desplaza en y segun el valor de
	 * la constante MOVIMIENTO definida en la clase actual. <br>
	 * <b> post : </b> posX >= 0 <br>
	 * <b> post : </b> posX + getLargo() <= ancho <br>
	 * @param alto El alto del panel donde se esta desplazando la nave. alto > 0.
	 * @param ancho El ancho del panel donde se esta desplazando la nave. ancho > 0.
	 */
	public void validarMovimiento (int alto, int ancho){
		if (getPosX() + getLargo() > ancho){
			direccion = IZQUIERDA;
			super.mover(-MOVIMIENTO, MOVIMIENTO, 0, 0);
		} else if (getPosX() < 0){
			direccion = DERECHA;
			super.mover(MOVIMIENTO, MOVIMIENTO, 0, 0);
		}
	}
	
	/**
	 * Modifica la siguiente nave dentro de la lista enlazada de NavEnemiga.
	 * @param n la nueva nave correspondiente a la siguiente nave.
	 */
	public void setSiguiente (NaveEnemiga n){
		siguiente = n;
	}
	
	/**
	 * Retorna la siguiente nave a la actual dentro de la lista enlazada de navEnemiga.
	 * @return siguiente.
	 */
	public NaveEnemiga getSiguiente(){
		return siguiente;
	}
	
	
	
	
	

}
