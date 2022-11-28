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
        switch (option) {
            case 1 -> {
               tareaDAO.new_Tarea("12/12/1998","DUAL",7.00,"Instalación Hadoop","",1);
            }
            case 2->{
                tareaDAO.delete_Tarea(1);
            }
            case 3->{
                tareaDAO.update_Tarea(5,"12/11/2011","FCT",12.00,"Instalación de hadoop","Mucho hadoop");
            }
        }

    }
}
