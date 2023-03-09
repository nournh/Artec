/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projet.Entities;

import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TextField;
import projet.Services.ServiceArticle;

/**
 *
 * @author Lenovo
 */


   public class Article {
      int userId;
       int id;
      private String Categorie;
        private  String Description;
        private String Titre;
   private  int  likes,dislikes;
        private List<Commentaire> commentaires;;
        private byte[] image;

    public Article(String Categorie, String Description, String Titre, byte[] image) {
        this.Categorie = Categorie;
        this.Description = Description;
        this.Titre = Titre;
        this.image = image;
    }

    public int getUserId() {
        return userId;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
public void setImage(byte[] image) {
    this.image = image;
}

    public byte[] getImage() {
        return image;
    }

    

    
    

    public int getLikes() {
        return likes;
    }

    public int getDislikes() {
        return dislikes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public void setDislikes(int dislikes) {
        this.dislikes = dislikes;
    }
    

    public Article(String Categorie,  String Description, String Titre, List<Commentaire> commentaires) {
        this.Categorie = Categorie;
       
        this.Description = Description;
        this.Titre = Titre;
        this.commentaires = commentaires;
    }
public Article(String title, String description, String category) {
    this.Titre = title;
    this.Description = description;
    this.Categorie = category;
    this.commentaires = new ArrayList<>();
}

  
public static String pathfile; 
    public static String filename="";
   
   

   
     public List<Commentaire> getCommentaires() {
        return commentaires;
    }

    public void setCommentaires(List<Commentaire> commentaires) {
        this.commentaires = commentaires;
    }

        public void ajouterCommentaire(Commentaire commentaire) {
        this.commentaires.add(commentaire);
    }

    public Article() {
    }

    public String getCategorie() {
        return Categorie;
    }

    public void setCategorie(String Categorie) {
        this.Categorie = Categorie;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String Description) {
        this.Description = Description;
    }

    public String getTitre() {
        return Titre;
    }

    public void setTitre(String Titre) {
        this.Titre = Titre;
    }

   
}