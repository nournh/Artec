/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pieya.GUI;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import static java.util.Collections.list;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import pieya.Entities.Reservation;
import pieya.Services.serviceReservation;

/**
 *
 * @author eyach
 */
public class ReservationPersoFXMLcontroler implements Initializable {

    private List<Reservation> listreser;
    int paye = 0;

    @FXML
    private Label labbbb;
    @FXML
    private Button refresheya;
    @FXML
    private ScrollPane mescontainerr;
    @FXML
    private GridPane gridide;
    @FXML
    private Button filteride;
    @FXML
    private Button capt;

    @FXML
    private ChoiceBox<String> chide;
    URL url;
    ResourceBundle rb;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        chide.getItems().addAll("payée", "non payée", "TOUTES");
        chide.getItems().addAll("payée", "non payée", "TOUTES");
        chide.setValue("TOUTES");
        listreser = new ArrayList<>(getReservation());
        System.out.println(listreser);
        int column = 0;
        int row = 1;
        try {
            for (int i = 0; i < listreser.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();

                fxmlLoader.setLocation(getClass().getResource("CardiFXML.fxml"));

                Pane cardBox = fxmlLoader.load();

                CardiFXMLcontroler cardController = fxmlLoader.getController();

                Reservation r = listreser.get(i);

                System.out.println(r);
                cardController.setData(r);

                if (column == 2) {
                    column = 0;
                    ++row;
                }

                gridide.add(cardBox, column++, row);
            }

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

    }

    public List<Reservation> getReservation() {
        serviceReservation sr = new serviceReservation();
        return sr.affciher();
    }

    @FXML
    private void Filter(ActionEvent event) {

        gridide.getChildren().clear();
        if (chide.getValue().equals("TOUTES")) {
            initialize(url, rb);
        } else {
            listreser = new ArrayList<>(getReservation());
            System.out.println(listreser);
            if ((chide.getValue().equals("payée"))) {
                paye = 1;
            } else {
                paye = 0;
            }
            List<Reservation> listFiltre = listreser.stream().filter(r -> r.getPayer() == paye).collect(Collectors.toList());
            System.out.println(listFiltre);
            int column = 0;
            int row = 1;
            try {
                for (int i = 0; i < listFiltre.size(); i++) {
                    FXMLLoader fxmlLoader = new FXMLLoader();

                    fxmlLoader.setLocation(getClass().getResource("CardiFXML.fxml"));

                    Pane cardBox = fxmlLoader.load();

                    CardiFXMLcontroler cardController = fxmlLoader.getController();

                    Reservation r = listFiltre.get(i);
                    System.out.println(r);

                    cardController.setData(r);

                    if (column == 2) {
                        column = 0;
                        ++row;
                    }

                    gridide.add(cardBox, column++, row);
                }

            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

    @FXML
    private void Refresh(ActionEvent event) {
        gridide.getChildren().clear();
        initialize(url, rb);
    }

    @FXML
    private void retour1(ActionEvent event) throws IOException {
        Parent fxml;
        fxml = FXMLLoader.load(getClass().getResource("ReservationAddPageFXML.fxml"));
        Scene scene3 = new Scene(fxml);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(scene3);
        window.show();
    }

}
