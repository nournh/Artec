/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package projet.Services;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import projet.Entities.Article;

import projet.utils.MyDB;

public  class ServiceArticle implements ArticleInterface<Article>{
    Connection cnx;
    Statement stm;

    public ServiceArticle() {
        cnx = MyDB.getInstance().getCnx();

    }

    @Override
    public void add(Article article) {
         if (article.getTitre().isEmpty() || article.getDescription().isEmpty()|| article.getCategorie().isEmpty() ) {
    throw new IllegalArgumentException("Veuillez renseigner tous les champs !");
}
         if (!article.getTitre().matches("^[A-Z].*")) {
    throw new IllegalArgumentException("Le titre doit commencer par une lettre majuscule !");
}
if (article.getTitre().length() > 100) {
        throw new IllegalArgumentException("Le titre de l'article ne doit pas dépasser 100 caractères !");
    }

         try {
            String qry = "INSERT INTO article(Id,catégorie , Description, Titre) VALUES (null,  '" + article.getCategorie() + "', '" + article.getDescription() +"', '" + article.getTitre() + "')";
            stm = cnx.createStatement();
            System.out.println("Added Successfully !");
            stm.executeUpdate(qry);
        } catch (SQLException ex) {

            System.out.println(ex.getMessage());

        }
    
    }
     public void updateArticle(Article article) {
         
       try{  String req = "UPDATE Article SET description = '" + article.getDescription() + "', Titre = '" + article.getTitre() + "',catégorie ='" + article.getCategorie() +"' WHERE Article.Id = " + article.getId();

     //   try {String req = "UPDATE Article SET description = '" + article.getDescription() + "', Titre = '" + article.getTitre() + "' WHERE Article.Id = " + id);

         
stm = cnx.createStatement();
            System.out.println("Updated Successfully !");
                 stm.executeUpdate(req);
        } catch (SQLException ex) {
             System.out.println(ex.getMessage());

        }
        }
    

     public void delete(int id) {
         try {
     
       String req = "UPDATE article SET Archive = 1 WHERE id = " + id;
        stm = cnx.createStatement();
            System.out.println("Deleted Successfully !");
            stm.executeUpdate(req);
        } catch (SQLException ex) {
             System.out.println(ex.getMessage());
        }}
     
    /* public void read()  {
         try {
        String req = "SELECT * FROM article ";
        stm = cnx.createStatement();
            System.out.println(" Successfully !");
            stm.executeQuery(req);
     }  catch (SQLException ex) {
             System.out.println(ex.getMessage());
          
     }*/
     
     public ObservableList<Article> getall() {
        ObservableList<Article> posts = FXCollections.observableArrayList();
        try {
            String req = "select * from article WHERE archive = 0";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);

            while (rs.next()) {
                Article p = new Article();
                p.setDescription(rs.getString("Description"));
            p.setTitre(rs.getString("Titre"));
            p.setCategorie(rs.getString("catégorie"));
            
               posts.add(p);
            }
            System.out.print(posts);

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return posts;
    }
    @Override
    public List<Article> affciher() {
         List<Article> articlee = new ArrayList<>();

    try {
        String req = "SELECT * FROM article  WHERE archive = 0;";
        stm = cnx.createStatement();
        ResultSet rs = stm.executeQuery(req);

        while (rs.next()) {
            // créez un objet CategorieArticle à partir des résultats de la requête
            Article art = new Article();
//            Article.setId(rs.getInt("Id"));
            art.setDescription(rs.getString("Description"));
            art.setTitre(rs.getString("Titre"));
            art.setCategorie(rs.getString("catégorie"));
            
            
               

            // ajoutez l'objet Article à la liste de résultats
            articlee.add(art);
        }

        System.out.println("Successfully!");
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }

    // retourne la liste de résultats
    return articlee;

    }
public List<Article> getArticlesByCategory(String category) {
    // Get all articles from the database
    List<Article> allArticles = getall();
    
    // Filter the articles by category
    List<Article> filteredArticles = allArticles.stream()
        .filter(article -> article.getCategorie().equals(category))
        .collect(Collectors.toList());
    
    return filteredArticles;
}

    @Override
    public Boolean modifier(Article i) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Boolean supprimer(Article i) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


 


   }

  
