package org.ieschabas.conexion;

import org.ieschabas.models.Pasajero;
import org.ieschabas.models.Trayecto;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PasajeroDaoTest {
    final Dao<Trayecto> daoTrayecto = new Dao<>(Trayecto.class);
    final Dao<Pasajero> daoPasajero = new Dao<>(Pasajero.class);
    @Test
    void insertar() {
        List<Trayecto> trayectos = new ArrayList<>();
        trayectos.add(daoTrayecto.buscar("1"));
        trayectos.add(daoTrayecto.buscar("2"));
        trayectos.add(daoTrayecto.buscar("3"));
        Pasajero pasajero = new Pasajero("Manuel", "69679876", "23/22/1980", trayectos);
        boolean check = daoPasajero.insertar(pasajero);
        assertTrue(check);
    }

    @Test
    void modificar() {
    }

    @Test
    void buscar() {
    }

    @Test
    void eliminar() {
    }

    @Test
    void listar() {
        List<Pasajero> pasajeros = daoPasajero.listar();
        for(Pasajero pasajero: pasajeros){
            System.out.println(pasajero.toString());
        }
        assertEquals(pasajeros.size(), 1);
    }
}