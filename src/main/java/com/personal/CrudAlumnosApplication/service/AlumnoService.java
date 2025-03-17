package com.personal.CrudAlumnosApplication.service;

import com.personal.CrudAlumnosApplication.model.Alumno;
import com.personal.CrudAlumnosApplication.repository.AlumnoDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
/* qualifier : para tipo de per
    alumnoDAOJdbc
    alumnoDAOEnMemoria

 */
@Service
public class AlumnoService {

    private final AlumnoDAO alumnoDAO;

    @Autowired
    public AlumnoService(@Qualifier("alumnoDAOJdbc") AlumnoDAO alumnoDAO) {
        this.alumnoDAO = alumnoDAO;
    }

    public void guardarAlumno(Alumno alumno) {
        alumnoDAO.guardar(alumno);
    }

    public void actualizarAlumno(Alumno alumno) {
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
