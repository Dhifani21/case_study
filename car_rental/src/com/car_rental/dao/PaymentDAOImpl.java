package com.car_rental.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.car_rental.entity.Lease;
import com.car_rental.entity.Payment;
import com.car_rental.service.ILeaseService;
import com.car_rental.service.LeaseServiceImpl;
import com.car_rental.util.DBUtil;

public class PaymentDAOImpl implements IPaymentDAO {
	private static Connection connPayment;

	@Override
	public int addPayment(Payment payment) throws ClassNotFoundException,SQLException{
		connPayment = DBUtil.createConnection();
		String query = "INSERT INTO payment(paymentID, leaseID, paymentdate,amount) " + "VALUES(?,?,?,?)";

		Date paymentdate = Date.valueOf(payment.getPaymentdate());
		
		PreparedStatement prepareSt = connPayment.prepareStatement(query);
		prepareSt.setDate(3, paymentdate);
		prepareSt.setInt(4, payment.getAmount());
		prepareSt.setInt(2, payment.getLease().getLeaseId());
		prepareSt.setInt(1, payment.getPaymentid());
		
		int result = prepareSt.executeUpdate();

		DBUtil.closeConnection();
		return result;

	}

	@Override
	public int updatePayment(Payment payment) throws ClassNotFoundException,SQLException{
		connPayment = DBUtil.createConnection();
		String query = "UPDATE payment SET LeaseID=?, paymentdate=?, amount=? "
				+ "WHERE paymentID=?";

		Date paymentdate = Date.valueOf(payment.getPaymentdate());
		
		PreparedStatement prepareSt = connPayment.prepareStatement(query);
		prepareSt.setDate(2, paymentdate);
		prepareSt.setInt(1, payment.getLease().getLeaseId());
		prepareSt.setInt(3, payment.getAmount());
		prepareSt.setInt(4, payment.getPaymentid());
		
		int result = prepareSt.executeUpdate();

		DBUtil.closeConnection();
		return result;

	}

	@Override
	public int deletePayment(int paymentid) throws ClassNotFoundException,SQLException {
		Payment payment = null;

		LocalDate paymentdate = null;
		
		connPayment = DBUtil.createConnection();

		String queryCheck = "SELECT * FROM payment WHERE paymentID = ?";
		String queryDelete = "DELETE FROM payment WHERE paymentID = ?";
		
		int amount = 0;
		
		int success = 0;
		
		PreparedStatement prepareSt = connPayment.prepareStatement(queryCheck);
		PreparedStatement prepareStDelete = connPayment.prepareStatement(queryDelete);
		
		prepareSt.setInt(1, paymentid);
		prepareStDelete.setInt(1, paymentid);
		
		
		ResultSet rs = prepareSt.executeQuery();

		while (rs.next()) {// Till there are further records.
			paymentdate = rs.getDate("payment").toLocalDate();
			amount = rs.getInt("amount");
			
			payment = new Payment(paymentdate, amount);
		}
		

		if (payment == null) {
			success=0;
		}else {
			success = prepareStDelete.executeUpdate();
		}
		DBUtil.closeConnection();
		return success;

	}

	@Override
	public Payment viewPayment(int paymentid) throws ClassNotFoundException,SQLException {
		Payment payment = null;

		int leaseid = 0;
		LocalDate paymentdate = null;
		
		Lease lease = null;
		int amount=0;
		
		connPayment = DBUtil.createConnection();

		String queryCheck = "SELECT paymentID, LeaseID, paymentdate, amount "
				+ "FROM payment join lease "
				+ "USING(LeaseID) WHERE "
				+ "paymentID = ?";
						
		PreparedStatement prepareSt = connPayment.prepareStatement(queryCheck);
		prepareSt.setInt(1, paymentid);
		
		ResultSet rs = prepareSt.executeQuery();

		while (rs.next()) {// Till there are further records.
			leaseid = rs.getInt("LeaseID");
			paymentid = rs.getInt("paymentID");
			paymentdate = rs.getDate("paymentdate").toLocalDate();
			amount = rs.getInt("amount");
			
			lease = new Lease();
			lease.setLeaseId(leaseid);
		
			payment = new Payment(paymentid, lease, paymentdate, amount);
		}
		DBUtil.closeConnection();

		if (payment == null) {
			return null;
		}

		return payment;

	}

	@Override
	public List<Payment> viewPayments() throws ClassNotFoundException,SQLException {
		ILeaseService leaseservice = new LeaseServiceImpl();
		List<Payment> payments = new ArrayList<>();
		Payment payment = null;

		int leaseid = 0;
		LocalDate paymentdate = null;
		
		Lease lease = null;

		connPayment = DBUtil.createConnection();

		String query = "SELECT * FROM payment";

		int amount = 0;
		int paymentid = 0;
		
		PreparedStatement prepareSt = connPayment.prepareStatement(query);

		ResultSet rs = prepareSt.executeQuery();

		while (rs.next()) {// Till there are further records.
			paymentid = rs.getInt("paymentID");
			leaseid = rs.getInt("LeaseID");
			paymentdate = rs.getDate("paymentdate").toLocalDate();
			amount = rs.getInt("amount");
			lease = leaseservice.viewLease(leaseid);
		
			payment = new Payment(paymentid, lease, paymentdate, amount);
			payments.add(payment);
		}
		DBUtil.closeConnection();

		if (payments.size() == 0) {
			return null;
		}

		return payments;

	}

}
