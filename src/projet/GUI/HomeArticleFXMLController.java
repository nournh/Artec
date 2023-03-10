package projet.GUI;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import projet.Entities.Article;
import projet.Services.ServiceArticle;

public class HomeArticleFXMLController implements Initializable {

  
    @FXML
    private TextArea description;
    @FXML
    private TextField titre;
    @FXML
    private ChoiceBox<String> categorie;
    @FXML
    private Button beck;
    

    @Override
    public void initialize(URL url, ResourceBundle rb) {
      categorie.getItems().addAll("Art", "peinture");
}
    

    @FXML
    private void addArticle(ActionEvent event)  {
        ServiceArticle articleService = new ServiceArticle();
        Article article = new Article((String)categorie.getValue(), titre.getText(), description.getText());
try {
 articleService.add(article);
} catch (IllegalArgumentException ex) {
    System.out.println(ex.getMessage());
  
   
        // Clear fields after adding article
        titre.clear();
        description.clear();
    }

}
  

    @FXML
    private void back(ActionEvent event) {
        try {
/*
         Parent root = FXMLLoader.load(getClass().getResource("/Gui/Voyage.fxml"));
              Scene scene = new Scene(root);
              Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
              stage.setScene(scene);
              stage.show();*/
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Article.fxml"));
            Parent root = loader.load();
            ArticleController pc = loader.getController();
            
            beck.getScene().setRoot(root);
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        }
    }
}


