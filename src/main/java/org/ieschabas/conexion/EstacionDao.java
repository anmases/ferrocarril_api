package org.ieschabas.conexion;

import org.ieschabas.models.Estacion;
import java.sql.SQLException;
import java.util.ArrayList;

public final class EstacionDao extends Dao{
    @Override
    public boolean crearTabla() throws SQLException {
        //Añadimos la sentencia:
        String sentencia = "CREATE TABLE IF NOT EXISTS estacion (" +
                "id VARCHAR(50) PRIMARY KEY," +
                "nombre VARCHAR(50)," +
                "ciudad VARCHAR(100)" +
                ");";
        //Añadimos la operación de escritura (actualización) en la BD:
        escribirBaseDatos(sentencia);
        return true;
    }

    @Override
    public boolean eliminarTabla() throws SQLException {
        String sentencia = "DROP TABLE IF EXISTS estacion;";
        escribirBaseDatos(sentencia);
        return true;
    }

    @Override
    public ArrayList<Estacion> listar() throws SQLException {
        ArrayList<Estacion> lista = new ArrayList<>();
        //El Result Set nos devuelve un registro, para obtenerlos todos, debe ser iterado:
        //Se instancian los campos según su tipo de dato:
        consulta = leerBaseDatos("SELECT * FROM estacion");
        Estacion estacion;
        while(consulta.next()){
            //Se crea el objeto con los datos de la consulta, según su tipo
            estacion = new Estacion(
                    consulta.getString("id"),
                    consulta.getString("nombre"),
                    consulta.getString("ciudad")
            );
            lista.add(estacion);
        }
        //Cerramos consulta
        closeQuery();
        //desconectamos
        desconectar();
        return lista;
    }

    @Override
    public Estacion buscar(String id) throws SQLException {
        String sentencia = "SELECT * FROM estacion WHERE id = \""+id+"\";";
        consulta = leerBaseDatos(sentencia);
        consulta.next(); //Toma el primer elemento
        Estacion estacion = new Estacion(
                consulta.getString("id"),
                consulta.getString("nombre"),
                consulta.getString("ciudad")
        );
        closeQuery();
        desconectar();
        return estacion;
    }

    @Override
    public boolean insertar(Object obj) throws SQLException {
        //Como el objeto puede ser genérico, comprobamos que se trata de una estación
        if(obj instanceof Estacion){
            Estacion estacion = (Estacion) obj;    //Si efectivamente es estación, lo casteamos
            //Creamos la sentencia a partir de los campos del objeto (solo hay que colocarle comillas a los que son de tipo String)
            String sentencia = "INSERT INTO estacion (id, nombre, ciudad) VALUES ("
                    +"'"+estacion.getId()+"',"
                    +"'"+estacion.getNombre()+"',"
                    +"'"+estacion.getCiudad()+"'"
                    +");";
            escribirBaseDatos(sentencia);
            desconectar();
            return true; //Si se realiza correctamente devuelve true
        }
        return false;
    }

    @Override
    public boolean modificar(Object obj) throws SQLException {
        if(obj instanceof Estacion){
            Estacion estacion = (Estacion) obj;
            String sentencia = "UPDATE estacion SET"+
                    " nombre = "+"'"+estacion.getNombre()+"'"+","+
                    " ciudad = "+"'"+estacion.getCiudad()+"'"+
                    " WHERE id = \""+estacion.getId()+"\";";
            escribirBaseDatos(sentencia);
            desconectar();
            return true;
        }
        return false;
    }

    @Override
    public boolean eliminar(String id) throws SQLException {
        //Elimina el registro (Se eliminarán en cascada los trayectos que estén relacionados):
        String sentencia = "DELETE FROM estacion WHERE id = \""+id+"\";";
        escribirBaseDatos(sentencia);
        desconectar();
        return true;
    }
}

