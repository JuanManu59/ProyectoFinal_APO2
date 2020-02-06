package test;

import static org.junit.Assert.*;

import org.junit.Test;

import mundo.Nave;
import mundo.NaveEscudo;

public class NaveEscudoTest {

	private NaveEscudo nave;
	
	private void setupEscenario3 (){
		nave = new NaveEscudo(10, 10, "x.png", 50, 50, false, 'd');
		nave.setDisparosRecibidos(1);
		nave.setDireccion(nave.DERECHA);
	}
	
	private void setupEscenario4 (){
		nave = new NaveEscudo(10, 10, "x.png", 50, 50, false, 'd');
		nave.setDireccion(Nave.IZQUIERDA);
	}
	
	private void setupEscenario5 (){
		nave = new NaveEscudo(-3, 10, "x.png", 50, 50, false, 'd');
		nave.setDireccion(Nave.IZQUIERDA);
	}
	
	@Test
	public void testRecibioDisparoSuperiorIzquierda (){
		setupEscenario3();
		boolean recibio = nave.recibioDisparo(30, 80, 30, 80);
		assertTrue (recibio);
		assertEquals(nave.getDisparosRecibidos(), 2);
	}
	
	@Test
	public void testRecibioDisparoInferiorIzquierda (){
		setupEscenario3();
		boolean recibio = nave.recibioDisparo(30, 80, 5, 30);
		assertTrue (recibio);
		assertEquals(nave.getDisparosRecibidos(), 2);
	}
	
	@Test
	public void testRecibioDisparoSuperiorDerecha (){
		setupEscenario3();
		boolean recibio = nave.recibioDisparo(5, 30, 30, 80);
		assertTrue (recibio);
		assertEquals(nave.getDisparosRecibidos(), 2);
	}
	
	@Test
	public void testRecibioDisparoInferiorDerecha (){
		setupEscenario3();
		boolean recibio = nave.recibioDisparo(5, 30, 5, 30);
		assertTrue (recibio);
		assertEquals(nave.getDisparosRecibidos(), 2);
	}
	
	@Test
	public void testRecibioDisparoNoLados(){
		setupEscenario3();
		boolean recibio = nave.recibioDisparo(70, 80, 11, 30);
		assertFalse(recibio);
		assertEquals(nave.getDisparosRecibidos(), 1);
	}
	
	@Test
	public void testRecibioDisparoNoArribaAbajo(){
		setupEscenario3();
		boolean recibio = nave.recibioDisparo(11, 30, 70, 80);
		assertFalse(recibio);
		assertEquals(nave.getDisparosRecibidos(), 1);
	}
	
	@Test
	public void testRecibioDisparoEliminada (){
		setupEscenario3();
		boolean recibio = nave.recibioDisparo(11, 30, 11, 30);
		assertTrue (recibio);
		assertFalse (nave.escudoActivo());
		assertTrue (nave.isEliminada());
	}
	
	@Test
	public void testRecibioDisparoNoEliminada(){
		setupEscenario4();
		boolean recibio = nave.recibioDisparo(11, 30, 11, 30);
		assertTrue (recibio);
		assertFalse (nave.isEliminada());
		assertTrue (nave.escudoActivo());
	}
	
	@Test
	public void testValidarMovimientoValido(){
		setupEscenario3();
		nave.validarMovimiento(20, 80);
		assertEquals(nave.getPosX(), 10);
		assertEquals(nave.getPosY(), 10);
		assertEquals(nave.getDireccion(), Nave.DERECHA);
	}
	
	@Test
	public void testValidarMovimientoSuperaAncho(){
		setupEscenario3();
		nave.validarMovimiento(20, 15);
		assertEquals(nave.getPosX(), -40);
		assertEquals(nave.getPosY(), 60);
		assertEquals(nave.getDireccion(), Nave.IZQUIERDA);
	}
	
	@Test
	public void testValidarMovimientoInferior0(){
		setupEscenario5();
		nave.validarMovimiento(20, 80);
		assertEquals(nave.getPosX(), 47);
		assertEquals(nave.getPosY(), 60);
		assertEquals (nave.getDireccion(), Nave.DERECHA);
	}
	
	@Test
	public void testMoverDerechaValido(){
		setupEscenario3();
		nave.mover(20, 0, 20, 80);
		assertEquals(nave.getPosX(), 30);
		assertEquals(nave.getPosY(), 10);
		assertEquals(nave.getDireccion(), Nave.DERECHA);
	}
	
	@Test
	public void testMoverIzquierdaValido(){
		setupEscenario4();
		nave.mover(5, 0, 20, 80);
		assertEquals(nave.getPosX(), 5);
		assertEquals(nave.getPosY(), 10);
		assertEquals(nave.getDireccion(), Nave.IZQUIERDA);
	}
	
	@Test
	public void testMoverDerechaInvalido(){
		setupEscenario3();
		nave.mover(50, 0, 20, 80);
		assertEquals(nave.getPosX(), 10);
		assertEquals(nave.getPosY(), 60);
		assertEquals(nave.getDireccion(), Nave.IZQUIERDA);
	}
	
	@Test
	public void testMoverIzquierdaInvalido(){
		setupEscenario4();
		nave.mover(50, 0, 20, 80);
		assertEquals(nave.getPosX(), 10);
		assertEquals(nave.getPosY(), 60);
		assertEquals(nave.getDireccion(), Nave.DERECHA);
	}
	
	
	
	
}
