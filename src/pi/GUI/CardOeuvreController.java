/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pi.GUI;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import pi.Entities.Oeuvre;
import pi.Services.ServiceOeuvre;
import pi.Entities.Categorie_oeuvre;
import pi.Entities.Famille;
import pi.Services.ServiceCatOeuv;
import pi.Services.ServiceVote;
/**
 * FXML Controller class
 *
 * @author octanet
 */
public class CardOeuvreController implements Initializable {

    private AnchorPane Cardoeuvre;
    @FXML
    private Label titleLabel;
    @FXML
    private Label artisteLabel;
    private Label butndetailLabel;
    
    //IMAGE
        public static String pathfile; 
        public static String filename="";
    @FXML
    private AnchorPane Panedetaill;
    @FXML
    private Label Etatlabel;
    @FXML
    private Label familleLabel;
    @FXML
    private Label prixLabel;
    @FXML
    private Button btnvote;
    @FXML
    private Button btnclosefiche;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
   ServiceOeuvre s = new ServiceOeuvre();

    void setData(Oeuvre o) {
        
//        //recupere l'oeuvre (objet ) bich itnajim itrecuperi el path imta3 l'image 
  //     Image image = new Image( o.getImage().getImagePath().toURI().toString());
//    Image icon = new Image("/Image/icon.png");
//    image.setImage(icon);
    //Ajoutimage.getImage().impl_getUrl();
    //image.setImage(o.getImage().impl_getUrl());
    titleLabel.setText(o.getTitre_oeuvre());
    artisteLabel.setText(o.getArtiste());
    butndetailLabel.setText(o.toString());
    //Famille.setValue();
    //state_famille.setText(String.valueOf(o.getFamille()));
     Button detailsButton = new Button("Détails");
     detailsButton.setOnAction(event -> showOeuvreDetails(o));
     Cardoeuvre.getChildren().add(detailsButton);

    int catId = o.getId();
    Cardoeuvre.getProperties().put("catId", catId);
    }
    
    private void showOeuvreDetails(Oeuvre o) {
    try {
        // Charger l'interface FXML OeuvreDetail.fxml
        FXMLLoader loader = new FXMLLoader(getClass().getResource("OeuvreDetail.fxml"));
        Parent root = loader.load();

        // Récupérer les Labels de la vue pour afficher les détails de l'oeuvre
        
         Label titleValueLabel = new Label(o.getTitre_oeuvre());
        Label titleLabel = (Label) loader.getNamespace().get("titleLabel");
        Label artisteLabel = (Label) loader.getNamespace().get("artisteLabel");
        Label prixLabel = (Label) loader.getNamespace().get("prixLabel");
         //Label prixLabel = (Label) loader.getNamespace().get("prix_oeuvre");
         
        
        // Ajouter d'autres Labels pour afficher d'autres détails de l'oeuvre
        Label Id_cat_oeuv = (Label) loader.getNamespace().get("Id_cat_oeuv");
        Label Etatlabel = (Label) loader.getNamespace().get("Etatlabel");
         Label familleLabel = (Label) loader.getNamespace().get("Id_cat_oeuv");
        // Afficher les détails de l'article dans les Labels correspondants
        titleLabel.setText(o.getTitre_oeuvre());
        artisteLabel.setText(o.getArtiste());
         familleLabel.setText(o.getFamille());
        //prixLabel.setText(Integer.toString(o.getPrix_oeuvre()));
         prixLabel.setText(o.getPrix_oeuvre());
   
        // Ajouter d'autres détails de l'oeuvre dans les Labels correspondants
      // String Id_cat_oeuv = this.Id_cat_oeuv.getText();
       //Id_cat_oeuv.setText(Integer.parseInt(Id_cat_oeuv.getText()));
        //prixLabel.setText(o.getPrix_oeuvre().toString());
        Etatlabel.setText(o.getEtat());
        // Créer une nouvelle fenêtre pour afficher les détails de l'oeuvre
        Stage scene = new Stage();
        scene.setTitle(o.getTitre_oeuvre());
       // stage1.setArtiste(o.getArtiste());
        scene.setScene(new Scene(root));
        scene.show();
    } catch (IOException e) {
        e.printStackTrace();
    }
}

    @FXML
    private void Voter(ActionEvent event) {
//       ServiceVote v = new ServiceVote(); 
//        v.setId_client(rs.getId_client());
//        v.setRate((int) rating.getRating());
//        if (!sv.existe(v.getId_oeuvre(),v.getId_client())){     
//            sv.add(v);
//        }else{
//            JOptionPane.showMessageDialog(null,"vous avez deja evaluer");
//        }
    }

    @FXML
    private void Closefiche(ActionEvent event) {
         
    }
    
    

    
}
