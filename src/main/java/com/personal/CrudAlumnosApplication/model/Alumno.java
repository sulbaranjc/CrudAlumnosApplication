package com.personal.CrudAlumnosApplication.model;

public class Alumno {
    private int id;
    private String nombre;
    private double nota1;
    private double nota2;
    private double nota3;
    private double notaFinal;
    private double promedioFinal;

    // Constructor vacío
    public Alumno() {}

    // Constructor con parámetros
    public Alumno(int id, String nombre, double nota1, double nota2, double nota3, double notaFinal) {
        this.id = id;
        this.nombre = nombre;
        this.nota1 = nota1;
        this.nota2 = nota2;
        this.nota3 = nota3;
        this.notaFinal = notaFinal;
        this.promedioFinal = calcularPromedio();
    }

    // Getters y Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public double getNota1() { return nota1; }
    public void setNota1(double nota1) { this.nota1 = nota1; }

    public double getNota2() { return nota2; }
    public void setNota2(double nota2) { this.nota2 = nota2; }

    public double getNota3() { return nota3; }
    public void setNota3(double nota3) { this.nota3 = nota3; }

    public double getNotaFinal() { return notaFinal; }
    public void setNotaFinal(double notaFinal) { this.notaFinal = notaFinal; }

    public double getPromedioFinal() { return promedioFinal; }
    public void setPromedioFinal(double promedioFinal) { this.promedioFinal = promedioFinal; }

    // Método para calcular el promedio ponderado
    public double calcularPromedio() {
        double promedioNotas = (nota1 + nota2 + nota3) / 3;
        return (promedioNotas * 0.7) + (notaFinal * 0.3);
    }
}
