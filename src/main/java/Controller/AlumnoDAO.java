/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import models.Alumno;
import models.Empresa;
import models.Profesor;

/**
 *
 * @author AlejandroMarínBermúd
 */
public interface AlumnoDAO {
    
    String get_horasDual();
    
    String get_horasFCT();

    void new_alumno(Alumno a);
    
    void up_alumno(Alumno a);
    
    void del_alumno(Alumno a);
    
    void alumno_empresa(Alumno a, Empresa p);
    
    void alumnolist(Profesor p);
}
