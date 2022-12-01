/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import model.Empresa;
import model.Profesor;

import java.util.ArrayList;

/**
 *
 * @author AlejandroMarínBermúd
 */
public interface EmpresaDAO {
    
    void new_empresa(String nombre, int telefono, String email, String responsable, String observaciones);
    
    ArrayList<Empresa> get_info(int id_alumno);
    
    void up_empresa(String empresaObjetivo,String nombre, int telefono, String email, String responsable,
                    String observaciones);
   
    ArrayList<Empresa> getAll();

    ArrayList<Empresa> getAllByProfesor(Profesor profesor);

    void del_empresa(String empresa);
}
