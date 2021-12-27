/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package codedoc;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import static javax.swing.JOptionPane.YES_OPTION;
import javax.swing.JPopupMenu;
import javax.swing.SwingUtilities;
import javax.swing.text.Caret;
import javax.swing.text.Document;

/**
 *
 * @author lenovo
 */

//NEW ONE
public class CodeDoc extends javax.swing.JFrame {
    int tabCount = 1;
    PrintWriter out;
    BufferedReader in;
    private Document model;
    private transient Caret caret;
    PrivateChatPanel pc;
    String tabName="";
    /**
     * Creates new form CodeDoc
     */
    public CodeDoc() {
        try {
            out=new PrintWriter(LoginWindow.soc.getOutputStream(),true);
            in = new BufferedReader(new InputStreamReader(LoginWindow.soc.getInputStream()));
        }  
        catch (IOException ex) {
            Logger.getLogger(CodeDoc.class.getName()).log(Level.SEVERE, null, ex);
        }
        initComponents();
        buttonSettings();
        button.setText("+");
        jtp.addTab("", null);
        jtp.setTabComponentAt(0, button);
        CodeforcePanel pc1=new CodeforcePanel();

        pc= new PrivateChatPanel();

        jtp.addTab("Chat", pc);
        jtp.addTab("codeforce",pc1);
        
        tabName = "Tab "+Integer.toString(tabCount);
        jtp.addTab(tabName, new NewJPanel(tabName));
        this.add(jtp);
        tabCount++;
        
        Thread privateChatThread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    while(true){
                        String combinedText = in.readLine();
                        System.out.println("Received");
                        try {
                            combinedText= EncryptDecrypt.decrypt(combinedText);
                        } catch (Exception ex) {
                            JOptionPane.showMessageDialog(null, "Exception in Decryption: "+ex);
                        }
                        
                         if(combinedText.equals("Audio_call"))
                        {
                            String msg=EncryptDecrypt.decrypt(in.readLine());
                            String receiverEmail=in.readLine();
                            int response=JOptionPane.showConfirmDialog(null, msg);
                            out.println("Intiate Audio Call");
                            if(response==YES_OPTION)
                            {
                                out.println("1");
                                out.println(receiverEmail);
                                new audio().setVisible(true);                                
                            }
                            else
                            {
                                out.println("0");
                                out.println(receiverEmail);
                            }
                        }
                        else if(combinedText.equals("End Call"))
                        {
                            JOptionPane.showMessageDialog(null, "Call Rejected");
                        }
                        else if(combinedText.equals("Start Call"))
                        {
                            JOptionPane.showMessageDialog(null, "Call Accepted");
                            new audio().setVisible(true);
                        } 
                        else
                         {
                          combinedText= combinedText+"\n";   
                          String text= combinedText.replaceAll("~", "\n");
                          int len= pc.ta().getText().length();
                          pc.ta().setCaretPosition(len);
                          pc.ta().append(text);
                         }
                        
                    }
                }
                catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Exception in Decryption: "+ex);
                }
            }
        });
        privateChatThread.start();
    }
                

    public void buttonSettings(){
        button.setBorder(null);
        button.setFocusPainted(false);
        button.setContentAreaFilled(false);
        button.setPreferredSize(new Dimension(30, 30));
        button.setToolTipText("New Tab");
    }
    
    public Document getDocument() {
        return model;
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
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        button = new javax.swing.JButton();
        jtp = new javax.swing.JTabbedPane();
        jButton1 = new javax.swing.JButton();
        audioCall = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        button.setText("+");
        button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonActionPerformed(evt);
            }
        });

        jtp.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jtpMouseClicked(evt);
            }
        });

        jButton1.setText("Logout");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        audioCall.setText("Audio Call");
        audioCall.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                audioCallActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jtp, javax.swing.GroupLayout.DEFAULT_SIZE, 1471, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(button)
                .addGap(224, 224, 224)
                .addComponent(audioCall, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40)
                .addComponent(jButton1)
                .addGap(80, 80, 80))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(button)
                    .addComponent(jButton1)
                    .addComponent(audioCall))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jtp, javax.swing.GroupLayout.DEFAULT_SIZE, 803, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonActionPerformed
        tabName = "Tab "+Integer.toString(tabCount);
        jtp.addTab(tabName, new NewJPanel(tabName));
        this.add(jtp);
        tabCount++;
    }//GEN-LAST:event_buttonActionPerformed

    private void jtpMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtpMouseClicked
        if(SwingUtilities.isRightMouseButton(evt)){
            int index= jtp.getSelectedIndex();
            if(index != 0){
                JPopupMenu popupMenu= new JPopupMenu();
                JMenuItem delete= new JMenuItem("Delete Tab");
                delete.addActionListener(new ActionListener(){
                    @Override
                    public void actionPerformed(ActionEvent evt) {
                        jtp.remove(index);
                    }

                });
                popupMenu.add(delete);
                popupMenu.show(this, evt.getX(), evt.getY()+40);
            }
        }
    }//GEN-LAST:event_jtpMouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        out.println("logout");
        this.setVisible(false);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void audioCallActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_audioCallActionPerformed
        out.println("Audio_Call");
        String receiverEmail= JOptionPane.showInputDialog("Type email ID of the call receiver:");        
        out.println(receiverEmail);
    }//GEN-LAST:event_audioCallActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(CodeDoc.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CodeDoc.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CodeDoc.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CodeDoc.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CodeDoc().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton audioCall;
    private javax.swing.JButton button;
    private javax.swing.JButton jButton1;
    private javax.swing.JTabbedPane jtp;
    // End of variables declaration//GEN-END:variables
}
