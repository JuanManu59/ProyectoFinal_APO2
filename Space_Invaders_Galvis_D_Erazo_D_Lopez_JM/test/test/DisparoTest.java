package test;

import static org.junit.Assert.*;

import org.junit.Test;

import mundo.Disparador;
import mundo.Disparo;
import mundo.MenuPrincipal;
import mundo.Partida;

public class DisparoTest {

	
	private Disparo disparo;
	//ESCENARIO
	
	public void setUpEscenario1() {
	
		disparo = new Disparo(10, 20, disparo.DISPARO_ALIADO);
	}
	
	public void setUpEscenario2() {
		disparo = new Disparo(10, 20, disparo.DISPARO_ENEMIGO);
	}
	
	
	//PRUEBAS 
		
	@Test
	public void testMoverAliado() {
		setUpEscenario1();
		disparo.mover(10, 5, 1025, 675);		
		assertTrue(disparo.getPosY() == 15);	
	
	}
	
	
	@Test
	public void testMoverEnemigo() {
		setUpEscenario2();
		disparo.mover(10, 5, 1025, 675);
		assertTrue(disparo.getPosY()==25);
	}
	
	

}
