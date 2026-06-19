import models.Persona;
import structures.node.Node;
import structures.trees.IntTree;
import structures.trees.BynariTree;

public class App {
    public static void main(String[] args) throws Exception {
        runIntTree();
        runBynariTree();
    }

    private static void runBynariTree() {
        BynariTree<String> arbolStrings = new BynariTree<>();
        BynariTree<Persona> arbolPersonas = new BynariTree<>();

        arbolPersonas.add(new Persona("Pablo", 30));
        arbolPersonas.add(new Persona("Ana", 25));
        arbolPersonas.add(new Persona("Luis", 35));
        arbolPersonas.add(new Persona("Maria", 28));
    }

    public static void runIntTree() {

        IntTree arbolNumero = new IntTree();
        // Node <Integer> nodo1 = new Node<>(50);
        // Node <Integer> nodo2 = new Node<>(10);
        // Node <Integer> nodo3 = new Node<>(30);

        // arbolNumero.setRoot(nodo1);
        // nodo1.setRight(nodo2);
        // nodo2.setLeft(nodo3);

        // System.out.println(arbolNumero.getRoot());
        // System.out.println(arbolNumero.getRoot().getRight());
        // System.out.println(arbolNumero.getRoot().getLeft().getRight());

        arbolNumero.add(50);
        arbolNumero.add(10);
        arbolNumero.add(30);

        System.out.println("    " + arbolNumero.getRoot().getValue());
        System.out.println("   /");
        System.out.println("  " + arbolNumero.getRoot().getLeft().getValue());
        System.out.println("   \\");
        System.out.println("    " + arbolNumero.getRoot().getLeft().getRight().getValue());

        System.out.println("Preorden:");
        arbolNumero.preOrden();

        System.out.println("Inorden:");
        arbolNumero.inOrden();

        System.out.println("Postorden:");
        arbolNumero.posOrden();

        System.out.println("Altura del arbol: " + arbolNumero.getHeigh());
        System.out.println("Peso del arbol: " + arbolNumero.getPeso());

    }
}