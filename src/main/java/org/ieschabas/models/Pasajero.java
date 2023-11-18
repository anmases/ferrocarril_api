package org.ieschabas.models;

public class Pasajero {
    //Campos
    private String id;
    private String nombre;
    private String telefono;
    private String nacimiento;
    private String trayecto_id;
    //Constructor
    public Pasajero(){}
   //Constructor sobrecargado
    public Pasajero(String id, String nombre, String telefono, String nacimiento, String trayecto_id) {
        this.id = id;
        this.nombre = nombre;
        this.telefono = telefono;
        this.nacimiento = nacimiento;
        this.trayecto_id = trayecto_id;
    }
//Getters y setters:
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

    public String getTrayecto_id() {
        return trayecto_id;
    }

    public void setTrayecto_id(String trayecto_id) {
        this.trayecto_id = trayecto_id;
    }
//Para convertir el objeto a cadena de texto:
    @Override
    public String toString() {
        return "Pasajero{" +
                "id='" + id + '\'' +
                ", nombre='" + nombre + '\'' +
                ", telefono='" + telefono + '\'' +
                ", nacimiento='" + nacimiento + '\'' +
                ", viaje='" + trayecto_id + '\'' +
                '}';
    }
}
