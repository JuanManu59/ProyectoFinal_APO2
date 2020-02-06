package mundo;
/**
 * Interfaz que representa el escudo de una nave.
 *
 */
public interface Escudo {
	/**
	 * Indica si el escudo de la nave esta activo o no. Una nave tiene escudo activo hasta que reciba cierto numero
	 * de disparos.
	 * @return true si el escudo esta activo. false en caso contrario.
	 */
	public boolean escudoActivo();
}
