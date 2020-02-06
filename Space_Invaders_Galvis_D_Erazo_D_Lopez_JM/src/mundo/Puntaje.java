package mundo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Clase que representa toda la información de un puntaje.
 *
 */
public class Puntaje {

	/**
	 * Asociacion que representa el jugador al que pertenece el puntaje actual.
	 */
	private Jugador jugador;

	/**
	 * Atributo con los puntos registrados.
	 */
	private int puntos;

	/**
	 * Fecha en la que se obtuvo el puntaje.
	 */
	private Date fecha;

	/**
	 * Crea una nueva instancia de la clase Puntaje con la fecha actual.
	 * 
	 * @param jugador El jugador al que pertenece el puntaje. jugador != null.
	 * @param puntos  Los puntos que se registraron. puntos >= 0.
	 */
	public Puntaje(Jugador jugador, int puntos) {
		this.jugador = jugador;
		this.puntos = puntos;
		fecha = new Date();
	}

	/**
	 * Crea una nueva instancia de la clase Puntaje con los datos pasados por parametro.
	 * @param jugador El jugador al que le corresponde el Puntaje. jugador != null
	 * @param puntos Los puntos obtenidos en el puntaje. puntos > 0.
	 * @param fecha Cadena de caracteres con la fecha en la que se obtuvo el puntaje. La cadena debe
	 * de tener el siguiente formato: dd-MM-yyyy
	 */
	public Puntaje(Jugador jugador, int puntos, String fecha) {
		this.jugador = jugador;
		this.puntos = puntos;
		this.fecha = deFormatoADate(fecha);
	}

	/**
	 * Descripcion Devuelve una cadena de caracteres con la fecha correspondiente al
	 * puntaje actual con el siguiente formato: dd-MM-yyyy <br>
	 * <b> pre : </b> fecha != null <br>
	 * @return String con la fecha en el formato indicado.
	 */
	public String deDateAFormate() {
		SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
		return format.format(fecha);
	}

	/**
	 * Descripcion: Retorna un objeto de tipo Date que contiene la informacion de la
	 * fecha pasada por parametro como una cadena de caracteres.
	 * @param formato La fecha que se quiere convertir como una cadena de caracteres.
	 * Debe de tener el siguiente formato: dd-MM-yy 
	 * @return Objeto de tipo Date con la fecha correspondiente al String pasado por parametro.
	 */
	public Date deFormatoADate(String formato) {
		SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
		Date fecha = new Date();
		try {
			fecha = format.parse(formato);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return fecha;
	}

	/**
	 * Descripcion: Compara el Puntaje actual con otro puntaje según el valor del
	 * atributo "puntaje" de cada una de las dos instancias.
	 * 
	 * @param p
	 *            El Puntaje con el que se va a comparar el Puntaje actual.
	 * @return La diferencia entre el valor del atributo del puntaje actual y el del
	 *         pasado por parametro. Si p es null retorna 1.
	 */
	public int comparPuntos(Puntaje p) {
		int retorno = 0;
		if (p == null) {
			retorno = 1;
		} else {
			retorno = puntos - p.puntos;
		}
		return retorno;
	}

	/**
	 * Descripcion: Compara el Puntaje actual con otro puntaje segun el valor del
	 * atributo "nickname" del jugador al que pertenece cada uno de los dos
	 * puntajes.
	 * 
	 * @param p
	 *            El Puntaje con el que se va a comparar el Puntaje actual.
	 * @return El valor retornado por el metodo "compareToIgnoreCase" entre el nick
	 *         del jugador del puntaje actual en comparacion al nick del jugador al
	 *         que pertenece el Puntaje pasado por parametro. Si p es null, retorna
	 *         -1.
	 */
	public int compararNick(Puntaje p) {
		int retorno = 0;
		if (p == null) {
			retorno = -1;
		} else {
			retorno = jugador.getNickname().compareToIgnoreCase(p.jugador.getNickname());
		}

		return retorno;
	}

	/**
	 * Retorna el jugador al que pertenece el puntaje actual. <br>
	 * <b> pre : </b> jugador != null <br>
	 * 
	 * @return jugador
	 */
	public Jugador getJugador() {
		return jugador;
	}

	/**
	 * Modifica el jugador al que pertenece el puntaje actual.
	 * 
	 * @param jugador
	 *            El nuevo jugador al que pertenece el puntaje. jugador != null.
	 */
	public void setJugador(Jugador jugador) {
		this.jugador = jugador;
	}

	/**
	 * Retorna la cantidad de puntos registrados.
	 * 
	 * @return puntos.
	 */
	public int getPuntos() {
		return puntos;
	}

	/**
	 * Modifica los puntos registrados.
	 * 
	 * @param puntos
	 *            El nuevo puntaje registrado. puntos >= 0.
	 */
	public void setPuntos(int puntos) {
		this.puntos = puntos;
	}

	/**
	 * Retorna la fecha en la que se registró el puntaje.
	 * 
	 * @return fecha.
	 */
	public Date getFecha() {
		return fecha;
	}

	/**
	 * Modifica la fecha en la que se registró el puntaje.
	 * 
	 * @param fecha
	 *            La nueva fecha de registro. fecha != null.
	 */
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	/**
	 * Retorna el nick del jugador al que pertenece el puntaje. <br>
	 * <b> pre : </b> jugador != null <br>
	 * <b> pre : </b> jugador.getNickname() != null. <br>
	 * <b> pre : </b> jugador.getNickname() != "" <br>
	 * 
	 * @return el nick del jugador.
	 */
	public String getNickJugador() {
		return jugador.getNickname();
	}

	@Override
	public String toString() {
		String msn = jugador.getNickname() + " - " + puntos;
		return msn;
		// return "Puntaje [jugador=" + jugador.getNickname() + ", puntos=" + puntos +
		// "]";
	}

}
