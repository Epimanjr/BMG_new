/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import model.Exercise;
import model.QuestionCustom;
import model.Wording;

/**
 *
 * @author blaise
 */
public class BmgPanelCustom extends JPanel {
    
    BmgFrame fen;
    
    public BmgPanelCustom(BmgFrame fen) {
        super();
        this.fen = fen;
        
        this.setLayout(new BorderLayout());
        
        JPanel panHaut = new JPanel();
        panHaut.setLayout(new GridLayout(3, 2));

        // Labels
        JLabel label1 = new JLabel("Nom de l'exercice : ");
        JLabel label2 = new JLabel("Difficulté : ");
        JLabel label3 = new JLabel("Enoncé : ");
        
        label1.setPreferredSize(new Dimension(200, 50));
        label2.setPreferredSize(new Dimension(200, 50));
        label3.setPreferredSize(new Dimension(200, 50));
        
        final JTextField saisieNom = new JTextField(20);
        final JTextField saisieEnonce = new JTextField(20);

        // Slide
        final JSlider slide = new JSlider();
        final JLabel labelInfo = new JLabel("Valeur actuelle : " + 2);
        
        slide.setMaximum(5);
        slide.setMinimum(1);
        slide.setValue(2);
        slide.setPaintTicks(true);
        slide.setPaintLabels(true);
        slide.setMinorTickSpacing(1);
        slide.setMajorTickSpacing(1);
        slide.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent event) {
                labelInfo.setText("Valeur actuelle : "
                        + ((JSlider) event.getSource()).getValue());
            }
        });

        // Ajout des composants
        panHaut.add(label1);
        panHaut.add(saisieNom);
        panHaut.add(label2);
        panHaut.add(slide);
        panHaut.add(label3);
        panHaut.add(saisieEnonce);
        
        this.add(panHaut, BorderLayout.NORTH);
        
        JPanel panCenter = new JPanel();
        panCenter.setLayout(new GridLayout(5, 3));
        
        final JTextField[] tabQuestions = new JTextField[5];
        final JTextField[] tabSolutions = new JTextField[5];
        final JComboBox[] tabTypes = new JComboBox[5];
        
        final String[] types = {"entier", "réel", "chaine"};
        
        panCenter.add(new BmgLabel("Question", "green"));
        panCenter.add(new BmgLabel("Type solution", "green"));
        panCenter.add(new BmgLabel("Solution", "green"));
        
        for (int i = 1; i < 5; i++) {
            tabQuestions[i] = new JTextField(12);
            panCenter.add(tabQuestions[i]);
            tabTypes[i] = new JComboBox(types);
            panCenter.add(tabTypes[i]);
            tabSolutions[i] = new JTextField(12);
            panCenter.add(tabSolutions[i]);
        }
        
        this.add(panCenter, BorderLayout.CENTER);
        
        JPanel panSouth = new JPanel();
        panSouth.setLayout(new BoxLayout(panSouth, BoxLayout.PAGE_AXIS));
        
        JButton createEx = new JButton("Créer l'exercice");
        createEx.setPreferredSize(new Dimension(fen.height, 100));
        createEx.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent ae) {
                
                String name = saisieNom.getText();
                String wording = saisieEnonce.getText();
                int diff = slide.getValue();
                
                Exercise e = new Exercise(name, "custom");
                e.setWording(new Wording(wording));
                e.setDifficulty(diff);
                
                String q1 = tabQuestions[1].getText();
                String t1 = types[tabTypes[1].getSelectedIndex()];
                String s1 = tabSolutions[1].getText();
                System.out.println("s1"+s1);
                
                switch(t1) {
                    case "entier":
                        Integer[] ti = {new Integer(s1)};
                        QuestionCustom<Integer> qcu1 = new QuestionCustom<Integer>(q1,ti);
                        e.addQuestion(qcu1);
                        break;
                }
                
                System.out.println("e:"+e);
                e.save();
                
            }
        }
        );
        
        JLabel labelSouth = new JLabel("");
        labelSouth.setPreferredSize(new Dimension(800, 100));
        
        panSouth.add(labelSouth);
        panSouth.add(createEx);
        
        this.add(panSouth, BorderLayout.SOUTH);
        
    }
}
