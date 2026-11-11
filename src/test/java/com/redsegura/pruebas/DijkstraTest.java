
package com.redsegura.pruebas;

import com.redsegura.grafo.MixedGraph;
import com.redsegura.principal.DataFactory;
import com.redsegura.algoritmos.Dijkstra;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class DijkstraTest {

    @Test
    public void probarRutaMasCorta() {

        MixedGraph grafo = DataFactory.crearGrafo();

        Dijkstra dijkstra = new Dijkstra(grafo);

        Dijkstra.Resultado resultado =
                dijkstra.encontrarRuta("S02", "S08");

        assertTrue(resultado.isAlcanzable());

        assertEquals(24.0, resultado.getDistancia(), 0.001);

        assertEquals(
                "S02 -> S01 -> S07 -> S08",
                resultado.getRuta().stream()
                        .map(v -> v.getId())
                        .reduce((a, b) -> a + " -> " + b)
                        .orElse("")
        );
    }

    @Test
    public void probarBloqueoDeConexion() {

        MixedGraph grafo = DataFactory.crearGrafo();

        boolean eliminado = grafo.eliminarArista("R16");

        assertTrue(eliminado);

        assertEquals(17, grafo.cantidadAristas());

        Dijkstra dijkstra = new Dijkstra(grafo);

        Dijkstra.Resultado resultado =
                dijkstra.encontrarRuta("S02", "S08");

        assertTrue(resultado.isAlcanzable());

        assertEquals(24.0, resultado.getDistancia(), 0.001);
    }

    @Test
    public void probarOrigenInexistente() {

        MixedGraph grafo = DataFactory.crearGrafo();

        Dijkstra dijkstra = new Dijkstra(grafo);

        assertThrows(
                IllegalArgumentException.class,
                () -> dijkstra.encontrarRuta("S99", "S08")
        );
    }
}
