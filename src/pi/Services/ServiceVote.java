/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pi.Services;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import pi.Entities.Categorie_oeuvre;
import pi.Entities.Vote;
import pi.Utils.MyDB;

/**
 *
 * @author octanet
 */
public class ServiceVote implements IIIService<Vote>  {
    
    
   
Connection cnx;
    Statement stm;

    public ServiceVote() {
        cnx = MyDB.getInstance().getCnx();

    }

   
    @Override
    public void add(Vote v) {
        
         
              try {
                String qry = "INSERT INTO `categorie_oeuvre`( `id_vote`, `id_client`,`id_oeuvre`,`nb_vote`) VALUES ('" + v.getId_vote() + "','" + v.getId_client() + "','" + v.getId_oeuvre() + "','" + v.getNb_vote() + "')";
                stm = cnx.createStatement();
                System.out.println(" Category added Successfully !");

                stm.executeUpdate(qry);
            } catch (SQLException ex) {

                System.out.println(ex.getMessage());

            }}

    @Override
    public List<Vote> afficher() {
       List<Vote> votes = new ArrayList();
        try {
            String qry = "SELECT * FROM `vote`";
            stm = cnx.createStatement();

            ResultSet rs = stm.executeQuery(qry);

            while (rs.next()) {
                Vote sv = new Vote();
                sv.setId_vote(rs.getInt("id_vote"));
                sv.setId_client(rs.getInt("id_client"));
                sv.setId_oeuvre(rs.getInt("id_oeuvre"));
                 sv.setNb_vote(rs.getInt("nb_vote"));

                
                //p.setStock(rs.getString("5"));

                votes.add(sv);
            }
            return votes;

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return votes;
  
    }

    @Override
    public Boolean modifier(Vote v) {

     try {
         String qry = "UPDATE Categorie_oeuvre SET `id_vote`='" + v.getId_vote() + "' , `id_client`='" + v.getId_client() + "' , `id_oeuvre`='" + v.getId_oeuvre() + "' , `nb_vote`='" + v.getNb_vote() + "' WHERE `id_vote`='" + v.getId_vote() + "'";
  
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
    public Boolean supprimer(Vote v) {
         try {
        String qry = "DELETE FROM vote WHERE `Id_vote`='" + v.getId_vote()+ "'";
        stm = cnx.createStatement();
        System.out.println("vote deleted Successfully !");
        stm.executeUpdate(qry);
        return true;
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
        return false;
    }}}
    
