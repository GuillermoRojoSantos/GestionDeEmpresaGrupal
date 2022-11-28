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
import models.Tarea;

/**
 *
 * @author AlejandroMarínBermúd
 */
public class TareaDAOMySQL implements TareaDAO {
    
    private static final String USER = "root";
    private static final String PASSWORD = "";
    private static final String URL ="jdbc:mysql://localhost:3306/trabajogrupo?zeroDateTimeBehavior=CONVERT_TO_NULL";
    private static Connection conexion;
    private static final String get_TareaByAlumno ="SELECT * FROM `tarea` WHERE id_alumno = ?;";
    public static final String add_tarea  ="INSERT INTO `tarea` (`fecha`, "
            + "`tipo`, `totalHoras`, `actividad`, `observaciones`, `id_alumno`) VALUES "
            + "(?, ?, ?, ?, ?, ?);";
    public static final String up_actividad = "UPDATE `tarea` SET `fecha` = ?, `tipo` = ?, " +
            "`totalHoras` = ?, `actividad` = ?, `observaciones` = ? WHERE `id_tarea` = ?;";
    public static final String del_actividad = "DELETE FROM tarea WHERE `id_tarea`= ?";
    static {
        try {
            conexion = DriverManager.getConnection(URL, USER, PASSWORD);
            Logger.getLogger(ProfesorDAOMySQL.class.getName()).info("Conexión establecida con exito");
        } catch (SQLException ex) {
            Logger.getLogger(ProfesorDAOMySQL.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
  
    @Override
    public ArrayList<Tarea> get_TareaByAlumno(int id_alumno) {
        var resultado = new ArrayList<Tarea>();
        try(var pst = conexion.prepareStatement(get_TareaByAlumno)){
            pst.setInt(1,id_alumno);
            ResultSet resultSet = pst.executeQuery();
            while(resultSet.next()){
                var tarea = new Tarea();
                tarea.setId_tarea(resultSet.getInt("id_tarea"));
                tarea.setFecha(resultSet.getString("fecha"));
                tarea.setFecha(resultSet.getString("tipo"));
                tarea.setFecha(resultSet.getString("totalHoras"));
                tarea.setFecha(resultSet.getString("actividad"));
                tarea.setFecha(resultSet.getString("observaciones"));
                tarea.setId_tarea(resultSet.getInt("id_alumno"));

               
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return resultado;
    }

    @Override
    public void new_Tarea(String fecha,String tipo,double totalHoras,String actividad,String observaciones, int id_alumno) {
        try (var pst = conexion.prepareStatement(add_tarea, RETURN_GENERATED_KEYS)) {
            var tarea = new Tarea();
            tarea.setFecha(fecha);
            tarea.setTipo(tipo);
            tarea.setTotalHoras(totalHoras);
            tarea.setActividad(actividad);
            tarea.setObservaciones(observaciones);
            tarea.setId_alumno(id_alumno);

            pst.setString(1, tarea.getFecha());
            pst.setString(2, tarea.getTipo());
            pst.setDouble(3, tarea.getTotalHoras());
            pst.setString(4, tarea.getActividad());
            pst.setString(5, tarea.getObservaciones());
            pst.setInt(6, tarea.getId_alumno());

            if (pst.executeUpdate() > 0) {

                var keys = pst.getGeneratedKeys();
                keys.next();

                tarea.setId_tarea(keys.getInt(1));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update_Tarea(int id_tarea, String fecha,String tipo,double totalHoras,String actividad,String observaciones) {
        try(var pst = conexion.prepareStatement(up_actividad)){
            pst.setString(1,fecha);
            pst.setString(2,tipo);
            pst.setDouble(3,totalHoras);
            pst.setString(4,actividad);
            pst.setString(5,observaciones);
            pst.setInt(6,id_tarea);
            pst.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public void delete_Tarea(int id_tarea) {
        try (var pst = conexion.prepareStatement(del_actividad)){
            pst.setInt(1,id_tarea);
            pst.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
}
