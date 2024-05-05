/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package apk.makost.form;

import apk.makost.koneksi.Koneksi;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;
import javax.swing.table.DefaultTableModel;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author dF
 */
public class RiwayatPembayaran extends javax.swing.JPanel {

    /**
     * Creates new form RiwayatPembayaran
     */
    DefaultTableModel model = new DefaultTableModel() {
        boolean[] canEdit = new boolean[]{
            false, false, false, false, false, false, false
        };

        public boolean isCellEditable(int rowIndex, int columnIndex) {
            return canEdit[columnIndex];
        }
    };

    public RiwayatPembayaran() {
        initComponents();

        tblRiwayat.setModel(model);
        model.addColumn("Tanggal");
        model.addColumn("No Transaksi");
        model.addColumn("Jumlah Pembayaran");
        model.addColumn("Total Harga");
        model.addColumn("Kembalian");
        model.addColumn("Tipe kamar");
        model.addColumn("Banyak Bayar");

        loadData();
        btnCetak.setEnabled(false);
        txtNoTransaksi.setEditable(false);
    }

    private void loadData() {
        model.getDataVector().removeAllElements();
        model.fireTableDataChanged();

        try {
            Connection c = Koneksi.configDB();
            Statement s = c.createStatement();

            String sql = "SELECT * FROM tbl_pembayaran";
            ResultSet r = s.executeQuery(sql);

            while (r.next()) {
                Object[] o = new Object[7];
                o[0] = r.getString("tanggal");
                o[1] = r.getString("no_transaksi");
                o[2] = r.getString("jumlah_pembayaran");
                o[3] = r.getString("total_harga");
                o[4] = r.getString("kembalian");
                o[5] = r.getString("tipeKamar");
                o[6] = r.getString("jumlahpertipeKamar");
                model.addRow(o);
            }
            r.close();
            s.close();
        } catch (Exception e) {
            System.out.println("terjadi kesalahan pada load data");
        }

    }

    private void cariData() {
        DefaultTableModel tabel = new DefaultTableModel() {
            boolean[] canEdit = new boolean[]{
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
        };

        String cari = txtCari.getText();

        tabel.addColumn("Tanggal");
        tabel.addColumn("No Transaksi");
        tabel.addColumn("Jumlah Bayar");
        tabel.addColumn("Total Harga");
        tabel.addColumn("Kembalian");
        tabel.addColumn("Tipe Kamar");
        tabel.addColumn("Banyak Bayar");

        try {
            Connection c = Koneksi.configDB();
            String sql = "SELECT * FROM tbl_pembayaran"
                    + " WHERE no_transaksi Like '%" + cari + "%'"
                    + " OR tipeKamar LIKE '%" + cari + "%'"
                    + " OR tanggal LIKE '%" + cari + "%'";

            Statement stat = c.createStatement();
            ResultSet rs = stat.executeQuery(sql);
            while (rs.next()) {
                tabel.addRow(new Object[]{
                    rs.getString(8),
                    rs.getString(1),
                    rs.getString(2),
                    rs.getString(3),
                    rs.getString(7),
                    rs.getString(4),
                    rs.getString(5)
                });
            }
            tblRiwayat.setModel(tabel);
            loadData();
        } catch (Exception e) {
            System.out.println("Cari Data Error");
        } finally {
        }
    }

    private void ctk2() {
        try {
            Connection c = Koneksi.configDB();
            Statement s = c.createStatement();
            String sql = "SELECT * FROM tbl_detailpembayaran "
                    + "WHERE no_transaksi = '" + txtNoTransaksi.getText() + "'";
            ResultSet r = s.executeQuery(sql);
            HashMap hash = new HashMap();
            hash.put("kode", txtNoTransaksi.getText());

            if (!r.next()) {
                String report2 = ("D:\\Users\\hp\\OneDrive\\Documents\\"
                    + "Penting\\Projek\\apk\\SIBDMK\\src\\apk\\makost\\"
                    + "report\\reportPembayaran.jrxml");
                JasperReport JRpt2 = JasperCompileManager.compileReport(report2);
                JasperPrint JPrint2 = JasperFillManager.fillReport(JRpt2, hash, c);
                JasperViewer.viewReport(JPrint2, false);
            } else {
                String report = ("D:\\Users\\hp\\OneDrive\\Documents\\Penting\\"
                    + "Projek\\apk\\SIBDMK\\src\\apk\\makost\\report\\"
                    + "reportDetailPembayaran.jrxml");
                JasperReport JRpt = JasperCompileManager.compileReport(report);
                JasperPrint JPrint = JasperFillManager.fillReport(JRpt, hash, c);
                JasperViewer.viewReport(JPrint, false);
            }
        } catch (Exception e) {
            System.out.println("Report Can't View because : " + e);
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

        shadowPanel2 = new apk.makost.swing.ShadowPanel();
        txtCari = new apk.makost.swing.TextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblRiwayat = new apk.makost.swing.Table();
        btnCetak = new apk.makost.swing.Button();
        txtNoTransaksi = new apk.makost.swing.TextField();

        setBackground(new java.awt.Color(236, 243, 255));
        setForeground(new java.awt.Color(186, 202, 228));

        txtCari.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        txtCari.setLabelText("Cari");
        txtCari.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCariActionPerformed(evt);
            }
        });
        txtCari.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCariKeyTyped(evt);
            }
        });

        tblRiwayat.setModel(new javax.swing.table.DefaultTableModel(
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
        tblRiwayat.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblRiwayatMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblRiwayat);

        btnCetak.setBackground(new java.awt.Color(0, 102, 102));
        btnCetak.setForeground(new java.awt.Color(255, 255, 255));
        btnCetak.setText("CETAK");
        btnCetak.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCetakActionPerformed(evt);
            }
        });

        txtNoTransaksi.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtNoTransaksi.setLabelText("No. Transaksi");
        txtNoTransaksi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNoTransaksiActionPerformed(evt);
            }
        });
        txtNoTransaksi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNoTransaksiKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout shadowPanel2Layout = new javax.swing.GroupLayout(shadowPanel2);
        shadowPanel2.setLayout(shadowPanel2Layout);
        shadowPanel2Layout.setHorizontalGroup(
            shadowPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(shadowPanel2Layout.createSequentialGroup()
                .addGroup(shadowPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(shadowPanel2Layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1006, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(shadowPanel2Layout.createSequentialGroup()
                        .addGap(57, 57, 57)
                        .addComponent(txtCari, javax.swing.GroupLayout.PREFERRED_SIZE, 236, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(277, 277, 277)
                        .addComponent(btnCetak, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtNoTransaksi, javax.swing.GroupLayout.PREFERRED_SIZE, 236, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(31, Short.MAX_VALUE))
        );
        shadowPanel2Layout.setVerticalGroup(
            shadowPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(shadowPanel2Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(shadowPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtCari, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnCetak, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtNoTransaksi, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 544, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(43, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(shadowPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(shadowPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void txtCariKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCariKeyTyped
        // TODO add your handling code here:
        cariData();
    }//GEN-LAST:event_txtCariKeyTyped

    private void txtCariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCariActionPerformed
        // TODO add your handling code here:
        cariData();
    }//GEN-LAST:event_txtCariActionPerformed

    private void tblRiwayatMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblRiwayatMouseClicked
        // TODO add your handling code here:
        btnCetak.setEnabled(true);

        int row = tblRiwayat.getSelectedRow();
        if (row == -1) {
            return;
        }

        String noTran = (String) model.getValueAt(row, 1);
        txtNoTransaksi.setText(noTran);
    }//GEN-LAST:event_tblRiwayatMouseClicked

    private void txtNoTransaksiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNoTransaksiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNoTransaksiActionPerformed

    private void txtNoTransaksiKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNoTransaksiKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNoTransaksiKeyTyped

    private void btnCetakActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCetakActionPerformed
        // TODO add your handling code here:
        int row = tblRiwayat.getSelectedRow();
        if (row == -1) {
            return;
        }
        ctk2();
    }//GEN-LAST:event_btnCetakActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private apk.makost.swing.Button btnCetak;
    private javax.swing.JScrollPane jScrollPane1;
    private apk.makost.swing.ShadowPanel shadowPanel2;
    private apk.makost.swing.Table tblRiwayat;
    private apk.makost.swing.TextField txtCari;
    private apk.makost.swing.TextField txtNoTransaksi;
    // End of variables declaration//GEN-END:variables
}
