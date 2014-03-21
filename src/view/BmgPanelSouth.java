package view;

// Here all imports needed for this class.
import javax.swing.JPanel;

/**
 * @Class BmgPanelSouth, which ...
 * @Author Maxime Blaise
 */
public class BmgPanelSouth extends JPanel {

    /**
     * Constructor which creates south panel.
     */
    public BmgPanelSouth(int width) {
        super();

        //Label
        this.add(new BmgLabel("©M.Blaise, A.Nosal, J.Rische, J.Dzimbalka, for BMG 2013/2014", "green"));
    }
}
