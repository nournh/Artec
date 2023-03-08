/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pi1.GUI;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

/**
 *
 * @author Asus
 */
public class artConController implements Initializable {
     @FXML
    private ImageView artc1;
      @FXML
    private ImageView artc2;
       @FXML
    private ImageView artc3;
        @FXML
    private ImageView artc4;
        @FXML
         private Button comid;
        @FXML
         private Button ahayat;
      
         @Override
    public void initialize(URL location, ResourceBundle resources) {
//       throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
       @FXML
     private void Commenter(ActionEvent event)throws IOException  {
        
         
        Parent fxml;
        fxml = FXMLLoader.load(getClass().getResource("commentaire.fxml"));
        Scene scene3 = new Scene(fxml);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(scene3);
        window.show();
    } 
    @FXML
      private void Back(ActionEvent event)throws IOException  {
        Parent fxml;
        fxml = FXMLLoader.load(getClass().getResource("Yesmina.fxml"));
        Scene scene3 = new Scene(fxml);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(scene3);
        window.show();
}
}
   
