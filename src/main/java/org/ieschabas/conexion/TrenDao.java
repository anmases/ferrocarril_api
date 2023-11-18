package org.ieschabas.conexion;

import org.ieschabas.models.Tren;

import java.sql.SQLException;
import java.util.ArrayList;

public final class TrenDao extends Dao{
    //No es necesario colocar javadoc en los métodos implementados, ya están definidos en la superclase DAO
    @Override
    public boolean crearTabla() throws SQLException {
        //Añadimos la sentencia:
        String sentencia = "CREATE TABLE IF NOT EXISTS tren (" +
                "id VARCHAR(50) PRIMARY KEY," +
                "modelo VARCHAR(50)," +
                "capacidad INT" +
                ");";
        //Añadimos la operación de escritura (actualización) en la BD:
        escribirBaseDatos(sentencia);
        desconectar();
        return true;
    }

    @Override
    public boolean eliminarTabla() throws SQLException {
        String sentencia = "DROP TABLE IF EXISTS tren;";
        escribirBaseDatos(sentencia);
        desconectar();
        return true;
    }

    @Override
    public ArrayList<Tren> listar() throws SQLException {
        ArrayList<Tren> lista = new ArrayList<>();
        //El Result Set nos devuelve un registro, para obtenerlos todos, debe ser iterado:
        //Se instancian los campos según su tipo de dato:
        consulta = leerBaseDatos("SELECT * FROM tren");
        Tren tren;
        while(consulta.next()){
            //Se crea el objeto con los datos de la consulta, según su tipo
            tren = new Tren(
                    consulta.getString("id"),
                    consulta.getString("modelo"),
                    consulta.getInt("capacidad")
            );
            lista.add(tren); //Añadimos el objeto a la lista
        }
        //Cerramos consulta
        closeQuery();
        //desconectamos
        desconectar();
        return lista;
    }

    @Override
    public Tren buscar(String id) throws SQLException {
        String sentencia = "SELECT * FROM tren WHERE id = \""+id+"\";";
        consulta = leerBaseDatos(sentencia);
        consulta.next(); //Toma el primer elemento
        Tren tren = new Tren(
                consulta.getString("id"),
                consulta.getString("modelo"),
                consulta.getInt("capacidad")
        );
        closeQuery();
        desconectar();
       return tren;
    }

    @Override
    public boolean insertar(Object obj) throws SQLException {
        //Como el objeto puede ser genérico, comprobamos que se trata de un Tren
        if(obj instanceof Tren){
            Tren tren = (Tren) obj;    //Si efectivamente es un tren, lo casteamos
            //Creamos la sentencia a partir de los campos del objeto (solo hay que colocarle comillas a los que son de tipo String)
            String sentencia = "INSERT INTO tren (id, modelo, capacidad) VALUES ("
                    +"'"+tren.getId()+"',"
                    +"'"+tren.getModelo()+"',"
                    +tren.getCapacidad()
                    +");";
            escribirBaseDatos(sentencia);
            desconectar();
            return true; //Si se realiza correctamente devuelve true
        }
        return false;
    }

    @Override
    public boolean modificar(Object obj) throws SQLException {
        if(obj instanceof Tren){
            Tren tren = (Tren) obj;
            String sentencia = "UPDATE tren SET"+
                    " modelo = "+"'"+tren.getModelo()+"'"+","+
                    " capacidad = "+tren.getCapacidad()+
                    " WHERE id = \""+tren.getId()+"\";";
            escribirBaseDatos(sentencia);
            desconectar();
            return true;
        }
        return false;
    }

    @Override
    public boolean eliminar(String id) throws SQLException {
        //Elimina el registro (Se eliminarán en cascada los trayectos que estén relacionados):
        String sentencia = "DELETE FROM tren WHERE id = \""+id+"\";";
        escribirBaseDatos(sentencia);
        desconectar();
        return true;
    }
}
