package structures.trees;

import structures.node.Node;

public class Ejercicio4 {

    public int maxDepth(Node root){

        if(root == null){
            return 0;
        }

        int profundidadIzquierda = maxDepth(root.getLeft());
        int profundidadDerecha = maxDepth(root.getRight());
        return Math.max(profundidadIzquierda, profundidadDerecha) + 1;
    }

    public void printDepth(Node<Integer> root){

        int profundidad = maxDepth(root);
        System.out.println("----- Ejercicio 4 -----");
        System.out.println("Profundidad máxima: " + profundidad);
    }
}