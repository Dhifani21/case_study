package com.car_rental.service;

import java.util.List;

import com.car_rental.entity.Customers;

public interface ICustomerService {
	public int Addcustomer(Customers customer);
	public int updatecustomer(Customers customer);
	public int deletecustomer(int customerid);
	public Customers viewcustomer(int customerid);
	public List<Customers>viewcustomers();

}
