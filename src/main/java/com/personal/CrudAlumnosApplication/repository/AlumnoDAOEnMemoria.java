package com.personal.CrudAlumnosApplication.repository;

import com.personal.CrudAlumnosApplication.model.Alumno;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class AlumnoDAOEnMemoria implements AlumnoDAO {

    private final List<Alumno> alumnos = new ArrayList<>();
    private final AtomicInteger contadorId = new AtomicInteger(1);

    @Override
    public void guardar(Alumno alumno) {
        alumno.setId(contadorId.getAndIncrement());
        alumno.setPromedioFinal(alumno.calcularPromedio()); // 🔥 Usamos el cálculo del modelo
        alumnos.add(alumno);
    }

    @Override
    public void actualizar(Alumno alumno) {
        eliminar(alumno.getId());
        alumno.setPromedioFinal(alumno.calcularPromedio()); // 🔥 Recalcular antes de actualizar
        alumnos.add(alumno);
    }

    @Override
    public void eliminar(int id) {
        alumnos.removeIf(a -> a.getId() == id);
    }

    @Override
    public Alumno obtenerPorId(int id) {
        return alumnos.stream().filter(a -> a.getId() == id).findFirst().orElse(null);
    }

    @Override
    public List<Alumno> listarTodos() {
        return new ArrayList<>(alumnos);
    }
}
