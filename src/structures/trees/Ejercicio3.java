package structures.trees;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import structures.node.Node;

public class Ejercicio3 {

    public List<List<Node>> ListLevels(Node root) {

        List<List<Node>> niveles = new ArrayList<>();

        if (root == null) {
            return niveles;
        }

        Queue<Node> cola = new LinkedList<>();
        cola.add(root);

        while (!cola.isEmpty()) {

            int cantidad = cola.size();
            List<Node> nivel = new LinkedList<>();

            for (int i = 0; i < cantidad; i++) {

                Node actual = cola.poll();
                nivel.add(actual);

                if (actual.getLeft() != null) {
                    cola.add(actual.getLeft());
                }
                if (actual.getRight() != null) {
                    cola.add(actual.getRight());
                }
            }
            niveles.add(nivel);
        }

        System.out.println("----- Ejercicio 3 -----");
        int numeroNivel = 0;

        for (List<Node> lista : niveles) {
            System.out.print("Nivel " + numeroNivel + ": ");
            for (Node nodo : lista) {
                System.out.print(nodo.getValue() + " ");
            }
            System.out.println();
            numeroNivel++;
        }
        return niveles;
    }
}