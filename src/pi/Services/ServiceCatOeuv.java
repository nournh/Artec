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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import pi.Entities.Categorie_oeuvre;
import pi.Entities.Oeuvre;

import pi.Utils.MyDB;
/**
 *
 * @author octanet
 */
public class ServiceCatOeuv implements IIService<Categorie_oeuvre> {

    
    
Connection cnx;
    Statement stm;

    public ServiceCatOeuv() {
        cnx = MyDB.getInstance().getCnx();

    }

    @Override
    public void add(Categorie_oeuvre cat) {
        
           if (cat.getNom_cat_oeuv().isEmpty() || cat.getDescription_cat_oeuv().isEmpty() ) {
        throw new IllegalArgumentException("Veuillez renseigner tous les champs !");
    }
             if (!cat.getDescription_cat_oeuv().matches("^[A-Z].*")) {
        throw new IllegalArgumentException("Le description doit commencer par une lettre majuscule !");
    }
    if (cat.getDescription_cat_oeuv().length() > 100) {
            throw new IllegalArgumentException("La description ne doit pas dépasser 100 caractères !");
        }
              try {
                String qry = "INSERT INTO `categorie_oeuvre`( `Nom_cat_oeuv`, `description_cat_oeuv`) VALUES ('" + cat.getNom_cat_oeuv() + "','" + cat.getDescription_cat_oeuv() + "')";
                stm = cnx.createStatement();
                System.out.println(" Category added Successfully !");

                stm.executeUpdate(qry);
            } catch (SQLException ex) {

                System.out.println(ex.getMessage());

            }}

    @Override
    public List<Categorie_oeuvre> afficher() {
       List<Categorie_oeuvre> categories = new ArrayList();
        try {
            String qry = "SELECT * FROM `categorie_oeuvre`";
            stm = cnx.createStatement();

            ResultSet rs = stm.executeQuery(qry);

            while (rs.next()) {
                Categorie_oeuvre cat = new Categorie_oeuvre();
                cat.setId_cat_oeuv(rs.getInt("Id_cat_oeuv"));
                cat.setNom_cat_oeuv(rs.getString("Nom_cat_oeuv"));
                cat.setDescription_cat_oeuv(rs.getString("description_cat_oeuv"));
                
                //p.setStock(rs.getString("5"));

                categories.add(cat);
            }
            return categories;

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return categories;
  
    }

    @Override
    public Boolean modifier(Categorie_oeuvre cat) {

     try {
         String qry = "UPDATE Categorie_oeuvre SET `Id_cat_oeuv`='" + cat.getId_cat_oeuv() + "' , `Nom_cat_oeuv`='" + cat.getNom_cat_oeuv() + "' , `Description_cat_oeuv`='" + cat.getDescription_cat_oeuv() + "' WHERE `Id_cat_oeuv`='" + cat.getId_cat_oeuv() + "'";
  
            stm = cnx.createStatement();
            System.out.println(" Category updated Successfully !");
            stm.executeUpdate(qry);
            return true;
        } catch (SQLException ex) {

            System.out.println(ex.getMessage());

        }
         return false;
    }

    @Override
    public Boolean supprimer(Categorie_oeuvre cat) {
         try {
        String qry = "DELETE FROM categorie_oeuvre WHERE `Id_cat_oeuv`='" + cat.getId_cat_oeuv()+ "'";
        stm = cnx.createStatement();
        System.out.println("Category deleted Successfully !");
        stm.executeUpdate(qry);
        return true;
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
        return false;
    }}
    
    
    public void delete(int id) {
         try {
     
       String req = "UPDATE categorie_oeuvre SET Archive = 1 WHERE id = " + id;
        stm = cnx.createStatement();
            System.out.println("Deleted Successfully !");
            stm.executeUpdate(req);
        } catch (SQLException ex) {
             System.out.println(ex.getMessage());
        }}
    
    
    
public ObservableList<Categorie_oeuvre> getall() {
       ObservableList<Categorie_oeuvre> listcategorie = FXCollections.observableArrayList();
        try {
            String req = "select * from categorie_oeuvre WHERE archive = 0";
            Statement stm = cnx.createStatement();
            ResultSet rs = stm.executeQuery(req);

            while (rs.next()) {
                Categorie_oeuvre cat = new Categorie_oeuvre();
                 cat.setId_cat_oeuv(rs.getInt("Id_cat_oeuv"));
                cat.setNom_cat_oeuv(rs.getString("Nom_cat_oeuv"));
                cat.setDescription_cat_oeuv(rs.getString("description_cat_oeuv"));
                
               listcategorie.add(cat);
            }
            System.out.print(listcategorie);

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return listcategorie;
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


 public void updateCategorie(Categorie_oeuvre cat) {
         
       try{ // String req = "UPDATE Categorie_oeuvre SET description = '" + cat.getDescription() + "', Titre = '" + article.getTitre() + "',catégorie ='" + article.getCategorie() +"' WHERE Article.Id = " + article.getId();
              String qry = "UPDATE Categorie_oeuvre SET `Id_cat_oeuv`='" + cat.getId_cat_oeuv() + "' , `Nom_cat_oeuv`='" + cat.getNom_cat_oeuv() + "' , `Description_cat_oeuv`='" + cat.getDescription_cat_oeuv() + "' WHERE `Id_cat_oeuv`='" + cat.getId_cat_oeuv() + "'";
  
     //   try {String req = "UPDATE Article SET description = '" + article.getDescription() + "', Titre = '" + article.getTitre() + "' WHERE Article.Id = " + id);

         
stm = cnx.createStatement();
            System.out.println("Updated Successfully !");
                 stm.executeUpdate(qry);
        } catch (SQLException ex) {
             System.out.println(ex.getMessage());

        }
        }



}



