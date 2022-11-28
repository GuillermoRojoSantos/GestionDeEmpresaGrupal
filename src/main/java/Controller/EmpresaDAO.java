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
    
    void new_empresa(Empresa e);
    
    void get_info(Empresa e);
    
    void up_empresa(Empresa e);
}
