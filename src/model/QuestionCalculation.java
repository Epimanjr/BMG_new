package model;

import exceptions.EncodeException;
import exceptions.DecodeException;
import database.BaseSetting;
import interfaces.iDbManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Question object for calculation
 * @author Joseph DZIMBALKA
 * @author Antoine NOSAL
 * @author Julien RISCHE
 */
public class QuestionCalculation extends Question implements iDbManager {

    // ----- ATTRIBUTES -----
    // Inherited
    /**
     * Operands of the calculation.
     */
    private ArrayList<Integer> operands;

    /**
     * Operators of the calculation.
     */
    private ArrayList<Character> operators;

    /**
     * Length of the calculation.
     */
    private int length;

	// ----------------------
    // ---- CONSTRUCTORS ----
    /**
     * This constructor creates the simplest question,
     * for a calculation.
     */
    public QuestionCalculation() {
        super();
        this.text = "Calculate.";
        this.difficulty = 0;
        this.operands = new ArrayList<>();
        this.operators = new ArrayList<>();
        this.length = 0;
    }

    /**
     * This constructor creates a question,
     * with the text given in parameter.
     * @param QCtext QuestionCalculation text
     */
    public QuestionCalculation(String QCtext) {
        super();
        if (QCtext != null) {
            this.text = QCtext;
        } else {
            this.text = "...";
        }
        this.difficulty = 0;
        this.operands = new ArrayList<>();
        this.operators = new ArrayList<>();
        this.length = 0;
    }

    /**
     * This constructor creates a question,
     * with the text and the difficulty given in parameters.
     * @param QCtext QuestionCalculation text
     * @param QCdifficulty QuestionCalculation difficulty level
     */
    public QuestionCalculation(String QCtext, int QCdifficulty) {
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
        this.operands = new ArrayList<>();
        this.operators = new ArrayList<>();
        this.length = 0;
    }
    
    /**
     * QuestionCalculation constructor giving text, difficulty, operands list, operators list and lenght
     * @param QCtext QuestionCalculation text
     * @param QCdifficulty QuestionCalculation difficulty level
     * @param QCoperands QuestionCalculation operators
     * @param QCoperators QuestionCalculation operands
     * @param QClength QuestionCalculation lenght
     */
    QuestionCalculation(String QCtext, int QCdifficulty, ArrayList<Integer> QCoperands, ArrayList<Character> QCoperators, int QClength) {
        super();
        this.text = QCtext;
        this.difficulty = QCdifficulty;
        this.operands = QCoperands;
        this.operators = QCoperators;
        this.length = QClength;
    }
    
    /**
     * QuestionCalculation constructor giving ID, text, difficulty, operands list, operators list and lenght
     * @param QCid QuestionCalculation ID
     * @param QCtext QuestionCalculation text
     * @param QCdifficulty QuestionCalculation difficulty level
     * @param QCoperands QuestionCalculation operators
     * @param QCoperators QuestionCalculation operands
     * @param QClength QuestionCalculation lenght
     */
    QuestionCalculation(int QCid, String QCtext, int QCdifficulty, ArrayList<Integer> QCoperands, ArrayList<Character> QCoperators, int QClength) {
        super();
        this.id = QCid;
        this.text = QCtext;
        this.difficulty = QCdifficulty;
        this.operands = QCoperands;
        this.operators = QCoperators;
        this.length = QClength;
    }

    // ----------------------
    // ------- METHODS ------
    // Inherited
    /**
     * Generate a random question of calculation.
     */
    public void generate() {
        char[] possible_operators = {'+', '-', '*', '/'};
        this.length = (int) (Math.random() * 10) + 2;
        System.out.println("	Random length: " + this.length);
        for (int i = 0; i < this.length; i++) {
            this.operands.add((int) (Math.random() * 20) + 1);
            if (i < this.length - 1) {
                this.operators.add(possible_operators[(int) (Math.random() * 4)]);
            }
        }
    }

    /**
     * Generate a random question of calculation,
     * with the length given in parameter.
     * @param QClength Generated QuestionCalculation length
     */
    public void generate(int QClength) {
        char[] possible_operators = {'+', '-', '*', '/'};
        this.length = 2;
        if (QClength > 0) {
            this.length = QClength;
        }
        System.out.println("	Chosen length: " + this.length);
        for (int i = 0; i < this.length; i++) {
            this.operands.add((int) (Math.random() * 20) + 1);
            if (i < length - 1) {
                this.operators.add(possible_operators[(int) (Math.random() * 4)]);
            }
        }
    }

    /**
     * Generate a random question of calculation,
     * Operators are choosen in the ArrayList given in parameter.
     * @param QCoperators Allowed operators for generated QuestionCalculation
     */
    public void generate(ArrayList<Character> QCoperators) {
        Character[] possible_operators = new Character[QCoperators.size()];
        possible_operators = QCoperators.toArray(possible_operators);
        this.length = (int) (Math.random() * 10) + 2;
        System.out.println("	Random length: " + this.length);
        for (int i = 0; i < this.length; i++) {
            this.operands.add((int) (Math.random() * 20) + 1);
            if (i < this.length - 1) {
                this.operators.add(possible_operators[(int) (Math.random() * QCoperators.size())]);
            }
        }
    }
    
    /**
     * Generate a random question of calculation,
     * with the length given in parameter,
     * Operators are choosen in the ArrayList given in parameter.
     * @param QCoperators Allowed operators for generated QuestionCalculation
     * @param QClength QuestionCalculation length
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
            this.operands.add((int) (Math.random() * 20) + 1);
            if (i < length - 1) {
                this.operators.add(possible_operators[(int) (Math.random() * QCoperators.size())]);
            }
        }
    }

    /**
     * Solve a calculation question.
     * @return QuestionCalculation solution
     */
    public double solve() {
        double res = 0.0;
        Object[] operands_array = this.operands.toArray();
        Object[] operators_array = this.operators.toArray();
        char operator, old_operator;
        int i;
        for (i = 0; i < operands_array.length; i++) {
            operands_array[i] = (double) ((int) (operands_array[i]));
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
                    // System.out.println("res*: "+operands_array[i+1]);
                } else {
                    operands_array[i + 1] = (double) (operands_array[i])
                            / (double) (operands_array[i + 1]);
                    if (old_operator == '-') {
                        operands_array[i + 1] = (double) operands_array[i + 1]
                                * (-1.0);
                    }
                    // System.out.println("res/: "+operands_array[i+1]);
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
                    // System.out.println("res+: "+operands_array[i+1]);
                } else {
                    operands_array[i + 1] = (double) (operands_array[i])
                            - (double) (operands_array[i + 1]);
                    // System.out.println("res-: "+operands_array[i+1]);
                }
                operands_array[i] = 0.0;
            }

        }
        res = (double) (operands_array[operands_array.length - 1]);
        return res;
    }
    
    /**
     * QuestionCalculation text accessor
     * @return QuestionCalculation text
     */
    @Override
    public String getText() {
        String res = "";
        res = res + this.text + "\n";
        Iterator<Integer> it_operands = this.operands.iterator();
        Iterator<Character> it_operators = this.operators.iterator();
        res = res + it_operands.next();
        while (it_operands.hasNext()) {
            res = res + it_operators.next() + it_operands.next();
        }
        return res;
    }
    
    /**
     * Give a text description of the QuestionCalculation object
     * @return QuestionCalculation description
     */
    @Override
    public String toString() {
        String res = "		QuestionCalculation";
        res = res + "\n			Text: " + this.text;
        res = res + "\n			Difficulty: " + this.difficulty;
        res = res + "\n			Operands: " + this.operands;
        res = res + "\n			Operators: " + this.operators;
        res = res + "\n			Operation: ";
        Iterator<Integer> it_operands = this.operands.iterator();
        Iterator<Character> it_operators = this.operators.iterator();
        res = res + it_operands.next();
        while (it_operands.hasNext()) {
            res = res + it_operators.next() + it_operands.next();
        }
        res = res + "\n			" + this.solve();
        return res;
    }
    
    /**
     * QuestionCalculation operands accessor
     * @return Operands ArrayList
     */
    public ArrayList<Integer> getOperands() {
        return this.operands;
    }
    
    /**
     * Set QuestionCalculation operands
     * @param operands  QuestionCalculation new operands ArrayList
     */
    public void setOperands(ArrayList<Integer> operands) {
        this.operands = operands;
    }
    
    /**
     * QuestionCalculation operators accessor
     * @return Operators ArrayList
     */
    public ArrayList<Character> getOperators() {
        return operators;
    }
    
    /**
     * Set QuestionCalculation operators
     * @param operators  QuestionCalculation new operators ArrayList
     */
    public void setOperators(ArrayList<Character> operators) {
        this.operators = operators;
    }
    
    /**
     * QuestionCalculation length accessor
     * @return QuestionCalculation length
     */
    public int getLength() {
        return this.length;
    }
    
    /**
     * Set QuestionCalculation length
     * @param length QuestionCalculation new length
     */
    public void setLength(int length) {
        this.length = length;
    }
    
    /**
     * Encode the operands ArrayList as a string
     * @return Encoded operands ArrayList string
     * @throws EncodeException 
     */
    public String encodeOperands() throws EncodeException {
        String res = new String();
        if (operands.size() > 0) {
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
    
    /**
     * Encode the operators ArrayList as a string
     * @return Encoded opertaors ArrayList string
     * @throws EncodeException 
     */
    public String encodeOperators() throws EncodeException {
        String res = new String();
        if (operators.size() > 0) {
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
     * @return encoded question
     * @throws exceptions.EncodeException
     */
    @Override
    public String encode() throws EncodeException {
        
        String res = "#QuestionCalculaion<";
        res = res + encodeOperands();
        res = res + "><";
        res = res + encodeOperators();
        res = res + "><" + length + ">";
        res = res + super.encode();
        return res;
    }
    
    /**
     * Recreate an operands ArrayList from an encoded string
     * @param encodedOperands Encoded operands string
     * @return Operands ArrayList
     */
    public static ArrayList<Integer> decodeOperands(String encodedOperands) {
        ArrayList<Integer> res = new ArrayList<>();
        String[] tab = encodedOperands.split(":");
        for (String val : tab) {
            res.add(Integer.valueOf(val));
        }
        assert res.size() > 0 : "empty operands table";
        return res;
    }
    
    /**
     * Recreate an operators ArrayList from an encoded string
     * @param encodedOperators Encoded operands string
     * @return Operators ArrayList
     */
    public static ArrayList<Character> decodeOperators(String encodedOperators) {
        ArrayList<Character> res = new ArrayList<>();
        String[] tab = encodedOperators.split(":");
        for (String val : tab) {
            res.add(val.charAt(0));
        }
        assert res.size() > 0 : "empty operators table";
        return res;
    }

    /**
     * Decode the encodedQuestionCalculationing generate by the encode() method of this class
     * @param encodedQuestionCalculation encoded question
     * @return decoded question (object)
     * @throws exceptions.DecodeException
     */
    public static QuestionCalculation decode(String encodedQuestionCalculation) throws DecodeException {
        QuestionCalculation res;
        if (encodedQuestionCalculation.substring(0, 19).compareTo("#QuestionCalculaion") == 0) {
            res = new QuestionCalculation();
            int i = 19;
            if (encodedQuestionCalculation.charAt(i) == '<') {
                while (encodedQuestionCalculation.charAt(i) != '>') {
                    i++;
                }
                ArrayList<Integer> tmp_opd = decodeOperands(encodedQuestionCalculation.substring(20, i));
                res.setOperands(tmp_opd);

                i++;
                int beginning = i;
                if (encodedQuestionCalculation.charAt(i) == '<') {
                    while (encodedQuestionCalculation.charAt(i) != '>') {
                        i++;
                    }
                    ArrayList<Character> tmp_opt = decodeOperators(encodedQuestionCalculation.substring(beginning + 1, i));
                    assert tmp_opt.size() == tmp_opt.size() + 1 : "incorrect size of operators table";
                    res.setOperators(tmp_opt);

                    i++;
                    beginning = i;
                    if (encodedQuestionCalculation.charAt(i) == '<') {
                        while (encodedQuestionCalculation.charAt(i) != '>') {
                            i++;
                        }
                        int tmp_lth = Integer.valueOf(encodedQuestionCalculation.substring(beginning + 1, i));
                        assert tmp_lth < 0 : "negative length";
                        res.setLength(tmp_lth);

                        i++;
                        encodedQuestionCalculation = encodedQuestionCalculation.substring(i);
                        Question.decode(res, encodedQuestionCalculation);
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
    @Override
    public boolean insert(BaseSetting bs) 
    {
	Connection connection = bs.getConnection();
	
	try 
	{
	    String query = "INSERT INTO QuestionCalculation (text_qc,diff_qc,operands_qc,operators_qc,length_qc) VALUES (?,?,?,?,?)";
	    PreparedStatement p_statement = connection.prepareStatement(query,PreparedStatement.RETURN_GENERATED_KEYS);
	    p_statement.setString(1,this.text);
	    p_statement.setInt(2,this.difficulty);
	    p_statement.setString(3,this.encodeOperands());
	    p_statement.setString(4,this.encodeOperators());
	    p_statement.setInt(5,this.length);
	    p_statement.executeUpdate();
	    ResultSet rs = p_statement.getGeneratedKeys();
			
	    if (rs.next()) this.id = rs.getInt(1);		
	}  
	catch (SQLException sqle) 
	{
	    System.out.println("ERREUR");
	    sqle.printStackTrace();
	}
    catch (EncodeException ee)
    {
        ee.printStackTrace();
    }
	
	return true;
    }

    @Override
    public boolean update(BaseSetting bs) 
    {
        Connection connection = bs.getConnection();
	
	try 
	{
	    if (this.id < 0)
	    {
		String query = "UPDATE QuestionCalculation SET (text_qc = ? , diff_qc = ? , operands_qc = ? , operators_qc = ? , length_qc = ?) WHERE id_qf = ?";
		PreparedStatement p_statement = connection.prepareStatement(query);
		p_statement.setString(1,this.text);
		p_statement.setInt(2,this.difficulty);
		p_statement.setString(3,this.encodeOperands());
		p_statement.setString(4,this.encodeOperators());
		p_statement.setInt(5,this.length);
		p_statement.setInt(6,this.id);
		p_statement.executeUpdate();
	    }
	}  
	catch (SQLException sqle) 
	{
	    System.out.println("ERREUR");
	    sqle.printStackTrace();
	}
    catch (EncodeException ee)
    {
        ee.printStackTrace();
    }
	
	return true;
    }

    @Override
    public boolean delete(BaseSetting bs) 
    {
        Connection connection = bs.getConnection();
	
	try 
	{
	    if (QuestionCalculation.findById(this.getID(), bs) != null)
	    {
		String query = "DELETE FROM QuestionCalculation WHERE id_qc = ?";
		PreparedStatement p_statement = connection.prepareStatement(query);
		p_statement.setInt(1,this.id);
		p_statement.executeUpdate();
	    }
	}  
	catch (SQLException sqle) 
	{
	    System.out.println("ERREUR");
	    sqle.printStackTrace();
	}
	
	return true;
    }

    /* FINDERS */
    public static QuestionCalculation findById(int id, BaseSetting bs) 
    {
        Connection connection = bs.getConnection();
	
	QuestionCalculation questionCalculation = null;
	
	try 
	{
	    String query = "SELECT * FROM QuestionCalculation WHERE id_qc = ?";
	    PreparedStatement p_statement = connection.prepareStatement(query);
	    p_statement.setInt(1,id);
	    
	    ResultSet rs = p_statement.executeQuery();
	    
	    if (rs.next())
	    {
		int idqc = rs.getInt("id_qc");
		String textqc = rs.getString("text_qc");
		int diffqc = rs.getInt("diff_qc");
		String operandsqc = rs.getString("operands_qc");
		ArrayList<Integer> alioprd = QuestionCalculation.decodeOperands(operandsqc);
		String operatorsqc = rs.getString("operators_qc");
		ArrayList<Character> alcoprr = QuestionCalculation.decodeOperators(operatorsqc);
		int lengthqc = rs.getInt("length_qc");

		questionCalculation = new QuestionCalculation(idqc,textqc,diffqc,alioprd,alcoprr,lengthqc);
	    }
		    
	}  
	catch (SQLException sqle) 
	{
	    System.out.println("ERREUR");
	    sqle.printStackTrace();
	}
	
	return questionCalculation;
    }

    // ----------------------

    @Override
    public ArrayList<Question> findAll_ByIdExercise(int ide, BaseSetting bs) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
