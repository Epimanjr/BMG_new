/*
NOTES IMPORTANTES : [OK = test verifie ; ... = en cours ; / = non implementee]
insert : OK
update : OK
delete : OK
findById : OK
findByNom : /
*/

package user;

import database.BaseSetting;
import database.Database;
import interfaces.iDbManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Screen implements iDbManager
{
    private int id_s;
    private String name_s;
    
    /* CONSTRUCTOR */
    public Screen(String names) 
    {
	name_s = names;
    }
    
    public Screen(int ids, String names)
    {
	id_s = ids;
	name_s = names;
    }
    
    /* GETTERS & SETTERS */
    public int getId_s()
    {
	return id_s;
    }

    public void setId_s(int id_s) 
    {
	this.id_s = id_s;
    }

    public String getName_s() 
    {
	return name_s;
    }

    public void setName_s(String name_s) 
    {
	this.name_s = name_s;
    }

    /* MISE A JOURS */
    public boolean insert(BaseSetting bs) 
    {
        Connection connection = bs.getConnection();
	
	try 
	{
	    String query = "INSERT INTO Screen (name_s) VALUE (?)";
	    PreparedStatement p_statement = connection.prepareStatement(query,PreparedStatement.RETURN_GENERATED_KEYS);
	    p_statement.setString(1,""+this.name_s+"");
	    p_statement.executeUpdate();
	    ResultSet rs = p_statement.getGeneratedKeys();
	    
	    if (rs.next()) this.id_s = rs.getInt(1);
		    
	}  
	catch (SQLException sqle) 
	{
	    System.out.println("ERREUR");
	    sqle.printStackTrace();
	}
	
	return true;
    }
    
    public boolean update(BaseSetting bs) 
    {
        Connection connection = bs.getConnection();
	
	try 
	{
	    if (this.id_s < 0)
	    {
		String query = "UPDATE Screen SET name_s = ? WHERE id_s = ?";
		PreparedStatement p_statement = connection.prepareStatement(query);
		p_statement.setString(1,this.name_s);
		p_statement.setInt(2,this.id_s);
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
	
	try 
	{
	    if (Screen.findById(this.getId_s(),bs) != null)
	    {
		String query = "DELETE FROM Screen WHERE id_s = ?";
		PreparedStatement p_statement = connection.prepareStatement(query);
		p_statement.setInt(1,this.id_s);
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
    public static Screen findById(int id,BaseSetting bs) 
    {
        Connection connection = bs.getConnection();
	
	Screen screen = null;
	
	try 
	{
	    String query = "SELECT * FROM Screen WHERE id_s = ?";
	    PreparedStatement p_statement = connection.prepareStatement(query);
	    p_statement.setInt(1,id);
	    
	    ResultSet rs = p_statement.executeQuery();
	    
	    if (rs.next())
	    {
		int ids = rs.getInt("id_s");
		String names = rs.getString("name_s");
	    
		screen = new Screen(ids,names);
	    }
		    
	}  
	catch (SQLException sqle) 
	{
	    System.out.println("ERREUR");
	    sqle.printStackTrace();
	}
	
	return screen;
    }
}
