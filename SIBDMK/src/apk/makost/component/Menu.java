package apk.makost.component;

import apk.makost.event.MenuEvent;
import apk.makost.shadow.ShadowRenderer;
import apk.makost.swing.MenuButton;
import apk.makost.swing.Split;
import apk.makost.swing.ScrollBar;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
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
    //shadow
    int shadowSize = 4;
    float shadowOpacity = 0.2f;
    private Color shadowColor = Color.BLACK;

    public Menu() {
        initComponents();
        setOpaque(false);
        scroll.setViewportBorder(null);
        scroll.setBorder(null);
        scroll.getViewport().setOpaque(false);
        scroll.getVerticalScrollBar();
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
        animator = new Animator(100, target);
        animator.setAcceleration(.5f);
        animator.setDeceleration(.5f);
        animator.setResolution(0);
    }

    public void initMenu(MenuEvent event) {
        this.event = event;
        addMenu("dashboard", "Dashboard", 0);
        addMenu("penghuni", "Penghuni", 1);
        addMenu("kamar", "Kamar", 2);
        addMenu("kapasitas", "Kapasitas Kamar", 3);
        addMenu("mkamar", "Manajemen Kamar", 4);
        addMenu("tarif", "Data Tarif", 5);
        addMenu("bayar", "Pembayaran", 6);
        addMenu("riwayat", "Riwayat Pembayaran", 7);
        addMenu("akun", "Akun", 8);
        split("Laporan");
        addMenu("pengeluaran", "Pengeluaran", 9);
        addMenu("pendapatan", "Pendapatan", 10);
        space();
        addMenu("logout", "Log Out", 11);
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
    
    private void split(String name) {
        panelMenu.add(new Split(name), "h 0");
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

    @Override
    protected void paintComponent(Graphics grphcs) {
        createShadow(grphcs);
        super.paintComponent(grphcs); 
    }
    
    private void createShadow(Graphics grphcs){
        Graphics2D g2 = (Graphics2D) grphcs;
        int size = shadowSize * 2;
        int x = 0;
        int y = 0;
        int width = getWidth() - size;
        int height = getHeight();
        BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = img.createGraphics();
        g.setBackground(getBackground());
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g.fillRoundRect(0, 0, width, height, 10, 0);
        //Create Shadow
        ShadowRenderer render = new ShadowRenderer(size, shadowOpacity, shadowColor);
        g2.drawImage(render.createShadow(img), 0, 0, null);
        g2.drawImage(img, x, y, null);
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
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(scroll, javax.swing.GroupLayout.DEFAULT_SIZE, 230, Short.MAX_VALUE))
                .addGap(0, 25, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(scroll, javax.swing.GroupLayout.DEFAULT_SIZE, 552, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel panelMenu;
    private javax.swing.JScrollPane scroll;
    // End of variables declaration//GEN-END:variables
}
