package interfaz;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import mundo.Disparo;
import mundo.NaveDisparadora;
import mundo.NaveEnemiga;
import mundo.NaveUsuario;
import mundo.PartidaIndividual;

public abstract class PanelJugar extends JPanel {


	/**
	 * Descripcion: Relacion con la interfaz principal
	 */
	private InterfazInicio principal;
	
	/**
	 * Construye el panel y los elementos que contiene
	 * @param v Es la relacion con la interfaz principal
	 */
	public PanelJugar(InterfazInicio v) {
		principal = v;
		setLayout (new BorderLayout());
		//setPreferredSize (new Dimension (500, 600));
		setBackground(Color.BLACK);
	}
	
	/**
	 * Descripción: Permite pintar diferentes elementos como la nave del usuario y sus respectivos disparos
	 */
	@Override
	public void paintComponent (Graphics g){
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		g2.setColor(Color.GREEN);
		g2.drawRect(0, 0, getWidth(), getHeight());
		g2.setColor(Color.WHITE);
		g2.fillArc(10, 10, 50, 50, 0, 15);
		g2.fillArc(600, 600, 300, 50, 0, 90);
		int x1 [] = {10, 50, 25};
		int y1 [] = {10, 100, 15};
		g2.fillPolygon(x1, y1, 3);
		NaveUsuario nave = principal.getPartida().getNave();
		ImageIcon icon = new ImageIcon (nave.getRutaImagen());
		g2.drawImage(icon.getImage(), nave.getPosX(), nave.getPosY(), null);
		if (principal.getPartida().getNave().isDisparando()){
			g2.setColor(principal.getPartida().getNave().getDisparo().getColor());
			g2.fillRect(principal.getPartida().getNave().getDisparo().getPosX(), principal.getPartida().getNave().getDisparo().getPosY(), Disparo.ANCHO_MISILES, Disparo.ALTO_MISILES);
		}
		
		if (!principal.getPartida().isEnPartida()){
			principal.cambiarPerdiste();
			
			if (principal.getPartida() instanceof PartidaIndividual){
				try {
					principal.getMenuConexion().registrarPuntaje();
				} catch (IOException e) {
					JOptionPane.showMessageDialog(this, "No se pudieron guardar los puntajes, se presento un error al sobreescribir el archivo.");
				}
			}
		}
		
	}
	
	public InterfazInicio getPrincipal() {
		return principal;
	}
	

	
	
}
