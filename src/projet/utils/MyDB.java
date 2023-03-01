/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projet.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import projet.Entities.Article;
import projet.Services.ServiceArticle;

/**
 *
 * @author Lenovo
 */
public class MyDB {
final String URL ="jdbc:mysql://127.0.0.1:3306/artec";
final String USER ="root";
final String PWD ="";
   private  Connection cnx;
 private  static MyDB instance;

private MyDB(){
    try {
        cnx = DriverManager.getConnection(URL, USER, PWD);
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());   
    }
   
}
public static MyDB getInstance (){
    if(instance == null){
        instance = new MyDB();
    }
    return instance;
}
public Connection getCnx(){
    return cnx;
}
}

          