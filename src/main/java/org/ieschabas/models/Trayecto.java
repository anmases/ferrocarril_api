package org.ieschabas.models;

public class Trayecto {
    private String id;
    private String tren_id;
    private String estacion_id_salida;
    private String estacion_id_llegada;
    private String fecha;
    private String hora;
    public Trayecto(){}

    public Trayecto(String id, String tren_id, String estacion_id_salida, String estacion_id_llegada, String fecha, String hora) {
        this.id = id;
        this.tren_id = tren_id;
        this.estacion_id_salida = estacion_id_salida;
        this.estacion_id_llegada = estacion_id_llegada;
        this.fecha = fecha;
        this.hora = hora;
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

    public String getEstacion_id_salida() {
        return estacion_id_salida;
    }

    public void setEstacion_id_salida(String estacion_id_salida) {
        this.estacion_id_salida = estacion_id_salida;
    }

    public String getEstacion_id_llegada() {
        return estacion_id_llegada;
    }

    public void setEstacion_id_llegada(String estacion_id_llegada) {
        this.estacion_id_llegada = estacion_id_llegada;
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
        return "Trayecto{" +
                "id='" + id + '\'' +
                ", tren_id='" + tren_id + '\'' +
                ", estacion_id_salida='" + estacion_id_salida + '\'' +
                ", estacion_id_llegada='" + estacion_id_llegada + '\'' +
                ", fecha='" + fecha + '\'' +
                ", hora='" + hora + '\'' +
                '}';
    }
}
