/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import static java.sql.Statement.RETURN_GENERATED_KEYS;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import models.Alumno;
import models.Empresa;
import models.Profesor;


/**
 * @author AlejandroMarínBermúd
 */
public class AlumnoDAOMySQL implements AlumnoDAO {

    private static final String USER = "root";
    private static final String PASSWORD = "";
    private static final String URL = "jdbc:mysql://localhost:3306/trabajogrupo?zeroDateTimeBehavior=CONVERT_TO_NULL";

    static {
        try {
            conexion = DriverManager.getConnection(URL, USER, PASSWORD);
            Logger.getLogger(AlumnoDAOMySQL.class.getName()).info("Conexión establecida con exito");
        } catch (SQLException ex) {
            Logger.getLogger(AlumnoDAOMySQL.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private static Connection conexion;

    public static final String getAlumno = "SELECT * FROM `alumno` WHERE id_alumno = '?;";

    public static final String listadoporprofesor = "SELECT * FROM `alumno` WHERE Profesor = '?;";

    public static final String horasDual = "SELECT horasTotalesDual as HorasAc,"
            + "(360-horasTotalesDual) as horasR FROM `alumno` WHERE id_alumno = '?';";
    //Hay que actualizar estas dos consultas por que deberia calcularse con una suma de horas hechas en las actividades realizadas
    //Ejemplo alumno "A" ha realizado 3 actividades, en ellas a realizado 3, 7 y 10 horas, (20 horas en total) 360-20  = 340 horas restantes
    //Tambien es cierto que no se scuantas horas se deben de realizar por lo que 360 esa un numero arbitrario habria que cambiarlo
    public static final String horasFCT = "SELECT horasTotalesFCT as HorasAc,"
            + "(360-horasTotalesFCT) as horasR FROM `alumno` WHERE id_alumno = '?';";

    public static final String nuevoAlumno = "INSERT INTO `alumno` "
            + "(`id_alumno`, `nombre`, `apellidos`, `contraseña`, `dni`, `fechaN`, `correo`, "
            + "`telefono`, `empresa`, `profesor`, `horasTotalesDual`, `horasTotalesFCT`, `observaciones`)"
            + " VALUES (NULL, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";

    public static final String modificarAlumno = "UPDATE `alumno` SET"
            + "`nombre`= ?,`apellidos`= ?, `contraseña`= ?, `dni`= ?,"
            + "`fechaN`= ?, `correo`= ?, `telefono`= ?,`empresa`= ?,"
            + "`profesor`= ?, `horasTotalesDual`=?, `horasTotalesFCT`= ?, `observaciones`= ?";

    public static final String borrarAlumno = "DELETE FROM alumno WHERE"
            + " `id_alumno` = ?";

    public static final String asignarEmpresa = "UPDATE `alumno` SET `Empresa`"
            + " = ? WHERE `id_alumno` = ?;";


    public void get_horasDual(int id_alumno) {

        try (var pst = conexion.prepareStatement(horasDual)) {

            ResultSet resultado = pst.executeQuery();

            while (resultado.next()) {

                System.out.println(resultado.getDouble("horasR"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProfesorDAOMySQL.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void get_horasFCT(int id_alumno) {
        try (var pst = conexion.prepareStatement(horasFCT)) {

            ResultSet resultado = pst.executeQuery();

            while (resultado.next()) {

                System.out.println(resultado.getDouble("horasR"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProfesorDAOMySQL.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void new_alumno(String nombre, String apellido, String contraseña,
                           String dni, String fechaN, String email, int telefono, Empresa empresa,
                           Profesor profesor, double horasTotalesDual, double horasTotalesFCT,
                           String observaciones) {
        try (var pst = conexion.prepareStatement(nuevoAlumno, RETURN_GENERATED_KEYS)) {
            var alumno = new Alumno();

            alumno.setNombre(nombre);
            alumno.setApellidos(apellido);
            alumno.setContraseña(contraseña);
            alumno.setDNI(dni);
            alumno.setFechaN(fechaN);
            alumno.setCorreo(email);
            alumno.setTelefono(telefono);
            alumno.setEmpresa(empresa.getNombre());
            alumno.setProfesor(profesor.getNombre());
            alumno.setHorasTotalesDual(horasTotalesDual);
            alumno.setHorasTotalesFCT(horasTotalesFCT);
            alumno.setObservaciones(observaciones);

            pst.setString(1, alumno.getNombre());
            pst.setString(2, alumno.getApellidos());
            pst.setString(3, alumno.getContraseña());
            pst.setString(4, alumno.getDNI());
            pst.setString(5, alumno.getFechaN());
            pst.setString(6, alumno.getCorreo());
            pst.setInt(7, alumno.getTelefono());
            pst.setString(8, alumno.getEmpresa());
            pst.setString(9, alumno.getProfesor());
            pst.setDouble(10, alumno.getHorasTotalesDual());
            pst.setDouble(11, alumno.getHorasTotalesFCT());
            pst.setString(12, alumno.getObservaciones());

            if (pst.executeUpdate() > 0) {

                var keys = pst.getGeneratedKeys();
                keys.next();

                alumno.setId_alumno(keys.getInt(1));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void up_alumno(String nombre, String apellido, String contraseña,
                          String dni, String fechaN, String email, int telefono, String empresa,
                          String profesor, double horasTotalesDual, double horasTotalesFCT,
                          String observaciones) {

        try (var pst = conexion.prepareStatement(modificarAlumno)) {
            pst.setString(1, nombre);
            pst.setString(2, apellido);
            pst.setString(3, contraseña);
            pst.setString(4, dni);
            pst.setString(5, fechaN);
            pst.setString(6, email);
            pst.setInt(7, telefono);
            pst.setString(8, empresa);
            pst.setString(9, profesor);
            pst.setDouble(10, horasTotalesDual);
            pst.setDouble(11, horasTotalesFCT);
            pst.setString(12, observaciones);
            pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void del_alumno(int id_Alumno) {
        try (var pst = conexion.prepareStatement(borrarAlumno)) {
            pst.setInt(1, id_Alumno);
            pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void alumno_empresa(int id_alumno, Empresa empresa) {
        try (var pst = conexion.prepareStatement(asignarEmpresa)) {

            pst.setObject(1, empresa.getNombre());
            pst.setInt(2, id_alumno);

            pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(AlumnoDAOMySQL.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public ArrayList<Alumno> alumnolist
            (String nombre, String apellido, String dni,
             String fechaN, String email, int telefono, String empresa, String profesor,
             double horasTotalesDual, double horasTotalesFCT, String observaciones,
             int id_profesor) {

        var resultado = new ArrayList<Alumno>();
        try (var pst = conexion.prepareStatement(listadoporprofesor)) {
            pst.setInt(1, id_profesor);
            ResultSet resultSet = pst.executeQuery();
            while (resultSet.next()) {
                var alumno = new Alumno();
                alumno.setId_alumno(resultSet.getInt("id_alumno"));
                alumno.setApellidos(resultSet.getString("apellidos"));
                alumno.setDNI(resultSet.getString("dni"));
                alumno.setFechaN(resultSet.getString("fechaN"));
                alumno.setCorreo(resultSet.getString("correo"));
                alumno.setTelefono(resultSet.getInt("telefono"));
                alumno.setEmpresa(resultSet.getString("empresa"));
                alumno.setProfesor(resultSet.getString("profesor"));
                alumno.setHorasTotalesDual(resultSet.getDouble("horasTotalesDual"));
                alumno.setHorasTotalesFCT(resultSet.getDouble("horasTotalesFCT"));
                alumno.setObservaciones(resultSet.getString("observaciones"));

                resultado.add(alumno);
            }
            return resultado;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultado;
    }

    @Override
    public Alumno getAlumoById(int id_alumno) {
        var alumno = new Alumno();
        try(var pst = conexion.prepareStatement(getAlumno)){
            pst.setInt(1,id_alumno);
            ResultSet resultSet = pst.executeQuery();

            while (resultSet.next()) {
                alumno.setId_alumno(resultSet.getInt("id_alumno"));
                alumno.setApellidos(resultSet.getString("apellidos"));
                alumno.setDNI(resultSet.getString("dni"));
                alumno.setFechaN(resultSet.getString("fechaN"));
                alumno.setCorreo(resultSet.getString("correo"));
                alumno.setTelefono(resultSet.getInt("telefono"));
                alumno.setEmpresa(resultSet.getString("empresa"));
                alumno.setProfesor(resultSet.getString("profesor"));
                alumno.setHorasTotalesDual(resultSet.getDouble("horasTotalesDual"));
                alumno.setHorasTotalesFCT(resultSet.getDouble("horasTotalesFCT"));
                alumno.setObservaciones(resultSet.getString("observaciones"));
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return alumno;
    }
}
