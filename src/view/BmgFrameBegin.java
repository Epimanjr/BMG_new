/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

// Here all imports needed for this class.
import database.BaseSetting;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/**
 *
 * @author Maxime Blaise
 */
public class BmgFrameBegin {

    /**
     * dialog, only if database connection exists.
     */
    JDialog dialog = new JDialog();

    final BaseSetting bs;

    final BmgFrame fen;

    /**
     * Constructor.
     *
     * @param bs
     * @param fen
     */
    public BmgFrameBegin(final BaseSetting bs, final BmgFrame fen) {
        //Initialise fields
        this.bs = bs;
        this.fen = fen;

        if (bs.testerConnexion()) {
            //If connection is correct.
            JPanel pan = new JPanel();

            //PanHaut,in which user can put his pseudo and password
            JPanel panHaut = new JPanel();
            panHaut.setLayout(new GridLayout(2, 2));

            final JTextField saisiePseudo = new JTextField(15);
            final JPasswordField saisiePass = new JPasswordField(15);

            panHaut.add(new JLabel("Pseudo : "));
            panHaut.add(saisiePseudo);
            panHaut.add(new JLabel("Password : "));
            panHaut.add(saisiePass);

            //PanelSouth, which contains button and listeners.
            JPanel panSouth = new JPanel();
            panSouth.setLayout(new GridLayout(3, 1));

            //Buttons
            BmgButton buttonSignin = new BmgButton("Sign in", 300, 40, Color.yellow);
            BmgButton buttonSignup = new BmgButton("Sign up", 300, 40, Color.yellow);
            BmgButton buttonLater = new BmgButton("No thank's, later", 300, 40, Color.yellow);

            buttonSignin.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent ae) {

                    actionSignIn(saisiePseudo, saisiePass);
                }
            });
            
            //KeyListener 
            KeyListener key = new KeyListener() {

                @Override
                public void keyTyped(KeyEvent ke) {

                }

                @Override
                public void keyPressed(KeyEvent ke) {
                    //if entrer
                    if(ke.getKeyCode() == KeyEvent.VK_ENTER) {
                        actionSignIn(saisiePseudo, saisiePass);
                    }
                }

                @Override
                public void keyReleased(KeyEvent ke) {

                }
            };
            
            //Add Key Listener
            saisiePseudo.addKeyListener(key);
            saisiePass.addKeyListener(key);
            
            panSouth.add(buttonSignin);
            buttonSignup.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent ae) {
                    fen.setPanSignUp(saisiePseudo.getText(), saisiePass.getText());
                    //fen.setPanel(BmgFrame.panSignup);
                    fermer();
                }
            });
            panSouth.add(buttonSignup);

            buttonLater.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent ae) {
                    fen.setPanel(BmgFrame.panMain);
                    fermer();
                }
            });
            panSouth.add(buttonLater);

            //Add to main pan
            pan.add(panHaut, BorderLayout.NORTH);
            pan.add(panSouth, BorderLayout.SOUTH);

            //Dialog settings
            dialog.setContentPane(pan);
            dialog.setTitle("Would you want to connect ?");
            dialog.setContentPane(pan);
            dialog.setLocation(300, 300);
            dialog.setPreferredSize(new Dimension(400, 300));
            dialog.pack();
            dialog.setVisible(true);
        } else {
            //Wrong connection
            JOptionPane jop = new JOptionPane();

            String[] choix = {"Setting database now", "No thank's, maybe later"};

            //Just show small dialog whith 2 buttons.
            int rang = JOptionPane.showOptionDialog(null,
                    "What would you want to do ?",
                    "Error database !",
                    JOptionPane.YES_NO_CANCEL_OPTION,
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    choix,
                    choix[0]);

            if (rang == 0) {
                //User wants to change settings.
                fen.setPanel(BmgFrame.panSettings);
            } else {
                //User doesn't want to do anything.
                fen.setPanel(BmgFrame.panMain);
            }
        }

    }

    public void actionSignIn(JTextField saisiePseudo, JPasswordField saisiePass) {
        char[] c = saisiePass.getPassword();
        String password = new String(c);

        BmgCreatePanel.actionSignIn(fen, bs, saisiePseudo.getText(), password);
        fen.setPanel(BmgFrame.panMain);
        fermer();
    }

    public void fermer() {
        dialog.dispose();
    }

}
