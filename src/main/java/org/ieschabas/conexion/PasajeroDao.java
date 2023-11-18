package org.ieschabas.conexion;

import org.ieschabas.models.Pasajero;

import java.sql.SQLException;
import java.util.ArrayList;

public final class PasajeroDao extends Dao{
    @Override
    public boolean crearTabla() throws SQLException {
        //Añadimos la sentencia:
        String sentencia = "CREATE TABLE IF NOT EXISTS pasajero (" +
                "id VARCHAR(50) PRIMARY KEY," +
                "nombre VARCHAR(50)," +
                "telefono VARCHAR(50)," +
                "nacimiento VARCHAR(50),"+
                "trayecto_id VARCHAR(50)," +
                "FOREIGN KEY (trayecto_id) REFERENCES trayecto(id)"+
                ");";
        //Añadimos la operación de escritura (actualización) en la BD:
        escribirBaseDatos(sentencia);
        return true;
    }

    @Override
    public boolean eliminarTabla() throws SQLException {
        String sentencia = "DROP TABLE IF EXISTS pasajero;";
        escribirBaseDatos(sentencia);
        return true;
    }

    @Override
    public ArrayList<Pasajero> listar() throws SQLException {
        ArrayList<Pasajero> lista = new ArrayList<>();
        //El Result Set nos devuelve un registro, para obtenerlos todos, debe ser iterado:
        //Se instancian los campos según su tipo de dato:
        consulta = leerBaseDatos("SELECT * FROM pasajero");
        Pasajero pasajero;
        while(consulta.next()) {
            //Se crea el objeto con los datos de la consulta, según su tipo.
            pasajero = new Pasajero(
                    consulta.getString("id"),
                    consulta.getString("nombre"),
                    consulta.getString("telefono"),
                    consulta.getString("nacimiento"),
                    consulta.getString("trayecto_id")
            );
            lista.add(pasajero);
        }
        //Cerramos consulta
        closeQuery();
        //desconectamos
        desconectar();
        return lista;
    }

    @Override
    public Pasajero buscar(String id) throws SQLException {
        String sentencia = "SELECT * FROM pasajero WHERE id = \""+id+"\";";
        consulta = leerBaseDatos(sentencia);
        consulta.next(); //Toma el primer elemento
        Pasajero pasajero = new Pasajero(
                consulta.getString("id"),
                consulta.getString("nombre"),
                consulta.getString("telefono"),
                consulta.getString("nacimiento"),
                consulta.getString("trayecto_id")
        );
        closeQuery();
        desconectar();
        return pasajero;
    }

    @Override
    public boolean insertar(Object obj) throws SQLException {
        if(obj instanceof Pasajero){
            Pasajero pasajero = (Pasajero) obj;
            String sentencia = "INSERT INTO pasajero (id, nombre, telefono, nacimiento, trayecto_id) VALUES ("
                    +"'"+pasajero.getId()+"',"
                    +"'"+pasajero.getNombre()+"',"
                    +"'"+pasajero.getTelefono()+"',"
                    +"'"+pasajero.getNacimiento()+"',"
                    +"'"+pasajero.getTrayecto_id()+"'"
                    +");";
            escribirBaseDatos(sentencia);
            desconectar();
            return true; //Si se realiza correctamente devuelve true
        }
        return false;
    }

    @Override
    public boolean modificar(Object obj) throws SQLException {
        if (obj instanceof Pasajero) {
            Pasajero pasajero = (Pasajero) obj;
            String sentencia = "UPDATE pasajero SET"
                    + " id = " + "'" + pasajero.getId() + "',"
                    + " nombre = " + "'" + pasajero.getNombre() + "',"
                    + " telefono = " + "'" + pasajero.getTelefono() + "',"
                    + " nacimiento = " + "'" + pasajero.getNacimiento() + "',"
                    + " trayecto_id = " + "'" + pasajero.getTrayecto_id() + "'"
                    + " WHERE id = \""+pasajero.getId()+"\";";
            escribirBaseDatos(sentencia);
            desconectar();
        }
        return true; //Si se realiza correctamente devuelve true
    }

    @Override
    public boolean eliminar(String id) throws SQLException {
        //Elimina el registro (Se eliminarán en cascada los trayectos que estén relacionados):
        String sentencia = "DELETE FROM pasajero WHERE id = \""+id+"\";";
        escribirBaseDatos(sentencia);
        desconectar();
        return true;
    }
}
