/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
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
      
     public static final String nueva_empresa = "INSERT INTO `empresa` (`nombre`, `telefono`, "
     + "`correo`, `responsable`, `observaciones`) VALUES ( ?, ?, ?, ?, ?);";
    
     public static final String mod_empresa = "UPDATE `empresa` SET"
            + "`nombre`= ?,`telefono`= ?, `correo`= ?, `responsable`= ?,"
            + "`observaciones`= ?";
    
     public static final String datosEmpresa =  "SELECT * FROM `empresa` WHERE nombre = ?;";
    
    @Override
    public void new_empresa(String nombre, int telefono, String email, String responsable, String observaciones) {
        
        try (var pst = conexion.prepareStatement(nueva_empresa)) {
            
            var empresa = new Empresa();
            
            empresa.setNombre(nombre);
            empresa.setCorreo(email);
            empresa.setTelefono(telefono);
            empresa.setResponsable(responsable);
            empresa.setObservaciones(observaciones);
           
            pst.setString(1, empresa.getNombre());
            pst.setInt(2, empresa.getTelefono());
            pst.setString(3, empresa.getCorreo());
            pst.setString(4, empresa.getResponsable());
            pst.setString(5, empresa.getObservaciones());

            pst.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(EmpresaDAOMySQL.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void get_info(String nombre) {
        try(var pst = conexion.prepareStatement(datosEmpresa)){
            pst.setString(1,nombre);

             System.out.println("Datos de la empresa: ");
            
            ResultSet resultado = pst.executeQuery();
          while (resultado.next()){
              System.out.println(resultado.getString("nombre")
                      + " " + resultado.getString("telefono")
                      + " " + resultado.getString("correo")
                      + " " + resultado.getString("responsable")
                      + " " + resultado.getString("observaciones"));
          }

        }   
        catch (SQLException ex) {
            Logger.getLogger(EmpresaDAOMySQL.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void up_empresa(String nombre, int telefono, String email, String responsable, String observaciones) {
      
        try (var pst = conexion.prepareStatement(mod_empresa)) {
            pst.setString(1, nombre);
            pst.setInt(2, telefono);
            pst.setString(3, email);
            pst.setString(4, responsable);
            pst.setString(5, observaciones);

            pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } 
    }
    
}
