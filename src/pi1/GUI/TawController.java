/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pi1.GUI;

import com.sun.org.glassfish.gmbal.Description;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import pi1.Entities.evenement;
import pi1.Services.serviceEvenement;
import pi1.Utils.MyDB;

/**
 * FXML Controller class
 *
 * @author Asus
 */
public class TawController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private TextField tfNom_Galeriey;
    @FXML
    private TextField tfDescription_Galeriey;

    @FXML
    private TextField tfNombre_Party;
    @FXML
    private TextField tfNombre_Part_Resty;
    @FXML
    private TextField tfprixy;
    @FXML
    private ChoiceBox cbcategoriey;
    @FXML
    private DatePicker iddatey;
    @FXML
    private Button AjyBtn;
    @FXML
    private Button MdyBtn;
    @FXML
    private Button SupyBtn;
    @FXML
    private Button AfyBtn;
    @FXML
    private TableView<evenement> tvtable;
    @FXML
    private TableColumn<evenement, String> Nom_Galerie;
    @FXML
    private TableColumn<evenement, String> Description_Galerie;
    @FXML
    private TableColumn<evenement, Enum> categ_gal;
    @FXML
    private TableColumn<evenement, Integer> prix;
    @FXML
    private TableColumn<evenement, Integer> Nombre_Part;
    @FXML
    private TableColumn<evenement, Integer> Nombre_Part_Rest;
    @FXML
    private TableColumn<evenement, String> Date_Gal;
    @FXML
    private Button goed;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

        cbcategoriey.getItems().addAll("artContemporain", "artModerne", "PopArt", "opArt");
        cbcategoriey.setValue("artContemporain");
        serviceEvenement se = new serviceEvenement();
        tvtable.getItems().addAll(se.affciher());

    }

    @FXML
    private void faza() {
        evenement e = tvtable.getSelectionModel().getSelectedItem();
        System.out.println(e);
        if (tvtable.getSelectionModel().isEmpty()) {
            System.out.println("No item is selected");
        } else {
            tfNom_Galeriey.setText(e.getNom_Galerie());
            tfDescription_Galeriey.setText(e.getDescription_Galerie());
            tfNombre_Party.setText(String.valueOf(e.getNombre_Part()));
            tfNombre_Part_Resty.setText(String.valueOf(e.getNombre_Part_Rest()));
            tfprixy.setText(String.valueOf(e.getPrix()));
            cbcategoriey.setValue(String.valueOf(e.getCateg_gal()));
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDate date = LocalDate.parse(e.getDate_Gal(), formatter);
            iddatey.setValue(date);
        }
    }

    @FXML
    private void ajouter(ActionEvent event) {

        String Nom_Galerie = (tfNom_Galeriey.getText());
        System.out.println(Nom_Galerie);
        String Description_Galerie = tfDescription_Galeriey.getText();
        System.out.println(Description_Galerie);
        LocalDate Date_Gal = iddatey.getValue();
        String dateString = Date_Gal.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        System.out.println(dateString);
//        int Nombre_Part = Integer.parseInt(tfNombre_Party.getText());
//        System.out.println(Nombre_Part);
        int Nombre_Part = Integer.parseInt(tfNombre_Party.getText());
        if (Nombre_Part <= 20) {
            // Le nombre de personnes est valide, donc on peut ajouter l'événement
            // Code pour ajouter l'événement ici
            // ...
        } else {
            // Afficher un message d'erreur si le nombre de personnes est supérieur à 20
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText(null);
            alert.setContentText("Le nombre de participants ne peut pas dépasser 20.");
            alert.showAndWait();
        }
        int prix = Integer.parseInt(tfprixy.getText());
        System.out.println(prix);
        int Nombre_Part_Rest = Integer.parseInt(tfNombre_Part_Resty.getText());
        System.out.println(Nombre_Part_Rest);
        String categ_galS = (String) cbcategoriey.getValue();
        System.out.println(categ_galS);
        evenement.Categorie_gal categ_gall = evenement.Categorie_gal.valueOf(categ_galS);
        System.out.println(categ_gall);
        evenement e = new evenement();
        e.setId_oeuvre(25);
        //System.out.println("oeuvre taaditou");
        e.setId_Res(1);
        //System.out.println("setId_Res taaditou");
        e.setNom_Galerie(Nom_Galerie);
        //System.out.println("Nom_Galerie taaditou");
        e.setCateg_gal(categ_gall);
        //System.out.println("categ_gall taaditou");
        e.setDescription_Galerie(Description_Galerie);
        //System.out.println("Description_Galerie taaditou");
        e.setDate_Gal(dateString);
        //System.out.println("dateString taaditou");
        e.setNombre_Part(Nombre_Part);
        System.out.println("Nombre_Part taaditou");
        e.setPrix(prix);
        System.out.println("prix taaditou");
        e.setNombre_Part_Rest(Nombre_Part_Rest);
        System.out.println("Nombre_Part_Rest taaditou");
        System.out.println(e.toString());
        serviceEvenement se = new serviceEvenement();
        se.add(e);
        tvtable.getItems().add(e);

    }

    @FXML
    private void affciher(ActionEvent event) {
        serviceEvenement se = new serviceEvenement();
        //Id.setCellValueFactory(new PropertyValueFactory<Oeuvre , Integer> ("Id"));
        Nom_Galerie.setCellValueFactory(new PropertyValueFactory<evenement, String>("Nom_Galerie"));
        Description_Galerie.setCellValueFactory(new PropertyValueFactory<evenement, String>("Description_Galerie"));
        categ_gal.setCellValueFactory(new PropertyValueFactory<evenement, Enum>("categ_gal"));
        prix.setCellValueFactory(new PropertyValueFactory<evenement, Integer>("prix"));
        Nombre_Part.setCellValueFactory(new PropertyValueFactory<evenement, Integer>("Nombre_Part"));
        Nombre_Part_Rest.setCellValueFactory(new PropertyValueFactory<evenement, Integer>("Nombre_Part_Rest"));
        Date_Gal.setCellValueFactory(new PropertyValueFactory<evenement, String>("Date_Gal"));
        ObservableList<evenement> evenements = tvtable.getItems();
        evenements.addAll(se.affciher());
        tvtable.setItems(evenements);

        System.out.println(se.affciher());

    }

    @FXML
    private void modifier(ActionEvent event) {
        System.out.println("wsolt ya raby ");

        evenement e = tvtable.getSelectionModel().getSelectedItem();
        System.out.println("wsolt ya raby ");
        System.out.println(e);
        System.out.println("wsolt ya raby ");

        serviceEvenement se = new serviceEvenement();
        System.out.println("wsolt ya raby ");
        String Nom_Galerie = (tfNom_Galeriey.getText());
        System.out.println("wsolt ya raby ");
        String Description_Galerie = tfDescription_Galeriey.getText();
        System.out.println("wsolt ya raby ");
        LocalDate Date_Gal = iddatey.getValue();
        String dateString = Date_Gal.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        System.out.println("wsolt ya raby ");
        int Nombre_Part = Integer.parseInt(tfNombre_Party.getText());
        int prix = Integer.parseInt(tfprixy.getText());
        int Nombre_Part_Rest = Integer.parseInt(tfNombre_Part_Resty.getText());
        String categ_galS = (String) cbcategoriey.getValue();
        evenement.Categorie_gal categ_gall = evenement.Categorie_gal.valueOf(categ_galS);
        e.setId_oeuvre(25);
        e.setId_Res(1);
        e.setNom_Galerie(Nom_Galerie);
        e.setCateg_gal(categ_gall);
        e.setDescription_Galerie(Description_Galerie);
        e.setDate_Gal(dateString);
        e.setNombre_Part(Nombre_Part);
        e.setPrix(prix);
        e.setNombre_Part_Rest(Nombre_Part_Rest);
        se.modifier(e);
        //System.out.println(se.modifier(e));
    }

    @FXML
    private void supprimer(ActionEvent event) {
//    // Get the selected galerie from the table view
//        evenement selectedevenement = tvtable.getSelectionModel().getSelectedItem();
//        if (selectedevenement != null) {
//            // Create an instance of serviceEvenement
//            serviceEvenement se = new serviceEvenement();
//
//            // Call the supprimer(galerie) method
//            boolean result = se.supprimer(selectedevenement);

        tvtable.setOnMouseClicked(next -> {
            if (next.getClickCount() == 2) {
                evenement selectedevenement = tvtable.getSelectionModel().getSelectedItem();
                System.out.println("Nombre_Part taaditou");
                if (selectedevenement != null) {
                    // Create an instance of serviceCommentaire
                    serviceEvenement se = new serviceEvenement();

                    // Call the supprimer(galerie) method
                    boolean result = se.supprimer(selectedevenement);
                    // Refresh the TableView
                    tvtable.getItems().remove(selectedevenement);
                }
            }
        });

    }

    @FXML
    private void go(ActionEvent event) throws IOException {

        Parent fxml;
        fxml = FXMLLoader.load(getClass().getResource("Yesmina.fxml"));
        Scene scene3 = new Scene(fxml);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(scene3);
        window.show();
    }

}
