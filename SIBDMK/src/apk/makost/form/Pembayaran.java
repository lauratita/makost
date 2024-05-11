/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package apk.makost.form;

import apk.makost.koneksi.Koneksi;
import apk.makost.form.list.ListKamar;
import apk.makost.form.list.ListTarif;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author dF
 */
public class Pembayaran extends javax.swing.JPanel {

    DefaultTableModel model = new DefaultTableModel() {
        boolean[] canEdit = new boolean[]{
            false, false, false, false, false, false
        };

        public boolean isCellEditable(int rowIndex, int columnIndex) {
            return canEdit[columnIndex];
        }
    };

    Date date = new Date();
    SimpleDateFormat sDateFormate = new SimpleDateFormat("yyyy-MM-dd");
    DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public Pembayaran() {
        initComponents();
        tableBayar.setModel(model);
        model.addColumn("No Transaksi");
        model.addColumn("ID Tarif");
        model.addColumn("Nama Tarif");
        model.addColumn("Jumlah");
        model.addColumn("Harga");
        model.addColumn("Total");

        utama();
        clear();
        uneditAble();

        txtTanggalBayar.setText(sDateFormate.format(date));
        txtJumlahBayar.setText("0");
        txtTotalHarga.setText("0");
        txtKembalian.setText("0");
        txtUser.setText("1");
        btnHapus.setEnabled(false);
    }

    public void utama() {
        autonumber();
    }

    private void Total() {
        int total, bayar, kembalian;

        total = Integer.valueOf(txtTotalHarga.getText());
        bayar = Integer.valueOf(txtJumlahBayar.getText());

        kembalian = bayar - total;
        txtKembalian.setText(String.valueOf(kembalian));
    }

    public void kosong() {
        DefaultTableModel model = (DefaultTableModel) tableBayar.getModel();

        while (model.getRowCount() > 0) {
            model.removeRow(0);
        }
    }

    private void hitungJthTempo() {
        String dateString = txtJthTempo.getText();
        String tipe = txtStatus.getText();
        String newDateString;
        LocalDate jthTempo = LocalDate.parse(dateString, dateTimeFormatter);
        LocalDate newDate = null;
        int bnyk = Integer.parseInt(txtBerapaHBT.getText());
        switch (tipe) {
            case "Tahunan":
                newDate = jthTempo.plusYears(bnyk);
                break;
            case "Bulanan":
                newDate = jthTempo.plusMonths(bnyk);
                break;
            case "Harian":
                newDate = jthTempo.plusDays(bnyk);
                break;
            default:
                JOptionPane.showMessageDialog(null, "Tipe Pembayaran Tidak Diketahui");
        }
        newDateString = newDate.format(dateTimeFormatter);
        txtJthTempo.setText(newDateString);
    }

    private void hitungKamar() {
        hargaKamar();
        String banyak = spinnerStatus.getValue().toString();
        String tipe = cbTipeKamar.getSelectedItem().toString();
        String tnggl = null;
        int hargaKamar, totalBiaya;
        int bTBH = (int) spinnerStatus.getValue();
        totalBiaya = Integer.parseInt(txtTotalHarga.getText());
        hargaKamar = Integer.parseInt(txthargaKamar.getText());
        totalBiaya = totalBiaya + hargaKamar * bTBH;
        switch (tipe) {
            case "TAHUN":
                tnggl = "Tahunan";
                break;
            case "BULAN":
                tnggl = "Bulanan";
                break;
            case "HARI":
                tnggl = "Harian";
                break;
            default:
                JOptionPane.showMessageDialog(null, "Tipe Pembayaran Tidak Diketahui");
        }
        txtTotalHarga.setText(String.valueOf(totalBiaya));
        txtStatus.setText(tnggl);
        txtBerapaHBT.setText(banyak);
        hitungJthTempo();
        clearKamar();
    }

    private void hargaKamar() {
        try {
            Connection c = Koneksi.configDB();
            Statement s = c.createStatement();

            String sql = "SELECT * FROM tbl_manajemen_kamar"
                    + " JOIN tbl_kamar ON tbl_manajemen_kamar.no_kamar = tbl_kamar.no_kamar"
                    + " JOIN tbl_penghuni ON tbl_manajemen_kamar.id_penghuni = tbl_penghuni.id_penghuni"
                    + " JOIN tbl_kapasitas ON tbl_kamar.kd_kapasitas = tbl_kapasitas.kd_kapasitas"
                    + " WHERE id_manajemen = " + txtIdMKamar.getText() + "";
            ResultSet r = s.executeQuery(sql);
            if (r.next()) {
                String tipe = null;
                String selectedValue = cbTipeKamar.getSelectedItem().toString();
                if (selectedValue == "TAHUN") {
                    tipe = r.getString("harga_tahunan");
                } else if (selectedValue == "BULAN") {
                    tipe = r.getString("harga_bulanan");
                } else if (selectedValue == "HARI") {
                    tipe = r.getString("harga_harian");
                }
                txthargaKamar.setText(tipe);
            }
            r.close();
            s.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Data Tidak Ada");
        }
    }

    public void uneditAble() {
        txtTanggalBayar.setEditable(false);
        txtUser.setEditable(false);
        txtTransaksi.setEditable(false);
        txtIdMKamar.setEditable(false);
        txthargaKamar.setEditable(false);
        txtStatus.setEditable(false);
        txtBerapaHBT.setEditable(false);
        txtNoKamar.setEditable(false);
        txtTotalTarif.setEditable(false);
        txtJthTempo.setEditable(false);
        txtTotalHarga.setEditable(false);
        txtKembalian.setEditable(false);
        txtIdTarif.setEditable(false);
        txtNamaTarif.setEditable(false);
    }

    public void clear() {
        txtIdMKamar.setText("");
        txtJthTempo.setText("");
        txtJumlahBayar.setText("0");
        txtTotalHarga.setText("0");
        txtKembalian.setText("0");
        txthargaKamar.setText("0");
        txtStatus.setText("");
        txtBerapaHBT.setText("");
        clearTarif();
        clearKamar();
    }

    public void clearTarif() {
        txtIdTarif.setText("");
        txtHargaTarif.setText("");
        txtNamaTarif.setText("");
        txtJumlahTarif.setText("");
        txtTotalTarif.setText("");
    }

    public void clearKamar() {
        txtNoKamar.setText("");
        cbTipeKamar.setSelectedItem(null);
        spinnerStatus.setValue(0);
    }

    private void autonumber() {
        try {
            Connection c = Koneksi.configDB();
            Statement s = c.createStatement();
            String sql = "SELECT * FROM tbl_pembayaran ORDER BY no_transaksi DESC";
            ResultSet r = s.executeQuery(sql);
            if (r.next()) {
                String NoTransaksi = r.getString("no_transaksi").substring(3);
                String BYR = "" + (Integer.parseInt(NoTransaksi) + 1);
                String Nol = "";

                if (BYR.length() == 1) {
                    Nol = "0000";
                } else if (BYR.length() == 2) {
                    Nol = "000";
                } else if (BYR.length() == 3) {
                    Nol = "00";
                } else if (BYR.length() == 4) {
                    Nol = "0";
                } else if (BYR.length() == 5) {
                    Nol = "";
                }
                txtTransaksi.setText("BYR" + Nol + BYR);
            } else {
                txtTransaksi.setText("BYR00001");
            }
            r.close();
            s.close();
        } catch (Exception e) {
            System.out.println("autonumber error");
        }
    }

    private void totalBiaya() {
        int jumlahBaris = tableBayar.getRowCount();
        int jumlahTarif, hargaTarif;
        int totalBiaya = Integer.parseInt(txtTotalTarif.getText());
        int totaltotal = Integer.parseInt(txtTotalHarga.getText());
        for (int i = 0; i < jumlahBaris; i++) {
            jumlahTarif = Integer.parseInt(tableBayar.getValueAt(i, 3).toString());
            hargaTarif = Integer.parseInt(tableBayar.getValueAt(i, 4).toString());
            totalBiaya = (jumlahTarif * hargaTarif);
        }
        totaltotal = totaltotal + totalBiaya;
        txtTotalHarga.setText(String.valueOf(totaltotal));
    }

    public void tambahTransaksi() {
        int jumlah, harga, total;

        jumlah = Integer.valueOf(txtJumlahTarif.getText());
        harga = Integer.valueOf(txtHargaTarif.getText());
        total = jumlah * harga;

        txtTotalTarif.setText(String.valueOf(total));
        loadData();
        totalBiaya();
        clearTarif();
        txtIdTarif.requestFocus();

    }

    private void loadData() {
        DefaultTableModel model = (DefaultTableModel) tableBayar.getModel();

        model.addRow(new Object[]{
            txtTransaksi.getText(),
            txtIdTarif.getText(),
            txtNamaTarif.getText(),
            txtJumlahTarif.getText(),
            txtHargaTarif.getText(),
            txtTotalTarif.getText()
        });
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        shadowPanel1 = new apk.makost.swing.ShadowPanel();
        txtNoKamar = new apk.makost.swing.TextField();
        txtTotalHarga = new apk.makost.swing.TextField();
        txtJumlahBayar = new apk.makost.swing.TextField();
        txtKembalian = new apk.makost.swing.TextField();
        txtNamaTarif = new apk.makost.swing.TextField();
        txtJumlahTarif = new apk.makost.swing.TextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableBayar = new apk.makost.swing.Table();
        spinnerStatus = new apk.makost.swing.Spinner();
        btncariKamar = new apk.makost.swing.Button();
        btnHapus = new apk.makost.swing.Button();
        btnClear = new apk.makost.swing.Button();
        btntambahTarif = new apk.makost.swing.Button();
        btncariTarif = new apk.makost.swing.Button();
        btnBayar = new apk.makost.swing.Button();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txthargaKamar = new apk.makost.swing.TextField();
        jLabel4 = new javax.swing.JLabel();
        txtIdTarif = new apk.makost.swing.TextField();
        cbTipeKamar = new apk.makost.swing.Combobox();
        txtHargaTarif = new apk.makost.swing.TextField();
        jLabel5 = new javax.swing.JLabel();
        btntambahKamar = new apk.makost.swing.Button();
        gradientRPanel1 = new apk.makost.swing.GradientRPanel();
        txtTransaksi = new apk.makost.swing.TextField();
        txtUser = new apk.makost.swing.TextField();
        txtTanggalBayar = new apk.makost.swing.TextField();
        txtIdMKamar = new apk.makost.swing.TextField();
        txtStatus = new apk.makost.swing.TextField();
        txtBerapaHBT = new apk.makost.swing.TextField();
        txtJthTempo = new apk.makost.swing.TextField();
        jLabel6 = new javax.swing.JLabel();
        txtTotalTarif = new apk.makost.swing.TextField();

        setBackground(new java.awt.Color(236, 243, 255));
        setPreferredSize(new java.awt.Dimension(1069, 663));

        txtNoKamar.setLabelText("No Kamar");
        txtNoKamar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNoKamarActionPerformed(evt);
            }
        });

        txtTotalHarga.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        txtTotalHarga.setLabelText("Total Harga");
        txtTotalHarga.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTotalHargaActionPerformed(evt);
            }
        });

        txtJumlahBayar.setLabelText("Jumlah Bayar");
        txtJumlahBayar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtJumlahBayarMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                txtJumlahBayarMouseEntered(evt);
            }
        });
        txtJumlahBayar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtJumlahBayarActionPerformed(evt);
            }
        });
        txtJumlahBayar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtJumlahBayarKeyTyped(evt);
            }
        });

        txtKembalian.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        txtKembalian.setLabelText("Kembalian");
        txtKembalian.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtKembalianActionPerformed(evt);
            }
        });

        txtNamaTarif.setLabelText("Nama Tarif");
        txtNamaTarif.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNamaTarifActionPerformed(evt);
            }
        });

        txtJumlahTarif.setLabelText("Jumlah");
        txtJumlahTarif.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtJumlahTarifActionPerformed(evt);
            }
        });
        txtJumlahTarif.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtJumlahTarifKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtJumlahTarifKeyTyped(evt);
            }
        });

        tableBayar.setModel(new javax.swing.table.DefaultTableModel(
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
        tableBayar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableBayarMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tableBayar);

        spinnerStatus.setModel(new javax.swing.SpinnerNumberModel(1, 1, null, 1));
        spinnerStatus.setLabelText("");

        btncariKamar.setBackground(new java.awt.Color(0, 153, 153));
        btncariKamar.setForeground(new java.awt.Color(255, 255, 255));
        btncariKamar.setText("CARI");
        btncariKamar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btncariKamarActionPerformed(evt);
            }
        });

        btnHapus.setBackground(new java.awt.Color(153, 0, 0));
        btnHapus.setForeground(new java.awt.Color(255, 255, 255));
        btnHapus.setText("HAPUS");
        btnHapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHapusActionPerformed(evt);
            }
        });

        btnClear.setBackground(new java.awt.Color(255, 0, 0));
        btnClear.setForeground(new java.awt.Color(255, 255, 255));
        btnClear.setText("CLEAR");
        btnClear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClearActionPerformed(evt);
            }
        });

        btntambahTarif.setBackground(new java.awt.Color(0, 204, 204));
        btntambahTarif.setForeground(new java.awt.Color(255, 255, 255));
        btntambahTarif.setText("TAMBAH");
        btntambahTarif.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btntambahTarifActionPerformed(evt);
            }
        });

        btncariTarif.setBackground(new java.awt.Color(0, 153, 153));
        btncariTarif.setForeground(new java.awt.Color(255, 255, 255));
        btncariTarif.setText("CARI");
        btncariTarif.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btncariTarifActionPerformed(evt);
            }
        });

        btnBayar.setBackground(new java.awt.Color(102, 204, 0));
        btnBayar.setForeground(new java.awt.Color(255, 255, 255));
        btnBayar.setText("BAYAR");
        btnBayar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBayarActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        jLabel1.setText("Rp.");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        jLabel2.setText("Rp.");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel3.setText("Rp.");

        txthargaKamar.setLabelText("Harga Kamar");
        txthargaKamar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txthargaKamarActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel4.setText("Rp.");

        txtIdTarif.setLabelText("ID. Tarif");
        txtIdTarif.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtIdTarifActionPerformed(evt);
            }
        });

        cbTipeKamar.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "TAHUN", "BULAN", "HARI" }));
        cbTipeKamar.setLabeText("Tipe Kamar");

        txtHargaTarif.setLabelText("Harga");
        txtHargaTarif.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtHargaTarifActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel5.setText("Rp.");

        btntambahKamar.setBackground(new java.awt.Color(0, 204, 204));
        btntambahKamar.setForeground(new java.awt.Color(255, 255, 255));
        btntambahKamar.setText("TAMBAH");
        btntambahKamar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btntambahKamarActionPerformed(evt);
            }
        });

        gradientRPanel1.setColor1(new java.awt.Color(204, 255, 255));
        gradientRPanel1.setColor2(new java.awt.Color(5, 240, 252));

        txtTransaksi.setLabelText("No. Transaksi");
        txtTransaksi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTransaksiActionPerformed(evt);
            }
        });

        txtUser.setLabelText("ID User");
        txtUser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtUserActionPerformed(evt);
            }
        });

        txtTanggalBayar.setLabelText("Tanggal ");
        txtTanggalBayar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTanggalBayarActionPerformed(evt);
            }
        });

        txtIdMKamar.setLabelText("ID. Manajemen");
        txtIdMKamar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtIdMKamarActionPerformed(evt);
            }
        });

        txtStatus.setLabelText("Tipe Pembayaran Kamar");
        txtStatus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtStatusActionPerformed(evt);
            }
        });

        txtBerapaHBT.setLabelText("Berapa kali Bayar");
        txtBerapaHBT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtBerapaHBTActionPerformed(evt);
            }
        });

        txtJthTempo.setLabelText("Jatuh Tempo");
        txtJthTempo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtJthTempoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout gradientRPanel1Layout = new javax.swing.GroupLayout(gradientRPanel1);
        gradientRPanel1.setLayout(gradientRPanel1Layout);
        gradientRPanel1Layout.setHorizontalGroup(
            gradientRPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(gradientRPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(gradientRPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtTransaksi, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtUser, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTanggalBayar, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29)
                .addGroup(gradientRPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtJthTempo, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(gradientRPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(txtIdMKamar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtStatus, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtBerapaHBT, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(20, Short.MAX_VALUE))
        );
        gradientRPanel1Layout.setVerticalGroup(
            gradientRPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(gradientRPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(gradientRPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTanggalBayar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtIdMKamar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(gradientRPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTransaksi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtStatus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(8, 8, 8)
                .addGroup(gradientRPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtBerapaHBT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtUser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtJthTempo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(23, Short.MAX_VALUE))
        );

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel6.setText("Rp.");

        txtTotalTarif.setLabelText("Total");
        txtTotalTarif.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTotalTarifActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout shadowPanel1Layout = new javax.swing.GroupLayout(shadowPanel1);
        shadowPanel1.setLayout(shadowPanel1Layout);
        shadowPanel1Layout.setHorizontalGroup(
            shadowPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, shadowPanel1Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(shadowPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(shadowPanel1Layout.createSequentialGroup()
                        .addComponent(gradientRPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(shadowPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(shadowPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, shadowPanel1Layout.createSequentialGroup()
                                    .addComponent(jLabel1)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(txtTotalHarga, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, shadowPanel1Layout.createSequentialGroup()
                                    .addComponent(jLabel2)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(txtKembalian, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, shadowPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txthargaKamar, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(shadowPanel1Layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtJumlahBayar, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addComponent(jScrollPane1)
                    .addGroup(shadowPanel1Layout.createSequentialGroup()
                        .addComponent(txtNoKamar, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btncariKamar, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(spinnerStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cbTipeKamar, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btntambahKamar, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(24, 24, 24)
                        .addComponent(txtIdTarif, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btncariTarif, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtNamaTarif, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtHargaTarif, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txtJumlahTarif, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtTotalTarif, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(shadowPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnBayar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnHapus, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnClear, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btntambahTarif, javax.swing.GroupLayout.DEFAULT_SIZE, 80, Short.MAX_VALUE))
                .addGap(0, 23, Short.MAX_VALUE))
        );
        shadowPanel1Layout.setVerticalGroup(
            shadowPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(shadowPanel1Layout.createSequentialGroup()
                .addGroup(shadowPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, shadowPanel1Layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addGroup(shadowPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(shadowPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(txtNamaTarif, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtJumlahTarif, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtHargaTarif, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel5)
                                .addComponent(txtTotalTarif, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel6))
                            .addGroup(shadowPanel1Layout.createSequentialGroup()
                                .addComponent(btncariTarif, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(1, 1, 1))))
                    .addGroup(shadowPanel1Layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addGroup(shadowPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btntambahTarif, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(shadowPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(cbTipeKamar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(spinnerStatus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(shadowPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(btntambahKamar, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtIdTarif, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(shadowPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(btncariKamar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(txtNoKamar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(shadowPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 319, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnHapus, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(shadowPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(gradientRPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(shadowPanel1Layout.createSequentialGroup()
                        .addGroup(shadowPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtJumlahBayar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3))
                        .addGap(9, 9, 9)
                        .addGroup(shadowPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(shadowPanel1Layout.createSequentialGroup()
                                .addGroup(shadowPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txthargaKamar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel4))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(shadowPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtTotalHarga, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel1))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(shadowPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtKembalian, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel2)))
                            .addGroup(shadowPanel1Layout.createSequentialGroup()
                                .addGap(56, 56, 56)
                                .addComponent(btnClear, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(9, 9, 9)
                                .addComponent(btnBayar, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(28, 28, 28))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(shadowPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(shadowPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void txtNoKamarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNoKamarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNoKamarActionPerformed

    private void txtTotalHargaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTotalHargaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTotalHargaActionPerformed

    private void txtJumlahBayarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtJumlahBayarActionPerformed
        // TODO add your handling code here:
        Total();
    }//GEN-LAST:event_txtJumlahBayarActionPerformed

    private void txtKembalianActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtKembalianActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtKembalianActionPerformed

    private void txtNamaTarifActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNamaTarifActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNamaTarifActionPerformed

    private void txtJumlahTarifActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtJumlahTarifActionPerformed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_txtJumlahTarifActionPerformed

    private void txtTanggalBayarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTanggalBayarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTanggalBayarActionPerformed

    private void btncariTarifActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btncariTarifActionPerformed
        // TODO add your handling code here:
        ListTarif a = new ListTarif();
        a.setVisible(true);
    }//GEN-LAST:event_btncariTarifActionPerformed

    private void btncariKamarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btncariKamarActionPerformed
        // TODO add your handling code here:
        ListKamar b = new ListKamar();
        b.setVisible(true);
    }//GEN-LAST:event_btncariKamarActionPerformed

    private void btnHapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHapusActionPerformed
        // TODO add your handling code here:
        DefaultTableModel model = (DefaultTableModel) tableBayar.getModel();
        int row = tableBayar.getSelectedRow();
        int theValueOfCell = Integer.parseInt(tableBayar.getValueAt(row, 5).toString());
        int totalHarga = Integer.parseInt(txtTotalHarga.getText());
        int totaltotal = totalHarga - theValueOfCell;
        txtTotalHarga.setText(String.valueOf(totaltotal));
        model.removeRow(row);
    }//GEN-LAST:event_btnHapusActionPerformed

    private void txtUserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtUserActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtUserActionPerformed

    private void txtIdMKamarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtIdMKamarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtIdMKamarActionPerformed

    private void txtTransaksiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTransaksiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTransaksiActionPerformed

    private void btnBayarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBayarActionPerformed
        // TODO add your handling code here:
        DefaultTableModel model = (DefaultTableModel) tableBayar.getModel();

        int total, bayar;
        int baris = tableBayar.getRowCount();
        total = Integer.valueOf(txtTotalHarga.getText());
        bayar = Integer.valueOf(txtJumlahBayar.getText());
        String noTransaki = txtTransaksi.getText();
        String kembalian = txtKembalian.getText();
        String jmlBayar = txtJumlahBayar.getText();
        String tipeBayar = txtStatus.getText();
        String bnykTBH = txtBerapaHBT.getText();
        String total_harga = txtTotalHarga.getText();
        String hargaKamar = txthargaKamar.getText();
        String tanggal = txtTanggalBayar.getText();
        String user = txtUser.getText();
        String idkamar = txtIdMKamar.getText();
        String jthTempo = txtJthTempo.getText();

        if (total > bayar) {
            JOptionPane.showMessageDialog(null, "Uang tidak cukup untuk melakukan pembayaran");
        } else if (total == 0) {
            JOptionPane.showMessageDialog(null, "Pembayaran Kosong");
        } else if (!hargaKamar.equals("0")) {
            try {
                Connection c = Koneksi.configDB();
                String sql = "INSERT INTO tbl_pembayaran VALUES ('"
                        + noTransaki + "','"
                        + jmlBayar + "','"
                        + total_harga + "','"
                        + tipeBayar + "','"
                        + bnykTBH + "','"
                        + hargaKamar + "','"
                        + kembalian + "','"
                        + tanggal + "','"
                        + user + "','"
                        + idkamar + "')";
                PreparedStatement p = c.prepareStatement(sql);
                p.executeUpdate();
                p.close();

            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Data Kamar Kosong");
            }

            try {
                Connection c = Koneksi.configDB();

                for (int i = 0; i < baris; i++) {
                    String sql = "INSERT INTO tbl_detailpembayaran"
                            + " (no_transaksi, id_tarif, nama_tarif, jumlah, harga, total)"
                            + " VALUES('"
                            + tableBayar.getValueAt(i, 0) + "','"
                            + tableBayar.getValueAt(i, 1) + "','"
                            + tableBayar.getValueAt(i, 2) + "','"
                            + tableBayar.getValueAt(i, 3) + "','"
                            + tableBayar.getValueAt(i, 4) + "','"
                            + tableBayar.getValueAt(i, 5) + "'"
                            + ")";
                    PreparedStatement p = c.prepareStatement(sql);
                    p.executeUpdate();
                    p.close();
                }
            } catch (Exception e) {
                System.out.println("simpan penjualanrinci error");
            }

            try {
                Connection c = Koneksi.configDB();
                String sql = "UPDATE tbl_manajemen_kamar SET "
                        + "jthtempo = '" + jthTempo + "' "
                        + "WHERE id_manajemen = '" + idkamar + "'";
                PreparedStatement p = c.prepareStatement(sql);
                p.executeUpdate();
                p.close();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e.getMessage());
            }
            clear();
            kosong();
            JOptionPane.showMessageDialog(null, "Pembayaran Berhasil");
        } else {
            JOptionPane.showMessageDialog(null, "Data Pembayaran Invalid");
        }
        utama();
        autonumber();
    }//GEN-LAST:event_btnBayarActionPerformed

    private void btnClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearActionPerformed
        // TODO add your handling code here:
        clear();
    }//GEN-LAST:event_btnClearActionPerformed

    private void txthargaKamarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txthargaKamarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txthargaKamarActionPerformed

    private void txtIdTarifActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtIdTarifActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtIdTarifActionPerformed

    private void btntambahTarifActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btntambahTarifActionPerformed
        // TODO add your handling code here:
        String tarif = txtIdTarif.getText();
        if (tarif.equals("")) {

        } else {
            tambahTransaksi();
            Total();
        }

    }//GEN-LAST:event_btntambahTarifActionPerformed

    private void txtHargaTarifActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtHargaTarifActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtHargaTarifActionPerformed

    private void txtJumlahBayarKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtJumlahBayarKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtJumlahBayarKeyTyped

    private void btntambahKamarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btntambahKamarActionPerformed
        // TODO add your handling code here:
        String kamar = txtNoKamar.getText();
        if (kamar.equals("")) {

        } else {
            hitungKamar();
            Total();
        }
    }//GEN-LAST:event_btntambahKamarActionPerformed

    private void txtBerapaHBTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtBerapaHBTActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtBerapaHBTActionPerformed

    private void txtStatusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtStatusActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtStatusActionPerformed

    private void txtJumlahBayarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtJumlahBayarMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_txtJumlahBayarMouseEntered

    private void txtJumlahBayarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtJumlahBayarMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_txtJumlahBayarMouseClicked

    private void txtJthTempoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtJthTempoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtJthTempoActionPerformed

    private void txtTotalTarifActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTotalTarifActionPerformed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_txtTotalTarifActionPerformed

    private void txtJumlahTarifKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtJumlahTarifKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtJumlahTarifKeyTyped

    private void tableBayarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableBayarMouseClicked
        // TODO add your handling code here:
        int i = tableBayar.getSelectedRow();
        if (i == -1) {
            return;
        } else {
            btnHapus.setEnabled(true);
        }
    }//GEN-LAST:event_tableBayarMouseClicked

    private void txtJumlahTarifKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtJumlahTarifKeyReleased
        // TODO add your handling code here:
        if (txtJumlahTarif.getText().equals("")
                || txtHargaTarif.getText().equals("")){
            return;
        }
        String a = txtJumlahTarif.getText();
        String b = txtHargaTarif.getText();
        
        int a1 = Integer.parseInt(a);
        int b1 = Integer.parseInt(b);
        int c1 = a1 * b1;
        
        txtTotalTarif.setText(String.valueOf(c1));
    }//GEN-LAST:event_txtJumlahTarifKeyReleased


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private apk.makost.swing.Button btnBayar;
    private apk.makost.swing.Button btnClear;
    private apk.makost.swing.Button btnHapus;
    private apk.makost.swing.Button btncariKamar;
    private apk.makost.swing.Button btncariTarif;
    private apk.makost.swing.Button btntambahKamar;
    private apk.makost.swing.Button btntambahTarif;
    public static apk.makost.swing.Combobox cbTipeKamar;
    private apk.makost.swing.GradientRPanel gradientRPanel1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JScrollPane jScrollPane1;
    private apk.makost.swing.ShadowPanel shadowPanel1;
    public static apk.makost.swing.Spinner spinnerStatus;
    private apk.makost.swing.Table tableBayar;
    public static apk.makost.swing.TextField txtBerapaHBT;
    public static apk.makost.swing.TextField txtHargaTarif;
    public static apk.makost.swing.TextField txtIdMKamar;
    public static apk.makost.swing.TextField txtIdTarif;
    public static apk.makost.swing.TextField txtJthTempo;
    private apk.makost.swing.TextField txtJumlahBayar;
    public static apk.makost.swing.TextField txtJumlahTarif;
    private apk.makost.swing.TextField txtKembalian;
    public static apk.makost.swing.TextField txtNamaTarif;
    public static apk.makost.swing.TextField txtNoKamar;
    public static apk.makost.swing.TextField txtStatus;
    private apk.makost.swing.TextField txtTanggalBayar;
    private apk.makost.swing.TextField txtTotalHarga;
    public static apk.makost.swing.TextField txtTotalTarif;
    private apk.makost.swing.TextField txtTransaksi;
    private apk.makost.swing.TextField txtUser;
    public static apk.makost.swing.TextField txthargaKamar;
    // End of variables declaration//GEN-END:variables
}
