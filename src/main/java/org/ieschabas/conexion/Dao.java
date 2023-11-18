package org.ieschabas.conexion;

import java.sql.*;
import java.util.ArrayList;

public abstract class Dao {
    //Conexión con la base de datos:
    
    /**
     * Constructor de la superclase abstracta gestor:
     */
    public Dao(){}
    public Statement conectar() throws SQLException {
        //Carga el controlador (opcional para versiones recientes:
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        //Se crea la conexión:
        conexion = DriverManager.getConnection(HOST, NOMBRE, CONTRASENYA);
        //Se selecciona la base de datos, se puede hacer con el USE de sql o también con:
        conexion.setCatalog("ferrocarril");
        //Se crea un Statement que sirve para comunicarse con la BD y enviarle instrucciones:
        statement = conexion.createStatement();
        //Los Statements (o extractos) pueden ser de lectura (consultas) con executeQuery()
        // o de sentencias DML de escritura con executeUpdate() (insertar, modificar o eliminar), así como DDL o DCL.
        return statement;
    }
    public ResultSet leerBaseDatos(String instruccion) throws SQLException{
        //En el caso de las consultas, el statement devuelve un objeto de tipo ResultSet, que sería un objeto como de tipo consulta:
        consulta = conectar().executeQuery(instruccion);
        return consulta;
    }

    public void escribirBaseDatos(String instruccion) throws SQLException{
        conectar().executeUpdate(instruccion);
    }
    public void closeQuery() throws SQLException {
        consulta.close();
    }
    public void desconectar() throws SQLException {
        statement.close();
        conexion.close();
    }

    //////////////////////////////////////Métodos abstractos////////////////////////////////////////////////////////////////

    /**
     * Método para crear una tabla en la base de datos
     * SQL: sentencia CREATE TABLE
     * @author Antonio Mas Esteve
     * @return boolean
     * @throws SQLException Error de ejecución SQL
     */
    public abstract boolean crearTabla() throws SQLException;

    /**
     * Método que elimina una tabla de la base de datos seleccionada
     * SQL: sentencia DROP TABLE
     * @author Antonio Mas Esteve
     * @return boolean
     * @throws SQLException Error de ejecución SQL
     */
    public abstract boolean eliminarTabla() throws SQLException;
    /**
     * Método para listar las distintas tablas de las bases de datos y almacenarlas en una lista.
     * Como no se necesitan restricciones, usamos ArrayList.
     * SQL: sentencia SELECT
     * @author Antonio Mas Esteve
     * @return ArrayList
     * @throws SQLException Error de ejecución SQL
     */
    public abstract ArrayList<?> listar() throws SQLException;

    /**
     * Busca un registro concreto en una tabla cualquiera y devuelve su objeto correspondiente.
     * SQL: sentencia: SELECT
     * @author Antonio Mas Esteve
     * @return Object
     * @throws SQLException Error de ejecución SQL
     */
    public abstract Object buscar(String id) throws SQLException;

    /**
     * Método para insertar objetos en la base de datos. Devuelve si se ha hecho bien o no
     * @author Antonio Mas Esteve
     * SQL sentencia INSERT
     * @return boolean
     * @throws SQLException Error de ejecución SQL
     */
    public abstract boolean insertar(Object obj) throws SQLException;

    /**
     * Método para modificar un registro de la tabla. Devuelve si está hecho o no
     * @author Antonio Mas Esteve
     * SQL sentencia UPDATE
     * @return boolean
     * @throws SQLException Error de ejecución SQL
     */
    public abstract boolean modificar(Object obj) throws SQLException;

    /**
     * Método para eliminar un registro según si Id.
     * SQL sentencia DELETE
     * @return boolean
     * @throws SQLException Error de ejecución SQL
     */
    public abstract boolean eliminar(String id) throws SQLException;




}
