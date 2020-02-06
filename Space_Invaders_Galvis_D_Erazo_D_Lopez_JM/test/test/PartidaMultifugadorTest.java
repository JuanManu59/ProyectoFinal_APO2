package test;

import static org.junit.Assert.*;

import org.junit.Test;

import mundo.Disparo;
import mundo.MenuPrincipal;
import mundo.NaveUsuario;
import mundo.PartidaMultijugador;

public class PartidaMultifugadorTest {

	
	//ESCENARIOS
	
	NaveUsuario nave2;
	PartidaMultijugador partida;
	
	public void setUpEscenario1() {
		partida = new PartidaMultijugador(500, 500);
		partida.getNave().setDisparosRecibidos(1);
	}
	
	
	
	
	//PRUEBAS
	
	@Test
	public void testMoverDisparo() {
		setUpEscenario1();
		boolean escudoActivo = partida.getNave().escudoActivo();
		assertTrue(escudoActivo);
		assertEquals(partida.isEnPartida(), true);			

	}
	
	@Test
	public void testEmpezarMovimientoNave2() {
		
		setUpEscenario1();
		partida.empezarMovimientoNave2();
		assertTrue(partida.getNave2().isEnMovimiento());
		
	}
	
	@Test
	public void testDetenerNave2() {
		setUpEscenario1();
		partida.detenerNave2();
		assertFalse(partida.getNave2().isEnMovimiento());
		
		
	}
	
	@Test
	public void testMoverNave2Derecha() {
		setUpEscenario1();
		partida.moverNave2Derecha();
		assertEquals(partida.getNave2().getPosX(),217);
	}
	
	@Test
	public void testMoverNave2Izquierda() {
		setUpEscenario1();
		partida.moverNave2Izquierda();
		assertEquals(partida.getNave2().getPosX(), 213);
	}
	

	@Test
	public void testDetenerDisparoNave2() {
		setUpEscenario1();
		boolean disparo = false;
		disparo = partida.getNave2().isDisparando();
		partida.detenerDisparo2();
		assertFalse(disparo);
		
	}
	

}
