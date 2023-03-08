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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import pieya.Entities.evenement;
import pieya.Services.serviceReservation;
import java.time.LocalDate;
import javafx.scene.image.Image;
import pieya.Entities.Reservation;
import pieya.Services.serviceEmail;
import pieya.Services.serviceEvenement;
import java.time.LocalDate;

/**
 * FXML Controller class
 *
 * @author eyach
 */
public class ReservationdetailsFXMLController implements Initializable {

//    @FXML
//    private ImageView imageView;
    @FXML
    private Label descripty;
    @FXML
    private Label eventide;
    @FXML
    private Button resid;
    @FXML
    private Label categoriey;
    @FXML
    private Label nomgaly;
   
    LocalDate date = LocalDate.now();

    //  serviceEvenement se= new serviceEvenement();
    // @FXML
    // private ImageView imgeed;
    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // TODO
    }

    void setData(evenement e) {
        //Image image = new Image( e.getId_Ouevre.getImagePath()).toURI().toString());
        // Image icon = new Image("/imgy/gal.jpg");
        //imgeed.setImage(icon);
        nomgaly.setText(e.getNom_Galerie());
        descripty.setText(e.getDescription_Galerie());
        categoriey.setText(String.valueOf(e.getCateg_gal()));
         eventide.setText(String.valueOf(e.getId_event()));
        

    }

//    @FXML
//    private void reserver(ActionEvent event) {
//        serviceReservation sr = new serviceReservation();
//        Reservation r= new Reservation(date.getDayOfYear(),Id_event,Id_user);
//    }
    void setData(Reservation r) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @FXML
    private void ajouterreservation(ActionEvent event) {
        evenement e = new evenement();
        Reservation r = new Reservation();
         serviceReservation sr = new serviceReservation();
         r.setId_event(Integer.parseInt(eventide.getText()));
         r.setid_user(1);
         r.setPayer(0);
         r.setDate(LocalDate.now().toString());
       
       sr.add(r);
//       serviceEmail emailService = new serviceEmail();
//        emailService.sendEmailF("yesmine.kchaou@esprit.tn ");
    }
   
}
