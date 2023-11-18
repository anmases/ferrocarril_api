package org.ieschabas;

import org.ieschabas.conexion.*;
import org.ieschabas.enumerados.Accion;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;



public class Main {
    private final EmpleadoDao empleadoDao;
    private final EstacionDao estacionDao;
    private final MantenimientoDao mantenimientoDao;
    private final PasajeroDao pasajeroDao;
    private final TrayectoDao trayectoDao;
    private final TrenDao trenDao;
    public Main(){
        //Inicializamos todos los campos dentro del constructor:
        empleadoDao = new EmpleadoDao();
        estacionDao = new EstacionDao();
        mantenimientoDao = new MantenimientoDao();
        pasajeroDao = new PasajeroDao();
        trayectoDao = new TrayectoDao();
        trenDao = new TrenDao();
    }

    /**
     * Punto de entrada en el programa.
     * @author Antonio Mas Esteve
     * @param args
     */
    public static void main(String[] args) {
        try {
            Main main = new Main();
            main.runApp();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * Inicio de la aplicación
     * @author Antonio Mas Esteve
     * @throws IOException Error de lectura-escritura
     */
    public void runApp() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int opcion = 0;
       do {
           System.out.println("-------------------------FERROCARRILES DEL OESTE--------------------------\n" +
                   "Introduzca el número de la opción:\n" +
                   "1. Crear tabla.\n" +
                   "2. Eliminar tabla.\n" +
                   "3. Listar tabla\n" +
                   "4. Insertar registro.\n" +
                   "5. Modificar regisro.\n" +
                   "6. Eliminar registro\n" +
                   "7. Salir");
           try {
               opcion = Integer.parseInt(br.readLine());
           }catch(NumberFormatException ex){
               System.out.println("El valor introducido no es un número");
           }
           switch (opcion) {
               case 1:
                   if(dataDefinitionAction(br, Accion.crear)){
                       System.out.println("tabla creada correctamente");
                   }else{
                       System.out.println("la tabla no se creó");
                   }
                   break;
               case 2:
                   System.out.println("******ATENCION: Debe eliminar antes las tablas que tengan claves foráneas********************** ");
                   if(dataDefinitionAction(br, Accion.eliminar)){
                       System.out.println("Tabla eliminada correctamente");
                   }else{
                       System.out.println("La tabla no fue eliminada");
                   }
                   break;
               case 3:
                   if(dataDefinitionAction(br, Accion.listar)){
                       System.out.println("Tabla listada correctamente");
                   }else{
                       System.out.println("La tabla no fue listada");
                   }
                   break;
               case 4:
                   if(dataDefinitionAction(br, Accion.insertar)){
                       System.out.println("registro creado correctamente");
                   }else{
                       System.out.println("el registro no fue creado");
                   }
                   break;
               case 5:
                   if(dataDefinitionAction(br, Accion.actualizar)){
                       System.out.println("registro actualizado correctamente");
                   }else{
                       System.out.println("el registro no fue actualizado");
                   }
                   break;
               case 6:
                   System.out.println("******ATENCION: Si elimina un registro, sus registros asociados se borrarán********************** ");
                   if(dataDefinitionAction(br, Accion.borrar)){
                       System.out.println("registro borrado correctamente");
                   }else{
                       System.out.println("el registro no fue borrado");
                   }
                   break;
               case 7:
                   System.out.println("Hasta luego!");
                   break;
               default:
                   System.out.println("La opción seleccionada no está disponible");
                   break;
           }
       }while(opcion != 7);
    }
    ///////////////////////////////////////////////////////////////////////////////////////
    public boolean dataDefinitionAction(BufferedReader br, Accion accion) throws IOException {
        int opcion = 0;
        System.out.println("¿Qué desea "+accion+"?");
        System.out.println(" 1. Tren\n 2. Estación\n 3. Empleado\n 4. Trayecto\n 5. Pasajero\n 6. Mantenimiento\n 7. Atrás");
        try {
             opcion = Integer.parseInt(br.readLine());
        }catch (Exception e){
            System.out.println("La opción introducida no es un número");
        }
        try {
            switch (opcion) {
                case 1:
                    return EntradaUsuario.menuTren(br, trenDao, accion);
                case 2:
                   return EntradaUsuario.menuEstacion(br, estacionDao, accion);
                case 3:
                    return EntradaUsuario.menuEmpleado(br, empleadoDao, accion);
                case 4:
                    return EntradaUsuario.menuTrayecto(br, trayectoDao, accion);
                case 5:
                   return EntradaUsuario.menuPasajero(br, pasajeroDao, accion);
                case 6:
                  return EntradaUsuario.menuMantenimiento(br, mantenimientoDao, accion);
                case 7:
                    return false;
                default:
                    System.out.println("La opción introducida no es correcta");
                    break;
            }
        }catch (SQLException sql){
            System.out.println("no se pudo "+ accion);
            System.out.println(sql.getMessage());
            return false;
        }
        return false;
    }



}