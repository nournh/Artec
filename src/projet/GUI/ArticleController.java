/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projet.GUI;

import java.awt.Image;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.ArrayList; 
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
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
import javafx.scene.control.TextInputDialog;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;                
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javax.smartcardio.Card;
import static jdk.nashorn.internal.objects.NativeFunction.function;
import projet.Entities.Article;
import projet.Entities.Commentaire;


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
    }
}

private void initCardView(List<Article> articles) {
 
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

        
    
    
    private VBox createCard(Article article) {
        // Créer une carte en utilisant HBox ou VBox pour organiser les éléments de la carte
     
    // Créer une carte en utilisant HBox ou VBox pour organiser les éléments de la carte
    VBox card = new VBox();
    card.getStyleClass().add("card");

    // Ajouter un titre à la carte
    Label titleLabel = new Label("Titre: ");
    Label titleValueLabel = new Label(article.getTitre());
    titleLabel.getStyleClass().add("card-title");
    card.getChildren().addAll(titleLabel, titleValueLabel);

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

    int articleId = article.getId();
    card.getProperties().put("articleId", articleId);

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
        Label likesLabel = (Label) loader.getNamespace().get("likesLabel"); // Add a Label for the number of likes
        ScrollPane commentsScrollPane = (ScrollPane) loader.getNamespace().get("commentsScrollPane");
         Button addCommentButton = (Button) loader.getNamespace().get("addCommentButton"); // Récupérer le bouton "Ajouter un commentaire"
        Button likeButton = (Button) loader.getNamespace().get("likeButton");
likeButton.setOnAction(event -> {
    try {
          
          article.setLikes(article.getLikes() +1 );
          likeArticle(article, true);
          likesLabel.setText(Integer.toString(article.getLikes()));
    } catch (SQLException ex) {
        Logger.getLogger(ArticleController.class.getName()).log(Level.SEVERE, null, ex);
    }
});

        // Ajouter d'autres Labels pour afficher d'autres détails de l'article

        // Afficher les détails de l'article dans les Labels correspondants
        titleLabel.setText(article.getTitre());
        descriptionLabel.setText(article.getDescription());
        likesLabel.setText(Integer.toString(article.getLikes()));
        
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
    PreparedStatement stmt = conn.prepareStatement("SELECT * FROM commentaire WHERE id_art = ?");
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
    dialog.setHeaderText("Saisissez votre commentaire pour l'article \"" + article.getTitre() + "\" :");
    dialog.setContentText("Commentaire :");

    Optional<String> result = dialog.showAndWait();
    if (result.isPresent()) {
        String contenu = result.get();
        ajouterCommentaire(article.getId(), contenu,commentsScrollPane);
    }
}

private void ajouterCommentaire(int articleId, String contenu, ScrollPane commentsScrollPane) throws SQLException {

    
        String dburl = "jdbc:mysql://localhost:3306/Artec";
        String username = "root";
        String password = "";
        Connection conn = DriverManager.getConnection(dburl, username, password);
        
        PreparedStatement stmt = conn.prepareStatement("INSERT INTO commentaire (id_art, Contenu) VALUES (?, ?)");
        stmt.setInt(1, articleId);
        stmt.setString(2, contenu);
        stmt.executeUpdate();
         refreshComments(commentsScrollPane, articleId);
        conn.close();
   
    
}
private void likeArticle(Article article, boolean like) throws SQLException {
    // Mettre à jour le nombre de likes ou dislikes de l'article dans la base de données
    String dburl = "jdbc:mysql://localhost:3306/Artec";
    String username = "root";
    String password = "";
    Connection conn = DriverManager.getConnection(dburl, username, password);
    PreparedStatement stmt = conn.prepareStatement("UPDATE article SET likes = ?, dislikes = ? WHERE id = ?");
    stmt.setInt(1, like ? article.getLikes()  : article.getLikes());
    stmt.setInt(2, like ? article.getDislikes() : article.getDislikes() + 1);
    stmt.setInt(3, article.getId());
    stmt.executeUpdate();
    conn.close();
    
    // Rafraîchir les détails de l'article dans la vue
   // showArticleDetails(article);
}

private void refreshComments(ScrollPane commentsScrollPane, int articleId) throws SQLException {
    List<Commentaire> comments = getCommentsForArticle(articleId);
    initCommentsScrollPane(commentsScrollPane, comments);
}

    public void setArticle(Article article) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
       




    