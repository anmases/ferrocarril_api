package org.ieschabas.models;

public class Tren{
    private String id;
    private String modelo;
    private int capacidad;
    public Tren(){}

    public Tren(String id, String modelo, int capacidad) {
        this.id = id;
        this.modelo = modelo;
        this.capacidad = capacidad;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
    }

    @Override
    public String toString() {
        return "Tren{" +
                "id='" + id + '\'' +
                ", modelo='" + modelo + '\'' +
                ", capacidad='" + capacidad + '\'' +
                '}';
    }

}
