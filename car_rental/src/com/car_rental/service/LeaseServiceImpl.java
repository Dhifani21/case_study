package com.car_rental.service;

import java.sql.SQLException;
import java.util.List;

import com.car_rental.dao.ILeaseDAO;
import com.car_rental.dao.LeaseDAOImpl;
import com.car_rental.entity.Lease;
import com.car_rental.exception.LeaseNotFoundException;

public class LeaseServiceImpl implements ILeaseService {
	private ILeaseDAO leaseDAO;

	public LeaseServiceImpl() {
		this.leaseDAO = new LeaseDAOImpl();
	}

	@Override
	public int addLease(Lease lease) {
		int result = 0;
		try {
			result = leaseDAO.addLease(lease);
		} catch (ClassNotFoundException cnfe) {
			System.out.println("Looks like JDBC driver is NOT loaded.");
		} catch (SQLException se) {
			se.printStackTrace();
			System.out.println("Either url, username or password is wrong or duplicate record");
		}
		return result;
	}

	@Override
	public int updateLease(Lease lease) {
		int result = 0;
		try {
			result = leaseDAO.updateLease(lease);
		} catch (ClassNotFoundException cnfe) {
			System.out.println("Looks like JDBC driver is NOT loaded.");
		} catch (SQLException se) {
			System.out.println("Either url, username or password is wrong or duplicate record");
			se.printStackTrace();
		} catch (LeaseNotFoundException cnfe) {
			System.out.println(cnfe.getMessage());
		}
		return result;
	}

	@Override
	public int deleteLease(int leaseID) {
		int result = 0;
		try {
			result = leaseDAO.deleteLease(leaseID);
		} catch (ClassNotFoundException cnfe) {
			System.out.println("Looks like JDBC driver is NOT loaded.");
		} catch (SQLException se) {
			System.out.println("Either url, username or password is wrong or duplicate record");
		} catch (LeaseNotFoundException cnfe) {
			System.out.println(cnfe.getMessage());
		}

		return result;
	}

	@Override
	public Lease viewLease(int leaseID) {
		Lease lease = null;

		try {
			lease = leaseDAO.viewLease(leaseID);
		} catch (ClassNotFoundException cnfe) {
			System.out.println("Looks like JDBC driver is NOT loaded.");
		} catch (SQLException se) {
			System.out.println("Either url, username or password is wrong or duplicate record");
			se.printStackTrace();
		} catch (LeaseNotFoundException cnfe) {
			System.out.println(cnfe.getMessage());
		}

		return lease;
	}

	@Override
	public List<Lease> viewLeases() {
		List<Lease> leaseList = null;

		try {
			leaseList = leaseDAO.viewLeases();
		} catch (ClassNotFoundException cnfe) {
			System.out.println("Looks like JDBC driver is NOT loaded.");
		} catch (SQLException se) {
			System.out.println("Either url, username or password is wrong or duplicate record");
		} catch (LeaseNotFoundException cnfe) {
			System.out.println(cnfe.getMessage());
		}
		return leaseList;
	}

}
