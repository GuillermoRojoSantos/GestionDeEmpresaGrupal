/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;


import models.Alumno;
import models.Empresa;
import models.Profesor;

import java.util.ArrayList;

/**
 * @author AlejandroMarínBermúd
 */
public interface AlumnoDAO {

    void get_horasDual(int id_alumno);

    void get_horasFCT(int id_alumno);

    void new_alumno(String nombre, String apellido, String contraseña,
                    String dni, String fechaN, String email, int telefono, Empresa empresa,
                    Profesor profesor, double horasTotalesDual, double horasTotalesFCT,
                    String observaciones);

    void up_alumno(String nombre, String apellido, String contraseña,
                   String dni, String fechaN, String email, int telefono, String empresa,
                   String profesor, double horasTotalesDual, double horasTotalesFCT,
                   String observaciones);

    void del_alumno(int id_alumno);

    void alumno_empresa(int id_alumno, Empresa empresa);

    ArrayList<Alumno> alumnolist(String nombre, String apellido, String dni, String fechaN,
                                 String email, int telefono, String empresa, String profesor,
                                 double horasTotalesDual, double horasTotalesFCT, String observaciones,
                                 int id_profesor);

    Alumno getAlumoById(int id_alumno);
}
