package interfaz;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import mundo.Jugador;
import mundo.Puntaje;

public class PanelInformacionJugador extends JPanel {

	/**
	 * Descripci�n: Jlabel que muestra el campo Nombre del jugador 
	 */
	private JLabel labNombre;
	/**
	 * Descripci�n: Jlabel que muestra el campo Edad del jugador
	 */
	private JLabel labEdad;
	/**
	 * Descripci�n: Jlabel que muestra el campo puntaje del jugador 
	 */
	private JLabel labPuntaje;
	/**
	 * Descripci�n: Jlabel que muestra el campo de la fecha del jugador 
	 */
	private JLabel labFecha;
	/**
	 * Descripci�n: Jlabel que muestra el campo del nick name dle jugador 
	 */
	private JLabel labNickName;
	
	
	/**
	 * Descripci�n: JtextField que muestra el campo con la informacion del nombre del jugador
	 */
	private JTextField txtNombre;
	/**
	 * Descripci�n: JtextField que muestra el campo con la informacion del nick name del jugador 
	 */
	private JTextField txtNick;
	/**
	 * Descripci�n: JtextField que muestra el campo con la informacion de la edad del jugador 
	 */
	private JTextField txtEdad;
	/**
	 * Descripci�n: JtextFied que muestra el campo con la informacion del puntaje del jugador 
	 */
	private JTextField txtPuntaje;
	/**
	 * Descripci�n: JtextField que muestra el campo con la informacion de la fecha del jugador 
	 */
	private JTextField txtFecha;

	/**
	 * Descripci�n: Construye el panel Informacion del jugador y los elementos que contiene 
	 */
	public PanelInformacionJugador() {

		
		labNombre = new JLabel("Nombre: ");
		labNickName = new JLabel("Nick Name: ");
		labEdad = new JLabel("Edad: ");
		labPuntaje = new JLabel("Puntaje: ");
		labFecha = new JLabel("Fecha: ");

		
		labNickName.setFont(new Font("OCR A Extended", Font.BOLD, 30));
		labNombre.setFont(new Font("OCR A Extended", Font.BOLD, 30));
		labEdad.setFont(new Font("OCR A Extended", Font.BOLD, 30));
		labFecha.setFont(new Font("OCR A Extended", Font.BOLD, 30));
		labPuntaje.setFont(new Font("OCR A Extended", Font.BOLD, 30));

		labNickName.setForeground(Color.ORANGE);
		labNombre.setForeground(Color.ORANGE);
		labPuntaje.setForeground(Color.ORANGE);
		labFecha.setForeground(Color.ORANGE);
		labEdad.setForeground(Color.ORANGE);

		
	
		
		txtNombre = new JTextField();
		txtNick = new JTextField();
		txtEdad = new JTextField();
		txtPuntaje = new JTextField();
		txtFecha = new JTextField();

		
		txtNombre.setEditable(false);
		txtNick.setEditable(false);
		txtEdad.setEditable(false);
		txtFecha.setEditable(false);
		txtPuntaje.setEditable(false);
		
		
		txtNombre.setFont(new Font("OCR A Extended", Font.BOLD, 30));
		txtNick.setFont(new Font("OCR A Extended", Font.BOLD, 30));
		txtEdad.setFont(new Font("OCR A Extended", Font.BOLD, 30));
		txtFecha.setFont(new Font("OCR A Extended", Font.BOLD, 30));
		txtPuntaje.setFont(new Font("OCR A Extended", Font.BOLD, 30));

		setLayout(new GridLayout(7, 2));

		
		add(labNickName);
		add(txtNick);
		add(labNombre);
		add(txtNombre);
		add(labEdad);
		add(txtEdad);
		add(labFecha);
		add(txtFecha);
		add(labPuntaje);
		add(txtPuntaje);

	}

	/**
	 * Descripci�n: Permite refrescar los datos del jugador cuando el usuario desea conocer su informaci�n
	 * @param jugador Un objeto tipo Puntaje 
	 */
	public void refrestarDatosJugador(Puntaje jugador) {

		txtNombre.setText(jugador.getJugador().getNombre());
		txtNick.setText(jugador.getJugador().getNickname());
		txtEdad.setText(jugador.getJugador().getEdad()+"");
		txtPuntaje.setText(jugador.getPuntos()+"");
		txtFecha.setText(jugador.deDateAFormate()+"");
		
	}

}
