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
public class Empresa implements Serializable{

    private String nombre;
    private int telefono;
    private String correo;
    private String responsable;
    private String observaciones;

}
