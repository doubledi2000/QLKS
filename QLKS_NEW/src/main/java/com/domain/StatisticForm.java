package com.domain;

public class StatisticForm {
	private int day;
	private int month;
	private int year;
	private double deposit;
	private double roomFines;
	private double personFines;
	private double rest;
	private double repay;
	private double revenue;
	
	
	
	public StatisticForm() {

	}
	public StatisticForm(int month, int year, double deposit, double roomFines, double personFines, double rest,
			double repay) {
		this.month = month;
		this.year = year;
		this.deposit = deposit;
		this.roomFines = roomFines;
		this.personFines = personFines;
		this.rest = rest;
		this.repay = repay;
		this.revenue = deposit + roomFines + personFines + rest - repay;
	}
	public StatisticForm(int day, int month, int year, double deposit, double roomFines, double personFines,
			double rest, double repay) {
		this.day = day;
		this.month = month;
		this.year = year;
		this.deposit = deposit;
		this.roomFines = roomFines;
		this.personFines = personFines;
		this.rest = rest;
		this.repay = repay;
		this.revenue = deposit + roomFines + personFines + rest - repay;
	}
	
	public int getDay() {
		return day;
	}
	public void setDay(int day) {
		this.day = day;
	}
	public int getMonth() {
		return month;
	}
	public void setMonth(int month) {
		this.month = month;
	}
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public double getDeposit() {
		return deposit;
	}
	public void setDeposit(double deposit) {
		this.deposit = deposit;
	}
	public double getRoomFines() {
		return roomFines;
	}
	public void setRoomFines(double roomFines) {
		this.roomFines = roomFines;
	}
	public double getPersonFines() {
		return personFines;
	}
	public void setPersonFines(double personFines) {
		this.personFines = personFines;
	}
	public double getRest() {
		return rest;
	}
	public void setRest(double rest) {
		this.rest = rest;
	}
	public double getRepay() {
		return repay;
	}
	public void setRepay(double repay) {
		this.repay = repay;
	}
	public double getRevenue() {
		return revenue;
	}
	public void setRevenue(double revenue) {
		this.revenue = revenue;
	}
	
	
}
