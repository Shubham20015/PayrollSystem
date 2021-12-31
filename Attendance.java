package com.employee.manage;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.*;

public class Attendance implements ActionListener, ItemListener {
	Frame fr = new Frame("ATTENDANCE");
	TextField t1 = new TextField();
	Choice idList = new Choice();
	Choice c1 = new Choice();
	Button submit = new Button("SUBMIT");
	Button exit = new Button("EXIT");
	Dialog d1 = new Dialog(fr);
	Button yes = new Button("YES");
	Button no = new Button("NO");
	Button addB = new Button("ADD");
	Button delete = new Button("DELETE");

	public Attendance() {
		Label id = new Label("EMPLOYEE ID:");
		id.setBounds(30, 50, 120, 25);
		id.setBackground(new Color(245, 245, 245));
		idList.setBounds(190, 50, 130, 20);
		idList.setFont(new Font("Roboto", Font.PLAIN, 14));
		try {
			ArrayList<Integer> empIdList = EmployeeDao.getAllId();
			for(int val : empIdList) {
				idList.add(val + "");
			}
		}catch(Exception e) {
			System.out.print("error");
			e.printStackTrace();
		}

		idList.setVisible(true);
		Label type = new Label("ATTENDANCE:");
		type.setBounds(30, 110, 120, 25);

		type.setBackground(new Color(245, 245, 245));

		c1.add("FULL DAY");
		c1.add("HALF DAY");
		c1.add("PAID LEAVE");
		c1.add("UNPAID LEAVE");
		c1.setBounds(190, 110, 130, 20);
		c1.setFont(new Font("Roboto", Font.PLAIN, 14));
		c1.setVisible(true);

		// SUBMIT BUTTON
		submit.setBounds(50, 250, 100, 34);
		submit.setFont(new Font("Serif", Font.BOLD, 18));
		submit.setVisible(true);
		submit.addActionListener(this);

		// EXIT BUTTON
		exit.setBounds(240, 250, 100, 34);
		exit.setFont(new Font("Serif", Font.BOLD, 18));
		exit.setVisible(true);
		exit.addActionListener(this);

		fr.add(exit);
		fr.add(submit);
		fr.add(c1);
		fr.add(type);
		fr.add(t1);
		fr.add(idList);
		fr.add(id);
		fr.setLayout(null);
		fr.setSize(400, 400);
		fr.setVisible(true);
		int width = 400, height = 720;
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		int x = (dim.width - width) / 2;
		int y = (dim.height - height) / 2;
		fr.setLocation(x, y);
		fr.setFont(new Font("Roboto", Font.PLAIN, 15));
		fr.setResizable(false);
		fr.setBackground(new Color(245, 245, 245));
		fr.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent x) {
				fr.dispose();
			}
		});

	}

	public void itemStateChanged(ItemEvent ie) {

		int id = Integer.parseInt(idList.getSelectedItem());
		
		
		try {
			Connection con = ConDatabase.createCon();
			String q1 = "(select distinct Emp_id from attendance)";
			PreparedStatement pstmt = con.prepareStatement(q1);
			pstmt.setInt(1, id);
			pstmt.execute();
			con.close();
		} catch (SQLException e) {

			e.printStackTrace();
		}

	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == submit) {
			int e_id = Integer.parseInt(idList.getSelectedItem());
			String attn = c1.getSelectedItem();
			Type t1 = new Type(e_id, attn);
			boolean ans = AttendanceDao.addAttendeance(t1);
			if (ans) {
				reset();
				JOptionPane.showMessageDialog(fr, "Successfully Added.", "Alert", JOptionPane.INFORMATION_MESSAGE);
			} else {
				JOptionPane.showMessageDialog(fr, "Something gone wrong.", "Alert", JOptionPane.ERROR_MESSAGE);
			}
		}
		if (e.getSource() == exit) {
			fr.dispose();
		}
	}

	public void reset() {
		idList.select(0);
		c1.select(0);
	}
}
