
package ultimate_idm;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.text.DecimalFormat;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import org.apache.commons.io.FileUtils;

public class Downloading_file extends javax.swing.JFrame {
    
    static double file_size = 0;
    
    public Downloading_file() {
        initComponents();
        System.setProperty("http.agent", "Chrome");
        jTextField1.requestFocus();
    }

    
    static class download extends Thread{
        @Override
        public void run()
        {
            try{
                URL url = new URL(jTextField1.getText().trim());
                URLConnection con = url.openConnection();
                InputStream is = con.getInputStream();
                int size = con.getContentLength();
                jProgressBar1.setMaximum(size);
                jProgressBar1.setValue(0);
                
                int buffer_size = 512;
                
                byte[] buffer = new byte[buffer_size];
                String[] str = jTextField1.getText().trim().split("/");
                BufferedOutputStream bos = new BufferedOutputStream(
                        new FileOutputStream("D:\\prabeer\\Data\\java\\Coding\\Java FTP\\save\\"+str[str.length-1]));
                int len = 0;
                
                while((len = is.read(buffer, 0, buffer_size))>-1)
                {
                    bos.write(buffer, 0, len);
                    jProgressBar1.setValue(jProgressBar1.getValue()+len);
                }
                
                bos.flush();
                bos.close();
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
        }
    }
    private static void saveFile(String file_name, String file_url)
    
    {
        Thread t2 = new Thread()
                {
                    public void run()
                {
                    
                    BufferedInputStream bis = null;
                    FileOutputStream fos = null;
                    
                    try{
                        
                        bis = new BufferedInputStream(new URL(file_url).openStream());
                        fos = new FileOutputStream(file_name);
                        byte data[] = new byte[4096];
                        int count;
                        
                        String downloading_text = "Downloading Files.....................";
                        System.out.println(downloading_text);
                        jLabel13.setText(downloading_text);
                        jLabel13.revalidate();
                        jLabel13.repaint();
                        jTextField1.setEnabled(false);
                        jButton2.setText("Stop");
                        
                        
                        while((count = bis.read(data,0,4096))!=-1)
                        {
                            fos.write(data, 0, count);
                        }
                        
                        String download_finish = "Downloaded Finished................";
                        System.out.println(download_finish);
                        jLabel13.setText(download_finish);
                        jLabel13.revalidate();
                        jLabel13.repaint();
                        
                        if((count = bis.read(data,0,4096))==-1)
                        {
                            jButton2.setText("Start");
                            jTextField1.setEnabled(true);
                            JFrame jframe = new JFrame();
                            jframe.dispose();
                        }
                    }
                    catch(Exception e)
                {
                    e.printStackTrace();
                }
                   
                    finally
                {
                    
                    if(bis!=null)
                    {
                        try{
                            bis.close();
                        }
                        catch(Exception e)
                        {
                            e.printStackTrace();
                        }
                    }
                    
                    if(fos!=null)
                    {
                        try{
                            fos.close();
                        }
                        catch(Exception e)
                        {
                            e.printStackTrace();
                        }
                    }
                    
                }
                    
                }
                };
        
        Thread t1 = new Thread()
        {
           public void run()
            {
            try{
               URL url = new URL(file_url);
                HttpURLConnection httpconn = (HttpURLConnection) url.openConnection();
                httpconn.setRequestMethod("HEAD");
                long remove_file = httpconn.getContentLength();
                
                BufferedInputStream bis = null;
                FileOutputStream fos = null;
                
                bis = new BufferedInputStream(new URL(file_url).openStream() );
                byte data[] = new byte[4096];
                long count = 0;
                
                DecimalFormat df = new DecimalFormat("###.##");
                
                if(remove_file<=1024)
                {
                    System.out.println("File Size : "+remove_file+" bytes");
                    jLabel6.setText(remove_file+" bytes");
                    jLabel6.revalidate();
                    jLabel6.repaint();
                }
                
                if(remove_file>1024 && remove_file<=1048576)
                {
                    
                    file_size = (remove_file/1024);
                    System.out.println("File Size : "+df.format(file_size)+" Kb");
                    jLabel6.setText(df.format(file_size)+" Kb");
                    jLabel6.revalidate();
                    jLabel6.repaint();
                }
                
                if(remove_file>1048576 && remove_file<=1073741824)
                {
                    file_size = (remove_file/1048576);
                    System.out.println("File Size : "+df.format(file_size)+" MB");
                    jLabel6.setText(df.format(file_size)+" MB");
                    jLabel6.revalidate();
                    jLabel6.repaint();
                }                
                
                if(remove_file>1073741824 )
                {
                    file_size = (remove_file/1073741824);
                    System.out.println("File Size : "+df.format(file_size)+" GB");
                    jLabel6.setText(df.format(file_size)+" GB");
                    jLabel6.revalidate();
                    jLabel6.repaint();
                }                                
                
                long file_size = 0;
                while(file_size<=remove_file)
                {
                    System.out.println(file_size);
                    file_size = file_size + file_size;
                    jLabel7.setText(""+file_size);
                    jLabel7.revalidate();
                    jLabel7.repaint();
                    file_size++;
                }
                }
               catch(Exception e)
               {
               e.printStackTrace();
               }
            }
        };
        
        t1.start();
        t2.start();
        download d = new download();
        d.start();
    }
    
    public static void saveFileWithCommonsIO(String file_name, String file_url )
    {
        try{
            FileUtils.copyURLToFile(new URL(file_url), new File(file_name));
            System.out.println("Download Finsihed..................");
        }
        catch(Exception e)
        {
            e.printStackTrace();
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

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jProgressBar1 = new javax.swing.JProgressBar();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jLabel12 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel1.setFont(new java.awt.Font("Bookman Old Style", 1, 14)); // NOI18N
        jLabel1.setText("Status :");

        jLabel2.setFont(new java.awt.Font("Bookman Old Style", 1, 14)); // NOI18N
        jLabel2.setText("Downloaded");

        jLabel3.setFont(new java.awt.Font("Bookman Old Style", 1, 14)); // NOI18N
        jLabel3.setText("File Size :");

        jLabel4.setFont(new java.awt.Font("Bookman Old Style", 1, 14)); // NOI18N
        jLabel4.setText("Transfer Rate");

        jLabel5.setFont(new java.awt.Font("Bookman Old Style", 1, 14)); // NOI18N
        jLabel5.setText("Time Left ");

        jLabel6.setFont(new java.awt.Font("Bookman Old Style", 1, 14)); // NOI18N
        jLabel6.setText("NA");

        jLabel7.setFont(new java.awt.Font("Bookman Old Style", 1, 14)); // NOI18N
        jLabel7.setText("NA");

        jLabel8.setFont(new java.awt.Font("Bookman Old Style", 1, 14)); // NOI18N
        jLabel8.setText("NA");

        jLabel9.setFont(new java.awt.Font("Bookman Old Style", 1, 14)); // NOI18N
        jLabel9.setText("NA");

        jLabel10.setFont(new java.awt.Font("Bookman Old Style", 1, 14)); // NOI18N
        jLabel10.setText("Resume");

        jLabel11.setFont(new java.awt.Font("Bookman Old Style", 1, 14)); // NOI18N
        jLabel11.setText("Yes");

        jLabel13.setFont(new java.awt.Font("Bookman Old Style", 1, 14)); // NOI18N
        jLabel13.setText("Download Not Started");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 351, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel2)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5)
                            .addComponent(jLabel10))
                        .addGap(65, 65, 65)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 254, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 254, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 254, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 254, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 254, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(168, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel13))
                .addGap(31, 31, 31)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel7))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jLabel8))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jLabel9))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(jLabel11))
                .addContainerGap(17, Short.MAX_VALUE))
        );

        jProgressBar1.setFont(new java.awt.Font("Bookman Old Style", 1, 14)); // NOI18N
        jProgressBar1.setStringPainted(true);

        jButton1.setFont(new java.awt.Font("Bookman Old Style", 1, 14)); // NOI18N
        jButton1.setText("Details");

        jButton2.setFont(new java.awt.Font("Bookman Old Style", 1, 14)); // NOI18N
        jButton2.setText("Start");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setFont(new java.awt.Font("Bookman Old Style", 1, 14)); // NOI18N
        jButton3.setText("Cancel");

        jLabel12.setFont(new java.awt.Font("Bookman Old Style", 1, 14)); // NOI18N
        jLabel12.setText("Enter URL :");

        jTextField1.setFont(new java.awt.Font("Bookman Old Style", 0, 14)); // NOI18N
        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });

        jMenu1.setText("Status");
        jMenu1.setFont(new java.awt.Font("Bookman Old Style", 1, 13)); // NOI18N

        jMenuItem1.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_W, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem1.setFont(new java.awt.Font("Bookman Old Style", 1, 12)); // NOI18N
        jMenuItem1.setText("Exit");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Options");
        jMenu2.setFont(new java.awt.Font("Bookman Old Style", 1, 13)); // NOI18N
        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jProgressBar1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(37, 37, 37)))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jLabel12)
                .addGap(51, 51, 51)
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 397, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addComponent(jProgressBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2)
                    .addComponent(jButton3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 41, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        
        String saveLoc = "D:\\prabeer\\Data\\java\\Coding\\Java FTP\\save";
        
        if(jButton2.getText().endsWith("Start"))
        {
        try{
            File file = new File(jTextField1.getText().trim());
            String fileName = file.getName();
            
            saveFile(saveLoc+"\\"+fileName,jTextField1.getText().trim());
        }
        
        catch(Exception e)
        {
            e.printStackTrace();
        }
        }
        else{
            JOptionPane.showMessageDialog(null,"An Error : ");
        }
        
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        
        try{
            
            int i = JOptionPane.showConfirmDialog(null,"Sure to Exit","Exit Screen", JOptionPane.YES_OPTION, JOptionPane.NO_OPTION );
            
            if(i == JOptionPane.YES_OPTION)
            {
                dispose();
            }
                    
            
            
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null,"An Error : "+e);
        } 
    }//GEN-LAST:event_jMenuItem1ActionPerformed

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
            java.util.logging.Logger.getLogger(Downloading_file.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Downloading_file.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Downloading_file.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Downloading_file.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Downloading_file().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private static javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private static javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private static javax.swing.JLabel jLabel6;
    private static javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JPanel jPanel1;
    private static javax.swing.JProgressBar jProgressBar1;
    private static javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables
}
