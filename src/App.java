import java.util.LinkedList;

import structures.node.Node;
import structures.trees.IntTree;

public class App {
    public static void main(String[] args) throws Exception {
       runIntTree();
    }

    public static void runIntTree() {
        IntTree arbolNumero = new IntTree();
        Node<Integer> nodo1 = new Node<>(50);
        Node<Integer> nodo2 = new Node<>(10);
        Node<Integer> nodo3 = new Node<>(30);
        
        arbolNumero.setRoot(nodo1);
        nodo1.setRight(nodo2);
        nodo2.setLeft(nodo3);

        System.out.println(arbolNumero.getRoot());
        System.out.println(arbolNumero.getRoot().getRight());
        System.out.println(arbolNumero.getRoot().getLeft());
    }
}
