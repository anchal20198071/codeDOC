/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package codedoc;

import java.awt.Color;
import java.awt.Window;
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
import javax.swing.JFrame;
import javax.swing.JOptionPane;
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
    PrintWriter out;
    BufferedReader in;
    int k = 0;
    int l= 1;
    private Document model;
    private transient Caret caret;
    boolean sender= false;
    boolean codeArea= false;
    boolean chatArea= false;
    public Socket soc;
    /**
     * Creates new form CodeDoc
     */


    int exit=0;
    int sec=0;
    public static String path=null;
    private static final String FILE_LOCATION1 ="Desktop\\december\\codeDOC\\codeDOC\\arya.c";    
     private static final String FILE_LOCATION2 ="Desktop\\december\\codeDOC\\codeDOC\\arya.py";
     private static final String FILE_LOCATION3 ="Desktop\\december\\codeDOC\\codeDOC\\arya.cpp";
     
     private static final String FILE_LOCATION4 ="Desktop\\december\\codeDOC\\codeDOC\\arya.java";
     
    /**
     * Creates new form NewJPanel
     */int currpos=0 ,prevpos=0,flag=0;
    MyTrie trieobj=new MyTrie();
    String x="";
    
    public NewJPanel() {
        trieobj.initialwords();
      
        try
        { 
            
            soc = new Socket("localhost", 9886);
            out=new PrintWriter(soc.getOutputStream(),true);
            in = new BufferedReader(new InputStreamReader(soc.getInputStream()));
        }
         catch (Exception ex)
        {System.out.println("Exception : " + ex);}
        initComponents();
        
        Thread serverResponse = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    while(true){
                        String combinedText = in.readLine();
                        try {
                            combinedText= EncryptDecrypt.decrypt(combinedText);
                        } catch (Exception ex) {
                            JOptionPane.showMessageDialog(null, "Exception in Decryption: "+ex);
                        }
                        
                        String text= combinedText.replaceAll("~", "\n");

                        if(text.length() > 10 && text.substring(0, 10).equals("*chatArea*")){
                            String code= text.substring(0, 10);
                            text= text.substring(10);
                            chatSection.append(text+"\n");
                            typeMessg.setText("");
                            
                        }
                        else if(k == 1){
                            compiletextbox.setText(text);
                            if(sender == true){
                                int len= compiletextbox.getText().length();
                                compiletextbox.setCaretPosition(len);
                                sender= false;
                            }
                        }
                            
                        }
                    }
                 catch (IOException ex) {
                    System.out.println("Exception thrown : +"+ex);
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

        jPanel3 = new javax.swing.JPanel();
        languageSelector = new javax.swing.JComboBox<>();
        autoComplete = new javax.swing.JCheckBox();
        reset = new javax.swing.JButton();
        press = new javax.swing.JButton();
        release = new javax.swing.JButton();
        logout = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane6 = new javax.swing.JScrollPane();
        compiletextbox = new javax.swing.JTextArea();
        jPanel1 = new javax.swing.JPanel();
        jTextField1 = new javax.swing.JTextField();
        newDoc = new javax.swing.JButton();
        editDoc = new javax.swing.JButton();
        saveDoc = new javax.swing.JButton();
        shareButton = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        Open = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        getinput = new javax.swing.JTextArea();
        compileAndRun1 = new javax.swing.JButton();
        jScrollPane7 = new javax.swing.JScrollPane();
        getoutput = new javax.swing.JTextArea();
        timer = new javax.swing.JTextField();
        jScrollPane5 = new javax.swing.JScrollPane();
        typeMessg = new javax.swing.JTextArea();
        privateChat = new javax.swing.JButton();
        sendMessage = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        output = new javax.swing.JTextArea();
        jScrollPane4 = new javax.swing.JScrollPane();
        chatSection = new javax.swing.JTextArea();
        jScrollPane3 = new javax.swing.JScrollPane();
        lineTable = new javax.swing.JTable();
        searchTextField = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();

        setBackground(new java.awt.Color(102, 102, 0));

        jPanel3.setBackground(new java.awt.Color(212, 239, 242));

        languageSelector.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "C", "C++", "Java", "Python" }));

        autoComplete.setText("Autocomplete");

        reset.setText("Reset");
        reset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                resetActionPerformed(evt);
            }
        });

        press.setText("Press");
        press.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pressActionPerformed(evt);
            }
        });

        release.setText("Release");
        release.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                releaseActionPerformed(evt);
            }
        });

        logout.setText("Logout");
        logout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                logoutActionPerformed(evt);
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
                .addGap(133, 133, 133)
                .addComponent(reset)
                .addGap(404, 404, 404)
                .addComponent(press)
                .addGap(18, 18, 18)
                .addComponent(release)
                .addGap(18, 18, 18)
                .addComponent(logout)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(languageSelector, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(autoComplete)
                    .addComponent(reset)
                    .addComponent(press)
                    .addComponent(release)
                    .addComponent(logout))
                .addContainerGap())
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

        jPanel1.setBackground(new java.awt.Color(255, 255, 204));

        jTextField1.setBackground(new java.awt.Color(255, 255, 204));
        jTextField1.setFont(new java.awt.Font("Cambria", 1, 18)); // NOI18N
        jTextField1.setText("CodeDOC");
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

        shareButton.setText("Share");
        shareButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                shareButtonActionPerformed(evt);
            }
        });

        jButton2.setText("Chat");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton4.setText("Audio Call");

        jButton5.setText("Video Call");

        Open.setText("Open");
        Open.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                OpenActionPerformed(evt);
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 230, Short.MAX_VALUE)
                .addComponent(shareButton)
                .addGap(18, 18, 18)
                .addComponent(jButton2)
                .addGap(18, 18, 18)
                .addComponent(jButton4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton5)
                .addGap(525, 525, 525))
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
                    .addComponent(shareButton)
                    .addComponent(jButton2)
                    .addComponent(jButton4)
                    .addComponent(jButton5)
                    .addComponent(Open))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

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

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(117, 117, 117)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 431, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(38, 38, 38)
                .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addComponent(compileAndRun1))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(45, 45, 45)
                        .addComponent(timer, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(29, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(compileAndRun1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(timer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(34, 34, 34))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))))
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

        output.setEditable(false);
        output.setColumns(20);
        output.setRows(5);
        jScrollPane1.setViewportView(output);

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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(searchTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(36, 36, 36))
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 303, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 303, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 303, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(sendMessage)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(privateChat)))
                .addGap(48, 48, 48))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createSequentialGroup()
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE))
                .addGroup(layout.createSequentialGroup()
                    .addGap(58, 58, 58)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(94, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(searchTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 418, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(14, 14, 14)
                        .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(sendMessage)
                            .addComponent(privateChat))
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(99, 99, 99))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(37, 37, 37)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(213, Short.MAX_VALUE)))
        );
    }// </editor-fold>//GEN-END:initComponents
    
    public Document getDocument() {
        return model;
    }
    
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
    
    private void resetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_resetActionPerformed
        compiletextbox.setText("");
    }//GEN-LAST:event_resetActionPerformed

    private void pressActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pressActionPerformed

        out.println("Share Button Clicked");
        l= 0;
        k = 1;

    }//GEN-LAST:event_pressActionPerformed

    private void releaseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_releaseActionPerformed
        // TODO add your handling code here:
        k= 0;
        out.println("Unshare");
    }//GEN-LAST:event_releaseActionPerformed

    private void logoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_logoutActionPerformed
        // TODO add your handling code here:
        out.println("logout");
        this.setVisible(false);
    }//GEN-LAST:event_logoutActionPerformed

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1ActionPerformed

    private void newDocActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newDocActionPerformed
        compiletextbox.setText("");
    }//GEN-LAST:event_newDocActionPerformed

    private void editDocActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editDocActionPerformed
        compiletextbox.setEditable(true);
    }//GEN-LAST:event_editDocActionPerformed

    private void saveDocActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveDocActionPerformed

        String fileName=JOptionPane.showInputDialog("File name");
        try
        {
            out.println("Save_fileName");
            out.println(LoginWindow.userId);
            System.out.println("User Id: "+ LoginWindow.userId);
            System.out.println("Arya Sending Data to the server side");
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

    private void shareButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_shareButtonActionPerformed

    }//GEN-LAST:event_shareButtonActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton2ActionPerformed

    private void OpenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_OpenActionPerformed

        OpenDialogBox db= new OpenDialogBox(this);
        db.show();
        compiletextbox.setEditable(false);

    }//GEN-LAST:event_OpenActionPerformed

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
        });
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
                processBuilder.command("cmd.exe", "/c", "gcc",FILE_LOCATION1);
            } else {
                processBuilder.command("sh", "-c", "ls");
            }
            processBuilder.directory(new File(System.getProperty("user.home")));

            try {
                process = processBuilder.start();
                BufferedReader br=new BufferedReader(new InputStreamReader(process.getInputStream()));//getting the output
                String coutput;
                while((coutput=br.readLine())!=null)
                {
                    System.out.println(coutput);
                }

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
                BufferedReader br=new BufferedReader(new InputStreamReader(process.getInputStream()));//getting the output
                String coutput;
                while((coutput=br.readLine())!=null)
                {
                    System.out.println(coutput);
                }

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

                getoutput.setText("file not found");
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
                BufferedReader br=new BufferedReader(new InputStreamReader(process.getInputStream()));//getting the output
                String coutput;
                while((coutput=br.readLine())!=null)
                {

                    System.out.println(coutput);
                }

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
                processBuilder.command("cmd.exe", "/c", "java","-cp",FILE_LOCATION4);
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

    private void privateChatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_privateChatActionPerformed
        out.println("Private Chat");
        String messg= typeMessg.getText();
        if(messg.equals("")){
            JOptionPane.showMessageDialog(null, "Please Type a Message");
        }
        else{
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
        //if(m.get(lastWord)==1)
       // compiletextbox.setForeground(Color.red);
        compiletextbox.select(startIndex, startIndex);       
    }
    
//    public void setPrentComponent(){
//        Object o = getParent();
//        MainClass mc;
//        if(o != null && o instanceof MainClass)
//            mc = (MainClass)o;
//
//        mc.setTitle(lbl.getText());            
//    }
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
            try {
                
                out.println(EncryptDecrypt.encrypt(LoginWindow.userId +" : "+messg));
                //out.println(messg);
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

    private void compiletextboxCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_compiletextboxCaretUpdate

        // TODO add your handling code here:
        removeHighlight(compiletextbox);

        // prevLastWord=lastWord;
        // lastWord = compiletextbox.getText().substring(compiletextbox.getText().lastIndexOf(" ")+1);

        // int startIndex=compiletextbox.getText().lastIndexOf(" ");
        //  int endIndex=compiletextbox.getText().length();
        //  highLightingFeature(lastWord,startIndex,endIndex);
        displayLine(compiletextbox.getLineCount()); //For displaying line count

        // TODO add your handling code here:
        if(k == 1 && l== 0 && sender == false){
            sender= true;
            out.println("Key Pressed");
            String text = compiletextbox.getText();

            text = text.replaceAll("\n", "~");
            try {
                out.println(EncryptDecrypt.encrypt(text));
            } catch (Exception ex) {
                System.out.println("Exception in encrypting text Area content : "+ex);
            }

        }
        currpos=compiletextbox.getCaretPosition();
        System.out.println("it is"+currpos +prevpos);
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

            arr=trieobj.searcher(ch);
            if(arr==null)
            return;
            output.setText("");
            for (Map.Entry mapElement : arr.entrySet())
            {

                output.setText(output.getText()+(String)mapElement.getKey() +" ");
            }

        }
        else if(flag==1 )//backspace is pushed
        {
            System.out.println("here");
            Map <String , Integer> arr = new HashMap <>();

            if(x.length()>0)
            x=x.substring(0, x.length() - 1);

            if(MyTrie.currnode!=null && MyTrie.currnode.parent!=null)
            {
                MyTrie.currnode=MyTrie.currnode.parent.parent;

                arr =trieobj.searcher(ch);
                output.setText("");
            }
            if(arr==null)
            return;
            for (Map.Entry mapElement : arr.entrySet())
            {

                output.setText(output.getText()+(String)mapElement.getKey() +" ");
            }
        }
        else //if we took the word from table it shouldnt be inserted only currnode should be
        //made equal to trie obj
        {
            if(!(x==""))
            trieobj.inserter(x);

            x="";

            MyTrie.currnode=trieobj;
        }
    }//GEN-LAST:event_compiletextboxCaretUpdate


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Open;
    private javax.swing.JCheckBox autoComplete;
    private javax.swing.JTextArea chatSection;
    private javax.swing.JButton compileAndRun1;
    private javax.swing.JTextArea compiletextbox;
    private javax.swing.JButton editDoc;
    private javax.swing.JTextArea getinput;
    private javax.swing.JTextArea getoutput;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
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
    private javax.swing.JTextField jTextField1;
    private javax.swing.JComboBox<String> languageSelector;
    private javax.swing.JTable lineTable;
    private javax.swing.JButton logout;
    private javax.swing.JButton newDoc;
    private javax.swing.JTextArea output;
    private javax.swing.JButton press;
    private javax.swing.JButton privateChat;
    private javax.swing.JButton release;
    private javax.swing.JButton reset;
    private javax.swing.JButton saveDoc;
    private javax.swing.JTextField searchTextField;
    private javax.swing.JButton sendMessage;
    private javax.swing.JButton shareButton;
    private javax.swing.JTextField timer;
    private javax.swing.JTextArea typeMessg;
    // End of variables declaration//GEN-END:variables
}
