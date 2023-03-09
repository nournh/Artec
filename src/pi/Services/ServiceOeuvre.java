/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pi.Services;

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

import pi.Entities.Categorie_oeuvre;
import pi.Entities.Oeuvre;
import pi.Utils.MyDB;
import pi.Entities.Etat;
import pi.Entities.Famille;


/**
 *
 * @author Mohamed
 */
public class ServiceOeuvre implements IService<Oeuvre> {
    
    
Connection cnx;
    Statement stm;

    public ServiceOeuvre() {
        cnx = MyDB.getInstance().getCnx();

    }

    @Override
    public void add(Oeuvre o) {
        try {
            String qry = "INSERT INTO `oeuvre`( `image`,`titre_oeuvre`, `artiste`,`Famille`, `prix_oeuvre`,`Id_cat_oeuv`,`Etat`,`archive`) VALUES ('" + o.getImage() + "','" + o.getTitre_oeuvre() + "','" + o.getArtiste() +  "','" + o.getFamille() + "','" + o.getPrix_oeuvre() + "','" + o.getId_cat_oeuv() +"','" + o.getEtat() + "','" + 0 + "')";
            stm = cnx.createStatement();
            System.out.println("Oeuvre added Successfully !");
            stm.executeUpdate(qry);
        } catch (SQLException ex) {

            System.out.println(ex.getMessage());

        }

    }
//    
//    @Override
//    public Categorie_oeuvre GetId(int id) {
//        Categorie_oeuvre categorie = new Categorie_oeuvre();
//        try {
//            String query = "Select Nom_cat_oeuv FROM categorie_oeuvre WHERE Id_cat_oeuv=" + id";
//            Statement st = cnx.createStatement();
//            ResultSet rs = st.executeQuery(query);
//            if (rs.next()) {
//                categorie.setId_cat_oeuv(rs.getInt("id_"));
//                categorie.setNom_cat_oeuv(rs.getString("Nom_cat_oeuv"));
//            }
//        } catch (SQLException ex) {
//            Logger.getLogger(ServiceOeuvre.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        return categorie;
//    }
   
    
    
     
   
    
    @Override
    public List<Oeuvre> afficher() {
        
        List<Oeuvre> oeuvres = new ArrayList();
      
        try {
            String qry = "SELECT * FROM `oeuvre` WHERE archive=0";
            
             stm = cnx.createStatement();

            ResultSet rs = stm.executeQuery(qry);
          
            while (rs.next()) {
                Oeuvre o = new Oeuvre();
                //o.setId(rs.getInt("id"));
                o.setImage(rs.getString("image"));
                o.setTitre_oeuvre(rs.getString("titre_oeuvre"));
                o.setArtiste(rs.getString("artiste"));
                o.setFamille(rs.getString("Famille"));// aappartient a quelle type d'oeuvre
                o.setPrix_oeuvre(rs.getString("prix_oeuvre"));
               // o.setNom_cat_oeuv(rs.getString("Nom_cat_oeuv"));
                o.setId_cat_oeuv(rs.getInt("Id_cat_oeuv"));
                o.setEtat(rs.getString("Etat"));
                //p.setStock(rs.getString("5"));
            // ajoutez l'objet Article à la liste de résultats
                oeuvres.add(o);
            }
            return oeuvres;

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
            // retourne la liste de résultats

        return oeuvres;
    }
    
    
    public ObservableList<Oeuvre> getall() {
        ObservableList<Oeuvre> oeuvres = FXCollections.observableArrayList();
        try {
            String req = "select * from oeuvre WHERE archive = 0";
           //String req1 =  " Select Nom_cat_oeuv FROM categorie_oeuvre WHERE Id_cat_oeuv=" + id + ";
                    
            Statement stm = cnx.createStatement();
            ResultSet rs = stm.executeQuery(req);
             
            //ResultSet rs = stm.executeQuery(req1);


            while (rs.next()) {
                Oeuvre o = new Oeuvre();
                 //o.setId(rs.getInt("Id"));
                 o.setId(rs.getInt("id"));
                o.setImage(rs.getString("image"));
                o.setTitre_oeuvre(rs.getString("titre_oeuvre"));
                o.setArtiste(rs.getString("artiste"));
                o.setFamille(rs.getString("Famille"));// aappartient a quelle type d'oeuvre
                o.setPrix_oeuvre(rs.getString("prix_oeuvre"));
               // String q="select Nom_cat_oeuv FROM categorie_oeuvre WHERE Id_cat_oeuv='"+o.getId_cat_oeuv()+"'";
             //   ResultSet rs1 = stm.executeQuery(q);
              //  o.setNom_cat_oeuv(rs1.getString("Nom_cat_oeuv"));
            //  o.setId_cat_oeuv(rs.getString("Id_cat_oeuv"));
            
                o.setEtat(rs.getString("Etat"));
              
                //o.setDescription(rs.getString("Description"));
            //o.setTitre(rs.getString("Titre"));
            //o.setCategorie(rs.getString("catégorie"));
            
               oeuvres.add(o);
            }
            System.out.print(oeuvres);

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return oeuvres;
    }
    
    
//    //FILTRAGE  hethy mteeei*
    
//    public List<Oeuvre> getOeuvresByFamille(String famille) {
//    // Get all articles from the database
//    List<Oeuvre> allOeuvres = getall();
//    
//    // Filter the articles by category
//    List<Oeuvre> filteredOeuvres = allOeuvres.stream()
//        .filter(oeuvre -> oeuvre.getFamille().equals(famille))
//        .collect(Collectors.toList());
//    
//    return filteredOeuvres;
//}
    
   

    @Override
    public Boolean modifier(Oeuvre o) {
         try {
           
            //String qry = "UPDATE oeuvre SET nom_oeuvre ='rr', prix_oeuvre ='rr', stock ='oui' WHERE id ='30' ";
            //String qry = "UPDATE oeuvre SET id`='" + t.getId() + "',nom_oeuvre`='" + t.getNom_oeuvre() + "' ,`prix_oeuvre`='" + t.getPrix_oeuvre() + "' ,`stock`='" + t.getStock() + "' WHERE `id`='" + t.getId() + "'";
    
            String qry = "UPDATE oeuvre SET  `image`='" + o.getImage() + "', `titre_oeuvre`='" + o.getTitre_oeuvre() +"' , `artiste`='" + o.getArtiste() + "', `Famille`='"+"', `prix_oeuvre`='" + o.getPrix_oeuvre() + "' , `Id_cat_oeuv`='" + o.getId_cat_oeuv() +"', `Etat`='" + o.getEtat()+  "' WHERE `Id`='" + o.getId() + "'";
  
            stm = cnx.createStatement();
            System.out.println(" Oeuvre updated Successfully !");
            stm.executeUpdate(qry);
            return true;
        } catch (SQLException ex) {

            System.out.println(ex.getMessage());

        }
         return false;
    }
    
    //UPDATE
//    
//    public void updateOeuvre(Oeuvre o)  {
//      
//    try {
//        // Get a connection to the database
//        Connection cnx = MyDB.getInstance().getCnx();
//
//        // Create the SQL statement to update the comment
//      
//        // String qry = "UPDATE oeuvre SET `id`='" + o.getId() + "', `image`='" + "', `titre_oeuvre`='" + o.getTitre_oeuvre() +"' , `artiste`='" + o.getArtiste() + "', `Famille`='"+"', `prix_oeuvre`='" + o.getPrix_oeuvre() + "' , `Id_cat_oeuv`='" + o.getId_cat_oeuv() +"', `Etat`='" + o.getEtat()+  "' WHERE `id`='" + o.getId() + "'";
//         String qry = "UPDATE oeuvre SET Id=?,titre_oeuvre=? , artiste=? , Famille=? , prix_oeuvre=?  , Id_cat_oeuv=?  , Etat=? WHERE Id=?";
//        PreparedStatement pstmt = cnx.prepareStatement(qry);
//
//        // Set the values of the parameters in the statement
//           
//        //  pstmt.setInt(1, o.getId());
//          
//        //  pstmt.setString(2, o.getImage());
//          pstmt.setString(3, o.getTitre_oeuvre());
//           pstmt.setString(4, o.getArtiste());
//            pstmt.setString(5,o.getFamille());
//         
//           pstmt.setString(6, o.getPrix_oeuvre());
//             pstmt.setInt(7, o.getId_cat_oeuv());
//          pstmt.setString(8, o.getEtat());
//
//        // Execute the update statement
//        int rowsAffected = pstmt.executeUpdate();
//
//        // Check if any rows were affected
//        if (rowsAffected > 0) {
//            System.out.println("Oeuvre updated successfully.");
//        } else {
//            System.out.println("Failed to update Oeuvre.");
//        }
//
//        // Close the database connection and statement
//        pstmt.close();
//        cnx.close();
//
//    } catch (SQLException e) {
//        System.out.println("Error updating Oeuv: " + e.getMessage());
//    }
//}
//    
    
   //DELETE PUBLIC 
    public void delete(int id) {
         try {
     
       String req = "UPDATE oeuvre SET Archive = 1 WHERE Id = " + id;
        stm = cnx.createStatement();
            System.out.println("Deleted Successfully !");
            stm.executeUpdate(req);
        } catch (SQLException ex) {
             System.out.println(ex.getMessage());
        }}

    //SUPPRIMER
    @Override
    public Boolean supprimer(Oeuvre o) {
          try {
        //String qry = "DELETE FROM oeuvre WHERE `id`='" + o.getId()+ "'";
        String qry = "UPDATE oeuvre SET archive= 1 WHERE `Id`='" + o.getId()+ "'";
        
        stm = cnx.createStatement();
        System.out.println("Oeuvre deleted Successfully !");
        stm.executeUpdate(qry);
        return true;
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
        return false;
    } }
    
    ////FILTRER LES OEUVRES BY CATEGORIE
    public List<Oeuvre> getOeuvresByFamille(String Famille) {
    // Get all articles from the database
    List<Oeuvre> allOeuvres= getall();
    
    // Filter the articles by category
    List<Oeuvre> filteredOeuvres = allOeuvres.stream()
        .filter(oeuvre -> oeuvre.getFamille().equals(Famille))
        .collect(Collectors.toList());
    
    return filteredOeuvres;
}

 
public ObservableList<Oeuvre> getFamilleForOeuvre(int catId) {
    ObservableList<Oeuvre> oeuvres = FXCollections.observableArrayList();
    try {
        String req = "select * from oeuvre where Id_cat_oeuv = ? and archive = 0";
        PreparedStatement pst = cnx.prepareStatement(req);
        pst.setInt(1, catId);
        ResultSet rs = pst.executeQuery();

        while (rs.next()) {
            Oeuvre o = new Oeuvre();
           
            
                o.setTitre_oeuvre(rs.getString("titre_oeuvre"));
                o.setArtiste(rs.getString("artiste"));
                
           
            oeuvres.add(o);
            System.out.println("Added titre: " + o.getTitre_oeuvre());
        }

    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
    return oeuvres;
}

//public List<Oeuvre> getOeuvresByArtiste(String Artiste) {
//    // Get all articles from the database
//    List<Oeuvre> allOeuvres = getall();
//    
//    // Filter the articles by category
//    List<Oeuvre> filteredOeuvres = allOeuvres.stream()
//        .filter(oeuvre -> oeuvre.getArtiste().equals(Artiste))
//        .collect(Collectors.toList());
//    
//    return filteredOeuvres;
//}
    
    //RECHERCHE

//    @Override
//    public List<Oeuvre> rechercheByArtiste() {
//        List<Oeuvre> oeuvres = new ArrayList<>();
//        String artiste = null;
//        try {
//            stm = cnx.createStatement();
//            String req = "SELECT * FROM oeuvre WHERE artiste LIKE '%" + artiste + "%';";
//            ResultSet rs = stm.executeQuery(req);
//
//            while (rs.next()) {
//                Oeuvre o = new Oeuvre();
//               o.setId(rs.getInt("id"));
//               o.setImage(rs.getString("image"));
//                o.setTitre_oeuvre(rs.getString("titre_oeuvre"));
//                o.setArtiste(rs.getString("artiste"));
//                o.setFamille(rs.getString("famille")); //aappartient a quelle type d'oeuvre
//                o.setPrix_oeuvre(rs.getString("prix_oeuvre"));
//                o.setId_cat_oeuv(rs.getInt("Id_cat_oeuv"));
//                o.setEtat(rs.getString("Etat"));
//                oeuvres.add(o);
//            }
//        } catch (SQLException ex) {
//            System.out.println(ex.getMessage());
//        }
//        return oeuvres;
//    }



}
