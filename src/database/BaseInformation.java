package database;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;


public class BaseInformation implements Serializable {
	private String driver = "";
	private String namedriver = "";
	private String dbname = "";
	private String login = "";
	private String password = "";
	private String url = "";
	
	/**
	 * Constructeur qui construit l'objet.
	 * @param driver
	 * @param dbname
	 * @param login
	 * @param password
	 */
	public BaseInformation(String driver, String namedriver, String dbname, String login,
			String password, String url) {
		super();
		this.driver = driver;
		this.namedriver = namedriver;
		this.dbname = dbname;
		this.login = login;
		this.password = password;
		this.url = url;
		
		this.ecrireInformations();
	}
        
        public BaseInformation() {
            this.ecrireInformations();
        }
	
	
	
	

	public String getNamedriver() {
		return namedriver;
	}





	public void setNamedriver(String namedriver) {
		this.namedriver = namedriver;
	}





	public String getUrl() {
		return url;
	}



	public void setUrl(String url) {
		this.url = url;
	}



	public String getDriver() {
		return driver;
	}

	public void setDriver(String driver) {
		this.driver = driver;
	}

	public String getDbname() {
		return dbname;
	}

	public void setDbname(String dbname) {
		this.dbname = dbname;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public static BaseInformation lectureInformations() {
		BaseInformation res = null;
		try {
			ObjectInputStream ois =  new ObjectInputStream(new FileInputStream("infobdd.idb"));
			res = (BaseInformation)ois.readObject();
			ois.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		return res;
	}
	
	private void ecrireInformations() {
            System.out.println("Ecriture");
		try {
			ObjectOutputStream oos =  new ObjectOutputStream(new FileOutputStream("infobdd.idb"));
			oos.writeObject(this);
			oos.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		
	}
	
}
