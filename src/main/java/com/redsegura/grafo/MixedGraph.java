
package com.redsegura.grafo;

import com.redsegura.modelo.Vertex;
import com.redsegura.modelo.Edge;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class MixedGraph {

    private Map<String, Vertex> vertices;
    private List<Edge> aristas;

    // Almacena las conexiones que han sido bloqueadas
    private Map<String, Edge> conexionesBloqueadas;

    public MixedGraph() {
        vertices = new LinkedHashMap<>();
        aristas = new ArrayList<>();
        conexionesBloqueadas = new LinkedHashMap<>();
    }

    // Agregar un vertice
    public void agregarVertice(Vertex vertex) {

        if (vertex == null) {
            throw new IllegalArgumentException(
                "El vertice no puede ser nulo."
            );
        }

        if (vertices.containsKey(vertex.getId())) {
            throw new IllegalArgumentException(
                "Ya existe un vertice con ID: " + vertex.getId()
            );
        }

        vertices.put(vertex.getId(), vertex);
    }

    // Agregar una arista o arco
    public void agregarArista(
            String id,
            String etiqueta,
            String origen,
            String destino,
            double peso,
            boolean dirigida) {

        for (Edge arista : aristas) {
            if (arista.getId().equals(id)) {
                throw new IllegalArgumentException(
                    "Ya existe una conexion con ID: " + id
                );
            }
        }

        if (conexionesBloqueadas.containsKey(id)) {
            throw new IllegalArgumentException(
                "La conexion esta bloqueada: " + id
            );
        }

        Vertex vOrigen = vertices.get(origen);
        Vertex vDestino = vertices.get(destino);

        if (vOrigen == null || vDestino == null) {
            throw new IllegalArgumentException(
                "El vertice de origen o destino no existe."
            );
        }

        Edge nuevaArista = new Edge(
            id, etiqueta, vOrigen, vDestino, peso, dirigida
        );

        aristas.add(nuevaArista);
    }

    // Obtener todos los vertices
    public List<Vertex> getVertices() {
        return new ArrayList<>(vertices.values());
    }

    // Obtener todas las conexiones activas
    public List<Edge> getAristas() {
        return new ArrayList<>(aristas);
    }

    // Buscar un vertice
    public Vertex buscarVertice(String id) {
        return vertices.get(id);
    }

    // Obtener vecinos respetando la orientacion
    public List<Vertex> obtenerVecinos(String id) {

        if (!vertices.containsKey(id)) {
            throw new IllegalArgumentException(
                "No existe el vertice: " + id
            );
        }

        List<Vertex> vecinos = new ArrayList<>();

        for (Edge arista : aristas) {

            String origen = arista.getOrigen().getId();
            String destino = arista.getDestino().getId();

            if (origen.equals(id)) {
                vecinos.add(arista.getDestino());
            }

            if (!arista.isDirigida() && destino.equals(id)) {
                vecinos.add(arista.getOrigen());
            }
        }

        return vecinos;
    }

    // Cantidad de vertices
    public int cantidadVertices() {
        return vertices.size();
    }

    // Cantidad de conexiones activas
    public int cantidadAristas() {
        return aristas.size();
    }

    // Mostrar el contenido del grafo
    public void mostrarGrafo() {

        System.out.println("===== GRAFO REDSEGURA =====");
        System.out.println("Vertices: " + cantidadVertices());
        System.out.println("Conexiones activas: " + cantidadAristas());

        for (Edge arista : aristas) {
            System.out.println(arista);
        }
    }

    // Bloquear una conexion y guardar sus datos
    public boolean eliminarArista(String id) {

        for (int i = 0; i < aristas.size(); i++) {

            Edge arista = aristas.get(i);

            if (arista.getId().equalsIgnoreCase(id)) {

                conexionesBloqueadas.put(arista.getId(), arista);

                aristas.remove(i);

                return true;
            }
        }

        return false;
    }

    // Obtener las conexiones bloqueadas
    public List<Edge> getConexionesBloqueadas() {
        return new ArrayList<>(conexionesBloqueadas.values());
    }

    // Desbloquear y restaurar una conexion
    public boolean desbloquearArista(String id) {

        Edge conexion = conexionesBloqueadas.remove(
            id.toUpperCase()
        );

        if (conexion == null) {
            return false;
        }

        aristas.add(conexion);

        return true;
    }
}
