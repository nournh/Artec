/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projet.GUI;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.cell.PropertyValueFactory;
import projet.Entities.Article;
import projet.Entities.Commentaire;
import projet.Services.ServiceArticle;
import projet.Services.ServiceCommentaire;
import projet.utils.MyDB;

/**
 * FXML Controller class
 *
 * @author Lenovo
 */
public class AffichercommentaireController implements Initializable {

    @FXML
    private TableColumn<Commentaire, String> contenu;
    @FXML
    private TableColumn<Commentaire, String> Date;
    @FXML
    private TableView<Commentaire> Comment;
    @FXML
    private TextArea description;
    @FXML
    private Button modifier;
    Connection cnx = MyDB.getInstance().getCnx();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       ServiceCommentaire service=new ServiceCommentaire();
          ObservableList<Commentaire> list = service.getall();
          System.out.print(list);
      contenu.setCellValueFactory(new PropertyValueFactory<>("contenu"));
          Date.setCellValueFactory(new PropertyValueFactory<>("date_c"));

          Comment.setItems(list);



    }    
    
    
     /*private void loadvoy() {
        Commentaire c = new Commentaire();
        cnx = MyDB.getInstance().getCnx();
        VID.setCellValueFactory(new PropertyValueFactory<>("id"));
        VDest.setCellValueFactory(new PropertyValueFactory<>("destination"));
        VNom.setCellValueFactory(new PropertyValueFactory<>("nom_voyage"));
        VDuree.setCellValueFactory(new PropertyValueFactory<>("duree_voyage"));
        Vdate.setCellValueFactory(new PropertyValueFactory<>("date"));
        VValibilite.setCellValueFactory(new PropertyValueFactory<>("valabilite"));
        Vimage.setCellValueFactory(new PropertyValueFactory<>("image"));
        Vprix.setCellValueFactory(new PropertyValueFactory<>("prix"));
    }*/


    @FXML
    private void add(ActionEvent event) {
         ServiceCommentaire commentaireService = new ServiceCommentaire();
        Commentaire comment = new Commentaire(description.getText());
  commentaireService.add(comment);
  ObservableList<Commentaire> currentData = Comment.getItems();
   currentData.add(comment);
   System.out.println(Comment);
  Comment.refresh();
   
    }
    
 
    
    public void deleteb() {
        ServiceCommentaire SC = new ServiceCommentaire();
        SC.delete(Comment.getSelectionModel().getSelectedItem().getIdC());
        System.out.println(Comment.getSelectionModel().getSelectedItem().getIdC());
    }
     @FXML
    private void delete(ActionEvent event) {
        deleteb();
        Comment.getItems().removeAll(Comment.getSelectionModel().getSelectedItem());
        System.out.println(Comment);
        Comment.refresh();
  

        
        }
     
    
   
    
  

    @FXML
    private void modifier(ActionEvent event) {
    
    // Get the selected Commentaire object
    Commentaire selectedComment = Comment.getSelectionModel().getSelectedItem();
    if (selectedComment == null) {
        // No comment selected, display an error message
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText("Please select a comment to edit.");
        alert.showAndWait();
        return;
    }
    
    // Open a new dialog window or form to allow the user to edit the selected comment.
    // You can use a TextInputDialog or a custom dialog.
    TextInputDialog dialog = new TextInputDialog(selectedComment.getContenu());
    dialog.setTitle("Edit Comment");
    dialog.setHeaderText(null);
    dialog.setContentText("Please enter the new comment:");
    Optional<String> result = dialog.showAndWait();
    
    if (result.isPresent()) {
        // Update the Commentaire object with the new values.
        selectedComment.setContenu(result.get());
        
        // Call the update() method of the ServiceCommentaire class, passing in the updated Commentaire object.
        ServiceCommentaire commentaireService = new ServiceCommentaire();
        commentaireService.updateCommentaire(selectedComment);
        
        // Refresh the Comment ListView to reflect the changes.
        Comment.refresh();
    }
}
    

}
  
  
   
        
    
    
