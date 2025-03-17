package com.personal.CrudAlumnosApplication.repository;

import com.personal.CrudAlumnosApplication.model.Alumno;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
@Qualifier("alumnoDAOJdbc")
public class AlumnoDAOJdbc implements AlumnoDAO {

    private Connection getConnection() {
        return Conexion.getInstancia().getConnection();
    }

    @Override
    public void guardar(Alumno alumno) {
        String sql = "INSERT INTO alumnos (nombre, nota1, nota2, nota3, notaFinal, promedioFinal) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement pstmt = getConnection().prepareStatement(sql)) {
            pstmt.setString(1, alumno.getNombre());
            pstmt.setDouble(2, alumno.getNota1());
            pstmt.setDouble(3, alumno.getNota2());
            pstmt.setDouble(4, alumno.getNota3());
            pstmt.setDouble(5, alumno.getNotaFinal());
            pstmt.setDouble(6, alumno.calcularPromedio());
            pstmt.executeUpdate();
            System.out.println("✅ Alumno guardado correctamente.");
        } catch (SQLException e) {
            System.err.println("❌ Error al guardar el alumno.");
            e.printStackTrace();
        }
    }

    @Override
    public void actualizar(Alumno alumno) {
        String sql = "UPDATE alumnos SET nombre = ?, nota1 = ?, nota2 = ?, nota3 = ?, notaFinal = ?, promedioFinal = ? WHERE id = ?";
        try (PreparedStatement pstmt = getConnection().prepareStatement(sql)) {
            pstmt.setString(1, alumno.getNombre());
            pstmt.setDouble(2, alumno.getNota1());
            pstmt.setDouble(3, alumno.getNota2());
            pstmt.setDouble(4, alumno.getNota3());
            pstmt.setDouble(5, alumno.getNotaFinal());
            pstmt.setDouble(6, alumno.calcularPromedio());
            pstmt.setInt(7, alumno.getId());
            pstmt.executeUpdate();
            System.out.println("✅ Alumno actualizado correctamente.");
        } catch (SQLException e) {
            System.err.println("❌ Error al actualizar el alumno.");
            e.printStackTrace();
        }
    }

    @Override
    public void eliminar(int id) {
        String sql = "DELETE FROM alumnos WHERE id = ?";
        try (PreparedStatement pstmt = getConnection().prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
            System.out.println("✅ Alumno eliminado correctamente.");
        } catch (SQLException e) {
            System.err.println("❌ Error al eliminar el alumno.");
            e.printStackTrace();
        }
    }

    @Override
    public Alumno obtenerPorId(int id) {
        String sql = "SELECT * FROM alumnos WHERE id = ?";
        Alumno alumno = null;
        try (PreparedStatement pstmt = getConnection().prepareStatement(sql)) {
            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    alumno = mapearAlumno(rs);
                }
            }
        } catch (SQLException e) {
            System.err.println("❌ Error al obtener el alumno por ID.");
            e.printStackTrace();
        }
        return alumno;
    }

    @Override
    public List<Alumno> listarTodos() {
        List<Alumno> alumnos = new ArrayList<>();
        String sql = "SELECT * FROM alumnos";
        try (PreparedStatement pstmt = getConnection().prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                alumnos.add(mapearAlumno(rs));
            }
            System.out.println("✅ Listado de alumnos recuperado correctamente.");
        } catch (SQLException e) {
            System.err.println("❌ Error al listar los alumnos.");
            e.printStackTrace();
        }
        return alumnos;
    }

    private Alumno mapearAlumno(ResultSet rs) throws SQLException {
        Alumno alumno = new Alumno();
        alumno.setId(rs.getInt("id"));
        alumno.setNombre(rs.getString("nombre"));
        alumno.setNota1(rs.getDouble("nota1"));
        alumno.setNota2(rs.getDouble("nota2"));
        alumno.setNota3(rs.getDouble("nota3"));
        alumno.setNotaFinal(rs.getDouble("notaFinal"));
        alumno.setPromedioFinal(rs.getDouble("promedioFinal"));
        return alumno;
    }
}
