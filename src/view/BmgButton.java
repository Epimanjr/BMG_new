/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package view;

// Here all imports needed for this class.
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JButton;

/**
 *
 * @author Maxime Blaise
 */
public class BmgButton extends JButton {
    
    /**
     * Constructor which set default PreferredSize and background color.
     *
     * @param name 
     */
    public BmgButton(String name) {
        super(name);
        
        this.setPreferredSize(new Dimension(150,40));
        this.setBackground(Color.red);
    }
    
    /**
     * Create a button with width, height and color given.
     * 
     * @param name
     * @param width
     * @param height
     * @param color 
     */
    public BmgButton(String name, int width, int height, Color color) {
        super(name);
        
        this.setPreferredSize(new Dimension(width, height));
        this.setBackground(color);
    }
}
