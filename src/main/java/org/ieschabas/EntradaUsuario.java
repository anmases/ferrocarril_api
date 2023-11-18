package org.ieschabas;

import org.ieschabas.models.*;
import org.ieschabas.conexion.Dao;
import org.ieschabas.enumerados.Accion;

import java.io.BufferedReader;
import java.io.IOException;
import java.sql.SQLException;

public abstract class EntradaUsuario {
    public static boolean menuTren(BufferedReader br, Dao dao, Accion accion) throws SQLException, IOException{
        if(accion == Accion.crear) {
            return dao.crearTabla(); //Crear tabla tren
        }else if (accion == Accion.eliminar){
            return dao.eliminarTabla(); //Eliminar tabla tren
        }else if (accion == Accion.listar){
            return leerListado(dao);
        }else if(accion == Accion.insertar){
            return leerEntradaTren(br, dao);
        }else if(accion == Accion.actualizar){
            return leerModificarTren(br, dao);
        } else if (accion == Accion.borrar) {
            return leerBorrado(br, dao);
        }
        return false;
    }
    public static boolean menuEstacion (BufferedReader br, Dao dao, Accion accion) throws SQLException, IOException{
        if(accion == Accion.crear) {
            return dao.crearTabla(); //Crear tabla tren
        }else if (accion == Accion.eliminar){
            return dao.eliminarTabla(); //Eliminar tabla tren
        }else if (accion == Accion.listar){
            return leerListado(dao);
        }else if(accion == Accion.insertar){
            return leerEntradaEstacion(br, dao);
        }else if(accion == Accion.actualizar){
            return leerModificarEstacion(br, dao);
        } else if (accion == Accion.borrar) {
            return leerBorrado(br, dao);
        }
        return false;
    }

    public static boolean menuEmpleado (BufferedReader br, Dao dao, Accion accion) throws SQLException, IOException{
        if(accion == Accion.crear) {
            return dao.crearTabla(); //Crear tabla tren
        }else if (accion == Accion.eliminar){
            return dao.eliminarTabla(); //Eliminar tabla tren
        }else if (accion == Accion.listar){
            return leerListado(dao);
        }else if(accion == Accion.insertar){
            return leerEntradaEmpleado(br, dao);
        }else if(accion == Accion.actualizar){
            return leerModificarEmpleado(br, dao);
        } else if (accion == Accion.borrar) {
            return leerBorrado(br, dao);
        }
        return false;
    }
    public static boolean menuTrayecto (BufferedReader br, Dao dao, Accion accion) throws SQLException, IOException{
        if(accion == Accion.crear) {
            return dao.crearTabla(); //Crear tabla tren
        }else if (accion == Accion.eliminar){
            return dao.eliminarTabla(); //Eliminar tabla tren
        }else if (accion == Accion.listar){
            return leerListado(dao);
        }else if(accion == Accion.insertar){
            return leerEntradaTrayecto(br, dao);
        }else if(accion == Accion.actualizar){
            return leerModificarTrayecto(br, dao);
        } else if (accion == Accion.borrar) {
            return leerBorrado(br, dao);
        }
        return false;
    }
    public static boolean menuPasajero (BufferedReader br, Dao dao, Accion accion) throws SQLException, IOException{
        if(accion == Accion.crear) {
            return dao.crearTabla(); //Crear tabla tren
        }else if (accion == Accion.eliminar){
            return dao.eliminarTabla(); //Eliminar tabla tren
        }else if (accion == Accion.listar){
            return leerListado(dao);
        }else if(accion == Accion.insertar){
            return leerEntradaPasajero(br, dao);
        }else if(accion == Accion.actualizar){
            return leerModificarPasajero(br, dao);
        } else if (accion == Accion.borrar) {
            return leerBorrado(br, dao);
        }
        return false;
    }
    public static boolean menuMantenimiento (BufferedReader br, Dao dao, Accion accion) throws SQLException, IOException{
        if(accion == Accion.crear) {
            return dao.crearTabla(); //Crear tabla tren
        }else if (accion == Accion.eliminar){
            return dao.eliminarTabla(); //Eliminar tabla tren
        }else if (accion == Accion.listar){
            return leerListado(dao);
        }else if(accion == Accion.insertar){
            return leerEntradaMantenimiento(br, dao);
        }else if(accion == Accion.actualizar){
            return leerModificarMantenimiento(br, dao);
        } else if (accion == Accion.borrar) {
            return leerBorrado(br, dao);
        }
        return false;
    }
////////////////////////////////////Entradas de datos/////////////////////////////////////////////////////
    public static boolean leerListado(Dao dao) throws SQLException {
        for(var objeto: dao.listar()){
            System.out.println(objeto.toString());
        }
        return true;
    }
    public static boolean leerBorrado(BufferedReader br, Dao dao) throws SQLException, IOException {
        System.out.println("introduzca el id: ");
        String id = br.readLine();
        System.out.println("¿Está seguro que desea eliminar el registro? Los registros asociados serán borrados\n [S/N]");
        String confirmacion = br.readLine();
        if(confirmacion.equalsIgnoreCase("S")){
            return dao.eliminar(id);
        } else if (confirmacion.equalsIgnoreCase("N")) {
            return false;
        }else{
            System.out.println("la opción introducida no es correcta");
            return false;
        }
    }
    public static boolean leerEntradaTren(BufferedReader br, Dao dao) throws SQLException, IOException {
        Tren tren = new Tren();
        System.out.println("id:");
        tren.setId(br.readLine());
        System.out.println("modelo:");
        tren.setModelo(br.readLine());
        try {
        System.out.println("Capacidad:");
            tren.setCapacidad(Integer.parseInt(br.readLine()));
        }catch (NumberFormatException ex){
            System.out.println("El valor debe ser un entero");
        }
        return dao.insertar(tren);
    }
    public static boolean leerEntradaEstacion(BufferedReader br, Dao dao) throws SQLException, IOException{
        Estacion estacion = new Estacion();
        System.out.println("id: ");
        estacion.setId(br.readLine());
        System.out.println("nombre: ");
        estacion.setNombre(br.readLine());
        System.out.println("ciudad: ");
        estacion.setCiudad(br.readLine());
        return dao.insertar(estacion);
    }
    public static boolean leerEntradaEmpleado(BufferedReader br, Dao dao) throws SQLException, IOException{
        Empleado empleado = new Empleado();
        System.out.println("id: ");
        empleado.setId(br.readLine());
        System.out.println("nombre: ");
        empleado.setNombre(br.readLine());
        System.out.println("ciudad: ");
        empleado.setPuesto(br.readLine());
        System.out.println("Fecha contratado: ");
        empleado.setContratado(br.readLine());
        System.out.println("id estación asignada: ");
        empleado.setEstacion_id(br.readLine());
        return dao.insertar(empleado);
    }
    public static boolean leerEntradaTrayecto(BufferedReader br, Dao dao) throws SQLException, IOException{
        Trayecto trayecto = new Trayecto();
        System.out.println("id: ");
        trayecto.setId(br.readLine());
        System.out.println("id del tren: ");
        trayecto.setTren_id(br.readLine());
        System.out.println("id estación salida: ");
        trayecto.setEstacion_id_salida(br.readLine());
        System.out.println("id estación llegada: ");
        trayecto.setEstacion_id_llegada(br.readLine());
        System.out.println("fecha: ");
        trayecto.setFecha(br.readLine());
        System.out.println("hora: ");
        trayecto.setHora(br.readLine());
        return dao.insertar(trayecto);
    }
    public static boolean leerEntradaPasajero (BufferedReader br, Dao dao) throws SQLException, IOException{
       Pasajero pasajero = new Pasajero();
        System.out.println("id: ");
        pasajero.setId(br.readLine());
        System.out.println("nombre: ");
        pasajero.setNombre(br.readLine());
        System.out.println("teléfono: ");
        pasajero.setTelefono(br.readLine());
        System.out.println("fecha de nacimiento: ");
        pasajero.setNacimiento(br.readLine());
        System.out.println("id del trayecto: ");
        pasajero.setTrayecto_id(br.readLine());
        return dao.insertar(pasajero);
    }
    public static boolean leerEntradaMantenimiento (BufferedReader br, Dao dao) throws SQLException, IOException{
        Mantenimiento mantenimiento = new Mantenimiento();
        System.out.println("id: ");
        mantenimiento.setId(br.readLine());
        System.out.println("id del tren: ");
        mantenimiento.setTren_id(br.readLine());
        System.out.println("id de empleado: ");
        mantenimiento.setEmpleado_id(br.readLine());
        System.out.println("fecha inicio: ");
        mantenimiento.setInicio(br.readLine());
        System.out.println("fecha fin: ");
        mantenimiento.setFin(br.readLine());
        System.out.println("descripción: ");
        mantenimiento.setDescripcion(br.readLine());
        return dao.insertar(mantenimiento);
    }
    public static boolean leerModificarTren (BufferedReader br, Dao dao) throws SQLException, IOException{
        System.out.println("introduzca el id del tren: ");
        Tren tren = (Tren) dao.buscar(br.readLine());
        System.out.println("¿Qué campo desea modificar?\n 1. Modelo\n 2. Capacidad");
        try {
            switch (Integer.parseInt(br.readLine())) {
                case 1:
                    System.out.println("Introduzca el modelo: ");
                    tren.setModelo(br.readLine());
                    break;
                case 2:
                    System.out.println("Introduzca la capacidad: ");
                    tren.setCapacidad(Integer.parseInt(br.readLine()));
                    break;
                default:
                    System.out.println("la opción introducida no es correcta");
                    break;
            }
        }catch (NumberFormatException ex){
            System.out.println("el valor introducido no es un número");
        }
        return dao.modificar(tren);
    }
    public static boolean leerModificarEstacion (BufferedReader br, Dao dao) throws SQLException, IOException{
        System.out.println("introduzca el id de la estación: ");
        Estacion estacion = (Estacion) dao.buscar(br.readLine());
        System.out.println("¿Qué campo desea modificar?\n 1. nombre\n 2. ciudad");
        try {
            switch (Integer.parseInt(br.readLine())) {
                case 1:
                    System.out.println("Introduzca el nombre: ");
                    estacion.setNombre(br.readLine());
                    break;
                case 2:
                    System.out.println("Introduzca la ciudad: ");
                    estacion.setCiudad(br.readLine());
                    break;
                default:
                    System.out.println("El valor introducido no es correcto");
                    break;
            }
        }catch (NumberFormatException ex){
            System.out.println("el valor introducido no es un número");
        }
        return dao.modificar(estacion);
    }
    public static boolean leerModificarEmpleado (BufferedReader br, Dao dao) throws SQLException, IOException{
        System.out.println("introduzca el id del empleado: ");
        Empleado empleado = (Empleado) dao.buscar(br.readLine());
        System.out.println("¿Qué campo desea modificar?\n 1. nombre\n 2. ciudad\n 3. fecha contrato\n 4. estación");
        try {
            switch (Integer.parseInt(br.readLine())) {
                case 1:
                    System.out.println("Introduzca el nombre: ");
                    empleado.setNombre(br.readLine());
                    break;
                case 2:
                    System.out.println("Introduzca la ciudad: ");
                    empleado.setPuesto(br.readLine());
                    break;
                case 3:
                    System.out.println("introduzca la fecha de contratación");
                    empleado.setContratado(br.readLine());
                    break;
                case 4:
                    System.out.println("introduzca el id de la estación:");
                    empleado.setEstacion_id(br.readLine());
                    break;
                default:
                    System.out.println("El valor introducido no es correcto");
                    break;
            }
        }catch (NumberFormatException ex){
            System.out.println("el valor introducido no es un número");
        }
        return dao.modificar(empleado);
    }
    public static boolean leerModificarTrayecto (BufferedReader br, Dao dao) throws SQLException, IOException{
        System.out.println("introduzca el id del trayecto: ");
        Trayecto trayecto = (Trayecto) dao.buscar(br.readLine());
        System.out.println("¿Qué campo desea modificar?\n 1. tren\n 2. Estación salida\n 3. Estación llegada\n 4. fecha\n 5.hora");
        try {
            switch (Integer.parseInt(br.readLine())) {
                case 1:
                    System.out.println("introduzca el id del tren:");
                    trayecto.setTren_id(br.readLine());
                    break;
                case 2:
                    System.out.println("Introduzca el id de la estación de salida: ");
                    trayecto.setEstacion_id_salida(br.readLine());
                    break;
                case 3:
                    System.out.println("Introduzca el id de la estación de llegada: ");
                    trayecto.setEstacion_id_llegada(br.readLine());
                    break;
                case 4:
                    System.out.println("Introduzca la fecha: ");
                    trayecto.setFecha(br.readLine());
                    break;
                case 5:
                    System.out.println("introduzca la hora de salida: ");
                    trayecto.setHora(br.readLine());
                    break;
                default:
                    System.out.println("la opción introducida no es correcta");
                    break;
            }
        }catch (NumberFormatException ex){
            System.out.println("el valor introducido no es un número");
        }
        return dao.modificar(trayecto);
    }
    public static boolean leerModificarPasajero (BufferedReader br, Dao dao) throws SQLException, IOException{
        System.out.println("introduzca el id del psajero: ");
        Pasajero pasajero = (Pasajero) dao.buscar(br.readLine());
        System.out.println("¿Qué campo desea modificar?\n 1. nombre\n 2. teléfono\n 3. fecha de nacimiento\n 4. trayecto");
       try {
           switch (Integer.parseInt(br.readLine())) {
               case 1:
                   System.out.println("introduzca el nombre: ");
                   pasajero.setNombre(br.readLine());
                   break;
               case 2:
                   System.out.println("introduzca el teléfono: ");
                   pasajero.setTelefono(br.readLine());
                   break;
               case 3:
                   System.out.println("Introduzca la fecha de nacimiento: ");
                   pasajero.setNacimiento(br.readLine());
                   break;
               case 4:
                   System.out.println("Introduzca el id del trayecto: ");
                   pasajero.setTrayecto_id(br.readLine());
                   break;
               default:
                   System.out.println("la opción introducida no es correcta");
                   break;
           }
       }catch (NumberFormatException ex){
           System.out.println("el valor introducido no es un número");
       }
        return dao.modificar(pasajero);
    }
    public static boolean leerModificarMantenimiento (BufferedReader br, Dao dao) throws SQLException, IOException{
        System.out.println("introduzca el id del servicio de mantenimiento: ");
        Mantenimiento mantenimiento = (Mantenimiento) dao.buscar(br.readLine());
        System.out.println("¿Qué campo desea modificar?\n 1. Tren\n 2. Empleado\n 3. fecha inicio\n 4. fecha fin\n 5. descripción");
        try {
            switch (Integer.parseInt(br.readLine())) {
                case 1:
                    System.out.println("introduzca el id del tren: ");
                    mantenimiento.setTren_id(br.readLine());
                    break;
                case 2:
                    System.out.println("introduzca el id del empleado: ");
                    mantenimiento.setEmpleado_id(br.readLine());
                    break;
                case 3:
                    System.out.println("introduzca la fecha de inicio: ");
                    mantenimiento.setInicio(br.readLine());
                    break;
                case 4:
                    System.out.println("introduzca la fecha de fin: ");
                    mantenimiento.setFin(br.readLine());
                    break;
                case 5:
                    System.out.println("introduzca la descripción: ");
                    mantenimiento.setDescripcion(br.readLine());
                    break;
                default:
                    System.out.println("la opción introducida no es correcta");
                    break;
            }
        }catch (NumberFormatException ex){
            System.out.println("el valor introducido no es un número");
        }
        return dao.modificar(mantenimiento);
    }
}
