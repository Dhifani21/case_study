package com.car_rental.service;

import java.sql.SQLException;
import java.util.List;

import com.car_rental.dao.CustomerDAOImpl;
import com.car_rental.dao.ICustomerDAO;
import com.car_rental.entity.Customers;
import com.car_rental.exception.CustomerNotFoundException;

public class CustomerserviceImpl implements ICustomerService {
	
	private ICustomerDAO icustomerDAO;
	

	public CustomerserviceImpl() {
		super();
		icustomerDAO = new CustomerDAOImpl();
	}

	@Override
	public int Addcustomer(Customers customer) {
		int result=0;
		try {
			result=icustomerDAO.Addcustomer(customer);
		}catch(ClassNotFoundException cnfe) {
			System.out.println("looks like driver not found");
		}catch(SQLException se) {
			System.out.println("either url,username or password are wrong");
		}
		return result;
	}

	@Override
	public int updatecustomer(Customers customer) {
		int result=0;
		try {
			result=icustomerDAO.updatecustomer(customer);
		}catch(ClassNotFoundException cnfe) {
			System.out.println("looks like driver not found");
		}catch(SQLException se) {
			System.out.println("either url,username or password are wrong");
		}catch(CustomerNotFoundException ce) {
			System.out.println(ce.getMessage());
		}
		return result;	
	}

	@Override
	public int deletecustomer(int customerid) {
		int result=0;
		try {
		result=icustomerDAO.deletecustomer(customerid);
		}catch(ClassNotFoundException cnfe) {
			System.out.println("looks like driver not found");
		}catch(SQLException se) {
			System.out.println("either url,username or password are wrong");
		}catch(CustomerNotFoundException cfe) {
			System.out.println(cfe.getMessage());
		}		
		return result;
	}

	@Override
	public Customers viewcustomer(int customerid) {
		Customers customer=null;
		try {
		customer=icustomerDAO.viewcustomer(customerid);
		}catch(ClassNotFoundException cnfe) {
			System.out.println("looks like driver not found");
		}catch(SQLException se) {
			System.out.println("either url,username or password are wrong");
		}catch(CustomerNotFoundException cfe) {
			System.out.println(cfe.getMessage());
		}		
		return customer;
	}

	@Override
	public List<Customers> viewcustomers() {
		List<Customers>customerlist=null;
		try {
		customerlist=icustomerDAO.viewcustomers();
		}catch(ClassNotFoundException cnfe) {
			System.out.println("looks like driver not found");
		}catch(SQLException se) {
			se.printStackTrace();
			System.out.println("either url,username or password are wrong");
		}catch(CustomerNotFoundException cfe) {
			System.out.println(cfe.getMessage());
		}
		return customerlist;
	}

}
