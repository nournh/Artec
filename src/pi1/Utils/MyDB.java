/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pi1.Utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Asus
 */
public class MyDB {
    
final String URL ="jdbc:mysql://127.0.0.1:3306/artec";
final String USER ="root";
final String PWD ="";
private  Connection cnx;
 private  static MyDB instance;

public MyDB(){
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

    public Connection getConnection() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}


    
