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

public class PanelRegistro extends JPanel implements ActionListener {

	/**
	 * Descripción: Constante que representa la acción del boton registrarse
	 */
	public static final String REGISTRARSE = "Registrarse";
	/**
	 * Descripción: Constante que representa la acción del boton volver
	 */
	public static final String VOLVER = "Volver";

	/**
	 * Descripción: Jlabel que representa un espacio vacio en la interfaz
	 */
	private JLabel labVacio1;
	/**
	 * Descripción: Jlabel que representa un espacio vacio en la interfaz
	 */
	private JLabel labVacio2;
	/**
	 * Descripción: Jlabel que representa un espacio vacio en la interfaz
	 */
	private JLabel labVacio3;
	/**
	 * Descripción: Jlabel que representa un espacio vacio en la interfaz
	 */
	private JLabel labVacio4;
	/**
	 * Descripción: Jlabel que representa un espacio vacio en la interfaz
	 */
	private JLabel labVacio5;

	/**
	 * Descripción: Es el boton para registrar un jugador
	 */
	private JButton butRegistrar;
	/**
	 * Descripción: Es el boton para volver al panel login
	 */
	private JButton butVolver;
	/**
	 * Descripción: Jlabel que representa el espacio donde se muestra el titulo
	 * Registro
	 */
	private JLabel labImagenRegistro;
	/**
	 * Descripción: Jlabel que representa el campo de la contraseña del jugador
	 */
	private JLabel labcontrasena;
	/**
	 * Descripción: Jlabel que representa el campo del nombre del jugador
	 */
	private JLabel labNombre;
	/**
	 * Descripción: Jlabel que representa el campo de la edad del jugador
	 */
	private JLabel labEdad;

	/**
	 * Descripción: Jlabel que respresenta el campo del nickName del jugador
	 */
	private JLabel labNickName;

	/**
	 * Descripción: JtextField que representa el campo con la informacion de la
	 * contraseña del jugador
	 */
	private JPasswordField txtContrasena;
	/**
	 * Descripción: JtextField que representa el campo con la informacion del nombre
	 * del jugador
	 */
	private JTextField txtNombre;
	/**
	 * Descripción: JtextField que representa el campo con la informacion del
	 * nickName del jugador
	 */
	private JTextField txtNick;
	/**
	 * Descripción: JtextField que representa el campo con la informacion de la edad
	 * del jugador
	 */
	private JTextField txtEdad;

	/**
	 * Descripción: Relacion con la interfaz principal
	 */
	private InterfazInicio principal;

	public PanelRegistro(InterfazInicio principal) {

		this.principal = principal;

		labVacio1 = new JLabel(" ");
		labVacio2 = new JLabel(" ");
		labVacio3 = new JLabel(" ");
		labVacio4 = new JLabel(" ");
		labVacio5 = new JLabel(" ");

		labcontrasena = new JLabel("Contraseña: ");
		labNombre = new JLabel("Nombre: ");
		labNickName = new JLabel("Nick Name: ");
		labEdad = new JLabel("Edad: ");

		butVolver = new JButton("Volver");
		butVolver.setActionCommand(VOLVER);
		butVolver.addActionListener(this);
		butVolver.setFont(new Font("OCR A Extended", Font.BOLD, 20));

		butRegistrar = new JButton("Registrarse");
		butRegistrar.setActionCommand(REGISTRARSE);
		butRegistrar.addActionListener(this);
		butRegistrar.setFont(new Font("OCR A Extended", Font.BOLD, 20));

		labImagenRegistro = new JLabel();
		ImageIcon icon = new ImageIcon("./data/imagenes/tituloRegistro.png");
		labImagenRegistro.setIcon(icon);

		labNickName.setFont(new Font("OCR A Extended", Font.BOLD, 20));
		labNombre.setFont(new Font("OCR A Extended", Font.BOLD, 20));
		labEdad.setFont(new Font("OCR A Extended", Font.BOLD, 20));
		labcontrasena.setFont(new Font("OCR A Extended", Font.BOLD, 20));

		labNickName.setForeground(Color.ORANGE);
		labNombre.setForeground(Color.ORANGE);
		labcontrasena.setForeground(Color.ORANGE);
		labEdad.setForeground(Color.ORANGE);

		txtNombre = new JTextField();
		txtNick = new JTextField();
		txtEdad = new JTextField();
		txtContrasena = new JPasswordField();

		txtNombre.setFont(new Font("OCR A Extended", Font.BOLD, 20));
		txtNick.setFont(new Font("OCR A Extended", Font.BOLD, 20));
		txtEdad.setFont(new Font("OCR A Extended", Font.BOLD, 20));
		txtContrasena.setFont(new Font("OCR A Extended", Font.BOLD, 20));

		setLayout(new BorderLayout());

		JPanel auxInfo = new JPanel();
		auxInfo.setLayout(new GridLayout(7, 2));

		auxInfo.setBackground(Color.BLACK);

		auxInfo.add(labVacio1);
		auxInfo.add(labVacio2);

		auxInfo.add(labNickName);
		auxInfo.add(txtNick);
		auxInfo.add(labNombre);
		auxInfo.add(txtNombre);
		auxInfo.add(labcontrasena);
		auxInfo.add(txtContrasena);
		auxInfo.add(labEdad);
		auxInfo.add(txtEdad);

		auxInfo.add(butVolver);
		auxInfo.add(butRegistrar);

		auxInfo.add(labVacio4);
		auxInfo.add(labVacio5);

		add(labImagenRegistro, BorderLayout.NORTH);
		add(auxInfo, BorderLayout.CENTER);

	}

	/**
	 * Descripción: Permite limpiar los campos de la informacion del jugador
	 */
	public void limpiarCamposInformacion() {

		txtNick.setText("");
		txtNombre.setText("");
		txtEdad.setText("");
		txtContrasena.setText("");

	}

	/**
	 * Descripción: Permiten realizar la escucha de los evento que se realizan en el
	 * panelRegistro
	 */
	@Override
	public void actionPerformed(ActionEvent e) {

		String comando = e.getActionCommand();
		if (comando.equals(REGISTRARSE)) {
			// Registrar la persona

			if (txtNombre.getText() == null || txtNombre.getText().equals("") || txtNick.getText() == null
					|| txtNick.getText().equals("") || txtContrasena.getText() == null
					|| txtContrasena.getText().equals("") || txtEdad.getText() == null
					|| txtEdad.getText().equals("")) {
				JOptionPane.showMessageDialog(null, "Porfavor complete todos los campos del registro");
			} else {

				String edad = txtEdad.getText().trim();
				String nombre = txtNombre.getText().trim();
				String nickname = txtNick.getText().trim();
				String contrasena = txtContrasena.getText();

				principal.registrarJugador(nombre, nickname, contrasena, edad);

				limpiarCamposInformacion();

			}

		} else if (comando.equals(VOLVER)) {

			principal.cambiarLogin();
			limpiarCamposInformacion();

		}

	}

}
