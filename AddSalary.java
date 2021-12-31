package com.employee.manage;

import java.awt.*;
import java.sql.*;
import java.util.ArrayList;
import java.awt.event.*;
import javax.swing.*;

@SuppressWarnings("serial")
public class AddSalary extends Frame implements ActionListener {

		Label emIdLabel = new Label("Enter EmpId :");
		Label hraLabel = new Label("HRA : ");
		Label daLabel = new Label("DA : ");
		Label medicalLabel = new Label("Medical Insurance : ");
		Label ppfLabel = new Label("PPF : ");
		Label travelLabel = new Label("Travel Allowance : ");
		Label basicLabel = new Label("Basic Salary : ");
		Label totalSalLabel = new Label("Total Salary : ");
		
		Choice empIdInput = new Choice();
		TextField hraInput = new TextField();
		TextField daInput = new TextField();
		TextField medicalInput = new TextField();
		TextField ppfInput = new TextField();
		TextField travelInput = new TextField();
		TextField basicInput = new TextField();
		TextField totalInput = new TextField();

		
		Button submitBtn = new Button("Submit");
		Button cancelBtn = new Button("Cancel");

		
		public AddSalary()
		{
	        add(emIdLabel);
			add(hraLabel);
			add(daLabel);
			add(medicalLabel);
			add(ppfLabel);
			add(travelLabel);
			add(basicLabel);
			add(totalSalLabel);
			
			add(empIdInput);
			try {
				ArrayList<Integer> empIdList = EmployeeDao.getAllId();
				for(int id : empIdList) {
					empIdInput.add(id + "");
				}
			}catch(Exception e) {
				e.printStackTrace();
			}
			add(hraInput);
			add(daInput);
			add(medicalInput);
			add(ppfInput);
			add(travelInput);
			add(basicInput);
			add(totalInput);
			
			
			add(submitBtn);
			add(cancelBtn);
			
			emIdLabel.setBounds(30,65,100,30); 
			basicLabel.setBounds(30,105,130,30);
			hraLabel.setBounds(30,155,100,30);
			daLabel.setBounds(30,205,100,30);
			medicalLabel.setBounds(30,255,133,30);
			ppfLabel.setBounds(30,305,120,30);
			travelLabel.setBounds(30,355,125,30);
			totalSalLabel.setBounds(30,405,130,30);
			
			
			empIdInput.setBounds(170,65,180,25);
			basicInput.setBounds(170,110,180,25);
			hraInput.setBounds(170,155,180,25);
			daInput.setBounds(170,205,180,25);
			medicalInput.setBounds(170,255,180,25);
			ppfInput.setBounds(170,305,180,25);
			travelInput.setBounds(170,355,180,25);
			totalInput.setBounds(170,405,180,25);
			
			
			submitBtn.setBounds(60,460,100,30);
			submitBtn.setBackground(new Color(30, 30, 30));
			submitBtn.setForeground(Color.white);
			cancelBtn.setBounds(220,460,100,30);
			cancelBtn.setBackground(new Color(30, 30, 30));
			cancelBtn.setForeground(Color.white);
			
			submitBtn.addActionListener(this);  
			cancelBtn.addActionListener(this); 
			
			int width = 400, height = 520;
			setSize(width,height);
			Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
			int x = (dim.width-width)/2;
			int y = (dim.height-height)/2;		 
			setLocation(x, y);
			setTitle("Add Salary");
			setBackground(new Color(245, 245, 245));
			setFont(new Font ("Roboto", Font.PLAIN ,15));
			setLayout(null);   
			setVisible(true);
			setResizable(false);
			addWindowListener(new WindowAdapter()
			  {
			        public void windowClosing(WindowEvent x)
			        {
			        	dispose();
			        }

		   });
		}
		
		@Override
		public void actionPerformed(ActionEvent ae) {
			
			if (ae.getSource() == cancelBtn){    
	            dispose();  
	            return ;
	        } 
	    
	     int i = Integer.parseInt(empIdInput.getSelectedItem());
	     String h = hraInput.getText();
	     String d = daInput.getText();
	     String m = medicalInput.getText();
	     String p = ppfInput.getText();
	     String t = travelInput.getText();
	     String b = basicInput.getText();
	     String f = totalInput.getText();
	     String qry = "insert into salaries values('"+i+"','"+h+"','"+d+"','"+m+"','"+p+"','"+t+"','"+b+"','"+f+"')";
		
		     try{
		    	 Connection c1 = ConDatabase.createCon();
		    	 c1.prepareStatement(qry).executeUpdate(qry);
		    	 reset();
		         JOptionPane.showMessageDialog(null,"Salary updated");
		         c1.close();
		     }catch(Exception e){
		         e.printStackTrace();
		     }
		}
			
		public void reset() {
			empIdInput.select(0);
			hraInput.setText(null);
			daInput.setText(null);
			medicalInput.setText(null);
			ppfInput.setText(null);
			travelInput.setText(null);
			basicInput.setText(null);
			totalInput.setText(null);
		}
}
