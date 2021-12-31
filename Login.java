package com.employee.manage;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;

@SuppressWarnings("serial")
public class Login extends JFrame implements ActionListener

{

	JLabel l1, l2, l3;
	JTextField tf1;
	JButton btn1;
	JPasswordField p1;

	public Login()

	{

		setTitle("Login Form in Windows Form");

		setVisible(true);

		int width = 500, height = 300;
		setSize(width,height);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		int x = (dim.width-width)/2;
		int y = (dim.height-height)/2;		 
		setLocation(x, y);

		setLayout(null);

		l1 = new JLabel("Login Form in Windows Form:");

		l1.setForeground(Color.blue);

		l1.setFont(new Font("Serif", Font.BOLD, 20));

		l2 = new JLabel("Enter Username:");

		l3 = new JLabel("Enter Password:");

		tf1 = new JTextField();

		p1 = new JPasswordField();

		btn1 = new JButton("Submit");

		add(l1);

		add(l2);

		add(tf1);

		add(l3);

		add(p1);

		add(btn1);
		
		l1.setBounds(100, 30, 400, 30);

		l2.setBounds(80, 70, 200, 30);

		l3.setBounds(80, 110, 200, 30);

		tf1.setBounds(230, 70, 200, 30);

		p1.setBounds(230, 110, 200, 30);

		btn1.setBounds(185, 160, 100, 30);

		btn1.addActionListener(this);

	}

	public void actionPerformed(ActionEvent e)

	{

		showData();

	}

	public void showData() {

		String str1 = tf1.getText();
		char[] p = p1.getPassword();
		String str2 = new String(p);

		try {

			Connection con = ConDatabase.createCon();
//			CREATE TABLE login (SNO int(10) AUTO_INCREMENT PRIMARY KEY,Name varchar(20),Password varchar(20));
			PreparedStatement ps = con.prepareStatement("select Name from login where Name=? and Password=?");

			ps.setString(1, str1);

			ps.setString(2, str2);

			ResultSet rs = ps.executeQuery();
			
			if (rs.next())
			{
				JOptionPane.showMessageDialog(null,"Successfully Logged In","Success",JOptionPane.WARNING_MESSAGE);
				HomePage home = new HomePage();
				home.showMenuDemo();
				home.showimagedemo();
				dispose();
			} 
			else {

				JOptionPane.showMessageDialog(null, "Incorrect email-Id or password..Try Again with correct detail");

			}
			con.close();

		}

		catch (Exception ex)

		{

			ex.printStackTrace();

		}

	}

	public static void main(String arr[])

	{

		Login login = new Login();
		login.setVisible(true);
		
	}

}
