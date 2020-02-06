package interfaz;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.PasswordAuthentication;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import org.omg.PortableServer.ServantRetentionPolicyValue;

import hilos.HiloDisparoEnemigo;
import hilos.HiloMoverDisparo;
import hilos.HiloMoverDisparo2;
import hilos.HiloMoverNave2;
import hilos.HiloMoverNaveUsuario;
import hilos.HiloMoverNavesEnemigas;
import mundo.ContrasenaIncorrectaException;
import mundo.Jugador;
import mundo.JugadorYaExisteException;
import mundo.MenuPrincipal;
import mundo.Nave;
import mundo.NoEstaEnMejoresPuntajesException;
import mundo.NoExisteJugadorException;
import mundo.Partida;
import mundo.PartidaIndividual;
import mundo.PartidaMultijugador;
import mundo.Puntaje;

public class InterfazInicio extends JFrame implements KeyListener {

	
	/**
	 * Descripcion: Relacion con el panel PanelMenuJuego  
	 */
	private PanelMenuJuego panelInicio;
	/**
	 * Descripcion: Relacion con el panel PanelPuntaje
	 */
	private PanelPuntaje panelPuntaje;
	/**
	 * Descripcion: Relacion con el panel PanelMultijugador
	 */
	private PanelMultijugador panelMultijugador;
	/**
	 * Descripcion: Relacion con el panel PanelLogin
	 */
	private PanelLogin panelLogin;
	/**
	 * Descripcion: Relacion con el panel PanelRegistro
	 */
	private PanelRegistro panelRegistro;
	/**
	 * Descripcion: Relacion con el panel PanelJugar
	 */
	private PanelIndividual panel;
	
	/**
	 * Descripcion: Relacion con el panelPerdiste;
	 */
	private PanelPerdiste panelPerdiste;
	
	/**
	 * Descripcion: Relacion con el JmenuBar 
	 */
	private MenuBar_BotonGuardar menuBarJugar;

	/**
	 * Descripcion: Relacion con la clase principal del mundo MenuPrincipal
	 */
	private MenuPrincipal menuConexion;// Conexion con el mundo

	/**
	 * Descripcion: Es el panel que me permite sobreescribir los demas paneles en el mismo Jframe
	 */
	private JPanel auxiliar;

	/**
	 * Descripcion: Permite construir la interfaz grafica
	 */
	public InterfazInicio() {

		setTitle("Space Invaders");
		setSize(1050, 700);
		setLocationRelativeTo(null);
		setResizable(false);

		try {
			menuConexion = new MenuPrincipal();
		} catch (IOException | ClassNotFoundException e) {
			JOptionPane.showMessageDialog(this, "No se pudo leer los archivos de entrada");
			System.exit(0);
		}
		menuBarJugar = new MenuBar_BotonGuardar(this);
		panelRegistro = new PanelRegistro(this);
		panelInicio = new PanelMenuJuego(this);
		panelPuntaje = new PanelPuntaje(this);
		panelMultijugador = new PanelMultijugador(this);
		panelLogin = new PanelLogin(this);
		panelPerdiste = new PanelPerdiste(this);
		
		
	
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		setLayout(new BorderLayout());

		auxiliar = new JPanel();
		auxiliar.setLayout(new BorderLayout());

		
		auxiliar.add(panelInicio, BorderLayout.CENTER);
		add(auxiliar, BorderLayout.CENTER);

		addKeyListener(this);
		requestFocus();
	}

	/**
	 * Descripcion: Permite inicilizar el hilo de las naves enemigas
	 */
	public void inicializarHiloNaves() {
		PartidaIndividual p = (PartidaIndividual) menuConexion.getPartida();
		Thread hilo = new Thread(new HiloMoverNavesEnemigas(p, this));
		hilo.start();
	}

	/**
	 * Descripcion: Permite inicializar el hilo de los disparos enemigos
	 */
	public void inicializarHiloDisparosEnemigos() {
		Thread hilo = new Thread(new HiloDisparoEnemigo((PartidaIndividual) getPartida(), this));
		hilo.start();
	}

	/**
	 * Descripcion: Permite dar la partida actual
	 * @return un objeto tipo Partida dado desde el mundo
	 */
	public Partida getPartida() {
		return menuConexion.getPartida();
	}

	/**
	 * Descripcion: Permite consultar el panelJugar 
	 * @return un objeto tipo PanelJugar
	 */
	public PanelIndividual getPanel() {
		return panel;
	}
		
		
	/**
	 * Descripcion: Permite actualizar constantemente el panel donde se juega.  
	 */
	public void refrescarPanel() {
		if (auxiliar.getComponents() [0] == panel){
			panel.repaint();
		} else {
			panelMultijugador.repaint();
		}
	}

	/**
	 * 	Descripcion: Permite dar los atributos de un jugador para luego ser añadido al árbol de los jugadores 
	 * @param nickname El nickname del jugador
	 * @param nombre El nombre del jugador
	 * @param contrasena La contraseña del jugador
	 * @param edad La edad del jugador
	 */
	public void registrarJugador( String nickname, String nombre,String contrasena , String edad ) {
		
		try {
			
			menuConexion.anadirJugador(nombre, nickname, contrasena, edad);
			JOptionPane.showMessageDialog(null, "Se a registrado correctamente");
			cambiarLogin();
		} catch (JugadorYaExisteException e) {
			
			JOptionPane.showMessageDialog(this, e.getMessage());
			
		} catch (NumberFormatException e) {
			
			JOptionPane.showMessageDialog(this, "La edad tiene que ser un valor númerico");
		} catch (IOException e ) {
			JOptionPane.showMessageDialog(this, "No se pudo guardar el nuevo jugador.");
		}
		
	}
	
	
	/**
	 * Descripción: Permite actualizar la informacion de un jugador
	 * @param jugador Un objeto tipo Puntaje 
	 */
	public void actualizarInfo(Puntaje jugador) {
		if(jugador != null) {
			panelPuntaje.getPanelInformacion().refrestarDatosJugador(jugador);
		}
	}
	
	
	
	/**
	 * Descripcion: Permite guardar el estado del juego de forma serializable
	 * @return un boolean "true" si el juego fue guardado correctamente o un "false" si no fue guardado correctamente
	 */
	public boolean guardarJuegoSerializable() {

		boolean guardoJuego = false;

		try {
			menuConexion.guardarSerialisablePartida();
			guardoJuego = true;
				JOptionPane.showMessageDialog(null, "El juego se cargo correctamente");
			
		} catch (Exception ioexc) { 
			JOptionPane.showMessageDialog(this,
					"Problemas guardando el archivo\nEs probable que no tenga permisos de escritura o\nel archivo puede estar bloqueado por otro programa.");
		}
		return guardoJuego;
	}

	
	
	/**
	 * Descripcion: Permite cargar el estado del juego en la interfaz de forma serializable
	 */
	public void cargarJuegoSerializable() {
				try {
					menuConexion.cargarSerializablePartida();
					cambiarIndividual(false);
				} catch (Exception e) {
					JOptionPane.showMessageDialog(this, e.getMessage());
				}
		

	}

	/**
	 * Descripcion: Permite salir del juego
	 */
	public void salirJuego() {
		int opcion;
		boolean guardo = false;
		do {
			opcion = JOptionPane.showConfirmDialog(this, "¿Desea guardar el juego actual antes de salir?");
			if (opcion == JOptionPane.YES_OPTION) {
				guardo = guardarJuegoSerializable();
			}
		} while (opcion == JOptionPane.YES_OPTION && !guardo);

		if (opcion == JOptionPane.YES_OPTION || opcion == JOptionPane.NO_OPTION) {
			System.exit(0);
		}
	}

	/**
	 * Descripcion: Permite modificar el panel auxiliar, mostrando el panelPuntaje 
	 */
	public void cambiarPuntaje() {
		auxiliar.remove(0);
		auxiliar.setPreferredSize(new Dimension(1100, 675));
		panelPuntaje.getPanelLista().actializarLista(menuConexion.getMejoresPuntajes());
		auxiliar.add(panelPuntaje);
		pack();
	}
	
	public void cambiarPerdiste() {

		auxiliar.remove(0);
		auxiliar.setPreferredSize(new Dimension(500, 300));
		auxiliar.add(panelPerdiste);
		pack();

	}
	

	/**
	 * Descripcion: Permite modificar el panel auxiliar, mostrando el panelMultijugador
	 */
	public void cambiarMultiplayer() {
		auxiliar.remove(0);
		auxiliar.setPreferredSize(new Dimension(1025, 675));
		auxiliar.add(panelMultijugador);
		pack();
		menuConexion.nuevaPartidaMultijugador(panelMultijugador.getWidth(), panelMultijugador.getHeight());
		refrescarPanel();
		requestFocus();
	}

	/**
	 * Descripcion: Permite modificar el panel auxiliar, mostrando el paneJugar 
	 */
	public void cambiarIndividual(boolean nuevo) {


		auxiliar.remove(0);
		panel = new PanelIndividual(this);
		auxiliar.setPreferredSize(new Dimension(1025, 675));
		auxiliar.add(panel);
		JMenuBar menu = new JMenuBar();
		menu.add(menuBarJugar);
		setJMenuBar(menu);
		pack();
			try {
				if (nuevo){
				menuConexion.nuevaPartida(panel.getWidth(), panel.getHeight());
				} else if (menuConexion.getPartida().getNave().isDisparando()){
					Thread hilo = new Thread(new HiloMoverDisparo(this, getPartida()));
					hilo.start();
				}
				inicializarHiloNaves();
				inicializarHiloDisparosEnemigos();
				refrescarPanel();
			} catch (Exception e) {
				setJMenuBar(null);
				cambiarPanelMenu();
				JOptionPane.showMessageDialog(auxiliar, e.getMessage());
			}
		requestFocus();
	}
	
	/**
	 * Descripcion: Permite consultar la conexion con el mundo
	 * @return la relacion con el mundo
	 */
	public MenuPrincipal getMenuConexion() {
		return menuConexion;
	}

	
	/**
	 * Descripcion: Permite modificar el panel auxiliar, mostrando el panelLogin
	 */
	public void cambiarLogin() {

		
		auxiliar.remove(0);
		auxiliar.setPreferredSize(new Dimension(500, 300));
		auxiliar.add(panelLogin);
		pack();

	}
	
	/**
	 * Descripcion: Permite la verificacion del logeo de un usuario
	 * @param nickName El nicknNama del jugador
	 * @param contrasena La contraseña del jugador
	 */
	public void logearse(String nickName, String contrasena) {
		
		if(nickName != null) {
			Jugador jugadorEncontrado = null;
			try {
				jugadorEncontrado = menuConexion.buscarJugador(nickName, menuConexion.getRaiz());
				if(contrasena != null) {
					try {
						menuConexion.logJugador(jugadorEncontrado, contrasena);
						cambiarPanelMenu();
					} catch (ContrasenaIncorrectaException e) {
						JOptionPane.showMessageDialog(this, e.getMessage());
					}
					
				}
				
				
			} catch (NoExisteJugadorException e) {
				JOptionPane.showMessageDialog(this, e.getMessage());
				
			}
			
			

		}
		
		
		
	}
	
	
	/**
	 * Descripcion: Permite modificar el panel auxiliar, mostrando el panelRegistro
	 */
	public void cambiarRegistrar() {

		auxiliar.remove(0);
		auxiliar.setPreferredSize(new Dimension(500, 400));
		panelLogin.setBackground(Color.BLACK);
		auxiliar.add(panelRegistro);
		pack();

	}

	/**
	 * Descripcion: Permite modificar el panel auxiliar, mostrando el panelInicio
	 */
	public void cambiarPanelMenu() {

		auxiliar.remove(0);
//		panelInicio = new PanelMenuJuego(this);
		auxiliar.setPreferredSize(new Dimension(1050, 675));
		auxiliar.add(panelInicio);
		pack();
	}

	/**
	 * Descripción: Permite consultar un arreglo con los 10 mejores puntajes
	 * @return un arreglo con los 10 mejores puntajes
	 */
	public Puntaje [] darMejoresPuntajes() {
		
		return menuConexion.getMejoresPuntajes();
	}
	
	/**
	 * Descripción: Permite conectar la interfaz con el metodo del mundo que ordena los puntajes por el nickName
	 */
	public void ordenarPorNickName() {
		menuConexion.ordenarPuntajesPorNickname();
		panelPuntaje.getPanelLista().actializarLista(menuConexion.getMejoresPuntajes());
	}
	
	/**
	 * Descripción:Permite conectar la interfaz con el metodo del mundo que ordena los puntajes de mayor a menor
	 */
	public void ordenarPorPuntante() {
		
		menuConexion.ordenarPuntajesPorPuntos();
		panelPuntaje.getPanelLista().actializarLista(menuConexion.getMejoresPuntajes());

	}
	
	/**
	 * Descripción: Permite conectar la interfaz con el metodo del mundo que busca un jugador
	 * @param nick El nick del jugador a buscar
	 */
	public void buscarJugador (String nick) {
		
		try {
			
			if(nick!=null) {
				String tag = nick.trim();
				if(!tag.equals("")) {
					ArrayList<Puntaje> elJugador = menuConexion.busquedaBinariaPuntajePorJugador(nick);
					panelPuntaje.getPanelLista().actializarListaPuntajesXJugador(elJugador);				
				}				
			}
		} catch (NoEstaEnMejoresPuntajesException e) {
			JOptionPane.showMessageDialog(null, "Digite un nick válido");
		}
		
	}
	
	/**
	 * Descripción: Permite dar la escucha del teclado en la interfaz principal cuando se invoca el panel Jugar
	 */
	@Override
	public void keyPressed(KeyEvent e) {
		int tecla = e.getKeyCode();
		if (auxiliar.getComponents()[0] == panel || auxiliar.getComponents()[0] == panelMultijugador) {
			if (!menuConexion.getPartida().getNave().isEnMovimiento()) {
				if (tecla == 39) {
					menuConexion.getPartida().empezarMovimientoNave();
					Thread hilo = new Thread(new HiloMoverNaveUsuario(menuConexion.getPartida(), this, Nave.DERECHA));
					hilo.start();
				} else if (tecla == 37) {
					menuConexion.getPartida().empezarMovimientoNave();
					Thread hilo = new Thread(new HiloMoverNaveUsuario(menuConexion.getPartida(), this, Nave.IZQUIERDA));
					hilo.start();
				}
			}
			if (!menuConexion.getPartida().getNave().isDisparando()) {
				if (tecla == 32) {
					menuConexion.getPartida().dispararNaveAliada();
					Thread hilo = new Thread(new HiloMoverDisparo(this, menuConexion.getPartida()));
					hilo.start();
				}
			}
		}
		
		if (auxiliar.getComponents()[0] == panelMultijugador){
			PartidaMultijugador multi = (PartidaMultijugador) menuConexion.getPartida();
			if (!multi.getNave2().isEnMovimiento()){
				if (tecla == 65){
					multi.empezarMovimientoNave2();
					Thread hilo = new Thread(new HiloMoverNave2(multi, this, Nave.IZQUIERDA));
					hilo.start();
				} else if (tecla == 68){
					multi.empezarMovimientoNave2();
					Thread hilo = new Thread(new HiloMoverNave2(multi, this, Nave.DERECHA));
					hilo.start();
				}
			}
			if (!multi.getNave2().isDisparando() && tecla == 16){
				multi.dispararNave2();
				Thread hilo = new Thread(new HiloMoverDisparo2(this, multi));
				hilo.start();
			}
		}

	}

	/**
	 * Descripción: NO LO ENTENDI
	 */
	@Override
	public void keyReleased(KeyEvent e) {

		int tecla = e.getKeyCode();
		if (auxiliar.getComponents()[0] == panel || auxiliar.getComponents()[0] == panelMultijugador) {
			if (tecla == 39 || tecla == 37) {
				menuConexion.getPartida().detenerNave();
			}

		}
		if (auxiliar.getComponents()[0] == panelMultijugador){
			if (tecla == 65 || tecla == 68){
				PartidaMultijugador multi = (PartidaMultijugador) menuConexion.getPartida();
				multi.detenerNave2();
			}
		}

	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

	/**
	 * Descripcion: Permite tener una visualizacion la interfaz de la aplicación
	 * @param args
	 */
	public static void main(String[] args) {
		InterfazInicio vent = new InterfazInicio();
		vent.setVisible(true);
	}
}
