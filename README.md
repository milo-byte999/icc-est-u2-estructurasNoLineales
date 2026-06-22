# UNIVERSIDAD POLITÉCNICA SALESIANA

## Carrera: Computación

### Práctica — Implementación de Árboles Binarios en Java

**Estudiante:** Emilio Montaleza
**Grupo:** 1
**Fecha:** 22 de junio de 2026

---

# Descripción de la práctica

En esta práctica se desarrolló una implementación de **Árboles Binarios de Búsqueda (Binary Search Tree - BST)** utilizando el lenguaje **Java** y aplicando principios de **Programación Orientada a Objetos (POO)**.

Se construyeron dos estructuras principales:

* **IntTree:** árbol binario diseñado para almacenar únicamente números enteros.
* **BynariTree<T>:** árbol binario genérico que permite almacenar cualquier objeto que implemente la interfaz `Comparable`.

Además, se realizaron pruebas utilizando objetos personalizados mediante la clase **Persona**, permitiendo comprobar el funcionamiento de estructuras genéricas y comparaciones entre objetos.

---

# Estructura del proyecto

```plaintext
src/
│
├── App.java
│
├── models/
│   └── Persona.java
│
├── structures/
│   ├── node/
│   │     └── Node.java
│   │
│   └── trees/
│         ├── IntTree.java
│         └── BynariTree.java
```

---

# Explicación de las clases

## Clase Node

Representa cada nodo del árbol.

### Atributos

```java
private T value;
private Node<T> left;
private Node<T> right;
```

### Función

Cada nodo almacena:

* Valor principal.
* Referencia al hijo izquierdo.
* Referencia al hijo derecho.

---

## Clase IntTree

Clase especializada para almacenar valores enteros.

### Constructor

```java
public IntTree()
```

Inicializa el árbol vacío.

---

### Método add()

```java
public void add(int value)
```

Inserta un nodo siguiendo la regla:

* Valores menores → izquierda.
* Valores mayores o iguales → derecha.

Ejemplo:

```plaintext
Insertar:
50
10
30
```

Resultado:

```plaintext
    50
   /
  10
   \
    30
```

---

### Método addRecursivo()

```java
private Node<Integer> addRecursivo()
```

Inserta nodos mediante recursividad hasta encontrar una posición vacía.

---

### Método preOrden()

Recorrido:

```plaintext
Raíz → Izquierda → Derecha
```

Salida:

```plaintext
50
10
30
```

---

### Método inOrden()

Recorrido:

```plaintext
Izquierda → Raíz → Derecha
```

Salida ordenada:

```plaintext
10
30
50
```

---

### Método posOrden()

Recorrido:

```plaintext
Izquierda → Derecha → Raíz
```

Salida:

```plaintext
30
10
50
```

---

### Método getHeigh()

```java
getHeigh()
```

Calcula la altura máxima del árbol.

Ejemplo:

```plaintext
Altura = 3
```

---

### Método getPeso()

```java
getPeso()
```

Cuenta la cantidad total de nodos.

Ejemplo:

```plaintext
Peso = 3
```

---

# Clase BynariTree<T>

Versión genérica del árbol.

### Declaración

```java
public class BynariTree<T extends Comparable<T>>
```

Permite almacenar:

```java
Integer
String
Persona
```

---

### Método add()

Inserta cualquier objeto comparable.

Comparación:

```java
compareTo()
```

Si el resultado es:

* Mayor → izquierda
* Menor o igual → derecha

---

### Método addRecursivo()

Realiza la inserción utilizando recursividad.

---

### Métodos de recorrido

Se implementaron:

```plaintext
preOrden()
inOrden()
posOrden()
```

Con el mismo funcionamiento que `IntTree`.

---

### Método getHeigh()

Calcula la profundidad del árbol.

---

### Método getPeso()

Cuenta el total de nodos.

---

# Clase Persona

Se creó una clase personalizada para probar el árbol genérico.

## Declaración

```java
public class Persona implements Comparable<Persona>
```

---

### Atributos

```java
private String nombre;
private int edad;
```

---

### Constructor

```java
public Persona(
String nombre,
int edad)
```

Permite crear personas con nombre y edad.

Ejemplo:

```java
new Persona("Pablo",30)
```

---

### Método compareTo()

```java
@Override
public int compareTo(Persona otra)
```

Define el orden dentro del árbol.

Lógica:

1. Compara edades.
2. Si tienen la misma edad:

   * compara nombres.

Código aplicado:

```java
int compEdad =
Integer.compare(
this.edad,
otra.getEdad());

if(compEdad != 0)
return compEdad;

return this.nombre
.compareTo(
otra.getNombre());
```

Esto permite ordenar correctamente objetos dentro del árbol.

---

# Clase App

Clase principal del programa.

Métodos ejecutados:

```java
runIntTree()
runBynariTree()
```

Funciones realizadas:

* Crear árboles.
* Insertar datos.
* Ejecutar recorridos.
* Calcular altura.
* Calcular peso.

---

# Resultados obtenidos

Salida esperada:

```plaintext
50
/
10
\
30

Preorden:
50
10
30

Inorden:
10
30
50

Postorden:
30
10
50

Altura del arbol: 3
Peso del arbol: 3
```

---

## Árbol generado

![Árbol](/src/imagenes/arbol.png)

---

## Salida en consola

![Consola](/src/imagenes/salida.png)

---

# Conclusión

La práctica permitió comprender el funcionamiento interno de los árboles binarios y el uso de recursividad para recorrer e insertar elementos. También se aplicó programación genérica mediante `Comparable`, permitiendo reutilizar la misma estructura para diferentes tipos de objetos y fortaleciendo el manejo de estructuras dinámicas en Java.
