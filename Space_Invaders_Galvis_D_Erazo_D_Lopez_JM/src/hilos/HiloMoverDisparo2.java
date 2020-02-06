package hilos;

import interfaz.InterfazInicio;
import mundo.PartidaMultijugador;

public class HiloMoverDisparo2 implements Runnable{

	private InterfazInicio ventana;
	private PartidaMultijugador partida;
	
	public HiloMoverDisparo2 (InterfazInicio v, PartidaMultijugador p){
		ventana = v;
		partida = p;
	}
	
	@Override
	public void run() {
		while (partida.getNave2().isDisparando()){
			partida.moverDisparoNave2();
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
