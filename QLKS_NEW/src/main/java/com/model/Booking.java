package com.model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the booking database table.
 * 
 */
@Entity
@Table(name="booking")
@NamedQuery(name="Booking.findAll", query="SELECT b FROM Booking b")
public class Booking implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="booking_code")
	private String bookingCode;

	@Column(name="booking_date")
	private Date bookingDate;

	@Column(name="checkin_date")
	private Date checkinDate;
	
	@Column(name="fines")
	private double fines;
	
	@Column(name="price_per_one")
	private double pricePerOne;
	
	private double deposit;

	private int extraPerson;

	private String note;
	private String refund;
	private int stt;

	@Column(name="total_room_charge")
	private double totalRoomCharge;
	//bi-directional many-to-one association to Bill
	@OneToMany(mappedBy="booking")
	private List<Bill> bills;

	//bi-directional many-to-one association to Customer
	@ManyToOne
	@JoinColumn(name="customer_code")
	private Customer customer;
	
	//bi-directional many-to-one association to Employee
	@ManyToOne
	@JoinColumn(name="employee_code")
	private Employee employee;

	//bi-directional many-to-one association to BookingDetail
	@OneToMany(mappedBy="booking")
	private List<BookingDetail> bookingDetails;

	public Booking() {
	}
	
	public String getRefund() {
		return refund;
	}

	public void setRefund(String refund) {
		this.refund = refund;
	}

	public double getPricePerOne() {
		return pricePerOne;
	}

	public void setPricePerOne(double pricePerOne) {
		this.pricePerOne = pricePerOne;
	}

	public double getTotalRoomCharge() {
		return totalRoomCharge;
	}

	public void setTotalRoomCharge(double totalRoomCharge) {
		this.totalRoomCharge = totalRoomCharge;
	}

	public double getFines() {
		return fines;
	}

	public void setFines(double fines) {
		this.fines = fines;
	}

	public String getBookingCode() {
		return this.bookingCode;
	}

	public void setBookingCode(String bookingCode) {
		this.bookingCode = bookingCode;
	}


	public Date getBookingDate() {
		return bookingDate;
	}

	public void setBookingDate(Date bookingDate) {
		this.bookingDate = bookingDate;
	}

	public Date getCheckinDate() {
		return checkinDate;
	}

	public void setCheckinDate(Date checkinDate) {
		this.checkinDate = checkinDate;
	}

	public double getDeposit() {
		return deposit;
	}

	public void setDeposit(double deposit) {
		this.deposit = deposit;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public int getExtraPerson() {
		return this.extraPerson;
	}

	public void setExtraPerson(int extraPerson) {
		this.extraPerson = extraPerson;
	}


	public int getStt() {
		return this.stt;
	}

	public void setStt(int stt) {
		this.stt = stt;
	}

	public List<Bill> getBills() {
		return this.bills;
	}

	public void setBills(List<Bill> bills) {
		this.bills = bills;
	}

	public Bill addBill(Bill bill) {
		getBills().add(bill);
		bill.setBooking(this);

		return bill;
	}

	public Bill removeBill(Bill bill) {
		getBills().remove(bill);
		bill.setBooking(null);
		return bill;
	}

	public Customer getCustomer() {
		return this.customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Employee getEmployee() {
		return this.employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public List<BookingDetail> getBookingDetails() {
		return this.bookingDetails;
	}

	public void setBookingDetails(List<BookingDetail> bookingDetails) {
		this.bookingDetails = bookingDetails;
	}

	public BookingDetail addBookingDetail(BookingDetail bookingDetail) {
		getBookingDetails().add(bookingDetail);
		bookingDetail.setBooking(this);

		return bookingDetail;
	}

	public BookingDetail removeBookingDetail(BookingDetail bookingDetail) {
		getBookingDetails().remove(bookingDetail);
		bookingDetail.setBooking(null);

		return bookingDetail;
	}

}