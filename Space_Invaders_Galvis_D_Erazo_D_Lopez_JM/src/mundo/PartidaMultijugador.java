package mundo;

/**
 * Clase que representa una partida multijugador. Extiende de Partida.
 *
 */
public class PartidaMultijugador extends Partida{
	/**
	 * Asociacion hacia NaveUsuario representando la nave del usuario 2.
	 */
	private NaveUsuario nave2;
	
	
	/**
	 * Crea una nueva instancia de PartidaMultijugador llamando al constructor de la clase padre. Inicializa 
	 * nave2 como una nueva instancia de NaveUsuario. <br>
	 * <b> post </b> nave2 != null <br>
	 * @param anchoPanel
	 * @param altoPanel
	 */
	public PartidaMultijugador(int anchoPanel, int altoPanel) {
		super(anchoPanel, altoPanel);
		nave2 = new NaveUsuario((anchoPanel/2) - 35, 0, "./data/imagenes/Nave_Aliada_2.png", 70, 70);
	}
	
	@Override
	/**
	 * Descripcion: Se encarga de mover el disparo de la nave segundaria reduciendole dos pixeles a su pocision Y.
	 * Si el Y del disparo coincide con 0, detiene el disparo. Si el disparo inpacta la nave 2, detiene el
	 * disparo y si la nave 2 no tiene escudo activo finaliza la partida. <br>
	 * <b> pre : </b> nave2 != null <br>
	 * <b> pre : </b> nave2.getDisparo() != null <br>
	 * <b> pre : </b> getAltoPanel () > 0 <br>
	 */
	public void moverDisparo(){
		super.moverDisparo();
		if (getNave().isDisparando()){
			int x1 = getNave().getDisparo().getPosX();
			int y1 = getNave().getDisparo().getPosY();
			if (nave2.recibioDisparo(x1, x1 + Disparo.ANCHO_MISILES, y1, y1 + Disparo.ALTO_MISILES)){
				if (!nave2.escudoActivo()){
					setEnPartida(false);
				}
				getNave().detenerDisparo();
			}
		}
		//TODO contrato
	}
	
	/**
	 * Descripcion: Se encarga de mover el disparo de la nave segundaria aumentandole dos pixeles a su pocision Y.
	 * Si el Y del disparo coincide con el alto del panel, detiene el disparo. Si el disparo inpacta la nave 1, detiene el
	 * disparo y si la nave 1 no tiene escudo activo finaliza la partida. <br>
	 * <b> pre : </b> nave2 != null <br>
	 * <b> pre : </b> nave2.getDisparo() != null <br>
	 * <b> pre : </b> getAltoPanel () > 0 <br>
	 */
	public void moverDisparoNave2 (){
		nave2.getDisparo().mover(0, Disparo.MOVIMIENTO_DISPARO, getAltoPanel(), getAnchoPanel());
		if (nave2.isDisparando() && (nave2.getDisparo().getPosY() == getAltoPanel())){
			nave2.detenerDisparo();
		}
		if (nave2.isDisparando()){
			int x1 = nave2.getDisparo().getPosX();
			int y1 = nave2.getDisparo().getPosY();
			if (getNave().recibioDisparo(x1, x1 + Disparo.ANCHO_MISILES, y1, y1 + Disparo.ALTO_MISILES)){
				if (!getNave().escudoActivo()){
					setEnPartida(false);
				}
				nave2.detenerDisparo();
			}
		}
	}
	
	/**
	 * Descripcion: Comienza el movimiento de la nave 2, es decir, indica que la nave 2 se esta moviendo. <br>
	 * <b> pre : </b> nave2 != null <br>
	 * <b> post : </b> nave2.isEnMovimiento () == true <br>
	 */
	public void empezarMovimientoNave2(){
		nave2.setEnMovimiento(true);
	}
	
	/**
	 * Descripcion: Detiene el movimiento de la nave 2, es decir, indica que la nave 2 no se esta moviendo. <br>
	 * <b> pre : </b> nave2 != null <br>
	 * <b> post : </b> nave2.isEnMovimiento() == false <br>
	 */
	public void detenerNave2(){
		nave2.setEnMovimiento(false);
	}
	
	/**
	 * Descripcion: Intenta mover la nave del usuario 2 hacia la derecha. Para hacerlo, agrega
	 * a su pocision en X el valor de la constante definida en la clase NaveUsuario. <br>
	 * <b> pre : </b> nave2 != null <br>
	 * <b> post : </b> nave2.getPosX() + nave2.getLargo() <= anchoPanel <br>
	 */
	public void moverNave2Derecha (){
		nave2.mover(NaveUsuario.MOVIMIENTO, 0, getAltoPanel(), getAnchoPanel());
	}
	
	/**
	 * Descripcion: Intenta mover la nave del usuario 2 hacia la izquierda. Para hacerlo, remueve
	 * a su pocision en X el valor de la constante definida en la clase NaveUsuario. <br>
	 * <b> pre : </b> nave2 != null <br>
	 * <b> post : </b> nave2.getPosX() >= 0 <br>
	 */
	public void moverNave2Izquierda (){
		nave2.mover(-NaveUsuario.MOVIMIENTO, 0, getAltoPanel(), getAnchoPanel());
	}
	
	/**
	 * Descripcion: Ejecuta un disparo de la nave del usuario 2, esto es, inicializa la relacion hacia
	 * la clase Disparo de la nave con una nueva instancia de Disparo e indica que la nave se encuentra disparando. <br>
	 * <b> pre : </b> nave2 != null <br>
	 * <b> post : </b> nave2.isDisparando() == true <br>
	 * <b> post : </b> nave2.getDisparo() != null <br>
	 */
	public void dispararNave2(){
		nave2.disparo2();
	}
	
	/**
	 * Descripcion: Detiene el disparo de la nave del usuario 2. Esto es, indica que la nave
	 * ya no se encuentra disparando y define la relacion de la nave hacia Disparo como null. <br>
	 * <b> pre </b> nave != null <br>
	 * <b> post : </b> nave2.isDisparando == false <br>
	 * <b> post : </b> nave2.getDisparo() == null <br>
	 */
	public void detenerDisparo2(){
		nave2.detenerDisparo();
	}

	/**
	 * Retorna la nave del usuario 2. <br>
	 * <b> pre : </b> nave2 != null <br>
	 * @return nave2
	 */
	public NaveUsuario getNave2() {
		return nave2;
	}


	/**
	 * Modifica la nave del usuario 2. 
	 * @param nave La nueva nave del usuario 2. nave2 != null 
	 */
	public void setNave2(NaveUsuario nave2) {
		this.nave2 = nave2;
	}
	
	

	

}
