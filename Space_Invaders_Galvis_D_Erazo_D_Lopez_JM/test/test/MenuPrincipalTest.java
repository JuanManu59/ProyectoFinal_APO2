package test;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.junit.Test;

import mundo.ContrasenaIncorrectaException;
import mundo.Jugador;
import mundo.JugadorYaExisteException;
import mundo.MenuPrincipal;
import mundo.Nave;
import mundo.NaveEnemiga;
import mundo.NaveEscudo;
import mundo.NaveNormal;
import mundo.NaveUsuario;
import mundo.NoEstaEnMejoresPuntajesException;
import mundo.NoExisteJugadorException;
import mundo.Partida;
import mundo.PartidaIndividual;
import mundo.Puntaje;

public class MenuPrincipalTest {

	private MenuPrincipal menu;
	
	private void setupEscenario6 () throws ClassNotFoundException, IOException{
		menu = new MenuPrincipal();
		Puntaje [] mejoresPuntajes  = new Puntaje [10];
		menu.setRaiz(new Jugador("d", "dan", "123", 10));
		menu.getRaiz().setIzquierda(new Jugador("a", "An", "123", 20));
		menu.getRaiz().setDerecha(new Jugador("h", "Her", "123", 50));
		Puntaje punt = new Puntaje(menu.getRaiz(), 180);
		punt.setFecha(punt.deFormatoADate("19-11-2017"));
		mejoresPuntajes [0] = punt;
		Puntaje punt2 = new Puntaje(menu.getRaiz().getIzquierda(), 300);
		punt2.setFecha(punt2.deFormatoADate("19-11-2017"));
		mejoresPuntajes [1] = punt2;
		Puntaje punt3 = new Puntaje(menu.getRaiz().getDerecha(), 200);
		punt3.setFecha(punt3.deFormatoADate("19-11-2017"));
		mejoresPuntajes [9] = punt3;
		menu.setMejoresPuntajes(mejoresPuntajes);
	}
	
	private void setupEscenario7 (){
		try {
			menu = new MenuPrincipal();
			Puntaje [] mejoresPuntajes  = new Puntaje [10];
			menu.setRaiz(new Jugador("d", "dan", "123", 10));
			menu.getRaiz().setIzquierda(new Jugador("a", "An", "123", 20));
			menu.getRaiz().setDerecha(new Jugador("h", "Her", "123", 50));
			Puntaje punt = new Puntaje(menu.getRaiz(), 180);
			punt.setFecha(punt.deFormatoADate("19-11-2017"));
			mejoresPuntajes [0] = punt;
			Puntaje punt2 = new Puntaje(menu.getRaiz().getIzquierda(), 300);
			punt2.setFecha(punt2.deFormatoADate("19-11-2017"));
			mejoresPuntajes [1] = punt2;
			Puntaje punt3 = new Puntaje(menu.getRaiz().getDerecha(), 200);
			punt3.setFecha(punt3.deFormatoADate("19-11-2017"));
			mejoresPuntajes [2] = punt3;
			mejoresPuntajes [3] = new Puntaje(menu.getRaiz().getIzquierda(), 180, "19-11-2017");
			mejoresPuntajes[4] = new Puntaje(menu.getRaiz().getIzquierda(), 180, "19-11-2017");
			mejoresPuntajes [5] =  new Puntaje(menu.getRaiz().getDerecha(), 180, "19-11-2017");
			mejoresPuntajes [6] = new Puntaje(menu.getRaiz().getDerecha(), 300, "19-11-2017");
			mejoresPuntajes [7] = new Puntaje (menu.getRaiz(), 100, "19-11-2017");
			mejoresPuntajes[8] = new Puntaje(menu.getRaiz().getDerecha(), 300, "19-11-2017");
			mejoresPuntajes [9] = new Puntaje(menu.getRaiz().getDerecha(), 180, "19-11-2017");
			menu.setMejoresPuntajes(mejoresPuntajes);
		} catch (ClassNotFoundException | IOException e) {
			fail ("no se deben de generar excepciones");
		}
	}
	
	private void setupEscenario8(){
		setupEscenario7();
		try {
			menu.setLogueado(menu.buscarJugador("d", menu.getRaiz()));
			menu.nuevaPartida(200, 300);
			menu.getPartida().setNave(new NaveUsuario(10, 20, "x.png", 10, 20));
			NaveNormal n = new NaveNormal(0, 10, "y.png", 10, 20, false, Nave.DERECHA);
			n.setSiguiente(new NaveEscudo(5, 10, "y.png", 10, 10, true, Nave.DERECHA));
			PartidaIndividual p = (PartidaIndividual) menu.getPartida();
			p.setPrimera(n);
			p.setPuntaje(100);
		} catch  (Exception e) {
			fail ("No deben de generarse excepciones");
		}
	}

	
	
	@Test
	public void testOrdenarPuntajePorPuntos(){
		try {
			setupEscenario6();
			Puntaje primero = menu.getMejoresPuntajes() [1];
			Puntaje segundo = menu.getMejoresPuntajes() [9];
			Puntaje tercero = menu.getMejoresPuntajes() [0];
			menu.ordenarPuntajesPorPuntos();
			assertEquals (primero, menu.getMejoresPuntajes()[0]);
			assertEquals(segundo, menu.getMejoresPuntajes()[1]);
			assertEquals (tercero, menu.getMejoresPuntajes()[2]);
			for (int i = 3; i < 10; i++){
				assertNull (menu.getMejoresPuntajes()[i]);
			}
		} catch (IOException | ClassNotFoundException e) {
			fail ("No deben de generarse excepciones");
		}
	}
	
	@Test
	public void testOrdenarPuntajePorPuntosArraylist(){
		try {
			setupEscenario6();
			ArrayList <Puntaje> puntos = new ArrayList<Puntaje>();
			puntos.add(menu.getMejoresPuntajes()[0]);
			puntos.add(menu.getMejoresPuntajes()[1]);
			puntos.add(menu.getMejoresPuntajes()[9]);
			Puntaje primero = puntos.get(1);
			Puntaje segundo = puntos.get(2);
			Puntaje tercero = puntos.get(0);
			menu.ordenarPuntajesPorPuntos(puntos);
			assertEquals(primero, puntos.get(0));
			assertEquals(segundo, puntos.get(1));
			assertEquals(tercero, puntos.get(2));
		} catch (ClassNotFoundException | IOException e) {
			fail ("No deben de generarse excepciones");
		}
	}
	
	@Test
	public void testOrdenarPuntajePorNickname(){
		try {
			setupEscenario6();
			Puntaje primero = menu.getMejoresPuntajes() [1];
			Puntaje segundo = menu.getMejoresPuntajes() [0];
			Puntaje tercero = menu.getMejoresPuntajes() [9];
			menu.ordenarPuntajesPorNickname();
			assertEquals (primero, menu.getMejoresPuntajes()[0]);
			assertEquals(segundo, menu.getMejoresPuntajes()[1]);
			assertEquals (tercero, menu.getMejoresPuntajes()[2]);
			for (int i = 3; i < 10; i++){
				assertNull (menu.getMejoresPuntajes()[i]);
			}
		} catch (ClassNotFoundException | IOException e) {
			fail ("No deben de generarse excepciones");
		}
	}
	
	@Test
	public void testAnadirPuntajePocisionNula (){
		try {
			setupEscenario6();
			Puntaje primero = menu.getMejoresPuntajes() [1];
			Puntaje segundo = menu.getMejoresPuntajes() [9];
			Puntaje tercero = menu.getMejoresPuntajes() [0];
			Puntaje nuevo = new Puntaje(new Jugador("o", "Os", "123", 15), 10);
			nuevo.setFecha(nuevo.deFormatoADate("20-11-2017"));
			menu.anadirPuntaje(nuevo, MenuPrincipal.RUTA1_TEST);
			assertEquals (primero, menu.getMejoresPuntajes()[0]);
			assertEquals(segundo, menu.getMejoresPuntajes()[1]);
			assertEquals (tercero, menu.getMejoresPuntajes()[2]);
			assertEquals(nuevo, menu.getMejoresPuntajes()[3]);
			for (int i = 4; i < 10; i++){
				assertNull (menu.getMejoresPuntajes()[i]);
			}
			
		} catch (ClassNotFoundException | IOException e) {
			fail ("No debe de generar excepciones");
		}
		
	}
	
	@Test
	public void testAnadirPuntajeSinNulosCorrectamente(){
		setupEscenario7();
		Puntaje eliminado = menu.getMejoresPuntajes()[7];
		Puntaje nuevo = new Puntaje(new Jugador("o", "Os", "123", 15), 150);
		try {
			menu.anadirPuntaje(nuevo, MenuPrincipal.RUTA1_TEST);
			for (int i = 0; i < 9; i++){
				assertTrue(menu.getMejoresPuntajes()[i].getPuntos() >= menu.getMejoresPuntajes()[i+1].getPuntos());
				assertNotEquals(eliminado, menu.getMejoresPuntajes()[i]);
				assertNotEquals(nuevo, menu.getMejoresPuntajes()[i]);
			}
			assertEquals(nuevo, menu.getMejoresPuntajes()[9]);
		} catch (IOException e) {
			fail ("No se deben de generar excepciones");
		}
	}
	
	@Test
	public void testAnadirPuntajeSinNulosMenor(){
		setupEscenario7();
		Puntaje nuevo = new Puntaje(new Jugador("o", "Os", "123", 15), 10);
		try {
			menu.anadirPuntaje(nuevo, MenuPrincipal.RUTA1_TEST);
			for (int i = 0; i < 9; i++){
				assertTrue(menu.getMejoresPuntajes()[i].getPuntos() >= menu.getMejoresPuntajes()[i+1].getPuntos());
				assertNotEquals(nuevo, menu.getMejoresPuntajes()[i]);
			}
			assertNotEquals(nuevo, menu.getMejoresPuntajes()[9]);
		} catch (IOException e) {
			fail ("No se deben de generar excepciones");
		}
	}
	
	@Test
	public void testRegistrarPuntajeMenor(){
		setupEscenario8();
		try {
			menu.registrarPuntaje(MenuPrincipal.RUTA1_TEST);
			Puntaje partidaActual = new Puntaje(menu.getLogeado(), 100);
			for (int i = 0; i < MenuPrincipal.TOP_PUNTAJES; i++){
				assertNotEquals(partidaActual, menu.getMejoresPuntajes()[i]);
			}
		} catch (IOException e) {
			fail ("No deben de generarse excepciones");
		}
	}
	
	@Test
	public void testRegistrarPuntajeValido(){
		setupEscenario8();
		PartidaIndividual p = (PartidaIndividual) menu.getPartida();
		p.setPuntaje(500);
		try {
			menu.registrarPuntaje(MenuPrincipal.RUTA1_TEST);
			assertEquals(menu.getMejoresPuntajes()[0].getPuntos(), 500);
			assertEquals(menu.getMejoresPuntajes()[0].getJugador(), menu.getRaiz());
			SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
			String fecha = format.format(new Date());
			assertEquals(menu.getMejoresPuntajes()[0].deDateAFormate(), fecha);
		} catch (IOException e) {
			fail ("No deben de generarse excepciones");
		}
	}
	
	@Test (expected = NoEstaEnMejoresPuntajesException.class)
	public void testBusquedaBinariaPuntajePorJugadorNoExiste() throws NoEstaEnMejoresPuntajesException{
		setupEscenario8();
		menu.busquedaBinariaPuntajePorJugador("victor");
	}
	
	@Test
	public void testBusquedaBinariaPuntajePorJugador() throws NoEstaEnMejoresPuntajesException{
		setupEscenario8();
		ArrayList<Puntaje> arreglo = menu.busquedaBinariaPuntajePorJugador("h");
		assertTrue(arreglo.size() == 5);
		for (int i = 0; i < arreglo.size(); i++){
			assertEquals(arreglo.get(i).getNickJugador(), "h");
		}
	}
	
	@Test
	public void testAnadirJugadorNumberFormatException(){
		setupEscenario8();
		Jugador raiz = menu.getRaiz();
		Jugador izquierda = menu.getRaiz().getIzquierda();
		Jugador derecha = menu.getRaiz().getDerecha();
		try {
			menu.anadirJugador("x", "x", "123", "uno", MenuPrincipal.RUTA3_TEST);
			fail ("Debe de generarse NumberFormatException");
		} catch (NumberFormatException e) {
			assertEquals(raiz, menu.getRaiz());
			assertEquals(izquierda, menu.getRaiz().getIzquierda());
			assertEquals(derecha, menu.getRaiz().getDerecha());
			assertNull(menu.getRaiz().getIzquierda().getIzquierda());
			assertNull(menu.getRaiz().getIzquierda().getDerecha());
			assertNull(menu.getRaiz().getDerecha().getIzquierda());
			assertNull(menu.getRaiz().getDerecha().getDerecha());
		} catch (IOException  | JugadorYaExisteException s ) {
			fail("La excepcion debe de ser de tipo NumberFormatException");
		} 
	}
	
	@Test
	public void testAnadirJugadorNumberFormatException2(){
		setupEscenario8();
		Jugador raiz = menu.getRaiz();
		Jugador izquierda = menu.getRaiz().getIzquierda();
		Jugador derecha = menu.getRaiz().getDerecha();
		try {
			menu.anadirJugador("x", "x", "123", "-3", MenuPrincipal.RUTA3_TEST);
			fail ("Debe de generarse NumberFormatException");
		} catch (NumberFormatException e) {
			assertEquals(raiz, menu.getRaiz());
			assertEquals(izquierda, menu.getRaiz().getIzquierda());
			assertEquals(derecha, menu.getRaiz().getDerecha());
			assertNull(menu.getRaiz().getIzquierda().getIzquierda());
			assertNull(menu.getRaiz().getIzquierda().getDerecha());
			assertNull(menu.getRaiz().getDerecha().getIzquierda());
			assertNull(menu.getRaiz().getDerecha().getDerecha());
		} catch (IOException  | JugadorYaExisteException s ) {
			fail("La excepcion debe de ser de tipo NumberFormatException");
		} 
	}
	
	@Test
	public void testAnadirJugadorExistente(){
		setupEscenario8();
		Jugador raiz = menu.getRaiz();
		Jugador izquierda = menu.getRaiz().getIzquierda();
		Jugador derecha = menu.getRaiz().getDerecha();
		try {
			menu.anadirJugador("a", "x", "123", "10", MenuPrincipal.RUTA3_TEST);
			fail ("Debe de generarse NumberFormatException");
		} catch (JugadorYaExisteException e) {
			assertEquals(raiz, menu.getRaiz());
			assertEquals(izquierda, menu.getRaiz().getIzquierda());
			assertEquals(derecha, menu.getRaiz().getDerecha());
			assertNull(menu.getRaiz().getIzquierda().getIzquierda());
			assertNull(menu.getRaiz().getIzquierda().getDerecha());
			assertNull(menu.getRaiz().getDerecha().getIzquierda());
			assertNull(menu.getRaiz().getDerecha().getDerecha());
		} catch (IOException  | NumberFormatException s ) {
			fail("La excepcion debe de ser de tipo JugadorYaExisteException");
		} 
	}
	
	@Test
	public void testAnadirJugador() throws NumberFormatException, FileNotFoundException, JugadorYaExisteException, IOException{
		setupEscenario8();
		setupEscenario8();
		Jugador raiz = menu.getRaiz();
		Jugador izquierda = menu.getRaiz().getIzquierda();
		Jugador derecha = menu.getRaiz().getDerecha();
		menu.anadirJugador("v", "x", "123", "10", MenuPrincipal.RUTA3_TEST);
		assertEquals(raiz, menu.getRaiz());
		assertEquals(izquierda, menu.getRaiz().getIzquierda());
		assertEquals(derecha, menu.getRaiz().getDerecha());
		assertNull(menu.getRaiz().getIzquierda().getIzquierda());
		assertNull(menu.getRaiz().getIzquierda().getDerecha());
		assertNull(menu.getRaiz().getDerecha().getIzquierda());
		assertEquals(menu.getRaiz().getDerecha().getDerecha().getNickname(), "v");
		assertEquals(menu.getRaiz().getDerecha().getDerecha().getNombre(), "x");
		assertEquals(menu.getRaiz().getDerecha().getDerecha().getContraseña(), "123");
		assertEquals (menu.getRaiz().getDerecha().getDerecha().getEdad(), 10);
		assertNull(menu.getRaiz().getDerecha().getDerecha().getDerecha());
		assertNull(menu.getRaiz().getDerecha().getDerecha().getIzquierda());
	}
	
	@Test
	public void testAnadirJugadorAlArbolVacio(){
		try {
			menu = new MenuPrincipal();
			Jugador nuevo = new Jugador("a", "x", "123", 20);
			menu.setRaiz(null);
			menu.anadirJugadorAlArbol(nuevo, MenuPrincipal.RUTA3_TEST);
			assertEquals(nuevo, menu.getRaiz());
		} catch (ClassNotFoundException | IOException | JugadorYaExisteException e) {
			fail ("No deben de generarse excepciones");
		}
	}
	
	@Test
	public void testAnadirJugadorAlArbol(){
		setupEscenario8();
		Jugador nuevo = new Jugador("b", "x", "123", 20);
		try {
			menu.anadirJugadorAlArbol(nuevo, MenuPrincipal.RUTA3_TEST);
			assertEquals(menu.getRaiz().getIzquierda().getDerecha(), nuevo);
		} catch (JugadorYaExisteException | IOException e) {
			fail ("No se deben de generar excepciones");
		}
	}
	
	@Test (expected = JugadorYaExisteException.class)
	public void testAnadirJugadorAlArbolExistente() throws JugadorYaExisteException{
		setupEscenario8();
		Jugador nuevo = new Jugador("h", "x", "123", 20);
		try {
			menu.anadirJugadorAlArbol(nuevo, MenuPrincipal.RUTA3_TEST);
		} catch (IOException e) {
			fail ("Esta excepcion no debe de ocurrir");
		}
	}
	
	@Test
	public void testbuscarJugadorRaiz(){
		setupEscenario8();
		try {
			Jugador buscado = menu.buscarJugador("d", menu.getRaiz());
			assertEquals(buscado, menu.getRaiz());
		} catch (NoExisteJugadorException e) {
			fail ("No debe de generar excepciones");
		}
	}
	
	@Test
	public void testBuscarJugadorNoRaiz(){
		setupEscenario8();
		try {
			Jugador buscado = menu.buscarJugador("a", menu.getRaiz());
			assertEquals(buscado, menu.getRaiz().getIzquierda());
		} catch (NoExisteJugadorException e) {
			fail ("No debe de generar excepciones");
		}
	}
	
	@Test (expected = NoExisteJugadorException.class)
	public void testBuscarJugadorNoExistente() throws NoExisteJugadorException{
		setupEscenario8();
		Jugador buscado = menu.buscarJugador("s", menu.getRaiz());
		fail ("El metodo debe de lanzar excepcion en el paso anterior");
	}
	
	@Test
	public void testLogJugadorNoLogueadoIncorrecto(){
		setupEscenario7();
		try {
			menu.logJugador(menu.getRaiz(), "x");
			fail ("Debe de ocurrir ContrasenaIncorrectaException");
		} catch (ContrasenaIncorrectaException e) {
			assertNull(menu.getLogueado());
		}
	}
	
	@Test
	public void testLogJugadorLogueadoIncorrecto(){
		setupEscenario8();
		try {
			menu.logJugador(menu.getRaiz().getIzquierda(), "x");
			fail ("Debe de ocurrir ContrasenaIncorrectaException");
		} catch (ContrasenaIncorrectaException e) {
			assertEquals(menu.getLogueado(), menu.getRaiz());
		}
	}
	
	@Test
	public void testLogJugadorNoLogueadoCorrecto(){
		setupEscenario7();
		try {
			menu.logJugador(menu.getRaiz(), "123");
			assertEquals(menu.getLogeado(), menu.getRaiz());
		} catch (ContrasenaIncorrectaException e) {
			fail ("No debe de ocurrir ContrasenaIncorrectaException");
		}
	}
	
	@Test
	public void testLogJugadorLogueadoCorrecto(){
		setupEscenario8();
		try {
			menu.logJugador(menu.getRaiz().getDerecha(), "123");
			assertEquals(menu.getLogeado(), menu.getRaiz().getDerecha());
		} catch (ContrasenaIncorrectaException e) {
			fail ("No debe de ocurrir ContrasenaIncorrectaException");
		}
	}
	
	@Test
	public void testNuevaPartidaSinLog (){
		setupEscenario7();
		try {
			menu.nuevaPartida(100, 100);
			fail ("Debe de generarse excepcion");
		} catch (Exception e) {
			assertEquals(e.getMessage(), "Debe de loguearse con algún jugador");
			assertNull(menu.getPartida());
		}
	}
	
	@Test
	public void testNuevaPartidaNoNullConLog(){
		setupEscenario8();
		Partida p = menu.getPartida();
		try {
			menu.nuevaPartida(100, 100);
			assertNotEquals(p, menu.getPartida());
			PartidaIndividual ind = (PartidaIndividual) menu.getPartida();
			assertEquals(ind.contadorNaves(), 15);
			assertNotNull(ind.getNave());
		} catch (Exception e) {
			fail ("No debe de generar excepcion");
		}
	}
	
	@Test
	public void testNuevaPartidaNullConLog (){
		setupEscenario7();
		menu.setLogueado(menu.getRaiz());
		try {
			menu.nuevaPartida(100, 100);
			assertNotNull(menu.getPartida());
			PartidaIndividual ind = (PartidaIndividual) menu.getPartida();
			assertEquals(ind.contadorNaves(), 15);
			assertNotNull(ind.getNave());
		} catch (Exception e) {
			fail ("No debe de generarse excepcion");
		}
	}
	
	@Test
	public void testCargarGuardarPuntajes (){
		setupEscenario8();
		menu.ordenarPuntajesPorPuntos();
		Puntaje [] antes = menu.getMejoresPuntajes();
		try {
			menu.guardarPuntajesYnombres(MenuPrincipal.RUTA1_TEST);
			menu.setMejoresPuntajes(null);
			menu.cargarPuntajesYnombres(MenuPrincipal.RUTA1_TEST);
			assertTrue(menu.arreglosIguales(antes, menu.getMejoresPuntajes()));
		} catch (IOException e) {
			fail ("No debe de generar excepciones.");
		}
	}
	
	@Test
	public void testCargarGuardarJugadores (){
		setupEscenario8();
		Jugador raizAntes = menu.getRaiz();
		System.out.println(raizAntes.getNickname());
		try {
			menu.guardarJugadores(MenuPrincipal.RUTA3_TEST);
			menu.setRaiz(null);
			menu.cargarJugadores(MenuPrincipal.RUTA3_TEST);
			assertEquals (menu.getRaiz().getNickname(), raizAntes.getNickname());
			assertEquals(menu.getRaiz().getIzquierda().getNickname(), raizAntes.getIzquierda().getNickname());
			assertEquals(menu.getRaiz().getDerecha().getNickname(), raizAntes.getDerecha().getNickname());
			assertEquals(menu.getRaiz().getIzquierda().getIzquierda(), raizAntes.getIzquierda().getIzquierda());
			assertEquals(menu.getRaiz().getDerecha().getDerecha(), raizAntes.getDerecha().getDerecha());
			assertEquals(menu.getRaiz().getIzquierda().getDerecha(), raizAntes.getIzquierda().getDerecha());
			assertEquals(menu.getRaiz().getDerecha().getIzquierda(), raizAntes.getDerecha().getIzquierda());
			
		} catch (IOException | ClassNotFoundException e) {
			fail ("No debe de generar excepcion");
		}
	}
	
	@Test
	public void testCargarPartidaUsuarioNulo(){
		setupEscenario7();
		try {
			menu.cargarSerializablePartida(MenuPrincipal.RUTA2_TEST);
			fail ("Debe de lanzar excepcion");
		} catch (Exception e) {
			assertEquals(e.getMessage(), "Registrese con algun usuario");
		}
	}
	
	@Test
	public void testCargarPartidaNoGuardada(){
		setupEscenario7();
		menu.setLogueado(menu.getRaiz().getIzquierda());
		try {
			menu.cargarSerializablePartida(MenuPrincipal.RUTA2_TEST);
			fail ("Debe de lanzar excepcion");
		} catch (Exception e) {
			assertEquals(e.getMessage(), "El usuario no tiene ninguna partida guardada");
		}
	}
	
	@Test
	public void testCargarGuardarPartida (){
		setupEscenario8();
		PartidaIndividual antes = (PartidaIndividual) menu.getPartida();
		
		try {
			menu.guardarSerialisablePartida(MenuPrincipal.RUTA2_TEST);
			menu.setPartida(null);
			menu.cargarSerializablePartida(MenuPrincipal.RUTA2_TEST);
			PartidaIndividual despues = (PartidaIndividual) menu.getPartida();
			assertEquals(despues.getPuntaje(), antes.getPuntaje());
			assertEquals(antes.getNave().getPosX(), despues.getNave().getPosX());
			assertTrue (antes.getNave().isDisparando() == despues.getNave().isDisparando());
			NaveEnemiga primeraAntes = antes.getPrimera();
			NaveEnemiga primeraDespues = despues.getPrimera();
			assertEquals(antes.contadorNaves(), despues.contadorNaves());
			while (primeraAntes != null && primeraDespues != null){
				assertEquals(primeraAntes.getPosX(), primeraDespues.getPosX());
				assertEquals(primeraAntes.getPosY(), primeraDespues.getPosY());
				assertTrue (primeraAntes.isEliminada() == primeraDespues.isEliminada());
				primeraAntes = primeraAntes.getSiguiente();
				primeraDespues = primeraDespues.getSiguiente();
			}
			if (antes.getNave().isDisparando()){
				assertEquals(antes.getNave().getDisparo().getPosX(), despues.getNave().getDisparo().getPosX());
				assertEquals(antes.getNave().getDisparo().getPosY(), despues.getNave().getDisparo().getPosY());
			}
		} catch (Exception e) {
			fail ("No debe de lanzar excepcion");
		}
	}
}
