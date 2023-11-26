package org.ieschabas.models;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.*;

@Entity
@Table(name = "empleado")
public class Empleado {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private int id;
    @Column
    private String nombre;
    @Column
    private String puesto;
    @Column
    private String contratado;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "estacion_id", referencedColumnName = "id")
    private Estacion estacion;
    public Empleado(){}

    public Empleado(String nombre, String puesto, String contratado, Estacion estacion) {
        this.nombre = nombre;
        this.puesto = puesto;
        this.contratado = contratado;
        this.estacion = estacion;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public Estacion getEstacion() {
        return estacion;
    }

    public void setEstacion(Estacion estacion) {
        this.estacion = estacion;
    }

    @Override
    public String toString() {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.writeValueAsString(this);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return "{}";
        }
    }
    public static Empleado fromJson(JsonNode node){
        Empleado empleado = new Empleado();
        if(node.has("id")) empleado.setId(node.get("id").asInt());
        if(node.has("nombre")) empleado.setNombre(node.get("nombre").asText());
        if(node.has("puesto")) empleado.setPuesto(node.get("puesto").asText());
        if(node.has("contratado")) empleado.setContratado(node.get("contratado").asText());
        //Cuando es un objeto llamamos a su propio método fromJson:
        if(node.has("estacion")) empleado.setEstacion(Estacion.fromJson(node.get("estacion")));
        return empleado;
    }
}
