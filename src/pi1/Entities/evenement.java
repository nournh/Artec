/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pi1.Entities;

/**
 *
 * @author Asus
 */
public class evenement {
    public enum Categorie_gal {
    artContemporain,
    artModerne,
    PopArt,
    opArt
}
private int Id_event , Nombre_Part  ,Nombre_Part_Rest,prix,Id_Res,Id_oeuvre ;
private String Nom_Galerie, Description_Galerie , Date_Gal;
private Categorie_gal categ_gal;

    @Override
    public String toString() {
        return "evenement{" + "Id_event=" + Id_event+ ", Nom_Galerie=" + Nom_Galerie + ", Description_Galerie=" + Description_Galerie + ", Date_Gal=" + Date_Gal + ", categ_gal=" + categ_gal  + ", Nombre_Part=" + Nombre_Part +  ", Nombre_Part_Rest=" + Nombre_Part_Rest + ", prix=" + prix + ", Id_Res=" + Id_Res + ", Id_oeuvre=" + Id_oeuvre + '}';
    }

    public int getId_event() {
        return Id_event;
    }

    public void setId_event(int Id_event) {
        this.Id_event = Id_event;
    }

    public int getNombre_Part() {
        return Nombre_Part;
    }

    public void setNombre_Part(int Nombre_Part) {
        this.Nombre_Part = Nombre_Part;
    }

  

    public int getNombre_Part_Rest() {
        return Nombre_Part_Rest;
    }

    public void setNombre_Part_Rest(int Nombre_Part_Rest) {
        this.Nombre_Part_Rest = Nombre_Part_Rest;
    }

    public int getPrix() {
        return prix;
    }

    public void setPrix(int prix) {
        this.prix = prix;
    }

    public int getId_Res() {
        return Id_Res;
    }

    public void setId_Res(int Id_Res) {
        this.Id_Res = Id_Res;
    }

    public int getId_oeuvre() {
        return Id_oeuvre;
    }

    public void setId_oeuvre(int Id_oeuvre) {
        this.Id_oeuvre = Id_oeuvre;
    }

    public String getNom_Galerie() {
        return Nom_Galerie;
    }

    public void setNom_Galerie(String Nom_Galerie) {
        this.Nom_Galerie = Nom_Galerie;
    }

    public String getDescription_Galerie() {
        return Description_Galerie;
    }

    public void setDescription_Galerie(String Description_Galerie) {
        this.Description_Galerie = Description_Galerie;
    }

    public String getDate_Gal() {
        return Date_Gal;
    }

    public void setDate_Gal(String Date_Gal) {
        this.Date_Gal = Date_Gal;
    }

    public Categorie_gal getCateg_gal() {
        return categ_gal;
    }

    public evenement() {
    }

    public void setCateg_gal(Categorie_gal categ_gal) {
        this.categ_gal = categ_gal;
    }

    public evenement( int Nombre_Part, int Nombre_Part_Rest, int prix, int Id_Res, int Id_oeuvre, String Nom_Galerie, String Description_Galerie, String Date_Gal, Categorie_gal categ_gal) {
        this.Nombre_Part = Nombre_Part;
        this.Nombre_Part_Rest = Nombre_Part_Rest;
        this.prix = prix;
        this.Id_Res = Id_Res;
        this.Id_oeuvre = Id_oeuvre;
        this.Nom_Galerie = Nom_Galerie;
        this.Description_Galerie = Description_Galerie;
        this.Date_Gal = Date_Gal;
        this.categ_gal = categ_gal;
    }
     public evenement( int Id_event ,int Nombre_Part, int Nombre_Part_Rest, int prix, int Id_Res, int Id_oeuvre, String Nom_Galerie, String Description_Galerie, String Date_Gal, Categorie_gal categ_gal) {
        this.Id_event = Id_event;
        this.Nombre_Part = Nombre_Part;
        this.Nombre_Part_Rest = Nombre_Part_Rest;
        this.prix = prix;
        this.Id_Res = Id_Res;
        this.Id_oeuvre = Id_oeuvre;
        this.Nom_Galerie = Nom_Galerie;
        this.Description_Galerie = Description_Galerie;
        this.Date_Gal = Date_Gal;
        this.categ_gal = categ_gal;
    }
 public evenement( int Id_event){
this.Id_event = Id_event;
}
}
    
