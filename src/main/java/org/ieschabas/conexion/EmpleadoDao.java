package org.ieschabas.conexion;

import org.ieschabas.models.Empleado;

import java.sql.SQLException;
import java.util.ArrayList;

public final class EmpleadoDao extends Dao{
    @Override
    public boolean crearTabla() throws SQLException {
        //Añadimos la sentencia:
        String sentencia = "CREATE TABLE IF NOT EXISTS empleado (" +
                "id VARCHAR(50) PRIMARY KEY," +
                "nombre VARCHAR(50)," +
                "puesto VARCHAR(50)," +
                "contratado VARCHAR(50),"+
                "estacion_id VARCHAR(50)," +
                "FOREIGN KEY (estacion_id) REFERENCES estacion(id)"+
                ");";
        //Añadimos la operación de escritura (actualización) en la BD:
        escribirBaseDatos(sentencia);
        return true;
    }

    @Override
    public boolean eliminarTabla() throws SQLException {
        String sentencia = "DROP TABLE IF EXISTS empleado;";
        escribirBaseDatos(sentencia);
        return true;
    }

    @Override
    public ArrayList<Empleado> listar() throws SQLException {
        ArrayList<Empleado> lista = new ArrayList<>();
        //El Result Set nos devuelve un registro, para obtenerlos todos, debe ser iterado:
        //Se instancian los campos según su tipo de dato:
        consulta = leerBaseDatos("SELECT * FROM empleado");
        Empleado empleado;
        while(consulta.next()){
            //Se crea el objeto con los datos de la consulta, según su tipo
            empleado = new Empleado(
                    consulta.getString("id"),
                    consulta.getString("nombre"),
                    consulta.getString("puesto"),
                    consulta.getString("contratado"),
                    consulta.getString("estacion_id")
            );
            lista.add(empleado);
        }
        //Cerramos consulta
        closeQuery();
        //desconectamos
        desconectar();
        return lista;
    }

    @Override
    public Empleado buscar(String id) throws SQLException {
        String sentencia = "SELECT * FROM empleado WHERE id = \""+id+"\";";
        consulta = leerBaseDatos(sentencia);
        consulta.next(); //Toma el primer elemento
        Empleado empleado = new Empleado(
                consulta.getString("id"),
                consulta.getString("nombre"),
                consulta.getString("puesto"),
                consulta.getString("contratado"),
                consulta.getString("estacion_id")
        );
        closeQuery();
        desconectar();
        return empleado;
    }

    @Override
    public boolean insertar(Object obj) throws SQLException {
        //Como el objeto puede ser genérico, comprobamos que se trata de un trayecto
        if(obj instanceof  Empleado){
            Empleado empleado = (Empleado) obj;
            //Creamos la sentencia a partir de los campos del objeto (solo hay que colocarle comillas a los que son de tipo String)
            String sentencia = "INSERT INTO empleado (id, nombre, puesto, contratado, estacion_id) VALUES ("
                    +"'"+empleado.getId()+"',"
                    +"'"+empleado.getNombre()+"',"
                    +"'"+empleado.getPuesto()+"',"
                    +"'"+empleado.getContratado()+"',"
                    +"'"+empleado.getEstacion_id()+"'"
                    +");";
            escribirBaseDatos(sentencia);
            desconectar();
            return true; //Si se realiza correctamente devuelve true
        }
        return false;
    }

    @Override
    public boolean modificar(Object obj) throws SQLException {
        if(obj instanceof  Empleado){
            Empleado empleado = (Empleado) obj;
            //Creamos la sentencia a partir de los campos del objeto (solo hay que colocarle comillas a los que son de tipo String)
            String sentencia = "UPDATE empleado SET"+
                    " nombre = "+"'"+empleado.getNombre()+"',"+
                    " puesto = "+"'"+empleado.getPuesto()+"',"+
                    " contratado = "+"'"+empleado.getContratado()+"',"+
                    " estacion_id = "+"'"+empleado.getEstacion_id()+"'"+
                    "WHERE id = \""+empleado.getId()+"\" ;";
            escribirBaseDatos(sentencia);
            desconectar();
            return true; //Si se realiza correctamente devuelve true
        }
        return false;
    }

    @Override
    public boolean eliminar(String id) throws SQLException {
        //Elimina el registro (Se eliminarán en cascada los trayectos que estén relacionados):
        String sentencia = "DELETE FROM empleado WHERE id = \""+id+"\";";
        escribirBaseDatos(sentencia);
        desconectar();
        return true;
    }
}
