package model;

import database.BaseSetting;
import exceptions.DecodeException;
import exceptions.EncodeException;
import interfaces.iDbManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import user.UserType;

public class QuestionCustom<SolutionType> extends Question implements iDbManager {

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
     * @param QCtext
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
     * @param QCtext
     * @param QCsolution
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
     * @param QCtext
     * @param QCdifficulty
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
     * @param QCtext
     * @param QCdifficulty
     * @param QCsolution
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
    
    QuestionCustom(int idqcustom,String QCtext, int QCdifficulty)
    {
        super();
        this.id = idqcustom;
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
    
    QuestionCustom(int idqcustom,String QCtext, int QCdifficulty, SolutionType[] QCsolution)
    {
        super();
        this.id = idqcustom;
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
    
    public SolutionType[] decodeSolution() throws DecodeException {
        SolutionType[] res =null;
        
        return res;
    }
    
    public SolutionType[] solve() {
        return solution;
    }
    
    /**
     * Display a question custom.
     * @return 
     */
    @Override
    public String toString() {
        String res = "		QuestionCustom";
        res = res + "\n                 Text: " + this.text;
        res = res + "\n                 Difficulty: " + this.difficulty;
        res = res + "\n                 Solution: " + this.solution;
        return res;
    }
    
    @Override
    public String getSolutionString() {
        String res = "";
        res = res + this.solution[0];
        return res;
    }
    
    public String encodeSolution() throws EncodeException {
        StringBuilder res = new StringBuilder();
        if (this.solution != null && this.solution.length > 0) {
            if (solution[0] instanceof Integer) {
                res.append("int");
            } else if (solution[0] instanceof Double) {
                res.append("dbl");
            } else if (solution[0] instanceof Character) {
                res.append("chr");
            } else if (solution[0] instanceof String) {
                res.append("str");
            } else if (solution[0] instanceof Boolean) {
                res.append("bln");
            } else {
                throw new EncodeException("Unsupported variable type");
            }
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
    
    public static Class decodeSolutionType(String encodedQuestion) throws DecodeException, ClassNotFoundException {
        Class res;
        int i=0;
        while (encodedQuestion.charAt(i) != '>') {
            i++;
        }
        String type = encodedQuestion.substring(0, i);
        
        switch(type) {
            case "int":
                res = Class.forName("java.lang.Integer");
                break;
            case "dbl":
                res = Class.forName("java.lang.Double");
                break;
            case "str":
                res = Class.forName("java.lang.String");
                break;
            case "chr":
                res = Class.forName("java.lang.Character");
                break;
            case "bln":
                res = Class.forName("java.lang.Boolean");
                break;
            default:
                throw new DecodeException("non recognized type");
        }
        return res;
    }
    
    public SolutionType[] decodeSolution(String str) throws DecodeException {
        SolutionType[] res;
        int i=0;
        int beginning = i;
        while (str.charAt(i) != '>') {
            i++;
        }
        String type = str.substring(beginning, i);
        
        i++;
        beginning = i;
        if (str.charAt(i) == '<') {
            i++;
            while (i < str.length()) {
                i++;
            }
            String[] tab = str.substring(beginning+1, i).split(":");
            res = (SolutionType[]) new Object[tab.length];
            for (int x=0; x<tab.length; x++) {
                switch(type) {
                case "int":
                    res[x] = (SolutionType) Integer.valueOf(tab[x]);
                    break;
                case "dbl":
                    res[x] = (SolutionType) Double.valueOf(tab[x]);
                    break;
                case "str":
                    res[x] = (SolutionType) tab[x];
                    break;
                case "chr":
                    res[x] = (SolutionType) ((Character) tab[x].charAt(0));
                    break;
                default:
                    throw new DecodeException("non recognized type");
                }
            }
        } else {
            res = null;
            throw new DecodeException();
        }
        return res;
    }
    
    @Override
    public String encode() throws EncodeException {
        StringBuilder res = new StringBuilder("#QuestionCustom<");
        res.append(encodeSolution());
        res.append(">");
        res.append(super.encode());
        return res.toString();
    }
    
    public static QuestionCustom decode(String str) throws DecodeException, ClassNotFoundException {
        if (str.substring(0, 15).compareTo("#QuestionCustom") == 0) {
            int i = 15;
            int beginning = i;
            if (str.charAt(i) == '<') {
                while (str.charAt(i) != '>') {
                    i++;
                }
                Class objectType = decodeSolutionType(str.substring(16));
                if (objectType == Class.forName("java.lang.Integer")) {
                    QuestionCustom<Integer> res = new QuestionCustom<>();
                    i++;
                    if (str.charAt(i) == '<') {
                        while (str.charAt(i) != '>') {
                            i++;
                        }
                        res.setSolution(res.decodeSolution(str.substring(beginning+1, i)));
                        
                        i++;
                        str = str.substring(i);
                        Question.decode(res, str);
                        return res;
                    } else {
                        throw new DecodeException();
                    }
                } else if (objectType == Class.forName("java.lang.Double")) {
                    QuestionCustom<Double> res = new QuestionCustom<>();
                    i++;
                    if (str.charAt(i) == '<') {
                        while (str.charAt(i) != '>') {
                            i++;
                        }
                        res.setSolution(res.decodeSolution(str.substring(beginning+1, i)));
                        
                        i++;
                        str = str.substring(i);
                        Question.decode(res, str);
                        return res;
                    } else {
                        throw new DecodeException();
                    }
                } else if (objectType == Class.forName("java.lang.String")) {
                    QuestionCustom<String> res = new QuestionCustom<>();
                    i++;
                    if (str.charAt(i) == '<') {
                        while (str.charAt(i) != '>') {
                            i++;
                        }
                        res.setSolution(res.decodeSolution(str.substring(beginning+1, i)));
                        
                        i++;
                        str = str.substring(i);
                        Question.decode(res, str);
                        return res;
                    } else {
                        throw new DecodeException();
                    }
                } else if (objectType == Class.forName("java.lang.Character")) {
                    QuestionCustom<Character> res = new QuestionCustom<>();
                    i++;
                    if (str.charAt(i) == '<') {
                        while (str.charAt(i) != '>') {
                            i++;
                        }
                        res.setSolution(res.decodeSolution(str.substring(beginning+1, i)));
                        
                        i++;
                        str = str.substring(i);
                        Question.decode(res, str);
                        return res;
                    } else {
                        throw new DecodeException();
                    }
                } else if (objectType == Class.forName("java.lang.Boolean")) {
                    QuestionCustom<Boolean> res = new QuestionCustom<>();
                    i++;
                    if (str.charAt(i) == '<') {
                        while (str.charAt(i) != '>') {
                            i++;
                        }
                        res.setSolution(res.decodeSolution(str.substring(beginning+1, i)));
                        
                        i++;
                        str = str.substring(i);
                        Question.decode(res, str);
                        return res;
                    } else {
                        throw new DecodeException();
                    }
                } else {
                    throw new DecodeException("unsupported type");
                }
            } else {
                throw new DecodeException();
            }
        } else {
            throw new DecodeException();
        }
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
            String query = "INSERT INTO QuestionCustom (text_qcustom, diff_qcustom , sol_qcustom) VALUES (?,?,?)";
            PreparedStatement p_statement = connection.prepareStatement(query,PreparedStatement.RETURN_GENERATED_KEYS);
            p_statement.setString(1, this.text);
            p_statement.setInt(2, this.difficulty);
            p_statement.setString(3, this.encodeSolution());
            p_statement.executeUpdate();
            ResultSet rs = p_statement.getGeneratedKeys();
            
            if (rs.next()) this.id = rs.getInt(1);
        }
        catch (SQLException sqle)
        {
            sqle.printStackTrace();
        }
        catch (EncodeException ee) 
        {
            ee.printStackTrace();
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
                String query = "UPDATE QuestionCustom SET (text_qcustom = ? , diff_qcustom = ? , sol_qcustom = ?) WHERE id_qcustom = ?";
                PreparedStatement p_statement = connection.prepareStatement(query);
                p_statement.setString(1, this.text);
                p_statement.setInt(2, this.difficulty);
                p_statement.setString(3, this.encodeSolution());
                p_statement.setInt(4, this.id);
                p_statement.executeUpdate();
            }
        }
        catch (SQLException sqle)
        {
            sqle.printStackTrace();
        } 
        catch (EncodeException ee) 
        {
            ee.printStackTrace();
        }
        
        return false;
    }

    @Override
    public boolean delete(BaseSetting bs) 
    {
        Connection connection = bs.getConnection();
        
        try
        {
            // ATTENTION AU FINDBYID (avant = static | maintenant != static)
            if (QuestionCustom.findById(id, bs) != null)
            {
                String query = "DELETE FROM QuestionCustom WHERE id_qcustom = ?";
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
    
    public QuestionCustom getSolutionType(String s, BaseSetting bs) throws DecodeException
    {
        QuestionCustom tips = new QuestionCustom("");
        
        Object[] decodeSolution = tips.decodeSolution(s);
        
        //fake.setSolution(decodeSolution);
        
        tips = QuestionCustom.findById(id, bs);
        
        tips.setSolution(decodeSolution);
        
        return tips;
    }
    
    // ATTENTION AU FINDBYID (avant = static | maintenant != static)
    public static QuestionCustom findById(int id, BaseSetting bs) 
    {
        Connection connection = bs.getConnection();
        
        QuestionCustom questionCustom = null;
        
        try
        {
            String query = "SELECT * FROM QuestionCustom WHERE id_qcustom = ?";
            PreparedStatement p_statement = connection.prepareStatement(query);
            p_statement.setInt(1,id);
            
            ResultSet rs = p_statement.executeQuery();
            
            if (rs.next())
            {
                int idqcustom = rs.getInt("id_qcustom");
                String textqcustom = rs.getString("text_qcustom");
                int diffqcustom = rs.getInt("diff_qcustom");
                String soltextqcustom = rs.getString("sol_qcustom");
                
                //SolutionType[] solqcustom = QuestionCustom.decodeSolution(soltextqcustom);
                
                questionCustom = new QuestionCustom(idqcustom,textqcustom,diffqcustom);//,solqcustom);
            }
        }
        catch (SQLException sqle)
        {
            sqle.printStackTrace();
        }
//        catch (DecodeException de) 
//        {
//            de.printStackTrace();
//        }
        
        return questionCustom;
    }
    
    // ATTENTION AU FINDALL (avant = static | maintenant != static)
    public static QuestionCustom[] findAll(BaseSetting bs) 
    {
        Connection connection = bs.getConnection();
        
        ArrayList<QuestionCustom> al_qcustom = new ArrayList();
        
        try
        {
            String query = "SELECT * FROM QuestionCustom";
            PreparedStatement p_statement = connection.prepareStatement(query);
            
            ResultSet rs = p_statement.executeQuery();
            
            while (rs.next())
            {
                int idqcustom = rs.getInt("id_qcustom");
                String textqcustom = rs.getString("text_qcustom");
                int diffqcustom = rs.getInt("diff_qcustom");
                String soltextqcustom = rs.getString("sol_qcustom");
                
                //SolutionType[] solqcustom = QuestionCustom.decodeSolution(soltextqcustom);
                
                QuestionCustom questionCustom = new QuestionCustom(idqcustom,textqcustom,diffqcustom);//,solqcustom);
                
                al_qcustom.add(questionCustom);
            }
        }
        catch (SQLException sqle)
        {
            sqle.printStackTrace();
        }
//        catch (DecodeException de) 
//        {
//            de.printStackTrace();
//        }
        
        QuestionCustom[] tab_qcustom = null;
        
        if (!(al_qcustom.isEmpty()))
        {
            tab_qcustom = new QuestionCustom[al_qcustom.size()];
            al_qcustom.toArray(tab_qcustom);
        }
        
        return tab_qcustom;
    }

    // ----------------------

    @Override
    public ArrayList<Question> findAll_ByIdExercise(int ide, BaseSetting bs) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
