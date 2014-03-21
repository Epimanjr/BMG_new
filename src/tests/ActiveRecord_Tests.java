package tests;

import database.BaseSetting;
import java.util.ArrayList;
import java.util.Collection;
import model.Exercise;
import model.Practice;
import model.Question;
import model.QuestionCalculation;
import model.Wording;
import user.School;
import user.Screen;
import user.User;
import user.UserType;
import user.Workgroup;

public class ActiveRecord_Tests 
{
    @SuppressWarnings("null")
    public static void main(String[] args)
    {
//	boolean b = true;
//	
//	BaseSetting bs = new BaseSetting();
//        //bs.setInfo();
//	
//	School school_1 = new School("Ecole1","Type1","Rue1","Ville1","CPostal1");
//	b = school_1.insert(bs);
//	System.out.println(""+b+" insertion school 1");
//	School school_2 = new School("Ecole2","Type2","Rue2","Ville2","CPostal2");
//	b = school_2.insert(bs);
//	System.out.println(""+b+" insertion school 2");
//	School school_3 = new School("Ecole3","Type3","Rue3","Ville3","CPostal3");
//	b = school_3.insert(bs);
//	System.out.println(""+b+" insertion school 3");
//	
//	School sch;
//	sch = School.findById(school_3.getId_sch(),bs); 
//	
//	if (sch != null) System.out.println(""+sch.getId_sch()+" | "+sch.getName_sch()+""); else System.out.println("find SCH : pas OK");
//	
//	sch.setName("...");
//	sch.update(bs);
//	
//	if (sch != null) System.out.println(""+sch.getId_sch()+" | "+sch.getName_sch()+""); else System.out.println("update SCH : pas OK");
//	
//	sch.delete(bs);
//	sch = School.findById(school_3.getId_sch(),bs);
//	
//	if (sch != null) System.out.println(""+sch.getId_sch()+""+sch.getName_sch()+""); else System.out.println("delete SCH : OK");
//	
//	UserType userType_1 = new UserType("Groupe1");
//	b = userType_1.insert(bs);
//	System.out.println(""+b+" insertion userType 1");
//	UserType userType_2 = new UserType("Groupe2");
//	b = userType_2.insert(bs);
//	System.out.println(""+b+" insertion userType 2");
//	UserType userType_3 = new UserType("Groupe3");
//	b = userType_3.insert(bs);
//	System.out.println(""+b+" insertion userType 3");
//	UserType userType_4 = new UserType("Groupe4");
//	b = userType_4.insert(bs);
//	System.out.println(""+b+" insertion userType 4");
//	UserType userType_5 = new UserType("Groupe5");
//	b = userType_5.insert(bs);
//	System.out.println(""+b+" insertion userType 5");
//	UserType userType_6 = new UserType("Groupe6");
//	b = userType_6.insert(bs);
//	System.out.println(""+b+" insertion userType 6");
//	
//	User user_1 = new User(userType_5.getId_ut(),school_2.getId_sch(),"Prenom1","Nom1","Email1","MdP1");
//	b = user_1.insert(bs);
//	System.out.println(""+b+" insertion user 1");
//	User user_2 = new User(userType_4.getId_ut(),school_1.getId_sch(),"Prenom2","Nom2","Email2","MdP2");
//	b = user_2.insert(bs);
//	System.out.println(""+b+" insertion user 2");
//	
//	User u;
//	u = User.findById(user_2.getId_u(),bs);
//	
//	if (u != null) System.out.println(""+u.getId_u()+" | "+u.getFname_u()+""); else System.out.println("find U : pas OK");
//	
//	u.setFname_u("...");
//	u.update(bs);
//	
//	if (u != null) System.out.println(""+u.getId_u()+" | "+u.getFname_u()+""); else System.out.println("update U : pas OK");
//	
//	u.delete(bs);
//	u = User.findById(user_2.getId_u(),bs);
//	
//	if (u != null) System.out.println(""+u.getId_u()+" | "+u.getFname_u()+""); else System.out.println("delete U : OK");
//	
//	UserType ut;
//	ut = UserType.findById(userType_6.getId_ut(),bs);
//	
//	if (ut != null) System.out.println(""+ut.getId_ut()+" | "+ut.getName_ut()+""); else System.out.println("find UT : pas OK");
//	
//	ut.setName_ut("...");
//	ut.update(bs);
//	
//	if (ut != null) System.out.println(""+ut.getId_ut()+" | "+ut.getName_ut()+""); else System.out.println("update UT : pas OK");
//	
//	ut.delete(bs);
//	ut = UserType.findById(userType_6.getId_ut(),bs);
//	
//	if (ut != null) System.out.println(""+ut.getId_ut()+" | "+ut.getName_ut()+""); else System.out.println("delete UT : OK");
//	
//	Screen screen_1 = new Screen("Ecran1");
//	b = screen_1.insert(bs);
//	System.out.println(""+b+" insertion screen 1");
//	Screen screen_2 = new Screen("Ecran2");
//	b = screen_2.insert(bs);
//	System.out.println(""+b+" insertion screen 2");
//	Screen screen_3 = new Screen("Ecran3");
//	b = screen_3.insert(bs);
//	System.out.println(""+b+" insertion screen 3");
//	
//	Screen sc;
//	sc = Screen.findById(screen_3.getId_s(),bs);
//	
//	if (sc != null) System.out.println(""+sc.getId_s()+" | "+sc.getName_s()+""); else System.out.println("find S : pas OK");
//	
//	sc.setName_s("...");
//	sc.update(bs);
//	
//	if (sc != null) System.out.println(""+sc.getId_s()+" | "+sc.getName_s()+""); else System.out.println("update S : pas OK");
//	
//	sc.delete(bs);
//	sc = Screen.findById(screen_3.getId_s(),bs);
//	
//	if (sc != null) System.out.println(""+sc.getId_s()+" | "+sc.getName_s()+""); else System.out.println("delete S : OK");
//	
//	Workgroup workgroup_1 = new Workgroup("GroupeDeTravail1");
//	b = workgroup_1.insert(bs);
//	System.out.println(""+b+" insertion workgroup 1");
//	Workgroup workgroup_2 = new Workgroup("GroupeDeTravail2");
//	b = workgroup_2.insert(bs);
//	System.out.println(""+b+" insertion workgroup 2");
//	
//	Workgroup wg;
//	wg = Workgroup.findById(workgroup_2.getId_wg(),bs);
//	
//	if (wg != null) System.out.println(""+wg.getId_wg()+" | "+wg.getName_wg()+""); else System.out.println("find WG : pas OK");
//	
//	wg.setName_wg("...");
//	wg.update(bs);
//	
//	if (wg != null) System.out.println(""+wg.getId_wg()+" | "+wg.getName_wg()+""); else System.out.println("update WG : pas OK");
//	
//	wg.delete(bs);
//	wg = Workgroup.findById(workgroup_2.getId_wg(),bs);
//	
//	if (wg != null) System.out.println(""+wg.getId_wg()+" | "+wg.getName_wg()+""); else System.out.println("delete WG : OK");
//
//	Object[] t_obj_1 = new Object[3]; t_obj_1[0] = 666; t_obj_1[1] = "hell"; t_obj_1[2] = 3.50;
//	Object[] t_obj_2 = new Object[3]; t_obj_2[0] = '!'; t_obj_2[1] = "hell"; t_obj_2[2] = 666;
//	
//	Wording wording_1 = new Wording("Wording1",t_obj_1);
//	b = wording_1.insert(bs);
//	System.out.println(""+b+" insertion wording_1");
//	Wording wording_2 = new Wording("Wording2",t_obj_2);
//	b = wording_2.insert(bs);
//	System.out.println(""+b+" insertion wording_2");
//	
//	Wording w;
//	w = Wording.findById(wording_2.getId(),bs);
//	
//	if (w != null) System.out.println(""+w.getId()+" | "+w.getText()+""); else System.out.println("find W : pas OK");
//	
//	w.setText("...");
//	w.update(bs);
//	
//	if (w != null) System.out.println(""+w.getId()+" | "+w.getText()+""); else System.out.println("update W : pas OK");
//	
//	w.delete(bs);
//	w = Wording.findById(wording_2.getId(),bs);
//	
//	if (w != null) System.out.println(""+w.getId()+" | "+w.getText()+""); else System.out.println("delete W : OK");
//	
//	ArrayList<Integer> ali1 = new ArrayList<>(); ali1.add(6); ali1.add(2); ali1.add(3); ali1.add(7);
//	ArrayList<Integer> ali2 = new ArrayList<>(); ali2.add(2); ali2.add(8); ali2.add(5); ali2.add(9);
//	ArrayList<Integer> ali3 = new ArrayList<>(); ali3.add(1); ali3.add(4); ali3.add(8); ali3.add(9);
//	ArrayList<Character> alc1 = new ArrayList<>(); alc1.add('+'); alc1.add('+'); alc1.add('-');
//	ArrayList<Character> alc2 = new ArrayList<>(); alc2.add('-'); alc2.add('*'); alc2.add('/');
//	ArrayList<Character> alc3 = new ArrayList<>(); alc3.add('/'); alc3.add('/'); alc3.add('*');
//	
//	QuestionCalculation questionCalculation_1 = new QuestionCalculation("QuestionCalculation1",2); questionCalculation_1.setOperands(ali1); questionCalculation_1.setOperators(alc1);
//	b = questionCalculation_1.insert(bs);
//	System.out.println(""+b+" insertion questionCalculation_1");
//	QuestionCalculation questionCalculation_2 = new QuestionCalculation("QuestionCalculation2",3); questionCalculation_2.setOperands(ali2); questionCalculation_2.setOperators(alc2);
//	b = questionCalculation_2.insert(bs);
//	System.out.println(""+b+" insertion questionCalculation_2");
//	QuestionCalculation questionCalculation_3 = new QuestionCalculation("QuestionCalculation3",1); questionCalculation_3.setOperands(ali3); questionCalculation_3.setOperators(alc3);
//	b = questionCalculation_3.insert(bs);
//	System.out.println(""+b+" insertion questionCalculation_3");
//	
//	QuestionCalculation qc;
//	qc = QuestionCalculation.findById(questionCalculation_3.getID(),bs);
//	
//	if (qc != null) System.out.println(""+qc.getID()+" | "+qc.getText()+""); else System.out.println("find QC : pas OK");
//	
//	qc.setText("...");
//	qc.update(bs);
//	
//	if (qc != null) System.out.println(""+qc.getID()+" | "+qc.getText()+""); else System.out.println("update QC : pas OK");
//	
//	qc.delete(bs);
//	qc = QuestionCalculation.findById(questionCalculation_3.getID(),bs);
//	
//	if (qc != null) System.out.println(""+qc.getID()+" | "+qc.getText()+""); else System.out.println("delete QC : OK");
//	
//	ArrayList<Question> alq1 = new ArrayList<Question>(); alq1.add(questionCalculation_2);  alq1.add(questionCalculation_1); 
//	ArrayList<Question> alq2 = new ArrayList<Question>(); alq1.add(questionCalculation_1);  alq1.add(questionCalculation_2); 
//	ArrayList<Question> alq3 = new ArrayList<Question>(); alq1.add(questionCalculation_2);  alq1.add(questionCalculation_2); 
//	
//	Exercise exercise_1 = new Exercise("Exercise1",wording_1,alq1,"type",0,false);
//	b = exercise_1.insert(bs);
//	System.out.println(""+b+" insertion exercise_1");
//	Exercise exercise_2 = new Exercise("Exercise2",wording_1,alq2,"type",0,false);
//	b = exercise_2.insert(bs);
//	System.out.println(""+b+" insertion exercise_2");
//	Exercise exercise_3 = new Exercise("Exercise3",wording_1,alq3,"type",0,false);
//	b = exercise_3.insert(bs);
//	System.out.println(""+b+" insertion exercise_3");
//	
//	Exercise e;
//	e = Exercise.findById(exercise_3.getId(),bs);
//	
//	if (e != null) System.out.println(""+e.getId()+" | "+e.getTitle()+""); else System.out.println("find E : pas OK");
//	
//	e.setTitle("...");
//	e.update(bs);
//	
//	if (e != null) System.out.println(""+e.getId()+" | "+e.getTitle()+""); else System.out.println("update E : pas OK");
//	
//	e.delete(bs);
//	e = Exercise.findById(exercise_3.getId(),bs);
//	
//	if (e != null) System.out.println(""+e.getId()+" | "+e.getTitle()+""); else System.out.println("delete E : OK");
//        
//        Practice practice_1 = new Practice();
//        b = practice_1.insert(bs);
//        System.out.println(""+b+" insertion practice_1");
//        Practice practice_2 = new Practice();
//        b = practice_2.insert(bs);
//        System.out.println(""+b+" insertion practice_2");
//        Practice practice_3 = new Practice();
//        b = practice_3.insert(bs);
//        System.out.println(""+b+" insertion practice_3");
//        Practice practice_4 = new Practice();
//        b = practice_4.insert(bs);
//        System.out.println(""+b+" insertion practice_4");
//        Practice practice_5 = new Practice();
//        b = practice_5.insert(bs);
//        System.out.println(""+b+" insertion practice_5");
//        
//        Practice pr;
//        pr = Practice.findById(practice_5.getId_p(),bs);
//        
//        if (pr != null) System.out.println(""+pr.getId_p()+" | "+pr.getExecution_time()+""); else System.out.println("find PR : pas OK");
//        
//        pr.setExecution_time(30);
//        pr.update(bs);
//        
//        if (pr != null) System.out.println(""+pr.getId_p()+" | "+pr.getExecution_time()+""); else System.out.println("update PR : pas OK");
//        
//        pr.delete(bs);
//        pr = Practice.findById(practice_5.getId_p(),bs);
//        
//        if (pr != null) System.out.println(""+pr.getId_p()+" | "+pr.getExecution_time()+""); else System.out.println("delete PR : OK");
    }
}
