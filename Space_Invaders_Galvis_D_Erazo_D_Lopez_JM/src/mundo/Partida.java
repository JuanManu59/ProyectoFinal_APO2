package mundo;

import java.io.Serializable;

/**
 * Clase abstracta que representa la partida en curso. Implementa la interfaz Serializable.
 *
 */

public abstract class Partida implements Serializable{
	
	/**
	 * Relacion hacia NaveUsuario que representa la nave manejada por el usuario principal.
	 */
	private NaveUsuario nave;
	
	/**
	 * El ancho del panel donde se lleva a cabo la partida.
	 */
	private int anchoPanel;
	
	/**
	 * El alto del panel donde se lleva a cabo la partida.
	 */
	private int altoPanel;
	
	/**
	 * Boolean que indica si actualmente la partida se encuentra en curso o no.
	 */
	private boolean enPartida;
	
	
	/**
	 * Crea una nueva instancia de Partida. Asigna al ancho y al alto los valores pasados por parametro, inicializa la asociacion hacia
	 * NaveUsuario creando una nueva instancia y asigna true a enPartida.
	 * @param anchoPanel El ancho del panel donde se llevara a cabo la partida. anchoPanel > 0.
	 * @param altoPanel El alto del panel donde se llevara a cabo la partida. altoPanel > 0.
	 */
	public Partida (int anchoPanel, int altoPanel){
		this.anchoPanel = anchoPanel;
		this.altoPanel = altoPanel;
		nave = new NaveUsuario((anchoPanel/2) - 35, altoPanel - 70, "./data/imagenes/Nave_Aliada.png", 70, 70);
		enPartida = true;
	}
	
	/**
	 * Descripcion: se encarga de mover el disparo de la nave actual. Para moverla, le reduce a sus coordenadas en Y
	 * el valor correspondiente al definido en la constante de la clase Disparo. Si el extremo inferior del disparo (posY + altoMisil)
	 * llega a la pocision 0, se detiene el disparo. <br>
	 * <b> pre : </b> nave != null <br>
	 * <b> pre : </b> nave.getDisparo () != null <br>
	 */
	public void moverDisparo (){
		nave.getDisparo().mover(0, Disparo.MOVIMIENTO_DISPARO, altoPanel, anchoPanel);
		if (nave.isDisparando() && (nave.getDisparo().getPosY() + Disparo.ALTO_MISILES == 0)){
			nave.detenerDisparo();
		}
	}
	
	/**
	 * Descripcion: Comienza el movimiento de la nave, es decir, indica que la nave se esta moviendo. <br>
	 * <b> pre : </b> nave != null <br>
	 * <b> post : </b> nave.isEnMovimiento () == true <br>
	 */
	public void empezarMovimientoNave(){
		nave.setEnMovimiento(true);
	}
	
	/**
	 * Descripcion: Detiene el movimiento de la nave, es decir, indica que la nave no se esta moviendo. <br>
	 * <b> pre : </b> nave != null <br>
	 * <b> post : </b> nave.isEnMovimiento() == false <br>
	 */
	public void detenerNave(){
		nave.setEnMovimiento(false);
	}
	
	/**
	 * Descripcion: Intenta mover la nave del usuario principal hacia la derecha. Para hacerlo, agrega
	 * a su pocision en X el valor de la constante definida en la clase NaveUsuario. <br>
	 * <b> pre : </b> nave != null <br>
	 * <b> post : </b> nave.getPosX() + nave.getLargo() <= anchoPanel <br>
	 */
	public void moverNaveUsuarioDerecha (){
		nave.mover(NaveUsuario.MOVIMIENTO, 0, altoPanel, anchoPanel);
	}
	
	/**
	 * Descripcion: Intenta mover la nave del usuario principal hacia la izquierda. Para hacerlo, remueve
	 * a su pocision en X el valor de la constante definida en la clase NaveUsuario. <br>
	 * <b> pre : </b> nave != null <br>
	 * <b> post : </b> nave.getPosX() >= 0 <br>
	 */
	public void moverNaveUsuarioIzquierda (){
		nave.mover(-NaveUsuario.MOVIMIENTO, 0, altoPanel, anchoPanel);
	}
	
	/**
	 * Descripcion: Ejecuta un disparo de la nave del usuario, esto es, inicializa la relacion hacia
	 * la clase Disparo de la nave con una nueva instancia de Disparo e indica que la nave se encuentra disparando. <br>
	 * <b> pre : </b> nave != null <br>
	 * <b> post : </b> nave.isDisparando() == true <br>
	 * <b> post : </b> nave.getDisparo() != null <br>
	 */
	public void dispararNaveAliada(){
		nave.disparar(1);
	}
	
	/**
	 * Descripcion: Detiene el disparo de la nave del usuario principal. Esto es, indica que la nave
	 * ya no se encuentra disparando y define la relacion de la nave hacia Disparo como null. <br>
	 * <b> pre </b> nave != null <br>
	 * <b> post : </b> nave.isDisparando == false <br>
	 * <b> post : </b> nave.getDisparo() == null <br>
	 */
	public void detenerDisparoAliado(){
		nave.detenerDisparo();
	}

	/**
	 * Retorna la nave del usuario principal. <br>
	 * <b> pre : </b> nave != null <br>
	 * @return nave
	 */
	public NaveUsuario getNave() {
		return nave;
	}

	/**
	 * Modifica la nave del usuario principal. 
	 * @param nave La nueva nave del usuario principal. nave != null 
	 */
	public void setNave(NaveUsuario nave) {
		this.nave = nave;
	}

	
	/**
	 * Retorna el ancho del panel en el que se esta llevando a cabo la partida <br>
	 * <b>  pre : </b> anchoPanel > 0 <br>
	 * @return anchoPanel
	 */
	public int getAnchoPanel() {
		return anchoPanel;
	}

	/**
	 * Modifica el ancho del panel en el que se esta jugando la partida.
	 * @param anchoPanel el nuevo ancho del panel. anchoPanel > 0
	 */
	public void setAnchoPanel(int anchoPanel) {
		this.anchoPanel = anchoPanel;
	}

	/**
	 * Retorna el alto del panel en el que se esta jugando la partida. <br>
	 * <b> pre : </b> altoPanel > 0 <br>
	 * @return altoPanel
	 */
	public int getAltoPanel() {
		return altoPanel;
	}

	/**
	 * Modifica el alto del panel en el que se esta jugando la partida.
	 * @param altoPanel el nuevo alto del panel. altoPanel > 0
	 */
	public void setAltoPanel(int altoPanel) {
		this.altoPanel = altoPanel;
	}
	
	/**
	 * Indica si actualmente la partida esta activa o no.
	 * @return true si la partida esta activa, false en caso contrario.
	 */
	public boolean isEnPartida(){
		return enPartida;
	}
	
	/**
	 * Modifica el valor del atributo que indica si la partida esta activa o no.
	 * @param enPartida El nuevo valor del atributo enPartida.
	 */
	public void setEnPartida (boolean enPartida){
		this.enPartida = enPartida;
	}
	
	
	
}
