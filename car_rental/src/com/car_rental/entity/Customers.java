package com.car_rental.entity;

import java.util.Objects;

public class Customers {
	private int customerid;
	private String fname;
	private String lname;
	private String email;
	private String phone;
	
	public Customers() {
		super();
	}

	public Customers(int customerid, String fname, String lname, String email, String phone) {
		super();
		this.customerid = customerid;
		this.fname = fname;
		this.lname = lname;
		this.email = email;
		this.phone = phone;
	}

	public Customers(String fname, String lname, String email, String phone) {
		super();
		this.fname = fname;
		this.lname = lname;
		this.email = email;
		this.phone = phone;
	}

	@Override
	public int hashCode() {
		return Objects.hash(customerid, email, fname, lname, phone);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Customers other = (Customers) obj;
		return customerid == other.customerid && Objects.equals(email, other.email)
				&& Objects.equals(fname, other.fname) && Objects.equals(lname, other.lname)
				&& Objects.equals(phone, other.phone);
	}

	public String getFname() {
		return fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

	public String getLname() {
		return lname;
	}

	public void setLname(String lname) {
		this.lname = lname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public int getCustomerid() {
		return customerid;
	}

	public void setCustomerid(int customerid) {
		this.customerid = customerid;
	}

	@Override
	public String toString() {
		return "Customers [customerid=" + customerid + ", fname=" + fname + ", lname=" + lname + ", email=" + email
				+ ", phone=" + phone + "]";
	}

}
