/*
 * $Id$
 *
 * Copyright 2013 Valentyn Kolesnikov
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.ClipboardOwner;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.ProtocolException;
import java.util.Hashtable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import java.sql.*;

/**
 * QR code generator.
 *
 * @author javadev
 * @version $Revision$ $Date$
 */
public class QrcodeGenerator extends javax.swing.JFrame implements Runnable{
    
    public void run()
    {
        //ResultSet rs = null;
//        while(true){
//        try {
//            Class.forName("com.mysql.jdbc.Driver");
//            Connection con = DriverManager.getConnection("jdbc:mysql://automaticcarparking.000webhostapp.com/id7554085_automaticcarparking", "id7554085_root", "goel1234567890");
//            Statement stmt = con.createStatement();
//            String query = "select * from QR where status='used';";
//            rs = stmt.executeQuery(query);
//            if (rs.next()) {
//                int otp=(int)(Math.random()*100000);
//        System.out.println(otp+"");
//        generateQrCode(otp+"");
//                
//            }
//            Thread.sleep(2000);
//            }
//         catch (Exception e) {
//            JOptionPane.showMessageDialog(this, e);
//        }
//        }
        int otp=(int)(Math.random()*100000);
                      System.out.println(otp+"");
                      generateQrCode(otp+"");
                      JavaPostRequest jpr =new JavaPostRequest(otp+"");
            try {
                jpr.Function();
            } catch (ProtocolException ex) {
                Logger.getLogger(QrcodeGenerator.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(QrcodeGenerator.class.getName()).log(Level.SEVERE, null, ex);
            }
        do
        {
            try {
                  JavaGetRequest jgr=new JavaGetRequest();
                  boolean vslue=jgr.func();
                  System.out.println(vslue);
                  if(vslue)
                  {
                      
                      otp=(int)(Math.random()*100000);
                      System.out.println(otp+"");
                      generateQrCode(otp+"");
                      jpr =new JavaPostRequest(otp+"");
                      jpr.Function();
                  }
                  Thread.sleep(2000);
//                  System.out.println(jgr.func());
              } catch (ProtocolException ex) {
                  Logger.getLogger(QrcodeGenerator.class.getName()).log(Level.SEVERE, null, ex);
              } catch (IOException ex) {
                  Logger.getLogger(QrcodeGenerator.class.getName()).log(Level.SEVERE, null, ex);
              }
              catch(Exception e)
              {
                  JOptionPane.showMessageDialog(this, e);
              }
        }
          while(true);
//          {
//              JavaGetRequest jgr=new JavaGetRequest();
//              try {
//                  boolean vslue=jgr.func();
//                  System.out.println(vslue);
//                  if(vslue)
//                  {
//                      
//                      otp=(int)(Math.random()*100000);
//                      System.out.println(otp+"");
//                      generateQrCode(otp+"");
//                      JavaPostRequest jpr =new JavaPostRequest(otp+"");
//                      jpr.Function();
//                  }
//                  Thread.sleep(2000);
////                  System.out.println(jgr.func());
//              } catch (ProtocolException ex) {
//                  Logger.getLogger(QrcodeGenerator.class.getName()).log(Level.SEVERE, null, ex);
//              } catch (IOException ex) {
//                  Logger.getLogger(QrcodeGenerator.class.getName()).log(Level.SEVERE, null, ex);
//              }
//              catch(Exception e)
//              {
//                  JOptionPane.showMessageDialog(this, e);
//              }
//          }
        
//        while(true)
//        {
//        int otp=(int)(Math.random()*100000);
//        System.out.println(otp+"");
//        generateQrCode(otp+"");
//        try
//        {
//            Thread.sleep(2000);
//        }
//        catch(Exception e)
//        {
//            JOptionPane.showMessageDialog(this, e);
//        }
//        }
    }
    private static final String ADDRESS_TEMPLATE =
    "BEGIN:VCARD\n"
    + "VERSION:3.0\n"
    + "N:{LN};{FN};\n"
    + "FN:{FN} {LN}\n"
    + "TITLE:{TITLE}/{COMPANYNAME}\n"
    + "TEL;TYPE=WORK;VOICE:{PHONE}\n"
    + "EMAIL;TYPE=WORK:{EMAIL}\n"
    + "ADR;TYPE=INTL,POSTAL,WORK:;;{STREET};{CITY};{STATE};{ZIP};{COUNTRY}\n"
    + "URL;TYPE=WORK:{WEBSITE}\n"
    + "END:VCARD";

    private BufferedImage image;
    private JFileChooser chooser1 = new JFileChooser();
    private class QRCodePanel extends javax.swing.JPanel {

        @Override
        protected void paintComponent(Graphics grphcs) {
            super.paintComponent(grphcs);
            if (image != null) {
                grphcs.drawImage(image, 0, 0, null);
            }
        }
    }

    private class FilterPNG extends javax.swing.filechooser.FileFilter {
        public String getDescription() {
            return "PNG files";
        }

        public boolean accept(File file) {
            if (file.isDirectory()) {
                return true;
            }
            String name = file.getName();
            name = name.toLowerCase();
            return name.endsWith(".png");
        }
    }

    private class TransferableImage implements Transferable {

        private final java.awt.Image image;

        public TransferableImage(java.awt.Image image) {
            this.image = image;
        }

        public Object getTransferData(DataFlavor flavor) throws UnsupportedFlavorException, IOException {
            if (flavor.equals( DataFlavor.imageFlavor) && image != null) {
                return image;
            } else {
                throw new UnsupportedFlavorException(flavor);
            }
        }

        public DataFlavor[] getTransferDataFlavors() {
            DataFlavor[] flavors = new DataFlavor[1];
            flavors[0] = DataFlavor.imageFlavor;
            return flavors;
        }

        public boolean isDataFlavorSupported( DataFlavor flavor) {
            DataFlavor[] flavors = getTransferDataFlavors();
            for (int i = 0; i < flavors.length; i++) {
                if (flavor.equals(flavors[i])) {
                    return true;
                }
            }

            return false;
        }
    }

    /** Creates new form */
    public QrcodeGenerator() {
        initComponents();
        XMLDecoder d;
        String x = null;
        String y = null;
        String height = null;
        String width = null;
        String index = null;
        try {
           d = new XMLDecoder(new BufferedInputStream(new FileInputStream("qrcode.xml")));
//           jTextField1.setText((String) d.readObject());
//           jTextField2.setText((String) d.readObject());
//           jTextField3.setText((String) d.readObject());
//           jTextField4.setText((String) d.readObject());
//           jTextField5.setText((String) d.readObject());
//           jTextField6.setText((String) d.readObject());
//           jTextField7.setText((String) d.readObject());
//           jTextField8.setText((String) d.readObject());
//           jTextField9.setText((String) d.readObject());
//           jTextField10.setText((String) d.readObject());
//           jTextField11.setText((String) d.readObject());
//           jTextField12.setText((String) d.readObject());
//           jTextField13.setText((String) d.readObject());
//           jTextField14.setText((String) d.readObject());
//           jTextField15.setText((String) d.readObject());
//           jTextArea1.setText((String) d.readObject());
//           jTextArea2.setText((String) d.readObject());
           x = (String) d.readObject();
           y = (String) d.readObject();
           height = (String) d.readObject();
           width = (String) d.readObject();
           index = (String) d.readObject();
           d.close();
        } catch (Exception ex) {
            ex.getMessage();
        }

        addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent winEvt) {
                XMLEncoder e;
                try {
                    e = new XMLEncoder(new BufferedOutputStream(new FileOutputStream("qrcode.xml")));
//                    e.writeObject(jTextField1.getText());
//                    e.writeObject(jTextField2.getText());
//                    e.writeObject(jTextField3.getText());
//                    e.writeObject(jTextField4.getText());
//                    e.writeObject(jTextField5.getText());
//                    e.writeObject(jTextField6.getText());
//                    e.writeObject(jTextField7.getText());
//                    e.writeObject(jTextField8.getText());
//                    e.writeObject(jTextField9.getText());
//                    e.writeObject(jTextField10.getText());
//                    e.writeObject(jTextField11.getText());
//                    e.writeObject(jTextField12.getText());
//                    e.writeObject(jTextField13.getText());
//                    e.writeObject(jTextField14.getText());
//                    e.writeObject(jTextField15.getText());
//                    e.writeObject(jTextArea1.getText());
//                    e.writeObject(jTextArea2.getText());
                    e.writeObject("" + getLocation().x);
                    e.writeObject("" + getLocation().y);
                    e.writeObject("" + getSize().height);
                    e.writeObject("" + getSize().width);
//                    e.writeObject("" + jTabbedPane6.getSelectedIndex());
                    e.close();
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(QrcodeGenerator.class.getName()).log(Level.SEVERE, null, ex);
                }
                System.exit(0);
            }
        });
        chooser1.addChoosableFileFilter(new FilterPNG());
        chooser1.setDialogTitle("Select PNG file");
        chooser1.setCurrentDirectory(new File("."));
        final java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        if (x == null) {
            x = "" + ((screenSize.width - getWidth()) / 2);
        }
        if (y == null) {
            y = "" + ((screenSize.height - getHeight()) / 2);
        }
        if (height == null) {
            height = "" + getPreferredSize().height;
        }
        if (width == null) {
            width = "" + getPreferredSize().width;
        }
        if (index == null) {
//            index = "" + jTabbedPane6.getSelectedIndex();
        }
//        setLocation(Integer.valueOf(x), Integer.valueOf(y));
//        setSize(new Dimension(Integer.valueOf(width), Integer.valueOf(height)));
       // jTabbedPane6.setSelectedIndex(Integer.valueOf(index));
    }


    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel3 = new QRCodePanel();
        jButton3 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("QR code generator");

        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel3.setPreferredSize(new java.awt.Dimension(212, 212));

        org.jdesktop.layout.GroupLayout jPanel3Layout = new org.jdesktop.layout.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(0, 208, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(0, 208, Short.MAX_VALUE)
        );

        jButton3.setText("Generate QR");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap(198, Short.MAX_VALUE)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, layout.createSequentialGroup()
                        .add(jPanel3, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .add(191, 191, 191))
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, layout.createSequentialGroup()
                        .add(jButton3)
                        .add(249, 249, 249))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .add(57, 57, 57)
                .add(jPanel3, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .add(49, 49, 49)
                .add(jButton3)
                .addContainerGap(117, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        int otp=(int)(Math.random()*100000);
        System.out.println(otp+"");
        generateQrCode(otp+"");
        JavaPostRequest jpr =new JavaPostRequest(otp+"");
        try {
            jpr.Function();
        } catch (ProtocolException ex) {
            Logger.getLogger(QrcodeGenerator.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(QrcodeGenerator.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton3ActionPerformed


    private static void setLookAndFeel()
        throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException
    {
        javax.swing.UIManager.LookAndFeelInfo infos[] = UIManager.getInstalledLookAndFeels();
        String firstFoundClass = null;
        for (javax.swing.UIManager.LookAndFeelInfo info : infos) {
            String foundClass = info.getClassName();
            if ("Nimbus".equals(info.getName())) {
                firstFoundClass = foundClass;
                break;
            }
            if (null == firstFoundClass) {
                firstFoundClass = foundClass;
            }
        }

        if(null == firstFoundClass)  {
            throw new IllegalArgumentException("No suitable Swing looks and feels");
        } else {
            UIManager.setLookAndFeel(firstFoundClass);
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) throws Exception {
        QrcodeGenerator q=new QrcodeGenerator();
        Thread t=new Thread(q);
        //t.start();
        setLookAndFeel();
        //t.start();
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                QrcodeGenerator q=new QrcodeGenerator();
                q.setVisible(true);
                Thread t=new Thread(q);
                t.start();
            }
        });
        //t.start();
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton3;
    private javax.swing.JPanel jPanel3;
    // End of variables declaration//GEN-END:variables

    private void generateQrCode(String messsage) {
        Logger.getLogger(QrcodeGenerator.class.getName()).log(Level.INFO, messsage);
        if (messsage == null || messsage.isEmpty()) {
            image = null;
            return;
        }
        try {
            Hashtable hintMap = new Hashtable();
            hintMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);
            hintMap.put(EncodeHintType.CHARACTER_SET, "UTF-8");
            QRCodeWriter qrCodeWriter = new QRCodeWriter();
            BitMatrix byteMatrix = qrCodeWriter.encode(messsage,
                    BarcodeFormat.QR_CODE, jPanel3.getPreferredSize().width, jPanel3.getPreferredSize().height, hintMap);
            int CrunchifyWidth = byteMatrix.getWidth();
            image = new BufferedImage(CrunchifyWidth, CrunchifyWidth,
                    BufferedImage.TYPE_INT_RGB);
            image.createGraphics();
            Graphics2D graphics=(Graphics2D)image.getGraphics();
            graphics.setColor(Color.WHITE);
            graphics.fillRect(0, 0, CrunchifyWidth, CrunchifyWidth);
            graphics.setColor(Color.BLACK);
            for (int i = 0; i < CrunchifyWidth; i++) {
                for (int j = 0; j < CrunchifyWidth; j++) {
                    if (byteMatrix.get(i, j)) {
                        graphics.fillRect(i, j, 1, 1);
                    }
                }
            }
            jPanel3.repaint();
        } catch (WriterException ex) {
            Logger.getLogger(QrcodeGenerator.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
