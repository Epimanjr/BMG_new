/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package view;

// Here all imports needed for this class.
import java.awt.Dimension;
import javax.swing.JLabel;

/**
 *
 * @author Maxime Blaise
 */
public class BmgLabel extends JLabel {
    /**
     * Label color
     */
    String color;
    
    /**
     * Constructor which create label with right color.
     * 
     * @param message
     * @param color 
     */
    public BmgLabel(String message, String color) {
        
        super("<html><p style=\"color: "+color+"\">"+message+"</p></html>");
        this.color = color;
    }
    
    public BmgLabel(String message, String color, int taille) {
        super("<html><p style=\"color: "+color+";font-size: "+taille+"px;\">"+message+"</p></html>");
        this.color = color;
        
        
    }
    
    public BmgLabel(String message, String color, int width, int height) {
        
        super("<html><p style=\"color: "+color+"\">"+message+"</p></html>");
        this.color = color;
        
        this.setPreferredSize(new Dimension(width, height));
    }
    
    /**
     * Set message, but keep the same color.
     * 
     * @param mess 
     */
    public void set(String mess) {
        this.setText("<html><p style=\"color: "+color+"\">"+mess+"</p></html>");
    }
}
