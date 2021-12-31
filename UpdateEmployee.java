package com.employee.manage;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.*;
import java.util.ArrayList;

import javax.swing.*;

public class UpdateEmployee implements ActionListener, ItemListener {
	Frame fr = new Frame("Employee Details");
	Choice idList = new Choice();
	TextField nameInput = new TextField();
	Choice genderInput = new Choice();
	TextField designationInput = new TextField();
	TextField departmentInput = new TextField();
	TextField dojInput = new TextField();
	TextField emailInput = new TextField();
	TextField mobileInput = new TextField();
	TextArea addressInput = new TextArea(null,10,2,TextArea.SCROLLBARS_NONE);
	TextField stateInput = new TextField();
	Button submit = new Button("SUBMIT");
	Button exit = new Button("EXIT");
	
	public UpdateEmployee() {
		Label id = new Label("EMP ID");
		id.setBounds(30, 45, 100, 30);
		
		idList.add("Choose ID");
		try {
			ArrayList<Integer> empIdList = EmployeeDao.getAllId();
			for(int val : empIdList) {
				idList.add(val + "");
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		idList.addItemListener(new ItemListener(){
	        public void itemStateChanged(ItemEvent ie)
	        {
	        	String id = idList.getSelectedItem();
	        	if(id == "Choose ID")
	        		return ;
	        	int empID = Integer.parseInt(id);
	        	getEmployee(empID);
	        	
	        }
	    });
		
		idList.setBounds(190, 45, 180, 25);
		idList.setFont(new Font("Roboto", Font.PLAIN, 14));
		idList.setVisible(true);
		idList.addItemListener(this);
		Label name = new Label("NAME");
		name.setBounds(30, 90, 100, 30);
		
		nameInput.setBounds(190, 90, 180, 25);

		Label gender = new Label("GENDER");
		gender.setBounds(30, 135, 100, 30);
		
		genderInput.add("MALE");
		genderInput.add("FEMALE");
		genderInput.add("OTHER");
		genderInput.setBounds(190, 135, 180, 25);
		
		genderInput.setFont(new Font("Roboto", Font.PLAIN, 14));
		genderInput.setVisible(true);

		Label dept = new Label("DEPARTMENT");
		dept.setBounds(30, 180, 110, 30);
		
		departmentInput.setBounds(190, 180, 180, 25);

		Label dsgn = new Label("DESIGNATION");
		dsgn.setBounds(30, 225, 110, 30);
		
		designationInput.setBounds(190, 225, 180, 25);

		Label doj = new Label("DATE OF JOINING");
		doj.setBounds(30, 270, 150, 30);
		dojInput.setBounds(190, 270, 180, 25);

		Label email = new Label("EMAIL");
		email.setBounds(30, 315, 100, 30);
		emailInput.setBounds(190, 315, 180, 25);

		Label mobile = new Label("MOBILE NUMBER");
		mobile.setBounds(30, 360, 150, 30);
		mobileInput.setBounds(190, 360, 180, 25);

		Label address = new Label("ADDRESS");
		address.setBounds(30, 425, 100, 30);
		addressInput.setBounds(190, 405, 180, 65);

		Label state = new Label("STATE");
		state.setBounds(30, 490, 100, 30);
		stateInput.setBounds(190, 490, 180, 25);
		
		id.setBackground(new Color(245, 245, 245));
		name.setBackground(new Color(245, 245, 245));
		gender.setBackground(new Color(245, 245, 245));
		dsgn.setBackground(new Color(245, 245, 245));
		dept.setBackground(new Color(245, 245, 245));
		doj.setBackground(new Color(245, 245, 245));
		email.setBackground(new Color(245, 245, 245));
		mobile.setBackground(new Color(245, 245, 245));
		address.setBackground(new Color(245, 245, 245));
		state.setBackground(new Color(245, 245, 245));

		// SUBMIT BUTTON
		submit.setBounds(90, 570, 100, 34);
		submit.setFont(new Font("Serif", Font.BOLD, 18));
		submit.setVisible(true);
		submit.addActionListener(this);

		// EXIT BUTTON
		exit.setBounds(250, 570, 100, 34);
		exit.setFont(new Font("Serif", Font.BOLD, 18));
		exit.setVisible(true);
		exit.addActionListener(this);


//ADDING COMPONENTS TO FRAME

		fr.add(exit);
		fr.add(submit);
		fr.add(state);
		
		fr.add(address);
		
		fr.add(mobile);
		
		fr.add(email);
		
		fr.add(doj);
	
		fr.add(dept);
		
		fr.add(dsgn);
	
		fr.add(gender);
		
		fr.add(name);
		fr.add(nameInput);
		fr.add(genderInput);
		
		fr.add(designationInput);
		fr.add(departmentInput);
		fr.add(dojInput);
		fr.add(emailInput);
		fr.add(mobileInput);
		fr.add(addressInput);
		fr.add(stateInput);
		fr.add(idList);
		fr.add(id);
		fr.setLayout(null);
		int width = 450, height = 640;
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		int x = (dim.width - width) / 2;
		int y = (dim.height - height) / 2;
		fr.setSize(width, height);
		fr.setLocation(x, y);
		fr.setVisible(true);
		fr.setBackground(new Color(245, 245, 245));
		fr.setFont(new Font("Roboto", Font.PLAIN, 15));
		fr.setResizable(false);
		fr.addWindowListener(new WindowAdapter()
		  {
	        public void windowClosing(WindowEvent x)
	        {
	        	fr.dispose();
	        }
		});

	}

	public void getEmployee(int id) {

		try {
			Connection con = ConDatabase.createCon();
			String q1 = "(select name,gender,designation,department,dateOfJoining,email,mobileNo,address,state from employees where empId=?)";
			PreparedStatement pstmt = con.prepareStatement(q1);
			pstmt.setInt(1, id);
			pstmt.execute();
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				nameInput.setText(rs.getString(1));
				genderInput.select(rs.getString(2));
				designationInput.setText(rs.getString(3));
				departmentInput.setText(rs.getString(4));
				dojInput.setText(rs.getString(5));
				emailInput.setText(rs.getString(6));
				mobileInput.setText(rs.getString(7));
				addressInput.setText(rs.getString(8));
				stateInput.setText(rs.getString(9));

			}
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == submit) {
			int eid = Integer.parseInt(idList.getSelectedItem());
			String ename = nameInput.getText();
			String egender = genderInput.getSelectedItem();
			String edept = departmentInput.getText();
			String edsgn = designationInput.getText();
			String edoj = dojInput.getText();
			String eemail = emailInput.getText();
			String Ephn = mobileInput.getText();
			long ephn = Long.parseLong(Ephn);
			String eaddress = addressInput.getText();
			String estate = stateInput.getText();
			Employee emp = new Employee(eid, ename, egender, edsgn, edept, edoj, eemail, ephn, eaddress, estate);
			
			Validation validator = new Validation();
			if(!validator.isValidEmail(eemail)) {
				JOptionPane.showMessageDialog(fr,"Please enter correct email !!","Warning",JOptionPane.WARNING_MESSAGE);
				return ;
			}
			if(!validator.isValidMobileNo(Ephn)) {
				JOptionPane.showMessageDialog(fr,"Please enter indian mobile number of 10 digits","Warning",JOptionPane.WARNING_MESSAGE);
				return ;
			}
			if(!validator.isValidateDate(edoj)) {
				JOptionPane.showMessageDialog(fr,"Please enter date in (yyyy/mm/dd) format","Warning",JOptionPane.WARNING_MESSAGE);
				return ;
			}
			
			int id = Integer.parseInt(idList.getSelectedItem());
			boolean f = false;
			try {
				Connection con = ConDatabase.createCon();
		
				String q = "update employees set name=?,gender=?,designation=?,department=?,dateOfJoining=?,email=?,mobileNo=?,address=?,state=?where empId=?";
				PreparedStatement pstmt = con.prepareStatement(q);
	
				pstmt.setString(1, emp.getName());
				pstmt.setString(2, emp.getGender());
				pstmt.setString(3, emp.getDesignation());
				pstmt.setString(4, emp.getDepartment());
				pstmt.setString(5, emp.getDateOfJoining());
				pstmt.setString(6, emp.getEmail());
				pstmt.setLong(7, emp.getMobile());
				pstmt.setString(8, emp.getAddress());
				pstmt.setString(9, emp.getState());
				pstmt.setInt(10, id);
				pstmt.executeUpdate();
				f = true;
				con.close();
			} catch (Exception ex) {
				ex.printStackTrace();
				f = false;
			}

			if (f) {
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
		nameInput.setText(null);
		genderInput.select(0);
		idList.select(0);
		designationInput.setText(null);
		departmentInput.setText(null);
		dojInput.setText(null);
		emailInput.setText(null);
		mobileInput.setText(null);
		addressInput.setText(null);
		stateInput.setText(null);

	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		return ;
		
	}
}
