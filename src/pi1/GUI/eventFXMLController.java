/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pi1.GUI;

//import java.awt.Choice;
import java.net.URL;
import java.sql.Connection;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import pi1.Entities.evenement;
import pi1.Entities.evenement.Categorie_gal;
import pi1.Services.serviceEvenement;
import pi1.Utils.MyDB;

/**
 *
 * @author Asus
 */
public class eventFXMLController implements Initializable {

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
    private Button AjyBtn;
    @FXML
    private Button MdyBtn;
    @FXML
    private Button SupyBtn;
    @FXML
    private Button AfyBtn;
    @FXML
    private TableView<evenement> tvtable;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        MyDB ds = new MyDB();
        Connection cnx = ds.getConnection();
        serviceEvenement se = new serviceEvenement();

    }

    @FXML
    private void ajouter(ActionEvent event) {
        String Nom_Galerie = (tfNom_Galeriey.getText());
        String Description_Galerie = tfDescription_Galeriey.getText();
        //  LocalDate Date_Gal =  iddatey.getValue();
        // String dateString = Date_Gal.format(DateTimeFormatter.ofPattern("yyyy-mm-dd"));
        int Nombre_Part = Integer.parseInt(tfNombre_Party.getText());
        int prix = Integer.parseInt(tfprixy.getText());
        int Nombre_Part_Rest = Integer.parseInt(tfNombre_Part_Resty.getText());
        String categ_galS = (String) cbcategoriey.getValue();
        Categorie_gal categ_gall = evenement.Categorie_gal.valueOf(categ_galS);
        evenement e = new evenement();
        e.setId_oeuvre(25);
        e.setId_Res(1);
        e.setNom_Galerie(Nom_Galerie);
        e.setCateg_gal(categ_gall);
        e.setDescription_Galerie(Description_Galerie);
        //  e.setDate_Gal(dateString);
        e.setPrix(Nombre_Part);
        e.setPrix(prix);
        e.setPrix(Nombre_Part_Rest);
        serviceEvenement se = new serviceEvenement();
        se.ajouterEvenement2(e);

    }

    @FXML
    private void affciher(ActionEvent event) {
        serviceEvenement se = new serviceEvenement();

    }

    @FXML
    private void modifier(ActionEvent event) {

        evenement e = new evenement();
        serviceEvenement se = new serviceEvenement();
        String Nom_Galerie = (tfNom_Galeriey.getText());
        String Description_Galerie = tfDescription_Galeriey.getText();
        // LocalDate Date_Gal =  iddatey.getValue();
        // String dateString = Date_Gal.format(DateTimeFormatter.ofPattern("yyyy-mm-dd"));
        int Nombre_Part = Integer.parseInt(tfNombre_Party.getText());
        int prix = Integer.parseInt(tfprixy.getText());
        int Nombre_Part_Rest = Integer.parseInt(tfNombre_Part_Resty.getText());
        String categ_galS = (String) cbcategoriey.getValue();
        Categorie_gal categ_gall = evenement.Categorie_gal.valueOf(categ_galS);

        e.setId_oeuvre(25);
        e.setId_Res(1);
        e.setNom_Galerie(Nom_Galerie);
        e.setCateg_gal(categ_gall);
        e.setDescription_Galerie(Description_Galerie);
        // e.setDate_Gal(dateString);
        e.setPrix(Nombre_Part);
        e.setPrix(prix);
        e.setPrix(Nombre_Part_Rest);
        se.modifier(e);

    }

    @FXML
    private void supprimer(ActionEvent event) {

    }

}
