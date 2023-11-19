package org.ieschabas.models;

import com.fasterxml.jackson.databind.JsonNode;
import jakarta.persistence.*;

@Entity
@Table(name = "estacion")
public class Estacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private int id;
    @Column
    private String nombre;
    @Column
    private String ciudad;
    public Estacion(){}

    public Estacion(String nombre, String ciudad) {
        this.nombre = nombre;
        this.ciudad = ciudad;
    }

    public int getId() {return id;}

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    @Override
    public String toString() {
        return "{" +
                "'id':'" + id + '\'' +
                ", 'nombre':'" + nombre + '\'' +
                ", 'ciudad':'" + ciudad + '\'' +
                '}';
    }
    public static Estacion fromJson(JsonNode node){
        Estacion estacion = new Estacion();
        if(node.has("id")) estacion.setId(node.get("id").asInt());
        if(node.has("nombre")) estacion.setNombre(node.get("nombre").asText());
        if(node.has("ciudad")) estacion.setCiudad(node.get("ciudad").asText());
        return estacion;
    }
}
