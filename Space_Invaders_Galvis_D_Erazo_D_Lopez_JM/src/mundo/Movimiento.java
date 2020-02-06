package mundo;

/**
 * Interfaz que contiene el metodo encargado del movimiento.
 *
 */
public interface Movimiento {
	
	/**
	 * Descripcion: metodo que se encarga de desplazar el objeto que lo invoca segun los
	 * paramteros recibidos,
	 * @param x Los pixeles para los que se quiere mover el objeto en el eje x
	 * @param y Los pixeles para los que se quiere mover el objeto en el eje y
	 * @param altoPanel El alto del panel en el que se quiere mover el objeto
	 * @param anchoPanel El ancho del panel en el que se quiere mover el objeto
	 */
	public void mover (int x, int y, int altoPanel, int anchoPanel);
}
