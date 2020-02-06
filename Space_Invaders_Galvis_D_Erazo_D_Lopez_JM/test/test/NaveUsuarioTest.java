package test;

import static org.junit.Assert.*;

import org.junit.Test;

import mundo.Disparo;
import mundo.NaveUsuario;

public class NaveUsuarioTest {


	private NaveUsuario nave;
	
	//ESCENARIO
	
	public void setUpEscenario1() {
		nave = new NaveUsuario(10,10, "./data/imagenes/Nave_Aliada.png", 70, 70);
	}
	
	public void setUpEscenario2() {
		nave = new NaveUsuario(20, 20,"./data/imagenes/Nave_Aliada.png", 70, 70);
		nave.setDisparosRecibidos(1);
		
	}
	
	

	//PRUEBAS
	@Test
	public void testMover() {
		setUpEscenario1();
		nave.mover(10, 10, 500, 500);
		assertEquals(nave.getPosX(), 20);
		assertEquals(nave.getPosY(), 10);
		
	}
	@Test
	public void testDisparar() {
		setUpEscenario1();
		boolean disparando = false;
		nave.disparar(10);
		disparando = nave.isDisparando();
		assertTrue(disparando);
		assertNotNull(nave.getDisparo());
		
		
	}
	
	@Test
	public void testDetenerDisparo() {
		boolean disparando = true;
		setUpEscenario1();
		nave.detenerDisparo();
		disparando = nave.isDisparando();
		assertFalse(disparando);
		assertNull(nave.getDisparo());
		
	}
	@Test
	public void testDisparo2() {
		setUpEscenario1();
		boolean disparando = false;
		nave.disparo2();
		disparando = nave.isDisparando();
		assertTrue(disparando);
		
	}
	@Test
	public void testRecibioDisparo() {
		setUpEscenario2();
		boolean recibio = nave.recibioDisparo(30, 80, 30, 80);
		assertTrue(recibio);
		assertEquals(nave.getDisparosRecibidos(), 2);
		
		
	}
	@Test
	public void testEscudoActivo() {
		setUpEscenario2();
		boolean escudoActivo = nave.escudoActivo();
		assertTrue(escudoActivo);
	}
	

}
