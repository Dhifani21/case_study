package com.car_rental.service;

import java.util.List;

import com.car_rental.entity.Vehicle;

public interface IVehicleService {
	public int addVehicle(Vehicle vehicle);
	public int updateVehicle(Vehicle vehicle);
	public int deleteVehicle(int vehicleid);
	public Vehicle viewVehicle(int vehicleid);
	public List<Vehicle>viewVehicles();

}
