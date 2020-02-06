package interfaz;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class PanelBotones extends JPanel implements ActionListener {

	/**
	 * Descripci�n: Constante que representa la acci�n del boton ordenar por
	 * nickName
	 */
	public static final String ORDENAR_NICKNAME = "Ordenar NickName";
	/**
	 * Descripci�n: Constante que representa la acci�n del boton ordenar por puntaje
	 */
	public static final String ORDENAR_PUNTAJE = "Ordenar Puntaje";
	/**
	 * Descripci�n: Constante que representa la acci�n del boton buscar jugador
	 */
	public static final String BUSQUEDA_JUGADOR = "Buscar jugador";
	/**
	 * Descripci�n: Constante que representa la acci�n del boton volver
	 */
	public static final String MENU_PRINCIPAL = "Men� principal";

	/**
	 * Descripci�n: Es el boton ordenar por nickName
	 */
	private JButton butOrdenarPorNombre;
	/**
	 * Descripci�n: Es el boton ordenar por puntaje
	 */
	private JButton butOrdenarPorPuntaje;
	/**
	 * Descripci�n: Es el boton buscar binario jugador
	 */
	private JButton butBuscarBinarioJugador;

	/**
	 * Descripci�n: Es el boton volver;
	 */
	private JButton butMenuPrincipal;

	/**
	 * Descripci�n: Representa un espacio vacio en panel
	 */
	private JLabel labVacio1;
	/**
	 * Descripci�n: Representa un espacio vacio en panel
	 */
	private JLabel labVacio2;
	/**
	 * Descripci�n: Representa un espacio vacio en panel
	 */
	private JLabel labVacio3;
	/**
	 * Descripci�n: Representa un espacio vacio en panel
	 */
	private JLabel labVacio4;
	/**
	 * Descripci�n: Representa un espacio vacio en panel
	 */
	private JLabel labVacio5;
	/**
	 * Descripci�n: Representa un espacio vacio en panel
	 */
	private JLabel labVacio6;
	/**
	 * Descripci�n: Representa un espacio vacio en panel
	 */
	private JLabel labVacio7;
	/**
	 * Descripci�n: Representa un espacio vacio en panel
	 */
	private JLabel labVacio8;
	/**
	 * Descripci�n: Representa un espacio vacio en panel
	 */
	private JLabel labVacio9;
	/**
	 * Descripci�n: Representa un espacio vacio en panel
	 */
	private JLabel labVacio10;
	/**
	 * Descripci�n: Representa un espacio vacio en panel
	 */
	private JLabel labVacio11;
	/**
	 * Descripci�n: Representa un espacio vacio en panel
	 */
	private JLabel labVacio12;
	/**
	 * Descripci�n: Representa un espacio vacio en panel
	 */
	private JLabel labVacio13;
	/**
	 * Descripci�n: Representa un espacio vacio en panel
	 */
	private JLabel labVacio14;
	/**
	 * Descripci�n: Representa un espacio vacio en panel
	 */
	private JLabel labVacio15;
	/**
	 * Descripci�n: Representa un espacio vacio en panel
	 */
	private JLabel labVacio16;

	/**
	 * Descripci�n: Es la relacion con el panel Puntaje
	 */
	private PanelPuntaje relacionPuntaje;
	/**
	 * Descripci�n: Es la relacion con la interfaz principal
	 */
	private InterfazInicio relacionInterfaz;

	/**
	 * Descripci�n: Construye el panel Botones y sus diferentes elementos
	 * 
	 * @param principal
	 *            La relacion con el panelPuntajes
	 * @param relacionInterfaz
	 *            La relacion con la interfaz principal
	 */
	public PanelBotones(PanelPuntaje principal, InterfazInicio relacionInterfaz) {

		this.relacionInterfaz = relacionInterfaz;
		relacionPuntaje = principal;

		labVacio1 = new JLabel(" ");
		labVacio2 = new JLabel(" ");
		labVacio3 = new JLabel(" ");
		labVacio4 = new JLabel(" ");
		labVacio5 = new JLabel(" ");
		labVacio6 = new JLabel(" ");
		labVacio7 = new JLabel(" ");
		labVacio8 = new JLabel(" ");
		labVacio9 = new JLabel(" ");
		labVacio10 = new JLabel(" ");
		labVacio11 = new JLabel(" ");
		labVacio12 = new JLabel(" ");
		labVacio13 = new JLabel(" ");
		labVacio14 = new JLabel(" ");
		labVacio15 = new JLabel(" ");
		labVacio16 = new JLabel(" ");

		butMenuPrincipal = new JButton("Men�");
		butOrdenarPorNombre = new JButton("Ord. Nombre");
		butOrdenarPorPuntaje = new JButton("Ord. Puntaje");
		butBuscarBinarioJugador = new JButton("Buscar Jugador");

		butOrdenarPorNombre.setPreferredSize(new Dimension(500, 50));

		butBuscarBinarioJugador.setActionCommand(BUSQUEDA_JUGADOR);
		butOrdenarPorNombre.setActionCommand(ORDENAR_NICKNAME);
		butOrdenarPorPuntaje.setActionCommand(ORDENAR_PUNTAJE);
		butMenuPrincipal.setActionCommand(MENU_PRINCIPAL);

		butOrdenarPorNombre.setFont(new Font("OCR A Extended", Font.BOLD, 20));
		butOrdenarPorPuntaje.setFont(new Font("OCR A Extended", Font.BOLD, 20));
		butBuscarBinarioJugador.setFont(new Font("OCR A Extended", Font.BOLD, 20));
		butMenuPrincipal.setFont(new Font("OCR A Extended", Font.BOLD, 20));

		butBuscarBinarioJugador.addActionListener(this);
		butOrdenarPorNombre.addActionListener(this);
		butOrdenarPorPuntaje.addActionListener(this);
		butMenuPrincipal.addActionListener(this);

		setLayout(new GridLayout(4, 5));

		add(labVacio1);
		add(labVacio2);
		add(labVacio3);
		add(labVacio4);
		add(labVacio5);
		add(butOrdenarPorNombre);
		add(labVacio6);
		add(butOrdenarPorPuntaje);
		add(labVacio7);
		add(butBuscarBinarioJugador);
		add(labVacio8);
		add(labVacio9);
		add(labVacio10);
		add(labVacio11);
		add(labVacio12);
		add(labVacio13);
		add(labVacio14);
		add(butMenuPrincipal);
		add(labVacio15);
		add(labVacio16);

	}

	/**
	 * Descripci�n: Permite realizar la escucha de los eventos que se presetan en el
	 * panelBotones
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		String comando = e.getActionCommand();

		if (comando.equals(ORDENAR_NICKNAME)) {

			relacionInterfaz.ordenarPorNickName();

		} else if (comando.equals(ORDENAR_PUNTAJE)) {

			relacionInterfaz.ordenarPorPuntante();

		} else if (comando.equals(BUSQUEDA_JUGADOR)) {

			String nick = JOptionPane.showInputDialog("Digite el nick del jugador a buscar");
			relacionInterfaz.buscarJugador(nick);

		} else if (comando.equals(MENU_PRINCIPAL)) {

			relacionPuntaje.cambiarPanel();

		}

	}

}
