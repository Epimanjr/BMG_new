package tests;

import database.BaseSetting;
import model.Exercise;
import model.Practice;
import user.User;
import user.UserType;

public class Practice_Tests 
{
    
    public static void main(String[] args)
    {   
        
        BaseSetting bs = new BaseSetting();
        
        try
        {
        Exercise e0 = new Exercise(); e0.insert(bs);
        Exercise e1 = new Exercise(); e1.insert(bs);
        Exercise e2 = new Exercise(); e2.insert(bs);
        
        UserType ut0 = new UserType("type"); ut0.insert(bs);
        
        User u0 = new User(ut0.getId_ut(),"prenom0","nom0","ecole0","email0@email.com","password"); u0.insert(bs);
        User u1 = new User(ut0.getId_ut(),"prenom1","nom1","ecole1","email1@email.com","password"); u1.insert(bs);
        User u2 = new User(ut0.getId_ut(),"prenom2","nom2","ecole2","email2@email.com","password"); u2.insert(bs);
        
        Practice p0 = new Practice(u0.getId_u(),e1); p0.insert(bs);
        Practice p1 = new Practice(u0.getId_u(),e2); p1.insert(bs);
        Practice p2 = new Practice(u1.getId_u(),e1); p2.insert(bs);
        Practice p3 = new Practice(u1.getId_u(),e2); p3.insert(bs);
        Practice p4 = new Practice(u0.getId_u(),e1); p4.insert(bs);
        Practice p5 = new Practice(u2.getId_u(),e0); p5.insert(bs);
        Practice p6 = new Practice(u0.getId_u(),e0); p6.insert(bs);
        Practice p7 = new Practice(u2.getId_u(),e0); p7.insert(bs);
        Practice p8 = new Practice(u2.getId_u(),e1); p8.insert(bs);
        Practice p9 = new Practice(u1.getId_u(),e0); p9.insert(bs);
        }
        catch (Exception e)
        {
            System.out.println("ERREUR");
            e.printStackTrace();
        }
        
    }
    
}
