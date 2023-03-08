/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pieya.GUI;

import static com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type.Int;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.Callback;
import pieya.Entities.Reservation;
import pieya.Services.serviceReservation;

/**
 * FXML Controller class
 *
 * @author Mohamed
 */
public class HomeReservationFXMLcontroler implements Initializable {

    @FXML
    private Button reserveride;
    @FXML
    private TableColumn<Reservation, String> Nom_Galerie;
    @FXML
    private TableColumn<Reservation, String> Date_Gal;
    @FXML
    private TableColumn<Reservation, String> categ_gal;
    @FXML
    private TableView<Reservation> tablereserve;
    @FXML
    private TableColumn<Reservation, Integer> prixide;
    @FXML
    private TableColumn<Reservation, Void> suppide;
    @FXML
    private Label titreide;
    @FXML
    private Label soustitreide;
    @FXML
    private TableColumn<Reservation, Integer> payeride;

    serviceReservation sr = new serviceReservation();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ObservableList<Reservation> list
                = sr.affciher();
        tablereserve.setItems(list);

        Nom_Galerie.setCellValueFactory(new PropertyValueFactory<>("nomGalerie"));
        Date_Gal.setCellValueFactory(new PropertyValueFactory<>("dateGalerie"));
        categ_gal.setCellValueFactory(new PropertyValueFactory<>("categ_gal"));
        prixide.setCellValueFactory(new PropertyValueFactory<>("Prix"));
        payeride.setCellValueFactory(new PropertyValueFactory<>("Payer"));
        tablereserve.setItems(sr.affciher());
        suppide.setCellFactory(new Callback<TableColumn<Reservation, Void>, TableCell<Reservation, Void>>() {
            @Override
            public TableCell<Reservation, Void> call(final TableColumn<Reservation, Void> param) {
                final TableCell<Reservation, Void> cell = new TableCell<Reservation, Void>() {

                    private final Button btn = new Button("Supprimer");

                    {
                        btn.setOnAction((ActionEvent event) -> {

                            Reservation r = getTableView().getItems().get(getIndex());
                                 System.out.println(r);
                            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                            alert.setTitle("Confirmation de la suppression");
                            alert.setHeaderText("Voulez-vous vraiment supprimer cette réservation ?");
                            alert.setContentText("La réservation sera définitivement supprimée.");
                            Optional<ButtonType> result = alert.showAndWait();
                            if (result.get() == ButtonType.OK) {
                                sr.supprimer(r);
                                tablereserve.setItems(sr.affciher());
                            }

                        });
                    }

                    // Cette méthode updateItem() sera appelée pour chaque ligne de la tablereservation,
                    // ce qui permettra de mettre un bouton "Modifier" pour chaque ligne
                    @Override
                    protected void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            setGraphic(btn);
                        }
                    }
                };
                return cell;
            }
        });

    }

    
    private void afficher(ActionEvent event) {

        
        ObservableList<Reservation> reservation = FXCollections.observableArrayList(sr.affciher());
        tablereserve.setItems(reservation);
        categ_gal.setCellValueFactory(new PropertyValueFactory<>("Categorie Galerie"));
        Nom_Galerie.setCellValueFactory(new PropertyValueFactory<>("Nom Galerie"));
        Date_Gal.setCellValueFactory(new PropertyValueFactory<>("Date Galerie"));
         payeride.setCellValueFactory(new PropertyValueFactory<>("payer"));
        System.out.println(sr.affciher());
    }

    @FXML
    private void Reserver(ActionEvent event) throws IOException {
        Parent fxml;
        fxml = FXMLLoader.load(getClass().getResource("ReservationAddPageFXML.fxml"));
        Scene scene3 = new Scene(fxml);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(scene3);
        window.show();
    }
    @FXML
    private void coupon (ActionEvent event) throws IOException {
        Parent fxml;
        fxml = FXMLLoader.load(getClass().getResource("CoupantFXML.fxml"));
        Scene scene3 = new Scene(fxml);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(scene3);
        window.show();
    }


}
