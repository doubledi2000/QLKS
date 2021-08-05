package com.model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;


/**
 * The persistent class for the bill database table.
 * 
 */
@Entity
@Table(name="bill")
@NamedQuery(name="Bill.findAll", query="SELECT b FROM Bill b")
public class Bill implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="bill_code")
	private String billCode;

	@Column(name="bill_type")
	private boolean billType;
	
	private int refund;

	@Column(name="total_payment")
	private double totalPayment;
	
	
	@Column(name="checkout_date")
	private Date checkoutDate;

	//bi-directional many-to-one association to Booking
	@ManyToOne
	@JoinColumn(name="booking_code")
	private Booking booking;

	public Bill() {
	}

	public String getBillCode() {
		return this.billCode;
	}

	public void setBillCode(String billCode) {
		this.billCode = billCode;
	}

	public boolean getBillType() {
		return this.billType;
	}

	public void setBillType(boolean billType) {
		this.billType = billType;
	}

	public int getRefund() {
		return this.refund;
	}

	public void setRefund(int refund) {
		this.refund = refund;
	}


	public double getTotalPayment() {
		return totalPayment;
	}

	public void setTotalPayment(double totalPayment) {
		this.totalPayment = totalPayment;
	}

	public Booking getBooking() {
		return this.booking;
	}

	public void setBooking(Booking booking) {
		this.booking = booking;
	}

	public Date getCheckoutDate() {
		return checkoutDate;
	}

	public void setCheckoutDate(Date checkoutDate) {
		this.checkoutDate = checkoutDate;
	}

}