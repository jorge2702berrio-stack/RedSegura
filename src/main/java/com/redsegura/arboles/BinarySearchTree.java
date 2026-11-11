
package com.redsegura.arboles;

import com.redsegura.modelo.Vertex;

public class BinarySearchTree {

    private Node raiz;
    private int pasosBusqueda;

    // Nodo interno del arbol
    private static class Node {

        Vertex dato;
        Node izquierdo;
        Node derecho;

        public Node(Vertex dato) {
            this.dato = dato;
            this.izquierdo = null;
            this.derecho = null;
        }
    }

    // Insertar un elemento
    public void insert(Vertex vertex) {

        if (vertex == null) {
            throw new IllegalArgumentException(
                "El vertice no puede ser nulo."
            );
        }

        raiz = insertarRecursivo(raiz, vertex);
    }

    private Node insertarRecursivo(Node actual, Vertex vertex) {

        if (actual == null) {
            return new Node(vertex);
        }

        int comparacion = vertex.getNombre()
                .compareToIgnoreCase(actual.dato.getNombre());

        if (comparacion < 0) {
            actual.izquierdo =
                    insertarRecursivo(actual.izquierdo, vertex);

        } else if (comparacion > 0) {
            actual.derecho =
                    insertarRecursivo(actual.derecho, vertex);

        } else {
            throw new IllegalArgumentException(
                "Ya existe un elemento con ese nombre."
            );
        }

        return actual;
    }

    // Buscar un elemento por nombre
    public Vertex search(String nombre) {

        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException(
                "El nombre no puede estar vacio."
            );
        }

        pasosBusqueda = 0;

        Node actual = raiz;

        while (actual != null) {

            pasosBusqueda++;

            int comparacion = nombre.compareToIgnoreCase(
                    actual.dato.getNombre()
            );

            if (comparacion == 0) {
                return actual.dato;
            }

            if (comparacion < 0) {
                actual = actual.izquierdo;
            } else {
                actual = actual.derecho;
            }
        }

        return null;
    }

    // Obtener los pasos de la ultima busqueda
    public int getPasosBusqueda() {
        return pasosBusqueda;
    }

    // Eliminar un elemento
    public void delete(String nombre) {

        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException(
                "El nombre no puede estar vacio."
            );
        }

        raiz = eliminarRecursivo(raiz, nombre);
    }

    private Node eliminarRecursivo(Node actual, String nombre) {

        if (actual == null) {
            return null;
        }

        int comparacion = nombre.compareToIgnoreCase(
                actual.dato.getNombre()
        );

        if (comparacion < 0) {

            actual.izquierdo =
                    eliminarRecursivo(actual.izquierdo, nombre);

        } else if (comparacion > 0) {

            actual.derecho =
                    eliminarRecursivo(actual.derecho, nombre);

        } else {

            // Caso 1: sin hijo izquierdo
            if (actual.izquierdo == null) {
                return actual.derecho;
            }

            // Caso 2: sin hijo derecho
            if (actual.derecho == null) {
                return actual.izquierdo;
            }

            // Caso 3: tiene dos hijos
            Node sucesor = encontrarMinimo(actual.derecho);

            actual.dato = sucesor.dato;

            actual.derecho = eliminarRecursivo(
                    actual.derecho,
                    sucesor.dato.getNombre()
            );
        }

        return actual;
    }

    // Encontrar el menor elemento
    private Node encontrarMinimo(Node actual) {

        while (actual.izquierdo != null) {
            actual = actual.izquierdo;
        }

        return actual;
    }

    // Recorrido InOrden
    public void inOrder() {

        System.out.println("\n===== RECORRIDO INORDEN =====");

        inOrderRecursivo(raiz);
        System.out.println();
    }

    private void inOrderRecursivo(Node actual) {

        if (actual != null) {

            inOrderRecursivo(actual.izquierdo);

            System.out.println(actual.dato);

            inOrderRecursivo(actual.derecho);
        }
    }

    // Recorrido PreOrden
    public void preOrder() {

        System.out.println("\n===== RECORRIDO PREORDEN =====");

        preOrderRecursivo(raiz);
        System.out.println();
    }

    private void preOrderRecursivo(Node actual) {

        if (actual != null) {

            System.out.println(actual.dato);

            preOrderRecursivo(actual.izquierdo);

            preOrderRecursivo(actual.derecho);
        }
    }

    // Recorrido PostOrden
    public void postOrder() {

        System.out.println("\n===== RECORRIDO POSTORDEN =====");

        postOrderRecursivo(raiz);
        System.out.println();
    }

    private void postOrderRecursivo(Node actual) {

        if (actual != null) {

            postOrderRecursivo(actual.izquierdo);

            postOrderRecursivo(actual.derecho);

            System.out.println(actual.dato);
        }
    }

    // Calcular altura del arbol
    public int height() {
        return calcularAltura(raiz);
    }

    private int calcularAltura(Node actual) {

        if (actual == null) {
            return 0;
        }

        int alturaIzquierda =
                calcularAltura(actual.izquierdo);

        int alturaDerecha =
                calcularAltura(actual.derecho);

        return 1 + Math.max(
                alturaIzquierda,
                alturaDerecha
        );
    }
}
