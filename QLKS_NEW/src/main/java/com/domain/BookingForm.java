package com.domain;

import java.util.Date;

public class BookingForm {
	private String bookingCode;
	private String fullname;
	private Date startDate;
	private Date endDate;
	
	public BookingForm() {
	}
	public BookingForm(String bookingCode, String fullname, Date startDate, Date endDate) {
		this.bookingCode = bookingCode;
		this.fullname = fullname;
		this.startDate = startDate;
		this.endDate = endDate;
	}
	public String getBookingCode() {
		return bookingCode;
	}
	public void setBookingCode(String bookingCode) {
		this.bookingCode = bookingCode;
	}
	public String getFullname() {
		return fullname;
	}
	public void setFullname(String fullname) {
		this.fullname = fullname;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
}
