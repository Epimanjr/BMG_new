package model;

import exceptions.EncodeException;
import exceptions.DecodeException;
import database.BaseSetting;
import interfaces.iDbManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class QuestionFraction extends Question implements iDbManager {

    // ----- ATTRIBUTES -----
    // Inherited
    /**
     * Numerators of the calculation with fractions.
     */
    private ArrayList<Integer> numerators;

    /**
     * Denominators of the calculation with fractions.
     */
    private ArrayList<Integer> denominators;

    /**
     * Operators of the calculation with fractions.
     */
    private ArrayList<Character> operators;

    /**
     * Length of the calculation with fractions.
     */
    private int length;

    // ----------------------
    // ---- CONSTRUCTORS ----
    /**
     * This constructor creates the simplest question, with fractions.
     */
    public QuestionFraction() {
        super();
        this.text = "Calculate.";
        this.difficulty = 0;
        this.numerators = new ArrayList<>();
        this.denominators = new ArrayList<>();
        this.operators = new ArrayList<>();
    }

    /**
     * This constructor creates a question, with the text given in parameter.
     * @param QCtext
     */
    public QuestionFraction(String QCtext) {
        super();
        if (QCtext != null) {
            this.text = QCtext;
        } else {
            this.text = "...";
        }
        this.difficulty = 0;
        this.numerators = new ArrayList<>();
        this.denominators = new ArrayList<>();
        this.operators = new ArrayList<>();
    }

    /**
     * This constructor creates a question, with the text and the difficulty given in parameters.
     * @param QCtext
     * @param QCdifficulty
     */
    public QuestionFraction(String QCtext, int QCdifficulty) {
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
        this.numerators = new ArrayList<>();
        this.denominators = new ArrayList<>();
        this.operators = new ArrayList<>();
    }
    
    public QuestionFraction(String textqf, int diffqf, ArrayList<Integer> numerqf, ArrayList<Integer> denomqf, ArrayList<Character> operqf)
    {
        super();
        this.text = textqf;
        this.difficulty = diffqf;
        this.numerators = numerqf;
        this.denominators = denomqf;
        this.operators = operqf;
    }
    
    public QuestionFraction(int idqf, String textqf, int diffqf, ArrayList<Integer> numerqf, ArrayList<Integer> denomqf, ArrayList<Character> operqf)
    {
        super();
        this.id = idqf;
        this.text = textqf;
        this.difficulty = diffqf;
        this.numerators = numerqf;
        this.denominators = denomqf;
        this.operators = operqf;
    }

    // ----------------------
    // ------- METHODS ------
    // Inherited
    /**
     * Generate a random question with fractions.
     */
    public void generate() {
        char[] possible_operators = {'+', '-', '*'};
        this.length = (int) (Math.random() * 10) + 2;
        System.out.println("	Random length: " + this.length);
        for (int i = 0; i < this.length; i++) {
            this.numerators.add((int) (Math.random() * 20) + 1);
            this.denominators.add((int) (Math.random() * 20) + 1);
            if (i < this.length - 1) {
                this.operators.add(possible_operators[(int) (Math.random() * 3)]);
            }
        }
    }

    /**
     * Generate a random question with fractions, with the length given in parameter.
     * @param QClength
     */
    public void generate(int QClength) {
        char[] possible_operators = {'+', '-', '*'};
        this.length = 2;
        if (QClength > 0) {
            this.length = QClength;
        }
        System.out.println("	Chosen length: " + this.length);
        for (int i = 0; i < this.length; i++) {
            this.numerators.add((int) (Math.random() * 20) + 1);
            this.denominators.add((int) (Math.random() * 20) + 1);
            if (i < this.length - 1) {
                this.operators.add(possible_operators[(int) (Math.random() * 3)]);
            }
        }
    }

    /**
     * Generate a random question with fractions, Operators are choosen in the ArrayList given in parameter.
     * @param QCoperators
     */
    public void generate(ArrayList<Character> QCoperators) {
        Character[] possible_operators = new Character[QCoperators.size()];
        possible_operators = QCoperators.toArray(possible_operators);
        this.length = (int) (Math.random() * 10) + 2;
        System.out.println("	Random length: " + this.length);
        for (int i = 0; i < this.length; i++) {
            this.numerators.add((int) (Math.random() * 20) + 1);
            this.denominators.add((int) (Math.random() * 20) + 1);
            if (i < this.length - 1) {
                this.operators.add(possible_operators[(int) (Math.random() * QCoperators.size())]);
            }
        }
    }

    /**
     * Generate a random question of calculation, with the length given in parameter, Operators are choosen in the ArrayList given in parameter.
     * @param QCoperators
     * @param QClength
     */
    public void generate(ArrayList<Character> QCoperators, int QClength) {
        Character[] possible_operators = new Character[QCoperators.size()];
        possible_operators = QCoperators.toArray(possible_operators);
        this.length = 2;
        if (QClength > 0) {
            this.length = QClength;
        }
        System.out.println("	Chosen length: " + this.length);
        for (int i = 0; i < this.length; i++) {
            this.numerators.add((int) (Math.random() * 20) + 1);
            this.denominators.add((int) (Math.random() * 20) + 1);
            if (i < this.length - 1) {
                this.operators.add(possible_operators[(int) (Math.random() * QCoperators.size())]);
            }
        }
    }

    /**
     * Solve a question with fraction
     * @return 
     */
    public double solve() {
        double res = 0;
        ArrayList<Double> operands = new ArrayList<>();
        Iterator<Integer> it_numerators = this.numerators.iterator();
        Iterator<Integer> it_denominators = this.denominators.iterator();
        while (it_numerators.hasNext()) {
            operands.add((double) ((double)(it_numerators.next()) / ((double)it_denominators.next())));
        }
        Object[] operands_array = new Object[operands.size()];
        operands_array = operands.toArray();
        Object[] operators_array = new Object[this.operators.size()];
        operators_array = this.operators.toArray();
        char operator, old_operator;
        int i;
        for (i = 0; i < operands_array.length; i++) {
            operands_array[i] = (double) (operands_array[i]);
        }
        for (i = 0; i < operands_array.length - 1; i++) {
            operator = (char) (operators_array[i]);
            old_operator = '+';
            if (i > 0) {
                old_operator = (char) (operators_array[i - 1]);
            }
            if (operator == '*' || operator == '/') {
                if (operator == '*') {
                    operands_array[i + 1] = (double) (operands_array[i])
                            * (double) (operands_array[i + 1]);
                    if (old_operator == '-') {
                        operands_array[i + 1] = (double) operands_array[i + 1]
                                * (-1.0);
                    }
                } else {
                    operands_array[i + 1] = (double) (operands_array[i])
                            / (double) (operands_array[i + 1]);
                    if (old_operator == '-') {
                        operands_array[i + 1] = (double) operands_array[i + 1]
                                * (-1.0);
                    }
                }
                if (i > 0) {
                    operators_array[i] = '+';
                }
                operands_array[i] = 0.0;
            }
        }
        for (i = 0; i < operands_array.length - 1; i++) {
            operator = (char) (operators_array[i]);
            if (operator == '+' || operator == '-') {
                if (operator == '+') {
                    operands_array[i + 1] = (double) (operands_array[i])
                            + (double) (operands_array[i + 1]);
                } else {
                    operands_array[i + 1] = (double) (operands_array[i])
                            - (double) (operands_array[i + 1]);
                }
                operands_array[i] = 0.0;
            }

        }
        res = (double) (operands_array[operands_array.length - 1]);
        return res;
    }

    @Override
    public String getText() {
        String res = "";
        res = res + this.text + " ";
        Iterator<Integer> it_numerators = this.numerators.iterator();
        Iterator<Integer> it_denominators = this.denominators.iterator();
        Iterator<Character> it_operators = this.operators.iterator();
        res = res + "(" + it_numerators.next() + "/" + it_denominators.next() + ")";
        while (it_numerators.hasNext()) {
            res = res + it_operators.next() + "(" + it_numerators.next() + "/" + it_denominators.next() + ")";
        }
        // res = res + "\n-----------------------";
        return res;
    }
    
    /**
     * Display a question of calculation
     * @return 
     */
    @Override
    public String toString() {
        String res = "		QuestionFraction";
        res = res + "\n			Text: " + this.text;
        res = res + "\n			Difficulty: " + this.difficulty;
        res = res + "\n			Numerators: " + this.numerators;
        res = res + "\n			Denominators: " + this.denominators;
        res = res + "\n			Operators: " + this.operators;
        res = res + "\n			Operation: ";
        Iterator<Integer> it_numerators = this.numerators.iterator();
        Iterator<Integer> it_denominators = this.denominators.iterator();
        Iterator<Character> it_operators = this.operators.iterator();
        res = res + "(" + it_numerators.next() + "/" + it_denominators.next() + ")";
        while (it_numerators.hasNext()) {
            res = res + it_operators.next() + "(" + it_numerators.next() + "/" + it_denominators.next() + ")";
        }
        // res = res + "\n-----------------------";
        return res;
    }
    
    public String getSolutionString() {
        String res = "";
        res = res + this.solve();
        return res;
    }

    public ArrayList<Integer> getNumerators() {
        return numerators;
    }

    public void setNumerators(ArrayList<Integer> numerators) {
        this.numerators = numerators;
    }

    public ArrayList<Integer> getDenominators() {
        return denominators;
    }

    public void setDenominators(ArrayList<Integer> denominators) {
        this.denominators = denominators;
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
    
    public String encodeNumerators() {
        StringBuilder res = new StringBuilder();
		Iterator<Integer> itnum = numerators.iterator();
        while (itnum.hasNext()) {
			res.append(itnum.next()).append(":");
		}
		res.replace(0, res.length()-1, "");
        return res.toString();
    }
    
    public String encodeDenominators() {
        StringBuilder res = new StringBuilder();
        Iterator<Integer> itdnm = denominators.iterator();
		while (itdnm.hasNext()) {
			res.append(itdnm.next()).append(":");
		}
		res.replace(0, res.length()-1, "");
        return res.toString();
    }
    
    public String encodeOperators() {
        StringBuilder res = new StringBuilder();
        Iterator<Character> itopt = operators.iterator();
		while (itopt.hasNext()) {
			res.append(itopt.next()).append(":");
		}
		res.replace(0, res.length()-1, "");
        return res.toString();
    }

    /**
	 * Encode the current question (object) in a string which can recreate this question by the decode() method
	 * @return encoded question
     * @throws exceptions.EncodeException
	 */
    @Override
	public String encode() throws EncodeException {
		StringBuilder res = new StringBuilder("#QuestionFraction<");
        res.append(encodeNumerators());
		res.append("><");
		res.append(encodeDenominators());
		res.append("><");
		res.append(encodeOperators());
		res.append("><").append(length).append(">");
		res.append(super.encode());
		return res.toString();
	}
    
    public static ArrayList<Integer> decodeNumerators(String str) {
        ArrayList<Integer> res = new ArrayList<>();
        String[] tab = str.split(":");
        for (int x=0; x<tab.length; x++) {
            res.add(Integer.valueOf(tab[x]));
        }
        assert res.size() > 0 : "empty numerators table";
        return res;
    }
    
    public static ArrayList<Integer> decodeDenominators(String str) {
        ArrayList<Integer> res = new ArrayList<>();
        String[] tab = str.split(":");
        for (int x=0; x<tab.length; x++) {
            res.add(Integer.valueOf(tab[x]));
        }
        assert res.size() > 0 : "empty denominators table";
        return res;
    }
    
    public static ArrayList<Character> decodeOperators(String str) {
        ArrayList<Character> res = new ArrayList<>();
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
     * @throws exceptions.DecodeException
	 */
	public static QuestionFraction decode(String str) throws DecodeException {
		QuestionFraction res = null;
		if (str.substring(0,17).compareTo("#QuestionFraction") == 0) {
			res = new QuestionFraction();
			int i = 17;
			if (str.charAt(i) == '<') {
				while (str.charAt(i) != '>') {
					i++;
				}
				ArrayList<Integer> tmp_num = decodeNumerators(str.substring(18,i));
				res.setNumerators(tmp_num);
				
				i++;
				int beginning = i;
				if (str.charAt(i) == '<') {
					while (str.charAt(i) != '>') {
						i++;
					}
					ArrayList<Integer> tmp_dnm = decodeDenominators(str.substring(beginning+1,i));
					res.setDenominators(tmp_dnm);
				
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

    // ----------------------
    // ----- DB METHODS -----

    /* MISE A JOURS */
    @Override
    public boolean insert(BaseSetting bs) 
    {
        Connection connection = bs.getConnection();
        
        try
        {
            String query = "INSERT INTO QuestionFraction (text_qf, diff_qf , numerators_qf, denominators_qf, operators_qf, length_qf) VALUES (?,?,?,?,?,?)";
            PreparedStatement p_statement = connection.prepareStatement(query,PreparedStatement.RETURN_GENERATED_KEYS);
            p_statement.setString(1, this.text);
            p_statement.setInt(2, this.difficulty);
            p_statement.setString(3, this.encodeNumerators());
            p_statement.setString(4, this.encodeDenominators());
            p_statement.setString(5, this.encodeOperators());
            p_statement.setInt(6, this.length);
            p_statement.executeUpdate();
            ResultSet rs = p_statement.getGeneratedKeys();
            
            if (rs.next()) this.id = rs.getInt(1);
        }
        catch (SQLException sqle)
        {
            sqle.printStackTrace();
        }
        
        return false;
    }

    @Override
    public boolean update(BaseSetting bs) 
    {
        Connection connection = bs.getConnection();
        
        try
        {
            if (this.id < 0)
            {
                String query = "UPDATE QuestionFraction SET (text_qf = ? , diff_qf = ? , numerators_qf = ? , denominators_qf = ? , operators_qf = ? , length_qf = ?) WHERE id_qf = ?";
                PreparedStatement p_statement = connection.prepareStatement(query);
                p_statement.setString(1, this.text);
                p_statement.setInt(2, this.id);
                p_statement.setString(3, this.encodeNumerators());
                p_statement.setString(4, this.encodeDenominators());
                p_statement.setString(5, this.encodeOperators());
                p_statement.setInt(6, this.length);
                p_statement.setInt(7, this.id);
                p_statement.executeUpdate();
            }
        }
        catch (SQLException sqle)
        {
            sqle.printStackTrace();
        }
        
        return false;
    }

    @Override
    public boolean delete(BaseSetting bs) 
    {
        Connection connection = bs.getConnection();
        
        try
        {
            if (QuestionFraction.findById(id, bs) != null)
            {
                String query = "DELETE FROM QuestionFraction WHERE id_qf = ?";
                PreparedStatement p_statement = connection.prepareStatement(query);
                p_statement.setInt(1,id);
                p_statement.executeUpdate();
            }
        }
        catch (SQLException sqle)
        {
            sqle.printStackTrace();
        }
        
        return false;
    }

    /* FINDERS */
    public static QuestionFraction findById(int id, BaseSetting bs) 
    {
        Connection connection = bs.getConnection();
        
        QuestionFraction questionFraction = null;
        
        try
        {
            String query = "SELECT * FROM QuestionFraction WHERE id_qf = ?";
            PreparedStatement p_statement = connection.prepareStatement(query);
            p_statement.setInt(1,id);
            
            ResultSet rs = p_statement.executeQuery();
            
            if (rs.next())
            {
                int idqf = rs.getInt("id_qf");
                String textqf = rs.getString("text_qf");
                int diffqf = rs.getInt("diff_qf");
                String s_numerqf = rs.getString("numerators_qf");
                ArrayList<Integer> numerqf = QuestionFraction.decodeNumerators(s_numerqf);
                String s_denomqf = rs.getString("denominators_qf");
                ArrayList<Integer> denomqf = QuestionFraction.decodeDenominators(s_denomqf);
                String s_operqf = rs.getString("operators_qf");
                ArrayList<Character> operqf = QuestionFraction.decodeOperators(s_operqf);
                
                questionFraction = new QuestionFraction(idqf,textqf,diffqf,numerqf,denomqf,operqf);
            }
        }
        catch (SQLException sqle)
        {
            sqle.printStackTrace();
        }
        
        return questionFraction;
    }

    // ----------------------

    @Override
    public ArrayList<Question> findAll_ByIdExercise(int ide, BaseSetting bs) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
