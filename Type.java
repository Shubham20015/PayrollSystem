package com.employee.manage;

public class Type {

	private int emp_id;
	private String attn_type;

	public Type(int emp_id, String attn_type) {
		super();
		this.emp_id = emp_id;
		this.attn_type = attn_type;
	}

	public int getEmp_id() {
		return emp_id;
	}

	public void setEmp_id(int emp_id) {
		this.emp_id = emp_id;
	}

	public String getAttn_type() {
		return attn_type;
	}

	public void setAttn_type(String attn_type) {
		this.attn_type = attn_type;
	}
}
