/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.gestion;

import com.mysql.cj.protocol.a.StringValueEncoder;
import dao.AlumnoDAOMySQL;
import dao.EmpresaDAOMySQL;
import dao.ProfesorDAOMySQL;
import dao.TareaDAOMySQL;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;

import javafx.scene.input.MouseEvent;
import model.Empresa;
import model.Tarea;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Alumno;
import model.Profesor;

/**
 * FXML Controller class
 *
 * @author AlejandroMarínBermúd
 */
public class ThirdaryController implements Initializable {

    static AlumnoDAOMySQL alumnoSQL = new AlumnoDAOMySQL();
    static ProfesorDAOMySQL profesorSQL = new ProfesorDAOMySQL();
    static TareaDAOMySQL tareaSQL = new TareaDAOMySQL();
    static EmpresaDAOMySQL empresaSQL = new EmpresaDAOMySQL();
    Tarea tareaTablaSeleccionado = new Tarea();

    @FXML
    private void switchToPrimary() throws IOException {
        App.setRoot("primary");
    }
    @FXML
    private Button atras;
    @FXML
    private TextField tarea_fecha;
    @FXML
    private TextField tarea_total;
    @FXML
    private TextField tarea_observaciones;
    @FXML
    private TextField tarea_tipo;
    @FXML
    private TextField tarea_actividad;
    @FXML
    private Button tarea_introducir;
    @FXML
    private Button tarea_modificar;
    @FXML
    private Button tarea_borrar;
    @FXML
    private Tab listaTareas;
    @FXML
    private TableView<Tarea> tablaTareas;
    @FXML
    private TableColumn<Tarea, String> tareaFecha;
    @FXML
    private TableColumn<Tarea, String> tareaTipo;
    @FXML
    private TableColumn<Tarea, Double> tareaTotal;
    @FXML
    private TableColumn<Tarea, String> tareaActividad;
    @FXML
    private TableColumn<Tarea, String> tareaObservaciones;
    @FXML
    private Tab listaEmpresa;
    @FXML
    private TableView<Empresa> tablaEmpresa;
    @FXML
    private TableColumn<Empresa, String> empresaNombre;
    @FXML
    private TableColumn<Empresa, Integer> empresaTelefono;
    @FXML
    private TableColumn<Empresa, String> empresaCorreo;
    @FXML
    private TableColumn<Empresa, String> empresaResponsable;
    @FXML
    private TableColumn<Empresa, String> empresaObservaciones;
    @FXML
    private Tab listaProfesor;
    @FXML
    private TableView<Profesor> tablaProfesor;
    @FXML
    private TableColumn<Profesor, String> profesorNombre;
    @FXML
    private TableColumn<Profesor, String> profesorApellido;
    @FXML
    private TableColumn<Profesor, String> profesorCorreo;
    @FXML
    private TextField txtDual;
    @FXML
    private TextField txtDualRes;
    @FXML
    private TextField txtFCT;
    @FXML
    private TextField txtFCTRest;

    @FXML
    void selectTarea(MouseEvent event) {
        if (tablaTareas.getSelectionModel().getSelectedItem() != null) {
            tareaTablaSeleccionado = tablaTareas.getSelectionModel().getSelectedItem();
            tarea_fecha.setText(tareaTablaSeleccionado.getFecha());
            tarea_tipo.setText(tareaTablaSeleccionado.getTipo());
            tarea_total.setText(String.valueOf((Double) tareaTablaSeleccionado.getTotalHoras()));
            tarea_actividad.setText(tareaTablaSeleccionado.getActividad());
            tarea_observaciones.setText(tareaTablaSeleccionado.getObservaciones());
            System.out.println(tareaTablaSeleccionado);
            updateTablesOnly();
        } else {
            update();
        }
    }

    @FXML
    void añadirTarea(ActionEvent event) {
        tareaSQL.new_Tarea(tarea_fecha.getText(), tarea_tipo.getText(), Double.parseDouble(tarea_total.getText()), tarea_actividad.getText()
                , tarea_observaciones.getText(), App.actualAlumno.getId_alumno());
        update();
    }

    @FXML
    void modificarEliminar(ActionEvent event) {
        tareaSQL.delete_Tarea(tareaTablaSeleccionado.getId_tarea());
        updateTablesOnly();
    }

    @FXML
    void modificarTarea(ActionEvent event) {
        tareaSQL.update_Tarea(tareaTablaSeleccionado.getId_tarea(), tarea_fecha.getText(), tarea_tipo.getText(), Double.parseDouble(tarea_total.getText()), tarea_actividad.getText()
                , tarea_observaciones.getText());
        update();
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        App.actualProfesor.setNombre("Francisco");
        //cellValueFactories for table profesor
        profesorNombre.setCellValueFactory(new PropertyValueFactory("nombre"));
        profesorApellido.setCellValueFactory(new PropertyValueFactory("apellido"));
        profesorCorreo.setCellValueFactory(new PropertyValueFactory("correo"));

        //cellValueFactory for table Empresa
        empresaNombre.setCellValueFactory(new PropertyValueFactory("nombre"));
        empresaTelefono.setCellValueFactory(new PropertyValueFactory("telefono"));
        empresaCorreo.setCellValueFactory(new PropertyValueFactory("correo"));
        empresaResponsable.setCellValueFactory(new PropertyValueFactory("responsable"));
        empresaObservaciones.setCellValueFactory(new PropertyValueFactory("observaciones"));

        //CellValueFactory for table Actividades
        tareaFecha.setCellValueFactory(new PropertyValueFactory("fecha"));
        tareaTipo.setCellValueFactory(new PropertyValueFactory("tipo"));
        tareaTotal.setCellValueFactory(new PropertyValueFactory("totalHoras"));
        tareaObservaciones.setCellValueFactory(new PropertyValueFactory("observaciones"));
        tareaActividad.setCellValueFactory(new PropertyValueFactory("actividad"));
        update();

    }

    private void update() {
        updateTablesOnly();
        tarea_fecha.setText("");
        tarea_fecha.setPromptText("dd/mm/yyyy");
        tarea_tipo.setText("");
        tarea_total.setText("");
        tarea_actividad.setText("");
        tarea_observaciones.setText("");
        txtDual.setText(String.valueOf(alumnoSQL.horasDual(App.actualAlumno.getId_alumno())));
        txtFCT.setText(String.valueOf(alumnoSQL.horasFCT(App.actualAlumno.getId_alumno())));
        txtDualRes.setText(String.valueOf(530-alumnoSQL.horasDual(App.actualAlumno.getId_alumno())));
        txtFCTRest.setText(String.valueOf(530-alumnoSQL.horasFCT(App.actualAlumno.getId_alumno())));
    }

    private void updateTablesOnly() {
        ObservableList<Profesor> profesor = FXCollections.observableArrayList();
        ObservableList<Empresa> empresas = FXCollections.observableArrayList();
        ObservableList<Tarea> actividades = FXCollections.observableArrayList();

        // Integer alumnoid = App.actualAlumno.getId_alumno();


        var tareas = tareaSQL.get_TareaByAlumno(App.actualAlumno.getId_alumno());
        actividades.addAll(tareas);
        tablaTareas.getItems().clear();
        tablaTareas.setItems(actividades);

        profesor.addAll(profesorSQL.get_profesorInfo(App.actualAlumno.getId_alumno()));
        tablaProfesor.getItems().clear();
        tablaProfesor.setItems(profesor);


        empresas.addAll(empresaSQL.get_info(App.actualAlumno.getId_alumno()));
        tablaEmpresa.getItems().clear();
        tablaEmpresa.setItems(empresas);


    }
}
