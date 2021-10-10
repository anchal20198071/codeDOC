/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servercodedoc;
import java.sql.Connection;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
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
public static ArrayList <HandleClient> clients = new ArrayList<>();
private ExecutorService pool= Executors.newFixedThreadPool(20);
Connection con =myc.getConn();

        public static void main(String[] args) // made executor method because static variables cannot be reffered from main.
 {
           new ServercodeDoc().executor();
  }
        
    void executor()
 {
    try 
    {
        ss = new ServerSocket(9886); // ek baar connection ban gya untangled wale project
        
        while(true)
        {
            soc= ss.accept(); //jaise hi yaha accept hota hai handle client ki taraf jata h iski jagah in.realine hojae eveytime button is pused
            //soc hai , con hai in = new BufferedReader(new InputStreamReader(soc.getInputStream())); in.readline karke ek msg lelo;
System.out.println("connection established");

            HandleClient clientThread= new HandleClient(soc,con);
            clients.add(clientThread);
            pool.execute(clientThread); // yaha se humne thread class ka run method chalaya 
        }  
    } catch (Exception ex)
    {
        Logger.getLogger(ServercodeDoc.class.getName()).log(Level.SEVERE, null, ex);
    }
}


}