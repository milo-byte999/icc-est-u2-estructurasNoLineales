# Universidad Politécnica Salesiana  
**Materia:** Estructuras de Datos  
**Grupo:** Grupo 1  
**Nombre:** Emilio Montaleza  
**Fecha:** 23/06/2026  

---

# Descripción

En esta práctica se trabajó con la implementación de árboles binarios utilizando el lenguaje de programación Java. Se aplicaron conceptos como la creación de nodos, inserción de datos, recorridos del árbol y diferentes operaciones que permiten manipular y obtener información de la estructura.

---

# Ejercicio 1: Insertar un Árbol Binario de Búsqueda

En este ejercicio se creó un árbol binario de números enteros utilizando un arreglo de valores. Cada número del arreglo fue insertado dentro del árbol mediante un ciclo que utilizó el método `add()`:

```java
BynariTree<Integer> arbol = new BynariTree<>();

for (int numero : numeros) {
    arbol.add(numero);
}
```

Después de insertar los elementos se realizó un recorrido inorden para mostrar los valores almacenados:

```java
arbol.inOrden();
```

También se implementó una impresión visual del árbol utilizando un método recursivo que recibe la raíz del árbol:

```java
public void printTree(Node<Integer> root) {
    printTreeRecursivo(root, 0);
}
```

El método recursivo recorre primero el lado derecho del árbol, imprime el valor del nodo actual y posteriormente recorre el lado izquierdo, permitiendo observar la estructura del árbol de una manera más organizada:

```java
printTreeRecursivo(actual.getRight(), nivel + 1);

System.out.println(actual.getValue());

printTreeRecursivo(actual.getLeft(), nivel + 1);
```

## Resultado del Ejercicio 1

![Ejercicio 1](ruta_de_la_imagen)
---

# Ejercicio 2: Invertir un Árbol Binario

En este ejercicio se realizó la inversión del árbol binario, intercambiando los nodos hijos izquierdos y derechos de cada nodo.

Para esto se creó el método recursivo:

```java
public void invertRecursivo(Node<Integer> actual)
```

Dentro de este método se utilizó una variable temporal para almacenar un hijo del nodo y posteriormente realizar el intercambio de posiciones:

```java
Node<Integer> temp = actual.getLeft();
actual.setLeft(actual.getRight());
actual.setRight(temp);
```

Después del intercambio, el método vuelve a ejecutarse sobre los nuevos hijos para continuar el proceso hasta recorrer completamente el árbol:

```java
invertRecursivo(actual.getLeft());
invertRecursivo(actual.getRight());
```

Finalmente se imprimió el árbol antes y después de la inversión para poder comparar los cambios realizados.

## Resultado del Ejercicio 2

---

# Ejercicio 3: Listar Niveles en Listas Enlazadas

En este ejercicio se realizó un recorrido por niveles, el cual permite visitar primero la raíz y luego los nodos de cada nivel del árbol.

Para lograr esto se utilizó una cola (`Queue`), donde inicialmente se agregó el nodo raíz:

```java
Queue<Node> cola = new LinkedList<>();
cola.add(root);
```

Mientras la cola tenga elementos, se obtiene cada nodo y se agregan sus hijos para continuar con el recorrido de los siguientes niveles:

```java
Node actual = cola.poll();

if(actual.getLeft() != null){
    cola.add(actual.getLeft());
}

if(actual.getRight() != null){
    cola.add(actual.getRight());
}
```

Los nodos encontrados en cada nivel se almacenaron en listas y posteriormente se imprimieron mostrando el número de nivel y los valores correspondientes.

## Resultado del Ejercicio 3

---

# Ejercicio 4: Calcular la Profundidad Máxima

En este ejercicio se calculó la profundidad máxima del árbol, es decir, la cantidad de niveles que existen desde la raíz hasta el nodo más profundo.

Para ello se implementó un método recursivo que primero verifica si el nodo recibido está vacío:

```java
if(root == null){
    return 0;
}
```

Luego se obtiene la profundidad del subárbol izquierdo y del subárbol derecho:

```java
int profundidadIzquierda = maxDepth(root.getLeft());
int profundidadDerecha = maxDepth(root.getRight());
```

Finalmente se compara cuál de las dos profundidades es mayor y se suma uno para considerar el nivel del nodo actual:

```java
return Math.max(profundidadIzquierda, profundidadDerecha) + 1;
```

El resultado obtenido se mostró mediante el método encargado de imprimir la profundidad:

```java
printDepth(root);
```

## Resultado del Ejercicio

---

# Conclusión

Durante esta práctica se reforzaron los conocimientos sobre árboles binarios mediante la implementación de diferentes operaciones en Java. Se trabajó con la creación de árboles, la inserción de elementos, la impresión visual de su estructura, la inversión de nodos, el recorrido por niveles y el cálculo de la profundidad máxima.

La práctica permitió comprender mejor el funcionamiento de las estructuras jerárquicas y cómo la recursividad y otras estructuras auxiliares, como las colas, facilitan el recorrido y manipulación de los árboles binarios.
