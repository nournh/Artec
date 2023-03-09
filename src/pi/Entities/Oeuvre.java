/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pi.Entities;
import pi.Entities.Famille;
import java.sql.Blob;
import java.util.List;



/**
 *
 * @author octanet
 */
import java.util.ArrayList;
public class Oeuvre {
  private int Id ,Id_cat_oeuv , archive ;
  private String titre_oeuvre ,artiste ,  prix_oeuvre ,image , Nom_cat_oeuv ;
  private String Etat , Famille;
 // private Blob image;
  
     private List<Categorie_oeuvre> categories;

  
    public Oeuvre() {
    }
    
    // CONSTRUCTEUR
//Const pour listes oeuvres
    public Oeuvre(  String image ,String titre_oeuvre, String artiste, String Famille, String prix_oeuvre,int Id_cat_oeuv, String Etat  ) {
        this.image = image;
        this.titre_oeuvre = titre_oeuvre;
        this.artiste = artiste;
        this.Famille = Famille;
        this.prix_oeuvre = prix_oeuvre;
       
        this.Id_cat_oeuv = Id_cat_oeuv;
        this.Etat = Etat;
        
        //this.archive = archive;
        //  this.stock = stock;
    } 
    
    
     

//    public String getNom_cat_oeuv() {
//        return Nom_cat_oeuv;
//    }

//    public void setNom_cat_oeuv(String Nom_cat_oeuv) {
//        this.Nom_cat_oeuv = Nom_cat_oeuv;
//    }
    
    
    public Oeuvre(  String image ,String titre_oeuvre, String artiste, String Famille, String prix_oeuvre ,int Id_cat_oeuv, String Etat ,List<Categorie_oeuvre> categories  ) {
        this.image = image;
        this.titre_oeuvre = titre_oeuvre;
        this.artiste = artiste;
        this.Famille = Famille;
        this.prix_oeuvre = prix_oeuvre;
        this.Id_cat_oeuv = Id_cat_oeuv;
        this.Etat = Etat;
        this.categories = categories;
      
    } 

    public Oeuvre(String titre_oeuvre, String artiste , String Famille, String prix_oeuvre,int Id_cat_oeuv, String Etat ) {
        // this.Id = Id;
        this.titre_oeuvre = titre_oeuvre;
        this.artiste = artiste;
        this.Famille = Famille;
        this.prix_oeuvre = prix_oeuvre;
        this.Id_cat_oeuv = Id_cat_oeuv;
        this.Etat = Etat;

    }

    public Oeuvre(String text, String text0) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Oeuvre(String text, String text0, String text1, int idd, String string) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
    
    
    
    public List<Categorie_oeuvre> getCategories() {
        return categories;
    }
//VOIDDDD!!!!!!!
    public void setCategories(List<Categorie_oeuvre> categories) {
        this.categories = categories;
    }
//ouch mthabt min cat wela categorie
        public void ajouterCategorie(Categorie_oeuvre cat) {
        this.categories.add(cat);
    }

   
    
    //IMAGE
        public static String pathfile; 
        public static String filename="";
        
    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Oeuvre(int Id, String titre_oeuvre, String artiste , String Famille) {
        this.Id = Id;
        this.titre_oeuvre = titre_oeuvre;
        this.artiste = artiste;
        this.Famille = Famille;

    }


    public Oeuvre(int Id) {
        this.Id = Id;
    }

      
    public int getId_cat_oeuv() {
        return Id_cat_oeuv;
    }

    public void setId_cat_oeuv(int Id_cat_oeuv) {
        this.Id_cat_oeuv = Id_cat_oeuv;
    }

    
    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    public String getTitre_oeuvre() {
        return titre_oeuvre;
    }

    public void setTitre_oeuvre(String titre_oeuvre) {
        this.titre_oeuvre = titre_oeuvre;
    }

    public String getPrix_oeuvre() {
        return prix_oeuvre;
    }

    public void setPrix_oeuvre(String prix_oeuvre) {
        this.prix_oeuvre = prix_oeuvre;
    }

    public String getArtiste() {
        return artiste;
    }

    public void setArtiste(String artiste) {
        this.artiste = artiste;
    }

    public String getFamille() {
        return Famille;
    }

    public void setFamille(String Famille) {
        this.Famille = Famille;
    }

    public String getEtat() {
        return Etat;
    }

    public void setEtat(String Etat) {
        this.Etat = Etat;
    }

    public int getArchive() {
        return archive;
    }

    public void setArchive(int archive) {
        this.archive = archive;
    }

    @Override
    public String toString() {
        return "Oeuvre{" + "Id=" + Id + ", Id_cat_oeuv=" + Id_cat_oeuv + ", archive=" + archive + ", titre_oeuvre=" + titre_oeuvre + ", artiste=" + artiste + ", prix_oeuvre=" + prix_oeuvre + ", image=" + image +  ", Etat=" + Etat + ", Famille=" + Famille +  '}';
    }
    

    

    public void add(List<Oeuvre> oeuvres) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    

    
}
