/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.io.IOException;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import pi.Entities.Etat;
import pi.Entities.Oeuvre;
import pi.Services.ServiceOeuvre;

/**
 *
 * @author octanet
 */
public class NewFXMain extends Application {
    
    @Override
    
    public void start(Stage stage) {
      try {
                  // je veux aff une interface graphique donc c pourquoi on met FXLOADER et on recupére le url avec la methode getclass bech norbtouh bil GUI
     
          // Parent root=FXMLLoader.load(getClass().getResource("../pi/GUI/OeuvreDetails.fxml"));
           Parent root=FXMLLoader.load(getClass().getResource("../pi/GUI/AjouterOeuvre.fxml"));
               // Parent root=FXMLLoader.load(getClass().getResource("../pi/GUI/Test.fxml"));
                //Parent root=FXMLLoader.load(getClass().getResource("../pi/GUI/Listeoeuvre.fxml"));

            //Parent root=FXMLLoader.load(getClass().getResource("../pi/GUI/Gestioncategorie.fxml"));
          //Parent root=FXMLLoader.load(getClass().getResource("../pi/GUI/OeuvreDetail.fxml"));
  
          Scene scene = new Scene(root);
            stage.setTitle("ARTEC");
//            scene.getStylesheets().add("../pi/GUI/AjouterOeuvre.css");
            stage.setScene(scene); // le contenu
            stage.show();  
            
             //stage1.show();
          
            
        } catch (IOException ex) {
            System.out.println("Erreur"+ex.getMessage());
        }
       
    }
    
    

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
      
            launch(args);
    }
    
}
