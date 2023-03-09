/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pi.GUI;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import java.sql.Connection;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import pi.Utils.MyDB;

import pi.Entities.Categorie_oeuvre;
import static pi.Entities.Categorie_oeuvre.refresh;
import pi.Entities.Etat;
import pi.Entities.Famille;
import pi.Entities.Oeuvre;
import pi.Services.ServiceCatOeuv;
import pi.Services.ServiceOeuvre;

/**
 * FXML Controller class
 *
 * @author octanet
 */
public class GestioncategorieController implements Initializable {

    @FXML
    private TextField nomInput;
    @FXML
    private TextArea descriptionInput;
    //TABLE
    @FXML
    private TableView<Categorie_oeuvre> tablecategorie;
   // @FXML
   // private TableColumn<Categorie_oeuvre, Integer> Id_Cat_Oeuv;
    @FXML
    private TableColumn<Categorie_oeuvre, String> Nom_cat_oeuv;
    @FXML
    private TableColumn<Categorie_oeuvre, String> description_cat_oeuv;

    
       
     ObservableList<Categorie_oeuvre>  Listcategorie = FXCollections.observableArrayList ();
    
      ServiceCatOeuv sc = new ServiceCatOeuv();
    @FXML
    private Button btnfermer;
    private Categorie_oeuvre cat;
      
     @Override
    public void initialize(URL url, ResourceBundle rb) {
        
          Nom_cat_oeuv.setCellValueFactory(new PropertyValueFactory<Categorie_oeuvre , String> ("Nom_cat_oeuv"));
         description_cat_oeuv.setCellValueFactory(new PropertyValueFactory<Categorie_oeuvre , String> ("description_cat_oeuv"));
         tablecategorie.getItems().addAll(sc.afficher());
                          tablecategorie.refresh();
        

    } 
    
     //BUTTON AJOUTER
    @FXML
    private void ajouterCategorie(ActionEvent event) {
        
           // Vérifier que tous les champs sont remplis
           if ( nomInput.getText().isEmpty() ||  descriptionInput.getText().isEmpty()) {
               // Afficher un message d'erreur
               Alert alert = new Alert(Alert.AlertType.ERROR);
               alert.setTitle("Erreur");
               alert.setHeaderText(null);
               alert.setContentText("Veuillez remplir tous les champs");
               alert.showAndWait();
               return;
           }
            
        
         Categorie_oeuvre cat = new  Categorie_oeuvre();
         cat.setNom_cat_oeuv(nomInput.getText());
         cat.setDescription_cat_oeuv(descriptionInput.getText());
            sc.add(cat);
            
          ObservableList<Categorie_oeuvre> Listcategorie = tablecategorie.getItems();
             Listcategorie.add(cat);


                    // Afficher un message de succès
             Alert alert = new Alert(Alert.AlertType.INFORMATION);
             alert.setTitle("Succès");
             alert.setHeaderText(null);
             alert.setContentText("categorie a été ajoutée avec succès.");
             alert.showAndWait();
             
              // Réinitialiser les champs de saisie
            nomInput.setText("");
            descriptionInput.setText("");
           
    }

    private void afficherCategorie(ActionEvent event) {
       
         //Id_Cat_Oeuv.setCellValueFactory(new PropertyValueFactory<Categorie_oeuvre , Integer> ("Id_Cat_Oeuv"));
         Nom_cat_oeuv.setCellValueFactory(new PropertyValueFactory<Categorie_oeuvre , String> ("Nom_cat_oeuv"));
         description_cat_oeuv.setCellValueFactory(new PropertyValueFactory<Categorie_oeuvre , String> ("description_cat_oeuv"));
         tablecategorie.getItems().addAll(sc.afficher());

    }

    @FXML
    private void supprimerCategorie(ActionEvent event) {
        
         // Get the selected cat from the table categorie
        Categorie_oeuvre selectedCategorie = tablecategorie.getSelectionModel().getSelectedItem();
        if (selectedCategorie != null) {
            // Create an instance of Serviceccat
            ServiceCatOeuv sc = new ServiceCatOeuv();

            // Call the supprimer(category) method
            boolean result = sc.supprimer(selectedCategorie);

          
            if (result) {
                tablecategorie.getItems().remove(selectedCategorie);
            } else {
                // Display an error message if the cat could not be deleted
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erreur");
                alert.setHeaderText(null);
                alert.setContentText("Erreur lors de la suppression de la catégorie sélectionné.");
                alert.showAndWait();
            }
        } else {
            // Display a message if no category was selected
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Avertissement");
            alert.setHeaderText(null);
            alert.setContentText("Veuillez sélectionner une catégorie à supprimer.");
            alert.showAndWait();
        }
    }

    @FXML
    private void modifierCategorie(ActionEvent event) {
        
               Categorie_oeuvre cat = new Categorie_oeuvre();
             
        if(tablecategorie.getSelectionModel().getSelectedItem()!=null){
               cat = tablecategorie.getSelectionModel().getSelectedItem();
                  cat.setNom_cat_oeuv(nomInput.getText());
                  cat.setDescription_cat_oeuv(descriptionInput.getText());
              
                    sc.updateCategorie(cat);

                  
                     //Réinitialiser les champs de saisie
                    nomInput.setText("");
                    descriptionInput.setText("");
                    tablecategorie.refresh();

        }
    }
    //ACTUALISERRRRRRRR
    public void refresh(){
        
      Nom_cat_oeuv.setCellValueFactory(new PropertyValueFactory<Categorie_oeuvre , String> ("Nom_cat_oeuv"));
                 description_cat_oeuv.setCellValueFactory(new PropertyValueFactory<Categorie_oeuvre , String> ("description_cat_oeuv"));
                tablecategorie.getItems().addAll(sc.afficher());
                 tablecategorie.refresh();
    }


    @FXML
    private void closeCategorie(MouseEvent event) {
                System.exit(0);

    }


}
