package hilos;

import interfaz.InterfazInicio;
import mundo.PartidaIndividual;

public class HiloDisparoEnemigo implements Runnable{

	private PartidaIndividual partida;
	private InterfazInicio ventana;
	
	public HiloDisparoEnemigo(PartidaIndividual p, InterfazInicio v) {
		partida = p;
		ventana = v;
	}
	
	
	@Override
	public void run() {
		while (partida.isEnPartida()){
			partida.moverDisparosEnemigos();
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
