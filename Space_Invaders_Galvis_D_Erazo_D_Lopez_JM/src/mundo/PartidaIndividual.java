package mundo;

/**
 * Clase que representa una partida individual. Extiende de Partida.
 *
 */
public class PartidaIndividual extends Partida{
	
	/**
	 * Constante que representa la probabilidad inicial de que una nave enemiga disparadora dispare cada
	 * vez que se le solicite disparar.
	 * (Es decir, 1/30).
	 */
	public final static int PROBABILIDAD_INICIAL = 30;
	
	/**
	 * Constante que representa la espera inicial en milisegundos del hilo encargado de mover las naves
	 * enemigas entre cada movimiento.
	 */
	public final static int ESPERA_INICIAL = 1000;
	
	/**
	 * Constante que representa los puntos que otorga eliminar una nave normal enemiga.
	 */
	public final static int PUNTAJE_NORMAL = 10;
	
	/**
	 * Constante que representa los puntos que otorga eliminar una nave disparadora enemiga. 
	 */
	public final static int PUNTAJE_DISPARADOR = 15;
	
	/**
	 * Constante que representa los puntos que otorga eliminar una nave con escudo enemiga.
	 */
	public final static int PUNTAJE_ESCUDO = 20;

	
	
	
	/**
	 * Relacion hacia NaveEnemiga con la primera nave enemiga de la lista enlazada.
	 */
	private NaveEnemiga primera;
	
	/**
	 * Atributo que representa los milisegundos de espera del hilo encargado de mover las naves enemigas entre cada movimiento.
	 */
	private long esperaHiloNavesEnemigas;
	
	/**
	 * La probabilidad de que una nave enemiga disparadora dispare cuando se le solicite.
	 */
	private int probablidadDisparo;
	
	/**
	 * Puntos acumulados en la partida.
	 */
	private int puntaje;
	
	
	/**
	 * Crea una nueva instancia de PartidaIndividual llamando el constructor de la clase padre. Inicializa las
	 * naves enemigas, asigna a esperaHiloNavesEnemigas la constante definida en esta clase, al igual que con
	 * probabilidadDisparo, e inicializa puntaje como 0. <br>
	 * <b> post : </b> primera != null <br>
	 * @param anchoPanel El ancho del panel donde se jugara la partida. anchoPanel > 0.
	 * @param altoPanel El alto del panel donde se jugara la partida. altoPanel > 0.
	 */
	public PartidaIndividual (int anchoPanel, int altoPanel){
		super(anchoPanel, altoPanel);
		inicializarNaves();
		esperaHiloNavesEnemigas = ESPERA_INICIAL;
		probablidadDisparo = PROBABILIDAD_INICIAL;
		puntaje = 0;
	}

	/**
	 * Retorna la probabilidad que una nave disparadora enemiga dispare cuando se le solicite. <br>
	 * <b> pre : </b> probablidadDisparo > 0 <br>
	 * @return probabilidadDisparo
	 */
	public int getProbablidadDisparo() {
		return probablidadDisparo;
	}

	/**
	 * Modifica la probabilidad que una nave disparadora enemiga dispare cuando se le solicite. <br>
	 * @param probablidadDisparo La nueva probabilidad de disparo enemigo. probablidadDisparo > 0. 
	 */
	public void setProbablidadDisparo(int probablidadDisparo) {
		this.probablidadDisparo = probablidadDisparo;
	}

	/**
	 * Retorna el puntaje actual de la partida.
	 * @return puntaje
	 */
	public int getPuntaje() {
		return puntaje;
	}

	/**
	 * Modifica el puntaje actual de la partida. 
	 * @param puntaje El nuevo puntaje de la partida. puntaje >= 0.
	 */
	public void setPuntaje(int puntaje) {
		this.puntaje = puntaje;
	}

	/**
	 * Retorna la espera del hilo encargado de mover las naves enemigas entre cada movimiento. <br>
	 * <b> pre : </b> esperaHiloNavesEnemigas > 0 <br>
	 * @return  esperaHiloNavesEnemigas 
	 */
	public long getEsperaHiloNavesEnemigas() {
		return esperaHiloNavesEnemigas;
	}

	/**
	 * Modifica la espera del hilo encargado de mover las naves enemigas entre cada movimiento. <br>
	 * @param esperaHiloNavesEnemigas La nueva espera del hilo. esperaHiloNavesEnemigas > 0
	 */
	public void setEsperaHiloNavesEnemigas(long esperaHiloNavesEnemigas) {
		this.esperaHiloNavesEnemigas = esperaHiloNavesEnemigas;
	}

	/**
	 * Descripcion: Agrega una nueva nave enemiga a la lista enlazada de naves enemigas. Si no hay ninguna nave en la lista, asigna la nave a 
	 * agregar como la primera. En caso contrario, la agrega al final de la lista. <br>
	 * <b> post : </b> primera != null <br>
	 * @param n La nave enemiga que se quiere agregar a la lista. n != null.
	 */
	public void agregarNave(NaveEnemiga n){
		if (primera == null){
			primera = n;
		} else {
			NaveEnemiga actual = primera;
			while (actual.getSiguiente() != null){
				actual = actual.getSiguiente();
			}
			actual.setSiguiente(n);
		}
	}
	
	/**
	 * Descripcion: Se encarga de inicializar las naves enemigas presentes en la partida. Para hacerlo, crea 5 instancias
	 * de NaveEscudo, cada una con la misma pocision en Y (145) y separadas de 52 pixeles en X. Lo mismo se hace con la instanciacion
	 * de las 5 NaveNormal y las 5 NaveDisparadora. las NaveNormal se inicializan en una pocision Y 90 mientras que las NaveDisparo de 40.
	 * Cada vez que se inicializa una nave, se agrega a la lista enlazada de naves enemigas. <br>
	 * <b> post : </b> primera != null <br>
	 * <b> post : </b> La lista enlazada de naves enemigas contiene 15 elementos. <br>
	 */
	public void inicializarNaves (){
		for (int i = 0; i < 5; i++){
			NaveEscudo n =  new NaveEscudo(i*52, 145, "./data/imagenes/Imagen_NaveEnemiga.png", 52, 45, false, NaveEnemiga.DERECHA);
			agregarNave (n);
		}
		for (int i = 0; i < 5; i++){
			NaveNormal n = new NaveNormal(i*52, 90, "./data/imagenes/space.png", 52, 52, false, NaveEnemiga.DERECHA);
			agregarNave (n);
		}
		for (int i = 0; i < 5; i++){
			NaveDisparadora n = new NaveDisparadora(i*52, 40, "./data/imagenes/nave_disparadora.jpg", 52, 41, false, NaveEnemiga.DERECHA);
			agregarNave(n);
		}
	}
	
	
	/**
	 * Descripcion: Metodo encargado de mover las naves enemigas. Para lograrlo, recorre la lista enlazada de naves
	 * enemigas y a todas les solocita desplazarse en X la constante definida en la clase NaveEnemiga (Si le reduce o aumenta al valor en
	 * X depende de su direccion) En caso de que alguna de las naves se salga de las dimensiones del panel en el que se esta llevando a cabo la
	 * partida, devuelve la nave a su pocision original, la hace desplazarse en Y y cambia su direccion. Si la nave que se esta moviendo es una
	 * NaveDisparadora, le solicita de disparar. Si alguna de las naves llega a la pocision de la parte superior de la nave del usuario, finaliza la partida. <br>
	 * <b> pre : </b> primera != null <br>
	 * <b> pre : </b> getAltoPanel () > 0 <br>
	 * <b> pre : </b> getAnchoPanel () > 0 <br>
	 * <b> pre : </b> alto y ancho de Nave > 0 <br>
	 */
	public void moverNaves(){
		NaveEnemiga actual = primera;
		while (actual != null){
			if (!actual.isEliminada()){
				actual.mover(NaveEnemiga.MOVIMIENTO, 0, getAltoPanel(), getAnchoPanel());
				if (actual instanceof NaveDisparadora){
					NaveDisparadora d = (NaveDisparadora) actual;
					d.disparar(probablidadDisparo);
				}
				if (actual.getPosY() + actual.getAlto() >= getAltoPanel() - getNave().getAlto()){
					 setEnPartida(false);
				}
			}
			actual = actual.getSiguiente();
		}
	}
	
	/**
	 * Descripcion: indica si continuar en el nivel actual. El nivel se continua si al
	 * menos una de las naves enemigas no esta eliminada.
	 * <b> pre : </b> primera != null <br>
	 * @return
	 */
	public boolean continuar(){
		boolean continuar = false;
		NaveEnemiga  actual = primera;
		while(actual!=null && !continuar){
			continuar = !actual.isEliminada();
			actual = actual.getSiguiente();
		}
		return continuar;
	}
	
	
	@Override
	/**
	 * Descripcion: se encarga de mover el disparo de la nave actual. Para moverla, le reduce a sus coordenadas en Y
	 * el valor correspondiente al definido en la constante de la clase Disparo. Si el extremo inferior del disparo (posY + altoMisil)
	 * llega a la pocision 0, se detiene el disparo. Si impacta a un enemigo, elimina el disparo. Si elimina al enemigo, se suma al
	 * puntaje de la partida lo que vale dicho enemigo. <br>
	 * <b> pre : </b> nave != null <br>
	 * <b> pre : </b> nave.getDisparo () != null <br>
	 */	public void moverDisparo (){
		super.moverDisparo();
		
		NaveEnemiga actual = primera;		
		while(actual!=null && getNave().isDisparando()){
				
			if(!actual.isEliminada()){
				int x1 = getNave().getDisparo().getPosX();
				int y1 = getNave().getDisparo().getPosY();		
					
				if(actual.recibioDisparo(x1, x1+Disparo.ANCHO_MISILES, y1, y1+Disparo.ALTO_MISILES)){
					
					if (actual instanceof NaveNormal){
						puntaje += PUNTAJE_NORMAL;
						} else if (actual instanceof NaveDisparadora){
							puntaje += PUNTAJE_DISPARADOR;
						} else {
							NaveEscudo e = (NaveEscudo) actual;
							if (!e.escudoActivo()){
								puntaje += PUNTAJE_ESCUDO;
							}
						}
					getNave().detenerDisparo();
					}
				}
				actual = actual.getSiguiente();
			}
			if (!continuar()){
				avanzarNivel();
			}
			//TODO contrato
	}
	
	/**
	 * Descripcion: Se encarga de mover el disparo de la NaveDisparadora pasado por parametro en
	 * caso de que la nave este disparando. Si esta disparando y la nave del usuario recibe el disparo o 
	 * su pocision en y queda igual al alto del panel donde se esta llevando a cabo la partida, detiene su disparo.
	 * Si la nave del usuario recibe el disparo y no tiene el escudo activo, detiene la partida. <br>
	 * <b> pre : </b> getNave () != null <br>
	 * <b> pre : </b> getAltoPanel () > 0 <br>
	 * <b> pre : </b> getAnchoPanel () > 0 <br>
	 * @param n La NaveDisparadora para la cual se quiere mover su disparo. n != null.
	 */
	public void moverDisparoEnemigo(NaveDisparadora n){
		if (n.isDisparando()){
			n.getDisparo().mover(0, Disparo.MOVIMIENTO_DISPARO, getAltoPanel(), getAnchoPanel());
			int x1 = n.getDisparo().getPosX();
			int y1 = n.getDisparo().getPosY();
			if (getNave().recibioDisparo(x1, x1 + Disparo.ANCHO_MISILES, y1, y1 + Disparo.ALTO_MISILES)){
				if (!getNave().escudoActivo()){
					setEnPartida(false);
				}
				n.detenerDisparo();
			}
		}
		if (n.isDisparando() && (n.getDisparo().getPosY() == getAltoPanel())){
			n.detenerDisparo();
		}
	}
	
	/**
	 * Descripcion: Se encarga de mover los disparos enemigos de todas las naves disparadoras
	 * que se encuentran disparando. Si alguno de los disparos impacta a la nave del usuario o su pocision en Y
	 * iguala la del alto del panel, detiene el disparo de dicha nave. Si alguno de los disparos impacta la nave del
	 * usuario y ya no tiene el escudo activo, finaliza la partida. <br>
	 * <b> pre : </b> primera != null <br>
	 */
	public void moverDisparosEnemigos (){
		NaveEnemiga actual = primera;
		
		while (actual != null){
			if (!actual.isEliminada() && actual instanceof NaveDisparadora){
				NaveDisparadora d = (NaveDisparadora) actual;
				moverDisparoEnemigo(d);
			}
			actual = actual.getSiguiente();
		}
	}
	
	/**
	 * Descripcion: Avanza de nivel en la partida. Es decir, reestablece todas las naves a su pocision original,
	 * a las enemigas les indica que ninguna esta eliminada, a las NaveEscudo les indica que han recibido 0 disparos y detiene el
	 * disparo de todas las naves disparadoras. Ademas, multiplica la probabilidad de disparo enemigo por 0.95 y la espera del hilo de naves enemigas por 0.75. <br>
	 *  <b> pre : </b> getNave() != null <br>
	 */
	public void avanzarNivel(){
		probablidadDisparo *= 0.95;
		esperaHiloNavesEnemigas *= 0.75;
		reestablecerNaves();
	}
	
	/**
	 * Reestablece todas las naves a su pocision original, a las enemigas les indica que ninguna esta eliminada,
	 * a las NaveEscudo les indica que han recibido 0 disparos y detiene el disparo de todas las naves disparadoras.
	 * <b> pre : </b> getNave () != null <br>
	 */
	public void reestablecerNaves (){
		NaveEnemiga actual = primera;
		while (actual != null){
			actual.setEliminada(false);
			actual.setPosX(actual.getxOriginal());
			actual.setPosY(actual.getyOriginal());
			actual.setDireccion(Nave.DERECHA);
			if (actual instanceof NaveDisparadora){
				NaveDisparadora d = (NaveDisparadora) actual;
				d.setDisparando(false);
				d.setDisparo(null);
			} else if (actual instanceof NaveEscudo){
				((NaveEscudo) actual).setDisparosRecibidos(0);
			}
			actual = actual.getSiguiente();
		}
		getNave().setPosX(getNave().getxOriginal());
		getNave().setPosY(getNave().getyOriginal());
	}
	
	/**
	 * Descripcion: metodo que se encarga de contar el total de navaes enemigas en la lista enlazada
	 * @return numero de naves enemigas.
	 */
	public int contadorNaves(){
		int cont = 0;
		NaveEnemiga actual = primera;
		while (actual != null){
			actual = actual.getSiguiente();
			cont++;
		}
		return cont;
	}
	
	/**
	 * Retorna la primera NaveEnemiga de la lista enlazada.
	 * @return primera
	 */
	public NaveEnemiga getPrimera() {
		return primera;
	}

	/**
	 * Modifica la primera NaveEnemiga de la lista enlazada. 
	 * @param primera La nueva primera nave de la lista enlazada. primera != null.
	 */
	public void setPrimera(NaveEnemiga primera) {
		this.primera = primera;
	}
}
