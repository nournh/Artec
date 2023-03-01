/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projet.Services;

import java.util.List;
import static javafx.scene.input.KeyCode.I;

/**
 *
 * @author Lenovo
 */
public interface ArticleInterface<I> {
    
     public void add(I i );
   public List<I> affciher();
    public Boolean modifier(I i);
    public Boolean supprimer(I i);
    
}
