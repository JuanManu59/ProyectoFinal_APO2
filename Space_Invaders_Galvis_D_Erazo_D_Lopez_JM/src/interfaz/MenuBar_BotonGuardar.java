package interfaz;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

public class MenuBar_BotonGuardar  extends JMenu implements ActionListener{

	/**
	 * Descripci�n: Constante que representa la acci�n de guardar
	 */
	public final static String GUARDAR = "GUARDAR";
	/**
	 * Descripci�n: Constante que representa la acci�n de salir 
	 */
	public final static String SALIR   = "SALIR";
	
	/**
	 * Descripci�n: Es el JMenuItem que permite guardar la partida
	 */
	private JMenuItem meitGuardar;
	/**
	 * Descripci�n: Es el JMenuItem que permite salir del juego
	 */
	private JMenuItem meitSalir;
	
	/**
	 * Descripci�n: Es la relacion con la interfaz principal
	 */
	private InterfazInicio principal;
	
	/**
	 * Descripci�n: Construye el JMenuBar con sus distintos items
	 * @param principal Relacion con la interfaz principal
	 */
	public MenuBar_BotonGuardar(InterfazInicio principal) {
		
		super("Archivo");
		
		this.principal = principal;
		
		meitGuardar = new JMenuItem("Guardar Partida");
		meitSalir = new JMenuItem("Salir");
		
		meitGuardar.setActionCommand(GUARDAR);
		meitSalir.setActionCommand(SALIR);
		
		meitGuardar.addActionListener(this);
		meitSalir.addActionListener(this);
		

		add(meitGuardar);
		addSeparator();
		add(meitSalir);
		
	}
	
	
	/**
	 * Descripci�n: Permite realizar la escucha de los eventos que se presentan en la barra del JMenuBar 
	 */
	@Override
	public void actionPerformed(ActionEvent e) { 
		
		String comando = e.getActionCommand();
		
		if(comando.equals(GUARDAR)) {
			
			principal.guardarJuegoSerializable();
			
		}else if(comando.equals(SALIR)) {
			
			principal.salirJuego();
		}
		
		
		
	}

}
