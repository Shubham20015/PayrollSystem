package com.employee.manage;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

@SuppressWarnings("serial")
public class AddEmployee extends Frame implements ActionListener
{
//	Labels
	Label nameLabel = new Label("Name : ");
	Label genderLabel = new Label("Gender: ");
	Label designationLabel = new Label("Designation : ");
	Label departmentLabel = new Label("Department : ");
	Label dojLabel = new Label("Date of Joining : ");
	Label emailLabel = new Label("Email : ");
	Label mobileLabel = new Label("Mobile No. : ");
	Label addressLabel = new Label("Address : ");
	Label stateLabel = new Label("State : ");
	
//	Inputs
	TextField nameInput = new TextField();
	Choice genderInput = new Choice();
	TextField designationInput = new TextField();
	TextField departmentInput = new TextField();
	TextField dojInput = new TextField();
	TextField emailInput = new TextField();
	TextField mobileInput = new TextField();
	TextArea addressInput = new TextArea(null,10,2,TextArea.SCROLLBARS_NONE);
	TextField stateInput = new TextField();
	
//	Buttons
	Button submitBtn = new Button("Submit");
	Button cancelBtn = new Button("Cancel");
	
	public AddEmployee()
	{
        add(nameLabel);
		add(genderLabel);
		add(designationLabel);
		add(departmentLabel);
		add(dojLabel);
		add(emailLabel);
		add(mobileLabel);
		add(addressLabel);
		add(stateLabel);
		
		add(nameInput);
		add(genderInput);

		genderInput.add("MALE");
		genderInput.add("FEMALE");
		genderInput.add("OTHERS");
		
		add(designationInput);
		add(departmentInput);
		add(dojInput);
		add(emailInput);
		add(mobileInput);
		add(addressInput);
		add(stateInput);
		
		add(submitBtn);
		add(cancelBtn);
		
		nameLabel.setBounds(30,65,100,30); 
		genderLabel.setBounds(30,105,100,30);
		designationLabel.setBounds(30,155,100,30);
		departmentLabel.setBounds(30,205,100,30);
		dojLabel.setBounds(30,255,120,30);
		emailLabel.setBounds(30,305,100,30);
		mobileLabel.setBounds(30,355,100,30);
		addressLabel.setBounds(30,425,100,30);
		stateLabel.setBounds(30,495,100,30);
		
		nameInput.setBounds(170,65,180,25);
		genderInput.setBounds(170,110,180,25);
		designationInput.setBounds(170,155,180,25);
		departmentInput.setBounds(170,205,180,25);
		dojInput.setBounds(170,255,180,25);
		emailInput.setBounds(170,305,180,25);
		mobileInput.setBounds(170,355,180,25);
		addressInput.setBounds(170,405,180,65);
		stateInput.setBounds(170,495,180,25);
		

		submitBtn.setBounds(60,555,100,30);
		submitBtn.setBackground(new Color(30, 30, 30));
		submitBtn.setForeground(Color.white);
		cancelBtn.setBounds(220,555,100,30);
		cancelBtn.setBackground(new Color(30, 30, 30));
		cancelBtn.setForeground(Color.white);
		
		submitBtn.addActionListener(this);  
		cancelBtn.addActionListener(this);  
		
//		Frame Settings
		setTitle("Add new Employee");
		int width = 400, height = 620;
		setSize(width,height);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		int x = (dim.width-width)/2;
		int y = (dim.height-height)/2;		 
		setLocation(x, y);
		setBackground(new Color(245, 245, 245));
		setFont(new Font ("Roboto", Font.PLAIN ,15));
		requestFocusInWindow(true);	
		setResizable(false);
		setLayout(null);   
		setVisible(true);
		
		addWindowListener(new WindowAdapter()
		  {
		        public void windowClosing(WindowEvent x)
		        {
		        	dispose();
		        }
		  });
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == cancelBtn){    
            dispose();  
            return ;
        } 
		
		String name = nameInput.getText(); 
		String gender = genderInput.getItem(genderInput.getSelectedIndex());
		String designation = designationInput.getText();
		String department = departmentInput.getText();
		String dateOfJoining = dojInput.getText();
		String email = emailInput.getText();
		String mobile = mobileInput.getText();
		long mobileNum = Long.parseLong(mobile);
		String address = addressInput.getText();
		String state = stateInput.getText();
		
		Validation validator = new Validation();
		AddEmployee employeeForm = new AddEmployee();
		
		if(!validator.isValidEmail(email)) {
			JOptionPane.showMessageDialog(employeeForm,"Please enter correct email !!","Warning",JOptionPane.WARNING_MESSAGE);
			return ;
		}
		
		if(!validator.isValidMobileNo(mobile)) {
			JOptionPane.showMessageDialog(employeeForm,"Please enter indian mobile number of 10 digits","Warning",JOptionPane.WARNING_MESSAGE);
			return ;
		}
		
		if(!validator.isValidateDate(dateOfJoining)) {
			JOptionPane.showMessageDialog(employeeForm,"Please enter date in (dd/mm/yyyy) format","Warning",JOptionPane.WARNING_MESSAGE);
			return ;
		}
	
		Employee employee = new Employee(name, gender, designation, department, dateOfJoining, email, mobileNum, address, state);
		boolean response = EmployeeDao.insertEmployee(employee);
		
		if(response) {
			reset(); 
			JOptionPane.showMessageDialog(employeeForm,"Successfully Added.","Alert",JOptionPane.INFORMATION_MESSAGE);
		}else {
			JOptionPane.showMessageDialog(employeeForm,"Something gone wrong.","Alert",JOptionPane.ERROR_MESSAGE);
		}
	}
	
	public void reset() {
		nameInput.setText(null);
		genderInput.select(0);
		designationInput.setText(null);
		departmentInput.setText(null);
		dojInput.setText(null);
		emailInput.setText(null);
		mobileInput.setText(null);
		addressInput.setText(null);
		stateInput.setText(null);
	}
}