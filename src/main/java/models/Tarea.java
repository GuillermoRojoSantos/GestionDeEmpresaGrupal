/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 *
 * @author AlejandroMarínBermúd
 */
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Tarea {
               
    private int id_tarea;
    private String fecha;
    private String tipo;
    private double totalHoras;
    private String actividad;
    private String observaciones;
    private int id_alumno;
}
