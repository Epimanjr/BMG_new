package model;

import database.BaseSetting;
import exceptions.EncodeException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Iterator;
import user.User;

public class Practice {

    private int id_p;

    private int id_u;

    /**
     *
     */
    private int execution_time;

    /**
     *
     */
    private Calendar execution_date;

    /**
     *
     */
    private double success;

    /**
     *
     */
    private ArrayList<Integer> wrong_answers;

    /**
     *
     */
    private ArrayList<Integer> right_answers;

    /**
     *
     */
    private Exercise practiced_exercise;

    public Practice(Exercise e) {
        this.execution_time = 0;
        this.execution_date = new GregorianCalendar();
        this.execution_date.setTime(new Date());
        this.success = 0.0;
        this.wrong_answers = new ArrayList<Integer>();
        this.right_answers = new ArrayList<Integer>();
        this.practiced_exercise = e;
    }

    public Practice(int idu, Exercise e) {
        this.id_u = idu;
        this.execution_time = 0;
        this.execution_date = new GregorianCalendar();
        this.execution_date.setTime(new Date());
        this.execution_date.setTime(new Date());
        this.success = 0.0;
        this.wrong_answers = new ArrayList<Integer>();
        this.right_answers = new ArrayList<Integer>();
        this.practiced_exercise = e;
    }

    public Practice() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void addRight(int r) {
        this.right_answers.add(r);
    }

    public void addWrong(int w) {
        this.wrong_answers.add(w);
    }

    public void updateSuccess() {
        this.success = ((double) this.right_answers.size() / ((double) this.right_answers.size() + (double) this.wrong_answers.size())) * 100.0;
        Calendar currentDate = new GregorianCalendar();
        currentDate.setTime(new Date());
        //this.execution_time = new Date().getSeconds() - this.execution_date.getSeconds();
        this.execution_time = currentDate.get(Calendar.SECOND) - this.execution_date.get(Calendar.SECOND);
    }

    public String encodeWrongAnswers() throws EncodeException {
        String res = new String();
        if (wrong_answers.size() > 0) {
            Iterator<Integer> itwan = wrong_answers.iterator();
            while (itwan.hasNext()) {
                res = res + itwan.next() + ":";
            }
            res = res.substring(0, res.length() - 1);
        }
        return res;
    }

    public String encodeRightAnswers() throws EncodeException {
        String res = new String();
        if (right_answers.size() > 0) {
            Iterator<Integer> itran = right_answers.iterator();
            while (itran.hasNext()) {
                res = res + itran.next() + ":";
            }
            res = res.substring(0, res.length() - 1);
        }
        return res;
    }

    public String encode() throws EncodeException {
        String res = "#Practice<";
        int id_e = practiced_exercise.getId();
        res = res + id_p + "><" + id_u + "><" + id_e + "><";
        res = res + execution_date + "><" + execution_time + "><" + success + ">\n";
        res = res + encodeWrongAnswers() + "\n";
        res = res + encodeRightAnswers() + "\n";
        res = res + practiced_exercise.encode();
        return res;
    }

    public static ArrayList<Integer> decodeRightAnswers(String str) {
        ArrayList<Integer> res = new ArrayList<Integer>();
        String[] tab = str.split(":");
        for (int x = 0; x < tab.length; x++) {
            res.add(Integer.valueOf(tab[x]));
        }
        assert res.size() > 0 : "empty right answers table";
        return res;
    }

    public static ArrayList<Integer> decodeWrongAnswers(String str) {
        ArrayList<Integer> res = new ArrayList<Integer>();
        String[] tab = str.split(":");
        for (int x = 0; x < tab.length; x++) {
            res.add(Integer.valueOf(tab[x]));
        }
        assert res.size() > 0 : "empty wrong answers table";
        return res;
    }

    public int getExecution_time() {
        return execution_time;
    }

    public Date getExecution_date() {
        return execution_date.getTime();
    }

    public String getExecution_dateString() {
        Date currentDate = this.execution_date.getTime();
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy à hh:mm");
        return format.format(currentDate);
        //return "" + this.execution_date.get(Calendar.DAY_OF_MONTH) + " " + this.execution_date.get(Calendar.MONTH) + " " + this.execution_date.get(Calendar.YEAR);
    }

    public double getSuccess() {
        return success;
    }

    public ArrayList<Integer> getWrong_answers() {
        return wrong_answers;
    }

    public String getWrong_answersString() {
        System.out.println("r:" + this.right_answers);
        System.out.println("w:" + this.wrong_answers);
        String wanswers = "";
        Iterator it = this.wrong_answers.iterator();
        while (it.hasNext()) {
            wanswers = wanswers + ((int) it.next() + 1);
            if (it.hasNext()) {
                wanswers = wanswers + ", ";
            } else {
                System.out.println("kiki");
            }
        }
        return wanswers;
    }

    public ArrayList<Integer> getRight_answers() {
        return right_answers;
    }

    public Exercise getPracticed_exercise() {
        return practiced_exercise;
    }

    public void setExecution_time(int execution_time) {
        this.execution_time = execution_time;
    }

    public int getId_p() {
        return id_p;
    }

    public boolean insert(BaseSetting bs) throws EncodeException {
        Connection connection = bs.getConnection();

        int id_e = practiced_exercise.getId();

        try {
            if (User.findById(this.id_u, bs) != null && Exercise.findById(id_e, bs) != null) {

                String query = "INSERT INTO PracticeAn (id_u,id_e,execution_date,execution_time,success,wrong_answers) VALUES (?,?,?,?,?,?)";
                PreparedStatement p_statement = connection.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
                p_statement.setInt(1, this.id_u);
                p_statement.setInt(2, id_e);
                p_statement.setString(3, ""+this.execution_date.getTime());
                p_statement.setString(4, ""+this.execution_time);
                p_statement.setDouble(5, this.success);
                p_statement.setString(6, this.encodeWrongAnswers());
                p_statement.executeUpdate();
                ResultSet rs = p_statement.getGeneratedKeys();

                if (rs.next()) {
                    this.id_p = rs.getInt(1);
                }

                return true;

            }
        } catch (SQLException sqle) {
            System.out.println("ERREUR");
            sqle.printStackTrace();
        }

        return false;
    }

    public boolean update(BaseSetting bs) throws EncodeException {
        Connection connection = bs.getConnection();

        int id_e = practiced_exercise.getId();

        try {
            if (User.findById(this.id_u, bs) != null && Exercise.findById(id_e, bs) != null) {
                String query = "UPDATE PracticeAn SET (id_u = ? , id_e = ? , execution_date = ? , execution_time = ? , success = ? , wrong_answers = ?) WHERE id_p = ?";
                PreparedStatement p_statement = connection.prepareStatement(query);
                p_statement.setInt(1, this.id_u);
                p_statement.setInt(2, id_e);
                p_statement.setString(3, ""+this.execution_date.getTime());
                p_statement.setString(4, ""+this.execution_time);
                p_statement.setDouble(5, this.success);
                p_statement.setString(6, this.encodeWrongAnswers());
                p_statement.setInt(7, this.id_p);
                p_statement.executeUpdate();
            }
        } catch (SQLException sqle) {
            System.out.println("ERREUR");
            sqle.printStackTrace();
        }

        return false;
    }

    public boolean delete(BaseSetting bs) {
        Connection connection = bs.getConnection();

        try {
            if (Practice.findById(this.getId_p(), bs) != null) {
                String query = "DELETE FROM Practice WHERE id_p = ?";
                PreparedStatement p_statement = connection.prepareStatement(query);
                p_statement.setInt(1, this.id_p);
                p_statement.executeUpdate();
            }
        } catch (SQLException sqle) {
            System.out.println("ERREUR");
            sqle.printStackTrace();
        }

        return false;
    }

    public static Practice findById(int id_p, BaseSetting bs) {
        Connection connection = bs.getConnection();

        Practice practice = null;

        try {
            String query = "SELECT * FROM PracticeAn WHERE id_p = ?";
            PreparedStatement p_statement = connection.prepareStatement(query);
            p_statement.setInt(1, id_p);

            ResultSet rs = p_statement.executeQuery();

            if (rs.next()) {
                int idp = rs.getInt("id_p");
                int idu = rs.getInt("id_u");
                int ide = rs.getInt("id_e");
                String execd = rs.getString("execution_date");
                String exect = rs.getString("execution_time");
                double s = rs.getDouble("success");
                String wa = rs.getString("wrong_answers");

                /*constructeur*/
            }

        } catch (SQLException sqle) {
            System.out.println("ERREUR");
            sqle.printStackTrace();
        }

        return practice;
    }

    public static ArrayList<Practice> findByIds(int id_u, int id_e, BaseSetting bs) {
        Connection connection = bs.getConnection();

        ArrayList<Practice> al_practice = new ArrayList<>();

        try {
            String query = "SELECT * FROM PracticeAn WHERE id_u = ? AND id_e = ?";
            PreparedStatement p_statement = connection.prepareStatement(query);
            p_statement.setInt(1, id_u);
            p_statement.setInt(2, id_e);

            ResultSet rs = p_statement.executeQuery();

            while (rs.next()) {
                int idp = rs.getInt("id_p");
                int idu = rs.getInt("id_u");
                int ide = rs.getInt("id_e");
                String execd = rs.getString("execution_date");
                String exect = rs.getString("execution_time");
                double s = rs.getDouble("success");
                String wa = rs.getString("wrrong_answers");

                /*constructeur*/
                al_practice.add(null);
            }

        } catch (SQLException sqle) {
            System.out.println("ERREUR");
            sqle.printStackTrace();
        }

        return al_practice;
    }

}
