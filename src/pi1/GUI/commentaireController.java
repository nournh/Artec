/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pi1.GUI;

import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import pi1.Entities.Commentaire;

import pi1.Services.serviceCommentaire;


/**
 *
 * @author Asus
 */
public class commentaireController implements Initializable {
     /**
     * Initializes the controller class.
     */
    @FXML
    private TextField comied;
    @FXML
    private DatePicker datey;
    @FXML
    private Button ajty;
    @FXML
    private Button modify;
    @FXML
    private Button supprimery;
    @FXML
    private Button affichery;
//     @FXML
//    private Button retoured;
    @FXML
    private TableView<Commentaire> tvtablec;
     @FXML
    private TableColumn<Commentaire, String> date; 
    @FXML
    private TableColumn<Commentaire, String> text;
      @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
 
       serviceCommentaire sc = new serviceCommentaire();
       tvtablec.getItems().addAll(sc.affciher());
    }   
     @FXML
    private void faza() {
        Commentaire c = tvtablec.getSelectionModel().getSelectedItem();
        System.out.println(c);
        if (tvtablec.getSelectionModel().isEmpty()) {
            System.out.println("No item is selected");
        } else {
            comied.setText(c.getText());
            
            
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDate date = LocalDate.parse(c.getDate(), formatter);
            datey.setValue(date);
        }
    }
     @FXML
    private void ajouter(ActionEvent event) {
       
         String text = (comied.getText());
         System.out.println(text);
         LocalDate Date =  datey.getValue();
         String date = Date.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
         System.out.println(date);
         Commentaire c = new Commentaire();
         c.setDate( date);
         c.setText(text);
         System.out.println( c.toString());
         serviceCommentaire sc = new serviceCommentaire();
         sc.add(c);
         tvtablec.getItems().add(c);
    
}
    @FXML
     private void affciher(ActionEvent event) {
        serviceCommentaire sc = new serviceCommentaire();
 
         date.setCellValueFactory(new PropertyValueFactory<Commentaire , String> ("date"));
         text.setCellValueFactory(new PropertyValueFactory<Commentaire , String> ("text"));
         ObservableList<Commentaire>  commentaire =  tvtablec.getItems();
         tvtablec.setItems(commentaire);  
         System.out.println(sc.affciher());
    }
     
     
     @FXML
      private void modifier(ActionEvent event) {
          
//          commentaire c = new commentaire();
//        serviceCommentaire sc = new serviceCommentaire();
//        String text = (comied.getText());
//      
//         LocalDate date =  datey.getValue();
//         String dateString = date.format(DateTimeFormatter.ofPattern("yyyy-mm-dd"));
//         c.setText(text);
//         c.setDate(dateString);
//          tvtablec.setOnMouseClicked((MouseEvent bof) -> {
//        if (bof.getClickCount() == 2) {
//        commentaire selectedcommentaire = tvtablec.getSelectionModel().getSelectedItem();
//        if (selectedcommentaire != null) {
//            // Create a TextInputDialog to get the new value for the selected item
//            TextInputDialog dialog = new TextInputDialog(selectedcommentaire.getCommentaire());
//            // Set the dialog title and content text
//            dialog.setTitle("Modifier un commentaire");
//            dialog.setHeaderText(null);
//            dialog.setContentText("Nouveau commentaire:");
//
//            // Show the dialog box and wait for user input
//            Optional<String> result = dialog.showAndWait();
//
//            // Update the selected item if the user clicked OK
//            if (result.isPresent()) {
//                selectedcommentaire.setCommentaire(result.get());
//                // Call the update method of the service class to update the selected item in the database
//                serviceCommentaire sc = new serviceCommentaire();
//                sc.modifier(selectedcommentaire);
//                // Refresh the TableView
//                tvtablec.refresh();
//            }
//        }
//    }
//});

              Commentaire c = tvtablec.getSelectionModel().getSelectedItem();
              serviceCommentaire sc = new serviceCommentaire();
              LocalDate Date= datey.getValue();
              String dateString = Date.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
              String text = (comied.getText());
              c.setText(text);
              c.setDate(dateString);
              sc.modifier(c);
              
      }
      @FXML
      private void supprimer (ActionEvent event){
//           commentaire selectedcommentaire = tvtablec.getSelectionModel().getSelectedItem();
//        if (selectedcommentaire != null) {
//            // Create an instance of serviceEvenement
//            serviceCommentaire sc = new serviceCommentaire();
//            // Call the supprimer(galerie) method
//            boolean result = sc.supprimer(selectedcommentaire); 
        tvtablec.setOnMouseClicked(next -> {
       if (next.getClickCount() == 2) {
        Commentaire selectedcommentaire = tvtablec.getSelectionModel().getSelectedItem();
        if (selectedcommentaire != null) {
            // Create an instance of serviceCommentaire
            serviceCommentaire sc = new serviceCommentaire();

            // Call the supprimer(galerie) method
            boolean result = sc.supprimer(selectedcommentaire);
            // Refresh the TableView
            tvtablec.getItems().remove(selectedcommentaire);
        }
    }
});

      }
      
}

