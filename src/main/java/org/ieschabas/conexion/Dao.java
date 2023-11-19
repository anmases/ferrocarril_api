package org.ieschabas.conexion;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Dao <T> {
    private final Class<T> clase;
    //Conexión con la base de datos:e
    private static final Configuration cfg = new Configuration().configure("hibernate.cfg.xml");
    private final SessionFactory sf;
    private Session session;

    /**
     * Constructor de la superclase abstracta gestor:
     */
    public Dao(Class<T> clase) {
        this.clase = clase;
        sf = cfg.buildSessionFactory();
    }

    private void setUp(){
        session = sf.openSession();
        session.beginTransaction();
    }
    private void dispose(){
        session.getTransaction().commit();
        session.close();
    }

    public boolean insertar(T entidad){
        setUp();
        session.merge(entidad);
        dispose();
        return true;
    }
    public T buscar(String id){
        T entidad;
        setUp();
        entidad = session.get(clase, id);
        dispose();
        return entidad;
    }
    public boolean eliminar(String id){
        //Buscamos primero la entidad.
     T entidad = buscar(id);
     setUp();
     session.remove(entidad);
     dispose();
     return true;
    }
    public List<T> listar(){
        List<T> lista = new ArrayList<>();
        setUp();
        lista = session.createQuery("FROM "+clase.getName(), clase).list();
        dispose();
        return lista;
    }

}
