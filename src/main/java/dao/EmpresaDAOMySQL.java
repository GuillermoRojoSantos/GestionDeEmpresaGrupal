/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import com.mycompany.gestion.App;
import model.Alumno;
import model.Empresa;
import model.Profesor;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author AlejandroMarínBermúd
 */
public class EmpresaDAOMySQL implements EmpresaDAO {

    private static final String USER = "root";
    private static final String PASSWORD = "";
    private static final String URL = "jdbc:mysql://localhost:3306/trabajogrupo?zeroDateTimeBehavior=CONVERT_TO_NULL";

    static {
        try {
            conexion = DriverManager.getConnection(URL, USER, PASSWORD);
            Logger.getLogger(EmpresaDAOMySQL.class.getName()).info("Conexión establecida con exito");
        } catch (SQLException ex) {
            Logger.getLogger(EmpresaDAOMySQL.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private static Connection conexion;

    public static final String nueva_empresa = "INSERT INTO `empresa` (`nombre`, `telefono`, "
            + "`correo`, `responsable`, `observaciones`) VALUES ( ?, ?, ?, ?, ?);";

    public static final String mod_empresa = "UPDATE `empresa` SET"
            + "`nombre`= ?,`telefono`= ?, `correo`= ?, `responsable`= ?,"
            + "`observaciones`= ? where `nombre`=?";

    public static final String datosEmpresa = "SELECT empresa.* FROM `empresa`, alumno WHERE empresa.nombre = " +
            "alumno.empresa and alumno.id_alumno = ?;";

    public static final String getAll = "SELECT * FROM empresa";

    public static final String getEmpresasProfesor = "SELECT empresa.* FROM `alumno`,profesor,empresa WHERE " +
            "alumno.profesor=profesor.nombre and alumno.empresa=empresa.nombre and profesor.id_profesor=? GROUP BY empresa.nombre;";

    public static final String delEmpresa = "DELETE FROM empresa WHERE `empresa`.`nombre` = ?;";


    @Override
    public void new_empresa(String nombre, int telefono, String email, String responsable, String observaciones) {

        try (var pst = conexion.prepareStatement(nueva_empresa)) {

            var empresa = new Empresa();

            empresa.setNombre(nombre);
            empresa.setCorreo(email);
            empresa.setTelefono(telefono);
            empresa.setResponsable(responsable);
            empresa.setObservaciones(observaciones);

            pst.setString(1, empresa.getNombre());
            pst.setInt(2, empresa.getTelefono());
            pst.setString(3, empresa.getCorreo());
            pst.setString(4, empresa.getResponsable());
            pst.setString(5, empresa.getObservaciones());

            pst.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(EmpresaDAOMySQL.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public ArrayList<Empresa> get_info(int id_alumno) {

        var resultado = new ArrayList<Empresa>();

        try (var pst = conexion.prepareStatement(datosEmpresa)) {


            pst.setInt(1, id_alumno);
            ResultSet resultSet = pst.executeQuery();

            while (resultSet.next()) {
                var empresa = new Empresa();

                empresa.setNombre(resultSet.getString("nombre"));
                empresa.setTelefono(resultSet.getInt("telefono"));
                empresa.setCorreo(resultSet.getString("correo"));
                empresa.setResponsable(resultSet.getString("responsable"));
                empresa.setObservaciones(resultSet.getString("observaciones"));

                resultado.add(empresa);
            }
        } catch (SQLException ex) {
            Logger.getLogger(EmpresaDAOMySQL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return resultado;
    }

    @Override
    public void up_empresa(String empresaObjetivo, String nombre, int telefono, String email, String responsable,
                           String observaciones) {

        try (var pst = conexion.prepareStatement(mod_empresa)) {
            pst.setString(1, nombre);
            pst.setInt(2, telefono);
            pst.setString(3, email);
            pst.setString(4, responsable);
            pst.setString(5, observaciones);
            pst.setString(6, empresaObjetivo);

            pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public ArrayList<Empresa> getAll() {
        var empresas = new ArrayList<Empresa>();
        try (var pst = conexion.prepareStatement(getAll)) {
            ResultSet resultSet = pst.executeQuery();
            while (resultSet.next()) {
                var empresa = new Empresa();
                empresa.setNombre(resultSet.getString("nombre"));
                empresa.setTelefono(resultSet.getInt("telefono"));
                empresa.setCorreo(resultSet.getString("correo"));
                empresa.setResponsable(resultSet.getString("responsable"));
                empresa.setObservaciones(resultSet.getString("observaciones"));
                empresas.add(empresa);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return empresas;
    }

    @Override
    public ArrayList<Empresa> getAllByProfesor(Profesor profesor) {
        var empresas = new ArrayList<Empresa>();
        try (var pst = conexion.prepareStatement(getEmpresasProfesor)) {
            pst.setInt(1, profesor.getId_profe());
            ResultSet resultSet = pst.executeQuery();
            while (resultSet.next()) {
                var empresa = new Empresa();
                empresa.setNombre(resultSet.getString("nombre"));
                empresa.setTelefono(resultSet.getInt("telefono"));
                empresa.setCorreo(resultSet.getString("correo"));
                empresa.setResponsable(resultSet.getString("responsable"));
                empresa.setObservaciones(resultSet.getString("observaciones"));
                empresas.add(empresa);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return empresas;
    }

    @Override
    public void del_empresa(String empresa) {
        try (var pst = conexion.prepareStatement(delEmpresa)) {
            pst.setString(1, empresa);
            pst.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
