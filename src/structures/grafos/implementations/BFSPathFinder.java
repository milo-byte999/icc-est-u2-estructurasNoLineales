package structures.grafos.implementations;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

import structures.grafos.Graph;
import structures.grafos.PathFinder;
import structures.grafos.PathResult;
import structures.node.Node;

public class BFSPathFinder<T> implements PathFinder<T> {

    @Override
    public PathResult<T> find(Graph<T> graph, T start, T end) {

        List<T> visited = new ArrayList<>();
        List<T> path = new ArrayList<>();

        Set<T> visitedSet = new LinkedHashSet<>();
        Map<T, T> predecesores = new LinkedHashMap<>();
        Queue<T> cola = new ArrayDeque<>();

        cola.add(start);
        visitedSet.add(start);

        boolean encontrado = false;

        while (!cola.isEmpty()) {
            T actual = cola.poll();
            visited.add(actual);

            if (actual.equals(end)) {
                encontrado = true;
                break;
            }

            for (Node<T> vecino : graph.getVecinos(actual)) {
                T valorVecino = vecino.getValue();

                if (!visitedSet.contains(valorVecino)) {
                    visitedSet.add(valorVecino);
                    predecesores.put(valorVecino, actual);
                    cola.add(valorVecino);
                }
            }
        }

        if (encontrado) {
            T paso = end;
            while (paso != null) {
                path.add(0, paso);
                paso = predecesores.get(paso);
            }
        }

        return new PathResult<>(visited, path);
    }
}