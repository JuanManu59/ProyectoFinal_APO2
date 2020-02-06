package test;

import static org.junit.Assert.*;

import org.junit.Test;

import mundo.NaveDisparadora;
import mundo.NaveEnemiga;

public class NaveDisparadoraTest {

	private NaveDisparadora nave;

	
	public void setUpEscenario1() {
		
		nave = new NaveDisparadora(104 + 20, 40, "./data/imagenes/nave_disparadora.jpg", 52, 41, false, NaveEnemiga.DERECHA);
				
	}
	
	
	@Test
	public void testDisparar() {
		setUpEscenario1();
		nave.disparar(1);
	}
	
//	@Test
//	public void testDetenerDisparo() {
//		
//		nave.detenerDisparo();
//	}
	
	
	

}
