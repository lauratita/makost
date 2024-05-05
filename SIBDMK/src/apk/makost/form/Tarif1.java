/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package apk.makost.form;

/**
 *
 * @author dF
 */
import apk.makost.koneksi.Koneksi;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class Tarif1 extends javax.swing.JPanel {

    private DefaultTableModel model;

    public Tarif1() {
        initComponents();

        model = new DefaultTableModel();
        tblTarif.setModel(model);
        model.addColumn("ID Tarif");
        model.addColumn("Nama Tarif");
        model.addColumn("Harga");
        model.addColumn("Keterangan");

        loadData();
        autoNumber();

        btnEdit.setEnabled(false);
        btnHapus.setEnabled(false);
    }
    
    public void clear(){
        txtIdTarif.setText("");
        txtNamaTarif.setText("");
        txtHargaTarif.setText("");
        txtKeterangan.setText("");
    }

    private void autoNumber() {
        try {
            Connection c = Koneksi.configDB();
            Statement s = c.createStatement();
            String sql = "SELECT * FROM tbl_tarif_tambahan ORDER BY id_tarif DESC";
            ResultSet r = s.executeQuery(sql);
            if (r.next()) {
                String idTarif = r.getString("id_tarif").substring(2);
                String TR = "" + (Integer.parseInt(idTarif) + 1);
                String Nol = "";

                if (TR.length() == 1) {
                    Nol = "00";
                } else if (TR.length() == 2) {
                    Nol = "0";
                } else if (TR.length() == 3) {
                    Nol = "";
                }

                txtIdTarif.setText("TR" + Nol + TR);
            } else {
                txtIdTarif.setText("TR001");
            }
            r.close();
            s.close();
        } catch (Exception e) {
            System.out.println("autonumber error");
        }
        txtIdTarif.setEditable(false);
    }

    public void loadData() {
        model.getDataVector().removeAllElements();

        model.fireTableDataChanged();

        try {
            Connection c = Koneksi.configDB();
            Statement s = c.createStatement();

            String sql = "SELECT * FROM tbl_tarif_tambahan";
            ResultSet r = s.executeQuery(sql);

            while (r.next()) {
                Object[] o = new Object[7];
                o[0] = r.getString("id_tarif");
                o[1] = r.getString("nama_tarif");
                o[2] = r.getString("harga");
                o[3] = r.getString("keterangan");

                model.addRow(o);
            }
            r.close();
            s.close();
        } catch (Exception e) {
            System.out.println("terjadi kesalahan pada load data");
        }
    }

    public void cariData() {
        DefaultTableModel tabel = new DefaultTableModel();

        tabel.addColumn("ID Tarif");
        tabel.addColumn("Nama Tarif");
        tabel.addColumn("Harga");
        tabel.addColumn("Keterangan");

        try {
            Connection c = Koneksi.configDB();
            String sql = "Select * from tbl_tarif_tambahan where id_tarif like '%" + txtCari.getText() + "%'"
                    + "or nama_tarif like '%" + txtCari.getText() + "%'";
            Statement stat = c.createStatement();
            ResultSet rs = stat.executeQuery(sql);
            while (rs.next()) {
                tabel.addRow(new Object[]{
                    rs.getString(1),
                    rs.getString(2),
                    rs.getString(3),
                    rs.getString(4)
                });
            }
            tblTarif.setModel(tabel);
            loadData();
        } catch (Exception e) {
            System.out.println("Cari Data Error");
        } finally {
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

        shadowPanel1 = new apk.makost.swing.ShadowPanel();
        txtIdTarif = new apk.makost.swing.TextField();
        txtNamaTarif = new apk.makost.swing.TextField();
        txtHargaTarif = new apk.makost.swing.TextField();
        txtKeterangan = new apk.makost.swing.TextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblTarif = new apk.makost.swing.Table();
        btnHapus = new apk.makost.swing.Button();
        btnSimpan = new apk.makost.swing.Button();
        btnEdit = new apk.makost.swing.Button();
        txtCari = new apk.makost.swing.TextField();
        jLabel1 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(236, 243, 255));

        txtIdTarif.setLabelText("ID. Tarif");
        txtIdTarif.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtIdTarifActionPerformed(evt);
            }
        });

        txtNamaTarif.setLabelText("Nama Tarif");

        txtHargaTarif.setLabelText("Harga");

        txtKeterangan.setLabelText("Keterangan");

        tblTarif.setModel(new javax.swing.table.DefaultTableModel(
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
        tblTarif.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblTarifMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblTarif);

        btnHapus.setBackground(new java.awt.Color(204, 0, 0));
        btnHapus.setForeground(new java.awt.Color(255, 255, 255));
        btnHapus.setText("HAPUS");
        btnHapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHapusActionPerformed(evt);
            }
        });

        btnSimpan.setBackground(new java.awt.Color(0, 204, 0));
        btnSimpan.setForeground(new java.awt.Color(255, 255, 255));
        btnSimpan.setText("SIMPAN");
        btnSimpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSimpanActionPerformed(evt);
            }
        });

        btnEdit.setBackground(new java.awt.Color(0, 0, 153));
        btnEdit.setForeground(new java.awt.Color(255, 255, 255));
        btnEdit.setText("EDIT");
        btnEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditActionPerformed(evt);
            }
        });

        txtCari.setLabelText("Cari Data");
        txtCari.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCariActionPerformed(evt);
            }
        });
        txtCari.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtCariKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCariKeyTyped(evt);
            }
        });

        jLabel1.setText("Rp.");

        javax.swing.GroupLayout shadowPanel1Layout = new javax.swing.GroupLayout(shadowPanel1);
        shadowPanel1.setLayout(shadowPanel1Layout);
        shadowPanel1Layout.setHorizontalGroup(
            shadowPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(shadowPanel1Layout.createSequentialGroup()
                .addGroup(shadowPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(shadowPanel1Layout.createSequentialGroup()
                        .addContainerGap(29, Short.MAX_VALUE)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1010, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(shadowPanel1Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(shadowPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(shadowPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(txtIdTarif, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtNamaTarif, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtHargaTarif, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(txtKeterangan, javax.swing.GroupLayout.PREFERRED_SIZE, 222, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtCari, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnSimpan, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnHapus, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12)))
                .addContainerGap(30, Short.MAX_VALUE))
        );
        shadowPanel1Layout.setVerticalGroup(
            shadowPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(shadowPanel1Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(txtIdTarif, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtNamaTarif, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(shadowPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtHargaTarif, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(shadowPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(shadowPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnHapus, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnSimpan, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(shadowPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtKeterangan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtCari, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(6, 6, 6)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 409, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(24, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(shadowPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(shadowPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void txtIdTarifActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtIdTarifActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtIdTarifActionPerformed

    private void txtCariKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCariKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCariKeyPressed

    private void txtCariKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCariKeyTyped
        // TODO add your handling code here:
        cariData();
    }//GEN-LAST:event_txtCariKeyTyped

    private void txtCariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCariActionPerformed
        // TODO add your handling code here:
        cariData();
    }//GEN-LAST:event_txtCariActionPerformed

    private void tblTarifMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblTarifMouseClicked
        // TODO add your handling code here:
        btnEdit.setEnabled(true);
        btnHapus.setEnabled(true);
        btnSimpan.setEnabled(false);

        int i = tblTarif.getSelectedRow();
        if (i == -1) {
            return;
        }

        String id = (String) model.getValueAt(i, 0);
        String nama = (String) model.getValueAt(i, 1);
        String harga = (String) model.getValueAt(i, 2);
        String keterangan = (String) model.getValueAt(i, 3);

        txtIdTarif.setText(id);
        txtNamaTarif.setText(nama);
        txtHargaTarif.setText(harga);
        txtKeterangan.setText(keterangan);
    }//GEN-LAST:event_tblTarifMouseClicked

    private void btnEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditActionPerformed
        // TODO add your handling code here:
        int i = tblTarif.getSelectedRow();
        if (i == -1) {
            return;
        }
        String id = (String) model.getValueAt(i, 0);
        String nama = txtNamaTarif.getText();
        String harga = txtHargaTarif.getText();
        String keterangan = txtKeterangan.getText();

        try {
            Connection c = Koneksi.configDB();
            String sql = "UPDATE tbl_tarif_tambahan SET nama_tarif = ?, harga = ?, keterangan = ? WHERE id_tarif = ?";
            PreparedStatement p = c.prepareStatement(sql);
            p.setString(1, nama);
            p.setString(2, harga);
            p.setString(3, keterangan);
            p.setString(4, id);

            p.executeUpdate();
            p.close();
            JOptionPane.showMessageDialog(null, "Data Terubah");
            btnSimpan.setEnabled(true);
            btnEdit.setEnabled(false);
            btnHapus.setEnabled(false);
            clear();
        } catch (Exception e) {
            System.out.println("update error");
        } finally {
            loadData();
            autoNumber();
        }
    }//GEN-LAST:event_btnEditActionPerformed

    private void btnHapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHapusActionPerformed
        // TODO add your handling code here:
        int i = tblTarif.getSelectedRow();
        if (i == -1) {
            return;
        }
        String id = (String) model.getValueAt(i, 0);
        int pernyataan = JOptionPane.showConfirmDialog(null, "Yakin Data Akan Dihapus", "Konfirmasi", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (pernyataan == JOptionPane.OK_OPTION) {
            try {
                Connection c = Koneksi.configDB();
                String sql = "DELETE FROM tbl_tarif_tambahan WHERE id_tarif = ?";
                PreparedStatement p = c.prepareStatement(sql);
                p.setString(1, id);
                p.executeUpdate();
                p.close();
                JOptionPane.showMessageDialog(null, "Data Terhapus");
            } catch (Exception e) {
                System.out.println("Terjadi Kesalahan pada btn hapus");
            } finally {
                btnSimpan.setEnabled(true);
                btnEdit.setEnabled(false);
                btnHapus.setEnabled(false);
                loadData();
                autoNumber();
                clear();
            }
        }
        if (pernyataan == JOptionPane.CANCEL_OPTION) {

        }
    }//GEN-LAST:event_btnHapusActionPerformed

    private void btnSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSimpanActionPerformed
        // TODO add your handling code here:
        String id = txtIdTarif.getText();
        String nama = txtNamaTarif.getText();
        String harga = txtHargaTarif.getText();
        String keterangan = txtKeterangan.getText();

        try {
            Connection c = Koneksi.configDB();
            String sql = "INSERT INTO tbl_tarif_tambahan VALUES ('"+id+"', '"+nama+"', '"+harga+"', '"+keterangan+"')";
            PreparedStatement p = c.prepareStatement(sql);
            p.executeUpdate();
            p.close();
            JOptionPane.showMessageDialog(null, "Data Tersimpan");
            loadData();
        } catch (Exception e) {
            System.out.println("Terjadi Kesalahan pada btn simpan");
        }finally{
            autoNumber();
            clear();
        }
    }//GEN-LAST:event_btnSimpanActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private apk.makost.swing.Button btnEdit;
    private apk.makost.swing.Button btnHapus;
    private apk.makost.swing.Button btnSimpan;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private apk.makost.swing.ShadowPanel shadowPanel1;
    private apk.makost.swing.Table tblTarif;
    private apk.makost.swing.TextField txtCari;
    private apk.makost.swing.TextField txtHargaTarif;
    private apk.makost.swing.TextField txtIdTarif;
    private apk.makost.swing.TextField txtKeterangan;
    private apk.makost.swing.TextField txtNamaTarif;
    // End of variables declaration//GEN-END:variables
}
