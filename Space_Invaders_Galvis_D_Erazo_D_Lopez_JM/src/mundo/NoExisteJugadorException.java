package mundo;

/**
 * Clase que representa una excepcion cuando se intenta buscar un jugador que no esta registrado. Extiende de Exception.
 *
 */
public class NoExisteJugadorException extends Exception{
	
	/**
	 * Crea una nueva instancia de NoExisteJugadorException, llamando al constructor de la clase padre y pasandole
	 * una cadena de caracteres como parametro.
	 * @param mensaje El mensaje que se le asignara a la excepcion y se le pasara por parametro al constructor de la clase padre.
	 * mensaje != null. mensaje != "".
	 */
	public NoExisteJugadorException(String mensaje){
		super(mensaje);
	}
}
