import Controller.*;
import models.Empresa;
import models.Profesor;

import java.util.Random;
import java.util.Scanner;

public class menutst {
    public static void main(String[] args) {
        // make one instance each of AlumnoDAOMySQL, EmpresaDAOMySQL, ProfesorDAOMySQL, TareaDAOMySQL
        AlumnoDAOMySQL alumnoDAO = new AlumnoDAOMySQL();
        EmpresaDAOMySQL empresaDAO = new EmpresaDAOMySQL();
        ProfesorDAOMySQL profesorDAO = new ProfesorDAOMySQL();
        TareaDAOMySQL tareaDAO = new TareaDAOMySQL();
        var scan = new Scanner(System.in);
        int option = scan.nextInt();
        var Sipadan = new Empresa();
        var profe = new Profesor();
        Sipadan.setNombre("Sipadan");
        profe.setNombre("Francisco");
        switch (option) {
            case 1 -> {

                alumnoDAO.new_alumno("Guillermo", "Rojo", "1234", "abcdefg1", "30/12/2003"
                        , "asdfg@mlm", 12345667, Sipadan, profe, "Buen alumno pero vago");
            }
            case 2 -> {
                alumnoDAO.up_alumno(2, "Manuel", "Mnu", "axert", "123456H",
                        "12/12/2012", "qwertyu", 123456, "Accenture", "Francisco", "Ninguna");
            }
            case 3->{
                var alumnos=alumnoDAO.alumnolist(profe);
                //display every object in arraylist alumnos
                for (var alumno : alumnos) {
                    System.out.println(alumno);
                }
            }
            case 4->{
                System.out.println(alumnoDAO.horasDual(1));
                System.out.println(alumnoDAO.horasFCT(1));
            }
        }
    }
}
