/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;


import model.Alumno;
import model.Empresa;
import model.Profesor;

import java.util.ArrayList;

/**
 * @author AlejandroMarínBermúd
 */
public interface AlumnoDAO {


    void new_alumno(String nombre, String apellido, String contraseña,
                    String dni, String fechaN, String email, String telefono, String empresa,
                    String profesor, String observaciones);

    void up_alumno(int id_AlumnoObjetivo,String nombre, String apellido, String contraseña,
                   String dni, String fechaN, String email, String telefono,
                   String empresa, String profesor,String observaciones);

    void del_alumno(int id_alumno);

    void alumno_empresa(int id_alumno, Empresa empresa);

    ArrayList<Alumno> alumnolist(Profesor profesor);

    Alumno getAlumoById(int id_alumno);
    
    double horasDual(int id_alumno);
    
    double horasFCT(int id_alumno);

    ArrayList<Alumno> getAll();
    
}
