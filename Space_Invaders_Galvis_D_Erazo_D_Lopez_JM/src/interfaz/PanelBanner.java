package interfaz;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class PanelBanner extends JPanel{

	/**
	 * Descripción: Es por el cual se muestra una imagen
	 */
	private JLabel imagen;
	
	/**
	 * Descripción: Construye el panel y sus distintos componentes
	 * @param ruta Un String con la ruta de la imagen a mostrar en el panel
	 */
	public PanelBanner(String ruta) {

		imagen = new JLabel();
		ImageIcon icono = new ImageIcon(ruta);
		imagen.setIcon(icono);
		
		add(imagen);
	}
	
	
	
	
}
