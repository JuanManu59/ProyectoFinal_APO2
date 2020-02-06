package test;

import static org.junit.Assert.*;

import org.junit.Test;

import mundo.Jugador;
import mundo.JugadorYaExisteException;

public class JugadorTest {
	
	private Jugador raiz;
	
	private void setupEscenario1(){
		raiz = new Jugador("dan", "Daniel", "123", 29);
	}
	
	private void setupEscenario2(){
		raiz = new Jugador("dan", "Daniel", "123", 29);
		Jugador izquierda = new Jugador("a", "Andres", "1", 10);
		raiz.setIzquierda(izquierda);
		Jugador derecha = new Jugador("gss", "David", "aaa", 18);
		Jugador derecha2 = new Jugador("jRex", "Juan", "123", 20);
		derecha.setDerecha(derecha2);
		raiz.setDerecha(derecha);
	}
	
	@Test
	public void testAnadirJugadorMenorRaiz (){
		setupEscenario1();
		Jugador nuevo = new Jugador("a", "x", "123", 12);
		try {
			raiz.anadirJugador(nuevo);
			assertEquals(nuevo, raiz.getIzquierda());
			assertNull(raiz.getDerecha());
		} catch (JugadorYaExisteException e) {
			fail ("El jugador debe de agregarse correctamente, por lo que no deberia de generarse excepcion");
		}
		
	}
	
	@Test
	public void testAnadirJugadorMayorRaiz(){
		setupEscenario1();
		Jugador nuevo = new Jugador("z", "x", "1", 38);
		try {
			raiz.anadirJugador(nuevo);
			assertNull(raiz.getIzquierda());
			assertEquals(nuevo, raiz.getDerecha());
		} catch (JugadorYaExisteException e) {
			fail ("El jugador debe de agregarse correctamente, por lo que no deberia de generarse excepcion");
		}
	}
	
	@Test
	public void testAnadirJugadorMismoNickRaiz (){
		setupEscenario1();
		Jugador nuevo = new Jugador("dan", "x", "12", 20);
		try {
			raiz.anadirJugador(nuevo);
			fail ("Debe de generarse JugadorYaExisteException");
		} catch (JugadorYaExisteException e) {
			assertNull(raiz.getDerecha());
			assertNull(raiz.getIzquierda());
		}
	}
	
	@Test
	public void testAnadirJugadorArbolGrande(){
		setupEscenario2();
		Jugador nuevo = new Jugador("hola", "x", "123", 20);
		try {
			raiz.anadirJugador(nuevo);
			assertEquals(raiz.getIzquierda().getNickname(), "a");
			assertEquals(raiz.getDerecha().getNickname(), "gss");
			assertNull (raiz.getDerecha().getIzquierda());
			assertEquals(raiz.getDerecha().getDerecha().getNickname(), "jRex");
			assertNull(raiz.getDerecha().getDerecha().getDerecha());
			assertEquals(raiz.getDerecha().getDerecha().getIzquierda(), nuevo);
		} catch (JugadorYaExisteException e) {
			fail ("No deben de generarse excepciones ya que el jugador debe de añadirse correctamente");
		}
	}
	
	@Test 
	public void testAnadirJugadorYaExistente() {
		setupEscenario2();
		Jugador nuevo = new Jugador("gss", "x", "123", 20);
		try {
			raiz.anadirJugador(nuevo);
			fail ("Ya existe un jugador con el nick gss, por lo que debe de generarse JugadorYaExisteException");
		} catch (JugadorYaExisteException e) {
			assertEquals(raiz.getIzquierda().getNickname(), "a");
			assertEquals(raiz.getDerecha().getNickname(), "gss");
			assertNull (raiz.getDerecha().getIzquierda());
			assertEquals(raiz.getDerecha().getDerecha().getNickname(), "jRex");
			assertNull(raiz.getDerecha().getDerecha().getDerecha());
			assertNull(raiz.getDerecha().getDerecha().getIzquierda());
		}
		
	}

}
