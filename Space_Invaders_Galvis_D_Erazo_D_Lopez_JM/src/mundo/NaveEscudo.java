package mundo;
/**
 * Clase que representa una nave enemiga con escudo. Extiende de NaveEnemiga e implementa la interfaz Escudo.
 *
 */
public class NaveEscudo extends NaveEnemiga implements Escudo{
	
	/**
	 * Constante que indica el maximo de disparos que puede recibir la nave para conservar su escudo.
	 */
	public final static int MAXIMO_DISPAROS = 2;
	
	/**
	 * Atributo indicando el numero de disparos que ha recibido la nave.
	 */
	private int disparosRecibidos;
	
	
	
	/**
	 * Crea una nueva instancia de NaveEscudo, llamando al constructor de la clase padre.
	 * @param posX La pocision x donde se comenzara a dibujar la nave. posX >= 0.
	 * @param posY La pocision y donde se comenzara a dibujar la nave. posY >= 0.
	 * @param rutaImagen La ruta de la imagen correspondiente a la nave actual. rutaImagen != null. rutaImagen != "".
	 * @param largo El largo de la nave actual en pixeles. largo > 0.
	 * @param ancho El ancho de la nave actual en pixeles. ancho > 0.
	 * @param eliminada Indica si la nave actual esta eliminada o no.
	 * @param direccion La direccion en la que comenzara a moverse la nave. Es una de las constantes definidas en la clase Nave.
	 */
	public NaveEscudo(int posX, int posY, String rutaImagen, int largo, int ancho, boolean eliminada, char direccion) {
		super(posX, posY, rutaImagen, largo, ancho, eliminada, direccion);
		disparosRecibidos = 0;
	}
	

	@Override 
	/**
	 * Descripcion: Indica si la nave actual recibio un disparo con pocision indicada. Se considera que la nave
	 * recibio el disparo  si: el extremo superior o inferior del disparo esta entre el extremo superior e inferior de la nave y
	 * el extremo izquierdo o derecho del disparo esta entre el extremo izquierdo y derecho de la nave. Si recibe el disparo,
	 * aumenta 1 a sus disparos recibidos. Si llega a dos disparos recibidos elimina la nave,
	 * @param x1 El extremo izquierdo del disparo. x1 >= 0
	 * @param x2 El extremo derecho del disparo. x2 >= 0.
	 * @param y1 El extremo inferior del disparo. y1 >= 0.
	 * @param y2 El extremo superior del disparo. y2 >= 0.
	 * @return true si recibio el disparo, false en caso contrario.
	 */
	public boolean recibioDisparo (int x1, int x2, int y1, int y2){
		boolean retorno = super.recibioDisparo(x1, x2, y1, y2);
		if (retorno){
			disparosRecibidos ++;
			if (!escudoActivo()){
				setEliminada(true);
			}
		}
		return retorno;
		//TODO contrato
	}
	
	@Override
	/**
	 * Indica si el escudo de la nave esta activo o no. Una nave tiene escudo activo hasta que reciba cierto numero
	 * de disparos, es decir, si ha recibido menos de dos disparos.
	 * @return true si el escudo esta activo. false en caso contrario.
	 */
	public boolean escudoActivo() {
		return disparosRecibidos < MAXIMO_DISPAROS;
		//TODO contrato
	}

	/**
	 * Retorna el numero de disparos que la nave actual ha recibido. <br>
	 * <b> pre : </b> disparosRecibidos >= 0 <br>
	 * @return disparosRecibidos.
	 */
	public int getDisparosRecibidos() {
		return disparosRecibidos;
	}

	/**
	 * Modifica el numero de disparos recibidos.
	 * @param disparosRecibidos El nuevo numero de disparos recibidos por la nave. disparosRecibidos >= 0.
	 */
	public void setDisparosRecibidos(int disparosRecibidos) {
		this.disparosRecibidos = disparosRecibidos;
	}
	
	
	
}
