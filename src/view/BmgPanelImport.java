/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import model.Exercise;

/**
 *
 * @author blaise
 */
public class BmgPanelImport extends JPanel {

    public BmgFrame fen;

    public JPanel panHaut;
    public JPanel panCenter;

    public JLabel labelRes;

    public ArrayList<Exercise> listeExercices;

    /**
     * Création du panel qui permet d'importer un exercice.
     *
     * @param fen
     */
    BmgPanelImport(BmgFrame fen) {
        super();

        this.fen = fen;

        //Initialisation exercices
        listeExercices = Exercise.findAll(fen.bs);
        System.out.println("ex:"+listeExercices);
        //listeExercices = new ArrayList<>();
        
        this.setLayout(new BorderLayout());

        this.setPanel();
    }

    private void setPanelHaut() {
        //Initialisation
        panHaut = new JPanel();

        // Récupération du tableau d'exercices
        final String[] tabExercices = convertToTableau();

        // Création du menu déroulant
        final JComboBox jcb = new JComboBox(tabExercices);

        // Button OK
        JButton bok = new JButton("Ok");

        // Add Listener
        bok.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                // Index
                int index = jcb.getSelectedIndex();

                // Récupération de l'exercice
                Exercise exercise = listeExercices.get(index);

                // Affichage
                String trueFileName = tabExercices[index];
                String style = "style=\"color: blue; font-size: 12px;\"";
                
                //Label with summary
                //labelRes = new JLabel("");
                labelRes.setText("<html><p style=\"color: green;font-size: 15px;\">Résumé de : " + trueFileName + "<br/><br/></p>"
                        + "<p><span " + style + ">Title : </span>" + exercise.getTitle() + "<br/></p>"
                        + "<p><span " + style + ">Type : </span>" + exercise.getType() + "<br/></p>"
                        + "<p><span " + style + ">Difficulty : </span>" + exercise.getDifficulty() + "<br/></p>"
                        + "<p><span " + style + ">Number of questions : </span>" + exercise.getNumberOfQuestions() + "<br/></p>"
                        + "</html>");
                
                exercise.save();
                
            }
        });

        // Ajout
        panHaut.add(jcb);
        panHaut.add(bok);
        
        this.add(panHaut, BorderLayout.NORTH);
    }

    private void setPanel() {
        // Génération des autres panels
        this.setPanelHaut();
        this.setPanelCenter();

    }

    private void setPanelCenter() {
        // Initialisation
        panCenter = new JPanel();
        
        // résumé
        labelRes = new JLabel("");
        labelRes.setPreferredSize(new Dimension(400,300));
        
        // Button import
        JButton bimport = new JButton("Import");
        
        // Add Listener
        bimport.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                String style = "style=\"color: red;font-size: 15px\"";
                labelRes.setText("<html><p "+style+">OK</p></html>");
            }
        });
        
        
        // Ajout
        panCenter.add(labelRes);
        panCenter.add(bimport);
        
        this.add(panCenter, BorderLayout.CENTER);
    }

    private String[] convertToTableau() {
        //Création du tableau
        String[] res = new String[listeExercices.size()];

        // Remplissage
        int it = 0;
        for (Exercise e : listeExercices) {
            res[it] = e.getTitle();
            it++;
        }

        return res;
    }

}
