package com.employee.manage;

import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.swing.*;

@SuppressWarnings("serial")
public class UpdateSalary extends Frame implements ActionListener, ItemListener {
	// CREATING LABELS FOR FIELDS
	Label empidLabel = new Label("EmpId: ");
	Label hraLabel = new Label("HRA : ");
	Label daLabel = new Label("DA : ");
	Label medicalinsuranceLabel = new Label("Medical Insurance : ");
	Label ppfLabel = new Label("PPF : ");
	Label travelallowanceLabel = new Label("Travel Allowance : ");
	Label basicsalLabel = new Label("Basic Salary : ");
	Label totalsalLabel = new Label("Total Salary : ");

	// INPUTS FOR FIELDS
	Choice empidInput = new Choice();
	TextField hraInput = new TextField();
	TextField daInput = new TextField();
	TextField medicalinsuranceInput = new TextField();
	TextField ppfInput = new TextField();
	TextField travelallowanceInput = new TextField();
	TextArea basicsalInput = new TextArea(null, 10, 2, TextArea.SCROLLBARS_NONE);
	TextField totalsalInput = new TextField();

	// CREATING A FRAME AND DIALOG
	Frame fr = new Frame();

	Dialog d = new Dialog(fr, true);

	// YES AND NO BUTTONS FOR DIALOG BOX
	Button yes = new Button("YES");
	Button no = new Button("NO");

	// SAVE AND EXIT BUTTONS FOR DIALOG BOX
	Button submitBtn = new Button("Save");
	Button cancelBtn = new Button("Exit");

	public UpdateSalary() {
		// ADDING LABELS
		add(empidLabel);
		add(hraLabel);
		add(daLabel);
		add(medicalinsuranceLabel);
		add(ppfLabel);
		add(travelallowanceLabel);
		add(basicsalLabel);
		add(totalsalLabel);

		// ADDING INPUTS
		add(empidInput);
		add(hraInput);
		add(daInput);
		add(medicalinsuranceInput);
		add(ppfInput);
		add(travelallowanceInput);
		add(basicsalInput);
		add(totalsalInput);
		empidInput.add("Choose ID");
		try {
			ArrayList<Integer> idList = EmployeeDao.getSalariesId();
			for(int id : idList) {
				empidInput.add(id + "");
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		empidInput.addItemListener(new ItemListener(){
	        public void itemStateChanged(ItemEvent ie)
	        {
	        	String id = empidInput.getSelectedItem();
	        	if(id == "Choose ID")
	        		return ;
	        	int empID = Integer.parseInt(id);
	        	getEmployee(empID);
	        	
	        }
	    });

//	  
		// ADDING SAVE AND EXIT BUTTONS
		add(submitBtn);
		add(cancelBtn);

		// SETTING BOUNDS FOR THE LABELS

		empidLabel.setBounds(30, 55, 100, 30);
		basicsalLabel.setBounds(30, 105, 100, 30);
		hraLabel.setBounds(30, 155, 100, 30);
		daLabel.setBounds(30, 205, 100, 30);
		medicalinsuranceLabel.setBounds(30, 255, 130, 30);
		ppfLabel.setBounds(30, 305, 100, 30);
		travelallowanceLabel.setBounds(30, 355, 130, 30);
		totalsalLabel.setBounds(30, 405, 100, 30);

		// SETTING BOUNDS FOR THE INPUTS
		empidInput.setBounds(170, 60, 180, 25);
		basicsalInput.setBounds(170, 105, 180, 25);
		hraInput.setBounds(170, 155, 180, 25);
		daInput.setBounds(170, 205, 180, 25);
		medicalinsuranceInput.setBounds(170, 255, 180, 25);
		ppfInput.setBounds(170, 305, 180, 25);
		travelallowanceInput.setBounds(170, 355, 180, 25);
		totalsalInput.setBounds(170, 410, 180, 25);

		// SETTING BOUNDS AND COLORS TO THE SAVE AND EXIT BUTTON
		submitBtn.setBounds(60, 485, 100, 30);
		submitBtn.setBackground(new Color(30, 30, 30));
		submitBtn.setForeground(Color.white);
		submitBtn.addActionListener(this);

		cancelBtn.setBounds(220, 485, 100, 30);
		cancelBtn.setBackground(new Color(30, 30, 30));
		cancelBtn.setForeground(Color.white);
		cancelBtn.addActionListener(this);
		
		setTitle("Update Salary");
		int width = 400, height = 550;
		setSize(width, height);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		int x = (dim.width - width) / 2;
		int y = (dim.height - height) / 2;
		setLocation(x, y);
		setBackground(new Color(245, 245, 245));
		setFont(new Font("Roboto", Font.PLAIN, 15));
		setResizable(false);
		setLayout(null);
		setVisible(true);

		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent x) {
				dispose();
			}
		});

	}

	public void getEmployee(int id) {

		try {
			Connection con = ConDatabase.createCon();
			String query = "(select hra, da, medicalinsurance, ppf, travelallowance, basicsal,totalsal from salaries where empId=? )";
			PreparedStatement pstmt = con.prepareStatement(query);
			pstmt.setInt(1, id);
			pstmt.execute();

			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				hraInput.setText(rs.getString(1));
				daInput.setText(rs.getString(2));
				medicalinsuranceInput.setText(rs.getString(3));
				ppfInput.setText(rs.getString(4));
				travelallowanceInput.setText(rs.getString(5));
				basicsalInput.setText(rs.getString(6));
				totalsalInput.setText(rs.getString(7));
			}
			con.close();
		}

		catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if (e.getSource() == cancelBtn) {
			dispose();
			return;
		}
		if (e.getSource() == submitBtn) {
			String empid = empidInput.getItem(empidInput.getSelectedIndex());
			int empID = Integer.parseInt(empid);
			String hra = hraInput.getText();
			String da = daInput.getText();
			String medicalinsurance = medicalinsuranceInput.getText();
			String ppf = ppfInput.getText();
			String travelallowance = travelallowanceInput.getText();
			String basicsal = basicsalInput.getText();
			String totalsal = totalsalInput.getText();

			SalarySlip slip = new SalarySlip(empID, hra, da, medicalinsurance, ppf, travelallowance, basicsal, totalsal);
			boolean response = EmployeeDao.updateSalary(slip);
			if (response) {
				reset();
				JOptionPane.showMessageDialog(fr, "Successfully Updated.", "Alert", JOptionPane.WARNING_MESSAGE);
			}else {
				JOptionPane.showMessageDialog(fr, "Something gone wrong", "Alert", JOptionPane.ERROR_MESSAGE);
			}
		}

	}

	public void reset() {
		empidInput.select(0);
		hraInput.setText(null);
		daInput.setText(null);
		medicalinsuranceInput.setText(null);
		ppfInput.setText(null);
		travelallowanceInput.setText(null);
		basicsalInput.setText(null);
		totalsalInput.setText(null);

	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		return ;
	}

}
