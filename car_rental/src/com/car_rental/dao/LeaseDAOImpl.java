package com.car_rental.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.car_rental.entity.Customers;
import com.car_rental.entity.Lease;
import com.car_rental.entity.Vehicle;
import com.car_rental.exception.LeaseNotFoundException;
import com.car_rental.service.CustomerserviceImpl;
import com.car_rental.service.ICustomerService;
import com.car_rental.service.IVehicleService;
import com.car_rental.service.VehicleServiceImpl;
import com.car_rental.util.DBUtil;

public class LeaseDAOImpl implements ILeaseDAO {
	private static Connection connLease;

	@Override
	public int addLease(Lease lease) throws ClassNotFoundException, SQLException {

		connLease = DBUtil.createConnection();
		String query = "INSERT INTO lease(vehicleid, customerid, Startdate, Enddate,Type) " + "VALUES(?,?,?,?,?)";

		Date startDate = Date.valueOf(lease.getStartDate());
		Date endDate = Date.valueOf(lease.getEndDate());

		PreparedStatement prepareSt = connLease.prepareStatement(query);
		prepareSt.setDate(3, startDate);
		prepareSt.setDate(4, endDate);
		prepareSt.setInt(2, lease.getCustomer().getCustomerid());
		prepareSt.setInt(1, lease.getVehicle().getVehicleid());
		prepareSt.setString(5, lease.getType());

		int result = prepareSt.executeUpdate();

		DBUtil.closeConnection();
		return result;

	}

	@Override
	public int updateLease(Lease lease) throws ClassNotFoundException, SQLException, LeaseNotFoundException {
		connLease = DBUtil.createConnection();
		String query = "UPDATE lease SET Startdate=?, Enddate=?, customerid=?,vehicleid=?, type=? " + "WHERE leaseID=?";

		Date startDate = Date.valueOf(lease.getStartDate());
		Date endDate = Date.valueOf(lease.getEndDate());

		PreparedStatement prepareSt = connLease.prepareStatement(query);
		prepareSt.setDate(1, startDate);
		prepareSt.setDate(2, endDate);
		prepareSt.setInt(3, lease.getCustomer().getCustomerid());
		prepareSt.setInt(4, lease.getVehicle().getVehicleid());
		prepareSt.setString(5, lease.getType());
		prepareSt.setInt(6, lease.getLeaseId());

		int result = prepareSt.executeUpdate();

		DBUtil.closeConnection();
		return result;
	}

	@Override
	public int deleteLease(int leaseID) throws ClassNotFoundException, SQLException, LeaseNotFoundException {
		Lease lease = null;

		LocalDate startDate = null;
		LocalDate endDate = null;

		connLease = DBUtil.createConnection();

		String queryCheck = "SELECT * FROM lease WHERE lease_id = ?";
		String queryDelete = "DELETE FROM lease WHERE lease_id = ?";

		String type = null;

		int success = 0;

		PreparedStatement prepareStLease = connLease.prepareStatement(queryCheck);
		PreparedStatement prepareStDelete = connLease.prepareStatement(queryDelete);

		prepareStLease.setInt(1, leaseID);
		prepareStDelete.setInt(1, leaseID);

		ResultSet rsLease = prepareStLease.executeQuery();

		while (rsLease.next()) {// Till there are further records.
			leaseID = rsLease.getInt("lease_id");
			startDate = rsLease.getDate("start_date").toLocalDate();
			endDate = rsLease.getDate("end_date").toLocalDate();
			type = rsLease.getString("type");

			lease = new Lease(startDate, endDate, type);
		}

		if (lease == null) {
			throw new LeaseNotFoundException("No Lease Found");
		} else {
			success = prepareStDelete.executeUpdate();
		}
		DBUtil.closeConnection();
		return success;

	}

	@Override
	public Lease viewLease(int leaseID) throws ClassNotFoundException, SQLException, LeaseNotFoundException {
		Lease lease = null;

		int customerId = 0;
		int vehicleid = 0;
		LocalDate startDate = null;
		LocalDate endDate = null;

		Customers customer = null;
		Vehicle vehicle = null;

		connLease = DBUtil.createConnection();
		
		  String queryCheck =
		  "SELECT l.LeaseID, l.Startdate, l.Enddate, l.Type, l.customerid,l.vehicleid " +
		  "FROM lease l "+
		  "WHERE l.LeaseID = ?";
		 
		
		String type = null;

		PreparedStatement prepareSt = connLease.prepareStatement(queryCheck);
		prepareSt.setInt(1, leaseID);

		ResultSet rsLease = prepareSt.executeQuery();

		while (rsLease.next()) {// Till there are further records.
			customerId = rsLease.getInt("customerid");
			vehicleid = rsLease.getInt("vehicleid");
			leaseID = rsLease.getInt("LeaseID");
			startDate = rsLease.getDate("Startdate").toLocalDate();
			endDate = rsLease.getDate("Enddate").toLocalDate();
			type = rsLease.getString("Type");

			customer = new Customers();
			customer.setCustomerid(customerId);

			vehicle = new Vehicle();
			vehicle.setVehicleid(vehicleid);

			lease = new Lease(leaseID, startDate, endDate, customer, vehicle, type);
		}
		DBUtil.closeConnection();

		if (lease == null) {
			throw new LeaseNotFoundException("No Lease Found");
		}

		return lease;

	}

	@Override
	public List<Lease> viewLeases() throws ClassNotFoundException, SQLException, LeaseNotFoundException {
		ICustomerService customerService = new CustomerserviceImpl();
		IVehicleService vehicleservice = new VehicleServiceImpl();
		List<Lease> leases = new ArrayList<>();
		Lease lease = null;

		int customerId = 0;
		int vehicleid = 0;
		LocalDate startDate = null;
		LocalDate endDate = null;

		Customers customer = null;
		Vehicle vehicle = null;

		connLease = DBUtil.createConnection();

		String query = "SELECT * FROM lease";

		String type = null;
		int leaseId = 0;

		PreparedStatement prepareSt = connLease.prepareStatement(query);

		ResultSet rsLease = prepareSt.executeQuery();

		while (rsLease.next()) {// Till there are further records.
			customerId = rsLease.getInt("customerid");
			vehicleid = rsLease.getInt("vehicleid");
			leaseId = rsLease.getInt("LeaseID");
			startDate = rsLease.getDate("Startdate").toLocalDate();
			endDate = rsLease.getDate("Enddate").toLocalDate();
			type = rsLease.getString("Type");
			customer = customerService.viewcustomer(customerId);
			vehicle = vehicleservice.viewVehicle(vehicleid);

			lease = new Lease(leaseId, startDate, endDate, customer, vehicle, type);
			leases.add(lease);
		}
		DBUtil.closeConnection();

		if (leases.size() == 0) {
			throw new LeaseNotFoundException("No Lease Found");
		}

		return leases;

	}

}
