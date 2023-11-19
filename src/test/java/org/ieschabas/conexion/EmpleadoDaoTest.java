package org.ieschabas.conexion;

import org.ieschabas.models.Empleado;
import org.ieschabas.models.Estacion;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class EmpleadoDaoTest {
Dao<Empleado> daoEmpleado = new Dao<>(Empleado.class);
    @Test
    void insertar() {
        Dao<Estacion> estacionDao = new Dao<>(Estacion.class);
        Estacion estacion = estacionDao.buscar("2");
        Empleado obj = new Empleado("Antonio", "Encargado", "2/2/22", estacion);
        boolean check = daoEmpleado.insertar(obj);
        assertTrue(check);
    }

    @Test
    void modificar() {

    }

    @Test
    void buscar() {
        Empleado obj = daoEmpleado.buscar("1");
        assertEquals(obj.getPuesto(), "Encargado");
    }

    @Test
    void listar() {
        List<Empleado> lista = daoEmpleado.listar();
        assertEquals(lista.size(), 1);
        assertEquals(lista.get(0).getId(),1);
    }

    @Test
    void eliminar() {
        boolean check = daoEmpleado.eliminar("1");
        assertTrue(check);
    }
}