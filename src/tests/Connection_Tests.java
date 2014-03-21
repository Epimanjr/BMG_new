package tests;

import database.BaseSetting;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.lang.*;
import java.net.*;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import user.User;
import user.UserType;

public class Connection_Tests  extends JFrame implements MouseListener
{
	// CONSTANTES DE LA FENETRE
	private static final String TITRE="Brilliant Mathematicians Generator";
	private static int LARGEUR=500;
	private static int HAUTEUR=300;
	
	// JPANELS DE LA FENETRE
	private JPanel jp_start = new JPanel();
	private JPanel jp_center = new JPanel();
	private JPanel jp_end = new JPanel();
	
	// LAYOUT DU JPANEL_CENTRE
	private GridLayout gl = new GridLayout(4,1);
	
	// DESCRIPTION DE LA FENETRE
	private JLabel message = new JLabel("Fenêtre de connexion.");
	
	// INFOS BdD
	private String driver = "com.mysql.jdbc.Driver";
	private String url = "jdbc:mysql://localhost:3306/BMG_db";
	private String query = "";
	private Connection connection;
	private Statement statement;
	private PreparedStatement p_statement;
	private ResultSet result_set;
	
	// LOGIN/PASSWORD/GROUPE
	private String login;
	private String password;
	private String groupe;
	
	// MENU DE CONNEXION
	private JTextField jtf_login = new JTextField(10);
	private JPasswordField jpf_password = new JPasswordField(10);
	private JButton jb_connexion = new JButton("Connexion");
	private JButton jb_mdp_oublie = new JButton("MDP oublié");
	private JLabel jl_message = new JLabel("Veuillez entrer vos identifiants");
	private BaseSetting bs = new BaseSetting();
	
	// MENU PRINCIPAL
	//
	
	// MENU DE ...
	//
	
	// MENU DE ...
	//
	
	// MENU DE ...
	//
	
	// MENU DE ...
	//
	
	public Connection_Tests()
	{
		super();
		
		setPreferredSize(new Dimension(LARGEUR,HAUTEUR));
		setResizable(false);
		setLocationRelativeTo(null);
		setTitle(TITRE);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		jp_center.setLayout(gl);
		
		try
		{
			Class.forName(driver);
			connection = DriverManager.getConnection(url,"Joseph","j0j0");
		}
		catch (Exception e)
		{
			System.out.println("Exception : connexion");
			e.printStackTrace();
		}
		
		// menu affiché par défaut
		afficherMenuConnexion();
		
		jp_end.add(message);
		
		getContentPane().add(jp_start,BorderLayout.PAGE_START);
		getContentPane().add(jp_center,BorderLayout.CENTER);
		getContentPane().add(jp_end,BorderLayout.PAGE_END);
		pack();
		requestFocus();
		setVisible(true);
	}
	
	public void effacerPanel()
	{
		jp_center.removeAll();
		repaint();
	}
	
	public void afficherMenuConnexion()
	{
		effacerPanel();
		setResizable(false);
		setPreferredSize(new Dimension(LARGEUR,HAUTEUR));
		
		// ajouter le MouseListener aux boutons
		jb_connexion.addMouseListener(this);
		jb_mdp_oublie.addMouseListener(this);
		
		// définir une taille pour le bouton de connexion
		jb_connexion.setPreferredSize(new Dimension(100,20));
		jb_connexion.setMinimumSize(new Dimension(100,20));
		jb_connexion.setMaximumSize(new Dimension(100,20));
		
		// définir une taille pour le bouton de mdp oublié
		jb_mdp_oublie.setPreferredSize(new Dimension(100,20));
		jb_mdp_oublie.setMinimumSize(new Dimension(100,20));
		jb_mdp_oublie.setMaximumSize(new Dimension(100,20));
		
		// centrer les éléments
		jtf_login.setHorizontalAlignment(SwingConstants.CENTER);
		jpf_password.setHorizontalAlignment(SwingConstants.CENTER);
		jb_connexion.setHorizontalAlignment(SwingConstants.CENTER);
		jb_mdp_oublie.setHorizontalAlignment(SwingConstants.CENTER);
		
		// définir le nombre de lignes/colonnes du GridLayout
		gl.setRows(5);
		gl.setColumns(1);
		
		// définir des panels
		JPanel jp11 = new JPanel();
		JPanel jp21 = new JPanel();
		JPanel jp31 = new JPanel();
		JPanel jp41 = new JPanel();
		JPanel jp51 = new JPanel();
		
		// ajouter les éléments aux panels
		jp11.add(jtf_login);
		jp21.add(jpf_password);
		jp31.add(jb_connexion);
		jp41.add(jb_mdp_oublie);
		jp51.add(jl_message);
		
		// ajouter les panels au JPanel
		jp_center.add(jp11);
		jp_center.add(jp21);
		jp_center.add(jp31);
		jp_center.add(jp41);
		jp_center.add(jp51);
		
	}
	
	public void afficherMenuPrincipal()
	{
		effacerPanel();
		
		/* 
		* A COMPLETER 
		* afficher le menu principal
		*/
		
		if (groupe.equals("eleve"))
		{
			System.out.println("MENU - ELEVE");
		}
		if (groupe.equals("professeur"))
		{
			System.out.println("MENU - PROFESSEUR");
		}
		if (groupe.equals("DB admin"))
		{
			System.out.println("MENU - DB ADMIN");
		}
	}
	
	public void afficherMenu1()
	{
		effacerPanel();
		
		/* 
		* A COMPLETER 
		* afficher le menu n°1
		*/
	}
	
	public void afficherMenu2()
	{
		effacerPanel();
		
		/* 
		* A COMPLETER 
		* afficher le menu n°2
		*/
	}
	
	public void afficherMenu3()
	{
		effacerPanel();
		
		/* 
		* A COMPLETER 
		* afficher le menu n°3
		*/
	}
	
	public void afficherMenu4()
	{
		effacerPanel();
		
		/* 
		* A COMPLETER 
		* afficher le menu n°4
		*/
	}
	
	public void mouseClicked(MouseEvent me)
	{
		
		try
		{
			if (me.getSource() == jb_connexion)
			{
				login = jtf_login.getText();
				password = String.valueOf(jpf_password.getPassword());
				
				System.out.println("Email : "+login);
				System.out.println("MotDePasse : "+password);
				
				User u = null;
				
				Connection co = bs.getConnection();
				
				System.out.println("avant IF");
				if (User.checkPresence(bs,login,password))
				{
				    System.out.println("debut IF");
				    u = User.findByLogs(login,password,bs);
				    groupe = UserType.findById(u.getId_ut(),bs).getName_ut();
				    System.out.println("OK : " + groupe);
				    /**/
				    afficherMenuPrincipal();
				    setResizable(true);
				    setExtendedState(MAXIMIZED_BOTH);
				    /**/
				}
				else
				{
					System.out.println("NON OK");
				}
				
			}
			if (me.getSource() == jb_mdp_oublie)
			{
				/* 
				* A COMPLETER : 
				* interroger la base de données,
				* demander à l'élève de "prouver" son identité, 
				* (question/réponse secrètes ?)
				* changer le mot de passe 
				* et envoyer un mail à l'adresse associée
				* ou demander de changer le mot de passe
				*/

				if (SwingUtilities.isLeftMouseButton(me))
				{
//					statement = connection.createStatement();
//					query = "CREATE TABLE table_test (champ1_test VARCHAR(30) NOT NULL, champ2_test INTEGER, PRIMARY KEY (champ1_test))";
//					statement.executeUpdate(query);
//					System.out.println("Creation reussie (create table)");
//					query = "INSERT INTO table_test VALUES ('valeur numero 1',2)";
//					statement.executeUpdate(query);
//					System.out.println("Enregistrement 1/2 reussi (insert v1)");
//					query = "INSERT INTO table_test VALUES ('valeur numero 2',2)";
//					statement.executeUpdate(query);
//					System.out.println("Enregistrement 2/2 reussi (insert v2)");
//					query = "UPDATE table_test SET champ2_test = 1 WHERE champ1_test = 'valeur numero 1'";
//					statement.executeUpdate(query);
//					System.out.println("Modification reussie (update v1)");
//					query = "DELETE FROM table_test WHERE champ1_test = 'valeur numero 2'";
//					statement.executeUpdate(query);
//					System.out.println("Effacement reussi (delete v2)");
				}
				if (SwingUtilities.isRightMouseButton(me))
				{
//					statement = connection.createStatement();
//					query = "DROP TABLE table_test";
//					statement.executeUpdate(query);
//					System.out.println("Suppression reussie (drop table)");
				}
				
			}
		}
//		catch (SQLException sqle)
//		{
//			jl_message.setText("Mauvaise combinaison login/password");
//			System.out.println("SQL Exception");
//			sqle.printStackTrace();
//			
//		}
		catch (Exception e)
		{
			System.out.println("Exception");
			e.printStackTrace();
		}
		
	}
	
	public void mouseEntered(MouseEvent me) {}
	
	public void mouseExited(MouseEvent me) {}
	
	public void mousePressed(MouseEvent me) {}
	
	public void mouseReleased(MouseEvent me) {}

	
	public static void main(String[] args)
	{
	    Connection_Tests ct = new Connection_Tests();
	}
}
