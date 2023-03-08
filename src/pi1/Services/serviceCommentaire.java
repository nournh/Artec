/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pi1.Services;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import pi1.Entities.Commentaire;
import pi1.Utils.MyDB;

/**
 *
 * @author Asus
 */
    public class serviceCommentaire implements Iservice.IService<Commentaire>  {
    Connection cnx;
    Statement stm;

    public serviceCommentaire() {
        cnx = MyDB.getInstance().getCnx();

    }

    @Override
  public void add(Commentaire c) {
        try {
            String qry =  "INSERT INTO `commentaire`(`id_comm`, `date`, `text`) values ('" + c.getId_comm() + "','" + c.getDate() +  "','" + c.getText() + "')";
            stm = cnx.createStatement();

            stm.executeUpdate(qry);
        } catch (SQLException ex) {

            System.out.println(ex.getMessage());

        }
  }
    @Override
    public List<Commentaire> affciher() {
        List<Commentaire> commentaires = new ArrayList();
          try {
            String qry = "SELECT `id_comm`, `date`, `text` FROM `commentaire`";
            stm = cnx.createStatement();

            ResultSet rs = stm.executeQuery(qry);

            while (rs.next()) {                 
           
                Commentaire c = new Commentaire();
                c.setId_comm(rs.getInt("Id_comm"));
                c.setDate(rs.getString("Date"));
                c.setText(rs.getString("Text"));
                commentaires.add(c);
            }
            return commentaires;

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return commentaires;
    }

    @Override
    public Boolean modifier(Commentaire c) {
          String requete = "UPDATE `commentaire` SET  `date`='"+ c.getDate()+"',`text`='"+ c.getText()+"' WHERE Id_comm='"+c.getId_comm()+"' ";            

        try {
             stm = cnx.createStatement();
            stm.executeUpdate(requete);
            System.out.println("commentaire modifié avec succés");
            return true;
        } catch (SQLException ex) {
            System.err.println (ex.getMessage());
          
        }
        return false;
    }  

  @Override
     public Boolean supprimer(Commentaire c) {
                String req = "UPDATE commentaire SET archiver = 1 WHERE Id_comm = '"+c.getId_comm()+"' ";

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
    }


