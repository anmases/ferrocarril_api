package org.ieschabas.models;

import com.fasterxml.jackson.databind.JsonNode;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "pasajero")
public class Pasajero {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private int id;
    @Column
    private String nombre;
    @Column
    private String telefono;
    @Column
    private String nacimiento;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "pasajero_trayecto",
            joinColumns = @JoinColumn(name = "pasajero_id"),
            inverseJoinColumns = @JoinColumn(name = "trayecto_id"))
    private List<Trayecto> trayectos;
    //Constructor
    public Pasajero(){}
   //Constructor sobrecargado
    public Pasajero(String nombre, String telefono, String nacimiento, List<Trayecto> trayectos) {
        this.nombre = nombre;
        this.telefono = telefono;
        this.nacimiento = nacimiento;
        this.trayectos = trayectos;
    }
//Getters y setters:
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

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getNacimiento() {
        return nacimiento;
    }

    public void setNacimiento(String nacimiento) {
        this.nacimiento = nacimiento;
    }

    public List<Trayecto> getTrayectos() {
        return trayectos;
    }

    public void setTrayectos(List<Trayecto> trayectos) {
        this.trayectos = trayectos;
    }

    //Para convertir el objeto a cadena de texto:
    @Override
    public String toString() {
        return "{" +
                "'id':'" + id + '\'' +
                ", 'nombre':'" + nombre + '\'' +
                ", 'telefono':'" + telefono + '\'' +
                ", 'nacimiento':'" + nacimiento + '\'' +
                ", 'trayectos':'" + trayectos.toString() + '\'' +
                '}';
    }
    public static Pasajero fromJson(JsonNode node){
        Pasajero pasajero = new Pasajero();
        if(node.has("id")) pasajero.setId(node.get("id").asInt());
        if(node.has("nombre")) pasajero.setNombre(node.get("nombre").asText());
        if(node.has("telefono")) pasajero.setTelefono(node.get("telefono").asText());
        if(node.has("nacimiento")) pasajero.setNacimiento(node.get("nacimiento").asText());
        if(node.has("trayectos") && node.get("trayectos").isArray()) {
            List<Trayecto> trayectos = new ArrayList<>();
            for(JsonNode obj: node.get("trayectos")){
                trayectos.add(Trayecto.fromJson(obj));
            }
            pasajero.setTrayectos(trayectos);
        }
        return pasajero;
    }
}
