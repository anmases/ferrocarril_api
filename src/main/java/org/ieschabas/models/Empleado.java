package org.ieschabas.models;

public class Empleado {
    private String id;
    private String nombre;
    private String puesto;
    private String contratado;
    private String estacion_id;
    public Empleado(){}

    public Empleado(String id, String nombre, String puesto, String contratado, String estacion_id) {
        this.id = id;
        this.nombre = nombre;
        this.puesto = puesto;
        this.contratado = contratado;
        this.estacion_id = estacion_id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPuesto() {
        return puesto;
    }

    public void setPuesto(String puesto) {
        this.puesto = puesto;
    }

    public String getContratado() {
        return contratado;
    }

    public void setContratado(String contratado) {
        this.contratado = contratado;
    }

    public String getEstacion_id() {
        return estacion_id;
    }

    public void setEstacion_id(String estacion_id) {
        this.estacion_id = estacion_id;
    }

    @Override
    public String toString() {
        return "Empleado{" +
                "id='" + id + '\'' +
                ", nombre='" + nombre + '\'' +
                ", puesto='" + puesto + '\'' +
                ", contratado='" + contratado + '\'' +
                ", estacion_id='" + estacion_id + '\'' +
                '}';
    }
}
