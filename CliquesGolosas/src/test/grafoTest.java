package test;

import grafo.Grafo;

import static org.junit.Assert.*;
import java.util.HashSet;

import org.junit.Test;

public class grafoTest {
	

	
	@Test
	public void verificarVecinosTest()
	{
		Grafo grafo = new Grafo(4);
		grafo.agregarArco(1, 0);
		grafo.agregarArco(1, 2);
		grafo.agregarArco(1, 3);
		
		HashSet<Integer> esperado = new HashSet<>();
	    esperado.add(0);
	    esperado.add(2);
	    esperado.add(3);
	    esperado.add(1);
	   
		assertEquals(esperado, grafo.getVecinos(1));
	}
	@Test
	public void borrarArcoExistente()
	{
		Grafo grafo = new Grafo(5);
		grafo.agregarArco(2, 4);	
		grafo.borrarArco(2, 4);
		assertFalse(grafo.existeArco(2, 4));
	}
	
	@Test
	public void borrarArcoInexistenteTest()
	{
		Grafo grafo = new Grafo(5);
		grafo.borrarArco(2, 4);
		assertFalse(grafo.existeArco(2, 4));
	}

	@Test
	public void verificarArcoTest()
	{
		Grafo grafo = new Grafo(5);
		grafo.agregarArco(2, 3);
		assertTrue(grafo.existeArco(2, 3));
	}

	@Test
	public void arcoAlRevesTest()
	{
		Grafo grafo = new Grafo(5);
		grafo.agregarArco(2, 3);
		assertTrue(grafo.existeArco(3, 2));
	}
	
	@Test
	public void arcoInexistenteTest()
	{
		Grafo grafo = new Grafo(5);
		grafo.agregarArco(2, 3);
		assertFalse(grafo.existeArco(1, 4));
	}


	@Test
	public void cambiarPesoVerticeExistenteTest()
	{
		Grafo grafo= new Grafo(5);
		grafo.agregarPeso(0, 3);
		grafo.cambiarPeso(0, 56);
		assertTrue(grafo.pesoExiste(56));
	}
	

	
	@Test
	public void obtenerPosicionPorPesoExistenteTest()
	{
		Grafo grafo= new Grafo(5);
		grafo.agregarPeso(0, 3);
		int valorEsperado= 0;
		assertEquals(valorEsperado, grafo.obtenerPosicionPorPeso(3));
	}
	
	@Test
	public void pesoInexistenteTest()
	{
		Grafo grafo = new Grafo(5);
		grafo.agregarPeso(0, 3);
	    assertFalse(grafo.pesoExiste(6));	        	    
	}

	
	@Test
	public void verticeNormalTest()
	{
		Grafo grafo = new Grafo(5);
		grafo.agregarArco(1, 3);
		grafo.agregarArco(2, 3);
		grafo.agregarArco(2, 4);
		
		HashSet<Integer> esperado = new HashSet<>();
	    esperado.add(1);
	    esperado.add(2);
	    esperado.add(3);
	    
	   
		assertEquals(esperado, grafo.getVecinos(3));
	}


	@Test
	public void agregarArcoTest() {
		Grafo grafo = new Grafo(3);
		grafo.agregarArco(0, 1);
		grafo.agregarArco(1,2);
		grafo.agregarArco(2,0);
		
		int cantidadArcosOk=3;
		
		assertEquals(cantidadArcosOk, grafo.getCantArcos());
	}
	
	@Test
	public void borrarArcoTest() {
		Grafo grafo = new Grafo(3);
		grafo.agregarArco(0, 1);
		grafo.agregarArco(1,2);
		grafo.agregarArco(2,0);
		grafo.borrarArco(0, 1);
		
		int cantidadArcosOk=2;
		assertEquals(cantidadArcosOk, grafo.getCantArcos());
		
	}

	
}
