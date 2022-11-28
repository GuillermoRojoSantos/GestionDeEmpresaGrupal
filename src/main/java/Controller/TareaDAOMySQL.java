/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import static java.sql.Statement.RETURN_GENERATED_KEYS;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.Tarea;

/**
 *
 * @author AlejandroMarínBermúd
 */
public class TareaDAOMySQL implements TareaDAO {
    
    
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
    
    
    public static final String listadoTareasAlumno = "SELECT * FROM `tarea` WHERE id_alumno = ?;";
    
    public static final String add_actividad  ="INSERT INTO `tarea` (`fecha`, "
    + "`Tipo`, `Total`, `Actividad`, `Observaciones`, `id_alumno`) VALUES "
    + "('?', '?', '?', '?', '?', '?');";
    
    public static final String up_actividad = "";
    
    public static final String del_actividad = "DELETE FROM tarea WHERE `tarea`.`id` = '?'";
    
    
    
    
    @Override
    public String get_entrada() {
         
        try (var pst = conexion.prepareStatement(add_actividad, RETURN_GENERATED_KEYS)) {
            Scanner sci = new Scanner(System.in);
            Tarea t = new Tarea();
            TareaDAOMySQL prod = new TareaDAOMySQL();

            System.out.println("Introduce una fecha");
            String fecha = sci.nextLine();
            t.setFecha(fecha);

            System.out.println("Introduce una Fecha");
            String tipo = sci.nextLine();
            t.setTipo(tipo);

            System.out.println("Introduce cuantas horas tiene de Dual");
            double total = sci.nextDouble();
            t.setHorasTotalesDual(total);
            
             System.out.println("Introduce cuantas horas tiene de FCT");
            double totalFCT = sci.nextDouble();
            t.setHorasTotalesFCT(total);

            System.out.println("Introduce un Producto (el id)");
            int id = sci.nextInt();
            //String nombre = sci.nextLine();
            t.setProducto(prod.getNombre(id));

            System.out.println("Introduce el Precio");
            //Float precio = sci.nextFloat();
            t.setPrecio(prod.getPrecio(id));

            pst.setString(1, t.getAlumno());
            pst.setString(2, t.getProducto());
            pst.setString(3, t.getFecha());
            pst.setFloat(4, t.getPrecio());
            pst.setString(5, t.getEstado());

            if (pst.executeUpdate() > 0) {

                var keys = pst.getGeneratedKeys();
                keys.next();

                t.setId(keys.getInt(1));
            }

        } catch (SQLException ex) {
            Logger.getLogger(TareaDAOMySQL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public void new_Entrada() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String up_Entrada() {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    @Override
    public String del_Entrada() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
}
