package tests;

import database.BaseSetting;
import exceptions.EncodeException;
import java.util.ArrayList;
import model.Exercise;
import model.Practice;
import model.Question;
import model.QuestionCalculation;
import model.QuestionCustom;
import model.QuestionEquation;
import model.QuestionFraction;
import model.QuestionPower;
import model.Wording;
import user.School;
import user.Screen;
import user.User;
import user.UserType;
import user.Workgroup;

public class ActiveRecord_Tests 
{
    @SuppressWarnings("null")
    public static void main(String[] args) throws EncodeException
    {
	try
        {
            boolean b;
	
            BaseSetting bs = new BaseSetting();
            //bs.setInfo();

            /* SCHOOL */

            School school_1 = new School("Ecole1","Type1","Rue1","Ville1","CPostal1");
            b = school_1.insert(bs);
            System.out.println(""+b+" insertion school 1");
            School school_2 = new School("Ecole2","Type2","Rue2","Ville2","CPostal2");
            b = school_2.insert(bs);
            System.out.println(""+b+" insertion school 2");
            School school_3 = new School("Ecole3","Type3","Rue3","Ville3","CPostal3");
            b = school_3.insert(bs);
            System.out.println(""+b+" insertion school 3");

            School sch;
            sch = School.findById(school_3.getId_sch(),bs); 

            if (sch != null) System.out.println(""+sch.getId_sch()+" | "+sch.getName_sch()+""); else System.out.println("find SCH : pas OK");

            sch.setName("...");
            sch.update(bs);

            if (sch != null) System.out.println(""+sch.getId_sch()+" | "+sch.getName_sch()+""); else System.out.println("update SCH : pas OK");

            sch.delete(bs);
            sch = School.findById(school_3.getId_sch(),bs);

            if (sch != null) System.out.println(""+sch.getId_sch()+""+sch.getName_sch()+""); else System.out.println("delete SCH : OK");

            /* USER & USERTYPE */

            UserType userType_1 = new UserType("Groupe1");
            b = userType_1.insert(bs);
            System.out.println(""+b+" insertion userType 1");
            UserType userType_2 = new UserType("Groupe2");
            b = userType_2.insert(bs);
            System.out.println(""+b+" insertion userType 2");
            UserType userType_3 = new UserType("Groupe3");
            b = userType_3.insert(bs);
            System.out.println(""+b+" insertion userType 3");
            UserType userType_4 = new UserType("Groupe4");
            b = userType_4.insert(bs);
            System.out.println(""+b+" insertion userType 4");
            UserType userType_5 = new UserType("Groupe5");
            b = userType_5.insert(bs);
            System.out.println(""+b+" insertion userType 5");
            UserType userType_6 = new UserType("Groupe6");
            b = userType_6.insert(bs);
            System.out.println(""+b+" insertion userType 6");

            User user_1 = new User(userType_5.getId_ut(),school_2.getId_sch(),"Prenom1","Nom1","Email1","MdP1");
            b = user_1.insert(bs);
            System.out.println(""+b+" insertion user 1");
            User user_2 = new User(userType_4.getId_ut(),school_1.getId_sch(),"Prenom2","Nom2","Email2","MdP2");
            b = user_2.insert(bs);
            System.out.println(""+b+" insertion user 2");

            User u;
            u = User.findById(user_2.getId_u(),bs);

            if (u != null) System.out.println(""+u.getId_u()+" | "+u.getFname_u()+""); else System.out.println("find U : pas OK");

            u.setFname_u("...");
            u.update(bs);

            if (u != null) System.out.println(""+u.getId_u()+" | "+u.getFname_u()+""); else System.out.println("update U : pas OK");

            u.delete(bs);
            u = User.findById(user_2.getId_u(),bs);

            if (u != null) System.out.println(""+u.getId_u()+" | "+u.getFname_u()+""); else System.out.println("delete U : OK");

            UserType ut;
            ut = UserType.findById(userType_6.getId_ut(),bs);

            if (ut != null) System.out.println(""+ut.getId_ut()+" | "+ut.getName_ut()+""); else System.out.println("find UT : pas OK");

            ut.setName_ut("...");
            ut.update(bs);

            if (ut != null) System.out.println(""+ut.getId_ut()+" | "+ut.getName_ut()+""); else System.out.println("update UT : pas OK");

            ut.delete(bs);
            ut = UserType.findById(userType_6.getId_ut(),bs);

            if (ut != null) System.out.println(""+ut.getId_ut()+" | "+ut.getName_ut()+""); else System.out.println("delete UT : OK");

            /* SCREEN */

            Screen screen_1 = new Screen("Ecran1");
            b = screen_1.insert(bs);
            System.out.println(""+b+" insertion screen 1");
            Screen screen_2 = new Screen("Ecran2");
            b = screen_2.insert(bs);
            System.out.println(""+b+" insertion screen 2");
            Screen screen_3 = new Screen("Ecran3");
            b = screen_3.insert(bs);
            System.out.println(""+b+" insertion screen 3");

            Screen sc;
            sc = Screen.findById(screen_3.getId_s(),bs);

            if (sc != null) System.out.println(""+sc.getId_s()+" | "+sc.getName_s()+""); else System.out.println("find S : pas OK");

            sc.setName_s("...");
            sc.update(bs);

            if (sc != null) System.out.println(""+sc.getId_s()+" | "+sc.getName_s()+""); else System.out.println("update S : pas OK");

            sc.delete(bs);
            sc = Screen.findById(screen_3.getId_s(),bs);

            if (sc != null) System.out.println(""+sc.getId_s()+" | "+sc.getName_s()+""); else System.out.println("delete S : OK");

            /* WORKGROUP */

            Workgroup workgroup_1 = new Workgroup("GroupeDeTravail1");
            b = workgroup_1.insert(bs);
            System.out.println(""+b+" insertion workgroup 1");
            Workgroup workgroup_2 = new Workgroup("GroupeDeTravail2");
            b = workgroup_2.insert(bs);
            System.out.println(""+b+" insertion workgroup 2");

            Workgroup wg;
            wg = Workgroup.findById(workgroup_2.getId_wg(),bs);

            if (wg != null) System.out.println(""+wg.getId_wg()+" | "+wg.getName_wg()+""); else System.out.println("find WG : pas OK");

            wg.setName_wg("...");
            wg.update(bs);

            if (wg != null) System.out.println(""+wg.getId_wg()+" | "+wg.getName_wg()+""); else System.out.println("update WG : pas OK");

            wg.delete(bs);
            wg = Workgroup.findById(workgroup_2.getId_wg(),bs);

            if (wg != null) System.out.println(""+wg.getId_wg()+" | "+wg.getName_wg()+""); else System.out.println("delete WG : OK");

            /* WORDING */

            Object[] t_obj_1 = new Object[3]; t_obj_1[0] = 666; t_obj_1[1] = "hell"; t_obj_1[2] = 3.50;
            Object[] t_obj_2 = new Object[3]; t_obj_2[0] = '!'; t_obj_2[1] = "hell"; t_obj_2[2] = 666;

            Wording wording_1 = new Wording("Wording1",t_obj_1);
            b = wording_1.insert(bs);
            System.out.println(""+b+" insertion wording_1");
            Wording wording_2 = new Wording("Wording2",t_obj_2);
            b = wording_2.insert(bs);
            System.out.println(""+b+" insertion wording_2");

            Wording w;
            w = Wording.findById(wording_2.getId(),bs);

            if (w != null) System.out.println(""+w.getId()+" | "+w.getText()+""); else System.out.println("find W : pas OK");

            w.setText("...");
            w.update(bs);

            if (w != null) System.out.println(""+w.getId()+" | "+w.getText()+""); else System.out.println("update W : pas OK");

            w.delete(bs);
            w = Wording.findById(wording_2.getId(),bs);

            if (w != null) System.out.println(""+w.getId()+" | "+w.getText()+""); else System.out.println("delete W : OK");

            /* QUESTION-CALCULATION */

            ArrayList<Integer> aliqc1 = new ArrayList<>(); aliqc1.add(6); aliqc1.add(2); aliqc1.add(3); aliqc1.add(7);
            ArrayList<Integer> aliqc2 = new ArrayList<>(); aliqc2.add(2); aliqc2.add(8); aliqc2.add(5); aliqc2.add(9);
            ArrayList<Integer> aliqc3 = new ArrayList<>(); aliqc3.add(1); aliqc3.add(4); aliqc3.add(8); aliqc3.add(9);
            ArrayList<Character> alcqc1 = new ArrayList<>(); alcqc1.add('+'); alcqc1.add('+'); alcqc1.add('-');
            ArrayList<Character> alcqc2 = new ArrayList<>(); alcqc2.add('-'); alcqc2.add('*'); alcqc2.add('/');
            ArrayList<Character> alcqc3 = new ArrayList<>(); alcqc3.add('/'); alcqc3.add('/'); alcqc3.add('*');

            QuestionCalculation questionCalculation_1 = new QuestionCalculation("QuestionCalculation1",2); questionCalculation_1.setOperands(aliqc1); questionCalculation_1.setOperators(alcqc1);
            b = questionCalculation_1.insert(bs);
            System.out.println(""+b+" insertion questionCalculation_1");
            QuestionCalculation questionCalculation_2 = new QuestionCalculation("QuestionCalculation2",3); questionCalculation_2.setOperands(aliqc2); questionCalculation_2.setOperators(alcqc2);
            b = questionCalculation_2.insert(bs);
            System.out.println(""+b+" insertion questionCalculation_2");
            QuestionCalculation questionCalculation_3 = new QuestionCalculation("QuestionCalculation3",1); questionCalculation_3.setOperands(aliqc3); questionCalculation_3.setOperators(alcqc3);
            b = questionCalculation_3.insert(bs);
            System.out.println(""+b+" insertion questionCalculation_3");

            QuestionCalculation qc;
            qc = QuestionCalculation.findById(questionCalculation_3.getID(),bs);

            if (qc != null) System.out.println(""+qc.getID()+" | "+qc.getText()+""); else System.out.println("find QC : pas OK");

            qc.setText("...");
            qc.update(bs);

            if (qc != null) System.out.println(""+qc.getID()+" | "+qc.getText()+""); else System.out.println("update QC : pas OK");

            qc.delete(bs);
            qc = QuestionCalculation.findById(questionCalculation_3.getID(),bs);

            if (qc != null) System.out.println(""+qc.getID()+" | "+qc.getText()+""); else System.out.println("delete QC : OK");

            /* QUESTION-FRACTION */

            ArrayList<Integer> aliqfn1 = new ArrayList<>(); aliqfn1.add(6); aliqfn1.add(2); aliqfn1.add(3); aliqfn1.add(7);
            ArrayList<Integer> aliqfn2 = new ArrayList<>(); aliqfn2.add(2); aliqfn2.add(8); aliqfn2.add(5); aliqfn2.add(9);
            ArrayList<Integer> aliqfn3 = new ArrayList<>(); aliqfn3.add(1); aliqfn3.add(4); aliqfn3.add(8); aliqfn3.add(9);
            ArrayList<Integer> aliqfd1 = new ArrayList<>(); aliqfd1.add(1); aliqfd1.add(4); aliqfd1.add(8); aliqfd1.add(9);
            ArrayList<Integer> aliqfd2 = new ArrayList<>(); aliqfd2.add(6); aliqfd2.add(2); aliqfd2.add(3); aliqfd2.add(7);
            ArrayList<Integer> aliqfd3 = new ArrayList<>(); aliqfd3.add(2); aliqfd3.add(8); aliqfd3.add(5); aliqfd3.add(9);
            ArrayList<Character> alcqf1 = new ArrayList<>(); alcqf1.add('+'); alcqf1.add('+'); alcqf1.add('-');
            ArrayList<Character> alcqf2 = new ArrayList<>(); alcqf2.add('-'); alcqf2.add('*'); alcqf2.add('/');
            ArrayList<Character> alcqf3 = new ArrayList<>(); alcqf3.add('/'); alcqf3.add('/'); alcqf3.add('*');

            QuestionFraction questionFraction_1 = new QuestionFraction("questionFraction_1",2); questionFraction_1.setNumerators(aliqfn1); questionFraction_1.setDenominators(aliqfd1); questionFraction_1.setOperators(alcqf1);
            b = questionFraction_1.insert(bs);
            System.out.println(""+b+" insertion questionFraction_1");
            QuestionFraction questionFraction_2 = new QuestionFraction("questionFraction_2",3); questionFraction_2.setNumerators(aliqfn2); questionFraction_2.setDenominators(aliqfd2); questionFraction_2.setOperators(alcqf2);
            b = questionFraction_2.insert(bs);
            System.out.println(""+b+" insertion questionFraction_2");
            QuestionFraction questionFraction_3 = new QuestionFraction("questionFraction_3",1); questionFraction_3.setNumerators(aliqfn3); questionFraction_3.setDenominators(aliqfd3); questionFraction_3.setOperators(alcqf3);
            b = questionFraction_3.insert(bs);
            System.out.println(""+b+" insertion questionFraction_3");

            QuestionFraction qf;
            qf = QuestionFraction.findById(questionFraction_3.getID(),bs);

            if (qf != null) System.out.println(""+qf.getID()+" | "+qf.getText()+""); else System.out.println("find QF : pas OK");

            qf.setText("...");
            qf.update(bs);

            if (qf != null) System.out.println(""+qf.getID()+" | "+qf.getText()+""); else System.out.println("update QF : pas OK");

            qf.delete(bs);
            qf = QuestionFraction.findById(questionFraction_3.getID(),bs);

            if (qf != null) System.out.println(""+qf.getID()+" | "+qf.getText()+""); else System.out.println("delete QF : OK");

            /* QUESTION-EQUATION */

            ArrayList<Integer> aliqeo1 = new ArrayList<>(); aliqeo1.add(3); aliqeo1.add(5); aliqeo1.add(3); aliqeo1.add(2);
            ArrayList<Integer> aliqeo2 = new ArrayList<>(); aliqeo2.add(7); aliqeo2.add(2); aliqeo2.add(6); aliqeo2.add(6);
            ArrayList<Integer> aliqeo3 = new ArrayList<>(); aliqeo3.add(1); aliqeo3.add(4); aliqeo3.add(4); aliqeo3.add(9);
            ArrayList<Integer> aliqeu1 = new ArrayList<>(); aliqeu1.add(5); aliqeu1.add(4); aliqeu1.add(4); aliqeu1.add(9);
            ArrayList<Integer> aliqeu2 = new ArrayList<>(); aliqeu2.add(6); aliqeu2.add(8); aliqeu2.add(2); aliqeu2.add(1);
            ArrayList<Integer> aliqeu3 = new ArrayList<>(); aliqeu3.add(3); aliqeu3.add(9); aliqeu3.add(5); aliqeu3.add(2);
            ArrayList<Character> alcqe1 = new ArrayList<>(); alcqe1.add('+'); alcqe1.add('+'); alcqe1.add('-');
            ArrayList<Character> alcqe2 = new ArrayList<>(); alcqe2.add('-'); alcqe2.add('*'); alcqe2.add('/');
            ArrayList<Character> alcqe3 = new ArrayList<>(); alcqe3.add('/'); alcqe3.add('/'); alcqe3.add('*');

            QuestionEquation questionEquation_1 = new QuestionEquation("questionEquation_1",2); questionEquation_1.setOperands(aliqeo1); questionEquation_1.setUnknowns(aliqeu1); questionEquation_1.setOperators(alcqe1);
            b = questionEquation_1.insert(bs);
            System.out.println(""+b+" insertion questionEquation_1");
            QuestionEquation questionEquation_2 = new QuestionEquation("questionEquation_2",3); questionEquation_2.setOperands(aliqeo2); questionEquation_2.setUnknowns(aliqeu2); questionEquation_2.setOperators(alcqe2);
            b = questionEquation_2.insert(bs);
            System.out.println(""+b+" insertion questionEquation_2");
            QuestionEquation questionEquation_3 = new QuestionEquation("questionEquation_3",1); questionEquation_3.setOperands(aliqeo3); questionEquation_3.setUnknowns(aliqeu3); questionEquation_3.setOperators(alcqe3);
            b = questionEquation_3.insert(bs);
            System.out.println(""+b+" insertion questionEquation_3");

            QuestionEquation qe;
            qe = QuestionEquation.findById(questionEquation_3.getID(),bs);

            if (qe != null) System.out.println(""+qe.getID()+" | "+qe.getText()+""); else System.out.println("find QE : pas OK");

            qe.setText("...");
            qe.update(bs);

            if (qe != null) System.out.println(""+qe.getID()+" | "+qe.getText()+""); else System.out.println("update QE : pas OK");

            qe.delete(bs);
            qe = QuestionEquation.findById(questionEquation_3.getID(),bs);

            if (qe != null) System.out.println(""+qe.getID()+" | "+qe.getText()+""); else System.out.println("delete QE : OK");

            /* QUESTION-POWER */

            ArrayList<Character> alcqpo1 = new ArrayList<>(); alcqpo1.add('+'); alcqpo1.add('+'); alcqpo1.add('-');
            ArrayList<Character> alcqpo2 = new ArrayList<>(); alcqpo2.add('-'); alcqpo2.add('*'); alcqpo2.add('/');
            ArrayList<Character> alcqpo3 = new ArrayList<>(); alcqpo3.add('/'); alcqpo3.add('/'); alcqpo3.add('*');
            ArrayList<Integer> aliqpp1 = new ArrayList<>(); aliqpp1.add(1); aliqpp1.add(4); aliqpp1.add(8); aliqpp1.add(9);
            ArrayList<Integer> aliqpp2 = new ArrayList<>(); aliqpp2.add(6); aliqpp2.add(2); aliqpp2.add(3); aliqpp2.add(7);
            ArrayList<Integer> aliqpp3 = new ArrayList<>(); aliqpp3.add(2); aliqpp3.add(8); aliqpp3.add(5); aliqpp3.add(9);

            QuestionPower questionPower_1 = new QuestionPower("questionPower_1",2); questionPower_1.setOperators(alcqpo1); questionPower_1.setPowers(aliqpp1);
            b = questionPower_1.insert(bs);
            System.out.println(""+b+" insertion questionPower_1");
            QuestionPower questionPower_2 = new QuestionPower("questionPower_2",3); questionPower_2.setOperators(alcqpo2); questionPower_2.setPowers(aliqpp2);
            b = questionPower_2.insert(bs);
            System.out.println(""+b+" insertion questionPower_2");
            QuestionPower questionPower_3 = new QuestionPower("questionPower_3",1); questionPower_3.setOperators(alcqpo3); questionPower_3.setPowers(aliqpp3);
            b = questionPower_3.insert(bs);
            System.out.println(""+b+" insertion questionPower_3");

            QuestionPower qp;
            qp = QuestionPower.findById(questionPower_3.getID(),bs);

            if (qp != null) System.out.println(""+qp.getID()+" | "+qp.getText()+""); else System.out.println("find QP : pas OK");

            qp.setText("...");
            qp.update(bs);

            if (qp != null) System.out.println(""+qp.getID()+" | "+qp.getText()+""); else System.out.println("update QP : pas OK");

            qp.delete(bs);
            qp = QuestionPower.findById(questionPower_3.getID(),bs);

            if (qp != null) System.out.println(""+qp.getID()+" | "+qp.getText()+""); else System.out.println("delete QP : OK");

            /* QUESTION-CUSTOM */

            Object[] o1 = new Object[]{(int) 5 ,(int) 2 ,(int) 4};
            Object[] o2 = new Object[]{(double) 3.2 , (double) 9.6};
            Object[] o3 = new Object[]{(String) "abc" , (String) "xyz"};

            QuestionCustom questionCustom_1 = new QuestionCustom("questionCustom_1",2); questionCustom_1.setSolution(o1);
            b = questionCustom_1.insert(bs);
            System.out.println(""+b+" insertion questionCustom_1");
            QuestionCustom questionCustom_2 = new QuestionCustom("questionCustom_2",3); questionCustom_2.setSolution(o2);
            b = questionCustom_2.insert(bs);
            System.out.println(""+b+" insertion questionCustom_2");
            QuestionCustom questionCustom_3 = new QuestionCustom("questionCustom_3",1); questionCustom_3.setSolution(o3);
            b = questionCustom_3.insert(bs);
            System.out.println(""+b+" insertion questionCustom_3");

            QuestionCustom qcust;
            qcust = QuestionCustom.findById(questionCustom_3.getID(),bs);

            if (qcust != null) System.out.println(""+qcust.getID()+" | "+qcust.getText()+""); else System.out.println("find QCT : pas OK");

            qcust.setText("...");
            qcust.update(bs);

            if (qcust != null) System.out.println(""+qcust.getID()+" | "+qcust.getText()+""); else System.out.println("update QCT : pas OK");

            qcust.delete(bs);
            qcust = QuestionCustom.findById(questionCustom_3.getID(),bs);

            if (qcust != null) System.out.println(""+qcust.getID()+" | "+qcust.getText()+""); else System.out.println("delete QCT : OK");

            /* EXERCISE */

            QuestionFraction qc1ex1 = new QuestionFraction("InsertBy1stExercise",2); qc1ex1.setNumerators(aliqfn1); qc1ex1.setDenominators(aliqfd1); qc1ex1.setOperators(alcqf1);
            QuestionCalculation qc2ex1 = new QuestionCalculation("InsertBy1stExercise",3); qc2ex1.setOperands(aliqc2); qc2ex1.setOperators(alcqc2);
            QuestionCalculation qc1ex2 = new QuestionCalculation("InsertBy2ndExercise",2); qc1ex2.setOperands(aliqc1); qc1ex2.setOperators(alcqc1);
            QuestionFraction qc2ex2 = new QuestionFraction("InsertBy2ndExercise",3); qc2ex2.setNumerators(aliqfn2); qc2ex2.setDenominators(aliqfd2); qc2ex2.setOperators(alcqf2);
            QuestionCalculation qc1ex3 = new QuestionCalculation("InsertBy3rdExercise",3); qc1ex3.setOperands(aliqc2); qc1ex3.setOperators(alcqc2);
            QuestionCalculation qc2ex3 = new QuestionCalculation("InsertBy3rdExercise",3); qc2ex3.setOperands(aliqc2); qc2ex3.setOperators(alcqc2);
            
            ArrayList<Question> alq1 = new ArrayList<>(); alq1.add(qc1ex1); alq1.add(qc2ex1);
            ArrayList<Question> alq2 = new ArrayList<>(); alq2.add(qc1ex2); alq2.add(qc2ex2);
            ArrayList<Question> alq3 = new ArrayList<>(); alq3.add(qc1ex3); alq3.add(qc2ex3);

            Exercise exercise_1 = new Exercise("exercise1",wording_1,alq1,"type",0,false);
            b = exercise_1.insert(bs);
            System.out.println(""+b+" insertion exercise_1");
            Exercise exercise_2 = new Exercise("exercise2",wording_1,alq2,"type",0,false);
            b = exercise_2.insert(bs);
            System.out.println(""+b+" insertion exercise_2");
            //System.in.read();
            Exercise exercise_3 = new Exercise("exercise3",wording_1,alq3,"type",0,false);
            b = exercise_3.insert(bs);
            System.out.println(""+b+" insertion exercise_3");

            Exercise e;
            e = Exercise.findById(exercise_3.getId(),bs);

            if (e != null) System.out.println(""+e.getId()+" | "+e.getTitle()+""); else System.out.println("find E : pas OK");

            e.setTitle("...");
            e.update(bs);

            if (e != null) System.out.println(""+e.getId()+" | "+e.getTitle()+""); else System.out.println("update E : pas OK");

            e.delete(bs);
            e = Exercise.findById(exercise_3.getId(),bs);

            if (e != null) System.out.println(""+e.getId()+" | "+e.getTitle()+""); else System.out.println("delete E : OK");

            /* PRACTICE */

            ArrayList<Integer> wa = new ArrayList();
            wa.add(1);
            wa.add(3);
            ArrayList<Integer> ra = new ArrayList();
            ra.add(2);
            ra.add(4);

            Practice practice_1 = new Practice(user_1.getId_u(),exercise_2); practice_1.setWrongAnswers(wa); practice_1.setRightAnswers(ra);
            //System.out.println("ID avant insertion : "+practice_1.getId_p());
            b = practice_1.insert(bs);
            //System.out.println("ID apres insertion : "+practice_1.getId_p());
            System.out.println(""+b+" insertion practice_1");
            Practice practice_2 = new Practice(user_1.getId_u(),exercise_1); practice_2.setWrongAnswers(wa); practice_2.setRightAnswers(ra);
            b = practice_2.insert(bs);
            System.out.println(""+b+" insertion practice_2");
            Practice practice_3 = new Practice(user_1.getId_u(),exercise_1); practice_3.setWrongAnswers(wa); practice_3.setRightAnswers(ra);
            b = practice_3.insert(bs);
            System.out.println(""+b+" insertion practice_3");
            Practice practice_4 = new Practice(user_1.getId_u(),exercise_2); practice_4.setWrongAnswers(wa); practice_4.setRightAnswers(ra);
            b = practice_4.insert(bs);
            System.out.println(""+b+" insertion practice_4");
            Practice practice_5 = new Practice(user_1.getId_u(),exercise_2); practice_5.setWrongAnswers(wa); practice_5.setRightAnswers(ra);
            b = practice_5.insert(bs);
            System.out.println(""+b+" insertion practice_5");

            Practice pr;
            pr = Practice.findById(practice_5.getId_p(),bs);

            if (pr != null) System.out.println(""+pr.getId_p()+" | "+pr.getExecution_time()+""); else System.out.println("find PR : pas OK");

            pr.setExecution_time(30);
            //pr.setSuccess(100.0);
            pr.update(bs);

            if (pr != null) System.out.println(""+pr.getId_p()+" | "+pr.getExecution_time()+""); else System.out.println("update PR : pas OK");

            pr.delete(bs);
            pr = Practice.findById(practice_5.getId_p(),bs);

            if (pr != null) System.out.println(""+pr.getId_p()+" | "+pr.getExecution_time()+""); else System.out.println("delete PR : OK");
        }
        catch (Exception e)
        {
            System.out.println("EXCEPTION");
            e.printStackTrace();
        }
    }
}
