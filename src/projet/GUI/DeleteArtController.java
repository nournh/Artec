/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projet.GUI;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import projet.Services.ServiceArticle;

/**
 * FXML Controller class
 *
 * @author Lenovo
 */
public class DeleteArtController implements Initializable {

    @FXML
    private TextField id;
    @FXML
    private Button supp;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void supprimer(ActionEvent event) {
       
        int idd = Integer.parseInt(id.getText());
        supprimerReclamation(idd);
        // autres opérations après la suppression
    }

    private void supprimerReclamation(int idd) {
        ServiceArticle commentaireService = new ServiceArticle();
        commentaireService.delete(idd);}
    }
    

