/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package apk.makost.main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.LayoutManager;
import java.awt.RenderingHints;
import java.io.File;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author DELL
 */
public class Register extends javax.swing.JFrame {
private String filename;
    /**
     * Creates new form Register
     */
    public Register() {
        initComponents();
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        
    }
    
    class akun extends Register{
    String iduser="", username="",password="",nadep="",nabel="",hewan="",
            alamat="",hobi="",nik="",email="",notelp="",jabatan="",tgllahir="";
    public akun(){
    iduser= txtiduser.getText(); 
    username= txtuserregis.getText();
    password= txtpwregis.getText();
    nadep= txtnadep.getText();
    nabel= txtnabel.getText();
    hewan= txtnamahewan.getText();
    alamat= txtalamat.getText();
    hobi= txthobi.getText();
    nik= txtnik.getText();
    email= txtemail.getText();
    notelp= txtnotelp.getText();
    jabatan= cbjabatan.getSelectedItem().toString();
    tgllahir= jDateChooser1.getDateFormatString();
    
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
        jPanel3 = new RoundedPanel(200, Color.LIGHT_GRAY);
        jPanel4 = new RoundedPanel(250, Color.LIGHT_GRAY);
        jPanel5 = new RoundedPanel(350, Color.WHITE);
        jLabel1 = new javax.swing.JLabel();
        jPanel6 = new RoundedPanel(150, Color.LIGHT_GRAY);
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        header2 = new apk.makost.component.Header();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtiduser = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtuserregis = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txtpwregis = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        txtnadep = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        txtnabel = new javax.swing.JTextField();
        txtnamahewan = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        txthobi = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        txtnik = new javax.swing.JTextField();
        jLabel27 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        txtemail = new javax.swing.JTextField();
        jLabel29 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        txtnotelp = new javax.swing.JTextField();
        jLabel31 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtalamat = new javax.swing.JTextArea();
        btnbatal = new javax.swing.JButton();
        btnbuatakun = new javax.swing.JButton();
        jLabel34 = new javax.swing.JLabel();
        cbjabatan = new javax.swing.JComboBox<>();
        jLabel35 = new javax.swing.JLabel();
        labelfoto = new javax.swing.JLabel();
        btnpilihfoto = new javax.swing.JButton();
        jLabel36 = new javax.swing.JLabel();
        jDateChooser1 = new com.toedter.calendar.JDateChooser();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(243, 246, 251));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel3.setBackground(new java.awt.Color(243, 246, 251));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 200, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 200, Short.MAX_VALUE)
        );

        jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jPanel4.setBackground(new java.awt.Color(243, 246, 251));

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 250, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 250, Short.MAX_VALUE)
        );

        jPanel1.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 608, -1, -1));

        jPanel5.setBackground(new java.awt.Color(243, 246, 251));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/apk/makost/icon/logo.png"))); // NOI18N

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addComponent(jLabel1)
                .addContainerGap(50, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap(83, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(74, 74, 74))
        );

        jPanel1.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(136, 252, -1, -1));

        jPanel6.setBackground(new java.awt.Color(243, 246, 251));

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 150, Short.MAX_VALUE)
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 150, Short.MAX_VALUE)
        );

        jPanel1.add(jPanel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(492, 228, -1, -1));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Registrasi");
        jPanel2.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 110, 710, -1));
        jPanel2.add(header2, new org.netbeans.lib.awtextra.AbsoluteConstraints(29, 23, 710, -1));

        jLabel3.setFont(new java.awt.Font("Segoe UI Semilight", 0, 12)); // NOI18N
        jLabel3.setText("ID User");
        jPanel2.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 180, 290, -1));

        jLabel4.setFont(jLabel4.getFont().deriveFont(jLabel4.getFont().getSize()+2f));
        jLabel4.setText("_______________________________________");
        jPanel2.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 200, -1, 30));

        txtiduser.setBorder(null);
        jPanel2.add(txtiduser, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 200, 260, -1));

        jLabel5.setFont(new java.awt.Font("Segoe UI Semilight", 0, 12)); // NOI18N
        jLabel5.setText("Username");
        jPanel2.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 230, 280, -1));

        jLabel6.setFont(jLabel6.getFont().deriveFont(jLabel6.getFont().getSize()+2f));
        jLabel6.setText("_______________________________________");
        jPanel2.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 250, -1, 30));

        txtuserregis.setBorder(null);
        jPanel2.add(txtuserregis, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 250, 270, -1));

        jLabel7.setFont(new java.awt.Font("Segoe UI Semilight", 0, 12)); // NOI18N
        jLabel7.setText("Password");
        jPanel2.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 280, 270, -1));

        jLabel8.setFont(jLabel8.getFont().deriveFont(jLabel8.getFont().getSize()+2f));
        jLabel8.setText("_______________________________________");
        jPanel2.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 300, -1, 30));

        txtpwregis.setBorder(null);
        jPanel2.add(txtpwregis, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 300, 270, -1));

        jLabel9.setFont(new java.awt.Font("Segoe UI Semilight", 0, 12)); // NOI18N
        jLabel9.setText("Nama depan");
        jPanel2.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 330, 270, -1));

        jLabel10.setFont(jLabel10.getFont().deriveFont(jLabel10.getFont().getSize()+2f));
        jLabel10.setText("_______________________________________");
        jPanel2.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 350, -1, 30));

        txtnadep.setBorder(null);
        jPanel2.add(txtnadep, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 350, 270, -1));

        jLabel11.setFont(new java.awt.Font("Segoe UI Semilight", 0, 12)); // NOI18N
        jLabel11.setText("Nama belakang");
        jPanel2.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 380, 270, -1));

        jLabel12.setFont(jLabel12.getFont().deriveFont(jLabel12.getFont().getSize()+2f));
        jLabel12.setText("_______________________________________");
        jPanel2.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 400, -1, 30));

        txtnabel.setBorder(null);
        jPanel2.add(txtnabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 400, 270, -1));

        txtnamahewan.setBorder(null);
        jPanel2.add(txtnamahewan, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 450, 280, -1));

        jLabel13.setFont(jLabel13.getFont().deriveFont(jLabel13.getFont().getSize()+2f));
        jLabel13.setText("_______________________________________");
        jPanel2.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 450, -1, 30));

        jLabel14.setFont(new java.awt.Font("Segoe UI Semilight", 0, 12)); // NOI18N
        jLabel14.setText("Nama Hewan");
        jPanel2.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 430, 270, -1));

        txthobi.setBorder(null);
        jPanel2.add(txthobi, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 200, 280, -1));

        jLabel15.setFont(jLabel15.getFont().deriveFont(jLabel15.getFont().getSize()+2f));
        jLabel15.setText("_______________________________________");
        jPanel2.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 200, -1, 30));

        jLabel16.setFont(new java.awt.Font("Segoe UI Semilight", 0, 12)); // NOI18N
        jLabel16.setText("Hobi");
        jPanel2.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 180, 270, -1));

        txtnik.setBorder(null);
        jPanel2.add(txtnik, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 250, 280, -1));

        jLabel27.setFont(jLabel27.getFont().deriveFont(jLabel27.getFont().getSize()+2f));
        jLabel27.setText("_______________________________________");
        jPanel2.add(jLabel27, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 250, -1, 30));

        jLabel28.setFont(new java.awt.Font("Segoe UI Semilight", 0, 12)); // NOI18N
        jLabel28.setText("NIK");
        jPanel2.add(jLabel28, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 230, 270, -1));

        txtemail.setBorder(null);
        jPanel2.add(txtemail, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 300, 280, -1));

        jLabel29.setFont(jLabel29.getFont().deriveFont(jLabel29.getFont().getSize()+2f));
        jLabel29.setText("_______________________________________");
        jPanel2.add(jLabel29, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 300, -1, 30));

        jLabel30.setFont(new java.awt.Font("Segoe UI Semilight", 0, 12)); // NOI18N
        jLabel30.setText("Email");
        jPanel2.add(jLabel30, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 280, 270, -1));

        txtnotelp.setBorder(null);
        jPanel2.add(txtnotelp, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 350, 280, -1));

        jLabel31.setFont(jLabel31.getFont().deriveFont(jLabel31.getFont().getSize()+2f));
        jLabel31.setText("_______________________________________");
        jPanel2.add(jLabel31, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 350, -1, 30));

        jLabel32.setFont(new java.awt.Font("Segoe UI Semilight", 0, 12)); // NOI18N
        jLabel32.setText("Foto");
        jPanel2.add(jLabel32, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 480, 270, -1));

        jLabel33.setFont(new java.awt.Font("Segoe UI Semilight", 0, 12)); // NOI18N
        jLabel33.setText("Alamat");
        jPanel2.add(jLabel33, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 480, 240, -1));

        jScrollPane1.setBackground(new java.awt.Color(255, 255, 255));

        txtalamat.setColumns(20);
        txtalamat.setRows(5);
        txtalamat.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txtalamat.setSelectedTextColor(new java.awt.Color(0, 0, 0));
        jScrollPane1.setViewportView(txtalamat);

        jPanel2.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 500, 290, 120));

        btnbatal.setBackground(new java.awt.Color(54, 134, 178));
        btnbatal.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        btnbatal.setForeground(new java.awt.Color(255, 255, 255));
        btnbatal.setText("Batal");
        btnbatal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnbatalActionPerformed(evt);
            }
        });
        jPanel2.add(btnbatal, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 630, 180, 40));

        btnbuatakun.setBackground(new java.awt.Color(54, 134, 178));
        btnbuatakun.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        btnbuatakun.setForeground(new java.awt.Color(255, 255, 255));
        btnbuatakun.setText("Buat Akun");
        btnbuatakun.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnbuatakunActionPerformed(evt);
            }
        });
        jPanel2.add(btnbuatakun, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 630, 470, 40));

        jLabel34.setFont(new java.awt.Font("Segoe UI Semilight", 0, 12)); // NOI18N
        jLabel34.setText("No Telepon");
        jPanel2.add(jLabel34, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 330, 270, -1));

        cbjabatan.setFont(new java.awt.Font("Segoe UI Semilight", 0, 12)); // NOI18N
        cbjabatan.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "admin", "karyawan", " " }));
        jPanel2.add(cbjabatan, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 450, 120, -1));

        jLabel35.setFont(new java.awt.Font("Segoe UI Semilight", 0, 12)); // NOI18N
        jLabel35.setText("Jabatan");
        jPanel2.add(jLabel35, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 430, 270, -1));

        labelfoto.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel2.add(labelfoto, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 500, 220, 120));

        btnpilihfoto.setFont(new java.awt.Font("Segoe UI Semilight", 0, 12)); // NOI18N
        btnpilihfoto.setText("Pilih Foto");
        btnpilihfoto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnpilihfotoActionPerformed(evt);
            }
        });
        jPanel2.add(btnpilihfoto, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 590, -1, -1));

        jLabel36.setFont(new java.awt.Font("Segoe UI Semilight", 0, 12)); // NOI18N
        jLabel36.setText("Tanggal Lahir");
        jPanel2.add(jLabel36, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 380, 270, -1));
        jPanel2.add(jDateChooser1, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 400, 270, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 612, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 754, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 769, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnbatalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnbatalActionPerformed
        // TODO add your handling code here:
        this.setVisible(false);
        new Login().setVisible(true);
    }//GEN-LAST:event_btnbatalActionPerformed

    private void btnpilihfotoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnpilihfotoActionPerformed
        // TODO add your handling code here:
        akun a = new akun();
        if(a.username.equals("") || a.password.equals("")){
            JOptionPane.showMessageDialog(null, "Data tidak boleh kosong, silahkan isi terlebih dahulu");
        }else{
            try {
            JFileChooser chooser = new JFileChooser();
            chooser.showOpenDialog(null);
            File f = chooser.getSelectedFile();
            ImageIcon icon = new ImageIcon(f.toString());
            Image img = icon.getImage().getScaledInstance(labelfoto.getWidth(), labelfoto.getHeight(), Image.SCALE_DEFAULT);
            ImageIcon ic = new ImageIcon(img);
            labelfoto.setIcon(ic);
            this.filename = f.getAbsolutePath();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
            }
        }
    }//GEN-LAST:event_btnpilihfotoActionPerformed

    private void btnbuatakunActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnbuatakunActionPerformed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_btnbuatakunActionPerformed

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
            java.util.logging.Logger.getLogger(Register.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Register.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Register.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Register.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Register().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnbatal;
    private javax.swing.JButton btnbuatakun;
    private javax.swing.JButton btnpilihfoto;
    private javax.swing.JComboBox<String> cbjabatan;
    private apk.makost.component.Header header2;
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel labelfoto;
    private javax.swing.JTextArea txtalamat;
    private javax.swing.JTextField txtemail;
    private javax.swing.JTextField txthobi;
    private javax.swing.JTextField txtiduser;
    private javax.swing.JTextField txtnabel;
    private javax.swing.JTextField txtnadep;
    private javax.swing.JTextField txtnamahewan;
    private javax.swing.JTextField txtnik;
    private javax.swing.JTextField txtnotelp;
    private javax.swing.JTextField txtpwregis;
    private javax.swing.JTextField txtuserregis;
    // End of variables declaration//GEN-END:variables
class RoundedPanel extends JPanel
    {
        private Color backgroundColor;
        private int cornerRadius = 15;
        public RoundedPanel(LayoutManager layout, int radius) {
            super(layout);
            cornerRadius = radius;
        }
        public RoundedPanel(LayoutManager layout, int radius, Color bgColor) {
            super(layout);
            cornerRadius = radius;
            backgroundColor = bgColor;
        }
        public RoundedPanel(int radius) {
            super();
            cornerRadius = radius;
            
        }
        public RoundedPanel(int radius, Color bgColor) {
            super();
            cornerRadius = radius;
            backgroundColor = bgColor;
        }
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Dimension arcs = new Dimension(cornerRadius, cornerRadius);
            int width = getWidth();
            int height = getHeight();
            Graphics2D graphics = (Graphics2D) g;
            graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            //Draws the rounded panel with borders.
            if (backgroundColor != null) {
                graphics.setColor(backgroundColor);
            } else {
                graphics.setColor(getBackground());
            }
            graphics.fillRoundRect(0, 0, width-1, height-1, arcs.width, arcs.height); //paint background
            graphics.setColor(getForeground());
//            graphics.drawRoundRect(0, 0, width-1, height-1, arcs.width, arcs.height); //paint border
//             
        }
    }


}
