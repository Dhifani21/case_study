package com.car_rental.dao;

import java.sql.SQLException;
import java.util.List;

import com.car_rental.entity.Customers;
import com.car_rental.exception.CustomerNotFoundException;

public interface ICustomerDAO {
	public int Addcustomer(Customers customer) throws ClassNotFoundException,SQLException;
	public int updatecustomer(Customers customer) throws ClassNotFoundException,SQLException, CustomerNotFoundException;
	public int deletecustomer(int customerid) throws ClassNotFoundException,SQLException, CustomerNotFoundException;
	public Customers viewcustomer(int customerid) throws ClassNotFoundException,SQLException, CustomerNotFoundException;
	public List<Customers>viewcustomers() throws ClassNotFoundException,SQLException,CustomerNotFoundException;

}
