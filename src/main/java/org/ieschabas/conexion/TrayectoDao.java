package org.ieschabas.conexion;

import org.ieschabas.models.Trayecto;

import java.sql.SQLException;
import java.util.ArrayList;

public final class TrayectoDao extends Dao{
    @Override
    public boolean crearTabla() throws SQLException {
        //Añadimos la sentencia:
        String sentencia = "CREATE TABLE IF NOT EXISTS trayecto (" +
                "id VARCHAR(50) PRIMARY KEY," +
                "tren_id VARCHAR(50)," +
                "estacion_id_salida VARCHAR(50)," +
                "estacion_id_llegada VARCHAR(50),"+
                "fecha VARCHAR(50)," +
                "hora VARCHAR(5),"+
                "FOREIGN KEY (estacion_id_salida) REFERENCES estacion(id),"+
                "FOREIGN KEY (estacion_id_llegada) REFERENCES estacion(id),"+
                "FOREIGN KEY (tren_id) REFERENCES tren(id) ON DELETE CASCADE"+
                ");";
        //Añadimos la operación de escritura (actualización) en la BD:
        escribirBaseDatos(sentencia);
        return true;
    }

    @Override
    public boolean eliminarTabla() throws SQLException {
        String sentencia = "DROP TABLE IF EXISTS trayecto;";
        escribirBaseDatos(sentencia);
        return true;
    }

    @Override
    public ArrayList<Trayecto> listar() throws SQLException {
        ArrayList<Trayecto> lista = new ArrayList<>();
        //El Result Set nos devuelve un registro, para obtenerlos todos, debe ser iterado:
        //Se instancian los campos según su tipo de dato:
        consulta = leerBaseDatos("SELECT * FROM trayecto");
        Trayecto trayecto;
        while(consulta.next()){
            //Se crea el objeto con los datos de la consulta, según su tipo
            trayecto = new Trayecto(
                    consulta.getString("id"),
                    consulta.getString("tren_id"),
                    consulta.getString("estacion_id_salida"),
                    consulta.getString("estacion_id_llegada"),
                    consulta.getString("fecha"),
                    consulta.getString("hora")
            );
            lista.add(trayecto);
        }
        //Cerramos consulta
        closeQuery();
        //desconectamos
        desconectar();
        return lista;
    }

    @Override
    public Trayecto buscar(String id) throws SQLException {
        String sentencia = "SELECT * FROM trayecto WHERE id = \""+id+"\";";
        consulta = leerBaseDatos(sentencia);
        consulta.next(); //Toma el primer elemento
        Trayecto trayecto = new Trayecto(
                consulta.getString("id"),
                consulta.getString("tren_id"),
                consulta.getString("estacion_id_salida"),
                consulta.getString("estacion_id_llegada"),
                consulta.getString("fecha"),
                consulta.getString("hora")
        );
        closeQuery();
        desconectar();
        return trayecto;
    }

    @Override
    public boolean insertar(Object obj) throws SQLException {
        //Como el objeto puede ser genérico, comprobamos que se trata de un trayecto
        if(obj instanceof  Trayecto){
            Trayecto trayecto = (Trayecto) obj;
            //Creamos la sentencia a partir de los campos del objeto (solo hay que colocarle comillas a los que son de tipo String)
            String sentencia = "INSERT INTO trayecto (id, tren_id, estacion_id_salida, estacion_id_llegada, fecha, hora) VALUES ("
                    +"'"+trayecto.getId()+"',"
                    +"'"+trayecto.getTren_id()+"',"
                    +"'"+trayecto.getEstacion_id_salida()+"',"
                    +"'"+trayecto.getEstacion_id_llegada()+"',"
                    +"'"+trayecto.getFecha()+"',"
                    +"'"+trayecto.getHora()+"'"
                    +");";
            escribirBaseDatos(sentencia);
            desconectar();
            return true; //Si se realiza correctamente devuelve true
        }
        return false;
    }

    @Override
    public boolean modificar(Object obj) throws SQLException {
        if(obj instanceof Trayecto){
            Trayecto trayecto = (Trayecto) obj;
            String sentencia = "UPDATE trayecto SET" +
                    " tren_id = "+"'"+trayecto.getTren_id()+"'," +
                    " estacion_id_salida = "+"'"+trayecto.getEstacion_id_salida()+"'," +
                    " estacion_id_llegada = "+"'"+trayecto.getEstacion_id_llegada()+"'," +
                    " fecha = "+"'"+trayecto.getFecha()+"'," +
                    " hora = "+"'"+trayecto.getHora()+"'" +
                    " WHERE id = \""+trayecto.getId()+"\";";
            escribirBaseDatos(sentencia);
            desconectar();
            return true;
        }
        return false;
    }

    @Override
    public boolean eliminar(String id) throws SQLException {
        //Elimina el registro (Se eliminarán en cascada los trayectos que estén relacionados):
        String sentencia = "DELETE FROM trayecto WHERE id = \""+id+"\";";
        escribirBaseDatos(sentencia);
        desconectar();
        return true;
    }
}
