package mundo;

/**
 * 
 * Interfaz que contiene los metodos necesarios para disparar.
 *
 */
public interface Disparador {
	
	/**
	 * Ejecuta el disparo segun el caso. En ciertas ocasiones, solo lo hace en ciertas ocasiones
	 * de acuerdo con la probabilidad.
	 * @param probabilidadDisparo La probabilidad de que el disparo se ejecute. Por ejemplo, si probabilidadDisparo
	 * vale 10, hay 1/10 probabilidades de que se ejecute el disparo.
	 */
	public void disparar (int probabilidadDisparo);
	
	/**
	 * Detiene el disparo que se esta ejecutando.
	 */
	public void detenerDisparo();
}
