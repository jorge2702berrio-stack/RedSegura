
package com.redsegura.modelo;

public class Vertex {

    private String id;
    private String nombre;
    private String tipo;
    private double latitud;
    private double longitud;

    // Constructor principal
    public Vertex(String id, String nombre, String tipo) {

        if (id == null || id.trim().isEmpty()) {
            throw new IllegalArgumentException(
                "El ID no puede estar vacio."
            );
        }

        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException(
                "El nombre no puede estar vacio."
            );
        }

        if (tipo == null || tipo.trim().isEmpty()) {
            throw new IllegalArgumentException(
                "El tipo no puede estar vacio."
            );
        }

        this.id = id;
        this.nombre = nombre;
        this.tipo = tipo;
        this.latitud = Double.NaN;
        this.longitud = Double.NaN;
    }

    // Constructor compatible con la version anterior
    public Vertex(String id, String nombre,
                  double latitud, double longitud) {

        this(id, nombre, "Sin especificar");
        this.latitud = latitud;
        this.longitud = longitud;
    }

    public String getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getTipo() {
        return tipo;
    }

    public double getLatitud() {
        return latitud;
    }

    public double getLongitud() {
        return longitud;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public void setLatitud(double latitud) {
        this.latitud = latitud;
    }

    public void setLongitud(double longitud) {
        this.longitud = longitud;
    }

    @Override
    public String toString() {
        return id + " - " + nombre + " (" + tipo + ")";
    }
}
