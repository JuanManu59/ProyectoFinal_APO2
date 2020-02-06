package interfaz;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.ImageIcon;

import mundo.Disparo;
import mundo.NaveDisparadora;
import mundo.NaveEnemiga;
import mundo.NaveUsuario;
import mundo.PartidaIndividual;

public class PanelIndividual extends PanelJugar {



	public PanelIndividual(InterfazInicio principal) {
		super(principal);
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		PartidaIndividual partida = (PartidaIndividual) getPrincipal().getPartida();
		g2.setFont(new  Font("OCR A Extended", Font.BOLD, 20));
		g2.setColor(Color.ORANGE);
		g2.drawString("Puntaje: " + partida.getPuntaje(), 10, 30);
		g2.drawString("Escudo: " + (NaveUsuario.MAX_DISPAROS - getPrincipal().getPartida().getNave().getDisparosRecibidos()), 600, 30);
		g2.drawLine(0, getHeight() - partida.getNave().getAlto(), getWidth(), getHeight() - partida.getNave().getAlto());
		
		NaveEnemiga actual = partida.getPrimera();
		while (actual != null){
			if(!actual.isEliminada()) {				
				ImageIcon icon = new ImageIcon(actual.getRutaImagen());
				g2.drawImage(icon.getImage(), actual.getPosX(), actual.getPosY(), null);
				if (actual instanceof NaveDisparadora){
					NaveDisparadora d = (NaveDisparadora) actual;
					if (d.isDisparando()){
						g2.setColor(d.getDisparo().getColor());
						g2.fillOval(d.getDisparo().getPosX(), d.getDisparo().getPosY(), Disparo.ANCHO_MISILES, Disparo.ALTO_MISILES);
					}
				}
			}
			
			actual = actual.getSiguiente();
		}
		
		
		
	}

}
