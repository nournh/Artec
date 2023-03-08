/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pi1.Entities;

import javafx.scene.control.TableColumn;

/**
 *
 * @author Asus
 */
public class Commentaire {
      private int id_comm ;
      private String date;
      private String text;
      
        public Commentaire(String date,String text) {
            
        this.date = date;
        this.text = text;
}
         public Commentaire(int id_comm ,String date,String text) {
         this.id_comm=id_comm;
        this.date = date;
        this.text = text;
}
         public Commentaire(int id_comm ) {
         this.id_comm=id_comm;
        
}
         public Commentaire() {
        
    }


    public int getId_comm() {
        return id_comm;
    }

    public String getDate() {
        return date;
    }

    public String getText() {
        return text;
    }
    

    public void setId_comm(int id_comm) {
        this.id_comm = id_comm;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return "commentaire{" + "id_comm=" + id_comm + ", date=" + date + ", text=" + text + '}';
    }

    public void setText(TableColumn<Commentaire, String> text) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void setDate(TableColumn<Commentaire, String> date) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void setCommentaire(String get) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public String getCommentaire() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
