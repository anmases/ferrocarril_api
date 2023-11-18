package org.ieschabas.models;

public class Mantenimiento {
    private String id;
    private String tren_id;
    private String empleado_id;
    private String inicio;
    private String fin;
    private String descripcion;
    public Mantenimiento(){}

    public Mantenimiento(String id, String tren_id, String empleado_id, String inicio, String fin, String descripcion) {
        this.id = id;
        this.tren_id = tren_id;
        this.empleado_id = empleado_id;
        this.inicio = inicio;
        this.fin = fin;
        this.descripcion = descripcion;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTren_id() {
        return tren_id;
    }

    public void setTren_id(String tren_id) {
        this.tren_id = tren_id;
    }

    public String getEmpleado_id() {
        return empleado_id;
    }

    public void setEmpleado_id(String empleado_id) {
        this.empleado_id = empleado_id;
    }

    public String getInicio() {
        return inicio;
    }

    public void setInicio(String inicio) {
        this.inicio = inicio;
    }

    public String getFin() {
        return fin;
    }

    public void setFin(String fin) {
        this.fin = fin;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public String toString() {
        return "Mantenimiento{" +
                "id='" + id + '\'' +
                ", tren_id='" + tren_id + '\'' +
                ", empleado_id='" + empleado_id + '\'' +
                ", inicio='" + inicio + '\'' +
                ", fin='" + fin + '\'' +
                ", descripcion='" + descripcion + '\'' +
                '}';
    }
}
