# UNIVERSIDAD POLITÉCNICA SALESIANA

## Carrera: Computación

### Práctica — Evaluación de estructuras de datos: Set y Map

**Estudiante:** Emilio Montaleza
**Grupo:** 1
**Fecha:** 2 de julio de 2026

---
# Método A — `filtrarYOrdenar`

## ¿Qué implementación de Set o Map usé?

Usé un `TreeSet<Persona1>`, y le pasé un `Comparator` (una función que le dice cómo comparar dos personas) directo en el constructor.

---

## ¿Por qué elegí esta implementación?

Porque necesitaba dos cosas al mismo tiempo: que no se repitieran personas y que quedaran ordenadas. El `TreeSet` hace las dos cosas solo, cada vez que agrego una persona la va acomodando en su lugar según el orden que yo le diga, así no tengo que ordenar aparte después de filtrar.

---

## ¿Cómo garantizo que no se repitan los datos?

El `TreeSet` no se fija en `equals()` para saber si algo ya existe, se fija en lo que le diga mi `Comparator`. Entonces en mi comparador puse que si dos personas tienen la misma edad y el mismo nombre (sin importar mayúsculas o minúsculas), se consideren iguales y devuelvan `0`:

![Comparator](/src/imagenes/comparator.png)

Así, si dos personas son "iguales" para el comparador, el `TreeSet` automáticamente descarta la segunda. Por eso en mi ejemplo los dos **"Juan-20"** terminan siendo solo uno.

---

## ¿Cómo queda el orden de los resultados?

Primero ordeno por edad, pero de mayor a menor (por eso comparo `p2` con `p1` y no al revés). Y si dos personas tienen la misma edad, ahí sí las desempato ordenando por nombre alfabéticamente.

---

## ¿Cómo funciona la lógica?

Recorro la lista de personas que me llega, y por cada una me fijo si su edad es mayor o igual al umbral que me pasan. Si cumple, la agrego al `TreeSet` (que ya se encarga de ordenar y evitar duplicados) y voy imprimiendo cada persona que agrego para ir viendo el proceso.

---

## Resultado

![Salida Método A](/src/imagenes/metodoA.png)

---

# Método B — `agruparPorEdad`

## ¿Qué implementación de Set o Map usé?

Un `Map<String, Set<String>>`, armado con un `TreeMap` por fuera y un `LinkedHashSet` por dentro para cada grupo.

---

## ¿Por qué elegí esta implementación?

Usé `TreeMap` porque ordena las llaves solo, sin que yo tenga que hacer nada extra, así el mapa final queda ordenado alfabéticamente ("ADULTO", "JOVEN", "MAYOR") tal como se pedía.

Usé `LinkedHashSet` para los nombres de cada grupo porque necesitaba que fuera un `Set` (para que no se repitan nombres) pero que además mantuviera el orden en que las personas aparecen en la lista original. Un `HashSet` normal no me aseguraba ningún orden, y un `TreeSet` me los hubiera ordenado alfabéticamente, que no era lo que pedían.

---

## ¿Cómo garantizo que no se repitan los datos?

Antes de agregar un nombre, recorro el grupo correspondiente y me fijo si ya existe un nombre igual (sin importar mayúsculas/minúsculas) usando `equalsIgnoreCase`. Solo si no lo encuentro, lo agrego.

También la unicidad la baso solo en el primer nombre, así que antes de comparar hago:

```java
p.getNombre().split(" ")[0]
```

para quedarme solo con la primera palabra. Por eso **"Juan Pérez"** y **"Juan Morales"** cuentan como el mismo nombre y solo se guarda **"Juan"** una vez.

---

## ¿Cómo queda el orden de los resultados?

Los grupos ("ADULTO", "JOVEN", "MAYOR") salen ordenados alfabéticamente porque así los ordena el `TreeMap` solo.

Y dentro de cada grupo, los nombres quedan en el mismo orden en que aparecen en la lista original, porque uso `LinkedHashSet` y recorro la lista tal cual me la pasan, sin alterar el orden.

---

## ¿Cómo funciona la lógica?

Primero armo el mapa con las tres categorías ya creadas y vacías, para que siempre estén las tres aunque algún grupo quede sin nadie. Después recorro la lista de personas una por una:

1. Reviso su edad y decido a qué grupo pertenece (menor a 30 es **"JOVEN"**, entre 30 y 59 es **"ADULTO"**, 60 o más es **"MAYOR"**).
2. Saco su primer nombre.
3. Reviso si ese nombre ya está en el grupo (ignorando mayúsculas).
4. Si no está, lo agrego y lo imprimo; si ya está, imprimo que se repite y lo salto.

Al final devuelvo el mapa completo con los tres grupos y los nombres únicos de cada uno.

---

## Resultado

![Salida Método B](/src/imagenes/metodoB.png)
