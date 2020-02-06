package interfaz;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class PanelPuntaje extends JPanel {
	
	/**
	 * Descripci�n: Constante que representa la ruta de la imagen del titulo del panel
	 */
	public static final String PUNTAJE = "./data/imagenes/BannerTitulo.png";
	
	/**
	 * Descripci�n: Relacion con el panelInformacion
	 */
	private PanelInformacionJugador panelInformacion;
	/**
	 * Descripci�n: Relacion con el panelBonotes
	 */
	private PanelBotones panelBotones;
	/**
	 * Descripci�n: Relacion con el panelLista
	 */
	private PanelListaJugadores panelLista;
	/**
	 * Descripci�n: Relacion con el panelBanner;
	 */
	private PanelBanner panelBanner;
	
	/**
	 * Descripci�n:Relacion con la interfaz principal
	 */
	private InterfazInicio principal;
	
	/**
	 * Descripci�n: Construye el panelPuntaje
	 * @param v un objeto de tipo interfazInicio
	 */
	public PanelPuntaje(InterfazInicio v) {

		
		principal = v;
		
		
		panelInformacion = new PanelInformacionJugador();
		panelBotones = new PanelBotones(this,principal);
		panelLista = new PanelListaJugadores(this, principal.darMejoresPuntajes(), principal);
		panelBanner = new PanelBanner(PUNTAJE);
	
		panelBotones.setBackground(Color.BLACK);
		panelInformacion.setBackground(Color.BLACK);
		panelLista.setBackground(Color.BLACK);
		panelBanner.setBackground(Color.BLACK);
		
		
		setLayout(new BorderLayout());
	
		JPanel aux = new JPanel();
		aux.setLayout(new GridLayout(1, 2));
		
		aux.add(panelLista);
		aux.add(panelInformacion);
		
		add(panelBanner, BorderLayout.NORTH);
		add(aux, BorderLayout.CENTER);
		add(panelBotones, BorderLayout.SOUTH);
		
		
		
	}
	
	/**
	 * Descripci�n: Permite consultar el panelLista
	 * @return un objeto tipo panelLista
	 */
	public PanelListaJugadores getPanelLista() {
		return panelLista;
	}

	/**
	 * Descripci�n: Permite consultar el panelInformacion 
	 * @return un objeto tipo panelInformacion
	 */
	public PanelInformacionJugador getPanelInformacion() {
		return panelInformacion;
	}


	/**
	 *	Descripci�n: Permite modificar auxiliar, mostrando el panelMenu 
	 */
	public void cambiarPanel() {		
		principal.cambiarPanelMenu();
		
	}
	

	
	
	
	
	
}
