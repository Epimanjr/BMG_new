package model;

import exceptions.EncodeException;
import exceptions.DecodeException;
import database.BaseSetting;
import java.util.*;

public class QuestionEquation extends Question {

    // ----- ATTRIBUTES -----
    // Inherited
    /**
     * Operands of the equation (without unknowns)
     */
    private ArrayList<Integer> operands;

    /**
     * Placements of the unknowns in the equation
     */
    private ArrayList<Integer> unknowns;

    /**
     * Operators of the equation ('=' included)
     */
    private ArrayList<Character> operators;

    /**
     * Length of the equation
     */
    private int length;

    // ----------------------
    // ---- CONSTRUCTORS ----
    /**
     * This constructor creates the simplest question for a calculation
     */
    public QuestionEquation() {
        super();
        this.text = "Solve.";
        this.difficulty = 0;
        this.operands = new ArrayList<Integer>();
        this.unknowns = new ArrayList<Integer>();
        this.operators = new ArrayList<Character>();
        this.length = 0;
    }

    /**
     * This constructor creates a question with the text given in parameter
     */
    public QuestionEquation(String QCtext) {
        super();
        this.text = QCtext;
        this.difficulty = 0;
        this.operands = new ArrayList<Integer>();
        this.unknowns = new ArrayList<Integer>();
        this.operators = new ArrayList<Character>();
        this.length = 0;
    }

    /**
     * This constructor creates a question with the text and the difficulty given in parameters
     */
    public QuestionEquation(String QCtext, int QCdifficulty) {
        super();
        this.text = QCtext;
        this.difficulty = QCdifficulty;
        this.operands = new ArrayList<Integer>();
        this.unknowns = new ArrayList<Integer>();
        this.operators = new ArrayList<Character>();
        this.length = 0;
    }

    // ----------------------
    // ------- METHODS ------
    // Inherited
    /**
     * Generate a random question with an equation
     */
    public void generate() {
        char[] possible_operators = {'+', '-', '*', '/'};
        this.length = (int) (Math.random() * 10) + 2;
        System.out.println("	Random length: " + this.length);
        boolean order0;
        int ukn;
        for (int i = 0; i < this.length; i++) {
            this.operands.add((int) (Math.random() * 20) + 1);
            if (i == 0) {
                this.unknowns.add(1);
                order0 = false;
            } else {
                ukn = (int) (Math.random() * 3);
                if (ukn > 0) {
                    order0 = false;
                } else {
                    order0 = true;
                }
                this.unknowns.add(ukn);
            }
            if (i < this.length - 1) {
                if (i < this.length - 2) {
                    if (!order0) {
                        this.operators.add(possible_operators[(int) (Math.random() * 2)]);
                    } else {
                        this.operators.add(possible_operators[(int) (Math.random() * 4)]);
                    }
                } else {
                    this.operators.add('=');
                }
            }
        }
    }

    /**
     * Generate a random question with an equation
     */
    public void generate(int order, ArrayList<Character> QCoperators) {
        Character[] possible_operators = new Character[QCoperators.size()];
        possible_operators = QCoperators.toArray(possible_operators);
        char needed_operators[] = {'+', '-'};
        this.length = (int) (Math.random() * 10) + 2;
        System.out.println("	Random length: " + this.length);
        boolean order0;
        int ukn;
        for (int i = 0; i < this.length; i++) {
            this.operands.add((int) (Math.random() * 20) + 1);
            if (i == 0) {
                this.unknowns.add(1);
                order0 = false;
            } else {
                if (Math.random() < 0.45) {
                    ukn = (int) (Math.random() * (order + 1));
                    order0 = false;
                } else {
                    ukn = 0;
                    order0 = true;
                }
                this.unknowns.add(ukn);
            }
            if (i < this.length - 1) {
                if (i < this.length - 2) {
                    if (!order0) {
                        this.operators.add(needed_operators[(int) (Math.random() * 2)]);
                    } else {
                        this.operators.add(possible_operators[(int) (Math.random() * QCoperators.size())]);
                    }
                } else {
                    this.operators.add('=');
                }
            }
        }
    }

    /**
     * Generate a random question with an equation with the length given in parameter
     */
    /*public void generate(int QClength) {
     char[] possible_operators = {'+', '-', '*', '/'};
     this.length = 2;
     if (QClength > 2) {
     this.length = QClength;
     }
     System.out.println("	Chosen length: " + this.length);
     for (int i = 0; i < this.length; i++) {
     this.operands.add((int) (Math.random() * 20) + 1);
     if (i < this.length - 1) {
     if (i < this.length - 2) {
     this.unknowns.add(Math.random() < 0.3);
     this.operators.add(possible_operators[(int) (Math.random() * 4)]);
     } else {
     this.unknowns.add(true);
     this.operators.add('=');
     }
     }
     }
     }*/
    public void generate(int QClength) {
        char[] possible_operators = {'+', '-', '*', '/', '='};
        boolean equal_exists = false;
        int random_op = 5;
        this.length = 2;
        if (QClength > 2) {
            this.length = QClength;
        }
        System.out.println("	Chosen length: " + this.length);
        for (int i = 0; i < this.length; i++) {
            this.operands.add((int) (Math.random() * 20) + 1);
            if (i == 0) {
                this.unknowns.add(1);
            } else {
                this.unknowns.add((int) (Math.random() * 3));
            }
            if (i < this.length - 1) {
                if (i < this.length - 2) {
                    Character op = possible_operators[(int) (Math.random() * random_op)];
                    this.operators.add(op);
                    if (op == '=') {
                        equal_exists = true;
                        random_op = 4;
                    }
                } else {
                    if (!equal_exists) {
                        this.operators.add('=');
                    } else {
                        this.operators.add(possible_operators[(int) (Math.random() * 4)]);
                    }
                }
            }
        }
    }

    /**
     * Solve a question with an equation
     */
    public double[] solve() {
        /*double res = 0;
         Iterator<Integer> it_operands = this.operands.iterator();
         Iterator<Character> it_operators = this.operators.iterator();
         Iterator<Boolean> it_unknowns = this.unknowns.iterator();
         ArrayList<Integer> operands_qc1l = new ArrayList<Integer>();
         ArrayList<Character> operators_qc1l = new ArrayList<Character>();
         ArrayList<Integer> operands_qc2l = new ArrayList<Integer>();
         ArrayList<Character> operators_qc2l = new ArrayList<Character>();
         ArrayList<Integer> operands_qc1r = new ArrayList<Integer>();
         ArrayList<Character> operators_qc1r = new ArrayList<Character>();
         ArrayList<Integer> operands_qc2r = new ArrayList<Integer>();
         ArrayList<Character> operators_qc2r = new ArrayList<Character>();
         boolean after_equal = false;
         if (!it_unknowns.next()) {
         operands_qc1l.add(it_operands.next());
         } else {
         operands_qc2l.add(it_operands.next());
         }
         while (it_operands.hasNext()) {
         if (it_operators.hasNext()) {
         after_equal = (it_operators.next() == '=');
         }
         if (!after_equal) {
         if (!it_unknowns.next()) {
         operands_qc1l.add(it_operands.next());
         } else {
         operands_qc2l.add(it_operands.next());
         }
         } else {
         if (!it_unknowns.next()) {
         operands_qc1r.add(it_operands.next());
         } else {
         operands_qc2r.add(it_operands.next());
         }
         }
         }
         System.out.println(operands_qc1l);
         System.out.println(operands_qc1r);
         System.out.println(operands_qc2l);
         System.out.println(operands_qc2r);
         return res;*/

        double[] res = new double[0];
        if (!operands.isEmpty()) {
            int order = 0;
            int o;
            for (Iterator<Integer> it = unknowns.iterator(); it.hasNext();) {
                o = it.next();
                if (o > order) {
                    order = o;
                }
            }
            res = new double[order];

            ArrayList<Double> equation = new ArrayList<Double>();
            equation.add((double) operands.get(0));
            for (int i = 1; i < operands.size(); i++) {
                if (operators.get(i - 1) == '-') {
                    equation.add((double) operands.get(i) * -1);
                } else if (operators.get(i - 1) == '/') {
                    equation.set(equation.size(), equation.get(equation.size()) / ((double) operands.get(i)));
                    equation.add(0.0);
                } else if (operators.get(i - 1) == '*') {
                    equation.set(equation.size(), equation.get(equation.size()) * ((double) operands.get(i)));
                    equation.add(0.0);
                } else {
                    equation.add((double) operands.get(i));
                }
            }
            int i = 0;
            while (i < operators.size()) {
                if (operators.get(i) == '=') {
                    i++;
                    for (; i < operands.size(); i++) {
                        equation.set(i, -equation.get(i));
                    }
                    break;
                }
                i++;
            }
            if (order == 1) {
                double knownsSum = 0;
                double unknownsSum = 0;
                for (i = 0; i < equation.size(); i++) {
                    if (unknowns.get(i) == 1) {
                        unknownsSum += equation.get(i);
                    } else {
                        knownsSum += equation.get(i);
                    }
                }
                knownsSum *= -1.0;
                res[0] = (double) (knownsSum / unknownsSum);
            } else if (order == 2) {
                double a = 0;
                double b = 0;
                double c = 0;
                double delta;
                for (i = 0; i < equation.size(); i++) {
                    if (unknowns.get(i) == 2) {
                        a += equation.get(i);
                    } else if (unknowns.get(i) == 1) {
                        b += equation.get(i);
                    } else {
                        c += equation.get(i);
                    }
                }
                delta = Math.pow(b, 2) - 4 * a * c;
                if (delta == 0.0) {
                    res[0] = -b / (2 * a);
                } else {
                    res[0] = (-b - Math.sqrt(delta)) / (2.0 * a);
                    res[1] = (-b + Math.sqrt(delta)) / (2.0 * a);
                }
                //System.out.println(a + " " + b + " " + c + " " + delta);
            }
        }
        return res;
    }

    /**
     * Display a question with an equation
     */
    public String toString() {
        String res = "		QuestionEquation";
        res = res + "\n			Text: " + this.text;
        res = res + "\n			Difficulty: " + this.difficulty;
        res = res + "\n			Operands: " + this.operands;
        res = res + "\n			Operators: " + this.operators;
        res = res + "\n			Unknowns: " + this.unknowns;
        res = res + "\n			Equation: ";
        Iterator<Integer> it_operands = this.operands.iterator();
        Iterator<Character> it_operators = this.operators.iterator();
        Iterator<Integer> it_unknowns = this.unknowns.iterator();
        res = res + it_operands.next();
        while (it_operands.hasNext()) {
            int u = it_unknowns.next();
            if (u == 1) {
                res = res + "x ";
            } else if (u == 2) {
                res = res + "x² ";
            } else {
                res = res + " ";
            }
            res = res + it_operators.next() + " ";
            res = res + it_operands.next();
        }
        int u = it_unknowns.next();
        if (u == 1) {
            res = res + "x ";
        } else if (u == 2) {
            res = res + "x² ";
        } else {
            res = res + " ";
        }
        return res;
    }

    public ArrayList<Integer> getOperands() {
        return operands;
    }

    public void setOperands(ArrayList<Integer> operands) {
        this.operands = operands;
    }

    public ArrayList<Integer> getUnknowns() {
        return unknowns;
    }

    public void setUnknowns(ArrayList<Integer> unknowns) {
        this.unknowns = unknowns;
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

    public String encodeOperands() throws EncodeException {
        String res = new String();
        if (this.operands.size() > 0) {
            Iterator<Integer> itopd = operands.iterator();
            while (itopd.hasNext()) {
                res = res + itopd.next() + ":";
            }
            res = res.substring(0, res.length() - 1);
        } else {
            throw new EncodeException("Empty ArrayList");
        }
        return res;
    }

    public String encodeUnknowns() throws EncodeException {
        String res = new String();
        if (this.unknowns.size() > 0) {
            Iterator<Integer> itukn = unknowns.iterator();
            while (itukn.hasNext()) {
                res = res + itukn.next() + ":";
            }
            res = res.substring(0, res.length() - 1);
        }  else {
            throw new EncodeException("Empty ArrayList");
        }
        return res;
    }

    public String encodeOperators() throws EncodeException {
        String res = new String();
        if (this.operators.size() > 0) {
            Iterator<Character> itopt = operators.iterator();
            while (itopt.hasNext()) {
                res = res + itopt.next() + ":";
            }
            res = res.substring(0, res.length() - 1);
        } else {
            throw new EncodeException("Empty ArrayList");
        }
        return res;
    }

    /**
     * Encode the current question (object) in a string which can recreate this question by the decode() method
     *
     * @return encoded question
     */
    public String encode() throws EncodeException {
        String res = "#QuestionEquation<";
        res = res + encodeOperands();
        res = res + "><";
        res = res + encodeUnknowns();
        res = res + "><";
        res = res + encodeOperators();
        res = res + "><" + length + ">";
        res = res + super.encode();
        return res;
    }

    public static ArrayList<Integer> decodeOperands(String str) throws DecodeException {
        ArrayList<Integer> res = new ArrayList<>();
        String[] tab = str.split(":");
        for (int x = 0; x < tab.length; x++) {
            res.add(Integer.valueOf(tab[x]));
        }
        assert res.size() > 0 : "empty operands table";
        return res;
    }

    public static ArrayList<Integer> decodeUnknowns(String str) throws DecodeException {
        ArrayList<Integer> res = new ArrayList<>();
        String[] tab = str.split(":");
        for (int x = 0; x < tab.length; x++) {
            res.add(Integer.valueOf(tab[x]));
        }
        assert res.size() > 0 : "empty unknowns table";
        return res;
    }

    public static ArrayList<Character> decodeOperators(String str) throws DecodeException {
        ArrayList<Character> res = new ArrayList<>();
        String[] tab = str.split(":");
        for (int x = 0; x < tab.length; x++) {
            res.add(tab[x].charAt(0));
        }
        assert res.size() > 0 : "empty operators table";
        return res;
    }

    /**
     * Decode the string generate by the encode() method of this class
     *
     * @param str encoded question
     * @return decoded question (object)
     */
    public static QuestionEquation decode(String str) throws DecodeException {
        QuestionEquation res = null;
        if (str.substring(0, 17).compareTo("#QuestionEquation") == 0) {
            res = new QuestionEquation();
            int i = 17;
            if (str.charAt(i) == '<') {
                while (str.charAt(i) != '>') {
                    i++;
                }
                ArrayList<Integer> tmp_opd = decodeOperands(str.substring(18, i));
                res.setOperands(tmp_opd);

                i++;
                int beginning = i;
                if (str.charAt(i) == '<') {
                    while (str.charAt(i) != '>') {
                        i++;
                    }
                    ArrayList<Integer> tmp_ukn = decodeUnknowns(str.substring(beginning + 1, i));
                    res.setUnknowns(tmp_ukn);

                    i++;
                    beginning = i;
                    if (str.charAt(i) == '<') {
                        while (str.charAt(i) != '>') {
                            i++;
                        }
                        ArrayList<Character> tmp_opt = decodeOperators(str.substring(beginning + 1, i));
                        assert tmp_opt.size() == tmp_opt.size() + 1 : "incorrect size of operators table";
                        res.setOperators(tmp_opt);

                        i++;
                        beginning = i;
                        if (str.charAt(i) == '<') {
                            while (str.charAt(i) != '>') {
                                i++;
                            }
                            int tmp_lth = Integer.valueOf(str.substring(beginning + 1, i));
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
                res = null;
                throw new DecodeException();
            }
        } else {
            res = null;
            throw new DecodeException();
        }
        return res;
    }

    // ----------------------
    // ----- DB METHODS -----

    /* MISE A JOURS */
    public boolean insert(BaseSetting bs) {
        return false;
    }

    public boolean update(BaseSetting bs) {
        return false;
    }

    public boolean delete(BaseSetting bs) {
        return false;
    }

    /* FINDERS */
    public static QuestionEquation findById(int id, BaseSetting bs) {
        return null;
    }

    // ----------------------

    @Override
    public ArrayList<Question> findAll_ByIdExercise(int ide, BaseSetting bs) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
