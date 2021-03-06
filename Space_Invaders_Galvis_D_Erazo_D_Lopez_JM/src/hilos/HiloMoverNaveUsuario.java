package hilos;

import java.awt.Component;

import interfaz.InterfazInicio;
import mundo.Nave;
import mundo.NaveUsuario;
import mundo.Partida;


public class HiloMoverNaveUsuario implements Runnable{
	private Partida partida;
	private InterfazInicio ventana;
	private char direccion;
	
	public HiloMoverNaveUsuario (Partida p, InterfazInicio v, char dir){
		partida = p;
		ventana = v;
		direccion = dir;
	}
	
	@Override
	public void run() {
		while (partida.getNave().isEnMovimiento()){
		
			if (direccion == Nave.DERECHA){
				partida.moverNaveUsuarioDerecha();
				
			} else {
				partida.moverNaveUsuarioIzquierda();
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


