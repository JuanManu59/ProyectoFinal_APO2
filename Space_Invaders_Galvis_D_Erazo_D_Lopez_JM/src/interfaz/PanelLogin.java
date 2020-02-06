package interfaz;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class PanelLogin extends JPanel implements ActionListener {

	
	/**
	 * Descripci�n: Constante que representa la acci�n del boton logeo
	 */
	public static final String LOGIN = "Logeo";
	/**
	 * Descripci�n: Constante que representa la acci�n del boton registrar
	 */
	public static final String REGISTRAR = "Rregistrar";
	/**
	 * Descripci�n: Constante que rerpresenta la acci�n del boton volver
	 */
	public static final String VOLVER = "Volver";
	
	/**
	 * Descripci�n: Jlabel que muestra el campo Usuario
	 */
	private JLabel labUser;
	/**
	 * Descripci�n: Jlabel que muestra el campo Constrase�a
	 */
	private JLabel labPassword;
	/**
	 * Descripci�n: JtextFlied que muestra el campo con la informacion de usuario del jugador 
	 */
	private JTextField txtUser;
	/**
	 * Descripci�n: JtextFlied que muestra el campo con la informacion de la contrase�a del jugador
	 */
	private JPasswordField txtPassword;
	/**
	 * Descripci�n: Jlabel que muestra el campo de la imagen del titulo
	 */
	private JLabel labImagenTitulo;
	/*
	 * Descripci�n: Es el boton que permite logear el jugador
	 */
	private JButton butLogin;
	/**
	 * Descripci�n: Es el boton que permite registrar el jugador
	 */
	private JButton butRegistra;
	/**
	 * Descripci�n: Es el boton que permite volver al panelMenu
	 */
	private JButton butVolver;
	
	/**
	 * Descripci�n: Jlabel que respresenta un espacio vacio 
	 */
	private JLabel labVacio2;
	/**
	 * Descripci�n: Jlabel que representa un espacio vacio
	 */
	private JLabel labVacio3;
	/**
	 * Descripci�n:Jlabel que representa un espacio vacio
	 */
	private JLabel labVacio4;
	/**
	 * Descripci�n: Jlabel que representa un espacio vacio 
	 */
	private JLabel labVacio12;
	/**
	 * Descripci�n: Jlabel que representa un espacio vacio
	 */
	private JLabel labVacio13;

	/**
	 * Descripci�n: Es la relacion con la interfaz principal
	 */
	private InterfazInicio principal;

	/**
	 * Descripci�n: Construye el panel Login y los elementos que contiene
	 * @param principal La relacion con la interfaz principal
	 */
	public PanelLogin(InterfazInicio principal) {

		this.principal = principal;

		

		labVacio2 = new JLabel(" ");
		labVacio3 = new JLabel(" ");
		labVacio4 = new JLabel(" ");
		labVacio12 = new JLabel(" ");
		labVacio13 = new JLabel(" ");

		labImagenTitulo = new JLabel();
		ImageIcon icon = new ImageIcon("./data/imagenes/tituloLogin.png");
		labImagenTitulo.setIcon(icon);

		labUser = new JLabel("Usuario ");
		labPassword = new JLabel("Constrase�a ");
		txtUser = new JTextField("");
		txtPassword = new JPasswordField("");

		labUser.setFont(new Font("OCR A Extended", Font.BOLD, 15));
		labPassword.setFont(new Font("OCR A Extended", Font.BOLD, 15));
		
		txtUser.setFont(new Font("OCR A Extended", Font.BOLD, 15));
		txtPassword.setFont(new Font("OCR A Extended", Font.BOLD, 15));
		
	
		butVolver = new JButton("Volver");
		butLogin = new JButton("Login");
		butRegistra = new JButton("Registrarse");

		butLogin.addActionListener(this);
		butRegistra.addActionListener(this);
		butVolver.addActionListener(this);

		butLogin.setActionCommand(LOGIN);
		butVolver.setActionCommand(VOLVER);
		butRegistra.setActionCommand(REGISTRAR);

		butLogin.setFont(new Font("OCR A Extended", Font.BOLD, 15));
		butRegistra.setFont(new Font("OCR A Extended", Font.BOLD, 15));
		butVolver.setFont(new Font("OCR A Extended", Font.BOLD, 15));
		
		labUser.setForeground(Color.ORANGE);
		labPassword.setForeground(Color.ORANGE);
		
		setLayout(new BorderLayout());

		JPanel aux = new JPanel();
		aux.setLayout(new GridLayout(4, 3));
		aux.setBackground(Color.BLACK);
		
		aux.add(labVacio2);
		aux.add(labVacio3);
		aux.add(labVacio4);

		aux.add(labUser);
		aux.add(labPassword);
		aux.add(butLogin);

		aux.add(txtUser);
		aux.add(txtPassword);
		aux.add(butRegistra);
		aux.add(labVacio12);
		aux.add(labVacio13);
		aux.add(butVolver);

		

		add(labImagenTitulo, BorderLayout.NORTH);
		add(aux, BorderLayout.CENTER);

	}
	
	/**
	 * Descripci�n: Permite consultar el nickName del jugador
	 * @return un String con el nickName del jugador
	 */
	public String getNickName() {
		return txtUser.getText();
	}
	
	/**
	 * Descripci�n: Permite consultar la contrase�a del jugador
	 * @return un String con la contrase�a del jugador
	 */
	public String getContrasena() {
		return txtPassword.getText();
	}

	/**
	 * Descripci�n:Permite limpiar los campos de texto una vez logeado
	 */
	public void limpiarCampos() {
		txtUser.setText("");
		txtPassword.setText("");		
	}
	
	/**
	 * Descripci�n: Permite realizar la escucha de los eventos que se presentan en el panelLogin
	 */
	@Override
	public void actionPerformed(ActionEvent e) {

		String comando = e.getActionCommand();

		if (comando.equals(LOGIN)) {

			if(getNickName()==null || getContrasena()== null || getNickName().equals("") || getContrasena().equals("")) {
				JOptionPane.showMessageDialog(null, "Por favor complete los campos");
			}else {
				principal.logearse(getNickName(),getContrasena());
				limpiarCampos();
			}
			
			
		} else if (comando.equals(REGISTRAR)) {
			
			principal.cambiarRegistrar();
			limpiarCampos();
			
		} else if(comando.equals(VOLVER)) {
			
			principal.cambiarPanelMenu();
			limpiarCampos();
			
		}

	}

}
