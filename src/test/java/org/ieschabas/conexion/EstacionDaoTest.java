package org.ieschabas.conexion;

import org.ieschabas.models.Estacion;
import org.ieschabas.models.Tren;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class EstacionDaoTest {
final Dao<Estacion> daoEstacion = new Dao<>(Estacion.class);
    @Test
    void insertar() {
        Estacion estacion = new Estacion("Norte", "Valencia");
        boolean check = daoEstacion.insertar(estacion);
        assertTrue(check);
    }

    @Test
    void modificar() {

    }

    @Test
    void buscar() {
        Estacion estacion = daoEstacion.buscar("1");
        System.out.println(estacion.toString());
        assertEquals(estacion.getCiudad(), "Madrid");
    }

    @Test
    void listar() {
        List<Estacion> lista = daoEstacion.listar();
        for(Estacion obj: lista){
            System.out.println(obj.toString());
        }
        assertEquals(lista.size(), 1);
        assertEquals(lista.get(0).getId(),1);
    }
    @Test
    void eliminar() {
        boolean check = daoEstacion.eliminar("1");
        assertTrue(check);
    }
}