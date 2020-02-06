package mundo;

import java.io.Serializable;

/**
 * Clase que contiene los datos de un jugador registrado e implementa la interfaz Serializable.
 *
 */
public class Jugador implements Serializable{
	
	/**
	 * Cadena de caracteres que representa el nickname del jugador.
	 */
	private String nickname;
	
	/**
	 * Cadena de caracteres con el nombre del jugador.
	 */
	private String nombre;
	
	/**
	 * Cadena de caracteres con la contraseña del jugador.
	 */
	private String contrasena;
	
	/**
	 * Entero que representa la edad del jugador.
	 */
	private int edad;
	
	/**
	 * Primer jugador del subarbol izquierdo del jugador actual
	 */
	private Jugador izquierda;
	
	/**
	 * Primer jugador del subarbol derecho del jugador actual.
	 */
	private Jugador derecha;
	
	/**
	 * Descripcion: crea una nueva instancia de la clase Jugador.
	 * @param nickname El nickname que se le otorga al nuevo jugador. nickname != null. nickname != ""
	 * @param nombre El nombre del nuevo jugador. nombre != null. nombre != ""
	 * @param contraseña La contraseña con la que se registrara el nuevo jugador. contraseña != null. contraseña != ""
	 * @param edad La edad del jugador. edad > 0.
	 */
	public Jugador (String nickname, String nombre, String contraseña, int edad){
		this.nombre = nombre;
		this.contrasena = contraseña;
		this.nickname = nickname;
		this.edad = edad;
	}
	
	/**
	 * 
	 * Retorna el nombre con el que esta registrado el usuario.
	 * @return nombre
	 */
	public String getNombre() {
		return nombre;
	}
	
	/**
	 * Modifica el nombre con el que esta registrado el usuario.
	 * @param nombre el nuevo nombre del usuario. nombre != null. nombre != "".
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	/**
	 * Retorna la contraseña con la que esta registrado el usuario
	 * @return contraseña
	 */
	public String getContraseña() {
		return contrasena;
	}
	
	/**
	 * Modifica la contraseña con la que esta registrado el usuario.
	 * @param contraseña La nueva contraseña del usuario. contraseña != null. contraseña != "".
	 */
	public void setContraseña(String contraseña) {
		this.contrasena = contraseña;
	}
	
	
	/**
	 * Retorna el nickname del jugador actual.
	 * @return nickname.
	 */
	public String getNickname(){
		return nickname;
	}
	
	/**
	 * Modifica el nickname del jugador actual.
	 * @param nickname El nuevo nickname del jugador. nickname != null. nickname != "".
	 */
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	
	/**
	 * Retorna la edad del jugador actual.
	 * @return edad.
	 */
	public int getEdad() {
		return edad;
	}
	
	/**
	 * Modifica la edad del jugador actual.
	 * @param edad La nueva edad del jugador. edad > 0
	 */
	public void setEdad(int edad) {
		this.edad = edad;
	}
	
	/**
	 * Retorna el primer jugador del subarbol izquierdo del jugador actual.
	 * @return izquierda
	 */
	public Jugador getIzquierda() {
		return izquierda;
	}
	
	/**
	 * Modifica el primer jugador del subarbol izquierdo del jugador actual.
	 * @param izquierda El nuevo jugador del subarbol izquierdo del jugador actual.
	 */
	public void setIzquierda(Jugador izquierda) {
		this.izquierda = izquierda;
	}
	
	/**
	 * Retorna el primer jugador del subarbol derecho del jugador actual.
	 * @return derecha
	 */
	public Jugador getDerecha() {
		return derecha;
	}
	
	/**
	 * Modifica el primer jugador del subarbol derecho del jugador actual.
	 * @param izquierda El nuevo jugador del subarbol derecho del jugador actual.
	 */
	public void setDerecha(Jugador derecha) {
		this.derecha = derecha;
	}
	
	/**
	 * Descripcion: Metodo recursivo que se encarga de agregar un nuevo jugador al ABB en la pocision que le corresponde basandose en
	 * el nickname, siempre y cuando no exista ningun otro jugador con el mismo nickname. Si el nick del jugador es menor, debe agregarse a la izquierda
	 * del actual hasta que encuentre una pocision nula, si es mayor, lo agrega a la derecha hasta que encuentre una pocision nula, si son iguales no lo agrega.
	 * <b> post : </b> izquierda != null || derecha != null <br>
	 * @param nuevo El nuevo jugador que se quiere agregar al arbol. nuevo != null.
	 * @throws JugadorYaExisteException Si encuentra un jugador con el mismo nick del que se quiere agregar, lanza excepcion informando que ya hay un jugador
	 * registrado con dicho nickname.
	 */
	public void anadirJugador (Jugador nuevo) throws JugadorYaExisteException{
		if (nuevo.nickname.equals(nickname)){
			throw new JugadorYaExisteException("Ya existe un jugador con el nickname " + nickname);
		} else if (nuevo.nickname.compareTo(nickname) > 0){
			if (derecha == null){
				derecha = nuevo;
			} else {
				derecha.anadirJugador(nuevo);
			}
		} else {
			if (izquierda == null){
				izquierda = nuevo;
			} else {
				izquierda.anadirJugador(nuevo);
			}
		}
	}
	
	
	
	
	
}
