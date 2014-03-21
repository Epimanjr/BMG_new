/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

// Here all imports needed for this class.
import java.awt.BorderLayout;
import javax.swing.JPanel;

/**
 *
 * @author Maxime Blaise
 */
public class BmgPanel extends JPanel {
    /**
     * Center panel.
     */
    JPanel pan = new JPanel();
    
    /**
     * Constructeur qui se charge de créer la structure d'un panel.
     * @param fen
     * @param pan
     */
    public BmgPanel(BmgFrame fen, JPanel pan) {
        //Initialisation du panel et de ses attributs
        super(new BorderLayout());
  
        //Ajout des panels au panel principal
        this.add(fen.panMenu, BorderLayout.NORTH);
        this.add(pan, BorderLayout.CENTER);
        this.add(fen.panSouth, BorderLayout.SOUTH);
        
        repaint();
    }
    
    
}
