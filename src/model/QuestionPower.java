package model;

import database.BaseSetting;
import exceptions.EncodeException;
import exceptions.DecodeException;
import java.util.ArrayList;
import java.util.Iterator;


public class QuestionPower extends Question {
    
    // ----- ATTRIBUTES -----
    // Inherited
    /**
     * Operands of the power calculation.
     */
    private int operand;

    /**
     * Operators of the power calculation.
     */
    private ArrayList<Character> operators;
    
    /**
     * Powers of power calculation
     */
    private ArrayList<Integer> powers;

    /**
     * Length of the calculation.
     */
    private int length;

	// ----------------------
    // ---- CONSTRUCTORS ----
    /**
     * This constructor creates the simplest question,
     * for a power calculation.
     */
    public QuestionPower() {
        super();
        this.text = "Calculate.";
        this.difficulty = 0;
        this.operand = 0;
        this.operators = new ArrayList<Character>();
        this.powers = new ArrayList<Integer>();
        this.length = 0;
    }

    /**
     * This constructor creates a question,
     * with the text given in parameter.
     */
    public QuestionPower(String QPtext) {
        super();
        if (QPtext != null) {
            this.text = QPtext;
        } else {
            this.text = "...";
        }
        this.difficulty = 0;
        this.operand = 0;
        this.operators = new ArrayList<Character>();
        this.powers = new ArrayList<Integer>();
        this.length = 0;
    }

    /**
     * This constructor creates a question,
     * with the text and the difficulty given in parameters.
     */
    public QuestionPower(String QPtext, int QPdifficulty) {
        super();
        if (QPtext != null) {
            this.text = QPtext;
        } else {
            this.text = "...";
        }
        if (QPdifficulty >= 0) {
            this.difficulty = QPdifficulty;
        } else {
            this.difficulty = 0;
        }
        this.operand = 0;
        this.operators = new ArrayList<Character>();
        this.powers = new ArrayList<Integer>();
        this.length = 0;
    }

    QuestionPower(String textqc, int QPdiff, int QPoperand, ArrayList<Character> QPoperators, ArrayList<Integer> QPpowers, int QPlength) {
        super();
        this.text = textqc;
        this.difficulty = QPdiff;
        this.operand = QPoperand;
        this.operators = QPoperators;
        this.powers = QPpowers;
        this.length = QPlength;
    }
    
    QuestionPower(int QPid, String QPtext, int QPdiff, int QPoperand, ArrayList<Character> QPoperators, int QPlength) {
        super();
        this.id = QPid;
        this.text = QPtext;
        this.difficulty = QPdiff;
        this.operand = QPoperand;
        this.operators = QPoperators;
        this.length = QPlength;
    }

    // ----------------------
    // ------- METHODS ------
    // Inherited
    /**
     * Generate a random question with powers.
     */
    public void generate() {
        char[] possible_operators = {'*', '/'};
        this.length = (int) (Math.random() * 10) + 2;
        System.out.println("	Random length: " + this.length);
        this.operand = (int) (Math.random() * 20) + 1;
        for (int i = 0; i < this.length; i++) {
            this.powers.add((int) (Math.random() * 20) + 1);
            if (i < this.length - 1) {
                this.operators.add(possible_operators[(int) (Math.random() * 2)]);
            }
        }
    }

    /**
     * Generate a random question with powers, with the length given in parameter.
     */
    public void generate(int QPlength) {
        char[] possible_operators = {'+', '-', '*'};
        this.length = 2;
        if (QPlength > 0) {
            this.length = QPlength;
        }
        System.out.println("	Chosen length: " + this.length);
        this.operand = (int) (Math.random() * 20) + 1;
        for (int i = 0; i < this.length; i++) {
            this.powers.add((int) (Math.random() * 20) + 1);
            if (i < this.length - 1) {
                this.operators.add(possible_operators[(int) (Math.random() * 3)]);
            }
        }
    }

    /**
     * Generate a random question with powers, Operators are choosen in the ArrayList given in parameter.
     */
    public void generate(ArrayList<Character> QPoperators) {
        Character[] possible_operators = new Character[QPoperators.size()];
        possible_operators = QPoperators.toArray(possible_operators);
        this.length = (int) (Math.random() * 10) + 2;
        System.out.println("	Random length: " + this.length);
        this.operand = (int) (Math.random() * 20) + 1;
        for (int i = 0; i < this.length; i++) {
            this.powers.add((int) (Math.random() * 20) + 1);
            if (i < this.length - 1) {
                this.operators.add(possible_operators[(int) (Math.random() * QPoperators.size())]);
            }
        }
    }

    /**
     * Generate a random question of powers, with the length given in parameter, Operators are choosen in the ArrayList given in parameter.
     */
    public void generate(ArrayList<Character> QPoperators, int QPlength) {
        Character[] possible_operators = new Character[QPoperators.size()];
        possible_operators = QPoperators.toArray(possible_operators);
        this.length = 2;
        if (QPlength > 0) {
            this.length = QPlength;
        }
        System.out.println("	Chosen length: " + this.length);
        this.operand = (int) (Math.random() * 20) + 1;
        for (int i = 0; i < this.length; i++) {
            this.powers.add((int) (Math.random() * 20) + 1);
            if (i < this.length - 1) {
                this.operators.add(possible_operators[(int) (Math.random() * QPoperators.size())]);
            }
        }
    }
    
    public double solve() {
        double res = 0;
        Iterator<Integer> itPower = powers.iterator();
        Iterator<Character> itOperator = operators.iterator();
        res = itPower.next();
        while (itPower.hasNext()) {
            if (itOperator.next() == '*') {
                res += itPower.next();
            } else {
                res -= itPower.next();
            }
        }
        return res;
    }
    
    /**
     * Display a question of power calculation
     */
    public String toString() {
        String res = "		QuestionPower";
        res = res + "\n			Text:       " + this.text;
        res = res + "\n			Difficulty: " + this.difficulty;
        res = res + "\n			Operand:    " + this.operand;
        res = res + "\n			Powers:     " + this.powers;
        res = res + "\n			Operators:  " + this.operators;
        res = res + "\n			Operation:  ";
        Iterator<Integer> it_powers = this.powers.iterator();
        Iterator<Character> it_operators = this.operators.iterator();
        res = res + "(" + operand + "^" + it_powers.next() + ")";
        while (it_operators.hasNext()) {
            res = res + it_operators.next() + "(" + operand + "^" + it_powers.next() + ")";
        }
        // res = res + "\n-----------------------";
        return res;
    }
    
    public int getOperand() {
        return operand;
    }

    public void setOperand(int operand) {
        this.operand = operand;
    }

    public ArrayList<Integer> getPowers() {
        return powers;
    }

    public void setPowers(ArrayList<Integer> powers) {
        this.powers = powers;
    }

    public ArrayList<Character> getOperators() {
        return operators;
    }

    public void setOperators(ArrayList<Character> operators) {
        this.operators = operators;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }
    
    public String encodePowers() throws EncodeException {
        String res = new String();
        Iterator<Integer> itdnm = powers.iterator();
		while (itdnm.hasNext()) {
			res = res + itdnm.next() + ":";
		}
		res = res.substring(0, res.length()-1);
        return res;
    }
    
    public String encodeOperators() throws EncodeException {
        String res = new String();
        Iterator<Character> itopt = operators.iterator();
		while (itopt.hasNext()) {
			res = res + itopt.next() + ":";
		}
		res = res.substring(0, res.length()-1);
        return res;
    }

    /**
	 * Encode the current question (object) in a string which can recreate this question by the decode() method
	 * @return encoded question
	 */
	public String encode() throws EncodeException {
		String res = "#QuestionPower<";
        res = res + operand;
		res = res + "><";
		res = res + encodePowers();
		res = res + "><";
		res = res + encodeOperators();
		res = res + "><" + length + ">";
		res = res + super.encode();
		return res;
	}
    
    public static ArrayList<Integer> decodePowers(String str) throws DecodeException {
        ArrayList<Integer> res = new ArrayList<Integer>();
        String[] tab = str.split(":");
        for (int x=0; x<tab.length; x++) {
            res.add(Integer.valueOf(tab[x]));
        }
        assert res.size() > 0 : "empty denominators table";
        return res;
    }
    
    public static ArrayList<Character> decodeOperators(String str) throws DecodeException {
        ArrayList<Character> res = new ArrayList<Character>();
        String[] tab = str.split(":");
        for (int x=0; x<tab.length; x++) {
            res.add(tab[x].charAt(0));
        }
        assert res.size() > 0 : "empty operators table";
        return res;
    }
	
	/**
	 * Decode the string generate by the encode() method of this class
	 * @param str encoded question
	 * @return decoded question (object)
	 */
	public static QuestionPower decode(String str) throws DecodeException {
		QuestionPower res = null;
        if (str.substring(0,14).compareTo("#QuestionPower") == 0) {
            res = new QuestionPower();
            int i = 14;
            if (str.charAt(i) == '<') {
                while (str.charAt(i) != '>') {
                    i++;
                }
                res.setOperand(Integer.valueOf(str.substring(15, i)));

                i++;
                int beginning = i;
                if (str.charAt(i) == '<') {
                    while (str.charAt(i) != '>') {
                        i++;
                    }
                    ArrayList<Integer> tmp_pow = decodePowers(str.substring(beginning+1,i));
                    res.setPowers(tmp_pow);

                    i++;
                    beginning = i;
                    if (str.charAt(i) == '<') {
                        while (str.charAt(i) != '>') {
                            i++;
                        }
                        ArrayList<Character> tmp_opt = decodeOperators(str.substring(beginning+1,i));
                        assert tmp_opt.size() == tmp_opt.size()+1 : "incorrect size of operators table";
                        res.setOperators(tmp_opt);

                        i++;
                        beginning = i;
                        if (str.charAt(i) == '<') {
                            while (str.charAt(i) != '>') {
                                i++;
                            }
                            int tmp_lth = Integer.valueOf(str.substring(beginning+1,i));
                            assert tmp_lth < 0 : "negative length";
                            res.setLength(tmp_lth);

                            i++;
                            str = str.substring(i);
                            Question.decode(res, str);
                        } else {
                            res = null;
                            throw new DecodeException();
                        }
                    } else {
                        res = null;
                        throw new DecodeException();
                    }
                } else {
                    res = null;
                    throw new DecodeException();
                }
            } else {
                res =null;
                throw new DecodeException();
            }
        } else {
            res = null;
            throw new DecodeException();
        }
		return res;
	}

    @Override
    public ArrayList<Question> findAll_ByIdExercise(int ide, BaseSetting bs) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean insert(BaseSetting bs) 
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
