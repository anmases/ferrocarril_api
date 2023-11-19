package org.ieschabas.conexion;

import org.ieschabas.models.Empleado;
import org.ieschabas.models.Estacion;
import org.ieschabas.models.Mantenimiento;
import org.ieschabas.models.Tren;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MantenimientoDaoTest {
final Dao<Mantenimiento> daoMantenimiento = new Dao<>(Mantenimiento.class);
final Dao<Tren> daoTren = new Dao<>(Tren.class);
final Dao<Empleado> daoEmpleado = new Dao<>(Empleado.class);
    @Test
    void insertar() {
        Mantenimiento mantenimiento = new Mantenimiento(daoTren.buscar("3"), daoEmpleado.buscar("2"), "2/2/2022", "2/2/2022", "Supervisión");
        boolean check = daoMantenimiento.insertar(mantenimiento);
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