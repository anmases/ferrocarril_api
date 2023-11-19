package org.ieschabas.models;

import com.fasterxml.jackson.databind.JsonNode;
import jakarta.persistence.*;

@Entity
@Table(name = "trayecto")
public class Trayecto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private int id;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "tren_id")
    private Tren tren;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "estacion_id_salida")
    private Estacion estacion1;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "estacion_id_llegada")
    private Estacion estacion2;
    @Column
    private String fecha;
    @Column
    private String hora;
    public Trayecto(){}

    public Trayecto(Tren tren, Estacion estacion1, Estacion estacion2, String fecha, String hora) {
        this.tren = tren;
        this.estacion1 = estacion1;
        this.estacion2 = estacion2;
        this.fecha = fecha;
        this.hora = hora;
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

    public Estacion getEstacion1() {
        return estacion1;
    }

    public void setEstacion1(Estacion estacion1) {
        this.estacion1 = estacion1;
    }

    public Estacion getEstacion2() {
        return estacion2;
    }

    public void setEstacion2(Estacion estacion2) {
        this.estacion2 = estacion2;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    @Override
    public String toString() {
        return "{" +
                "'id':'" + id + '\'' +
                ", 'tren':'" + tren.toString() + '\'' +
                ", 'estacion1':'" + estacion1.toString() + '\'' +
                ", 'estacion2':'" + estacion2.toString() + '\'' +
                ", 'fecha':'" + fecha + '\'' +
                ", 'hora':'" + hora + '\'' +
                '}';
    }
    public static Trayecto fromJson(JsonNode node){
        Trayecto trayecto = new Trayecto();
        if(node.has("id")) trayecto.setId(node.get("id").asInt());
        if(node.has("fecha")) trayecto.setFecha(node.get("fecha").asText());
        if(node.has("hora")) trayecto.setHora(node.get("hora").asText());
        if(node.has("tren")) trayecto.setTren(Tren.fromJson(node.get("tren")));
        if(node.has("estacion1")) trayecto.setEstacion1(Estacion.fromJson(node.get("estacion1")));
        if(node.has("estacion2")) trayecto.setEstacion2(Estacion.fromJson(node.get("estacion2")));
        return trayecto;
    }
}
