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
        if (fen.bs.testerConnexion()) {
            listeExercices = Exercise.findAll(fen.bs);
            //System.out.println("coucou: "+listeExercices);
        }
        //System.out.println("ex:"+listeExercices);
        //listeExercices = new ArrayList<>();

        this.setLayout(new BorderLayout());

        this.setPanel();
    }

    private void setPanelHaut() {
        //Initialisation
        panHaut = new JPanel();

        // Récupération du tableau d'exercices
        if(listeExercices == null) {
            listeExercices = new ArrayList<>();
        }
        final String[] tabExercices = convertToTableau();
        //System.out.println("tab"+tabExercices[0]);
        // Création du menu déroulant
        final JComboBox jcb = new JComboBox(tabExercices);

        // Button OK
        JButton bok = new JButton("Sélectionner");
        JButton bup = new JButton("Actualiser");

        // Add Listener
        bok.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                // Index
                int index = jcb.getSelectedIndex();
                System.out.println("index:"+index);
                listeExercices = Exercise.findAll(fen.bs);
                System.out.println("lex:"+listeExercices);
                // Récupération de l'exercice
                Exercise exercise = listeExercices.get(index);

                // Affichage
                String trueFileName = tabExercices[index];
                String style = "style=\"color: #E43B01; font-size: 13px;\"";

                //Label with summary
                //labelRes = new JLabel("");
                labelRes.setText("<html><p style=\"color: rgb(45,45,45);font-size: 15px;\">Résumé de : " + trueFileName + "<br/><br/></p>"
                        + "<p><span " + style + ">Titre : </span>" + exercise.getTitle() + "<br/></p>"
                        + "<p><span " + style + ">Type : </span>" + exercise.getType() + "<br/></p>"
                        + "<p><span " + style + ">Difficulté : </span>" + exercise.getDifficulty() + "<br/></p>"
                        + "<p><span " + style + ">Nombre de questions : </span>" + exercise.getNumberOfQuestions() + "<br/></p>"
                        + "</html>");

                exercise.save();

            }
        });
        
        bup.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                ArrayList<Exercise> list = Exercise.findAll(fen.bs);
                //System.out.println("list"+list);
                updateListExercises(jcb,list,tabExercices);
            }
        });

        // Ajout
        panHaut.add(jcb);
        panHaut.add(bok);
        panHaut.add(bup);

        this.add(panHaut, BorderLayout.NORTH);
    }

    public void updateListExercises(JComboBox j, ArrayList<Exercise> l, String[] t) {
        j.removeAllItems();
        this.listeExercices = l;
        t = convertToTableau();
        for (String st : t) {
            j.addItem(st);
        }
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
        labelRes.setPreferredSize(new Dimension(400, 300));

        // Button import
        JButton bimport = new JButton("Importer l'exercice");

        // Add Listener
        bimport.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                String style = "style=\"color: #E43B01;font-size: 15px\"";
                labelRes.setText("<html><p " + style + ">OK</p></html>");
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
        //System.out.println("koko: "+listeExercices.get(0));
        for (Exercise e : listeExercices) {
            System.out.println("kaka");
            res[it] = e.getTitle();
            it++;
        }
        //System.out.println("koko: "+listeExercices.get(0));

        //System.out.println("res :"+res[0]);
        return res;
    }

}
