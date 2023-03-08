/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pieya.Services;
import java.util.List;
import javafx.collections.ObservableList;
/**
 *
 * @author eyach
 */
public interface Iservice {
    public interface IService<T> {
    public void add(T t );
    public List<T> affciher();
    public List<T> affciherbyid(T t);
    public Boolean modifier(T t);
    public Boolean supprimer(T t);
    
}
    
}
