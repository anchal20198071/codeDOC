/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servercodedoc;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.security.MessageDigest;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashSet;
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
    private String receiverEmail;
    private static final String FILE_LOCATION1 ="Desktop\\december\\codeDOC\\codeDOC\\arya.c";    
    private static final String FILE_LOCATION2 ="Desktop\\december\\codeDOC\\codeDOC\\arya.py";
    private static final String FILE_LOCATION3 ="Desktop\\december\\codeDOC\\codeDOC\\arya.cpp";
    DatagramSocket serverSocket; 
    private static final String FILE_LOCATION4 ="Desktop\\december\\codeDOC\\codeDOC\\arya.java";
    static String userEmail="";
     
    private int noOfUsers = 0;

    
    //constructor for clienthandle class
    public HandleClient(Socket clientSocket, Connection conclient,DatagramSocket serverSocket) throws IOException {
        con = conclient;
        this.client = clientSocket;
        this.serverSocket=serverSocket;
        out = new PrintWriter(this.client.getOutputStream(), true);
        //out should get stored in an static array
        in = new BufferedReader(new InputStreamReader(this.client.getInputStream()));
        
        //ServercodeDoc.outArrayList.add(out);
    }

    @Override
    //taking request from clients 
    public void run() {
        try {

            String choice="";
            while(true)
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
            else if(choice.equals("global compile"))
            {
            globalcompile();
            }
            else if(choice.equals("download"))
            {
            download();
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
            else if(choice.equals("Release")){
                System.out.println("Release Key Pressed");
                release();
            }
            else if(choice.equals("Share Button Clicked")){
                System.out.println("share Key Pressed");
                try {
                    share();
                } catch (Exception ex) {
                    Logger.getLogger(HandleClient.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            else if(choice.equals("Join Collaboration")){
                System.out.println("Join Collaboration");
                joinCollaboration();
            }
            else if(choice.equals("Request Verification")){
                System.out.println("Request Verification");
                verifyRequest();
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
            else if(choice.equals("Audio_Call"))
            {
                System.out.println("Initiating Audio Call");
                audioCall();
            }
            else if(choice.equals("Intiate Audio Call"))
            {
                System.out.println("Initiating Audio Call Response");
                startAudioCall();
            }
           
            }

        } catch (IOException ex) {
            Logger.getLogger(HandleClient.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(HandleClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    void globalcompile()
    {
        int exit=0;
        try {
            String a=in.readLine();
            System.out.println(a);
            if(a.equalsIgnoreCase("C")){
                String code=in.readLine();
                code=code.replaceAll("~","\n");
                System.out.println(code);
                String getinput=in.readLine();
                getinput=getinput.replaceAll("~","\n");
                System.out.println(getinput);
                try {
                    String filename = "arya.c";
                    FileWriter fileWriter=new FileWriter(filename);
                   // String code=in.readLine();
                    fileWriter.write(code);
//                setTitle(filename);
                    fileWriter.close();
                } catch (IOException e) {
                    exit=1;
                    System.out.println("file not found");
                }
                System.out.println("reached here 3");
                //created a file successfully in the folder where project is cloned
                boolean isWindows = System.getProperty("os.name")
                        .toLowerCase().startsWith("windows");
                System.out.println("reached here 2");
                ProcessBuilder processBuilder = new ProcessBuilder();
                Process process=null;
                System.out.println("reached here 1");
                if (isWindows) {
                    processBuilder.command("cmd.exe","/c", "gcc",FILE_LOCATION1);
                    System.out.println("reached here");
                } else {
                    processBuilder.command("sh", "-c", "ls");
                }
                processBuilder.directory(new File(System.getProperty("user.home")));
                
                try {
                    process = processBuilder.start();
                 } catch (IOException ex) {
                    System.out.println("file not found");
                }
                
                try {
                    if( process.getErrorStream().read() != -1 )
                    {
                        exit=1;
                        System.out.println("file cant be compiled " +process.getErrorStream());
                        //getoutput.setText("file cant be compiled " +process.getErrorStream());
                         return;
                    }
                } catch (IOException ex) {
                    System.out.println("file not found");
                }
                System.out.println("compiled");
                if (isWindows) {
                    processBuilder.command("cmd.exe", "/c","a");
                } else {
                    processBuilder.command("sh", "-c", "ls");
                }
                try {
                    process = processBuilder.start();
                    OutputStream os=process.getOutputStream();
                    if(!getinput.equals("")){
                    PrintStream ps = new PrintStream(os);//if we want to send some input
                    
                    ps.println(getinput);
                    ps.flush();}
                    BufferedReader br=new BufferedReader(new InputStreamReader(process.getInputStream()));//getting the output
                    String coutput1,coutput="";
                    while((coutput1=br.readLine())!=null)
                    {
                        /*if(sec>=gotsec)
                        {
                            getoutput.setText("TLE");
                            return;
                        }*/
                        coutput=coutput+coutput1;
                    }
                    coutput.replaceAll("\n","~");
                        out.println(coutput);
                        System.out.println(coutput);
                } catch (IOException ex) {
                    System.out.println("file not found");
                }
                exit=1;
                
            }
            
            if(a.equalsIgnoreCase("C++")){
                
            String code=in.readLine();
                code=code.replaceAll("~","\n");
                System.out.println(code);
                String getinput=in.readLine();
                getinput=getinput.replaceAll("~","\n");
                System.out.println(getinput);
                            
                
                try {
                    String filename = "arya.cpp";
                    FileWriter fileWriter=new FileWriter(filename);
                   // String code=in.readLine();
                    fileWriter.write(code);
//                setTitle(filename);
                    fileWriter.close();
                } catch (IOException e) {
                    exit=1;
                    System.out.println("file not found");
                }
                System.out.println("reached here 3");
                //created a file successfully in the folder where project is cloned
                boolean isWindows = System.getProperty("os.name")
                        .toLowerCase().startsWith("windows");
                System.out.println("reached here 2");
                ProcessBuilder processBuilder = new ProcessBuilder();
                Process process=null;
                System.out.println("reached here 1");
                if (isWindows) {
                    processBuilder.command("cmd.exe","/c", "g++",FILE_LOCATION3);
                    System.out.println("reached here");
                } else {
                    processBuilder.command("sh", "-c", "ls");
                }
                processBuilder.directory(new File(System.getProperty("user.home")));
                
                try {
                    process = processBuilder.start();
                 } catch (IOException ex) {
                    System.out.println("file not found");
                }
                
                try {
                    if( process.getErrorStream().read() != -1 )
                    {
                        exit=1;
                        System.out.println("file cant be compiled " +process.getErrorStream());
                        //getoutput.setText("file cant be compiled " +process.getErrorStream());
                         return;
                    }
                } catch (IOException ex) {
                    System.out.println("file not found");
                }
                System.out.println("compiled");
                
                if (isWindows) {
                    processBuilder.command("cmd.exe", "/c","a");
                } else {
                    processBuilder.command("sh", "-c", "ls");
                }
                System.out.println("compiled2");
                
                try {
                    process = processBuilder.start();
                    
                    if(!getinput.equals("")){
                        OutputStream os=process.getOutputStream();
                    PrintStream ps = new PrintStream(os);//if we want to send some input
                    
                    ps.println(getinput);
                    ps.flush();}
                    System.out.println("compiled3");
                
                    BufferedReader br=new BufferedReader(new InputStreamReader(process.getInputStream()));//getting the output
                    System.out.println("compiled4");
                    String coutput1,coutput="";
                    while((coutput1=br.readLine())!=null)
                    {
                        System.out.println("compiled5");
                
                        /*if(sec>=gotsec)
                        {
                            getoutput.setText("TLE");
                            return;
                        }*/
                        coutput=coutput+coutput1;
                    }
                    coutput=coutput.replaceAll("\n","~");
                        out.println(coutput);
                        System.out.println(coutput);
                } catch (IOException ex) {
                    System.out.println("file not found");
                }
                exit=1;
                
            }
        
        
        if(a.equalsIgnoreCase("Java")){
                
            String code=in.readLine();
                code=code.replaceAll("~","\n");
                System.out.println(code);
                String getinput=in.readLine();
                getinput=getinput.replaceAll("~","\n");
                System.out.println(getinput);
                            
                
                try {
                    String filename = "arya.java";
                    FileWriter fileWriter=new FileWriter(filename);
                   // String code=in.readLine();
                    fileWriter.write(code);
//                setTitle(filename);
                    fileWriter.close();
                } catch (IOException e) {
                    exit=1;
                    System.out.println("file not found");
                }
                System.out.println("reached here 3");
                //created a file successfully in the folder where project is cloned
                boolean isWindows = System.getProperty("os.name")
                        .toLowerCase().startsWith("windows");
                System.out.println("reached here 2");
                ProcessBuilder processBuilder = new ProcessBuilder();
                Process process=null;
                System.out.println("reached here 1");
                if (isWindows) {
                    processBuilder.command("cmd.exe","/c", "javac",FILE_LOCATION4);
                    System.out.println("reached here");
                } else {
                    processBuilder.command("sh", "-c", "ls");
                }
                processBuilder.directory(new File(System.getProperty("user.home")));
                
                try {
                    process = processBuilder.start();
                 } catch (IOException ex) {
                    System.out.println("file not found");
                }
                
                try {
                    if( process.getErrorStream().read() != -1 )
                    {
                        exit=1;
                        System.out.println("file cant be compiled " +process.getErrorStream());
                        //getoutput.setText("file cant be compiled " +process.getErrorStream());
                         return;
                    }
                } catch (IOException ex) {
                    System.out.println("file not found");
                }
                System.out.println("compiled");
                
                if (isWindows) {
                    processBuilder.command("cmd.exe", "/c","java",FILE_LOCATION4);
                } else {
                    processBuilder.command("sh", "-c", "ls");
                }
                System.out.println("compiled2");
                
                try {
                    process = processBuilder.start();
                    
                    if(!getinput.equals("")){
                        OutputStream os=process.getOutputStream();
                    PrintStream ps = new PrintStream(os);//if we want to send some input
                    
                    ps.println(getinput);
                    ps.flush();}
                    System.out.println("compiled3");
                
                    BufferedReader br=new BufferedReader(new InputStreamReader(process.getInputStream()));//getting the output
                    System.out.println("compiled4");
                    String coutput1,coutput="";
                    while((coutput1=br.readLine())!=null)
                    {
                        System.out.println("compiled5");
                
                        /*if(sec>=gotsec)
                        {
                            getoutput.setText("TLE");
                            return;
                        }*/
                        coutput=coutput+coutput1;
                    }
                    coutput=coutput.replaceAll("\n","~");
                        out.println(coutput);
                        System.out.println(coutput);
                } catch (IOException ex) {
                    System.out.println("file not found");
                }
                exit=1;
                
            
        }
    
        
        if(a.equalsIgnoreCase("Python")){
                
            String code=in.readLine();
                code=code.replaceAll("~","\n");
                System.out.println(code);
                String getinput=in.readLine();
                getinput=getinput.replaceAll("~","\n");
                System.out.println(getinput);
                            
                
                try {
                    String filename = "arya.py";
                    FileWriter fileWriter=new FileWriter(filename);
                   // String code=in.readLine();
                    fileWriter.write(code);
//                setTitle(filename);
                    fileWriter.close();
                } catch (IOException e) {
                    exit=1;
                    System.out.println("file not found");
                }
                System.out.println("reached here 3");
                //created a file successfully in the folder where project is cloned
                boolean isWindows = System.getProperty("os.name")
                        .toLowerCase().startsWith("windows");
                System.out.println("reached here 2");
                ProcessBuilder processBuilder = new ProcessBuilder();
                Process process=null;
                System.out.println("reached here 1");
                
                if (isWindows) {
                    processBuilder.command("cmd.exe", "/c","py",FILE_LOCATION2);
                } else {
                    processBuilder.command("sh", "-c", "ls");
                }
                System.out.println("compiled2");
                
                try {
                    process = processBuilder.start();
                OutputStream os=process.getOutputStream();
                PrintStream ps = new PrintStream(os);//if we want to send some input

                ps.println(getinput);
                ps.flush();

                System.out.println("here");

                BufferedReader br=new BufferedReader(new InputStreamReader(process.getInputStream()));//getting the output
                    System.out.println("compiled4");
                    String coutput1,coutput="";
                    while((coutput1=br.readLine())!=null)
                    {
                        System.out.println("compiled5");
                
                        /*if(sec>=gotsec)
                        {
                            getoutput.setText("TLE");
                            return;
                        }*/
                        
                        coutput=coutput+coutput1;
                        
                    }
                coutput=coutput.replaceAll("\n","~");
                out.println(coutput);
                System.out.println(coutput);
                } catch (IOException ex) {
                    System.out.println("file not found");
                }
                exit=1;
        }   
            
        }
    
            catch (IOException ex) {
            Logger.getLogger(HandleClient.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
            /*if(a.equalsIgnoreCase("python")){
                try {
                    String filename = "arya.py";
                    FileWriter fileWriter=new FileWriter(filename);
                    fileWriter.write(compiletextbox.getText());
//                setTitle(filename);
fileWriter.close();
                } catch (IOException e) {
                    exit=1;
                    System.out.println("file not found");
                }
                //created a file successfully in the folder where project is cloned
                boolean isWindows = System.getProperty("os.name")
                        .toLowerCase().startsWith("windows");
                
                ProcessBuilder processBuilder = new ProcessBuilder();
                Process process=null;
                if (isWindows) {
                    processBuilder.command("cmd.exe", "/c", "py",FILE_LOCATION2);
                } else {
                    processBuilder.command("sh", "-c", "ls");
                }
                processBuilder.directory(new File(System.getProperty("user.home")));
                
                try {
                    process = processBuilder.start();
                    OutputStream os=process.getOutputStream();
                    PrintStream ps = new PrintStream(os);//if we want to send some input
                    
                    ps.println(getinput.getText());
                    ps.flush();
                    
                    System.out.println("here");
                    
                    BufferedReader br=new BufferedReader(new InputStreamReader(process.getInputStream()));//getting the output
                    String coutput;
                    if(sec>=gotsec)
                    {
                        getoutput.setText("TLE");

                        return;
                    }
                    while((coutput=br.readLine())!=null)
                    {
                        if(sec>=gotsec)
                        {
                            getoutput.setText("TLE");
                            
                            return;
                        }
                        getoutput.setText(coutput);
                        System.out.println(coutput);
                    }
                    
                } catch (IOException ex) {
//                Logger.getLogger(CodeDoc.class.getName()).log(Level.SEVERE, null, ex);
System.out.println("Exception : "+ex);
                }
                
                try {
                    if( process.getErrorStream().read() != -1 )
                    {
                        exit=1;
                        getoutput.setText("file cant be compiled " +process.getErrorStream());
                        return;
                    }
                } catch (IOException ex) {
                    System.out.println("Exception : "+ex);
                }
                //file is compile and runnable a file is created
                exit=1;
                
            }*/
        
    }
    void download(){
    String[] S = new String[6];
    
        try {
            for (int i = 0; i < 6; i++) {
            System.out.println(S[i] = in.readLine());}
             PreparedStatement pst;
            ResultSet rs;
            System.out.println("reached here");
            String sql = "INSERT INTO problemscf (ID,name,statement,input,output,tags) VALUES (?,?,?,?,?,?) ";
            pst = con.prepareStatement(sql);
            System.out.println("reached here");

            pst.setString(1, S[0]);
            pst.setString(2, S[1]);
            pst.setString(3, S[2]);
            pst.setString(4, S[3]);
            pst.setString(5, S[4]);
            pst.setString(6, S[5]);
            int j = pst.executeUpdate();

            if (j >= 1) {
                System.out.println("problem updated successfully");
                out.println("1");
            } else {
                System.out.println("problem update unsuccessful");
            }
        } catch (IOException ex) {
            Logger.getLogger(HandleClient.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
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
                s[i] = in.readLine();
                //System.out.println();
            }
            String getPortNo=in.readLine(); 
            PreparedStatement pst;
            ResultSet rs;
            String sql = "select * from registration where email=? and password=? ";

            pst = con.prepareStatement(sql);
            userEmail=s[0];    
            pst.setString(1, s[0]);
            pst.setString(2, md5(s[1]));

            rs = pst.executeQuery();

            if (rs.next()) {
//                JOptionPane.showMessageDialog(null, "Username and Password are correct..");
                email= s[0];
                ServercodeDoc.userStatus.put(email, new OnlineUser(out, 1,getPortNo));
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
        ServercodeDoc.userStatus.put(email, new OnlineUser(out, 0,"a"));
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

            String useremail=in.readLine();
            String sql = "SELECT fileName,filePath FROM files where email=?";
            p = con.prepareStatement(sql);
            p.setString(1, useremail);  
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
        String collabCode= in.readLine();
        
        String x,y;
        String name= in.readLine();
        //receive tab key
//        x=Integer.parseInt(in.readLine());
//        y=Integer.parseInt(in.readLine());
        x=in.readLine();
        y=in.readLine();
        String combinedText = in.readLine();
        for (ClientTabIdentification entry : ServercodeDoc.pair.get(collabCode)){
            System.out.println("Sending m");
            entry.out.println(combinedText);
            entry.out.println(name);
            //send tab key
            entry.out.println(x);
            entry.out.println(y);
            
        }
            
    }
    
    void share() throws IOException, Exception{
        String adminEmail= in.readLine();
        String newCode = adminEmail + getAlphaNumericString(5);
        System.out.println("Admin code generated: "+newCode);
        ServercodeDoc.pair.put(newCode, new HashSet<>());
        ServercodeDoc.pair.get(newCode).add(new ClientTabIdentification(out, adminEmail));
        
        ServercodeDoc.collabAdmin.put(newCode, out);
        
        //server sends this code to the user to collaborate with others
        out.println(EncryptDecrypt.encrypt(newCode));
        System.out.println("Sent the code to the admin client");
    }
    
    void unshare() throws IOException, Exception{
        String code= in.readLine();
        String id= in.readLine();
        
        for(ClientTabIdentification entry: ServercodeDoc.pair.get(code)){
            if(entry.clientId.equals(id)){
                entry.out.println(EncryptDecrypt.encrypt("Removed")); //iska case handle krna hai  client side
                ServercodeDoc.pair.get(code).remove(entry);
            }
        }
    }
    
    void joinCollaboration() throws IOException, Exception{
        String code= in.readLine();
        
        //PERMISSION ALERT
        String requestEmail = in.readLine();
        String message= "COLLAB PERMISSION ALERT"+"User "+requestEmail+" wants to Join Your Collaboratory! \nPlease Verify!!";
        message= EncryptDecrypt.encrypt(message);
        ServercodeDoc.requestList.put(out, code);
        ServercodeDoc.collabAdmin.get(code).println(message); //send request to the collab admin
        ServercodeDoc.collabAdmin.get(code).println(EncryptDecrypt.encrypt(requestEmail));
    }
    
    static String getAlphaNumericString(int n)
    {
  
        // chose a Character random from this String
        String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
                                    + "0123456789"
                                    + "abcdefghijklmnopqrstuvxyz";
  
        // create StringBuffer size of AlphaNumericString
        StringBuilder sb = new StringBuilder(n);
  
        for (int i = 0; i < n; i++) {
  
            // generate a random number between
            // 0 to AlphaNumericString variable length
            int index
                = (int)(AlphaNumericString.length()
                        * Math.random());
  
            // add Character one by one in end of sb
            sb.append(AlphaNumericString
                          .charAt(index));
        }
  
        return sb.toString();
    }
    
    void sendMessage() throws Exception{
        try {
            String collabCode= in.readLine();
            String mssg; 
            mssg= in.readLine();
            
            for (ClientTabIdentification entry : ServercodeDoc.pair.get(collabCode)){
           
                //if(entry.getValue() == 1){
                    String codedMessage = "*chatArea*"+EncryptDecrypt.decrypt(mssg);
                    codedMessage= EncryptDecrypt.encrypt(codedMessage);
                    entry.out.println(codedMessage);
                //}
            }
        } catch (IOException ex) {
            System.out.println("Exception: "+ex);
        }
        
    }
    
    //code quality improve by removing k
    void privateChat() throws IOException, Exception{
        String arr[] = new String[2];
        for(int i=0; i< 2; i++){ //arr[0]= receiver mail id
            arr[i]= in.readLine(); //apne jPanel in se receive krega
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
                        entry.getValue().out.println(arr[1]); //but yaha apne out se nhi send krega, it will use login ka out
                        //out.println(arr[1]);
                        JOptionPane.showMessageDialog(null, "Message Sent!");
                    }
            }
        }
        
    }
    
    void release() throws IOException, Exception{
        String code= in.readLine();
        String id= in.readLine();
        
        for(ClientTabIdentification entry: ServercodeDoc.pair.get(code)){
            if(entry.out.equals(out)){
                //send notification to admin
                ServercodeDoc.collabAdmin.get(code).println(EncryptDecrypt.encrypt("Released")); //Encrypt-decrypt
                ServercodeDoc.collabAdmin.get(code).println(EncryptDecrypt.encrypt(id));
                ServercodeDoc.pair.get(code).remove(entry);
            }
        }
        
        if(ServercodeDoc.pair.get(code).size() == 0){
            ServercodeDoc.pair.remove(code);
        }
    }
    
    void verifyRequest() throws Exception{
        String status= in.readLine();
        String code= in.readLine();
        String client= in.readLine();
        
        for (Map.Entry<PrintWriter,String> entry : ServercodeDoc.requestList.entrySet()){
            if(entry.getValue().equals(code)){
                if(status.equals("1")){
                    entry.getKey().println(EncryptDecrypt.encrypt("COLLAB VERIFICATION ALERT1"));
                    System.out.println("Join collab code received at server: "+code);
                    ServercodeDoc.pair.get(code).add(new ClientTabIdentification(entry.getKey(), client));
                }
                else{
                     entry.getKey().println(EncryptDecrypt.encrypt("COLLAB VERIFICATION ALERT0"));
                }
                
                ServercodeDoc.requestList.remove(entry.getKey());
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

     private void audioCall() throws IOException, Exception {
        receiverEmail = in.readLine();
        
        if(!ServercodeDoc.userStatus.containsKey(receiverEmail)){
            JOptionPane.showMessageDialog(null, "User is currently OFFLINE!!");
        }
        else
        {
            ServercodeDoc.userStatus.get(receiverEmail).out.println(EncryptDecrypt.encrypt("Audio_call"));
            ServercodeDoc.userStatus.get(receiverEmail).out.println
        (EncryptDecrypt.encrypt("User "+email+" ,wants to start audio call "));
            ServercodeDoc.userStatus.get(receiverEmail).out.println(email);                
        }
        
    }

    private void startAudioCall() throws Exception {
        String response=in.readLine();
        receiverEmail=in.readLine();
        if(response.equals("0"))
        {
            ServercodeDoc.userStatus.get(receiverEmail).out.println(EncryptDecrypt.encrypt("End Call"));
        }
        else
        {
            ServercodeDoc.userStatus.get(receiverEmail).out.println(EncryptDecrypt.encrypt("Start Call"));
            String port=ServercodeDoc.userStatus.get(receiverEmail).port;
            System.out.println("fun--"+port);
            new audioServer(port).start();            
        }
    }
    
    //Thread for audio calling
    class audioServer extends Thread{    
    InetAddress addr; 
    DatagramPacket receivePacket;
    String port;
    audioServer(String port)
    {
        this.port=port;
    }
    @Override
    public void run() {
        System.out.println("Audio Server started at port:"+50005);        
        //Getting port no. of receiver
        System.out.println(receiverEmail);        
        
        //Receiving data
         byte[] receiveData = new byte[4096];         
         receivePacket = new DatagramPacket(receiveData, receiveData.length);
         ByteArrayInputStream baiss = new ByteArrayInputStream(receivePacket.getData());
        
         //Sending data  
        try {
                      
            addr = InetAddress.getByName("127.0.0.1");
        } catch (UnknownHostException ex) {
            Logger.getLogger(HandleClient.class.getName()).log(Level.SEVERE, null, ex);
        }
             
        
        DatagramPacket sendPacket;
        byte[] data = new byte[4096];     
               
        
        while (true)
        {             
                try {
                //receive datagram packet
                serverSocket.receive(receivePacket);            
                data=receivePacket.getData();
                //send received datagram packet
                sendPacket = new DatagramPacket (data,data.length,addr,Integer.parseInt(port));//Integer.parseInt(port)
                serverSocket.send(sendPacket);
                } catch (IOException ex) {
                Logger.getLogger(HandleClient.class.getName()).log(Level.SEVERE, null, ex);
                }                      
       }
     }
    
    }
  
}
