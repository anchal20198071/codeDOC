/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package codedoc;


import com.github.sarxos.webcam.Webcam;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;

/**
 *
 * @author Asmita Yadav
 */

public class clientReceiver extends javax.swing.JFrame {
        Socket s;
        public clientReceiver() throws IOException {
        s=new Socket("localhost",7600);
        new videoReceiver().start();
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        img_server = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jButton1.setText("End Call");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        img_server.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        jLabel1.setText("Call Receiver");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                .addGap(170, 170, 170)
                .addComponent(img_server, javax.swing.GroupLayout.PREFERRED_SIZE, 612, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 13, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton1)
                        .addGap(293, 293, 293))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 368, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(366, 366, 366))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(34, 34, 34)
                .addComponent(img_server, javax.swing.GroupLayout.PREFERRED_SIZE, 377, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 75, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addGap(65, 65, 65))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
    
      this.dispose();
        
    }//GEN-LAST:event_jButton1ActionPerformed

 
    public static void main(String args[]) throws IOException, ClassNotFoundException {
       
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new clientReceiver().setVisible(true);
                } catch (IOException ex) {
                    Logger.getLogger(clientReceiver.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        
        
     
    }
  
    
    class videoReceiver extends Thread
    { 
        ObjectInputStream in;
        ImageIcon ic;      
           
        @Override
        public void run()
        {   
           
            try {
                in =new ObjectInputStream(s.getInputStream());
            } catch (IOException ex) {
                Logger.getLogger(clientReceiver.class.getName()).log(Level.SEVERE, null, ex);
            }
           
           while(true)
            {
                try {
                    ic=(ImageIcon)in.readObject();
                } catch (IOException ex) {
                    Logger.getLogger(clientReceiver.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(clientReceiver.class.getName()).log(Level.SEVERE, null, ex);
                }
                img_server.setIcon(ic); 
            }
            }
            
        }
    
    
   

    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static javax.swing.JLabel img_server;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    // End of variables declaration//GEN-END:variables
}
