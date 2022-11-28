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
public class Profesor implements Serializable {

    private int id_profe;
    private String nombre;
    private String apellido;
    private String contraseña;
    private String correo;


}
