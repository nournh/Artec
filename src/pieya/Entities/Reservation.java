/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pieya.Entities;

/**
 *
 * @author eyach
 */
public class Reservation {

    public enum Categorie_gal {
        artContemporain,
        artModerne,
        PopArt,
        opArt
    }

    private int Id_Res, Id_event, id_user, Prix, Payer;
    private String Date, nomGalerie, dateGalerie, Nom, Prenom, Email;
    private Categorie_gal categ_gal;
    double PrixR;

    public Reservation() {
    }

    public Reservation(int Id_Res, String Date, int Id_event, int id_user) {
        this.Id_Res = Id_Res;
        this.Date = Date;
        this.Id_event = Id_event;
        this.id_user = id_user;
    }

    public Reservation(String Date, int Id_event, int id_user) {

        this.Date = Date;
        this.Id_event = Id_event;
        this.id_user = id_user;
    }

    public void setId_event(int Id_event) {
        this.Id_event = Id_event;
    }

    public void setPrixR(double Id_event) {
        this.PrixR = PrixR;
    }

    public void setid_user(int id_user) {
        this.id_user = id_user;
    }

    public void setPayer(int Payer) {
        this.Payer = Payer;
    }

    public void setPrix(int Prix) {
        this.Prix = Prix;
    }

    public void setCateg_gal(Categorie_gal categ_gal) {
        this.categ_gal = categ_gal;
    }

    public void setNom(String Nom) {
        this.Nom = Nom;
    }

    public void setPrenom(String Prenom) {
        this.Prenom = Prenom;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public void setNomGalerie(String nomGalerie) {
        this.nomGalerie = nomGalerie;
    }

    public void setDateGalerie(String dateGalerie) {
        this.dateGalerie = dateGalerie;
    }

    public Reservation(int Id_Res) {

        this.Id_Res = Id_Res;
    }

    public int getId_Res() {
        return Id_Res;
    }
    
    public double getPrixR() {
        return PrixR;
    }

    public int getPayer() {
        return Payer;
    }

    public void setId_Res(int Id_Res) {
        this.Id_Res = Id_Res;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String Date) {
        this.Date = Date;
    }

    public int getId_event() {
        return Id_event;
    }

    public Categorie_gal getCateg_gal() {
        return categ_gal;
    }

    public int getPrix() {
        return Prix;
    }

    public String getNomGalerie() {
        return nomGalerie;
    }

    public String getNom() {
        return Nom;
    }

    public String getPrenom() {
        return Prenom;
    }

    public String getEmail() {
        return Email;
    }

    public String getDateGalerie() {
        return dateGalerie;
    }

    public int getid_user() {
        return id_user;
    }

    @Override
    public String toString() {
        return "Reservation{" + "Id_Res=" + Id_Res + ", Id_event=" + Id_event + ", id_user=" + id_user + ", Prix=" + Prix + ", Date=" + Date + ", categ_gal=" + categ_gal + ", nomGalerie=" + nomGalerie + ", dateGalerie=" + dateGalerie + ", payer=" + Payer + '}';
    }

}
