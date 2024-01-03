package com.car_rental.dao;

import java.sql.SQLException;
import java.util.List;

import com.car_rental.entity.Payment;

public interface IPaymentDAO {
	public int addPayment(Payment payment)throws ClassNotFoundException,SQLException;
	public int updatePayment(Payment payment)throws ClassNotFoundException,SQLException;
	public int deletePayment(int paymentid)throws ClassNotFoundException,SQLException;
	public Payment viewPayment(int paymentid)throws ClassNotFoundException,SQLException;
	public List<Payment>viewPayments()throws ClassNotFoundException,SQLException;

}
