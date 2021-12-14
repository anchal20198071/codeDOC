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
import java.util.ArrayList;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class HandleClient implements Runnable {
    private String email;
    private Socket client;
    private BufferedReader in;
    private PrintWriter out;
    PreparedStatement p;
    StringBuilder sb = new StringBuilder();
    Connection con;

    static String userEmail="";

    private int noOfUsers = 0;

    
    //constructor for clienthandle class
    public HandleClient(Socket clientSocket, Connection conclient) throws IOException {
        con = conclient;
        this.client = clientSocket;
        out = new PrintWriter(this.client.getOutputStream(), true);
        //out should get stored in an static array
        in = new BufferedReader(new InputStreamReader(this.client.getInputStream()));
        
        //ServercodeDoc.outArrayList.add(out);
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
           
            else if(choice.equals("Key Pressed")){
                System.out.println("Key has been pressed");
                editTextArea();
            }
            else if(choice.equals("Unshare")){
                System.out.println("Unshare Key Pressed");
                unshare();
            }
            else if(choice.equals("Share Button Clicked")){
                System.out.println("share Key Pressed");
                share();
            }
            else if(choice.equals("Send Message")){
                System.out.println("Send Message");
                try {
                    sendMessage();
                } catch (Exception ex) {
                    System.out.println("Server side exception in sending Message: "+ex);
                }
            }
            else if(choice.equals("logout")){
                System.out.println("User logout");
                logout();
            }
            else if(choice.equals("Private Chat")){
                System.out.println("Private Chat");
                try {
                    privateChat();
                } catch (Exception ex) {
                    System.out.println("Server side exception in sending Private Message: "+ex);
                }
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
            userEmail=s[0];    
            pst.setString(1, s[0]);
            pst.setString(2, md5(s[1]));

            rs = pst.executeQuery();

            if (rs.next()) {
                JOptionPane.showMessageDialog(null, "Username and Password are correct..");
                email= s[0];
                ServercodeDoc.userStatus.put(email, new OnlineUser(out, 1));
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
    
    void logout(){
        ServercodeDoc.userStatus.put(email, new OnlineUser(out, 0));
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
        System.out.println("Entered inside Save Action");
       try
       {    
           String useremail= in.readLine();
            System.out.println("useremail "+useremail);
            String st = in.readLine();
            System.out.println("FName: " +st);           
            String s2, s1="";
           
            PreparedStatement pst;
            
            String path="D:\\"+st;
                        
            System.out.println("Saving file"+useremail);
            String sql = "Insert into files values(?,?,?)";
            
            pst = con.prepareStatement(sql);
            pst.setString(1, useremail);
            pst.setString(2, st);
            pst.setString(3, path);
            
            int j = pst.executeUpdate();
            if (j >= 1) {
               System.out.println("Saved document");
            } else {
                System.out.println("Unable to save document");
            }
           
            
            //previousCode();
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

   

    void editTextArea() throws IOException{
        String combinedText = in.readLine();
        
        for (Map.Entry<PrintWriter, Integer > entry : ServercodeDoc.pair.entrySet()){
           
            if(entry.getValue() == 1){
                entry.getKey().println(combinedText);
            }
        }
            
    }
    
    void share(){
        
        ServercodeDoc.pair.put(out, 1);
    }
    
    void unshare(){
        ServercodeDoc.pair.put(out, 0);
    }
    
    void sendMessage() throws Exception{
        try {
            String mssg; 
            mssg= in.readLine();
            
            for (Map.Entry<PrintWriter, Integer > entry : ServercodeDoc.pair.entrySet()){
           
                if(entry.getValue() == 1){
                    String codedMessage = "*chatArea*"+EncryptDecrypt.decrypt(mssg);
                    codedMessage= EncryptDecrypt.encrypt(codedMessage);
                    entry.getKey().println(codedMessage);
                }
            }
        } catch (IOException ex) {
            System.out.println("Exception: "+ex);
        }
        
    }
    
    void privateChat() throws IOException, Exception{
        String arr[] = new String[2];
        for(int i=0; i< 2; i++){
            arr[i]= in.readLine();
        }
        
        if(!ServercodeDoc.userStatus.containsKey(arr[0])){
            JOptionPane.showMessageDialog(null, "User is currently OFFLINE!!");
        }
        else{
            for (Map.Entry<String, OnlineUser > entry : ServercodeDoc.userStatus.entrySet()){

                    if(entry.getKey().equals(arr[0]) && entry.getValue().k == 0){
                        JOptionPane.showMessageDialog(null, "User is currently OFFLINE!!");
                        break;
                    }
                    else if(entry.getKey().equals(arr[0]) && entry.getValue().k == 1){
                        entry.getValue().out.println(arr[1]);
                        out.println(arr[1]);
                        JOptionPane.showMessageDialog(null, "Message Sent!");
                    }
            }
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
