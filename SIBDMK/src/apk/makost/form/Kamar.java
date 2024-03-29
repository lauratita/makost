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
//        data.addColumn("Fasilitas");
        data.addColumn("Harga/Hari");
        data.addColumn("Harga/Bulan");
        data.addColumn("Harga/Tahun");
        data.addColumn("Status");
        
//        SELECT tbl_kamar.no_kamar, tbl_kamar.lantai, tbl_kapasitas.kapasitas,"
//                    + "tbl_kapasitas.harga_harian, tbl_kapasitas.harga_bulanan, tbl_kapasitas.harga_tahnan,"
//                    + "FROM tbl_kamar INNER JOIN tbl_kapasitas ON tbl_kamar.kd_kapasitas = tbl_kapsitas.kd_kapasitas
        try{
            String sql = "select no_kamar, lantai, kapasitas,"
                    + "harga_harian, harga_bulanan, "
                    + "harga_tahunan, status from tbl_kamar INNER JOIN tbl_kapasitas ON tbl_kamar.kd_kapasitas = tbl_kapasitas.kd_kapasitas";
            java.sql.Connection conn = (Connection)apk.makost.koneksi.Koneksi.configDB();
            java.sql.Statement stm = conn.createStatement();
            java.sql.ResultSet res = stm.executeQuery(sql);
            while(res.next())
                data.addRow(new Object[]{
//                    ikutin jumlah kolom tabel di database
                    res.getString(1), //no_kamar
                    res.getString(2), //lantai
                    res.getString(3), //kapasitas
//                    res.getString(4), //fasilitas
                    res.getString(4), //harga/hari
                    res.getString(5), //harga/bulan
                    res.getString(6), //harga/tahun
                    res.getString(7) //status
                });
                tbl_dataKamar.setModel(data);
            } catch (Exception e){
                JOptionPane.showMessageDialog(null, e);
        }
    }
    
//    method no_kamar otomatis
    private void no_kamar(){
        int no_kamar = 0;
        int i = 0;
        try{
            String sql = "Select no_kamar from tbl_kamar";
            java.sql.Connection conn = (Connection)apk.makost.koneksi.Koneksi.configDB();
            java.sql.Statement stm = conn.createStatement();
            java.sql.ResultSet res = stm.executeQuery(sql);
            while(res.next()){
                no_kamar = res.getInt("no_kamar");
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

    
    public Kamar() {
        initComponents();
        tabel_tersedia();
        no_kamar();
        cmbkapasitas();
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
        btndetailkamar = new apk.makost.swing.Button();
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
        txtcari.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtcariActionPerformed(evt);
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
        jScrollPane1.setViewportView(tbl_dataKamar);

        btneditkamar.setBackground(new java.awt.Color(207, 211, 0));
        btneditkamar.setForeground(new java.awt.Color(255, 255, 255));
        btneditkamar.setText("EDIT");

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

        btnresetkamar.setBackground(new java.awt.Color(126, 166, 195));
        btnresetkamar.setForeground(new java.awt.Color(255, 255, 255));
        btnresetkamar.setText("RESET");

        btnsimpankamar.setBackground(new java.awt.Color(126, 166, 195));
        btnsimpankamar.setForeground(new java.awt.Color(255, 255, 255));
        btnsimpankamar.setText("SIMPAN");
        btnsimpankamar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnsimpankamarActionPerformed(evt);
            }
        });

        btndetailkamar.setBackground(new java.awt.Color(117, 149, 197));
        btndetailkamar.setForeground(new java.awt.Color(255, 255, 255));
        btndetailkamar.setText("DETAIL");

        btnhapuskamar.setBackground(new java.awt.Color(210, 70, 70));
        btnhapuskamar.setForeground(new java.awt.Color(255, 255, 255));
        btnhapuskamar.setText("HAPUS");

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
                        .addComponent(btnresetkamar, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnsimpankamar, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(548, 548, 548)
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
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 290, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, shadowPanel1Layout.createSequentialGroup()
                            .addComponent(btnhapuskamar, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(32, 32, 32)
                            .addComponent(btneditkamar, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(29, 29, 29)
                            .addComponent(btndetailkamar, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE))))
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 16, Short.MAX_VALUE)
                .addGroup(shadowPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtcari, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnresetkamar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnsimpankamar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(132, 132, 132)
                .addGroup(shadowPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btndetailkamar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btneditkamar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnhapuskamar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(41, 41, 41))
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
        try{
            String sql = "INSERT INTO tbl_kamar(no_kamar, lantai, kd_kapasitas, fasilitas, status) VALUES"
                    + "('"+ txtNoKamar.getText() +"', '"+ txtLantai.getText()+"', '" + cmbkapasitas.getSelectedItem()+"', '"+ txtfasilitas.getText() +"', '"+ "Tersedia"+"')";
            java.sql.Connection conn = (Connection)apk.makost.koneksi.Koneksi.configDB();
            java.sql.PreparedStatement pst = conn.prepareStatement(sql);
            pst.execute();
            JOptionPane.showMessageDialog(null, "Data Kamar Berhasil Disimpan");
            no_kamar();
        } catch (Exception e){
            JOptionPane.showMessageDialog(this, "Data Tidak Tersimpan");
        }
        tabel_tersedia();
    }//GEN-LAST:event_btnsimpankamarActionPerformed

    private void cmbkapasitasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbkapasitasActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbkapasitasActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private apk.makost.swing.Button btndetailkamar;
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
