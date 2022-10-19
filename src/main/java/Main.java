/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.Alumno;

/**
 *
 * @author guiro
 */
public class Main {
    
    static ArrayList<models.Alumno> clase;
    static final String ARCHIVO = "data.bin";
    
    static{
        System.out.println("iniciando...");
        clase= new ArrayList<>();
    }
    
    public static void saveData(){
        
        try(var fos = new FileOutputStream(ARCHIVO);
            var oos = new ObjectOutputStream(fos)){
            oos.writeObject(clase);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        
             
}

    public static void loadData(){
        
        if(!new File(ARCHIVO).exists()) return;
        
        try (FileInputStream fis = new FileInputStream(ARCHIVO);
             ObjectInputStream ois = new ObjectInputStream(fis)) {
                
                clase = (ArrayList<Alumno>) ois.readObject();
                                   
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        loadData();
        
        Scanner sc = new Scanner(System.in);
        
        while (sc.hasNext()){
            var a = new Alumno();
            a.setNombre(sc.nextLine());
            a.setApelidos(sc.nextLine());
            a.setDNI(sc.nextLine());
            clase.add(a);
            break;
        }
        
        System.out.println("-----------------------------------");
        saveData();
        System.out.println(clase);
        
        
    }
    
}
