/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servercodedoc;
import java.io.BufferedReader;
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

    //constructor for clienthandle class
    public HandleClient(Socket clientSocket, Connection conclient) throws IOException {
        con = conclient;
        this.client = clientSocket;
        out = new PrintWriter(this.client.getOutputStream(), true);
        in = new BufferedReader(new InputStreamReader(this.client.getInputStream()));

    }

    @Override
    public void run() {
        try {

            String choice="";
            while(choice!="200")
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
            String sql = "INSERT INTO user_registration (name,email,password,mobile,gender,dateofbirth) VALUES (?,?,?,?,?,?) ";
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
            String sql = "select * from user_registration where email=? and password=? ";

            pst = con.prepareStatement(sql);
            System.out.println("Executing Query!!");

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
