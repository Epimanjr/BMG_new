package user;

import database.BaseSetting;
import exceptions.NotFoundException;
import interfaces.iDbManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class School implements iDbManager 
{
    
    private int id_sch;
    private String name_sch;
    private String type_sch;
    private String street_sch;
    private String city_sch;
    private String postalCode_sch;
    
    public School(String n)
    {
        name_sch = n;
    }
    
    public School(String n, String t, String s, String c, String pc)
    {
	id_sch = -1;
	name_sch = n;
	type_sch = t;
	street_sch = s;
	city_sch = c;
	postalCode_sch = pc;
    }
    
    public School(int id, String n, String t, String s, String c, String pc)
    {
	id_sch = id;
	name_sch = n;
	type_sch = t;
	street_sch = s;
	city_sch = c;
	postalCode_sch = pc;
    }
    
    public int getId_sch()
    {
	return this.id_sch;
    }
    
    public String getName_sch()
    {
	return this.name_sch;
    }
    
    public void setName(String name)
    {
	this.name_sch = name;
    }

    @Override
    public boolean insert(BaseSetting bs) 
    {
	Connection connection = bs.getConnection();
	
	try
	{
	    String query = "INSERT INTO School (name_sch,type_sch,street_sch,city_sch,postalcode_sch) VALUES (?,?,?,?,?)";
	    PreparedStatement p_statement = connection.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
	    p_statement.setString(1,this.name_sch);
	    p_statement.setString(2,this.type_sch);
	    p_statement.setString(3,this.street_sch);
	    p_statement.setString(4,this.city_sch);
	    p_statement.setString(5,this.postalCode_sch);
	    p_statement.executeUpdate();
	    ResultSet rs = p_statement.getGeneratedKeys();
	    
	    if (rs.next())
		this.id_sch = rs.getInt(1);
	    
	    return true;
	}
	catch (SQLException sqle)
	{
	    System.out.println("ERREUR");
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
            if (this.id_sch < 0)
            {
                String query = "UPDATE School SET (name_sch = ? , type_sch = ? , street_sch = ? , city_sch = ? , postalcode_sch = ?) WHERE id_sch = ?";
                PreparedStatement p_statement = connection.prepareStatement(query);
                p_statement.setString(1,this.name_sch);
                p_statement.setString(2,this.type_sch);
                p_statement.setString(3,this.street_sch);
                p_statement.setString(4,this.city_sch);
                p_statement.setString(5,this.postalCode_sch);
                p_statement.setInt(6,this.id_sch);
                p_statement.executeUpdate();
            }
	}
	catch (SQLException sqle)
	{
	    System.out.println("ERREUR");
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
	    String query = "DELETE FROM School WHERE id_sch = ?";
	    PreparedStatement p_statement = connection.prepareStatement(query);
	    p_statement.setInt(1,this.id_sch);
	    p_statement.executeUpdate();
	}
	catch (SQLException sqle)
	{
	    System.out.println("ERREUR");
	    sqle.printStackTrace();
	}
	
	return false;
    }
    
    public static School findById(int id, BaseSetting bs)
    {
	Connection connection = bs.getConnection();
	
	School school = null;
	
	try
	{
	    String query = "SELECT * FROM School WHERE id_sch = ?";
	    PreparedStatement p_statement = connection.prepareStatement(query);
	    p_statement.setInt(1,id);
	    ResultSet rs = p_statement.executeQuery();
	    
	    if (rs.next())
	    {
		int idsch = rs.getInt("id_sch");
		String namesch = rs.getString("name_sch");
		String typesch = rs.getString("type_sch");
		String streetsch = rs.getString("street_sch");
		String citysch = rs.getString("city_sch");
		String postalcodesch = rs.getString("postalcode_sch");
		
		school = new School(idsch,namesch,typesch,streetsch,citysch,postalcodesch);
	    }
	}
	catch (SQLException sqle)
	{
	    System.out.println("ERREUR");
	    sqle.printStackTrace();
	}
	
	return school;
    }
    
    public static ArrayList<School> findByName(String name, BaseSetting bs)
    {
	Connection connection = bs.getConnection();
	
	ArrayList<School> al_school = new ArrayList<>();
	
	try
	{
	    String query = "SELECT * FROM School WHERE name_sch = ?";
	    PreparedStatement p_statement = connection.prepareStatement(query);
	    p_statement.setString(1,name);
	    ResultSet rs = p_statement.executeQuery();
	    
	    while (rs.next())
	    {
		int idsch = rs.getInt("id_sch");
		String namesch = rs.getString("name_sch");
		String typesch = rs.getString("type_sch");
		String streetsch = rs.getString("street_sch");
		String citysch = rs.getString("city_sch");
		String postalcodesch = rs.getString("postalcode_sch");
		
		School school = new School(idsch,namesch,typesch,streetsch,citysch,postalcodesch);
		
		al_school.add(school);
	    }
	}
	catch (SQLException sqle)
	{
	    System.out.println("ERREUR");
	    sqle.printStackTrace();
	}
	
	return al_school;
    }
    
    public static ArrayList<School> findAll(BaseSetting bs)
    {
	Connection connection = bs.getConnection();
	
	ArrayList<School> al_school = new ArrayList<>();
	
	try
	{
	    String query = "SELECT * FROM School";
	    PreparedStatement p_statement = connection.prepareStatement(query);
	    ResultSet rs = p_statement.executeQuery();
	    
	    while (rs.next())
	    {
		int idsch = rs.getInt("id_sch");
		String namesch = rs.getString("name_sch");
		String typesch = rs.getString("type_sch");
		String streetsch = rs.getString("street_sch");
		String citysch = rs.getString("city_sch");
		String postalcodesch = rs.getString("postalcode_sch");
		
		School school = new School(idsch,namesch,typesch,streetsch,citysch,postalcodesch);
		
		al_school.add(school);
	    }
	}
	catch (SQLException sqle)
	{
	    System.out.println("ERREUR");
	    sqle.printStackTrace();
	}
	
	return al_school;
    }
    
    public static School[] researchSchool(BaseSetting bs, String name) throws NotFoundException
    {
	ArrayList<School> als = findByName(name,bs);
	
	School[] ts = null;
	
	if(als.size() > 0)
	    ts = new School[als.size()];
	else
	    throw new NotFoundException();
		
	return ts;
    }
    
    public static School[] researchAllSchool(BaseSetting bs) throws NotFoundException
    {
	ArrayList<School> als = findAll(bs);
	
	School[] ts = null;
	
	if (als.size() > 0)
	    ts = new School[als.size()];
	else
	    throw new NotFoundException();
	
	return ts;
    }
    
    public static String[] researchAllSchool_Name(BaseSetting bs) throws NotFoundException
    {
	School[] ts1;
	ts1 = School.researchAllSchool(bs);
	
	String[] ts2;
	
	if (ts1.length > 0)
	{
	    ts2 = new String[ts1.length];
	    
	    for (int i = 0 ; i < ts2.length ; i++)
	    {
		ts2[i] = ts1[i].getName_sch();
	    }
	}
	else throw new NotFoundException();
	
	return ts2;
    }
    
    public static boolean addSchool(BaseSetting bs, String n, String t, String s, String c, String pc)
    {
        School sch = new School(n, t, s, c, pc);
        
        boolean b = sch.insert(bs);
        
        return b;
    }
    
    public static boolean addSchoolName(BaseSetting bs, String n) throws Exception
    {
        School sch = new School(n, null, null, null, null);
        
        boolean b = sch.insert(bs);
        
        if (b)
            return b;
        else
            throw new Exception();
    }
    
    public static String[] getAllSchoolName(BaseSetting bs) throws Exception
    {
        Connection connection = bs.getConnection();
	
	ArrayList<String> al_school = new ArrayList<>();
        
	try
	{
	    String query = "SELECT name_sch FROM School";
	    PreparedStatement p_statement = connection.prepareStatement(query);
	    ResultSet rs = p_statement.executeQuery();
	    
	    while (rs.next())
	    {
                System.out.println(rs.getString(1));
//                System.out.println(rs.getString("name_sch"));
		String namesch = rs.getString("name_sch");
                
		al_school.add(namesch);
	    }
            
            if (al_school.isEmpty()) throw new Exception();
	}
	catch (SQLException sqle)
	{
	    System.out.println("ERREUR");
	    sqle.printStackTrace();
	}
	
        String[] ts_school = new String[al_school.size()];
        ts_school = al_school.toArray(ts_school);
        
        return ts_school;
    }
}
