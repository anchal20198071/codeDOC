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

public class clientSender extends javax.swing.JFrame {
        Socket s;
        public clientSender() throws IOException {
        s=new Socket("localhost",7800);
        new videoSender().start();
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        img_client = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        img_client.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        jButton1.setText("jButton1");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel1.setText("Caller");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(img_client, javax.swing.GroupLayout.PREFERRED_SIZE, 689, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 424, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(54, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addGap(144, 144, 144))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(img_client, javax.swing.GroupLayout.PREFERRED_SIZE, 471, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
                    new clientSender().setVisible(true);
                } catch (IOException ex) {
                    Logger.getLogger(clientSender.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        
    }
    
    
    class videoSender extends Thread
    {   
        ObjectOutputStream out;
        ImageIcon ic;
        BufferedImage br;
        @Override
        public void run()
        {
               Webcam cam;
               cam=Webcam.getDefault();
               cam.setViewSize(new Dimension(640,480));
               cam.open();
   
            try {
                out = new ObjectOutputStream(s.getOutputStream());
            } catch (IOException ex) {
                Logger.getLogger(clientSender.class.getName()).log(Level.SEVERE, null, ex);
            }
     
        while(true)
        {
            br=cam.getImage();
            ic=new ImageIcon(br);
                   try {
                       out.writeObject(ic);
                   } catch (IOException ex) {
                       Logger.getLogger(clientSender.class.getName()).log(Level.SEVERE, null, ex);
                   }
                   try {
                       out.flush();
                   } catch (IOException ex) {
                       Logger.getLogger(clientSender.class.getName()).log(Level.SEVERE, null, ex);
                   }
            img_client.setIcon(ic);
          
        }
   
        }
    }
    
        
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static javax.swing.JLabel img_client;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    // End of variables declaration//GEN-END:variables
}
