/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import model.Profesor;

/**
 * @author AlejandroMarínBermúd
 */
public class ProfesorDAOMySQL implements ProfesorDAO {

    private static final String USER = "root";
    private static final String PASSWORD = "";
    private static final String URL = "jdbc:mysql://localhost:3306/trabajogrupo?zeroDateTimeBehavior=CONVERT_TO_NULL";

    static {
        try {
            conexion = DriverManager.getConnection(URL, USER, PASSWORD);
            Logger.getLogger(ProfesorDAOMySQL.class.getName()).info("Conexión establecida con exito");
        } catch (SQLException ex) {
            Logger.getLogger(ProfesorDAOMySQL.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private static Connection conexion;

    public static final String datosProfesor = "SELECT profesor.* FROM `profesor`, alumno WHERE profesor.nombre = alumno.profesor and alumno.id_alumno = ?;";

    public static final String getAll = "select * from profesor;";

    @Override
    public ArrayList<Profesor> get_profesorInfo(int id_alumno) {
        var resultado = new ArrayList<Profesor>();
        try (var pst = conexion.prepareStatement(datosProfesor)) {

            pst.setInt(1, id_alumno);
            ResultSet resultSet = pst.executeQuery();

            while (resultSet.next()) {
                var profesor = new Profesor();
                profesor.setId_profe(resultSet.getInt("id_profesor"));
                profesor.setNombre(resultSet.getString("nombre"));
                profesor.setApellido(resultSet.getString("apellido"));
                profesor.setCorreo(resultSet.getString("correo"));
                profesor.setContraseña(resultSet.getString("contraseña"));

                resultado.add(profesor);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProfesorDAOMySQL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return resultado;
    }

    @Override
    public ArrayList<Profesor> getAll() {
        var resultado = new ArrayList<Profesor>();
        try (var pst = conexion.prepareStatement(getAll)) {
            ResultSet resultSet= pst.executeQuery();
            while (resultSet.next()){
                var profesor = new Profesor();
                profesor.setId_profe(resultSet.getInt("id_profesor"));
                profesor.setNombre(resultSet.getString("nombre"));
                profesor.setApellido(resultSet.getString("apellido"));
                profesor.setCorreo(resultSet.getString("correo"));
                profesor.setContraseña(resultSet.getString("contraseña"));

                resultado.add(profesor);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return resultado;
    }
}
