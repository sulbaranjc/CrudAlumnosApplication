package com.personal.CrudAlumnosApplication.service;

import com.personal.CrudAlumnosApplication.model.Alumno;
import com.personal.CrudAlumnosApplication.repository.AlumnoDAO;
import java.util.List;

public class AlumnoService {
    private final AlumnoDAO alumnoDAO = new AlumnoDAO();

    public void guardarAlumno(Alumno alumno) {
        alumno.setPromedioFinal(alumno.calcularPromedio());
        alumnoDAO.guardar(alumno);
    }

    public void actualizarAlumno(Alumno alumno) {
        alumno.setPromedioFinal(alumno.calcularPromedio());
        alumnoDAO.actualizar(alumno);
    }

    public void eliminarAlumno(int id) {
        alumnoDAO.eliminar(id);
    }

    public Alumno obtenerAlumnoPorId(int id) {
        return alumnoDAO.obtenerPorId(id);
    }

    public List<Alumno> listarTodosLosAlumnos() {
        return alumnoDAO.listarTodos();
    }
}
