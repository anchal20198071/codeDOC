/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package codedoc;

import java.awt.Color;
import java.awt.Point;
import java.awt.Window;
import static java.awt.event.KeyEvent.VK_SPACE;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.PrintWriter;
import static java.lang.String.valueOf;
import java.lang.System.Logger;
import java.lang.System.Logger.Level;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import static javax.swing.JOptionPane.INFORMATION_MESSAGE;
import static javax.swing.JOptionPane.YES_OPTION;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.Caret;
import javax.swing.text.DefaultHighlighter;
import javax.swing.text.Document;
import javax.swing.text.Highlighter;
import javax.swing.text.JTextComponent;

/**
 *
 * @author lenovo
 */
public class NewJPanel extends javax.swing.JPanel {
    public String ClientIDToShare;
    PrintWriter out ,outglobal;
    BufferedReader in ,inglobal;
    int k = 0;
    int l= 1;
    private Document model;
    private transient Caret caret;
    boolean sender= false;
    boolean codeArea= false;
    boolean chatArea= false;
    public Socket soc;
    public String code="";
    boolean startCollab= false;
    private DefaultListModel mod;
    boolean update= true;
    /**
     * Creates new form CodeDoc
     */
    String tabName;
    DefaultListModel mod1;
    static Boolean firstword=true;
    Socket socglobal;
    int exit=0;
    int sec=0;
    public static String path=null;
    private static final String FILE_LOCATION1 ="Desktop\\december\\codeDOC\\codeDOC\\arya.c";    
     private static final String FILE_LOCATION2 ="Desktop\\december\\codeDOC\\codeDOC\\arya.py";
     private static final String FILE_LOCATION3 ="Desktop\\december\\codeDOC\\codeDOC\\arya.cpp";
     
     private static final String FILE_LOCATION4 ="Desktop\\december\\codeDOC\\codeDOC\\arya.java";
     int flag1=1;
    /**
     * Creates new form NewJPanel
     */static int currpos=0;
     int prevpos=0,flag=0;
    MyTrie trieobj=new MyTrie();
    String x="";
    //constructor intialising socket ,in, out objects and running thread for accepting input for group chat and collaboratory
    public NewJPanel(String tabName) {
        this.tabName= tabName;
        trieobj.initialwords();
        
        try
        { 
            
            soc = new Socket("localhost", 9886);
            out=new PrintWriter(soc.getOutputStream(),true);
            in = new BufferedReader(new InputStreamReader(soc.getInputStream()));
            
            socglobal = new Socket("localhost", 9886);
            outglobal=new PrintWriter(socglobal.getOutputStream(),true);
            inglobal = new BufferedReader(new InputStreamReader(socglobal.getInputStream()));
            
        }
         catch (Exception ex)
        {System.out.println("Exception : " + ex);}
        initComponents();
        
        //for showing list
        collabMenu.add(collabPanel);
        mod1= new DefaultListModel();
        collabList.setModel(mod1);
        
        System.out.println(1);
        menu.add(panel);
        System.out.println(2);
        mod= new DefaultListModel();
        System.out.println(3);
        list.setModel(mod);
        System.out.println(4);
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        System.out.println(5);
        
        Thread serverResponse = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    while(true){
                        String combinedText = in.readLine(); // read by all in collab
                        try {
                            combinedText= EncryptDecrypt.decrypt(combinedText);
                        } catch (Exception ex) {
                            JOptionPane.showMessageDialog(null, "Exception in Decryption: "+ex);
                        }
                        
                        String text= combinedText.replaceAll("~", "\n");
                        System.out.println("Combined text: "+ combinedText);
                        
                        if(combinedText.equals("Removed")){
                            release.setEnabled(false);
                            press.setEnabled(true);
                            joinCollab.setEnabled(true);
                        }
                        else if(combinedText.equals("Released")){
                            String clientId= EncryptDecrypt.decrypt(in.readLine());
                            System.out.println("Client with id: "+clientId+" has released from collab");
                            mod1.removeElement(clientId);
                        }
                        else if(text.length() > 25 && text.substring(0, 25).equals("COLLAB VERIFICATION ALERT")){
                            System.out.println("Verfication Received");
                            text= text.substring(25);
                            if(text.equals("1")){
                                JOptionPane.showMessageDialog(null, "Collaboratory Joined!");
                                
                            }
                            else{
                                JOptionPane.showMessageDialog(null, "Admin Rejected Your request");
                                press.setEnabled(true);
                                joinCollab.setEnabled(true);
                                release.setEnabled(false);
                            }
                        }
                        else if(text.length() > 23 && text.substring(0, 23).equals("COLLAB PERMISSION ALERT")){
                            String client= EncryptDecrypt.decrypt(in.readLine());
                            System.out.println("client asking for permission: "+client);
                            System.out.println("Asking for permission");
                            
                            text= text.substring(23);
                            int res= JOptionPane.showConfirmDialog(null, text);
        
                            out.println("Request Verification");
                            if(res == YES_OPTION){                              
                                out.println("1");
                                System.out.println("yes pressed");
                                mod1.addElement(client);
                            }
                            else{                               
                                out.println("0");
                                System.out.println("No pressed");
                            }
                            out.println(code);
                            out.println(client);
                        }
                        
                        else if(text.length() > 10 && text.substring(0, 10).equals("*chatArea*")){
                            String code= text.substring(0, 10);
                            text= text.substring(10);
                            chatSection.append(text+"\n");
                            typeMessg.setText("");
                            
                        }
                        else if(k == 1 && startCollab == true )//startcollab is the admin
                        {
                            String name=in.readLine();
                            //receive tab key
                            String x=in.readLine();
                            String y=in.readLine();
                            //map with key string and value pair of xand y
                            
                            //put list with above x y and name;
                            System.out.println("name is"+name+"x is"+x+"y is"+y);
                            if(sender==false)
                            {
                                int i=compiletextbox.getCaretPosition();
                                 flag1=0;
                                System.out.println("updating textarea");
                            compiletextbox.setText(text);
                            if(i> text.length()-1)
                                compiletextbox.setCaretPosition(text.length());
                            else compiletextbox.setCaretPosition(i);
                            flag1=1;
                            
                            }
                            if(sender == true){
                                
//                                int len= compiletextbox.getText().length();
//                                compiletextbox.setCaretPosition(len);
                                sender= false;
                            }
                        }
                        else{//for collab creation to receive code when created
                            code= text;
                            startCollab = true;
                            new displayCollabCode(code).show();
                            //System.out.println("Code aa gya idhar, and Code : "+code);
                            //JOptionPane.showMessageDialog(null, "Your Collaboration Key is: "+code);
                        }
                            
                        }
                    }
                 catch (IOException ex) {
                    System.out.println("Exception thrown : +"+ex);
                  } catch (Exception ex) {
                    java.util.logging.Logger.getLogger(NewJPanel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
                }
                }
            });
            serverResponse.start();
    }
    
    //Highlighter Class
    class MyHighlightPainter extends DefaultHighlighter.DefaultHighlightPainter {
        public MyHighlightPainter(Color color) {
            super(color);
        }
    }

    Highlighter.HighlightPainter myHighlightPainter =  new MyHighlightPainter(Color.yellow);
    
    //Method for removing highlight
    public void removeHighlight(JTextComponent textComp) {
        Highlighter hilite = textComp.getHighlighter();
        Highlighter.Highlight[] hilites = hilite.getHighlights();
        for (int i=0;i<hilites.length;i++) {
            if (hilites[i].getPainter() instanceof MyHighlightPainter) {
                hilite.removeHighlight(hilites[i]);
            }
        }
    }
    
    //Method for highlighting the word
     public void highlight(JTextComponent textComp,String pattern) {
        removeHighlight(textComp);
        try {
            Highlighter hilite = textComp.getHighlighter();
            Document doc = textComp.getDocument();
            String text=doc.getText(0,doc.getLength());
            int pos=0;
            while ((pos=text.toUpperCase().indexOf(pattern.toUpperCase(),pos))>=0) {
                hilite.addHighlight(pos,pos+pattern.length(),myHighlightPainter);
                pos+=pattern.length();
            } 
        } catch (Exception e) {
            
        }
    }
     

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panel = new javax.swing.JPanel();
        jScrollPane8 = new javax.swing.JScrollPane();
        list = new javax.swing.JList<>();
        menu = new javax.swing.JPopupMenu();
        collabPanel = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        collabList = new javax.swing.JList<>();
        collabMenu = new javax.swing.JPopupMenu();
        jPanel3 = new javax.swing.JPanel();
        languageSelector = new javax.swing.JComboBox<>();
        autoComplete = new javax.swing.JCheckBox();
        press = new javax.swing.JButton();
        release = new javax.swing.JButton();
        darkMode = new javax.swing.JCheckBox();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane6 = new javax.swing.JScrollPane();
        compiletextbox = new javax.swing.JTextArea();
        jPanel1 = new javax.swing.JPanel();
        jTextField1 = new javax.swing.JTextField();
        newDoc = new javax.swing.JButton();
        editDoc = new javax.swing.JButton();
        saveDoc = new javax.swing.JButton();
        Open = new javax.swing.JButton();
        fontSettings = new javax.swing.JButton();
        collaborationList = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        getinput = new javax.swing.JTextArea();
        compileAndRun1 = new javax.swing.JButton();
        jScrollPane7 = new javax.swing.JScrollPane();
        getoutput = new javax.swing.JTextArea();
        timer = new javax.swing.JTextField();
        globalcompilebutton = new javax.swing.JButton();
        jScrollPane5 = new javax.swing.JScrollPane();
        typeMessg = new javax.swing.JTextArea();
        privateChat = new javax.swing.JButton();
        sendMessage = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        chatSection = new javax.swing.JTextArea();
        jScrollPane3 = new javax.swing.JScrollPane();
        lineTable = new javax.swing.JTable();
        searchTextField = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        joinCollab = new javax.swing.JButton();

        list.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                listMouseClicked(evt);
            }
        });
        jScrollPane8.setViewportView(list);

        javax.swing.GroupLayout panelLayout = new javax.swing.GroupLayout(panel);
        panel.setLayout(panelLayout);
        panelLayout.setHorizontalGroup(
            panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane8, javax.swing.GroupLayout.DEFAULT_SIZE, 88, Short.MAX_VALUE)
        );
        panelLayout.setVerticalGroup(
            panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane8, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
        );

        menu.setFocusable(false);

        collabList.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                collabListMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(collabList);

        javax.swing.GroupLayout collabPanelLayout = new javax.swing.GroupLayout(collabPanel);
        collabPanel.setLayout(collabPanelLayout);
        collabPanelLayout.setHorizontalGroup(
            collabPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 214, Short.MAX_VALUE)
        );
        collabPanelLayout.setVerticalGroup(
            collabPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 151, Short.MAX_VALUE)
        );

        setBackground(new java.awt.Color(171, 230, 242));

        jPanel3.setBackground(new java.awt.Color(0, 102, 102));

        languageSelector.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "C", "C++", "Java", "Python" }));

        autoComplete.setText("Autocomplete");

        press.setText("Start Collaboration");
        press.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pressActionPerformed(evt);
            }
        });

        release.setText("Release");
        release.setEnabled(false);
        release.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                releaseActionPerformed(evt);
            }
        });

        darkMode.setText("Dark Mode");
        darkMode.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                darkModeActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addComponent(languageSelector, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(autoComplete, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(329, 329, 329)
                .addComponent(press)
                .addGap(225, 225, 225)
                .addComponent(release, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(88, 88, 88)
                .addComponent(darkMode)
                .addContainerGap(251, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(languageSelector, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(autoComplete)
                    .addComponent(press)
                    .addComponent(release)
                    .addComponent(darkMode))
                .addContainerGap(14, Short.MAX_VALUE))
        );

        jPanel2.setBackground(new java.awt.Color(153, 153, 153));

        compiletextbox.setColumns(20);
        compiletextbox.setRows(5);
        compiletextbox.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                compiletextboxCaretUpdate(evt);
            }
        });
        compiletextbox.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                compiletextboxKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                compiletextboxKeyReleased(evt);
            }
        });
        jScrollPane6.setViewportView(compiletextbox);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 1055, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 387, Short.MAX_VALUE)
        );

        jPanel1.setBackground(new java.awt.Color(0, 51, 102));

        jTextField1.setBackground(new java.awt.Color(204, 255, 255));
        jTextField1.setFont(new java.awt.Font("Cambria", 1, 18)); // NOI18N
        jTextField1.setText(" CodeDOC");
        jTextField1.setBorder(null);
        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });

        newDoc.setText("New");
        newDoc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newDocActionPerformed(evt);
            }
        });

        editDoc.setText("Edit");
        editDoc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editDocActionPerformed(evt);
            }
        });

        saveDoc.setText("Save");
        saveDoc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveDocActionPerformed(evt);
            }
        });

        Open.setText("Open");
        Open.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                OpenActionPerformed(evt);
            }
        });

        fontSettings.setText("Font");
        fontSettings.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fontSettingsActionPerformed(evt);
            }
        });

        collaborationList.setText("See List of Collaborated Users");
        collaborationList.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                collaborationListActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(newDoc)
                .addGap(18, 18, 18)
                .addComponent(editDoc)
                .addGap(18, 18, 18)
                .addComponent(saveDoc)
                .addGap(18, 18, 18)
                .addComponent(Open)
                .addGap(18, 18, 18)
                .addComponent(fontSettings)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(collaborationList, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(245, 245, 245))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTextField1, javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(newDoc)
                    .addComponent(editDoc)
                    .addComponent(saveDoc)
                    .addComponent(Open)
                    .addComponent(fontSettings)
                    .addComponent(collaborationList))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel6.setBackground(new java.awt.Color(0, 102, 102));

        getinput.setColumns(20);
        getinput.setRows(5);
        jScrollPane2.setViewportView(getinput);

        compileAndRun1.setText("Compile & Run");
        compileAndRun1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                compileAndRun1ActionPerformed(evt);
            }
        });

        getoutput.setColumns(20);
        getoutput.setRows(5);
        jScrollPane7.setViewportView(getoutput);

        timer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                timerActionPerformed(evt);
            }
        });

        globalcompilebutton.setText("Global Compile & Run");
        globalcompilebutton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                globalcompilebuttonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 431, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(35, 35, 35)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(compileAndRun1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(globalcompilebutton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(72, 72, 72)
                        .addComponent(timer, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(95, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(compileAndRun1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(globalcompilebutton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(timer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(3, 3, 3))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        typeMessg.setColumns(20);
        typeMessg.setRows(5);
        jScrollPane5.setViewportView(typeMessg);

        privateChat.setText("Send Privately");
        privateChat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                privateChatActionPerformed(evt);
            }
        });

        sendMessage.setText("Send");
        sendMessage.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sendMessageActionPerformed(evt);
            }
        });

        chatSection.setEditable(false);
        chatSection.setColumns(20);
        chatSection.setRows(5);
        jScrollPane4.setViewportView(chatSection);

        lineTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null},
                {null},
                {null},
                {null}
            },
            new String [] {
                "."
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane3.setViewportView(lineTable);

        searchTextField.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                searchTextFieldCaretUpdate(evt);
            }
        });
        searchTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchTextFieldActionPerformed(evt);
            }
        });

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/search-icon.jpg"))); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        joinCollab.setText("Join Collab");
        joinCollab.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                joinCollabActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(searchTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(36, 36, 36)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 303, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 303, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(sendMessage)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(privateChat)))
                .addGap(48, 48, 48))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(joinCollab, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(579, 579, 579))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createSequentialGroup()
                    .addGap(58, 58, 58)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(57, Short.MAX_VALUE)
                .addComponent(joinCollab)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(searchTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 418, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(14, 14, 14)
                        .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(99, 99, 99))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 325, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(sendMessage)
                            .addComponent(privateChat))
                        .addGap(143, 143, 143))))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(33, 33, 33)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(215, Short.MAX_VALUE)))
        );
    }// </editor-fold>//GEN-END:initComponents
    
    //for caret update
    public Document getDocument() {
        return model;
    }
    
    //for initialising line numer to 1
       public void emptyLineTable()
    {
         DefaultTableModel tablerow = (DefaultTableModel) lineTable.getModel();
        int rowCount = tablerow.getRowCount();
        for (int i = rowCount - 1; i >= 0; i--) {
            tablerow.removeRow(i);
        }
    }
    //For line number feature
    public void displayLine(int n)
    {   
        emptyLineTable();
        int i;
        
        for (i = 0; i < n; i++) {
               
                String[] s = {valueOf(i+1)};                
                DefaultTableModel tblModel = (DefaultTableModel) lineTable.getModel();
                tblModel.addRow(s);
            }
    }
    
    
    
//Get files from open dialog box 
    String data1;
    static String data="";
   
    public void showContent(String path)
    {   
        data="";
        try
        {
            File myObj=new File(path);
            Scanner myReader = new Scanner(myObj);
            
            while(myReader.hasNextLine())
            {
                data+=myReader.nextLine()+"\n";
            }
            
            compiletextbox.setText(data);
        }
        catch (Exception e) {
            System.out.println(e);
        }
    }
 
//overridding the default method of caret update
    public void setCaretPosition(int position) {
        Document doc = getDocument();
        if (doc != null) {
            if (position > doc.getLength() || position < 0) {
                throw new IllegalArgumentException("bad position: " + position);
            }
            caret.setDot(position);
        }
    }
    public void moveCaretPosition(int pos) {
        Document doc = getDocument();
        if (doc != null) {
            if (pos > doc.getLength() || pos < 0) {
                throw new IllegalArgumentException("bad position: " + pos);
            }
            caret.moveDot(pos);
        }
    }
    
    // to start collaborating 
    private void pressActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pressActionPerformed

        out.println("Share Button Clicked");
        out.println(LoginWindow.userId+" - "+tabName);
        l= 0;
        k = 1;
        
        press.setEnabled(false);
        joinCollab.setEnabled(false);
        release.setEnabled(true);
    }//GEN-LAST:event_pressActionPerformed
//to end collaborating
    private void releaseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_releaseActionPerformed
        // TODO add your handling code here:
        k= 0;
        startCollab= false;
        System.out.println("Unshare key button pressed");
        out.println("Release");
        out.println(code);
        out.println(LoginWindow.userId+" - "+tabName);
        
        release.setEnabled(false);
        press.setEnabled(true);
        joinCollab.setEnabled(true);
    }//GEN-LAST:event_releaseActionPerformed

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1ActionPerformed

    private void newDocActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newDocActionPerformed
        compiletextbox.setText("");
    }//GEN-LAST:event_newDocActionPerformed

    private void editDocActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editDocActionPerformed
        compiletextbox.setEditable(true);
    }//GEN-LAST:event_editDocActionPerformed
// saving file in d drive and sending path to server side
    private void saveDocActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveDocActionPerformed

        String fileName=JOptionPane.showInputDialog("File name");
        try
        {
            out.println("Save_fileName");
            out.println(LoginWindow.userId);
            System.out.println("User Id: "+ LoginWindow.userId);
            //System.out.println("Arya Sending Data to the server side");
            out.println(fileName);
            System.out.println("File Name Sent");
            try {
                System.out.println("Entered Try block");
                String path="D:\\"+fileName; 
                FileWriter fileWriter=new FileWriter(path);
                fileWriter.write(compiletextbox.getText());
                fileWriter.close();

            } catch (Exception ex)
            {System.out.println("Exception : " + ex);}
            System.out.println(compiletextbox.getText());
        }
        catch(Exception et)
        {
            System.out.println("exception "+et);
        }

    }//GEN-LAST:event_saveDocActionPerformed
// opens dialogue box to see saved files
    private void OpenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_OpenActionPerformed

        OpenDialogBox db= new OpenDialogBox(this);
        db.show();
        compiletextbox.setEditable(false);

    }//GEN-LAST:event_OpenActionPerformed
// compiles and runs codes on basis of selected lang and assigned time
    private void compileAndRun1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_compileAndRun1ActionPerformed

        exit=0;
        String a = languageSelector.getSelectedItem().toString();
        //get the preffered language from user
        int gotsec=Integer.valueOf(timer.getText());

        Thread th7;
        th7 = new Thread(new Runnable() {
            @Override
            public void run() {

                sec=0;
                while(sec<gotsec && exit==0)
                {
                    try {
                        Thread.sleep(1000);
                        sec++;
                        timer.setText(Integer.toString(sec));
                    } catch (InterruptedException ex) {
                        System.out.println("Exception : "+ex);
                    }
                } 
                if(gotsec<=sec)
                {
                    getoutput.setText("TLE");
                    return;
                } 

            }
        }
        );
        th7.start();
        if(a.equalsIgnoreCase("C")){
            try {
                String filename = "arya.c";
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
                processBuilder.command("cmd.exe","/c", "gcc",FILE_LOCATION1);
            } else {
                processBuilder.command("sh", "-c", "ls");
            }
            processBuilder.directory(new File(System.getProperty("user.home")));

            try {
                process = processBuilder.start();
                /*BufferedReader br=new BufferedReader(new InputStreamReader(process.getInputStream()));//getting the output
                String coutput;
                while((coutput=br.readLine())!=null)
                {
                    System.out.println(coutput);
                }*/

            } catch (IOException ex) {
                System.out.println("file not found");
            }

            try {
                if( process.getErrorStream().read() != -1 )
                {
                    exit=1;
                    getoutput.setText("file cant be compiled " +process.getErrorStream());

                    return;
                }
            } catch (IOException ex) {
                System.out.println("file not found");
            }

            if (isWindows) {
                processBuilder.command("cmd.exe", "/c","a");
            } else {
                processBuilder.command("sh", "-c", "ls");
            }
            try {
                process = processBuilder.start();
                OutputStream os=process.getOutputStream();
                PrintStream ps = new PrintStream(os);//if we want to send some input
                ps.println(getinput.getText());
                ps.flush();
                BufferedReader br=new BufferedReader(new InputStreamReader(process.getInputStream()));//getting the output
                String coutput;
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
                System.out.println("file not found");
            }
            exit=1;

        }
        if(a.equalsIgnoreCase("C++")){
            try {
                String filename = "arya.cpp";
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
                processBuilder.command("cmd.exe", "/c", "g++",FILE_LOCATION3);
            } else {
                processBuilder.command("sh", "-c", "ls");
            }
            processBuilder.directory(new File(System.getProperty("user.home")));

            try {
                process = processBuilder.start();
                /*BufferedReader br=new BufferedReader(new InputStreamReader(process.getInputStream()));//getting the output
                String coutput;
                while((coutput=br.readLine())!=null)
                {
                    System.out.println(coutput);
                }*/

            } catch (IOException ex) {
                System.out.println("file not found");
            }

            try {
                if( process.getErrorStream().read() != -1 )
                {
                    exit=1;
                    getoutput.setText("file cant be compiled " +process.getErrorStream());
                    return;
                }
            } catch (IOException ex) {
                System.out.println("file not found");
            }

            if (isWindows) {
                processBuilder.command("cmd.exe", "/c", "a");
            } else {
                processBuilder.command("sh", "-c", "ls");
            }
            try {
                process = processBuilder.start();
                OutputStream os=process.getOutputStream();
                PrintStream ps = new PrintStream(os);//if we want to send some input
                ps.println(getinput.getText());
                ps.flush();
                BufferedReader br=new BufferedReader(new InputStreamReader(process.getInputStream()));//getting the output
                String coutput;
                while((coutput=br.readLine())!=null)
                {
                    getoutput.setText(coutput);
                    System.out.println(coutput);
                }
            } catch (IOException ex) {
                System.out.println("file not found");
            }

            exit=1;
        }
        if(a.equalsIgnoreCase("java")){
            try {
                String filename = "arya.java";
                FileWriter fileWriter=new FileWriter(filename);
                fileWriter.write(compiletextbox.getText());
//                setTitle(filename);
                fileWriter.close();
            } catch (IOException e) {
                exit=1;

                //getoutput.setText("file not found");
                return;
            }
            //created a file successfully in the folder where project is cloned
            boolean isWindows = System.getProperty("os.name")
            .toLowerCase().startsWith("windows");

            ProcessBuilder processBuilder = new ProcessBuilder();
            Process process=null;
            if (isWindows) {
                processBuilder.command("cmd.exe", "/c", "javac",FILE_LOCATION4);
            } else {
                processBuilder.command("sh", "-c", "ls");
            }
            processBuilder.directory(new File(System.getProperty("user.home")));

            try {
                process = processBuilder.start();
                /*BufferedReader br=new BufferedReader(new InputStreamReader(process.getInputStream()));//getting the output
                String coutput;
                while((coutput=br.readLine())!=null)
                {

                    System.out.println(coutput);
                }*/

            } catch (IOException ex) {
                System.out.println("file not found");
            }

            try {
                if( process.getErrorStream().read() != -1 )
                {
                    exit=1;
                    getoutput.setText("file cant be compiled " +process.getErrorStream());
                    return;
                }
            } catch (IOException ex) {
                System.out.println("file not found");
            }

            if (isWindows) {
                processBuilder.command("cmd.exe", "/c", "java",FILE_LOCATION4);
            } else {
                processBuilder.command("sh", "-c", "ls");
            }
            try {
                process = processBuilder.start();
                OutputStream os=process.getOutputStream();
                PrintStream ps = new PrintStream(os);//if we want to send some input
                ps.println(getinput.getText());
                ps.flush();
                System.out.println("here");

                BufferedReader br=new BufferedReader(new InputStreamReader(process.getInputStream()));//getting the output
                String coutput;
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
                   System.out.println("file not found");
            }

            exit=1;
        }
        if(a.equalsIgnoreCase("python")){
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

        }

    }//GEN-LAST:event_compileAndRun1ActionPerformed
//send a message to another user on the basis of email enetered
    private void privateChatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_privateChatActionPerformed
        out.println("Private Chat");
        String messg= typeMessg.getText();
        if(messg.equals("")){
            JOptionPane.showMessageDialog(null, "Please Type a Message");
        }
        else if(messg!= null){
            String mailId= JOptionPane.showInputDialog("Type email ID of person:");
            out.println(mailId);

            messg = messg.replaceAll("\n", "~");
            int length = chatSection.getText().length();
            chatSection.setCaretPosition(length);
            try {
                out.println(EncryptDecrypt.encrypt(LoginWindow.userId+" : "+messg));
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Exception in Encryption: "+ex);
            }
            typeMessg.setText("");
        }
    }//GEN-LAST:event_privateChatActionPerformed

    public void highLightingFeature(String lastWord,int startIndex,int endIndex)
    {
        compiletextbox.select(startIndex, startIndex);       
    }
 
    
    // send a message to a group in collaboration
    private void sendMessageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sendMessageActionPerformed

        String messg= typeMessg.getText();
        if(messg.equals("")){
            JOptionPane.showMessageDialog(null, "Please Type a Message");
        }
        else{
            messg = messg.replaceAll("\n", "~");
            int length = chatSection.getText().length();
            chatSection.setCaretPosition(length);

            out.println("Send Message");
            out.println(code);
            try {
                
                out.println(EncryptDecrypt.encrypt(LoginWindow.userId +" : "+messg));
               
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Exception in Encryption: "+ex);
            }

        }
    }//GEN-LAST:event_sendMessageActionPerformed

    private void searchTextFieldCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_searchTextFieldCaretUpdate

    }//GEN-LAST:event_searchTextFieldCaretUpdate

    private void searchTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_searchTextFieldActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        highlight(compiletextbox,searchTextField.getText());
    }//GEN-LAST:event_jButton1ActionPerformed

    private void timerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_timerActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_timerActionPerformed

    private void compiletextboxKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_compiletextboxKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_compiletextboxKeyPressed
// collaboration ,highlight and recommender 
    private void compiletextboxCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_compiletextboxCaretUpdate
        System.out.println("Inside compiletextboxCaretUpdate");
        // TODO add your handling code here:
        if(k == 1  && sender == false && update== true && flag1==1)//k=1 joined collab ,sender is false initailly becomes true after ,sync with autocomplete
        {
            System.out.println("sending to server");
            sender= true;
            out.println("Key Pressed");
            out.println(code);//collab code
            Point p= compiletextbox.getCaret().getMagicCaretPosition();
            int y= p.y, x= p.x;               //txt.getHeight();
            System.out.println("x: "+x+" y: "+y);
            out.println(LoginWindow.name);//take from +tab key also send
            out.println(Integer.toString(x));
            out.println(Integer.toString(y));
            String text = compiletextbox.getText();
            System.out.println("text sending to server : "+text);
            text = text.replaceAll("\n", "~");
            try {
                out.println(EncryptDecrypt.encrypt(text));//sent to server side
            } catch (Exception ex) {
                System.out.println("Exception in encrypting text Area content : "+ex);
            }

        }
        

        removeHighlight(compiletextbox);

        try
        {
         displayLine(compiletextbox.getLineCount()); //For displaying line count
        }
        catch(Exception e)
        {
            System.out.println("");
        }

    }//GEN-LAST:event_compiletextboxCaretUpdate

    private void joinCollabActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_joinCollabActionPerformed
        
        code = JOptionPane.showInputDialog("Please Enter Collaboration Key:");
        if(code != null){
            k= 1;
            l= 0;
            startCollab= true;
            out.println("Join Collaboration");
            out.println(code);
            out.println(LoginWindow.userId+" - "+tabName);

            joinCollab.setEnabled(false);
            press.setEnabled(false);
            release.setEnabled(true);
        }
    }//GEN-LAST:event_joinCollabActionPerformed

    private void listMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_listMouseClicked
        //System.out.println("bit clicked");
        String valSelected = list.getSelectedValue();
        //System.out.println("mouse clicked: "+valSelected);
        menu.setVisible(false);
        
        String data= compiletextbox.getText();
        data= data.replaceAll("\n", "~");
        int y= compiletextbox.getCaretPosition();
        String right= data.substring(y);
        
        y= y-1;
        while(y >= 0 && data.charAt(y) != ' ' && data.charAt(y) != '~' && Character.isLetter(data.charAt(y))){
            y--;
        }
        
        String left= "";
        if(y>= 0) left= data.substring(0, y+1);
        
        //System.out.println("Printing info");
        //System.out.println(left);
        //System.out.println(right);
        
        data= left+valSelected+right;
        //System.out.println("data: "+data);
        data= data.replaceAll("~", "\n");
        System.out.println("data is "+data);
        
        update= false;
        compiletextbox.setText(data);
        update= true;
        
            //System.out.println("moving caret position");
          compiletextbox.setCaretPosition(compiletextbox.getText().length());
//            System.out.println("moved caret position");
        
    }//GEN-LAST:event_listMouseClicked

    
    private void compiletextboxKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_compiletextboxKeyReleased
       
        //if(evt.getKeyCode() != VK_SPACE) {
        mod.removeAllElements();
        menu.setVisible(false);
        currpos=compiletextbox.getCaretPosition();
        System.out.println("it is "+currpos +" "+prevpos);

        
        currpos=compiletextbox.getCaretPosition();
        //System.out.println("it is"+currpos +prevpos);

        if(prevpos-currpos==1)
        {
            flag=1;
            prevpos=prevpos-1;
        }
        else if(prevpos-currpos>1)
        {
            MyTrie.currnode=trieobj;
        }
        else
        {
            prevpos=currpos;
            flag=0;
        }
        String s=compiletextbox.getText();
        int l=s.length();
        char ch='a';
        
        if(l!=0)
        ch=s.charAt(l-1);
        else
        return;

        if(((ch>='a' && ch<='z' )|| (ch>='A'&&ch<='Z')) &&flag==0 )
        {
            
            x=x+ch;
            Map <String , Integer> arr=new HashMap<>();

            arr=trieobj.searcher(ch ,trieobj);
            firstword=false;
            if(arr==null)
            return;
            
            //output.setText("");
//            mod.removeAllElements();
//            menu.setVisible(false);
            int cnt= 0;
            
            for (Map.Entry mapElement : arr.entrySet())
            {
                //output.setText(output.getText()+(String)mapElement.getKey() +" ");
                mod.addElement((String)mapElement.getKey());
                cnt++;
            }
            
            Point p= compiletextbox.getCaret().getMagicCaretPosition();
            int y= p.y, x= p.x;               //txt.getHeight();
            System.out.println("x: "+x+" y: "+y);

            menu.show(compiletextbox, x, y+19);
               
        }
        else if(flag==1 )//backspace is pushed
        {
           
            Map <String , Integer> arr = new HashMap <>();

            if(x.length()>0)
            x=x.substring(0, x.length() - 1);

            if(MyTrie.currnode!=null && MyTrie.currnode.parent!=null)
            {
                MyTrie.currnode=MyTrie.currnode.parent.parent;

                arr =trieobj.searcher(ch,trieobj);
                //output.setText("");
                
            }
            if(arr==null)
            return;
            
//            mod.removeAllElements();
//            menu.setVisible(false);
            
            int cnt = 0;
            for (Map.Entry mapElement : arr.entrySet())
            {

                //output.setText(output.getText()+(String)mapElement.getKey() +" ");
                mod.addElement((String)mapElement.getKey());
                cnt++;
            }
            
            Point p= compiletextbox.getCaret().getMagicCaretPosition();
           
            int y= p.y, x= p.x;               //txt.getHeight();
            System.out.println("x: "+x+" y: "+y);
            menu.show(compiletextbox, x, y+19);
              
        }
        else //if we took the word from table it shouldnt be inserted only currnode should be
        //made equal to trie obj,its is a newqord if it is after a digit enter or first word
        {
            if(!(x==""))
            {
                //System.out.println("called");
            trieobj.inserter(x);
            }
            x="";
            firstword=true;
            MyTrie.currnode=trieobj;
        }
        
        //}
        
    }//GEN-LAST:event_compiletextboxKeyReleased

    private void fontSettingsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fontSettingsActionPerformed
        FontSettings db= new FontSettings(this);
        db.show();
    }//GEN-LAST:event_fontSettingsActionPerformed


    private void globalcompilebuttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_globalcompilebuttonActionPerformed
        // TODO add your handling code here:
        
            
outglobal.println("global compile");

        exit=0;
        String a = languageSelector.getSelectedItem().toString();
        //get the preffered language from user
        outglobal.println(a);
        
        int gotsec=Integer.valueOf(timer.getText());

        /*Thread th8;
        th8= new Thread(new Runnable() {
            @Override
            public void run() {

                sec=0;
                while(sec<gotsec && exit==0)
                {
                    try {
                        Thread.sleep(1000);
                        sec++;
                        timer.setText(Integer.toString(sec));
                    } catch (InterruptedException ex) {
                        System.out.println("Exception : "+ex);
                    }
                } 
                if(gotsec<=sec)
                {
                    getoutput.setText("TLE");
                    return;
                } 

            }
        }
        );
        th8.start();*/
        
        
    try {
        String code=compiletextbox.getText();
        code=code.replaceAll("\n", "~");
        outglobal.println(code);
        String input=getinput.getText();
        input=input.replaceAll("\n", "~");
        outglobal.println(input);
        String output=inglobal.readLine();
        output.replaceAll("~","\n");
        getoutput.setText(output);
    } catch (IOException ex) {
        java.util.logging.Logger.getLogger(NewJPanel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
    }
        
        
    }//GEN-LAST:event_globalcompilebuttonActionPerformed

    private void collabListMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_collabListMouseClicked
        if(SwingUtilities.isRightMouseButton(evt)){
            String st= collabList.getSelectedValue();
            int it= collabList.getSelectedIndex();
            
            int val= JOptionPane.showConfirmDialog(null, "Remove user : "+st);
            if(val == YES_OPTION){
                mod1.removeElementAt(it);
                out.println("Unshare");
                out.println(code);
                out.println(st);
                System.out.println("Hurrah");
            }
            
        }
    }//GEN-LAST:event_collabListMouseClicked

    private void collaborationListActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_collaborationListActionPerformed
        if(collabMenu.isVisible()){
            collabMenu.setVisible(false);
        }
        else collabMenu.show(collaborationList, 0, collaborationList.getHeight());
    }//GEN-LAST:event_collaborationListActionPerformed

    private void darkModeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_darkModeActionPerformed
        if(darkMode.isSelected()){
            this.setBackground(new java.awt.Color(51,51,51));
            jPanel3.setBackground(new java.awt.Color(153,153,153));
            jPanel2.setBackground(new java.awt.Color(153,153,153));
            jPanel1.setBackground(new java.awt.Color(102,102,102));
            jPanel6.setBackground(new java.awt.Color(204,204,204));
            jTextField1.setBackground(new java.awt.Color(51,51,51));
            compiletextbox.setBackground(new java.awt.Color(102,102,102));
            getinput.setBackground(new java.awt.Color(102,102,102));
            getoutput.setBackground(new java.awt.Color(102,102,102));
            timer.setBackground(new java.awt.Color(102,102,102));
            typeMessg.setBackground(new java.awt.Color(153,153,153));
            chatSection.setBackground(new java.awt.Color(102,102,102));
            lineTable.setBackground(new java.awt.Color(204,204,204));
            
            //[255,255,255]
            compiletextbox.setForeground(new java.awt.Color(255,255,255));
            getinput.setForeground(new java.awt.Color(255,255,255));
            getoutput.setForeground(new java.awt.Color(255,255,255));
            timer.setForeground(new java.awt.Color(255,255,255));
            typeMessg.setForeground(new java.awt.Color(255,255,255));
            chatSection.setForeground(new java.awt.Color(255,255,255));
            lineTable.setForeground(new java.awt.Color(255,255,255));
            jTextField1.setForeground(new java.awt.Color(255,255,255));
        }
        else{
            this.setBackground(new java.awt.Color(171,230,242));
            jPanel3.setBackground(new java.awt.Color(0,102,102));
            jPanel2.setBackground(new java.awt.Color(153,153,153));
            jPanel1.setBackground(new java.awt.Color(0,51,102));
            jPanel6.setBackground(new java.awt.Color(0,102,102));
            jTextField1.setBackground(new java.awt.Color(204,255,255));
            compiletextbox.setBackground(new java.awt.Color(255,255,255));
            getinput.setBackground(new java.awt.Color(255,255,255));
            getoutput.setBackground(new java.awt.Color(255,255,255));
            timer.setBackground(new java.awt.Color(255,255,255));
            typeMessg.setBackground(new java.awt.Color(255,255,255));
            chatSection.setBackground(new java.awt.Color(255,255,255));
            lineTable.setBackground(new java.awt.Color(255,255,255));
            
            //[255,255,255]
            compiletextbox.setForeground(new java.awt.Color(0,0,0));
            getinput.setForeground(new java.awt.Color(0,0,0));
            getoutput.setForeground(new java.awt.Color(0,0,0));
            timer.setForeground(new java.awt.Color(0,0,0));
            typeMessg.setForeground(new java.awt.Color(0,0,0));
            chatSection.setForeground(new java.awt.Color(0,0,0));
            lineTable.setForeground(new java.awt.Color(0,0,0));
            jTextField1.setForeground(new java.awt.Color(0,0,0));
        }
    }//GEN-LAST:event_darkModeActionPerformed
//public static void main(String args[])
//{
//    JFrame jf;
//    jf=new JFrame();
//    jf.add(new NewJPanel("s"));
//    jf.setVisible(true);
//}

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Open;
    private javax.swing.JCheckBox autoComplete;
    private javax.swing.JTextArea chatSection;
    private javax.swing.JList<String> collabList;
    private javax.swing.JPopupMenu collabMenu;
    private javax.swing.JPanel collabPanel;
    private javax.swing.JButton collaborationList;
    private javax.swing.JButton compileAndRun1;
    public javax.swing.JTextArea compiletextbox;
    private javax.swing.JCheckBox darkMode;
    private javax.swing.JButton editDoc;
    private javax.swing.JButton fontSettings;
    private javax.swing.JTextArea getinput;
    private javax.swing.JTextArea getoutput;
    private javax.swing.JButton globalcompilebutton;
    private javax.swing.JButton jButton1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JButton joinCollab;
    private javax.swing.JComboBox<String> languageSelector;
    private javax.swing.JTable lineTable;
    private javax.swing.JList<String> list;
    private javax.swing.JPopupMenu menu;
    private javax.swing.JButton newDoc;
    private javax.swing.JPanel panel;
    private javax.swing.JButton press;
    private javax.swing.JButton privateChat;
    private javax.swing.JButton release;
    private javax.swing.JButton saveDoc;
    private javax.swing.JTextField searchTextField;
    private javax.swing.JButton sendMessage;
    private javax.swing.JTextField timer;
    private javax.swing.JTextArea typeMessg;
    // End of variables declaration//GEN-END:variables
}
