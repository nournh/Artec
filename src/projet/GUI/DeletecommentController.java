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
import projet.Services.ServiceCommentaire;

/**
 * FXML Controller class
 *
 * @author Lenovo
 */
public class DeletecommentController implements Initializable {

    @FXML
    private Button supp;
    @FXML
    private TextField id;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void supp(ActionEvent event) {
        
        int idd = Integer.parseInt(id.getText());
        supprimerCommentaire(idd);
        // autres opérations après la suppression
    }

    private void supprimerCommentaire(int idd) {
        ServiceCommentaire commentaireService = new ServiceCommentaire();
        commentaireService.delete(idd);}
    }
    
    

