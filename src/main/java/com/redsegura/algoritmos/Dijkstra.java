
package com.redsegura.algoritmos;

import com.redsegura.grafo.MixedGraph;
import com.redsegura.modelo.Vertex;
import com.redsegura.modelo.Edge;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

public class Dijkstra {

    private MixedGraph grafo;

    public Dijkstra(MixedGraph grafo) {
        if (grafo == null) {
            throw new IllegalArgumentException(
                "El grafo no puede ser nulo."
            );
        }

        this.grafo = grafo;
    }

    // Algoritmo de Dijkstra
    public Resultado encontrarRuta(
            String origen, String destino) {

        if (grafo.buscarVertice(origen) == null) {
            throw new IllegalArgumentException(
                "No existe el vertice de origen: " + origen
            );
        }

        if (grafo.buscarVertice(destino) == null) {
            throw new IllegalArgumentException(
                "No existe el vertice de destino: " + destino
            );
        }

        Map<String, Double> distancias = new HashMap<>();
        Map<String, String> anteriores = new HashMap<>();

        // Inicializar las distancias
        for (Vertex vertex : grafo.getVertices()) {
            distancias.put(vertex.getId(), Double.POSITIVE_INFINITY);
        }

        distancias.put(origen, 0.0);

        // Cola de prioridad
        PriorityQueue<String> cola = new PriorityQueue<>(
            Comparator.comparingDouble(distancias::get)
        );

        cola.offer(origen);

        while (!cola.isEmpty()) {

            String actual = cola.poll();

            if (actual.equals(destino)) {
                break;
            }

            double distanciaActual = distancias.get(actual);

            // Revisar todas las conexiones
            for (Edge arista : grafo.getAristas()) {

                String idOrigen = arista.getOrigen().getId();
                String idDestino = arista.getDestino().getId();

                String vecino = null;

                // Conexion dirigida: solo se avanza desde el origen
                if (idOrigen.equals(actual)) {
                    vecino = idDestino;
                }

                // Conexion no dirigida: permite regresar
                else if (!arista.isDirigida()
                        && idDestino.equals(actual)) {
                    vecino = idOrigen;
                }

                if (vecino != null) {

                    double nuevaDistancia =
                            distanciaActual + arista.getPeso();

                    if (nuevaDistancia < distancias.get(vecino)) {

                        distancias.put(vecino, nuevaDistancia);
                        anteriores.put(vecino, actual);

                        // Actualizar la cola de prioridad
                        cola.remove(vecino);
                        cola.offer(vecino);
                    }
                }
            }
        }

        // Verificar si existe una ruta
        if (distancias.get(destino) == Double.POSITIVE_INFINITY) {
            return new Resultado(
                Collections.emptyList(),
                Double.POSITIVE_INFINITY,
                false
            );
        }

        // Reconstruir la ruta
        List<Vertex> ruta = new ArrayList<>();

        String actual = destino;

        while (actual != null) {
            ruta.add(grafo.buscarVertice(actual));
            actual = anteriores.get(actual);
        }

        Collections.reverse(ruta);

        return new Resultado(
            ruta,
            distancias.get(destino),
            true
        );
    }

    // Clase para almacenar el resultado
    public static class Resultado {

        private List<Vertex> ruta;
        private double distancia;
        private boolean alcanzable;

        public Resultado(
                List<Vertex> ruta,
                double distancia,
                boolean alcanzable) {

            this.ruta = ruta;
            this.distancia = distancia;
            this.alcanzable = alcanzable;
        }

        public List<Vertex> getRuta() {
            return ruta;
        }

        public double getDistancia() {
            return distancia;
        }

        public boolean isAlcanzable() {
            return alcanzable;
        }

        public void mostrarResultado() {

            System.out.println("\n===== RESULTADO DIJKSTRA =====");

            if (!alcanzable) {
                System.out.println(
                    "No existe una ruta entre las ubicaciones."
                );
                return;
            }

            System.out.print("Ruta encontrada: ");

            for (int i = 0; i < ruta.size(); i++) {

                System.out.print(ruta.get(i).getId());

                if (i < ruta.size() - 1) {
                    System.out.print(" -> ");
                }
            }

            System.out.println();
            System.out.println(
                "Costo total: " + distancia + " minutos"
            );
        }
    }
}
