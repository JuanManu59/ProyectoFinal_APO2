package mundo;

/**
 * Clase que representa una nave manejada por el usuario. Extiende de Nave e implementa las interfaces Disparador y Escudo.
 *
 */
public  class NaveUsuario extends Nave implements Disparador, Escudo{
	
	//HACER PRUEBAS
	/**
	 * Constante que representa cuantos pixeles se movera la nave por cada movimiento.
	 */
	public final static int MOVIMIENTO = 2;
	
	/**
	 * Constante que representa los milisegundos que debera esperar el hilo encargado de mover la nave entre cada movimiento.
	 */
	public final static int ESPERA_HILO = 5;
	
	/**
	 * Constante que indica el maximo de disparos que puede recibir el escudo de la nave.
	 */
	public final static int MAX_DISPAROS = 3;

	
	
	/**
	 * Atributo booleano que indica si la nave actual se esta moviendo o no.
	 */
	private boolean enMovimiento;
	
	/**
	 * Atributo booleano que indica si la nave actual esta disparando o no.
	 */
	private boolean disparando;
	
	/**
	 * Relacion hacia la clase Disparo con el disparo de la nave actual.
	 */
	private Disparo disparo;
	
	/**
	 * El numero de disparos que ha recibido la nave actual.
	 */
	private int disparosRecibidos;
	
	
	/**
	 * Crea una nueva instancia de la clase NaveUsuario llamando al constructor de la clase padre.
	  * @param posX La pocision x donde se comenzara a dibujar la nave. posX >= 0.
	 * @param posY La pocision y donde se comenzara a dibujar la nave. posY >= 0.
	 * @param rutaImagen La ruta de la imagen correspondiente a la nave actual. rutaImagen != null. rutaImagen != "".
	 * @param largo El largo de la nave actual en pixeles. largo > 0.
	 * @param ancho El ancho de la nave actual en pixeles. ancho > 0.
	 */
	public NaveUsuario(int posX, int posY, String rutaImagen, int largo, int ancho) {
		super(posX, posY, rutaImagen, largo, ancho);
		enMovimiento = false;
		disparando = false;
		disparo = null;
		disparosRecibidos = 0;
	}

	
	/**
	 * Retorna si la nave actual se encuentra en movimiento o no.
	 * @return true si esta en movimiento. false en caso contrario.
	 */
	public boolean isEnMovimiento() {
		return enMovimiento;
	}
	
	/**
	 * Modifica el valor del atributo enMovimiento de la nave.
	 * @param enMovimiento El nuevo valor del atributo enMovimiento.
	 */
	public void setEnMovimiento(boolean enMovimiento) {
		this.enMovimiento = enMovimiento;
	}

	
	@Override
	/**
	 * Descripcion: metodo que se encarga de desplazar la nave aliada en el eje x segun el valor pasado en x, siempre
	 * y cuando no se salga de las dimensiones del panel.
	 * @param x Los pixeles para los que se quiere mover el objeto en el eje x
	 * @param y No tiene importancia en este caso.
	 * @param altoPanel El alto del panel en el que se quiere mover el objeto
	 * @param anchoPanel El ancho del panel en el que se quiere mover el objeto
	 */
	public void mover(int x, int y, int altoPanel, int anchoPanel) {
		if ((getPosX() > 0 && x < 0) || (getPosX() + getLargo() < anchoPanel  && x > 0)){
			super.mover(x, 0, altoPanel, anchoPanel);
		}
		//TODO contrato
	}

	@Override
	/**
	 * Ejecuta un nuevo disparo. Esto es, inicializa una relacion hacia Disparo e indica que esta disparando. <br>
	 * <b> post : </b> disparando = true <br>
	 * <b> post : </b> disparo != null <br>
	 * @param probabilidadDisparo La probabilidad de que el disparo se ejecute. Por ejemplo, si probabilidadDisparo
	 * vale 10, hay 1/10 probabilidades de que se ejecute el disparo.
	 */
	public void disparar(int probablidadDisparo) {
		disparando = true;
		disparo = new Disparo((2*getPosX() + getLargo())/2, getPosY(), Disparo.DISPARO_ALIADO);
		//TODO contrato
	}
	
	/**
	 * Descripcion: Ejecuta un disparo en caso de que la nave actual corresponda al jugador 2 en el Multiplayer. <br>
	 * <b> post : </b> disparando = true <br>
	 * <b> post : </b> disparo != null <br>
	 */
	public void disparo2(){
		disparando = true;
		disparo = new Disparo((2*getPosX() + getLargo())/2, getPosY(), Disparo.DISPARO_ENEMIGO);
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
	
	@Override 
	/**
	 * Descripcion: Indica si la nave actual recibio un disparo con pocision indicada. Se considera que la nave
	 * recibio el disparo  si: el extremo superior o inferior del disparo esta entre el extremo superior e inferior de la nave y
	 * el extremo izquierdo o derecho del disparo esta entre el extremo izquierdo y derecho de la nave. Si recibe el disparo,
	 * aumenta 1 a sus disparos recibidos. 
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
		}
		return retorno;
		//TODO contrato
	}
	
	/**
	 * Indica si la nave actual se encuentra disparando o no.
	 * @return true si esta disparando, false en caso contrario.
	 */
	public boolean isDisparando() {
		return disparando;
	}

	
	/**
	 * Modifica el valor del atributo disparando de la nave.
	 * @param disparando El nuevo valor del atributo disparando.
	 */
	public void setDisparando(boolean disparando) {
		this.disparando = disparando;
	}
	
	/**
	 * Retorna la relacion hacia la clase Disparo de la nave actual.
	 * @return disparo
	 */
	public Disparo getDisparo() {
		return disparo;
	}
	
	/**
	 * Modifica la relacion hacia la clase Disparo de la nave actual.
	 * @param disparo La nueva relacion hacia la clase Disparo.
	 */
	public void setDisparo(Disparo disparo) {
		this.disparo = disparo;
	}

	/**
	 * Retorna el numero de disparos recibids por la nave actual. <br>
	 * <b> pre : </b> disparosRecibidos >= 0 <br>
	 * @return disparosRecibidos
	 */
	public int getDisparosRecibidos() {
		return disparosRecibidos;
	}

	/**
	 * Modifica el numero de disparos recibidos por la nave actual.
	 * @param disparosRecibidos El nuevo numero de disparos recibidos por la nave. disparosRecibidos >= 0.
	 */
	public void setDisparosRecibidos(int disparosRecibidos) {
		this.disparosRecibidos = disparosRecibidos;
	}

	@Override
	/**
	 * Indica si el escudo de la nave esta activo o no. Una nave tiene escudo activo hasta que reciba cierto numero
	 * de disparos, es decir, si ha recibido menos de 3 disparos.
	 * @return true si el escudo esta activo. false en caso contrario.
	 */
	public boolean escudoActivo() {
		System.out.println(disparosRecibidos + "");
		return disparosRecibidos < MAX_DISPAROS;
		//TODO contrato
	}
	
}
