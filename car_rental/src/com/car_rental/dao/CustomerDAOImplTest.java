package com.car_rental.dao;

import static org.junit.Assert.assertTrue;

import java.sql.SQLException;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.car_rental.entity.Customers;
import com.car_rental.exception.CustomerNotFoundException;

public class CustomerDAOImplTest {
	private ICustomerDAO customerdao;

	@Before
	public void setUp() throws Exception {
		customerdao=new CustomerDAOImpl();
	}

	@After
	public void tearDown() throws Exception {
		customerdao=null;
	}

	@Test
	public final void testAddcustomer() {
		int result=0;
		Customers customer=null;
		customer=new Customers("vishnu","ram","visra@gmail.com","999989800");
		try {
			result=customerdao.Addcustomer(customer);
		}catch(ClassNotFoundException cnfe) {
			System.out.println("looks like driver not found");
		}catch(SQLException se) {
			System.out.println("either url,username or password are wrong");
		}
		 assertTrue(result==1);	
		}

	@Test
	public final void testUpdatecustomer() {
		int result = 0;
		Customers customer = new Customers("vishnu", "pras", "vis@email.com", "9999999997");
		customer.setCustomerid(3);
		try {
			result = customerdao.updatecustomer(customer);
		} catch (ClassNotFoundException cnfe) {
			System.out.println("Looks like JDBC driver is NOT loaded.");
		} catch (SQLException se) {
			System.out.println("Either url, username or password is wrong or duplicate record");
			se.printStackTrace();
		} catch (CustomerNotFoundException cnfe) {
			System.out.println("No such customer");
		}
		assertTrue(result == 1);
	}

	@Test
	public final void testDeletecustomer() {
		int result = 0;
		int customerId = 4;
		try {
			result = customerdao.deletecustomer(customerId);
		} catch (ClassNotFoundException cnfe) {
			System.out.println("Looks like JDBC driver is NOT loaded.");
		} catch (SQLException se) {
			System.out.println("Either url, username or password is wrong or duplicate record");
		} catch (CustomerNotFoundException cnfe) {
			System.out.println(cnfe.getMessage());
		}

		assertTrue(result == 1);
	}

	@Test
	public final void testViewcustomer() {
		Customers customer = null;
		int customerId = 3;
		try {
			customer = customerdao.viewcustomer(customerId);
		} catch (ClassNotFoundException cnfe) {
			System.out.println("Looks like JDBC driver is NOT loaded.");
		} catch (SQLException se) {
			System.out.println("Either url, username or password is wrong or duplicate record");
		} catch (CustomerNotFoundException cnfe) {
			System.out.println(cnfe.getMessage());
		}

		assertTrue(customer != null);
	}

	@Test
	public final void testViewcustomers() {
		List<Customers> customerList = null;
		try {
			customerList = customerdao.viewcustomers();
		} catch (ClassNotFoundException cnfe) {
			System.out.println("Looks like JDBC driver is NOT loaded.");
		} catch (SQLException se) {
			System.out.println("Either url, username or password is wrong or duplicate record");
		} catch (CustomerNotFoundException cnfe) {
			System.out.println(cnfe.getMessage());
		}
		assertTrue(customerList != null);	}

}
