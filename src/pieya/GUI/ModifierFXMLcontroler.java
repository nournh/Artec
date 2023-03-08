/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pieya.GUI;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import pieya.Entities.Coupant;
import pieya.Services.serviceCoupant;
import pieya.Services.serviceReservation;

/**
 *
 * @author eyach
 */
public class ModifierFXMLcontroler implements Initializable {

    @FXML
    private Button confide;
    @FXML
    private TextField codeee;
    @FXML
    private TextField valeurr;

    private Coupant coupant;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

    @FXML
    private void Confirmer(ActionEvent event) {
        Coupant t = coupant;
        t.setCode(codeee.getText());
        t.setValeur(Integer.parseInt(valeurr.getText()));

        serviceCoupant st = new serviceCoupant();
        boolean resultat = st.modifier(t);
        if (resultat) {
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Succès");
            alert.setHeaderText("Coupan modifiée avec succès");
            alert.showAndWait();
        }
        Stage stage = (Stage) confide.getScene().getWindow();
        stage.close();
    }

    @FXML
    public void setCoupant(Coupant coupant) {
        this.coupant = coupant;
        codeee.setText(coupant.getCode());
       // int Valeur = Integer.parseInt(valeurr.getText());

    }

}
