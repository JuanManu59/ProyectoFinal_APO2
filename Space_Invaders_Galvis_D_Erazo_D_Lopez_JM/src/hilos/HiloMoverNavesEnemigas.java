package hilos;

import interfaz.InterfazInicio;
import mundo.PartidaIndividual;


public class HiloMoverNavesEnemigas implements Runnable{
	private PartidaIndividual partida;
	private InterfazInicio ventana;
	
	public HiloMoverNavesEnemigas (PartidaIndividual p, InterfazInicio v){
		partida = p;
		ventana = v;
	}
	
	
	@Override
	public void run() {
		while (partida.isEnPartida()){
			partida.moverNaves();
			ventana.refrescarPanel();
			try {
				Thread.sleep(partida.getEsperaHiloNavesEnemigas());
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
	}

}
