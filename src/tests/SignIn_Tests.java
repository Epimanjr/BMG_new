package tests;

import database.BaseSetting;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import user.User;

public class SignIn_Tests
{
	public static void main(String[] args)
	{
		final BaseSetting bs = new BaseSetting();
	    
		JFrame jf = new JFrame("SignIn windows");
		JPanel jp = new JPanel();
		JPanel jpbis = new JPanel();
		BorderLayout bl = new BorderLayout();
		GridLayout gl = new GridLayout(5,2);
		final JLabel jl = new JLabel("SignIn here !");
		final JButton jb = new JButton("SignIn");
		final JLabel jl_fname = new JLabel("First name");
		final JTextField jtf_fname = new JTextField("Prenom");
		final JLabel jl_lname = new JLabel("Last name");
		final JTextField jtf_lname = new JTextField("NomDeFamille");
		final JLabel jl_school = new JLabel("School");
		final JTextField jtf_school = new JTextField("Ecole");
		final JLabel jl_email = new JLabel("Email");
		final JTextField jtf_email = new JTextField("email@exemple.fr");
		final JLabel jl_pass = new JLabel("Password");
		final JPasswordField jpf_pass = new JPasswordField("mdp");
		
		jb.addMouseListener
		(
		new MouseListener() 
		{
			@Override
			public void mouseClicked(MouseEvent me) 
			{
				if (me.getSource() == jb)
				{
					int idut = 444;
					String s_fname = jtf_fname.getText();
					String s_lname = jtf_lname.getText();
					String s_school = jtf_school.getText();
					String s_email = jtf_email.getText();
					String s_pass = jpf_pass.getText();
					
					int i_school = Integer.parseInt(s_school);
					
					User u = new User(idut,i_school,s_fname,s_lname,s_email,s_pass);
					u.insert(bs);
					
					int id = User.findById(u.getId_u(),bs).getId_u();
					jl.setText("Thanks and welcome "+s_fname+"! Your id is : "+id);
				}
			}
			
			@Override
			public void mousePressed(MouseEvent me) {}
			
			@Override
			public void mouseReleased(MouseEvent me) {}
			
			@Override
			public void mouseEntered(MouseEvent me) {}
			
			@Override
			public void mouseExited(MouseEvent me) {}
		}
		);
		
		jpbis.setLayout(gl);
		
		jpbis.add(jl_fname);
		jpbis.add(jtf_fname);
		jpbis.add(jl_lname);
		jpbis.add(jtf_lname);
		jpbis.add(jl_school);
		jpbis.add(jtf_school);
		jpbis.add(jl_email);
		jpbis.add(jtf_email);
		jpbis.add(jl_pass);
		jpbis.add(jpf_pass);
		
		jp.setLayout(bl);
		
		jp.add(jl,BorderLayout.NORTH);
		jp.add(jpbis,BorderLayout.CENTER);
		jp.add(jb,BorderLayout.SOUTH);
		
		jf.add(jp);
		jf.setSize(400,200);
		jf.setResizable(false);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.setVisible(true);
	}
}
