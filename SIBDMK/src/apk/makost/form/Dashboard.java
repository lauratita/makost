/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package apk.makost.form;

import apk.makost.koneksi.Koneksi;
import apk.makost.model.ModelCard;
import apk.makost.model.ModelChart;
import java.awt.Color;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.ImageIcon;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author dF
 */
public class Dashboard extends javax.swing.JPanel {

    DefaultTableModel model = new DefaultTableModel() {
        boolean[] canEdit = new boolean[]{
            false, false, false, false
        };

        public boolean isCellEditable(int rowIndex, int columnIndex) {
            return canEdit[columnIndex];
        }
    };
    Date d = new Date();
    

    public Dashboard() {
        initComponents();
        table.fixTable(jScrollPane1);
        chart.clear();
        chart.addLegend("Pengeluaran", (new Color(242, 140, 251)));
        chart.addLegend("Pendapatan", (new Color(140, 184, 251)));
        chart.addLegend("Laba", (new Color(250, 179, 156)));
        chart.addData(new ModelChart("Januari", new double[]{800, 300, 500}));
        chart.addData(new ModelChart("Maret", new double[]{600, 200, 400}));
        chart.addData(new ModelChart("Mei", new double[]{300, 300, 0}));
        chart.addData(new ModelChart("Juli", new double[]{500, 100, 400}));
        chart.addData(new ModelChart("September", new double[]{800, 100, 700}));
        chart.addData(new ModelChart("Oktober", new double[]{200, 100, 100}));
        chart.addData(new ModelChart("Desember", new double[]{600, 400, 200}));
        card1.setData(new ModelCard(new ImageIcon(getClass().getResource("/apk/makost/icon/PenghuniCard.png")), "Penghuni", "10", "Penghuni Yang Menyewa"));
        card2.setData(new ModelCard(new ImageIcon(getClass().getResource("/apk/makost/icon/KamarCard.png")), "Kamar", "5", "Total Kamar Kost"));
        card3.setData(new ModelCard(new ImageIcon(getClass().getResource("/apk/makost/icon/LabaCard.png")), "Total Laba", "Rp.5.500.000", "Jumlah Per Tahun"));
        chart.start();
        //tambahkan data table
        table.setModel(model);
        model.addColumn("Tanggal");
        model.addColumn("Penghuni");
        model.addColumn("Total");
        model.addColumn("Status");
        loadData();
    }

    private void loadData() {
        model.getDataVector().removeAllElements();
        model.fireTableDataChanged();
        SimpleDateFormat SDF = new SimpleDateFormat("MM");
        String date = SDF.format(d);

        try {
            Connection c = Koneksi.configDB();
            Statement s = c.createStatement();

            String sql = "SELECT * FROM tbl_pembayaran "
                    + "JOIN tbl_manajemen_kamar ON tbl_pembayaran.id_manajemen = tbl_manajemen_kamar.id_manajemen "
                    + "JOIN tbl_penghuni ON tbl_manajemen_kamar.id_penghuni = tbl_penghuni.id_penghuni "
                    + "WHERE MONTH(tanggal) = "+date+"";
            ResultSet r = s.executeQuery(sql);

            while (r.next()) {
                Object[] o = new Object[4];
                o[0] = r.getString("tanggal");
                o[1] = r.getString("nama_depan");
                o[2] = r.getString("total_harga");
                o[3] = r.getString("tipeKamar");
                model.addRow(o);
            }
            r.close();
            s.close();
        } catch (Exception e) {
            System.out.println("terjadi kesalahan pada load data");
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

        panel = new javax.swing.JLayeredPane();
        card1 = new apk.makost.component.Card();
        card2 = new apk.makost.component.Card();
        card3 = new apk.makost.component.Card();
        shadowPanel3 = new apk.makost.swing.ShadowPanel();
        chart = new apk.makost.chart.Chart();
        jLabel1 = new javax.swing.JLabel();
        shadowPanel4 = new apk.makost.swing.ShadowPanel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        table = new apk.makost.swing.Table();

        setBackground(new java.awt.Color(236, 243, 255));
        setForeground(new java.awt.Color(243, 246, 251));

        panel.setLayout(new java.awt.GridLayout(1, 0, 20, 0));

        card1.setColor1(new java.awt.Color(242, 140, 251));
        card1.setColor2(new java.awt.Color(242, 140, 251));
        panel.add(card1);

        card2.setColor1(new java.awt.Color(140, 184, 251));
        card2.setColor2(new java.awt.Color(140, 184, 251));
        panel.add(card2);

        card3.setColor1(new java.awt.Color(250, 179, 156));
        card3.setColor2(new java.awt.Color(250, 179, 156));
        panel.add(card3);

        jLabel1.setBackground(new java.awt.Color(37, 37, 37));
        jLabel1.setFont(new java.awt.Font("SansSerif", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(102, 102, 102));
        jLabel1.setText("Grafik Laporan Bulanan");

        javax.swing.GroupLayout shadowPanel3Layout = new javax.swing.GroupLayout(shadowPanel3);
        shadowPanel3.setLayout(shadowPanel3Layout);
        shadowPanel3Layout.setHorizontalGroup(
            shadowPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(shadowPanel3Layout.createSequentialGroup()
                .addGroup(shadowPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(shadowPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(chart, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(shadowPanel3Layout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addComponent(jLabel1)))
                .addContainerGap(20, Short.MAX_VALUE))
        );
        shadowPanel3Layout.setVerticalGroup(
            shadowPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, shadowPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(chart, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(22, 22, 22))
        );

        jLabel2.setBackground(new java.awt.Color(37, 37, 37));
        jLabel2.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(153, 153, 153));
        jLabel2.setText("Riwayat Pembayaran Bulan ini");

        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Tanggal", "Penghuni", "Total", "Status"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(table);

        javax.swing.GroupLayout shadowPanel4Layout = new javax.swing.GroupLayout(shadowPanel4);
        shadowPanel4.setLayout(shadowPanel4Layout);
        shadowPanel4Layout.setHorizontalGroup(
            shadowPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(shadowPanel4Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(shadowPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 318, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addContainerGap(36, Short.MAX_VALUE))
        );
        shadowPanel4Layout.setVerticalGroup(
            shadowPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(shadowPanel4Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 383, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(20, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(panel, javax.swing.GroupLayout.PREFERRED_SIZE, 973, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(shadowPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(shadowPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(panel, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(shadowPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(shadowPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private apk.makost.component.Card card1;
    private apk.makost.component.Card card2;
    private apk.makost.component.Card card3;
    private apk.makost.chart.Chart chart;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLayeredPane panel;
    private apk.makost.swing.ShadowPanel shadowPanel3;
    private apk.makost.swing.ShadowPanel shadowPanel4;
    private apk.makost.swing.Table table;
    // End of variables declaration//GEN-END:variables
}
