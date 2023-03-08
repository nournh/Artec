package pieya.Services;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import pieya.Entities.evenement;
import pieya.Entities.evenement.Categorie_gal;

import pieya.Utils.MyDB;


/**
 *
 * @author Asus
 */
public class serviceEvenement implements Iservice1.IService<evenement>  {
    Connection cnx;
    Statement stm;

    public serviceEvenement() {
        cnx = MyDB.getInstance().getCnx();

    }

   
    @Override
     public void add(evenement e) {
        try {
            String qry =  "INSERT INTO evenement`(Nom_Galerie`, Description_Galerie, Date_Gal, Nombre_Part, Nombre_Part_Rest, prix, categ_gal, Id_Res, Id_oeuvre) values ('" + e.getNom_Galerie() + "','" + e.getDescription_Galerie() +  "','" + e.getDate_Gal() + "','" + e.getNombre_Part() + "','" + e.getNombre_Part_Rest() + "','" + e.getPrix() + "','" + e.getCateg_gal() + "','" + e.getId_Res()+ "','" + e.getId_oeuvre()+ "')";
            stm = cnx.createStatement();

            stm.executeUpdate(qry);
        } catch (SQLException ex) {

            System.out.println(ex.getMessage());

        }
        

} 
    @Override
      public List<evenement> affciher() {
        List<evenement> evenements = new ArrayList();
       
        try {
            String qry = "SELECT Id_event, Nom_Galerie, Description_Galerie, Date_Gal, Nombre_Part, Nombre_Part_Rest, prix, categ_gal, Id_Res, Id_oeuvre FROM evenement";
            stm = cnx.createStatement();

            ResultSet rs = stm.executeQuery(qry);

            while (rs.next()) {                 
                String categ_galS = rs.getString("categ_gal");
                Categorie_gal categ_gall = Categorie_gal.valueOf(categ_galS);
                evenement e= new evenement();
                e.setId_event(rs.getInt("Id_event"));
                e.setNom_Galerie(rs.getString("Nom_Galerie"));
                e.setDescription_Galerie(rs.getString("Description_Galerie"));
                e.setDate_Gal(rs.getString("Date_Gal"));
                e.setNombre_Part(rs.getInt("Nombre_Part"));
                e.setNombre_Part_Rest(rs.getInt("Nombre_Part_Rest"));
                e.setPrix(rs.getInt("prix"));
                e.setCateg_gal(categ_gall);
                e.setNombre_Part(rs.getInt("Nombre_Part_Rest"));
                e.setPrix(rs.getInt("Id_Res"));
                e.setPrix(rs.getInt("Id_oeuvre"));
                evenements.add(e);
            }
            return evenements;

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return evenements;

    }
    @Override
       public Boolean modifier(evenement e) {
       String requete = "UPDATE evenement SET Nom_Galerie`='"+ e.getNom_Galerie()+"',Description_Galerie`='"+ e.getDescription_Galerie()+"',`Date_Gal`='"+  e.getDate_Gal()+"',`Nombre_Part`='"+ e.getNombre_Part()+"',`Nombre_Part_Rest`='"+ e.getNombre_Part_Rest()+"',`prix`='"+  e.getPrix()+"',`categ_gal`='"+ e.getCateg_gal()+"',`Id_Res`='"+ e.getId_Res()+"',`Id_oeuvre`='"+ e.getId_oeuvre()+"' WHERE Id_event="+ e.getId_event();            
        try {
            stm = cnx.createStatement();
            stm.executeUpdate(requete);
            System.out.println("evenement modifié avec succés");
            return true;
        } catch (SQLException ex) {
            System.err.println (ex.getMessage());
           
        }
        return false;
    }
    @Override
         public Boolean supprimer(evenement e) {
        String req = "UPDATE evenement SET archiver = 1 WHERE Id_event = '"+e.getId_event()+"' ";
        try {
 
           stm = cnx.createStatement();
            int rowsDeleted = stm.executeUpdate(req);
            if (rowsDeleted > 0) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    public void ajouterEvenement2(evenement e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

   
 }