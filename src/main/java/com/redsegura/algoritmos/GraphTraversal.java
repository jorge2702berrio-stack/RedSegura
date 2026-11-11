
package com.redsegura.algoritmos;

import com.redsegura.grafo.MixedGraph;
import com.redsegura.modelo.Vertex;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Queue;
import java.util.Set;

public class GraphTraversal {

    private MixedGraph grafo;

    public GraphTraversal(MixedGraph grafo) {
        if (grafo == null) {
            throw new IllegalArgumentException(
                "El grafo no puede ser nulo."
            );
        }

        this.grafo = grafo;
    }

    // BFS: recorrido en anchura
    public List<Vertex> bfs(String inicio) {

        validarInicio(inicio);

        List<Vertex> recorrido = new ArrayList<>();
        Queue<Vertex> cola = new ArrayDeque<>();
        Set<String> visitados = new LinkedHashSet<>();

        Vertex primero = grafo.buscarVertice(inicio);

        cola.offer(primero);
        visitados.add(inicio);

        while (!cola.isEmpty()) {

            Vertex actual = cola.poll();
            recorrido.add(actual);

            for (Vertex vecino :
                    grafo.obtenerVecinos(actual.getId())) {

                if (!visitados.contains(vecino.getId())) {

                    visitados.add(vecino.getId());
                    cola.offer(vecino);
                }
            }
        }

        return recorrido;
    }

    // DFS: recorrido en profundidad recursivo
    public List<Vertex> dfsRecursivo(String inicio) {

        validarInicio(inicio);

        List<Vertex> recorrido = new ArrayList<>();
        Set<String> visitados = new HashSet<>();

        Vertex primero = grafo.buscarVertice(inicio);

        dfsAuxiliar(primero, visitados, recorrido);

        return recorrido;
    }

    private void dfsAuxiliar(
            Vertex actual,
            Set<String> visitados,
            List<Vertex> recorrido) {

        visitados.add(actual.getId());
        recorrido.add(actual);

        for (Vertex vecino :
                grafo.obtenerVecinos(actual.getId())) {

            if (!visitados.contains(vecino.getId())) {
                dfsAuxiliar(vecino, visitados, recorrido);
            }
        }
    }

    // DFS: recorrido en profundidad iterativo
    public List<Vertex> dfsIterativo(String inicio) {

        validarInicio(inicio);

        List<Vertex> recorrido = new ArrayList<>();
        Set<String> visitados = new HashSet<>();
        Deque<Vertex> pila = new ArrayDeque<>();

        pila.push(grafo.buscarVertice(inicio));

        while (!pila.isEmpty()) {

            Vertex actual = pila.pop();

            if (visitados.contains(actual.getId())) {
                continue;
            }

            visitados.add(actual.getId());
            recorrido.add(actual);

            List<Vertex> vecinos =
                    grafo.obtenerVecinos(actual.getId());

            // Se agregan en orden inverso para conservar
            // el mismo orden del recorrido recursivo.
            for (int i = vecinos.size() - 1; i >= 0; i--) {

                Vertex vecino = vecinos.get(i);

                if (!visitados.contains(vecino.getId())) {
                    pila.push(vecino);
                }
            }
        }

        return recorrido;
    }

    // Validar que exista el vértice inicial
    private void validarInicio(String inicio) {

        if (inicio == null ||
                grafo.buscarVertice(inicio) == null) {

            throw new IllegalArgumentException(
                "El vertice inicial no existe: " + inicio
            );
        }
    }
}
