package com.car_rental.service;

import java.util.List;

import com.car_rental.entity.Lease;

public interface ILeaseService {
	public int addLease(Lease lease);
	public int updateLease(Lease lease);
	public int deleteLease(int leaseID);
	public Lease viewLease(int leaseID);
	public List<Lease>viewLeases();
}
