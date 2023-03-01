
package Projett;
        
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import static java.util.Collections.list;
import java.util.List;
import java.util.logging.Level;
import javafx.collections.ObservableList;
import projet.Entities.Article;
import projet.Services.ServiceArticle;
import projet.utils.MyDB;
import projet.Entities.Commentaire;
import projet.Services.ServiceCommentaire;




public class  projet {
    /**
     * @param args the command line arguments
     * @param article
     * @throws java.sql.SQLException
     */
    public static void main(String[] args) throws SQLException{
       
//        A a = A.getInstance();
//        A a1 = A.getInstance();
//        System.out.println(a.hashCode());
//        System.out.println(a1.hashCode());

Article A= new Article("peinture","une fois","Art");
ServiceArticle sa = new ServiceArticle() {
   /* @Override
    public List<Article> affciher() {
  throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }*/

    @Override
    public Boolean modifier(Article i) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Boolean supprimer(Article i) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
};
    

  List<Article> articlee = sa.affciher();


    // itérez sur la liste de résultats et affichez les propriétés de chaque enregistrement
  for (Article arte : articlee) {
//     System.out.println(A.getId());
      System.out.println(A.getDescription());
        System.out.println(A.getTitre());
         System.out.println(A.getCategorie());
        
       
    }
        
    Commentaire C= new Commentaire("yoyo",49,31);

     ServiceCommentaire   sc = new ServiceCommentaire() {
            @Override
            public List<Commentaire> affciher() {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
            
            @Override
            public Boolean modifier(Commentaire i) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
            
            @Override
            public Boolean supprimer(Commentaire i) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        };
   //   List<Commentaire> comment = sc.getaLL();
ObservableList<Commentaire> comment =sc.getall();
    // itérez sur la liste de résultats et affichez les propriétés de chaque enregistrement
  for (Commentaire cmnt : comment) {
//     System.out.println(A.getId());
      System.out.println(C.getContenu());
  System.out.println(C.getDate_c());
       
    }
       
   // }
  try {
 //sa.add(A);
} catch (IllegalArgumentException ex) {
    System.out.println(ex.getMessage());
}
   // sa.updateArticle(A);
sc.add(C);

//sa.delete(30);




//sa.add(A);
      
//sc.updateCommentaire(C);

//sa.(A);
//sa.delete(315);

//System.out.println(sa.read());
        
   
        
       
       
   // }
}}

     
           

