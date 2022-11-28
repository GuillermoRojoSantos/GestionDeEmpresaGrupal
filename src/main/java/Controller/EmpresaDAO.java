/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import models.Alumno;
import models.Empresa;

/**
 *
 * @author AlejandroMarínBermúd
 */
public interface EmpresaDAO {
    
    void new_empresa(String nombre, int telefono, String email, String responsable, String observaciones);
    
    void get_info(String nombre, int telefono, String email, String responsable, String observaciones);
    
    void up_empresa(String nombre, int telefono, String email, String responsable, String observaciones);
}
