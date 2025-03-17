package com.personal.CrudAlumnosApplication.controller;

import com.personal.CrudAlumnosApplication.model.Alumno;
import com.personal.CrudAlumnosApplication.service.AlumnoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class AlumnoController {

    private final AlumnoService alumnoService;

    // Inyección automática del servicio
    @Autowired
    public AlumnoController(AlumnoService alumnoService) {
        this.alumnoService = alumnoService;
    }

    // Listar todos los alumnos para "/" y "/alumnos"
    @GetMapping({"/", "/alumnos"})
    public String listarAlumnos(Model model) {
        model.addAttribute("alumnos", alumnoService.listarTodosLosAlumnos());
        return "index";
    }

    // Mostrar formulario para agregar un nuevo alumno
    @GetMapping("/alumnos/nuevo")
    public String mostrarFormulario(Model model) {
        model.addAttribute("alumno", new Alumno());
        return "form";
    }

    // Guardar un nuevo alumno
    @PostMapping("/alumnos/guardar")
    public String guardarAlumno(@ModelAttribute Alumno alumno) {
        alumnoService.guardarAlumno(alumno);
        return "redirect:/alumnos";
    }

    // Mostrar formulario para editar un alumno existente
    @GetMapping("/alumnos/editar/{id}")
    public String mostrarFormularioEditar(@PathVariable int id, Model model) {
        Alumno alumno = alumnoService.obtenerAlumnoPorId(id);
        model.addAttribute("alumno", alumno);
        return "form";
    }

    // Actualizar un alumno existente
    @PostMapping("/alumnos/actualizar")
    public String actualizarAlumno(@ModelAttribute Alumno alumno) {
        alumnoService.actualizarAlumno(alumno);
        return "redirect:/alumnos";
    }

    // Eliminar un alumno
    @GetMapping("/alumnos/eliminar/{id}")
    public String eliminarAlumno(@PathVariable int id) {
        alumnoService.eliminarAlumno(id);
        return "redirect:/alumnos";
    }
}
