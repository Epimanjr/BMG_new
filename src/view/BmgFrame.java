/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

// Here all imports needed for this class.
import database.BaseSetting;
import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JPanel;
import user.User;

//Bonjour
/**
 * Class BmgFrame
 *
 * @author Maxime Blaise
 */
public final class BmgFrame extends JFrame {

    //Needed fields.
    BaseSetting bs = new BaseSetting();
    int width;
    int height;

    //Menu bar and label in south, generate only once.
    BmgPanelMenu panMenu;
    BmgPanelSouth panSouth;

    //Main pan!!
    BmgPanel pan;

    // All panel needed for our program.
    static JPanel panMain;
    static JPanel panSettings;
    static JPanel panSignup;
    static JPanel panSignin;
    static JPanel panAboutUs;
    static JPanel panGenerate;
    static JPanel panPractice;
    static JPanel panExport;
    static JPanel panImport;

    BmgCreatePanel bcp;

    static String labelConnection = ">Not connected yet";

    User currentUser = null;

    boolean nobegin = false;

    /**
     * Constructor who create the main window of BMG.
     *
     * @param string
     * @param width
     * @param height
     * @param nb
     */
    public BmgFrame(String string, int width, int height, boolean nb) {
        super(string);
        this.width = width;
        this.height = height;
        this.nobegin = nb;

        //Create these panel.
        panMenu = new BmgPanelMenu(fen());
        panSouth = new BmgPanelSouth(width);

        bcp = new BmgCreatePanel(fen(), bs, width, height);

        //Create all panels for our program, once at beginning only !
        setAllPanels();

        //Frame settings
        setFrameSettings();
    }

    /**
     * This method aims at creating all panels at the beginning.
     *
     */
    private void setAllPanels() {

        panMain = bcp.createMainPanel();
        panSettings = bcp.createPanelSettings();
        panSignin = bcp.createPanelSignin();
        panSignup = bcp.createPanelSignup("", "");
        panAboutUs = bcp.createPanelAboutUs();
        panGenerate = bcp.createPanelGenerateExercises();
        panPractice = bcp.createPanelPractice();
        panExport = new BmgPanelExport(fen(), "");
        panImport = new BmgPanelImport(fen());
    }

    /**
     * Method who change window's settings.
     */
    private void setFrameSettings() {

        //All settings
        this.setPanel(panMain);
        this.setLocation(200, 100);
        this.setPreferredSize(new Dimension(width, height));
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.pack();
        this.setVisible(true);

        //Begin frame, just once !
        if (nobegin) {
            BmgFrameBegin bmgFrameBegin = new BmgFrameBegin(bs, fen());

        }
    }

    /**
     * Return this frame.
     *
     * @return
     */
    public BmgFrame fen() {
        return this;
    }

    /**
     * Change only the center panel !
     *
     * @param panel
     */
    public void setPanel(JPanel panel) {
        //System.out.println(labelConnection);
        this.setContentPane(new BmgPanel(fen(), panel));
        this.pack();
    }

    public void setPanSignUp(String string, String string0) {
        panSignup = bcp.createPanelSignup(string, string0);
        setPanel(panSignup);
    }

    public String getLabelConnection() {
        return BmgFrame.labelConnection;
    }

    public void setLabelConnection(String s) {
        BmgFrame.labelConnection = s;
        this.panMenu = new BmgPanelMenu(fen());
        this.panMenu.repaint();

        this.panMenu.menu.repaint();
    }

}
