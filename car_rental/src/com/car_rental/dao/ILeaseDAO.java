package com.car_rental.dao;

import java.sql.SQLException;
import java.util.List;

import com.car_rental.entity.Lease;
import com.car_rental.exception.LeaseNotFoundException;

public interface ILeaseDAO {
	
		public int addLease(Lease lease) throws ClassNotFoundException, SQLException;
		public int updateLease(Lease lease) throws ClassNotFoundException, SQLException, LeaseNotFoundException;
		public int deleteLease(int leaseID) throws ClassNotFoundException, SQLException, LeaseNotFoundException;
		public Lease viewLease(int leaseID) throws ClassNotFoundException, SQLException, LeaseNotFoundException;
		public List<Lease>viewLeases() throws ClassNotFoundException, SQLException, LeaseNotFoundException;
}
