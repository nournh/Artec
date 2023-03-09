/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projet.GUI;

import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.awt.image.DataBuffer;
import java.awt.image.DataBufferByte;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.ByteBuffer;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.ArrayList; 
import java.util.List;
import static java.util.Locale.filter;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelFormat;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.image.WritablePixelFormat;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;                
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javax.imageio.ImageIO;
import javax.smartcardio.Card;
import static jdk.nashorn.internal.objects.NativeFunction.function;
import projet.Entities.Article;
import projet.Entities.Commentaire;
import projet.Entities.User;
import projet.Services.BadWordFilter;
import projet.Services.ServiceArticle;


/**
 * FXML Controller class
 *                        
 * @author Lenovo
 */
public class ArticleController implements Initializable {

    @FXML
    private GridPane CrdView;
private List<Article> articles;
private List<Commentaire> comments;
    @FXML
    private TextField searchField;
    @FXML
    private Button ajouter;
    
    
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
        String dburl = "jdbc:mysql://localhost:3306/Artec";
        String username = "root";
        String password = "";
        Connection conn = DriverManager.getConnection(dburl, username, password);

        // Fetch the articles from the database
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM article  WHERE archive = 0");

        // Convert the ResultSet to a List of Article objects
        List<Article> articles = new ArrayList<>();
        while (rs.next()) {
            Article article = new Article();
            article.setTitre(rs.getString("Titre"));
            article.setDescription(rs.getString("Description"));
            article.setId(rs.getInt("Id"));
            article.setLikes(rs.getInt("Likes"));
          

          articles.add(article);
          
}


            // Set other properties of the Article object as needed
        
        // Initialize the card view with the fetched articles
        initCardView(articles);

        // Close the database connection
        conn.close();
    } catch (SQLException e) {
        e.printStackTrace();
    }   catch (IOException ex) {
            Logger.getLogger(ArticleController.class.getName()).log(Level.SEVERE, null, ex);
        }
}

private void initCardView(List<Article> articles) throws IOException, SQLException {
 
    int row = 0;
    int col = 0;

    for (Article article : articles) {
        // Créer une carte pour l'article
        VBox card = createCard(article);

        // Ajouter la carte à la grille en alternant les colonnes
        if (col % 1== 0) {
            CrdView.add(card, col, row);
        } else {
            CrdView.add(card, col, row+1);
        }
        
        // Calculer la prochaine position dans la grille
        col++;
        if (col == 3) {
            col = 0;
            row ++;
        }
    


   



   }}

        
    
    
    private VBox createCard(Article article) throws IOException, SQLException {
        // Créer une carte en utilisant HBox ou VBox pour organiser les éléments de la carte
       
    // Créer une carte en utilisant HBox ou VBox pour organiser les éléments de la carte
    VBox card = new VBox();
    card.getStyleClass().add("card");

    // Ajouter un titre à la carte
    Label titleLabel = new Label("Titre: ");
   // titleLabel.getStyleClass().addAll("card-title", "card-title-custom");
    Label titleValueLabel = new Label(article.getTitre());
       HBox titleBox = new HBox(titleLabel, titleValueLabel);
    titleBox.getStyleClass().add("card-title");
    card.getChildren().add(titleBox);
   // titleLabel.getStyleClass().add("card-title");
   // card.getChildren().addAll(titleLabel, titleValueLabel);
    
 // ImageView imageView = new ImageView();
  
  //  card.getChildren().add(imageView);
    // Ajouter un bouton pour afficher les détails de l'article et ses commentaires
    Button detailsButton = new Button("Détails");
   detailsButton.setOnAction(event -> {
        try {
            showArticleDetails(article);
        } catch (SQLException ex) {
            Logger.getLogger(ArticleController.class.getName()).log(Level.SEVERE, null, ex);
        }
    });
    card.getChildren().add(detailsButton);

//    int articleId = article.getId();
//    card.getProperties().put("articleId", articleId);

    return card;
}


       
private void showArticleDetails(Article article) throws SQLException {
    try {
        // Charger l'interface FXML ArticleDetails.fxml
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ArticleDetails.fxml"));
        Parent root = loader.load();

        // Récupérer les Labels de la vue pour afficher les détails de l'article
        Label titleLabel = (Label) loader.getNamespace().get("titleLabel");
        Label descriptionLabel = (Label) loader.getNamespace().get("descriptionLabel");
        Label catégorie = (Label) loader.getNamespace().get("catégorie");
        Label likesLabel = (Label) loader.getNamespace().get("likesLabel");
         
        ScrollPane commentsScrollPane = (ScrollPane) loader.getNamespace().get("commentsScrollPane");
         Button addCommentButton = (Button) loader.getNamespace().get("addCommentButton");
         // Récupérer le bouton "Ajouter un commentaire"
        Button likeButton = (Button) loader.getNamespace().get("likeButton");
        //Button dislike = (Button) loader.getNamespace().get("dislike");
        Button back =(Button) loader.getNamespace().get("back");

 likeButton.setOnAction(event -> {
    // Prompt the user to enter their ID
    TextInputDialog dialog = new TextInputDialog();
    dialog.setTitle("Enter your ID");
    dialog.setHeaderText("Please enter your ID to like this article.");
    dialog.setContentText("ID:");

    Optional<String> result = dialog.showAndWait();
    if (result.isPresent()) {
        // Convert the ID to an integer
        int userId = Integer.parseInt(result.get());

        try {
            // Check if the user has already liked the article
            if (hasUserLikedArticle(article, userId)) {
                // Show an error message
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("You have already liked this article");
                alert.showAndWait();
            } else {
                // Update the number of likes for the Article object
                article.setLikes(article.getLikes() + 1);

                // Call the likeArticle method to update the number of likes in the database
                likeArticle(article, userId, true);

                // Update the likesLabel to display the new number of likes
                likesLabel.setText(Integer.toString(article.getLikes()));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ArticleController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
});




        // Afficher les détails de l'article dans les Labels correspondants
        titleLabel.setText(article.getTitre());
        descriptionLabel.setText(article.getDescription());
        likesLabel.setText(Integer.toString(article.getLikes()));
        catégorie.setText(article.getCategorie());
        
        // Récupérer les commentaires de l'article depuis la base de données
        List<Commentaire> comments = getCommentsForArticle(article.getId());
        
        // Initialiser la ScrollPane des commentaires avec les commentaires récupérés
        initCommentsScrollPane(commentsScrollPane, comments);
         
           addCommentButton.setOnAction(event -> {
    try {
        showAddCommentDialog(article, commentsScrollPane);
    } catch (SQLException ex) {
        Logger.getLogger(ArticleController.class.getName()).log(Level.SEVERE, null, ex);
    }
});
               
        back.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("Article.fxml"));
                    Parent root = loader.load();
                    ArticleController pc = loader.getController();
                    back.getScene().setRoot(root);
                }catch (IOException ex) {
                    System.err.println(ex.getMessage());
                }
            }
        });
           refreshComments(commentsScrollPane, article.getId());

        // Créer une nouvelle fenêtre pour afficher les détails de l'article
        Stage stage = new Stage();
        stage.setTitle(article.getTitre());
        stage.setScene(new Scene(root));
        stage.show();
    } catch (IOException e) {
        e.printStackTrace();
    }
   
    
    
    
    
}



private List<Commentaire> getCommentsForArticle(int articleId) throws SQLException {
    List<Commentaire> comments = new ArrayList<>();
    
    // Exécuter une requête SQL pour récupérer les commentaires associés à l'article
    String dburl = "jdbc:mysql://localhost:3306/Artec";
    String username = "root";
    String password = "";
    Connection conn = DriverManager.getConnection(dburl, username, password);
    PreparedStatement stmt = conn.prepareStatement("SELECT * FROM feedback WHERE id_art = ?");
    stmt.setInt(1, articleId);
    ResultSet rs = stmt.executeQuery();

    // Convertir le ResultSet en une liste d'objets Commentaire
    while (rs.next()) {
        Commentaire comment = new Commentaire();
        comment.setContenu(rs.getString("Contenu"));
    //    comment.setDate_c(rs.getDate("date"));
        // Ajouter d'autres propriétés de l'objet Commentaire si nécessaire
        
        comments.add(comment);
    }

    conn.close();
    
    return comments;
}

private void initCommentsScrollPane(ScrollPane commentsScrollPane, List<Commentaire> comments) {
    VBox commentsContainer = new VBox();
    
    // Ajouter chaque commentaire dans un Label
    for (Commentaire comment : comments) {
        Label commentLabel = new Label(comment.getContenu() + " - " );
        // Ajouter d'autres propriétés du commentaire au Label si nécessaire
        
        commentsContainer.getChildren().add(commentLabel);
    }
    
    // Ajouter le VBox contenant les commentaires dans la ScrollPane
    commentsScrollPane.setContent(commentsContainer);
}
private void showAddCommentDialog(Article article, ScrollPane commentsScrollPane) throws SQLException {
    TextInputDialog dialog = new TextInputDialog();
    dialog.setTitle("Ajouter un commentaire");
    dialog.setHeaderText("Saisissez votre feedback pour l'article \"" + article.getTitre() + "\" :");
    dialog.setContentText("Commentaire :");

    Optional<String> result = dialog.showAndWait();
    if (result.isPresent()) {
        String contenu = result.get();
        BadWordFilter filter = new BadWordFilter();
//String inputText = "This is a test sentence that contains a bad word: shit";
if (filter.containsBadWord(contenu)) {
    // Display an error message or take other actions as needed
     Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
       
        alert.setContentText("The input text contains a bad word.");
        alert.showAndWait();
    System.out.println("The input text contains a bad word.");
} else {
    // Proceed with normal processing
    System.out.println("The input text is clean.");
        ajouterCommentaire(article.getId(), contenu,commentsScrollPane);
        
    }
 
}
}

private void ajouterCommentaire(int articleId, String contenu, ScrollPane commentsScrollPane) throws SQLException {

    
        String dburl = "jdbc:mysql://localhost:3306/Artec";
        String username = "root";
        String password = "";
        Connection conn = DriverManager.getConnection(dburl, username, password);
        
        PreparedStatement stmt = conn.prepareStatement("INSERT INTO feedback (id_art, Contenu) VALUES (?, ?)");
        stmt.setInt(1, articleId);
        stmt.setString(2, contenu);
        stmt.executeUpdate();
         refreshComments(commentsScrollPane, articleId);
        conn.close();
   
    
}
private void likeArticle(Article article, int userId, boolean like) throws SQLException {
    String dburl = "jdbc:mysql://localhost:3306/Artec";
    String username = "root";
    String password = "";
    Connection conn = DriverManager.getConnection(dburl, username, password);

    // Check if the user has already liked this article
    PreparedStatement checkStmt = conn.prepareStatement("SELECT COUNT(*) FROM likes WHERE user_id = ? AND article_id = ?");
    checkStmt.setInt(1, userId);
    checkStmt.setInt(2, article.getId());
    ResultSet checkResult = checkStmt.executeQuery();
    checkResult.next();
    int count = checkResult.getInt(1);
    if (count > 0) {
        System.out.println("User has already liked this article");
        return;
    }

    // Update the number of likes in the article table
    if (like) {
        PreparedStatement updateStmt = conn.prepareStatement("UPDATE article SET likes = likes + 1 WHERE id = ?");
        updateStmt.setInt(1, article.getId());
        updateStmt.executeUpdate();
    }

    // Insert a new row into the likes table
    PreparedStatement insertStmt = conn.prepareStatement("INSERT INTO likes (user_id, article_id) VALUES (?, ?)");
    insertStmt.setInt(1, userId);
    insertStmt.setInt(2, article.getId());
    insertStmt.executeUpdate();

    conn.close();
}

  private boolean hasUserLikedArticle(Article article, int userId) throws SQLException {
    String dburl = "jdbc:mysql://localhost:3306/Artec";
    String username = "root";
    String password = "";
    Connection conn = DriverManager.getConnection(dburl, username, password);

    PreparedStatement stmt = conn.prepareStatement("SELECT COUNT(*) FROM likes WHERE user_id = ? AND article_id = ?");
    stmt.setInt(1, userId);
    stmt.setInt(2, article.getId());
    ResultSet rs = stmt.executeQuery();
    rs.next();
    int count = rs.getInt(1);

    conn.close();

    return count > 0;
}



private void refreshComments(ScrollPane commentsScrollPane, int articleId) throws SQLException {
    List<Commentaire> comments = getCommentsForArticle(articleId);
    initCommentsScrollPane(commentsScrollPane, comments);
}

    public void setArticle(Article article) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
@FXML
private void search(ActionEvent event) {
    ServiceArticle serviceArticle = new ServiceArticle();
    String searchText = searchField.getText();
    ObservableList<Article> allArticles = serviceArticle.getall();

    // Filter the articles based on the search text
    ObservableList<Article> filteredList = allArticles.filtered(article ->
        article.getCategorie().toLowerCase().contains(searchText.toLowerCase())
    );

    // Clear the current content of the card view
    CrdView.getChildren().clear();

    // Create a new grid pane to display the filtered articles
    GridPane gridPane = new GridPane();
    gridPane.setHgap(10);
    gridPane.setVgap(10);

    // Add the filtered articles as new cards to the grid
    int rowIndex = 0;
    int columnIndex = 0;
    for (Article article : filteredList) {
        try {
            VBox card = createCard(article);
            gridPane.add(card, columnIndex, rowIndex);

            // Increment the row or column index based on the grid layout
            if (columnIndex == 1) {
                rowIndex++;
                columnIndex = 0;
            } else {
                columnIndex++;
            }
        } catch (IOException | SQLException ex) {
            Logger.getLogger(ArticleController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // Add the grid to the card view
    CrdView.getChildren().add(gridPane);
}

//    @FXML
//    private void search(ActionEvent event) {
//          ServiceArticle serviceArticle = new ServiceArticle();
//    String searchText = searchField.getText();
//    ObservableList<Article> allArticles = serviceArticle.getall();
//
//    // Filter the articles based on the search text
//    ObservableList<Article> filteredList = allArticles.filtered(article -> {
//       // return article.getTitre().toLowerCase().contains(searchText.toLowerCase()) ||
//                //article.getDescription().toLowerCase().contains(searchText.toLowerCase()) ||
//             return    article.getCategorie().toLowerCase().contains(searchText.toLowerCase());
//    });
//
//    // Show the filtered articles in the table
//   CrdView.getChildren().clear();
//
//    // Add the filtered articles as new cards to the card view
//    filteredList.forEach(article -> {
//        VBox card = null;
//              try {
//                  card = createCard(article);
//              } catch (IOException ex) {
//                  Logger.getLogger(ArticleController.class.getName()).log(Level.SEVERE, null, ex);
//              } catch (SQLException ex) {
//                  Logger.getLogger(ArticleController.class.getName()).log(Level.SEVERE, null, ex);
//              }
//        CrdView.getChildren().add(card);
//    });
//    }

}
   
    
       




    