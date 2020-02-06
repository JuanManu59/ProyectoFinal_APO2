package test;

import static org.junit.Assert.*;

import org.junit.Test;

import mundo.Disparo;
import mundo.NaveEnemiga;
import mundo.NaveNormal;

public class testNaveNormal {

	private NaveNormal principal;
	
	public void setupEscenario1 (){
		principal = new NaveNormal(1, 1, "", 1, 1, false, NaveEnemiga.DERECHA);
	}
	
	@Test
	public void testRecibioDisparo (){
		setupEscenario1();
		principal.recibioDisparo(1, 1+Disparo.ANCHO_MISILES, 1, 1+Disparo.ALTO_MISILES);
		assertEquals(true, principal.isEliminada());
		
	}
}
