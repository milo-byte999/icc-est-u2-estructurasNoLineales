package structures.trees;

import structures.node.Node;

public class Ejercicio1 {

    public void insert(int[] numeros) {
        // ARBOL DE ENTEROS
        // INSERTAR CADA NUMERO

        BynariTree<Integer> arbol = new BynariTree<>();

        // INSERTAR CADA NUMERO
        for (int numero : numeros) {
            arbol.add(numero);
        }

        System.out.println("----- Ejercicio 1 -----");
        // IMPRIMIR EL ARBOL
        System.out.println("Inorden:");
        arbol.inOrden();

        printTree(arbol.getRoot());

    }

    public void printTree(Node<Integer> root) {
        System.out.println("Imprimiendo el árbol");
        printTreeRecursivo(root, 0);
    }

    private void printTreeRecursivo(Node<Integer> actual, int nivel) {

        if (actual == null)
            return;

        printTreeRecursivo(actual.getRight(), nivel + 1);

        for (int i = 0; i < nivel; i++) {
            System.out.print("    ");
        }

        System.out.println(actual.getValue());
        printTreeRecursivo(actual.getLeft(), nivel + 1);
    }
}
