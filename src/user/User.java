/*
 NOTES IMPORTANTES : [OK = test verifie ; ... = en cours ; / = non implementee]
 insert : OK
 update : OK
 delete : OK
 findById : OK
findByLogs : ...
 findByFName : /
 findByLName : /
 findBySchool : /
 findByEmail : /
 findByPass : /
 findByConnected : /
signIn : OK
checkPresence : ...
setConnected : ...
setDisconnected : ...
 */
package user;

import database.BaseSetting;
import database.Database;
import exceptions.AccessDeniedException;
import exceptions.AlreadyExistsException;
import interfaces.iDbManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class User implements iDbManager
{

    private int id_u;
    private int id_ut;
    private int id_sch;
    private String fname_u;
    private String lname_u;
    private String school_u;
    private String email_u;
    private String pass_u;
    private int connected_u;

    /* CONSTRUCTOR */
    public User(int id_u, int id_ut, String fname_u, String lname_u, String school_u, String email_u, String pass_u, int connected_u)
    {
        this.id_u = id_u;
        this.id_ut = id_ut;
        this.fname_u = fname_u;
        this.lname_u = lname_u;
        this.school_u = school_u;
        this.email_u = email_u;
        this.pass_u = pass_u;
    }
    
    public User(int id_ut, String fname_u, String lname_u, String school_u, String email_u, String pass_u) {
        this.id_ut = id_ut;
        this.fname_u = fname_u;
        this.lname_u = lname_u;
        this.school_u = school_u;
        this.email_u = email_u;
        this.pass_u = pass_u;
    }
    
    public User(int idut, int idsch, String fnameu, String lnameu, String emailu, String passu) {
        id_ut = idut;
        id_sch = idsch;
        fname_u = fnameu;
        lname_u = lnameu;
        email_u = emailu;
        pass_u = passu;
        connected_u = 0;
    }
    
    public User(int idut, int idsch, String fnameu, String lnameu, String emailu, String passu, int connectedu) {
        id_ut = idut;
        id_sch = idsch;
        fname_u = fnameu;
        lname_u = lnameu;
        email_u = emailu;
        pass_u = passu;
        connected_u = connectedu;
    }

    public User(int idu, int idut, int idsch, String fnameu, String lnameu, String emailu, String passu, int connectedu) {
        id_u = idu;
        id_ut = idut;
        id_sch = idsch;
        fname_u = fnameu;
        lname_u = lnameu;
        email_u = emailu;
        pass_u = passu;
        connected_u = connectedu;
    }

    /* GETTERS & SETTERS */
    public int getId_u() {
        return id_u;
    }

    public void setId_u(int id_u) {
        this.id_u = id_u;
    }

    public int getId_ut() {
        return id_ut;
    }

    public void setId_ut(int id_ut) {
        this.id_ut = id_ut;
    }
    
    public int getId_sch()
    {
        return id_sch;
    }

    public void setId_sch(int idsch)
    {
        id_sch = idsch;
    }
    
    public String getFname_u() {
        return fname_u;
    }

    public void setFname_u(String fname_u) {
        this.fname_u = fname_u;
    }

    public String getLname_u() {
        return lname_u;
    }

    public void setLname_u(String lname_u) {
        this.lname_u = lname_u;
    }

    public String getEmail_u() {
        return email_u;
    }

    public void setEmail_u(String email_u) {
        this.email_u = email_u;
    }

    public String getPass_u() {
        return pass_u;
    }

    public void setPass_u(String pass_u) {
        this.pass_u = pass_u;
    }

    public int isConnected_u() {
        return connected_u;
    }

    public void setConnected_u(int connected_u) {
        this.connected_u = connected_u;
    }

    /* MISE A JOURS */
    public boolean insert(BaseSetting bs) 
    {
        Connection connection = bs.getConnection();

        try {
            String query = "INSERT INTO User (id_ut,fname_u,lname_u,school_u,email_u,pass_u,connected_u) VALUES (?,?,?,?,?,sha(?),?)";
            PreparedStatement p_statement = connection.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
            p_statement.setInt(1, this.id_ut);
            p_statement.setString(2, this.fname_u);
            p_statement.setString(3, this.lname_u);
            p_statement.setString(4, this.school_u);
            p_statement.setString(5, this.email_u);
            p_statement.setString(6, this.pass_u);
            p_statement.setInt(7, this.connected_u);
            p_statement.executeUpdate();
            ResultSet rs = p_statement.getGeneratedKeys();

            if (rs.next()) {
                this.id_u = rs.getInt(1);
            }
            
            return true;

        }  
	catch (SQLException sqle) 
	{
	    System.out.println("ERREUR");
	    sqle.printStackTrace();
	}

        return false;
    }    
    
    public boolean update(BaseSetting bs) 
    {
        Connection connection = bs.getConnection();

        try {
            if (this.id_u < 0) {
                String query = "UPDATE User SET (id_ut = ? , id_sch = ? , fname_u = ? , lname_u = ? , email_u = ? , pass_u = sha(?) , connected_u = ?) WHERE id_u = ?";
                PreparedStatement p_statement = connection.prepareStatement(query);
                p_statement.setInt(1, this.id_ut);
                p_statement.setInt(2, this.id_sch);
                p_statement.setString(3, this.fname_u);
                p_statement.setString(4, this.lname_u);
                p_statement.setString(5, this.email_u);
                p_statement.setString(6, this.pass_u);
                p_statement.setInt(7, this.connected_u);
                p_statement.setInt(8, this.id_u);
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

    public boolean delete(BaseSetting bs) 
    {
        Connection connection = bs.getConnection();

        try {
            if (User.findById(this.getId_u(),bs) != null) {
                String query = "DELETE FROM User WHERE id_u = ?";
                PreparedStatement p_statement = connection.prepareStatement(query);
                p_statement.setInt(1, this.id_u);
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
    public static User findById(int id,BaseSetting bs) 
    {
        Connection connection = bs.getConnection();

        User user = null;

        try {
            String query = "SELECT * FROM User WHERE id_u = ?";
            PreparedStatement p_statement = connection.prepareStatement(query);
            p_statement.setInt(1, id);

            ResultSet rs = p_statement.executeQuery();

            if (rs.next()) {
                int idu = rs.getInt("id_u");
                int idut = rs.getInt("id_ut");
                String fnameu = rs.getString("fname_u");
                String lnameu = rs.getString("lname_u");
                String schoolu = rs.getString("school_u");
                String emailu = rs.getString("email_u");
                String passu = rs.getString("pass_u");
                int connectedu = rs.getInt("connected_u");

                user = new User(idu, idut, fnameu, lnameu, schoolu, emailu, passu, connectedu);
            }

        }  
	catch (SQLException sqle) 
	{
	    System.out.println("ERREUR");
	    sqle.printStackTrace();
	}

        return user;
    }
    
    public static User findByEmail(String eml,BaseSetting bs) 
    {
        Connection connection = bs.getConnection();

        User user = null;

        try {
            String query = "SELECT * FROM User WHERE email_u = ?";
            PreparedStatement p_statement = connection.prepareStatement(query);
            p_statement.setString(1, eml);

            ResultSet rs = p_statement.executeQuery();

            if (rs.next()) {
                int idu = rs.getInt("id_u");
                int idut = rs.getInt("id_ut");
                int idsch = rs.getInt("id_sch");
                String fnameu = rs.getString("fname_u");
                String lnameu = rs.getString("lname_u");
                String emailu = rs.getString("email_u");
                String passu = rs.getString("pass_u");
                int connectedu = rs.getInt("connected_u");

                user = new User(idu, idut, idsch, fnameu, lnameu, emailu, passu, connectedu);
            }

        }  
	catch (SQLException sqle) 
	{
	    System.out.println("ERREUR");
	    sqle.printStackTrace();
	}

        return user;
    }
    
    public static User findByLogs(String eml, String pswd, BaseSetting bs) 
    {
	Connection connection = bs.getConnection();

        User user = null;

        try {
	    String query;
	    PreparedStatement p_statement;
	    ResultSet rs;
	    
	    query = "SELECT sha(?)";
            p_statement = connection.prepareStatement(query);
	    p_statement.setString(1, pswd);
	    
	    rs = p_statement.executeQuery();
	    
	    if (rs.next())
		pswd = rs.getString(1);
	    
	    query = "SELECT * FROM User WHERE email_u = ? AND pass_u = ?";
            p_statement = connection.prepareStatement(query);
            p_statement.setString(1, eml);
	    p_statement.setString(2, pswd);
	    
            rs = p_statement.executeQuery();

            if (rs.next()) 
	    {
                int idu = rs.getInt("id_u");
                int idut = rs.getInt("id_ut");
                String fnameu = rs.getString("fname_u");
                String lnameu = rs.getString("lname_u");
                String schoolu = rs.getString("school_u");
                String emailu = rs.getString("email_u");
                String passu = rs.getString("pass_u");
                int connectedu = rs.getInt("connected_u");

                user = new User(idu, idut, fnameu, lnameu, schoolu, emailu, passu, connectedu);
            }

        } 
	catch (SQLException sqle) 
	{
	    System.out.println("ERREUR");
	    sqle.printStackTrace();
	}

        return user;
    }

    /* OTHERS */
    public static boolean signUp(BaseSetting bs, int ut, String fn, String ln, String sch, String eml, String pswd) throws AlreadyExistsException
    {
        boolean b = false;
	
	if (checkPresence(bs,eml,pswd))
	{
	    throw new AlreadyExistsException("Sorry, this user already exists.");
	}
	else
	{
	    User u = new User(ut, fn, ln, sch, eml, pswd);
	    b = u.insert(bs);
//	    b = (User.findById(u.getId_u(), bs) != null);
	}
	
	return b;
    }
    
    public static boolean checkPresence(BaseSetting bs, String eml, String pswd)
    {
	User u = User.findByLogs(eml, pswd, bs);
	
	boolean b = false;
	if (u != null) b = true;
	
	return b;
    }
    
    public static boolean setConnected(BaseSetting bs, String eml, String pswd) throws AccessDeniedException
    {
	boolean b = false;
	
	if (checkPresence(bs,eml,pswd))
	{
	    User u = User.findByLogs(eml, pswd, bs);
	    u.setConnected_u(1);
	    b = u.update(bs);
	    b = (User.findById(u.getId_u(), bs).isConnected_u() == 1);
            b = true;
	}
	else
	{
	    throw new AccessDeniedException("Wrong combination of login/password or unknown user!");
	}
	
	return b;
    }
    
    public static boolean setDisconnected(BaseSetting bs, String eml, String pswd)
    {
	boolean b = false;
	
	if (checkPresence(bs,eml,pswd))
	{
	    User u = User.findByLogs(eml, pswd, bs);
	    u.setConnected_u(0);
	    b = u.update(bs);
	    b = (User.findById(u.getId_u(), bs).isConnected_u() == 0);
	}
	
	return b;
    }
}
