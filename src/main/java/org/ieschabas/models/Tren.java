package org.ieschabas.models;

import com.fasterxml.jackson.databind.JsonNode;
import jakarta.persistence.*;

@Entity
@Table(name = "tren")
public class Tren{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private int id;
    @Column
    private String modelo;
    @Column
    private int capacidad;
    public Tren(){}

    public Tren(String modelo, int capacidad) {
        this.modelo = modelo;
        this.capacidad = capacidad;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {this.id = id;}

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
        return "{" +
                "'id':'" + id + '\'' +
                ", 'modelo':'" + modelo + '\'' +
                ", 'capacidad':'" + capacidad + '\'' +
                '}';
    }

    public static Tren fromJson(JsonNode node){
        Tren tren = new Tren();
        if(node.has("id")) tren.setId(node.get("id").asInt());
        if(node.has("modelo")) tren.setModelo(node.get("modelo").asText());
        if(node.has("capacidad")) tren.setCapacidad(node.get("capacidad").asInt());
        return tren;
    }

}
