/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projet.GUI;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;

/**
 * FXML Controller class
 *
 * @author Lenovo
 */
public class InterfaceController implements Initializable {

    @FXML
    private Button ajouter;
    @FXML
    private Button modifier;
    @FXML
    private Button supprimer;
    @FXML
    private Button afficher;
    private Button ajout;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private  void ajouter(ActionEvent event) {
        
         try {
/*
         Parent root = FXMLLoader.load(getClass().getResource("/Gui/Voyage.fxml"));
              Scene scene = new Scene(root);
              Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
              stage.setScene(scene);
              stage.show();*/
            FXMLLoader loader = new FXMLLoader(getClass().getResource("HomeArticleFXML.fxml"));
            Parent root = loader.load();
            HomeArticleFXMLController pc = loader.getController();
            
            ajouter.getScene().setRoot(root);
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        }
    }
    

    @FXML
    private void modifier(ActionEvent event) {
        try {
/*
         Parent root = FXMLLoader.load(getClass().getResource("/Gui/Voyage.fxml"));
              Scene scene = new Scene(root);
              Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
              stage.setScene(scene);
              stage.show();*/
            FXMLLoader loader = new FXMLLoader(getClass().getResource("EditArticle.fxml"));
            Parent root = loader.load();
            EditArticleController pc = loader.getController();
            
            ajouter.getScene().setRoot(root);
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        }
    
    }

    @FXML
    private void supprimer(ActionEvent event) {
        try {
/*
         Parent root = FXMLLoader.load(getClass().getResource("/Gui/Voyage.fxml"));
              Scene scene = new Scene(root);
              Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
              stage.setScene(scene);
              stage.show();*/
            FXMLLoader loader = new FXMLLoader(getClass().getResource("deleteArt.fxml"));
            Parent root = loader.load();
            DeleteArtController pc = loader.getController();
            
            ajouter.getScene().setRoot(root);
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        }
    }

    @FXML
    private void afficher(ActionEvent event) {
        try {
/*
         Parent root = FXMLLoader.load(getClass().getResource("/Gui/Voyage.fxml"));
              Scene scene = new Scene(root);
              Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
              stage.setScene(scene);
              stage.show();*/
            FXMLLoader loader = new FXMLLoader(getClass().getResource("afficherArticle.fxml"));
            Parent root = loader.load();
            AfficherArticleController pc = loader.getController();
            
            ajout.getScene().setRoot(root);
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        }
    }

    @FXML
    private void comment(ActionEvent event) {
         try {

                FXMLLoader loader = new FXMLLoader(getClass().getResource("affichercommentaire.fxml"));
            Parent root = loader.load();
            AffichercommentaireController pc = loader.getController();
            
            ajout.getScene().setRoot(root);
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        }
    }



    }
        
    
    
    

