/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package apk.makost.form;

import static apk.makost.koneksi.Koneksi.conn;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.RowFilter;
import javax.swing.table.TableRowSorter;


/**
 *
 * @author dF
 */

public class KapasitasKamar extends javax.swing.JPanel {
    
    
//    private Connection con;
//    private Statement st;
//    private ResultSet rsKamar;
//    private String sql = "";
    
//    private String no_kamar, harga_bulan, harga_harian, harga_tahun, lantai, kapasitas, fasilitas, status;
    
//    method tabel kamar
    private void tabel_tersedia(){
        DefaultTableModel data = new DefaultTableModel();
        data.addColumn("Id Kapasitas");
        data.addColumn("Jumlah Kapasitas");
        data.addColumn("Harga/Hari");
        data.addColumn("Harga/Minggu");
        data.addColumn("Harga/Bulan");
        
        String cari = txtcari.getText();
        try{
            String sql = "select * from tbl_kapasitas "
                    + "WHERE kapasitas LIKE '%"+cari+"%' order by kapasitas ASC";
            java.sql.Connection conn = (Connection)apk.makost.koneksi.Koneksi.configDB();
            java.sql.Statement stm = conn.createStatement();
            java.sql.ResultSet res = stm.executeQuery(sql);
            while(res.next())
                data.addRow(new Object[]{
//                    ikutin jumlah kolom tabel di database
                    res.getString(1), //id_kapasitas
                    res.getString(2), //kapasitas
                    res.getString(3), //harian
                    res.getString(4), //bulanan
                    res.getString(5), //tahunan
                });
                tbl_dataKapasitas.setModel(data);
            } catch (Exception e){
                JOptionPane.showMessageDialog(null, e);
        }
    }
 
    /**
     * Creates new form Kamar
     */
    
//    mengosongkan inputan yang telah diambil
    private void Clear(){
        txtHargaHarian.setText("");
        txtHargaBulanan.setText("");
        txtHargaMingguan.setText("");
        txtKapasitas.setText("");
        txtKdKapasitas.setText("");
        tabel_tersedia();
    }
    
    public KapasitasKamar() {
        initComponents();
        tabel_tersedia();
    }
    
    private void hapus(){
        try{
            String sql = "DELETE FROM tbl_kapasitas WHERE kd_kapasitas = '"+txtKdKapasitas.getText()+"'";
            java.sql.Connection con = (Connection)apk.makost.koneksi.Koneksi.configDB();
            Statement st = con.createStatement();
            int confirm = JOptionPane.showConfirmDialog(this,
                    "Apakah anda yakin ingin menghapus data kamar ini?",
                    "Konfirmasi Hapus",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE);
            if (confirm == JOptionPane.YES_OPTION){
                int row = st.executeUpdate(sql);
                if (row == 1){
                    JOptionPane.showMessageDialog(this, "Data berhasil dihapus!");
                    con.close();
                }
            }
        }catch (Exception e){
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
        tabel_tersedia();
    }
    
    private void simpanData(){
        try{
            String sql = "INSERT INTO tbl_kapasitas (kapasitas, harga_harian, harga_mingguan, harga_bulanan) values "
                    + "('"+txtKapasitas.getText()+"', '"+txtHargaHarian.getText()+"', '"+txtHargaMingguan.getText()+"', "
                    + "'"+txtHargaBulanan.getText()+"')";
            java.sql.Connection conn = (Connection)apk.makost.koneksi.Koneksi.configDB();
            java.sql.PreparedStatement pst = conn.prepareStatement(sql);
            pst.execute();
            JOptionPane.showMessageDialog(null, "Kapasitas Kamar Berhasil Disimpan");
            Clear();
        } catch (Exception e){
            JOptionPane.showMessageDialog(this, "Data belum terisi atau jumlah kapasitas telah tersedia");
        }
        tabel_tersedia();
    }
    
    private void editKamar(){
//        UPDATE `tbl_kapasitas` SET `kapasitas` = '5', `harga_harian` = '1', `harga_mingguan` = '2', `harga_bulanan` = '3' 
//          WHERE `tbl_kapasitas`.`kd_kapasitas` = 14;
        try{
            String sql = "UPDATE tbl_kapasitas SET kapasitas = '"+txtKapasitas.getText()+"',"
                    + "harga_harian = '"+txtHargaHarian.getText()+"',"
                    + "harga_mingguan = '"+txtHargaMingguan.getText()+"', harga_bulanan = '"+txtHargaBulanan.getText()+"' "
                    + "WHERE tbl_kapasitas.kd_kapasitas = '"+txtKdKapasitas.getText()+"'";
            java.sql.Connection conn = (Connection)apk.makost.koneksi.Koneksi.configDB();
            java.sql.PreparedStatement pst = conn.prepareStatement(sql);
            pst.execute();
            JOptionPane.showMessageDialog(null, "Data Berhasil Diubah");
        }catch (Exception e){
            JOptionPane.showMessageDialog(null, "Perubahan Data Gagal");
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
        txtHargaBulanan = new apk.makost.swing.TextField();
        txtcari = new apk.makost.swing.TextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbl_dataKapasitas = new apk.makost.swing.Table();
        btneditkamar = new apk.makost.swing.Button();
        txtHargaHarian = new apk.makost.swing.TextField();
        btnresetkamar = new apk.makost.swing.Button();
        btnsimpankamar = new apk.makost.swing.Button();
        btnhapuskamar = new apk.makost.swing.Button();
        txtHargaMingguan = new apk.makost.swing.TextField();
        txtKapasitas = new apk.makost.swing.TextField();
        txtKdKapasitas = new apk.makost.swing.TextField();

        setBackground(new java.awt.Color(236, 243, 255));
        setPreferredSize(new java.awt.Dimension(1069, 663));

        txtHargaBulanan.setLabelText("Harga/Bulan");
        txtHargaBulanan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtHargaBulananActionPerformed(evt);
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

        tbl_dataKapasitas.setForeground(new java.awt.Color(255, 255, 255));
        tbl_dataKapasitas.setModel(new javax.swing.table.DefaultTableModel(
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
        tbl_dataKapasitas.setGridColor(new java.awt.Color(14, 11, 164));
        tbl_dataKapasitas.setSelectionBackground(new java.awt.Color(0, 153, 255));
        tbl_dataKapasitas.setShowGrid(false);
        tbl_dataKapasitas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_dataKapasitasMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbl_dataKapasitas);

        btneditkamar.setBackground(new java.awt.Color(207, 211, 0));
        btneditkamar.setForeground(new java.awt.Color(255, 255, 255));
        btneditkamar.setText("EDIT");
        btneditkamar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btneditkamarActionPerformed(evt);
            }
        });

        txtHargaHarian.setLabelText("Harga/Hari");
        txtHargaHarian.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtHargaHarianActionPerformed(evt);
            }
        });

        btnresetkamar.setBackground(new java.awt.Color(126, 166, 195));
        btnresetkamar.setForeground(new java.awt.Color(255, 255, 255));
        btnresetkamar.setText("RESET");
        btnresetkamar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnresetkamarActionPerformed(evt);
            }
        });

        btnsimpankamar.setBackground(new java.awt.Color(126, 166, 195));
        btnsimpankamar.setForeground(new java.awt.Color(255, 255, 255));
        btnsimpankamar.setText("SIMPAN");
        btnsimpankamar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnsimpankamarActionPerformed(evt);
            }
        });

        btnhapuskamar.setBackground(new java.awt.Color(210, 70, 70));
        btnhapuskamar.setForeground(new java.awt.Color(255, 255, 255));
        btnhapuskamar.setText("HAPUS");
        btnhapuskamar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnhapuskamarActionPerformed(evt);
            }
        });

        txtHargaMingguan.setLabelText("Harga/Minggu");
        txtHargaMingguan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtHargaMingguanActionPerformed(evt);
            }
        });

        txtKapasitas.setLabelText("Kapasitas");
        txtKapasitas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtKapasitasActionPerformed(evt);
            }
        });

        txtKdKapasitas.setEditable(false);
        txtKdKapasitas.setLabelText("Id Kapasitas");
        txtKdKapasitas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtKdKapasitasActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout shadowPanel1Layout = new javax.swing.GroupLayout(shadowPanel1);
        shadowPanel1.setLayout(shadowPanel1Layout);
        shadowPanel1Layout.setHorizontalGroup(
            shadowPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, shadowPanel1Layout.createSequentialGroup()
                .addContainerGap(30, Short.MAX_VALUE)
                .addGroup(shadowPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(shadowPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(shadowPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(btnhapuskamar, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(shadowPanel1Layout.createSequentialGroup()
                                .addComponent(btnsimpankamar, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btneditkamar, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnresetkamar, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtcari, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1003, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, shadowPanel1Layout.createSequentialGroup()
                        .addGroup(shadowPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtKapasitas, javax.swing.GroupLayout.PREFERRED_SIZE, 233, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtHargaHarian, javax.swing.GroupLayout.PREFERRED_SIZE, 233, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(151, 151, 151)
                        .addGroup(shadowPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(shadowPanel1Layout.createSequentialGroup()
                                .addComponent(txtHargaBulanan, javax.swing.GroupLayout.PREFERRED_SIZE, 233, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(shadowPanel1Layout.createSequentialGroup()
                                .addComponent(txtHargaMingguan, javax.swing.GroupLayout.PREFERRED_SIZE, 233, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtKdKapasitas, javax.swing.GroupLayout.PREFERRED_SIZE, 233, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(36, 36, 36))
        );
        shadowPanel1Layout.setVerticalGroup(
            shadowPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(shadowPanel1Layout.createSequentialGroup()
                .addContainerGap(50, Short.MAX_VALUE)
                .addGroup(shadowPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtKapasitas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtHargaMingguan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtKdKapasitas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(shadowPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtHargaHarian, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtHargaBulanan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(32, 32, 32)
                .addGroup(shadowPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtcari, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnsimpankamar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btneditkamar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnresetkamar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 17, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 308, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnhapuskamar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(42, 42, 42))
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

    private void txtHargaBulananActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtHargaBulananActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtHargaBulananActionPerformed

    private void txtHargaHarianActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtHargaHarianActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtHargaHarianActionPerformed

    private void txtcariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtcariActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtcariActionPerformed

    private void btnsimpankamarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnsimpankamarActionPerformed
        // TODO add your handling code here:
//       INSERT INTO `tbl_kamar`(`no_kamar`, `lantai`, `kd_kapasitas`, `fasilitas`, `status`)
//       VALUES ('6', '1', '2 orang', 'asd', 'tersedia' )
        simpanData();
    }//GEN-LAST:event_btnsimpankamarActionPerformed

    private void btnresetkamarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnresetkamarActionPerformed
        // TODO add your handling code here:
        Clear();
    }//GEN-LAST:event_btnresetkamarActionPerformed

    private void btnhapuskamarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnhapuskamarActionPerformed
        // TODO add your handling code here:
        hapus();
    }//GEN-LAST:event_btnhapuskamarActionPerformed

    private void tbl_dataKapasitasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_dataKapasitasMouseClicked
        // TODO add your handling code here:
        int baris = tbl_dataKapasitas.rowAtPoint(evt.getPoint());
        String id_kapasitas = tbl_dataKapasitas.getValueAt(baris, 0).toString();
        txtKdKapasitas.setText(id_kapasitas);
        if(tbl_dataKapasitas.getValueAt(baris, 1)== null){
            txtKapasitas.setText("");
        }else{
            txtKapasitas.setText(tbl_dataKapasitas.getValueAt(baris, 1).toString());
        }
        if(tbl_dataKapasitas.getValueAt(baris, 2)== null){
            txtHargaHarian.setText("");
        }else{
            txtHargaHarian.setText(tbl_dataKapasitas.getValueAt(baris, 2).toString());
        }
        if(tbl_dataKapasitas.getValueAt(baris, 3) == null){
            txtHargaMingguan.setText("");
        }else{
            txtHargaMingguan.setText(tbl_dataKapasitas.getValueAt(baris, 3).toString());
        }
        if(tbl_dataKapasitas.getValueAt(baris, 4) == null){
            txtHargaBulanan.setText("");
        }else{
            txtHargaBulanan.setText(tbl_dataKapasitas.getValueAt(baris, 4).toString());
        }
    }//GEN-LAST:event_tbl_dataKapasitasMouseClicked

    private void btneditkamarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btneditkamarActionPerformed
        // TODO add your handling code here:
//        UPDATE tbl_kamar SET lantai = '2', kd_kapasitas = '2', fasilitas = 'meja' WHERE no_kamar = '1';
        editKamar();
    }//GEN-LAST:event_btneditkamarActionPerformed

    private void txtcariKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtcariKeyReleased
        // TODO add your handling code here:
        tabel_tersedia();
    }//GEN-LAST:event_txtcariKeyReleased

    private void txtHargaMingguanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtHargaMingguanActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtHargaMingguanActionPerformed

    private void txtKapasitasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtKapasitasActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtKapasitasActionPerformed

    private void txtKdKapasitasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtKdKapasitasActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtKdKapasitasActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private apk.makost.swing.Button btneditkamar;
    private apk.makost.swing.Button btnhapuskamar;
    private apk.makost.swing.Button btnresetkamar;
    private apk.makost.swing.Button btnsimpankamar;
    private javax.swing.JScrollPane jScrollPane1;
    private apk.makost.swing.ShadowPanel shadowPanel1;
    private apk.makost.swing.Table tbl_dataKapasitas;
    private apk.makost.swing.TextField txtHargaBulanan;
    private apk.makost.swing.TextField txtHargaHarian;
    private apk.makost.swing.TextField txtHargaMingguan;
    private apk.makost.swing.TextField txtKapasitas;
    private apk.makost.swing.TextField txtKdKapasitas;
    private apk.makost.swing.TextField txtcari;
    // End of variables declaration//GEN-END:variables
}
