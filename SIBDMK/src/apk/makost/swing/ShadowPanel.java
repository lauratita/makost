/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package apk.makost.swing;

import apk.makost.shadow.ShadowRenderer;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;

public class ShadowPanel extends JPanel {
    
    int shadowSize = 4;
    float shadowOpacity = 0.2f;
    private Color shadowColor = Color.BLACK;
    
    public ShadowPanel(){
        setOpaque(false);
        
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
        int height = getHeight() - size;
        BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g  = img.createGraphics();g.setBackground(getBackground());
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g.fillRoundRect(0, 0, width, height, 20, 20);
        //Create Shadow
        ShadowRenderer render = new ShadowRenderer(size, shadowOpacity, shadowColor);
        g2.drawImage(render.createShadow(img), 0, 0, null);
        g2.drawImage(img, x, y, null);
    }
    
}
