package com.car_rental.entity;

import java.time.LocalDate;
import java.util.Objects;

public class Lease {

	private int leaseId;
	private LocalDate startDate;
	private LocalDate endDate;
	private Customers customer;
	private Vehicle vehicle;
	private String type;

	public Lease() {
		super();
	}

	public Lease(int leaseId, LocalDate startDate, LocalDate endDate, Customers customer, Vehicle vehicle,
			String type) {
		super();
		this.leaseId = leaseId;
		this.startDate = startDate;
		this.endDate = endDate;
		this.customer = customer;
		this.vehicle = vehicle;
		this.type = type;
	}

	public Lease(LocalDate startDate, LocalDate endDate, Customers customer, Vehicle vehicle, String type) {
		super();
		this.startDate = startDate;
		this.endDate = endDate;
		this.customer = customer;
		this.vehicle = vehicle;
		this.type = type;
	}

	public Lease(LocalDate startDate, LocalDate endDate, String type) {
		super();
		this.startDate = startDate;
		this.endDate = endDate;
		this.type = type;
	}

	public int getLeaseId() {
		return leaseId;
	}

	public void setLeaseId(int leaseId) {
		this.leaseId = leaseId;
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	public LocalDate getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}

	public Customers getCustomer() {
		return customer;
	}

	public void setCustomer(Customers customer) {
		this.customer = customer;
	}

	public Vehicle getVehicle() {
		return vehicle;
	}

	public void setVehicle(Vehicle vehicle) {
		this.vehicle = vehicle;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Override
	public int hashCode() {
		return Objects.hash(customer, endDate, leaseId, startDate, type, vehicle);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Lease other = (Lease) obj;
		return Objects.equals(customer, other.customer) && Objects.equals(endDate, other.endDate)
				&& leaseId == other.leaseId && Objects.equals(startDate, other.startDate)
				&& Objects.equals(type, other.type) && Objects.equals(vehicle, other.vehicle);
	}

	@Override
	public String toString() {
		return "Lease [leaseId=" + leaseId + ", startDate=" + startDate + ", endDate=" + endDate + ", customer="
				+ customer + ", vehicle=" + vehicle + ", type=" + type + "]";
	}

}