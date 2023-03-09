/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pi.GUI;

import java.awt.Insets;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import pi.Entities.Categorie_oeuvre;
import pi.Entities.Oeuvre;
import pi.Services.ServiceCatOeuv;
import pi.Services.ServiceOeuvre;
import pi.Entities.Categorie_oeuvre;

import pi.Entities.Famille;
import pi.Entities.Etat;
/**
 * FXML Controller class
 *
 * @author octanet
 */
public class TestController implements Initializable {

    @FXML
    private ScrollPane scrol;
    @FXML
    private GridPane grid;
    
    
     private TableView<Oeuvre> tableoeuvre;
    private List<Categorie_oeuvre> categories;

    
    private List<Oeuvre> oeuvres = new ArrayList<>();
    
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        oeuvres.addAll(getData());
        
        int column= 0 ;
        int row = 0 ;
      try {
        for(int i = 0; i< oeuvres.size(); i++){
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("Oeuvre.fxml"));
           
             AnchorPane anchorPane = fxmlLoader.load();
                System.out.println(" taaditou1111");
            Oeuvre o = oeuvres.get(i);
             System.out.println(" taad");
            OeuvreController oeuvreController = fxmlLoader.getController();
            System.out.println(" taaditou52554");
            oeuvreController.setData(oeuvres.get(i));
            
             //(child,coloumn,row)
            
            if (column == 3) {
                column = 0 ;
                row++;
            }
          grid.add(anchorPane, column++, row); 
        
         //  GridPane.setMargin(anchorPane.new Insets(5));
        }
        
        
        } catch (IOException ex) {
                Logger.getLogger(TestController.class.getName()).log(Level.SEVERE, null, ex);
            }}
            
     
    
    
    
    private List<Oeuvre> getData(){
        List<Oeuvre> oeuvres = new ArrayList<>();
        Oeuvre o;
        for(int i=0; i<20; i++){
                 o = new Oeuvre();
                o.setTitre_oeuvre("Titre");
                o.setArtiste("artiste");
                o.setImage("/assets/dd.png");
                oeuvres.add(o);
    }
        return oeuvres;
    }

    private static class Insets {

        public Insets(int i) {
        }
    }

}
