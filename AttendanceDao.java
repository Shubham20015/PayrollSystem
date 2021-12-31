package com.employee.manage;
import java.sql.*;
import java.sql.PreparedStatement;

public class AttendanceDao {
	public static boolean addAttendeance(Type t) {
		boolean f = false;
		try {
			Connection con = ConDatabase.createCon();
			// Database-attendanceRecord
			// create table attendance(emp_id int not null,type varchar(20) not
			// null,Record_time varchar(50) not null);
			String q = "insert into attendance(emp_id,type,Record_time) values(?,?,?)";
			PreparedStatement pstmt = con.prepareStatement(q);
			pstmt.setInt(1, t.getEmp_id());
			pstmt.setString(2, t.getAttn_type());
			pstmt.setString(3, java.time.LocalDateTime.now() + "");
			pstmt.executeUpdate();
			f = true;
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return f;
	}

}
