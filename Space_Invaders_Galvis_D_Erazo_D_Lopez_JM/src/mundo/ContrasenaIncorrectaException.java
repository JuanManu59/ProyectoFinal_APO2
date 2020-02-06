package mundo;

/**
 * Clase que representa una excepcion lanzada por ingresar una contraseña que no coincide con la del usuario.
 *
 */
public class ContrasenaIncorrectaException extends Exception{
	
	/**
	 * Crea una nueva instancia de la excepcion correspondiente a esta clase, invocando el constructor de la clase
	 * padre y pasandole por parametro un mensaje informandole del problema.
	 */
	public ContrasenaIncorrectaException() {
		super("La contraseña ingresada no coincide con el usuario con el que se quiere ingresar.");
	}
}
