 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projet.GUI;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
//import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
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
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader; 
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Dialog;
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
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
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
   
private ObservableList<Article> suggestions = FXCollections.observableArrayList();
    @FXML
    private TableView<Commentaire> commentsTable;
    @FXML
    private TableColumn<Commentaire, String> contenu;
    @FXML
    private TableColumn<Article, Void> commentsColumn;
    @FXML
    private TableColumn<Commentaire,   String> date;


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
        private final Button commentsButton = new Button("Show Comments");
        private final Button deleteButton = new Button();
        private final Button editButton =new Button();

        {
            commentsButton.setOnAction(event -> {
                Article article = getTableView().getItems().get(getIndex());
                ServiceCommentaire serviceCommentaire = new ServiceCommentaire();
                System.out.println("Selected article ID: " + article.getId());


                ObservableList<Commentaire> comments = serviceCommentaire.getCommentsForArticle(article.getId());
                System.out.println("Comments for article " + article.getId() + ": " + comments);

                contenu.setCellValueFactory(new PropertyValueFactory<>("Contenu"));
                date.setCellValueFactory(new PropertyValueFactory<>("date_c"));
                
commentsTable.setItems(comments);
              //  commentsTable.setItems(comments);
            });
        }
         { FontAwesomeIconView deleteIcon = new FontAwesomeIconView(FontAwesomeIcon.TRASH);
        deleteIcon.setStyle(
            " -fx-cursor: hand ;"
            + "-glyph-size:28px;"
            + "-fx-fill:#ff1744;"
        ); deleteButton.setGraphic(deleteIcon);
        deleteButton.setOnAction(event -> {
            deleteB();
        articlesTable.getItems().removeAll(articlesTable.getSelectionModel().getSelectedItem());
        System.out.println(articlesTable);
        articlesTable.refresh();
         Article selectedArticle = articlesTable.getSelectionModel().getSelectedItem();
   
        }); 
         }
         {FontAwesomeIconView editIcon = new FontAwesomeIconView(FontAwesomeIcon.PENCIL_SQUARE);
           editIcon.setStyle(
                                " -fx-cursor: hand ;"
                                + "-glyph-size:28px;"
                                + "-fx-fill:#00E676;"
                        ); editButton.setGraphic(editIcon);
                        editButton.setOnAction(event -> {  
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
   
    Dialog<Article> dialog = new Dialog<>();
    dialog.setTitle("Edit Article");
    
    // Set the button types
    ButtonType saveButtonType = new ButtonType("Save", ButtonBar.ButtonData.OK_DONE);
    dialog.getDialogPane().getButtonTypes().addAll(saveButtonType, ButtonType.CANCEL);
    
    // Create the form fields
    TextField titreField = new TextField(selectedArticle.getTitre());
    ComboBox<String> categorieField = new ComboBox<>(FXCollections.observableArrayList("Art", "Peinture", "Culture"));
    categorieField.setValue(selectedArticle.getCategorie());
    TextArea descrField = new TextArea(selectedArticle.getDescription());
    
    // Layout the form fields in a grid
    GridPane grid = new GridPane();
    grid.setHgap(10);
    grid.setVgap(10);
    grid.add(new Label("Titre:"), 0, 0);
    grid.add(titreField, 1, 0);
    grid.add(new Label("Categorie:"), 0, 1);
    grid.add(categorieField, 1, 1);
    grid.add(new Label("Description:"), 0, 2);
    grid.add(descrField, 1, 2);
    
    dialog.getDialogPane().setContent(grid);
    
    // Convert the result to an article object when the save button is clicked
    dialog.setResultConverter(dialogButton -> {
        if (dialogButton == saveButtonType) {
            Article editedArticle = new Article(categorieField.getValue(), titreField.getText(), descrField.getText());
            editedArticle.setId(selectedArticle.getId());
            return editedArticle;
        }
        return null;
    });
    
    Optional<Article> result = dialog.showAndWait();
    
    result.ifPresent(editedArticle -> {
        // Update the Article object with the new values
        selectedArticle.setCategorie(editedArticle.getCategorie());
        selectedArticle.setTitre(editedArticle.getTitre());
        selectedArticle.setDescription(editedArticle.getDescription());
        
        // Call the update() method of the ServiceArticle class, passing in the updated Article object.
        ServiceArticle articleService = new ServiceArticle();
        articleService.updateArticle(selectedArticle);
        
        // Refresh the TableView to reflect the changes.
        articlesTable.refresh();
      });
    });

                                } 

        @Override
        public void updateItem(Void item, boolean empty) {
            super.updateItem(item, empty);
            if (empty) {
                setGraphic(null);
                 setText(null);
            } else {
                setGraphic(commentsButton);
            
                setGraphic(new HBox(commentsButton, deleteButton,editButton));
            setText(null);
                
                       
                  }
    }
});






           
                        
//commentaire.setCellValueFactory(new PropertyValueFactory<>("commentaires"));
          articlesTable.setItems(list);
 categorie.getItems().addAll("Art", "peinture");
 //categorie.setOnAction(this::filterByCategory);
searchField.setOnAction(this::search);

   

   
       
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

    

 public void deleteB() {
      ServiceArticle SA = new ServiceArticle();
       Article selectedArticle = articlesTable.getSelectionModel().getSelectedItem();
       SA.delete(selectedArticle.getId());
        SA.delete(articlesTable.getSelectionModel().getSelectedItem().getId());
       System.out.println(articlesTable.getSelectionModel().getSelectedItem().getId());
     
 }
// public void deleteC() {
//     ServiceCommentaire  SC = new ServiceCommentaire();
//       Commentaire selectedCommentaire = commentsTable.getSelectionModel().getSelectedItem(); 
//       SC.delete(selectedCommentaire.getIdC());
//       SC.delete(commentsTable.getSelectionModel().getSelectedItem().getIdC()); }
//  @FXML
//    private void delete(ActionEvent event) {
//          deleteB();
//        articlesTable.getItems().removeAll(articlesTable.getSelectionModel().getSelectedItem());
//        System.out.println(articlesTable);
//        articlesTable.refresh();
//         Article selectedArticle = articlesTable.getSelectionModel().getSelectedItem();
//   
//        deleteC();
//  commentsTable.getItems().removeAll(commentsTable.getSelectionModel().getSelectedItem());
//  commentsTable.refresh();
//   Commentaire selectedCommentaire = commentsTable.getSelectionModel().getSelectedItem();
//    // Get the selected Article object
   
   
    
   
    // Delete the selected article from the database
   
}


  
    

//    @FXML
//    private void modifier(ActionEvent event) {
//  
//    // Get the selected Article object
//    Article selectedArticle = articlesTable.getSelectionModel().getSelectedItem();
//    if (selectedArticle == null) {
//        // No article selected, display an error message
//        Alert alert = new Alert(Alert.AlertType.ERROR);
//        alert.setTitle("Error");
//        alert.setHeaderText(null);
//        alert.setContentText("Please select an article to edit.");
//        alert.showAndWait();
//        return;
//    }
//   
//    Dialog<Article> dialog = new Dialog<>();
//    dialog.setTitle("Edit Article");
//    
//    // Set the button types
//    ButtonType saveButtonType = new ButtonType("Save", ButtonBar.ButtonData.OK_DONE);
//    dialog.getDialogPane().getButtonTypes().addAll(saveButtonType, ButtonType.CANCEL);
//    
//    // Create the form fields
//    TextField titreField = new TextField(selectedArticle.getTitre());
//    ComboBox<String> categorieField = new ComboBox<>(FXCollections.observableArrayList("Art", "Peinture", "Culture"));
//    categorieField.setValue(selectedArticle.getCategorie());
//    TextArea descrField = new TextArea(selectedArticle.getDescription());
//    
//  
         
  

//private void filterByCategory(ActionEvent event) {
//    // Get the selected category from the ComboBox
//    String selectedCategory = categorie.getValue();
//    
//    // Call the getArticlesByCategory method of the ServiceArticle class to get a filtered list of articles
//    ServiceArticle articleService = new ServiceArticle();
//    List<Article> filteredArticles = articleService.getArticlesByCategory(selectedCategory);
//    
//    // Update the TableView to display the filtered list of articles
//    articlesTable.setItems(FXCollections.observableArrayList(filteredArticles));






    

   

