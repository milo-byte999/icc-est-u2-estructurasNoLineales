import collections.maps.Maps;
import collections.sets.Sets;
import eva.Persona1;
import eva.PersonaController;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import models.Contacto;
import models.Persona;
import structures.grafos.Graph;
import structures.node.Node;
import structures.trees.BynariTree;
import structures.trees.Ejercicio1;
import structures.trees.Ejercicio2;
import structures.trees.Ejercicio3;
import structures.trees.Ejercicio4;
import structures.trees.IntTree;

public class App {
    public static void main(String[] args) throws Exception {
        runIntTree();
        runBynariTree();
        runEjercicios();

        runSets();
        runMaps();
        runPersonaController();
        runGraph();

    }

private static void runGraph() {

    Graph<String> g = new Graph<>();

    g.add("A");
    g.add("B");
    g.add("C");
    g.add("D");
    g.add("E");
    g.add("J");
    g.add("K");

    g.addEdge("A", "D");
    g.addEdge("D", "J");
    g.addEdge("E", "J");

    g.addEdgeUni("A", "C");
    g.addEdgeUni("B", "C");
    g.addEdgeUni("C", "D");
    g.addEdgeUni("C", "E");
    g.addEdgeUni("E", "K");
    g.addEdgeUni("K", "A");

    System.out.println("----- GRAFO ORIGINAL -----");
    g.printGraph();

    g.removeEdge("E", "J");
    g.removeEdge("A", "B"); 
    g.addEdgeUni("A", "B"); 

    System.out.println("\n----- GRAFO MODIFICADO -----");
    g.printGraph();

    System.out.println("\nDirecciones: " + g.totalDirecciones());
    System.out.println("Conexiones: " + g.totalConexiones());

    g.remove("K");

    System.out.println("\n----- Sin K -----");
    g.printGraph();

    System.out.println("\nTotal direcciones: " + g.totalDirecciones());
    System.out.println("Total conexiones: " + g.totalConexiones());
}

    private static void runPersonaController() {
        PersonaController controller = new PersonaController();

        List<Persona1> personas = new ArrayList<>();

        personas.add(new Persona1("Juan", 20));
        personas.add(new Persona1("Ana", 18));
        personas.add(new Persona1("Luis", 30));
        personas.add(new Persona1("Carlos", 17));
        personas.add(new Persona1("Pedro", 25));
        personas.add(new Persona1("Juan", 20));

        controller.filtrarYOrdenar(personas, 18);
        controller.agruparPorEdad(personas);
    }

    private static void runMaps() {
        Maps maps = new Maps();
        Map<String, Integer> mapa = maps.construirHashMap();

    }

    private static void runSets() {
        Sets sets = new Sets();

        // Implementacion -> HashSet hashcode

        System.out.println("\n-------------HashCode-----------");
        Set<String> hashSet = sets.construirHashSet();
        System.out.println(hashSet);
        System.out.println("Size = " + hashSet.size());
        System.out.println(hashSet.contains("F"));

        ArrayList<String> lis = new ArrayList<>();
        lis.add("S");
        System.out.println(lis);

        System.out.println("\n-------------LinkedHashCode-----------");
        Set<String> lSet = sets.LinkedHashSet();
        System.out.println(lSet);
        System.out.println("Size = " + lSet.size());
        System.out.println(lSet.contains("F"));

        System.out.println("\n-------------TreeSet-----------");
        Set<String> tSet = sets.TreeSet();
        System.out.println(tSet);
        System.out.println("Size = " + tSet.size());
        System.out.println(tSet.contains("F"));

        System.out.println("\n-------------HashSet Contacto-----------");
        Set<Contacto> hCSet = sets.construirHContacto();
        System.out.println(hCSet);
        System.out.println("Size = " + hCSet.size());

        System.out.println("\n-------------TreeSet Contacto-----------");
        Set<Contacto> thCSet = sets.construirTreeSetContacto();
        System.out.println(thCSet);
        System.out.println("Size = " + thCSet.size());
    }

    private static void runEjercicios() {
        Ejercicio1 ejercicio1 = new Ejercicio1();
        int[] numeros = new int[] { 5, 3, 7, 2, 4, 6, 8 };
        ejercicio1.insert(numeros);

        Ejercicio2 ejercicio2 = new Ejercicio2();
        int[] numeros2 = new int[] { 5, 3, 7, 2, 4, 6, 8 };
        BynariTree<Integer> arbol = new BynariTree<>();
        for (int numero : numeros2) {
            arbol.add(numero);
        }
        Node<Integer> root = arbol.getRoot();
        ejercicio2.invertTree(root);

        Ejercicio3 ejercicio3 = new Ejercicio3();
        BynariTree<Integer> arbol2 = new BynariTree<>();
        for (int numero : numeros2) {
            arbol2.add(numero);
        }
        ejercicio3.ListLevels(arbol2.getRoot());

        Ejercicio4 ejercicio4 = new Ejercicio4();
        BynariTree<Integer> arbol3 = new BynariTree<>();
        for (int numero : numeros2) {
            arbol3.add(numero);
        }
        ejercicio4.printDepth(arbol3.getRoot());
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