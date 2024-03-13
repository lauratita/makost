package apk.makost.component;

import apk.makost.event.MenuEvent;
import apk.makost.swing.MenuButton;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import net.miginfocom.swing.MigLayout;
import org.jdesktop.animation.timing.Animator;
import org.jdesktop.animation.timing.TimingTarget;
import org.jdesktop.animation.timing.TimingTargetAdapter;

public class Menu extends javax.swing.JPanel {

    private Animator animator;
    private MenuButton selectedMenu;
    private MenuButton unselectedMenu;
    private MenuEvent event;

    public Menu() {
        initComponents();

        scroll.setViewportBorder(null);
        scroll.setBorder(null);
        scroll.getViewport().setOpaque(false);
//        scroll.getVerticalScrollBar(new ScrollBarCustom());
        panelMenu.setLayout(new MigLayout("wrap, fillx, inset 0", "[fill]"));
        TimingTarget target = new TimingTargetAdapter() {
            @Override
            public void timingEvent(float fraction) {
                selectedMenu.setAnimate(fraction);
                if (unselectedMenu != null) {
                    unselectedMenu.setAnimate(1f - fraction);
                }
            }

        };
        animator = new Animator(300, target);
        animator.setAcceleration(.5f);
        animator.setDeceleration(.5f);
        animator.setResolution(0);
    }

    public void initMenu(MenuEvent event) {
        this.event = event;
        addMenu("dashboard", "Dashboard", 0);
        addMenu("penghuni", "Penghuni", 1);
        addMenu("kamar", "Kamar", 2);
        addMenu("mkamar", "Manajemen Kamar", 3);
        addMenu("tarif", "Data Tarif", 4);
        addMenu("bayar", "Pembayaran", 5);
        addMenu("riwayat", "Riwayat Pembayaran", 6);
        addMenu("pengeluaran", "Pengeluaran", 7);
        addMenu("pendapatan", "Pendapatan", 8);
        space();
        addMenu("logout", "Log Out", 9);
    }

    private void addMenu(String icon, String text, int index) {
        MenuButton menu = new MenuButton(index);
        setFont(menu.getFont().deriveFont(Font.PLAIN, 16));
        menu.setIcon(new ImageIcon(getClass().getResource("/apk/makost/icon/" + icon + ".png")));
        menu.setText("   " + text);
        menu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!animator.isRunning()) {
                    if (menu != selectedMenu) {
                        unselectedMenu = selectedMenu;
                        selectedMenu = menu;
                        animator.start();
                        event.menuSeleceted(index);
                    }
                }
            }
        });
        panelMenu.add(menu);
    }
    
    private void space(){
        panelMenu.add(new JLabel(),"push");
    }

    public void setSelected(int index) {
        for (Component com : panelMenu.getComponents()) {
            MenuButton menu = (MenuButton) com;
            if (menu.getIndex() == index) {
                if (menu != selectedMenu) {
                    unselectedMenu = selectedMenu;
                    selectedMenu = menu;
                    animator.start();
                    event.menuSeleceted(index);
                }
                break;
            }
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        scroll = new javax.swing.JScrollPane();
        panelMenu = new javax.swing.JPanel();

        setBackground(new java.awt.Color(255, 255, 255));
        setForeground(new java.awt.Color(255, 255, 255));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/apk/makost/icon/logo.png"))); // NOI18N

        panelMenu.setBackground(new java.awt.Color(255, 255, 255));
        panelMenu.setForeground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout panelMenuLayout = new javax.swing.GroupLayout(panelMenu);
        panelMenu.setLayout(panelMenuLayout);
        panelMenuLayout.setHorizontalGroup(
            panelMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 253, Short.MAX_VALUE)
        );
        panelMenuLayout.setVerticalGroup(
            panelMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 544, Short.MAX_VALUE)
        );

        scroll.setViewportView(panelMenu);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(scroll, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(scroll)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel panelMenu;
    private javax.swing.JScrollPane scroll;
    // End of variables declaration//GEN-END:variables
}
