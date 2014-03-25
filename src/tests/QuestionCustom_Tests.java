package tests;

import database.BaseSetting;
import model.QuestionCustom;

public class QuestionCustom_Tests 
{
    public static void main(String[] args)
    {
        BaseSetting bs = new BaseSetting();
        
        QuestionCustom qcustom1 = new QuestionCustom("Je suis la 1ere question personnalisee au monde! J'ai droit a la medaille d'or! <3", 0, new Object[5]);
        QuestionCustom qcustom2 = new QuestionCustom("Je suis la 2nde question personnalisee au monde! J'ai droit a la medaille d'argent! :D", 0, new Object[5]);
        QuestionCustom qcustom3 = new QuestionCustom("Je suis la 3eme question personnalisee au monde! J'ai droit a la medaille de bronze! :)", 0, new Object[5]);
        QuestionCustom qcustom4 = new QuestionCustom("Je suis la 4eme question personnalisee au monde! Mais j'ai perdu de peu! :/", 0, new Object[5]);
        QuestionCustom qcustom5 = new QuestionCustom("Je suis la 5eme question personnalisee au monde! Mais je suis dernier! :(", 0, new Object[5]);
        
        qcustom1.insert(bs);
        qcustom2.insert(bs);
        qcustom3.insert(bs);
        qcustom4.insert(bs);
        
        QuestionCustom qcustom;
        
        qcustom = null;
        // ATTENTION AU FINDBYID (avant = static | maintenant != static)
        qcustom = QuestionCustom.findById(4, bs);
        qcustom.setText("Je suis la 4eme question personnalisee au monde! J'ai droit au lot de consolation et je le veux tout de suite!");
        
        qcustom.update(bs);
        
        qcustom = null;
        // ATTENTION AU FINDBYID (avant = static | maintenant != static)
        qcustom = QuestionCustom.findById(5, bs);
        qcustom.setText("\"- Toi t'es dernier donc tu t'en vas! Au revoir!\"");
        
        qcustom.delete(bs);
        
        // ATTENTION AU FINDALL (avant = static | maintenant != static)
        QuestionCustom[] tab_qcustom = QuestionCustom.findAll(bs);
        
        for (int i=0 ; i<tab_qcustom.length ; i++)
        {
            QuestionCustom qcust = tab_qcustom[i];
            System.out.println(qcust.getID() + " | " + qcust.getText());
        }
    }
}
