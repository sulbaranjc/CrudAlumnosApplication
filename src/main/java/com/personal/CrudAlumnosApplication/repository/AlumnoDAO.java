package com.personal.CrudAlumnosApplication.repository;

import com.personal.CrudAlumnosApplication.model.Alumno;

import java.util.List;

public interface AlumnoDAO {
    void guardar(Alumno alumno);
    void actualizar(Alumno alumno);
    void eliminar(int id);
    Alumno obtenerPorId(int id);
    List<Alumno> listarTodos();
}
