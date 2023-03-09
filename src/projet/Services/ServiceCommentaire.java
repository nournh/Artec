/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projet.Services;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.sql.Types;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import projet.Entities.Article;

import projet.Entities.Commentaire;
import projet.utils.MyDB;
/**
 *
 * @author Lenovo
 */
public  class ServiceCommentaire implements ArticleInterface<Commentaire>{
    Connection cnx;
    Statement stm;

    public ServiceCommentaire() {
        cnx = MyDB.getInstance().getCnx();

    }

    
    public void add(Commentaire commentaire) {
         if (commentaire.getContenu().isEmpty()) {
               throw new IllegalArgumentException("Veuillez renseigner tous les champs !");
         }
      //   if (article.getTitre().isEmpty() || article.getDescription().isEmpty() ) {
   // throw new IllegalArgumentException("Veuillez renseigner tous les champs !");
//}
        // if (!article.getTitre().matches("^[A-Z].*")) {
    //throw new IllegalArgumentException("Le titre doit commencer par une lettre majuscule !");
//}
/*if (article.getTitre().length() > 100) {
        throw new IllegalArgumentException("Le titre de l'article ne doit pas dépasser 100 caractères !");
    }*/

         try {
            String qry = "INSERT INTO feedback (contenu, date_c, id_art) VALUES (?, NOW(), ?)";
            PreparedStatement pstmt = cnx.prepareStatement(qry);
        pstmt.setString(1, commentaire.getContenu());
        pstmt.setInt(2, commentaire.getId_art());
        int rowsAffected = pstmt.executeUpdate();
        if (rowsAffected > 0) {
            System.out.println("Commentaire ajouté avec succès !");
        } else {
            System.out.println("Impossible d'ajouter le commentaire.");
        }
        pstmt.close();
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
}
           /* stm = cnx.createStatement();
            System.out.println("Added Successfully !");
            stm.executeUpdate(qry);
        } catch (SQLException ex) {

            System.out.println(ex.getMessage());

        }
    */
    
    
     public void updateCommentaire(Commentaire commentaire)  {
      
    try {
        // Get a connection to the database
        Connection conn = MyDB.getInstance().getCnx();

        // Create the SQL statement to update the comment
        String sql = "UPDATE feedback SET contenu=?, date_c=IFNULL(?, NOW()), id_art=? WHERE Id=?";
        PreparedStatement pstmt = conn.prepareStatement(sql);

        // Set the values of the parameters in the statement
        pstmt.setString(1, commentaire.getContenu());
        if (commentaire.getDate_c() != null) {
            pstmt.setTimestamp(2, Timestamp.valueOf(commentaire.getDate_c()));
} else {
   pstmt.setNull(2, Types.TIMESTAMP);
}
         //  pstmt.setDate(2, java.sql.Date.valueOf(LocalDate.now()));
       // } else {
         //  pstmt.setNull(2, java.sql.Types.DATE);
       // }
        pstmt.setInt(3, commentaire.getId_art());
        pstmt.setInt(4, commentaire.getIdC());

        // Execute the update statement
        int rowsAffected = pstmt.executeUpdate();

        // Check if any rows were affected
        if (rowsAffected > 0) {
            System.out.println("Commentaire updated successfully.");
        } else {
            System.out.println("Failed to update commentaire.");
        }

        // Close the database connection and statement
        pstmt.close();
        conn.close();

    } catch (SQLException e) {
        System.out.println("Error updating commentaire: " + e.getMessage());
    }
}

     
   
        

/*stm = cnx.createStatement();
            System.out.println("Updated Successfully !");
            stm.executeUpdate(req);
        } catch (SQLException ex) {
             System.out.println(ex.getMessage());

        }*/
        
    

     public void delete(int id) {
         try {
     
       String req = "UPDATE feedback SET Archive = 1 WHERE id = " + id;
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
     
      public ObservableList<Commentaire> getall() {
        ObservableList<Commentaire> posts = FXCollections.observableArrayList();
        try {
            String req = "select * from feedback  WHERE archive = 0";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);

            while (rs.next()) {
                Commentaire c = new Commentaire();
                c.setContenu(rs.getString("Contenu"));
               Timestamp timestamp = rs.getTimestamp("date_c"); 
               if (timestamp != null) {
    LocalDateTime localDateTime = timestamp.toLocalDateTime();
    c.setDate_c(localDateTime);
}
posts.add(c);
//This code checks if timestamp is not null before converting it to a LocalDateTime object. If timestamp is null, it will skip the conversion and move on to the next iteration of the loop.





               // LocalDateTime localDateTime = timestamp.toLocalDateTime();
               // c.setDate_c(localDateTime);
               

           // p.setCategorie(rs.getString("catégorie"));
           
              // posts.add(c);
            }
         //   System.out.print(posts);

        } catch (SQLException ex) {
            //System.out.println(ex.getMessage());
        }
        return posts; }
     /*public List<Commentaire> read() {
    List<Commentaire> comment = new ArrayList<>();

    try {
        String req = "SELECT * FROM commentaire  WHERE archive = 0";
        stm = cnx.createStatement();
        ResultSet rs = stm.executeQuery(req);

        while (rs.next()) {
            // créez un objet CategorieArticle à partir des résultats de la requête
            Commentaire  commentaire= new Commentaire();
//            Article.setId(rs.getInt("Id"));
            commentaire.setContenu(rs.getString("contenu"));
 commentaire.setDate_c(rs.get("Date_c"));
            
            
               

            // ajoutez l'objet Article à la liste de résultats
           comment.add(commentaire);
        }

        System.out.println("Successfully!");
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }

    // retourne la liste de résultats
    return comment;*/


    @Override
    public List<Commentaire> affciher() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Boolean modifier(Commentaire i) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     *
     * @param i
     * @return
     */
    //@Override
    @Override
    public Boolean supprimer(Commentaire i) {
      try {
     
       String req = "UPDATE 'feedback' SET 'Archive' = 1 WHERE 'id' = '" + i.getIdC() + "'";
        stm = cnx.createStatement();
      
            System.out.println("Deleted Successfully !");
           int result = stm.executeUpdate(req);
           return result>0;
        } catch (SQLException ex) {
             System.out.println(ex.getMessage());
        }
        
    

 
return false;

 

    }
public ObservableList<Commentaire> getCommentsForArticle(int articleId) {
    ObservableList<Commentaire> comments = FXCollections.observableArrayList();
    try {
        String req = "select * from feedback where id_art = ? and archive = 0";
        PreparedStatement pst = cnx.prepareStatement(req);
        pst.setInt(1, articleId);
        ResultSet rs = pst.executeQuery();

        while (rs.next()) {
            Commentaire c = new Commentaire();
            c.setContenu(rs.getString("Contenu"));
            Timestamp timestamp = rs.getTimestamp("date_c"); 
            if (timestamp != null) {
                LocalDateTime localDateTime = timestamp.toLocalDateTime();
                c.setDate_c(localDateTime);
            }
            comments.add(c);
            System.out.println("Added comment: " + c.getContenu());

        }

    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
    return comments;
}
}


