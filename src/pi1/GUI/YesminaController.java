/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pi1.GUI;
import java.awt.Desktop;
import java.io.File;
import com.sun.pdfview.PDFViewer;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javafx.collections.ObservableList;
import java.io.IOException;
import static java.util.Locale.filter;
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
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import pi1.Entities.evenement;
import pi1.Entities.evenement.Categorie_gal;
import pi1.Services.serviceEvenement;

/**
 *
 * @author Asus
 */
public class YesminaController implements Initializable {

    private List<evenement> listeven;

    @FXML
    private ComboBox comboboxfd;
    @FXML
    private TextField recherchefd;
    @FXML
    private Label Categorie;
    @FXML
    private Button filtrery;
    @FXML
    private Button backed;
     @FXML
    private Button chercheried;
      @FXML
    private Button PDFied;
    URL url;
    ResourceBundle rb;
    @FXML
    private GridPane eventContainer;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
//      cbcategoriey.getItems().addAll("artContemporain", "artModerne", "PopArt","opArt");
//       cbcategoriey.setValue("artContemporain");
//       System.out.println(" taaditou");
        EnumSet.allOf(Categorie_gal.class).stream().forEach(s -> comboboxfd.getItems().add(s.toString()));
        comboboxfd.getItems().add("TOUTES");
        listeven = new ArrayList<>(getEvenements());
        int column = 0;
        int row = 1;
        try {
            for (int i = 0; i < listeven.size(); i++) {
                //int i=0;
                FXMLLoader fxmlLoader = new FXMLLoader();
                System.out.println(" taaditou");
                fxmlLoader.setLocation(getClass().getResource("cardView.fxml"));
                System.out.println(" taaditou");

                Pane cardBox = fxmlLoader.load();
                System.out.println(" taaditou");

                cardViewController cardController = fxmlLoader.getController();
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
            Logger.getLogger(YesminaController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<evenement> getEvenements() {
        serviceEvenement se = new serviceEvenement();
        return se.affciher();
    }

    @FXML
    private void Filter(ActionEvent event) {
        eventContainer.getChildren().clear();
        if (comboboxfd.getValue().equals("TOUTES")) {
            initialize(url, rb);
        } else {
            
          
//        File file = new File("C:\\Users\\Asus\\Downloads\\Histoire-de-lArt-contemporain-2.pdf");
//        Desktop.getDesktop().open(file);
//        
    

            
            listeven = new ArrayList<>(getEvenements());
            List<evenement> listFiltre = listeven.stream().filter(e -> e.getCateg_gal().toString().equals(comboboxfd.getValue())).collect(Collectors.toList());
            int column = 0;
            int row = 1;
            try {
                for (int i = 0; i < listFiltre.size(); i++) {
                    FXMLLoader fxmlLoader = new FXMLLoader();
                    fxmlLoader.setLocation(getClass().getResource("cardView.fxml"));
                    Pane cardBox = fxmlLoader.load();
                    cardViewController cardController = fxmlLoader.getController();
                    evenement e = listFiltre.get(i);
                    cardController.setData(e);

                    if (column == 2) {
                        column = 0;
                        ++row;
                       // PDDocument document = PDDocument.load(new File("example.pdf"));

          
                    }

                    eventContainer.add(cardBox, column++, row);
                }

            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

    @FXML
    private void chercher(ActionEvent event) {
        eventContainer.getChildren().clear();
        listeven = new ArrayList<>(getEvenements());
        List<evenement> listRecherche = listeven.stream().filter(e -> e.getNom_Galerie().toLowerCase().contains(recherchefd.getText().toLowerCase()) || e.getDescription_Galerie().toLowerCase().contains(recherchefd.getText().toLowerCase())).collect(Collectors.toList());
        int column = 0;
        int row = 1;
        try {
            for (int i = 0; i < listRecherche.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("cardView.fxml"));
                Pane cardBox = fxmlLoader.load();
                cardViewController cardController = fxmlLoader.getController();
                evenement e = listRecherche.get(i);
                cardController.setData(e);
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

    @FXML
    private void back(ActionEvent event) throws IOException {
           Parent fxml;
        fxml = FXMLLoader.load(getClass().getResource("Taw.fxml"));
        Scene scene3 = new Scene(fxml);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(scene3);
        window.show();
    }
     @FXML
    private void GenererPdf(ActionEvent event) throws IOException {
        
      
        
       if(comboboxfd.getValue().equals("artContemporain")){
               File file = new File("C:\\Users\\Asus\\Downloads\\Histoire-de-lArt-contemporain-2.pdf");
              Desktop.getDesktop().open(file);}
       else if (comboboxfd.getValue().equals("artModerne")){
           File file = new File("C:\\Users\\Asus\\Downloads\\GENARD Art moderne art contemporain esthetique philosophique.pdf");
              Desktop.getDesktop().open(file);}
          else if (comboboxfd.getValue().equals("PopArt")){
           File file = new File("C:\\Users\\Asus\\Pictures\\AnyDesk\\Le-Pop-Art.pdf");
              Desktop.getDesktop().open(file);}
            else if (comboboxfd.getValue().equals("opArt")){
           File file = new File("C:\\Users\\Asus\\Pictures\\AnyDesk\\art_cinetique_art_optique.pdf");
              Desktop.getDesktop().open(file);}
           
           
    }
    }

