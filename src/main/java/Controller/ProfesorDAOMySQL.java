/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.Profesor;

/**
 *
 * @author AlejandroMarínBermúd
 */
public class ProfesorDAOMySQL implements ProfesorDAO{
    
    private static final String USER = "root";
    private static final String PASSWORD = "";
    private static final String URL ="jdbc:mysql://localhost:3306/trabajogrupo?zeroDateTimeBehavior=CONVERT_TO_NULL";
    
    static {
        try {
            conexion = DriverManager.getConnection(URL, USER, PASSWORD);
            Logger.getLogger(ProfesorDAOMySQL.class.getName()).info("Conexión establecida con exito");
        } catch (SQLException ex) {
            Logger.getLogger(ProfesorDAOMySQL.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private static Connection conexion;
    
     public static final String datosProfesor = "SELECT profesor.Nombre, profesor.Apellido, profesor.Correo FROM "
     + "`alumno`,`profesor` WHERE alumno.Profesor = '?' and alumno.id = '?';";

    @Override
    public void get_profesorInfo(Profesor P) {
        
     try(var pst = conexion.prepareStatement(datosProfesor)){
            
             System.out.println("Datos del profesor: ");
            
            ResultSet resultado = pst.executeQuery();
            
           while(resultado.next()){
               

               System.out.println(resultado.getString("Nombre") 
               + " " + resultado.getString("Apellido")
               + " " + resultado.getInt("Correo"));
            }
        }   
        catch (SQLException ex) {
            Logger.getLogger(ProfesorDAOMySQL.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
