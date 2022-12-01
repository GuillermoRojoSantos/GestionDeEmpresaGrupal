package com.mycompany.gestion;
import dao.AlumnoDAOMySQL;
import dao.ProfesorDAOMySQL;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import model.Alumno;
import model.Profesor;

import java.io.IOException;

public class PrimaryController {

    @FXML
    private Button aceptar;

    @FXML
    private Button cancelar;

    @FXML
    private TextField contraseña;

    @FXML
    private TextField usuario;

    @FXML
    private void switchToSecondary() throws IOException {

        var profesorSQL = new ProfesorDAOMySQL();
        var alumnoSQL = new AlumnoDAOMySQL();

        var listaAlumnos = alumnoSQL.getAll();
        var listaProfesores = profesorSQL.getAll();

        for (Alumno alumno : listaAlumnos){
            if(alumno.getCorreo().equals(usuario.getText()) && alumno.getContraseña().equals(contraseña.getText())){
                App.actualAlumno=alumno;
                App.setRoot("thirdary");
            }
        }
        for (Profesor profesor : listaProfesores){
            if(profesor.getCorreo().equals(usuario.getText()) && profesor.getContraseña().equals(contraseña.getText())){
                App.actualProfesor=profesor;
                App.setRoot("secondary");
            }
        }

    }

}
