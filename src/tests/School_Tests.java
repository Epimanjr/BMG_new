package tests;

import database.BaseSetting;
import user.School;

public class School_Tests 
{

    public static void main(String[] args)
    {
        try 
        {
            BaseSetting bs = new BaseSetting();
            
            School.addSchoolName(bs,"ECOLE 1");
            School.addSchoolName(bs,"ECOLE 2");
            School.addSchoolName(bs,"ECOLE 3");
            String[] tab = School.getAllSchoolName(bs);
        } 
        catch (Exception e) 
        {
            e.printStackTrace();
        }
        
    }
    
}
