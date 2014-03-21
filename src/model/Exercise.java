package model;

import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import exceptions.EncodeException;
import exceptions.DecodeException;
import database.BaseSetting;
import interfaces.iDbManager;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.Scanner;

/**
 * Exercise object containing all it components
 *
 * @author Joseph DZIMBALKA
 * @author Antoine NOSAl
 * @author Julien RISCHE
 */
public class Exercise implements iDbManager {

    // ----- ATTRIBUTES -----
    /**
     * Exercise ID
     */
    private int id;

    /**
     * Exercise title
     */
    private String title;

    /**
     * Exercise statement
     */
    private Wording wording;

    /**
     * Exercise questions
     */
    private ArrayList<Question> questions;

    /**
     * Exercise type
     */
    private String type;

    /**
     * Exercise difficulty '0' corresponds to an unknown difficulty '1' corresponds to the easiest difficulty
     */
    private int difficulty;

    /**
     * True if the exercise is ready to be used, so if the exercise has (at least): - 1 statement (not null); - 1 question in the list of questions; - a type; - a difficulty;
     */
    private boolean ready;

    // ---- CONSTRUCTORS ----
    /**
     * This constructor creates a completely random exercise
     */
    public Exercise() {
        this.id = -1;
        this.title = "Exercise";
        this.wording = new Wording();
        this.questions = new ArrayList<>();
        this.type = "";
        this.difficulty = 0;
        this.ready = false;
    }

    /**
     * This constructor creates an exercise of the type given in parameter All others characteristics are random
     *
     * @param Etype Exercise type
     */
    public Exercise(String Etype) {
        this.id = -1;
        this.title = "Exercise";
        this.wording = new Wording();
        this.questions = new ArrayList<>();
        this.difficulty = 0;
        if (Etype != null) {
            this.type = Etype;
        } else {
            this.type = null;
        }
        this.ready = false;
    }

    /**
     * This constructor creates an exercise of the type given in parameter All others characteristics are random
     *
     * @param Etype Exercise type
     */
    public Exercise(String Etitle, String Etype) {
        this.id = -1;
        this.title = Etitle;
        this.wording = new Wording();
        this.questions = new ArrayList<>();
        this.difficulty = 0;
        if (Etype != null) {
            this.type = Etype;
        } else {
            this.type = null;
        }
        this.ready = false;
    }

    /**
     * This constructor creates an exercise of the type and of a difficulty given in parameters
     *
     * @param Etype Exercise type
     * @param Edifficulty Exercise difficulty
     */
    public Exercise(String Etype, int Edifficulty) {
        this.id = -1;
        this.title = "Exercise";
        this.wording = new Wording();
        this.questions = new ArrayList<>();
        this.type = "";
        if (Edifficulty >= 0) {
            this.difficulty = Edifficulty;
        } else {
            this.difficulty = 0;
        }
        this.ready = false;
    }

    /**
     * This constructor creates an exercise of the type and of a difficulty given in parameters
     *
     * @param Etitle Exercise title
     * @param Ewording Exercise wording
     * @param Equestions Exercise questions ArrayList
     * @param Etype Exercise type
     * @param Edifficulty Exercise difficulty
     * @param Eready Exercise status
     */
    public Exercise(String Etitle, Wording Ewording, ArrayList<Question> Equestions, String Etype, int Edifficulty, boolean Eready) {
        this.title = Etitle;
        this.wording = Ewording;
        this.questions = Equestions;
        this.type = Etype;
        this.difficulty = Edifficulty;
        this.ready = Eready;
    }

    /**
     * This constructor creates an exercise of the type and of a difficulty given in parameters
     *
     * @param Eid Exercise ID
     * @param Etitle Exercise title
     * @param Ewording Exercise wording
     * @param Equestions Exercise questions ArrayList
     * @param Etype Exercise type
     * @param Edifficulty Exercise difficulty
     * @param Eready Exercise status
     */
    public Exercise(int Eid, String Etitle, Wording Ewording, ArrayList<Question> Equestions, String Etype, int Edifficulty, boolean Eready) {
        this.id = Eid;
        this.title = Etitle;
        this.wording = Ewording;
        this.questions = Equestions;
        this.type = Etype;
        this.difficulty = Edifficulty;
        this.ready = Eready;
    }

    // ----------------------
    // ------- METHODS ------
    /**
     * Generate a random exercise with 10 randoms questions, type must be precised in the attribute.
     */
    public void generate() {
        if (this.type.compareTo("") != 0) {
            if (type.compareTo("calculation") == 0) {
                for (int i = 0; i < 10; i++) {
                    QuestionCalculation qc = new QuestionCalculation();
                    qc.generate();
                    this.addQuestion(qc);
                }
            } else if (type.compareTo("fraction") == 0) {
                for (int i = 0; i < 10; i++) {
                    QuestionFraction qf = new QuestionFraction();
                    qf.generate();
                    this.addQuestion(qf);
                }
            } else if (type.compareTo("equation") == 0) {
                for (int i = 0; i < 10; i++) {
                    QuestionEquation qe = new QuestionEquation();
                    qe.generate();
                    this.addQuestion(qe);
                }
            }
        }
    }

    /**
     * Generate a random exercise with 10 randoms questions, type must be precised in the attribute.
     */
    public void generate(int numberOfQuestions) {
        if (this.type.compareTo("") != 0) {
            if (type.compareTo("calculation") == 0) {
                for (int i = 0; i < numberOfQuestions; i++) {
                    QuestionCalculation qc = new QuestionCalculation();
                    qc.generate();
                    this.addQuestion(qc);
                }
            } else if (type.compareTo("fraction") == 0) {
                for (int i = 0; i < numberOfQuestions; i++) {
                    QuestionFraction qf = new QuestionFraction();
                    qf.generate();
                    this.addQuestion(qf);
                }
            } else if (type.compareTo("equation") == 0) {
                for (int i = 0; i < numberOfQuestions; i++) {
                    QuestionEquation qe = new QuestionEquation();
                    qe.generate();
                    this.addQuestion(qe);
                }
            }
        }
    }

    /**
     * Generate a random exercise with 10 randoms questions, type must be precised in the attribute.
     */
    public void generate(int numberOfQuestions, ArrayList<Character> Eoperators) {
        if (this.type.compareTo("") != 0) {
            if (type.compareTo("calculation") == 0) {
                for (int i = 0; i < numberOfQuestions; i++) {
                    QuestionCalculation qc = new QuestionCalculation();
                    qc.generate(Eoperators);
                    this.addQuestion(qc);
                }
            } else if (type.compareTo("fraction") == 0) {
                for (int i = 0; i < numberOfQuestions; i++) {
                    QuestionFraction qf = new QuestionFraction();
                    qf.generate(Eoperators);
                    this.addQuestion(qf);
                }
            }
        }
    }

    /**
     * Generate an Exercise this 10 questions using given operators
     *
     * @param Eoperators List of allowed operators
     */
    public void generate(ArrayList<Character> Eoperators) {
        if (this.type.compareTo("") != 0) {
            if (type.compareTo("calculation") == 0) {
                for (int i = 0; i < 10; i++) {
                    QuestionCalculation qc = new QuestionCalculation();
                    qc.generate(Eoperators);
                    this.addQuestion(qc);
                }
            }
        }
    }

    /**
     * Join a Practice object to the Exercise
     *
     * @param Epractice Practice object
     */
    public void practiceCalculation(Practice Epractice) {
        Scanner sc = new Scanner(System.in);
        Iterator<Question> it_questions = this.questions.iterator();
        while (it_questions.hasNext()) {
            QuestionCalculation q = (QuestionCalculation) it_questions.next();
            System.out.println(q);
            System.out.print("Your answer? ");
            double answer = sc.nextDouble();
            if (answer == q.solve()) {
                System.out.print("  --> Right!\n");
                Epractice.addRight(this.questions.indexOf(q));
            } else {
                System.out.print("  --> Wrong!\n");
                Epractice.addWrong(this.questions.indexOf(q));
            }
        }
        Epractice.updateSuccess();
        Epractice.setExecution_time((new Date().getSeconds()) - Epractice.getExecution_date().getSeconds());
        System.out.println("Finish in " + Epractice.getExecution_time() + " seconds !\nScore:" + Epractice.getSuccess() + "% (" + Epractice.getExecution_date() + ")");
    }

    /**
     * Add a question to the exercise
     *
     * @param Equestion New question
     */
    public void addQuestion(Question Equestion) {
        if (Equestion != null) {
            this.questions.add(Equestion);
            this.update_ready();
        }
    }

    /**
     * Update the attribute ready
     */
    public void update_ready() {
        if ((this.wording != null) && (!this.questions.isEmpty())
                && (this.type != null) && (this.difficulty >= 0)) {
            this.ready = true;
        }
    }

    /**
     * True if the exercise is ready to be used
     *
     * @return Exercise status
     */
    public boolean isReady() {
        return this.ready;
    }

    /**
     * Set Exercise wording
     *
     * @param Ewording Exercise new wording
     */
    public void setWording(Wording Ewording) {
        if (Ewording != null) {
            this.wording = Ewording;
            this.update_ready();
        }
    }

    /**
     * Set Exercise type
     *
     * @param Etype Exercise new type
     */
    public void setType(String Etype) {
        if (Etype != null) {
            this.type = Etype;
            this.update_ready();
        }
    }

    /**
     * Set Exercise title
     *
     * @param Etitle Exercise new title
     */
    public void setTitle(String Etitle) {
        this.title = Etitle;
    }

    /**
     * Set Exercise difficulty level
     *
     * @param Edifficulty Exercise new difficulty
     */
    public void setDifficulty(int Edifficulty) {
        if (Edifficulty >= 0) {
            this.difficulty = Edifficulty;
            this.update_ready();
        }
    }

    /**
     * Set Exercise ID number
     *
     * @param Eid Exercise new ID number
     */
    public void setID(int Eid) {
        if (Eid > 0) {
            this.id = Eid;
        } else {
            this.id = 0;
        }
    }

    /**
     * ID number accessor
     *
     * @return Exercise ID number
     */
    public int getId() {
        return id;
    }

    /**
     * Title accessor
     *
     * @return Exercise title
     */
    public String getTitle() {
        return title;
    }

    /**
     * Type accessor
     *
     * @return Exercise type
     */
    public String getType() {
        return type;
    }

    /**
     * Difficulty level accessor
     *
     * @return Exercise difficulty
     */
    public int getDifficulty() {
        return difficulty;
    }

    /**
     * Give the number of questions contained in the Exercise
     *
     * @return Number of questions
     */
    public int getNumberOfQuestions() {
        return this.questions.size();
    }

    /**
     * Wording text accessor
     *
     * @return Wording text
     */
    public String getWordingText() {
        return this.wording.getText();
    }

    /**
     * Give one of the questions cointained in the Exercise
     *
     * @param questionNumber Number of the asked Question in Exercise list
     * @return Question asked (if existing)
     */
    public Question getQuestion(int questionNumber) {
        Question res = null;
        if ((questionNumber >= 0) && (questionNumber < questions.size())) {
            res = this.questions.get(questionNumber);
        }
        return res;
    }

    /**
     * Give text from one of the questions cointained in the Exercise
     *
     * @param questionNumber Number of the asked Question in Exercise list
     * @return Question text (if existing)
     */
    public String getQuestionText(int questionNumber) {
        String res = null;
        if ((questionNumber >= 0) && (questionNumber < questions.size())) {
            res = this.questions.get(questionNumber).getText();
        }
        return res;
    }

    /**
     * Give a text description of the Exercise
     *
     * @return Exercise text description
     */
    @Override
    public String toString() {
        String res = "--> EXERCISE";
        res = res + "\n	Statement:  " + this.wording;
        res = res + "\n	Type:       " + this.type;
        res = res + "\n	Difficulty: " + this.difficulty;
        res = res + "\n	Ready:      " + this.ready;
        res = res + "\n	Questions:  \n";
        Iterator<Question> it = this.questions.iterator();
        while (it.hasNext()) {
            res = res + it.next() + "\n";
        }
        return res;
    }

    /**
     * Encode the Exercise as a string
     *
     * @return Encoded exercise string
     * @throws EncodeException
     */
    public String encode() throws EncodeException {
        String res;
        this.update_ready();
        System.out.println(this.isReady());
        if (this.isReady()) {
            res = "#Exercise<" + id + "><" + title + "><" + type + "><" + difficulty + ">\n";
            res = res + wording.encode() + "\n";
            for (Question question : this.questions) {
                if (question != null) {
                    res = res + question.encode() + "\n";
                } else {
                    throw new EncodeException("Null value in questions ArrayList of Exercise");
                }
            }
        } else {
            res = null;
            throw new EncodeException();
        }
        return res;
    }

    /**
     * Recreate an Exercise from an encoded string
     *
     * @param encodedExercise Encoded exercise string
     * @return Decoded exercise
     * @throws DecodeException
     */
    public static Exercise decode(String encodedExercise) throws DecodeException {
        Exercise res;
        System.out.println(encodedExercise.substring(0, 10));
        if (encodedExercise.substring(0, 10).compareTo("#Exercise<") == 0) {
            res = new Exercise();
            int i = 11;
            while (encodedExercise.charAt(i) != '>') {
                i++;
            }
            res.setID(Integer.valueOf(encodedExercise.substring(10, i)));

            i++;
            int beginning = i;
            if (encodedExercise.charAt(i) == '<') {
                while (encodedExercise.charAt(i) != '>') {
                    i++;
                }
                res.setTitle(encodedExercise.substring(beginning + 1, i));

                i++;
                beginning = i;
                if (encodedExercise.charAt(i) == '<') {
                    while (encodedExercise.charAt(i) != '>') {
                        i++;
                    }
                    res.setType(encodedExercise.substring(beginning + 1, i));

                    i++;
                    beginning = i;
                    if (encodedExercise.charAt(i) == '<') {
                        while (encodedExercise.charAt(i) != '>') {
                            i++;
                        }
                        res.setDifficulty(Integer.valueOf(encodedExercise.substring(beginning + 1, i)));

                        i++;
                        encodedExercise = encodedExercise.substring(i);

                        i = 0;
                        beginning = 0;
                        while (encodedExercise.length() != i) {
                            if (encodedExercise.charAt(i) == '#') {
                                encodedExercise = encodedExercise.substring(i);
                                System.out.println(encodedExercise);
                                i = 0;
                                while (encodedExercise.charAt(i) != '<') {
                                    i++;
                                }
                                String qtype = encodedExercise.substring(beginning, i);
                                if (qtype.compareTo("#QuestionCalculaion") == 0) {
                                    QuestionCalculation qc = QuestionCalculation.decode(encodedExercise);
                                    System.out.println(qc);
                                    res.addQuestion(qc);
                                } else if (qtype.compareTo("#QuestionFraction") == 0) {
                                    QuestionFraction qf = QuestionFraction.decode(encodedExercise);
                                    System.out.println(qf);
                                    res.addQuestion(qf);
                                } else if (qtype.compareTo("#QuestionEquation") == 0) {
                                    QuestionEquation qe = QuestionEquation.decode(encodedExercise);
                                    System.out.println(qe);
                                    res.addQuestion(qe);
                                } else if (qtype.compareTo("#QuestionPower") == 0) {
                                    QuestionPower qp = QuestionPower.decode(encodedExercise);
                                    System.out.println(qp);
                                    res.addQuestion(qp);
                                } else if (qtype.compareTo("#Wording") == 0) {
                                    Wording w = Wording.decode(encodedExercise);
                                    System.out.println(w);
                                    res.setWording(w);
                                }
                                i = 0;
                            }
                            i++;
                        }
                    } else {
                        res = null;
                        throw new DecodeException();
                    }
                } else {
                    res = null;
                    throw new DecodeException();
                }
            } else {
                res = null;
                throw new DecodeException();
            }
        } else {
            res = null;
            throw new DecodeException();
        }
        return res;
    }

    /**
     * Encode the Exercise and save it in a file called : ex<ID>.bmg
     */
    public void save() {
        try (BufferedWriter file = new BufferedWriter(new FileWriter("ex" + id + ".bmg"))) {
            file.write(this.encode());
        } catch (FileNotFoundException fnfe) {
            System.out.println("ERROR : file can not be found");
        } catch (IOException ioe) {
            System.out.println("ERROR : in/out failure");
        } catch (EncodeException ee) {
            System.out.println("ERROR : encoding failure");
        }
    }

    /**
     * Recreate an Exercise by loading a file and decoding the string it contain
     *
     * @param fname Exercise save file
     * @return Exercise loaded
     */
    public static Exercise load(String fname) {
        Exercise res = null;
        try (BufferedReader file = new BufferedReader(new FileReader(fname))) {
            String str = new String();
            int i;
            while ((i = file.read()) != (-1)) {
                char c = (char) i;
                str += c;
            }
            res = Exercise.decode(str);
        } catch (FileNotFoundException fnfe) {
            System.out.println("ERROR : file can not be found");
        } catch (IOException ioe) {
            System.out.println("ERROR : in/out failure");
        } catch (DecodeException de) {
            System.out.println("ERROR : decoding failure");
        }
        return res;
    }

    public void exportToFile() {
        //Create a new Document object
        Document document = new Document();
        try {
            //Associate the document with a PDF writer and an output stream
            PdfWriter.getInstance(document, new FileOutputStream("/home/" + this.title + ".pdf"));

            //Open the document
            document.open();

            String strExercise = "";
            strExercise = strExercise + "                                       B.M.G. - Exercice\n\n";
            strExercise = strExercise + "                   Titre: " + this.title + "\n";
            strExercise = strExercise + "                   Type: " + this.type + "\n";
            strExercise = strExercise + "                   Difficulté : " + this.difficulty + "\n";
            strExercise = strExercise + "                   Nombre de questions : " + this.questions.size() + "\n\n";
            strExercise = strExercise + "                   Énoncé : " + this.wording.getText() + "\n\n\n";
            Iterator it = this.questions.iterator();
            int i = 0;
            while (it.hasNext()) {
                strExercise = strExercise + "           # Question " + (i + 1) + "\n";
                strExercise = strExercise + ((Question) (it.next())).getText();
                strExercise = strExercise + "\nRéponse : __________________\n\n";
                i++;
            }
            strExercise = strExercise + "\n\n\n                                                 Généré grâce à B.M.G.";

            //Write into the document
            document.add(new Paragraph(strExercise));
        } catch (Exception e) {
            System.err.println(e);
        } finally {
            //Close the document
            document.close();
        }
    }
    
    public void exportToFile(String s) {
        //Create a new Document object
        Document document = new Document();
        try {
            //Associate the document with a PDF writer and an output stream
            PdfWriter.getInstance(document, new FileOutputStream(s));

            //Open the document
            document.open();

            String strExercise = "";
            strExercise = strExercise + "                                       B.M.G. - Exercice\n\n";
            strExercise = strExercise + "                   Titre: " + this.title + "\n";
            strExercise = strExercise + "                   Type: " + this.type + "\n";
            strExercise = strExercise + "                   Difficulté : " + this.difficulty + "\n";
            strExercise = strExercise + "                   Nombre de questions : " + this.questions.size() + "\n\n";
            strExercise = strExercise + "                   Énoncé : " + this.wording.getText() + "\n\n\n";
            Iterator it = this.questions.iterator();
            int i = 0;
            while (it.hasNext()) {
                strExercise = strExercise + "           # Question " + (i + 1) + "\n";
                strExercise = strExercise + ((Question) (it.next())).getText();
                strExercise = strExercise + "\nRéponse : __________________\n\n";
                i++;
            }
            strExercise = strExercise + "\n\n\n                                                 Généré grâce à B.M.G.";

            //Write into the document
            document.add(new Paragraph(strExercise));
        } catch (Exception e) {
            System.err.println(e);
        } finally {
            //Close the document
            document.close();
        }
    }

    // ----------------------
    // ----- DB METHODS -----

    /* MISE A JOURS */
    @Override
    public boolean insert(BaseSetting bs) {
        //Insertion du Wording 
        this.wording.insert(bs);

        Connection connection = bs.getConnection();

        try {
            String query = "INSERT INTO Exercise (id_w,title_e,type_e,diff_e,ready_e) VALUES (?,?,?,?,?)";
            PreparedStatement p_statement = connection.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
            p_statement.setInt(1, this.wording.getId());
            p_statement.setString(2, this.title);
            p_statement.setString(3, this.type);
            p_statement.setInt(4, this.difficulty);
            p_statement.setBoolean(5, this.ready);
            p_statement.executeUpdate();
            ResultSet rs = p_statement.getGeneratedKeys();

            if (rs.next()) {
                this.id = rs.getInt(1);
            }

//            Iterator it = questions.iterator();
//            
//            while (it.hasNext())
            System.out.println("DEBUT AFFICHAGE LISTE");
            System.out.println(questions.toString());
            System.out.println("FIN AFFICHAGE LISTE");

            for (Question q : questions) {
                //(Question)(it.next()).insert(bs);

                //Object o = it.next();
//                if (!(o instanceof QuestionCustom))
//                {
//                    //o.insert(bs);
//                }
                q.insert(bs);

                String query_2 = "INSERT INTO Contains (id_e,id_q) VALUES (?,?)";
                PreparedStatement p_statement_2 = connection.prepareStatement(query_2);
                p_statement_2.setInt(1, this.getId());
                p_statement_2.setInt(2, q.getID());
                p_statement_2.executeUpdate();
                p_statement_2.close();
            }

        } catch (SQLException sqle) {
            System.out.println("ERREUR");
            sqle.printStackTrace();
        }

        //Insertions des questions
        //Parcourt des de toutes les questions de l'exercice
        //On insert la question
        //On insert dans 'Contains' le couple (this.getID(),question.getID()) !
        //Fin
        return true;
    }

    @Override
    public boolean update(BaseSetting bs) {
        Connection connection = bs.getConnection();

        try {
            if (this.id < 0) {
                String query = "UPDATE Exercise SET (id_w = ? , title_e = ? , type_e = ? , diff_e = ? , ready_e = ?) WHERE id_e = ?";
                PreparedStatement p_statement = connection.prepareStatement(query);
                p_statement.setInt(1, this.wording.getId());
                p_statement.setString(2, this.title);

                /*
                 questions : 
                 parcours de la liste de questions avec recupperation de l'id chaque question 
                 et 
                 insertion dans contains du couple (ide,idq) avec ide constant et idq de variant
                 */
                p_statement.setString(3, this.type);
                p_statement.setInt(4, this.difficulty);
                p_statement.setBoolean(5, this.ready);
                p_statement.setInt(6, this.id);
                p_statement.executeUpdate();
            }
        } catch (SQLException sqle) {
            System.out.println("ERREUR");
            sqle.printStackTrace();
        }

        return true;
    }

    @Override
    public boolean delete(BaseSetting bs) {
        Connection connection = bs.getConnection();

        try {
            if (Exercise.findById(this.getId(), bs) != null) {
                String query = "DELETE FROM Exercise WHERE id_e = ?";
                PreparedStatement p_statement = connection.prepareStatement(query);
                p_statement.setInt(1, this.id);
                p_statement.executeUpdate();
            }
        } catch (SQLException sqle) {
            System.out.println("ERREUR");
            sqle.printStackTrace();
        }

        return true;
    }

    /* FINDERS */
    public static Exercise findById(int id, BaseSetting bs) {
        Connection connection = bs.getConnection();

        Exercise exercise = null;

        try {
            String query = "SELECT * FROM Exercise WHERE id_e = ?";
            PreparedStatement p_statement = connection.prepareStatement(query);
            p_statement.setInt(1, id);

            ResultSet rs = p_statement.executeQuery();

            if (rs.next()) {
                int ide = rs.getInt("id_e");
                int idw = rs.getInt("id_w");
                String titlee = rs.getString("title_e");
                String typee = rs.getString("type_e");
                int diffe = rs.getInt("diff_e");
                int readye = rs.getInt("ready_e");
                boolean readye_b = false;
                if (readye == 1) {
                    readye_b = true;
                }
                Wording wordinge = Wording.findById(idw, bs);

                exercise = new Exercise(ide, titlee, wordinge, Exercise.findById_AllQuestions(id, bs), typee, diffe, readye_b);
            }

        } catch (SQLException sqle) {
            System.out.println("ERREUR");
            sqle.printStackTrace();
        }

        return exercise;
    }

    public static ArrayList<Question> findById_AllQuestions(int id, BaseSetting bs) {
        Connection connection = bs.getConnection();

        ArrayList<Question> alq = new ArrayList<Question>();

        try {
            String query = "SELECT * FROM Contains WHERE id_e = ?";
            PreparedStatement p_statement = connection.prepareStatement(query);
            p_statement.setInt(1, id);

            ResultSet rs = p_statement.executeQuery();

            while (rs.next()) {
                int idq = rs.getInt("id_q");

                if (QuestionCalculation.findById(idq, bs) != null)
                alq.add(QuestionCalculation.findById(idq, bs));
                if (QuestionFraction.findById(idq, bs) != null)
                alq.add(QuestionFraction.findById(idq, bs));
                if (QuestionEquation.findById(idq, bs) != null)
                alq.add(QuestionEquation.findById(idq, bs));
            }

        } catch (SQLException sqle) {
            System.out.println("ERREUR");
            sqle.printStackTrace();
        }

        return alq;
    }

    public static ArrayList<Exercise> findAll(BaseSetting bs) {
        Connection connection = bs.getConnection();

        ArrayList<Exercise> ale = new ArrayList<Exercise>();

        try {
            String query = "SELECT * FROM Exercise";
            PreparedStatement p_statement = connection.prepareStatement(query);
            ResultSet rs = p_statement.executeQuery();

            int i = 0;
            
            while (rs.next()) {
                System.out.println(i++);
                int ide = rs.getInt("id_e");
                int idw = rs.getInt("id_w");
                String titlee = rs.getString("title_e");
                String typee = rs.getString("type_e");
                int diffe = rs.getInt("diff_e");
                int readye = rs.getInt("ready_e");
                boolean readye_b = false;
                if (readye == 1) {
                    readye_b = true;
                }
                Wording wordinge = Wording.findById(idw, bs);
                ArrayList<Question> alq = Exercise.findById_AllQuestions(ide, bs);
                System.out.println("\n\nDEBUT AFFICHAGE ARRAYLIST QUESTIONS\n\n" + alq + "\n\nFIN AFFICHAGE ARRAYLIST QUESTIONS\n\n");
                Exercise e = new Exercise(ide, titlee, wordinge, alq, typee, diffe, readye_b);             
                System.out.println("\n\nDEBUT AFFICHAGE ARRAYLIST EXERCISES\n\n" + ale + "\n\nFIN AFFICHAGE ARRAYLIST EXERCISES\n\n");
                ale.add(e);
            }
        } catch (SQLException sqle) {
            System.out.println("ERREUR");
            sqle.printStackTrace();
        }

        return ale;
    }

    // ----------------------
}
