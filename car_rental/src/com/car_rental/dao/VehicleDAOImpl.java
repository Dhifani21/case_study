package com.car_rental.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.car_rental.entity.Vehicle;
import com.car_rental.exception.VehicleNotFoundException;
import com.car_rental.util.DBUtil;
public class VehicleDAOImpl implements IVehicleDAO {
	private static Connection connVehicle;

	@Override
	public int addVehicle(Vehicle vehicle) throws ClassNotFoundException,SQLException{
		connVehicle = DBUtil.createConnection();
		String query = "INSERT INTO vehicle (make,model,year, dailyRate,status,passengerCapacity,engineCapacity) "
				 + "VALUES(?,?,?,?,?,?,?)";
		
		PreparedStatement prepareSt = connVehicle.prepareStatement(query);
		
		prepareSt.setString(1, vehicle.getMake());
		prepareSt.setString(2, vehicle.getModel());
		prepareSt.setInt(3, vehicle.getYear());
		prepareSt.setDouble(4, vehicle.getDailyrate());
		prepareSt.setString(5, vehicle.getStatus());
		prepareSt.setInt(6, vehicle.getP_capacity());
		prepareSt.setInt(7, vehicle.getE_capacity());

		int result = prepareSt.executeUpdate();

		DBUtil.closeConnection();
		return result;

	}

	@Override
	public int updateVehicle(Vehicle vehicle) throws ClassNotFoundException,SQLException{
		connVehicle = DBUtil.createConnection();
		String query = "UPDATE vehicle SET make=?, model=?, year=?, dailyRate=?, status=?,passengerCapacity=?,engineCapacity=?  "
				+ "WHERE vehicleID=?";

		PreparedStatement prepareSt = connVehicle.prepareStatement(query);
		prepareSt.setString(1, vehicle.getMake());
		prepareSt.setString(2, vehicle.getModel());
		prepareSt.setInt(3, vehicle.getYear());
		prepareSt.setDouble(4, vehicle.getDailyrate());
		prepareSt.setString(5, vehicle.getStatus());
		prepareSt.setInt(6, vehicle.getP_capacity());
		prepareSt.setInt(7, vehicle.getE_capacity());
		prepareSt.setInt(8, vehicle.getVehicleid());
		
		int result = prepareSt.executeUpdate();

		DBUtil.closeConnection();
		return result;
	}

	@Override
	public int deleteVehicle(int vehicleid) throws ClassNotFoundException,SQLException{
		Vehicle vehicle = null;
		
		connVehicle = DBUtil.createConnection();

		String queryCheck = "SELECT * FROM vehicle WHERE vehicleID = ?";
		String queryDelete = "DELETE FROM vehicle WHERE vehicleID = ?";
		 String make=null;
		 String model=null;
		 int year=0;
		 double dailyrate=0;
		 String status=null;
		 int p_capacity=0;
		 int e_capacity=0;
		int success = 0;
		
		PreparedStatement prepareStvehicle = connVehicle.prepareStatement(queryCheck);
		PreparedStatement prepareStDelete = connVehicle.prepareStatement(queryDelete);
		
		prepareStvehicle.setInt(1, vehicleid);
		prepareStDelete.setInt(1, vehicleid);
		
		
		ResultSet rs = prepareStvehicle.executeQuery();

		while (rs.next()) {// Till there are further records.
			vehicleid = rs.getInt("vehicleID");
			make = rs.getString("make");
			model = rs.getString("model");
			dailyrate = rs.getDouble("dailyRate");
			status = rs.getString("status");
			year = rs.getInt("year");
			p_capacity = rs.getInt("passengerCapacity");
			e_capacity = rs.getInt("engineCapacity");

			vehicle = new Vehicle(make, model, year, dailyrate,status,p_capacity,e_capacity);
		}
		if (vehicle == null) {
			return 0;
		}else {
			success = prepareStDelete.executeUpdate();
		}		DBUtil.closeConnection();
		return success;
	}

	@Override
	public Vehicle viewVehicle(int vehicleid) throws ClassNotFoundException,SQLException,VehicleNotFoundException{
		Vehicle vehicle = null;
		
		String make=null;
		 String model=null;
		 int year=0;
		 double dailyrate=0;
		 String status=null;
		 int p_capacity=0;
		 int e_capacity=0;
		
		connVehicle = DBUtil.createConnection();

		String queryCheck = "SELECT vehicleID, make, model, year, dailyRate, status, passengerCapacity, engineCapacity "
				+ "FROM vehicle WHERE "
				+ "vehicleID = ?";
						
		PreparedStatement prepareSt = connVehicle.prepareStatement(queryCheck);
		prepareSt.setInt(1, vehicleid);
		
		ResultSet rs = prepareSt.executeQuery();

		while (rs.next()) {// Till there are further records.
			make = rs.getString("make");
			model = rs.getString("model");
			year = rs.getInt("year");
			dailyrate = rs.getDouble("dailyRate");
			status = rs.getString("status");
			p_capacity = rs.getInt("passengerCapacity");
			e_capacity = rs.getInt("engineCapacity");
			vehicleid = rs.getInt("vehicleID");
		
			vehicle = new Vehicle(vehicleid,make, model, year, dailyrate,status,p_capacity,e_capacity);
		}
		DBUtil.closeConnection();

		if (vehicle == null) {
			throw new VehicleNotFoundException("No vehicle Found");
			}

		return vehicle;

	}

	@Override
	public List<Vehicle> viewVehicles() throws ClassNotFoundException,SQLException{
		List<Vehicle> vehicles = new ArrayList<>();
		Vehicle vehicle = null;

		connVehicle = DBUtil.createConnection();

		String query = "SELECT * FROM vehicle";
		int vehicleid=0;
		 String make=null;
		 String model=null;
		 double dailyrate=0;
		 String status=null;
		 int p_capacity=0;
		 int e_capacity=0;
		int year = 0 ;
		
		PreparedStatement prepareSt = connVehicle.prepareStatement(query);

		ResultSet rs = prepareSt.executeQuery();

		while (rs.next()) {// Till there are further records.
			make = rs.getString("make");
			model = rs.getString("model");
			year = rs.getInt("year");
			dailyrate = rs.getDouble("dailyRate");
			status = rs.getString("status");
			p_capacity = rs.getInt("passengerCapacity");
			e_capacity = rs.getInt("engineCapacity");
			vehicleid = rs.getInt("vehicleID");
			
			vehicle = new Vehicle(vehicleid,make, model, year, dailyrate,status,p_capacity,e_capacity);
			vehicles.add(vehicle);
		}
		DBUtil.closeConnection();

		if (vehicles.size() == 0) {
			return null;
		}

		return vehicles;

	}

}
