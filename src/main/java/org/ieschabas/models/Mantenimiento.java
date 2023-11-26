package org.ieschabas.models;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.*;
@Entity
@Table(name = "mantenimiento")
public class Mantenimiento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private int id;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "tren_id", referencedColumnName = "id")
    private Tren tren;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "empleado_id", referencedColumnName = "id")
    private Empleado empleado;
    @Column
    private String inicio;
    @Column
    private String fin;
    @Column
    private String descripcion;
    public Mantenimiento(){}

    public Mantenimiento(Tren tren, Empleado empleado, String inicio, String fin, String descripcion) {
        this.tren = tren;
        this.empleado = empleado;
        this.inicio = inicio;
        this.fin = fin;
        this.descripcion = descripcion;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Tren getTren() {
        return tren;
    }

    public void setTren(Tren tren) {
        this.tren = tren;
    }

    public Empleado getEmpleado() {
        return empleado;
    }

    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
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
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.writeValueAsString(this);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return "{}";
        }
    }
    public static Mantenimiento fromJson(JsonNode node){
        Mantenimiento mantenimiento = new Mantenimiento();
        if(node.has("id")) mantenimiento.setId(node.get("id").asInt());
        if(node.has("inicio")) mantenimiento.setInicio(node.get("inicio").asText());
        if(node.has("fin")) mantenimiento.setFin(node.get("fin").asText());
        if(node.has("descripcion")) mantenimiento.setDescripcion(node.get("descripcion").asText());
        if(node.has("tren")) mantenimiento.setTren(Tren.fromJson(node.get("tren")));
        if(node.has("empleado")) mantenimiento.setEmpleado(Empleado.fromJson(node.get("empleado")));
        return mantenimiento;
    }
}
