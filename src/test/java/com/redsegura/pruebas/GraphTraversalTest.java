
package com.redsegura.pruebas;

import com.redsegura.grafo.MixedGraph;
import com.redsegura.principal.DataFactory;
import com.redsegura.algoritmos.GraphTraversal;
import com.redsegura.modelo.Vertex;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

public class GraphTraversalTest {

    @Test
    public void probarBFS() {

        MixedGraph grafo = DataFactory.crearGrafo();
        GraphTraversal recorrido = new GraphTraversal(grafo);

        List<Vertex> resultado = recorrido.bfs("S02");

        assertEquals(12, resultado.size());
        assertEquals("S02", resultado.get(0).getId());
    }

    @Test
    public void probarDFSRecursivo() {

        MixedGraph grafo = DataFactory.crearGrafo();
        GraphTraversal recorrido = new GraphTraversal(grafo);

        List<Vertex> resultado =
                recorrido.dfsRecursivo("S02");

        assertEquals(12, resultado.size());
        assertEquals("S02", resultado.get(0).getId());
    }

    @Test
    public void probarDFSIterativo() {

        MixedGraph grafo = DataFactory.crearGrafo();
        GraphTraversal recorrido = new GraphTraversal(grafo);

        List<Vertex> resultado =
                recorrido.dfsIterativo("S02");

        assertEquals(12, resultado.size());
        assertEquals("S02", resultado.get(0).getId());
    }

    @Test
    public void probarInicioInexistente() {

        MixedGraph grafo = DataFactory.crearGrafo();
        GraphTraversal recorrido = new GraphTraversal(grafo);

        assertThrows(
                IllegalArgumentException.class,
                () -> recorrido.bfs("S99")
        );
    }
}
