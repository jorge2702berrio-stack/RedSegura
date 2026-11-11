
package com.redsegura.pruebas;

import com.redsegura.grafo.MixedGraph;
import com.redsegura.modelo.Vertex;
import com.redsegura.modelo.Edge;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class GraphValidationTest {

    @Test
    public void probarPesoCero() {

        Vertex origen = new Vertex("S01", "Universidad", "Universidad");
        Vertex destino = new Vertex("S02", "Hospital", "Salud");

        assertThrows(
                IllegalArgumentException.class,
                () -> new Edge("R99", "Prueba", origen, destino, 0, true)
        );
    }

    @Test
    public void probarPesoNegativo() {

        Vertex origen = new Vertex("S01", "Universidad", "Universidad");
        Vertex destino = new Vertex("S02", "Hospital", "Salud");

        assertThrows(
                IllegalArgumentException.class,
                () -> new Edge("R99", "Prueba", origen, destino, -5, true)
        );
    }

    @Test
    public void probarVerticeDuplicado() {

        MixedGraph grafo = new MixedGraph();

        grafo.agregarVertice(
                new Vertex("S01", "Universidad", "Universidad")
        );

        assertThrows(
                IllegalArgumentException.class,
                () -> grafo.agregarVertice(
                        new Vertex("S01", "Hospital", "Salud")
                )
        );
    }

    @Test
    public void probarConexionConVerticeInexistente() {

        MixedGraph grafo = new MixedGraph();

        grafo.agregarVertice(
                new Vertex("S01", "Universidad", "Universidad")
        );

        assertThrows(
                IllegalArgumentException.class,
                () -> grafo.agregarArista(
                        "R01", "Prueba", "S01", "S99", 5, true
                )
        );
    }

    @Test
    public void probarConexionDuplicada() {

        MixedGraph grafo = new MixedGraph();

        grafo.agregarVertice(
                new Vertex("S01", "Universidad", "Universidad")
        );

        grafo.agregarVertice(
                new Vertex("S02", "Hospital", "Salud")
        );

        grafo.agregarArista(
                "R01", "Primera conexion", "S01", "S02", 5, true
        );

        assertThrows(
                IllegalArgumentException.class,
                () -> grafo.agregarArista(
                        "R01", "Conexion duplicada", "S01", "S02", 8, true
                )
        );
    }
}
