package com.car_rental.service;

import java.sql.SQLException;
import java.util.List;

import com.car_rental.dao.IVehicleDAO;
import com.car_rental.dao.VehicleDAOImpl;
import com.car_rental.entity.Vehicle;
import com.car_rental.exception.VehicleNotFoundException;

public class VehicleServiceImpl implements IVehicleService {
	private IVehicleDAO vehicledao;

	public VehicleServiceImpl() {
		this.vehicledao = new VehicleDAOImpl();
	}

	@Override
	public int addVehicle(Vehicle vehicle) {
		int result = 0;
		try {
			result = vehicledao.addVehicle(vehicle);
		} catch (ClassNotFoundException cnfe) {
			System.out.println("Looks like JDBC driver is NOT loaded.");
		} catch (SQLException se) {
			System.out.println("Either url, username or password is wrong or duplicate record");
		}
		return result;
	}

	@Override
	public int updateVehicle(Vehicle vehicle) {
		int result = 0;
		try {
			result = vehicledao.updateVehicle(vehicle);
		} catch (ClassNotFoundException cnfe) {
			System.out.println("Looks like JDBC driver is NOT loaded.");
		}catch(SQLException se) {
			System.out.println("Either url, username or password is wrong or duplicate record");
			se.printStackTrace();
		}
		return result;
	}

	@Override
	public int deleteVehicle(int vehicleid) {
		int result = 0;
		try {
			result = vehicledao.deleteVehicle(vehicleid);
			}catch (ClassNotFoundException cnfe) {
				System.out.println("Looks like JDBC driver is NOT loaded.");
			}catch(SQLException se) {
				System.out.println("Either url, username or password is wrong or duplicate record");
				se.printStackTrace();
			}
		return result;
	}

	@Override
	public Vehicle viewVehicle(int vehicleid) {
		Vehicle vehicle = null;

		try {
			vehicle = vehicledao.viewVehicle(vehicleid);
		} catch (ClassNotFoundException cnfe) {
			System.out.println("Looks like JDBC driver is NOT loaded.");
		} catch (SQLException se) {
			System.out.println("Either url, username or password is wrong or duplicate record");
		} catch(VehicleNotFoundException nfe) {
			System.out.println(nfe.getMessage());
		}
		return vehicle;

	}

	@Override
	public List<Vehicle> viewVehicles() {
		List<Vehicle> vehicleList = null;

		try {
			vehicleList = vehicledao.viewVehicles();
		} catch (ClassNotFoundException cnfe) {
			System.out.println("Looks like JDBC driver is NOT loaded.");
		} catch (SQLException se) {
			System.out.println("Either url, username or password is wrong or duplicate record");
		}
		return vehicleList;
	}

}
