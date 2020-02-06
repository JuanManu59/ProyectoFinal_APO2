package interfaz;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.accessibility.AccessibleAttributeSequence;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class PanelPerdiste extends JPanel implements ActionListener {

	
	
	/**
	 * Descripci�n: Constante que representa la ruta de la imagen del titulo del panel
	 */
	public static final String RUTA = "./data/imagenes/tituloPerdiste.png";
	
	/**
	 * Descripci�n: Constante que representa la accion del boton jugar de nuevo
	 */
	public static final String JUGAR = "Juegar de nuevo";
	/**
	 * Descripci�n: Constante que representa la accion del boton menu principal
	 */
	public static final String MENU = "Menu principal";
	
	
	/**
	 * Descripci�n: Jlabel que muestra una imagen con el titulo Perdiste
	 */
	
	/**
	 * Descripci�n: 
	 */
	private JButton butJugarDeNuevo;
	/**
	 * Descripci�n:
	 */
	private JButton butMenuPrincipal;
	
	
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
	
	
	/**
	 * Descripci�n: Relacion con el panleBanner 
	 */
	private PanelBanner panelBanner;
	
	/**
	 * Descripci�n: Relacion con la interfaz principal
	 */
	private InterfazInicio principal;
	
	/**
	 * Descripci�n: Construye el panel y los elementos que contiene 
	 * @param principal Es la relacion con la interfaz principal
	 */
	public PanelPerdiste(InterfazInicio principal) {
		
		this.principal = principal;
		
		panelBanner = new PanelBanner(RUTA);
		panelBanner.setBackground(Color.BLACK);
		
		labVacio1 = new JLabel(" ");
		labVacio2 = new JLabel(" ");
		labVacio3 = new JLabel(" ");
		labVacio4 = new JLabel(" ");
		

		
		
		butJugarDeNuevo = new JButton("Volver a Jugar");
		butMenuPrincipal = new JButton("Men� Principal");
		
		butJugarDeNuevo.setActionCommand(JUGAR);
		butMenuPrincipal.setActionCommand(MENU);
		
		butJugarDeNuevo.addActionListener(this);
		butMenuPrincipal.addActionListener(this);
		
		butJugarDeNuevo.setFont(new Font("OCR A Extended", Font.BOLD, 20));
		butMenuPrincipal.setFont(new Font("OCR A Extended", Font.BOLD, 20));
		
		setLayout(new BorderLayout());
		
		JPanel aux = new  JPanel();
		aux.setLayout(new GridLayout(3, 2));
		
		aux.setBackground(Color.BLACK);
		
		aux.add(labVacio1);
		aux.add(labVacio2);
		aux.add(butJugarDeNuevo);
		aux.add(butMenuPrincipal);
		aux.add(labVacio3);
		aux.add(labVacio4);
		
		add(panelBanner, BorderLayout.NORTH);
		add(aux, BorderLayout.CENTER);
		
		
	}
	
	
	
	/**
	 * Descripci�n: Permite realizar la escucha de los eventos que se realizan en el panelPerdiste
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		
		String comando = e.getActionCommand();
		
		if(comando.equals(JUGAR)) {
			principal.cambiarIndividual(true);
		}else if(comando.equals(MENU)){
			principal.cambiarPanelMenu();
			
		}
		
		
	}

}
