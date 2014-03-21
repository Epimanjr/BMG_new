import exceptions.DecodeException;
import exceptions.EncodeException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Exercise;
import model.QuestionCalculation;
import model.QuestionEquation;
import model.QuestionFraction;
import model.QuestionPower;
import model.Wording;


public class TestKernel57 {

	public static void main(String[] args) {
        try {
            QuestionCalculation qcal = new QuestionCalculation();
            ArrayList<Integer> opd = new ArrayList<Integer>();
            opd.add(12);
            opd.add(84);
            opd.add(45);
            opd.add(90);
            ArrayList<Character> opt = new ArrayList<Character>();
            opt.add('+');
            opt.add('/');
            opt.add('-');
            qcal.setOperands(opd);
            qcal.setOperators(opt);
            qcal.setLength(4);
            qcal.setDifficulty(1);
            qcal.setID(2);
            qcal.setText("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed vulputate lacus eu odio ultricies porta.");
            
            String str = new String();
            try {
                str = qcal.encode();
                //System.out.println("encode 1 qcal :" + str);
                QuestionCalculation qcal2 = QuestionCalculation.decode(str);
                //System.out.println("decode 1 qcal2 : " + qcal2);
                str = qcal2.encode();
                //System.out.println(str+ "\n");
            } catch (EncodeException ee) {
            } catch (DecodeException de) {}
            
            QuestionFraction qfra = new QuestionFraction();
            ArrayList<Integer> num = new ArrayList<Integer>();
            num.add(12);
            num.add(84);
            num.add(45);
            num.add(90);
            ArrayList<Integer> dnm = new ArrayList<Integer>();
            dnm.add(5);
            dnm.add(3);
            dnm.add(2);
            dnm.add(45);
            opt = new ArrayList<Character>();
            opt.add('+');
            opt.add('/');
            opt.add('-');
            qfra.setNumerators(num);
            qfra.setDenominators(dnm);
            qfra.setOperators(opt);
            qfra.setLength(4);
            qfra.setDifficulty(3);
            qfra.setID(8);
            qfra.setText("Sed vulputate lacus eu odio ultricies porta. Lorem ipsum dolor sit amet, consectetur adipiscing elit.");
            //System.out.println(qfra.encode()+ "\n");
            
            QuestionEquation qequ = new QuestionEquation();
            opd = new ArrayList<Integer>();
            opd.add(12);
            opd.add(84);
            opd.add(45);
            opd.add(90);
            ArrayList<Integer> ukn = new ArrayList<Integer>();
            ukn.add(0);
            ukn.add(0);
            ukn.add(1);
            ukn.add(0);
            opt = new ArrayList<Character>();
            opt.add('+');
            opt.add('/');
            opt.add('=');
            qequ.setOperands(opd);
            qequ.setOperators(opt);
            qequ.setUnknowns(ukn);
            qequ.setLength(4);
            //System.out.println(qequ.encode());
            
            QuestionPower qpow = new QuestionPower();
            opt = new ArrayList<Character>();
            opt.add('*');
            opt.add('/');
            opt.add('*');
            qpow.setOperand(4);
            qpow.setPowers(dnm);
            qpow.setOperators(opt);
            qpow.setLength(4);
            qpow.setDifficulty(3);
            qpow.setID(8);
            qpow.setText("Sed vulputate lacus eu odio ultricies porta. Lorem ipsum dolor sit amet, consectetur adipiscing elit.");
            
            Exercise ex = new Exercise();
            ex.setTitle("machin");
            ex.setID(3);
            Object[] tval = {78,"ezr",9.32,34,'c',24};
            ex.setWording(new Wording("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed vulputate lacus eu odio ultricies porta. Cras blandit aliquam nisi at iaculis. Pellentesque tincidunt neque et est ultrices, nec luctus risus consequat. Pellentesque sed est magna. Phasellus ullamcorper ligula eu est vehicula, sit amet hendrerit leo malesuada. Cras fringilla lorem sit amet pharetra porttitor. Nullam venenatis convallis nisi. Nulla sem sem. ", tval));
            ex.setType("calculation");
            ex.setDifficulty(4);
            ex.addQuestion(qcal);
            ex.addQuestion(qfra);
            ex.addQuestion(qpow);
            ex.update_ready();
            try {
                System.out.println("\n\n" + ex);
                str = ex.encode();
                System.out.println("\n\n" + str);
                Exercise e = Exercise.decode(str);
                System.out.println(e);
            } catch (EncodeException ee) {
            } catch (DecodeException de) {}
            
            System.out.println(qpow.solve());
            
            System.out.println();
            double d = 1.0 / 3;
            System.out.println(d);
            
            System.out.println(d * 3);
            
            QuestionEquation qe = QuestionEquation.decode("#QuestionEquation<9:3:7:3><1:0:2:2><-:*:=><4><-1><$<Solve.>$><0>");
            double[] res = qe.solve();
            System.out.println("\n" + res[0]);
            System.out.println(res[1]);
        } catch (DecodeException ex1) {
            Logger.getLogger(TestKernel57.class.getName()).log(Level.SEVERE, null, ex1);
        }
	}

}