package structures.trees;

import structures.node.Node;

public class Ejercicio2 {

 public void invertTree(Node<Integer> root){
    System.out.println("Arbol Original");
    printTree(root);

    invertRecursivo(root);

    System.out.println("Arbol Invertido");
    printTree(root);
    
    }

    public void invertRecursivo(Node<Integer> actual){
        if(actual == null){
            return;
        }

        Node<Integer> temp = actual.getLeft();
        actual.setLeft(actual.getRight());
        actual.setRight(temp);

        invertRecursivo(actual.getLeft());
        invertRecursivo(actual.getRight());
    }

    public void printTree(Node<Integer> root){
        System.out.println("Imprimiendo árbol:");
        printTreeRecursivo(root, 0);
    }

    private void printTreeRecursivo(Node<Integer> actual, int nivel){

        if(actual == null)
            return;

        printTreeRecursivo(actual.getRight(), nivel + 1);

        for(int i = 0; i < nivel; i++){
            System.out.print("    ");
        }

        System.out.println(actual.getValue());
        printTreeRecursivo(actual.getLeft(), nivel + 1);
    }

}

