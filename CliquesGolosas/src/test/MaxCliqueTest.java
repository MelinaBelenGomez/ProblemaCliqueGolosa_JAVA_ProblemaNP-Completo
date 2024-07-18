package test;

import grafo.Grafo;
import maxClique.MaxClique;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashSet;

import org.junit.Test;

public class MaxCliqueTest {
	
	@Test
	public void resolverCliqueMaxima() 
	{

		Grafo grafo = new Grafo(6);
		grafo.agregarArco(0, 1);
		grafo.agregarArco(0, 3);
		grafo.agregarArco(1, 3);
		grafo.agregarArco(1, 2);
		grafo.agregarArco(2, 4);
		grafo.agregarArco(1, 4);
		grafo.agregarArco(1, 5);
		grafo.agregarArco(3, 4);
		grafo.agregarArco(4, 5);
		grafo.agregarArco(3, 5);

		grafo.agregarPeso(0, 11);
		grafo.agregarPeso(1, 5);
		grafo.agregarPeso(2, 1);
		grafo.agregarPeso(3, 7);
		grafo.agregarPeso(4, 2);
		grafo.agregarPeso(5, 3);

		MaxClique maxClique = new MaxClique(grafo);
		HashSet<Integer> cliqueMaxima = maxClique.resolverCliqueMaxima();
		assertTrue(maxClique.esClique(cliqueMaxima));

		int pesoMaximoEsperado = 23;
		int pesoCliqueMaxima = maxClique.sumaDePesosVertices(cliqueMaxima);
		assertEquals(pesoMaximoEsperado, pesoCliqueMaxima);
	}

	
	@Test
	public void esCliqueTest() 
	{
		Grafo grafo = new Grafo(5);
		grafo.agregarArco(0, 1);
		grafo.agregarArco(2, 1);
		grafo.agregarArco(0, 2);
		grafo.agregarArco(0, 3);
		grafo.agregarArco(1, 3);
		grafo.agregarArco(2, 3);

		MaxClique maxClique = new MaxClique(grafo);
		HashSet<Integer> verificoClique = grafo.getVecinos(0);
		assertTrue(maxClique.esClique(verificoClique));

	}
	
    
    @Test
    public void ordenarPesosVertices()
    {
    	Grafo grafo= new Grafo (6);
    	grafo.agregarPeso(0, 1);
    	grafo.agregarPeso(1, 78);
    	grafo.agregarPeso(2, 10);
    	grafo.agregarPeso(3, 9);
    	grafo.agregarPeso(4, 3);
    	grafo.agregarPeso(5, 15);
    	
    	ArrayList<Integer> grafoOrdenado= new ArrayList<>();
    	grafoOrdenado.add(78);
    	grafoOrdenado.add(15);
    	grafoOrdenado.add(10);
    	grafoOrdenado.add(9);
    	grafoOrdenado.add(3);
    	grafoOrdenado.add(1);
        MaxClique maxClique = new MaxClique(grafo);
    	ArrayList<Integer> ordenObtenido= maxClique.ordenarPesosVertices();
    	assertEquals(grafoOrdenado,ordenObtenido);
    }
    
    @Test
    public void sumaDePesoVerticesTest()
    {
    	Grafo grafo= new Grafo (3);
    	grafo.agregarPeso(0, 3);
    	grafo.agregarPeso(1, 6);
    	grafo.agregarPeso(2, 2);
    	grafo.agregarArco(0, 1);
    	
    	MaxClique cliqueMaximaTest= new MaxClique(grafo);
    	HashSet<Integer> cliqueMax= cliqueMaximaTest.resolverCliqueMaxima();
    	int valorEsperado=9;
    	assertEquals(valorEsperado,cliqueMaximaTest.sumaDePesosVertices(cliqueMax));
    	
    	
    }

	
	
}
