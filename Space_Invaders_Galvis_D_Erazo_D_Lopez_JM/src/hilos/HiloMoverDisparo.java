package hilos;

import interfaz.InterfazInicio;
import mundo.NaveUsuario;
import mundo.Partida;


public class HiloMoverDisparo implements Runnable{
	private InterfazInicio ventana;
	private Partida partida;
	
	public HiloMoverDisparo (InterfazInicio v, Partida p){
		ventana = v;
		partida = p;
	}
	
	
	@Override
	public void run() {
		while (partida.getNave().isDisparando()){
			partida.moverDisparo();
			ventana.refrescarPanel();
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
	}
	
}
