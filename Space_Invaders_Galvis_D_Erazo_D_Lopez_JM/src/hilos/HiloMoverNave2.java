package hilos;

import interfaz.InterfazInicio;
import mundo.Nave;
import mundo.NaveUsuario;
import mundo.PartidaMultijugador;

public class HiloMoverNave2 implements Runnable{
	private PartidaMultijugador partida;
	private InterfazInicio ventana;
	private char direccion;
	
	public HiloMoverNave2(PartidaMultijugador p, InterfazInicio v, char d){
		partida = p;
		ventana = v;
		direccion = d;
	}
	
	@Override
	public void run() {
		while (partida.getNave2().isEnMovimiento()){
			
			if (direccion == Nave.DERECHA){
				partida.moverNave2Derecha();
			} else {
				partida.moverNave2Izquierda();
			}
			ventana.refrescarPanel();
			try {
				Thread.sleep(NaveUsuario.ESPERA_HILO);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}

}
