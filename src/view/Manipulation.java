/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import database.BaseSetting;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author blaise
 */
public class Manipulation {
    //Class just with static method.

   //// private String label = "Not Connected yet";

    public Manipulation() {

    }

    /* public String getLabel() {
     return label;
     }

     public void setLabel(String label) {
     this.label = label;
     }*/
    public static String[] getAllSchoolName(BaseSetting bs) {

        if (bs == null) {
            System.out.println("school - BS NULL");
        } else {

            //On initialise la liste des écoles
            ArrayList<String> list = new ArrayList<>();
            try {
                //

                //Si la connexion à la base est correcte
                if (bs.testerConnexion()) {
                    
                    //SQL
                    String query = "SELECT name_sch from School";
                    bs.setStatement(bs.getConnection().createStatement());
                    bs.setResult_set(bs.getStatement().executeQuery(query));

                    //Parcours des résultats
                    while (bs.getResult_set().next()) {
                        list.add(bs.getResult_set().getString("name_sch"));
                    }
                    
                    //Mise sous la forme d'un tableau
                    String res[] = new String[list.size()];

                    //Remplissage
                    for (int i = 0; i < list.size(); i++) {
                        res[i] = list.get(i);
                    }

                    return res;
                }

            } catch (SQLException ex) {
                Logger.getLogger(Manipulation.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return null;
    }
}
