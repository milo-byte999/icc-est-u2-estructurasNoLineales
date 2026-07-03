package structures.grafos;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import structures.node.Node;

public class Graph<T> {

    Map<Node<T>, Set<Node<T>>> nodes;

    public Graph() {
        this.nodes = new HashMap<>();
    }

    public void add(Node<T> node) {

    }

    public void add(T value) {
        Node<T> node = new Node<>(value);
        nodes.putIfAbsent(node, new HashSet<>());
    }

    public void addEdge(T v1, T v2) {
        Node<T> nV1 = new Node<>(v1);
        Node<T> nV2 = new Node<>(v2);
        add(v1);
        add(v2);
        nodes.get(nV1).add(nV2);
        nodes.get(nV2).add(nV1);
    }

    public void addEdgeUnit(T v1, T v2) {
        Node<T> nV1 = new Node<>(v1);
        Node<T> nV2 = new Node<>(v2);
        add(v1);
        add(v2);
        nodes.get(nV1).add(nV2);
    }

    public void print() {
        for (Map.Entry<Node<T>, Set<Node<T>>> entry : nodes.entrySet()) {
            System.out.print(entry.getKey() + "->");
            for (Node<T> node : entry.getValue()) {
                System.out.print(node);
            }
            System.out.println();
        }
        System.out.println("Total de direcciones: " + totalDirecciones());
        System.out.println("Total de conexiones: " + totalConexiones());
    }

    public void removeEdge(T v1, T v2) {
        Node<T> nV1 = new Node<>(v1);
        Node<T> nV2 = new Node<>(v2);
        if (nodes.containsKey(nV1) && nodes.containsKey(nV2)) {
            nodes.get(nV1).remove(nV2);
            nodes.get(nV2).remove(nV1);
        }
    }

    public void removeEdgeUnit(T v1, T v2) {
        Node<T> nV1 = new Node<>(v1);
        Node<T> nV2 = new Node<>(v2);
        if (nodes.containsKey(nV1) && nodes.containsKey(nV2)) {
            nodes.get(nV1).remove(nV2);
        }
    }

    public void removeNode(T value) {

        Node<T> node = new Node<>(value);

        if (!nodes.containsKey(node)) {
            return;
        }

        for (Set<Node<T>> vecinos : nodes.values()) {
            vecinos.remove(node);
        }

        nodes.remove(node);
    }

    public int totalDirecciones() {

        int total = 0;

        for (Set<Node<T>> vecinos : nodes.values()) {
            total += vecinos.size();
        }

        return total;
    }

    public int totalConexiones() {
        return totalDirecciones() / 2;
    }

    public void direcciones(T value) {
        Node<T> node = new Node<>(value);
        if (!nodes.containsKey(node)) {
            System.out.println("No existe.");
            return;
        }
        System.out.print(node + " -> ");
        for (Node<T> vecino : nodes.get(node)) {
            System.out.print(vecino + " ");
        }
        System.out.println();
    }

}
// direcciones conecciones eliminar nodo