package interfaz;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

public class PanelMenuJuego extends JPanel implements MouseListener {

	/**
	 * Descripción: Relacion con la interfaz principal
	 */
	private InterfazInicio principal;

	/**
	 * Descripción: Construye el panelMenuJuego y los elementos que contiene 
	 * @param principal La relacion con la interfaz principal
	 */
	public PanelMenuJuego(InterfazInicio principal) {

		this.principal = principal;

		addMouseListener(this);

	}

	/**
	 * Descripción: Pintar los graphics del panelMenuJuego 
	 */
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

		Graphics2D g2 = (Graphics2D) g;
		g2.setColor(Color.BLACK);
		g2.fillRect(0, 0, 1280, 1000);

		ImageIcon imagenFondo = new ImageIcon("./data/imagenes/titulo.png");
		g2.drawImage(imagenFondo.getImage(), 50, 0, null);

		Font font = new Font("OCR A Extended", Font.BOLD, 50);
		g2.setColor(Color.ORANGE);
		g2.setFont(font);
		g2.drawString("Login", 100, 450);
		g2.drawString("Multiplayer", 100, 500);
		g2.drawString("Puntajes", 100, 550);
		g2.drawString("Jugar", 100, 600);
		g2.drawString("Cargar Partida", 100, 650);

	}

	/**
	 * Descripción: Permite realizar la escucha de los eventos que se presentan en el panelMenuJuego
	 */
	@Override
	public void mouseClicked(MouseEvent e) {
		//System.out.println("Escuchó mouse");
		
		if (e.getButton()==MouseEvent.BUTTON1) {
			//System.out.println("Escuchó mouse 1");
			if ((e.getX() >= 100 && e.getX() <= 260) && (e.getY() >= 420 && e.getY() <= 450)) {

				//principal.cambiarJugar(true);
				principal.cambiarLogin();
				//System.out.println("Jugar");

			} else if ((e.getX() >= 100 && e.getX() <= 460) && (e.getY() >= 470 && e.getY() <= 500)) {

				principal.cambiarMultiplayer();
				//System.out.println("Multiplayer");

			} else if ((e.getX() >= 100 && e.getX() <= 360) && (e.getY() >= 520 && e.getY() <= 550)) {

				principal.cambiarPuntaje();
				//System.out.println("Puntajes");

			} else if ((e.getX() >= 100 && e.getX() <= 260) && (e.getY() >= 570 && e.getY() <= 600)) {

				principal.cambiarIndividual(true);
				//System.out.println("Login");

			} else if ((e.getX() >= 100 && e.getX() <= 560) && (e.getY() >= 620 && e.getY() <= 650)) {

				principal.cargarJuegoSerializable();
				
				
			}

		}

	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

}
