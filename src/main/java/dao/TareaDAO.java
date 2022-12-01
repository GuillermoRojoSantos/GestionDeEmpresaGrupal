/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
package daos;

import models.Tarea;

import java.util.ArrayList;

/**
 *
 * @author AlejandroMarínBermúd
 */
package dao;

import java.util.ArrayList;
import model.Tarea;

public interface TareaDAO {
    
    
    ArrayList<Tarea> get_TareaByAlumno(int id_alumno);
    
    void new_Tarea(String fecha,String tipo,double totalHoras,String actividad,String observaciones, int id_alumno);

    void update_Tarea(int id_tarea, String fecha,String tipo,double totalHoras,String actividad,String observaciones);

    void delete_Tarea(int id_tarea);
    

}
