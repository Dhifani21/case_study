package com.car_rental.entity;

public class Vehicle {
	private int vehicleid;
	private String make;
	private String model;
	private int year;
	private double dailyrate;
	private String status;
	private int p_capacity;
	private int e_capacity;
	
	public Vehicle(int vehicleid, String make, String model, int year, double dailyrate, String status, int p_capacity,
			int e_capacity) {
		super();
		this.vehicleid = vehicleid;
		this.make = make;
		this.model = model;
		this.year = year;
		this.dailyrate = dailyrate;
		this.status = status;
		this.p_capacity = p_capacity;
		this.e_capacity = e_capacity;
	}

	public Vehicle() {
		super();
	}

	public Vehicle(String make, String model, int year, double dailyrate, String status, int p_capacity,
			int e_capacity) {
		super();
		this.make = make;
		this.model = model;
		this.year = year;
		this.dailyrate = dailyrate;
		this.status = status;
		this.p_capacity = p_capacity;
		this.e_capacity = e_capacity;
	}

	public Vehicle(String make, String model) {
		super();
		this.make = make;
		this.model = model;
	}

	public int getVehicleid() {
		return vehicleid;
	}

	public void setVehicleid(int vehicleid) {
		this.vehicleid = vehicleid;
	}

	public String getMake() {
		return make;
	}

	public void setMake(String make) {
		this.make = make;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public double getDailyrate() {
		return dailyrate;
	}

	public void setDailyrate(double dailyrate) {
		this.dailyrate = dailyrate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getP_capacity() {
		return p_capacity;
	}

	public void setP_capacity(int p_capacity) {
		this.p_capacity = p_capacity;
	}

	public int getE_capacity() {
		return e_capacity;
	}

	public void setE_capacity(int e_capacity) {
		this.e_capacity = e_capacity;
	}

	@Override
	public String toString() {
		return "Vehicle [vehicleid=" + vehicleid + ", make=" + make + ", model=" + model + ", year=" + year
				+ ", dailyrate=" + dailyrate + ", status=" + status + ", p_capacity=" + p_capacity + ", e_capacity="
				+ e_capacity + "]";
	}
	
}

