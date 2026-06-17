package structures.trees;
import structures.node.Node;

//Clase de arbol solo de enteros
public class IntTree {

    private Node<Integer> root;

    //Constructor
    public IntTree() {
        this.root = null;
    }

    public Node<Integer> getRoot() {
        return root;
    }

    public void setRoot(Node<Integer> root) {
        this.root = root;
    }

    public void setRoot(Integer value) {
        Node<Integer> node = new Node<Integer>(value);
        this.root = node;
    }

    public void add(int value){
        Node<Integer> node = new Node<Integer>(value);
        root = addRecursive(root, node);
        }

    private Node<Integer> addRecursive(
            Node<Integer> actual,
            Node<Integer> nodeInsertar){
                if (actual == null) 
                    return nodeInsertar;
                if(actual.getValue() > nodeInsertar.getValue())
                    actual.setLeft(addRecursive(actual.getLeft(), nodeInsertar));
                else if (actual.getValue() < nodeInsertar.getValue())
                    actual.setRight(addRecursive(actual.getRight(), nodeInsertar));
                return actual;
            }
    }

