/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package apk.makost.form;

import java.awt.Color;
import javax.swing.ImageIcon;

/**
 *
 * @author dF
 */
public class ManajemenKamar extends javax.swing.JPanel {

    /**
     * Creates new form ManajemenKamar
     */
    public ManajemenKamar() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jTextField1 = new javax.swing.JTextField();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();

        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton1.setText("jButton1");
        add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 93, 80, 30));

        jButton2.setText("jButton2");
        add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 93, 80, 30));

        jTextField1.setText("jTextField1");
        add(jTextField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 92, 220, 30));

        jButton3.setText("jButton3");
        add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 93, 80, 30));

        jButton4.setText("jButton4");
        add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 93, 80, 30));

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(jTable1);

        add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 200, 890, -1));
    }// </editor-fold>//GEN-END:initComponents

/*  jButton1 = btnTambahManajemenKamar
    jButton2 = btnRefreshManajaemenKamar
    JTextField1 = txtCariManajemenKamar
    JTable = tblManajemenKamar
    */
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables
}
