package maxClique;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;

import grafo.Grafo;

public class MaxClique 
{
	private Grafo grafo;
	private ArrayList<Integer> verticesVisitados;
	private HashSet<Integer> conjuntoClique;


	public MaxClique(Grafo grafo) 
	{
		this.grafo = grafo;
		new ArrayList<>();
		verticesVisitados = new ArrayList<>();
		conjuntoClique = new HashSet<>();


	}


	public HashSet<Integer> resolverCliqueMaxima() 
	{
	    HashSet<Integer> hashADevolver = new HashSet<>();
	    
	    conjuntoClique.clear();
	    verticesVisitados.clear();
	    
	    for (int vertice : ordenarPesosVertices()) 
	    {
	        int posicion = grafo.obtenerPosicionPorPeso(vertice);
	     
	        verticesVisitados.add(posicion);
	        
	        HashSet<Integer> vecinos = grafo.getVecinos(posicion);
	        
	        if (esClique(vecinos) && vecinos.size() > 1) 
	        { 
	            conjuntoClique.addAll(vecinos);
	            hashADevolver.addAll(vecinos);
	            return hashADevolver;
	        }
	    }
	    
	    if (hashADevolver.isEmpty()) 
	    {
	        throw new IllegalArgumentException("No es posible calcular una clique maxima");
	    }
	    
	    return hashADevolver;
	}


	public boolean esClique(HashSet<Integer> vertices) 
	{
		return vertices.stream().allMatch(vertice1 -> vertices.stream()
				.allMatch(vertice2 -> vertice1.equals(vertice2) || grafo.existeArco(vertice1, vertice2)));
	}
	

	public ArrayList<Integer> ordenarPesosVertices() 
	{
		ArrayList<Integer> ret = grafo.getPesosVertices();
		Collections.sort(ret, Collections.reverseOrder());
		return ret;
	}

	public int sumaDePesosVertices(HashSet<Integer> vertices) 
	{
		int ret = 0;
		for (int vertice : vertices) {
			ret += grafo.getPeso(vertice);
		}

		return ret;
	}

}
