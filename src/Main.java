
import exceptions.EncodeException;
import java.util.ArrayList;
import java.util.Scanner;
import model.*;

public class Main {

    private static Scanner sc;

    public static void main(String[] args) {

        System.out.println("\n-----------------------\n  Welcome in B.M.G. !\n-----------------------\n");

        while (true) {
            Scanner sc = new Scanner(System.in);
            System.out.println("1 - Calculation part");
            System.out.println("2 - Fraction part");
            System.out.println("3 - Equation part");
            System.out.println("4 - Custom part");
            System.out.println("5 - Power part");
            System.out.println("6 - Save/Load part");
            System.out.println("7 - Practice part");
            int sel = sc.nextInt();
            switch (sel - 1) {

                case 0:
                    System.out.println("-------> A random calculation <-------");
                    QuestionCalculation qc1 = new QuestionCalculation();
                    qc1.generate();
                    System.out.println(qc1.getText());
                    double res1 = qc1.solve();
                    System.out.println("Result1: " + res1);
                    System.out.println("---------------------------------------------------------------------\n");

                    System.out.println("\n-------> A calculation with a given length <-------");
                    sc = new Scanner(System.in);
                    System.out.print("Length? ");
                    int l = sc.nextInt();
                    QuestionCalculation qc2 = new QuestionCalculation();
                    qc2.generate(l);
                    System.out.println(qc2);
                    double res2 = qc2.solve();
                    System.out.println("Result2: " + res2);
                    System.out.println("---------------------------------------------------------------------\n");

                    System.out.println("\n-------> A calculation with selected operators <-------");
                    sc = new Scanner(System.in);
                    char op = '+';
                    ArrayList<Character> operators = new ArrayList<Character>();
                    while (op != '0') {
                        System.out.print("Operator? ");
                        op = sc.next().charAt(0);
                        if (op != '0') {
                            operators.add(op);
                        }
                    }
                    QuestionCalculation qc3 = new QuestionCalculation();
                    qc3.generate(operators);
                    System.out.println(qc3);
                    double res3 = qc3.solve();
                    System.out.println("Result3: " + res3);
                    System.out.println("---------------------------------------------------------------------\n");

                    System.out.println("\n-------> A calculation with selected operators and given length <-------");
                    sc = new Scanner(System.in);
                    System.out.print("Length? ");
                    int l2 = sc.nextInt();
                    char op2 = '+';
                    ArrayList<Character> operators2 = new ArrayList<Character>();
                    while (op2 != '0') {
                        System.out.print("Operator? ");
                        op2 = sc.next().charAt(0);
                        if (op2 != '0') {
                            operators2.add(op2);
                        }
                    }
                    QuestionCalculation qc4 = new QuestionCalculation();
                    qc4.generate(operators2, l2);
                    System.out.println(qc4);
                    double res4 = qc4.solve();
                    System.out.println("Result4: " + res4);
                    System.out.println("---------------------------------------------------------------------\n");

                    System.out.println("\n-------> An exercise with these questions <-------");
                    Exercise ex1 = new Exercise("calculation");
                    ex1.setWording(new Wording());
                    ex1.addQuestion(qc1);
                    ex1.addQuestion(qc2);
                    ex1.addQuestion(qc3);
                    ex1.addQuestion(qc4);
                    ex1.exportToFile();
                    System.out.println(ex1);
                    System.out.println("---------------------------------------------------------------------\n");

                    System.out.println("\n-------> An exercise with a given type <-------");
                    System.out.println("Type? ");
                    String t = sc.next();
                    System.out.println("");
                    Exercise ex2 = new Exercise(t);
                    ex2.generate();
                    System.out.println(ex2);
                    System.out.println("---------------------------------------------------------------------\n");

                    break;
                case 1:
                    System.out.println("-------> A random question with fractions <-------");
                    QuestionFraction qf1 = new QuestionFraction();
                    qf1.generate();
                    System.out.println(qf1);
                    double res1f = qf1.solve();
                    System.out.println("Result1: " + res1f);
                    System.out.println("---------------------------------------------------------------------\n");

                    System.out.println("\n-------> A question with fractions with a given length <-------");
                    sc = new Scanner(System.in);
                    System.out.print("Length? ");
                    int lf = sc.nextInt();
                    QuestionFraction qf2 = new QuestionFraction();
                    qf2.generate(lf);
                    System.out.println(qf2);
                    double res2f = qf2.solve();
                    System.out.println("Result2: " + res2f);
                    System.out.println("---------------------------------------------------------------------\n");

                    System.out.println("\n-------> A question with fractions with selected operators <-------");
                    sc = new Scanner(System.in);
                    char opf = '+';
                    ArrayList<Character> operatorsf = new ArrayList<Character>();
                    while (opf != '0') {
                        System.out.print("Operator? ");
                        opf = sc.next().charAt(0);
                        if (opf != '0') {
                            operatorsf.add(opf);
                        }
                    }
                    QuestionFraction qf3 = new QuestionFraction();
                    qf3.generate(operatorsf);
                    System.out.println(qf3);
                    double res3f = qf3.solve();
                    System.out.println("Result3: " + res3f);
                    System.out.println("---------------------------------------------------------------------\n");

                    System.out.println("\n-------> A question with fractions with selected operators and given length <-------");
                    sc = new Scanner(System.in);
                    System.out.print("Length? ");
                    int l2f = sc.nextInt();
                    char op2f = '+';
                    ArrayList<Character> operators2f = new ArrayList<Character>();
                    while (op2f != '0') {
                        System.out.print("Operator? ");
                        op2f = sc.next().charAt(0);
                        if (op2f != '0') {
                            operators2f.add(op2f);
                        }
                    }
                    QuestionFraction qf4 = new QuestionFraction();
                    qf4.generate(operators2f, l2f);
                    System.out.println(qf4);
                    double res4f = qf4.solve();
                    System.out.println("Result4: " + res4f);
                    System.out.println("---------------------------------------------------------------------\n");

                    System.out.println("\n-------> An exercise with these questions <-------");
                    Exercise ex1f = new Exercise("fraction");
                    ex1f.setWording(new Wording());
                    ex1f.addQuestion(qf1);
                    ex1f.addQuestion(qf2);
                    ex1f.addQuestion(qf3);
                    ex1f.addQuestion(qf4);
                    System.out.println(ex1f);
                    System.out.println("---------------------------------------------------------------------\n");

                    System.out.println("\n-------> An exercise with a given type <-------");
                    System.out.println("Type? ");
                    String tf = sc.next();
                    System.out.println("");
                    Exercise ex2f = new Exercise(tf);
                    ex2f.generate();
                    System.out.println(ex2f);
                    System.out.println("---------------------------------------------------------------------\n");

                    break;
                case 2:
                    System.out.println("-------> A random equation <-------");
                    QuestionEquation qe1 = new QuestionEquation();
                    sc = new Scanner(System.in);
                    char opcu = '+';
                    ArrayList<Character> operatorscu = new ArrayList<Character>();
                    while (opcu != '0') {
                        System.out.print("Operator? ");
                        opcu = sc.next().charAt(0);
                        if (opcu != '0') {
                            operatorscu.add(opcu);
                        }
                    }
                    qe1.generate(1,operatorscu);
                    System.out.println(qe1);
                    double[] res1e = qe1.solve();
                    System.out.println("Result1: " + res1e[0]);
                    System.out.println("---------------------------------------------------------------------\n");

                    System.out.println("\n-------> An equation with a given length <-------");
                    sc = new Scanner(System.in);
                    //System.out.print("Length? ");
                    //int le = sc.nextInt();
                    QuestionEquation qe2 = new QuestionEquation();
                    qe2.generate(2,operatorscu);
                    System.out.println(qe2);
                    double[] res2e = qe2.solve();
                    if (res2e.length > 1) {
                        System.out.println("Result2(x1): " + res2e[0]);
                        System.out.println("Result2(x2): " + res2e[1]);
                    } else {
                        System.out.println("Result2: " + res2e[0]);
                    }
                    System.out.println("---------------------------------------------------------------------\n");
                    /*
                     System.out.println("\n-------> A question with fractions with selected operators <-------");
                     sc = new Scanner(System.in);
                     char opf = '+';
                     ArrayList<Character> operatorsf = new ArrayList<Character>();
                     while (opf != '0') {
                     System.out.print("Operator? ");
                     opf = sc.next().charAt(0);
                     if (opf != '0') {
                     operatorsf.add(opf);
                     }
                     }
                     QuestionFraction qf3 = new QuestionFraction();
                     qf3.generate(operatorsf);
                     System.out.println(qf3);
                     double res3f = qf3.solve();
                     System.out.println("Result3: " + res3f);
                     System.out.println("---------------------------------------------------------------------\n");

                     System.out.println("\n-------> A question with fractions with selected operators and given length <-------");
                     sc = new Scanner(System.in);
                     System.out.print("Length? ");
                     int l2f = sc.nextInt();
                     char op2f = '+';
                     ArrayList<Character> operators2f = new ArrayList<Character>();
                     while (op2f != '0') {
                     System.out.print("Operator? ");
                     op2f = sc.next().charAt(0);
                     if (op2f != '0') {
                     operators2f.add(op2f);
                     }
                     }
                     QuestionFraction qf4 = new QuestionFraction();
                     qf4.generate(operators2f, l2f);
                     System.out.println(qf4);
                     double res4f = qf4.solve();
                     System.out.println("Result4: " + res4f);
                     System.out.println("---------------------------------------------------------------------\n");

                     System.out.println("\n-------> An exercise with these questions <-------");
                     Exercise ex1f = new Exercise("fraction");
                     ex1f.setWording(new Wording());
                     ex1f.addQuestion(qf1);
                     ex1f.addQuestion(qf2);
                     ex1f.addQuestion(qf3);
                     ex1f.addQuestion(qf4);
                     System.out.println(ex1f);
                     System.out.println("---------------------------------------------------------------------\n");

                     System.out.println("\n-------> An exercise with a given type <-------");
                     System.out.println("Type? ");
                     String tf = sc.next();
                     System.out.println("");
                     Exercise ex2f = new Exercise(tf);
                     ex2f.generate();
                     System.out.println(ex2f);
                     System.out.println("---------------------------------------------------------------------\n");*/

                    break;
                case 3:
                    System.out.println("-------> A custom question <-------");
                    QuestionCustom qcu = null;
                    sc = new Scanner(System.in);
                    System.out.println("Wording ?");
                    String tcu = sc.nextLine();
                    System.out.println("Solution type ?\n1- int\n2- double\n3- string");
                    int i = new Integer(sc.next());
                    if (i == 1) {
                        int sol = new Integer(sc.next());
                        qcu = new QuestionCustom<Integer>(tcu,sol);
                    }
                    if (i == 2) {
                        Double[] sol = {new Double(sc.next())};
                        qcu = new QuestionCustom<Double>(tcu,sol);
                    }
                    if (i == 3) {
                        String[] sol = {sc.nextLine()};
                        qcu = new QuestionCustom<String>(tcu,sol);
                    }
                    System.out.println(qcu);
                    System.out.println("---------------------------------------------------------------------\n");
                    
                    break;
                case 4:
                    System.out.println("-------> A random question with power <-------");
                    QuestionPower qp1 = new QuestionPower();
                    qp1.generate();
                    System.out.println(qp1);
                    double res1p = qp1.solve();
                    System.out.println("Result1: " + res1p);
                    System.out.println("---------------------------------------------------------------------\n");
/*
                    System.out.println("\n-------> A calculation with a given length <-------");
                    sc = new Scanner(System.in);
                    System.out.print("Length? ");
                    int l = sc.nextInt();
                    QuestionCalculation qc2 = new QuestionCalculation();
                    qc2.generate(l);
                    System.out.println(qc2);
                    double res2 = qc2.solve();
                    System.out.println("Result2: " + res2);
                    System.out.println("---------------------------------------------------------------------\n");

                    System.out.println("\n-------> A calculation with selected operators <-------");
                    sc = new Scanner(System.in);
                    char op = '+';
                    ArrayList<Character> operators = new ArrayList<Character>();
                    while (op != '0') {
                        System.out.print("Operator? ");
                        op = sc.next().charAt(0);
                        if (op != '0') {
                            operators.add(op);
                        }
                    }
                    QuestionCalculation qc3 = new QuestionCalculation();
                    qc3.generate(operators);
                    System.out.println(qc3);
                    double res3 = qc3.solve();
                    System.out.println("Result3: " + res3);
                    System.out.println("---------------------------------------------------------------------\n");

                    System.out.println("\n-------> A calculation with selected operators and given length <-------");
                    sc = new Scanner(System.in);
                    System.out.print("Length? ");
                    int l2 = sc.nextInt();
                    char op2 = '+';
                    ArrayList<Character> operators2 = new ArrayList<Character>();
                    while (op2 != '0') {
                        System.out.print("Operator? ");
                        op2 = sc.next().charAt(0);
                        if (op2 != '0') {
                            operators2.add(op2);
                        }
                    }
                    QuestionCalculation qc4 = new QuestionCalculation();
                    qc4.generate(operators2, l2);
                    System.out.println(qc4);
                    double res4 = qc4.solve();
                    System.out.println("Result4: " + res4);
                    System.out.println("---------------------------------------------------------------------\n");

                    System.out.println("\n-------> An exercise with these questions <-------");
                    Exercise ex1 = new Exercise("calculation");
                    ex1.setWording(new Wording());
                    ex1.addQuestion(qc1);
                    ex1.addQuestion(qc2);
                    ex1.addQuestion(qc3);
                    ex1.addQuestion(qc4);
                    System.out.println(ex1);
                    System.out.println("---------------------------------------------------------------------\n");

                    System.out.println("\n-------> An exercise with a given type <-------");
                    System.out.println("Type? ");
                    String t = sc.next();
                    System.out.println("");
                    Exercise ex2 = new Exercise(t);
                    ex2.generate();
                    System.out.println(ex2);
                    System.out.println("---------------------------------------------------------------------\n");*/

                    break;
                case 5:
                    System.out.println("\n-------> An exercise with a given type <-------");
                    System.out.println("Type? ");
                    String t3 = sc.next();
                    System.out.println("Title ");
                    String t4 = sc.next();
                    System.out.println("");
                    Exercise ex5 = new Exercise(t3);
                    ex5.setTitle(t4);
                    ex5.setWording(new Wording());
                    ex5.generate();
                    //System.out.println(ex5);
                    System.out.println("---------------------------------------------------------------------\n");
                    System.out.println("Saving...");
                    ex5.save();
                    System.out.println("Loading...");
                    Exercise ex5load = new Exercise();
                    ex5load.load("ex-1.bmg");
                    System.out.println(ex5);

                    break;
                case 6:
                    System.out.println("\n-------> Practice an exercise with a given type <-------");
                    System.out.println("Type? ");
                    String tp = sc.next();
                    System.out.println("");
                    Exercise ex1p = new Exercise(tp);
                    ArrayList<Character> c = new ArrayList<Character>();
                    c.add('+');
                    c.add('-');
                    ex1p.generate(c);
                    Practice p1 = new Practice(ex1p);
                    ex1p.practiceCalculation(p1);
                    System.out.println("---------------------------------------------------------------------\n");

                    break;
                default:
                    break;
            };
        }
    }

}
