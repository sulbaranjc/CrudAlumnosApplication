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
        calcularNotas(alumno); // 🔥 Cálculo antes de guardar
        alumnos.add(alumno);
    }

    @Override
    public void actualizar(Alumno alumno) {
        eliminar(alumno.getId());
        calcularNotas(alumno); // 🔥 Recalcular antes de actualizar
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

    private void calcularNotas(Alumno alumno) {
        // Suponiendo que la nota final es un 30% y las tres notas son el 70%
        double promedioNotas = (alumno.getNota1() + alumno.getNota2() + alumno.getNota3()) / 3;
        double promedioFinal = (promedioNotas * 0.7) + (alumno.getNotaFinal() * 0.3);
        alumno.setPromedioFinal(promedioFinal);
    }
}
