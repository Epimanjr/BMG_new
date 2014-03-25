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
import java.io.InputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
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
	    //String query = "INSERT INTO Screen (name_s) VALUE (?)";
            String query = "INSERT INTO Screen (name_s,object_s) VALUES (?,?)";
	    PreparedStatement p_statement = connection.prepareStatement(query,PreparedStatement.RETURN_GENERATED_KEYS);
	    p_statement.setString(1,""+this.name_s+"");
            //p_statement.setBlob(2,this);
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
                //String query = "UPDATE Screen SET (name_s = ? , object_s = ?) WHERE id_s = ?";
		PreparedStatement p_statement = connection.prepareStatement(query);
		p_statement.setString(1,this.name_s);
                //p_statement.setBlob(2,this);
                
		p_statement.setInt(2,this.id_s);
                //p_statement.setInt(3,this.id_s);
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

                //Screen screen = (Screen)rs.getBlob("object_s");
	    }
		    
	}  
	catch (SQLException sqle) 
	{
	    System.out.println("ERREUR");
	    sqle.printStackTrace();
	}
	
	return screen;
    }
    
    public static Screen findByName(String name,BaseSetting bs) 
    {
        Connection connection = bs.getConnection();
	
	Screen screen = null;
	
	try 
	{
	    String query = "SELECT * FROM Screen WHERE name_s = ?";
	    PreparedStatement p_statement = connection.prepareStatement(query);
	    p_statement.setString(1,name);
	    
	    ResultSet rs = p_statement.executeQuery();
	    
	    if (rs.next())
	    {
		int ids = rs.getInt("id_s");
		String names = rs.getString("name_s");
                
		screen = new Screen(ids,names);

                //Screen screen = (Screen)rs.getBlob("object_s");
	    }
		    
	}  
	catch (SQLException sqle) 
	{
	    System.out.println("ERREUR");
	    sqle.printStackTrace();
	}
	
	return screen;
    }
    
    /* A COMPLETER */
    public static ArrayList<Screen> findAll(BaseSetting bs)
    {
        Connection connection = bs.getConnection();
	
	ArrayList<Screen> al_screen = new ArrayList<>();
        
        try
        {
            String query = "SELECT * FROM Screen";
	    PreparedStatement p_statement = connection.prepareStatement(query);
	    ResultSet rs = p_statement.executeQuery();
            
            while (rs.next())
            {
                int ids = rs.getInt("id_s");
		String names = rs.getString("name_s");
                
		Screen screen = new Screen(ids,names);
                
                //Screen screen = (Screen)rs.getBlob("object_s");
                
                al_screen.add(screen);
            }
        }
        catch (SQLException sqle)
	{
	    System.out.println("ERREUR");
	    sqle.printStackTrace();
	}
        
        return al_screen;
    }
    
    /* A COMPLETER */
    public static Blob findById_Blob(BaseSetting bs)
    {
        return null;
    }
    
    private static HashMap<String,Screen> accessibleScreensForAnonymous(HashMap<String,Screen> allScreens, BaseSetting bs)
    {
        HashMap<String,Screen> res = new HashMap();
        
        ArrayList<String> anonymousAutorized = new ArrayList();
        anonymousAutorized.add("test");
        
        for (Map.Entry<String,Screen> e : allScreens.entrySet()) 
        {
            if (anonymousAutorized.contains(e.getKey()))
              res.put(e.getKey(),e.getValue());
        }
        
        return res;
    }
    
    private static HashMap<String,Screen> accessibleScreensForStudent(HashMap<String,Screen> allScreens, BaseSetting bs)
    {
        HashMap<String,Screen> res = new HashMap();
        
        ArrayList<String> studentAutorized = new ArrayList();
        studentAutorized.add("test");
        
        for (Map.Entry<String,Screen> e : allScreens.entrySet()) 
        {
            if (studentAutorized.contains(e.getKey()))
              res.put(e.getKey(),e.getValue());
        }
        
        return res;
    }
    
    private static HashMap<String,Screen> accessibleScreensForTeacher(HashMap<String,Screen> allScreens, BaseSetting bs)
    {
        HashMap<String,Screen> res = new HashMap();
        
        ArrayList<String> teacherAutorized = new ArrayList();
        teacherAutorized.add("test");
        
        for (Map.Entry<String,Screen> e : allScreens.entrySet()) 
        {
            if (teacherAutorized.contains(e.getKey()))
              res.put(e.getKey(),e.getValue());
        }
        
        return res;
    }
    
    private static HashMap<String,Screen> accessibleScreensForAdmin(HashMap<String,Screen> allScreens, BaseSetting bs)
    {
        HashMap<String,Screen> res = new HashMap();
        
        ArrayList<String> adminAutorized = new ArrayList();
        adminAutorized.add("test");
        
        for (Map.Entry<String,Screen> e : allScreens.entrySet()) 
        {
            if (adminAutorized.contains(e.getKey()))
              res.put(e.getKey(),e.getValue());
        }
        
        return res;
    }
    
    public static HashMap<String,Screen> accessibleScreens(UserType ut, HashMap<String,Screen> allScreens, BaseSetting bs)
    {
        HashMap<String,Screen> res;
        
        if (ut.getName_ut().compareToIgnoreCase("student") == 0)
        {
            res = Screen.accessibleScreensForStudent(allScreens, bs);
        }
        else
        {
            if (ut.getName_ut().compareToIgnoreCase("teacher") == 0)
            {
                res = Screen.accessibleScreensForTeacher(allScreens, bs);
            }
            else
            {
                if (ut.getName_ut().compareToIgnoreCase("admin") == 0)
                {
                    res = Screen.accessibleScreensForAdmin(allScreens, bs);
                }
                else
                {
                    res = Screen.accessibleScreensForAnonymous(allScreens, bs);
                }
            }
        }
        
        return res;
    }
}
