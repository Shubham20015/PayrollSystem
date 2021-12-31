package com.employee.manage;

public class SalarySlip {

	private int empid;
	private String hra;
	private String da;
	private String medinsurance;
	private String ppf;
	private String travelallowance;
	private String basicsal;
	private String totalsal;
//	private String address;
//	private String state;
	
	public int getempid() {
		return empid;
	}

	public void setempid(int empid) {
		this.empid = empid;
	}

	public String gethra() {
		return hra;
	}

	public void sethra(String hra) {
		this.hra = hra;
	}

	public String getda() {
		return da;
	}

	public void setda(String da) {
		this.da = da;
	}

	public String getmedinsurance() {
		return medinsurance;
	}

	public void setmedinsurance(String medinsurance) {
		this.medinsurance = medinsurance;
	}

	public String getppf() {
		return ppf;
	}
	public void setppf(String ppf) {
		this.ppf = ppf;
	}

	public String gettravelallowance() {
		return travelallowance;
	}

	public void settravelallowance(String travelallowance) {
		this.travelallowance = travelallowance;
	}

	public String getbasicsal() {
		return basicsal;
	}

	public void setbasicsal(String basicsal) {
		this.basicsal = basicsal;
	}

	public String gettotalsal() {
		return totalsal;
	}

	public void settotalsal(String totalsal) {
		this.totalsal = totalsal;
	}
	
	public SalarySlip(int empid, String hra, String da, String medinsurance, String ppf, String travelallowance,
			String basicsal, String totalsal) {
		this.empid = empid;
		this.hra = hra;
		this.da = da;
		this.medinsurance = medinsurance;
		this.ppf = ppf;
		this.travelallowance = travelallowance;
		this.basicsal = basicsal;this.totalsal = totalsal;
	}
	
	

	public SalarySlip(String hra, String da, String medinsurance, String ppf, String travelallowance,
			String basicsal, String totalsal) {
		this.hra = hra;
		this.da = da;
		this.medinsurance = medinsurance;
		this.ppf = ppf;
		this.travelallowance = travelallowance;
		this.basicsal = basicsal;
		this.totalsal = totalsal;
	}

	@Override
	public String toString() {
		return "Employee [empid=" + empid + ", hra=" + hra + ", da=" + da + ", medinsurance=" + medinsurance
				+ ", ppf=" + ppf + ", travelallowance=" + travelallowance + ", basicsal=" + basicsal + ", totalsal="
				+ totalsal + "]"; 	}

}

