/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pieya;
import pieya.Entities.Coupant;
import pieya.Entities.Reservation;
import pieya.Services.serviceCoupant;
import pieya.Services.serviceEmail;
import pieya.Services.serviceEvenement;
import pieya.Services.serviceReservation;

/**
 *
 * @author eyach
 */
public class pieya {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //        A a = A.getInstance();
//        A a1 = A.getInstance();
//        System.out.println(a.hashCode());
//        System.out.println(a1.hashCode());

//Coupant t =new Coupant (3,"hiba",15);
 //serviceCoupant st = new serviceCoupant() ;
 //st.add(t); 
  //System.out.println(st.affciher());
//Reservation r=new Reservation(16,"2024",1,1 );

   
 //serviceEvenement se = new serviceEvenement() ;
    
       //System.out.println(se.affciher());
       serviceEmail emailService = new serviceEmail();
        emailService.sendEmailF("eya.chabchoub@esprit.tn ");
    }
   
}


