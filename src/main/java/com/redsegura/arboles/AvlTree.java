
package com.redsegura.arboles;

import com.redsegura.modelo.Vertex;

public class AvlTree {

    private Node raiz;
    private int pasosBusqueda;

    // Nodo del arbol AVL
    private static class Node {

        Vertex dato;
        Node izquierdo;
        Node derecho;
        int altura;

        public Node(Vertex dato) {
            this.dato = dato;
            this.altura = 1;
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

        actualizarAltura(actual);

        return equilibrar(actual);
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

            // Caso 1: no tiene hijo izquierdo
            if (actual.izquierdo == null) {
                return actual.derecho;
            }

            // Caso 2: no tiene hijo derecho
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

        actualizarAltura(actual);

        return equilibrar(actual);
    }

    // Encontrar el menor elemento
    private Node encontrarMinimo(Node actual) {

        while (actual.izquierdo != null) {
            actual = actual.izquierdo;
        }

        return actual;
    }

    // Obtener altura de un nodo
    private int obtenerAltura(Node nodo) {
        return nodo == null ? 0 : nodo.altura;
    }

    // Actualizar altura
    private void actualizarAltura(Node nodo) {

        nodo.altura = 1 + Math.max(
                obtenerAltura(nodo.izquierdo),
                obtenerAltura(nodo.derecho)
        );
    }

    // Calcular factor de equilibrio
    private int factorEquilibrio(Node nodo) {

        if (nodo == null) {
            return 0;
        }

        return obtenerAltura(nodo.izquierdo)
                - obtenerAltura(nodo.derecho);
    }

    // Equilibrar el arbol
    private Node equilibrar(Node nodo) {

        int balance = factorEquilibrio(nodo);

        // Caso LL
        if (balance > 1 &&
                factorEquilibrio(nodo.izquierdo) >= 0) {

            return rotacionDerecha(nodo);
        }

        // Caso RR
        if (balance < -1 &&
                factorEquilibrio(nodo.derecho) <= 0) {

            return rotacionIzquierda(nodo);
        }

        // Caso LR
        if (balance > 1 &&
                factorEquilibrio(nodo.izquierdo) < 0) {

            nodo.izquierdo =
                    rotacionIzquierda(nodo.izquierdo);

            return rotacionDerecha(nodo);
        }

        // Caso RL
        if (balance < -1 &&
                factorEquilibrio(nodo.derecho) > 0) {

            nodo.derecho =
                    rotacionDerecha(nodo.derecho);

            return rotacionIzquierda(nodo);
        }

        return nodo;
    }

    // Rotacion simple a la derecha (LL)
    private Node rotacionDerecha(Node y) {

        Node x = y.izquierdo;
        Node auxiliar = x.derecho;

        x.derecho = y;
        y.izquierdo = auxiliar;

        actualizarAltura(y);
        actualizarAltura(x);

        return x;
    }

    // Rotacion simple a la izquierda (RR)
    private Node rotacionIzquierda(Node x) {

        Node y = x.derecho;
        Node auxiliar = y.izquierdo;

        y.izquierdo = x;
        x.derecho = auxiliar;

        actualizarAltura(x);
        actualizarAltura(y);

        return y;
    }

    // Recorrido InOrden
    public void inOrder() {

        System.out.println("\n===== AVL INORDEN =====");

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

        System.out.println("\n===== AVL PREORDEN =====");

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

        System.out.println("\n===== AVL POSTORDEN =====");

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

    // Obtener altura total del arbol
    public int height() {
        return obtenerAltura(raiz);
    }

    // Mostrar el factor de equilibrio de la raiz
    public int getRootBalanceFactor() {
        return factorEquilibrio(raiz);
    }
}
