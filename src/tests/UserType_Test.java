package tests;

import database.BaseSetting;
import user.UserType;

public class UserType_Test 
{
    public static void main(String[] args)
    {
        BaseSetting bs = new BaseSetting();
        
        UserType ut1 = new UserType("anonymous");
        UserType ut2 = new UserType("student");
        UserType ut3 = new UserType("teacher");
        UserType ut4 = new UserType("admin");
        
        ut1.insert(bs);
        ut2.insert(bs);
        ut3.insert(bs);
        ut4.insert(bs);
        
        UserType[] ut = UserType.findAll(bs);
        
        for (int i=0 ; i<ut.length ; i++)
        {
            System.out.println(ut[i].getId_ut() + " | " + ut[i].getName_ut());
        }
    }
}
