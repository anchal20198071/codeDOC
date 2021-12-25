/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servercodedoc;
//import con.mysql.cj.conf.ConnectionUrlParser.Pair;
import java.sql.Connection;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author Arya Pandey
 */
public class ServercodeDoc {
Mysqlconnect myc=new Mysqlconnect();
ServerSocket ss;
Socket soc;
PrintWriter out;
BufferedReader in;
//create string email gets at the time of login
public static ArrayList <HandleClient> clients = new ArrayList<>();
private ExecutorService pool= Executors.newFixedThreadPool(20);
Connection con =myc.getConn();
public static HashMap<String, HashSet<PrintWriter> > pair= new HashMap<>();
public static HashMap<String, OnlineUser > userStatus= new HashMap<>();


  public static void main(String[] args) 
 {
    new ServercodeDoc().executor();
 }
        
    void executor()
 {
    try 
    {
        ss = new ServerSocket(9886); 
        
        while(true)
        {
            soc= ss.accept(); 
            System.out.println("connection established");

            HandleClient clientThread= new HandleClient(soc,con);
         
            clients.add(clientThread);
            pool.execute(clientThread); 
        }  
    } catch (Exception ex)
    {
        Logger.getLogger(ServercodeDoc.class.getName()).log(Level.SEVERE, null, ex);
    }
}

}
