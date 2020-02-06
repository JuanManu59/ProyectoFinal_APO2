package test;

import static org.junit.Assert.*;

import org.junit.Test;

import mundo.Disparo;
import mundo.Nave;
import mundo.NaveDisparadora;
import mundo.NaveEnemiga;
import mundo.NaveEscudo;
import mundo.NaveNormal;
import mundo.NaveUsuario;
import mundo.PartidaIndividual;

public class testPartidaIndividual {

	private PartidaIndividual principal;
	
	public void setupEscenario1 (){
		principal = new PartidaIndividual(0,0);
		principal.setPrimera(null);
	}
	
	public void setupEscenario2 (){
		principal = new PartidaIndividual(100, 100);
		principal.setPrimera(null);
		NaveNormal enemy = new NaveNormal(1, 1, "", 1, 1, false, Nave.DERECHA);
		principal.agregarNave(enemy);
	}
	
	public void setupEscenario3 (){
		principal = new PartidaIndividual(100, 100);
		principal.setPrimera(null);
		NaveEscudo enemy = new NaveEscudo(1, 1, "", 1, 1, false,  Nave.DERECHA);
		NaveDisparadora enemy2 = new NaveDisparadora(60, 2, "", 1, 1, false,  Nave.IZQUIERDA);
		NaveNormal enemy3 = new NaveNormal(3, 3, "", 1, 1, false,  Nave.DERECHA);
		principal.agregarNave(enemy);
		principal.agregarNave(enemy2);
		principal.agregarNave(enemy3);
	}
	
	@Test
	public void testMoverDisparo(){
		setupEscenario2();
		NaveUsuario user = new NaveUsuario(1, 100, "", 1, 1);
		user.setDisparando(true);
		user.setDisparo(new Disparo(1,3, Disparo.DISPARO_ALIADO));
		principal.setNave(user);
		principal.moverDisparo();
		principal.getPrimera().recibioDisparo(1, 1+Disparo.ANCHO_MISILES, 1, 1+Disparo.ALTO_MISILES);
		assertEquals(true, principal.getPrimera().isEliminada());
		assertEquals(principal.getPuntaje(), 10);
	}
	
	@Test
	public void testAgregarNave() {
		NaveEscudo aAgregar = new NaveEscudo(4, 4, "", 1, 1, false, Nave.IZQUIERDA);

		setupEscenario1();
		principal.agregarNave(aAgregar);
		assertEquals(1, principal.contadorNaves());
		
		setupEscenario2();
		principal.agregarNave(aAgregar);
		assertEquals(2, principal.contadorNaves());
		
		setupEscenario3();
		principal.agregarNave(aAgregar);
		assertEquals(4, principal.contadorNaves());
	}
	
	@Test
	public void testContinuar(){
		setupEscenario1();
		assertEquals(false,principal.continuar());
		
		setupEscenario2();
		assertEquals(true,principal.continuar());
		
		setupEscenario2();
		assertEquals(true,principal.continuar());
	}
	
	@Test
	public void testMoverNaves(){
		setupEscenario3();

		int x = principal.getPrimera().getPosX();
		int y = principal.getPrimera().getPosY();
		
		int x2 = principal.getPrimera().getSiguiente().getPosX();
		int y2 = principal.getPrimera().getSiguiente().getPosY();

		int x3 = principal.getPrimera().getSiguiente().getSiguiente().getPosX();
		int y3 = principal.getPrimera().getSiguiente().getSiguiente().getPosY();
		
		principal.moverNaves();
		assertEquals(principal.getPrimera().getPosX(), x+NaveEnemiga.MOVIMIENTO);
		assertEquals(principal.getPrimera().getPosY(), y);
		
		assertEquals(principal.getPrimera().getSiguiente().getPosX(), x2-NaveEnemiga.MOVIMIENTO);
		assertEquals(principal.getPrimera().getSiguiente().getPosY(), y2);
		
		assertEquals(principal.getPrimera().getSiguiente().getSiguiente().getPosX(), x3+NaveEnemiga.MOVIMIENTO);
		assertEquals(principal.getPrimera().getSiguiente().getSiguiente().getPosY(), y3);
	
	}
}
