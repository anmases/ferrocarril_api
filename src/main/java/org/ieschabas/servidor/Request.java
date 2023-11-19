package org.ieschabas.servidor;

import com.fasterxml.jackson.databind.JsonNode;
import org.ieschabas.conexion.Dao;
import org.ieschabas.models.*;

import java.util.List;


public class Request {
    private final Dao<Tren> daoTren;
    private final Dao<Estacion> daoEstacion;
    private final Dao<Empleado> daoEmpleado;
    private final Dao<Trayecto> daoTrayecto;
    private final Dao<Pasajero> daoPasajero;
    private final Dao<Mantenimiento> daoMantenimiento;
    public Request(){
        daoTren = new Dao<>(Tren.class);
        daoEstacion = new Dao<>(Estacion.class);
        daoEmpleado = new Dao<>(Empleado.class);
        daoTrayecto = new Dao<>(Trayecto.class);
        daoPasajero = new Dao<>(Pasajero.class);
        daoMantenimiento = new Dao<>(Mantenimiento.class);
    }
    public List<?> get(String type){
        switch (type.toLowerCase()){
            case "tren":
                return daoTren.listar();
            case "estacion":
                return daoEstacion.listar();
            case "empleado":
                return daoEmpleado.listar();
            case "mantenimiento":
                return daoMantenimiento.listar();
            case "pasajero":
                return daoPasajero.listar();
            case "trayecto":
                return daoTrayecto.listar();
            default:
                return null;
        }
    }
    public boolean post(String type, JsonNode data){
        switch (type.toLowerCase()){
            case "tren":
                return daoTren.insertar(Tren.fromJson(data));
            case "estacion":
                return daoEstacion.insertar(Estacion.fromJson(data));
            case "empleado":
                return daoEmpleado.insertar(Empleado.fromJson(data));
            case "mantenimiento":
                return daoMantenimiento.insertar(Mantenimiento.fromJson(data));
            case "pasajero":
                return daoPasajero.insertar(Pasajero.fromJson(data));
            case "trayecto":
                return daoTrayecto.insertar(Trayecto.fromJson(data));
            default:
                return false;
        }
    }

    public boolean delete(String type, JsonNode data){
        switch (type.toLowerCase()){
            case "tren":
                return daoTren.eliminar(data.get("id").asText());
            case "estacion":
                return daoEstacion.eliminar(data.get("id").asText());
            case "empleado":
                return daoEmpleado.eliminar(data.get("id").asText());
            case "mantenimiento":
                return daoMantenimiento.eliminar(data.get("id").asText());
            case "pasajero":
                return daoPasajero.eliminar(data.get("id").asText());
            case "trayecto":
                return daoTrayecto.eliminar(data.get("id").asText());
            default:
                return false;
        }
    }
}
