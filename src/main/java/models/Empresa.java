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
public class Empresa implements Serializable{
    
    private String nombre;
    private int telefono;
    private String correo;
    private String respondable;
    private String observaciones;

    public Empresa() {
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getTelefono() {
        return telefono;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getRespondable() {
        return respondable;
    }

    public void setRespondable(String respondable) {
        this.respondable = respondable;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    @Override
    public String toString() {
        return "Empresa{" + "nombre=" + nombre + ", telefono=" + telefono + ", correo=" + correo + ", respondable=" + respondable + ", observaciones=" + observaciones + '}';
    }
    
    
}
