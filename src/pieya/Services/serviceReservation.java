/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pieya.Services;

import java.sql.Connection;
import java.sql.ResultSet;
//import com.mysql.jdbc.*;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import static javax.swing.DropMode.ON;
import pieya.Entities.Reservation;
import pieya.Entities.Reservation.Categorie_gal;
import pieya.Services.Iservice.IService;
import pieya.Utils.MyDB;

/**
 *
 * @author eyach
 */
 public class serviceReservation implements IService<Reservation> {

    Connection cnx;
    Statement stm;

    public serviceReservation() {
        cnx = MyDB.getInstance().getCnx();

    }

    @Override
    public void add(Reservation r) {
        //try {
            //String qry ="INSERT INTO `reservation` (`Date`, `Id_event`, `Id_user`) VALUES ('" + r.getDate()+ "',  (SELECT `Id_event` FROM `evenement` WHERE `Nom_Galerie` = '" +r.getId_event() + "'),  (SELECT `Id_user` FROM `utilisateur` WHERE `prenom` = '" + r.getId_user() + "'))";
          // String qry ="INSERT INTO `reservation` (`Date`,` Id_event`, `Id_user`)  VALUES ('" + r.getDate()+ "',  (SELECT e.Id_event FROM evenement e inner join reservation r ON e.Id_event = r.Id_event ),(SELECT u.`Id_user` FROM `utilisateur` u  inner join reservation r ON   u.Id_user = r.Id_user))";
           // String qry ="INSERT INTO `reservation` (`Date`,`Id_event`,`Id_user`)  VALUES ('" + r.getDate()+ "',(SELECT e.Id_event FROM evenement e inner join reservation r ON e.Id_event = r.Id_event ),(SELECT u.Id_user FROM utilisateur u inner join reservation r ON u.Id_user = r.Id_user))";
            
           //stm = cnx.createStatement();
          // String qry_user ="SELECT u.Id_user FROM utilisateur u inner join reservation r ON u.Id_user = r.Id_user";
           //String qry_user ="SELECT u.Id_user FROM utilisateur u where u.Id_user = 1";
           
              //System.out.println(qry_user);

          // ResultSet rs = stm.executeQuery(qry_user);
            
            // System.out.println(rs);
             // String qry_event="SELECT e.Id_event FROM evenement e where e.Id_event = 1";
           
            //  System.out.println(qry_event);

         //  ResultSet rs_event  = stm.executeQuery(qry_event);
            
           //  System.out.println(rs_event);
           
       // } catch (SQLException ex) {

           // System.out.println(ex.getMessage());
           try {
               System.out.println(r);
               
            String qry =  "INSERT INTO `reservation`(`Date`,`Id_event`,`id_user`) values ('" + r.getDate()+ "','" + r.getId_event()+  "','" + r.getid_user()+ "')";
            stm = cnx.createStatement();

            stm.executeUpdate(qry);
        } catch (SQLException ex) {

            System.out.println(ex.getMessage());

        }


        }

    

     @Override
    public ObservableList<Reservation> affciher() {
        List<Reservation> Reservations = new ArrayList();
        try {
            String qry = "Select r.Id_Res, r.Payer, e.categ_gal,e.Nom_Galerie,e.Date_Gal,e.prix from evenement e inner join reservation r ON e.Id_event=r.Id_event where r.archiver=0 ; ";
            stm = cnx.createStatement();

            ResultSet rs = stm.executeQuery(qry);

            while (rs.next()) {
              String categ_galS = rs.getString("e.categ_gal");
              Categorie_gal categ_gall = Categorie_gal.valueOf(categ_galS);
             
                Reservation r = new Reservation();
      
                r.setId_Res(rs.getInt("r.Id_Res"));
                r.setPayer(rs.getInt("r.Payer"));
                r.setDateGalerie(rs.getString("e.Date_Gal"));
                r.setNomGalerie(rs.getString("e.Nom_galerie"));
                r.setCateg_gal(categ_gall);
              
                r.setPrix(rs.getInt("e.Prix"));

      


                Reservations.add(r);
            }
           



        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
       return FXCollections.observableArrayList(Reservations);

    }

    @Override
       public Boolean modifier(Reservation r) {
        String requete = "UPDATE `reservation` SET `Date`='" + r.getDate() + "',`Id_event`='" + r.getId_event() + "',`Id_user`='" + r.getid_user() + "'  WHERE Id_Res="+ r.getId_Res(); 
        try {
           
            stm = cnx.createStatement();
            stm.executeUpdate(requete);
            System.out.println("reservation modifiée avec succés");
            return true;
        } catch (SQLException ex) {
            System.err.println (ex.getMessage());
           
        }
        return false;
    }
    @Override
    public Boolean supprimer(Reservation r) {
           System.out.println(r);
        String req = "UPDATE reservation SET archiver ='1' WHERE Id_Res = '"+r.getId_Res()+"' ";
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

    @Override
    public List<Reservation> affciherbyid(Reservation r) {
         List<Reservation> Reservations = new ArrayList();
        try {
            String qry = "SELECT * FROM Reservation where Id_Res="+ r.getId_Res();;
            stm = cnx.createStatement();

            ResultSet rs = stm.executeQuery(qry);

            while (rs.next()) {
               
                r.setId_Res(rs.getInt("Id_Res"));
                r.setDate(rs.getString("Date"));
                r.setId_event(rs.getInt("Id_event"));
                r.setid_user(rs.getInt("id_user"));

                Reservations.add(r);
            }
            return Reservations;

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return Reservations;

    }

   
        
    }
 
