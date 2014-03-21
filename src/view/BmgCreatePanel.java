/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

// Here all imports needed for this class.
import database.BaseInformation;
import database.BaseSetting;
import exceptions.AccessDeniedException;
import exceptions.AlreadyExistsException;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import model.Exercise;
import user.User;

/**
 * class BmgCreatePanel
 *
 * @author Maxime Blaise
 */
public class BmgCreatePanel {

    BmgFrame fen;
    BaseSetting bs;
    int width;
    int height;
    String[] listSchool;

    public BmgCreatePanel(BmgFrame fen, BaseSetting bs, int width, int height) {
        this.fen = fen;
        this.bs = bs;
        this.listSchool = Manipulation.getAllSchoolName(bs);

        this.width = width;
        this.height = height;
    }

    /**
     * This method create the main panel.
     *
     * @param fen
     * @param bs
     * @param width
     * @param height
     * @return
     */
    public JPanel createMainPanel() {
        // Some settings for this panel, size, color ...
        int nb = 5;
        String colortitle = "green";
        String colortext = "black";

        //Create the main panel
        JPanel pan = new JPanel();

        //Panel Exercises
        JPanel panExercises = new JPanel();
        panExercises.setPreferredSize(new Dimension(width - 100, ((height - 100) / 2)));
        panExercises.setBorder(BorderFactory.createTitledBorder("<html><p style=\"color: " + colortitle + ";\">Training with exercises !</p></html>"));
        panExercises.setLayout(new GridLayout(5, 2));

        //Button Generate in panel Exercises.
        panExercises.add(new BmgLabel("Automatically generate exercises : ", colortext));
        BmgButton bgenerate = new BmgButton("Generate");
        //listener
        bgenerate.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                fen.setPanel(BmgFrame.panGenerate);
            }
        });
        panExercises.add(bgenerate);

        //Button Practice in panel Exercises.
        panExercises.add(new BmgLabel("Let's practice here : ", colortext));
        BmgButton bpractice = new BmgButton("Practice");
        //listener
        bpractice.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                fen.setPanel(BmgFrame.panPractice);
            }
        });
        panExercises.add(bpractice);
        
         //Button Import in panel Exercises.
        panExercises.add(new BmgLabel("Importez les exercices en lignes : ", colortext));
        BmgButton bimport = new BmgButton("Import");
        //listener
        bimport.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                fen.setPanel(BmgFrame.panImport);
            }
        });
        panExercises.add(bimport);
        
        //Button Export in panel Exercises.
        panExercises.add(new BmgLabel("Exportez en ligne ou en PDF : ", colortext));
        BmgButton bexport = new BmgButton("Export");
        //listener
        bexport.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                fen.setPanel(BmgFrame.panExport);
            }
        });
        panExercises.add(bexport);
        
        //Button Solve in panel Exercises.
        panExercises.add(new BmgLabel("Résoudre les exercices : ", colortext));
        BmgButton bsolve = new BmgButton("Solve");
        //listener
        bsolve.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
               // fen.setPanel(BmgFrame.panS);
            }
        });
        panExercises.add(bsolve);
        

        //Panel Account
        JPanel panAccount = new JPanel();
        panAccount.setPreferredSize(new Dimension(width - 100, ((height - 100) / nb)));
        panAccount.setBorder(BorderFactory.createTitledBorder("<html><p style=\"color: " + colortitle + ";\">Account setting !</p></html>"));
        panAccount.setLayout(new GridLayout(2, 2));

        //Add label
        panAccount.add(new BmgLabel("Sign in here : ", colortext));

        //Button Sign in in panel Account
        BmgButton bsignin = new BmgButton("Sign in");
        bsignin.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                fen.setPanel(BmgFrame.panSignin);
            }
        });
        panAccount.add(bsignin);

        //Button Sign up in panel Account
        panAccount.add(new BmgLabel("You should create an account if you don't have ", colortext));
        BmgButton bsignup = new BmgButton("Sign up now !");
        bsignup.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                fen.setPanel(BmgFrame.panSignup);
            }
        });
        panAccount.add(bsignup);

        //Panel Database Settings
        JPanel panSettings = new JPanel();
        panSettings.setPreferredSize(new Dimension(width - 100, ((height - 100) / nb)));
        panSettings.setBorder(BorderFactory.createTitledBorder("<html><p style=\"color: " + colortitle + ";\">Database setting !</p></html>"));
        panSettings.setLayout(new GridLayout(2, 2));

        //Add label
        panSettings.add(new BmgLabel("Change database settings : ", colortext));

        //Button Setting in panel Database Settings
        BmgButton bsettings = new BmgButton("Settings");
        bsettings.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                fen.setPanel(BmgFrame.panSettings);
            }
        });
        panSettings.add(bsettings);

        //Add label and button test in panel Database settings
        panSettings.add(new BmgLabel("Test your database connexion : ", colortext));
        BmgButton btest = new BmgButton("-->Test");
        btest.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                testConnection(bs);
            }
        });
        panSettings.add(btest);

        //Add all panels in main panel
        pan.add(panExercises);
        pan.add(panAccount);
        pan.add(panSettings);

        return pan;
    }

    public BmgPanelPracticeFirst createPanelPractice() {
        return new BmgPanelPracticeFirst(fen, "");
    }

    /**
     * This method create the panel sign in.
     *
     * @return
     */
    public JPanel createPanelSignin() {
        //Some settings of this panel.
        int nb = 8;
        String labelcolor = "red";

        //Create this panel here
        JPanel pan = new JPanel();

        BmgLabel label = new BmgLabel("You have an account ? Sign in now ", labelcolor);
        label.setPreferredSize(new Dimension(width - 100, (height - 100) / (nb)));
        pan.add(label, BorderLayout.NORTH);

        //Center panel, in which user can put information.
        JPanel panCenter = new JPanel();
        panCenter.setLayout(new GridLayout(2, 2));
        panCenter.setPreferredSize(new Dimension(width - 100, (height - 100) / nb));

        //TextField
        final JTextField saisieEmail = new JTextField(15);
        final JPasswordField saisiePass = new JPasswordField(15);
        //Labels

        JLabel[] labels = {
            new JLabel("Email address : "),
            new JLabel("Password : "),};

        //Add text field and label into panels.
        panCenter.add(labels[0]);
        panCenter.add(saisieEmail);
        panCenter.add(labels[1]);
        panCenter.add(saisiePass);

        //Create panel South , just for the moment a button.
        JPanel panSouth = new JPanel();
        panSouth.setLayout(new BorderLayout());

        //Button sign in
        BmgButton buttonSignin = new BmgButton("Sign in now !");
        buttonSignin.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                char[] c = saisiePass.getPassword();
                String pass = new String(c);
                actionSignIn(fen, bs, saisieEmail.getText(), pass);

            }
        });

        KeyListener key = new KeyListener() {

            @Override
            public void keyTyped(KeyEvent ke) {

            }

            @Override
            public void keyPressed(KeyEvent ke) {
                //if enter
                if (ke.getKeyCode() == KeyEvent.VK_ENTER) {
                    char[] c = saisiePass.getPassword();
                    String pass = new String(c);
                    actionSignIn(fen, bs, saisieEmail.getText(), pass);
                }
            }

            @Override
            public void keyReleased(KeyEvent ke) {

            }
        };

        //Add Key Listener 
        saisiePass.addKeyListener(key);
        saisieEmail.addKeyListener(key);

        panSouth.add(buttonSignin, BorderLayout.NORTH);

        //Add all panels into main panel.
        pan.add(panCenter, BorderLayout.CENTER);
        pan.add(panSouth, BorderLayout.SOUTH);

        return pan;
    }

    /**
     * This method create the panel sign up
     *
     * @param email
     * @param password
     * @return
     */
    public JPanel createPanelSignup(String email, String password) {
        //Some settings
        int nb = 3;
        String labelcolor = "red";

        //Create panel
        JPanel pan = new JPanel();

        BmgLabel label = new BmgLabel("Sign up now, for free of course : ", labelcolor);
        label.setPreferredSize(new Dimension(width - 100, (height - 100) / (2 * nb)));
        pan.add(label, BorderLayout.NORTH);

        //Center panel
        JPanel panCenter = new JPanel();
        panCenter.setLayout(new GridLayout(6, 2));
        panCenter.setPreferredSize(new Dimension(width - 100, (height - 100) / nb));

        //TextFields
        final JTextField[] jtfs = {
            new JTextField(15),
            new JTextField(15),
            new JTextField(15),};

        //String[] listSchool = School.;
        final JComboBox<String> jcb = new JComboBox<String>(listSchool);
        final JPasswordField jpf = new JPasswordField(15);

        //Labels
        JLabel[] labels = {
            new JLabel("Firstname : "),
            new JLabel("Lastname : "),
            new JLabel("School's name : "),
            new JLabel("Email : "),
            new JLabel("Password : "),};

        //Fill in some blank with parameters
        jtfs[2].setText(email);
        jpf.setText(password);

        //Add components to panel
        panCenter.add(labels[0]);
        panCenter.add(jtfs[0]);
        panCenter.add(labels[1]);
        panCenter.add(jtfs[1]);
        panCenter.add(labels[2]);
        panCenter.add(jcb);
        panCenter.add(labels[3]);
        panCenter.add(jtfs[2]);
        panCenter.add(labels[4]);
        panCenter.add(jpf);

        //Create panSouth, with button
        JPanel panSouth = new JPanel();
        panSouth.setLayout(new BorderLayout());

        BmgButton buttonSignup = new BmgButton("Sign up now !");
        buttonSignup.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                actionSignUp(jtfs, jcb, jpf);

            }
        });

        //KeyListener 
        KeyListener key = new KeyListener() {

            @Override
            public void keyTyped(KeyEvent ke) {
            }

            @Override
            public void keyPressed(KeyEvent ke) {
                //Action s'il appui sur la touche entrée
                if (ke.getKeyCode() == KeyEvent.VK_ENTER) {
                    //Méthode méthode que l'action avec la souris
                    actionSignUp(jtfs, jcb, jpf);
                }
            }

            @Override
            public void keyReleased(KeyEvent ke) {
            }
        };

        //Add key listener
        for (JTextField jtfAjoutListener : jtfs) {
            jtfAjoutListener.addKeyListener(key);
        }

        //Add key listener in JPassWordField
        jpf.addKeyListener(key);

        panSouth.add(buttonSignup, BorderLayout.NORTH);

        //Add to panel sign up
        pan.add(panCenter, BorderLayout.CENTER);
        pan.add(panSouth, BorderLayout.SOUTH);

        return pan;
    }

    /**
     * This method create the panel settings.
     *
     * @return
     */
    public JPanel createPanelSettings() {
        //Some settings
        int nb = 2;

        //Create panel
        JPanel pan = new JPanel();

        JLabel label = new JLabel("");
        label.setPreferredSize(new Dimension(width - 100, (height - 100) / (nb * 2)));

        //Center Panel
        JPanel panCenter = new JPanel();
        panCenter.setPreferredSize(new Dimension(width - 100, (height - 100) / nb));

        //TextFields
        final JTextField[] jtfs = {
            new JTextField(15),
            new JTextField(15),
            new JTextField(15),
            new JTextField(15),
            new JTextField(15),
            new JTextField(15)
        };

        //Labels
        JLabel[] labels = {
            new JLabel("Pilote : "),
            new JLabel("Driver : "),
            new JLabel("Database name : "),
            new JLabel("Login : "),
            new JLabel("Password : "),
            new JLabel("Url : ")
        };

        //Add components to panel
        panCenter.setLayout(new GridLayout(6, 2));
        for (int i = 0; i < jtfs.length; i++) {
            panCenter.add(labels[i]);
            panCenter.add(jtfs[i]);
        }

        //Button and label, to register
        JButton buttonConfirm = new JButton("Register information");
        final BmgLabel labelRes = new BmgLabel("Waiting ...", "green");
        buttonConfirm.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                BaseInformation baseInformation = new BaseInformation(jtfs[0].getText(), jtfs[1].getText(), jtfs[2].getText(), jtfs[3].getText(), jtfs[4].getText(), jtfs[5].getText());
                JOptionPane jop = new JOptionPane();
                JOptionPane.showMessageDialog(null, "Registration success !", "Database Connexion", JOptionPane.INFORMATION_MESSAGE);
                bs.setInfo();
            }
        });

        //Button and label, to test
        JButton buttonTest = new JButton("Connexion test");
        final BmgLabel labelTest = new BmgLabel("Waiting ...", "red");
        buttonTest.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                BaseInformation baseInformation = new BaseInformation(jtfs[0].getText(), jtfs[1].getText(), jtfs[2].getText(), jtfs[3].getText(), jtfs[4].getText(), jtfs[5].getText());

                bs.setInfo();
                testConnection(bs);

                /*if(bs.testerConnexion()) {
                 labelTest.set("Success !!");
                 } else {
                 labelTest.set("Error !!");
                 }*/
            }

        });

        //
        jtfs[0].setText(bs.getBi().getDriver());
        jtfs[1].setText(bs.getBi().getNamedriver());
        jtfs[2].setText(bs.getBi().getDbname());
        jtfs[3].setText(bs.getBi().getLogin());
        jtfs[4].setText(bs.getBi().getPassword());
        jtfs[5].setText(bs.getBi().getUrl());

        //PanSouth, with button confirm and test
        JPanel panSouth = new JPanel();
        panSouth.add(buttonConfirm);
        panSouth.add(buttonTest);

        //Add panels to main panel
        pan.add(label, BorderLayout.NORTH);
        pan.add(panCenter, BorderLayout.CENTER);
        pan.add(panSouth, BorderLayout.SOUTH);

        return pan;
    }

    /**
     * Test connection and print a popup which print result.
     *
     * @param bs
     */
    public void testConnection(BaseSetting bs) {

        JOptionPane jop = new JOptionPane();

        if (bs.testerConnexion()) {
            //Success
            JOptionPane.showMessageDialog(null, "Success !", "Database Connexion", JOptionPane.INFORMATION_MESSAGE);
        } else {
            //Error
            JOptionPane.showMessageDialog(null, "Error !", "Database Connexion", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Create panel in order to generate exercises.
     *
     * @return panel
     */
    public JPanel createPanelGenerateExercises() {
        //create

        // Données : 
        int longueurExercice = 10;

        JPanel pan = new JPanel();

        // Panel haut
        JPanel panHaut = new JPanel();
        panHaut.add(new BmgLabel("Here you can (not yet) generate exercises : ", "red"));

        // Panel center
        JPanel panCenter = new JPanel();

        final String[] tabChoixType = {
            "Calcul arithmétique",
            "Calcul fractionnaire",
            "Equation",
            "Puissance"
        };
        final JComboBox choixType = new JComboBox(tabChoixType);

        // Slide
        final JSlider slide = new JSlider();
        final JLabel labelInfo = new JLabel("Valeur actuelle : " + 10);

        slide.setMaximum(30);
        slide.setMinimum(2);
        slide.setValue(10);
        slide.setPaintTicks(true);
        slide.setPaintLabels(true);
        slide.setMinorTickSpacing(3);
        slide.setMajorTickSpacing(3);
        slide.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent event) {
                labelInfo.setText("Valeur actuelle : "
                        + ((JSlider) event.getSource()).getValue());
            }
        });

        // Center1
        JPanel panCenter1 = new JPanel();
        panCenter1.setPreferredSize(new Dimension(fen.width, 200));

        panCenter1.setLayout(new GridLayout(4, 3));
        String couleur = "black";

        final JTextField choixNom = new JTextField(12);
        panCenter1.add(new BmgLabel("Nom de l'exercice : ", couleur));
        panCenter1.add(choixNom);
        panCenter1.add(new JLabel(""));
        panCenter1.add(new BmgLabel("Type : ", couleur));
        panCenter1.add(choixType);

        JButton boutonAleatoire = new JButton("Aléatoire !");
        boutonAleatoire.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                //TODO
                double numeroAleatoire = Math.random() * 30;
                slide.setValue((int) numeroAleatoire);

                labelInfo.setText("Valeur actuelle : " + (int) numeroAleatoire);

                numeroAleatoire = Math.random() * 4;
                int num = (int) numeroAleatoire;

                choixType.setSelectedIndex(num);
            }
        });

        panCenter1.add(boutonAleatoire);
        panCenter1.add(new BmgLabel("Nombre de questions : ", couleur));
        panCenter1.add(slide);
        panCenter1.add(labelInfo);

        final JRadioButton jrbPlus = new JRadioButton("+");
        final JRadioButton jrbMoins = new JRadioButton("-");
        final JRadioButton jrbFois = new JRadioButton("*");
        final JRadioButton jrbDiv = new JRadioButton("/");

        panCenter1.add(new BmgLabel("Opérateurs : ", couleur));

        JPanel panCenter11 = new JPanel();
        panCenter11.setLayout(new GridLayout(2, 2));
        panCenter11.add(jrbPlus);
        panCenter11.add(jrbMoins);
        panCenter11.add(jrbFois);
        panCenter11.add(jrbDiv);
        
        panCenter1.add(panCenter11);

        // Valider
        final JPanel panCenter2 = new JPanel();
        panCenter2.setPreferredSize(new Dimension(fen.width / 2, fen.height / 4));
        panCenter2.setLayout(new BorderLayout());

        final BmgLabel labelSum = new BmgLabel("", "red");
        labelSum.setPreferredSize(new Dimension(fen.width, 100));

        // Confirm
        final JButton confirm = new JButton("Confirmer");
        //confirm.setPreferredSize(new Dimension(fen.width/2, 50));
        confirm.setVisible(false);

        JButton valider = new JButton("Valider");

        final BmgLabel labFin = new BmgLabel("", "green");
        labFin.setVisible(false);

        valider.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                //TODO

                // Récap
                String s = "Vous avez choisi<br/>";

                s += "Nom : " + choixNom.getText() + "<br/>";
                s += "Type : " + tabChoixType[choixType.getSelectedIndex()] + "<br/>";
                s += "Nombre de questions : " + slide.getValue() + "<br/>";

                // Génération des opérateurs
                final ArrayList<Character> operateurs = new ArrayList<>();
                if (jrbPlus.isSelected()) {
                    operateurs.add('+');
                }
                if (jrbMoins.isSelected()) {
                    operateurs.add('-');
                }
                if (jrbFois.isSelected()) {
                    operateurs.add('*');
                }
                if (jrbDiv.isSelected()) {
                    operateurs.add('/');
                }

                s += "Opérateurs : ";
                for (char c : operateurs) {
                    s += " " + c;
                }
                s += "<br/>";

                labelSum.set(s);

                // Listener confirm
                confirm.addActionListener(new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent ae) {
                        // Récupération du type
                        String type = getType(choixType.getSelectedIndex());
                        
                        // Création de l'exercice
                        Exercise e = new Exercise(choixNom.getText(), type);
                        e.generate(slide.getValue(), operateurs);
                        e.save();
                        
                        labFin.set("Success !");
                        labFin.setVisible(true);
                    }

                    private String getType(int selectedIndex) {
                        String type = "";
                        switch(selectedIndex) {
                            case 0:
                                type = "calculation";
                                break;
                            case 1: 
                                type = "fraction";
                                break;
                            case 2: 
                                type = "equation";
                                break;
                            case 3:
                                type = "power";
                                break;
                        }
                        
                        return type;
                    }
                });
                confirm.setVisible(true);

            }
        });

        panCenter2.add(valider, BorderLayout.NORTH);
        panCenter2.add(labelSum, BorderLayout.CENTER);

        JPanel panCenterTmp = new JPanel();
        panCenterTmp.setLayout(new BoxLayout(panCenterTmp, BoxLayout.PAGE_AXIS));
        panCenterTmp.add(confirm);
        panCenterTmp.add(labFin);

        panCenter2.add(panCenterTmp, BorderLayout.SOUTH);

        // Layout
        pan.setLayout(new BorderLayout());
        pan.add(panHaut, BorderLayout.NORTH);

        panCenter.add(panCenter1);
        panCenter.add(panCenter2);

        pan.add(panCenter, BorderLayout.CENTER);

        return pan;
    }

    /**
     * Method who create panel AboutUs, which print some information about our
     * team.
     *
     * @return
     */
    public JPanel createPanelAboutUs() {
        JPanel pan = new JPanel();

        String s = "<span style=\"color: red;\">About us : </span><br/><br/>";
        s += "We are 4 students of Technical High School called Université de Lorraine, in french.<br/>";
        s += "And I have no idea to continue this label ...";

        pan.add(new BmgLabel(s, "blue"));

        return pan;
    }

    /**
     * This method is called when user push sign in button.
     *
     * @param fen
     * @param bs
     * @param email
     * @param password
     */
    public static void actionSignIn(BmgFrame fen, BaseSetting bs, String email, String password) {
        try {

            JOptionPane jop = new JOptionPane();

            if (User.setConnected(bs, email, password)) {
                JOptionPane.showMessageDialog(null, "Sign in success !", "Sign up information", JOptionPane.INFORMATION_MESSAGE);

                //Edit label menu bar
                fen.panMenu.setLabel(">Connected as " + email);

                //Edit instance User
                fen.setPanel(BmgFrame.panMain);
            } else {
                JOptionPane.showMessageDialog(null, "Error, false !", "Sign up information", JOptionPane.ERROR_MESSAGE);

            }

        } catch (AccessDeniedException ex) {
            //Logger.getLogger(BmgCreatePanel.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Access denied !", "Sign up information", JOptionPane.ERROR_MESSAGE);

        }
    }

    /**
     * This method is called when user push sign up button.
     *
     * @param saisies
     * @param jcb
     * @param jpf
     */
    public void actionSignUp(JTextField[] saisies, JComboBox jcb, JPasswordField jpf) {
        //Try to register
        try {
            JOptionPane jop = new JOptionPane();
            String school = listSchool[jcb.getSelectedIndex()];

            /*
             Je ne sais pas ce que viens faire cette ligne ici
             int i_school = Integer.parseInt(school);
             */
            char[] c = jpf.getPassword();
            String password = new String(c);
            if (User.signUp(bs, 1, saisies[0].getText(), saisies[1].getText(), listSchool[jcb.getSelectedIndex()], saisies[2].getText(), password)) {
                //Sign up success !
                JOptionPane.showMessageDialog(null, "Sign up success !", "Sign up information", JOptionPane.INFORMATION_MESSAGE);

            } else {
                //Error ! don't know why
                JOptionPane.showMessageDialog(null, "Error when sign up !", "Sign up information", JOptionPane.ERROR_MESSAGE);

            }

        } catch (AlreadyExistsException ex) {
            //Exception, raise when account already exist
            JOptionPane.showMessageDialog(null, "Error ! Account already exist !", "Sign up information", JOptionPane.ERROR_MESSAGE);

        }
    }
    //Old code, but still useful sometimes.

    /* public static JPanel createMainPanel2(int width, int height) {
     JPanel panPrincipal = new JPanel();
     panPrincipal.setLayout(new BorderLayout());

     JPanel pan = new JPanel();

     pan.setLayout(new GridLayout(5, 1));
     pan.add(new Button("PRACTICE"));
     pan.add(new Button("GENERATE"));

     Button bSettings = new Button("Settings");
     bSettings.addActionListener(new ActionListener() {

     @Override
     public void actionPerformed(ActionEvent e) {

     //setPanel(new PanelSettings(bs.getBaseInformations(), fen()));
     }
     });
     Button bSignin = new Button("Sign in");
     bSignin.addActionListener(new ActionListener() {

     @Override
     public void actionPerformed(ActionEvent e) {
     // setPanel(new PanelSignin(bs, fen()));
     }
     });
     Button bConnexion = new Button("Connexion");
     bConnexion.addActionListener(new ActionListener() {

     @Override
     public void actionPerformed(ActionEvent e) {
     // setPanel(new PanelConnexion(bs, fen()));
     }
     });
     pan.add(bSettings);
     pan.add(bSignin);
     pan.add(bConnexion);

     JPanel panHaut = new JPanel();
     //panHaut.setLayout(new GridLayout(2,1));
     panHaut.setLayout(new BoxLayout(panHaut, BoxLayout.PAGE_AXIS));

     panPrincipal.add(panHaut, BorderLayout.NORTH);
     panPrincipal.add(pan, BorderLayout.CENTER);
     return panPrincipal;
     }*/
}
