package structures.trees;
import structures.node.Node;

public class BynariTree <T extends Comparable<T>>{

    public BynariTree() {
        this.root = null;
    }

    private Node<T> root;

    public Node<T> getRoot() {
        return root;
    }

    public void setRoot(Node<T> root) {
        this.root = root;
    }

    public void setRoot (T value){
        Node<T> node = new Node<T>(value);
        this.root = node;
    }

    public void add (T value){
        Node<T> node = new Node<T>(value);
        root = addRecursivo (root, node);

    }

    public Node<T> addRecursivo(
        Node<T> actual, 
        Node<T> nodeInsertar) {

        if(actual == null) 
            return nodeInsertar;

        //if(actual.getValue() > nodeInsertar.getValue()){
        if(actual.getValue().compareTo(nodeInsertar.getValue())>0){
            actual.setLeft(addRecursivo(actual.getLeft(), nodeInsertar));
        } else {
            actual.setRight(addRecursivo(actual.getRight(), nodeInsertar));
                    
        }

            
    return actual;
        
    }

    public void preOrden(){
        preOrdenRecursivo(root);
    }

    private void preOrdenRecursivo(Node <T> actual){
        if (actual == null)
            return;

        System.out.println(actual);
        preOrdenRecursivo(actual.getLeft());
        preOrdenRecursivo(actual.getRight());
    }

    public void posOrden(){
        posOrdenRecursivo(root);
    }

    public void posOrdenRecursivo(Node <T> actual){
        if (actual == null)
            return;
        
        posOrdenRecursivo(actual.getLeft());
        posOrdenRecursivo(actual.getRight());
        System.out.println(actual);
    }

    public void inOrden(){
        inOrdenRecursivo(root);
    }

    public void inOrdenRecursivo(Node<T> actual){
    if (actual == null)
        return;

        inOrdenRecursivo(actual.getLeft());  
        System.out.println(actual);          
        inOrdenRecursivo(actual.getRight()); 
    }

    public int getHeigh(){
        return getHeighRecursivo(root);
    }

    private int getHeighRecursivo(Node<T> actual) {

        if(actual == null)
            return 0;

        int heigthLeft = getHeighRecursivo(actual.getLeft());
        int heightRight = getHeighRecursivo(actual.getRight());

        int masAlto = Math.max(heigthLeft, heightRight);
        return masAlto+1;
    }

    /*private int getHeighRecursivo(Node<Interger> actual){
        return actual != null ? Math.max(
            getHeighRecursivo(actual.getLeft),
            getHeighRecursivo(actual.getRight))
            +1 : null;
    }*/

    public int getPeso(){
        return getPesoRecursivo(root);
    }

    private int getPesoRecursivo(Node<T> actual){
        if(actual==null)
            return 0;
        int pesoLeft = getPesoRecursivo(actual.getLeft());
        int pesoRight = getPesoRecursivo(actual.getRight());
        return pesoLeft + pesoRight + 1;
    }

    
}
