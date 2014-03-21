
import database.BaseInformation;
import view.BmgFrame;
import view.Automate;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author blaise
 */
public class Principale {

    public static void main(String[] args) {
        BaseInformation baseInformation = new BaseInformation("mysql", "com.mysql.jdbc.Driver", "BMG_DB", "root", "", "//localhost");
        if (args.length == 1) {
            System.out.println("Args 1");
            Automate a = new Automate(args);

        } else {
            BmgFrame bmgFrame = new BmgFrame("BMG 2014", 800, 600, true);
        }
    }

}
