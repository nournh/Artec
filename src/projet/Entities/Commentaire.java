/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projet.Entities;

import java.time.LocalDateTime;
import java.util.Date;
import javafx.collections.ObservableList;
import javafx.scene.control.TextField;

/**
 *
 * @author Lenovo
 */
public class Commentaire {
   
     
       
        private  String Contenu;
        private LocalDateTime date_c;
   private int idC;
   private int id_art;

    public Commentaire(String Contenu, int idC, int id_art) {
        this.Contenu = Contenu;
        this.idC = idC;
        this.id_art = id_art;
    }

    
public Commentaire(String Contenu,int id_art) {
    this.Contenu = Contenu;
    this.id_art = id_art;
    
}

    public Commentaire(String text) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


    public int getIdC() {
        return idC;
    }

    public void setIdC(int idC) {
        this.idC = idC;
    }
   

    public Commentaire(String Contenu,LocalDateTime date_c) {
      
      
        this.Contenu = Contenu;
       this.date_c = date_c;
    
        
    }

    public void setDate_c(LocalDateTime date_c) {
        this.date_c = date_c;
    }

    public LocalDateTime getDate_c() {
        return date_c;
    }

    public Commentaire() {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void setContenu(String Contenu) {
        this.Contenu = Contenu;
    }

   // public void setDate(String Date) {
   //     this.Date = Date;
   // }

    public String getContenu() {
        return Contenu;
    }

    public void setId_art(int id_art) {
        this.id_art = id_art;
    }

    public int getId_art() {
        return id_art;
    }

    
}

    //public String getDate() {
   //     return Date;
   // }

   

  