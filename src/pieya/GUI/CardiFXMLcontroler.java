/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pieya.GUI;

import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextInputDialog;
import pieya.Entities.Coupant;
import pieya.Entities.Reservation;
import pieya.Services.serviceCoupant;
import pieya.Services.serviceReservation;
import pieya.Utils.MyDB;

/**
 *
 * @author eyach
 */
public class CardiFXMLcontroler implements Initializable {

    private List<Coupant> listcoup;
    @FXML
    private Label cat;
    @FXML
    private Label nomGalerie;
    @FXML
    private Label laaa;
    @FXML
    private Label dateGalerie;
    @FXML
    private Button annulide;
    @FXML
    private Button payide;
    @FXML
    private Label hidden;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    void setData(Reservation r) {
//    Image image = new Image( e.getId_Ouevre.getImagePath()).toURI().toString());
        //imgy.setImage(new Image("../../Image/icon.png"));
        nomGalerie.setText(r.getNomGalerie());
        dateGalerie.setText(r.getDateGalerie());
        hidden.setText(String.valueOf(r.getId_Res()));

    }

    public List<Coupant> getCoupant() {
        serviceCoupant st = new serviceCoupant();
        return st.affciher();
    }

    @FXML
    private boolean PAYER(ActionEvent event) {
        listcoup = new ArrayList<>(getCoupant());
        System.out.println(listcoup);
        Connection cnx;
        Statement stm;
        cnx = MyDB.getInstance().getCnx();
        int id = Integer.parseInt(hidden.getText());

        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Avez-vous un code coupon de réduction ?");
        dialog.setHeaderText("Veuillez entrer votre code coupon");

        Optional<String> result = dialog.showAndWait();
        if (result.isPresent()) {
            String code = result.get();

            List<Coupant> listFiltre = listcoup.stream().filter(t -> t.getCode().equals(code)).collect(Collectors.toList());
            System.out.println(listFiltre);
            int val = listFiltre.get(0).getValeur();
            System.out.println(val);
            double valJdida = 25 - (25 * val / 100);
            System.out.println(valJdida);
            String requete = "UPDATE `reservation` SET `PrixR`='" + valJdida + "'WHERE Id_Res=" + id;
            try {

                stm = cnx.createStatement();
                stm.executeUpdate(requete);
                System.out.println("prix modifiée avec succés");

            } catch (SQLException ex) {
                System.err.println(ex.getMessage());

            }

            requete = "UPDATE `reservation` SET `payer`='" + 1 + "'WHERE Id_Res=" + id;
            try {

                stm = cnx.createStatement();
                stm.executeUpdate(requete);
                System.out.println("payee $");
                return true;
            } catch (SQLException ex) {
                System.err.println(ex.getMessage());

            }
            return false;
        }
        return false;

    }

    @FXML
    private void Annuler(ActionEvent event) {
        int id = Integer.parseInt(hidden.getText());
        serviceReservation sr = new serviceReservation();
        Reservation r = new Reservation();
        r.setId_Res(id);
        if (sr.supprimer(r)) {
            System.out.println("reservation supprime avec succés");
        }
    }

//    @FXML
//    private void reserver(ActionEvent event) {
//        serviceReservation sr = new serviceReservation();
//        Reservation r= new Reservation(date.getDayOfYear(),Id_event,Id_user);
//    }
}
