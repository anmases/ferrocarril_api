package org.ieschabas.conexion;

import org.ieschabas.models.Estacion;
import org.ieschabas.models.Trayecto;
import org.ieschabas.models.Tren;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TrayectoDaoTest {
    final Dao<Tren> daoTren = new Dao<>(Tren.class);
    final Dao<Estacion> daoEstacion = new Dao<>(Estacion.class);
    final Dao<Trayecto> daoTrayecto = new Dao<>(Trayecto.class);
    @Test
    void insertar() {
        Trayecto trayecto = new Trayecto(daoTren.buscar("4"), daoEstacion.buscar("2"), daoEstacion.buscar("5"), "2/2/2022", "10:00");
        boolean check = daoTrayecto.insertar(trayecto);
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
    }
}