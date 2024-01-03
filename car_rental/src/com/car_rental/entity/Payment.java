package com.car_rental.entity;

import java.time.LocalDate;

public class Payment {
	private int paymentid;
	private Lease lease;
	private LocalDate paymentdate;
	private int amount;
	
	public Payment() {
		super();
	}

	public Payment(int paymentid, Lease lease, LocalDate paymentdate, int amount) {
		super();
		this.paymentid = paymentid;
		this.lease = lease;
		this.paymentdate = paymentdate;
		this.amount = amount;
	}

	public Payment(LocalDate paymentdate, int amount) {
		super();
		this.paymentdate = paymentdate;
		this.amount = amount;
	}

	public Payment(Lease lease, LocalDate paymentdate, int amount) {
		super();
		this.lease = lease;
		this.paymentdate = paymentdate;
		this.amount = amount;
	}

	public int getPaymentid() {
		return paymentid;
	}

	public void setPaymentid(int paymentid) {
		this.paymentid = paymentid;
	}

	public Lease getLease() {
		return lease;
	}

	public void setLease(Lease lease) {
		this.lease = lease;
	}

	public LocalDate getPaymentdate() {
		return paymentdate;
	}

	public void setPaymentdate(LocalDate paymentdate) {
		this.paymentdate = paymentdate;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	@Override
	public String toString() {
		return "Payment [paymentid=" + paymentid + ", lease=" + lease + ", paymentdate=" + paymentdate + ", amount="
				+ amount + "]";
	}

	
}
