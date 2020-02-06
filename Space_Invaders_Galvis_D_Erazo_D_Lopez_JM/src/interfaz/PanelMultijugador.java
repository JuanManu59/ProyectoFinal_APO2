package interfaz;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import mundo.Disparo;
import mundo.NaveUsuario;
import mundo.PartidaMultijugador;

public class PanelMultijugador extends PanelJugar {
	
	
	public PanelMultijugador(InterfazInicio principal) {
		super(principal);
	}
	
	/**
	 * Descripcion: 
	 */
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		PartidaMultijugador multi = (PartidaMultijugador) getPrincipal().getPartida();
		 NaveUsuario nave2 = multi.getNave2();
		 ImageIcon icon = new ImageIcon(nave2.getRutaImagen());
		 g2.drawImage(icon.getImage(), nave2.getPosX(), nave2.getPosY(), null);
		 if (nave2.isDisparando()){
			 g2.setColor(nave2.getDisparo().getColor());
			 g2.fillRect(nave2.getDisparo().getPosX(), nave2.getDisparo().getPosY(), Disparo.ANCHO_MISILES, Disparo.ALTO_MISILES);
		 }
		
		
	
		
	}
	
	
	
}
