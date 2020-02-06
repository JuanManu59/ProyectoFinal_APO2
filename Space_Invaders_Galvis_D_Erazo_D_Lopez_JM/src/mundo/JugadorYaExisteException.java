package mundo;

/**
 * 
 * Clase que representa una excepcion en caso de que ya exista un jugador con el nickname del que se quiere agregar.
 *
 */
public class JugadorYaExisteException extends Exception{
	
	/**
	 * Crea una nueva instancia de la la excepcion JugadorYaExisteExcepcion, llamando al constructor de la clase padre (Exception).
	 * @param mensaje El mensaje que se le otorgara a la excepcion.
	 */
	public JugadorYaExisteException(String mensaje){
		super(mensaje);
	}
}
