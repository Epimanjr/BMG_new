package model;

import database.BaseSetting;
import exceptions.EncodeException;
import exceptions.DecodeException;
import interfaces.iDbManager;
import java.util.ArrayList;

/**
 * Abstract Question object containing it base components
 * @author Antoine NOSAl
 * @author Julien RISCHE
 */
public abstract class Question {

    // ----- ATTRIBUTES -----
    /**
     * Question'ID
     */
    protected int id;

    /**
     * Question text
     */
    protected String text;

    /**
     * Question difficulty '0' corresponds to an unknow difficulty '1' corresponds to the easiest difficulty
     */
    protected int difficulty;

    // ----------------------
    // ---- CONSTRUCTORS ----
    /**
     * Empty constructor for Question
     */
    public Question() {
        this.id = -1;
    }
    
    /**
     * Question constructor giving text and difficulty
     * @param Qtext Question text
     * @param Qdifficulty Question difficulty level
     */
    public Question(String Qtext, int Qdifficulty) {
        this.text = Qtext;
        this.difficulty = Qdifficulty;
    }
    
    /**
     * Question constructor giving ID, text and difficulty level
     * @param Qid Question ID number
     * @param Qtext Question text
     * @param Qdifficulty Question difficulty
     */
    public Question(int Qid, String Qtext, int Qdifficulty) {
        this.id = Qid;
        this.text = Qtext;
        this.difficulty = Qdifficulty;
    }

    // ----------------------
    // ------- METHODS ------
    /**
     * Set Question text
     * @param Qtext New Question text
     */
    public void setText(String Qtext) {
        if (Qtext != null) {
            this.text = Qtext;
        }
    }
    
    /**
     * Question ID accessor
     * @return Question ID Number
     */
    public int getID() {
        return this.id;
    }
    /**
     * Set Question ID number
     * @param Qid Question new ID number
     */
    public void setID(int Qid) {
        this.id = Qid;
    }

    /**
     * Set Question difficulty level
     * @param Qdifficulty QUestion new difficulty
     */
    public void setDifficulty(int Qdifficulty) {
        if (Qdifficulty >= 0) {
            this.difficulty = Qdifficulty;
        }
    }
    
    /**
     * Encode the Question as a string
     * @return Encoded question string
     * @throws EncodeException 
     */
    public String encode() throws EncodeException {
        String res = "<" + id + "><$<" + text + ">$><" + difficulty + ">";
        return res;
    }
    
    /**
     * Question text accessor
     * @return Question text
     */
    public String getText() {
        return this.text;
    }
    
    /**
     * Question difficulty level accessor
     * @return Question difficulty
     */
    public int getDifficulty() {
        return difficulty;
    }
    
    public abstract boolean insert(BaseSetting bs);
    
    /**
     * Recreate a Question from an encoded string
     * @param decodedQuestion Question object in which place decoded Question
     * @param encodedQuestion Encoded question string
     * @throws DecodeException 
     */
    public static void decode(Question decodedQuestion, String encodedQuestion) throws DecodeException {
        if (encodedQuestion.charAt(0) == '<') {
            int i = 1;
            while (encodedQuestion.charAt(i) != '>') {
                i++;
            }
            if (i > 1) {
                decodedQuestion.setID(Integer.valueOf(encodedQuestion.substring(1, i)));
            } else {
                decodedQuestion.setID(-1);
            }

            i++;
            int beginning = i;
            if (encodedQuestion.substring(i, i + 3).compareTo("<$<") == 0) {
                while (encodedQuestion.substring(i, i + 3).compareTo(">$>") != 0) {
                    i++;
                }
                if (beginning < i - 1) {
                    decodedQuestion.setText(encodedQuestion.substring(beginning + 3, i));
                } else {
                    decodedQuestion.setText(null);
                }
                i += 3;

                beginning = i;
                if (encodedQuestion.charAt(0) == '<') {
                    while (encodedQuestion.charAt(i) != '>') {
                        i++;
                    }
                    if (i - 1 > beginning) {
                        decodedQuestion.setDifficulty(Integer.valueOf(encodedQuestion.substring(beginning + 1, i)));
                    } else {
                        decodedQuestion.setDifficulty(0);
                    }
                    i++;
                    encodedQuestion = encodedQuestion.substring(i); //do not remove this line
                } else {
                    throw new DecodeException();
                }
            } else {
                throw new DecodeException();
            }
        } else {
            throw new DecodeException();
        }
    }
    
    public abstract ArrayList<Question> findAll_ByIdExercise(int ide, BaseSetting bs);
    
}
