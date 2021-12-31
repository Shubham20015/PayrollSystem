package com.employee.manage;

import java.sql.*;
import java.sql.PreparedStatement;
import java.util.*;

public class EmployeeDao {

	public static boolean insertEmployee(Employee emp) {

		boolean success = false;

		try {
			Connection con = ConDatabase.createCon();

			String query = "insert into employees(name,gender,designation,department,dateOfJoining,email,mobileNo,address,state) values(?,?,?,?,?,?,?,?,?)";

			PreparedStatement queryStatement = con.prepareStatement(query);

//			queryStatement.setLong(1, 101);
			queryStatement.setString(1, emp.getName());
			queryStatement.setString(2, emp.getGender());
			queryStatement.setString(3, emp.getDesignation());
			queryStatement.setString(4, emp.getDepartment());
			queryStatement.setString(5, emp.getDateOfJoining());
			queryStatement.setString(6, emp.getEmail());
			queryStatement.setLong(7, emp.getMobile());
			queryStatement.setString(8, emp.getAddress());
			queryStatement.setString(9, emp.getState());

			queryStatement.executeUpdate();
			success = true;
			con.close();

//			CREATE TABLE employees (empId int(11) PRIMARY KEY AUTO_INCREMENT, name VARCHAR(20) NOT NULL,gender VARCHAR(20) NOT NULL,designation VARCHAR(20) NOT NULL,department VARCHAR(20) NOT NULL,dateOfJoining VARCHAR(20) NOT NULL,email VARCHAR(20) NOT NULL,mobileNo int(11) NOT NULL,address VARCHAR(50),state VARCHAR(20));

		} catch (Exception e) {
			e.printStackTrace();
		}
		return success;
	}

	public static boolean updateSalary(SalarySlip slip) {
		boolean success = false;
		
//		create table employees(empid int NOT NULL, hra varchar(50), da varchar(50), medicalinsurance varchar(50), ppf varchar(50), travelallowance varchar(50), basicsal varchar(50), totalsal varchar(50), PRIMARY KEY(empid));
		
		try {
			Connection con = ConDatabase.createCon();
			String query = "update salaries set hra =?, da=?, medicalinsurance=?, ppf=?, travelallowance=?, basicsal=?, totalsal=? where empid=?";
			PreparedStatement queryStatement = con.prepareStatement(query);

			queryStatement.setString(1, slip.gethra());
			queryStatement.setString(2, slip.getda());
			queryStatement.setString(3, slip.getmedinsurance());
			queryStatement.setString(4, slip.getppf());
			queryStatement.setString(5, slip.gettravelallowance());
			queryStatement.setString(6, slip.getbasicsal());
			queryStatement.setString(7, slip.gettotalsal());
			queryStatement.setLong(8, slip.getempid());

			queryStatement.executeUpdate();
			success = true;
			con.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return success;
	}

	public static boolean UpdateEmp(Employee emp) {
		boolean f = false;
		try {
			Connection con = ConDatabase.createCon();
			
			String q = "update Employee set Emp_id=?,E_name=?,Gender=?,dsgn=?,dept=?,doj=?,email=?,phno=?,address=?,state=?where Emp_id=?";
			PreparedStatement pstmt = con.prepareStatement(q);

			pstmt.setInt(1, emp.getEmpId());
			pstmt.setString(2, emp.getName());
			pstmt.setString(3, emp.getGender());
			pstmt.setString(4, emp.getDesignation());
			pstmt.setString(5, emp.getDepartment());
			pstmt.setString(6, emp.getDateOfJoining());
			pstmt.setString(7, emp.getEmail());
			pstmt.setLong(8, emp.getMobile());
			pstmt.setString(9, emp.getAddress());
			pstmt.setString(10, emp.getState());
			pstmt.executeUpdate();
			f = true;
			con.close();

		} catch (Exception e) {
			e.printStackTrace();
			f = false;
		}
		return f;
	}

	public static ArrayList<Integer> getAllId() {
		ArrayList<Integer> list = new ArrayList<>();

		try {
			Connection con = ConDatabase.createCon();

			String query = "select EmpID from employees";

			Statement queryStatement = con.createStatement();

			ResultSet set = queryStatement.executeQuery(query);

			while (set.next()) {
				int id = set.getInt(1);
				list.add(id);
			}
			con.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}
	
	public static ArrayList<Integer> getSalariesId() {
		ArrayList<Integer> list = new ArrayList<>();

		try {
			Connection con = ConDatabase.createCon();

			String query = "select empId from salaries";

			Statement queryStatement = con.createStatement();

			ResultSet set = queryStatement.executeQuery(query);

			while (set.next()) {
				int id = set.getInt(1);
				list.add(id);
			}
			con.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}
}
