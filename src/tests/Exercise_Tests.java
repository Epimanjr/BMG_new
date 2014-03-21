package tests;

import database.BaseSetting;
import java.util.ArrayList;
import java.util.Iterator;
import model.Exercise;
import model.Question;
import model.QuestionCalculation;
import model.Wording;

public class Exercise_Tests 
{
    
    public static void main(String[] args)
    {
        BaseSetting bs = new BaseSetting();
        
        Wording w = new Wording("Je suis un énoncé", new Object[10]);
        
        if (w != null) {System.out.println("WORDING NOT NULL");}
        
        // INSERTION WORDING
        w.insert(bs);
        
        if (w != null) {System.out.println("WORDING NOT NULL");}
        
        w = null;
        
        if (w == null) {System.out.println("WORDING NULL");}
        
        w = Wording.findById(1, bs);
        
        if (w != null) {System.out.println("WORDING NULL");}
        
        if (bs != null) {System.out.println("BS OK");}
        
        ArrayList<Question> alq = new ArrayList<>();
        
        QuestionCalculation qc1 = new QuestionCalculation("Qui suis-je ?",1);
        ArrayList<Integer> ali1 = new ArrayList<>();
        ali1.add(6);
        ali1.add(9);
        qc1.setOperands(ali1);
        ArrayList<Character> alc1 = new ArrayList<>();
        alc1.add('-');
        qc1.setOperators(alc1);
        qc1.setLength(0);
        QuestionCalculation qc2 = new QuestionCalculation("Où est Brian ?",1);
        ArrayList<Integer> ali2 = new ArrayList<>();
        ali2.add(6);
        ali2.add(6);
        ali2.add(6);
        qc2.setOperands(ali2);
        ArrayList<Character> alc2 = new ArrayList<>();
        alc2.add('/');
        alc2.add('/');
        qc2.setOperators(alc2);
        qc2.setLength(0);
        
        // INSERTION QUESTIONS
        qc1.insert(bs);
        qc2.insert(bs);
        
        System.out.println("INSERT QC OK");
        
        alq.add(qc1);
        alq.add(qc2);
        
        Exercise e1 = new Exercise("Titre1", w, alq, "Exercice de calculs", 1, true);       
        Exercise e2 = new Exercise("Titre2", w, alq, "Exercice de calculs", 1, true);
        Exercise e3 = new Exercise("Titre3", w, alq, "Exercice de calculs", 1, true);
        Exercise e4 = new Exercise("Titre4", w, alq, "Exercice de calculs", 1, true);
        
        // INSERTION EXERCISE
        e1.insert(bs);
        e2.insert(bs);
        e3.insert(bs);
        e4.insert(bs);
        
        System.out.println("INSERT E OK");
        
        ArrayList<Exercise> ale = Exercise.findAll(bs);
        
        ArrayList<Question> alquest = e1.findById_AllQuestions(1, bs);
        System.out.println(">>> "+alquest+" <<<");
    }
    
}
