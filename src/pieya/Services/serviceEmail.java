/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pieya.Services;

/**
 *
 * @author eyach
 */
import javax.mail.*;
import javax.mail.internet.*;
import java.util.*;

import java.awt.SystemColor;
import static java.awt.SystemColor.text;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;




public class serviceEmail {
    public static void sendEmailF(String recipient){
        final String username = "arteca077@gmail.com";
        final String password = "inaqaeahaqilfvjc";
        String host = "smtp.gmail.com";
        
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", "587");
        
        
      Session session = Session.getInstance(props, new javax.mail.Authenticator() {
        protected PasswordAuthentication getPasswordAuthentication() {
            return new PasswordAuthentication(username, password);
         }
      });
      try {
         Message message = new MimeMessage(session);
         message.setFrom(new InternetAddress(username));
         message.setRecipients(
            Message.RecipientType.TO,
            InternetAddress.parse(recipient)
         );  
      
            message.setSubject("ARTEC paiement est En Attente");
            message.setText("Cher Artiste,\n\n"
        + "Votre reservation est en Attente.\n\n"
        + "Veuillez procéder au paiement de votre réservation le plus tot possible.\n\n"
        + "Cordialement,\nARTEC Team");


       
        Transport.send(message);

         System.out.println("Email sent to " + recipient + " successfully!");

      } catch (MessagingException e) {
         throw new RuntimeException(e);
      }
        
    }

    
    
}
    


