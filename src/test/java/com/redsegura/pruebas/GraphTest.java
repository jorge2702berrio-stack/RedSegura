
package com.redsegura.pruebas;

import com.redsegura.grafo.MixedGraph;
import com.redsegura.modelo.Vertex;
import com.redsegura.principal.DataFactory;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class GraphTest {

    @Test
    public void probarCantidadDeVertices() {

        MixedGraph grafo = DataFactory.crearGrafo();

        assertEquals(12, grafo.cantidadVertices(),
                "El grafo debe tener 12 vertices.");
    }

    @Test
    public void probarCantidadDeConexiones() {

        MixedGraph grafo = DataFactory.crearGrafo();

        assertEquals(18, grafo.cantidadAristas(),
                "El grafo debe tener 18 conexiones.");
    }

    @Test
    public void probarBusquedaDeVertice() {

        MixedGraph grafo = DataFactory.crearGrafo();

        Vertex vertex = grafo.buscarVertice("S05");

        assertNotNull(vertex,
                "El vertice S05 debe existir.");

        assertEquals("Hospital Universitario",
                vertex.getNombre());
    }

    @Test
    public void probarVerticeInexistente() {

        MixedGraph grafo = DataFactory.crearGrafo();

        Vertex vertex = grafo.buscarVertice("S99");

        assertNull(vertex,
                "El vertice S99 no debe existir.");
    }
}
