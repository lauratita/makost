
package apk.makost.model;

import java.awt.Color;


public class ModelLegend {

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the color
     */
    public Color getColor() {
        return color;
    }

    /**
     * @param color the color to set
     */
    public void setColor(Color color) {
        this.color = color;
    }

    public ModelLegend() {
    }

    public ModelLegend(String name, Color color) {
        this.name = name;
        this.color = color;
    }
    
    
    private String name;
    private Color color;
    
}
