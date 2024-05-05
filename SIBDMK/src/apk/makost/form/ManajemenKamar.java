/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package apk.makost.form;

import apk.makost.koneksi.Koneksi;
import static apk.makost.koneksi.Koneksi.conn;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.Locale;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.RowFilter;
import javax.swing.table.TableRowSorter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import javax.swing.UIManager;



/**
 *
 * @author dF
 */

public class ManajemenKamar extends javax.swing.JPanel {
    
    
//    method tabel kamar
    private void tabel_tersedia(){
        DefaultTableModel data = new DefaultTableModel();
        data.addColumn("Id Manajemen Kamar");
        data.addColumn("No Kamar");
        data.addColumn("Id Penghuni");
        data.addColumn("Nama");
        data.addColumn("Tanggal Registrasi");
        data.addColumn("Tanggal Jatuh Tempo");
        
        
//        SELECT no_kamar, nama, jenis_sewa, registrasi, jthtempo from tbl_manajemen_kamar 
//          INNER JOIN tbl_penghuni ON tbl_manajemen_kamar.id_penghuni = tbl_penghuni.id_penghuni ORDER BY no_kamar ASC;

        String cari = txtcari.getText();
        try{
            String sql = "SELECT id_manajemen, no_kamar, tbl_manajemen_kamar.id_penghuni, nama, registrasi, jthtempo FROM tbl_manajemen_kamar "
                    + "JOIN tbl_penghuni ON tbl_manajemen_kamar.id_penghuni = tbl_penghuni.id_penghuni "
                    + "WHERE tbl_manajemen_kamar.id_penghuni LIKE '%"+cari+"%' "
                    + "or nama LIKE '%"+cari+"%' or registrasi LIKE '%"+cari+"%' or jthtempo LIKE '%"+cari+"%'"
                    + "ORDER BY no_kamar ASC";
            java.sql.Connection conn = (Connection)apk.makost.koneksi.Koneksi.configDB();
            java.sql.Statement stm = conn.createStatement();
            java.sql.ResultSet res = stm.executeQuery(sql);
            while(res.next())
                data.addRow(new Object[]{
//                    ikutin jumlah kolom tabel di database
                    res.getString(1), //id_manajemen
                    res.getString(2), //no kamar
                    res.getString(3), //id_penghuni
                    res.getString(4), //nama
                    res.getString(5),// regis
                    res.getString(6)// jatuh tempo
                });
                tbl_manajemenKamar.setModel(data);
            } catch (Exception e){
                JOptionPane.showMessageDialog(null, e);
        }
    }
    
    /**
     * Creates new form ManajemenKamar
     */
    
//    mendapatkan nomor kamar
    private void cmbnoKamar(){
        try{
            String sql = "Select no_kamar from tbl_kamar WHERE status = 'Tersedia' ORDER BY no_kamar ASC";
            java.sql.Connection conn = (Connection)apk.makost.koneksi.Koneksi.configDB();
            java.sql.Statement stm = conn.createStatement();
            java.sql.ResultSet res = stm.executeQuery(sql);
            while(res.next()){
                cmbnoKamar.addItem(res.getString(1));
            }
        } catch (Exception e){
//            JOptionPane.showMessageDialog(shadowPanel1, e);
        }
    }
    
    private void cmbNamaPenghuni(){
        try{
            String sql = "Select id_penghuni from tbl_penghuni ORDER BY id_penghuni ASC";
            java.sql.Connection conn = (Connection)apk.makost.koneksi.Koneksi.configDB();
            java.sql.Statement stm = conn.createStatement();
            java.sql.ResultSet res = stm.executeQuery(sql);
            while(res.next()){
                cmbNamaPenghuni.addItem(res.getString(1));
            }
        }catch(Exception e){
            
        }
    }

//    mengosongkan inputan yang telah diambil
    private void Clear(){
//        dateJatuhTempo.setDateFormatString("");
        txtIdManajemenKamar.setText("");
        cmbNamaPenghuni.setSelectedItem(null);
        cmbnoKamar.setSelectedItem(null);
        tabel_tersedia();
    }
    
    Date date = new Date();
    SimpleDateFormat sDateFormate = new SimpleDateFormat("yyyy-MM-dd");
    DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    
    public ManajemenKamar() {
        initComponents();
        tabel_tersedia();
        cmbnoKamar();
        cmbNamaPenghuni();
        
        txtJatuhTempo.setText(sDateFormate.format(date));
    }

    public void tanggal(){
        DateFormat tgl = new SimpleDateFormat("dd/mm/yyyy");
        String jthtempo = tgl.format(Calendar.getInstance().getTime());
        txtJatuhTempo.setText(jthtempo);
    }
    
//    simpan data
    private void simpanManajemenKamar(){
//        INSERT INTO `tbl_manajemen_kamar` (`id_manajemen`, `no_kamar`, `id_penghuni`, `jthtempo`) 
//          VALUES (NULL, '4', '8', NULL);

        String jthtempo = txtJatuhTempo.getText();
        try{
            String sql = "INSERT INTO `tbl_manajemen_kamar` ( `id_manajemen`, `no_kamar`, `id_penghuni`, jthtempo) VALUES "
                    + "(NULL, '"+cmbnoKamar.getSelectedItem()+"', '"+cmbNamaPenghuni.getSelectedItem()+"', '"+jthtempo+"')";
            java.sql.Connection conn = (Connection)apk.makost.koneksi.Koneksi.configDB();
            java.sql.PreparedStatement pst = conn.prepareStatement(sql);
            int row = pst.executeUpdate(sql);
            if (row==1) {
                JOptionPane.showMessageDialog(null, "Data Berhasil Disimpan");
            }
            Clear();
        } catch (Exception e){
            JOptionPane.showMessageDialog(this, "Data Tidak Tersimpan \n" + e.getMessage());
        }
        tabel_tersedia();
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        shadowPanel1 = new apk.makost.swing.ShadowPanel();
        cmbnoKamar = new apk.makost.swing.Combobox();
        txtcari = new apk.makost.swing.TextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbl_manajemenKamar = new apk.makost.swing.Table();
        btnEditManajemenKamar = new apk.makost.swing.Button();
        btnResetManajemenKamar = new apk.makost.swing.Button();
        btnSimpanManajemenKamar = new apk.makost.swing.Button();
        jLabel1 = new javax.swing.JLabel();
        cmbNamaPenghuni = new apk.makost.swing.Combobox();
        jLabel2 = new javax.swing.JLabel();
        txtJatuhTempo = new apk.makost.swing.TextField();
        txtIdManajemenKamar = new apk.makost.swing.TextField();

        setBackground(new java.awt.Color(236, 243, 255));
        setPreferredSize(new java.awt.Dimension(1069, 663));

        cmbnoKamar.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        cmbnoKamar.setLabeText("");
        cmbnoKamar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbnoKamarActionPerformed(evt);
            }
        });

        txtcari.setLabelText("Cari");
        txtcari.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtcariActionPerformed(evt);
            }
        });
        txtcari.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtcariKeyReleased(evt);
            }
        });

        tbl_manajemenKamar.setForeground(new java.awt.Color(255, 255, 255));
        tbl_manajemenKamar.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tbl_manajemenKamar.setGridColor(new java.awt.Color(14, 11, 164));
        tbl_manajemenKamar.setSelectionBackground(new java.awt.Color(0, 153, 255));
        tbl_manajemenKamar.setShowGrid(false);
        tbl_manajemenKamar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_manajemenKamarMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbl_manajemenKamar);

        btnEditManajemenKamar.setBackground(new java.awt.Color(207, 211, 0));
        btnEditManajemenKamar.setForeground(new java.awt.Color(255, 255, 255));
        btnEditManajemenKamar.setText("EDIT");
        btnEditManajemenKamar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditManajemenKamarActionPerformed(evt);
            }
        });

        btnResetManajemenKamar.setBackground(new java.awt.Color(126, 166, 195));
        btnResetManajemenKamar.setForeground(new java.awt.Color(255, 255, 255));
        btnResetManajemenKamar.setText("RESET");
        btnResetManajemenKamar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResetManajemenKamarActionPerformed(evt);
            }
        });

        btnSimpanManajemenKamar.setBackground(new java.awt.Color(126, 166, 195));
        btnSimpanManajemenKamar.setForeground(new java.awt.Color(255, 255, 255));
        btnSimpanManajemenKamar.setText("SIMPAN");
        btnSimpanManajemenKamar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSimpanManajemenKamarActionPerformed(evt);
            }
        });

        jLabel1.setForeground(new java.awt.Color(153, 153, 153));
        jLabel1.setText("No Kamar");

        cmbNamaPenghuni.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        cmbNamaPenghuni.setLabeText("");
        cmbNamaPenghuni.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbNamaPenghuniActionPerformed(evt);
            }
        });

        jLabel2.setForeground(new java.awt.Color(153, 153, 153));
        jLabel2.setText("Id Penghuni");

        txtJatuhTempo.setEditable(false);
        txtJatuhTempo.setLabelText("Tanggal Jatuh Tempo");

        txtIdManajemenKamar.setEditable(false);
        txtIdManajemenKamar.setLabelText("Id Manajemen Kamar");
        txtIdManajemenKamar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtIdManajemenKamarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout shadowPanel1Layout = new javax.swing.GroupLayout(shadowPanel1);
        shadowPanel1.setLayout(shadowPanel1Layout);
        shadowPanel1Layout.setHorizontalGroup(
            shadowPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, shadowPanel1Layout.createSequentialGroup()
                .addContainerGap(30, Short.MAX_VALUE)
                .addGroup(shadowPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(shadowPanel1Layout.createSequentialGroup()
                        .addGroup(shadowPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtIdManajemenKamar, javax.swing.GroupLayout.PREFERRED_SIZE, 233, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1)
                            .addComponent(cmbnoKamar, javax.swing.GroupLayout.PREFERRED_SIZE, 233, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(224, 224, 224)
                        .addGroup(shadowPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtJatuhTempo, javax.swing.GroupLayout.PREFERRED_SIZE, 233, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2)
                            .addComponent(cmbNamaPenghuni, javax.swing.GroupLayout.PREFERRED_SIZE, 233, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, shadowPanel1Layout.createSequentialGroup()
                        .addGroup(shadowPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1003, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(shadowPanel1Layout.createSequentialGroup()
                                .addComponent(btnResetManajemenKamar, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnSimpanManajemenKamar, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnEditManajemenKamar, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(469, 469, 469)
                                .addComponent(txtcari, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(36, 36, 36))))
        );
        shadowPanel1Layout.setVerticalGroup(
            shadowPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(shadowPanel1Layout.createSequentialGroup()
                .addContainerGap(27, Short.MAX_VALUE)
                .addGroup(shadowPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtJatuhTempo, javax.swing.GroupLayout.DEFAULT_SIZE, 60, Short.MAX_VALUE)
                    .addComponent(txtIdManajemenKamar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(shadowPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(shadowPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cmbnoKamar, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(shadowPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cmbNamaPenghuni, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(30, 30, 30)
                .addGroup(shadowPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtcari, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnResetManajemenKamar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSimpanManajemenKamar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnEditManajemenKamar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 282, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(109, 109, 109))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(shadowPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(shadowPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void txtcariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtcariActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtcariActionPerformed

    private void btnSimpanManajemenKamarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSimpanManajemenKamarActionPerformed
        // TODO add your handling code here:
//       INSERT INTO `tbl_kamar`(`no_kamar`, `lantai`, `kd_kapasitas`, `fasilitas`, `status`)
//       VALUES ('6', '1', '2 orang', 'asd', 'tersedia' )
        simpanManajemenKamar();
//        cmbnoKamar();
    }//GEN-LAST:event_btnSimpanManajemenKamarActionPerformed

    private void cmbnoKamarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbnoKamarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbnoKamarActionPerformed

    private void btnResetManajemenKamarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResetManajemenKamarActionPerformed
        // TODO add your handling code here:
        Clear();
    }//GEN-LAST:event_btnResetManajemenKamarActionPerformed

    private void tbl_manajemenKamarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_manajemenKamarMouseClicked
        // TODO add your handling code here:
        int baris = tbl_manajemenKamar.rowAtPoint(evt.getPoint());
        String id_manajemenKamar = tbl_manajemenKamar.getValueAt(baris, 0).toString();
        txtIdManajemenKamar.setText(id_manajemenKamar);
        txtIdManajemenKamar.disable();
        if(tbl_manajemenKamar.getValueAt(baris, 1)== null){
            cmbnoKamar.setSelectedItem(this);
        }else{
            cmbnoKamar.setSelectedItem(tbl_manajemenKamar.getValueAt(baris, 1).toString());
        }
        if(tbl_manajemenKamar.getValueAt(baris, 2) == null){
            cmbNamaPenghuni.setSelectedItem(this);
        }else{
            cmbNamaPenghuni.setSelectedItem(tbl_manajemenKamar.getValueAt(baris, 2).toString());
        }
        if(tbl_manajemenKamar.getValueAt(baris, 5) == null){
            txtJatuhTempo.setText("");
        }else{
            txtJatuhTempo.setText(tbl_manajemenKamar.getValueAt(baris, 5).toString());
        }
    }//GEN-LAST:event_tbl_manajemenKamarMouseClicked

    private void btnEditManajemenKamarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditManajemenKamarActionPerformed
        // TODO add your handling code here:
//        UPDATE `tbl_manajemen_kamar` SET `no_kamar` = '3' WHERE `tbl_manajemen_kamar`.`id_manajemen` = 39;
        try{
            String sql = "UPDATE tbl_manajemen_kamar SET no_kamar = '"+cmbnoKamar.getSelectedItem()+"', "
                    + "id_penghuni = '"+cmbNamaPenghuni.getSelectedItem()+"' "
                    + "WHERE tbl_manajemen_kamar.id_manajemen = '"+txtIdManajemenKamar.getText()+"'";
            java.sql.Connection conn = (Connection)apk.makost.koneksi.Koneksi.configDB();
            java.sql.PreparedStatement pst = conn.prepareStatement(sql);
            pst.execute();
            JOptionPane.showMessageDialog(null, "Data Berhasil Diubah");
        }catch (Exception e){
            JOptionPane.showMessageDialog(null, "Perubahan Data Gagal");
        }
        tabel_tersedia();
    }//GEN-LAST:event_btnEditManajemenKamarActionPerformed

    private void txtcariKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtcariKeyReleased
        // TODO add your handling code here:
        tabel_tersedia();
    }//GEN-LAST:event_txtcariKeyReleased

    private void cmbNamaPenghuniActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbNamaPenghuniActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbNamaPenghuniActionPerformed

    private void txtIdManajemenKamarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtIdManajemenKamarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtIdManajemenKamarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private apk.makost.swing.Button btnEditManajemenKamar;
    private apk.makost.swing.Button btnResetManajemenKamar;
    private apk.makost.swing.Button btnSimpanManajemenKamar;
    private apk.makost.swing.Combobox cmbNamaPenghuni;
    private apk.makost.swing.Combobox cmbnoKamar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private apk.makost.swing.ShadowPanel shadowPanel1;
    private apk.makost.swing.Table tbl_manajemenKamar;
    private apk.makost.swing.TextField txtIdManajemenKamar;
    private apk.makost.swing.TextField txtJatuhTempo;
    private apk.makost.swing.TextField txtcari;
    // End of variables declaration//GEN-END:variables
}
