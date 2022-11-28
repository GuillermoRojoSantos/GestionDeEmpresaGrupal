/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import static Controller.ProfesorDAOMySQL.datosProfesor;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import static java.sql.Statement.RETURN_GENERATED_KEYS;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.Empresa;

/**
 *
 * @author AlejandroMarínBermúd
 */
public class EmpresaDAOMySQL implements EmpresaDAO {

    private static final String USER = "root";
    private static final String PASSWORD = "";
    private static final String URL ="jdbc:mysql://localhost:3306/trabajogrupo?zeroDateTimeBehavior=CONVERT_TO_NULL";
    
     static {
        try {
            conexion = DriverManager.getConnection(URL, USER, PASSWORD);
            Logger.getLogger(EmpresaDAOMySQL.class.getName()).info("Conexión establecida con exito");
        } catch (SQLException ex) {
            Logger.getLogger(EmpresaDAOMySQL.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
      private static Connection conexion;
      
     public static final String nueva_empresa = "INSERT INTO `empresa` (`Nombre`, `Telefono`, "
     + "`Email`, `Responsable`, `Observaciones`) VALUES ( ?, ?, ?, ?, ?);";
    
     public static final String mod_empresa = "";
    
     public static final String datosEmpresa =  "SELECT empresa.Nombre, empresa.Email,"
     + " empresa.Responsable, empresa.Observacion FROM `alumno`,`empresa`"
     + " WHERE empresa.Nombre = alumno.Empresa and alumno.id = '?';";
    
    @Override
    public void new_empresa(Empresa e) {
        try (var pst = conexion.prepareStatement(nueva_empresa, RETURN_GENERATED_KEYS)) {
            Scanner sci = new Scanner(System.in);    

            System.out.println("nombre");
            String nombre = sci.nextLine();
            e.setNombre(nombre);

            System.out.println("Introduce un telefono");
            int telefono = sci.nextInt();
            e.setTelefono(telefono);

            System.out.println("Introduce un email");
            String email = sci.next().toUpperCase();
            e.setCorreo(email);

            System.out.println("Introduce un Responsable");
            String responsable = sci.nextLine();
            e.setResponsable(responsable);

            System.out.println("Introduce una observacion");
            String observacion = sci.nextLine();
            e.setObservaciones(observacion);

            pst.setString(1, e.getNombre());
            pst.setInt(2, e.getTelefono());
            pst.setString(3, e.getCorreo());
            pst.setString(4, e.getResponsable());
            pst.setString(5, e.getObservaciones());

        } catch (SQLException ex) {
            Logger.getLogger(EmpresaDAOMySQL.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


    @Override
    public void get_info(Empresa e) {
        try(var pst = conexion.prepareStatement(datosEmpresa)){

             System.out.println("Datos de la empresa: ");
            
            ResultSet resultado = pst.executeQuery();
            
           while(resultado.next()){
                

               System.out.println(resultado.getString("Nombre") 
               + " " + resultado.getString("Email")
               + " " + resultado.getInt("Responsable")
               + " " + resultado.getInt("Observacion"));
            }
        }   
        catch (SQLException ex) {
            Logger.getLogger(EmpresaDAOMySQL.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void up_empresa(Empresa e) {
        throw new UnsupportedOperationException("Not supported yet."); 
    }
    
}
