package org.ieschabas.conexion;

import org.ieschabas.models.Tren;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TrenDaoTest {
final Dao<Tren> daoTren = new Dao<>(Tren.class);
    @Test
    void insertar() {
      Tren tren = new Tren("Bombardier", 100);
     boolean check = daoTren.insertar(tren);
     assertTrue(check);
    }

    @Test
    void modificar() {
        Tren tren = new Tren();
        tren.setId(3);
        tren.setModelo("cercanias");
        boolean check = daoTren.insertar(tren);
        assertTrue(check);
    }

    @Test
    void buscar() {
        Tren tren = daoTren.buscar("3");
        System.out.println(tren.toString());
        assertEquals(tren.getModelo(), "Euromed");
        assertEquals(tren.getCapacidad(), 450);
    }

    @Test
    void listar() {
        List<Tren> lista = daoTren.listar();
        for(Tren tren: lista){
            System.out.println(tren.toString());
        }
        assertEquals(lista.size(), 1);
        assertEquals(lista.get(0).getId(),1);
    }
    @Test
    void eliminar() {
        boolean check = daoTren.eliminar("1");
        assertTrue(check);
    }

}