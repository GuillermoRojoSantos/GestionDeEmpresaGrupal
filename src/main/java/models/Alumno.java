/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;
import java.io.Serializable;
/**
 *
 * @author guiro
 */
public class Alumno implements Serializable {

   

    private String nombre;
    
    private String apelidos;
    
    private Integer edad;
    
    private String contraseña;
    
    private String DNI;
    
    private String correo;
    
    private Integer telefono;
    
    private Empresa empresa;
    
    private Profesor profesor;
    
    private double horasTotalesDual;
    
    private double horasTotalesFCT;
    
    private String observaciones;
    
     public Alumno() {
    }
     
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApelidos() {
        return apelidos;
    }

    public void setApelidos(String apelidos) {
        this.apelidos = apelidos;
    }

    public Integer getEdad() {
        return edad;
    }

    public void setEdad(Integer edad) {
        this.edad = edad;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public String getDNI() {
        return DNI;
    }

    public void setDNI(String DNI) {
        this.DNI = DNI;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public Integer getTelefono() {
        return telefono;
    }

    public void setTelefono(Integer telefono) {
        this.telefono = telefono;
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    public Profesor getProfesor() {
        return profesor;
    }

    public void setProfesor(Profesor profesor) {
        this.profesor = profesor;
    }

    public double getHorasTotalesDual() {
        return horasTotalesDual;
    }

    public void setHorasTotalesDual(double horasTotalesDual) {
        this.horasTotalesDual = horasTotalesDual;
    }

    public double getHorasTotalesFCT() {
        return horasTotalesFCT;
    }

    public void setHorasTotalesFCT(double horasTotalesFCT) {
        this.horasTotalesFCT = horasTotalesFCT;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    @Override
    public String toString() {
        return "Alumno{" + "nombre=" + nombre + ", apelidos=" + apelidos + ", edad=" + edad + ", contrase\u00f1a=" + contraseña + ", DNI=" + DNI + ", correo=" + correo + ", telefono=" + telefono + ", empresa=" + empresa + ", profesor=" + profesor + ", horasTotalesDual=" + horasTotalesDual + ", horasTotalesFCT=" + horasTotalesFCT + ", observaciones=" + observaciones + '}';
    }
    
    
    
    
}
