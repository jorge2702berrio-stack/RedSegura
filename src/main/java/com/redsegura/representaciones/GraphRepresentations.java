
package com.redsegura.representaciones;

import com.redsegura.grafo.MixedGraph;
import com.redsegura.modelo.Vertex;
import com.redsegura.modelo.Edge;

import java.util.List;

public class GraphRepresentations {

    private MixedGraph grafo;

    public GraphRepresentations(MixedGraph grafo) {
        if (grafo == null) {
            throw new IllegalArgumentException(
                "El grafo no puede ser nulo."
            );
        }

        this.grafo = grafo;
    }

    // 1. Lista de adyacencia
    public void mostrarListaAdyacencia() {

        System.out.println("\n===== LISTA DE ADYACENCIA =====");

        for (Vertex vertex : grafo.getVertices()) {

            System.out.print(vertex.getId() + " -> ");

            List<Vertex> vecinos =
                    grafo.obtenerVecinos(vertex.getId());

            if (vecinos.isEmpty()) {
                System.out.println("Sin conexiones");
            } else {
                for (int i = 0; i < vecinos.size(); i++) {
                    System.out.print(vecinos.get(i).getId());

                    if (i < vecinos.size() - 1) {
                        System.out.print(", ");
                    }
                }
                System.out.println();
            }
        }
    }

    // 2. Matriz de adyacencia ponderada
    public void mostrarMatrizAdyacencia() {

        System.out.println("\n===== MATRIZ DE ADYACENCIA =====");

        List<Vertex> vertices = grafo.getVertices();
        List<Edge> aristas = grafo.getAristas();

        int n = vertices.size();
        double[][] matriz = new double[n][n];

        for (Edge arista : aristas) {

            int origen = buscarIndice(
                    vertices, arista.getOrigen().getId()
            );

            int destino = buscarIndice(
                    vertices, arista.getDestino().getId()
            );

            matriz[origen][destino] = arista.getPeso();

            if (!arista.isDirigida()) {
                matriz[destino][origen] = arista.getPeso();
            }
        }

        System.out.print("     ");
        for (Vertex vertex : vertices) {
            System.out.printf("%8s", vertex.getId());
        }
        System.out.println();

        for (int i = 0; i < n; i++) {
            System.out.printf("%5s", vertices.get(i).getId());

            for (int j = 0; j < n; j++) {
                if (matriz[i][j] == 0) {
                    System.out.printf("%8s", "-");
                } else {
                    System.out.printf("%8.0f", matriz[i][j]);
                }
            }

            System.out.println();
        }
    }

    // 3. Matriz de incidencia
    public void mostrarMatrizIncidencia() {

        System.out.println("\n===== MATRIZ DE INCIDENCIA =====");

        List<Vertex> vertices = grafo.getVertices();
        List<Edge> aristas = grafo.getAristas();

        int filas = vertices.size();
        int columnas = aristas.size();

        int[][] matriz = new int[filas][columnas];

        for (int j = 0; j < columnas; j++) {

            Edge arista = aristas.get(j);

            int origen = buscarIndice(
                    vertices, arista.getOrigen().getId()
            );

            int destino = buscarIndice(
                    vertices, arista.getDestino().getId()
            );

            if (arista.isDirigida()) {
                matriz[origen][j] = -1;
                matriz[destino][j] = 1;
            } else {
                matriz[origen][j] = 1;
                matriz[destino][j] = 1;
            }
        }

        System.out.print("     ");
        for (Edge arista : aristas) {
            System.out.printf("%6s", arista.getId());
        }
        System.out.println();

        for (int i = 0; i < filas; i++) {

            System.out.printf("%5s", vertices.get(i).getId());

            for (int j = 0; j < columnas; j++) {
                System.out.printf("%6d", matriz[i][j]);
            }

            System.out.println();
        }
    }

    // Busca la posición de un vértice
    private int buscarIndice(
            List<Vertex> vertices, String id) {

        for (int i = 0; i < vertices.size(); i++) {
            if (vertices.get(i).getId().equals(id)) {
                return i;
            }
        }

        return -1;
    }
}
