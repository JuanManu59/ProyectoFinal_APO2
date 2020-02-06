package mundo;

/**
 * Clase que representa una excepcion lanzada cuando un jugador no tiene ningun puntaje registrado entre los mejores puntajes. Extiende de la clase Exception.
 *
 */
public class NoEstaEnMejoresPuntajesException extends Exception{
	
	/**
	 * Crea una nueva instancia de la excepcion NoEstaEnMejoresPuntajesException, llamando al constructor de la clase padre
	 * y pasandole por parametro una cadena diciendo que en jugador no tiene ningun puntaje dentro del top.
	 * @param nickJugador El nickname del jugador que no tiene ningun puntaje dentro del top. nickJugador != null. nickJugador != "".
	 */
	public NoEstaEnMejoresPuntajesException(String nickJugador){
		super("El jugador " + nickJugador + " no tiene ningún puntaje dentro del top "
				+ MenuPrincipal.TOP_PUNTAJES);
	}
}
