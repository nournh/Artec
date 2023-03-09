/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pi.GUI;
import java.io.File;
import java.io.IOException;
import pi.Utils.MyDB; 
import java.sql.Connection;
import java.net.URL;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import static java.time.zone.ZoneRulesProvider.refresh;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Callback;
import pi.Entities.Categorie_oeuvre;

import pi.Entities.Famille;
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

public class AjouteroeuvreController implements Initializable {
   
    
    
    private int Id  ;
    private Oeuvre o;
    private Parent fxml;
    private Stage stage;
    private Scene scene;
    private Parent root;


    
    Connection cnx;
    Statement stm;

    // TEXT INPUT 
    @FXML
    private TextField nomInput;
    @FXML
    private TextField prixInput;
    @FXML
    private TextField titreInput;
    @FXML
    private TextField id_catInput;
    
        ObservableList<String> maritalStatusList = FXCollections.observableArrayList("En_prét", "Nondisponible", "Enstock_Dispo"); // teba3 l'enum d'etat
      
        ServiceOeuvre s = new ServiceOeuvre();
        ServiceCatOeuv sc = new ServiceCatOeuv();// on va créer un nouv constr et aussi il faut importer le service au m temps !! bech najmou n' applique les methodes CRUD
    
     //TABLE
     @FXML
     private TableView<Oeuvre> tableoeuvre;
     private List<Oeuvre> oeuvres;
     private List<Categorie_oeuvre> categories;
     
    @FXML
    private TableColumn<Oeuvre, String> titre_oeuvre; 
    @FXML
    private TableColumn<Oeuvre, String> artiste;
    @FXML
    private TableColumn<Oeuvre, Famille> Famille;
    @FXML
    private TableColumn<Oeuvre, String> prix_oeuvre;
    @FXML
    private TableColumn<Oeuvre, String> Id_cat_oeuv;
    @FXML
     private TableColumn<Oeuvre, Etat> Etat;
   
     @FXML
    private TableColumn<Oeuvre, ImageView> image;
    private  ImageView imageurl;
    
    

    ObservableList<Oeuvre>  oList = FXCollections.observableArrayList ();
    @FXML
    private ComboBox<String> state;
    
    @FXML
    private ComboBox<String> state_famille;
    @FXML
    private AnchorPane Pane;
    @FXML
    private TextField chercher;
    @FXML
    private Button btnfermer;
   
    @FXML
    private ImageView Ajoutimage;
    @FXML
    private Button btnsuivantt;
    @FXML
    private TableColumn<Categorie_oeuvre, String> Nom_cat_oeuv;
    @FXML
    private TableColumn<Categorie_oeuvre, String> description_cat_oeuv;
    @FXML
    private TableView<Categorie_oeuvre> tablecategorie;
    //IMAGE
        public static String pathfile; 
        public static String filename="";
    
   
   
   
      @Override
    public void initialize(URL location, ResourceBundle resources) {

       
        state.getItems().addAll("En_prét", "Nondisponible", "Enstock_Dispo");
          state.setValue("");
        state_famille.getItems().addAll("Art_contemporain", "Art_popart", "Art_visuel");
          state_famille.setValue("");  
          
          //affichage
           //Id.setCellValueFactory(new PropertyValueFactory<Oeuvre , Integer> ("Id"));
       //  image.setCellValueFactory(new PropertyValueFactory<Oeuvre , > ("image"));
         titre_oeuvre.setCellValueFactory(new PropertyValueFactory<Oeuvre , String> ("titre_oeuvre"));
         artiste.setCellValueFactory(new PropertyValueFactory<Oeuvre , String> ("artiste"));
        Famille.setCellValueFactory(new PropertyValueFactory<Oeuvre , Famille> ("Famille"));
         prix_oeuvre.setCellValueFactory(new PropertyValueFactory<Oeuvre , String> ("prix_oeuvre"));
         //Nom_cat_oeuv.setCellValueFactory(new PropertyValueFactory<Oeuvre , String> ("Nom_cat_oeuv")); 
           Etat.setCellValueFactory(new PropertyValueFactory<Oeuvre , Etat> ("Etat"));
         
         
         //AFFFICHAGE join idcat_oeuv.nom_cat oeuv avec id_oeuvre
         Id_cat_oeuv.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Oeuvre, String>, ObservableValue<String>>() {

                @Override
                public ObservableValue<String> call(TableColumn.CellDataFeatures<Oeuvre, String> param) {
                    try {
                        int catid = param.getValue().getId_cat_oeuv();
                       categories = sc.afficher() ;
                        List<Categorie_oeuvre> list = sc.afficher();
                        String cat = list.stream().filter((t) -> t.getId_cat_oeuv() == catid).limit(1).map((t) -> t.getNom_cat_oeuv()).collect(Collectors.joining(", "));


                        return new SimpleObjectProperty<>(cat);
                    } catch (Exception e) {
                        System.out.println(e);
                    }
                    return null;
                }
                });
         
        ////////// affichage imagee view
            image.setCellValueFactory(new PropertyValueFactory<>("image"));
            image.setPrefWidth(130);
           image.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Oeuvre, ImageView>, ObservableValue<ImageView>>() {

               @Override
               public ObservableValue<ImageView> call(TableColumn.CellDataFeatures<Oeuvre, ImageView> param) {
                   try {
                       String imagePath = param.getValue().getImage();

                       Image image = new Image(imagePath);
                       ImageView imageView = new ImageView();
                       imageView.setImage(image);
                       imageView.setFitHeight(120);
                       imageView.setFitWidth(120);


                       return new SimpleObjectProperty<>(imageView);
                   } catch (Exception e) {
                       System.out.println(e);
                   }
                   return null;
               }
           });
                      
        
          Ajoutimage.getImage().impl_getUrl();
         tableoeuvre.getItems().addAll(s.afficher());
          
         //tableoeuvre.getItems().addAll(s.getall());
         tableoeuvre.refresh();
         
             //TaBLE CATEGORIE OEUVRE
                 Nom_cat_oeuv.setCellValueFactory(new PropertyValueFactory<Categorie_oeuvre , String> ("Nom_cat_oeuv"));
                 description_cat_oeuv.setCellValueFactory(new PropertyValueFactory<Categorie_oeuvre , String> ("description_cat_oeuv"));
                 tablecategorie.getItems().addAll(sc.afficher());
                 tablecategorie.refresh();
    }
     
    
    //BUTTON AJOUTER
    @FXML
    private void ajouterOeuvre(ActionEvent event) {
        
                // Vérifier que tous les champs sont remplis
                if (image.getText().isEmpty() ||nomInput.getText().isEmpty() || prixInput.getText().isEmpty() || 
                   titreInput.getText().isEmpty() || id_catInput.getText().isEmpty()) {
               // Afficher un message d'erreur
               Alert alert = new Alert(AlertType.ERROR);
               alert.setTitle("Erreur");
               alert.setHeaderText(null);
               alert.setContentText("Veuillez remplir tous les champs");
               alert.showAndWait();
               return;
           }

                // Convertir l'année en entier
            int Id_cat_oeuv;
            try {
                Id_cat_oeuv = Integer.parseInt(id_catInput.getText());
            } catch (NumberFormatException e) {
                // Afficher un message d'erreur si l'année n'est pas un nombre
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Erreur");
                alert.setHeaderText(null);
                alert.setContentText("L'ID  de la catégorie doit être un nombre entier .");
                alert.showAndWait();
                return;
            }
        
            
              // Convertir le prix en entier
            int prix_oeuvre;
            try {
                prix_oeuvre = Integer.parseInt(prixInput.getText());
            } catch (NumberFormatException e) {
                // Afficher un message d'erreur si l'année n'est pas un nombre
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Erreur");
                alert.setHeaderText(null);
                alert.setContentText("Le prix doit être un nombre.");
                alert.showAndWait();
                return;
            }
            

         Oeuvre o = new  Oeuvre();
          // o.setId(Integer.parseInt(Id.getText()));// puisque houwa entier donc ybadlou l texte filed string
          o.setTitre_oeuvre(titreInput.getText());
          o.setImage(Ajoutimage.getImage().impl_getUrl());  // hthyy bech njibou l'url mtaa3 image
          o.setArtiste(nomInput.getText());
          o.setFamille(state_famille.getValue());
          o.setPrix_oeuvre(prixInput.getText());
         // id_catInput.setText(String.valueOf(o.getId_cat_oeuv()));
          o.setId_cat_oeuv(Integer.parseInt(id_catInput.getText()));
          o.setEtat(state.getValue());
            s.add(o);
            //AJOUT DIRECTEMENT SANS CLIQUER SUR LE BUTTON AFFICHER
            ObservableList<Oeuvre> oeuvres = tableoeuvre.getItems();
             oeuvres.add(o);
             System.out.println(tableoeuvre);
            tableoeuvre.refresh();
           /////////////////////////////////
            //ObservableList<Oeuvre>  oeuvres =  tableoeuvre.getItems();// hethom kount nekhdim bihoum min BD cad j'ajoute manuellement fields
           // oeuvres.add(o);
            // tableoeuvre.setItems(oList); // ill ne me l'aff pas mon BD .just le données que j'ai l'ajoute instantanement
            
              // Afficher un message de succès
             Alert alert = new Alert(AlertType.INFORMATION);
             alert.setTitle("Succès");
             alert.setHeaderText(null);
             alert.setContentText("L'oeuvre a été ajoutée avec succès.");
             alert.showAndWait();
             
             // Réinitialiser les champs de saisie
            titreInput.setText("");
            nomInput.setText("");
            state_famille.setValue("");
            prixInput.setText("");
           id_catInput.setText("");
            state.setValue("");
    }

   

   //SUPPRIMER OEUVRE
    @FXML
    private void supprimerOeuvre(ActionEvent event) {
         // Get the selected artiste from the table view
        Oeuvre selectedOeuvre = tableoeuvre.getSelectionModel().getSelectedItem();
        if (selectedOeuvre != null) {
            // Create an instance of ServiceOeuvre
            ServiceOeuvre serviceoeuvre = new ServiceOeuvre();

            // Call the supprimer(Oeuvre) method
            boolean result = serviceoeuvre.supprimer(selectedOeuvre);

            // If the oeuvre was deleted successfully, update the table oeuvre
            if (result) {
                tableoeuvre.getItems().remove(selectedOeuvre);
            } else {
                // Display an error message if the oeuvre could not be deleted
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Erreur");
                alert.setHeaderText(null);
                alert.setContentText("Erreur lors de la suppression de l'artiste sélectionné.");
                alert.showAndWait();
            }
        }
        else {
            // Display a message if no oeuvre was selected
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("Avertissement");
            alert.setHeaderText(null);
            alert.setContentText("Veuillez sélectionner une oeuvre à supprimer.");
            alert.showAndWait();
        }
    }
    
    
    //MODIFIER OEUVRE
    @FXML
    private void modifierOeuvre(ActionEvent event) {


//tikhdem hethyyyyyy

                                if(tableoeuvre.getSelectionModel().getSelectedItem()!=null){

                                       o = tableoeuvre.getSelectionModel().getSelectedItem();
                                           //o.setImage(filename);
                                          o.setTitre_oeuvre(titreInput.getText());
                                          o.setArtiste(nomInput.getText());
                                          o.setFamille(state_famille.getValue());
                                          o.setPrix_oeuvre(prixInput.getText());
                                        // o.setId_cat_oeuv(Integer.parseInt(id_catInput.getText()));
                                        // int id = Integer.parseInt(id_catInput.getText()); 
                                        // o.setId_cat_oeuv(id.getValue());
                                          o.setEtat(state.getValue());
                                            // o.setFamille(state.getValue());

                                           // o.setId_cat_oeuv(Integer.valueOf(Id_cat_oeuv.getValue()));
                                            //o.setId_cat_oeuv(Integer.valueOf(Id_cat_oeuv.getText()));
                                             ServiceOeuvre s = new ServiceOeuvre();
                                        //    Oeuvre o = new Oeuvre(image,titre_oeuvre, artiste,Famille,prix_oeuvre,Etat) ;      
                                        ObservableList<Oeuvre>  oeuvres =  tableoeuvre.getItems();    
                                        s.modifier(o);
                                        tableoeuvre.refresh();
                                            // Réinitialiser les champs de saisie
                                            titreInput.setText("");
                                            nomInput.setText("");
                                            state_famille.setValue("");
                                            prixInput.setText("");
                                            id_catInput.setText("");
                                          // id_catInput.setText(""); 
                                            state.setValue("");
                                     }
     
     
      //Get the selected oeuvre object
////    Oeuvre selectedOeuvre = tableoeuvre.getSelectionModel().getSelectedItem();
////    if (selectedOeuvre == null) {
////        // No ou selected, display an error message
////        Alert alert = new Alert(Alert.AlertType.ERROR);
////        alert.setTitle("Error");
////        alert.setHeaderText(null);
////        alert.setContentText("Please select an oeuvre to edit.");
////        alert.showAndWait();
////        return;
////    }
////    
////        // Open another dialog to edit the title.
////        TextInputDialog titleDialog = new TextInputDialog(selectedOeuvre.getTitre_oeuvre());
////        titleDialog.setTitle("Edit Oeuvre");
////        titleDialog.setHeaderText(null);
////        titleDialog.setContentText("Please enter the new Title:");
////        
////        Optional<String> titleResult = titleDialog.showAndWait();
////        
////        if (titleResult.isPresent()) {
////            // Update the oeuvre object with the new title value.
////            selectedOeuvre.setTitre_oeuvre(titleResult.get());
////            
////            // Open another dialog to edit .
////            TextInputDialog nomDialog = new TextInputDialog(selectedOeuvre.getArtiste());
////            nomDialog.setTitle("Edit Oeuvre");
////            nomDialog.setHeaderText(null);
////            nomDialog.setContentText("Please enter the new Artiste:");
////            
////            Optional<String> nomResult = nomDialog.showAndWait();
////            
////            if (nomResult.isPresent()) {
////                // Update the oeuvre object with the new 
////                selectedOeuvre.setArtiste(nomResult.get());
////                
////                
////                 // Open another dialog to edit the .
////            TextInputDialog prixDialog = new TextInputDialog(selectedOeuvre.getPrix_oeuvre());
////            nomDialog.setTitle("Edit Oeuvre");
////            nomDialog.setHeaderText(null);
////            nomDialog.setContentText("Please enter the new Prix:");
////                Optional<String> prixResult = prixDialog.showAndWait();
////            
////            if (prixResult.isPresent()) {
////                // Update the oeuvre object with the new description value.
////                selectedOeuvre.setPrix_oeuvre(prixResult.get());
////                
////                // Call the update() method of the Service class, passing in the updated Article object.
////                ServiceOeuvre s = new ServiceOeuvre();
////                s.modifier(selectedOeuvre);
////                
////                System.out.println();
////                // Refresh the TableView to reflect the changes.
////                tableoeuvre.refresh();
////            }
////            }}

    }
    

    
    //ACTUALISERRRRRRRR
    public void refresh(){
        
     // image.setCellValueFactory(new PropertyValueFactory<Oeuvre , String> ("Ajoutimage"));
         titre_oeuvre.setCellValueFactory(new PropertyValueFactory<Oeuvre , String> ("titre_oeuvre"));
         artiste.setCellValueFactory(new PropertyValueFactory<Oeuvre , String> ("artiste"));
           Famille.setCellValueFactory(new PropertyValueFactory<Oeuvre , Famille> ("Famille"));
         prix_oeuvre.setCellValueFactory(new PropertyValueFactory<Oeuvre , String> ("prix_oeuvre"));
          Id_cat_oeuv.setCellValueFactory(new PropertyValueFactory<Oeuvre , String> ("Id_cat_oeuv")); 
         Etat.setCellValueFactory(new PropertyValueFactory<Oeuvre , Etat> ("Etat"));
            
           tableoeuvre.getItems().addAll(s.afficher());
    
    }


        public void setId(int Id) {this.Id = Id;}

        public void setTitreText(String text) {
               titreInput.setText(text);
           }

            public void setDescriptionText(String text) {
                nomInput.setText(text);
            }



   @FXML
    private void close(MouseEvent event) {
        System.exit(0);
    }

    ///AJOUTER IMAGEE
    @FXML
    private void ajoutImage(MouseEvent event) {
        FileChooser fileChooser = new FileChooser();

        //Set extension filter
        FileChooser.ExtensionFilter extFilterJPG = new FileChooser.ExtensionFilter("JPG files (.jpg)", ".JPG");
        FileChooser.ExtensionFilter extFilterPNG = new FileChooser.ExtensionFilter("PNG files (.png)", ".PNG");
        fileChooser.getExtensionFilters().addAll(extFilterJPG, extFilterPNG);

        //Show open file dialog
        File file = fileChooser.showOpenDialog(null);

        if (file != null) {
            Image image = new Image(file.toURI().toString());
            Ajoutimage.setImage(image);
        }
    }

    @FXML
    public void switchtoscene2(ActionEvent event) throws IOException {
         
           Parent root = FXMLLoader.load(getClass().getResource("Listeoeuvre.fxml"));
         
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    
    }
    @FXML
    private void chercherOeuvre(ActionEvent event) {
        
    ServiceOeuvre s = new ServiceOeuvre();
    String searchText = chercher.getText();
    ObservableList<Oeuvre> allOeuvres = s.getall();

    // Filter the articles based on the search text
    ObservableList<Oeuvre> filteredList = allOeuvres.filtered(oeuvre -> {
        return oeuvre.getTitre_oeuvre().toLowerCase().contains(searchText.toLowerCase()) ||
               // article.getDescription().toLowerCase().contains(searchText.toLowerCase()) ||
                oeuvre.getFamille().toLowerCase().contains(searchText.toLowerCase());
    });
             // Show the filtered articles in the table
            tableoeuvre.setItems(filteredList);
         
          int id = Integer.parseInt(chercher.getText()); 
    for (Oeuvre oeuvre : tableoeuvre.getItems()) {
        if (oeuvre.getId() == id) { 
            tableoeuvre.getSelectionModel().select(oeuvre); 
            return; 
        }
    }
    }
    //COMBOBOX
    public void Filtrer(ActionEvent event) {
       
     
    }
    
    private void filterByFamille(ActionEvent event) {
    // Get the selected category from the ComboBox
    String selectedCategory = state_famille.getValue();
    
    // Call the getArticlesByCategory method of the ServiceArticle class to get a filtered list of articles
    ServiceOeuvre s = new ServiceOeuvre();
    List<Oeuvre> filteredOeuvres = s.getOeuvresByFamille(selectedCategory);
    
    // Update the TableView to display the filtered list of articles
    tableoeuvre.setItems(FXCollections.observableArrayList(filteredOeuvres));
}
    
    
        
         
         public void deleteC() {
             ServiceOeuvre  s = new ServiceOeuvre();
               Oeuvre selectedOeuvre = tableoeuvre.getSelectionModel().getSelectedItem(); 
               s.delete(selectedOeuvre.getId());
               s.delete(tableoeuvre.getSelectionModel().getSelectedItem().getId()); }
         
    private void delete(ActionEvent event) {
    
            
                deleteC();
          tableoeuvre.getItems().removeAll(tableoeuvre.getSelectionModel().getSelectedItem());
          tableoeuvre.refresh();
           Oeuvre o = tableoeuvre.getSelectionModel().getSelectedItem();
            // Get the selected oeuvre object

            // Delete the selected article from the database

        }
    }

    
    
         
         


    
        
        
        
        
        


   

    
    

