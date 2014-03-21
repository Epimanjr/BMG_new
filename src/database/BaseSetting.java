package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class BaseSetting {

    private BaseInformation bi = BaseInformation.lectureInformations();
    // INFOS BdD
    private String driver = "com.mysql.jdbc.Driver";
    //private String driver = "oracle.jdbc.driver.OracleDriver";
    private String url;
    private String query = "";
    private Connection connection;
    private Statement statement;
    private PreparedStatement p_statement;
    private ResultSet result_set;

    public BaseSetting() {
         //if(testerConnexion()) System.out.println("Connexion console ok");
         testerConnexion();
    }
    
    public void setInfo() {
        this.bi = BaseInformation.lectureInformations();
    }

    public boolean testerConnexion() {
        if (bi.getDbname().equals("")) {
            url = "jdbc:" + bi.getDriver() + ":" + bi.getUrl();
        } else {
            url = "jdbc:" + bi.getDriver() + ":" + bi.getUrl() + "/" + bi.getDbname();
        }
        try {
            /*System.out.println("url = " + url);
            System.out.println("login = " + bi.getLogin());
            System.out.println("pass = " + bi.getPassword());*/
            Class.forName(driver);
            connection = DriverManager.getConnection(url, bi.getLogin(), bi.getPassword());
            return true;
        } catch (Exception e) {
            /*System.out.println("Exception : connexion");
            e.printStackTrace();*/
        }
        return false;
    }

    public BaseInformation getBaseInformations() {
        miseAJourInfo();
        return this.bi;
    }

    public void miseAJourInfo() {
        bi = BaseInformation.lectureInformations();
    }

    public BaseInformation getBi() {
        return bi;
    }

    public void setBi(BaseInformation bi) {
        this.bi = bi;
    }

    public String getDriver() {
        return driver;
    }

    public void setDriver(String driver) {
        this.driver = driver;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public Statement getStatement() {
        return statement;
    }

    public void setStatement(Statement statement) {
        this.statement = statement;
    }

    public PreparedStatement getP_statement() {
        return p_statement;
    }

    public void setP_statement(PreparedStatement p_statement) {
        this.p_statement = p_statement;
    }

    public ResultSet getResult_set() {
        return result_set;
    }

    public void setResult_set(ResultSet result_set) {
        this.result_set = result_set;
    }
    
    public String getLogin() {
        return this.bi.getLogin();
    }
    
    public String getPassword() {
        return this.bi.getPassword();
    }
 

    /*
     * public boolean check(String login, String password)
     {
     String res = "";
     try
     {
     query = "SELECT login,password FROM utilisateur WHERE login = '"+login+"' and password = '"+password+"'";

     if (connection != null && query != null)
     {
     statement = connection.createStatement();
     result_set = statement.executeQuery(query);

     boolean b = result_set.next();

     if (result_set != null)
     {

     if (result_set.getString(1).equals(login) && result_set.getString(2).equals(password))
     {
     return true;
     }
     else System.out.println("NON OK");
     }
     else	System.out.println("result_set == null");
     }
     else System.out.println("connection == null OU query == null");

     }
     catch (SQLException sqle)
     {
     return false;
     }
     catch (Exception e)
     {
     System.out.println("Exception");
     }

     return false;
     }
     */
}
