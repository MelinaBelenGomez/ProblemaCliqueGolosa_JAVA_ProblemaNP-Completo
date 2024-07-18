package test;

import static org.junit.Assert.assertThrows;
import org.junit.Test;
import grafo.Grafo;
import maxClique.MaxClique;

public class logicaTest {
	
	//--------- Excepciones en MaxCliqueTest ---------
    
	   
    public void cliqueMaximaFallaTest() 
    {
    	Grafo grafo = new Grafo(3);
    	MaxClique cliqueTest= new MaxClique(grafo);
    		    assertThrows(IllegalArgumentException.class, () -> 
    		    {
    		        cliqueTest.resolverCliqueMaxima();
    		    });
    }
    
    
	
	//--------- Excepciones en Grafo ---------
	
	@Test
	public void verticeNegativoTest()
	{
	    Grafo grafo = new Grafo(3);
	    assertThrows(IllegalArgumentException.class, () -> 
	    {
	        grafo.agregarArco(0, -1);
	    });
	}
	
	
	@Test
	public void cambiarPesoVerticeInexistenteTest()
	{
		  Grafo grafo = new Grafo(5);
		    assertThrows(IllegalArgumentException.class, () -> 
		    {
		        grafo.agregarPeso(15, 16);
		    });
	}
	


	@Test
	public void VerticeExcedidoTest()
	{
	    Grafo grafo = new Grafo(5);
	    assertThrows(IllegalArgumentException.class, () -> 
	    {
	        grafo.agregarArco(5, 3);
	    });
	}
	

    

    
   
}