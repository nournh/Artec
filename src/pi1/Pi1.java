/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pi1;
import pi1.Entities.Commentaire;
import pi1.Entities.evenement;
import pi1.Entities.evenement.Categorie_gal;
import pi1.Services.serviceCommentaire;

import pi1.Services.serviceEvenement;


/**
 *
 * @author Asus
 */
public class Pi1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //        A a = A.getInstance();
//        A a1 = A.getInstance();
//        System.out.println(a.hashCode());
//        System.out.println(a1.hashCode());

//        String categ_galS = "PopArt";
//        Categorie_gal categ_gall = Categorie_gal.valueOf(categ_galS);
//        evenement e =new evenement ( 25,7,25,1,25,"yesmina l bnina","amoura w helwa","20/07/2000", categ_gall);
//        e.toString();
//   
//        serviceEvenement se = new serviceEvenement() ;
//        se.add(e);
//        
//        //System.out.println(se.supprimer(e));
//        //ajout2
        Commentaire c = new Commentaire (6,"yes","dumdum");
        c.toString();
        serviceCommentaire sc = new serviceCommentaire() ;
        //sc.add(c);
           System.out.println(sc.modifier(c));
        //System.out.println(sc.supprimer(c));
         
        
        

    }
    
}
