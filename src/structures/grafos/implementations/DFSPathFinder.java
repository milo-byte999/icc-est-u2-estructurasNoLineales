package structures.grafos.implementations;

import java.util.ArrayList;
import java.util.List;
import structures.grafos.Graph;
import structures.grafos.PathFinder;
import structures.grafos.PathResult;
import structures.node.Node;

public class DFSPathFinder<T> implements PathFinder<T>{

    @Override
    public PathResult<T> find(Graph<T> graph, T start, T end) {

        List<T> visited = new ArrayList<>();
        List<T> path = new ArrayList<>();

        boolean encontrado = dfs(graph, start, end, visited, path);

        if (!encontrado) {
            path.clear(); // Esta linea de codigo Si no se encuentra el camino, se limpia la lista de camino
        }

        return new PathResult<>(visited, path);//devuelve los visitados 
        
     
    }

    private boolean dfs(Graph<T> graph, T currente, T end, List<T> visited, List<T> path) {
        visited.add(currente);
        path.add(currente);

        Node<T> nC = new Node<>(currente);
        Node<T> nE = new Node<>(end);

        if(nC.equals(nE)) {
            return true; // Cuando se  encontró el nodo final
        }

        for (Node<T> vecino : graph.getVecinos(currente)) {//getVecinos me va a devolver un listado de nodos b y y el nodo j 
            
            if (!visited.contains(vecino.getValue())) {
                boolean encontrado = dfs(
                    graph, 
                    vecino.getValue(), end, 
                    visited, path);
                if (encontrado) {
                    return true; // Si se encontró el camino en la recursión, se retorna true
                }
            }
        }

        path.remove(path.size() - 1);
        return false;
        
    }

    
}