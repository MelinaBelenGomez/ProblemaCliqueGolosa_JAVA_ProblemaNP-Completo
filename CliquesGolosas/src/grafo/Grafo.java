package grafo;

import java.util.ArrayList;
import java.util.HashSet;



public class Grafo 
{

	private ArrayList<HashSet<Integer>> vecinos;
	private int cantArcos;
	private ArrayList<Integer> pesosVertices;
	
	
		public Grafo(int cantNodos) 
		{
			cantArcos= 0;			
			vecinos = new ArrayList<HashSet<Integer>>();
			pesosVertices = new ArrayList<Integer>();
			for(int i = 0; i < cantNodos; i++) 
			{
				vecinos.add(new HashSet<Integer>());
				pesosVertices.add(0);
			}			
		}
	
		public void agregarArco(int i, int j) 
		{
			if(esArcoValido(i, j) && i != j) 
			{
				vecinos.get(i).add(j);
				vecinos.get(j).add(i);			
				cantArcos++;
			}
			else 
			{
				throw new IllegalArgumentException("La arista no es valida");
			}
		}
		
		public void agregarPeso(int vertice, int peso)
		{
			if (vertice >= 0 && vertice < pesosVertices.size()&& peso >=0) {
            pesosVertices.set(vertice, peso);
        } else {
            throw new IllegalArgumentException("El vértice está fuera de rango");
        }
    }
		
		public void borrarArco(int i, int j) 
		{
			if(existeArco(i, j)) 
			{
				vecinos.get(i).remove(j);
				vecinos.get(j).remove(i);
				cantArcos--;
			}
		}

		public void cambiarPeso(int vertice, int nuevoPeso) 
		{
		    if (vertice >= 0 && vertice < pesosVertices.size()) {
		        pesosVertices.set(vertice, nuevoPeso);
		    } else {
		        throw new IndexOutOfBoundsException("Vertice no válido");
		    }
		}
		
		public boolean esArcoValido(int i, int j) 
		{
			return i >= 0 && j >= 0 && i<getCantNodos() && j<getCantNodos();
		}
		
		public boolean existeArco(int i, int j) 
		{
			if (esArcoValido(i, j))
			{
				return vecinos.get(i).contains(j);
			}
			return false;
		}
		
		public boolean pesoExiste(int peso) 
		{
	        
	        return pesosVertices.contains(peso);
	    }
		
		public int obtenerPosicionPorPeso(int peso) 
		{
		    if (pesoExiste(peso)) {
		        for (int i = 0; i < pesosVertices.size(); i++) {
		            if (peso == pesosVertices.get(i)) {
		                return i;
		            }
		        }
		    }
		    return -1;
		}
						
		public int getCantNodos() 
		{
			return vecinos.size();
		}
		
		public int getCantArcos()
		{
			return cantArcos;
		}
		
		public int getPeso(int vertice) 
		{
			if(vertice>=0 && vertice < pesosVertices.size()) 
			{
				return pesosVertices.get(vertice);
			
			}
			return -1;
		}
	
		public HashSet<Integer> getVecinos(int i) 
		{
		    HashSet<Integer> conjuntoVecions = new HashSet<>(vecinos.get(i)); 
		    conjuntoVecions.add(i);
		    return conjuntoVecions;
		}
		
		public ArrayList<Integer> getPesosVertices() 
		{
		    ArrayList<Integer> ret = new ArrayList<>();
		    for (Integer peso : pesosVertices) {
		        ret.add(peso);
		    }
		    return ret;
		}

		
}