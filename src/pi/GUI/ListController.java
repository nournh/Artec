/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pi.GUI;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.controlsfx.control.Rating;
import pi.Entities.Oeuvre;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;
import javafx.scene.image.ImageView;
import pi.Entities.Categorie_oeuvre;
import pi.Services.ServiceOeuvre;


/**
 * FXML Controller class
 *
 * @author octanet
 */
public class ListController implements Initializable {

    private Oeuvre o;
private Parent fxml , fxml1,fxml2,fxml3;


private Stage stage;
private Scene scene;
private Parent root;
//private CardOeuvre CardOeuvre;
   
    @FXML
    private GridPane cardView;
    private List<Oeuvre> oeuvres; 
    private TableView<Oeuvre> tableoeuvre;
    private List<Categorie_oeuvre> categories;
    private Rating rating;
  
    @FXML
    private TextField chercher;

    private ComboBox<String> state_famille;    
    @FXML
    private AnchorPane Panedetaill;
    @FXML
    private ScrollPane scroll;
    @FXML
    private Button btnretour;
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    
    
    
   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         try {
        String dburl = "jdbc:mysql://localhost:3306/artec";
        String username = "root";
        String password = "";
        Connection cnx = DriverManager.getConnection(dburl, username, password);

        // Fetch the oeuvres from the database
        Statement stmt = cnx.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM oeuvre  WHERE archive = 0");

        // Convert the ResultSet to a List of oeuvre objects
        List<Oeuvre> oeuvres = new ArrayList<>();
        while (rs.next()) {
            Oeuvre o = new Oeuvre();
                o.setId(rs.getInt("id"));
                //o.setImage(rs.getImage().impl_getUrl());
                o.setTitre_oeuvre(rs.getString("titre_oeuvre"));
                o.setArtiste(rs.getString("artiste"));
                o.setFamille(rs.getString("famille"));// aappartient a quelle type d'oeuvre
                o.setPrix_oeuvre(rs.getString("prix_oeuvre"));
                o.setId_cat_oeuv(rs.getInt("Id_cat_oeuv"));
                o.setEtat(rs.getString("Etat"));
                oeuvres.add(o);
                }
          // Set other properties of the oeuvre object as needed
        
        // Initialize the card view with the fetched oeuvreq
           initCardView(oeuvres); // na7it hethy awel wahda
//           createCard(o);
        
        // Close the database connection
        cnx.close();
    } catch (SQLException e) {
         e.printStackTrace();
    }
      
}

private void initCardView(List<Oeuvre> oeuvres) {
 
    int row = 0;
    int col = 0;
     for (Oeuvre o : oeuvres){
  //  for (int i = 0; i <oeuvres.size(); i++){
        // Créer une carte pour l'oeuvre
//////        FXMLLoader fxmlLoader = new FXMLLoader();
//////                System.out.println(" taaditoooooou");
//////                fxmlLoader.setLocation(getClass().getResource("CardOeuvre.fxml"));
//////       
//////         Pane card = fxmlLoader.load();
//////         CardOeuvreController cardController = fxmlLoader.getController();
//////                System.out.println(" taad");
//////                Oeuvre o = oeuvres.get(i);
//////                System.out.println(" taaditou1111");
//////
        VBox card = createCard(o);
//////                System.out.println(" ta");
//////                cardController.setData(o);
//////                System.out.println(" taaditou52554");
//////

// Ajouter la carte à la grille en alternant les colonnes

if (col % 1== 0) {
    cardView.add(card, col, row);
} else {
    cardView.add(card, col, row+1);
}

// Calculer la prochaine position dans la grille
col++;
if (col == 3) {
    col = 0;
    row ++;
}}
   }

        
    
    
    private VBox createCard(Oeuvre o) {
       
     
    // Créer une carte en utilisant HBox ou VBox pour organiser les éléments de la carte
    VBox card = new VBox();
   // card.getStyleClass().add("Cardoeuvre");

    // Ajouter un titre à la carte
    Label titleLabel = new Label("Titre: ");
    Label titleValueLabel = new Label(o.getTitre_oeuvre());
    Label artisteLabel = new Label("Artiste: ");
    Label artisteValueLabel = new Label(o.getArtiste());
    
    //titleLabel.getStyleClass().add("card-title");
    card.getChildren().addAll(titleLabel, titleValueLabel);
    card.getChildren().addAll(artisteLabel, artisteValueLabel);
    //card.getChildren().addAll(prixLabel, prixValueLabel);
    // Ajouter un bouton pour afficher les détails de l'oeuvre et ses commentaires
    Button detailsButton = new Button("Détails");
    detailsButton.setOnAction(event -> {
        showOeuvreDetails(o);
    });
    card.getChildren().add(detailsButton);

    int catId = o.getId();
    card.getProperties().put("catId", catId);

    return card;
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
        Label familleLabel = (Label) loader.getNamespace().get("familleLabel");
        Label Etatlabel = (Label) loader.getNamespace().get("Etatlabel");
        // Afficher les détails de l'article dans les Labels correspondants
        titleLabel.setText(o.getTitre_oeuvre());
        artisteLabel.setText(o.getArtiste());
         familleLabel.setText(o.getFamille());
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
 
   //filtrage by titre


    public void setOeuvre(Oeuvre o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    

    private void Rating(ActionEvent event) {
        System.out.println("Rating given by user : " + rating.getRating());
    }

//    private void retourfiche(MouseEvent event) {
//        //Panedetaill.getScene().getWindow().hide();
//        Stage retourfiche = new Stage();
//        try {
//        fxml1 = FXMLLoader.load(getClass().getResource("Listeoeuvre.fxml"));
//        Scene scene1 = new Scene(fxml1);
//        retourfiche.setScene(scene1);
//        retourfiche.show();
//        } catch (IOException e) {
//            e.printStackTrace();}
//       try {
//                   fxml = FXMLLoader.load(getClass().getResource("AjouterOeuvre.fxml"));
//                   root.getChildren().removeAll();
//                   root.getChildren().setAll(fxml);
//                   
//
//           }catch (IOException e) {
//              e.printStackTrace();}
  //  }


    @FXML
    public void retourscene1(ActionEvent event) throws IOException {
         
           Parent root = FXMLLoader.load(getClass().getResource("Ajouteroeuvre.fxml"));
           stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    

    public void switchtofichedetail(ActionEvent event) throws IOException {
         
         Parent root = FXMLLoader.load(getClass().getResource("OeuvreDetail.fxml"));
         
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    
    }


    @FXML
    private void chercherOeuvre(ActionEvent event) {
    
    ServiceOeuvre s = new ServiceOeuvre();
    String searchText = chercher.getText();
    ObservableList<Oeuvre> allOeuvres = s.getall();

    // Filter the articles based on the search text
    ObservableList<Oeuvre> filteredList = allOeuvres.filtered(o -> {
        return o.getTitre_oeuvre().toLowerCase().contains(searchText.toLowerCase()) ||
                o.getArtiste().toLowerCase().contains(searchText.toLowerCase()) ||
                o.getFamille().toLowerCase().contains(searchText.toLowerCase());
    });
    // Show the filtered articles in the table
    cardView.getChildren().clear();

    // Add the filtered articles as new cards to the card view
       filteredList.forEach(o -> {
        Pane card = createCard(o);
        cardView.getChildren().add(card);
    });
         
//        
    }
    
    
    private void filterByFamille(ActionEvent event) {
    // Get the selected category from the ComboBox
    String selectedCategory = state_famille.getValue();
    
    // Call the getArticlesByCategory method of the ServiceArticle class to get a filtered list of articles
    ServiceOeuvre s = new ServiceOeuvre();
    List<Oeuvre> filteredOeuvres = s.getOeuvresByFamille(selectedCategory);
    
    // Update the TableView to display the filtered list of articles
    tableoeuvre.setItems(FXCollections.observableArrayList(filteredOeuvres));
    
}



   
}








    
