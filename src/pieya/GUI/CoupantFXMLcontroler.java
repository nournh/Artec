/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pieya.GUI;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.Callback;
import pieya.Entities.Coupant;
import pieya.Services.serviceCoupant;

/**
 * FXML Controller class
 *
 * @author eyach
 */
public class CoupantFXMLcontroler implements Initializable {

    @FXML
    private Button addd;
    @FXML
    private TableColumn<Coupant, Integer> codeid;
    @FXML
    private TableColumn<Coupant, String> codeide;
    @FXML
    private TableColumn<Coupant, Integer> valeuride;
    @FXML
    private TableView<Coupant> tableide;
    @FXML
    private TableColumn<Coupant, Void> supprimeride;
    @FXML
    private TableColumn<Coupant, Void> modifieride;
    @FXML
    private Label gest;
    @FXML
    private TextField codetxt;
    @FXML
    private TextField valeurtxt;
     @FXML
    private Button retide;

    serviceCoupant st = new serviceCoupant();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ObservableList<Coupant> list
                = st.affciher();
        tableide.setItems(list);
       codeid.setCellValueFactory(new PropertyValueFactory<>("Id_Coupant"));
        codeide.setCellValueFactory(new PropertyValueFactory<>("code"));
        valeuride.setCellValueFactory(new PropertyValueFactory<>("Valeur"));
        tableide.setItems(st.affciher());
        supprimeride.setCellFactory(new Callback<TableColumn<Coupant, Void>, TableCell<Coupant, Void>>() {
        @Override
            public TableCell<Coupant, Void> call(final TableColumn<Coupant, Void> param) {
                final TableCell<Coupant, Void> cell = new TableCell<Coupant, Void>() {

                    private final Button btn = new Button("Supprimer");

                    {
                        btn.setOnAction((ActionEvent event) -> {

                            Coupant t = getTableView().getItems().get(getIndex());
                                   System.out.println(t);
                            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                            alert.setTitle("Confirmation de la suppression");
                            alert.setHeaderText("Voulez-vous vraiment bloquer cet utilisateur ?");
                            alert.setContentText("L'utilisateur sera définitivement bloqué.");
                            Optional<ButtonType> result = alert.showAndWait();
                            if (result.get() == ButtonType.OK) {
                                 System.out.println(t);
                                st.supprimer(t);
                                tableide.setItems(FXCollections.observableArrayList(st.affciher()));
                            }

                        });
                    }
                    

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
        modifieride.setCellFactory(new Callback<TableColumn<Coupant, Void>, TableCell<Coupant, Void>>() {
            @Override
            public TableCell<Coupant, Void> call(final TableColumn<Coupant, Void> param) {
                final TableCell<Coupant, Void> cell = new TableCell<Coupant, Void>() {

                    private final Button btn = new Button("modifier");

                    {
                        btn.setOnAction((ActionEvent event) -> {

                            Coupant t = getTableView().getItems().get(getIndex());
                            System.out.println(t);
                            FXMLLoader loader = new FXMLLoader(getClass().getResource("ModifierFXML.fxml"));
                            Parent root = null;
                            try {
                                root = loader.load();
                            } catch (IOException ex) {
                                Logger.getLogger(CoupantFXMLcontroler.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            ModifierFXMLcontroler controller = loader.getController();
                            controller.setCoupant(t);
                            Scene scene = new Scene(root);
                            Stage stage = new Stage();
                            stage.setScene(scene);
                            stage.showAndWait();

                            // Rafraîchir la table des coupants
                            tableide.setItems(FXCollections.observableArrayList(st.affciher()));
                        });
                    }

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

    @FXML
    private void afficher(ActionEvent event) {

        
        ObservableList<Coupant> coupant = FXCollections.observableArrayList(st.affciher());
        tableide.setItems(coupant);
         codeid.setCellValueFactory(new PropertyValueFactory<>("Id_Coupant"));
        codeide.setCellValueFactory(new PropertyValueFactory<>("code"));
        valeuride.setCellValueFactory(new PropertyValueFactory<>("Valeur"));
        System.out.println(st.affciher());
    }

    @FXML
    private void ajouter(ActionEvent event) {

        String code = (codetxt.getText());
        System.out.println(code);
        int Valeur = Integer.parseInt(valeurtxt.getText());
        System.out.println(Valeur);
        Coupant t = new Coupant();
        t.setCode(code);
        System.out.println("code taaditou");
        t.setValeur(Valeur);
        System.out.println("Valeur taaditou");
        System.out.println(t.toString());
        serviceCoupant st = new serviceCoupant();
        st.add(t);
        tableide.getItems().add(t);

    }
    @FXML
    private void retour3 (ActionEvent event) throws IOException {
        Parent fxml;
        fxml = FXMLLoader.load(getClass().getResource("HomeReservationFXML.fxml"));
        Scene scene3 = new Scene(fxml);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(scene3);
        window.show();
    }

}
