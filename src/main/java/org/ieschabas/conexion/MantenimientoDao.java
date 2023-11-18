package org.ieschabas.conexion;

import org.ieschabas.models.Mantenimiento;

import java.sql.SQLException;
import java.util.ArrayList;

public final class MantenimientoDao extends Dao{
    @Override
    public boolean crearTabla() throws SQLException {
        //Añadimos la sentencia:
        String sentencia = "CREATE TABLE IF NOT EXISTS mantenimiento (" +
                "id VARCHAR(50) PRIMARY KEY," +
                "tren_id VARCHAR(50)," +
                "empleado_id VARCHAR(50)," +
                "inicio VARCHAR(50),"+
                "fin VARCHAR(50)," +
                "descripcion TEXT,"+
                "FOREIGN KEY (tren_id) REFERENCES tren(id),"+
                "FOREIGN KEY (empleado_id) REFERENCES empleado(id)"+
                ");";
        //Añadimos la operación de escritura (actualización) en la BD:
        escribirBaseDatos(sentencia);
        return true;
    }

    @Override
    public boolean eliminarTabla() throws SQLException {
        String sentencia = "DROP TABLE IF EXISTS mantenimiento;";
        escribirBaseDatos(sentencia);
        return true;
    }

    @Override
    public ArrayList<Mantenimiento> listar() throws SQLException {
        ArrayList<Mantenimiento> lista = new ArrayList<>();
        //El Result Set nos devuelve un registro, para obtenerlos todos, debe ser iterado:
        //Se instancian los campos según su tipo de dato:
        consulta = leerBaseDatos("SELECT * FROM mantenimiento");
        Mantenimiento mantenimiento;
        while(consulta.next()) {
            //Se crea el objeto con los datos de la consulta, según su tipo.
            mantenimiento = new Mantenimiento(
                    consulta.getString("id"),
                    consulta.getString("tren_id"),
                    consulta.getString("empleado_id"),
                    consulta.getString("inicio"),
                    consulta.getString("fin"),
                    consulta.getString("descripcion")
            );
            lista.add(mantenimiento);
        }
        //Cerramos consulta
        closeQuery();
        //desconectamos
        desconectar();
        return lista;
    }

    @Override
    public Mantenimiento buscar(String id) throws SQLException {
        String sentencia = "SELECT * FROM mantenimiento WHERE id = \""+id+"\";";
        consulta = leerBaseDatos(sentencia);
        consulta.next(); //Toma el primer elemento
        Mantenimiento mantenimiento = new Mantenimiento(
                consulta.getString("id"),
                consulta.getString("tren_id"),
                consulta.getString("empleado_id"),
                consulta.getString("inicio"),
                consulta.getString("fin"),
                consulta.getString("descripcion")
        );
        closeQuery();
        desconectar();
        return mantenimiento;
    }

    @Override
    public boolean insertar(Object obj) throws SQLException {
        //Se puede también instanciar directamente el objeto en el instance of:
        if(obj instanceof Mantenimiento mantenimiento){
            String sentencia = "INSERT INTO mantenimiento (id, tren_id, empleado_id, inicio, fin, descripcion) VALUES ("
                    +"'"+mantenimiento.getId()+"',"
                    +"'"+mantenimiento.getTren_id()+"',"
                    +"'"+mantenimiento.getEmpleado_id()+"',"
                    +"'"+mantenimiento.getInicio()+"',"
                    +"'"+mantenimiento.getFin()+"',"
                    +"'"+mantenimiento.getDescripcion()+"'"
                    +");";
            escribirBaseDatos(sentencia);
            desconectar();
            return true; //Si se realiza correctamente devuelve true
        }
        return false;
    }

    @Override
    public boolean modificar(Object obj) throws SQLException {
        if(obj instanceof Mantenimiento mantenimiento){
            String sentencia = "UPDATE mantenimiento SET"
                    +" id = "+"'"+mantenimiento.getId()+"',"
                    +" tren_id = "+"'"+mantenimiento.getTren_id()+"',"
                    +" empleado_id = "+"'"+mantenimiento.getEmpleado_id()+"',"
                    +" inicio = "+"'"+mantenimiento.getInicio()+"',"
                    +" fin = "+"'"+mantenimiento.getFin()+"',"
                    +" descripcion = "+"'"+mantenimiento.getDescripcion()+"'"
                    +" WHERE id = \""+mantenimiento.getId()+"\";";
            escribirBaseDatos(sentencia);
            desconectar();
            return true; //Si se realiza correctamente devuelve true
        }
        return false;
    }

    @Override
    public boolean eliminar(String id) throws SQLException {
        //Elimina el registro (Se eliminarán en cascada los trayectos que estén relacionados):
        String sentencia = "DELETE FROM mantenimiento WHERE id = \""+id+"\";";
        escribirBaseDatos(sentencia);
        desconectar();
        return true;
    }
}
