package com.car_rental.service;

import java.sql.SQLException;
import java.util.List;

import com.car_rental.dao.IPaymentDAO;
import com.car_rental.dao.PaymentDAOImpl;
import com.car_rental.entity.Payment;

public class PaymentServiceImpl implements IPaymentService {
private IPaymentDAO paymentdao;
	
	public PaymentServiceImpl() {
		this.paymentdao=new PaymentDAOImpl();
	}

	@Override
	public int addPayment(Payment payment){
		int result = 0;
		try {
			result = paymentdao.addPayment(payment);
		} catch (ClassNotFoundException cnfe) {
			System.out.println("Looks like JDBC driver is NOT loaded.");
		} catch (SQLException se) {
			se.printStackTrace();
			System.out.println("Either url, username or password is wrong or duplicate record");
		}
		return result;
	}

	@Override
	public int updatePayment(Payment payment) {
		int result = 0;
		try {
			result = paymentdao.updatePayment(payment);
		} catch (ClassNotFoundException cnfe) {
			System.out.println("Looks like JDBC driver is NOT loaded.");
		}catch(SQLException se) {
			System.out.println("Either url, username or password is wrong or duplicate record");
			se.printStackTrace();
		}
		return result;
	}

	@Override
	public int deletePayment(int paymentid) {
		int result = 0;
		try {
			result = paymentdao.deletePayment(paymentid);
			}catch (ClassNotFoundException cnfe) {
				System.out.println("Looks like JDBC driver is NOT loaded.");
			}catch(SQLException se) {
				System.out.println("Either url, username or password is wrong or duplicate record");
			}
		return result;
	}

	@Override
	public Payment viewPayment(int paymentid) {
		Payment payment = null;

		try {
			payment = paymentdao.viewPayment(paymentid);
		} catch (ClassNotFoundException cnfe) {
			System.out.println("Looks like JDBC driver is NOT loaded.");
		} catch (SQLException se) {
			System.out.println("Either url, username or password is wrong or duplicate record");
		}
		return payment;
	}

	@Override
	public List<Payment> viewPayments() {
		List<Payment> paymentList = null;

		try {
			paymentList = paymentdao.viewPayments();
		} catch (ClassNotFoundException cnfe) {
			System.out.println("Looks like JDBC driver is NOT loaded.");
		} catch (SQLException se) {
			System.out.println("Either url, username or password is wrong or duplicate record");
		}
		return paymentList;
	}

}
