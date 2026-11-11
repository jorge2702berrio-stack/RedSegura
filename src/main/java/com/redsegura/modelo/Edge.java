
package com.redsegura.modelo;

public class Edge {

    private String id;
    private String etiqueta;
    private Vertex origen;
    private Vertex destino;
    private double peso;
    private boolean dirigida;

    // Constructor principal
    public Edge(String id, String etiqueta,
                Vertex origen, Vertex destino,
                double peso, boolean dirigida) {

        if (id == null || id.trim().isEmpty()) {
            throw new IllegalArgumentException(
                "El ID de la conexion no puede estar vacio."
            );
        }

        if (etiqueta == null || etiqueta.trim().isEmpty()) {
            throw new IllegalArgumentException(
                "La etiqueta no puede estar vacia."
            );
        }

        if (origen == null || destino == null) {
            throw new IllegalArgumentException(
                "Los vertices no pueden ser nulos."
            );
        }

        if (!Double.isFinite(peso) || peso <= 0) {
            throw new IllegalArgumentException(
                "El peso debe ser mayor que cero."
            );
        }

        this.id = id;
        this.etiqueta = etiqueta;
        this.origen = origen;
        this.destino = destino;
        this.peso = peso;
        this.dirigida = dirigida;
    }

    // Constructor de compatibilidad temporal
    public Edge(Vertex origen, Vertex destino, double peso) {
        this("SIN_ID", "Sin etiqueta",
             origen, destino, peso, false);
    }

    public String getId() {
        return id;
    }

    public String getEtiqueta() {
        return etiqueta;
    }

    public Vertex getOrigen() {
        return origen;
    }

    public Vertex getDestino() {
        return destino;
    }

    public double getPeso() {
        return peso;
    }

    public boolean isDirigida() {
        return dirigida;
    }

    public void setPeso(double peso) {
        if (!Double.isFinite(peso) || peso <= 0) {
            throw new IllegalArgumentException(
                "El peso debe ser mayor que cero."
            );
        }

        this.peso = peso;
    }

    @Override
    public String toString() {
        return id + ": "
                + origen.getId()
                + (dirigida ? " -> " : " -- ")
                + destino.getId()
                + " | " + etiqueta
                + " | " + peso + " minutos";
    }
}
