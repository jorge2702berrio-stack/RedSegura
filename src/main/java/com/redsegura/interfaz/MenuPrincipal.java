
package com.redsegura.interfaz;

import com.redsegura.grafo.MixedGraph;
import com.redsegura.principal.DataFactory;
import com.redsegura.modelo.Vertex;
import com.redsegura.modelo.Edge;
import com.redsegura.representaciones.GraphRepresentations;
import com.redsegura.algoritmos.GraphTraversal;
import com.redsegura.algoritmos.Dijkstra;
import com.redsegura.arboles.BinarySearchTree;
import com.redsegura.arboles.AvlTree;

import java.util.Scanner;

public class MenuPrincipal {

    private static final Scanner scanner = new Scanner(System.in);

    public static void iniciar() {

        MixedGraph grafo = DataFactory.crearGrafo();

        int opcion;

        do {
            System.out.println("\n==================================");
            System.out.println("       SISTEMA REDSEGURA");
            System.out.println("==================================");
            System.out.println("1. Mostrar grafo");
            System.out.println("2. Mostrar representaciones del grafo");
            System.out.println("3. Recorridos BFS y DFS");
            System.out.println("4. Calcular ruta mas corta (Dijkstra)");
            System.out.println("5. Comparar arbol ABB y AVL");
            System.out.println("6. Mostrar informacion del arbol AVL");
            System.out.println("7. Bloquear conexion entre R01 y R18");
            System.out.println("8. Consultar vertice");
            System.out.println("9. Desbloquear conexion entre R01 y R18");
            System.out.println("0. Salir");
            System.out.println("==================================");
            System.out.print("Seleccione una opcion: ");

            opcion = scanner.nextInt();

            switch (opcion) {

                case 1:
                    grafo.mostrarGrafo();
                    break;

                case 2:
                    GraphRepresentations representaciones =
                            new GraphRepresentations(grafo);

                    System.out.println("\n--- LISTA DE ADYACENCIA ---");
                    representaciones.mostrarListaAdyacencia();

                    System.out.println("\n--- MATRIZ DE ADYACENCIA ---");
                    representaciones.mostrarMatrizAdyacencia();

                    System.out.println("\n--- MATRIZ DE INCIDENCIA ---");
                    representaciones.mostrarMatrizIncidencia();
                    break;

                case 3:
                    GraphTraversal recorridos =
                            new GraphTraversal(grafo);

                    System.out.println("\n--- RECORRIDO BFS DESDE S02 ---");
                    System.out.println(recorridos.bfs("S02"));

                    System.out.println("\n--- RECORRIDO DFS RECURSIVO DESDE S02 ---");
                    System.out.println(recorridos.dfsRecursivo("S02"));

                    System.out.println("\n--- RECORRIDO DFS ITERATIVO DESDE S02 ---");
                    System.out.println(recorridos.dfsIterativo("S02"));
                    break;

                case 4:
                    System.out.println("\n===== CALCULAR RUTA MAS CORTA =====");

                    System.out.println("\nVertices disponibles:");

                    for (Vertex vertex : grafo.getVertices()) {
                        System.out.println(vertex.getId() + " - "
                                + vertex.getNombre());
                    }

                    scanner.nextLine();

                    System.out.print("\nIngrese el vertice de origen: ");
                    String origen = scanner.nextLine().trim().toUpperCase();

                    System.out.print("Ingrese el vertice de destino: ");
                    String destino = scanner.nextLine().trim().toUpperCase();

                    if (grafo.buscarVertice(origen) == null) {
                        System.out.println("\nError: el vertice de origen "
                                + origen + " no existe.");
                        break;
                    }

                    if (grafo.buscarVertice(destino) == null) {
                        System.out.println("\nError: el vertice de destino "
                                + destino + " no existe.");
                        break;
                    }

                    Dijkstra dijkstra = new Dijkstra(grafo);

                    Dijkstra.Resultado resultado =
                            dijkstra.encontrarRuta(origen, destino);

                    resultado.mostrarResultado();
                    break;

                case 5:
                    BinarySearchTree abb = new BinarySearchTree();
                    AvlTree avl = new AvlTree();

                    for (Vertex vertex : grafo.getVertices()) {
                        abb.insert(vertex);
                        avl.insert(vertex);
                    }

                    String nombreBuscado = "Hospital Universitario";

                    System.out.println("\n--- BUSQUEDA EN ABB ---");
                    System.out.println("Resultado: "
                            + abb.search(nombreBuscado));
                    System.out.println("Pasos: "
                            + abb.getPasosBusqueda());

                    System.out.println("\n--- BUSQUEDA EN AVL ---");
                    System.out.println("Resultado: "
                            + avl.search(nombreBuscado));
                    System.out.println("Pasos: "
                            + avl.getPasosBusqueda());
                    break;

                case 6:
                    AvlTree arbol = new AvlTree();

                    for (Vertex vertex : grafo.getVertices()) {
                        arbol.insert(vertex);
                    }

                    System.out.println("\n--- INFORMACION DEL AVL ---");
                    System.out.println("Altura del arbol: "
                            + arbol.height());
                    System.out.println("Factor de equilibrio de la raiz: "
                            + arbol.getRootBalanceFactor());
                    break;

                case 7:
                    System.out.println("\n===== BLOQUEAR CONEXION =====");

                    System.out.println("\nConexiones activas:");

                    for (Edge arista : grafo.getAristas()) {
                        System.out.println(arista);
                    }

                    scanner.nextLine();

                    System.out.print("\nIngrese el ID de la conexion que desea bloquear: ");
                    String idBloquear =
                            scanner.nextLine().trim().toUpperCase();

                    boolean eliminado = grafo.eliminarArista(idBloquear);

                    if (eliminado) {
                        System.out.println("\nLa conexion "
                                + idBloquear + " fue bloqueada correctamente.");

                        System.out.println("Conexiones activas: "
                                + grafo.cantidadAristas());

                        System.out.print("\nIngrese el vertice de origen para calcular una ruta: ");
                        String origenBloqueo =
                                scanner.nextLine().trim().toUpperCase();

                        System.out.print("Ingrese el vertice de destino: ");
                        String destinoBloqueo =
                                scanner.nextLine().trim().toUpperCase();

                        if (grafo.buscarVertice(origenBloqueo) == null
                                || grafo.buscarVertice(destinoBloqueo) == null) {

                            System.out.println("\nError: uno de los vertices no existe.");
                            break;
                        }

                        Dijkstra dijkstraBloqueo = new Dijkstra(grafo);

                        Dijkstra.Resultado resultadoBloqueo =
                                dijkstraBloqueo.encontrarRuta(
                                        origenBloqueo, destinoBloqueo);

                        resultadoBloqueo.mostrarResultado();

                    } else {
                        System.out.println("\nNo se encontro una conexion activa con ID: "
                                + idBloquear);
                    }
                    break;

                case 8:
                    System.out.println("\n===== CONSULTAR VERTICE =====");

                    scanner.nextLine();

                    System.out.print("Ingrese el ID del vertice que desea consultar: ");
                    String idVertice =
                            scanner.nextLine().trim().toUpperCase();

                    Vertex encontrado = grafo.buscarVertice(idVertice);

                    if (encontrado == null) {
                        System.out.println("\nERROR: El vertice "
                                + idVertice + " no existe en el grafo.");
                    } else {
                        System.out.println("\nVertice encontrado correctamente:");
                        System.out.println(encontrado);
                    }
                    break;

                case 9:
                    System.out.println("\n===== DESBLOQUEAR CONEXION =====");

                    if (grafo.getConexionesBloqueadas().isEmpty()) {
                        System.out.println("\nNo hay conexiones bloqueadas.");
                        break;
                    }

                    System.out.println("\nConexiones bloqueadas:");

                    for (Edge arista : grafo.getConexionesBloqueadas()) {
                        System.out.println(arista);
                    }

                    scanner.nextLine();

                    System.out.print("\nIngrese el ID de la conexion que desea desbloquear: ");
                    String idDesbloquear =
                            scanner.nextLine().trim().toUpperCase();

                    if (grafo.desbloquearArista(idDesbloquear)) {
                        System.out.println("\nLa conexion "
                                + idDesbloquear
                                + " fue desbloqueada correctamente.");

                        System.out.println("Conexiones activas: "
                                + grafo.cantidadAristas());
                    } else {
                        System.out.println("\nNo se encontro una conexion bloqueada con ID: "
                                + idDesbloquear);
                    }
                    break;

                case 0:
                    System.out.println("\nGracias por utilizar RedSegura.");
                    break;

                default:
                    System.out.println("\nOpcion no valida.");
            }

        } while (opcion != 0);
    }
}
