package model;

import database.BaseSetting;
import exceptions.DecodeException;
import exceptions.EncodeException;
import java.util.ArrayList;

public class QuestionCustom<SolutionType> extends Question {

    // ----- ATTRIBUTES -----
    
    // Inherited

    /**
     * Solution of the question.
     */
    private SolutionType[] solution;

    // ----------------------
    
    // ---- CONSTRUCTORS ----
    
    /**
     * This constructor creates an empty question.
     */
    public QuestionCustom() {
        super();
        this.text = "Answer.";
        this.difficulty = 0;
        this.solution = null;
    }

    /**
     * This constructor creates a question,
     * with the text given in parameter.
     */
    public QuestionCustom(String QCtext) {
        super();
        if (QCtext != null) {
            this.text = QCtext;
        } else {
            this.text = "...";
        }
        this.difficulty = 0;
        this.solution = null;
    }
    
    /**
     * This constructor creates a question,
     * with the text and the solution given in parameters.
     */
    public QuestionCustom(String QCtext, SolutionType[] QCsolution) {
        super();
        if (QCtext != null) {
            this.text = QCtext;
        } else {
            this.text = "...";
        }
        this.difficulty = 0;
        this.solution = QCsolution;
    }

    /**
     * This constructor creates a question,
     * with the text and the difficulty given in parameters.
     */
    public QuestionCustom(String QCtext, int QCdifficulty) {
        super();
        if (QCtext != null) {
            this.text = QCtext;
        } else {
            this.text = "...";
        }
        if (QCdifficulty >= 0) {
            this.difficulty = QCdifficulty;
        } else {
            this.difficulty = 0;
        }
        this.solution = null;
    }
    
    /**
     * This constructor creates a question,
     * with the text, the difficulty and the solution given in parameters.
     */
    public QuestionCustom(String QCtext, int QCdifficulty, SolutionType[] QCsolution) {
        super();
        if (QCtext != null) {
            this.text = QCtext;
        } else {
            this.text = "...";
        }
        if (QCdifficulty >= 0) {
            this.difficulty = QCdifficulty;
        } else {
            this.difficulty = 0;
        }
        this.solution = QCsolution;
    }
    
    
    
    
    /*@Override
    public String encode() throws EncodeException() {
        StringBuilder res = new StringBuilder();
        return "";
    }*/

    // ----------------------
    
    // ------- METHODS ------
    
    // Inherited

    public void setSolution(SolutionType[] solution) {
        this.solution = solution;
    }
    
    public String encodeSolution() throws EncodeException {
        StringBuilder res = new StringBuilder();
        if (this.solution != null && this.solution.length > 0) {
            for (Object value : this.solution) {
                if (value instanceof Integer) {
                    res.append("int:");
                } else if (value instanceof Double) {
                    res.append("dbl:");
                } else if (value instanceof Character) {
                    res.append("chr:");
                } else if (value instanceof String) {
                    res.append("str:");
                } else if (value instanceof Boolean) {
                    res.append("bln:");
                } else if (value == null) {
                    res.append("nul:");
                } else {
                    throw new EncodeException("Unsupported variable type");
                }
            }
            res.replace(res.length()-1, res.length(), "");
            res.append("><");
            for (Object value : this.solution) {
                res.append(value).append(':');
            }
            res.replace(res.length()-1, res.length(), "");
        } else {
            throw new EncodeException("Empty solution array");
        }
        return res.toString();
    }
    
    public static QuestionCustom decodeSolution() throws DecodeException {
        QuestionCustom res =null;
        
        return res;
    }
    
    public SolutionType[] solve() {
        return solution;
    }
    
    /**
     * Display a question custom.
     */
    public String toString() {
        String res = "		QuestionCustom";
        res = res + "\n                 Text: " + this.text;
        res = res + "\n                 Difficulty: " + this.difficulty;
        res = res + "\n                 Solution: " + this.solution;
        return res;
    }

    // ----------------------
    
    // ----- DB METHODS -----

    /* MISE A JOURS */
    public boolean insert(BaseSetting bs) 
    {
        return false;
    }

    public boolean update(BaseSetting bs) 
    {
        return false;
    }

    public boolean delete(BaseSetting bs) 
    {
        return false;
    }

    /* FINDERS */
    public static QuestionCustom findById(int id, BaseSetting bs) 
    {
        return null;
    }

    // ----------------------

    @Override
    public ArrayList<Question> findAll_ByIdExercise(int ide, BaseSetting bs) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
