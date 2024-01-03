package com.car_rental.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.car_rental.entity.Customers;
import com.car_rental.exception.CustomerNotFoundException;
import com.car_rental.util.DBUtil;
public class CustomerDAOImpl implements ICustomerDAO {

	private static Connection connCustomer;

	@Override
	public int Addcustomer(Customers customer) throws ClassNotFoundException,SQLException{
		connCustomer = DBUtil.createConnection();
		String query="INSERT INTO customer(firstname,lastname,email,phonenumber) "
				+ "VALUES(?,?,?,?)";
		
		PreparedStatement preparestcustomer=connCustomer.prepareStatement(query);
		preparestcustomer.setString(1, customer.getFname());
		preparestcustomer.setString(2, customer.getLname());
		preparestcustomer.setString(3, customer.getEmail());
		preparestcustomer.setString(4, customer.getPhone());
		int result=preparestcustomer.executeUpdate();
		DBUtil.closeConnection();
		return result;
	}

	@Override
	public int updatecustomer(Customers customer) throws CustomerNotFoundException,ClassNotFoundException,SQLException {
		connCustomer = DBUtil.createConnection();
		String query = "UPDATE customer SET firstname=?, lastname=?, email=?, phonenumber=? "
				+ "WHERE customerid=?";

		PreparedStatement prepareStCustomer = connCustomer.prepareStatement(query);
		prepareStCustomer.setString(1, customer.getFname());
		prepareStCustomer.setString(2, customer.getLname());
		prepareStCustomer.setString(3, customer.getEmail());
		prepareStCustomer.setString(4, customer.getPhone());
		prepareStCustomer.setInt(5, customer.getCustomerid());
		
		int result = prepareStCustomer.executeUpdate();

		DBUtil.closeConnection();
		return result;
	}

	@Override
	public int deletecustomer(int customerid) throws ClassNotFoundException,SQLException,CustomerNotFoundException {
				Customers customer = null;
		 
				String firstName = null;
				String lastName = null;
				String email = null;
				String phoneNumber = null;
		 
				int result = 0;
				
				connCustomer = DBUtil.createConnection();
		 
				String queryCheck = "SELECT * FROM customer WHERE customerid = ?";
				String queryDelete = "DELETE FROM customer WHERE customerid = ?";
				
				PreparedStatement prepareStCustomer = connCustomer.prepareStatement(queryCheck);
				PreparedStatement prepareCustomerDelete = connCustomer.prepareStatement(queryDelete);
				
				prepareStCustomer.setInt(1, customerid);
				prepareCustomerDelete.setInt(1, customerid);
				
				ResultSet rsCustomer = prepareStCustomer.executeQuery();
		 
				while (rsCustomer.next()) {// Till there are further records.
					customerid = rsCustomer.getInt("customerid");
					firstName = rsCustomer.getString("firstname");
					lastName = rsCustomer.getString("lastname");
					email = rsCustomer.getString("email");
					phoneNumber = rsCustomer.getString("phoneNumber");
		 
					customer = new Customers(firstName, lastName, email, phoneNumber);
					customer.setCustomerid(customerid);
				}
		 
				if (customer == null) {
					throw new CustomerNotFoundException("No Customer Found");
				}else {
					result = prepareCustomerDelete.executeUpdate();
				}
		 
				DBUtil.closeConnection();
				
				return result;
	}

	@Override
	public Customers viewcustomer(int customerid) throws ClassNotFoundException,SQLException,CustomerNotFoundException {
		Customers customer=null;
		int customerId=0;
		String firstname=null;
		String lastname=null;
		String email=null;
		String phonenumber=null;
		
		connCustomer = DBUtil.createConnection();
		String query="SELECT * FROM customer WHERE customerid = ?";
		
		PreparedStatement preparestcustomer=connCustomer.prepareStatement(query);
		preparestcustomer.setInt(1, customerid);
		ResultSet rscustomer=preparestcustomer.executeQuery();
		
		while(rscustomer.next()) {
			customerId=rscustomer.getInt("customerid");
			firstname=rscustomer.getString("firstname");
			lastname=rscustomer.getString("lastname");
			email=rscustomer.getString("email");
			phonenumber=rscustomer.getString("phonenumber");
			
			customer = new Customers(firstname,lastname,email,phonenumber);
			customer.setCustomerid(customerId);			
		}
		DBUtil.closeConnection();
		if(customer==null) {
			throw new CustomerNotFoundException("customer not found");
		}
		return customer;
	}

	@Override
	public List<Customers> viewcustomers() throws ClassNotFoundException,SQLException,CustomerNotFoundException {
		List<Customers>custlist=new ArrayList<>();
		Customers customer=null;
		
		int customerid=0;
		String firstname=null;
		String lastname=null;
		String email=null;
		String phonenumber=null;
		
		connCustomer = DBUtil.createConnection();
		String query="SELECT * FROM customer";
		
		PreparedStatement preparestcustomer=connCustomer.prepareStatement(query);
		ResultSet rscustomer=preparestcustomer.executeQuery();
		
		while(rscustomer.next()) {
			customerid=rscustomer.getInt("customerid");
			firstname=rscustomer.getString("firstname");
			lastname=rscustomer.getString("lastname");
			email=rscustomer.getString("email");
			phonenumber=rscustomer.getString("phonenumber");
			
			customer = new Customers(firstname,lastname,email,phonenumber);
			customer.setCustomerid(customerid);
			custlist.add(customer);
			
		}
		DBUtil.closeConnection();
		if(custlist.size()==0) {
			throw new CustomerNotFoundException("customer not found");
		}
		return custlist;
		
	}

}
