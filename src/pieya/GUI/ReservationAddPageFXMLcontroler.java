/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pieya.GUI;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import pieya.Entities.evenement;
import pieya.Entities.evenement.Categorie_gal;
import pieya.Services.serviceReservation;

import pieya.Services.serviceEvenement;

/**
 *
 * @author eyach
 */
public class ReservationAddPageFXMLcontroler implements Initializable {

    private List<evenement> listeven;
    @FXML
    private Label catgal;
    @FXML
    private ScrollPane reservationContainer;
    @FXML
    private GridPane eventContainer;
    @FXML
    private Button meside;
    @FXML
    private Button filtrery;
    @FXML
    private Button ret;
   
    URL url;
    ResourceBundle rb;
    @FXML
    private ComboBox cbcategoriey;
    

    @Override
    public void initialize(URL url, ResourceBundle rb) {
       //  cbcategoriey.getItems().addAll("artContemporain", "artModerne", "PopArt", "opArt");
     //  cbcategoriey.setValue("artContemporain");
        EnumSet.allOf(Categorie_gal.class).stream().forEach(s -> cbcategoriey.getItems().add(s.toString()));
        cbcategoriey.getItems().add("TOUTES");
        listeven = new ArrayList<>(getEvenements());
        int column = 0;
        int row = 1;
        try {
            for (int i = 0; i < listeven.size(); i++) {
                //int i=0;
                FXMLLoader fxmlLoader = new FXMLLoader();
                System.out.println(" taaditou");
                fxmlLoader.setLocation(getClass().getResource("ReservationdetailsFXML.fxml"));
                System.out.println(" taaditou");
                Pane cardBox = fxmlLoader.load();
                System.out.println(" taaditou");

                ReservationdetailsFXMLController cardController = fxmlLoader.getController();
                System.out.println(" taaditou");
                evenement e = listeven.get(i);
                System.out.println(" taaditou");
                cardController.setData(e);
                System.out.println(" taaditou");

                if (column == 2) {
                    column = 0;
                    ++row;
                    System.out.println(" taaditou");
                }
                eventContainer.add(cardBox, column++, row);
                System.out.println(" taaditou");
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public List<evenement> getEvenements() {
        serviceEvenement se = new serviceEvenement();
        return se.affciher();
    }

    @FXML
    private void Filter(ActionEvent event) {
        eventContainer.getChildren().clear();
        if (cbcategoriey.getValue().equals("TOUTES")) {
            initialize(url, rb);
        } else {
            listeven = new ArrayList<>(getEvenements());
            List<evenement> listFiltre = listeven.stream().filter(e -> e.getCateg_gal().toString().equals(cbcategoriey.getValue())).collect(Collectors.toList());
            int column = 0;
            int row = 1;
            try {
                for (int i = 0; i < listFiltre.size(); i++) {
                    FXMLLoader fxmlLoader = new FXMLLoader();
                    System.out.println(" taaditou");
                    fxmlLoader.setLocation(getClass().getResource("ReservationdetailsFXML.fxml"));
                        System.out.println(" taaditou");
                    Pane cardBox = fxmlLoader.load();
                       System.out.println(" taaditou");
                    ReservationdetailsFXMLController cardController = fxmlLoader.getController();
                       System.out.println(" taaditou");
                    evenement e = listFiltre.get(i);
                       System.out.println(" taaditou");
                    cardController.setData(e);
                       System.out.println(" taaditou");

                    if (column == 2) {
                        column = 0;
                        ++row;
                    }

                    eventContainer.add(cardBox, column++, row);
                }

            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

   


    
    @FXML
    private void MesReservations(ActionEvent event) throws IOException {
        Parent fxml;
         System.out.println("ena taw wsolt 1 ");
        fxml = FXMLLoader.load(getClass().getResource("ReservationPersoFXML.fxml"));
        System.out.println("ena taw wsolt 2 ");
        Scene scene3 = new Scene(fxml);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(scene3);
        window.show();

    }
    @FXML
    private void retour2 (ActionEvent event) throws IOException {
        Parent fxml;
        fxml = FXMLLoader.load(getClass().getResource("HomeReservationFXML.fxml"));
        Scene scene3 = new Scene(fxml);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(scene3);
        window.show();
    }

}
