
package com.redsegura.pruebas;

import com.redsegura.arboles.BinarySearchTree;
import com.redsegura.arboles.AvlTree;
import com.redsegura.modelo.Vertex;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TreeTest {

    @Test
    public void probarInsercionYBusquedaABB() {

        BinarySearchTree abb = new BinarySearchTree();

        Vertex v1 = new Vertex("S01", "Universidad", "Universidad");
        Vertex v2 = new Vertex("S02", "Hospital", "Salud");

        abb.insert(v1);
        abb.insert(v2);

        assertNotNull(abb.search("Hospital"));
        assertEquals("S02", abb.search("Hospital").getId());
    }

    @Test
    public void probarEliminacionABB() {

        BinarySearchTree abb = new BinarySearchTree();

        Vertex v1 = new Vertex("S01", "Universidad", "Universidad");
        Vertex v2 = new Vertex("S02", "Hospital", "Salud");

        abb.insert(v1);
        abb.insert(v2);

        abb.delete("Hospital");

        assertNull(abb.search("Hospital"));
    }

    @Test
    public void probarInsercionYBusquedaAVL() {

        AvlTree avl = new AvlTree();

        Vertex v1 = new Vertex("S01", "Carlos", "Prueba");
        Vertex v2 = new Vertex("S02", "Andres", "Prueba");
        Vertex v3 = new Vertex("S03", "Daniel", "Prueba");

        avl.insert(v1);
        avl.insert(v2);
        avl.insert(v3);

        assertNotNull(avl.search("Andres"));
        assertEquals("S02", avl.search("Andres").getId());
    }

    @Test
    public void probarEliminacionAVL() {

        AvlTree avl = new AvlTree();

        Vertex v1 = new Vertex("S01", "Carlos", "Prueba");
        Vertex v2 = new Vertex("S02", "Andres", "Prueba");
        Vertex v3 = new Vertex("S03", "Daniel", "Prueba");

        avl.insert(v1);
        avl.insert(v2);
        avl.insert(v3);

        avl.delete("Carlos");

        assertNull(avl.search("Carlos"));
        assertNotNull(avl.search("Andres"));
    }

    @Test
    public void probarAlturaAVL() {

        AvlTree avl = new AvlTree();

        avl.insert(new Vertex("S01", "Carlos", "Prueba"));
        avl.insert(new Vertex("S02", "Andres", "Prueba"));
        avl.insert(new Vertex("S03", "Daniel", "Prueba"));

        assertTrue(avl.height() > 0);
        assertTrue(Math.abs(avl.getRootBalanceFactor()) <= 1);
    }
    
@Test
public void probarLasCuatroRotacionesAVL() {

    // Rotacion LL
    AvlTree arbolLL = new AvlTree();
    arbolLL.insert(new Vertex("S01", "Carlos", "Prueba"));
    arbolLL.insert(new Vertex("S02", "Bruno", "Prueba"));
    arbolLL.insert(new Vertex("S03", "Andres", "Prueba"));

    assertEquals(2, arbolLL.height());
    assertEquals(0, arbolLL.getRootBalanceFactor());

    // Rotacion RR
    AvlTree arbolRR = new AvlTree();
    arbolRR.insert(new Vertex("S01", "Andres", "Prueba"));
    arbolRR.insert(new Vertex("S02", "Bruno", "Prueba"));
    arbolRR.insert(new Vertex("S03", "Carlos", "Prueba"));

    assertEquals(2, arbolRR.height());
    assertEquals(0, arbolRR.getRootBalanceFactor());

    // Rotacion LR
    AvlTree arbolLR = new AvlTree();
    arbolLR.insert(new Vertex("S01", "Carlos", "Prueba"));
    arbolLR.insert(new Vertex("S02", "Andres", "Prueba"));
    arbolLR.insert(new Vertex("S03", "Bruno", "Prueba"));

    assertEquals(2, arbolLR.height());
    assertEquals(0, arbolLR.getRootBalanceFactor());

    // Rotacion RL
    AvlTree arbolRL = new AvlTree();
    arbolRL.insert(new Vertex("S01", "Andres", "Prueba"));
    arbolRL.insert(new Vertex("S02", "Carlos", "Prueba"));
    arbolRL.insert(new Vertex("S03", "Bruno", "Prueba"));

    assertEquals(2, arbolRL.height());
    assertEquals(0, arbolRL.getRootBalanceFactor());
}

}
