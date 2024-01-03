package com.car_rental.dao;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.sql.Date;
import java.sql.SQLException;
import java.util.Scanner;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.car_rental.entity.Customers;
import com.car_rental.entity.Lease;
import com.car_rental.entity.Vehicle;
import com.car_rental.exception.CustomerNotFoundException;
import com.car_rental.exception.LeaseNotFoundException;
import com.car_rental.exception.VehicleNotFoundException;

public class CarrentJunit {
	private IVehicleDAO vehicledao;
	private ILeaseDAO leasedao;
	private ICustomerDAO customerdao;

	@Before
	public void setUp() throws Exception {
		vehicledao=new VehicleDAOImpl();
		leasedao=new LeaseDAOImpl();
		customerdao=new CustomerDAOImpl();
	}

	@After
	public void tearDown() throws Exception {
		vehicledao=null;
		leasedao=null;
		customerdao=null;
	}

	@Test
	public final void testVehicleCreation() {
		int result = 0;
		Vehicle vehicle=null;
		vehicle=new Vehicle("Audi","A6",2018,5000,"notavailable",6,800);
		try {
			result = vehicledao.addVehicle(vehicle);
		} catch (ClassNotFoundException cnfe) {
			System.out.println("Looks like JDBC driver is NOT loaded.");
		} catch (SQLException se) {
			System.out.println("Either url, username or password is wrong or duplicate record");
			se.printStackTrace();
		}
		assertTrue(result==1);
	}
	
	@Test
	public final void testLeaseCreation() {
		int result = 0;
		Lease lease=null;
		Date startdate=Date.valueOf("2023-12-31");
		Date enddate=Date.valueOf("2024-02-01");
		Customers customer=new Customers();
		customer.setCustomerid(3);
		Vehicle vehicle=new Vehicle();
		vehicle.setVehicleid(2);
		lease=new Lease(startdate.toLocalDate(),enddate.toLocalDate(),customer,vehicle,"monthly");
		try {
			result = leasedao.addLease(lease);
		} catch (ClassNotFoundException cnfe) {
			System.out.println("Looks like JDBC driver is NOT loaded.");
		} catch (SQLException se) {
			se.printStackTrace();
			System.out.println("Either url, username or password is wrong or duplicate record");
		}
		assertTrue(result==1);
	}
	
	@Test
	public final void testLeaseRetrieve() {
		int leaseid;
		Scanner sc=new Scanner(System.in);
		System.out.println("enter leaseid:");
		leaseid=sc.nextInt();
		Lease lease=null;

		try {
			lease = leasedao.viewLease(leaseid);
		} catch (ClassNotFoundException cnfe) {
			System.out.println("Looks like JDBC driver is NOT loaded.");
		} catch (SQLException se) {
			System.out.println("Either url, username or password is wrong or duplicate record");
			se.printStackTrace();
		} catch (LeaseNotFoundException cnfe) {
			System.out.println(cnfe.getMessage());
		}
		sc.close();
		assertTrue(lease!=null);
	}
	
	@Test
	public final void testLeaseException() {
		Lease lease=null;
		int leaseid=0;
		
		try {
			lease=leasedao.viewLease(leaseid);
		} catch (ClassNotFoundException cnfe) {
			System.out.println("Looks like JDBC driver is NOT loaded.");
		} catch (SQLException se) {
			System.out.println("Either url, username or password is wrong or duplicate record");
		} catch (LeaseNotFoundException lnfe) {
			System.out.println(lnfe.getMessage());
		} 
		assertTrue(lease==null);
	}
	
	@Test
	public final void testCustException() {
		Customers customer=null;
		int customerid=6;
		
		try {
			customer=customerdao.viewcustomer(customerid);
		} catch (ClassNotFoundException cnfe) {
			System.out.println("Looks like JDBC driver is NOT loaded.");
		} catch (SQLException se) {
			System.out.println("Either url, username or password is wrong or duplicate record");
		} catch(CustomerNotFoundException cfe) {
			System.out.println(cfe.getMessage());
		}

		assertTrue(customer==null);
	}
	
	@Test
	public final void testCarException(){
		Vehicle vehicle=null;
		int vehicleid=5;
		
		try {
			vehicle=vehicledao.viewVehicle(vehicleid);
		} catch (ClassNotFoundException cnfe) {
			System.out.println("Looks like JDBC driver is NOT loaded.");
		} catch (SQLException se) {
			System.out.println("Either url, username or password is wrong or duplicate record");
		} catch(VehicleNotFoundException vnfe) {
			System.out.println(vnfe.getMessage());
		}

		assertTrue(vehicle==null);
	}


}
