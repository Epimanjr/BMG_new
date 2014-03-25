/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import model.Exercise;

/**
 *
 * @author blaise
 */
public class BmgPanelExport extends JPanel {

    String filename = "";
    JLabel labelRes = new JLabel("Aucun fichier sélectionné.");
    JButton buttonOk = new JButton("GO");
    JButton boutonExportPDF;
    BmgFrame fen;

    Exercise ex = null;
    public BmgPanelExport(final BmgFrame fen, String name) {
        //Some settings
        this.filename = name;
        this.fen = fen;
        int nb = 10;
        String color = "#E43B01";

        //Label at first
        JPanel panFirst = new JPanel();
        panFirst.setPreferredSize(new Dimension(fen.width, fen.height / nb));
        final BmgLabel label = new BmgLabel("Exporter vos exercices en ligne ou basculer vers une utilisation papier en les exportant au format PDF pour impression.", color);
        panFirst.add(label);

        //Button choose file
        JButton choosefile = new JButton("Sélectionner l'exercice ...");

        choosefile.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser jfc = new JFileChooser();
                //FileNameExtensionFilter filter = new FileNameExtensionFilter("bmg");
                //jfc.setCurrentDirectory(new File("$HOME/Documents"));
                //jfc.setFileFilter(filter);
                String monFichier = "";
                String approve = "Enregistrer";
                int resultatEnregistrer = jfc.showDialog(jfc, approve);
                if (resultatEnregistrer == JFileChooser.APPROVE_OPTION) {
                    monFichier = jfc.getSelectedFile().toString();
                    if (monFichier.endsWith(".bmg") || monFichier.endsWith(".BMG")) {

                    } else {
                        //monFichier += ".bmg";
                    }
                }
                filename = monFichier;
                if (filename.equals("")) {
                    labelRes.setText("Aucun fichier sélectionné.");
                } else {
                    String[] monFichierSplit = monFichier.split("/");
                    String trueFileName = monFichierSplit[monFichierSplit.length - 1];

                    //Exercise exercise = new Exercise();
                    FileReader file;
                    try {
                        file = new FileReader(filename);
                        BufferedReader br = new BufferedReader(file);

                        //System.out.println(br.toString());

                    } catch (FileNotFoundException ex) {
                        Logger.getLogger(BmgPanelPracticeFirst.class.getName()).log(Level.SEVERE, null, ex);
                    }

                    //System.out.println(filename);

                    final Exercise exercise = Exercise.load(filename);
                    ex = exercise;

                    if (exercise == null) {
                        //System.out.println("EXERCISE NULL");
                    }

                    String style = "style=\"color: rgb(45,45,45); font-size: 13px;\"";
                    //Label with summary
                   labelRes.setText("<html><p style=\"color: #E43B01;font-size: 15px;\">Résumé de l'exercice : " + trueFileName + "<br/><br/></p>"
                            + "<p><span " + style + ">Titre : </span>" + exercise.getTitle() + "<br/></p>"
                            + "<p><span " + style + ">Type : </span>" + exercise.getType() + "<br/></p>"
                            + "<p><span " + style + ">Difficulté : </span>" + exercise.getDifficulty() + "<br/></p>"
                            + "<p><span " + style + ">Nombre de questions : </span>" + exercise.getNumberOfQuestions() + "<br/></p>"
                            + "</html>");

                }

            }
        });

        this.add(panFirst);

        JPanel panChoose = new JPanel();
        panChoose.setPreferredSize(new Dimension(fen.width, 100));
        panChoose.add(choosefile);
        this.add(panChoose);

        JPanel panSouth = new JPanel();
        panSouth.setPreferredSize(new Dimension(fen.width, 500));

        panSouth.add(labelRes);

        //Button GO
        buttonOk = new JButton("Exporter en ligne");
        //Listener
        buttonOk.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                actionExporter(ex);
            }
        });
        
        boutonExportPDF = new JButton("Exporter au format PDF");
        boutonExportPDF.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                // On exporte en PDF
                actionExporterPDF(ex);
            }
        });
        
        panSouth.add(buttonOk);
        panSouth.add(boutonExportPDF);

        this.add(panSouth);

    }
    
    public void actionExporterPDF(Exercise e) {
        String nomFichier = "";
        String approve = "Enregistrer";
       
        JFileChooser jfc = new JFileChooser();
        
        int resultatEnregistrer = jfc.showDialog(jfc, approve);
        
        if(JFileChooser.APPROVE_OPTION == resultatEnregistrer) {
            nomFichier = jfc.getSelectedFile().toString();
            
            e.exportToFile(nomFichier + ".pdf");
        }
    }
    
    public void actionExporter(Exercise e) {
        e.insert(fen.bs);
    }

    public void addSomething(Component c) {
        this.add(c);
    }

}
