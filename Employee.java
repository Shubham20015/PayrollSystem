package com.employee.manage;

public class Employee {
	private int empId;
	private String name;
	private String gender;
	private String designation;
	private String department;
	private String dateOfJoining;
	private String email;
	private long mobile;
	private String address;
	private String state;
	
	public int getEmpId() {
		return empId;
	}

	public void setEmpId(int empId) {
		this.empId = empId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getDateOfJoining() {
		return dateOfJoining;
	}

	public void setDateOfJoining(String dateOfJoining) {
		this.dateOfJoining = dateOfJoining;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public long getMobile() {
		return mobile;
	}

	public void setMobile(long mobile) {
		this.mobile = mobile;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}
	
	public Employee() {
		super();
	}
	
	public Employee(int empId, String name, String gender, String designation, String department, String dateOfJoining,
			String email, long mobile, String address, String state) {
		this.empId = empId;
		this.name = name;
		this.gender = gender;
		this.designation = designation;
		this.department = department;
		this.dateOfJoining = dateOfJoining;
		this.email = email;
		this.mobile = mobile;
		this.address = address;
		this.state = state;
	}
	
	

	public Employee(String name, String gender, String designation, String department, String dateOfJoining,
			String email, long mobile, String address, String state) {
		this.name = name;
		this.gender = gender;
		this.designation = designation;
		this.department = department;
		this.dateOfJoining = dateOfJoining;
		this.email = email;
		this.mobile = mobile;
		this.address = address;
		this.state = state;
	}

	@Override
	public String toString() {
		return "Employee [empId=" + empId + ", name=" + name + ", gender=" + gender + ", designation=" + designation
				+ ", department=" + department + ", dateOfJoining=" + dateOfJoining + ", email=" + email + ", mobile="
				+ mobile + ", address=" + address + ", state=" + state + "]";
	}

	

}
