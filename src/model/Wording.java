package model;

import exceptions.EncodeException;
import exceptions.DecodeException;
import database.BaseSetting;
import interfaces.iDbManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Wording implements iDbManager {

    // ----- ATTRIBUTES -----
    
    /**
     * Wording's id
     */
    private int id;
    
    /**
     * Wording's text
     */
    private String text;    // #@#

    /**
     * Wording's values
     */
    private Object[] values;

    // ----------------------
    
    // ---- CONSTRUCTORS ----
    
    /**
     * This constructor creates the simplest statement
     */
    public Wording() {
        this.id = -1;
        this.text = "Answer the following questions.";
        this.values = new Object[0];
    }

    /**
     * This constructor creates a statement with the text given in parameter
     */
    public Wording(String Stext) {
        this.id = -1;
        if (Stext != null) {
            this.text = Stext;
        } else {
            this.text = "...";
        }
        this.values = new Object[0];
    }

    /**
     * This constructor creates a statement with the text and the values given
     * in parameters
     */
    public Wording(String Stext, Object[] Svalues) {
        this.id = -1;
        if (Stext != null) {
            this.text = Stext;
        } else {
            this.text = "...";
        }
        this.values = new Object[Svalues.length];
        for (int i = 0; i < this.values.length; i++) {
            this.values[i] = Svalues[i];
        }
    }
    
    public Wording(int i, String t, Object[] v)
    {
	id = i;
	text = t;
	values = v;
    }

	// ----------------------
	// ------- METHODS ------
    /**
     *
     */
    public void setText(String Stext) {
        if (Stext != null) {
            this.text = Stext;
        }
    }

    /**
     *
     */
    public void setValues(Object[] Svalues) {
        //if (this.values.length == Svalues.length) {
            this.values = Svalues;
        //}
    }

    /**
     * Display a statement
     */
    public String toString() {
        String res = "";
        res = res + "\n		Text: " + this.text;
        res = res + "\n		Values: ";
        if (this.values == null) {
            res = res + "nothing";
        } else {
            for (int i = 0; i < this.values.length; i++) {
                res = res + this.values[i] + "  ";
            }
        }
        // res = res + "\n-----------------------";
        return res;
    }
    
    public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public Object[] getValues() {
		return values;
	}
    
    public String encodeValues() throws EncodeException {
        StringBuilder res = new StringBuilder();
        if (this.values != null && this.values.length > 0) {
            for (Object value : this.values) {
                if (value instanceof Integer) {
                    res.append("int:");
                } else if (value instanceof Double) {
                    res.append("dbl:");
                } else if (value instanceof Character) {
                    res.append("chr:");
                } else if (value instanceof String) {
                    res.append("str:");
                } else if (value == null) {
                    res.append("nul:");
                } else {
                    throw new EncodeException("Unsupported variable type");
                }
            }
            res.replace(res.length()-1, res.length(), "");
            res.append("><");
            for (Object value : this.values) {
                res.append(value).append(':');
            }
            res.replace(res.length()-1, res.length(), "");
        } else {
            res.append("emp><");
        }
        return res.toString();
    }

    public String encode() throws EncodeException {
		String res = "#Wording<" + id + "><$<" + text + ">$><";
        res = res + encodeValues();
		res = res + ">";
		return res;
	}
    
    public static Object[] decodeValues(String str) throws DecodeException {
        Object[] res;
        int i=0;
        int beginning = i;
        while (str.charAt(i) != '>') {
            i++;
        }
        String[] types = str.substring(beginning, i).split(":");
        
        i++;
        beginning = i;
        if (str.charAt(i) == '<') {
            i++;
            while (i < str.length()) {
                i++;
            }
            String[] tab = str.substring(beginning+1, i).split(":");
            res = new Object[tab.length];
            for (int x=0; x<tab.length; x++) {
                switch(types[x]) {
                case "int":
                    res[x] = Integer.valueOf(tab[x]);
                    break;
                case "dbl":
                    res[x] = Double.valueOf(tab[x]);
                    break;
                case "str":
                    res[x] = tab[x];
                    break;
                case "chr":
                    res[x] = tab[x].charAt(0);
                    break;
                case "nul":
                    res[x] = null;
                    break;
                }
            }
        } else {
            res = null;
            throw new DecodeException();
        }
        return res;
    }
    
	public static Wording decode(String str) throws DecodeException {
	    Wording res = null;
        if (str.substring(0, 8).compareTo("#Wording") == 0) {
            res = new Wording();
            int i = 8;
            if (str.charAt(i) == '<') {
                while (str.charAt(i) != '>') {
                    i++;
                }
                assert i > 8 : "no id number";
                res.setId(Integer.valueOf(str.substring(9, i)));

                i += 3;
                int beginning = i;
                if (str.substring(i-2, i+1).compareTo("<$<") == 0) {
                    while (str.substring(i-2, i+1).compareTo(">$>") != 0) {
                        i++;
                    }
                    assert i > beginning+1 : "no wording text";
                    res.setText(str.substring(beginning+1, i-2));
                    
                    i++;
                    if (str.charAt(i) == '<') {
	                    beginning = i;
	                    if (str.substring(i+1, i+4).compareTo("emp") != 0) {
		                    if (str.charAt(i) == '<') {
                                beginning = i;
                                
		                    	while (str.charAt(i) != '>') {
		                            i++;
		                        }
                                
                                i++;
                                if (str.charAt(i) == '<') {
                                    i++;
                                    while (str.charAt(i) != '>') {
                                        i++;
                                    }
                                    res.setValues(decodeValues(str.substring(beginning+1, i)));
                                } else {
                                    res = null;
                                    throw new DecodeException();
                                }
		                    } else {
		                    	res = null;
                                throw new DecodeException();
		                    }
	                    } else {
	                    	res.setValues(null);
                            i += 2;
	                    }
                        i++;
	                    str.substring(i);
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

    /*public static char getType(Object o)
    {
	char c = ' ';
	
	if (o instanceof Double) c = 'd';
	if (o instanceof Integer) c = 'i';
	if (o instanceof String) c = 's';
	if (o instanceof Character) c = 'c';
	
	return c;
    }*/
	
    /*public String encodeValues()
    {
	String res = "";
	
	for (int i=0; i<values.length; i++)
	{
	    res += Wording.getType(values[i])+"("+values[i]+")";
		
	    if (i<values.length-1) 
		res += ":";
	}
	
	return res;
    }*/

    /*public static Object[] decodeValues(String s)
    {
	String[] t_s = s.split(":");
	Object[] t_o = new Object[t_s.length];
	
	for (int k=0; k<t_s.length; k++)
	{
	    if (t_s[k].charAt(0) == 'd')
	        t_o[k] = Double.parseDouble(t_s[k].substring(2,t_s[k].length()-1));
	    if (t_s[k].charAt(0) == 'i')
		t_o[k] = Integer.parseInt(t_s[k].substring(2,t_s[k].length()-1));
	    if (t_s[k].charAt(0) == 's')
	       t_o[k] = new String(t_s[k].substring(2,t_s[k].length()-1));
	    if (t_s[k].charAt(0) == 'c')
	       t_o[k] = (t_s[k].substring(2,3));
	}
	
	return t_o;
    }*/

	
    // ----------------------
    
    // ----- DB METHODS -----

    /* MISE A JOURS */
    public boolean insert(BaseSetting bs) 
    {
	Connection connection = bs.getConnection();
	
	try 
	{
	    String query = "INSERT INTO Wording (text_w,values_w) VALUE (?,?)";
	    PreparedStatement p_statement = connection.prepareStatement(query,PreparedStatement.RETURN_GENERATED_KEYS);
	    p_statement.setString(1,""+this.text+"");
	    p_statement.setString(2,""+this.encodeValues()+"");
	    p_statement.executeUpdate();
	    ResultSet rs = p_statement.getGeneratedKeys();
	    
	    if (rs.next()) {this.id = rs.getInt(1); System.out.println(""+this.id);}
		    
	}  
	catch (SQLException sqle) 
	{
	    System.out.println("ERREUR");
	    sqle.printStackTrace();
	} catch (EncodeException ex) {
            Logger.getLogger(Wording.class.getName()).log(Level.SEVERE, null, ex);
        }
	
	return true;
    }

    public boolean update(BaseSetting bs) 
    {
	Connection connection = bs.getConnection();
	
	try 
	{
	    if (this.id < 0)
	    {
		String query = "UPDATE Wording SET (text_w = ? , values_w = ?) WHERE id_w = ?";
		PreparedStatement p_statement = connection.prepareStatement(query);
		p_statement.setString(1,this.text);
		p_statement.setString(2,this.encodeValues());
		p_statement.setInt(3,this.id);
		p_statement.executeUpdate();
	    }
	}  
	catch (SQLException sqle) 
	{
	    System.out.println("ERREUR");
	    sqle.printStackTrace();
	} catch (EncodeException ex) {
            Logger.getLogger(Wording.class.getName()).log(Level.SEVERE, null, ex);
        }
	
	return true;
    }

    public boolean delete(BaseSetting bs) 
    {
	Connection connection = bs.getConnection();
	
	try 
	{
	    if (Wording.findById(this.getId(),bs) != null)
	    {
		String query = "DELETE FROM Wording WHERE id_w = ?";
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
    public static Wording findById(int id, BaseSetting bs) 
    {
	Connection connection = bs.getConnection();
	
	Wording wording = null;
	
	try 
	{
	    String query = "SELECT * FROM Wording WHERE id_w = ?";
	    PreparedStatement p_statement = connection.prepareStatement(query);
	    p_statement.setInt(1,id);
	    
	    ResultSet rs = p_statement.executeQuery();
	    
	    if (rs.next())
	    {
		int idw = rs.getInt("id_w");
		String textw = rs.getString("text_w");
		String valuesw = rs.getString("values_w");
	    
		wording = new Wording(idw,textw,Wording.decodeValues(valuesw));
	    }
		    
	} 
	catch (SQLException sqle) 
	{
	    System.out.println("ERREUR");
	    sqle.printStackTrace();
	}
    catch (DecodeException de)
    {
        System.out.println("ERREUR");
        de.printStackTrace();
    }
	
	return wording;
    }

    // ----------------------
}
