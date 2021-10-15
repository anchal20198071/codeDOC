/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servercodedoc;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.net.Socket;
import java.security.MessageDigest;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class HandleClient implements Runnable {

    private Socket client;
    private BufferedReader in;
    private PrintWriter out;
    PreparedStatement p;
    StringBuilder sb = new StringBuilder();
    Connection con;
    static String userEmail="";
    
    //constructor for clienthandle class
    public HandleClient(Socket clientSocket, Connection conclient) throws IOException {
        con = conclient;
        this.client = clientSocket;
        out = new PrintWriter(this.client.getOutputStream(), true);
        //out should get stored in an static array
        in = new BufferedReader(new InputStreamReader(this.client.getInputStream()));

    }

    @Override
    public void run() {
        try {

            String choice="";
            while(choice!="300")
            {
            
            choice = in.readLine(); // to know which button has created the client 
            sb.append(choice);// converting choice received from readline to comparable string 
            System.out.println(choice);
            
            if (choice.equals("15")) {
                System.out.println("Register Window");
                register();
            } else if (choice.equals("16")) {
                System.out.println("Login Window");
                login();
            }
            else if(choice.equals("200")){
                System.out.println("200");
            }
            else if(choice.equals("300")){
                System.out.println("300");
            }
            else if(choice.equals("PreviousCode"))
            {
                previousCode();
            }
            else if(choice.equals("FetchPreviousCode"))
            {
                fetchPreviousCode();
            }
            else if(choice.equals("Save_fileName"))
            {
                SaveAction();
            }
            else if(choice.equals("Open_files"))
            {
                OpenFiles();
            }
            else if(choice.equals("Fetch_files"))
            {
                FetchFiles();
            }
           
            
            }

        } catch (IOException ex) {
            Logger.getLogger(HandleClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
     void register() {
        String[] s = new String[6];
        int i;
        try {
            for (i = 0; i < 6; i++) {
                System.out.println(s[i] = in.readLine());
            }
            PreparedStatement pst;
            ResultSet rs;
            System.out.println("Executing Register method");
            String sql = "INSERT INTO registration (name,email,password,mobile,gender,dateofbirth) VALUES (?,?,?,?,?,?) ";
            pst = con.prepareStatement(sql);
            pst.setString(1, s[0]);
            pst.setString(2, s[1]);
            pst.setString(3, md5(s[2]));
            pst.setString(4, s[3]);
            pst.setString(5, s[4]);
            pst.setString(6, s[5]);

            int j = pst.executeUpdate();

            if (j >= 1) {
                System.out.println("client updated successfully");
                JOptionPane.showMessageDialog(null, "User Registered Succuessfully..");
                out.println("1");
            } else {
                System.out.println("client update unsuccessful");
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "This email id is taken by some other user...."
                    + " Please try to register with some other email Id..");
            out.println("2");
        }
    }

    void login() {
        String[] s = new String[2];
        int i;
        try {
            for (i = 0; i < 2; i++) {
                System.out.println(s[i] = in.readLine());
            }

            PreparedStatement pst;
            ResultSet rs;
            String sql = "select * from registration where email=? and password=? ";

            pst = con.prepareStatement(sql);
            System.out.println("Executing Query!!");
            userEmail=s[0];    
            pst.setString(1, s[0]);
            pst.setString(2, md5(s[1]));

            rs = pst.executeQuery();

            if (rs.next()) {
                JOptionPane.showMessageDialog(null, "Username and Password are correct..");
                out.println(1);
            } else {
                JOptionPane.showMessageDialog(null, "Username and Password are Incorrect..."
                        + "Please try Again");
                out.println(0);
            }

        } catch (Exception ex) {
            System.out.println("Exception : " + ex);
        }
    }
    
    void previousCode()
    {
        try
        {
            try {
                
            String st;
            System.out.println(st= in.readLine());           
            
            PreparedStatement pst;
            ResultSet rs;
            
            
            System.out.println("Saving previous code"+userEmail);
            String sql = "UPDATE registration SET previouscode = ? WHERE email= ?";
           
            pst = con.prepareStatement(sql);
            pst.setString(1, st);
            pst.setString(2, userEmail);
            int j = pst.executeUpdate();
            if (j >= 1) {
               System.out.println("Saved document");
            } else {
                System.out.println("Unable to save document");
            }

            } catch (Exception e) {
              ;
           }

            
        }
        catch (Exception ex) {
            System.out.println("Exception : " + ex);
        }
    }
    
    void fetchPreviousCode()
    {
        try
        {
            PreparedStatement pst;
            ResultSet rs;
            String sql = "SELECT previouscode FROM registration WHERE email=?";
            pst = con.prepareStatement(sql);
            pst.setString(1, userEmail);            
            rs = pst.executeQuery();
            if(rs.next())
            {
            String path = rs.getString("previouscode");
            out.println(path);
            }rs.close();            
        }
        catch (Exception ex)
        {System.out.println("Exception : " + ex);}
    }
    
    void SaveAction() {
       try
       {
            String st;
            System.out.println(st= in.readLine());           
            String s2, s1="";
           
            PreparedStatement pst;
            
            String path="D:\\"+st;
                        
            System.out.println("Saving file"+userEmail);
            String sql = "Insert into files values(?,?,?)";
            
            pst = con.prepareStatement(sql);
            pst.setString(1, userEmail);
            pst.setString(2, st);
            pst.setString(3, path);
            
            int j = pst.executeUpdate();
            if (j >= 1) {
               System.out.println("Saved document");
            } else {
                System.out.println("Unable to save document");
            }
           
            
            previousCode();
             /*try {
                   
                    FileWriter fileWriter=new FileWriter(path);
                    fileWriter.write(s1);
                    fileWriter.close();
                  
                    
                } catch (Exception ex)
                  {System.out.println("Exception : " + ex);}*/
       }
       catch (Exception ex)
       {System.out.println("Exception : " + ex);}
    }
    
    
    void OpenFiles() {
       try
        {
            PreparedStatement pst;
            ResultSet rs;
            String sql = "SELECT previouscode FROM registration WHERE email=?";
            pst = con.prepareStatement(sql);
            pst.setString(1, userEmail);            
            rs = pst.executeQuery();
            if(rs.next())
            {
            String path = rs.getString("previouscode");
            out.println(path);
            }rs.close();            
        }
        catch (Exception ex)
        {System.out.println("Exception : " + ex);}
    }
    
    private void FetchFiles() {
            
        System.out.println("Connection..established UpdateTable");
        ResultSet rs;
        try {

            String sql = "SELECT fileName,filePath FROM files where email=?";
            p = con.prepareStatement(sql);
            p.setString(1, userEmail);  
            rs = p.executeQuery();

            int i = 0;

            String[] fileName = new String[100];
            String[] filePath = new String[100];
            

            while (rs.next()) {

                fileName[i] = rs.getString("fileName");
                filePath[i] = rs.getString("filePath");
                i++;

            }

            rs.close();

            out.println(i);

            int k;

            for (k = 0; k < i; k++) {

                out.println(fileName[k]);
                out.println(filePath[k]);
            }

            p.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        
    }

   
    
    private String md5(String string) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] messageDigest = md.digest(string.getBytes());
            BigInteger number = new BigInteger(1, messageDigest);
            String hashText = number.toString(16);
            while (hashText.length() < 32) {
                hashText = "0" + hashText;
            }
            return hashText;
        } catch (Exception e) {
            System.out.println(e);
        }
        return "";
    }

   

  
   

   
}
