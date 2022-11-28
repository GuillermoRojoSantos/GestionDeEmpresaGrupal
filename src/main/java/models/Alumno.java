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
    private Integer edad;
    private String contraseña;
    private String DNI;
    private String fechaN;
    private String correo;
    private Integer telefono;
    private Empresa empresa;
    private Profesor profesor;
    private double horasTotalesDual;
    private double horasTotalesFCT;
    private String observaciones;
    
    
    
}
