/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pi1;

//import java.awt.Image;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 *
 * @author asus
 */
public class NewFXMain extends Application {
    @Override
    public void start(Stage primaryStage){
        try{
            Parent root=FXMLLoader.load(getClass().getResource("./GUI/Taw.fxml"));
            Scene scene = new Scene(root);
            Image icon = new Image(getClass().getResourceAsStream("/Image/icon.png"));
            primaryStage.getIcons().add(icon);
            primaryStage.setTitle("Bienvenue à mon galerie");
            primaryStage.setScene(scene);
            primaryStage.show();
       }catch (IOException ex) {
           System.out.println(ex.getMessage());
       }
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
