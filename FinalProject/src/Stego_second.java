
import java.io.*;

public class Stego_second extends javax.swing.JFrame {
 String mfile;
String afile;
String key;
String message="";
String emessage="";
String coded;
String line;
byte[] buffer;
byte []messageArray;
byte []keyArray;
byte[] result;
    
    public Stego_second(String mf,String af,String k) {
        initComponents();
        mfile=mf;
        afile=af;
        key=k;
    }

    private Stego_second() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jButton1 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
        });

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        jButton1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton1.setText("Next");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setText("Processing...");

        jButton2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton2.setText("Cancel");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(67, 67, 67)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 78, Short.MAX_VALUE)
                            .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING)))
                .addContainerGap(98, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(69, 69, 69)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, 41, Short.MAX_VALUE)
                    .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(60, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
        
        {
                try {
                    FileReader fileReader = new FileReader(mfile); 
                BufferedReader bufferedReader = new BufferedReader(fileReader);
                jTextArea1.append("reading from the file");
                while((line =bufferedReader.readLine()) != null) {
                     message+=line;
                 }
            }
           
            catch(FileNotFoundException ex) {
            jTextArea1.append("\nUnable to open file '" +mfile + "'");
           
            }
         catch(IOException ex) {
           jTextArea1.append("\nError reading file '" + mfile + "'");					
            }
          }
        
      {jTextArea1.append("\nencrypting");
        if(message == null)
           jTextArea1.append("\nNo Message Found");
       char[] digit = message.toLowerCase().toCharArray();
       for(int i = 0; i < digit.length; i++) {
           if(digit[i] < 'a' || digit[i] > 'z')
             continue;
        digit[i] = Character.toUpperCase(digit[i]);
         digit[i] += 6;
       if(digit[i] > 'Z') {
               digit[i] -= 'Z';
               digit[i] += ('A' - 1);         
           }
        }
       emessage=new String(digit);
        }
        

{
jTextArea1.append("\nconvert msg to byte array");
try
		{
			ByteArrayOutputStream byteOut= new ByteArrayOutputStream();
			messageArray= emessage.getBytes();
			
}
catch(Exception e)
{jTextArea1.append("\n"+e);
}
}
{
        InputStream fis = null;
        try {
            jTextArea1.append("\nconvert wave file to byte array");
            File file = new File(afile);
            fis = new FileInputStream(file);
            buffer = new byte[(int)file.length()];
            fis.read(buffer, 0, buffer.length);
            fis.close();
        }
       
        catch (FileNotFoundException ex) {
           jTextArea1.append("\ncannot convert wave file to byte array FNFE");
        }   catch (IOException ex) {
               jTextArea1.append("\ncannot convert wave file to byte array IOE");
            } finally {
            try {
                fis.close();
            } catch (IOException ex) {
                jTextArea1.append("\ncannot convert wave file to byte array IOE");
            }
        }
}
{
jTextArea1.append("\nconvert key to byte array");
try
		{
			ByteArrayOutputStream byteOut= new ByteArrayOutputStream();
			keyArray= key.getBytes();
			
}
catch(Exception e)
{jTextArea1.append("\n"+e);
}
}
{               
 try
 { 
     
jTextArea1.append("\nimplementating lsb ajgorithm"); 
byte[] temp = new byte[buffer.length + keyArray.length];
System.arraycopy(buffer, 0, temp, 0, buffer.length);
System.arraycopy(keyArray, 0, temp, buffer.length, keyArray.length);
jTextArea1.append("\nimplementating lsb ajgorithm");
result = new byte[temp.length + messageArray.length];
System.arraycopy(temp, 0, result, 0, temp.length);
System.arraycopy(messageArray, 0, result, temp.length, messageArray.length);

 jTextArea1.append("\nimplementated");  
}
 catch(Exception e)
 {
     jTextArea1.append("\ncannot implement lsb ajgorithm");  
 }
 /* jTextArea1.append("\n"+buffer.length);
 jTextArea1.append("\n"+keyArray.length);
 jTextArea1.append("\n"+messageArray.length);
 jTextArea1.append("\n"+result.length);*/
}
{
    jTextArea1.append("\ncreating output file ");
      
try
                {
                   
               File file = new File("C:\\Users\\Ankit\\Documents\\output.wav");
               file.createNewFile();
                        FileOutputStream fos = new FileOutputStream(file);
                       
                       fos.write(result);
                        fos.close();
                       jTextArea1.append("\nOUTPUT FILE GENERATED");
                }
                catch(FileNotFoundException fnfe)
                {
                        jTextArea1.append("\nSpecified file not found" + fnfe);
                }
                catch(IOException ioe)
                {
                        jTextArea1.append("\nError while writing file" + ioe);
                }
               

}

 // TODO add your handling code here:
    }//GEN-LAST:event_formWindowActivated

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
this.setVisible(false);
Stego_output obj=new Stego_output();
obj.setVisible(true);         // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
      this.setVisible(false);
Stego_first obj2=new Stego_first();
obj2.setVisible(true);  // TODO add your handling code here:
    }//GEN-LAST:event_jButton2ActionPerformed

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
            java.util.logging.Logger.getLogger(Stego_second.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Stego_second.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Stego_second.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Stego_second.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Stego_second().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextArea1;
    // End of variables declaration//GEN-END:variables
}
