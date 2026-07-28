package structures.grafos;

import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import structures.node.Node;

public class Graph<T> {

    private Map<T, Node<T>> nodes;
    private Map<Node<T>, Set<Node<T>>> graph;

    public Graph() {
        nodes = new LinkedHashMap<>();
        graph = new LinkedHashMap<>();
    }

    public void add(T data) {
        if (!nodes.containsKey(data)) {
            Node<T> node = new Node<>(data);
            nodes.put(data, node);
            graph.put(node, new LinkedHashSet<>());
        }
    }

    public void addEdge(T v1, T v2) {
        add(v1);
        add(v2);

        Node<T> n1 = nodes.get(v1);
        Node<T> n2 = nodes.get(v2);

        graph.get(n1).add(n2);
        graph.get(n2).add(n1);
    }

    public void addEdgeUni(T v1, T v2) {
        add(v1);
        add(v2);

        Node<T> n1 = nodes.get(v1);
        Node<T> n2 = nodes.get(v2);

        graph.get(n1).add(n2);
    }

    public void removeEdge(T v1, T v2) {
        Node<T> n1 = nodes.get(v1);
        Node<T> n2 = nodes.get(v2);

        if (n1 != null && n2 != null) {
            graph.get(n1).remove(n2);
            graph.get(n2).remove(n1);
        }
    }

    public void removeEdgeUni(T v1, T v2) {
        Node<T> n1 = nodes.get(v1);
        Node<T> n2 = nodes.get(v2);

        if (n1 != null && n2 != null) {
            graph.get(n1).remove(n2);
        }
    }

    public void remove(T data) {
        Node<T> node = nodes.get(data);
        if (node == null)
            return;

        for (Set<Node<T>> conexiones : graph.values()) {
            conexiones.remove(node);
        }

        graph.remove(node);
        nodes.remove(data);
    }

    public void printGraph() {
        for (Map.Entry<Node<T>, Set<Node<T>>> entry : graph.entrySet()) {
            System.out.print(entry.getKey() + " -> ");
            for (Node<T> conexion : entry.getValue()) {
                System.out.print(conexion + " ");
            }
            System.out.println();
        }
    }

    public int totalDirecciones() {
        int total = 0;
        for (Set<Node<T>> conexiones : graph.values()) {
            total += conexiones.size();
        }
        return total;
    }

    public int totalConexiones() {
        Set<String> set = new LinkedHashSet<>();

        for (Map.Entry<Node<T>, Set<Node<T>>> entry : graph.entrySet()) {
            for (Node<T> destino : entry.getValue()) {
                String a = entry.getKey().toString();
                String b = destino.toString();

                String key = (a.compareTo(b) < 0) ? a + "-" + b : b + "-" + a;
                set.add(key);
            }
        }
        return set.size();
    }

    public Set<Node<T>> getVecinos(T currente) {

        Node<T> node = nodes.get(currente);

        if (node == null) {
            return new LinkedHashSet<>();
        }

        return graph.get(node);
    }

    public Set<Node<T>> getNodes() {
        return graph.keySet();
    }

    public Map<Node<T>, Set<Node<T>>> getGraph() {
        return graph;
    }

    public boolean contains(T data) {
        return nodes.containsKey(data);
    }
}