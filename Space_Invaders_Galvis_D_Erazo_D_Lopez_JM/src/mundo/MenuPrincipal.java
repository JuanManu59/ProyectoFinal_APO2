package mundo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.swing.plaf.basic.BasicInternalFrameTitlePane.MaximizeAction;

/**
 * Clase que representa el menu principal del juego.
 *
 */
public class MenuPrincipal {
	/**
	 * Constante que representa el numero de puntajes que se guardaran.
	 */
	public final static int TOP_PUNTAJES = 10;
	
	
	/**
	 * Constante que representa la ruta o dirección donde se guardará un archivo de texto plano con los puntajes
	 */
	public static final String RUTA1 = "./data/partidasJuegos/puntajes/puntajes.txt";
	/**
	 * Constante que representa la ruta o direccion donde se serializará la partida actual
	 */
	public static final String RUTA2 = "./data/partidasJuegos/estadoJuegos/partidaSerializable";
	
	/**
	 * Constante que representa la ruta o direccion donde se serializarán los jugadores.
	 */
	public static final String RUTA3 = "./data/partidasJuegos/jugadores/jugadoresSerializable";
	
	/**
	 * Constante que representa la ruta donde se probara la persistencia de los puntajes
	 */
	public static final String RUTA1_TEST = "./testPersistencia/puntajes/puntajes.txt";
	
	/**
	 * Constante que representa la ruta donde se probara la persistencia de las partidas
	 */
	public static final String RUTA2_TEST = "./testPersistencia/estadoJuegos/partidaSerializable";
	
	/**
	 * Constante que representa la ruta donde se probara la persistencia de jugadores
	 */
	public static final String RUTA3_TEST = "./testPersistencia/jugadores/jugadoresSerializable";
	
	/**
	 * Arreglo de Puntaje con los mejores puntajes registrados. El tamaño esta definido
	 * por una costante de esta clase.
	 */
	private Puntaje [] mejoresPuntajes;
	
	/**
	 * Relacion con la partida que se encuentra activa (si hay una activa).
	 */
	private Partida partida;
	
	/**
	 * Asociacion hacia el jugador que representa la raiz del ABB de jugadores.
	 */
	private Jugador raiz;
	
	/**
	 * Asociacion hacia el jugador que esta logueado actualmente.
	 */
	private Jugador logueado;
	
	/**
	 * Crea una nueva instancia de la clase MenuPrincipal, inicializando el arreglo mejoresPuntajes con el tamaño
	 * de la constante definida en esta clase. Ademas, carga a los jugadores registrados y los mejores puntajes. <br>
	 * <b> post : </b> mejoresPuntajes != null <br>
	 * @throws IOException Si no logra leer los archivos de puntaje y / o jugadores, lanza excepcion
	 * @throws ClassNotFoundException Si no encuentra la clase Jugador al cargar los jugadores, lanza excepcion
	 */
	public MenuPrincipal () throws IOException, ClassNotFoundException{
		mejoresPuntajes = new Puntaje[TOP_PUNTAJES];
		cargarJugadores();	
		try {
			cargarPuntajesYnombres();
		} catch (IOException e) {
			guardarPuntajesYnombres();
		}
		//System.out.println(mejoresPuntajes [0].getNickJugador() + ": " + mejoresPuntajes[0].getPuntos());
		//System.out.println(mejoresPuntajes [1].getNickJugador() + ": " + mejoresPuntajes[1].getPuntos());
	}
	
	/**
	 * Retorna la partida con la cual tiene relacion el menu.
	 * @return partida.
	 */
	public Partida getPartida() {
		return partida;
	}
	
	/**
	 * Retorna la raiz del arbol binario de busqueda de Jugadores.
	 * @return raiz
	 */
	public Jugador getRaiz() {
		return raiz;
	}
	
	/**
	 * Retorna al jugador que se encuentra actualmente logueado.
	 * @return logueado
	 */
	public Jugador getLogeado() {
		return logueado;
	}
	
	
	/**
	 * Modifica la partida con la que el menu tiene relacion.
	 * @param partida La nueva partida con la que se tiene relacion.
	 */
	public void setPartida(Partida partida) {
		this.partida = partida;
	}
	
	/**
	 * Retorna el jugador que actualmente se encuentra logueado
	 * @return logueado
	 */
	public Jugador getLogueado() {
		return logueado;
	}

	/**
	 * Modifica el jugador que se encuentra logueado
	 * @param logueado El nuevo jugador logueado
	 */
	public void setLogueado(Jugador logueado) {
		this.logueado = logueado;
	}

	/**
	 * Modifica la raiz del ABB de jugadores
	 * @param raiz El nuevo jugador que sera la raiz del ABB.
	 */
	public void setRaiz(Jugador raiz) {
		this.raiz = raiz;
	}
	
	/**
	 * Descripcion: Ordena el arreglo mejoresPuntajes descendentemente segun el valor del atributo puntos de cada Puntaje. Si hay
	 * pocisiones nulas en el arreglo, las ubica al final del arreglo. <br>
	 * <b> pre : </b> mejoresPuntajes != null <br>
	 * <b> post : </b> El arreglo mejoresPuntajes se encuentra ordenado segun el valor de puntos de forma ascendente. Las pocisiones
	 * nulas se encuentran al final del arreglo. <br>
	 */
	public void ordenarPuntajesPorPuntos(){
		for (int i = 0; i < TOP_PUNTAJES; i++){
			if (mejoresPuntajes [i] != null){
			for (int j = i; j > 0 && mejoresPuntajes [j].comparPuntos(mejoresPuntajes[j-1]) > 0; j--){
				Puntaje temp = mejoresPuntajes [j];
				mejoresPuntajes [j] = mejoresPuntajes [j-1];
				mejoresPuntajes [j-1] = temp;
				}
				
			}
		}
	}
	
	/**
	 * Descripcion: Ordena el ArrayList de Puntaje descendentemente pasado por parametro segun el valor del atributo
	 * puntos de cada Puntaje dentro del ArrayList. <br>
	 * <b> post : </b> el ArrayList pasado por parametro se encuentra ordenado descendentemente segun los puntos de cada Puntaje. <br>
	 * @param puntos el ArrayList a ordenar. puntos != null. puntos.get(i) != null para 0 <= i < puntos.size() 
	 */
	public void ordenarPuntajesPorPuntos(ArrayList <Puntaje> puntos){
		for (int i = 0; i < puntos.size(); i++){	
			for (int j = i; j > 0 && puntos.get(j).comparPuntos(puntos.get(j-1)) > 0; j--){
				Puntaje temp = puntos.get(j);
				puntos.set(j, puntos.get(j-1));
				puntos.set(j - 1, temp);
			}
		}
	}
	
	
	/**
	 * Descripcion: Ordena el arreglo mejoresPuntajes ascendentemente segun el valor del nombre del jugador con el que tiene
	 * relacion cada uno de los puntajes. Si hay pocisiones nulas, las ubica al final del arreglo. <br>
	 * <b> pre : </b> mejoresPuntajes != null. <br>
	 * <b> post : </b> El arreglo mejoresPuntajes se encuentra ordenado ascendentemente segun el nombre del jugador al que pertenece cada
	 * uno de los puntajes. Las pocisiones nulas se encuentran al final del arreglo. <br>
	 */
	public void ordenarPuntajesPorNickname(){
		for (int i = 0; i < TOP_PUNTAJES; i++){
			if (mejoresPuntajes [i] != null){
				for (int j = i; j > 0 && mejoresPuntajes[j].compararNick(mejoresPuntajes[j-1]) < 0; j--){
					Puntaje temp = mejoresPuntajes [j];
					mejoresPuntajes [j] = mejoresPuntajes [j-1];
					mejoresPuntajes [j-1] = temp;
				}
			}
		}
	}
	
	/**
	 * Retorna el arreglo con los mejores puntajes <br>
	 * <b> pre : </b> mejoresPuntajes != null <br>
	 * @return mejoresPuntajes
	 */
	public Puntaje[] getMejoresPuntajes() {
		return mejoresPuntajes;
	}
	
	/**
	 * Cambia el arreglo que contiene a los mejores puntajes.
	 * @param mejoresPuntajes El nuevo arreglo. mejoresPuntajes != null.
	 */
	public void setMejoresPuntajes(Puntaje[] mejoresPuntajes) {
		this.mejoresPuntajes = mejoresPuntajes;
	}
	
	/**
	 * Descripcion: Verifica si se puede agregar un puntaje al arreglo de los mejoresPuntajes. Esto se da si
	 * sus puntos son mayores al Puntaje con menos puntos o si el arreglo tiene al menos una pocision vacia.
	 * <b> pre : </b> mejoresPuntajes != null <br>
	 * <b> pos : </b> mejoresPuntajes se encuentra ordenado descendentemente segun los puntos de cada Puntaje <br>
	 * <b> pos : </b> las pocisiones nulas dentro del arreglo mejoresPuntajes se encuentran ubicados al final del arreglo <br>
	 * @param p El Puntaje que se quiere añadir. p != null.
	 * @throws IOException Si se presenta algun error al guardar el archivo, lanza excepcion.
	 */
	public void anadirPuntaje (Puntaje p) throws IOException{
		ordenarPuntajesPorPuntos();
		if (mejoresPuntajes[TOP_PUNTAJES - 1] == null || mejoresPuntajes[TOP_PUNTAJES - 1].getPuntos() < p.getPuntos()){
			mejoresPuntajes[TOP_PUNTAJES - 1] = p;
			ordenarPuntajesPorPuntos();
		}
		guardarPuntajesYnombres();
	}
	
	/**
	 * Descripcion: Verifica si se puede agregar un puntaje al arreglo de los mejoresPuntajes. Esto se da si
	 * sus puntos son mayores al Puntaje con menos puntos o si el arreglo tiene al menos una pocision vacia.
	 * <b> pre : </b> mejoresPuntajes != null <br>
	 * <b> pos : </b> mejoresPuntajes se encuentra ordenado descendentemente segun los puntos de cada Puntaje <br>
	 * <b> pos : </b> las pocisiones nulas dentro del arreglo mejoresPuntajes se encuentran ubicados al final del arreglo <br>
	 * @param p El Puntaje que se quiere añadir. p != null.
	 * @param ruta La ruta donde se guardaran los puntajes de prueba.
	 * @throws IOException Si se presenta algun error al guardar el archivo, lanza excepcion.
	 */
	public void anadirPuntaje (Puntaje p, String ruta) throws IOException{
		ordenarPuntajesPorPuntos();
		if (mejoresPuntajes[TOP_PUNTAJES - 1] == null || mejoresPuntajes[TOP_PUNTAJES - 1].getPuntos() < p.getPuntos()){
			mejoresPuntajes[TOP_PUNTAJES - 1] = p;
			ordenarPuntajesPorPuntos();
		}
		guardarPuntajesYnombres(ruta);
	}
	
	
	/**
	 *Descripcion: Verifica si se puede agregar el puntaje actual al arreglo de los mejoresPuntajes. Esto se da si
	 * sus puntos son mayores al Puntaje con menos puntos o si el arreglo tiene al menos una pocision vacia.
	 * <b> pre : </b> partida != null <br>
	 * <b> pre : </b> partida es una instancia de PartidaIndividual <br>
	 * <b> pre : </b> logueado != null <br>
	 * <b> pre : </b> mejoresPuntajes != null <br>
	 * <b> pos : </b> mejoresPuntajes se encuentra ordenado descendentemente segun los puntos de cada Puntaje <br>
	 * <b> pos : </b> las pocisiones nulas dentro del arreglo mejoresPuntajes se encuentran ubicados al final del arreglo <br>
	 * @throws IOException Si se presenta algun error al guardar el archivo, lanza excepcion.
	 */
	public void registrarPuntaje () throws IOException{
		PartidaIndividual partidaI = (PartidaIndividual) partida;
		Puntaje p = new Puntaje(logueado, partidaI.getPuntaje());
		anadirPuntaje(p);
	}
	
	/**
	 *Descripcion: Verifica si se puede agregar el puntaje actual al arreglo de los mejoresPuntajes. Esto se da si
	 * sus puntos son mayores al Puntaje con menos puntos o si el arreglo tiene al menos una pocision vacia.
	 * <b> pre : </b> partida != null <br>
	 * <b> pre : </b> partida es una instancia de PartidaIndividual <br>
	 * <b> pre : </b> logueado != null <br>
	 * <b> pre : </b> mejoresPuntajes != null <br>
	 * <b> pos : </b> mejoresPuntajes se encuentra ordenado descendentemente segun los puntos de cada Puntaje <br>
	 * <b> pos : </b> las pocisiones nulas dentro del arreglo mejoresPuntajes se encuentran ubicados al final del arreglo <br>
	 * @param ruta La ruta donde se guardara el puntaje de prueba. ruta != null. ruta != "".
	 * @throws IOException Si se presenta algun error al guardar el archivo, lanza excepcion.
	 */
	public void registrarPuntaje (String ruta) throws IOException{
		PartidaIndividual partidaI = (PartidaIndividual) partida;
		Puntaje p = new Puntaje(logueado, partidaI.getPuntaje());
		anadirPuntaje(p, ruta);
	}
	
	/**
	 * Descripcion: Hace una busqueda binaria de los puntajes para los cuales el nombre del jugador al que pertenecen sea 
	 * igual al pasado por parametro y los agrega a un ArrayList de Puntaje. <br>
	 * <b> pre : </b> mejoresPuntajes != null <br>
	 * <b> pre : </b> mejoresPuntajes.getNombreJugador() != null <br>
	 * <b> post : </b> el arreglo mejoresPuntajes se encuentra ordenado de forma ascendente por el nombre del jugador. <br>
	 * @param nickJugador El nick del jugador para el que se está buscando los puntajes. nickJugador != null. nickJugador != "".
	 * @return ArrayList con los puntajes el cual el nombre del jugador al que pertenecen es igual al pasado por parametro. El Array no tiene 
	 * pocisiones vacias y tiene un tamaño superior a 0.
	 * @throws NoEstaEnMejoresPuntajesException Si no se encontro ningun puntaje el cual el jugador al que pertenece tenga el mismo nombre al del 
	 * pasado por parametro, lanza la excepcion NoEstaEnMejoresPuntajesException.
	 */
	public ArrayList <Puntaje> busquedaBinariaPuntajePorJugador(String nickJugador) throws NoEstaEnMejoresPuntajesException{
		ordenarPuntajesPorNickname();
		boolean encontrado = false;
		ArrayList <Puntaje> puntajesJugador = new ArrayList<Puntaje>();
		int inicio = 0;
		int fin = TOP_PUNTAJES;
		int medio = 0;
		while (!encontrado && inicio <= fin && inicio < TOP_PUNTAJES){
			medio = (inicio + fin) / 2;
			if (mejoresPuntajes [medio] == null){
				fin = medio - 1;
			}else if (nickJugador.equals(mejoresPuntajes[medio].getNickJugador())){
				encontrado = true;
			} else if (nickJugador.compareTo(mejoresPuntajes[medio].getNickJugador()) < 0){
				fin = medio - 1;
			} else {
				inicio = medio + 1;
			}
		}
		if (!encontrado){
			throw new NoEstaEnMejoresPuntajesException(nickJugador);
		}
		for (int i = medio; i < TOP_PUNTAJES && mejoresPuntajes[i] != null && mejoresPuntajes[i].getNickJugador().equals(nickJugador); i++){
			puntajesJugador.add(mejoresPuntajes[i]);
		}
		
		for (int i = medio - 1; i >= 0 && mejoresPuntajes[i] != null && mejoresPuntajes[i].getNickJugador().equals(nickJugador); i--){
			puntajesJugador.add(mejoresPuntajes [i]);
		}
		
		return puntajesJugador;
	}

	/**
	 * Descripcion: Recibe por parametros los datos de un nuevo jugador a añadir y lo agrega al arbol binario de jugadores si no hay ninguno 
	 * con su mismo nickname. <br>
	 * @param nick El nickname del nuevo jugador. nick != null. nick != "".
	 * @param nombre El nombre del jugador que se va a añadir. nombre != null. nombre != ""
	 * @param contrasena La contraseña con la que el usuario ingresara a su cuenta. contraseña != null. contraseña != "".
	 * @param edad La edad del usuario que se quiere añadir. edad != null. edad != "".
	 * @throws NumberFormatException Si el String recibido como "edad" no es un numero entero, lanza NumberFormatException al intentar convertirlo a int.
	 * @throws JugadorYaExisteException Si ya existe un jugador con el nick del que se quiere agregar, lanza excepcion informando que dicho nick ya existe.
	 * @throws IOException Si no logra guardar los jugadores, lanza excepcion.
	 * @throws FileNotFoundException Si no logra guardar los jugadores, lanza excepcion.
	 */
	public void anadirJugador (String nick, String nombre, String contrasena, String edad) throws NumberFormatException, JugadorYaExisteException, FileNotFoundException, IOException{
		int laEdad = Integer.parseInt(edad);
		if (laEdad <= 0){
			throw new NumberFormatException();
		}
		Jugador nuevo = new Jugador(nick, nombre, contrasena, laEdad);
		anadirJugadorAlArbol(nuevo);
		
		//System.out.println(nuevo.getNickname()+" - "+nuevo.getNombre()+" - "+nuevo.getEdad());
	}
	
	/**
	 * Descripcion: Recibe por parametros los datos de un nuevo jugador a añadir y lo agrega al arbol binario de jugadores si no hay ninguno 
	 * con su mismo nickname. <br>
	 * @param nick El nickname del nuevo jugador. nick != null. nick != "".
	 * @param nombre El nombre del jugador que se va a añadir. nombre != null. nombre != ""
	 * @param contrasena La contraseña con la que el usuario ingresara a su cuenta. contraseña != null. contraseña != "".
	 * @param edad La edad del usuario que se quiere añadir. edad != null. edad != "".
	 * @param ruta La ruta donde se guardaran los jugadores de prueba.
	 * @throws NumberFormatException Si el String recibido como "edad" no es un numero entero, lanza NumberFormatException al intentar convertirlo a int.
	 * @throws JugadorYaExisteException Si ya existe un jugador con el nick del que se quiere agregar, lanza excepcion informando que dicho nick ya existe.
	 * @throws IOException Si no logra guardar los jugadores, lanza excepcion.
	 * @throws FileNotFoundException Si no logra guardar los jugadores, lanza excepcion.
	 */
	public void anadirJugador (String nick, String nombre, String contrasena, String edad, String ruta) throws NumberFormatException, JugadorYaExisteException, FileNotFoundException, IOException{
		int laEdad = Integer.parseInt(edad);
		if (laEdad <= 0){
			throw new NumberFormatException();
		}
		Jugador nuevo = new Jugador(nick, nombre, contrasena, laEdad);
		anadirJugadorAlArbol(nuevo, ruta);
		
		//System.out.println(nuevo.getNickname()+" - "+nuevo.getNombre()+" - "+nuevo.getEdad());
	}
	
	/**
	 * Descripcion: Agrega un nuevo jugador al arbol binario de jugadores de forma que quede ordenado. Solo se agrega al arbol binario
	 * si no hay ningun otro jugador con el mismo nickname.<br>
	 * <b> post : </b> actual != null <br> 
	 * @param nuevo El jugador que se quiere añadir al arbol binario de jugadores. nuevo != null.
	 * @throws JugadorYaExisteException Si se encuentra un usuario con el mismo nickname que nuevo, lanza una excepcion informando 
	 * que ya existe otro jugador con dicho nickname y no agrega al jugador.
	 * @throws IOException Si no logra guardar los jugadores, lanza excepcion.
	 * @throws FileNotFoundException Si no logra guardar los jugadores, lanza excepcion.
	 */
	public void anadirJugadorAlArbol (Jugador nuevo) throws JugadorYaExisteException, FileNotFoundException, IOException{
		if (raiz == null){
			raiz = nuevo;
		} else {
			raiz.anadirJugador(nuevo);
		}
		guardarJugadores();
	}
	
	/**
	 * Descripcion: Agrega un nuevo jugador al arbol binario de jugadores de forma que quede ordenado. Solo se agrega al arbol binario
	 * si no hay ningun otro jugador con el mismo nickname.<br>
	 * <b> post : </b> actual != null <br> 
	 * @param nuevo El jugador que se quiere añadir al arbol binario de jugadores. nuevo != null.
	 * * @param ruta La ruta donde se guardaran los jugadores de prueba.
	 * @throws JugadorYaExisteException Si se encuentra un usuario con el mismo nickname que nuevo, lanza una excepcion informando 
	 * que ya existe otro jugador con dicho nickname y no agrega al jugador.
	 * @throws IOException Si no logra guardar los jugadores, lanza excepcion.
	 * @throws FileNotFoundException Si no logra guardar los jugadores, lanza excepcion.
	 */
	public void anadirJugadorAlArbol (Jugador nuevo, String ruta) throws JugadorYaExisteException, FileNotFoundException, IOException{
		if (raiz == null){
			raiz = nuevo;
		} else {
			raiz.anadirJugador(nuevo);
		}
		guardarJugadores(ruta);
	}
	
	/**
	 * Descripcion: Busca el jugador con el nickname ingresado por parametro y lo retorna.
	 * @param nick El nick del jugador que se esta buscando. nick != null. nick != "".
	 * @param actual El jugador dentro del arbol binario de busqueda en el cual se esta buscando al jugador.
	 * @return El jugador con el nick pasado por parametro.
	 * @throws NoExisteJugadorException Si no hay ningun jugador con el nickname pasado por parametro, lanza excepcion informando que
	 * no se encontro al jugador.
	 */
	public Jugador buscarJugador (String nick, Jugador actual) throws NoExisteJugadorException{
		if (actual == null){
			throw new NoExisteJugadorException ("No hay ningun jugador registrado con el nick " + nick);
		}
		if (nick.compareTo(actual.getNickname()) > 0){
			return buscarJugador(nick, actual.getDerecha());
		} else if (nick.compareTo(actual.getNickname()) < 0){
			return buscarJugador(nick, actual.getIzquierda());
		} else {
			return actual;
		}
	}
	
	/**
	 * Descripcion: Modifica el jugador que se encuentra logueado en la partida si la contraseña ingresada coincide con la del jugador con 
	 * el que se quiere ingresar. <br>
	 * @param j El jugador con el que se quiere ingresar. j != null. j es algun jugador dentro del ABB de jugadores.
	 * @param contrasena La contrasena ingresada por el usuario.
	 * @throws ContrasenaIncorrectaException Si la contrasena ingresada por el usuario no coincide con la del Jugador que se
	 * quiere ingresar, lanza excepcion y no modifica el estado de logueado.
	 */
	public void logJugador (Jugador j, String contrasena) throws ContrasenaIncorrectaException{
		if (j.getContraseña().equals(contrasena)){
			logueado = j;
		} else {
			throw new ContrasenaIncorrectaException();
		}
	}
	
	
	/**
	 * Descipcion: Crea una nueva instancia de la clase Partida la cual se le asigna a la relacion partida.
	 * @param anchoPanel El ancho del panel donde se visualizara la partida. anchoPanel > 0.
	 * @param altoPanel El alto del panel donde se visualizara la partida. altoPanel > 0.
	 * @throws Exception Si no hay ningun jugador logueado, lanza excepcion y no inicializa la partida.
	 */
	public void nuevaPartida (int anchoPanel, int altoPanel) throws Exception {
		if (logueado == null){
			throw new Exception ("Debe de loguearse con algún jugador");
		}
		partida = new PartidaIndividual(anchoPanel, altoPanel);
	}
	
	public void nuevaPartidaMultijugador (int anchoPanel, int altoPanel){
		partida = new PartidaMultijugador(anchoPanel, altoPanel);
	}
	
	public boolean isEnPartida (){
		return partida.isEnPartida();
	}
	
	/**
	 * Descripcion: Guarda la informacion de los mejores puntajes (puntos, nick, fecha) dentro de un archivo de texto.
	 * El formato para cada puntaje es el siguiente: nickname,puntos,fecha. La fecha se guardara segun el formato del metodo
	 * deDateAFormate en la clase Puntaje. <br>
	 * <b> pre : </b> mejoresPuntajes != null <br>
	 * @throws IOException Si no logra guardar los puntajes, lanza excepcion.
	 */
	public void guardarPuntajesYnombres ()  throws IOException{
		File archivo = new File(RUTA1);
		PrintWriter escritor = new PrintWriter(archivo);
		for (int i = 0; i < mejoresPuntajes.length && mejoresPuntajes[i] != null; i++) {
		escritor.write(mejoresPuntajes[i].getNickJugador()+",");
		escritor.write(mejoresPuntajes[i].getPuntos()+",");
		escritor.write(mejoresPuntajes[i].deDateAFormate() + "\n");
		}  
		escritor.close();
	}

	
	
	/**
	 * Descripcion: Se encarga de cargar los mejores puntajes y agregarlo al arreglo de 
	 * mejoresPuntajes a partir de la lectura de un archivo de texto. La ruta del archivo de texto esta indicada en una de las constantes de la clase.<br>
	 * <b> pre : </b> mejoresPuntajes != null <br>
	 * @throws IOException Si no logra cargar los puntajes por algun error en el archivo  de texto, lanza excepcion
	 */
	public void cargarPuntajesYnombres ()throws IOException{
		 
		FileReader reader = new FileReader(RUTA1);
		BufferedReader lector = new BufferedReader(reader);
		String linea = lector.readLine();
		int i = 0;
		mejoresPuntajes = new Puntaje [TOP_PUNTAJES];
		while (linea != null) {
		String [] nombreYpuntaje = linea.split(",");
		String nick = nombreYpuntaje[0];
		int puntaje = Integer.parseInt(nombreYpuntaje[1]);
		String fecha = nombreYpuntaje [2];
		try {
		Jugador jug = buscarJugador(nick, raiz);
		mejoresPuntajes[i] = new Puntaje(jug, puntaje, fecha);
		i++;
		} catch (NoExisteJugadorException e) {
		
		}
		linea = lector.readLine();
		}
		lector.close();
		}

	
	/**
	 * Guarda la informacion de la partida individual en curso dentro de un archivo serialisabe. La ruta del archivo esta declarada en una constante
	 * de esta clase. <br>
	 * <b> pre : </b> partida != null <br>
	 * <b> pre : </b> partida es una instancia de PartidaIndividual
	 * @throws IOException Si no logra guardar la partida por error en el archivo, lanza una excepcion
	 */
	public void guardarSerialisablePartida () throws IOException{
			ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(RUTA2 + "_" + logueado.getNickname()));
			os.writeObject(partida);
			os.close();	
	}
	
	/**
	 * Descripcion: Carga la partida individual guardada en el archivo serialisable para el jugador actualmente logueado. 
	 * @throws Exception Si no hay ningun jugador logueado, lanza excepcion indicando que se debe de registrar el usuario. 
	 * Si el jugador esta logueado pero no encuentra el archivo con la ruta que le corresponde, lanza excepcion informando
	 * que el usuario no tiene ninguna partida guardada. Si se presenta algun tipo de error en la lectura del archivo diferente 
	 * a no encontrar el archivo, lanza excepcion indicando que se presento un error leyendo los datos del archivo. En ninguno de estos casos
	 * se carga la partida.
	 */
	public void cargarSerializablePartida () throws Exception {
		if (logueado == null){
			throw new Exception ("Registrese con algun usuario");
		}
		File archivo = new File(RUTA2 + "_" + logueado.getNickname());
		FileInputStream fis;
			 try {
				fis = new FileInputStream(archivo);
				ObjectInputStream ois = new ObjectInputStream(fis);
				partida = (Partida) ois.readObject();
				ois.close();
			} catch (FileNotFoundException e) {
				throw new Exception("El usuario no tiene ninguna partida guardada");
			} catch (IOException e) {
				throw new Exception ("Se presento un error leyendo los datos del archivo");
			}
		
	}
	
	/**
	 * Guarda el ABB de jugadores junto a toda su informacion en un archivo serialisable. Para esto, basta con guardar la raiz del arbol. 
	 * La ruta del archivo esta indicada en una constante de esta clase.
	 * @throws IOException Si se presenta algun error con el archivo, lanza excepcion.
	 */
	public void guardarJugadores() throws FileNotFoundException, IOException{
		File arch = new File(RUTA3);
		ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(arch));
		os.writeObject(raiz);
		os.close();
	}
	
	/**
	 * Metodo que carga a los jugadores registrados al ABB de jugadores a partir del archivo serialisable con la informacion de la
	 * raiz del arbol. La ruta del archivo esta indicada en una constante de esta clase. En caso de que el archivo no exista, se crea
	 * uno nuevo guardando los datos de los jugadores actualmente registrados.
	 * @throws IOException Si se presenta algun error durante la lectura del archivo, lanza excepcion y no se cargan los jugadores.
	 * @throws ClassNotFoundException Si no logra encontrar a clase Jugador, lanza excepcion y no se cargan los jugadores.
	 */
	public void cargarJugadores() throws FileNotFoundException, IOException, ClassNotFoundException{
		File arch = new File(RUTA3);
		if (arch.exists()){
			ObjectInputStream is = new ObjectInputStream(new FileInputStream(arch));
			raiz = (Jugador) is.readObject();
			is.close();
		} else {
			ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(new File(RUTA3)));
			os.writeObject(raiz);
			os.close();
		}
	}
	
	/**
	 * Descripcion: Guarda la informacion de los mejores puntajes (puntos, nick, fecha) dentro de un archivo de texto.
	 * El formato para cada puntaje es el siguiente: nickname,puntos,fecha. La fecha se guardara segun el formato del metodo
	 * deDateAFormate en la clase Puntaje. <br>
	 * <b> pre : </b> mejoresPuntajes != null <br>
	 * * @param ruta la ruta donde se guardaran los puntajes. ruta != null. ruta != ""
	 * @throws IOException Si no logra guardar los puntajes, lanza excepcion.
	 */
	public void guardarPuntajesYnombres (String ruta)  throws IOException{
		File archivo = new File(ruta);
		PrintWriter escritor = new PrintWriter(archivo);
		for (int i = 0; i < mejoresPuntajes.length && mejoresPuntajes[i] != null; i++) {
		escritor.write(mejoresPuntajes[i].getNickJugador()+",");
		escritor.write(mejoresPuntajes[i].getPuntos()+",");
		escritor.write(mejoresPuntajes[i].deDateAFormate() + "\n");
		}  
		escritor.close();
	}

	
	
	/**
	 * Descripcion: Se encarga de cargar los mejores puntajes y agregarlo al arreglo de 
	 * mejoresPuntajes a partir de la lectura de un archivo de texto. La ruta del archivo de texto esta indicada en una de las constantes de la clase.<br>
	 * <b> pre : </b> mejoresPuntajes != null <br>
	 * * @param ruta la ruta donde se cargaran los puntajes. ruta != null. ruta != ""
	 * @throws IOException Si no logra cargar los puntajes por algun error en el archivo  de texto, lanza excepcion
	 */
	public void cargarPuntajesYnombres (String ruta)throws IOException{
		 
		FileReader reader = new FileReader(ruta);
		BufferedReader lector = new BufferedReader(reader);
		String linea = lector.readLine();
		int i = 0;
		mejoresPuntajes = new Puntaje [TOP_PUNTAJES];
		while (linea != null) {
		String [] nombreYpuntaje = linea.split(",");
		String nick = nombreYpuntaje[0];
		int puntaje = Integer.parseInt(nombreYpuntaje[1]);
		String fecha = nombreYpuntaje [2];
		try {
		Jugador jug = buscarJugador(nick, raiz);
		mejoresPuntajes[i] = new Puntaje(jug, puntaje, fecha);
		i++;
		} catch (NoExisteJugadorException e) {
			e.printStackTrace();
		}
		linea = lector.readLine();
		}
		lector.close();
		}

	
	/**
	 * Guarda la informacion de la partida individual en curso dentro de un archivo serialisabe. La ruta del archivo esta declarada en una constante
	 * de esta clase. <br>
	 * <b> pre : </b> partida != null <br>
	 * <b> pre : </b> partida es una instancia de PartidaIndividual
	 * @param ruta la ruta donde se guardara la informacion de la partida. ruta != null. ruta != ""
	 * @throws IOException Si no logra guardar la partida por error en el archivo, lanza una excepcion
	 */
	public void guardarSerialisablePartida (String ruta) throws IOException{
			ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(ruta + "_" + logueado.getNickname()));
			os.writeObject(partida);
			os.close();	
	}
	
	/**
	 * Descripcion: Carga la partida individual guardada en el archivo serialisable para el jugador actualmente logueado. 
	 * @param ruta la ruta donde se cargara la informacion de la partida. ruta != null. ruta != ""
	 * @throws Exception Si no hay ningun jugador logueado, lanza excepcion indicando que se debe de registrar el usuario. 
	 * Si el jugador esta logueado pero no encuentra el archivo con la ruta que le corresponde, lanza excepcion informando
	 * que el usuario no tiene ninguna partida guardada. Si se presenta algun tipo de error en la lectura del archivo diferente 
	 * a no encontrar el archivo, lanza excepcion indicando que se presento un error leyendo los datos del archivo. En ninguno de estos casos
	 * se carga la partida.
	 */
	public void cargarSerializablePartida (String ruta) throws Exception {
		if (logueado == null){
			throw new Exception ("Registrese con algun usuario");
		}
		File archivo = new File(ruta + "_" + logueado.getNickname());
		FileInputStream fis;
			 try {
				fis = new FileInputStream(archivo);
				ObjectInputStream ois = new ObjectInputStream(fis);
				partida = (Partida) ois.readObject();
				ois.close();
			} catch (FileNotFoundException e) {
				throw new Exception("El usuario no tiene ninguna partida guardada");
			} catch (IOException e) {
				throw new Exception ("Se presento un error leyendo los datos del archivo");
			}
		
	}
	
	/**
	 * Guarda el ABB de jugadores junto a toda su informacion en un archivo serialisable. Para esto, basta con guardar la raiz del arbol. 
	 * La ruta del archivo esta indicada en una constante de esta clase.
	 * * @param ruta la ruta donde se guardaran los jugadores registrados. ruta != null. ruta != ""
	 * @throws IOException Si se presenta algun error con el archivo, lanza excepcion.
	 */
	public void guardarJugadores(String ruta) throws FileNotFoundException, IOException{
		File arch = new File(ruta);
		ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(arch));
		os.writeObject(raiz);
		os.close();
	}
	
	/**
	 * Metodo que carga a los jugadores registrados al ABB de jugadores a partir del archivo serialisable con la informacion de la
	 * raiz del arbol. La ruta del archivo esta indicada en una constante de esta clase. En caso de que el archivo no exista, se crea
	 * uno nuevo guardando los datos de los jugadores actualmente registrados.
	 * @param ruta la ruta donde se cargaran los jugadores registrados. ruta != null. ruta != ""
	 * @throws IOException Si se presenta algun error durante la lectura del archivo, lanza excepcion y no se cargan los jugadores.
	 * @throws ClassNotFoundException Si no logra encontrar a clase Jugador, lanza excepcion y no se cargan los jugadores.
	 */
	public void cargarJugadores(String ruta) throws FileNotFoundException, IOException, ClassNotFoundException{
		File arch = new File(ruta);
		if (arch.exists()){
			ObjectInputStream is = new ObjectInputStream(new FileInputStream(arch));
			raiz = (Jugador) is.readObject();
			is.close();
		} else {
			ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(new File(RUTA3)));
			os.writeObject(raiz);
			os.close();
		}
	}
	
	/**
	 * Descripcion: Metodo que determina si dos arreglos de Puntaje son iguales. Dos arreglos se consideran
	 * iguales si, para cada uno de sus pocisiones, o ambos elementos son nulos o ambos son diferentes de null
	 * y tienen mismos Puntos, pertenecen al mismo Jugador y tienen la misma fecha.
	 * @param puntajes1 El primer arreglo de puntajes. puntajes1.length == puntajes2.length
	 * @param puntajes2 El segundo arreglo de puntajes. puntajes2.length == puntajes1.length
	 * @return true si son iguales, false en caso contrario.
	 */
	public boolean arreglosIguales (Puntaje [] puntajes1, Puntaje [] puntajes2){
		boolean iguales = true;
		for (int i = 0; i < puntajes1.length && iguales; i++){
			if (puntajes1[i] == null || puntajes2 [i] == null){
				if (puntajes1[i] == null && puntajes2 [i] != null){
					iguales = false;
				} else if (puntajes2[i] == null && puntajes1[i] != null){
					iguales = false;
				}
			} else {
				iguales = puntajes1[i].getFecha().equals(puntajes2[i].getFecha()) && puntajes1[i].getJugador().equals(puntajes2[i].getJugador()) && puntajes1[i].getPuntos() == puntajes2[i].getPuntos();
			}
		}
		return iguales;
	}

	
	
}
