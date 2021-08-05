package com.model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;


/**
 * The persistent class for the booking_detail database table.
 * 
 */
@Entity
@Table(name="booking_detail")
@NamedQuery(name="BookingDetail.findAll", query="SELECT b FROM BookingDetail b")
public class BookingDetail implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	private double amount;

	@Column(name="booking_type")
	private boolean bookingType;

	@Column(name="end_day")
	private Date endDay;

	@Column(name="start_day")
	private Date startDay;
	
	private double price;

	//bi-directional many-to-one association to Booking
	@ManyToOne
	@JoinColumn(name="booking_code")
	private Booking booking;

	//bi-directional many-to-one association to Room
	@ManyToOne
	@JoinColumn(name="room_code")
	private Room room;

	public BookingDetail() {
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}
	public boolean getBookingType() {
		return this.bookingType;
	}

	public void setBookingType(boolean bookingType) {
		this.bookingType = bookingType;
	}
	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public Date getEndDay() {
		return endDay;
	}

	public void setEndDay(Date endDay) {
		this.endDay = endDay;
	}

	public Date getStartDay() {
		return startDay;
	}

	public void setStartDay(Date startDay) {
		this.startDay = startDay;
	}

	public Booking getBooking() {
		return this.booking;
	}

	public void setBooking(Booking booking) {
		this.booking = booking;
	}

	public Room getRoom() {
		return this.room;
	}

	public void setRoom(Room room) {
		this.room = room;
	}

}