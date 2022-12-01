/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.util.ArrayList;
import model.Profesor;


/**
 *
 * @author AlejandroMarínBermúd
 */
public interface ProfesorDAO {
      
   ArrayList<Profesor> get_profesorInfo(int id_profesor);

   ArrayList<Profesor> getAll();

}
