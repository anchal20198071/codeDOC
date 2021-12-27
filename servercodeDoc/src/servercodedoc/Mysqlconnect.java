/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servercodedoc;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

    public class Mysqlconnect {
    Connection con ; // stores object connect object returned from running DriverManager class's getConnection function
    public Connection getConn(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Mysqlconnect.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {

            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/codedoc","root","root");

        } catch (SQLException ex) {
            Logger.getLogger(Mysqlconnect.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println(" sending connection ");
         return con;
    }
    
}
