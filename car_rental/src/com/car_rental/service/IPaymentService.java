package com.car_rental.service;

import java.util.List;

import com.car_rental.entity.Payment;

public interface IPaymentService {
	public int addPayment(Payment payment);
	public int updatePayment(Payment payment);
	public int deletePayment(int paymentid);
	public Payment viewPayment(int paymentid);
	public List<Payment>viewPayments();

}
