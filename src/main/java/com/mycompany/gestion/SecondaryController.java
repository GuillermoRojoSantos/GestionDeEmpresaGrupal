package com.mycompany.gestion;

import com.mysql.cj.conf.PropertyDefinitions;
import dao.AlumnoDAOMySQL;
import dao.EmpresaDAOMySQL;
import dao.ProfesorDAOMySQL;
import dao.TareaDAOMySQL;
import javafx.beans.property.Property;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import model.Alumno;
import model.Empresa;
import model.Tarea;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class SecondaryController implements Initializable {


    static AlumnoDAOMySQL alumnoSQL = new AlumnoDAOMySQL();
    static ProfesorDAOMySQL profesorSQL = new ProfesorDAOMySQL();
    static TareaDAOMySQL tareaSQL = new TareaDAOMySQL();
    static EmpresaDAOMySQL empresaSQL = new EmpresaDAOMySQL();
    Alumno alumnoTablaSeleccionado = null;
    Empresa empresaTablaSeleccionado = null;

    @FXML
    private TextField alumno_apellido;

    @FXML
    private Button alumno_borrar;

    @FXML
    private Button alumno_borrar1;

    @FXML
    private TextField alumno_correo;

    @FXML
    private TextField alumno_dni;

    @FXML
    private TextField alumno_empresa;

    @FXML
    private TextField alumno_fecha;

    @FXML
    private Button alumno_introducir;

    @FXML
    private Button alumno_introducir1;

    @FXML
    private Tab alumno_list;

    @FXML
    private Button alumno_modificar;

    @FXML
    private Button alumno_modificar1;

    @FXML
    private TextField alumno_nombre;

    @FXML
    private TextField alumno_observaciones;

    @FXML
    private TextField alumno_contraseña;

    @FXML
    private TableColumn<Alumno, String> apellidosAlumno;

    @FXML
    private TableColumn<Alumno, String> telefonoAlumno;

    @FXML
    private Button atras;

    @FXML
    private TableColumn<Alumno, Double> dualAlumno;

    @FXML
    private TableColumn<Alumno, Double> fctAlumno;

    @FXML
    private TableColumn<Alumno, String> correoAlumno;

    @FXML
    private TableColumn<Empresa, String> correoEmpresa;

    @FXML
    private TableColumn<Alumno, String> dniAlumno;

    @FXML
    private TableColumn<Alumno, String> empresaAlumno;

    @FXML
    private Button empresa_borrar;

    @FXML
    private TextField empresa_correo;

    @FXML
    private Button empresa_introducir;

    @FXML
    private Tab empresa_list;

    @FXML
    private Button empresa_modificar;

    @FXML
    private TextField empresa_nombre;

    @FXML
    private TextField empresa_observaciones;

    @FXML
    private TextField empresa_responsable;

    @FXML
    private TextField empresa_telefono;

    @FXML
    private TableColumn<Tarea, String> fechaActividad;

    @FXML
    private TableColumn<Alumno, String> fechaNAlumno;

    @FXML
    private TableColumn<Alumno, String> nombreAlumno;

    @FXML
    private TableColumn<Empresa, String> nombreEmpresa;

    @FXML
    private TableColumn<Empresa, Integer> numeroEmpresa;

    @FXML
    private TableColumn<Tarea, String> observacionesActividad;

    @FXML
    private TableColumn<Alumno, String> observacionesAlumno;

    @FXML
    private TableColumn<Empresa, String> observacionesEmpresa;

    @FXML
    private TableColumn<Alumno, String> profesorAlumno;

    @FXML
    private TableColumn<Tarea, String> realizadaActividad;

    @FXML
    private TableColumn<Empresa, String> responsableEmpresa;

    @FXML
    private TableColumn<Tarea, String> tipoActividad;

    @FXML
    private TableColumn<Tarea, String> totalHorasActividad;

    @FXML
    private TableView<Alumno> tablaAlumno;

    @FXML
    private TableView<Empresa> tablaEmpresa;

    @FXML
    private TableView<Tarea> tablaActividades;

    @FXML
    private TextField alumno_telefono;

    @FXML
    private Tab pestañaActividades;

    @FXML
    private void switchToPrimary() throws IOException {
        App.setRoot("primary");
    }

    @FXML
    void selectAlumno(MouseEvent event) {
        alumnoTablaSeleccionado = tablaAlumno.getSelectionModel().getSelectedItem();
        alumno_nombre.setText(alumnoTablaSeleccionado.getNombre());
        alumno_apellido.setText(alumnoTablaSeleccionado.getApellidos());
        alumno_dni.setText(alumnoTablaSeleccionado.getDNI());
        alumno_telefono.setText(alumnoTablaSeleccionado.getTelefono());
        alumno_fecha.setText(alumnoTablaSeleccionado.getFechaN());
        alumno_empresa.setText(alumnoTablaSeleccionado.getEmpresa());
        alumno_correo.setText(alumnoTablaSeleccionado.getCorreo());
        alumno_contraseña.setText(alumnoTablaSeleccionado.getContraseña());
        alumno_observaciones.setText(alumnoTablaSeleccionado.getObservaciones());
        System.out.println(alumnoTablaSeleccionado.getTelefono());
        updateTablesOnly();
    }

    @FXML
    void addAlumno(ActionEvent event) {
        alumnoSQL.new_alumno(alumno_nombre.getText(), alumno_apellido.getText(), alumno_contraseña.getText(),
                alumno_dni.getText(), alumno_fecha.getText(), alumno_correo.getText(), alumno_telefono.getText(),
                alumno_empresa.getText(), App.actualProfesor.getNombre(), alumno_observaciones.getText());
        update();
    }

    @FXML
    void borrarAlumno(ActionEvent event) {
        alumnoSQL.del_alumno(alumnoTablaSeleccionado.getId_alumno());
        updateTablesOnly();
    }

    @FXML
    void modificarAlumno(ActionEvent event) {
        alumnoSQL.up_alumno(alumnoTablaSeleccionado.getId_alumno(), alumno_nombre.getText(), alumno_apellido.getText(), alumno_contraseña.getText(),
                alumno_dni.getText(), alumno_fecha.getText(), alumno_correo.getText(), alumno_telefono.getText(),
                alumno_empresa.getText(), App.actualProfesor.getNombre(), alumno_observaciones.getText());
        update();
    }

    @FXML
    void getEmpresaTabla(MouseEvent event) {
        empresaTablaSeleccionado = tablaEmpresa.getSelectionModel().getSelectedItem();
        empresa_nombre.setText(empresaTablaSeleccionado.getNombre());
        empresa_telefono.setText(String.valueOf(empresaTablaSeleccionado.getTelefono()));
        empresa_correo.setText(empresaTablaSeleccionado.getCorreo());
        empresa_responsable.setText(empresaTablaSeleccionado.getResponsable());
        empresa_observaciones.setText(empresaTablaSeleccionado.getObservaciones());
        updateTablesOnly();
    }

    @FXML
    void addEmpresa(ActionEvent event) {
        empresaSQL.new_empresa(empresa_nombre.getText(), Integer.parseInt(empresa_telefono.getText()), empresa_correo.getText(),
                empresa_responsable.getText(), empresa_observaciones.getText());
        update();
    }

    @FXML
    void delEmpresa(ActionEvent event) {
        empresaSQL.del_empresa(empresaTablaSeleccionado.getNombre());
        update();
    }

    @FXML
    void modEmpresa(ActionEvent event) {
        empresaSQL.up_empresa(empresaTablaSeleccionado.getNombre(), empresa_nombre.getText(), Integer.parseInt(empresa_telefono.getText()), empresa_correo.getText(),
                empresa_responsable.getText(), empresa_observaciones.getText());
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //App.actualProfesor.setNombre("Francisco");
        //cellValueFactories for table alumnos
        nombreAlumno.setCellValueFactory(new PropertyValueFactory("nombre"));
        apellidosAlumno.setCellValueFactory(new PropertyValueFactory("apellidos"));
        dniAlumno.setCellValueFactory(new PropertyValueFactory("DNI"));
        telefonoAlumno.setCellValueFactory(new PropertyValueFactory("telefono"));
        fechaNAlumno.setCellValueFactory(new PropertyValueFactory("fechaN"));
        correoAlumno.setCellValueFactory(new PropertyValueFactory("correo"));
        empresaAlumno.setCellValueFactory(new PropertyValueFactory("empresa"));
        profesorAlumno.setCellValueFactory(new PropertyValueFactory("profesor"));
        observacionesAlumno.setCellValueFactory(new PropertyValueFactory("observaciones"));
        dualAlumno.setCellValueFactory(new PropertyValueFactory("horasDual"));
        fctAlumno.setCellValueFactory(new PropertyValueFactory("horasFCT"));

        //cellValueFactory for table Empresa
        nombreEmpresa.setCellValueFactory(new PropertyValueFactory("nombre"));
        numeroEmpresa.setCellValueFactory(new PropertyValueFactory("telefono"));
        correoEmpresa.setCellValueFactory(new PropertyValueFactory("correo"));
        responsableEmpresa.setCellValueFactory(new PropertyValueFactory("responsable"));
        observacionesEmpresa.setCellValueFactory(new PropertyValueFactory("observaciones"));

        //CellValueFactory for table Actividades
        fechaActividad.setCellValueFactory(new PropertyValueFactory("fecha"));
        tipoActividad.setCellValueFactory(new PropertyValueFactory("tipo"));
        totalHorasActividad.setCellValueFactory(new PropertyValueFactory("totalHoras"));
        observacionesActividad.setCellValueFactory(new PropertyValueFactory("observaciones"));
        realizadaActividad.setCellValueFactory(new PropertyValueFactory("actividad"));
        update();
        System.out.println(empresaSQL.getAllByProfesor(App.actualProfesor));
    }

    private void update() {
        updateTablesOnly();
        alumno_nombre.setText("");
        alumno_apellido.setText("");
        alumno_dni.setText("");
        alumno_fecha.setText("");
        alumno_fecha.setPromptText("yyyy-mm-dd");
        alumno_empresa.setText("");
        alumno_correo.setText("");
        alumno_contraseña.setText("");
        alumno_observaciones.setText("");
        alumno_telefono.setText("");

        empresa_nombre.setText("");
        empresa_telefono.setText("");
        empresa_correo.setText("");
        empresa_responsable.setText("");
        empresa_observaciones.setText("");

    }

    private void updateTablesOnly() {
        ObservableList<Alumno> alumnos = FXCollections.observableArrayList();
        ObservableList<Empresa> empresas = FXCollections.observableArrayList();
        ObservableList<Tarea> actividades = FXCollections.observableArrayList();

        alumnos.addAll(alumnoSQL.alumnolist(App.actualProfesor));

        tablaAlumno.getItems().clear();
        tablaAlumno.setItems(alumnos);

        empresas.addAll(empresaSQL.getAllByProfesor(App.actualProfesor));
        tablaEmpresa.getItems().clear();
        tablaEmpresa.setItems(empresas);

        if (alumnoTablaSeleccionado != null) {
            actividades.addAll(tareaSQL.get_TareaByAlumno(alumnoTablaSeleccionado.getId_alumno()));
            tablaActividades.getItems().clear();
            tablaActividades.setItems(actividades);
        }
    }

}