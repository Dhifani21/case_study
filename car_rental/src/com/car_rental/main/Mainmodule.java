package com.car_rental.main;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

import com.car_rental.entity.Customers;
import com.car_rental.entity.Lease;
import com.car_rental.entity.Payment;
import com.car_rental.entity.Vehicle;
import com.car_rental.service.CustomerserviceImpl;
import com.car_rental.service.ICustomerService;
import com.car_rental.service.ILeaseService;
import com.car_rental.service.IPaymentService;
import com.car_rental.service.IVehicleService;
import com.car_rental.service.LeaseServiceImpl;
import com.car_rental.service.PaymentServiceImpl;
import com.car_rental.service.VehicleServiceImpl;

public class Mainmodule {

	public static void main(String[] args) throws Exception {
		ICustomerService customerservice = new CustomerserviceImpl();
		ILeaseService leaseService = new LeaseServiceImpl();
		IVehicleService vehicleservice = new VehicleServiceImpl();
		IPaymentService paymentservice = new PaymentServiceImpl();
		Customers customer = null;
		Lease lease = null;
		Vehicle vehicle = null;
		Payment payment = null;
		String firstname = null;
		String lastname = null;
		String email = null;
		String phone = null;
		String typeOfLease = null;
		int vehicleid = 0;
		String make = null;
		String model = null;
		double dailyrate = 0;
		String status = null;
		int p_capacity = 0;
		int e_capacity = 0;
		int paymentid = 0;
		LocalDate paymentdate = null;
		int amount = 0;

		int year = 0;
		int month = 0;
		int dayOfMonth = 0;

		LocalDate startDate = null;
		LocalDate endDate = null;
		int success = 0;
		int customerid = 0;
		int leaseid = 0;
		int choice = -1;
		int innerChoice = -1;
		Scanner scInput = new Scanner(System.in);

		while (choice != 0) {

			System.out.println("Following are the options:");
			System.out.println("1. Vehicle");
			System.out.println("2. customer");
			System.out.println("3. lease");
			System.out.println("4. payment");
			System.out.println("0. Exit");
			System.out.print("Please enter your choice: ");

			choice = scInput.nextInt();

			switch (choice) {
			case 1:
				while (innerChoice != 0) {
					System.out.println("Following are the options:");
					System.out.println("1. Insert Vehicle");
					System.out.println("2. Update vehicle");
					System.out.println("3. Delete vehicle");
					System.out.println("4. View vehicle by ID");
					System.out.println("5. View all vehicles");
					System.out.println("0. Exit");
					System.out.print("Please enter your choice: ");

					innerChoice = scInput.nextInt();
					scInput.nextLine();

					switch (innerChoice) {
					case 1:
						System.out.println("Enter vehicleId:");
						vehicleid = scInput.nextInt();
						scInput.nextLine();
						
						System.out.print("Enter make:");
						make = scInput.nextLine();
						scInput.nextLine();

						System.out.print("Enter model:");
						model = scInput.nextLine();
						scInput.nextLine();

						System.out.print("Enter year:");
						year = scInput.nextInt();
						scInput.nextLine();

						System.out.print("enter dailyrate:");
						dailyrate = scInput.nextDouble();
						scInput.nextLine();

						System.out.print("Enter status:");
						status = scInput.nextLine();

						System.out.print("Enter passengercapacity:");
						p_capacity = scInput.nextInt();
						scInput.nextLine();

						System.out.print("Enter engine capacity:");
						e_capacity = scInput.nextInt();
						scInput.nextLine();

						vehicle = new Vehicle(vehicleid,make, model, year, dailyrate, status, p_capacity, e_capacity);
						success = vehicleservice.addVehicle(vehicle);

						if (success == 1) {
							System.out.println("record inserted successfully...");
						}
						break;
					case 2:
						System.out.print("Enter make:");
						make = scInput.nextLine();

						System.out.print("Enter model:");
						model = scInput.nextLine();

						System.out.print("Enter year:");
						year = scInput.nextInt();
						scInput.nextLine();

						System.out.print("enter dailyrate:");
						dailyrate = scInput.nextDouble();
						scInput.nextLine();

						System.out.print("Enter status:");
						status = scInput.nextLine();

						System.out.print("Enter passengercapacity:");
						p_capacity = scInput.nextInt();
						scInput.nextLine();

						System.out.print("Enter engine capacity:");
						e_capacity = scInput.nextInt();
						scInput.nextLine();

						System.out.print("Enter vehicleid:");
						vehicleid = scInput.nextInt();
						scInput.nextLine();

						vehicle = new Vehicle(vehicleid, make, model, year, dailyrate, status, p_capacity, e_capacity);
						success = vehicleservice.updateVehicle(vehicle);

						if (success == 1) {
							System.out.println("record inserted successfully...");
						}
						break;
					case 3:
						System.out.print("Enter vehicle ID: ");
						vehicleid = scInput.nextInt();
						scInput.nextLine();

						success = vehicleservice.deleteVehicle(vehicleid);

						if (success == 0) {
							System.out.println("No such vehicle");
						} else {
							System.out.println("Record is deleted successfully...");
						}
						break;
					case 4:
						System.out.print("Enter vehicle ID: ");
						vehicleid = scInput.nextInt();
						scInput.nextLine();

						vehicle = vehicleservice.viewVehicle(vehicleid);

						if (vehicle == null) {
							System.out.println("No such vehicle");
						} else {
							System.out.println("The given vehicle: ");
							System.out.println(vehicle);
						}
						break;
					case 5:
						List<Vehicle> vehicleList = vehicleservice.viewVehicles();

						if (vehicleList == null) {
							System.out.println("No vehicle in table.");
						} else {
							System.out.println("Following are the vehicles:");

							for (Vehicle vehicle2 : vehicleList) {
								System.out.println(vehicle2);
							}
						}

						break;
					case 0:
						System.out.println("You have chosen to exit.");
						break;
					default:
						System.out.println("Incorrect option");
						break;
					}
				}
			case 2:
				while (innerChoice != 0) {
					System.out.println("Following are the options:");
					System.out.println("1. Insert customer");
					System.out.println("2. Update customer");
					System.out.println("3. Delete customer");
					System.out.println("4. View customer by ID");
					System.out.println("5. View all customers");
					System.out.println("0. Exit");
					System.out.print("Please enter your choice: ");

					innerChoice = scInput.nextInt();
					scInput.nextLine();

					switch (innerChoice) {
					case 1:
						System.out.print("Enter firstname:");
						firstname = scInput.nextLine();

						System.out.print("Enter lastname:");
						lastname = scInput.nextLine();

						System.out.print("Enter email:");
						email = scInput.nextLine();

						System.out.print("enter phone:");
						phone = scInput.nextLine();

						customer = new Customers(firstname, lastname, email, phone);
						success = customerservice.Addcustomer(customer);

						if (success == 1) {
							System.out.println("record inserted successfully...");
						}
						break;
					case 2:
						System.out.print("Enter firstname:");
						firstname = scInput.nextLine();

						System.out.print("Enter lastname:");
						lastname = scInput.nextLine();

						System.out.print("Enter email:");
						email = scInput.nextLine();

						System.out.print("enter phone:");
						phone = scInput.nextLine();

						System.out.println("enter customerid:");
						customerid = scInput.nextInt();

						customer = new Customers(customerid, firstname, lastname, email, phone);
						success = customerservice.updatecustomer(customer);

						if (success == 1) {
							System.out.println("record inserted successfully...");
						}
						break;
					case 3:
						System.out.println("enter customerid:");
						customerid = scInput.nextInt();
						scInput.nextLine();
						success = customerservice.deletecustomer(customerid);
						if (success == 0) {
							System.out.println("no such customer");
						} else {
							System.out.println("record is deleted");
						}
						break;
					case 4:
						System.out.println("enter customerid:");
						customerid = scInput.nextInt();
						scInput.nextLine();
						customer = customerservice.viewcustomer(customerid);
						if (customer == null) {
							System.out.println("no such customer");
						} else {
							System.out.println("the given customer:");
							System.out.println(customer);
						}
						break;
					case 5:
						List<Customers> customerlist = customerservice.viewcustomers();
						System.out.println("following are the customers:");
						for (Customers customers : customerlist) {
							System.out.println(customers);
						}
						break;
					case 0:
						System.out.println("You have chosen to exit.");
						break;
					default:
						System.out.println("Incorrect option");
						break;
					}

				}
			case 3:
				while (innerChoice != 0) {
					System.out.println("Following are the options:");
					System.out.println("1. Insert lease");
					System.out.println("2. Update lease");
					System.out.println("3. Delete lease");
					System.out.println("4. View lease by ID");
					System.out.println("5. View all leases");
					System.out.println("0. Exit");
					System.out.print("Please enter your choice: ");

					innerChoice = scInput.nextInt();
					scInput.nextLine();

					switch (innerChoice) {
					case 1:
						System.out.println("Enter Start Date: ");
						System.out.print("Enter year: ");
						year = Integer.parseInt(scInput.nextLine());

						System.out.print("Enter month: ");
						month = Integer.parseInt(scInput.nextLine());

						System.out.print("Enter day of month: ");
						dayOfMonth = Integer.parseInt(scInput.nextLine());

						startDate = LocalDate.of(year, month, dayOfMonth);

						System.out.println("Enter End Date: ");
						System.out.print("Enter year: ");
						year = Integer.parseInt(scInput.nextLine());

						System.out.print("Enter month: ");
						month = Integer.parseInt(scInput.nextLine());

						System.out.print("Enter day of month: ");
						dayOfMonth = Integer.parseInt(scInput.nextLine());

						endDate = LocalDate.of(year, month, dayOfMonth);

						System.out.print("Type of Lease: ");
						typeOfLease = scInput.nextLine();

						System.out.print("Customer ID: ");
						customerid = Integer.parseInt(scInput.nextLine());

						System.out.print("vehicle ID: ");
						vehicleid = Integer.parseInt(scInput.nextLine());

						customer = new Customers();
						customer.setCustomerid(customerid);

						vehicle = new Vehicle();
						vehicle.setVehicleid(vehicleid);

						lease = new Lease(startDate, endDate, customer, vehicle, typeOfLease);

						success = leaseService.addLease(lease);

						if (success == 1) {
							System.out.println("Record inserted successfully...");
						}
						break;
					case 2:
						System.out.print("Enter Lease ID: ");
						leaseid = scInput.nextInt();
						scInput.nextLine();

						System.out.println("Enter Start Date: ");
						System.out.print("Enter year: ");
						year = Integer.parseInt(scInput.nextLine());

						System.out.print("Enter month: ");
						month = Integer.parseInt(scInput.nextLine());

						System.out.print("Enter day of month: ");
						dayOfMonth = Integer.parseInt(scInput.nextLine());

						startDate = LocalDate.of(year, month, dayOfMonth);

						System.out.println("Enter End Date: ");
						System.out.print("Enter year: ");
						year = Integer.parseInt(scInput.nextLine());

						System.out.print("Enter month: ");
						month = Integer.parseInt(scInput.nextLine());

						System.out.print("Enter day of month: ");
						dayOfMonth = Integer.parseInt(scInput.nextLine());

						endDate = LocalDate.of(year, month, dayOfMonth);

						System.out.print("Type of Lease: ");
						typeOfLease = scInput.nextLine();

						System.out.print("Customer ID: ");
						customerid = Integer.parseInt(scInput.nextLine());

						System.out.print("vehicle ID: ");
						vehicleid = Integer.parseInt(scInput.nextLine());

						customer = new Customers();
						customer.setCustomerid(customerid);

						vehicle = new Vehicle();
						vehicle.setVehicleid(vehicleid);

						lease = new Lease(leaseid, startDate, endDate, customer, vehicle, typeOfLease);

						success = leaseService.updateLease(lease);

						if (success == 1) {
							System.out.println("Record updated successfully...");
						} else {
							System.out.println("Record was NOT updated...");
						}
						break;
					case 3:
						System.out.print("Enter Lease ID: ");
						leaseid = scInput.nextInt();
						scInput.nextLine();

						success = leaseService.deleteLease(leaseid);

						if (success == 0) {
							System.out.println("No such lease");
						} else {
							System.out.println("Record is deleted successfully...");
						}
						break;
					case 4:
						System.out.print("Enter lease ID: ");
						leaseid = scInput.nextInt();
						scInput.nextLine();

						lease = leaseService.viewLease(leaseid);

						if (lease == null) {
							System.out.println("No such lease");
						} else {
							System.out.println("The given lease: ");
							System.out.println(lease);
						}
						break;
					case 5:
						List<Lease> leaseList = leaseService.viewLeases();
						if (leaseList == null) {
							System.out.println("No leases in table.");
						} else {
							System.out.println("Following are the leases:");

							for (Lease lease2 : leaseList) {
								System.out.println(lease2);
							}
						}
						break;
					case 0:
						System.out.println("You have chosen to exit.");
						break;
					default:
						System.out.println("Incorrect option");
						break;
					}
				}
			case 4:
				while (innerChoice != 0) {
					System.out.println("Following are the options:");
					System.out.println("1. Insert payment");
					System.out.println("2. Update payment");
					System.out.println("3. Delete payment");
					System.out.println("4. View payment by ID");
					System.out.println("5. View all payments");
					System.out.println("0. Exit");
					System.out.print("Please enter your choice: ");

					innerChoice = scInput.nextInt();
					scInput.nextLine();

					switch (innerChoice) {
					case 1:
						System.out.print("Enter leaseid:");
						leaseid = scInput.nextInt();
						scInput.nextLine();

						lease = new Lease();
						lease.setLeaseId(leaseid);

						System.out.println("Enter Payment Date: ");
						System.out.print("Enter year: ");
						year = Integer.parseInt(scInput.nextLine());

						System.out.print("Enter month: ");
						month = Integer.parseInt(scInput.nextLine());

						System.out.print("Enter day of month: ");
						dayOfMonth = Integer.parseInt(scInput.nextLine());

						paymentdate = LocalDate.of(year, month, dayOfMonth);

						System.out.print("enter amount:");
						amount = scInput.nextInt();

						payment = new Payment(lease, paymentdate, amount);
						success = paymentservice.addPayment(payment);

						if (success == 1) {
							System.out.println("record inserted successfully...");
						}
						break;

					case 2:
						System.out.println("enter leaseid:");
						leaseid = scInput.nextInt();
						scInput.nextLine();

						lease = new Lease();
						lease.setLeaseId(leaseid);

						System.out.println("enter payment date:");
						System.out.println("enter year");
						year = Integer.parseInt(scInput.nextLine());

						System.out.print("Enter month: ");
						month = Integer.parseInt(scInput.nextLine());

						System.out.print("Enter day of month: ");
						dayOfMonth = Integer.parseInt(scInput.nextLine());

						paymentdate = LocalDate.of(year, month, dayOfMonth);

						System.out.println("enter amount:");
						amount = scInput.nextInt();

						payment = new Payment(lease, paymentdate, amount);
						success = paymentservice.updatePayment(payment);

						if (success == 1) {
							System.out.println("record updated successfully...");
						}
						break;
					case 3:
						System.out.print("Enter payment ID: ");
						paymentid = scInput.nextInt();
						scInput.nextLine();

						success = paymentservice.deletePayment(paymentid);

						if (success == 0) {
							System.out.println("No such payment");
						} else {
							System.out.println("Record is deleted successfully...");
						}
						break;
					case 4:
						System.out.print("Enter payment ID: ");
						paymentid = scInput.nextInt();
						scInput.nextLine();

						payment = paymentservice.viewPayment(paymentid);

						if (payment == null) {
							System.out.println("No such payment");
						} else {
							System.out.println("The given payment: ");
							System.out.println(payment);
						}
						break;
					case 5:
						List<Payment> paymentList = paymentservice.viewPayments();
						if (paymentList == null) {
							System.out.println("No payments in table.");
						} else {
							System.out.println("Following are the payments:");
							for (Payment payment2 : paymentList) {
								System.out.println(payment2);
							}
						}
						break;
					case 0:
						System.out.println("You have chosen to exit.");
						break;
					default:
						System.out.println("Incorrect option");
						break;
					}
				}
			case 0:
				System.out.println("Exiting...");
				break;
			default:
				System.out.println("Incorrect option");
			}
		}
		scInput.close();
	}
}
