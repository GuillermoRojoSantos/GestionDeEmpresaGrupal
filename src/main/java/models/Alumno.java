/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
/**
 *
 * @author guiro
 */
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Alumno implements Serializable {

    private int id_alumno;
    private String nombre;
    private String apellidos;
    private String contraseña;
    private String DNI;
    private String fechaN;
    private String correo;
    private Integer telefono;
    private String empresa;
    private String profesor;
    private String observaciones;
    
    
    
}
