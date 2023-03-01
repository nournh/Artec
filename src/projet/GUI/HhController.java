/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projet.GUI;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import projet.Entities.Article;
import projet.Services.ServiceArticle;

/**
 * FXML Controller class
 *
 * @author Lenovo
 */
public class HhController implements Initializable {

    private BorderPane articlesContainer;
    //@FXML
   // private BorderPane articleContainer;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       ServiceArticle a = new ServiceArticle();
   List<Article> articles = a.affciher();

   if (articles.isEmpty()) {
        System.out.println("No articles found.");
    }

    for (Article article : articles) {
        System.out.println("Article: " + article.getTitre());
        Label titleLabel = new Label("Titre: " + article.getTitre());
        titleLabel.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");
        Label categoryLabel = new Label("Catégorie: " + article.getCategorie());
        Label descriptionLabel = new Label("Description: " + article.getDescription());
        descriptionLabel.setStyle("-fx-font-size: 14px;");

        VBox card = new VBox(titleLabel, categoryLabel, descriptionLabel);
        card.getStyleClass().add("card");

       // articlesContainer.setTop(card);
    }
    }
}

   
    

