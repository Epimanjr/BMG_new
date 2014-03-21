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

public class UserType implements iDbManager
{
    private int id_ut;
    private String name_ut;
    
    /* CONSTRUCTOR */
    public UserType(String nameut) 
    {
	name_ut = nameut;
    }
    
    public UserType(int idut, String nameut) 
    {
	id_ut = idut;
	name_ut = nameut;
    }

    /* GETTERS & SETTERS */
    public int getId_ut() 
    {
	return id_ut;
    }

    public void setId_ut(int id_ut) 
    {
	this.id_ut = id_ut;
    }

    public String getName_ut() 
    {
	return name_ut;
    }

    public void setName_ut(String name_ut) 
    {
	this.name_ut = name_ut;
    }
    
    /* MISE A JOURS */
    public boolean insert(BaseSetting bs) 
    {
        Connection connection = bs.getConnection();
	
	try 
	{
	    String query = "INSERT INTO UserType (name_ut) VALUE (?)";
	    PreparedStatement p_statement = connection.prepareStatement(query,PreparedStatement.RETURN_GENERATED_KEYS);
	    p_statement.setString(1,""+this.name_ut+"");
	    p_statement.executeUpdate();
	    ResultSet rs = p_statement.getGeneratedKeys();
	    
	    if (rs.next()) this.id_ut = rs.getInt(1);
		    
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
	    if (this.id_ut < 0)
	    {
		String query = "UPDATE User SET name_ut = ? WHERE id_ut = ?";
		PreparedStatement p_statement = connection.prepareStatement(query);
		p_statement.setString(1,this.name_ut);
		p_statement.setInt(2,this.id_ut);
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
	    if (UserType.findById(this.getId_ut(),bs) != null)
	    {
		String query = "DELETE FROM UserType WHERE id_ut = ?";
		PreparedStatement p_statement = connection.prepareStatement(query);
		p_statement.setInt(1,this.id_ut);
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
    public static UserType findById(int id,BaseSetting bs) 
    {
        Connection connection = bs.getConnection();
	
	UserType userType = null;
	
	try 
	{
	    String query = "SELECT * FROM UserType WHERE id_ut = ?";
	    PreparedStatement p_statement = connection.prepareStatement(query);
	    p_statement.setInt(1,id);
	    
	    ResultSet rs = p_statement.executeQuery();
	    
	    if (rs.next())
	    {
		int idut = rs.getInt("id_ut");
		String nameut = rs.getString("name_ut");
	    
		userType = new UserType(idut,nameut);
	    }
		    
	}  
	catch (SQLException sqle) 
	{
	    System.out.println("ERREUR");
	    sqle.printStackTrace();
	}
	
	return userType;
    }
}
