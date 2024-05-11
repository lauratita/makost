/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package apk.makost.form;

import static apk.makost.koneksi.Koneksi.conn;
import java.awt.List;
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

public class Kamar extends javax.swing.JPanel {
    
    
//    private Connection con;
//    private Statement st;
//    private ResultSet rsKamar;
//    private String sql = "";
    
//    private String no_kamar, harga_bulan, harga_harian, harga_tahun, lantai, kapasitas, fasilitas, status;
    
//    method tabel kamar
    private void tabel_tersedia(){
        DefaultTableModel data = new DefaultTableModel();
        data.addColumn("No Kamar");
        data.addColumn("Lantai");
        data.addColumn("Kapasitas");
        data.addColumn("Fasilitas");
        data.addColumn("Harga/Hari");
        data.addColumn("Harga/Minggu");
        data.addColumn("Harga/Bulan");
        data.addColumn("Status");
        
//        SELECT tbl_kamar.no_kamar, tbl_kamar.lantai, tbl_kapasitas.kapasitas,"
//                    + "tbl_kapasitas.harga_harian, tbl_kapasitas.harga_bulanan, tbl_kapasitas.harga_tahnan,"
//                    + "FROM tbl_kamar INNER JOIN tbl_kapasitas ON tbl_kamar.kd_kapasitas = tbl_kapsitas.kd_kapasitas
        String cari = txtcari.getText();
        try{
            String sql = "select no_kamar, lantai, tbl_kamar.kapasitas, fasilitas,"
                    + "harga_harian, harga_mingguan, "
                    + "harga_bulanan, status from tbl_kamar INNER JOIN tbl_kapasitas ON tbl_kamar.kapasitas = tbl_kapasitas.kapasitas "
                    + "WHERE status LIKE '%"+cari+"%' or tbl_kamar.kapasitas LIKE '%"+cari+"%'"
                    + "ORDER BY no_kamar ASC";
            java.sql.Connection conn = (Connection)apk.makost.koneksi.Koneksi.configDB();
            java.sql.Statement stm = conn.createStatement();
            java.sql.ResultSet res = stm.executeQuery(sql);
            while(res.next())
                data.addRow(new Object[]{
//                    ikutin jumlah kolom tabel di database
                    res.getString(1), //no_kamar
                    res.getString(2), //lantai
                    res.getString(3), //kapasitas
                    res.getString(4), //fasilitas
                    res.getString(5), //harga/hari
                    res.getString(6), //harga/bulan
                    res.getString(7), //harga/tahun
                    res.getString(8) //status
                });
                tbl_dataKamar.setModel(data);
            } catch (Exception e){
                JOptionPane.showMessageDialog(null, e);
        }
    }
    
//    method no_kamar otomatis
    private void no_kamar(){
        int no_kamar = 1;
        int i = 1;
        try{
            String sql = "Select max(no_kamar) from tbl_kamar";
            java.sql.Connection conn = (Connection)apk.makost.koneksi.Koneksi.configDB();
            java.sql.Statement stm = conn.createStatement();
            java.sql.ResultSet res = stm.executeQuery(sql);
            while(res.next()){
                no_kamar = res.getInt(1);
                int n = no_kamar+1;
                txtNoKamar.setText(Integer.toString(n));
            }
        } catch (Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
 
    /**
     * Creates new form Kamar
     */
    
//    mendapatkan kapasitas
    private void cmbkapasitas(){
        try{
            String sql = "Select kapasitas from tbl_kapasitas";
            java.sql.Connection conn = (Connection)apk.makost.koneksi.Koneksi.configDB();
            java.sql.Statement stm = conn.createStatement();
            java.sql.ResultSet res = stm.executeQuery(sql);
            while(res.next()){
                cmbkapasitas.addItem(res.getString(1));
            }
        } catch (Exception e){
//            JOptionPane.showMessageDialog(shadowPanel1, e);
        }
    }

//    mengosongkan inputan yang telah diambil
    private void Clear(){
        txtNoKamar.enable();
        txtLantai.setText("");
        txtfasilitas.setText("");
        cmbkapasitas.setSelectedItem("1");
        tabel_tersedia();
    }
    
    public Kamar() {
        initComponents();
        tabel_tersedia();
        no_kamar();
        cmbkapasitas();
    }
    
    private void hapus(){
        try{
            String sql = "DELETE FROM tbl_kamar WHERE no_kamar = '"+txtNoKamar.getText()+"' "
                    + "AND kapasitas = '"+cmbkapasitas.getSelectedItem()+"'";
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
//        INSERT INTO tbl_kamar(no_kamar, lantai, kapasitas, fasilitas, status) VALUES ('7', '1', '5', 'asd', 'Tersedia');

        try{
            String sql = "INSERT INTO tbl_kamar(no_kamar, lantai, kapasitas, fasilitas, status) VALUES"
                    + "('"+ txtNoKamar.getText() +"', '"+ txtLantai.getText()+"', '" + cmbkapasitas.getSelectedItem()+"', "
                    + "'"+ txtfasilitas.getText() +"', '"+ "Tersedia"+"')";
            java.sql.Connection conn = (Connection)apk.makost.koneksi.Koneksi.configDB();
            java.sql.PreparedStatement pst = conn.prepareStatement(sql);
            pst.execute();
            JOptionPane.showMessageDialog(null, "Data Kamar Berhasil Disimpan");
            no_kamar();
            Clear();
        } catch (Exception e){
            JOptionPane.showMessageDialog(this, "Data belum terisi atau nomor kamar sudah tersedia");
        }
        tabel_tersedia();
    }
    
    private void editKamar(){
        
//      UPDATE `tbl_kamar` SET `fasilitas` = 'asd, pintu', `lantai` = '2', `kapasitas` = '3' WHERE `tbl_kamar`.`id_kamar` = 100;
        try{
            String sql = "UPDATE tbl_kamar SET lantai = '"+txtLantai.getText()+"', "
                    + "kapasitas = '"+cmbkapasitas.getSelectedItem()+"',"
                    + "fasilitas = '"+txtfasilitas.getText()+"' WHERE no_kamar = '"+txtNoKamar.getText()+"'";
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
        cmbkapasitas = new apk.makost.swing.Combobox();
        txtNoKamar = new apk.makost.swing.TextField();
        txtcari = new apk.makost.swing.TextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbl_dataKamar = new apk.makost.swing.Table();
        btneditkamar = new apk.makost.swing.Button();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtfasilitas = new javax.swing.JTextArea();
        jLabel33 = new javax.swing.JLabel();
        txtLantai = new apk.makost.swing.TextField();
        btnresetkamar = new apk.makost.swing.Button();
        btnsimpankamar = new apk.makost.swing.Button();
        btnhapuskamar = new apk.makost.swing.Button();
        jLabel1 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(236, 243, 255));
        setPreferredSize(new java.awt.Dimension(1069, 663));

        cmbkapasitas.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        cmbkapasitas.setLabeText("");
        cmbkapasitas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbkapasitasActionPerformed(evt);
            }
        });

        txtNoKamar.setLabelText("No Kamar");
        txtNoKamar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNoKamarActionPerformed(evt);
            }
        });

        txtcari.setLabelText("Cari");
        txtcari.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtcariMouseClicked(evt);
            }
        });
        txtcari.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtcariActionPerformed(evt);
            }
        });
        txtcari.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtcariKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtcariKeyTyped(evt);
            }
        });

        tbl_dataKamar.setForeground(new java.awt.Color(255, 255, 255));
        tbl_dataKamar.setModel(new javax.swing.table.DefaultTableModel(
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
        tbl_dataKamar.setGridColor(new java.awt.Color(14, 11, 164));
        tbl_dataKamar.setSelectionBackground(new java.awt.Color(0, 153, 255));
        tbl_dataKamar.setShowGrid(false);
        tbl_dataKamar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_dataKamarMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbl_dataKamar);

        btneditkamar.setBackground(new java.awt.Color(252, 210, 6));
        btneditkamar.setForeground(new java.awt.Color(255, 255, 255));
        btneditkamar.setText("UBAH");
        btneditkamar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btneditkamarActionPerformed(evt);
            }
        });

        jScrollPane2.setBackground(new java.awt.Color(255, 255, 255));

        txtfasilitas.setColumns(20);
        txtfasilitas.setRows(5);
        txtfasilitas.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        txtfasilitas.setSelectedTextColor(new java.awt.Color(0, 0, 0));
        jScrollPane2.setViewportView(txtfasilitas);

        jLabel33.setForeground(new java.awt.Color(153, 153, 153));
        jLabel33.setText("Fasilitas");

        txtLantai.setLabelText("Lantai");
        txtLantai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtLantaiActionPerformed(evt);
            }
        });

        btnresetkamar.setBackground(new java.awt.Color(76, 175, 80));
        btnresetkamar.setForeground(new java.awt.Color(255, 255, 255));
        btnresetkamar.setText("RESET");
        btnresetkamar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnresetkamarActionPerformed(evt);
            }
        });

        btnsimpankamar.setBackground(new java.awt.Color(33, 150, 243));
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

        jLabel1.setForeground(new java.awt.Color(153, 153, 153));
        jLabel1.setText("Kapasitas");

        javax.swing.GroupLayout shadowPanel1Layout = new javax.swing.GroupLayout(shadowPanel1);
        shadowPanel1.setLayout(shadowPanel1Layout);
        shadowPanel1Layout.setHorizontalGroup(
            shadowPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, shadowPanel1Layout.createSequentialGroup()
                .addContainerGap(30, Short.MAX_VALUE)
                .addGroup(shadowPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(shadowPanel1Layout.createSequentialGroup()
                        .addComponent(btnsimpankamar, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btneditkamar, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnresetkamar, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnhapuskamar, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(390, 390, 390)
                        .addComponent(txtcari, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(shadowPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 1003, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(shadowPanel1Layout.createSequentialGroup()
                            .addGroup(shadowPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(txtNoKamar, javax.swing.GroupLayout.DEFAULT_SIZE, 233, Short.MAX_VALUE)
                                .addComponent(txtLantai, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGap(98, 98, 98)
                            .addGroup(shadowPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(cmbkapasitas, javax.swing.GroupLayout.PREFERRED_SIZE, 233, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel1))
                            .addGap(93, 93, 93)
                            .addGroup(shadowPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel33, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 290, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(36, 36, 36))
        );
        shadowPanel1Layout.setVerticalGroup(
            shadowPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(shadowPanel1Layout.createSequentialGroup()
                .addGroup(shadowPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(shadowPanel1Layout.createSequentialGroup()
                        .addGap(55, 55, 55)
                        .addComponent(txtNoKamar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtLantai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(shadowPanel1Layout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addGroup(shadowPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel33)
                            .addComponent(jLabel1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(shadowPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cmbkapasitas, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 14, Short.MAX_VALUE)
                .addGroup(shadowPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtcari, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnresetkamar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnsimpankamar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btneditkamar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnhapuskamar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(14, 14, 14)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 342, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(67, 67, 67))
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

    private void txtNoKamarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNoKamarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNoKamarActionPerformed

    private void txtLantaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtLantaiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtLantaiActionPerformed

    private void txtcariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtcariActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtcariActionPerformed

    private void btnsimpankamarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnsimpankamarActionPerformed
        // TODO add your handling code here:
//       INSERT INTO `tbl_kamar`(`no_kamar`, `lantai`, `kd_kapasitas`, `fasilitas`, `status`)
//       VALUES ('6', '1', '2 orang', 'asd', 'tersedia' )
        simpanData();
    }//GEN-LAST:event_btnsimpankamarActionPerformed

    private void cmbkapasitasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbkapasitasActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbkapasitasActionPerformed

    private void btnresetkamarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnresetkamarActionPerformed
        // TODO add your handling code here:
        Clear();
        no_kamar();
    }//GEN-LAST:event_btnresetkamarActionPerformed

    private void btnhapuskamarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnhapuskamarActionPerformed
        // TODO add your handling code here:
        hapus();
    }//GEN-LAST:event_btnhapuskamarActionPerformed

    private void tbl_dataKamarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_dataKamarMouseClicked
        // TODO add your handling code here:
        int baris = tbl_dataKamar.rowAtPoint(evt.getPoint());
        String no_kamar = tbl_dataKamar.getValueAt(baris, 0).toString();
        txtNoKamar.setText(no_kamar);
        txtNoKamar.disable();
        if(tbl_dataKamar.getValueAt(baris, 1)== null){
            txtLantai.setText("");
        }else{
            txtLantai.setText(tbl_dataKamar.getValueAt(baris, 1).toString());
        }
        if(tbl_dataKamar.getValueAt(baris, 2) == null){
            cmbkapasitas.setSelectedItem(this);
        }else{
            cmbkapasitas.setSelectedItem(tbl_dataKamar.getValueAt(baris, 2).toString());
        }
        if(tbl_dataKamar.getValueAt(baris, 3) == null){
            txtfasilitas.setText("");
        }else{
            txtfasilitas.setText(tbl_dataKamar.getValueAt(baris, 3).toString());
        }
    }//GEN-LAST:event_tbl_dataKamarMouseClicked

    private void btneditkamarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btneditkamarActionPerformed
        // TODO add your handling code here:
//        UPDATE tbl_kamar SET lantai = '2', kd_kapasitas = '2', fasilitas = 'meja' WHERE no_kamar = '1';
        editKamar();
    }//GEN-LAST:event_btneditkamarActionPerformed

    private void txtcariKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtcariKeyReleased
        // TODO add your handling code here:
        tabel_tersedia();
    }//GEN-LAST:event_txtcariKeyReleased

    private void txtcariMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtcariMouseClicked
        // TODO add your handling code here:
       
    }//GEN-LAST:event_txtcariMouseClicked

    private void txtcariKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtcariKeyTyped
        // TODO add your handling code here:
        
    }//GEN-LAST:event_txtcariKeyTyped


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private apk.makost.swing.Button btneditkamar;
    private apk.makost.swing.Button btnhapuskamar;
    private apk.makost.swing.Button btnresetkamar;
    private apk.makost.swing.Button btnsimpankamar;
    private apk.makost.swing.Combobox cmbkapasitas;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private apk.makost.swing.ShadowPanel shadowPanel1;
    private apk.makost.swing.Table tbl_dataKamar;
    private apk.makost.swing.TextField txtLantai;
    private apk.makost.swing.TextField txtNoKamar;
    private apk.makost.swing.TextField txtcari;
    private javax.swing.JTextArea txtfasilitas;
    // End of variables declaration//GEN-END:variables

    
}
