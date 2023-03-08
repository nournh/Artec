/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pieya.Services;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import pieya.Entities.Coupant;
import pieya.Services.Iservice.IService;
import pieya.Utils.MyDB;

/**
 *
 * @author eyach
 */
 public class serviceCoupant implements IService<Coupant> {

    Connection cnx;
    Statement stm;

    public serviceCoupant() {
        cnx = MyDB.getInstance().getCnx();

    }

    @Override
    public void add(Coupant t) {
        try {
            String qry = "INSERT INTO `coupant`(`code`, `Valeur`) VALUES ('" + t.getCode() +"','" + t.getValeur() + "')";
            stm = cnx.createStatement();

            stm.executeUpdate(qry);
        } catch (SQLException ex) {

            System.out.println(ex.getMessage());

        }

    }

    @Override
    public ObservableList<Coupant> affciher() {
        List<Coupant> Coupants = new ArrayList();
        try {
            String qry = "SELECT code,Valeur,Id_Coupant FROM coupant where archiver=0";
            stm = cnx.createStatement();

            ResultSet rs = stm.executeQuery(qry);

            while (rs.next()) {
                Coupant t = new Coupant();
               t.setId_Coupant(rs.getInt("Id_Coupant"));
                t.setCode(rs.getString("Code"));
                t.setValeur(rs.getInt("Valeur"));
          

                Coupants.add(t);
            }
            return FXCollections.observableArrayList(Coupants);

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
         return FXCollections.observableArrayList(Coupants);

    }

   @Override
       public Boolean modifier(Coupant t) {
           System.out.println(t.getId_Coupant());
        String requete = "UPDATE `coupant` SET `Code`='" + t.getCode() + "',`Valeur`='" + t.getValeur() + "'  WHERE Id_Coupant="+ t.getId_Coupant(); 
        try {
           
            stm = cnx.createStatement();
            stm.executeUpdate(requete);
            System.out.println("Coupant modifiée avec succés");
            return true;
        } catch (SQLException ex) {
            System.err.println (ex.getMessage());
           
        }
        return false;
    }

   @Override
    public Boolean supprimer(Coupant t) {
           System.out.println(t);
        String req = "UPDATE coupant SET archiver = '1' WHERE Id_Coupant = '"+t.getId_Coupant()+"' ";
        try {
            stm = cnx.createStatement();
            stm.executeUpdate(req);
             System.out.println("Coupant modifiée avec succés");
          return true;
        } catch (SQLException ex) {
            System.err.println (ex.getMessage());
        }
         return false;
    }
    @Override
    public List<Coupant> affciherbyid(Coupant t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

  

 }