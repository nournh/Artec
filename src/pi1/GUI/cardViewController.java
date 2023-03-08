/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pi1.GUI;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import pi1.Entities.evenement;
import pi1.Services.serviceEvenement;

/**
 *
 * @author Asus
 */
public class cardViewController implements Initializable {
     @FXML
    private Label nomgaly;
    @FXML
    private Label descripty;
    @FXML
    private Label categoriey;
    @FXML
    private Label prixed;
  
    serviceEvenement se= new serviceEvenement();
    @FXML
    private Button visitery;
    @FXML
    private ImageView imgy;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
//       throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    void setData(evenement e) {
        
        //recupere l'oeuvre (objet ) bich itnajim itrecuperi el path imta3 l'image 
    //Image image = new Image( e.getId_oeuvre().getImagePath().toURI().toString());
//    Image icon = new Image("/Image/gal.jpg");
//    imgy.setImage(icon);
    nomgaly.setText(e.getNom_Galerie());
    descripty.setText(e.getDescription_Galerie());
    categoriey.setText(String.valueOf(e.getCateg_gal()));
    prixed.setText(String.valueOf(e.getPrix()));
    
   
    
    
    }
     @FXML
     private void VisiterGalerie(ActionEvent event)throws IOException  {
       // evenement e = new evenement();
        String filter=categoriey.getText();
        
        Parent fxml ;
        if(filter.equals("artModerne")){
        fxml = FXMLLoader.load(getClass().getResource("galerie.fxml"));
        Scene scene3 = new Scene(fxml);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(scene3);
        window.show();
        }
        else if(filter.equals("artContemporain")){
        fxml = FXMLLoader.load(getClass().getResource("artCon.fxml"));
        Scene scene3 = new Scene(fxml);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(scene3);
        window.show();
        }
        else if(filter.equals("PopArt")){
        fxml = FXMLLoader.load(getClass().getResource("popart.fxml"));
        Scene scene3 = new Scene(fxml);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(scene3);
        window.show();
        }
         else if(filter.equals("opArt")){
        fxml = FXMLLoader.load(getClass().getResource("opA.fxml"));
        Scene scene3 = new Scene(fxml);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(scene3);
        window.show();
        }
}
     }
     

  
    
    


