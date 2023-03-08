package pieya.Services;
import java.util.List;
/**
 *
 * @author Asus
 */
public interface Iservice1 {
    public interface IService<T> {
    public void add(T t );
    public List<T> affciher();
    public Boolean modifier(T t);
    public Boolean supprimer(T t);
}
    
}