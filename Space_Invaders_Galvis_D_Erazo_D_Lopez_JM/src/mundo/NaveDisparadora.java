package mundo;

/**
 * Clase que representa una nave enemiga disparador. Extiende de NavEnemiga e implementa la interfaz Disparador.
 * @author WINDOWS 10
 *
 */
public class NaveDisparadora extends NaveEnemiga implements Disparador{
	
	
	//HACER PRUEBAS 
	
	/**
	 * Atributo boolean que indica si la nave se encuentra disparando o no.
	 */
	private boolean disparando;
	
	/**
	 * Asociacion con la clase Disparo que representa el disparo de la nave actual.
	 */
	private Disparo disparo;

	
	/**
	 * Crea una nueva instancia de NaveDisparadora y llama al constructor de la clase padre.
	 * @param posX La pocision x donde se comenzara a dibujar la nave. x >= 0
	 * @param posY La pocision y donde se comenzara a dibujar la nave. y >= 0
	 * @param rutaImagen La ruta donde se encuentra la imagen correspondiente a la nave actual. rutaImagen != null. rutaImagen != "".
	 * @param largo El largo en pixeles de la imagen actual. largo > 0.
	 * @param ancho El ancho en pixeles de la imagen actual. ancho > 0.
	 * @param eliminada Indica si la nave actual esta eliminada o no.
	 * @param direccion Indica la direccion en la que la nave se esta moviendo. Es una de las constantes definidas en la clase Nave.
	 */
	public NaveDisparadora(int posX, int posY, String rutaImagen, int largo, int ancho, boolean eliminada, char direccion) {
		super(posX, posY, rutaImagen, largo, ancho, eliminada, direccion);
		disparando = false;
		disparo = null;
	}
	

	/**
	 * Descripcion: Decide aleatoriamente si la nave debe de disparar o no. Para tomar la decision, 
	 * genera un entero aleatorio y entre el int pasado por parametro - 1. Si dicho entero aleatorio es 0,
	 * indica de realizar el disparo, en caso contrario indica de no hacerlo.
	 * @param probablidadDisparo La probabilidad de que el metodo indique de disparar. Por ejemplo, si probabilidadDisparo es
	 * 10, la nave tiene una entre 10 chances de disparar. probabilidadDisparo > 0.
	 * @return boolean si el entero aleatorio generado es 0, false en caso contrario.
	 */
	public boolean definirDisparo(int probablidadDisparo){
		int numero = (int) (Math.random() * probablidadDisparo);
		return numero == 0;
	}

	@Override
	/**
	 * Descripcion: Se encarga de darle la orden a la nave de disparar. Para determinar si dispara o no,
	 * genera un numero aleatorio entre 0 y el valor pasado en probabilidadDisparo - 1. Si el valor aletorio es
	 * 0, ejecuta el disparo.
	 * @param probabilidadDisparo es la probabilidad de hacer el disparo.
	 */
	public void disparar(int probablidadDisparo) {
		if (definirDisparo(probablidadDisparo) && !disparando){
			disparo = new Disparo((2*getPosX() + getLargo())/2, getPosY(), Disparo.DISPARO_ENEMIGO);
			disparando = true;
		}
		//TODO contrato
	}
	
	@Override 
	/**
	 * Descripcion: Detiene el disparo de la nave, eliminando su relacion con Disparo (null) y diciendo que ya
	 * no esta disparando. <br>
	 * <b> post: </b> disparando = false <br>
	 * <b> post: </b> disparo = null <br>
	 */
	public void detenerDisparo(){
		disparando = false;
		disparo = null;
		//TODO contrato
	}
	
	/**
	 * Retorna si la nave actual se encuentra disparando o no.
	 * @return disparando
	 */
	public boolean isDisparando() {
		return disparando;
	}


	/**
	 * Modifica el atributo que indica si la nave se encuentra disparando.
	 * @param disparando El nuevo valor del atributo disparando.
	 */
	public void setDisparando(boolean disparando) {
		this.disparando = disparando;
	}

	/**
	 * Retorna la relacion hacia la clase Disparo de la nave actual.
	 * @return disparo.
	 */
	public Disparo getDisparo() {
		return disparo;
	}

	/**
	 * Modifica la relacion hacia la clase disparo de la nave actual.
	 * @param disparo La nueva relacion hacia la clase Disparo.
	 */
	public void setDisparo(Disparo disparo) {
		this.disparo = disparo;
	}


	@Override
	/**
	 * Descripcion: Indica si la nave actual recibio un disparo con pocision indicada. Se considera que la nave
	 * recibio el disparo  si: el extremo superior o inferior del disparo esta entre el extremo superior e inferior de la nave y
	 * el extremo izquierdo o derecho del disparo esta entre el extremo izquierdo y derecho de la nave. Si recibe el disparo,
	 * debe de indicar que la nave esta eliminada.
	 * @param x1 El extremo izquierdo del disparo. x1 >= 0
	 * @param x2 El extremo derecho del disparo. x2 >= 0.
	 * @param y1 El extremo inferior del disparo. y1 >= 0.
	 * @param y2 El extremo superior del disparo. y2 >= 0.
	 * @return true si recibio el disparo, false en caso contrario.
	 */
	public boolean recibioDisparo (int x1, int x2, int y1, int y2){
		boolean recibio = super.recibioDisparo(x1, x2, y1, y2);
		if (recibio){
			setEliminada(true);
		}
		return recibio;
		//TODO contrato
	}

}
