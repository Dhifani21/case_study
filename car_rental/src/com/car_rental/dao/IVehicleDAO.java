package com.car_rental.dao;

import java.sql.SQLException;
import java.util.List;

import com.car_rental.entity.Vehicle;
import com.car_rental.exception.VehicleNotFoundException;

public interface IVehicleDAO {
	
	public int addVehicle(Vehicle vehicle)throws ClassNotFoundException,SQLException;
	public int updateVehicle(Vehicle vehicle)throws ClassNotFoundException,SQLException;
	public int deleteVehicle(int vehicleid)throws ClassNotFoundException,SQLException;
	public Vehicle viewVehicle(int vehicleid)throws ClassNotFoundException,SQLException,VehicleNotFoundException;
	public List<Vehicle>viewVehicles()throws ClassNotFoundException,SQLException;

}
