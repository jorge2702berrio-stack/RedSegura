
package com.redsegura.principal;

import com.redsegura.grafo.MixedGraph;
import com.redsegura.modelo.Vertex;

public class DataFactory {

    public static MixedGraph crearGrafo() {

        MixedGraph grafo = new MixedGraph();

        // =================================
        // 1. REGISTRAR LOS 12 VERTICES
        // =================================

        grafo.agregarVertice(
            new Vertex("S01", "UAJS Sede C", "Universidad")
        );

        grafo.agregarVertice(
            new Vertex("S02", "UAJS Sede E", "Universidad")
        );

        grafo.agregarVertice(
            new Vertex("S03", "Universidad de Sucre", "Universidad")
        );

        grafo.agregarVertice(
            new Vertex("S04", "CECAR", "Universidad")
        );

        grafo.agregarVertice(
            new Vertex("S05", "Hospital Universitario", "Salud")
        );

        grafo.agregarVertice(
            new Vertex("S06", "Clinica Las Americas", "Salud")
        );

        grafo.agregarVertice(
            new Vertex("S07", "Estacion Central SETP", "Transporte")
        );

        grafo.agregarVertice(
            new Vertex("S08", "Terminal de Transporte", "Transporte")
        );

        grafo.agregarVertice(
            new Vertex("S09", "Parque Santander", "Punto de encuentro")
        );

        grafo.agregarVertice(
            new Vertex("S10", "Mercado de Sincelejo", "Comercio")
        );

        grafo.agregarVertice(
            new Vertex("S11", "PEP Argelia", "Paradero")
        );

        grafo.agregarVertice(
            new Vertex("S12", "Glorieta Sincelejito", "Movilidad")
        );


        // =================================
        // 2. REGISTRAR LAS 18 CONEXIONES
        // =================================

        // R01 - Corredor universitario
        grafo.agregarArista(
            "R01", "Corredor universitario",
            "S01", "S07", 4, true
        );

        // R02 - Retorno por via alterna
        grafo.agregarArista(
            "R02", "Retorno por via alterna",
            "S07", "S01", 6, true
        );

        // R03 - Corredor peatonal
        grafo.agregarArista(
            "R03", "Corredor peatonal",
            "S01", "S09", 5, false
        );

        // R04 - Conexion entre sedes
        grafo.agregarArista(
            "R04", "Conexion entre sedes",
            "S02", "S01", 8, false
        );

        // R05 - Ruta de atencion
        grafo.agregarArista(
            "R05", "Ruta de atencion",
            "S02", "S06", 10, true
        );

        // R06 - Corredor clinico
        grafo.agregarArista(
            "R06", "Corredor clinico",
            "S06", "S05", 4, true
        );

        // R07 - Via hospitalaria
        grafo.agregarArista(
            "R07", "Via hospitalaria",
            "S05", "S09", 6, false
        );

        // R08 - Centro historico
        grafo.agregarArista(
            "R08", "Centro historico",
            "S09", "S10", 3, false
        );

        // R09 - Salida al terminal
        grafo.agregarArista(
            "R09", "Salida al terminal",
            "S10", "S08", 8, true
        );

        // R10 - Ingreso desde terminal
        grafo.agregarArista(
            "R10", "Ingreso desde terminal",
            "S08", "S10", 9, true
        );

        // R11 - Corredor Puerta Roja
        grafo.agregarArista(
            "R11", "Corredor Puerta Roja",
            "S03", "S12", 4, false
        );

        // R12 - Troncal al centro
        grafo.agregarArista(
            "R12", "Troncal al centro",
            "S12", "S09", 7, false
        );

        // R13 - Conexion universitaria
        grafo.agregarArista(
            "R13", "Conexion universitaria",
            "S04", "S11", 6, false
        );

        // R14 - Ruta SETP al centro
        grafo.agregarArista(
            "R14", "Ruta SETP al centro",
            "S11", "S07", 8, true
        );

        // R15 - Transferencia central
        grafo.agregarArista(
            "R15", "Transferencia central",
            "S07", "S09", 3, false
        );

        // R16 - Ruta SETP al terminal
        grafo.agregarArista(
            "R16", "Ruta SETP al terminal",
            "S07", "S08", 12, true
        );

        // R17 - Ruta hacia universidad
        grafo.agregarArista(
            "R17", "Ruta hacia universidad",
            "S09", "S03", 7, true
        );

        // R18 - Ruta hacia CECAR
        grafo.agregarArista(
            "R18", "Ruta hacia CECAR",
            "S12", "S04", 9, true
        );

        return grafo;
    }
}
