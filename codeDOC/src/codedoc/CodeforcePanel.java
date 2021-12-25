
package codedoc;
import com. google. gson. *; 
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.Socket;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
public class CodeforcePanel extends javax.swing.JPanel {
BufferedReader br;
HttpURLConnection connection;
DefaultTableModel tblModel;
CodeforcesProblem cp;
Socket soc;
PrintWriter out;
BufferedReader in;
    public CodeforcePanel() {
    try {
        initComponents();
        soc = new Socket("localhost", 9886);
        out=new PrintWriter(soc.getOutputStream(),true);
        in = new BufferedReader(new InputStreamReader(soc.getInputStream()));
    } catch (IOException ex) {
        Logger.getLogger(CodeforcePanel.class.getName()).log(Level.SEVERE, null, ex);
    }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        problemid = new javax.swing.JTextField();
        jComboBox1 = new javax.swing.JComboBox<>();
        jButton1 = new javax.swing.JButton();
        jTextField1 = new javax.swing.JTextField();
        nameta = new javax.swing.JTextField();
        jScrollPane3 = new javax.swing.JScrollPane();
        resultta = new javax.swing.JTextArea();
        downloadbutton = new javax.swing.JButton();
        tagsta = new javax.swing.JTextField();
        jScrollPane4 = new javax.swing.JScrollPane();
        inputta = new javax.swing.JTextArea();
        jScrollPane2 = new javax.swing.JScrollPane();
        outputta = new javax.swing.JTextArea();

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Title 1", "Title 2", "Title 3"
            }
        ));
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        problemid.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                problemidActionPerformed(evt);
            }
        });

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "trees", "sorting", "binary seaarch", "greedy", "dfs and similar", "data structures", "divide and conquer", "graphs", "number theory", "shortest paths", "implementation", "dsu", "two pointers", "brute force", "dp", "math" }));

        jButton1.setText("SEARCH");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jTextField1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField1KeyReleased(evt);
            }
        });

        resultta.setColumns(20);
        resultta.setRows(5);
        jScrollPane3.setViewportView(resultta);

        downloadbutton.setText("DOWNLOAD");
        downloadbutton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                downloadbuttonActionPerformed(evt);
            }
        });

        tagsta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tagstaActionPerformed(evt);
            }
        });

        inputta.setColumns(20);
        inputta.setRows(5);
        jScrollPane4.setViewportView(inputta);

        outputta.setColumns(20);
        outputta.setRows(5);
        jScrollPane2.setViewportView(outputta);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 845, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(tagsta, javax.swing.GroupLayout.PREFERRED_SIZE, 408, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(4, 4, 4)
                                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(33, 33, 33)
                                .addComponent(downloadbutton, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 574, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton1)
                        .addGap(259, 259, 259)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(186, 186, 186)
                        .addComponent(problemid, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(nameta, javax.swing.GroupLayout.PREFERRED_SIZE, 334, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(139, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextField1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton1)
                        .addComponent(problemid, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(nameta, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 416, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(11, 11, 11)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(41, 41, 41)
                                .addComponent(downloadbutton)))
                        .addGap(8, 8, 8)
                        .addComponent(tagsta, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 551, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(55, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        // TODO add your handling code here:
        int row=jTable1.getSelectedRow();
        String tableClick=jTable1.getModel().getValueAt(row,0).toString() ,problemname=jTable1.getModel().getValueAt(row,1).toString() ,problemtag=jTable1.getModel().getValueAt(row,2).toString();
            problemid.setText(tableClick);
            nameta.setText(problemname);
            tagsta.setText(problemtag);
            String id= problemid.getText();
        String url="";
        try {
            cp= new CodeforcesProblem(id,url);
            String s1=cp.fetchProblemStatement();
            s1=s1.replaceAll("\\$", "");
            //s1=s1.replaceAll("\\", "");
            //s1 = s1.replaceAll("[^a-zA-Z0-9]+"," ");
            s1=s1.replaceAll("<span class=\"tex-font-style", "<");
            s1=s1.replaceAll("</span", "<");
            
            resultta.setText(s1);
            String s2=cp.fetchInput();
            s2=s2.replaceAll("<br>", "\n");
            s2=s2.replaceAll("\n\n","\n");
            
            inputta.setText(s2);
            String s=cp.fetchOutput();
            s=s.replaceAll("<br>", "\n");
            s=s.replaceAll("\n\n","\n");
            outputta.setText(s);
            nameta.setText(cp.fetchName());
        } catch (IOException ex) {
            //Logger.getLogger(UI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jTable1MouseClicked

    private void problemidActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_problemidActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_problemidActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        EmptyTable();
        String s=(String)jComboBox1.getSelectedItem();
        s=s.replace(" ","+");
        System.out.println(s);
        execute(s);

    }//GEN-LAST:event_jButton1ActionPerformed

    private void jTextField1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField1KeyReleased
        filter(jTextField1.getText().toLowerCase());
    }//GEN-LAST:event_jTextField1KeyReleased

    private void downloadbuttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_downloadbuttonActionPerformed
    try {
        // TODO add your handling code here:
        String ID = problemid.getText();
        String name = nameta.getText();
        String statement = resultta.getText();
        String tags = tagsta.getText();
        String input = inputta.getText();
        String output = outputta.getText();
        
        statement=statement.replaceAll("\n","~");
        input=input.replaceAll("\n","~");
        output=output.replaceAll("\n", "~");
        
        out.println("download");
        String[] S = {ID,name,statement,input,output,tags};
        for (int i = 0; i < 6; i++) {
            out.println(S[i]);
        }
        
        int serverResponse = Integer.parseInt(in.readLine());
        System.out.println(serverResponse);
        
        if (serverResponse == 1) {
            JOptionPane.showMessageDialog(null, "Product saved successfully!");
            
        } else {
            JOptionPane.showMessageDialog(null, "Product NOT saved successfully!");
        }
    } catch (IOException ex) {
        Logger.getLogger(CodeforcePanel.class.getName()).log(Level.SEVERE, null, ex);
    }

    }//GEN-LAST:event_downloadbuttonActionPerformed

    private void tagstaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tagstaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tagstaActionPerformed

   

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton downloadbutton;
    private javax.swing.JTextArea inputta;
    private javax.swing.JButton jButton1;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField nameta;
    private javax.swing.JTextArea outputta;
    private javax.swing.JTextField problemid;
    private javax.swing.JTextArea resultta;
    private javax.swing.JTextField tagsta;
    // End of variables declaration//GEN-END:variables


void execute(String s)
{
    try {
            String line;
            StringBuffer response=new StringBuffer();
            String urlformat="https://codeforces.com/api/problemset.problems?tags=:c";
            urlformat=urlformat.replace(":c",s);
            System.out.println(s+urlformat);
            URL url = new URL (urlformat);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(50000000);
            connection.setReadTimeout(50000000);
            int status=connection.getResponseCode();
            //System.out.println(status);
            if(status>299){
            br =new BufferedReader(new InputStreamReader(connection.getErrorStream()));
            while((line = br.readLine()) !=null)
            {
                response.append(line);
            }
            br.close();
            }
            else{
                br=new BufferedReader(new InputStreamReader(connection.getInputStream()));
                while((line = br.readLine()) !=null)
            {
                response.append(line);
            }
                br.close();
            }
            System.out.println(response.toString());
            
            Gson gson=new Gson();
           Jsonobj1 person = gson.fromJson(response.toString(),Jsonobj1.class);
           //String s[]=new String[10];
           
            for(int i=0 ; i< person.result.problems.size() ;i++)
            {
                String S[] = new String[3];
                S[0]= Integer.toString(person.result.problems.get(i).contestId)+"-" +person.result.problems.get(i).index;
                //S[1]=person.result.problems.get(i).index;
                S[1]= person.result.problems.get(i).name.toLowerCase();
                String k="";
                for(int j=0 ; j<person.result.problems.get(i).tags.size();j++)
                {
                k=k.concat(person.result.problems.get(i).tags.get(j));
                k=k.concat(" ,");
                }
                S[2]=k.toLowerCase();
                //System.out.println("it is"+ S[1]);
                System.out.println(S[0] +" " +S[1]+" "+S[2]);
                tblModel = (DefaultTableModel) jTable1.getModel();
                tblModel.addRow(S);
            }
                   
                   return;
        } catch (MalformedURLException ex) {
            System.out.println("Malformed exception");
        } catch (IOException ex) {
            System.out.println("IOexception");
        }
        finally
            
        {
            connection.disconnect();
        }
}
public void EmptyTable() {

        DefaultTableModel tablerow = (DefaultTableModel) jTable1.getModel();
        int rowCount = tablerow.getRowCount();

        for (int i = rowCount - 1; i >= 0; i--) {
            tablerow.removeRow(i);
        }

    }
void filter(String query)
{
TableRowSorter<DefaultTableModel> tr= new TableRowSorter<DefaultTableModel> (tblModel);
jTable1.setRowSorter(tr);

tr.setRowFilter(RowFilter.regexFilter(query));
}

}
