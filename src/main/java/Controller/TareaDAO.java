/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import models.Tarea;

import java.util.ArrayList;

/**
 *
 * @author AlejandroMarínBermúd
 */
public interface TareaDAO {
    
    
    ArrayList<Tarea> get_TareaByAlumno(int id_alumno);
    
    void new_Tarea(String fecha,String tipo,double totalHoras,String actividad,String observaciones, int id_alumno);

    void update_Tarea(String fecha,String tipo,double totalHoras,String actividad,String observaciones, int id_alumno);

    void delete_Tarea(int id_tarea);
    

}
