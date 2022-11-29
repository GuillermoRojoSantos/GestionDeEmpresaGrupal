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

    public static final String horasDual = "SELECT sum(total) as Horas_Dual FROM"
    + " tarea, alumno where tarea.tipo ='Dual' and alumno.id = tarea.id_alumno "
    + "and alumno.id = ?;";
   
    public static final String horasFCT = "SELECT sum(total) as Horas_FCT FROM"
   + "tarea, alumno where tarea.tipo ='FCT' and alumno.id = tarea.id_alumno "
   + "and alumno.id = ?;";

    public static final String nuevoAlumno = "INSERT INTO `alumno` "
            + "(`id_alumno`, `nombre`, `apellidos`, `contraseña`, `dni`, `fechaN`, `correo`, "
            + "`telefono`, `empresa`, `profesor`, `observaciones`)"
            + " VALUES (NULL, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";

    public static final String modificarAlumno = "UPDATE `alumno` SET"
            + "`nombre`= ?,`apellidos`= ?, `contraseña`= ?, `dni`= ?,"
            + "`fechaN`= ?, `correo`= ?, `telefono`= ?,`empresa`= ?,"
            + "`profesor`= ?, `observaciones`= ?";

    public static final String borrarAlumno = "DELETE FROM alumno WHERE"
            + " `id_alumno` = ?";

    public static final String asignarEmpresa = "UPDATE `alumno` SET `Empresa`"
            + " = ? WHERE `id_alumno` = ?;";


    public void new_alumno(String nombre, String apellido, String contraseña,
                           String dni, String fechaN, String email, int telefono,
                           Empresa empresa, Profesor profesor, String observaciones) {
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
            pst.setString(10, alumno.getObservaciones());

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
                          String dni, String fechaN, String email, int telefono, 
                          String empresa,String profesor, String observaciones){

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
            pst.setString(10, observaciones);
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
             String observaciones, int id_profesor) {

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
                alumno.setObservaciones(resultSet.getString("observaciones"));
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return alumno;
    }

    @Override
    public double horasDual(int id_alumno) {
        
        double horas = 0;
         try(var pst = conexion.prepareStatement(horasDual)){
        
             ResultSet resultSet = pst.executeQuery();
             
             horas = resultSet.getDouble("Horas_Dual");
             
         } catch (SQLException ex) {
            Logger.getLogger(AlumnoDAOMySQL.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        return horas;   
    }

    //Tanto horasDual y horasFCT necesitamos hacer que se pueda ver junto el alumno (en el arraylist y el by id)
    
    @Override
    public double horasFCT(int id_alumno) {
        
        double horas = 0;
        
         try(var pst = conexion.prepareStatement(horasDual)){
        
             ResultSet resultSet = pst.executeQuery();
             
             horas = resultSet.getDouble("Horas_FCT");
         } catch (SQLException ex) {
            Logger.getLogger(AlumnoDAOMySQL.class.getName()).log(Level.SEVERE, null, ex);
        }

        return horas;  
    }
    
    
    
    
    
}
