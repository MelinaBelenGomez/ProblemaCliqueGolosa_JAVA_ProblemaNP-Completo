package logica;
import java.util.HashSet;
import grafo.Grafo;
import maxClique.MaxClique;

public class Logica {
	
	private Grafo grafo;
	private MaxClique cliqueMaxima;
	
	
	public Logica (int cantNodos)
	{
		grafo= new Grafo(cantNodos);
		cliqueMaxima = new MaxClique(grafo);
	}

	public void agregarArco(int i, int j)
	{
		grafo.agregarArco(i, j);
	}
	
	public void borrarArco(int i, int j)
	{
		grafo.borrarArco(i, j);
	}
	
	public void agregarPeso(int vertice, int peso)
	{
		grafo.agregarPeso(vertice, peso);
	}
	
	public void cambiarPeso(int vertice, int nuevoPeso)
	{
		grafo.cambiarPeso(vertice, nuevoPeso);
	}
	
	public boolean esArcoValido(int i, int j)
	{
		return grafo.esArcoValido(i, j);
	}
	
	public HashSet<Integer> resolverCliqueMaxima ()
	{
		return cliqueMaxima.resolverCliqueMaxima();
	}
	
	public int obtenerPeso (int vertice)
	{
		return grafo.getPeso(vertice);
	}
	
	public int cantidadArcos()
	{
		return grafo.getCantArcos();
	}
	


	
	public int getPeso (int vertice)
	{
		return grafo.getPeso(vertice);
	}

}
