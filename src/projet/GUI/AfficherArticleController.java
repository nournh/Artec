 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projet.GUI;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader; 
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.SortEvent;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.DragEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import projet.Entities.Article;
import projet.Entities.Commentaire;
import projet.Services.ServiceArticle;
import projet.Services.ServiceCommentaire;
import projet.utils.MyDB;

/**
 * FXML Controller class
 *
 * @author Lenovo
 */
public class AfficherArticleController implements Initializable {
      
   


 // private ObservableList<Article> allArticles;
@FXML
private TextField searchField;

    ServiceArticle art = new ServiceArticle();
    @FXML
    private TableView<Article> articlesTable;
    @FXML
    private TableColumn<Article, String> categoryColumn;
    @FXML
    private TableColumn<Article, String> titleColumn;
    @FXML
    private TableColumn<Article, String> descriptionColumn;
    @FXML
    private ComboBox<String> categorie;
    @FXML
    private TextField titre;
    @FXML
    private TextArea descr;
    @FXML
    private Button delete;
    @FXML
    private Button modifier;
   
private ObservableList<Article> suggestions = FXCollections.observableArrayList();
    @FXML
    private TableView<Commentaire> commentsTable;
    @FXML
    private TableColumn<Commentaire, String> contenu;
    @FXML
    private TableColumn<Article, Void> commentsColumn;


    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
     //   allArticles = service.getall();
        //articlesTable.setItems(allArticles);
        ServiceArticle service=new ServiceArticle();
          ObservableList<Article> list = service.getall();
        
        // List<Commentaire> comments = getCommentsForArticle(list.getId());
          System.out.print(list);
           categoryColumn.setCellValueFactory(new PropertyValueFactory<>("categorie"));
titleColumn.setCellValueFactory(new PropertyValueFactory<>("Titre"));
descriptionColumn.setCellValueFactory(new PropertyValueFactory<>("Description"));
 commentsColumn.setCellFactory(param -> new TableCell<Article, Void>() {
        private final Button commentsButton = new Button("Comments");

        {
            commentsButton.setOnAction(event -> {
                Article article = getTableView().getItems().get(getIndex());
                ServiceCommentaire serviceCommentaire = new ServiceCommentaire();
                System.out.println("Selected article ID: " + article.getId());


                ObservableList<Commentaire> comments = serviceCommentaire.getCommentsForArticle(article.getId());
                System.out.println("Comments for article " + article.getId() + ": " + comments);

                contenu.setCellValueFactory(new PropertyValueFactory<>("Contenu"));
commentsTable.setItems(comments);
              //  commentsTable.setItems(comments);
            });
        }

        @Override
        public void updateItem(Void item, boolean empty) {
            super.updateItem(item, empty);
            if (empty) {
                setGraphic(null);
            } else {
                setGraphic(commentsButton);
            }
        }
    });

//commentaire.setCellValueFactory(new PropertyValueFactory<>("commentaires"));
          articlesTable.setItems(list);
 categorie.getItems().addAll("Art", "peinture");
 categorie.setOnAction(this::filterByCategory);
searchField.setOnAction(this::search);

   

   
            /* articlesTable.setOnMouseClicked(event -> {
        if (event.getClickCount() == 1) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("CommentaireArticleFXML.fxml"));
                Parent root = loader.load();
                Scene scene = new Scene(root);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                System.err.println(ex.getMessage());
            }
        }
    });*/
}

    @FXML
    private void add(ActionEvent event) {
         ServiceArticle serviceArticle = new ServiceArticle();
         
    Article article = new Article((String)categorie.getValue(), titre.getText(), descr.getText());
       serviceArticle.add(article);
 
      
  ObservableList<Article> currentData = articlesTable.getItems();
   currentData.add(article);
   System.out.println(articlesTable);
  articlesTable.refresh();
  
 
   
    }

    
   // @FXML
 public void deleteB() {
      ServiceArticle SA = new ServiceArticle();
       Article selectedArticle = articlesTable.getSelectionModel().getSelectedItem();
       SA.delete(selectedArticle.getId());
        SA.delete(articlesTable.getSelectionModel().getSelectedItem().getId());
       System.out.println(articlesTable.getSelectionModel().getSelectedItem().getId());
     
 }
 public void deleteC() {
     ServiceCommentaire  SC = new ServiceCommentaire();
       Commentaire selectedCommentaire = commentsTable.getSelectionModel().getSelectedItem(); 
       SC.delete(selectedCommentaire.getIdC());
       SC.delete(commentsTable.getSelectionModel().getSelectedItem().getIdC()); }
  @FXML
    private void delete(ActionEvent event) {
          deleteB();
        articlesTable.getItems().removeAll(articlesTable.getSelectionModel().getSelectedItem());
        System.out.println(articlesTable);
        articlesTable.refresh();
         Article selectedArticle = articlesTable.getSelectionModel().getSelectedItem();
   
        deleteC();
  commentsTable.getItems().removeAll(commentsTable.getSelectionModel().getSelectedItem());
  commentsTable.refresh();
   Commentaire selectedCommentaire = commentsTable.getSelectionModel().getSelectedItem();
    // Get the selected Article object
   
   
    
   
    // Delete the selected article from the database
   
}

        /*delete();
        articlesTable.getItems().removeAll(articlesTable.getSelectionModel().getSelectedItem());
        System.out.println(articlesTable);
        articlesTable.refresh();*/
  
    

    @FXML
    private void modifier(ActionEvent event) {
  
    // Get the selected Article object
    Article selectedArticle = articlesTable.getSelectionModel().getSelectedItem();
    if (selectedArticle == null) {
        // No article selected, display an error message
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText("Please select an article to edit.");
        alert.showAndWait();
        return;
    }
    
    // Open a new dialog window or form to allow the user to edit the selected article.
    // You can use a TextInputDialog or a custom dialog.
   /* TextInputDialog dialog = new TextInputDialog(selectedArticle.getCategorie());
    dialog.setTitle("Edit Article");
    dialog.setHeaderText(null);
    dialog.setContentText("Please enter the new category:");
    Optional<String> result = dialog.showAndWait();
    
    if (result.isPresent()) {
        // Update the Article object with the new category value.
        selectedArticle.setCategorie(result.get());*/
        
        // Open another dialog to edit the title.
        TextInputDialog titleDialog = new TextInputDialog(selectedArticle.getTitre());
        titleDialog.setTitle("Edit Article");
        titleDialog.setHeaderText(null);
        titleDialog.setContentText("Please enter the new title:");
        Optional<String> titleResult = titleDialog.showAndWait();
        
        if (titleResult.isPresent()) {
            // Update the Article object with the new title value.
            selectedArticle.setTitre(titleResult.get());
            
            // Open another dialog to edit the description.
            TextInputDialog descDialog = new TextInputDialog(selectedArticle.getDescription());
            descDialog.setTitle("Edit Article");
            descDialog.setHeaderText(null);
            descDialog.setContentText("Please enter the new description:");
            Optional<String> descResult = descDialog.showAndWait();
            
            if (descResult.isPresent()) {
                // Update the Article object with the new description value.
                selectedArticle.setDescription(descResult.get());
                
                // Call the update() method of the ServiceArticle class, passing in the updated Article object.
                ServiceArticle articleService = new ServiceArticle();
                articleService.updateArticle(selectedArticle);
                
                // Refresh the TableView to reflect the changes.
                articlesTable.refresh();
            }
        }
    
}

         
      
   @FXML
private void search(ActionEvent event) {
    ServiceArticle serviceArticle = new ServiceArticle();
    String searchText = searchField.getText();
    ObservableList<Article> allArticles = serviceArticle.getall();

    // Filter the articles based on the search text
    ObservableList<Article> filteredList = allArticles.filtered(article -> {
        return article.getTitre().toLowerCase().contains(searchText.toLowerCase()) ||
               // article.getDescription().toLowerCase().contains(searchText.toLowerCase()) ||
                article.getCategorie().toLowerCase().contains(searchText.toLowerCase());
    });

    // Show the filtered articles in the table
    articlesTable.setItems(filteredList);

   
    }
    

private void filterByCategory(ActionEvent event) {
    // Get the selected category from the ComboBox
    String selectedCategory = categorie.getValue();
    
    // Call the getArticlesByCategory method of the ServiceArticle class to get a filtered list of articles
    ServiceArticle articleService = new ServiceArticle();
    List<Article> filteredArticles = articleService.getArticlesByCategory(selectedCategory);
    
    // Update the TableView to display the filtered list of articles
    articlesTable.setItems(FXCollections.observableArrayList(filteredArticles));
}



}

    

   

