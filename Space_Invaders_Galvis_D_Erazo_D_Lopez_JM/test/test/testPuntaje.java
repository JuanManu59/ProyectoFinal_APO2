package test;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Test;

import mundo.Jugador;
import mundo.MenuPrincipal;
import mundo.Puntaje;

public class testPuntaje {

	private Puntaje principal;
	
	public void setupEscenario1(){
		principal = new Puntaje(new Jugador("Juanma", "Juan", "1234", 18), 500);
		
	}
	
	@Test
	public void testCompararNick() {
		setupEscenario1();
		assertEquals(6, principal.compararNick(new Puntaje(new Jugador("Dangaltor", "Daniel", "1234", 19), 1000)));
	}
	
	@Test
	public void testCompararPuntos(){
		setupEscenario1();
		assertEquals(-200,principal.comparPuntos(new Puntaje(new Jugador("elDavid", "David", "1234", 18), 700)));
	}

}
