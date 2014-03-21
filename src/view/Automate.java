package view;

import java.util.Scanner;
import database.BaseSetting;
import java.sql.SQLException;

public class Automate {

    public Automate(String[] args) {
        switch (args[0]) {
            case "-test":
                testerConnection();
                break;

            case "-adds":
                addSchool();
                break;
                
            case "-nb":
                BmgFrame bmgFrame = new BmgFrame("BMG 2014", 800, 600, false);
                break;
        }
    }

    /**
     * Méthode qui permet d'ajouter une école à la base.
     * Et relance le programme avec l'école ajoutée.
     * 
     */
    public static void addSchool() {
        System.out.print("Connection add school ... ");
        
        //Connexion à la base
        BaseSetting bs = new BaseSetting();
        
        if (bs.testerConnexion()) {
            System.out.println("success");
            try (Scanner sc = new Scanner(System.in)) {
                
                //On récupère le nom de l'école voulu
                System.out.println("School's name");
                String name = sc.nextLine();
                
                //Insertion dans la base
                if (addSchoolWithName(bs, name)) {
                    
                    //Insertion success
                    System.out.println(getColor(91) + "Add success !\n" + getColor(92) + "Run BMG Program now ? (Y/n)" + getColor(0));
                    String again = sc.nextLine();
                    
                    //Lancemenent du programme
                    if (again.equals("Y") || again.equals("y")) {
                        BmgFrame frame = new BmgFrame("BMG 2014", 800, 600, true);
                    } else {
                        
                    }
                } else {
                    
                    //Erreur lors de l'insertion
                    System.out.println(getColor(91) + "Ohoh, error...  Try again ? (Y/n)" + getColor(0));
                    String again = sc.nextLine();
                    
                    if (again.equals("Y") || again.equals("y")) {
                        addSchool();
                    } else {
                        
                    }
                }
            }
        }
    }

    public static boolean addSchoolWithName(BaseSetting bs, String name) {
        
          //FIRST VERSION, WHITOUT OBJECT SCHOOL
        try {

            
            String query = "INSERT INTO School(name_sch) VALUES ('" + name + "')";

            bs.setStatement(bs.getConnection().createStatement());
            bs.getStatement().executeUpdate(query);

            return true;
        } catch (SQLException ex) {
            //Logger.getLogger(PanelChercherMot.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
        return false;
    }

    public static void testerConnection() {
        BaseSetting bs = new BaseSetting();
        if (bs.testerConnexion()) {
            System.out.println(getColor(41) + "Connection OK !" + getColor(0));
        } else {
            System.out.println(getColor(41) + "Error with database... !" + getColor(0));
        }
    }

    public static String getColor(int i) {
        return "\033[" + i + "m";
    }

}
