package com.domain;

import java.util.List;

import com.model.BookingDetail;

public class CheckOutForm {
	private String fullname;
	private String phoneNumber;
	private String addr;
	private double totalRoomCharge;
	private double deposit;
	private double fines;
	private double personFines;
	private double remaining;


	public CheckOutForm() {
	}


	public CheckOutForm(String fullname, String phoneNumber, String addr, double totalRoomCharge, double deposit,
			double fines, double personFines, double remaining) {
		this.fullname = fullname;
		this.phoneNumber = phoneNumber;
		this.addr = addr;
		this.totalRoomCharge = totalRoomCharge;
		this.deposit = deposit;
		this.fines = fines;
		this.personFines = personFines;
		
	}


	public String getFullname() {
		return fullname;
	}


	public void setFullname(String fullname) {
		this.fullname = fullname;
	}


	public String getPhoneNumber() {
		return phoneNumber;
	}


	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}


	public String getAddr() {
		return addr;
	}


	public void setAddr(String addr) {
		this.addr = addr;
	}


	public double getTotalRoomCharge() {
		return totalRoomCharge;
	}


	public void setTotalRoomCharge(double totalRoomCharge) {
		this.totalRoomCharge = totalRoomCharge;
	}


	public double getDeposit() {
		return deposit;
	}


	public void setDeposit(double deposit) {
		this.deposit = deposit;
	}


	public double getFines() {
		return fines;
	}


	public void setFines(double fines) {
		this.fines = fines;
	}


	public double getPersonFines() {
		return personFines;
	}


	public void setPersonFines(double personFines) {
		this.personFines = personFines;
	}


	public double getRemaining() {
		return remaining;
	}


	public void setRemaining(double remaining) {
		this.remaining = remaining;
	}


	
}
