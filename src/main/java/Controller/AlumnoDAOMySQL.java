/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import models.Alumno;
import models.Empresa;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.Profesor;


/**
 *
 * @author AlejandroMarínBermúd
 */
public class AlumnoDAOMySQL implements AlumnoDAO {
    
    private static final String USER = "root";
    private static final String PASSWORD = "";
     private static final String URL ="jdbc:mysql://localhost:3306/trabajogrupo?zeroDateTimeBehavior=CONVERT_TO_NULL";
    
     static {
        try {
            conexion = DriverManager.getConnection(URL, USER, PASSWORD);
            Logger.getLogger(AlumnoDAOMySQL.class.getName()).info("Conexión establecida con exito");
        } catch (SQLException ex) {
            Logger.getLogger(AlumnoDAOMySQL.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
      private static Connection conexion;
      
      
    public static final String listadoporprofesor = "SELECT * FROM `alumno` WHERE Profesor = '?;";
    
    public static final String horasDual = "SELECT NºhorasDual as HorasAc,"
    + "(360-NºhorasDual) as horasR FROM `alumno` WHERE id = '?';";
    
    public static final String horasFCT = "SELECT NºhorasFCT as HorasAc,"
    + "(360-NºhorasFCT) as horasR FROM `alumno` WHERE id = '?';";
   
    public static final String nuevoAlumno = "INSERT INTO `alumno` "
    + "(`id`, `Nombre`, `Apellido`, `Contraseña`, `DNI`, `FechaNacimiento`, `Email`, "
    + "`Telefono`, `Empresa`, `Profesor`, `NºhorasDual`, `NºhorasFCT`, `Observaciones`)"
    + " VALUES (NULL, '?', '?', '?', '?', '?', '?', '?', '?', '?', '?', '', '?');";
    
    public static final String modificarAlumno = "" ;
    
    public static final String borrarAlumno = "DELETE FROM alumno WHERE"
    + " `alumno`.`id` = ?" ;
    
    public static final String asignarEmpresa = "UPDATE `alumno` SET `Empresa`"
    + " = '?' WHERE `alumno`.`id` = ?;" ;


    

    
    
    

    public String get_horasDual() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public String get_horasFCT() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void new_alumno(String nombre, String apellido,String contraseña,
    String dni, String fechaN, String email, int telefono, String empresa,
    String profesor, double horasTotalesDual, double horasTotalesFCT,
    String observaciones) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void up_alumno(String nombre, String apellido,String contraseña,
    String dni, String fechaN, String email, int telefono, String empresa,
    String profesor, double horasTotalesDual, double horasTotalesFCT,
    String observaciones) {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    public void del_alumno(String nombre, String apellido,String contraseña,
    String dni, String fechaN, String email, int telefono, String empresa,
    String profesor, double horasTotalesDual, double horasTotalesFCT,
    String observaciones) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void alumno_empresa(int id_alumno, String empresa) {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    @Override
    public void alumnolist(String nombre, String apellido,String contraseña,
    String dni, String fechaN, String email, int telefono, String empresa,
    String profesor, double horasTotalesDual, double horasTotalesFCT,
    String observaciones, int id_profesor) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
}
