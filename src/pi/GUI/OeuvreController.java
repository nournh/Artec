/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pi.GUI;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import pi.Entities.Oeuvre;

/**
 * FXML Controller class
 *
 * @author octanet
 */
public class OeuvreController implements Initializable {

    @FXML
    private ImageView image;
    @FXML
    private Label titreLabel;
    @FXML
    private Label artisteLabel;

    /**
     * Initializes the controller class.
     */
    
    
    private Oeuvre o;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    public void setData(Oeuvre o){
        this.o = o;
        titreLabel.setText(o.getTitre_oeuvre());
        artisteLabel.setText(o.getArtiste());
        Image image = new Image(getClass().getResourceAsStream(o.getImage()));
        // image ims img id f oeuvre.fxml
        //image.setImage(image);
        
    }
}
