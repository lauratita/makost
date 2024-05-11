package apk.makost.form;

import apk.makost.event.MenuEvent;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import java.sql.*;
import javax.swing.JOptionPane;
import com.mysql.cj.jdbc.StatementImpl;
import com.toedter.calendar.demo.DateChooserPanel;
import java.text.SimpleDateFormat;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.util.Locale;
import javax.swing.Timer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import static apk.makost.koneksi.Koneksi.conn;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Date;

/**
 *
 * @author Melvina
 */
public class RiwayatPenghuni extends javax.swing.JPanel {

    public RiwayatPenghuni() {
        initComponents();
        datatable();
        load_riwayatpenghuni();
    }
    
    private void load_riwayatpenghuni(){            
        }

    RiwayatPenghuni(String move_to_RiwayatPenghuni) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

//  membuat koneksi dengan database    
    public void Koneksi() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/db_kos", "root", "");
            Statement stat = (Statement) con.createStatement();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    private void datatable() {
        DefaultTableModel tbl = new DefaultTableModel();
        tblRiwayat.setModel(tbl);
        tbl.addColumn("ID Penghuni");
        tbl.addColumn("Nama");
        tbl.addColumn("NIK");
        tbl.addColumn("Alamat");
        tbl.addColumn("No Handphone");
        tbl.addColumn("No Handphone Orang Tua");
        tbl.addColumn("Tanggal Lahir");
        tbl.addColumn("Registrasi");
        tbl.addColumn("Waktu");

        try {
            Statement st = (Statement) apk.makost.koneksi.Koneksi.configDB().createStatement();
            ResultSet res = st.executeQuery("SELECT * FROM history_penghuni");
            while (res.next()) {
                Object[] o = new Object[9];
                o[0] = res.getString("id_penghuni");
                o[1] = res.getString("nama");
                o[2] = res.getString("nik");
                o[3] = res.getString("alamat");
                o[4] = res.getString("noHP");
                o[5] = res.getString("noHP_ortu");
                o[6] = res.getString("tanggal_lahir");
                o[7] = res.getString("registrasi");
                o[8] = res.getString("waktu");
                tbl.addRow(o);
            }
        } catch (Exception e) {
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
        jScrollPane1 = new javax.swing.JScrollPane();
        tblRiwayat = new apk.makost.swing.Table();
        jLabel1 = new javax.swing.JLabel();
        txtCariRiwayat = new apk.makost.swing.TextField();
        btnKembaliPenghuni = new apk.makost.swing.Button();

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
        jScrollPane1.setViewportView(tblRiwayat);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setText("Riwayat Penghuni");

        txtCariRiwayat.setLabelText("Cari");
        txtCariRiwayat.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCariRiwayatKeyReleased(evt);
            }
        });

        btnKembaliPenghuni.setBackground(new java.awt.Color(126, 166, 195));
        btnKembaliPenghuni.setForeground(new java.awt.Color(255, 255, 255));
        btnKembaliPenghuni.setText("KEMBALI");
        btnKembaliPenghuni.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnKembaliPenghuniMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout shadowPanel1Layout = new javax.swing.GroupLayout(shadowPanel1);
        shadowPanel1.setLayout(shadowPanel1Layout);
        shadowPanel1Layout.setHorizontalGroup(
            shadowPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, shadowPanel1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(btnKembaliPenghuni, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(85, 85, 85))
            .addGroup(shadowPanel1Layout.createSequentialGroup()
                .addGroup(shadowPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(shadowPanel1Layout.createSequentialGroup()
                        .addGap(57, 57, 57)
                        .addGroup(shadowPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 950, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(shadowPanel1Layout.createSequentialGroup()
                        .addGap(110, 110, 110)
                        .addComponent(txtCariRiwayat, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(62, Short.MAX_VALUE))
        );
        shadowPanel1Layout.setVerticalGroup(
            shadowPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(shadowPanel1Layout.createSequentialGroup()
                .addContainerGap(59, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(48, 48, 48)
                .addComponent(txtCariRiwayat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(45, 45, 45)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33)
                .addComponent(btnKembaliPenghuni, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(59, 59, 59))
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

    private void txtCariRiwayatKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCariRiwayatKeyReleased
        DefaultTableModel ob = (DefaultTableModel) tblRiwayat.getModel();
        TableRowSorter<DefaultTableModel> obj = new TableRowSorter<>(ob);
        tblRiwayat.setRowSorter(obj);

        // mengubah teks pencarian menjadi huruf kecil
        String keyword = txtCariRiwayat.getText().toLowerCase();
        // (?i) digunakan untuk membuat pencarian menjadi tidak case-sensitive
        RowFilter<DefaultTableModel, Object> filter = RowFilter.regexFilter("(?i)" + keyword);
        obj.setRowFilter(filter);
    }//GEN-LAST:event_txtCariRiwayatKeyReleased

    private void btnKembaliPenghuniMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnKembaliPenghuniMouseClicked
            
            
    }//GEN-LAST:event_btnKembaliPenghuniMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private apk.makost.swing.Button btnKembaliPenghuni;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private apk.makost.swing.ShadowPanel shadowPanel1;
    private apk.makost.swing.Table tblRiwayat;
    private apk.makost.swing.TextField txtCariRiwayat;
    // End of variables declaration//GEN-END:variables

    void addActionListener(ActionListener actionListener) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
