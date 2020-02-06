package mundo;

/**
 * Clase que representa una nave normal enemiga. Extiende de NaveEnemiga.
 *
 */
public class NaveNormal extends NaveEnemiga{

	/**
	 * Crea una nueva instancia de NaveNormal llamando al constructor de la clase padre.
	 * @param posX La pocision x donde se comenzara a dibujar la nave. posX >= 0.
	 * @param posY La pocision y donde se comenzara a dibujar la nave. posY >= 0.
	 * @param rutaImagen La ruta de la imagen correspondiente a la nave actual. rutaImagen != null. rutaImagen != "".
	 * @param largo El largo de la nave actual en pixeles. largo > 0.
	 * @param ancho El ancho de la nave actual en pixeles. ancho > 0.
	 * @param eliminada Indica si la nave actual esta eliminada o no.
	 * @param direccion La direccion en la que comenzara a moverse la nave. Es una de las constantes definidas en la clase Nave.
	 */
	public NaveNormal(int posX, int posY, String rutaImagen, int largo, int ancho, boolean eliminada, char direccion) {
		super(posX, posY, rutaImagen, largo, ancho, eliminada, direccion);
		// TODO Auto-generated constructor stub
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
