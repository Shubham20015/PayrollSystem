package com.employee.manage;

import java.sql.*;

public class ConDatabase {
	static Connection con;
	
	public static Connection createCon() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			String user = "root";
			String password = "";
			String url = "jdbc:mysql://localhost:3306/employee_manage";
			
			con = DriverManager.getConnection(url,user, password);
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return con;
	}
}
